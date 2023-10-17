package cadastrobd.model;

import java.util.List;

public interface IPessoaJuridicaDao {
    void incluir(PessoaJuridica obj);
    void alterar(PessoaJuridica obj);
    void excluir(Integer id);
    PessoaJuridica getPessoa(Integer id);
    List<PessoaJuridica> getPessoas();

}
