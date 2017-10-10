package com.bwei.fragment.projectone;

import android.support.v7.app.AppCompatDelegate;

import com.mob.MobApplication;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by User on 2017/9/5.
 */

public class App extends MobApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        Config.DEBUG=true;
        PlatformConfig.setQQZone("1106036236","mjFCi0oxXZKZEWJs");
        UMShareAPI.get(this);
    }
}
