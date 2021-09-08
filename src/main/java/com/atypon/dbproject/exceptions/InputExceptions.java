package com.atypon.dbproject.exceptions;


public class InputExceptions {

    private InputExceptions() {
        throw new AssertionError();
    }

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

    public static boolean isEmpty(final String str) {
        return str.isEmpty();
    }


}
