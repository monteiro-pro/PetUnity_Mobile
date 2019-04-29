package com.example.petunity_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CadastroUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        (findViewById(R.id.btContinuar)).setOnClickListener(cadastrarUsuario);
    }

    View.OnClickListener cadastrarUsuario = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it = new Intent(CadastroUsuarioActivity.this, CadastroAnimalActivity.class);
            startActivity(it);
        }
    };
}
