package com.atypon.dbproject.securityconf;

public interface IPasswordHash {

    byte[] getSalt();
    byte[] getSaltedHash(String password, byte[] salt);
    byte[] fromHex(String hex);
    String toHex(byte[] array);

}
