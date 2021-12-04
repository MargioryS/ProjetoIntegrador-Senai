import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarroStorage {


    public static boolean inserir(Carro carro) {
        final String query = "INSERT INTO carro (modelo, ano, autonomia, disponivel) VALUES (?, ?, ?, ?)";

        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, carro.getModelo());
            statement.setInt(2, carro.getAno());
            statement.setDouble(3, Double.parseDouble(carro.getAutonomia()));
            statement.setBoolean(4,true);
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                carro.setId(resultSet.getInt(1));
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

    public static boolean atualizar(Carro carro) {
        final String query = "UPDATE carro SET modelo = ?, ano = ?, autonomia = ? WHERE id_carro = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setString(1, carro.getModelo());
            statement.setInt(2, carro.getAno());
            statement.setDouble(3, Double.parseDouble(carro.getAutonomia()));
            statement.setInt(4, carro.getId());
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

    public static boolean remover(Carro carro) {
        final String query = "DELETE FROM carro WHERE id_carro = ?";

        Connection conexao = null;
        PreparedStatement statement = null;

        try {
            conexao = ConexaoFactory.getConexao();

            statement = conexao.prepareStatement(query);
            statement.setInt(1, carro.getId());
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

    public static List<Carro> listar() {
        List<Carro> carros = new ArrayList<>();

        final String query = "SELECT * FROM carro ORDER BY id_carro";

        Connection conexao = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            conexao = ConexaoFactory.getConexao();
            statement = conexao.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Carro carro = new Carro();
                carro.setId(resultSet.getInt("id_carro"));
                carro.setModelo(resultSet.getString("modelo"));
                carro.setAno(resultSet.getString("ano"));
                carro.setAutonomia(resultSet.getString("autonomia"));
                carros.add(carro);
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

        return carros;
    }
} // fim da classe carroStorage
