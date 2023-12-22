package proyecto_final;

public class Ostra extends Jugador {

	Ostra(String nombre, boolean esHumano) {
		super(0, nombre, new Tentaculos(0, 20), esHumano, 80, 90);
	}
	
	public double calcularDanio() {
        
        double danioBase = 20;  

        int vidaRestante = this.getVida();

        double danioRealizado = ((Veneno) herramienta).calcularDano();

        danioRealizado *= (vidaRestante / 100); 

        return danioBase + danioRealizado;
	}
	
}