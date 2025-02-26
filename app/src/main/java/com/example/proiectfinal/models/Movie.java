package com.example.proiectfinal.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class Movie {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private float rating;
    private int imageResourceId;


    public Movie(String title, float rating, int imageResourceId) {
        this.title = title;
        this.rating = rating;
        this.imageResourceId = imageResourceId;
    }

    public Movie() {}

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    public int getImageResourceId() { return imageResourceId; }
    public void setImageResourceId(int imageResourceId) { this.imageResourceId = imageResourceId; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie movie = (Movie) obj;
        return title != null && title.equals(movie.title);
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }
}






