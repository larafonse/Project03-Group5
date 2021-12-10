package com.example.cst438_project_share;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String TAG= "RegisterActivity";
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;

    private static Retrofit retrofit = null;
    public static final String BASE_URL = "https://project-share-g5.herokuapp.com/";

    EditText username, password, repassword;
    Spinner occupation;
    Button register;

    String uName;
    String uPassword;
    String reuPassword;
    String uOccupation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        username = findViewById(R.id.Rusername);
        password = findViewById(R.id.Rpassword);
        repassword = findViewById(R.id.reRpassword);
        occupation = findViewById(R.id.spinner1);
        register = findViewById(R.id.registerBtn);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.occupations_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        occupation.setAdapter(adapter);
        occupation.setOnItemSelectedListener(this);

        register.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                uName = username.getText().toString();
                uPassword = password.getText().toString();
                reuPassword = repassword.getText().toString();

                username.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.white));
                password.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.white));
                repassword.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.white));

                if (uName.equals("") || uPassword.equals("") || reuPassword.equals("")) {
                    if (uName.equals("")) {
                        username.setError("Username cannot be blank!");
                        username.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.red));
                    }
                    if (uPassword.equals("")) {
                        password.setError("Password cannot be blank!");
                        password.setBackgroundTintList(getApplication().getResources().getColorStateList(R.color.red));
                    }
                    if (reuPassword.equals("")) {
                        repassword.setError("RePassword cannot be blank!");
                        repassword.setBackgroundTintList(getApplication().getResources().getColorStateList(R.color.red));
                    }
                    return;
                }
                if (!uPassword.equals(reuPassword)) {
                    password.setError("Passwords do not match!");
                    password.setBackgroundTintList(getApplication().getResources().getColorStateList(R.color.red));
                    repassword.setError("Passwords do not match!");
                    repassword.setBackgroundTintList(getApplication().getResources().getColorStateList(R.color.red));
                }

                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }

                ApiService apiService = retrofit.create(ApiService.class);
                apiService.insertUser(uName, "none", "none", "none", uPassword, "none", uOccupation).enqueue(
                    new Callback<Users>() {
                        @Override
                        public void onResponse(Call<Users> call, Response<Users> response) {
                            if (response.message().equals("Created")) {
                                Toast.makeText(RegisterActivity.this, "Account Created! Login!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Account NOT Created!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Users> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "Account NOT Created!", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "Unable to submit post to API.");
                        }
                    }
                );
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        uOccupation = (String) occupation.getItemAtPosition(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}