package com.slimroms.themecore;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ThemePackages implements Parcelable {

    private List<Theme> packages = new ArrayList<>();

    private ThemePackages(Parcel in) {
        in.readTypedList(packages, Theme.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(packages);
    }

    public static final Creator<ThemePackages> CREATOR
            = new Creator<ThemePackages>() {

        @Override
        public ThemePackages createFromParcel(Parcel parcel) {
            return new ThemePackages(parcel);
        }

        @Override
        public ThemePackages[] newArray(int i) {
            return new ThemePackages[i];
        }
    };
}
