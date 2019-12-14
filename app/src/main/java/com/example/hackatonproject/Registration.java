package com.example.hackatonproject;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {
    private EditText loginText;
    private EditText passwordText;
    private TextView errorMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        loginText = findViewById(R.id.reg_login);
        passwordText = findViewById(R.id.reg_password);
        errorMsg = findViewById(R.id.errorMsg);
    }

    public void register(View view) {
        String login = loginText.getText().toString();
        String password = passwordText.getText().toString();
        if (login.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Не все поля заполнены!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (User.tryToRegister(login, password)){
            errorMsg.setVisibility(View.GONE);
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }
        else{
            errorMsg.setVisibility(View.VISIBLE);
        }
    }
}
