package cadastrobd.model;
public class PessoaFisica extends Pessoa {
    private String cpf;

    public PessoaFisica(Integer id, String nome, String logradouro, String cidade, String telefone, String email, String cpf) {
        super(id, nome, logradouro, cidade, telefone, email);
        this.cpf = cpf;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CPF: " + cpf);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}