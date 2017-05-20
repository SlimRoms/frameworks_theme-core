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

import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class AssetUtils {

    private static final String TAG = "SlimTM-AssetUtils";

    public static InputStream getAsset(AssetManager am, String path, Cipher cipher) throws IOException {
        InputStream in = am.open(path);
        if (cipher != null && path.endsWith(".enc")) {
            return new CipherInputStream(in, cipher);
        }
        return in;
    }

    public static boolean copyAssetFolder(AssetManager am, String assetPath, String path, Cipher cipher) throws IOException {
        String[] files = am.list(assetPath);
        if (!new File(path).exists() && !new File(path).mkdirs()) {
            throw new RuntimeException("cannot create directory: " + path);
        }
        boolean res = true;
        for (String file : files) {
            if (am.list(assetPath + "/" + file).length == 0) {
                Log.d(TAG, "Copy asset file " + file + " from " + assetPath + " to " + path);
                res &= copyAsset(am, assetPath + "/" + file, path + "/" + file, cipher);
            } else {
                //Log.d(TAG, "Copy asset folder " + file + " from " + assetPath + " to " + path);
                res &= copyAssetFolder(am, assetPath + "/" + file, path + "/" + file, cipher);
            }
        }
        return res;
    }

    public static boolean copyAsset(AssetManager assetManager,
                                    String fromAssetPath, String toPath, Cipher cipher) throws IOException {
        InputStream in;
        File parent = new File(toPath).getParentFile();
        if (!parent.exists() && !parent.mkdirs()) {
            Log.e(TAG, "Unable to create " + parent.getAbsolutePath());
        }

        if (toPath.endsWith(".enc")) {
            toPath = toPath.substring(0, toPath.lastIndexOf("."));
        }

        if (cipher != null) {
            in = getAsset(assetManager, fromAssetPath, cipher);
        } else {
            in = assetManager.open(fromAssetPath);
        }
        if (in != null) {
            copyInputStreamToFile(in, new File(toPath));
            in.close();
        }
        return true;
    }
}
