package com.ulbra.applogin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrarActivity extends AppCompatActivity {

    EditText edUser, edPass1, edPass2;
    Button btRegistrar;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);


        db = new DBHelper(this);


        edUser = findViewById(R.id.edUser);
        edPass1 = findViewById(R.id.edPass1);
        edPass2 = findViewById(R.id.edPass2);
        btRegistrar = findViewById(R.id.btSalvar);

        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = edUser.getText().toString().trim();
                String pass1 = edPass1.getText().toString().trim();
                String pass2 = edPass2.getText().toString().trim();

                if (!pass1.equals(pass2)) {
                    Toast.makeText(RegistrarActivity.this,
                            "As senhas estão diferentes!!!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (user.isEmpty()) {
                    Toast.makeText(RegistrarActivity.this,
                            "Digite o usuário",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pass1.isEmpty() || pass2.isEmpty()) {
                    Toast.makeText(RegistrarActivity.this,
                            "Digite a senha",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                long result = db.criarUtilizador(user, pass1);

                if (result != -1) {
                    Toast.makeText(RegistrarActivity.this,
                            "Usuário registrado com sucesso!",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegistrarActivity.this,
                            "Erro ao registrar usuário",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}