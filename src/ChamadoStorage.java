import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChamadoStorage {


    public static boolean inserir(Chamado chamado) {
        final String query = "INSERT INTO chamado (lugar, id_carro, id_funcionario, distancia) VALUES (?, ?, ?, 0)";

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, chamado.getLocal());
            statement.setInt(2, chamado.getIdCarro());
            statement.setInt(3,chamado.getIdFunc());
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                chamado.setId(resultSet.getInt(1));
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

    public static boolean atualizar(Chamado chamado) {
        final String query = "UPDATE chamado SET lugar = ?, id_carro = ?, id_funcionario = ?, distancia = ? WHERE id_chamado = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setString(1, chamado.getLocal());
            statement.setInt(2, chamado.getIdCarro());
            statement.setInt(3, chamado.getIdFunc());
            statement.setDouble(4, chamado.getDistancia());
            statement.setInt(5,chamado.getId());
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

    public static boolean remover(Chamado chamado) {
        final String query = "DELETE FROM chamado WHERE id_chamado = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setInt(1, chamado.getId());
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

    public static List<Chamado> listar() {
        List<Chamado> chamados = new ArrayList<>();

        final String query = "SELECT * FROM chamado ORDER BY id_chamado";

        Connection conexao = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();
            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Chamado chamado = new Chamado();
                chamado.setId(resultSet.getInt("id_chamado"));
                chamado.setLocal(resultSet.getString("lugar"));
                chamado.setIdCarro(resultSet.getInt("Id_carro"));
                chamado.setIdFunc(resultSet.getInt("Id_Funcionario"));
                chamado.setDistancia(resultSet.getString("distancia"));
                chamados.add(chamado);
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

        return chamados;
    }
} // fim da classe chamadoStorage
