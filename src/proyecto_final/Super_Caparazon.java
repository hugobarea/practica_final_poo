package proyecto_final;

public class Super_Caparazon extends Caparazon{

    public Super_Caparazon(int id, double curacion){
        super(id, curacion);
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