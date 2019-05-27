package com.example.petunity_mobile;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petunity_mobile.DataBase.DaoDB;
import com.example.petunity_mobile.Model.Animal;
import com.example.petunity_mobile.Model.Usuario;
import com.example.petunity_mobile.Utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class PerfilActivity extends AppCompatActivity {

    private ImageView foto;
    private TextView nome;
    private TextView especie;
    private TextView raca;
    private TextView sexo;
    private TextView nomeDono;
    private TextView endereco;

    private Usuario usuario;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        foto = findViewById(R.id.imvFoto);
        nome = findViewById(R.id.tvNomePerfil);
        especie = findViewById(R.id.tvEspeciePerfil);
        raca = findViewById(R.id.tvRacaPerfil);
        sexo = findViewById(R.id.tvSexoPerfil);
        nomeDono = findViewById(R.id.tvNomeDono);
        endereco = findViewById(R.id.tvEndereco);

        Animal animal = (Animal) getIntent().getExtras().getSerializable("animal");

        nome.setText(animal.getNome());
        especie.setText(animal.getEspecie());
        raca.setText(animal.getRaca());
        sexo.setText(animal.getSexo());

        usuario = new DaoDB().selectUsuario(animal.getIdDono());

        nomeDono.setText(usuario.getNome());
        endereco.setText(usuario.getEndereco());

        if(!StringUtils.isNullOrEmpty(animal.getFoto())){
            String fotoPath = animal.getFoto();
            Bitmap mBitmap = BitmapFactory.decodeFile(fotoPath);

            Drawable d = new BitmapDrawable(getResources(),mBitmap);

            foto.setBackground(d);
        }

        (findViewById(R.id.btAdotar)).setOnClickListener(entrarContato);
    }

    View.OnClickListener entrarContato = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent it = new Intent(PerfilActivity.this, FazerLigacaoActivity.class);
            it.putExtra("usuario", usuario);
            startActivity(it);
        }
    };
}
