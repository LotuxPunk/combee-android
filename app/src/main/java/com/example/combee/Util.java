package com.example.combee;

public class Util {
    public static boolean isInputEmpty(String inputValue) {
        return inputValue.matches("");
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$");
    }

    public static boolean isStrongPassword(String password) {
        return password.matches("^((?=.*[a-z])(?=.*[A-Z])(?=.*\\d)).+$");
    }
}
