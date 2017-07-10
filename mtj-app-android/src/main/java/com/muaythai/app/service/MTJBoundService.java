package com.muaythai.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by pi19124 on 12.06.2017.
 */

public class MTJBoundService extends Service {

    public class MTJServiceBinder extends Binder{
        MTJBoundService getService(){
            return MTJBoundService.this;
        }
    }

    public MTJBoundService() { }

    @Nullable @Override
    public IBinder onBind(Intent intent) {
        return new MTJServiceBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
