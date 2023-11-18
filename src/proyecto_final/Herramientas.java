package proyecto_final;

public class Herramientas {
    private String nombre;
    private int id;
    private int danioBase;

    public Herramientas(String nombre, int id, int danioBase) {
        this.nombre = nombre;
        this.id = id;
        this.danioBase = danioBase;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public int getDanioBase() {
        return danioBase;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDanioBase(int danioBase) {
        this.danioBase = danioBase;
    }
}
