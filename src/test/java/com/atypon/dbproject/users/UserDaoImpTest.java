package com.atypon.dbproject.users;

import com.atypon.dbproject.dao.UserDao;
import com.atypon.dbproject.dao.daoImp.UserDaoImp;
import com.atypon.dbproject.entity.Role;
import com.atypon.dbproject.entity.User;
import com.atypon.dbproject.securityconf.SHA512HashAlgo;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoImpTest {


    @Test
    public void testCreateUser() {
        UserDao userDao = new UserDaoImp(new SHA512HashAlgo());
        User user = new User.UserBuilder().username("tester").password("123456").role(Role.valueOf("EDITOR")).build();
        userDao.createUser(user);
        assertTrue(userDao.userExists("tester"));
    }


    @Test
    public void testExistingUser() {
        UserDao userDao = new UserDaoImp(new SHA512HashAlgo());
        assertTrue(userDao.userExists("admin@atypon.com"));
        assertTrue(userDao.userExists("mamoun@atypon.com"));
        assertTrue(userDao.userExists("ahmad@atypon.com"));
        assertFalse(userDao.userExists("nouser"));
        assertFalse(userDao.userExists("letmein"));
        assertFalse(userDao.userExists("koushema"));
    }

    @Test
    public void testUserCredential(){
        UserDao userDao = new UserDaoImp(new SHA512HashAlgo());
        User user = userDao.verifyCredentials("ahmad@atypon.com","12345678");
        assertEquals("ahmad@atypon.com",user.getUsername());
        assertEquals("VIEWER",user.getRole().toString());


        user = userDao.verifyCredentials("mamoun@atypon.com","12342222");
        assertEquals("mamoun@atypon.com",user.getUsername());
        assertEquals("NOT_USER",user.getRole().toString());

        user = userDao.verifyCredentials("mamounS","12345678");
        assertNull(user.getUsername());
        assertEquals("NOT_USER",user.getRole().toString());

        user = userDao.verifyCredentials("mamoun@atypon.com","12345678");
        assertEquals("mamoun@atypon.com",user.getUsername());
        assertEquals("EDITOR",user.getRole().toString());

    }

    @Test
    public void testDeleteUser(){
        UserDao userDao = new UserDaoImp(new SHA512HashAlgo());
        userDao.deleteUser("tester");
        assertFalse(userDao.userExists("tester"));
    }






}