package com.vecindapp.utils;

public class JwtResponse {

    private String token;
    private Integer userId;
    private Integer rolId;

    public JwtResponse(String token, Integer userId, Integer rolId) {
        this.token = token;
        this.userId = userId;
        this.rolId = rolId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRolId() {
        return rolId;
    }

    public void setRolId(Integer rolId) {
        this.rolId = rolId;
    }
}
