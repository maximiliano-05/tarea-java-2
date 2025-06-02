package frontend;

import backend.ControladorPrueba;
import backend.Item;
import backend.ItemLoader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

public class MainWindow extends JFrame {

    private JButton iniciarPruebaButton;
    private final ControladorPrueba controlador = new ControladorPrueba();  // ✅ instancia única

    public MainWindow() {
        setTitle("Sistema de Evaluación - Taxonomía de Bloom");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 245, 255));
        mainPanel.setLayout(new BorderLayout(0, 20));
        mainPanel.setBorder(new EmptyBorder(40, 40, 40, 40));

        JLabel titleLabel = new JLabel("Sistema de Evaluación", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(30, 30, 60));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JButton cargarArchivoButton = new JButton("📁 Cargar archivo de ítems");
        cargarArchivoButton.setFocusPainted(false);
        cargarArchivoButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        cargarArchivoButton.setBackground(new Color(100, 149, 237));
        cargarArchivoButton.setForeground(Color.WHITE);
        cargarArchivoButton.setPreferredSize(new Dimension(250, 40));
        cargarArchivoButton.addActionListener(this::cargarArchivoAction);

        iniciarPruebaButton = new JButton("🚀 Iniciar prueba");
        iniciarPruebaButton.setFocusPainted(false);
        iniciarPruebaButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        iniciarPruebaButton.setBackground(new Color(60, 179, 113));
        iniciarPruebaButton.setForeground(Color.WHITE);
        iniciarPruebaButton.setPreferredSize(new Dimension(250, 40));
        iniciarPruebaButton.setEnabled(false);
        iniciarPruebaButton.addActionListener(this::iniciarPruebaAction);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(2, 1, 0, 20));
        buttonPanel.add(cargarArchivoButton);
        buttonPanel.add(iniciarPruebaButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        setContentPane(mainPanel);
    }

    private void cargarArchivoAction(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File archivo = fileChooser.getSelectedFile();
            try {
                List<Item> listaDeItems = ItemLoader.cargarDesdeArchivo(archivo);
                controlador.cargarItems(listaDeItems);
                JOptionPane.showMessageDialog(this, "Ítems cargados: " + listaDeItems.size(),
                        "Carga Exitosa", JOptionPane.INFORMATION_MESSAGE);
                iniciarPruebaButton.setEnabled(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar ítems:\n" + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void iniciarPruebaAction(ActionEvent e) {
        new TestWindow(controlador);
        dispose();
    }
}
