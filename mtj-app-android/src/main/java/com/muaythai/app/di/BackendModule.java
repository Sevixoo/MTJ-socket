package com.muaythai.app.di;

import com.muaythai.app.provider.SettingsProvider;
import com.muaythai.application.connection.p2p.IConnectifyProvider;
import com.muaythai.application.connection.p2p.MTJClient;
import com.muaythai.application.connection.rest.MTJRestClient;
import com.muaythai.application.login.LoginUseCase;
import com.muaythai.core.protocol.rest.IRestClient;
import com.muaythai.core.protocol.rest.MTJService;
import com.muaythai.core.protocol.rest.REST;
import com.muaythai.server.connection.p2p.MTJServer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pi19124 on 12.06.2017.
 */
@Module
public class BackendModule {

    @Provides
    MTJClient provideMTJClient(SettingsProvider settingsProvider, IConnectifyProvider connectifyProvider){
        return new MTJClient(connectifyProvider,settingsProvider.getPortNumber());
    }

    @Provides
    MTJServer provideMTJServer(SettingsProvider settingsProvider){
        return new MTJServer(settingsProvider.getPortNumber());
    }

    @Provides
    IRestClient provideMTJRestClient(){
        return new MTJRestClient();
    }

    @Provides
    MTJService provideMTJService(IRestClient restClient){
        return REST.create(MTJService.class,restClient);
    }

    @Provides
    LoginUseCase provideLoginUseCase(MTJService mtjService){
        return new LoginUseCase(mtjService);
    }

}
