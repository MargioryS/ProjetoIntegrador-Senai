public class Carro {
    private int id;
    private String modelo;
    private String ano;
    private double autonomia;
    private boolean disponivel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return Integer.parseInt(ano);
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getAutonomia() {
        return Double.toString(autonomia);
    }

    public void setAutonomia(String autonomia) {
        this.autonomia = Double.parseDouble(autonomia);
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
