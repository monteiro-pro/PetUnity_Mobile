package com.example.petunity_mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.btDaor)).setOnClickListener(btnDaorAtion);
        (findViewById(R.id.btAdotar)).setOnClickListener(btnAdotarAtion);
    }

   /* Button btnSend = (Button) findViewById(R.id.btDaor);

btnSend.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
            //faça algo
        }
    }; */

    View.OnClickListener btnDaorAtion= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            }
        };

    View.OnClickListener btnAdotarAtion= new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
}
