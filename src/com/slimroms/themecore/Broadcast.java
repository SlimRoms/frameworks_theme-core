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

import android.content.IntentFilter;

public class Broadcast {
    public static final String ACTION_BACKEND_QUERY = "com.slimroms.THEME_BACKEND";

    public static final String ACTION_BACKEND_CONNECTED = "com.slimroms.ACTION_BACKEND_CONNECTED";
    public static final String ACTION_BACKEND_DISCONNECTED = "com.slimroms.ACTION_BACKEND_DISCONNECTED";
    public static final String ACTION_BACKEND_BUSY = "com.slimroms.ACTION_BACKEND_BUSY";
    public static final String ACTION_BACKEND_NOT_BUSY = "com.slimroms.ACTION_BACKEND_NOT_BUSY";
    public static final String ACTION_REDRAW = "com.slimroms.ACTION_REDRAW";

    public static final String EXTRA_BACKEND_NAME = "EXTRA_BACKEND_NAME";
    public static final String EXTRA_THEME_PACKAGE = "EXTRA_THEME_PACKAGE";
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

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

    private static IntentFilter mRedrawFilter;
    public static IntentFilter getRedrawFilter() {
        if (mRedrawFilter == null) {
            mRedrawFilter = new IntentFilter();
            mRedrawFilter.addAction(ACTION_REDRAW);
        }
        return mRedrawFilter;
    }
}
