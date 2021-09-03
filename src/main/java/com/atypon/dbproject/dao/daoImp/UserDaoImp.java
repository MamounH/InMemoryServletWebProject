package com.atypon.dbproject.dao.daoImp;

import com.atypon.dbproject.dao.UserDao;
import com.atypon.dbproject.entity.Role;
import com.atypon.dbproject.entity.User;
import com.atypon.dbproject.securityconf.IPasswordHash;

import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoImp implements UserDao {

    IPasswordHash passwordHash;


    private static final String DBPATH = "./src/main/resources/users.txt";
    private static final String TEMPPATH = "./src/main/resources/userstemp.txt";
    private final Logger logger = Logger.getLogger("UserDao");

    File file = new File(DBPATH);
    File newFile = new File(TEMPPATH);


    public UserDaoImp(IPasswordHash passwordHash){
        this.passwordHash=passwordHash;
    }


    @Override
    public User getUser(String username){
        try(Scanner scanner =new Scanner(new File(DBPATH)) ){
            return retrieveUserFromDB(username, scanner);
        } catch (Exception e) {
            logError(e);
        }
        return null;
    }

    @Override
    public List<User> getAll(){

        List<User> users = new ArrayList<>();

        try (FileReader fileReader = new FileReader(DBPATH);
             BufferedReader bufferedReader= new BufferedReader(fileReader)) {
             loadUsers(users, bufferedReader);
        } catch (Exception e) {
            logError(e);
        }
        return users;

    }

    private void loadUsers(List<User> users, BufferedReader bufferedReader) throws IOException {

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");

            User user = new User.UserBuilder().fName(data[0]).lName(data[1]).username(data[2])
                    .role(Role.valueOf(data[5])).build();
            users.add(user);
        }
    }

    @Override
    public void updateUser(User user, String email){

        try(FileReader fileReader =new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter(newFile)) {
            configureUserPassword(user);
            updateExistingRecord(user,email, bufferedReader, fileWriter);
        } catch (Exception e) {
            logError(e);
        }

        handleTempFile();
    }


    private void updateExistingRecord(User user,String email, BufferedReader bufferedReader, FileWriter fileWriter) throws IOException {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");

            if (email.equals(data[2])) {
                fileWriter.write(user.toString());
            } else {
                fileWriter.write(line + "\n");
            }
        }
    }

    @Override
    public void createUser(User user) {
        configureUserPassword(user);
        saveUser(user);
    }

    private void configureUserPassword(User user) {

        byte[] byteSalt = passwordHash.getSalt();
        byte[] byteDigestPassword = passwordHash.getSaltedHash(user.getPassword(),byteSalt);
        String strDigestPassword= passwordHash.toHex(byteDigestPassword);
        String strSalt = passwordHash.toHex(byteSalt);

        user.setPassword(strDigestPassword);
        user.setSalt(strSalt);
    }

    private void saveUser(User user) {

        try (FileWriter fileWriter = new FileWriter(DBPATH, true)){
            addUserToDb(user, fileWriter);
        } catch (Exception e){
            logError(e);
        }
    }

    @Override
    public void deleteUser(String username) {
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader =new BufferedReader(fileReader);
             FileWriter fileWriter=new FileWriter(newFile) ){

            deleteUserFromDB(username, bufferedReader, fileWriter);
        } catch (Exception e) {
            logError(e);
        }
        handleTempFile();
    }

    @Override
    public User verifyCredentials(String username, String password) {

        User user = new User.UserBuilder().build();

        if (userExists(username)) {
            user.setUsername(username);
            user.setPassword(password);
            user = retrieveUser(user);
            String strOriginalSalt = user.getSalt();

            byte[] byteSalt = passwordHash.fromHex(strOriginalSalt);
            byte[] loginPassword = passwordHash.getSaltedHash(password, byteSalt);
            byte[] storedPassword = passwordHash.fromHex(user.getPassword());
            boolean isMatched = Arrays.equals(loginPassword, storedPassword);
            if (isMatched) {
                return user;
            }
        }
        user.setRole(Role.NOT_USER);
        return user;
    }

    @Override
    public boolean userExists(String username){
        boolean isFound = false;
        try (Scanner scanner =new Scanner(new FileReader(DBPATH)) ){
            while(scanner.hasNextLine() && !isFound) {
                isFound = scanner.nextLine().contains(username);
            }
        }
        catch(IOException e) {
            logError(e);
        }

        return isFound;
}

    public User retrieveUser(User user) {

        try(Scanner scanner =new Scanner(new File(DBPATH)) ){
            return retrieveUserFromDB(user.getUsername(), scanner);
        } catch (Exception e) {
            logError(e);
        }
        return user;
    }

    private User retrieveUserFromDB(String username, Scanner scanner) {
        String fName;
        String lName;
        String tempUsername;
        String tempPassword;
        String tempSalt;
        String tempRole;
        scanner.useDelimiter("[,\n]");
        while (scanner.hasNext()) {
            fName=scanner.next();
            lName=scanner.next();
            tempUsername = scanner.next();
            tempPassword = scanner.next();
            tempSalt = scanner.next();
            tempRole= scanner.next();
            if (tempUsername.trim().equalsIgnoreCase(username.trim())) {

                return new User.UserBuilder().fName(fName).lName(lName).username(tempUsername).salt(tempSalt)
                                .password(tempPassword).role(Role.valueOf(tempRole)).build();
            }
        }
        return new User.UserBuilder().build();
    }

    private void deleteUserFromDB(String username, BufferedReader bufferedReader, FileWriter fileWriter) throws IOException {
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");
            if (username.equals(data[2])) {
                System.out.println("Successfully Deleted : " + username);
            } else {
                fileWriter.write(line + "\n");
            }
        }
        fileWriter.flush();
    }


    private void addUserToDb(User user, FileWriter fileWriter) throws IOException {
        fileWriter.write(user.toString());
        fileWriter.flush();
    }


    private void handleTempFile()  {
        try {
            Files.delete(file.toPath());
            newFile.renameTo(file);
        } catch (Exception e){
            logError(e);
        }
    }

    private void logError (Exception e){
        logger.log(Level.SEVERE,e.getMessage());
    }

}
