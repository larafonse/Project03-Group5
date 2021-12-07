package com.example.cst438_project_share;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Headers;

public class LoginActivity extends AppCompatActivity {
    public static final String API_URL = "https://project-share-g5.herokuapp.com/api/users/?format=json";
    public static final String TAG = "LoginActivity";

    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnSignUp);
        AsyncHttpClient client = new AsyncHttpClient();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etUsername.getText().toString().equals("") || etPassword.getText().toString().equals("")){
                    Toast.makeText(LoginActivity.this, "Error: Empty text fields", Toast.LENGTH_LONG).show();
                }else{
                    String username = etUsername.getText().toString();
                    String password = etPassword.getText().toString();
                    String api_call = API_URL+"&username="+username+"&password="+password;
                    client.get(api_call, new JsonHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Headers headers, JSON json) {
                            Log.d(TAG, "onSuccess");
                            JSONArray results = json.jsonArray;
                            Log.i(TAG, "Results: "+results.toString());

                            if(results.length() != 0){
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Error: Incorrect Username/Password", Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                            Log.d(TAG, "onFailure");

                        }
                    });
                }
            }
        });
    }
}