package com.example.petunity_mobile.DataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.petunity_mobile.Model.Animal;
import com.example.petunity_mobile.Model.Usuario;

import java.util.ArrayList;

public class DaoDB {

    private SQLiteDatabase dbRead;
    private SQLiteDatabase dbWrit;

    public DaoDB() {
        dbRead = DataBaseOpenHelper.getInstance().getReadableDatabase();
        dbWrit = DataBaseOpenHelper.getInstance().getWritableDatabase();
    }

    //## USUÁRIO ##
    public boolean insertUsuario(Usuario usuario) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", usuario.getNome());
        contentValues.put("email", usuario.getEmail());
        contentValues.put("telefone", usuario.getTelefone());
        contentValues.put("endereco", usuario.getEndereco());
        //if (verificarUsuario(usuario)) {
            return dbWrit.insert(DataBaseOpenHelper.TABLE_USUARIO, null, contentValues) != -1;

        /*} else {
            return false;
        }*/
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

    public void testarBancoUsuario(){
        Cursor result = dbRead.query(DataBaseOpenHelper.TABLE_USUARIO, null, null, null, null, null, null);

        while (result.moveToNext()) {
            Log.i("Teste", "Id: " + result.getString(0));
            Log.i("Teste", "Nome: " + result.getString(1));
            Log.i("Teste", "E-mial: " + result.getString(2));
            Log.i("Teste", "Telefone: " + result.getString(3));
            Log.i("Teste", "Endereço: " + result.getString(4));
        }
    }
    // ----------------------------------------


    //## ANIMAL ##
    public boolean insertAnimal(Animal animal) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", animal.getNome());
        contentValues.put("especie", animal.getEspecie());
        contentValues.put("raca", animal.getRaca());
        contentValues.put("sexo", animal.getSexo());
        contentValues.put("foto", animal.getFoto());

        return dbWrit.insert(DataBaseOpenHelper.TABLE_ANIMAL, null, contentValues) != -1;
    }

    public boolean updateAnimal(Animal animal) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", animal.getNome());
        contentValues.put("especie", animal.getEspecie());
        contentValues.put("raca", animal.getRaca());
        contentValues.put("sexo", animal.getSexo());

        String where = "id = " + animal.getId();

        return dbWrit.update(DataBaseOpenHelper.TABLE_ANIMAL, contentValues, where,null) > 0;
    }

    public ArrayList<Animal> listAnimais() {
        Cursor result = dbRead.query(DataBaseOpenHelper.TABLE_ANIMAL, null, null, null, null, null, null);
        ArrayList<Animal> animais = new ArrayList<>();

        while (result.moveToNext()) {
            Animal animal = new Animal();
            animal.setId(Integer.parseInt(result.getString(0)));
            animal.setNome(result.getString(1));
            animal.setEspecie(result.getString(2));
            animal.setRaca(result.getString(3));
            animal.setSexo(result.getString(4));
            animais.add(animal);
        }
        result.close();
        return animais;
    }

    public boolean removeAnimal(Animal animal){
        String where = "id = " + animal.getId();
        return dbWrit.delete(DataBaseOpenHelper.TABLE_ANIMAL, where,null) > 0;
    }

    public void testarBancoAnimal(){
        Cursor result = dbRead.query(DataBaseOpenHelper.TABLE_ANIMAL, null, null, null, null, null, null);

        while (result.moveToNext()) {
            Log.i("Teste", "Id: " + result.getString(0));
            Log.i("Teste", "Nome: " + result.getString(1));
            Log.i("Teste", "Especie: " + result.getString(2));
            Log.i("Teste", "Raça: " + result.getString(3));
            Log.i("Teste", "Sexo: " + result.getString(4));
            Log.i("Teste", "Foto: " + result.getString(5));
        }
    }
    // ----------------------------------------
}
