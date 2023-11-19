package proyecto_final;

public class Escritor extends Jugador {

	Escritor(String nombre, boolean esHumano) {
		super(0, nombre, new Lapiz(0, 20), esHumano, 80, 90);
	}
	
	public double calcularDanio() {
        
        double danioBase = 20;  

        int vidaRestante = this.getVida();

        double danioRealizado = ((Petardo) herramienta).calcularDano();

        danioRealizado *= (vidaRestante / 100); 

        return danioBase + danioRealizado;
	}
	
}