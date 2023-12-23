package proyecto_final;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


// EL PROGRAMA DEBE ARRANCARSE DESDE ESTE ARCHIVO

public class InterfazGrafica extends JFrame implements ActionListener {

    private Partida partida;
    private int N_JUGADORES;
    private JButton btn_jugadores[];
    private JTextArea pantalla;
    private JButton n_ronda;
    private int contador_ronda;
    private JButton leer_archivo;
    private JPanel panel_introducir_jugador;
    private JButton siguienteRonda;
    private JPanel panel_decision;
    private JButton btn_decision;
    private JTextField decision;

    public InterfazGrafica() {

        this.N_JUGADORES = 30;
        this.contador_ronda = 1;

        this.partida = new Partida(N_JUGADORES);

        setSize(500, 1000);
        setTitle("Mariscos Royale");
        setLayout(new FlowLayout());

        this.btn_jugadores = new JButton[N_JUGADORES];
        Jugador jugadores[] = partida.getJugadores();


        //  CREAR IMAGEN Y TITULO

        JPanel titulo_portada = new JPanel();
        try {
            BufferedImage portada = ImageIO.read(new File("img.png"));
            Image portada_escalada = portada.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            JLabel portada_label = new JLabel(new ImageIcon(portada_escalada));

            JLabel titulo = new JLabel("Mariscos Royale - Nicolás Álvarez y Hugo Barea");

            titulo_portada.add(portada_label);
            add(titulo_portada);
            add(titulo);

        } catch(Exception e) {
            System.out.println("ERROR - Archivo de cabecera no encontrado");
            return;
        }

        // CREAR BOTON PARA ACTUALIZAR NUMERO DE RONDA

        n_ronda = new JButton("Ronda 1");
        add(n_ronda);

        // CREAR BOTON DE SIGUIENTE RONDA

        siguienteRonda = new JButton("Empezar");
        siguienteRonda.addActionListener(this);
        siguienteRonda.setEnabled(false);
        add(siguienteRonda);


        // CREAR PANTALLA PARA MOSTRAR MENSAJES

        String textoPantalla = "";
        pantalla = new JTextArea();
        pantalla.setColumns(30);
        pantalla.setRows(5);
        pantalla.setEditable(false);
        add(pantalla);

        // CREAR BOTON PARA LEER DESDE ARCHIVO

        leer_archivo = new JButton("Simular desde archivo");
        leer_archivo.addActionListener(this);
        add(leer_archivo);


        // CREAR TEXTO Y BOTON PARA CREAR JUGADOR REAL

        panel_introducir_jugador = new JPanel();
        JTextField nombre_jugador = new JTextField();
        nombre_jugador.setColumns(10);
        nombre_jugador.setText("Jugador");
        String array_clases[] = {"Ostra", "Camaron", "Langosta", "Gamba", "Almeja"};
        JList clases_jugador = new JList(array_clases);
        JButton crear_jugador = new JButton("Crear Marisco");
        crear_jugador.addActionListener(this);

        panel_introducir_jugador.add(nombre_jugador);
        panel_introducir_jugador.add(clases_jugador);
        panel_introducir_jugador.add(crear_jugador);

        add(panel_introducir_jugador);

        // PANEL DE DECISION DEL JUGADOR

        panel_decision = new JPanel();

        decision = new JTextField();
        decision.setColumns(10);
        btn_decision = new JButton("Decidir");
        btn_decision.addActionListener(this);
        panel_decision.add(decision);
        panel_decision.add(btn_decision);
    }

    public static void main(String args[]) {

        InterfazGrafica interfazGrafica = new InterfazGrafica();
        interfazGrafica.setVisible(true);
    }

    private void generarBotones() {

        JPanel grid_botones = new JPanel();
        grid_botones.setLayout(new GridLayout(Math.round(N_JUGADORES / 3), 3));

        for(int i = 0; i < N_JUGADORES; i++) {
            btn_jugadores[i] = new JButton();
            btn_jugadores[i].setText(partida.getJugadores()[i].getNombre());
            btn_jugadores[i].setOpaque(true);
            btn_jugadores[i].setBorderPainted(false);
            btn_jugadores[i].setBackground(Color.green);
            grid_botones.add(btn_jugadores[i]);
        }
        add(grid_botones);
    }

    private void crearPartida() {

        // Obtener el nombre y la clase del jugador, y rellenar con bots desde un archivo

        String nombre_jugador = ((JTextField) panel_introducir_jugador.getComponent(0)).getText();
        String clase = ((JList<String>) panel_introducir_jugador.getComponent(1)).getSelectedValue();

        partida.leerInformacionDesdeArchivo("input.txt", N_JUGADORES - 1);

        switch(clase) {
            case "Camaron":
                partida.addJugador(new Camaron(nombre_jugador, true));
                break;

            case "Langosta":
                partida.addJugador(new Langosta(nombre_jugador, true));
                break;

            case "Ostra":
                partida.addJugador(new Ostra(nombre_jugador, true));
                break;

            case "Gamba":
                partida.addJugador(new Gamba(nombre_jugador, true));
                break;

            case "Almeja":
                partida.addJugador(new Almeja(nombre_jugador, true));
                break;
        }

        remove(panel_introducir_jugador);
        generarBotones();
        siguienteRonda.setEnabled(true);

    }

    private void crearSimulacionArchivo() {

       remove(panel_introducir_jugador); // si simulamos una partida no se introducirá ningún jugador

        JFileChooser elegir_archivo = new JFileChooser();
        elegir_archivo.setCurrentDirectory(new File("."));
        int opcion = elegir_archivo.showOpenDialog(this);

        if(opcion == JFileChooser.APPROVE_OPTION) {
            partida.leerInformacionDesdeArchivo(elegir_archivo.getSelectedFile().getName(), N_JUGADORES);

            generarBotones();
            siguienteRonda.setEnabled(true);
        }
    }

    private void gestionarJugadorReal(double informacionAtaque[]) {
        int id_real = (int) informacionAtaque[0];
        Jugador j_real = partida.getJugadores()[id_real];
        String nombre_ataque;

        if(((JTextField) panel_decision.getComponent(0)).getText().equals("1")) {
            pantalla.setText("Elige el nombre del jugador a atacar:");
            ((JButton) panel_decision.getComponent(1)).setText("Atacar");

        } else if(((JTextField) panel_decision.getComponent(0)).getText().equals("2")) {
            pantalla.setText("Introduce 1 para un mini caparazón y 2 para un super caparazón");
            ((JButton) panel_decision.getComponent(1)).setText("Curarse");
        }

    }
    public void actionPerformed(ActionEvent e) {

        double informacionAtaque[] = new double[5];
        int posicion_j1 = 0;
        int posicion_j2 = 0;

        if(e.getSource() instanceof JButton) {

            if(((JButton) e.getSource()).getText() == "Empezar") {
                ((JButton) e.getSource()).setText("Siguiente ronda");
                leer_archivo.setEnabled(false);
            }

            if(((JButton) e.getSource()).getText() == "Siguiente ronda") {
                remove(btn_decision);
                remove(decision);
                informacionAtaque = partida.simularAtaque(-1, -1);
                contador_ronda++;
                n_ronda.setText("Ronda " + contador_ronda);

                if(informacionAtaque == null) {
                    pantalla.setText(partida.getJugadores()[0].nombre + " ha ganado la partida.");
                    ((JButton) e.getSource()).setEnabled(false);
                    partida.escribirResultadosEnArchivo("resultados.txt");
                }

                if(informacionAtaque[4] == -1) {
                    pantalla.setText("Elige qué hacer:\nIntroduce 1 para atacar\nIntroduce 2 para curarte");
                    siguienteRonda.setEnabled(false);
                    ((JButton) panel_decision.getComponent(1)).setText("Decidir");
                    add(panel_decision);
                }

                if(informacionAtaque[4] == 1) {

                    for(int i = 0; i < btn_jugadores.length; i++) {
                        if(btn_jugadores[i].getText().equals(partida.getMuertos()[N_JUGADORES - partida.getVivos() - 1].getNombre())) {
                            btn_jugadores[i].setBackground(Color.RED);
                        }
                    }
                    pantalla.setText(partida.getJugadores()[(int) informacionAtaque[0]].getNombre() + " ha atacado a " + partida.getMuertos()[N_JUGADORES - partida.getVivos() - 1].nombre + ": " + -1 * informacionAtaque[2] + " de daño.\n" + partida.getMuertos()[N_JUGADORES - partida.getVivos() - 1].nombre + " ha muerto.");


                } else if(informacionAtaque[4] != -1) {
                    pantalla.setText(partida.getJugadores()[(int) informacionAtaque[0]].getNombre() + " ha atacado a " + partida.getJugadores()[(int) informacionAtaque[1]].getNombre() + ": " + -1 * informacionAtaque[2] + " de daño.");
                }

            }
            else if(((JButton) e.getSource()).getText().equals("Simular desde archivo")) {
                crearSimulacionArchivo();
            }

            else if(((JButton) e.getSource()).getText().equals("Crear Marisco")) {
                crearPartida();
            }

            else if(((JButton) e.getSource()).getText().equals("Decidir")) {
                gestionarJugadorReal(informacionAtaque);
            }

            else if(((JButton) e.getSource()).getText().equals("Atacar")) {
                String nombre_ataque = ((JTextField) panel_decision.getComponent(0)).getText();
                informacionAtaque = partida.simularAtaque((int) informacionAtaque[0], partida.getJugadorByNombre(nombre_ataque));
                siguienteRonda.setEnabled(true);

                if(informacionAtaque[4] == 1) {
                    for(int i = 0; i < btn_jugadores.length; i++) {
                        if(btn_jugadores[i].getText().equals(partida.getMuertos()[N_JUGADORES - partida.getVivos() - 1].getNombre())) {
                            btn_jugadores[i].setBackground(Color.RED);
                        }
                    }
                    pantalla.setText(partida.getJugadores()[(int) informacionAtaque[0]].getNombre() + " ha atacado a " + partida.getMuertos()[N_JUGADORES - partida.getVivos() - 1].nombre + ": " + -1 * informacionAtaque[2] + " de daño.\n" + partida.getMuertos()[N_JUGADORES - partida.getVivos() - 1].nombre + " ha muerto.");
                }
            }

            else if(((JButton) e.getSource()).getText().equals("Curarse")) {
                String curacion = ((JTextField) panel_decision.getComponent(0)).getText();
                partida.getJugadores()[(int) informacionAtaque[0]].curarse(Integer.parseInt(curacion) - 1);
                siguienteRonda.setEnabled(true);
            }
        }

    }
}
