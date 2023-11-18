package proyecto_final;

public class Lapiz extends Herramienta{

	private double multiplicadorDano;
	
	public Lapiz(int id, int danoBase, double multiplicadorDano) {
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