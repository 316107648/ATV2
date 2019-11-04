package com.ems.bdsqlitefull.pojo;

import java.io.Serializable;

// POJO - Plain Old Java Objects
public class Carro implements Serializable {
    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private float valor;

    /**
     * Método construtor vazio
     */
    public Carro() {
    }

    /**
     * Método construtor da classe com assinatura
     *
     * @param placa
     * @param marca
     * @param modelo
     * @param valor
     */
    public Carro(String placa, String marca, String modelo, float valor) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.valor = valor;
    }

    /**
     * Método construtor da classe com assinatura
     *
     * @param id
     * @param placa
     * @param marca
     * @param modelo
     * @param valor
     */
    public Carro(int id, String placa, String marca, String modelo, Float valor) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.valor = valor;
    }
    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    /**
     * Método sobrescrito para retornar o nome do aluno na ListView
     *
     * @return
     */
    @Override
    public String toString() {
        return marca;
    }

    /**
     * Método que retorna todos os dados de uma só vez
     *
     * @return
     */
    public String getDados() {
        return "ID: " + id + "\n" +
                "Placa: " + placa + "\n" +
                "Marca: " + marca + "\n" +
                "Modelo: " + modelo + "\n" +
                "Valor: " + valor;
    }
}