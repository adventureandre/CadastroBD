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
    }
}
