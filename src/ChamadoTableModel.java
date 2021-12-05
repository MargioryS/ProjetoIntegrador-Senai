import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ChamadoTableModel extends AbstractTableModel {
    private List<Chamado> chamados = new ArrayList<>();
    private String[] colunas = new String[]{"Id",
            "Local",
            "Distancia",
            "ID Func",
            "ID Carro"};

    public ChamadoTableModel(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    @Override
    public int getRowCount() {
        return chamados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int colIdx) {
        String colName = null;

        colName = colunas[colIdx];

        return colName;
    }

    @Override
    public String getValueAt(int rowIdx, int colIdx) {
        String value = null;

        Chamado chamado = chamados.get(rowIdx);

        switch (colIdx) {
            case 0:
                value = Integer.toString(chamado.getId());
                break;
            case 1:
                value = chamado.getLocal();
                break;
            case 2:
                value = String.valueOf(chamado.getDistancia());
                break;
            case 3:
                value = String.valueOf(chamado.getIdFunc());
                break;
            case 4:
                value = String.valueOf(chamado.getIdCarro());
                break;
            case 5:
                value = String.valueOf(chamado.getDistancia());
                break;
            default:
                System.err.printf("[ERRO] Índice de coluna inválido: %d%n",
                        colIdx);
        }

        return value;
    }

    public void carregar(List<Chamado> chamados) {
        this.chamados = chamados;
        fireTableDataChanged();
    }

    public Chamado getChamado(int rowIdx) {
        Chamado chamado = null;

        chamado = chamados.get(rowIdx);

        return chamado;
    }

    public double autonomiaCarro(int i){
        double autonomia = 0;
        final String query = "SELECT autonomia FROM carro WHERE id_carro = ?";
        Connection conexao = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            conexao = ConexaoFactory.getConexao();
            statement = conexao.prepareStatement(query);
            statement.setInt(1, Integer.parseInt(getValueAt(i,4)));
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                autonomia = resultSet.getDouble("autonomia");
            }
        } catch (SQLException e){
            e.printStackTrace();
            return autonomia;
        }
        return autonomia;
    };

    public double co2Calc(int c, int i){
        double co2 = 0;
        Chamado chamado = chamados.get(i);
        double distancia = chamado.getDistancia();
        double autonomia = autonomiaCarro(i);
        double cg = (distancia*1) / autonomia;
        co2 += (cg * 0.73 * 0.75 * 3.7);
        return co2;
    };

    public double co2CalcGeral(){
        double co2 = 0;

        for(int i = 0;i < getRowCount(); i++) {
            Chamado chamado = chamados.get(i);
            double distancia = chamado.getDistancia();
            double autonomia = autonomiaCarro(i);
            double cg = (distancia*1) / autonomia;
            co2 += (cg * 0.73 * 0.75 * 3.7);
        }
        return co2;
    };
}