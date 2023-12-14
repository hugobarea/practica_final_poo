package proyecto_final;

public class Mini_Curacion extends Pociones{

    public Mini_Curacion(int id, double curacion){
        super(id, curacion);
    }

    public double calcularCuracion(){

        return this.curacion;
    }
}