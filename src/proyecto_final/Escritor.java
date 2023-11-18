package proyecto_final;

public class Escritor extends Jugador {

	Escritor(int id, String nombre) {
		super(id, nombre, new Lapiz(0, 20), true, 80, 90);
	}
}