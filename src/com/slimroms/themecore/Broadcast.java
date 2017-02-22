package com.slimroms.themecore;

import android.content.IntentFilter;

public class Broadcast {
    public static final String ACTION_BACKEND_QUERY = "com.slimroms.THEME_BACKEND";

    public static final String ACTION_BACKEND_CONNECTED = "ACTION_BACKEND_CONNECTED";
    public static final String ACTION_BACKEND_DISCONNECTED = "ACTION_BACKEND_DISCONNECTED";
    public static final String ACTION_BACKEND_BUSY = "ACTION_BACKEND_BUSY";
    public static final String ACTION_BACKEND_NOT_BUSY = "ACTION_BACKEND_NOT_BUSY";

    public static final String EXTRA_BACKEND_NAME = "EXTRA_BACKEND_NAME";
    public static final String EXTRA_THEME_PACKAGE = "EXTRA_THEME_PACKAGE";

    private static IntentFilter mBackendConnectFilter;
    public static IntentFilter getBackendConnectFilter() {
        if (mBackendConnectFilter == null) {
            mBackendConnectFilter = new IntentFilter();
            mBackendConnectFilter.addAction(Broadcast.ACTION_BACKEND_CONNECTED);
            mBackendConnectFilter.addAction(Broadcast.ACTION_BACKEND_DISCONNECTED);
        }
        return mBackendConnectFilter;
    }

    private static IntentFilter mBackendBusyFilter;
    public static IntentFilter getBackendBusyFilter() {
        if (mBackendBusyFilter == null) {
            mBackendBusyFilter = new IntentFilter();
            mBackendBusyFilter.addAction(ACTION_BACKEND_BUSY);
            mBackendBusyFilter.addAction(ACTION_BACKEND_NOT_BUSY);
        }
        return mBackendBusyFilter;
    }
}