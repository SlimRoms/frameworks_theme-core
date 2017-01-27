package com.slimroms.themecore;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;

import java.util.ArrayList;

public class OverlayFlavor implements Parcelable {
    public String name;
    public String key;
    public ArrayMap<String, String> flavors = new ArrayMap<>();
    public String selected;

    public OverlayFlavor(String name, String key) {
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
        parcel.writeMap(flavors);
        parcel.writeString(selected);
    }

    public void readFromParcel(Parcel in) {
        name = in.readString();
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
