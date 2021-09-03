package com.atypon.dbproject.exceptions;


public class InputExceptions {

    public static boolean isNull(final Object reference) {
        String checker = reference.toString();
        String[] newStrings = checker.split(",");
        for (String newString : newStrings) {
            if (isEmpty(newString)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmptyCredentials(String username, String password){
        return  username.isEmpty() ||password.isEmpty();
    }

    public static boolean isEmpty(final String str) {
        return str.isEmpty();
    }


}
