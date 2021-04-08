package com.banzaraktsaeva.console;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void enter (View view) {

        //if ((server.equals("server1")) && (login.equals("login1@mail.ru")) && (password.equals("QWERTY123"))) {
        Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(loginIntent);
           /* }else {
                Toast.makeText(LoginActivity.this, "Неверно введённые данные", Toast.LENGTH_SHORT).show();
            }*/
        }
}
