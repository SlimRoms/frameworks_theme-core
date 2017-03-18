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

import java.util.HashMap;

public class OverlayThemeInfo implements Parcelable {
    public final HashMap<String, OverlayGroup> groups = new HashMap<>();

    public OverlayThemeInfo() {

    }

    private OverlayThemeInfo(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeMap(groups);
    }

    public void readFromParcel(Parcel in) {
        in.readMap(groups, OverlayGroup.class.getClassLoader());
    }

    public static final Creator<OverlayThemeInfo> CREATOR = new Creator<OverlayThemeInfo>() {
        @Override
        public OverlayThemeInfo createFromParcel(Parcel parcel) {
            return new OverlayThemeInfo(parcel);
        }

        @Override
        public OverlayThemeInfo[] newArray(int i) {
            return new OverlayThemeInfo[i];
        }
    };

    public int getSelectedCount() {
        int result = 0;
        for (OverlayGroup group : groups.values()) {
            result += group.getSelectedCount();
        }
        return result;
    }

    public void clearSelection() {
        for (OverlayGroup group : groups.values()) {
            group.clearSelected();
        }
    }
}
