package com.slimroms.themecore;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutCompat;

public class Theme implements Parcelable {

    public String name;
    public String packageName;
    public int packageType;
    public int themeVersion;

    public Theme(String name, String packageName, int packageType, int themeVersion) {
        this.name = name;
        this.packageName = packageName;
        this.packageType = packageType;
        this.themeVersion = themeVersion;
    }

    private Theme(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(packageName);
        parcel.writeInt(packageType);
        parcel.writeInt(themeVersion);
    }

    public void readFromParcel(Parcel parcel) {
        name = parcel.readString();
        packageName = parcel.readString();
        packageType = parcel.readInt();
        themeVersion = parcel.readInt();
    }

    public static final Creator<Theme> CREATOR
            = new Creator<Theme>() {

        @Override
        public Theme createFromParcel(Parcel parcel) {
            return new Theme(parcel);
        }

        @Override
        public Theme[] newArray(int i) {
            return new Theme[i];
        }
    };
}
