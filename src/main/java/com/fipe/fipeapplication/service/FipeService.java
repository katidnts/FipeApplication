package com.fipe.fipeapplication.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fipe.fipeapplication.models.Ano;
import com.fipe.fipeapplication.models.DadosMarcas;
import com.fipe.fipeapplication.models.DadosModelos;
import com.fipe.fipeapplication.models.ModelosResponse;
import com.fipe.fipeapplication.principais.Principal;

import java.lang.reflect.Type;
import java.util.List;

public class FipeService {
    private static final String URL_FIPE = "https://parallelum.com.br/fipe/api/v1/";
    private static final String PATH_MARCAS = "/marcas";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final FipeClient client = new FipeClient();
    private static final ConverteDados conversor = new ConverteDados();


    public List<DadosMarcas> buscaDadosMarcas(String busca) {
        var json = client.obterDados(URL_FIPE + busca);
        List<DadosMarcas> marcas = conversor.obterDados(json, new TypeReference<List<DadosMarcas>>() {
        });
        return marcas;
    }

    public ModelosResponse buscaDadosModelos(String busca, String buscaMarca) {
        String jsonModelos = client.obterDados(URL_FIPE + busca + "/" + buscaMarca + "/modelos");
        ModelosResponse resposta = conversor.obterDados(jsonModelos, ModelosResponse.class);
        return resposta;
    }

    public List<Ano> buscaListaDeAnos(String busca, String buscaMarca, String buscaCarros) {
        String jsonCarros1 = client.obterDados(URL_FIPE + busca + "/" + buscaMarca + "/modelos/" + buscaCarros + "/anos");
        List<Ano> anos = conversor.obterDados(jsonCarros1, new TypeReference<List<Ano>>() {
        });
        return anos;
    }

    public DadosModelos buscaCadaAno(String busca, String buscaMarca, String buscaCarros, String codigo) {
        String jsonCarros;

            jsonCarros = client.obterDados(
                    URL_FIPE + busca + "/" + buscaMarca + "/modelos/" + buscaCarros + "/anos/" + codigo);

        DadosModelos modelo = conversor.obterDados(jsonCarros, DadosModelos.class);
        return modelo;
    }
}
