package com.example.proiectfinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proiectfinal.database.MovieDatabase;
import com.example.proiectfinal.models.User;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

    private MovieDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = MovieDatabase.getInstance(this);

        EditText usernameEditText = findViewById(R.id.login_username);
        EditText passwordEditText = findViewById(R.id.login_password);
        Button loginButton = findViewById(R.id.login_button);
        Button signUpButton = findViewById(R.id.signup_button);

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Vă rugăm introduceți numele de utilizator și parola", Toast.LENGTH_SHORT).show();
                return;
            }

            Executors.newSingleThreadExecutor().execute(() -> {
                try {
                    Log.d("LoginActivity", "Attempting login with username: " + username);

                    User user = database.userDao().getUserByUsernameAndPassword(username, password);

                    if (user != null && user.getUsername() != null && !user.getUsername().isEmpty()) {
                        Log.d("LoginActivity", "User found: " + user.getUsername());
                        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("logged_in_user", user.getUsername());
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Nume de utilizator sau parolă incorectă", Toast.LENGTH_SHORT).show());
                    }
                } catch (Exception e) {
                    Log.e("LoginActivity", "Error during login", e);
                    runOnUiThread(() -> Toast.makeText(LoginActivity.this, "Eroare. Încercați din nou.", Toast.LENGTH_SHORT).show());
                }
            });
        });

        signUpButton.setOnClickListener(v -> {
            if (signUpButton != null) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                if (intent != null) {
                    startActivity(intent);
                } else {
                    Log.e("LoginActivity", "Failed to create Intent for SignUpActivity");
                }
            } else {
                Log.e("LoginActivity", "signUpButton is null");
            }
        });

    }
}

