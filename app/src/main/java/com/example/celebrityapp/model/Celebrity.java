package com.example.celebrityapp.model;

import android.content.ContentValues;

public class Celebrity {

    private long id;
    private String firstName;
    private String lastName;
    private String height;
    private String industry;
    private String dob;
    private boolean isFavorite;

    public static final String[] keys = {"first_name", "last_name", "height", "industry",
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
        values.put(keys[0], this.firstName);
        values.put(keys[1], this.lastName);
        values.put(keys[2], this.height);
        values.put(keys[3], this.industry);
        values.put(keys[4], this.dob);
        values.put(keys[5], this.isFavorite);

        return values;
    }

    public static Celebrity fromContentValues(ContentValues values) {
        Celebrity c = new Celebrity();

        c.firstName = values.getAsString(keys[0]);
        c.lastName = values.getAsString(keys[1]);
        c.height = values.getAsString(keys[2]);
        c.industry = values.getAsString(keys[3]);
        c.dob = values.getAsString(keys[4]);
        c.isFavorite = values.getAsBoolean(keys[5]);

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


}
