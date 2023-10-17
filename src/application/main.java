package application;

import cadastro.model.util.ConectorBD;
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDao;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {

        System.out.println("=== Test 1: Pessoas getPessoas ===");
        PessoaFisicaDao pessoaFisicaDao = new PessoaFisicaDao(ConectorBD.getConnection());
        List<PessoaFisica> ps = pessoaFisicaDao.getPessoas();

        for (PessoaFisica obj: ps) {
            obj.exibir();
        }

        System.out.println("\n === Test 2: Pessoas Inserir ===");
        PessoaFisica newPessoa = new PessoaFisica(null,"Andre","rua2","goiania","2222","adventureandre@hotmail.com","03364545");
        pessoaFisicaDao.incluir(newPessoa);
        System.out.println("inserido"+ newPessoa.getNome());



        System.out.println("\n === Test 3: Pessoas Update ===");
        PessoaFisica mudaPessoa = pessoaFisicaDao.getPessoa(2);
        mudaPessoa.setCidade("anapolis");
        pessoaFisicaDao.alterar(mudaPessoa);
        System.out.println("Update Completed");


        System.out.println("\n === Test 4: Pessoa Delete ===");
        pessoaFisicaDao.excluir(18);
        System.out.println("Delete completed");
    }
}
