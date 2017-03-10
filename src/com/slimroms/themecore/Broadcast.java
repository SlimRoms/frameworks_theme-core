/*
 * Copyright (C) 2017 SlimRoms Project
 * Copyright (C) 2017 Victor Lapin
 * Copyright (C) 2017 Griffin Millender
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
