package com.example.cst438_project_share;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password, repassword;
    Spinner occupation;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.Rusername);
        password = findViewById(R.id.Rpassword);
        repassword = findViewById(R.id.reRpassword);
        occupation = findViewById(R.id.spinner1);
        register = findViewById(R.id.registerBtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uName = username.getText().toString();
                String uPassword = password.getText().toString();
                String reuPassword = repassword.getText().toString();
//                String uOccupation = occupation.get;
            }
        });
    }
}