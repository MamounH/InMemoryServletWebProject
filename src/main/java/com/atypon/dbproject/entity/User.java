package com.atypon.dbproject.entity;

public class User {

    String username;
    String fName;
    String lName;
    String salt;
    String password;
    Role role;

    @Override
    public String toString() {
        return fName + "," + lName + "," + username + "," + password + ","
                + salt + "," + role.toString() +"\n";
    }

    private User(UserBuilder builder){
        this.username=builder.username;
        this.password=builder.password;
        this.salt=builder.salt;
        this.role=builder.role;
        this.fName=builder.fName;
        this.lName=builder.lName;
    }

    public static class UserBuilder {

        private String username;
        private String fName;
        private String lName;
        private String password;
        private String salt;
        private Role role;
        private boolean isEnabled;


        public UserBuilder username (String username){
            this.username=username;
            return this;
        }
        public UserBuilder fName (String fName){
            this.fName=fName;
            return this;
        }

        public UserBuilder lName (String lName){
            this.lName=lName;
            return this;
        }



        public UserBuilder password (String password){
            this.password=password;
            return this;
        }

        public UserBuilder salt (String salt){
            this.salt=salt;
            return this;
        }

        public UserBuilder role (Role role){
            this.role=role;
            return this;
        }

        public UserBuilder isEnabled (Boolean isEnabled){
            this.isEnabled=isEnabled;
            return this;
        }


        public  User build(){
            return new User(this);
        }


    }

    public Role getRole() { return role; }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }




}