package proyecto_final;

public class Arco extends Herramienta{

	private double multiplicadorDano;
	
	public Arco(int id, int danoBase, double multiplicadorDano) {
		super(id,danoBase);
		this.multiplicadorDano = multiplicadorDano;
    }
	
	public double getMultiplicadorDano() {
        return multiplicadorDano;
    }

    public void setMultiplicadorDano(double multiplicadorDano) {
        this.multiplicadorDano = multiplicadorDano;
    }

}
