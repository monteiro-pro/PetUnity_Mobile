package com.example.petunity_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.btDaor)).setOnClickListener(btnDaorAction);
        (findViewById(R.id.btAdotar)).setOnClickListener(btnAdotarAction);
    }

   /* Button btnSend = (Button) findViewById(R.id.btDaor);

btnSend.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            //faça algo
}
    }; */

    View.OnClickListener btnDaorAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it = new Intent(MainActivity.this, CadastroUsuarioActivity.class);
            startActivity(it);
            }
        };

    View.OnClickListener btnAdotarAction= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
