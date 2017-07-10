package com.muaythai.application.login;

import com.muaythai.core.protocol.rest.MTJService;
import com.muaythai.core.common.UseCase;
import com.muaythai.core.login.LoginRequest;
import com.muaythai.core.login.LoginResponse;

/**
 * Created by pi19124 on 12.06.2017.
 */

public class LoginUseCase implements UseCase<LoginResponse,LoginRequest>{

    private MTJService mMtjService;

    public LoginUseCase(MTJService mMtjService) {
        this.mMtjService = mMtjService;
    }

    @Override
    public LoginResponse execute(LoginRequest loginRequest)throws Exception{
        return mMtjService.login(loginRequest);
    }

}
