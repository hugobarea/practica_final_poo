package proyecto_final;

public class Chilla extends Jugador{

	Chilla(String nombre, boolean esHumano) {
		super(0, nombre, new Arco(3, 80, 1.2), esHumano, 100, 60);
	}
	
	public double calcularDanio() {
        
        double danioBase = 80;  

        int vidaRestante = this.getVida();

        double danioRealizado = ((Petardo) herramienta).calcularDano();

        danioRealizado *= (vidaRestante / 100); 

        return danioBase + danioRealizado;
	}
	
}