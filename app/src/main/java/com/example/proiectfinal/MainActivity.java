package com.example.proiectfinal;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiectfinal.adapter.MovieAdapter;
import com.example.proiectfinal.database.MovieDatabase;
import com.example.proiectfinal.models.Movie;

import java.util.List;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private MovieDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = MovieDatabase.getInstance(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Executors.newSingleThreadExecutor().execute(() -> {
            List<Movie> topMovies = database.movieDao().getTopRatedMovies();

            runOnUiThread(() -> {
                MovieAdapter adapter = new MovieAdapter(topMovies);
                recyclerView.setAdapter(adapter);
            });
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_browse_movies) {
            String browseData = "Data for BrowseMovies";
            Intent intent = new Intent(this, BrowseMoviesActivity.class);

            if (browseData != null) {
                intent.putExtra("myKey", browseData);
            } else {
                intent.putExtra("myKey", "");
            }

            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.menu_profile) {
            String profileData = "Data for Profile";
            Intent intent = new Intent(this, ProfileActivity.class);


            if (profileData != null) {
                intent.putExtra("myKey", profileData);
            } else {
                intent.putExtra("myKey", "");
            }

            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
