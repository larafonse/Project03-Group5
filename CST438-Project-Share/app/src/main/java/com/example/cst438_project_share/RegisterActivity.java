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

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String TAG= "MainActivity";
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private BottomNavigationView bottomNavigationView;

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