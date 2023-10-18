package application;

import cadastro.model.util.ConectorBD;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDao;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDao;

import java.util.List;

public class CadastroBDTeste {
    public static void main(String[] args) {
        PessoaFisicaDao pessoaFisicaDao = new PessoaFisicaDao(ConectorBD.getConnection());
        PessoaJuridicaDao pessoaJuridicaDao = new PessoaJuridicaDao(ConectorBD.getConnection());

        System.out.println("===Incluido Pessoa===");
        PessoaFisica newpessoa = new PessoaFisica(null, "Marcos", "rua prof julita", "Leopoldo", "GO", "6296223054", "sivaldops68.gmail", "27168");
        pessoaFisicaDao.incluir(newpessoa);
        System.out.println("Pessoa Incluido com Sucesso!");


        System.out.println("\n ===Alterar Pessoa===");
        newpessoa = pessoaFisicaDao.getPessoa(2);
        newpessoa.setNome("Aurora2");
        newpessoa.setCpf("545454545");
        pessoaFisicaDao.alterar(newpessoa);
        System.out.println("Pessoa Alterada com Sucesso!");


        System.out.println("\n====Lista todas Pessoas====");
        List<PessoaFisica> psf = pessoaFisicaDao.getPessoas();

        for (PessoaFisica obj : psf) {
            obj.exibir();
            System.out.println("<---------------->");
        }


        System.out.println("\n====Deleta pessoa====");
        pessoaFisicaDao.excluir(46);
        System.out.println("Pessoa Deletada com Sucesso!");


        System.out.println("\n ===Incluido PessoaJuridica===");
        PessoaJuridica newPessoaJuridica = new PessoaJuridica(null, "EXpertCustom", "rua prof Julita", "anapolis", "MA", "629898989", "exprt@email", "4540001-47");
        pessoaJuridicaDao.incluir(newPessoaJuridica);
        System.out.println("Pessoa juridica Adicionada com Sucesso!");


        System.out.println("\n===Alterar PessoaJuridica===");
        PessoaJuridica newpessoaJuridica = pessoaJuridicaDao.getPessoa(4);
        newpessoaJuridica.setNome("ALFASET");
        newpessoaJuridica.setCnpj("00004");
        pessoaJuridicaDao.alterar(newpessoaJuridica);
        System.out.println("PessoaJuridica Alterada com Sucesso!");


        System.out.println("\n ===Lista todas PessoasJuridica===");
        List<PessoaJuridica> psj = pessoaJuridicaDao.getPessoas();

        for (PessoaJuridica obj : psj) {
            obj.exibir();
            System.out.println("<---------------->");
        }


        System.out.println("\n ====Deleta pessoaJuridica====");
        pessoaFisicaDao.excluir(49);
        System.out.println("PessoaJuridica Deletada com Sucesso!");

    }
}
