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

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.HashMap;

public class Overlay implements Parcelable, Comparable<Overlay> {
    public final HashMap<String, OverlayFlavor> flavors = new HashMap<>();
    public String overlayName;
    public String targetPackage;
    public boolean isOverlayInstalled;
    public boolean isTargetPackageInstalled;
    public boolean checked;
    public boolean isOverlayEnabled;
    public Bitmap overlayImage;
    public String themePackage;
    public String overlayVersion;
    public String tag;

    public Overlay(String overlayName, String targetPackage, boolean isTargetPackageInstalled) {
        this.overlayName = overlayName;
        this.targetPackage = targetPackage;
        this.isOverlayInstalled = false;
        this.isTargetPackageInstalled = isTargetPackageInstalled;
        this.checked = false;
        this.isOverlayEnabled = true;
        this.themePackage = "";
        this.overlayVersion = "";
    }

    private Overlay(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(overlayName);
        parcel.writeString(targetPackage);
        parcel.writeInt(isOverlayInstalled ? 1 : 0);
        parcel.writeInt(isTargetPackageInstalled ? 1 : 0);
        parcel.writeInt(checked ? 1 : 0);
        parcel.writeMap(flavors);
        parcel.writeInt(isOverlayEnabled ? 1 : 0);
        parcel.writeParcelable(overlayImage, flags);
        parcel.writeString(themePackage);
        parcel.writeString(overlayVersion);
        parcel.writeString(tag);
    }

    public void readFromParcel(Parcel in) {
        overlayName = in.readString();
        targetPackage = in.readString();
        isOverlayInstalled = in.readInt() == 1;
        isTargetPackageInstalled = in.readInt() == 1;
        checked = in.readInt() == 1;
        in.readMap(flavors, OverlayFlavor.class.getClassLoader());
        isOverlayEnabled = in.readInt() == 1;
        overlayImage = in.readParcelable(Bitmap.class.getClassLoader());
        themePackage = in.readString();
        overlayVersion = in.readString();
        tag = in.readString();
    }

    public void clearSelectedFlavors() {
        for (OverlayFlavor flavor : flavors.values())
            flavor.selected = "";
    }

    @Override
    public int compareTo(@NonNull Overlay another) {
        return this.overlayName.compareTo(another.overlayName);
    }

    public static final Creator<Overlay> CREATOR = new Creator<Overlay>() {
        @Override
        public Overlay createFromParcel(Parcel parcel) {
            return new Overlay(parcel);
        }

        @Override
        public Overlay[] newArray(int i) {
            return new Overlay[i];
        }
    };
}
