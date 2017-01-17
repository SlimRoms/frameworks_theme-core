package com.slimroms.themecore;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;

public abstract class BaseThemeService extends Service {
    private final ComponentName mBackendName = new ComponentName(this, this.getClass());

    public abstract BaseThemeHelper getThemeHelper();

    protected abstract String getThemeType();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IThemeService.Stub mBinder = getThemeHelper();

    protected Theme createTheme(String name, String packageName, int themeVersion) {
        return new Theme(mBackendName, name, packageName, getThemeType(), themeVersion);
    }
}
