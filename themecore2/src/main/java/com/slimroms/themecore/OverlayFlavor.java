package com.slimroms.themecore;

import android.os.Parcel;
import android.os.Parcelable;

public class OverlayFlavor implements Parcelable {
    public String name;
    public String overlayLocalPath;
    public boolean selected;

    public OverlayFlavor(String name, String path) {
        this.name = name;
        this.overlayLocalPath = path;
        this.selected = false;
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
        parcel.writeString(overlayLocalPath);
        parcel.writeInt(selected ? 1 : 0);
    }

    public void readFromParcel(Parcel in) {
        name = in.readString();
        overlayLocalPath = in.readString();
        selected = in.readInt() == 1;
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
