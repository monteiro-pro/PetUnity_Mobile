package com.example.petunity_mobile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petunity_mobile.DataBase.Create;
import com.example.petunity_mobile.DataBase.DaoDB;
import com.example.petunity_mobile.Model.Animal;

import java.io.File;
import java.io.IOException;

public class CadastroAnimalActivity extends AppCompatActivity {

    private EditText nome;
    private TextView especie;
    private EditText raca;
    private TextView sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_animal);

        nome = findViewById(R.id.edtNomeAnimal);
        especie = findViewById(R.id.texto_result_especie);
        raca = findViewById(R.id.edtRaca);
        sexo = findViewById(R.id.texto_result_sexo);

        (findViewById(R.id.btConcluir)).setOnClickListener(concluirCadastro);
        (findViewById(R.id.btnDog)).setOnClickListener(mudarEspecieDog);
        (findViewById(R.id.btnCat)).setOnClickListener(mudarEspecieCat);
        (findViewById(R.id.btnMale)).setOnClickListener(mudarSexoMale);
        (findViewById(R.id.btnFemale)).setOnClickListener(mudarSexoFemale);

        (findViewById(R.id.btnFoto)).setOnClickListener(carregarFoto);

        new Create().createTableAnimal();
    }

    View.OnClickListener concluirCadastro = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Animal animal = new Animal();
            animal.setNome(nome.getText().toString());
            animal.setEspecie(especie.getText().toString());
            animal.setRaca(raca.getText().toString());
            animal.setSexo(sexo.getText().toString());

            if(new DaoDB().insertAnimal(animal)){
                Toast.makeText(CadastroAnimalActivity.this, "Pet Cadastrado!", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(CadastroAnimalActivity.this, ListagemActivity.class);
                startActivity(it);
                (findViewById(R.id.btConcluir)).setEnabled(false);
                finish();
            }
            else{
                Toast.makeText(CadastroAnimalActivity.this, "Erro no cadastro do Pet!", Toast.LENGTH_SHORT).show();
            }

            new DaoDB().testarBancoAnimal();
        }
    };

    View.OnClickListener mudarEspecieDog = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            especie.setText("Cachorro");
            (findViewById(R.id.btnCat)).setBackgroundResource(R.drawable.button_background_especie_cat);
            (findViewById(R.id.btnDog)).setBackgroundResource(R.drawable.button_background_especie_dog_select);
        }
    };

    View.OnClickListener mudarEspecieCat = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            especie.setText("Gato");
            (findViewById(R.id.btnDog)).setBackgroundResource(R.drawable.button_background_especie_dog);
            (findViewById(R.id.btnCat)).setBackgroundResource(R.drawable.button_background_especie_cat_select);
        }
    };

    View.OnClickListener mudarSexoMale = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sexo.setText("Macho");
            (findViewById(R.id.btnFemale)).setBackgroundResource(R.drawable.button_background_sexo_femea);
            (findViewById(R.id.btnMale)).setBackgroundResource(R.drawable.button_background_sexo_macho_select);
        }
    };

    View.OnClickListener mudarSexoFemale = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sexo.setText("Fêmea");
            (findViewById(R.id.btnFemale)).setBackgroundResource(R.drawable.button_background_sexo_femea_select);
            (findViewById(R.id.btnMale)).setBackgroundResource(R.drawable.button_background_sexo_macho);
        }
    };

    View.OnClickListener carregarFoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intentPegaFoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(intentPegaFoto, "Selecione uma imagem"), 123);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String teste = "dsdads";
        if(resultCode == CadastroAnimalActivity.this.RESULT_OK){
            if(requestCode == 123){
                Uri imagemSelecionada = data.getData();
                //Bitmap bitmap = BitmapFactory.decodeFile(String.valueOf(imagemSelecionada));
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagemSelecionada);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //ImageButton eeee = (findViewById(R.id.btnFoto));
                //eeee.setImageBitmap(bitmap);

                Drawable d = new BitmapDrawable(getResources(),bitmap);

                (findViewById(R.id.btnFoto)).setBackground(d);


                /*Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                File f = new File(imagemSelecionada.getEncodedPath());
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);*/
            }
        }
    }
}
