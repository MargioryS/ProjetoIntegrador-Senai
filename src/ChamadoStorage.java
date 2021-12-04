import java.util.ArrayList;
import java.util.List;

public class ChamadoStorage {
    private static List<Chamado> chamados = new ArrayList<>();
    private static int incremento = 1;

    public static void inserir(Chamado chamado) {
        chamado.setId(incremento++);
        chamado.setDistancia("0");
        chamados.add(chamado);
    }

    public static void atualizar(Chamado chamado) {
        int idx = chamados.indexOf(chamado);
        if (idx >= 0) {
            chamados.set(idx, chamado);
        }
    }

    public static void remover(Chamado chamado) {
        chamados.remove(chamado);
    }

    public static List<Chamado> listar() {
        return chamados;
    }
} // fim da classe chamadoStorage
