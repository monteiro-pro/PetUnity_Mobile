package com.example.petunity_mobile;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petunity_mobile.DataBase.Create;
import com.example.petunity_mobile.DataBase.DaoDB;
import com.example.petunity_mobile.Model.Animal;
import com.example.petunity_mobile.Model.Usuario;
import com.example.petunity_mobile.Utils.StringUtils;

import java.io.File;
import java.io.IOException;

public class CadastroAnimalActivity extends AppCompatActivity {

    private EditText nome;
    private TextView especie;
    private EditText raca;
    private TextView sexo;
    private String foto;
    private int idDono;
    private Button remover;

    private AlertDialog alerta;

    private Animal animalEditar;
    private Usuario usuarioCadastro;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_animal);

        nome = findViewById(R.id.edtNomeAnimal);
        especie = findViewById(R.id.texto_result_especie);
        raca = findViewById(R.id.edtRaca);
        sexo = findViewById(R.id.texto_result_sexo);
        remover = findViewById(R.id.btRemover);

        usuarioCadastro = (Usuario) getIntent().getExtras().getSerializable("usuario");

        if(usuarioCadastro != null){
            idDono = usuarioCadastro.getId();
            remover.setText("Cancelar");
            (findViewById(R.id.btConcluir)).setOnClickListener(concluirCadastro);
            (findViewById(R.id.btRemover)).setOnClickListener(cancelar);
        }

        animalEditar = (Animal) getIntent().getExtras().getSerializable("animalEditar");

        if(animalEditar != null){
            nome.setText(animalEditar.getNome());
            especie.setText(animalEditar.getEspecie());
            raca.setText(animalEditar.getRaca());
            sexo.setText(animalEditar.getSexo());

            if(!StringUtils.isNullOrEmpty(animalEditar.getFoto())){
                String fotoPath = animalEditar.getFoto();
                Bitmap mBitmap = BitmapFactory.decodeFile(fotoPath);

                Drawable d = new BitmapDrawable(getResources(),mBitmap);
                (findViewById(R.id.btnFoto)).setBackground(d);
            }

            if(animalEditar.getEspecie().equals("Cachorro")){
                (findViewById(R.id.btnDog)).setBackground(getResources().getDrawable(R.drawable.especie_dog));
                (findViewById(R.id.btnCat)).setBackground(getResources().getDrawable(R.drawable.especie_cat_press));
            }
            else{
                (findViewById(R.id.btnCat)).setBackground(getResources().getDrawable(R.drawable.especie_cat));
                (findViewById(R.id.btnDog)).setBackground(getResources().getDrawable(R.drawable.especie_dog_press));
            }

            if(animalEditar.getSexo().equals("Macho")){
                (findViewById(R.id.btnMale)).setBackground(getResources().getDrawable(R.drawable.sexo_macho));
                (findViewById(R.id.btnFemale)).setBackground(getResources().getDrawable(R.drawable.sexo_femea_press));
            }
            else{
                (findViewById(R.id.btnFemale)).setBackground(getResources().getDrawable(R.drawable.sexo_femea));
                (findViewById(R.id.btnMale)).setBackground(getResources().getDrawable(R.drawable.sexo_macho_press));
            }

            (findViewById(R.id.btConcluir)).setOnClickListener(atualizarCadastro);
            (findViewById(R.id.btRemover)).setOnClickListener(removerPet);
        }

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
            animal.setFoto(foto);
            animal.setIdDono(idDono);

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

    View.OnClickListener atualizarCadastro = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            animalEditar.setNome(nome.getText().toString());
            animalEditar.setEspecie(especie.getText().toString());
            animalEditar.setRaca(raca.getText().toString());
            animalEditar.setSexo(sexo.getText().toString());
            //animalEditar.setFoto(foto);
            animalEditar.setIdDono(animalEditar.getIdDono());

            if(new DaoDB().updateAnimal(animalEditar)){
                Toast.makeText(CadastroAnimalActivity.this, "Informações Atualizadas!", Toast.LENGTH_SHORT).show();
                (findViewById(R.id.btConcluir)).setEnabled(false);
                finish();
            }
            else{
                Toast.makeText(CadastroAnimalActivity.this, "Erro na atualização do Pet!", Toast.LENGTH_SHORT).show();
            }

            new DaoDB().testarBancoAnimal();
        }
    };

    View.OnClickListener cancelar = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new DaoDB().removeUsuario(usuarioCadastro);
            Intent it = new Intent(CadastroAnimalActivity.this, MainActivity.class);
            startActivity(it);
            (findViewById(R.id.btRemover)).setEnabled(false);
            finish();
        }
    };

    View.OnClickListener removerPet = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View v) {
            alert_remover();
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
            sexo.setText(R.string.sexo_macho);
            (findViewById(R.id.btnFemale)).setBackgroundResource(R.drawable.button_background_sexo_femea);
            (findViewById(R.id.btnMale)).setBackgroundResource(R.drawable.button_background_sexo_macho_select);
        }
    };

    View.OnClickListener mudarSexoFemale = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sexo.setText(R.string.sexo_femea);
            (findViewById(R.id.btnFemale)).setBackgroundResource(R.drawable.button_background_sexo_femea_select);
            (findViewById(R.id.btnMale)).setBackgroundResource(R.drawable.button_background_sexo_macho);
        }
    };

    View.OnClickListener carregarFoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String[] galleryPermissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (ContextCompat.checkSelfPermission(CadastroAnimalActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(CadastroAnimalActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                } else {
                    ActivityCompat.requestPermissions(CadastroAnimalActivity.this,
                            galleryPermissions, 1);
                }
            }

            Intent intentPegaFoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(Intent.createChooser(intentPegaFoto, "Selecione uma imagem"), 123);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void alert_remover() {
        LayoutInflater li = getLayoutInflater();

        View view = li.inflate(R.layout.alert_remove_pet, null);
        view.findViewById(R.id.btnAlertConfirmar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                alerta.dismiss();

                new DaoDB().removeAnimal(animalEditar);

                Usuario donoAnimal = new  DaoDB().selectUsuario(animalEditar.getIdDono());
                new DaoDB().removeUsuario(donoAnimal);

                Toast.makeText(CadastroAnimalActivity.this, "Pet Removido!", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(CadastroAnimalActivity.this, ListagemActivity.class);
                startActivity(it);
                (findViewById(R.id.btRemover)).setEnabled(false);
                finish();
            }
        });

        view.findViewById(R.id.btnAlertNegar).setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                alerta.dismiss();
            }
        });

        if(!StringUtils.isNullOrEmpty(animalEditar.getFoto())){
            String fotoPath = animalEditar.getFoto();
            Bitmap mBitmap = BitmapFactory.decodeFile(fotoPath);

            Drawable d = new BitmapDrawable(getResources(),mBitmap);
            view.findViewById(R.id.imvAlert).setBackground(d);
        } else {
            Drawable image = getResources().getDrawable(R.drawable.borda_listagem);
            view.findViewById(R.id.imvAlert).setBackground(image);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remover Registro?");
        builder.setView(view);
        alerta = builder.create();
        alerta.show();
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == CadastroAnimalActivity.this.RESULT_OK){
            if(requestCode == 123){
                Uri imagemSelecionada = data.getData();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagemSelecionada);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Drawable d = new BitmapDrawable(getResources(),bitmap);

                (findViewById(R.id.btnFoto)).setBackground(d);

                File file = new File(getRealPathFromURI(imagemSelecionada));

                foto = file.getPath();

                /*Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                File f = new File(imagemSelecionada.getEncodedPath());
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);*/
            }
        }
    }
}
