package com.example.celebrityapp.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Celebrity implements Parcelable {

    public static final String TAG = "Celebrity.class";

    private long id;
    private String firstName;
    private String lastName;
    private String height;
    private String industry;
    private String dob;
    private boolean isFavorite;

    public static final String[] keys = {"id","first_name", "last_name", "height", "industry",
    "dob", "is_favorite"};

    public Celebrity() {}

    public Celebrity(String firstName, String lastName, String height, String industry,
                     String dob, boolean isFavorite) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.industry = industry;
        this.dob = dob;
        this.isFavorite = isFavorite;
    }

    protected Celebrity(Parcel in) {
        id = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
        height = in.readString();
        industry = in.readString();
        dob = in.readString();
        isFavorite = in.readByte() != 0;
    }

    public static final Creator<Celebrity> CREATOR = new Creator<Celebrity>() {
        @Override
        public Celebrity createFromParcel(Parcel in) {
            return new Celebrity(in);
        }

        @Override
        public Celebrity[] newArray(int size) {
            return new Celebrity[size];
        }
    };

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getFirstName() { return firstName; }

    public String getLastName() { return lastName; }

    public String getHeight() { return height; }

    public String getDob() { return  dob; }

    public String getIndustry() { return industry; };

    public boolean isFavorite() {return isFavorite; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setHeight(String height) { this.height = height; }

    public void setDob(String dob) { this.dob = dob; }

    public void setIndustry(String industry) { this.industry = industry; }

    public void setFavorite(boolean favorite) { isFavorite = favorite; }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(keys[1], this.firstName);
        values.put(keys[2], this.lastName);
        values.put(keys[3], this.height);
        values.put(keys[4], this.industry);
        values.put(keys[5], this.dob);
        values.put(keys[6], this.isFavorite);

        return values;
    }

    public static Celebrity fromContentValues(ContentValues values) {
        Celebrity c = new Celebrity();

        c.firstName = values.getAsString(keys[1]);
        c.lastName = values.getAsString(keys[2]);
        c.height = values.getAsString(keys[3]);
        c.industry = values.getAsString(keys[4]);
        c.dob = values.getAsString(keys[5]);
        c.isFavorite = values.getAsBoolean(keys[6]);

        return c;
    }


    @Override
    public String toString() {
        return "Celebrity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", height='" + height + '\'' +
                ", industry='" + industry + '\'' +
                ", dob='" + dob + '\'' +
                ", isFavorite=" + isFavorite +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(height);
        parcel.writeString(industry);
        parcel.writeString(dob);
        parcel.writeByte((byte) (isFavorite ? 1 : 0));
    }
}
