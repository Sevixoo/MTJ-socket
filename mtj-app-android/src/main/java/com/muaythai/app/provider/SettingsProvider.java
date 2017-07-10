package com.muaythai.app.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by pi19124 on 13.06.2017.
 */

public class SettingsProvider {

    private SharedPreferences mPrefs;

    public SettingsProvider(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public int getPortNumber(){
        return Integer.parseInt( mPrefs.getString("port_number", "8080") );
    }

    public boolean isStaticIPAddres() {
        return mPrefs.getBoolean("use_custom_ip_address", false);
    }

    public String getStaticIPAddress() {
        return mPrefs.getString("default_ip", "10.0.0.1");
    }
}
