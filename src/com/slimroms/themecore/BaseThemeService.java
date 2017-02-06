package com.slimroms.themecore;

import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.NotificationCompat;

import com.slimroms.themecore.R;

import java.util.HashMap;

public abstract class BaseThemeService extends Service {
    private ComponentName mBackendName;
    private HashMap<String, Theme> mThemes;

    private static final int NOTIFICATION_INSTALL_ID = 1001;
    private NotificationManager mNotifyManager;

    public abstract BaseThemeHelper getThemeHelper();

    protected abstract String getThemeType();

    @Override
    public void onCreate() {
        super.onCreate();
        mBackendName =  new ComponentName(this, this.getClass());
        mThemes = new HashMap<>();

        mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
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

    private void showNotification(int notificationId, @DrawableRes int icon, @StringRes int text) {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(icon);
        builder.setContentText(getString(text));
        builder.setStyle(new NotificationCompat.BigTextStyle());
        mNotifyManager.notify(notificationId, builder.build());
    }

    private void showOngoingNotification(int notificationId, @DrawableRes int icon, @StringRes int text,
                                         int max, int progress) {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(icon);
        builder.setContentText(getString(text));
        builder.setProgress(max, progress, false);
        builder.setNumber(max);
        builder.setStyle(new NotificationCompat.BigTextStyle());
        builder.setAutoCancel(false);
        builder.setOngoing(true);
        mNotifyManager.notify(notificationId, builder.build());
    }

    protected void notifyInstallProgress(int max, int progress) {
        showOngoingNotification(NOTIFICATION_INSTALL_ID, R.drawable.auto_fix,
                R.string.notification_install_progress, max, progress);
    }

    protected void notifyInstallComplete() {
        showNotification(NOTIFICATION_INSTALL_ID, R.drawable.check_circle,
                R.string.notification_install_complete);
    }
}
