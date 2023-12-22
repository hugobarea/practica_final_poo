package proyecto_final;

public class Gamba extends Jugador{

	Gamba(String nombre, boolean esHumano) {
		super(0, nombre, new Pinza(1, 70, 1.5), esHumano, 120, 70);
	}
	
	public double calcularDanio() {
        
        double danioBase = 70;  

        int vidaRestante = this.getVida();

        double danioRealizado = ((Veneno) herramienta).calcularDano();

        danioRealizado *= (vidaRestante / 100); 

        return danioBase + danioRealizado;
	}
	
}