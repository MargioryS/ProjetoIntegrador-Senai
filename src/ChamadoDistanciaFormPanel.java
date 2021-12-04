import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ChamadoDistanciaFormPanel extends JPanel {
    private AppFrame frame;

    private Chamado chamado;

    private GridBagLayout layout;
    private GridBagConstraints constraints;

    private JTextField txtId;
    private JTextField txtLocal;
    private JTextField txtDistancia;
    private JTextField txtCarro;
    private JTextField txtFunc;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public ChamadoDistanciaFormPanel(AppFrame frame) {
        this.frame = frame;

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        setLayout(layout);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                    txtId.setText(Integer.toString(chamado.getId()));
                    txtLocal.setText(chamado.getLocal());
                    txtDistancia.setText(chamado.getDistancia());
                    txtCarro.setText(Integer.toString(chamado.getIdCarro()));
                    txtFunc.setText(Integer.toString(chamado.getIdFunc()));
            }
        });
        criarForm();
    }
    public void setChamado(Chamado chamado) {
        this.chamado = chamado;
    }

    private void criarForm() {
        JLabel label;

        label = new JLabel("Id ");
        adicionarComponente(label, 0, 0);

        txtId = new JTextField(5);
        txtId.setEditable(false);
        adicionarComponente(txtId, 0, 1);

        label = new JLabel("Local ");
        adicionarComponente(label, 1, 0);
        txtLocal = new JTextField();
        txtLocal.setEditable(false);
        adicionarComponente(txtLocal, 1, 1);

        label = new JLabel("ID do Carro ");
        adicionarComponente(label, 3, 0);

        txtCarro = new JTextField(30);
        txtCarro.setEditable(false);
        adicionarComponente(txtCarro, 3, 1);

        label = new JLabel("ID do Func ");
        adicionarComponente(label, 4, 0);

        txtFunc = new JTextField(30);
        adicionarComponente(txtFunc, 4, 1);
        txtFunc.setEditable(false);
        criarBotoes();

        label = new JLabel("Distancia ");

        adicionarComponente(label, 5, 0);

        txtDistancia = new JTextField(5);

        adicionarComponente(txtDistancia, 5, 1);
    }

    private void criarBotoes() {
        JPanel btnPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        criarBtnSalvar(btnPanel);
        criarBtnCancelar(btnPanel);

        adicionarComponente(btnPanel, 8, 1, 2, 1);
    }

    private void criarBtnSalvar(JPanel btnPanel) {
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chamado.setLocal(txtLocal.getText());
                chamado.setDistancia(txtDistancia.getText());
                chamado.setIdFunc(Integer.parseInt(txtFunc.getText()));
                chamado.setIdCarro(Integer.parseInt(txtCarro.getText()));
                ChamadoStorage.atualizar(ChamadoDistanciaFormPanel.this.chamado);
                JOptionPane.showMessageDialog(ChamadoDistanciaFormPanel.this,
                        "Chamado atualizado com sucesso",
                        "Todo App",
                        JOptionPane.INFORMATION_MESSAGE);
                frame.mostrarChamadoListPanel();
            }
        });
        btnPanel.add(btnSalvar);
    }

    private void criarBtnCancelar(JPanel btnPanel) {
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarChamadoListPanel();
            }
        });
        btnPanel.add(btnCancelar);
    }

    private void adicionarComponente(JComponent componente,
                                     int linha, int coluna) {
        adicionarComponente(componente, linha, coluna, 1, 1);
    }

    private void adicionarComponente(JComponent componente,
                                     int linha, int coluna,
                                     int largura, int altura) {
        constraints.gridx = coluna;
        constraints.gridy = linha;
        constraints.gridwidth = largura;
        constraints.gridheight = altura;

        constraints.fill = GridBagConstraints.HORIZONTAL;

        layout.setConstraints(componente, constraints);
        add(componente);
    }
}

