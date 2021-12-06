import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuInicial extends JPanel{

    private BorderLayout layout;
    private JButton funcBtn;
    private JButton CarroBtn;
    private AppFrame frame;
    private JButton chamadoBtn;

    public MenuInicial(AppFrame appFrame) {
        this.frame = appFrame;
        layout = new BorderLayout();
        setLayout(layout);
        criarCabecalho();
        criarMain();
        criarRodape();
    }

    public void criarCabecalho() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1280,150));
        panel.setBackground(new Color(73, 153, 0));
        JLabel titulo = new JLabel ("EcoLogic");
        titulo.setForeground(Color.white);;
        titulo.setFont(new Font("Roboto", Font.PLAIN, 110));
        panel.add(titulo);
        add(panel, BorderLayout.NORTH);
    }

    public void criarMain() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1280, 540));
        panel.setBackground(Color.white);
        criarBotoes();
        panel.add(funcBtn);
        panel.add(CarroBtn);
        panel.add(chamadoBtn);
        add(panel, BorderLayout.CENTER);
    }

    public void criarRodape() {
        JPanel panel = new JPanel();
        JLabel titulo = new JLabel("SENAI - CTAI");
        titulo.setForeground(Color.white);
        panel.add(titulo);
        panel.setBackground(Color.black);
        panel.setPreferredSize(new Dimension(1280, 30));
        add(panel, BorderLayout.SOUTH);
    }

    private void criarBotoes() {
        CarroBtn = new JButton("Carros");
        CarroBtn.setPreferredSize(new Dimension(500, 40));
        funcBtn = new JButton("Funcionario");
        funcBtn.setPreferredSize(new Dimension(500, 40));
        chamadoBtn = new JButton("Chamado");
        chamadoBtn.setPreferredSize(new Dimension(1000, 40));

        CarroBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarCarroListPanel();
            }
        });
        funcBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarFuncionarioListPanel();
            }
        });
        chamadoBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.mostrarChamadoListPanel();
            }
        });

    }
} //fim da classe InicialPanel

