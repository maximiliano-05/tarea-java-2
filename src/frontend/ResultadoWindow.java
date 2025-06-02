package frontend;

import backend.Item;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ResultadoWindow extends JFrame {

    public ResultadoWindow(List<Item> items, List<Integer> respuestasUsuario) {
        setTitle("Resultados de la Evaluación");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(new Color(245, 248, 255));
        mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel titulo = new JLabel("Resumen de Resultados", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(new Color(33, 37, 41));
        mainPanel.add(titulo, BorderLayout.NORTH);

        JTextArea resultadoArea = new JTextArea();
        resultadoArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        resultadoArea.setEditable(false);
        resultadoArea.setMargin(new Insets(10, 10, 10, 10));

        int correctas = 0;
        StringBuilder resumen = new StringBuilder();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            int seleccion = respuestasUsuario.get(i);
            boolean correcta = seleccion == item.getIndiceRespuestaCorrecta();

            if (correcta) correctas++;

            resumen.append("Pregunta ").append(i + 1).append(": ")
                    .append(correcta ? "✔ Correcta" : "✘ Incorrecta").append("\n")
                    .append("Tu respuesta: ")
                    .append(seleccion != -1 && seleccion < item.getOpciones().size()
                            ? item.getOpciones().get(seleccion)
                            : "No respondida")
                    .append("\n")
                    .append("Respuesta correcta: ")
                    .append(item.getOpciones().get(item.getIndiceRespuestaCorrecta()))
                    .append("\n\n");
        }

        double porcentaje = (double) correctas / items.size() * 100;
        resumen.insert(0, "Puntaje total: " + correctas + "/" + items.size() +
                " (" + String.format("%.1f", porcentaje) + "%)\n\n");

        resultadoArea.setText(resumen.toString());

        JScrollPane scroll = new JScrollPane(resultadoArea);
        scroll.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JButton cerrarButton = new JButton("Cerrar");
        cerrarButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cerrarButton.setBackground(new Color(220, 53, 69));
        cerrarButton.setForeground(Color.WHITE);
        cerrarButton.addActionListener(_ -> System.exit(0));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(19, 68, 187));
        bottomPanel.add(cerrarButton);

        mainPanel.add(scroll, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }
}


