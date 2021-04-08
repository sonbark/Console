package com.banzaraktsaeva.console;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText serverEditText;
    EditText loginEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        serverEditText = findViewById(R.id.serverName);
        serverEditText.addTextChangedListener(new TextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0){ serverEditText.setError("Введите сервис"); }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (after == 0){ serverEditText.setError("Введите сервис"); }
            }
        });

        loginEditText = findViewById(R.id.userLogin);
        loginEditText.addTextChangedListener(new TextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                // действия, когда вводится какой то текст
                // s - то, что вводится, для преобразования в строку - s.toString()
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0){ loginEditText.setError("Введите логин");}
                else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(editable).matches()){
                    loginEditText.setError("Логин должен быть в формате email");}
                // действия после того, как что то введено
                // editable - то, что введено. В строку - editable.toString()
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (after == 0){ loginEditText.setError("Введите логин");}
            }
        });

        passwordEditText = findViewById(R.id.userPassword);
        passwordEditText.addTextChangedListener(new TextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){ }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0){ passwordEditText.setError("Введите пароль");}
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (after == 0){ passwordEditText.setError("Введите пароль");}
            }

        });
    }

    public void enter (View view) {
        String server = serverEditText.getText().toString();
        String login = loginEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        //if ((server.equals("s1")) && (login.equals("l1@mail.ru")) && (password.equals("QWERTY"))) {
        Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(loginIntent);
           /* }else {
                Toast.makeText(LoginActivity.this, "Неверно введённые данные", Toast.LENGTH_SHORT).show();
            }*/
    }
}
