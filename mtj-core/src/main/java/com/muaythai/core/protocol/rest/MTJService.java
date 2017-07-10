package com.muaythai.core.protocol.rest;

import com.muaythai.core.login.LoginRequest;
import com.muaythai.core.login.LoginResponse;

import java.io.IOException;

/**
 * Created by pi19124 on 12.06.2017.
 */
public interface MTJService{

    LoginResponse login(LoginRequest request) throws IOException;

}
