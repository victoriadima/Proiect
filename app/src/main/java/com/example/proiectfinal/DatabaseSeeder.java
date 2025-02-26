package com.example.proiectfinal;

import com.example.proiectfinal.database.MovieDatabase;
import com.example.proiectfinal.models.Movie;

import java.util.concurrent.Executors;

public class DatabaseSeeder {
    private MovieDatabase database;

    public DatabaseSeeder(MovieDatabase database) {
        this.database = database;
    }

    public void seedDatabase() {
        Executors.newSingleThreadExecutor().execute(() -> {

            Movie[] movies = new Movie[] {
                    new Movie("Inception", 4.8f, R.drawable.inception_image),
                    new Movie("Titanic", 4.5f, R.drawable.titanic_image),
                    new Movie("Avatar", 4.7f, R.drawable.avatar_image),
                    new Movie("The Dark Knight", 4.9f, R.drawable.dark_knight_image),
                    new Movie("The Matrix", 4.6f, R.drawable.matrix_image),
                    new Movie("Star Wars: Episode V", 4.8f, R.drawable.star_wars_image),
                    new Movie("The Godfather", 4.9f, R.drawable.godfather_image),
                    new Movie("Pulp Fiction", 4.7f, R.drawable.pulp_fiction_image),
                    new Movie("Fight Club", 4.8f, R.drawable.fight_club_image),
                    new Movie("Forrest Gump", 4.9f, R.drawable.forrest_gump_image)
            };


            for (Movie movie : movies) {
                database.movieDao().insert(movie);
            }
        });
    }
}

