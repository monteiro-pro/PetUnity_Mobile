package com.example.petunity_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CadastroAnimalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_animal);

        (findViewById(R.id.btConcluir)).setOnClickListener(concluirCadastro);
    }

    View.OnClickListener concluirCadastro = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it = new Intent(CadastroAnimalActivity.this, ListagemActivity.class);
            startActivity(it);
        }
    };
}
