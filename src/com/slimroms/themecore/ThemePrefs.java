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

import android.support.annotation.Nullable;
import android.util.ArraySet;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Set;


public class ThemePrefs {

    private JSONObject mPrefs;
    private File mFile;

    public ThemePrefs(String path) {
        mFile = new File(path);
        if (!mFile.exists()) {
            if (!mFile.getParentFile().exists()) {
                try {
                    FileUtils.forceMkdirParent(mFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //if (!mFile.getParentFile().mkdirs()) {
                    //IOException e = new IOException("Unable to create directory - " + mFile.getParent());
                    //throw new RuntimeException(e);
                //}
            }
            try {
                FileUtils.write(mFile, "{}", Charset.defaultCharset());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            String json = FileUtils.readFileToString(mFile, Charset.defaultCharset());
            mPrefs = new JSONObject(json);
        } catch (IOException|JSONException e) {
            e.printStackTrace();
            mPrefs = new JSONObject();
        }
    }

    @Nullable
    public String getString(String key, String defValue) {
        return mPrefs.optString(key, defValue);
    }

    @Nullable
    public Set<String> getStringSet(String s, Set<String> set) {
        Set<String> ret = new ArraySet<>();
        try {
            JSONArray jsonArray = mPrefs.getJSONArray(s);
            if (jsonArray == null || jsonArray.length() == 0) {
                return set;
            }
            for (int i = 0; i < jsonArray.length(); i++) {
                ret.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return set;
        }
        return ret;
    }

    public int getInt(String s, int i) {
        return mPrefs.optInt(s, i);
    }

    public long getLong(String s, long l) {
        return mPrefs.optLong(s, l);
    }

    public float getFloat(String s, float v) {
        String f = mPrefs.optString(s, String.valueOf(v));
        if (f != null && !f.isEmpty()) {
            return Float.valueOf(f);
        }
        return v;
    }

    public boolean getBoolean(String s, boolean b) {
        return mPrefs.optBoolean(s, b);
    }

    public boolean contains(String key) {
        return mPrefs.has(key);
    }

    public void putString(String s, String s1) {
        try {
            mPrefs.put(s, s1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putStringSet(String s, Set<String> set) {
        JSONArray jsonArray = new JSONArray();
        String[] temp = set.toArray(new String[0]);
        for (int i = 0; i < temp.length; i++) {
            try {
                jsonArray.put(i, temp[i]);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            mPrefs.put(s, jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putInt(String s, int i) {
        try {
            mPrefs.put(s, i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putLong(String s, long l) {
        try {
            mPrefs.put(s, l);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putFloat(String s, float v) {
        try {
            mPrefs.put(s, String.valueOf(v));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void putBoolean(String s, boolean b) {
        try {
            mPrefs.put(s, b);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void remove(String s) {
        mPrefs.remove(s);
    }

    public void clear() {
        String key;
        while ((key = mPrefs.keys().next()) != null && mPrefs.keys().hasNext()) {
            mPrefs.remove(key);
        }
    }

    public void removeFile() {
        mFile.delete();
    }

}
