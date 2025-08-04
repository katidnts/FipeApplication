package com.fipe.fipeapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ModelosResponse(@JsonProperty("modelos") List<DadosModelos> modelosList){


}
