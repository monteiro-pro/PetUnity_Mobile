package com.example.petunity_mobile.DataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.petunity_mobile.Model.Usuario;

import java.util.ArrayList;

public class DaoDB {

    private SQLiteDatabase dbRead;
    private SQLiteDatabase dbWrit;

    public DaoDB() {
        dbRead = DataBaseOpenHelper.getInstance().getReadableDatabase();
        dbWrit = DataBaseOpenHelper.getInstance().getWritableDatabase();
    }

    public boolean insertUsuario(Usuario usuario) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", usuario.getNome());
        contentValues.put("email", usuario.getEmail());
        contentValues.put("telefone", usuario.getTelefone());
        contentValues.put("endereco", usuario.getEndereco());
        if (verificarUsuario(usuario)) {
            return dbWrit.insert(DataBaseOpenHelper.TABLE_USUARIO, null, contentValues) != -1;

        } else {
            return false;
        }
    }

    public boolean updateUsuario(Usuario usuario) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", usuario.getNome());
        contentValues.put("email", usuario.getEmail());
        contentValues.put("telefone", usuario.getTelefone());
        contentValues.put("endereco", usuario.getEndereco());

        String where = "id = " + usuario.getId();

        return dbWrit.update(DataBaseOpenHelper.TABLE_USUARIO, contentValues, where,null) > 0;
    }

    public ArrayList<Usuario> listUsuarios() {
        Cursor result = dbRead.query(DataBaseOpenHelper.TABLE_USUARIO, null, null, null, null, null, null);
        ArrayList<Usuario> usuarios = new ArrayList<>();

        while (result.moveToNext()) {
            Usuario usuario = new Usuario();
            usuario.setId(Integer.parseInt(result.getString(0)));
            usuario.setNome(result.getString(1));
            usuario.setEmail(result.getString(2));
            usuario.setTelefone(result.getString(3));
            usuario.setEndereco(result.getString(4));
            usuarios.add(usuario);

            Log.i("rblb", "Id: " + result.getString(0));
            Log.i("rblb", "Nome: " + result.getString(1));
            Log.i("rblb", "E-mial: " + result.getString(2));
            Log.i("rblb", "Telefone: " + result.getString(3));
            Log.i("rblb", "Endereço: " + result.getString(4));
        }
        result.close();
        return usuarios;
    }

    public boolean removeUsuario(Usuario usuario){
        String where = "id = " + usuario.getId();
        return dbWrit.delete(DataBaseOpenHelper.TABLE_USUARIO, where,null) > 0;
    }

    public boolean verificarUsuario(Usuario usuario) {
        Cursor result = dbRead.rawQuery("SELECT * from " + DataBaseOpenHelper.TABLE_USUARIO + " WHERE email = '" + usuario.getEmail() + "'", null);

        boolean canInsert = true;

        while (result.moveToNext()) {
            canInsert = false;
        }

        result.close();
        return canInsert;
    }

    public void testarBanco(){
        Cursor result = dbRead.query(DataBaseOpenHelper.TABLE_USUARIO, null, null, null, null, null, null);

        while (result.moveToNext()) {
            Log.i("Teste", "Id: " + result.getString(0));
            Log.i("Teste", "Nome: " + result.getString(1));
            Log.i("Teste", "E-mial: " + result.getString(2));
            Log.i("Teste", "Telefone: " + result.getString(3));
            Log.i("Teste", "Endereço: " + result.getString(4));
        }
    }
}
