package com.example.hackatonproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void toRegistration(View view){
        Intent intent = new Intent(getApplicationContext(), Registration.class);
        startActivity(intent);
    }

    public void signIn(View view) {
    }
}
