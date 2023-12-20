package proyecto_final;

import java.io.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Scanner;

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
            j2 = (int) Math.floor(Math.random() * jugadores_actuales);
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

            /*for (int i = j2 + 1; i < this.max_jugadores; i++) {
                this.jugadores[i - 1] = this.jugadores[i];
            }*/

            for (int i = j2; i < this.jugadores_actuales - 1; i++) {
                this.jugadores[i] = this.jugadores[i + 1];
            }

            //this.jugadores[this.max_jugadores - 1] = null;
            this.jugadores[this.jugadores_actuales - 1] = null;
            this.jugadores_actuales--;

            if (this.jugadores_actuales == 1) {
                System.out.println("--> El ganador es " + this.jugadores[0].nombre);
            } else {
                System.out.println("--> Quedan " + this.jugadores_actuales + " jugadores");
                resultados = resultados + "\n--> Quedan " + this.jugadores_actuales + " jugadores\n\n";
            }
        }

        informacionAtaque[0] = j1;
        informacionAtaque[1] = j2;

        return informacionAtaque;
    }

    public int getJugadorById(int id) {
        for(int i = 0; i < jugadores_actuales; i++) {

            if(jugadores[i].getId() == id) {
                return i;
            }
        }

        return -1;
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
            writer.write(resultados);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leerInformacionDesdeArchivo(String nombreArchivo) {

        String linea;
        String nombre_jugador;
        Scanner s;

        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {

            while ((linea = lector.readLine()) != null) {
                s = new Scanner(linea);
                s.useDelimiter(",");
                nombre_jugador = s.next();

                if(jugadores_actuales == max_jugadores) {
                    return;
                }

                switch(s.next()) {
                    case "Bombero":
                        jugadores[jugadores_actuales] = new Bombero(nombre_jugador, false);
                        break;

                    case "Chilla":
                        jugadores[jugadores_actuales] = new Chilla(nombre_jugador, false);
                        break;

                    case "Escritor":
                        jugadores[jugadores_actuales] = new Escritor(nombre_jugador, false);
                        break;

                    case "Tyrion":
                        jugadores[jugadores_actuales] = new Tyrion(nombre_jugador, false);
                        break;

                    case "Legolas":
                        jugadores[jugadores_actuales] = new Legolas(nombre_jugador, false);
                        break;
                }

                jugadores[jugadores_actuales].id = jugadores_actuales;
                jugadores_actuales++;


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}