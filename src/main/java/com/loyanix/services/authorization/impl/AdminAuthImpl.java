package com.loyanix.services.authorization.impl;

import com.loyanix.services.authorization.AdminAuth;

public class AdminAuthImpl implements AdminAuth {

    private String password = "password";

    @Override
    public Boolean authenticate(String password) {
        return password.equals(this.password);
    }
}
