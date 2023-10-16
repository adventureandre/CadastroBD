package cadastrobd.model;

public class Pessoa {
    private Integer id;
    private String nome;
    private String endereco;
    private String cidade;
    private String telefone;
    private String email;

    public Pessoa() {
        // Construtor padrão
    }

    public Pessoa(Integer id, String nome, String endereco, String cidade, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.telefone = telefone;
        this.email = email;
    }

    public void exibir() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Logradouro: " + endereco);
        System.out.println("Cidade: " + cidade);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}