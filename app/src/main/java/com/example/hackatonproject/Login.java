package com.example.hackatonproject;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
        Task.setDBHelper(this);
    }

    public void toRegistration(View view){
        Intent intent = new Intent(getApplicationContext(), Registration.class);
        startActivity(intent);
    }

    public void signIn(View view) {
        String login = loginText.getText().toString();
        String password = passwordText.getText().toString();
        if (login.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Не все поля заполнены!", Toast.LENGTH_SHORT).show();
            return;
        }
        User user = User.tryToSignIn(login, password);
        if (user != null){
            AppHelper.getInstance().setUser(user);
            Intent intent = new Intent(getApplicationContext(), TabsHost.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Неверно введены логин/пароль!", Toast.LENGTH_SHORT).show();
        }
    }
}
