package com.example.petunity_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        (findViewById(R.id.btAdotar)).setOnClickListener(entrarContato);
    }

    View.OnClickListener entrarContato = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it = new Intent(PerfilActivity.this, FazerLigacaoActivity.class);
            startActivity(it);
        }
    };
}
