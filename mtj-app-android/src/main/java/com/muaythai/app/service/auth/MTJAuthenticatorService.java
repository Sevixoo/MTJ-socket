package com.muaythai.app.service.auth;

import android.accounts.AbstractAccountAuthenticator;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import javax.inject.Inject;

/**
 * Created by pi19124 on 12.06.2017.
 */

public class MTJAuthenticatorService extends Service {

    @Inject AbstractAccountAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}
