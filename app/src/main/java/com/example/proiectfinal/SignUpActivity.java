package com.example.proiectfinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectfinal.database.MovieDatabase;
import com.example.proiectfinal.models.User;

import java.util.concurrent.Executors;

public class SignUpActivity extends AppCompatActivity {
    private MovieDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        database = MovieDatabase.getInstance(this);


        Button signUpButton = findViewById(R.id.signup_button);
        signUpButton.setOnClickListener(v -> {

            EditText nameEditText = findViewById(R.id.signup_name);
            EditText lastNameEditText = findViewById(R.id.signup_last_name);
            EditText yearOfBirthEditText = findViewById(R.id.signup_year_of_birth);
            EditText usernameEditText = findViewById(R.id.signup_username);
            EditText passwordEditText = findViewById(R.id.signup_password);

            String name = nameEditText.getText().toString();
            String lastName = lastNameEditText.getText().toString();
            String yearOfBirth = yearOfBirthEditText.getText().toString();
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();


            if (!name.isEmpty() && !lastName.isEmpty() && !yearOfBirth.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                Log.d("SignUp", "Prenume: " + name);
                Log.d("SignUp", "Nume: " + lastName);
                Log.d("SignUp", "Anul nașterii: " + yearOfBirth);
                Log.d("SignUp", "Numele de utilizator: " + username);
                Log.d("SignUp", "Parola: " + password);

                User user = new User(name, lastName, yearOfBirth, username, password);

                Executors.newSingleThreadExecutor().execute(() -> {
                    database.userDao().insert(user);

                    runOnUiThread(() -> {
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);

                        String usernameData = user != null ? user.getUsername() : null;

                        if (usernameData != null) {
                            intent.putExtra("username_key", usernameData);
                        } else {
                            intent.putExtra("username_key", "");
                        }

                        startActivity(intent);
                        finish();
                    });
                });
            } else {

                Toast.makeText(SignUpActivity.this, "Vă rugăm să completați toate câmpurile", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
