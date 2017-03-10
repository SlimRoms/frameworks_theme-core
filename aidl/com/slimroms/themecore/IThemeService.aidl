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

import com.slimroms.themecore.Theme;
import com.slimroms.themecore.OverlayGroup;
import com.slimroms.themecore.OverlayThemeInfo;

// Declare any non-default types here with import statements

interface IThemeService {

    /**
    * @return Tab header for the collection of installed overlays provided by this backend
    */
    String getBackendTitle();

    /**
    * @param themes Collection of available themes found by the backend
    * @return Collection length
    */
    int getThemePackages(out List<Theme> themes);

    /**
    * @param theme Theme that was selected by user
    * @param info Selected theme's content grouped by type
    */
    void getThemeContent(in Theme theme, out OverlayThemeInfo info);

    /**
    * @return Permissions bit mask, 0 if ok
    */
    int checkPermissions();

    /**
    * Performs the actual installation of selected overlays
    * @param theme Theme that contains selected overlays
    * @param info Full theme content with some overlays selected by the user
    */
    boolean installOverlaysFromTheme(in Theme theme, in OverlayThemeInfo info);

    /**
    * @param group Collection previously returned by {@link #getInstalledOverlays(OverlayGroup)} with some overlays selected by the user
    */
    boolean uninstallOverlays(in OverlayGroup group);

    /**
    * @param group Collection of installed overlays found by the backend
    */
    void getInstalledOverlays(out OverlayGroup group);

    /**
    * @return Whether some kind of restart/reboot required after the previous operation
    */
    boolean isRebootRequired();

    /**
    * Performs reboot
    */
    void reboot();

    /**
    * @return Whether backend can be used as a themes provider. If false, connection will be dropped
    */
    boolean isAvailable();

    /**
    * @param packageName Theme apk package name
    * @return Theme instantiated object
    */
    Theme getThemeByPackage(in String packageName);
}
