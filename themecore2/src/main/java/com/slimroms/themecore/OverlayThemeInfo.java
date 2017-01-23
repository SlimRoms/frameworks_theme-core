package com.slimroms.themecore;

import android.os.Parcel;
import android.os.Parcelable;

public class OverlayThemeInfo implements Parcelable {

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

    }

    public void readFromParcel(Parcel in) {

    }

    public static final Creator<OverlayThemeInfo> CREATOR
            = new Creator<OverlayThemeInfo>() {
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
