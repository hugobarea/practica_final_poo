package proyecto_final;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class InterfazGrafica extends JFrame implements ActionListener {

    private Partida partida;
    private int N_JUGADORES;
    private JButton btn_jugadores[];
    private JTextArea pantalla;
    public InterfazGrafica() {

        this.N_JUGADORES = 5;

        this.partida = new Partida(N_JUGADORES);

        setSize(400, 800);
        setTitle("Battle Royale");
        setLayout(new FlowLayout());

        partida.addJugador(new Bombero( "Manolito", false));
        partida.addJugador(new Bombero("Fran", false));
        partida.addJugador(new Bombero("Paco", false));
        partida.addJugador(new Bombero( "El Gamer", false));
        partida.addJugador(new Bombero( "Manuel Rodriguez", false));

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
                System.out.println("Ataque simulado");

                if(informacionAtaque[4] == 1) {
                    btn_jugadores[(int) informacionAtaque[1]].setBackground(Color.RED);
                    pantalla.setText(partida.getMuertos()[N_JUGADORES - partida.getVivos() - 1].nombre + " ha muerto.");
                }

            }
        }

    }
}
