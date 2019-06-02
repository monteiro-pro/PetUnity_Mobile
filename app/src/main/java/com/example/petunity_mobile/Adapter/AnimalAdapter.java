package com.example.petunity_mobile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petunity_mobile.DataBase.DaoDB;
import com.example.petunity_mobile.ListagemActivity;
import com.example.petunity_mobile.Model.Animal;
import com.example.petunity_mobile.PerfilActivity;
import com.example.petunity_mobile.R;
import com.example.petunity_mobile.Utils.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AnimalAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Animal> list;
    Animal animal;

    public AnimalAdapter(Context context, ArrayList<Animal> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        Animal currentAnimal = list.get(position);
        return currentAnimal.getId();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Animal currentAnimal = list.get(position);
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.activity_item_listagem, null);

            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.edtNomeList);
            holder.raca = convertView.findViewById(R.id.edtRacaList);
            holder.sexo = convertView.findViewById(R.id.imvSexoList);
            holder.especie = convertView.findViewById(R.id.imvEspecieList);
            holder.foto = convertView.findViewById(R.id.btnPerfil);
            holder.id = convertView.findViewById(R.id.tvId);
            holder.idDono = convertView.findViewById(R.id.tvIdDono);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.id.setText(String.valueOf(currentAnimal.getId()));
        holder.name.setText(currentAnimal.getNome());
        holder.raca.setText(currentAnimal.getRaca());
        holder.idDono.setText(String.valueOf(currentAnimal.getIdDono()));

        if(currentAnimal.getSexo().equals("Macho")){
            holder.sexo.setBackground(context.getResources().getDrawable(R.drawable.sexo_macho));
        }
        else{
            holder.sexo.setBackground(context.getResources().getDrawable(R.drawable.sexo_femea));
        }

        if(currentAnimal.getEspecie().equals("Cachorro")){
            holder.especie.setBackground(context.getResources().getDrawable(R.drawable.imagem_listagem_dog));
        }
        else{
            holder.especie.setBackground(context.getResources().getDrawable(R.drawable.imagem_listagem_cat));
        }

        if(!StringUtils.isNullOrEmpty(currentAnimal.getFoto())){
            String path = currentAnimal.getFoto();
            Bitmap mBitmap = BitmapFactory.decodeFile(path);

            Drawable d = new BitmapDrawable(mBitmap);

            holder.foto.setBackground(d);
        }

        return convertView;
    }

    public void updateList(List<Animal> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        TextView id;
        TextView name;
        TextView raca;
        ImageView sexo;
        ImageView especie;
        ImageView foto;
        TextView idDono;
    }
}
