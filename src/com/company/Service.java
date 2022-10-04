package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {

    public static List<Usuario> clientesList = new ArrayList<>();

    static void metodoPrincipal() {
        metodoLogin(true);

        LoopUm:
        while (true) {
            mensagemInicial();
            Scanner scanner = new Scanner(System.in);
            int opcaoEscolhida = scanner.nextInt();
            if (opcaoEscolhida == 1) {

                criaCliente();

            } else if (opcaoEscolhida == 2) {

                Scanner removeCliente = new Scanner(System.in);
                System.out.println("Digite o cpf que você deseja excluir");
                String cpf = removeCliente.nextLine();
                removeClienteByCpf(cpf);

            } else if (opcaoEscolhida == 3) {

                Scanner removeProduto = new Scanner(System.in);
                System.out.println("Digite o cpf e o id do produto que você deseja excluir");
                String cpf = removeProduto.nextLine();
                String id = removeProduto.nextLine();
                atualizarProdutoClienteByCpfId(cpf, id);


            } else if (opcaoEscolhida == 4) {

                Scanner clienteExpecifico = new Scanner(System.in);
                System.out.println("Digite o CPF do cliente que deseja selecionar");
                String cpf = clienteExpecifico.nextLine();
                getClienteByCpf(cpf);


            } else if (opcaoEscolhida == 5) {

                getAll();


            } else if (opcaoEscolhida == 6) {
                System.out.println("Saindo....");
                break LoopUm;
            }
        }


    }

    static void mensagemInicial() {
        System.out.println("Seja bem vindo!!");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("(1) - para criar um novo cliente e adicionar no bando de dados");
        System.out.println("(2) - para remover um cliente pelo CPF dele");
        System.out.println("(3) - para remover um produto de um cliente");
        System.out.println("(4) - para imprimir as infomações de um cliente expecifico pelo seu cpf");
        System.out.println("(5) - para imprimir todas as informações dos nossos clientes");
        System.out.println("(6) - para sair do sistema");
        System.out.println("---------------------------------------------------------------------------");

    }

    static void post(Usuario clientes) {
        clientesList.add(clientes);
    }

    static void removeClienteByCpf(String cpf) {
        for (int i = 0; i < clientesList.size(); i++) {
            if (clientesList.get(i).cpf.equals(cpf)) {
                clientesList.remove(i);
                System.out.println("Cliente removido com sucesso!");
            } else {
                System.out.println("Cliente inexistente na lista!!");
            }
        }
    }

    static void atualizarProdutoClienteByCpfId(String cpf, String id) {
        for (int i = 0; i < clientesList.size(); i++) {
            boolean boolCpf = clientesList.get(i).cpf.equals(cpf);
            if (boolCpf) {
                for (int p = 0; p < clientesList.get(i).produtos.size(); p++) {
                    boolean boolId = clientesList.get(i).produtos.get(p).id.equals(id);
                    if (boolId) {
                        clientesList.get(i).produtos.remove(p);
                        System.out.println("Produto removido com sucesso!");
                    } else {
                        System.out.println("Produto inexistente na lista de compras desse cliente!");
                    }
                }
            } else {
                System.out.println("Cliente inexistente no sistema!");
            }
        }
    }

    static void getClienteByCpf(String cpf) {
        for (int i = 0; i < clientesList.size(); i++) {
            if (clientesList.get(i).cpf.equals(cpf)) {
                System.out.println("---------------------------------------------");
                System.out.println("CPF: " + clientesList.get(i).cpf);
                System.out.println("Nome: " + clientesList.get(i).nome);
                System.out.println("Total: " + clientesList.get(i).totalCompra);
                System.out.println("---------------------------------------------");
                for (Produto p : clientesList.get(i).produtos) {
                    System.out.println("---------------------------------------------");
                    System.out.println("Quantidade: " + p.quantidade);
                    System.out.println("ID: " + p.id);
                    System.out.println("Preço individual: " + p.precoIndividual);
                    System.out.println("Total de compras: " + p.precoTotal);
                    System.out.println("---------------------------------------------");
                }
            } else {
                System.out.println("Cliente inexistente!");
            }
        }
    }

    static void getAll() {
        if (clientesList.size() != 0 && !clientesList.isEmpty()) {
            for (int i = 0; i < clientesList.size(); i++) {
                System.out.println("---------------------------------------------");
                System.out.println("CPF: " + clientesList.get(i).cpf);
                System.out.println("Nome: " + clientesList.get(i).nome);
                System.out.println("Total: " + clientesList.get(i).totalCompra);
                System.out.println("---------------------------------------------");
            }
        } else {
            System.out.println("A lista está vazia!");
        }
    }

    static void criaCliente() {
        Scanner novoCLiente = new Scanner(System.in);
        System.out.println("Digite as seguintes informaçoes do cliente: cpf, nome  produtos desejados");
        String cpf = novoCLiente.nextLine();
        String nome = novoCLiente.nextLine();
        Produto produtoDesejado = new Produto();
        System.out.println("Qual o nome do produto? ");
        Scanner novoProduto = new Scanner(System.in);
        String nomeProduto = novoProduto.nextLine();
        System.out.println("Quantos desse produto você deseja? ");
        Integer quantidade = Integer.valueOf(novoProduto.nextLine());
        produtoDesejado.id = String.valueOf(produtoDesejado.hashCode());
        produtoDesejado.nome = nomeProduto;
        produtoDesejado.quantidade = quantidade;
        System.out.println("Preço do produto, por favor!");
        produtoDesejado.precoIndividual = novoProduto.nextDouble();
        produtoDesejado.atualizarPrecoTotal();
        Usuario clientes = new Usuario();
        clientes.produtos = new ArrayList<>();
        clientes.produtos.add(produtoDesejado);
        clientes.cpf = cpf;
        clientes.nome = nome;
        clientes.totalCompra = clientes.retornaTotalCompra();
        post(clientes);
    }

    static boolean metodoLogin(boolean passawor) {
        for (int i = 0; i < 3; i++) {
            Login entra = new Login();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite seu ID de usuario: ");
            entra.idLogin = scanner.nextInt();
            System.out.println("Digite sua senha: ");
            entra.senhaLogin = scanner.nextInt();
            if (entra.idLogin != 32) {
                System.out.println("Usurio nao existe, tente novamente!");
                passawor = false;
                break;
            } else if (entra.senhaLogin == 1234) {
                System.out.println("Senha incorreta, tente novamente!!");
                passawor = false;
                break;
            }
        }
        return passawor;
    }
}
