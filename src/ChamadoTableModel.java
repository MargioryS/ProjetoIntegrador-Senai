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
    public Object getValueAt(int rowIdx, int colIdx) {
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
                value = chamado.getDistancia();
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

}