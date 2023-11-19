package proyecto_final;

public class Legolas extends Jugador{

	Legolas(String nombre, boolean esHumano) {
        super(0, nombre, new Arco(3, 90, 1.2), esHumano, 80, 90);
    }
	
	public double calcularDanio() {
        
        double danioBase = 90;  

        int vidaRestante = this.getVida();

        double danioRealizado = ((Petardo) herramienta).calcularDano();

        danioRealizado *= (vidaRestante / 100); 

        return danioBase + danioRealizado;
	}
	
}