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
                            "(nome, endereco, cidade, estado, telefone, email) " +
                            "VALUES (?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setString(2, obj.getEndereco());
            st.setString(3, obj.getCidade());
            st.setString(4, obj.getEstado());
            st.setString(5, obj.getTelefone());
            st.setString(6, obj.getEmail());


            int rowAffected = st.executeUpdate();

            if (rowAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                ConectorBD.closeResultSet(rs);

                st = conn.prepareStatement("INSERT INTO pessoa_fisica " +
                                "(id_pessoa, cpf) " +
                                "VALUES (?,?)",
                        Statement.RETURN_GENERATED_KEYS);

                st.setInt(1, obj.getId());
                st.setString(2, obj.getCpf());
                st.executeUpdate();

            } else {
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
        PreparedStatement st = null;
        try {
            if (obj != null) { // Verifica se o objeto não é nulo
                StringBuilder query = new StringBuilder("UPDATE pessoas " +
                        "SET ");

                boolean hasUpdate = false; // Variável para verificar se houve alguma atualização

                // Verifica se cada campo não está vazio antes de adicioná-lo à consulta SQL
                if (obj.getNome() != null && !obj.getNome().isEmpty()) {
                    query.append("nome=?, ");
                    hasUpdate = true;
                }
                if (obj.getEndereco() != null && !obj.getEndereco().isEmpty()) {
                    query.append("endereco=?, ");
                    hasUpdate = true;
                }
                if (obj.getCidade() != null && !obj.getCidade().isEmpty()) {
                    query.append("cidade=?, ");
                    hasUpdate = true;
                }
                if (obj.getEstado() != null && !obj.getEstado().isEmpty()) {
                    query.append("estado=?, ");
                    hasUpdate = true;
                }
                if (obj.getTelefone() != null && !obj.getTelefone().isEmpty()) {
                    query.append("telefone=?, ");
                    hasUpdate = true;
                }
                if (obj.getEmail() != null && !obj.getEmail().isEmpty()) {
                    query.append("email=?, ");
                    hasUpdate = true;
                }

                // Remove o espaço em branco e a última vírgula, se houver
                if (hasUpdate) {
                    query.setLength(query.length() - 2);
                }

                // Adiciona a cláusula WHERE
                query.append(" WHERE id_pessoa =?");

                st = conn.prepareStatement(query.toString());

                int parameterIndex = 1; // Conta os ? do SQL

                // Define os parâmetros que foram incluídos na consulta
                if (obj.getNome() != null && !obj.getNome().isEmpty()) {
                    st.setString(parameterIndex++, obj.getNome());
                }
                if (obj.getEndereco() != null && !obj.getEndereco().isEmpty()) {
                    st.setString(parameterIndex++, obj.getEndereco());
                }
                if (obj.getCidade() != null && !obj.getCidade().isEmpty()) {
                    st.setString(parameterIndex++, obj.getCidade());
                }
                if (obj.getEstado() != null && !obj.getEstado().isEmpty()) {
                    st.setString(parameterIndex++, obj.getEstado());
                }
                if (obj.getTelefone() != null && !obj.getTelefone().isEmpty()) {
                    st.setString(parameterIndex++, obj.getTelefone());
                }
                if (obj.getEmail() != null && !obj.getEmail().isEmpty()) {
                    st.setString(parameterIndex++, obj.getEmail());
                }

                // Último parâmetro
                st.setInt(parameterIndex, obj.getId());

                // Se houver alguma atualização a ser feita, execute-a
                if (hasUpdate) {
                    st.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            ConectorBD.closeStatement(st);
        }
    }




    @Override
    public void excluir(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM pessoas WHERE id_pessoa IN " +
                    "(SELECT id_pessoa FROM pessoa_fisica WHERE id_pessoa = ?)");
            st.setInt(1,id);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            ConectorBD.closeStatement(st);
        }
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
                rs.getString("estado"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("cpf")
        );
        return obj;
    }
}
