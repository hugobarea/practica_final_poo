package proyecto_final;

public abstract class Jugador {
    protected int id;
    protected String nombre;
    protected int vida;
    protected int escudo;
    protected int dano;
    protected Herramienta herramienta;
    protected boolean esHumano;
    protected boolean estaVivo;

    Jugador(int id, String nombre, Herramienta herramienta, boolean esHumano) {
        this.id = id;
        this.nombre = nombre;

        this.vida = 100;
        this.escudo = 0;
        this.dano = 20;
        this.herramienta = herramienta;
        this.esHumano = esHumano;
        this.estaVivo = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getDano() {
        return dano;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVida() {
        return vida;
    }

    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    public boolean getEstaVivo() {
        return estaVivo;
    }
    
    public void setEsHumano(boolean esHumano) {
    	this.esHumano = esHumano;
    }
    
    public boolean getEsHumano() {
    	return esHumano;
    }

    public void atacar(Jugador rival) {

        int n_aleatorio = (int) Math.floor(Math.random() * 10);
        double danoRealizado;

        if(n_aleatorio == 1) {
            danoRealizado = this.dano * 1.5;
        } else {
            danoRealizado = this.dano;
        }

        rival.vida -= danoRealizado;


        if(rival.getVida() <= 0) {
            rival.setEstaVivo(false);
        }


        System.out.print(this.nombre + " ha atacado a " + rival.getNombre() + ": -" + danoRealizado + " de vida. ");

        if(danoRealizado > this.dano) {
            System.out.println("GOLPE CRÍTICO!");
        } else {
            System.out.println();
        }

        if(!rival.getEstaVivo()) {
            System.out.println(rival.getNombre() + " ha muerto.");
        }

    }

    public String toString() {
        return "El jugador " + this.nombre + " tiene " + this.vida + " de vida.";
    }

}
