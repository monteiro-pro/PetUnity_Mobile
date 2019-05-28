package com.example.petunity_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.petunity_mobile.Adapter.AnimalAdapter;
import com.example.petunity_mobile.DataBase.DaoDB;
import com.example.petunity_mobile.Model.Animal;

import java.util.ArrayList;

public class ListagemActivity extends AppCompatActivity {

    private ListView listView;
    ArrayList<Animal> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        list = new DaoDB().listAnimais();

        if (list.size() > 0){
            AnimalAdapter animalAdapter = new AnimalAdapter(this, list);

            listView = findViewById(R.id.lvwAnimal);
            listView.setAdapter(animalAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Animal currentAnimal = list.get(position);
                Intent it = new Intent(ListagemActivity.this, PerfilActivity.class);
                it.putExtra("animal", currentAnimal);
                startActivity(it);
                }
            });
        }
    }
}
