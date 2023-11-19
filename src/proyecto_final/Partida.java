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

        if(jugadores_actuales == 1) {
            return;
        }

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

            for(int i = j2 + 1; i < this.max_jugadores; i++) {
                this.jugadores[i - 1] = this.jugadores[i];
            }

            this.jugadores[this.max_jugadores - 1] = null;
            this.jugadores_actuales--;

            if(this.jugadores_actuales == 1) {
                System.out.println("--> El ganador es " + this.jugadores[0].nombre);
            } else {
                System.out.println("--> Quedan " + this.jugadores_actuales + " jugadores");
            }
        }

    }

    public static void main(String args[]) {
        Partida partida = new Partida(5);

        partida.addJugador(new Bombero( "Manolito", false));
        partida.addJugador(new Bombero("Fran", false));
        partida.addJugador(new Bombero("Paco", false));
        partida.addJugador(new Bombero( "El Gamer", false));
        partida.addJugador(new Bombero( "Manuel Rodriguez", false));
        partida.simularAtaque();
        partida.simularAtaque();
        partida.simularAtaque();
        partida.simularAtaque();
        partida.simularAtaque();
        partida.simularAtaque();
    }
}
