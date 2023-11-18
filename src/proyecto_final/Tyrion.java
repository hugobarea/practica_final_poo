package proyecto_final;

public class Tyrion extends Jugador{

	Tyrion(int id, String nombre) {
		super(id, nombre, new Hacha(1, 70, 1.5), true, 120, 70);
	}
}