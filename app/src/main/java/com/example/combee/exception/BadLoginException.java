package com.example.combee.exception;

import androidx.annotation.Nullable;

public class BadLoginException extends Exception {
    @Nullable
    @Override
    public String getMessage() {
        return "Login and/or password doesn't exist. Please retry!";
    }
}
