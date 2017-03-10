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

import android.content.ComponentName;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Theme implements Parcelable, Comparable<Theme> {

    public String name;
    public String packageName;
    public String themeType;
    public String themeVersion;
    public ComponentName backendName;
    public String themeAuthor;
    public Bitmap themeLogo;

    public Theme(ComponentName backendName, String name, String packageName,
                 String themeType, String themeVersion, String themeAuthor,
                 Bitmap themeLogo) {
        this.backendName = backendName;
        this.name = name;
        this.packageName = packageName;
        this.themeType = themeType;
        this.themeVersion = themeVersion;
        this.themeAuthor = themeAuthor;
        this.themeLogo = themeLogo;
    }

    private Theme(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(packageName);
        parcel.writeString(themeType);
        parcel.writeString(themeVersion);
        parcel.writeParcelable(backendName, flags);
        parcel.writeString(themeAuthor);
        parcel.writeParcelable(themeLogo, flags);
    }

    public void readFromParcel(Parcel parcel) {
        name = parcel.readString();
        packageName = parcel.readString();
        themeType = parcel.readString();
        themeVersion = parcel.readString();
        backendName = parcel.readParcelable(ComponentName.class.getClassLoader());
        themeAuthor = parcel.readString();
        themeLogo = parcel.readParcelable(Bitmap.class.getClassLoader());
    }

    @Override
    public int compareTo(@NonNull Theme another) {
        return this.name.compareTo(another.name);
    }

    public static final Creator<Theme> CREATOR
            = new Creator<Theme>() {

        @Override
        public Theme createFromParcel(Parcel parcel) {
            return new Theme(parcel);
        }

        @Override
        public Theme[] newArray(int i) {
            return new Theme[i];
        }
    };
}
