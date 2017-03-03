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
}
