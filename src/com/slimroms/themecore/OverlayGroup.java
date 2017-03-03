package com.slimroms.themecore;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class OverlayGroup implements Parcelable {
    public final List<Overlay> overlays = new ArrayList<>();

    public final LinkedHashMap<String, String> styles = new LinkedHashMap<>();
    public String selectedStyle = "";

    public static final String OVERLAYS = "OVERLAYS";
    public static final String BOOTANIMATIONS = "BOOTANIMATIONS";
    public static final String FONTS = "FONTS";
    public static final String WALLPAPERS = "WALLPAPERS";

    public OverlayGroup() { }

    private OverlayGroup(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(overlays);
        parcel.writeMap(styles);
        parcel.writeString(selectedStyle);
    }

    public void readFromParcel(Parcel in) {
        in.readTypedList(overlays, Overlay.CREATOR);
        in.readMap(styles, String.class.getClassLoader());
        selectedStyle = in.readString();
    }

    public void clearSelected() {
        for (Overlay overlay : overlays) {
            overlay.checked = false;
        }
    }

    public void sort() {
        Collections.sort(overlays);
    }

    public int getSelectedCount() {
        int result = 0;
        for (Overlay overlay : overlays) {
            if (overlay.checked) {
                result++;
            }
        }
        return result;
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
