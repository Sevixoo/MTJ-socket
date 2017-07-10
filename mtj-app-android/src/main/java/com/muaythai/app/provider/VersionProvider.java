package com.muaythai.app.provider;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by pi19124 on 12.06.2017.
 */

public class VersionProvider {

    private Context mContext;

    public VersionProvider(Context mContext) {
        this.mContext = mContext;
    }

    public String getVersionName(){
        PackageInfo pInfo = null;
        try {
            pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
