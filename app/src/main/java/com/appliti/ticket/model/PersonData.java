package com.appliti.ticket.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonData implements Parcelable {

    private final String name;
    private final String surname;
    private final String mail;
    private final String phone;

    protected PersonData(Parcel in) {
        name = in.readString();
        surname = in.readString();
        mail = in.readString();
        phone = in.readString();
    }

    public static final Creator<PersonData> CREATOR = new Creator<PersonData>() {
        @Override
        public PersonData createFromParcel(Parcel in) {
            return new PersonData(in);
        }

        @Override
        public PersonData[] newArray(int size) {
            return new PersonData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(surname);
        parcel.writeString(mail);
        parcel.writeString(phone);
    }
}
