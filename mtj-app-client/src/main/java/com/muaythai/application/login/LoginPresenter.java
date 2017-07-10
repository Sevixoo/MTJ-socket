package com.muaythai.application.login;

import com.muaythai.application.connection.rest.MTJRestClient;
import com.muaythai.core.common.BasePresenter;
import com.muaythai.core.common.UI;
import com.muaythai.core.login.LoginRequest;
import com.muaythai.core.login.LoginResponse;

import rx.Scheduler;
import rx.functions.Action1;

/**
 * Created by pi19124 on 12.06.2017.
 */
public class LoginPresenter extends BasePresenter<LoginPresenter.View> {

    private LoginUseCase mLoginUseCase;
    private MTJRestClient mMTJRestClient;

    public LoginPresenter(Scheduler viewThreadScheduler, LoginUseCase loginUseCase) {
        super(viewThreadScheduler);
        mLoginUseCase = loginUseCase;
    }

    public void initialize(){
    }

    public void login( String name, String password ){
        LoginRequest loginRequest = new LoginRequest();
        executeUseCase(mLoginUseCase,loginRequest).subscribe(new Action1<LoginResponse>() {
            @Override
            public void call(LoginResponse loginResponse) {

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }

    public interface View extends UI{

    }
}
