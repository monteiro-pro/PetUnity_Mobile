package com.example.petunity_mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petunity_mobile.DataBase.Create;
import com.example.petunity_mobile.DataBase.DaoDB;
import com.example.petunity_mobile.Model.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText telefone;
    private EditText endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        nome = findViewById(R.id.edtNomeUsuario);
        email = findViewById(R.id.edtEmail);
        telefone = findViewById(R.id.edtTelefone);
        endereco = findViewById(R.id.edtEndereco);

        (findViewById(R.id.btContinuar)).setOnClickListener(cadastrarUsuario);

        new Create().createTableUsuario();
    }

    View.OnClickListener cadastrarUsuario = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Usuario usuario = new Usuario();
            usuario.setNome(nome.getText().toString());
            usuario.setEmail(email.getText().toString());
            usuario.setTelefone(telefone.getText().toString());
            usuario.setEndereco(endereco.getText().toString());

            if(new DaoDB().insertUsuario(usuario)){
                Toast.makeText(CadastroUsuarioActivity.this, "Usuário Cadastrado!", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(CadastroUsuarioActivity.this, CadastroAnimalActivity.class);
                startActivity(it);
                limparCampos();
            }
            else{
                Toast.makeText(CadastroUsuarioActivity.this, "Erro: E-mail Já Cadastrado!", Toast.LENGTH_SHORT).show();
            }

            new DaoDB().testarBanco();
        }
    };

    private void limparCampos(){
        nome.setText("");
        email.setText("");
        telefone.setText("");
        endereco.setText("");
    }
}
