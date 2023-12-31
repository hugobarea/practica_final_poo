package proyecto_final;

public class Caña_Pescar extends Herramienta{

	private double multiplicadorDano;
	
	public Caña_Pescar(int id, int danoBase, double multiplicadorDano) {
		super(id,danoBase);
		this.multiplicadorDano = multiplicadorDano;
    }
	
	public double getMultiplicadorDano() {
        return multiplicadorDano;
    }

    public void setMultiplicadorDano(double multiplicadorDano) {
        this.multiplicadorDano = multiplicadorDano;
    }

    public double calcularDano() {
    	
        int n_aleatorio = (int) Math.floor(Math.random() * 10);
        double danoRealizado;
        
        if (n_aleatorio < 6) {
            danoRealizado = this.danoBase * this.multiplicadorDano;
        } else {
            danoRealizado = 0;
        }

        return danoRealizado;
    }
}
