package com.slimroms.themecore;

import android.content.ComponentName;
import android.os.Parcel;
import android.os.Parcelable;

public class Theme implements Parcelable {

    public String name;
    public String packageName;
    public String themeType;
    public int themeVersion;
    public ComponentName backendName;

    public Theme(ComponentName backendName, String name, String packageName,
                 String themeType, int themeVersion) {
        this.backendName = backendName;
        this.name = name;
        this.packageName = packageName;
        this.themeType = themeType;
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
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(name);
        parcel.writeString(packageName);
        parcel.writeString(themeType);
        parcel.writeInt(themeVersion);
        parcel.writeParcelable(backendName, flags);
    }

    public void readFromParcel(Parcel parcel) {
        name = parcel.readString();
        packageName = parcel.readString();
        themeType = parcel.readString();
        themeVersion = parcel.readInt();
        backendName = parcel.readParcelable(ComponentName.class.getClassLoader());
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
