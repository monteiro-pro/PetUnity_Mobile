package com.example.petunity_mobile.Model;

public class Animal {

    private int Id;
    private String Nome;
    private String Especie;
    private String Raca;
    private String Sexo;

    public void Animal(){}

    public Animal(String nome, String especie, String raca, String sexo) {
        Nome = nome;
        Especie = especie;
        Raca = raca;
        Sexo = sexo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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
}
