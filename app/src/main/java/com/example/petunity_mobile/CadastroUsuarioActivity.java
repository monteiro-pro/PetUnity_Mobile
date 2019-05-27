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
import com.example.petunity_mobile.Utils.MaskEditUtil;
import com.example.petunity_mobile.Utils.StringUtils;

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

        telefone.addTextChangedListener(MaskEditUtil.mask(telefone, MaskEditUtil.FORMAT_FONE));

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

            if(verificarCampos()){
                if(new DaoDB().insertUsuario(usuario)){
                    Usuario usuarioDB = new DaoDB().selectUsuario();
                    Toast.makeText(CadastroUsuarioActivity.this, "Usuário Cadastrado!", Toast.LENGTH_SHORT).show();
                    Intent it = new Intent(CadastroUsuarioActivity.this, CadastroAnimalActivity.class);
                    it.putExtra("usuario", usuarioDB);
                    startActivity(it);
                    (findViewById(R.id.btContinuar)).setEnabled(false);
                    finish();
                }
                else{
                    Toast.makeText(CadastroUsuarioActivity.this, "Erro: E-mail Já Cadastrado!", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(CadastroUsuarioActivity.this, "Preencha Todos os Campos!", Toast.LENGTH_SHORT).show();
            }

            new DaoDB().testarBancoUsuario();
        }
    };

    private boolean verificarCampos(){
        if(!StringUtils.isNullOrEmpty(nome.getText().toString())
        && !StringUtils.isNullOrEmpty(email.getText().toString())
        && !StringUtils.isNullOrEmpty(telefone.getText().toString())
        && !StringUtils.isNullOrEmpty(endereco.getText().toString())){
            return true;
        }
        else{
            return false;
        }
    }
}
