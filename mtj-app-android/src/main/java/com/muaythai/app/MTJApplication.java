package com.muaythai.app;

import android.app.Application;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import com.muaythai.app.di.AppComponent;
import com.muaythai.app.di.AppModule;
import com.muaythai.app.di.BackendModule;
import com.muaythai.app.di.DaggerAppComponent;
import com.muaythai.application.connection.p2p.IConnectifyProvider;
import com.muaythai.application.connection.p2p.MTJClient;
import com.muaythai.application.login.LoginUseCase;
import com.muaythai.application.connection.rest.MTJRestClient;
import com.muaythai.core.protocol.rest.MTJService;
import com.muaythai.core.protocol.rest.REST;
import com.muaythai.server.connection.p2p.MTJServer;

/**
 * Created by pi19124 on 09.06.2017.
 */

public class MTJApplication extends Application {

    public static MTJApplication get(Context context){
        return (MTJApplication)context.getApplicationContext();
    }

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .backendModule(new BackendModule())
                .build();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

}
