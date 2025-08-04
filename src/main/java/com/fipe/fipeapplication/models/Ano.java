package com.fipe.fipeapplication.models;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Ano(String codigo, String nome, @JsonAlias("ano") String ano) {
}
