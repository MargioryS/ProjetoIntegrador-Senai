import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class FuncionarioFormPanel extends JPanel {
    private AppFrame frame;

    private Funcionario funcionario;

    private GridBagLayout layout;
    private GridBagConstraints constraints;

    private JTextField txtId;
    private JTextField txtNome;
    private JTextField txtContato;
    private JTextField txtCnh;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public FuncionarioFormPanel(AppFrame frame) {
        this.frame = frame;

        layout = new GridBagLayout();
        constraints = new GridBagConstraints();

        setLayout(layout);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (funcionario == null) {
                    txtId.setText("");
                    txtNome.setText("");
                    txtContato.setText("");
                    txtCnh.setText("");
                } else {
                    txtId.setText(Integer.toString(funcionario.getId()));
                    txtNome.setText(funcionario.getNome());
                    txtContato.setText(funcionario.getContato());
                    txtCnh.setText(funcionario.getCnh());
                }
            }
        });
        criarForm();
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    private void criarForm() {
        JLabel label;

        label = new JLabel("Id");
        adicionarComponente(label, 0, 0);

        txtId = new JTextField(5);
        txtId.setEditable(false);
        adicionarComponente(txtId, 0, 1);

        label = new JLabel("Nome");
        adicionarComponente(label, 1, 0);

        txtNome = new JTextField(30);
        adicionarComponente(txtNome, 1, 1);

        label = new JLabel("Contato");
        adicionarComponente(label, 2, 0);

        txtContato = new JTextField(30);
        adicionarComponente(txtContato, 2, 1);

        label = new JLabel("Cnh");
        adicionarComponente(label, 3, 0);

        txtCnh = new JTextField(30);
        adicionarComponente(txtCnh, 3, 1);

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
                if (FuncionarioFormPanel.this.funcionario == null) {
                    Funcionario novoFuncionario = new Funcionario();
                    novoFuncionario.setNome(txtNome.getText());
                    novoFuncionario.setContato(txtContato.getText());
                    novoFuncionario.setCnh(txtCnh.getText());

                    FuncionarioStorage.inserir(novoFuncionario);
                    JOptionPane.showMessageDialog(FuncionarioFormPanel.this,
                            "Funcionario incluído com sucesso",
                            "Todo App",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    funcionario.setNome(txtNome.getText());
                    funcionario.setContato(txtContato.getText());
                    funcionario.setCnh(txtCnh.getText());
                    FuncionarioStorage.atualizar(FuncionarioFormPanel.this.funcionario);
                    JOptionPane.showMessageDialog(FuncionarioFormPanel.this,
                            "Funcionario atualizada com sucesso",
                            "Todo App",
                            JOptionPane.INFORMATION_MESSAGE);
                }


                frame.mostrarFuncionarioListPanel();
            }
        });
        btnPanel.add(btnSalvar);
    }

    private void criarBtnCancelar(JPanel btnPanel) {
        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarFuncionarioListPanel();
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
