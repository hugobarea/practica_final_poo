package proyecto_final;

public class Legolas extends Jugador{

	public Legolas(int id, String nombre) {
        super(id, nombre, new Arco(3, 90, 1.2), true, 80, 90);
    }
}