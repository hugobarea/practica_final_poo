package proyecto_final;

public class Bombero extends Jugador {

	Bombero(String nombre, boolean esHumano) {
		super(0, nombre, new Petardo(4, 100, 5), esHumano, 100, 120);
	}

	public double calcularDanio() {

		double danioBase = 100;

		int vidaRestante = this.getVida();

		double danioRealizado = ((Petardo) herramienta).calcularDano();

		danioRealizado *= (vidaRestante / 100);

		return danioBase + danioRealizado;
	}
}