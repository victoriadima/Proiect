package com.example.proiectfinal.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import com.example.proiectfinal.models.Movie;

@Dao
public interface MovieDao {
    @Insert
    void insert(Movie movie);

    @Query("SELECT * FROM movies")
    List<Movie> getAllMovies();

    @Query("SELECT * FROM movies WHERE title LIKE '%' || :title || '%'")
    List<Movie> searchMovies(String title);

    @Query("DELETE FROM movies")
    void deleteAllMovies();

    @Query("SELECT * FROM movies ORDER BY rating DESC LIMIT 3")
    List<Movie> getTopRatedMovies();

}
