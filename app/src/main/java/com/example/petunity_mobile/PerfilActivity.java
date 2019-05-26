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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class PerfilActivity extends AppCompatActivity {

    private String teste = "/storage/emulated/0/Movies/Naruto Shippuden/Naruto Shippuden 7ª Temporada/volume45.png";
    private ImageView foto;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        foto = findViewById(R.id.imvFoto);

        String teste = "/storage/emulated/0/Movies/Naruto Shippuden/Naruto Shippuden 7ª Temporada/volume45.png";
        Bitmap mBitmap = BitmapFactory.decodeFile(teste);

        Drawable d = new BitmapDrawable(getResources(),mBitmap);

        foto.setBackground(d);

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
