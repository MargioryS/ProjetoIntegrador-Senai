import java.util.ArrayList;
import java.util.List;

public class CarroStorage {
    private static List<Carro> Carros = new ArrayList<>();
    private static int incremento = 1;

    public static void inserir(Carro Carro) {
        Carro.setId(incremento++);
        Carros.add(Carro);
    }

    public static void atualizar(Carro Carro) {
        int idx = Carros.indexOf(Carro);
        if (idx >= 0) {
            Carros.set(idx, Carro);
        }
    }

    public static void remover(Carro Carro) {
        Carros.remove(Carro);
    }

    public static List<Carro> listar() {
        return Carros;
    }
} // fim da classe CarroStorage
