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
