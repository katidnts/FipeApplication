package com.fipe.fipeapplication.models;

public class Modelo {
    private Integer tipoVeiculo;
    private String codigo;
    private String marca;
    private String modelo;
    private String valor;
    private String ano;
    private String combustivel;

    public Modelo(String codigo, DadosModelos dadosModelos) {
        this.tipoVeiculo = dadosModelos.tipoVeiculo();
        this.codigo = dadosModelos.codigo();
        this.marca = dadosModelos.marca();
        this.modelo = dadosModelos.modelo();
        this.ano = dadosModelos.ano();
        this.valor = dadosModelos.valor();
        this.combustivel = dadosModelos.combustivel();
    }

    public Integer getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(Integer tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    @Override
    public String toString() {
        return "Veículos{" +
                ", modelo='" + modelo + '\'' +
                ", valor=" + valor +
                ", ano='" + ano + '\'' +
                ", combustivel='" + combustivel + '\'' +
                '}';
    }
}
