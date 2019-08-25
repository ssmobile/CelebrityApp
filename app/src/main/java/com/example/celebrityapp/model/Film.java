package com.example.celebrityapp.model;

import androidx.annotation.NonNull;

public class Film {

    private int id;

    private String filmName;

    private int year;

    private String genre;

    public Film(String filmName, int year, String genre) {
        this.filmName = filmName;
        this.year = year;
        this.genre = genre;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFilmName() { return filmName; }

    public int getYear() { return year; }

    public void setYear(int year) { this.year = year; }

    public void setFilmName(String filmName) { this.filmName = filmName; }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }
}
