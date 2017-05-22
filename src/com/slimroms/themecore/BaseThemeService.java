/*
 * Copyright (C) 2017 SlimRoms Project
 * Copyright (C) 2017 Victor Lapin
 * Copyright (C) 2017 Griffin Millender
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.slimroms.themecore;

import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.NotificationCompat;
import android.system.Os;
import android.util.Log;

import java.io.File;
import java.util.HashMap;

public abstract class BaseThemeService extends Service {
    private static String TAG;

    private ComponentName mBackendName;
    private HashMap<String, Theme> mThemes;

    private static final int NOTIFICATION_INSTALL_ID = 1001;
    private static final int NOTIFICATION_UNINSTALL_ID = 1002;
    private NotificationManager mNotifyManager;
    private PowerManager.WakeLock mWakelock;

    private ThemePrefs mThemePrefs;

    public abstract BaseThemeHelper getThemeHelper();

    protected abstract String getThemeType();

    @Override
    public void onCreate() {
        super.onCreate();
        mBackendName =  new ComponentName(this, this.getClass());
        mThemes = new HashMap<>();
        TAG = this.getClass().getSimpleName();

        mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void onDestroy() {
        stopWakeLock();
        super.onDestroy();
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
        final Intent busyIntent = new Intent(Broadcast.ACTION_BACKEND_NOT_BUSY);
        busyIntent.putExtra(Broadcast.EXTRA_BACKEND_NAME, mBackendName);
        sendBroadcast(busyIntent);
    }

    private void showOngoingNotification(int notificationId, @DrawableRes int icon, String text,
                                         int max, int progress) {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(icon);
        builder.setContentText(text);
        builder.setProgress(max, progress, false);
        builder.setNumber(max);
        builder.setStyle(new NotificationCompat.BigTextStyle());
        builder.setAutoCancel(false);
        builder.setOngoing(true);
        mNotifyManager.notify(notificationId, builder.build());
        final Intent busyIntent = new Intent(Broadcast.ACTION_BACKEND_BUSY);
        busyIntent.putExtra(Broadcast.EXTRA_BACKEND_NAME, mBackendName);
        busyIntent.putExtra(Broadcast.EXTRA_MESSAGE, text);
        sendBroadcast(busyIntent);
    }

    private File createCache(String basePath) {
        File cache = new File(basePath);
        try {
            Os.chmod(cache.getAbsolutePath(), 00777);
        } catch (Exception e) {
            e.printStackTrace();
        }
        File appCache = new File(cache, getPackageName());
        try {
            Os.chmod(appCache.getAbsolutePath(), 00777);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appCache;
    }

    @Override
    public File getCacheDir() {
        return createCache("/data/system/theme/cache/");
    }

    public File getPersistentCacheDir() {
        return createCache("/data/system/theme/persistent-cache/");
    }

    public static void clearPesistentCache(String packageName) {
        File cache = new File("/data/system/theme/persistent-cache/", packageName);
        if (cache.exists()) {
            deleteContents(cache);
        }
    }

    protected ThemePrefs getThemePrefs() {
        if (mThemePrefs == null) {
            mThemePrefs = new ThemePrefs(getCacheDir() + "/settings.json");
        }
        return mThemePrefs;
    }

    protected ThemePrefs getThemePrefs(String fileName) {
        return new ThemePrefs(getCacheDir() + "/" + fileName + ".json");
    }

    protected void notifyInstallProgress(int max, int progress, @Nullable String overlayName) {
        String text = (overlayName != null)
                ? String.format(
                        getString(R.string.notification_install_progress_overlay), overlayName)
                : getString(R.string.notification_install_progress);
        showOngoingNotification(NOTIFICATION_INSTALL_ID, R.drawable.auto_fix, text, max, progress);
    }

    protected void notifyInstallComplete() {
        showNotification(NOTIFICATION_INSTALL_ID, R.drawable.check_circle,
                R.string.notification_install_complete);
    }

    protected void notifyUninstallProgress(int max, int progress, @Nullable String overlayName) {
        String text = (overlayName != null)
                ? String.format(
                        getString(R.string.notification_uninstall_progress_overlay), overlayName)
                : getString(R.string.notification_uninstall_progress);
        showOngoingNotification(NOTIFICATION_UNINSTALL_ID, R.drawable.delete, text, max, progress);
    }

    protected void notifyUninstallComplete() {
        showNotification(NOTIFICATION_UNINSTALL_ID, R.drawable.check_circle,
                R.string.notification_uninstall_complete);
    }

    protected static void deleteContents(File file) {
        if (file.isDirectory()) {
            String[] children = file.list();
            for (int i = 0; i < children.length; i++) {
                File f = new File(file, children[i]);
                if (f.isFile()) {
                    if (f.delete()) {
                        Log.d(TAG, "Successfully deleted " + f.getPath());
                    } else {
                        Log.d(TAG, "Failed to delete " + f.getPath());
                    }
                } else {
                    deleteContents(f);
                    // Now delete the empty folder
                    f.delete();
                }
            }
            // Now delete the main empty folder
            file.delete();
        }
    }

    protected void startWakeLock() {
        if (mWakelock == null) {
            Log.d(TAG, "Getting a new WakeLock...");
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            Log.d(TAG, "PowerManager acquired");
            mWakelock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    this.getClass().getSimpleName() + "_WakeLock");
            Log.d(TAG, "WakeLock created");
        }
        mWakelock.acquire();
        Log.d(TAG, "WakeLock acquired");
    }

    protected void stopWakeLock() {
        if (mWakelock != null && mWakelock.isHeld()) {
            mWakelock.release();
            Log.d(TAG, "WakeLock released");
        }
    }
}
