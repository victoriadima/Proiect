package com.example.proiectfinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proiectfinal.adapter.MovieAdapter;
import com.example.proiectfinal.database.MovieDatabase;
import com.example.proiectfinal.models.Movie;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class BrowseMoviesActivity extends AppCompatActivity {
    private MovieDatabase database;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_movies);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        movieAdapter = new MovieAdapter(movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieAdapter);

        database = MovieDatabase.getInstance(this);
        loadMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_profile) {
            String dataToSend = "";
            Intent intent = new Intent(this, ProfileActivity.class);

            if (dataToSend != null) {
                intent.putExtra("myKey", dataToSend);
            } else {
                intent.putExtra("myKey", "");
            }

            startActivity(intent);
            return true;
        } else if (id == R.id.menu_home) {
            String homeData = "Home data";
            Intent intent = new Intent(this, MainActivity.class);

            if (homeData != null) {
                intent.putExtra("myKey", homeData);
            } else {
                intent.putExtra("myKey", "");
            }

            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadMovies() {
        Executors.newSingleThreadExecutor().execute(() -> {

            List<Movie> movieList = database.movieDao().getAllMovies();


            for (Movie movie : movieList) {
                Log.d("MovieLog", "Fetched movie: " + movie.getTitle());
            }

            runOnUiThread(() -> {
                movies.clear();

                movies.addAll(movieList);

                movieAdapter.notifyDataSetChanged();
            });
        });
    }

}






