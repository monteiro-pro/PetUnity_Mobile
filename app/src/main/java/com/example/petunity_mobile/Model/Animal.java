package com.example.petunity_mobile.Model;

import java.io.Serializable;

public class Animal implements Serializable {

    private int Id;
    private int IdDono;
    private String Nome;
    private String Especie;
    private String Raca;
    private String Sexo;
    private String Foto;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdDono() {
        return IdDono;
    }

    public void setIdDono(int idDono) {
        IdDono = idDono;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEspecie() {
        return Especie;
    }

    public void setEspecie(String especie) {
        Especie = especie;
    }

    public String getRaca() {
        return Raca;
    }

    public void setRaca(String raca) {
        Raca = raca;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        Foto = foto;
    }
}
