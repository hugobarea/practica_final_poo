package proyecto_final;

public class Lapiz extends Herramienta{
	
	public Lapiz(int id, int danoBase) {
		super(id,danoBase);
    }
    
    public double calcularDano() {
    	
        double danoRealizado;
       
        danoRealizado = this.danoBase;

        return danoRealizado;
    }
}