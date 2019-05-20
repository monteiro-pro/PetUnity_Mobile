package com.example.petunity_mobile;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FazerLigacaoActivity extends AppCompatActivity {

    private TextView telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_ligacao);

        telefone = findViewById(R.id.tvTelefone);

        (findViewById(R.id.btLigar)).setOnClickListener(fazerLigacao);
    }

    View.OnClickListener fazerLigacao = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Uri uri = Uri.parse("tel:" + telefone.getText());
            Intent intent = new Intent(Intent.ACTION_DIAL,uri);

            startActivity(intent);
        }
    };
}
