package com.fipe.fipeapplication.principais;

import com.fipe.fipeapplication.models.*;

import com.fipe.fipeapplication.service.FipeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final FipeService fipeService = new FipeService();

    public void mostraMenu() {

        System.out.println("****OPÇÕES****");
        System.out.println("Carro");
        System.out.println("Moto");
        System.out.println("Caminhão");
        System.out.println("Informe qual modelo você deseja pesquisar: ");
        var busca = SCANNER.nextLine();


        if (busca.toLowerCase().contains("carro")) {
            busca = "carros/marcas";
            buscaEImprimeMarcas(busca);
        } else if (busca.toLowerCase().contains("mot")) {
            busca = "motos/marcas";
            buscaEImprimeMarcas(busca);
        } else if (busca.toLowerCase().contains("caminh")) {
            busca = "caminhoes/marcas";
            buscaEImprimeMarcas(busca);
        }

//        buscaEImprimeMarcas(busca);


        System.out.println("Informe o código da marca para consulta: ");
        var buscaMarca = SCANNER.nextLine();

        List<DadosModelos> todosModelosDaMarca = buscaEImprimeModelos(busca, buscaMarca);

        System.out.println("Digite um trecho do nome do veículo para consulta? ");
        var buscaCarros = SCANNER.nextLine();

        List<DadosModelos> modelosComMesmoNome = buscaListaDeModelosEspecificos(todosModelosDaMarca, buscaCarros, busca, buscaMarca);

        System.out.println("Digite o código do modelo para buscar os valores de avaliação:");
        var buscaPrecos = SCANNER.nextLine();
        imprimeListaAnos(busca, buscaMarca, buscaPrecos);
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

    private List<DadosModelos> buscaListaDeModelosEspecificos(List<DadosModelos> todosModelosDaMarca, String buscaCarros, String busca, String buscaMarca) {
        List<DadosModelos> modelosDeCarroComMesmoNOme = todosModelosDaMarca.stream()
                .filter(m -> m.toString().toUpperCase().contains(buscaCarros.toUpperCase(
                )))
                .collect(Collectors.toList());
        System.out.println("Modelos com esse nome:");
        for (DadosModelos modelos : modelosDeCarroComMesmoNOme) {
            System.out.println("Cód: " + modelos.codigo() + " - Nome: " + modelos.modelo());
        }

//        modelosDeCarroComMesmoNOme.forEach(System.out::println);
        return modelosDeCarroComMesmoNOme;
//                .orElseThrow(() -> new IllegalArgumentException("Modelo não encontrado"));
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