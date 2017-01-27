package com.slimroms.themecore;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.List;

public class OverlayGroup implements Parcelable {
    public final List<Overlay> overlays = new ArrayList<>();

    public final ArrayMap<String, String> styles = new ArrayMap<>();

    public String title;

    public OverlayGroup(String title) {
        this.title = title;
    }

    private OverlayGroup(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(title);
        parcel.writeTypedList(overlays);
        parcel.writeMap(styles);
    }

    public void readFromParcel(Parcel in) {
        title = in.readString();
        in.readTypedList(overlays, Overlay.CREATOR);
        in.readMap(styles, String.class.getClassLoader());
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
