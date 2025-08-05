package com.fipe.fipeapplication.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModelos(@JsonAlias("TipoVeiculo") Integer tipoVeiculo,
                           @JsonAlias("Valor") String valor,
                           String codigo,
                           @JsonAlias("Marca") String marca,
                           @JsonAlias({"Modelo", "nome"}) String modelo,
                           @JsonAlias("AnoModelo") String ano,
                           @JsonAlias("Combustivel") String combustivel) {

}
