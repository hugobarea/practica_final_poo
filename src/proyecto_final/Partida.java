package proyecto_final;

import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Partida {
    private Jugador[] jugadores;
    private Jugador[] muertos;
    private int max_jugadores;
    private int jugadores_actuales;
    private String resultados;

    Partida(int max_jugadores) {
        this.max_jugadores = max_jugadores;
        this.jugadores_actuales = 0;
        this.jugadores = new Jugador[max_jugadores];
        this.muertos = new Jugador[max_jugadores];
        this.resultados = "";
    }

    public void addJugador(Jugador jugador) {

        if (jugadores_actuales == max_jugadores) {
            System.out.println("Máximo de jugadores alcanzado");
            return;
        }

        jugador.id = this.jugadores_actuales;
        this.jugadores[this.jugadores_actuales] = jugador;

        this.jugadores_actuales++;
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public int getVivos() {
        return jugadores_actuales;
    }

    public Jugador[] getMuertos() {
        return muertos;
    }

    public String getResultados() {
        return this.resultados;
    }

    public double[] simularAtaque() {

        if (jugadores_actuales == 1) {
            return null;
        }

        // Elegir dos jugadores aleatorios

        int j1 = (int) Math.floor(Math.random() * jugadores_actuales);
        int j2 = (int) Math.floor(Math.random() * jugadores_actuales);

        while (j1 == j2) {
            j2 = (int) Math.floor(Math.random() * (jugadores_actuales + 1));
        }

        double informacionAtaque[] = new double[5];

        if (!this.jugadores[j1].getEsHumano()) {
            informacionAtaque = this.jugadores[j1].atacar(this.jugadores[j2]);
            System.out.println(this.jugadores[j1].getNombre() + " ha atacado a " + this.jugadores[j2].getNombre() + ": " + -1 * informacionAtaque[2] + " de daño.");
            resultados = resultados + this.jugadores[j1].getNombre() + " ha atacado a " + this.jugadores[j2].getNombre() + ": " + -1 * informacionAtaque[2] + " de daño.\n";
        }

        // Si un jugador muere, meterle en el array de muertos y sacarle del array de jugadores actuales.

        if(informacionAtaque[4]==1) {

            System.out.println(this.jugadores[j2].nombre + " ha muerto.");
            resultados = resultados + this.jugadores[j2].nombre + " ha muerto.\n";
            this.muertos[max_jugadores - jugadores_actuales] = this.jugadores[j2];
            this.jugadores[j2] = null;

            for (int i = j2 + 1; i < this.max_jugadores; i++) {
                this.jugadores[i - 1] = this.jugadores[i];
            }

            this.jugadores[this.max_jugadores - 1] = null;
            this.jugadores_actuales--;

            if (this.jugadores_actuales == 1) {
                System.out.println("->; El ganador es " + this.jugadores[0].nombre);
            } else {
                System.out.println("-> Quedan " + this.jugadores_actuales + " jugadores");
                resultados = resultados + "--> Quedan " + this.jugadores_actuales + " jugadores\n";
            }
        }

        return informacionAtaque;
    }


    public void escribirResultadosEnArchivo(String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Jugador jugador : jugadores) {
                if (jugador != null) {
                    writer.write(jugador.toString());
                    writer.newLine();
                }
            }
            writer.write("Resultados de ataques:\n");
            writer.write(resultados.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void leerInformacionDesdeArchivo(String nombreArchivo) {
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}