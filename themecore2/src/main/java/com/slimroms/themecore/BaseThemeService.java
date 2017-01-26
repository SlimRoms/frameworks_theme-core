package com.slimroms.themecore;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;

import java.util.HashMap;

public abstract class BaseThemeService extends Service {
    private ComponentName mBackendName;
    private HashMap<String, Theme> mThemes;

    public abstract BaseThemeHelper getThemeHelper();

    protected abstract String getThemeType();

    @Override
    public void onCreate() {
        super.onCreate();
        mBackendName =  new ComponentName(this, this.getClass());
        mThemes = new HashMap<>();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IThemeService.Stub mBinder = getThemeHelper();

    protected Theme createTheme(String name, String packageName, String themeVersion, String themeAuthor,
                                Bitmap themeLogo) {
        final Theme theme = new Theme(mBackendName, name, packageName, getThemeType(), themeVersion, themeAuthor,
                themeLogo);
        mThemes.put(packageName, theme);
        return theme;
    }

    protected Theme getTheme(String packageName) {
        return mThemes.get(packageName);
    }
}
