package com.example.proiectfinal.database;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proiectfinal.dao.UserDao;
import com.example.proiectfinal.models.Movie;

import com.example.proiectfinal.dao.MovieDao;
import com.example.proiectfinal.models.User;

@Database(entities = {Movie.class, User.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    private static MovieDatabase instance;

    public abstract MovieDao movieDao();
    public abstract UserDao userDao();

    public static synchronized MovieDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            MovieDatabase.class, "movie_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}




