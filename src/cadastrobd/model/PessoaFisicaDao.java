package cadastrobd.model;

import cadastro.model.util.ConectorBD;
import cadastro.model.util.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaFisicaDao implements IPessoaFisicaDao {
    private Connection conn;

    public PessoaFisicaDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void incluir(PessoaFisica obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO pessoas " +
                            "(nome, endereco, cidade, telefone, email) " +
                            "VALUES (?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1,obj.getNome());
            st.setString(2, obj.getEndereco());
            st.setString(3,obj.getCidade());
            st.setString(4,obj.getTelefone());
            st.setString(5,obj.getEmail());
            st.executeUpdate();

            st = conn.prepareStatement("INSERT INTO pessoa_fisica " +
                    "(id_pessoa, cpf) " +
                    "VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setInt(1,obj.getId());
            st.setString(2,obj.getCpf());



            int rowAffected = st.executeUpdate();

            if (rowAffected > 0){
                ResultSet rs = st.getGeneratedKeys();

                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                ConectorBD.closeResultSet(rs);
            }else {
                throw new DbException("Erro inesperado! Nenhuma linha afetada!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());

        } finally {
            ConectorBD.closeStatement(st);

        }
    }

    @Override
    public void alterar(PessoaFisica obj) {

    }

    @Override
    public void excluir(Integer id) {

    }

    @Override
    public PessoaFisica getPessoa(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT pessoas.*, pessoa_fisica.cpf as cpf " +
                    "FROM pessoas INNER JOIN pessoa_fisica on pessoas.id_pessoa =  pessoa_fisica.id_pessoa " +
                    "WHERE pessoas.id_pessoa = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                PessoaFisica obj = instantiatePessoa(rs);
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            ConectorBD.closeStatement(st);
            ConectorBD.closeResultSet(rs);
        }

    }

    @Override
    public List<PessoaFisica> getPessoas() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT pessoas.*, pessoa_fisica.cpf as cpf " +
                    "FROM pessoas INNER JOIN pessoa_fisica on pessoas.id_pessoa =  pessoa_fisica.id_pessoa " +
                    "ORDER BY nome");

            rs = st.executeQuery();

            List<PessoaFisica> list = new ArrayList<>();

            while (rs.next()) {
                PessoaFisica obj = instantiatePessoa(rs);
                list.add(obj);
            }
            return list;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            ConectorBD.closeStatement(st);
            ConectorBD.closeResultSet(rs);
        }

    }

    private PessoaFisica instantiatePessoa(ResultSet rs) throws SQLException {
        PessoaFisica obj = new PessoaFisica(
                rs.getInt("id_pessoa"),
                rs.getString("nome"),
                rs.getString("endereco"),
                rs.getString("cidade"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("cpf")
        );
        return obj;
    }
}
