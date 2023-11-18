package proyecto_final;

public class Lapiz extends Herramienta{
	
	public Lapiz(int id, int danoBase) {
		super(id,danoBase);
    }
    
    public double calcularDano() {
         
    	return this.danoBase;
    }
}