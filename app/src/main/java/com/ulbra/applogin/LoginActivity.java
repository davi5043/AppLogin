package com.ulbra.applogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edLogin, edPass1, edPass;
    Button btLogin;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DBHelper(this);

        edLogin = findViewById(R.id.edLogin);
        edPass = findViewById(R.id.edPass);
        edPass1 = findViewById(R.id.edPass1);
        btLogin = findViewById(R.id.btLogin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = edLogin.getText().toString().trim();
                String password = edPass.getText().toString().trim();
                String password1 = edPass1.getText().toString().trim();

                if (username.isEmpty()) {
                    Toast.makeText(LoginActivity.this,
                            "Usuário não inserido",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.isEmpty()) {
                    Toast.makeText(LoginActivity.this,
                            "Senha não inserida",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean res = db.validarLogin(username, password);

                if (res) {
                    Toast.makeText(LoginActivity.this,
                            "Login OK !!",
                            Toast.LENGTH_SHORT).show();


                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this,
                            "Login ou senha incorretos",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}