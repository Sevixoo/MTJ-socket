package com.muaythai.app.app;

import com.muaythai.core.common.UseCase;

import javax.inject.Inject;

/**
 * Created by pi19124 on 12.06.2017.
 */

public class CheckAuthorizationUseCase implements UseCase<Boolean,Boolean>{

    @Inject
    public CheckAuthorizationUseCase() { }

    @Override
    public Boolean execute(Boolean aBoolean) throws Exception {
        Thread.sleep(1000);
        return true;
    }
}
