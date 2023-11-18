package proyecto_final;

public class Partida {
    private Jugador[] jugadores;
    private int max_jugadores;
    private int jugadores_actuales;

    Partida(int max_jugadores) {
        this.max_jugadores = max_jugadores;
        this.jugadores_actuales = 0;
        this.jugadores = new Jugador[max_jugadores];
    }

    public void addJugador(Jugador jugador) {

        if(jugadores_actuales == max_jugadores) {
            System.out.println("Máximo de jugadores alcanzado");
            return;
        }

        jugador.id = this.jugadores_actuales;
        this.jugadores[this.jugadores_actuales] = jugador;

        this.jugadores_actuales++;
    }

    public void simularAtaque() {

        // Elegir dos jugadores aleatorios

        int j1 = (int) Math.floor(Math.random() * jugadores_actuales);
        int j2 = (int) Math.floor(Math.random() * jugadores_actuales);

        while(j1 == j2) {
            j2 = (int) Math.floor(Math.random() * (jugadores_actuales + 1));
        }

        if(!this.jugadores[j1].getEsHumano()) {
            this.jugadores[j1].atacar(this.jugadores[j2]);
        }

        if(!this.jugadores[j2].getEstaVivo()) {
            this.jugadores[j2] = null;
        }

    }

    public static void main(String args[]) {
        Partida partida = new Partida(5);

        partida.addJugador(new Bombero(1, "Manolito"));
        partida.addJugador(new Bombero(1, "Fran"));
        partida.addJugador(new Bombero(1, "Paco"));
        partida.addJugador(new Bombero(1, "El Gamer"));
        partida.addJugador(new Bombero(1, "Manuel Rodriguez"));
        partida.simularAtaque();
        partida.simularAtaque();
        partida.simularAtaque();
        partida.simularAtaque();
        partida.simularAtaque();
        partida.simularAtaque();
    }
}
