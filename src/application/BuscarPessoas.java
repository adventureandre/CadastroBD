package application;


import cadastro.model.util.ConectorBD;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDao;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDao;

import java.util.Scanner;

public class BuscarPessoas {
    private Scanner sc;
    private PessoaFisicaDao pessoaFisicaDao;
    private PessoaJuridicaDao pessoaJuridicaDao;


    public BuscarPessoas(Scanner sc) {
        this.sc = sc;
        this.pessoaFisicaDao = new PessoaFisicaDao(ConectorBD.getConnection());
        this.pessoaJuridicaDao = new PessoaJuridicaDao(ConectorBD.getConnection());
        buscarPessoaPorId();
    }

    public void buscarPessoaPorId() {
        System.out.println("==== Buscar por ID ====");
        String tipoPessoa;
        do {
            System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
            tipoPessoa = sc.next().toLowerCase();
        } while (!tipoPessoa.equals("f") && !tipoPessoa.equals("j"));

        do {
            System.out.print("ID da pessoa a ser buscada: ");
            int id = sc.nextInt();
            sc.nextLine(); // Consumir a nova linha

            if (tipoPessoa.equals("f")) {
             PessoaFisica pessoaBuscada =  pessoaFisicaDao.getPessoa(id);
                if (pessoaBuscada != null) {
                    System.out.println("Pessoa Física encontrada:");
                    System.out.println("Nome: "+pessoaBuscada.getNome());
                    System.out.println("Endereço: "+pessoaBuscada.getEndereco());
                    System.out.println("Cidade: "+pessoaBuscada.getCidade());
                    System.out.println("Telefone: "+pessoaBuscada.getTelefone());
                    System.out.println("Email: "+pessoaBuscada.getEmail());
                    System.out.println("Estado: "+pessoaBuscada.getEstado());
                    System.out.println("Cpf: "+pessoaBuscada.getCpf());
                } else {
                    System.out.println("Pessoa Física com ID " + id + " não encontrada.");
                }
            } else if (tipoPessoa.equals("j")) {
             PessoaJuridica pessoaBuscada  = pessoaJuridicaDao.getPessoa(id);
                if (pessoaBuscada != null) {
                    System.out.println("Pessoa Jurídica encontrada:");
                    System.out.println("Nome: "+pessoaBuscada.getNome());
                    System.out.println("Endereço: "+pessoaBuscada.getEndereco());
                    System.out.println("Cidade: "+pessoaBuscada.getCidade());
                    System.out.println("Telefone: "+pessoaBuscada.getTelefone());
                    System.out.println("Email: "+pessoaBuscada.getEmail());
                    System.out.println("Estado: "+pessoaBuscada.getEstado());
                    System.out.println("Cnpj: "+pessoaBuscada.getCnpj());
                } else {
                    System.out.println("Pessoa Jurídica com ID " + id + " não encontrada.");
                }
            }

            System.out.print("Deseja buscar outra pessoa por ID? (S/N): ");
            String resposta = sc.next().toLowerCase();
            if (!resposta.equals("s")) {
                tipoPessoa = "null";
            }
        } while (tipoPessoa.equals("f") || tipoPessoa.equals("j"));
    }

}
