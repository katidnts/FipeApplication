package com.fipe.fipeapplication.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMarcas(String codigo, @JsonAlias("nome") String modelos, List<DadosModelos> dadosModelos) {
}
