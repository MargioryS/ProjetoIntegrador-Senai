import java.util.ArrayList;
import java.util.List;

public class FuncionarioStorage {
    private static List<Funcionario> funcionarios = new ArrayList<>();
    private static int incremento = 1;

    public static void inserir(Funcionario funcionario) {
        funcionario.setId(incremento++);
        funcionarios.add(funcionario);
    }

    public static void atualizar(Funcionario funcionario) {
        int idx = funcionarios.indexOf(funcionario);
        if (idx >= 0) {
            funcionarios.set(idx, funcionario);
        }
    }

    public static void remover(Funcionario funcionario) {
        funcionarios.remove(funcionario);
    }

    public static List<Funcionario> listar() {
        return funcionarios;
    }
} // fim da classe FuncionarioStorage
