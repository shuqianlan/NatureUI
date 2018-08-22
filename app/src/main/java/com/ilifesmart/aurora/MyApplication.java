package com.ilifesmart.aurora;

import android.app.Application;
import android.content.Context;

import com.ilifesmart.aurora.config.SettingItemsHelper;

public class MyApplication extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sContext = getApplicationContext();
        SettingItemsHelper.init();
    }

    public static Context getContext() {
        return sContext;
    }
}
