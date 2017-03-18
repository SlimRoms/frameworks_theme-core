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

import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;

public class OverlayFlavor implements Parcelable {
    public String name;
    public String key;
    public ArrayMap<String, String> flavors = new ArrayMap<>();
    public String selected;

    public OverlayFlavor(String key, String name) {
        this.name = name;
        this.key = key;
    }

    private OverlayFlavor(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(key);
        parcel.writeMap(flavors);
        parcel.writeString(selected);
    }

    public void readFromParcel(Parcel in) {
        name = in.readString();
        key = in.readString();
        in.readMap(flavors, String.class.getClassLoader());
        if (flavors == null) {
            flavors = new ArrayMap<>();
        }
        selected = in.readString();
    }

    public static final Creator<OverlayFlavor> CREATOR = new Creator<OverlayFlavor>() {
        @Override
        public OverlayFlavor createFromParcel(Parcel parcel) {
            return new OverlayFlavor(parcel);
        }

        @Override
        public OverlayFlavor[] newArray(int i) {
            return new OverlayFlavor[i];
        }
    };

    @Override
    public String toString() {
        return name;
    }
}
