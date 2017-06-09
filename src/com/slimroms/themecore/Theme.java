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
    public int themeVersionCode;
    public ComponentName backendName;
    public String themeAuthor;
    public Bitmap themeLogo;

    public Theme(ComponentName backendName, String name, String packageName,
                 String themeType, String themeVersion, int themeVersionCode, String themeAuthor,
                 Bitmap themeLogo) {
        this.backendName = backendName;
        this.name = name;
        this.packageName = packageName;
        this.themeType = themeType;
        this.themeVersion = themeVersion;
        this.themeVersionCode = themeVersionCode;
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
        parcel.writeInt(themeVersionCode);
        parcel.writeParcelable(backendName, flags);
        parcel.writeString(themeAuthor);
        parcel.writeParcelable(themeLogo, flags);
    }

    public void readFromParcel(Parcel parcel) {
        name = parcel.readString();
        packageName = parcel.readString();
        themeType = parcel.readString();
        themeVersion = parcel.readString();
        themeVersionCode = parcel.readInt();
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
