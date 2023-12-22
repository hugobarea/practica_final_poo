package proyecto_final;

public class Tentaculos extends Herramienta{
	
	public Tentaculos(int id, int danoBase) {
		super(id,danoBase);
    }
    
    public double calcularDano() {
         
    	return this.danoBase;
    }
}