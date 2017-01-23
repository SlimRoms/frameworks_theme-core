// IThemeService.aidl.aidl
package com.slimroms.themecore;

import com.slimroms.themecore.Theme;
import com.slimroms.themecore.OverlayThemeInfo;

// Declare any non-default types here with import statements

interface IThemeService {

    /**
    * @param themes Collection of available themes found by the backend
    * @return Collection length
    */
    int getThemePackages(out List<Theme> themes);

    /**
    * @param theme Theme that was selected by user
    * @param info Selected theme's overlays
    */
    void getThemeContent(in Theme theme, out OverlayThemeInfo info);

    /**
    * @return Permissions bit mask, 0 if ok
    */
    int checkPermissions();
    boolean installOverlaysFromTheme(in OverlayThemeInfo info);
    boolean uninstallOverlays(in OverlayThemeInfo info);
    boolean isRebootRequired();
    void reboot();
    boolean isAvailable();
}
