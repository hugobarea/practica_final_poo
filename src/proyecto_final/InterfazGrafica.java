package proyecto_final;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class InterfazGrafica extends JFrame implements ActionListener {

    private Partida partida;
    private int N_JUGADORES;
    private JButton btn_jugadores[];
    private JTextArea pantalla;
    private JButton n_ronda;
    private int contador_ronda;
    public InterfazGrafica() {

        this.N_JUGADORES = 5;
        this.contador_ronda = 1;

        this.partida = new Partida(N_JUGADORES);

        setSize(400, 800);
        setTitle("Battle Royale");
        setLayout(new FlowLayout());

        partida.addJugador(new Legolas( "Manolito", false));
        partida.addJugador(new Chilla("Fran", false));
        partida.addJugador(new Tyrion("Paco", false));
        partida.addJugador(new Bombero( "El Gamer", false));
        partida.addJugador(new Escritor( "Manuel Rodriguez", false));

        this.btn_jugadores = new JButton[N_JUGADORES];
        Jugador jugadores[] = partida.getJugadores();


        // CREAR BOTONES DE JUGADORES
        for(int i = 0; i < N_JUGADORES; i++) {
            btn_jugadores[i] = new JButton();
            btn_jugadores[i].setText(jugadores[i].getNombre());
            btn_jugadores[i].setOpaque(true);
            btn_jugadores[i].setBorderPainted(false);
            btn_jugadores[i].setBackground(Color.green);
            add(btn_jugadores[i]);
        }

        n_ronda = new JButton("Ronda 1");
        add(n_ronda);

        // CREAR BOTON DE SIGUIENTE RONDA

        JButton siguienteRonda = new JButton("Siguiente ronda");
        siguienteRonda.addActionListener(this);
        add(siguienteRonda);


        // CREAR PANTALLA PARA MOSTRAR MENSAJES

        String textoPantalla = "";
        pantalla = new JTextArea();
        pantalla.setColumns(30);
        pantalla.setRows(5);
        pantalla.setEditable(false);

        add(pantalla);

    }

    public static void main(String args[]) {

        /*partida.simularAtaque();
        partida.simularAtaque();
        partida.simularAtaque();
        partida.simularAtaque();
        partida.simularAtaque();
        partida.simularAtaque();*/

        InterfazGrafica interfazGrafica = new InterfazGrafica();
        interfazGrafica.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        double informacionAtaque[] = new double[5];

        if(e.getSource() instanceof JButton) {
            if(((JButton) e.getSource()).getText() == "Siguiente ronda") {
                informacionAtaque = partida.simularAtaque();
                contador_ronda++;
                n_ronda.setText("Ronda " + contador_ronda);

                if(informacionAtaque[4] == 1) {
                    btn_jugadores[(int) informacionAtaque[1]].setBackground(Color.RED);
                    pantalla.setText(partida.getMuertos()[N_JUGADORES - partida.getVivos() - 1].nombre + " ha muerto.");
                } else {
                    pantalla.setText(partida.getJugadores()[(int) informacionAtaque[0]].getNombre() + " ha atacado a " + partida.getJugadores()[(int) informacionAtaque[1]].getNombre() + ": " + -1 * informacionAtaque[2] + " de daño.");
                }

                if(partida.getVivos() == 1) {
                    pantalla.setText(partida.getJugadores()[0].nombre + " ha ganado la partida.");
                    ((JButton) e.getSource()).setEnabled(false);
                }

            }
        }

    }
}
