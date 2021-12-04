public class Chamado {
    private int id;
    private String local;
    private int idCarro;
    private int idFunc;
    private double distancia;

    public int getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(int idCarro) {
        this.idCarro = idCarro;
    }

    public int getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(int idFunc) {
        this.idFunc = idFunc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }


    public String getDistancia() {
        return Double.toString(distancia);
    }

    public void setDistancia(String distancia) {
        this.distancia = Double.parseDouble(distancia);
    }
}
