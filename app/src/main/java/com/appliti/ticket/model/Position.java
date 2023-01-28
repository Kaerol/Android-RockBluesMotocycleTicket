package com.appliti.ticket.model;

import android.os.Parcel;
import android.os.Parcelable;


public class Position implements Parcelable {

    private final String itemName;
    private final int count;

    public String getItemName() {
        return itemName;
    }

    public int getCount() {
        return count;
    }

    public Position(final Parcel in) {
        this(in.readString(), in.readInt());
    }

    public Position(String itemName, int count) {
        this.itemName = itemName;
        this.count = count;
    }

    public static final Creator<Position> CREATOR = new Creator<Position>() {
        @Override
        public Position createFromParcel(final Parcel in) {
            return new Position(in);
        }

        @Override
        public Position[] newArray(final int size) {
            return new Position[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int i) {
        parcel.writeString(itemName);
        parcel.writeInt(count);
    }
}
