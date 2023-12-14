package proyecto_final;

public abstract class Pociones {
    protected int id;
    protected double curacion;

    public Pociones(int id, double curacion){
        this.id = id;
        this.curacion = curacion;
    }

    public int getId() {
        return id;
    }
    public double getCuracion() {
        return curacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCuracion(double curacion) {
        this.curacion = curacion;
    }
}