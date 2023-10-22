package application;


import cadastro.model.util.ConectorBD;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDao;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDao;

import java.util.List;
import java.util.Scanner;

public class ListaTodos {
    private Scanner sc;
    private PessoaFisicaDao pessoaFisicaDao;
    private PessoaJuridicaDao pessoaJuridicaDao;


    public ListaTodos(Scanner sc) {
        this.sc = sc;
        this.pessoaFisicaDao = new PessoaFisicaDao(ConectorBD.getConnection());
        this.pessoaJuridicaDao = new PessoaJuridicaDao(ConectorBD.getConnection());
        getLista();
    }

    public void getLista() {
        System.out.println("==== Exibir Todas as Pessoas ====");
        String tipoPessoa;
        do {
            System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
            tipoPessoa = sc.next().toLowerCase();
        } while (!tipoPessoa.equals("f") && !tipoPessoa.equals("j"));

        if (tipoPessoa.equals("f")) {
            List<PessoaFisica> pessoasFisicas = pessoaFisicaDao.getPessoas();
            if (!pessoasFisicas.isEmpty()) {
                System.out.println("Pessoas Físicas encontradas:");
                for (PessoaFisica pessoa : pessoasFisicas) {
                    System.out.println("ID: " + pessoa.getId());
                    System.out.println("Nome: " + pessoa.getNome());
                    System.out.println("Endereço: " + pessoa.getEndereco());
                    System.out.println("Cidade: " + pessoa.getCidade());
                    System.out.println("Telefone: " + pessoa.getTelefone());
                    System.out.println("Email: " + pessoa.getEmail());
                    System.out.println("Estado: " + pessoa.getEstado());
                    System.out.println("CPF: " + pessoa.getCpf());
                    System.out.println();
                }
            } else {
                System.out.println("Nenhuma Pessoa Física encontrada.");
            }
        } else if (tipoPessoa.equals("j")) {
            List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDao.getPessoas();
            if (!pessoasJuridicas.isEmpty()) {
                System.out.println("Pessoas Jurídicas encontradas:");
                for (PessoaJuridica pessoa : pessoasJuridicas) {
                    System.out.println("ID: " + pessoa.getId());
                    System.out.println("Nome: " + pessoa.getNome());
                    System.out.println("Endereço: " + pessoa.getEndereco());
                    System.out.println("Cidade: " + pessoa.getCidade());
                    System.out.println("Telefone: " + pessoa.getTelefone());
                    System.out.println("Email: " + pessoa.getEmail());
                    System.out.println("Estado: " + pessoa.getEstado());
                    System.out.println("CNPJ: " + pessoa.getCnpj());
                    System.out.println();
                }
            } else {
                System.out.println("Nenhuma Pessoa Jurídica encontrada.");
            }
        }
    }


}
