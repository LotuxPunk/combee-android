package com.example.combee.exception;

import android.content.res.Resources;

import androidx.annotation.Nullable;

import com.example.combee.R;

public class BadRequestException extends Exception {
    @Nullable
    @Override
    public String getMessage() {
        return "One or more validation errors occurred.";
    }
}
