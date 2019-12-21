package com.example.combee.model;

public class Token {
    private String accessToken;
    private Integer expireIn;
    private String refreshToken;

    public Token(String accessToken, Integer expireIn, String refreshToken) {
        this.accessToken = accessToken;
        this.expireIn = expireIn;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Integer expireIn) {
        this.expireIn = expireIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
