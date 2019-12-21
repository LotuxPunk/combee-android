package com.example.combee;

import java.util.ArrayList;

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

    public static String joinedString(ArrayList<String> strings) {
        int i = 0;
        String joined = "?";
        while(i < strings.size()) {
            joined += "&" + strings.get(i);
            i++;
        }

        return joined;
    }

    public static String priceCategoryString(Integer nb) {
        String strPrice = "";

        for(int i = 0; i < nb; i++) strPrice += "€";

        return strPrice;
    }
}
