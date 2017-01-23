// IThemeService.aidl.aidl
package com.slimroms.themecore;

import com.slimroms.themecore.Theme;
import com.slimroms.themecore.OverlayThemeInfo;

// Declare any non-default types here with import statements

interface IThemeService {

    List<Theme> getThemePackages();
    void getThemeContent(in Theme theme, out OverlayThemeInfo info);
    int checkPermissions();
    boolean installOverlaysFromTheme(in OverlayThemeInfo info);
    boolean uninstallOverlays(in OverlayThemeInfo info);
    boolean isRebootRequired();
    void reboot();
    boolean isAvailable();
}
