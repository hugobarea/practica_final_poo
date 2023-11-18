package proyecto_final;

public class Chilla extends Jugador{

	Chilla(int id, String nombre) {
		super(id, nombre, new Arco(3, 80, 1.2), true, 100, 60);
	}
}