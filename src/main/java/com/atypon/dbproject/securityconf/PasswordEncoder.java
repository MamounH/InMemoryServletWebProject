package com.atypon.dbproject.securityconf;

public interface PasswordEncoder {

    byte[] getSalt();
    byte[] getSaltedHash(String password, byte[] salt);
    byte[] fromHex(String hex);
    String toHex(byte[] array);
}
