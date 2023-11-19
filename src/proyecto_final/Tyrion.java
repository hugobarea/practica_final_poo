package proyecto_final;

public class Tyrion extends Jugador{

	Tyrion(String nombre, boolean esHumano) {
		super(0, nombre, new Hacha(1, 70, 1.5), esHumano, 120, 70);
	}
	
	public double calcularDanio() {
        
        double danioBase = 70;  

        int vidaRestante = this.getVida();

        double danioRealizado = ((Petardo) herramienta).calcularDano();

        danioRealizado *= (vidaRestante / 100); 

        return danioBase + danioRealizado;
	}
	
}