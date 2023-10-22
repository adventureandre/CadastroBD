package application;

import cadastro.model.util.ConectorBD;
import cadastrobd.model.*;

import java.util.Scanner;

public class AlterarPessoas {
    private Scanner sc;
    private PessoaFisicaDao pessoaFisicaDao;
    private PessoaJuridicaDao pessoaJuridicaDao;

    public AlterarPessoas(Scanner sc) {
        this.sc = sc;
        this.pessoaFisicaDao = new PessoaFisicaDao(ConectorBD.getConnection());
        this.pessoaJuridicaDao = new PessoaJuridicaDao(ConectorBD.getConnection());
        adicionarPessoa();
    }

    private void adicionarPessoa() {
        System.out.println("==== Alterar ====");
        String tipoPessoa;
        do {
            System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
            tipoPessoa = sc.next().toLowerCase();
        } while (!tipoPessoa.equals("f") && !tipoPessoa.equals("j"));


        do {
            Pessoa pessoa = null;

            if (tipoPessoa.equals("f")) {
                System.out.println("Alterar Pessoa Física:");
                sc.nextLine();

                pessoa = scPessoa();

                System.out.print("CPF: ");
                String cpf = sc.nextLine();

                PessoaFisica newPessoa = new PessoaFisica(pessoa.getId(), pessoa.getNome(), pessoa.getEndereco(), pessoa.getCidade(), pessoa.getEstado(), pessoa.getTelefone(), pessoa.getEmail(), cpf);
                pessoaFisicaDao.alterar(newPessoa);

                System.out.println("Pessoa Alterada com sucesso!\n");


            } else if (tipoPessoa.equals("j")) {
                System.out.println("Alterar Pessoa Juridica:");
                sc.nextLine();

                pessoa = scPessoa();

                System.out.print("Cnpj: ");
                String cnpj = sc.nextLine();

                PessoaJuridica newPessoa = new PessoaJuridica(pessoa.getId(), pessoa.getNome(), pessoa.getEndereco(), pessoa.getCidade(), pessoa.getEstado(), pessoa.getTelefone(), pessoa.getEmail(), cnpj);
                pessoaJuridicaDao.alterar(newPessoa);

                System.out.println("Pessoa Alterada com sucesso!\n");

            }
            tipoPessoa = "null";
        } while (tipoPessoa.equals("f") || tipoPessoa.equals("j"));
    }


    private Pessoa scPessoa() {

        System.out.print("Id: ");
        Integer id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Endereço: ");
        String endereco = sc.nextLine();

        System.out.print("Cidade: ");
        String cidade = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Estado: ");
        String estado = sc.nextLine();

        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        pessoa.setNome(nome);
        pessoa.setEndereco(endereco);
        pessoa.setCidade(cidade);
        pessoa.setTelefone(telefone);
        pessoa.setEmail(email);
        pessoa.setEstado(estado);

        return pessoa;
    }
}
