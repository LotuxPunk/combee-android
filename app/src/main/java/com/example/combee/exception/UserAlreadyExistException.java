package com.example.combee.exception;

import androidx.annotation.Nullable;

public class UserAlreadyExistException extends Exception {
    @Nullable
    @Override
    public String getMessage() {
        return "User already exist. Please retry with another email.";
    }
}
