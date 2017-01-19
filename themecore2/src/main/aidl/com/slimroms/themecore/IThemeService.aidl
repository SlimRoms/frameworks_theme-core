// IThemeService.aidl.aidl
package com.slimroms.themecore;

import com.slimroms.themecore.Theme;

// Declare any non-default types here with import statements

interface IThemeService {

    List<Theme> getThemePackages();
    void getThemeContent(inout Theme theme);
    int checkPermissions();
    boolean installOverlaysFromTheme(inout Theme theme);
    boolean uninstallOverlays();
    boolean isRebootRequired();
    void reboot();
    boolean isAvailable();
}
