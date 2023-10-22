package application;


import cadastro.model.util.ConectorBD;
import cadastrobd.model.PessoaFisicaDao;
import cadastrobd.model.PessoaJuridicaDao;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        PessoaFisicaDao pessoaFisicaDao = new PessoaFisicaDao(ConectorBD.getConnection());
        PessoaJuridicaDao pessoaJuridicaDao = new PessoaJuridicaDao(ConectorBD.getConnection());

        Scanner sc = new Scanner(System.in);

        Integer opcao;

        do {
            System.out.println("======================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("0 - Finalizar Programa");
            System.out.println("======================");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    IncluirPessoas incluirpessoas = new IncluirPessoas(sc);
                    break;
                case 2:
                    AlterarPessoas alterarPessoas = new AlterarPessoas(sc);
                    break;
                case 3:
                    ExcluirPessoas excluirPessoas = new ExcluirPessoas(sc);
                    break;
                case 4:
                    BuscarPessoas buscarPessoas = new BuscarPessoas(sc);
                    break;
                case 5:
                    ListaTodos listaTodos = new ListaTodos(sc);
                    break;
                case 0:
                    System.out.println("Programa finalizado");
                    break;
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
                    break;
            }
        }
        while (opcao != 0);
        sc.close();
    }
}
