package proyecto_final;

public abstract class Herramienta {
    protected int id;
    protected int danoBase;

    public Herramienta(int id, int danoBase) {
        this.id = id;
        this.danoBase = danoBase;
    }

    public int getId() {
        return id;
    }

    public int getDanoBase() {
        return danoBase;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDanoBase(int danioBase) {
        this.danoBase = danioBase;
    }
}
