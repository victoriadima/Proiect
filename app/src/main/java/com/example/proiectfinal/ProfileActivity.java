package com.example.proiectfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.proiectfinal.database.MovieDatabase;
import com.example.proiectfinal.models.User;
import java.util.concurrent.Executors;

public class ProfileActivity extends AppCompatActivity {
    private TextView tvName, tvLastname, tvBirthYear, tvUsername;
    private MovieDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvName = findViewById(R.id.tv_name);
        tvLastname = findViewById(R.id.tv_lastname);
        tvBirthYear = findViewById(R.id.tv_birthyear);
        tvUsername = findViewById(R.id.tv_username);

        database = MovieDatabase.getInstance(this);

        loadUserData();
    }

    private void loadUserData() {
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String username = prefs.getString("logged_in_user", null);

        if (username != null) {
            Executors.newSingleThreadExecutor().execute(() -> {
                User user = database.userDao().getUserByUsername(username);
                if (user != null) {
                    runOnUiThread(() -> {
                        tvName.setText("Prenume: " + user.getFirstName());
                        tvLastname.setText("Nume: " + user.getLastName());
                        tvBirthYear.setText("Anul nașterii: " + user.getYearOfBirth());
                        tvUsername.setText("Numele de utilizator: " + user.getUsername());
                    });
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_home) {
            Intent intent = new Intent(this, MainActivity.class);

            String data = "Home Data";
            if (data != null) {
                intent.putExtra("myKey", data);
            } else {
                intent.putExtra("myKey", "");
            }

            startActivity(intent);
            return true;
        } else if (id == R.id.menu_browse_movies) {
            Intent intent = new Intent(this, BrowseMoviesActivity.class);

            String browseData = "Browse Movies Data";
            if (browseData != null) {
                intent.putExtra("myKey", browseData);
            } else {
                intent.putExtra("myKey", "");
            }

            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
