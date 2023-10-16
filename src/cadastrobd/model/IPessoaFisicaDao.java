package cadastrobd.model;

import java.util.List;

public interface IPessoaFisicaDao {
    void incluir(PessoaFisica obj);
    void alterar(PessoaFisica obj);
    void excluir(Integer id);
    PessoaFisica getPessoa(Integer id);
    List<PessoaFisica> getPessoas();

}
