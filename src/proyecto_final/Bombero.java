package proyecto_final;

public class Bombero extends Jugador {

	Bombero(int id, String nombre) {
		super(id, nombre, new Petardo(4, 100, 5), true, 100, 120);
	}
}