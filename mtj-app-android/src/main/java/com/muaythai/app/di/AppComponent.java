package com.muaythai.app.di;

import com.muaythai.app._old.ClientActivity;
import com.muaythai.app._old.ServerActivity;
import com.muaythai.app.ui.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by pi19124 on 12.06.2017.
 */
@Singleton
@Component(modules = { AppModule.class , BackendModule.class })
public interface AppComponent {

    void inject(SplashActivity splashActivity);

    void inject(ServerActivity serverActivity);

    void inject(ClientActivity clientActivity);
}
