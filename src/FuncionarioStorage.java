import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioStorage {


    public static boolean inserir(Funcionario funcionario) {
        final String query = "INSERT INTO funcionario (nome, contato, cnh, disponivel) VALUES (?, ?, ?, ?)";

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getContato());
            statement.setString(3, funcionario.getCnh());
            statement.setBoolean(4,true);
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                funcionario.setId(resultSet.getInt(1));
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public static boolean atualizar(Funcionario funcionario) {
        final String query = "UPDATE funcionario SET nome = ?, contato = ?, cnh = ?, disponivel = ?, WHERE id_funcionario = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getContato());
            statement.setString(3, funcionario.getCnh());
            statement.setInt(4, funcionario.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean remover(Funcionario funcionario) {
        final String query = "DELETE FROM funcionario WHERE id_funcionario = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setInt(1, funcionario.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }

        return true;
    }

    public static List<Funcionario> listar() {
        List<Funcionario> funcionarios = new ArrayList<>();

        final String query = "SELECT * FROM funcionario ORDER BY id_funcionario";

        Connection conexao = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();
            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt("id_funcionario"));
                funcionario.setNome(resultSet.getString("nome"));
                funcionario.setContato(resultSet.getString("contato"));
                funcionario.setCnh(resultSet.getString("cnh"));

                funcionarios.add(funcionario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return funcionarios;
    }
} // fim da classe FuncionarioStorage