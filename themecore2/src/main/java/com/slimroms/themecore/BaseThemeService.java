package com.slimroms.themecore;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public abstract class BaseThemeService extends Service {

    public abstract BaseThemeHelper getThemeHelper();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IThemeService.Stub mBinder = getThemeHelper();
}
