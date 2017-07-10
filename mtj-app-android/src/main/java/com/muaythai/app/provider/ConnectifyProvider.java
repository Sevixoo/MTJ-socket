package com.muaythai.app.provider;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import com.muaythai.application.connection.p2p.IConnectifyProvider;

/**
 * Created by pi19124 on 12.06.2017.
 */

public class ConnectifyProvider implements IConnectifyProvider {

    private Context mContext;

    private SettingsProvider mSettingsProvider;

    public ConnectifyProvider(Context context, SettingsProvider settingsProvider) {
        mContext = context;
        mSettingsProvider = settingsProvider;
    }

    @Override
    public String provideIPAddress() {
        if(mSettingsProvider.isStaticIPAddres()){
           return mSettingsProvider.getStaticIPAddress();
        }else{
            WifiManager wifiMgr = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
            int ip = wifiInfo.getIpAddress();
            return Formatter.formatIpAddress(ip);
        }
    }

}
