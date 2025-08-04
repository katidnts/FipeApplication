package com.fipe.fipeapplication.principais;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fipe.fipeapplication.models.*;

import com.fipe.fipeapplication.service.ConverteDados;
import com.fipe.fipeapplication.service.FipeClient;
import com.fipe.fipeapplication.service.FipeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final FipeService fipeService = new FipeService();

    public void mostraMenu() {

        System.out.println("****OPÇÕES****");
        System.out.println("Carros");
        System.out.println("Motos");
        System.out.println("Caminhões");
        System.out.println("Informe qual modelo você deseja pesquisar: ");
        var busca = SCANNER.nextLine();
        buscaEImprimeMarcas(busca);

        System.out.println("Informe o código da marca para consulta: ");
        var buscaMarca = SCANNER.nextLine();

        List<DadosModelos> todosModelosDaMarca = buscaEImprimeModelos(busca, buscaMarca);

        System.out.println("Digite um trecho do nome do veículo para consulta? ");
        var buscaCarros = SCANNER.nextLine();


        String codigoDoModelo = buscaModelo(todosModelosDaMarca, buscaCarros, busca, buscaMarca);
        imprimeListaAnos(busca, buscaMarca, codigoDoModelo);
    }

    private void buscaEImprimeMarcas(String busca) {

        List<DadosMarcas> marcas = fipeService.buscaDadosMarcas(busca);
        for (DadosMarcas marca : marcas) {
            System.out.println("Cod: " + marca.codigo() + " - Descrição: " + marca.modelos());
        }
    }

    private List<DadosModelos> buscaEImprimeModelos(String busca, String buscaMarca) {
        List<DadosModelos> todosModelosDaMarca = new ArrayList<>();

        ModelosResponse resposta = fipeService.buscaDadosModelos(busca, buscaMarca);
        for (DadosModelos modelo : resposta.modelosList()) {
            System.out.println("Cód: " + modelo.codigo() + " - Nome: " + modelo.modelo());
            todosModelosDaMarca.add(modelo);
        }
        return todosModelosDaMarca;
    }

    private String buscaModelo(List<DadosModelos> todosModelosDaMarca, String buscaCarros, String busca, String buscaMarca) {
        return todosModelosDaMarca.stream()
                .filter(m -> m.toString().toUpperCase().contains(buscaCarros.toUpperCase(
                )))
                .findFirst()
                .map(DadosModelos::codigo)
                .orElseThrow(() -> new IllegalArgumentException("Modelo não encontrado"));
    }

    private void imprimeListaAnos(String busca, String buscaMarca, String buscaCarros) {


        List<Ano> anos = fipeService.buscaListaDeAnos(busca, buscaMarca, buscaCarros);

        if (anos == null || anos.isEmpty()) {
            System.out.println("⚠️ Nenhum ano encontrado.");
            return;
        }

        for (Ano ano : anos) {
            DadosModelos modelo = fipeService.buscaCadaAno(busca, buscaMarca, buscaCarros, ano.codigo());
            System.out.println("Cód: " + ano.codigo() + " - Preço: " + modelo.valor() + " - Marca: " + modelo.marca() + " - Nome: " + modelo.modelo() + " - Ano: " + modelo.ano() + " - Combustível: " + modelo.combustivel());


        }
    }
}

/*
1- Mostrar menu
2-receber a opção digitada
3-Imprimir a Lista de marcas
4-perguntar qual modelo deseja buscar
5-mostrar todas as opções daquele modelo
6-perguntar qual das opções deseja buscar os anos e os preços
7-mostrar as opções de ano e preço desse modelo
 */