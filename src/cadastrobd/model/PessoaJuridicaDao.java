package cadastrobd.model;

import cadastro.model.util.ConectorBD;
import cadastro.model.util.DbException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDao implements IPessoaJuridicaDao {
    private Connection conn;

    public PessoaJuridicaDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void incluir(PessoaJuridica obj) {
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

                st = conn.prepareStatement("INSERT INTO pessoa_juridica " +
                                "(id_pessoa, cnpj) " +
                                "VALUES (?,?)",
                        Statement.RETURN_GENERATED_KEYS);

                st.setInt(1, obj.getId());
                st.setString(2, obj.getCnpj());
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
    public void alterar(PessoaJuridica obj) {
        PreparedStatement st = null;
        try {
            if (obj != null) { // Verifica se o objeto não é nulo
                StringBuilder query = new StringBuilder("UPDATE pessoas " +
                        "SET ");

                boolean hasUpdate = false; // Variável para verificar se houve alguma atualização

                // Consulta se existe os campos
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

                // remove espaco em branco no final e a virgula
                if (hasUpdate) {
                    query.setLength(query.length() - 2);
                }

                // Coloca o Where no final
                query.append(" WHERE id_pessoa =?");

                st = conn.prepareStatement(query.toString());

                int parameterIndex = 1; // Conta os ? do Sql

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

                // Ultimo parametro
                st.setInt(parameterIndex, obj.getId());

                // se retorna true executa a atualizaçao
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
                    "(SELECT id_pessoa FROM pessoa_juridica WHERE id_pessoa = ?)"
            );
            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            ConectorBD.closeStatement(st);
        }
    }

    @Override
    public PessoaJuridica getPessoa(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT pessoas.*, pessoa_juridica.cnpj as cnpj " +
                    "FROM pessoas INNER JOIN pessoa_juridica on pessoas.id_pessoa =  pessoa_juridica.id_pessoa " +
                    "WHERE pessoas.id_pessoa = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                PessoaJuridica obj = instantiatePessoa(rs);
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
    public List<PessoaJuridica> getPessoas() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT pessoas.*, pessoa_juridica.cnpj as cnpj " +
                    "FROM pessoas INNER JOIN pessoa_juridica on pessoas.id_pessoa =  pessoa_juridica.id_pessoa " +
                    "ORDER BY nome");

            rs = st.executeQuery();

            List<PessoaJuridica> list = new ArrayList<>();

            while (rs.next()) {
                PessoaJuridica obj = instantiatePessoa(rs);
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


    private PessoaJuridica instantiatePessoa(ResultSet rs) throws SQLException {
        PessoaJuridica obj = new PessoaJuridica(
                rs.getInt("id_pessoa"),
                rs.getString("nome"),
                rs.getString("endereco"),
                rs.getString("cidade"),
                rs.getString("estado"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("cnpj")
        );
        return obj;
    }
}
