package com.example.celebrityapp.model;

import androidx.annotation.NonNull;


import java.util.List;

public class Filmography {

    private long id;

    private long filmId;

    private long celebId;

    public Filmography(long celebId, long filmId) {
        this.id = celebId;
        this.filmId = filmId;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public long getFilmId() { return filmId; }

    public void setFilmId(long filmId) { this.filmId = filmId; }
}
