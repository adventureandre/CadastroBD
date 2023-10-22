package application;


import cadastro.model.util.ConectorBD;
import cadastrobd.model.PessoaFisicaDao;
import cadastrobd.model.PessoaJuridicaDao;

import java.util.Scanner;

public class ExcluirPessoas {
    private Scanner sc;
    private PessoaFisicaDao pessoaFisicaDao;
    private PessoaJuridicaDao pessoaJuridicaDao;


    public ExcluirPessoas(Scanner sc) {
        this.sc = sc;
        this.pessoaFisicaDao = new PessoaFisicaDao(ConectorBD.getConnection());
        this.pessoaJuridicaDao = new PessoaJuridicaDao(ConectorBD.getConnection());
        excluirPessoa();
    }

    public void excluirPessoa() {
        System.out.println("==== Excluir ====");
        String tipoPessoa;
        do {
            System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
            tipoPessoa = sc.next().toLowerCase();
        } while (!tipoPessoa.equals("f") && !tipoPessoa.equals("j"));

        do {
            System.out.print("ID da pessoa a ser excluída: ");
            int id = sc.nextInt();
            sc.nextLine(); // Consumir a nova linha

            if (tipoPessoa.equals("f")) {
                pessoaFisicaDao.excluir(id);
                System.out.println("Pessoa Física excluída com sucesso!");
            } else if (tipoPessoa.equals("j")) {
                pessoaJuridicaDao.excluir(id);
                System.out.println("Pessoa Jurídica excluída com sucesso!");
            }

            System.out.print("Deseja excluir outra pessoa? (S/N): ");
            String resposta = sc.next().toLowerCase();
            if (!resposta.equals("s")) {
                tipoPessoa = "null";
            }
        } while (tipoPessoa.equals("f") || tipoPessoa.equals("j"));
    }
}
