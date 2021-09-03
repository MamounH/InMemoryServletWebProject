//package com.atypon.dbproject.users;
//
//import com.atypon.dbproject.securityconf.IPasswordHash;
//import com.atypon.dbproject.securityconf.SHA512HashAlgo;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class SHA512HashAlgoTest {
//
//
//    @Test
//    public void testPasswordHashing(){
//
//        IPasswordHash passwordHash = new SHA512HashAlgo();
//
//        String password= "DevOpsTraining";
//        String testPass= "DevOpsTraining";
//        String testPass2="DevOpsTraining1";
//
//
//        byte[] byteSalt = passwordHash.getSalt();
//        byte[] byteDigestPassword = passwordHash.getSaltedHash(password,byteSalt);
//
//        String strDigestPassword= passwordHash.toHex(byteDigestPassword);
//        String strSalt = passwordHash.toHex(byteSalt);
//
//
//        String strOriginalSalt = strSalt;
//        byte[] byteSalt1 = passwordHash.fromHex(strOriginalSalt);
//        byte[] loginPassword = passwordHash.getSaltedHash(testPass, byteSalt1);
//        byte[] storedPassword = passwordHash.fromHex(strDigestPassword);
//
//        byte[] loginPassword1 = passwordHash.getSaltedHash(testPass2, byteSalt1);
//
//
//        assertArrayEquals(loginPassword,storedPassword);
//        assertNotEquals(loginPassword1,storedPassword);
//
//    }
//
//}