import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    public static final String TITULO = "EcoLogic";
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private MenuInicial menuInicial;

    private CarroListPanel carroListPanel;
    private CarroFormPanel carroFormPanel;

    private FuncionarioListPanel funcionarioListPanel;
    private FuncionarioFormPanel funcionarioFormPanel;

    private ChamadoFormPanel chamadoFormPanel;
    private ChamadoListPanel chamadoListPanel;

    public AppFrame(){
        super(TITULO);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        add(cardPanel);
        criarCards();
    }

    public void mostrar() {
        pack();
        setLocationRelativeTo(null);
        setSize(1280, 720);
        setResizable(false);
        setVisible(true);
    }

    public void mostrarInicialPanel() {
        cardLayout.show(cardPanel, MenuInicial.class.getName());
    }

    public void mostrarCarroListPanel() {
        carroListPanel.recarregar();
        cardLayout.show(cardPanel, CarroListPanel.class.getName());
    }

    public void mostrarCarroFormPanel(Carro carro) {
        carroFormPanel.setCarro(carro);
        cardLayout.show(cardPanel, CarroFormPanel.class.getName());
    }

    public void mostrarFuncionarioListPanel() {
        funcionarioListPanel.recarregar();
        cardLayout.show(cardPanel, FuncionarioListPanel.class.getName());
    }

    public void mostrarFuncionarioFormPanel(Funcionario funcionario) {
        funcionarioFormPanel.setFuncionario(funcionario);
        cardLayout.show(cardPanel, FuncionarioFormPanel.class.getName());
    }

    public void mostrarChamadoListPanel(){
        chamadoListPanel.recarregar();
        cardLayout.show(cardPanel, ChamadoListPanel.class.getName());
    };

    public void mostrarChamadoFormPanel(Chamado chamado){
        chamadoFormPanel.setChamado(chamado);
        cardLayout.show(cardPanel, ChamadoFormPanel.class.getName());
    };

    private void criarCards() {
        menuInicial = new MenuInicial(this);
        cardPanel.add(menuInicial, MenuInicial.class.getName());

        carroListPanel = new CarroListPanel(this);
        cardPanel.add(carroListPanel, CarroListPanel.class.getName());

        carroFormPanel = new CarroFormPanel(this);
        cardPanel.add(carroFormPanel, CarroFormPanel.class.getName());

        funcionarioListPanel = new FuncionarioListPanel(this);
        cardPanel.add(funcionarioListPanel, FuncionarioListPanel.class.getName());

        funcionarioFormPanel = new FuncionarioFormPanel(this);
        cardPanel.add(funcionarioFormPanel, FuncionarioFormPanel.class.getName());

        chamadoListPanel = new ChamadoListPanel(this);
        cardPanel.add(chamadoListPanel, ChamadoListPanel.class.getName());

        chamadoFormPanel = new ChamadoFormPanel(this);
        cardPanel.add(chamadoFormPanel, ChamadoFormPanel.class.getName());
    }
}
