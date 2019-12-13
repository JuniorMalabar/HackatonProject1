package com.example.hackatonproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private EditText loginText;
    private EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginText = findViewById(R.id.login_login);
        passwordText = findViewById(R.id.login_password);
    }

    public void toRegistration(View view){
        Intent intent = new Intent(getApplicationContext(), Registration.class);
        startActivity(intent);
    }

    public void signIn(View view) {
        String login = loginText.getText().toString();
        String password = passwordText.getText().toString();
        if (User.tryToRegistrate(login, password)){

        }
    }
}
