package com.muaythai.app.di;

import android.content.Context;

import com.muaythai.app.provider.SettingsProvider;
import com.muaythai.app.provider.VersionProvider;
import com.muaythai.app.provider.ConnectifyProvider;
import com.muaythai.application.connection.p2p.IConnectifyProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pi19124 on 12.06.2017.
 */
@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    IConnectifyProvider provideConnectifyProvider(SettingsProvider settingsProvider){
        return new ConnectifyProvider(mContext,settingsProvider);
    }

    @Provides
    VersionProvider provideVersionProvider(){
        return new VersionProvider(mContext);
    }

    @Provides
    SettingsProvider provideSettingsProvider(){
        return new SettingsProvider(mContext);
    }

}
