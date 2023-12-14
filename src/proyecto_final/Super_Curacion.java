package proyecto_final;

public class Super_Curacion extends Pociones{

    private double multiplicadorCuracion;

    public Super_Curacion(int id, double curacion, double multiplicadorCuracion){
        super(id, curacion);
        this.multiplicadorCuracion = multiplicadorCuracion;
    }

    public double getMultiplicadorCuracion() {
        return multiplicadorCuracion;
    }

    public void setMultiplicadorCuracion(double multiplicadorCuracion) {
        this.multiplicadorCuracion = multiplicadorCuracion;
    }

    public double calcularCuracion() {

        int bonuesCuracion = 100;
        double curacionRealizada;
        int n_aleatorio = (int) Math.floor(Math.random() * 10);

        if (n_aleatorio < 6) {
            curacionRealizada = this.curacion * bonuesCuracion;
        } else {
            curacionRealizada = 0;
        }

        return curacionRealizada;
    }
}