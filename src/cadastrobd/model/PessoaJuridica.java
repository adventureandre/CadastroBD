package cadastrobd.model;
public class PessoaJuridica extends Pessoa {
    private String cnpj;

    public PessoaJuridica(Integer id, String nome, String logradouro, String cidade, String telefone, String email, String cnpj) {
        super(id, nome, logradouro, cidade, telefone, email);
        this.cnpj = cnpj;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("Cnpj: " + this.cnpj);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}