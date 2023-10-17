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

//        System.out.println("Incluido Pessoa");
//        PessoaFisica newpessoa = new PessoaFisica(null,"Marcos","rua prof julita","Leopoldo","GO","6296223054","sivaldops68.gmail","27168");
//        PessoaFisicaDao pessoaDB = new PessoaFisicaDao(ConectorBD.getConnection());
//        pessoaDB.incluir(newpessoa);
//        System.out.println("Pessoa Incluido com Sucesso!");


//        System.out.println("Alterar Pessoa");
//        PessoaFisica newpessoa = pessoaFisicaDao.getPessoa(2);
//        newpessoa.setNome("Aurora");
//        newpessoa.setCpf("545454545");
//        pessoaFisicaDao.alterar(newpessoa);
//        System.out.println("Pessoa Alterada com Sucesso!");


//        System.out.println("Lista todas Pessoas");
//        List<PessoaFisica> ps = pessoaFisicaDao.getPessoas();
//
//        for (PessoaFisica obj: ps) {
//            obj.exibir();
//        }


//        System.out.println("Deleta pessoa");
//        pessoaFisicaDao.excluir(42);
//        System.out.println("Pessoa Deletada com Sucesso!");


//        System.out.println("Incluido PessoaJuridica");
//        PessoaJuridica newPessoaJuridica = new PessoaJuridica(null,"EXpertCustom","rua prof Julita","anapolis","MA","629898989","exprt@email","4540001-47");
//        pessoaJuridicaDao.incluir(newPessoaJuridica);
//        System.out.println("Pessoa juridica Adicionada com Sucesso!");


//        System.out.println("Alterar PessoaJuridica");
//        PessoaJuridica newpessoaJuridica = pessoaJuridicaDao.getPessoa(44);
//        newpessoaJuridica.setNome("ALFASET");
//        newpessoaJuridica.setCnpj("00004");
//        pessoaJuridicaDao.alterar(newpessoaJuridica);
//        System.out.println("PessoaJuridica Alterada com Sucesso!");




//        System.out.println("Lista todas PessoasJuridica");
//        List<PessoaJuridica> ps = pessoaJuridicaDao.getPessoas();
//
//        for (PessoaJuridica obj : ps) {
//            obj.exibir();
//        }


//                System.out.println("Deleta pessoaJuridica");
//        pessoaFisicaDao.excluir(44);
//        System.out.println("PessoaJuridica Deletada com Sucesso!");

    }
}
