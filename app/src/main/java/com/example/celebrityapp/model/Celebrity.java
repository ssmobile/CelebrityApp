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
        values.put("first_name", this.firstName);
        values.put("last_name", this.lastName);
        values.put("height", this.height);
        values.put("industry", this.industry);
        values.put("dob", this.dob);
        values.put("is_favorite", this.isFavorite);

        return values;
    }

    public static Celebrity fromContentValues(ContentValues values) {
        Celebrity c = new Celebrity();

        c.firstName = values.getAsString("first_name");
        c.lastName = values.getAsString("last_name");
        c.height = values.getAsString("stage_name");
        c.industry = values.getAsString("industry");
        c.dob = values.getAsString("dob");
        c.isFavorite = values.getAsBoolean("is_favorite");

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
