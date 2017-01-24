package com.slimroms.themecore;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class OverlayThemeInfo implements Parcelable {
    public final List<OverlayGroup> groups = new ArrayList<>();

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
        parcel.writeTypedList(groups);
    }

    public void readFromParcel(Parcel in) {
        in.readTypedList(groups, OverlayGroup.CREATOR);
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
}
