import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class CarroTableModel extends AbstractTableModel {
    private List<Carro> carros = new ArrayList<>();
    private String[] colunas = new String[]{"Id",
            "Modelo",
            "Ano",
            "Autonomia"
    };

    public CarroTableModel(List<Carro> carros) {
        this.carros = carros;
    }

    @Override
    public int getRowCount() {
        return carros.size();
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
    public Object getValueAt(int rowIdx, int colIdx) {
        String value = null;

        Carro carro = carros.get(rowIdx);

        switch (colIdx) {
            case 0:
                value = Integer.toString(carro.getId());
                break;
            case 1:
                value = carro.getModelo();
                break;
            case 2:
                value = carro.getAno();
                break;
            case 3:
                value = carro.getAutonomia();
                break;
            default:
                System.err.printf("[ERRO] Índice de coluna inválido: %d%n",
                        colIdx);
        }

        return value;
    }

    public void carregar(List<Carro> carros) {
        this.carros = carros;
        fireTableDataChanged();
    }

    public Carro getCarro(int rowIdx) {
        Carro carro = null;

        carro = carros.get(rowIdx);

        return carro;
    }
} // fim da classe TarefaTableModel