package proyecto_final;

import java.util.Scanner;

public abstract class Jugador {
    protected int id;
    protected String nombre;
    protected int vida;
    protected int escudo;
    protected int dano;

    protected Herramienta herramienta;
    protected boolean esHumano;
    protected boolean estaVivo;
    protected Caparazon caparazon;
    protected int opcion;

    Jugador(int id, String nombre, Herramienta herramienta, boolean esHumano, int vida, int dano) {
        this.id = id;
        this.nombre = nombre;

        this.vida = vida;
        this.escudo = 0;
        this.dano = dano;
        this.herramienta = herramienta;
        this.esHumano = esHumano;
        this.estaVivo = true;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
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

    public double[] atacar(Jugador rival) {

        int n_aleatorio = (int) Math.floor(Math.random() * 10);
        double danoRealizado;

        double retorno[] = {0, 0, 0, 0, 0}; // { id j1, id j2, daño realizado, critico o no, ha muerto o no }

        if(n_aleatorio == 1) {
            danoRealizado = this.dano * 1.5;
        } else {
            danoRealizado = this.dano;
        }

        rival.vida -= danoRealizado;


        if(rival.getVida() <= 0) {
            rival.setEstaVivo(false);
        }

        if(danoRealizado > this.dano) {
            retorno[3] = 1;
        }

        if(!rival.getEstaVivo()) {
            retorno[4] = 1;
        }

        retorno[0] = this.id;
        retorno[1] = rival.id;
        retorno[2] = danoRealizado;

        return retorno;
    }

    public void curarse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecciona una opción para curarte:");
        System.out.println("0 - Curarse con Mini_Caparazon");
        System.out.println("1 - Curarse con Super_Caparazon");

        opcion = scanner.nextInt();

        if (opcion == 0){
            double curacion = new Mini_Caparazon(1, 25).calcularCuracion();
        } else if(opcion == 1){
            double curacion = new Super_Caparazon(2, 100).calcularCuracion();
        }
    }

    public String toString() {
        return "El jugador " + this.nombre + " tiene " + this.vida + " de vida.";
    }

}