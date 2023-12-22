package proyecto_final;

public class Almeja extends Jugador{

	Almeja(String nombre, boolean esHumano) {
        super(0, nombre, new Caña_Pescar(3, 90, 1.2), esHumano, 80, 90);
    }
	
	public double calcularDanio() {
        
        double danioBase = 90;  

        int vidaRestante = this.getVida();

        double danioRealizado = ((Veneno) herramienta).calcularDano();

        danioRealizado *= (vidaRestante / 100); 

        return danioBase + danioRealizado;
	}
	
}