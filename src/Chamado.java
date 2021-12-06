import java.sql.Date;
import java.time.LocalDate;

public class Chamado {
    private int id;
    private String local;
    private int idCarro;
    private int idFunc;
    private double distancia;
    private Date data;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

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


    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = Double.parseDouble(distancia);
    }
}
