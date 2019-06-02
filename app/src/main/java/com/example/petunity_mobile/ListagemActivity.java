package com.example.petunity_mobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.petunity_mobile.Adapter.AnimalAdapter;
import com.example.petunity_mobile.DataBase.DaoDB;
import com.example.petunity_mobile.Model.Animal;

import java.util.ArrayList;

public class ListagemActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Animal> list;
    private AnimalAdapter animalAdapter;
    private Button todos;
    private Button gato;
    private Button cachorro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem);

        list = new DaoDB().listAnimais();

        todos = (findViewById(R.id.btnListarTodos));
        cachorro = (findViewById(R.id.btnListarCachorro));
        gato = (findViewById(R.id.btnListarGato));

        if (list.size() > 0){
            animalAdapter = new AnimalAdapter(this, list);

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

        todos.setOnClickListener(listarTodos);
        cachorro.setOnClickListener(listarCachorro);
        gato.setOnClickListener(listarGato);
    }

    View.OnClickListener listarTodos = new View.OnClickListener() {
        @SuppressLint("ResourceType")
        @Override
        public void onClick(View v) {
            onRestart();
            todos.setTextColor(getResources().getColor(R.drawable.button_style_listagem_focus));
            cachorro.setTextColor(getResources().getColor(R.drawable.button_style_listagem));
            gato.setTextColor(getResources().getColor(R.drawable.button_style_listagem));
        }
    };

    View.OnClickListener listarCachorro = new View.OnClickListener() {
        @SuppressLint("ResourceType")
        @Override
        public void onClick(View v) {
            ListagemActivity.super.onRestart();
            animalAdapter.updateList(new DaoDB().listAnimaisCachorro());
            todos.setTextColor(getResources().getColor(R.drawable.button_style_listagem));
            cachorro.setTextColor(getResources().getColor(R.drawable.button_style_listagem_focus));
            gato.setTextColor(getResources().getColor(R.drawable.button_style_listagem));
        }
    };

    View.OnClickListener listarGato = new View.OnClickListener() {
        @SuppressLint("ResourceType")
        @Override
        public void onClick(View v) {
            ListagemActivity.super.onRestart();
            animalAdapter.updateList(new DaoDB().listAnimaisGato());
            todos.setTextColor(getResources().getColor(R.drawable.button_style_listagem));
            cachorro.setTextColor(getResources().getColor(R.drawable.button_style_listagem));
            gato.setTextColor(getResources().getColor(R.drawable.button_style_listagem_focus));
        }
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        animalAdapter.updateList(new DaoDB().listAnimais());
    }
}
