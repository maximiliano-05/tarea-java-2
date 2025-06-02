package frontend;

import backend.ControladorPrueba;
import backend.Item;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class TestWindow extends JFrame {
    private final ControladorPrueba controlador;

    private int indiceActual = 0;
    private JLabel enunciadoLabel;
    private JLabel timerLabel;
    private JRadioButton[] opcionesButtons;
    private ButtonGroup opcionesGroup;
    private JButton anteriorButton, siguienteButton;

    private Timer timer;
    private int tiempoRestante;

    public TestWindow(ControladorPrueba controlador) {
        this.controlador = controlador;
        this.tiempoRestante = controlador.getItems().size() * 60; // 1 minuto por pregunta

        setTitle("Aplicación de la Prueba");
        setSize(700, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        iniciarTemporizador();
        mostrarItem(indiceActual);

        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mainPanel.setBackground(new Color(245, 248, 255));

        enunciadoLabel = new JLabel();
        enunciadoLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        enunciadoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        enunciadoLabel.setForeground(new Color(33, 37, 41));

        timerLabel = new JLabel("Tiempo restante: 02:00", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        timerLabel.setForeground(new Color(100, 100, 100));

        JPanel opcionesPanel = new JPanel(new GridLayout(4, 1, 15, 15));
        opcionesPanel.setBackground(new Color(245, 248, 255));

        opcionesButtons = new JRadioButton[4];
        opcionesGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            opcionesButtons[i] = new JRadioButton();
            opcionesButtons[i].setFont(new Font("Segoe UI", Font.PLAIN, 15));
            opcionesButtons[i].setFocusPainted(false);
            opcionesButtons[i].setBackground(Color.WHITE);
            opcionesButtons[i].setOpaque(true);
            opcionesButtons[i].setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200)),
                    BorderFactory.createEmptyBorder(10, 15, 10, 15)
            ));
            opcionesGroup.add(opcionesButtons[i]);
            opcionesPanel.add(opcionesButtons[i]);

            int finalI = i;
            opcionesButtons[i].addActionListener(e -> controlador.responder(indiceActual, finalI));
        }

        anteriorButton = new JButton("← Atrás");
        siguienteButton = new JButton("Siguiente →");

        anteriorButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        siguienteButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        anteriorButton.setBackground(new Color(220, 220, 220));
        siguienteButton.setBackground(new Color(100, 181, 246));
        siguienteButton.setForeground(Color.WHITE);

        anteriorButton.setEnabled(false);

        anteriorButton.addActionListener(e -> {
            if (indiceActual > 0) {
                indiceActual--;
                mostrarItem(indiceActual);
            }
        });

        siguienteButton.addActionListener(e -> {
            if (indiceActual < controlador.getItems().size() - 1) {
                indiceActual++;
                mostrarItem(indiceActual);
            } else {
                enviarRespuestas();
            }
        });

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        botonesPanel.setBackground(new Color(245, 248, 255));
        botonesPanel.add(anteriorButton);
        botonesPanel.add(siguienteButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(245, 248, 255));
        topPanel.add(enunciadoLabel, BorderLayout.CENTER);
        topPanel.add(timerLabel, BorderLayout.SOUTH);

        JPanel leyendaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leyendaPanel.setBackground(new Color(245, 248, 255));
        leyendaPanel.setBorder(BorderFactory.createTitledBorder("Leyenda de Niveles Taxonómicos"));
        leyendaPanel.add(crearEtiquetaNivel("RECORDAR", new Color(52, 152, 219)));
        leyendaPanel.add(crearEtiquetaNivel("COMPRENDER", new Color(46, 204, 113)));
        leyendaPanel.add(crearEtiquetaNivel("APLICAR", new Color(241, 196, 15)));
        leyendaPanel.add(crearEtiquetaNivel("ANALIZAR", new Color(230, 126, 34)));
        leyendaPanel.add(crearEtiquetaNivel("EVALUAR", new Color(231, 76, 60)));
        leyendaPanel.add(crearEtiquetaNivel("CREAR", new Color(155, 89, 182)));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(245, 248, 255));
        centerPanel.add(opcionesPanel, BorderLayout.CENTER);
        centerPanel.add(leyendaPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(botonesPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private JLabel crearEtiquetaNivel(String texto, Color color) {
        JLabel label = new JLabel(texto);
        label.setOpaque(true);
        label.setBackground(color);
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return label;
    }

    private void mostrarItem(int indice) {
        Item item = controlador.getItems().get(indice);
        List<String> opciones = item.getOpciones();

        Color colorNivel;
        switch (item.getNivelTaxonomico()) {
            case RECORDAR -> colorNivel = new Color(52, 152, 219);
            case COMPRENDER -> colorNivel = new Color(46, 204, 113);
            case APLICAR -> colorNivel = new Color(241, 196, 15);
            case ANALIZAR -> colorNivel = new Color(230, 126, 34);
            case EVALUAR -> colorNivel = new Color(231, 76, 60);
            case CREAR -> colorNivel = new Color(155, 89, 182);
            default -> colorNivel = Color.BLACK;
        }

        String texto = String.format("<html><span style='color: rgb(%d,%d,%d);'>[%s]</span> Pregunta %d: %s</html>",
                colorNivel.getRed(), colorNivel.getGreen(), colorNivel.getBlue(),
                item.getNivelTaxonomico().name(), (indice + 1), item.getEnunciado());

        enunciadoLabel.setText(texto);

        for (int i = 0; i < opcionesButtons.length; i++) {
            if (i < opciones.size()) {
                opcionesButtons[i].setText(opciones.get(i));
                opcionesButtons[i].setVisible(true);
            } else {
                opcionesButtons[i].setVisible(false);
            }
        }

        opcionesGroup.clearSelection();
        int seleccion = controlador.getRespuestasUsuario().get(indice);
        if (seleccion != -1 && seleccion < opcionesButtons.length) {
            opcionesButtons[seleccion].setSelected(true);
        }

        anteriorButton.setEnabled(indice > 0);
        siguienteButton.setText(indice == controlador.getItems().size() - 1 ? "Enviar respuestas" : "Siguiente →");
    }

    private void enviarRespuestas() {
        if (timer != null) timer.stop();

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas enviar tus respuestas?",
                "Confirmar envío",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new ResultadoWindow(controlador.getItems(), controlador.getRespuestasUsuario());
        }
    }

    private void enviarRespuestasPorTiempo() {
        JOptionPane.showMessageDialog(this, "¡Se acabó el tiempo! Se enviarán tus respuestas.");
        dispose();
        new ResultadoWindow(controlador.getItems(), controlador.getRespuestasUsuario());
    }

    private void iniciarTemporizador() {
        timer = new Timer(1000, e -> {
            tiempoRestante--;
            int minutos = tiempoRestante / 60;
            int segundos = tiempoRestante % 60;
            timerLabel.setText(String.format("Tiempo restante: %02d:%02d", minutos, segundos));

            if (tiempoRestante <= 0) {
                timer.stop();
                enviarRespuestasPorTiempo();
            }
        });
        timer.start();
    }
}
