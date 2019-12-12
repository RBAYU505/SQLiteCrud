package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText etName, etPassword;
    Button btLogin;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        etName = (EditText) findViewById(R.id.etName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btLogin = (Button) findViewById(R.id.btLogin);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /*
                if (etName.getText().toString().toString().length() == 0) {
                    etName.setError("Please Enter Name");
                } else if (etPassword.getText().toString().length() == 0) {
                    etPassword.setError("Please Enter Password");
                } else if (etName.getText().toString().equals("admin") && etPassword.getText().toString().equals("123456")) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Username atau Password salah !", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
                */
                if (etName.getText().toString().equals("resta") && etPassword.getText().toString().equals("123456"))
                {
                    progressBar.setVisibility(View.VISIBLE);
                    finish();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(),"Username atau Password salah !", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
