import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CarroFormPanel extends JPanel {
    private AppFrame frame;

    private Carro Carro;

    private GridBagLayout layout;
    private GridBagConstraints constraints;

    private JTextField txtId;
    private JTextField txtModelo;
    private JTextField txtAno;
    private JTextField txtAutonomia;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public CarroFormPanel(AppFrame frame) {
        this.frame = frame;

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        setLayout(layout);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (Carro == null) {
                    txtId.setText("");
                    txtModelo.setText("");
                    txtAno.setText("");
                    txtAutonomia.setText("");
                } else {
                    txtId.setText(Integer.toString(Carro.getId()));
                    txtModelo.setText(Carro.getModelo());
                    txtAno.setText(Integer.toString(Carro.getAno()));
                    txtAutonomia.setText(Carro.getAutonomia());
                }
            }
        });
        criarForm();
    }

    public void setCarro(Carro Carro) {
        this.Carro = Carro;
    }

    private void criarForm() {
        JLabel label;

        label = new JLabel("Id");
        adicionarComponente(label, 0, 0);

        txtId = new JTextField(5);
        txtId.setEditable(false);
        adicionarComponente(txtId, 0, 1);

        label = new JLabel("Modelo");
        adicionarComponente(label, 1, 0);

        txtModelo = new JTextField(30);
        adicionarComponente(txtModelo, 1, 1);

        label = new JLabel("Ano");
        adicionarComponente(label, 2, 0);

        txtAno = new JTextField(30);
        adicionarComponente(txtAno, 2, 1);

        label = new JLabel("Autonomia");
        adicionarComponente(label, 3, 0);

        txtAutonomia = new JTextField(30);
        adicionarComponente(txtAutonomia, 3, 1);

        criarBotoes();
    }

    private void criarBotoes() {
        JPanel btnPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) btnPanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);

        criarBtnSalvar(btnPanel);
        criarBtnCancelar(btnPanel);

        adicionarComponente(btnPanel, 7, 1, 2, 1);
    }

    private void criarBtnSalvar(JPanel btnPanel) {
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (CarroFormPanel.this.Carro == null) {
                    Carro novoCarro = new Carro();
                    novoCarro.setModelo(txtModelo.getText());
                    novoCarro.setAno(txtAno.getText());
                    novoCarro.setAutonomia(txtAutonomia.getText());

                    CarroStorage.inserir(novoCarro);
                    JOptionPane.showMessageDialog(CarroFormPanel.this,
                            "Carro incluída com sucesso",
                            "Todo App",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    Carro.setModelo(txtModelo.getText());
                    Carro.setAno(txtAno.getText());
                    Carro.setAutonomia(txtAutonomia.getText());
                    CarroStorage.atualizar(CarroFormPanel.this.Carro);
                    JOptionPane.showMessageDialog(CarroFormPanel.this,
                            "Carro atualizada com sucesso",
                            "Todo App",
                            JOptionPane.INFORMATION_MESSAGE);
                }


                frame.mostrarCarroListPanel();
            }
        });
        btnPanel.add(btnSalvar);
    }

    private void criarBtnCancelar(JPanel btnPanel) {
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarCarroListPanel();
            }
        });
        btnPanel.add(btnCancelar);
    }

    private void adicionarComponente(JComponent componente, int linha, int coluna) {
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
