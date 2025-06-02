package backend;

import backend.observer.Observador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Vistaresumen extends JFrame implements Observador {
    private final ControladorPrueba controlador;
    private JTextArea resumenArea;

    public Vistaresumen(ControladorPrueba controlador) {
        this.controlador = controlador;
        this.controlador.agregarObservador(this);

        setTitle("Resumen de la Prueba");
        setSize(400, 300);
        setLocationRelativeTo(null);

        resumenArea = new JTextArea();
        resumenArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        resumenArea.setEditable(false);
        add(new JScrollPane(resumenArea), BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actualizar() {
        List<Item> items = controlador.getItems();
        List<Integer> respuestas = controlador.getRespuestasUsuario();

        int correctas = 0;
        for (int i = 0; i < items.size(); i++) {
            if (respuestas.get(i) == items.get(i).getIndiceRespuestaCorrecta()) {
                correctas++;
            }
        }

        resumenArea.setText("Respuestas correctas: " + correctas + " de " + items.size());
    }
}
