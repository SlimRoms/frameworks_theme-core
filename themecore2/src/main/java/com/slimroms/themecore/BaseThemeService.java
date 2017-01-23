package com.slimroms.themecore;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;

public abstract class BaseThemeService extends Service {
    private ComponentName mBackendName;

    public abstract BaseThemeHelper getThemeHelper();

    protected abstract String getThemeType();

    @Override
    public void onCreate() {
        super.onCreate();
        mBackendName =  new ComponentName(this, this.getClass());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IThemeService.Stub mBinder = getThemeHelper();

    protected Theme createTheme(String name, String packageName, String themeVersion, String themeAuthor,
                                Bitmap themeLogo) {
        return new Theme(mBackendName, name, packageName, getThemeType(), themeVersion, themeAuthor,
                themeLogo);
    }
}
