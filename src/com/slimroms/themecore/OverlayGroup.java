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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class OverlayGroup implements Parcelable {
    public final List<Overlay> overlays = new ArrayList<>();

    public final LinkedHashMap<String, String> styles = new LinkedHashMap<>();
    public String selectedStyle = "";

    public static final String OVERLAYS = "OVERLAYS";
    public static final String BOOTANIMATIONS = "BOOTANIMATIONS";
    public static final String FONTS = "FONTS";
    public static final String WALLPAPERS = "WALLPAPERS";

    public OverlayGroup() { }

    private OverlayGroup(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(overlays);
        parcel.writeMap(styles);
        parcel.writeString(selectedStyle);
    }

    public void readFromParcel(Parcel in) {
        in.readTypedList(overlays, Overlay.CREATOR);
        in.readMap(styles, String.class.getClassLoader());
        selectedStyle = in.readString();
    }

    public void clearSelected() {
        for (Overlay overlay : overlays) {
            overlay.checked = false;
        }
    }

    public void sort() {
        Collections.sort(overlays);
    }

    public int getSelectedCount() {
        int result = 0;
        for (Overlay overlay : overlays) {
            if (overlay.checked) {
                result++;
            }
        }
        return result;
    }

    public Overlay findByTargetPackage(String targetPackage) {
        for (Overlay overlay : overlays) {
            if (overlay.targetPackage.equals(targetPackage)) {
                return overlay;
            }
        }
        return null;
    }

    public static final Creator<OverlayGroup> CREATOR = new Creator<OverlayGroup>() {
        @Override
        public OverlayGroup createFromParcel(Parcel parcel) {
            return new OverlayGroup(parcel);
        }

        @Override
        public OverlayGroup[] newArray(int i) {
            return new OverlayGroup[i];
        }
    };
}
