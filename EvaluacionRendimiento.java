/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class EvaluacionRendimiento extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);

    public EvaluacionRendimiento() {
        setTitle("Evaluación de Rendimiento");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Paneles del CardLayout
        JPanel panelEvaluacion = crearPanelEvaluacion();
        JPanel panelResultados = crearPanelResultados();

        // Añadir paneles al CardLayout
        cardPanel.add(panelEvaluacion, "Evaluacion");
        cardPanel.add(panelResultados, "Resultados");

        // Configurar el menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");
        JMenuItem evaluarItem = new JMenuItem("Evaluar");
        JMenuItem resultadosItem = new JMenuItem("Ver Resultados");

        menu.add(evaluarItem);
        menu.add(resultadosItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Cambiar entre las pantallas de evaluación y resultados
        evaluarItem.addActionListener(e -> cardLayout.show(cardPanel, "Evaluacion"));
        resultadosItem.addActionListener(e -> cardLayout.show(cardPanel, "Resultados"));

        add(cardPanel);
        setVisible(true);
    }

    // Panel de Evaluación
    private JPanel crearPanelEvaluacion() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel labelCalificacion = new JLabel("Calificación:");
        JTextField campoCalificacion = new JTextField();
        campoCalificacion.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                JTextField textField = (JTextField) input;
                try {
                    int value = Integer.parseInt(textField.getText());
                    return value >= 0 && value <= 100;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        });

        JButton botonEnviar = new JButton("Enviar Evaluación");
        botonEnviar.addActionListener(e -> {
            if (campoCalificacion.getInputVerifier().verify(campoCalificacion)) {
                JOptionPane.showMessageDialog(null, "Evaluación enviada.");
            } else {
                JOptionPane.showMessageDialog(null, "Por favor ingresa una calificación válida (0-100).");
            }
        });

        panel.add(labelCalificacion);
        panel.add(campoCalificacion);
        panel.add(botonEnviar);

        return panel;
    }

    // Panel de Resultados con Tabla y Gráfico
    private JPanel crearPanelResultados() {
        JPanel panel = new JPanel(new BorderLayout());

        // Crear tabla con resultados
        String[] columnNames = {"Empleado", "Calificación", "Fecha"};
        Object[][] data = {
            {"Juan Pérez", "85", "01/09/2024"},
            {"Maria Gómez", "90", "02/09/2024"}
        };
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.NORTH);

        // Crear gráfico de rendimiento
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(85, "Calificación", "Juan Pérez");
        dataset.addValue(90, "Calificación", "Maria Gómez");

        JFreeChart chart = ChartFactory.createBarChart(
                "Rendimiento de Empleados",
                "Empleado",
                "Calificación",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel, BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {
        try {
            // Aplicar el Look and Feel Nimbus para un estilo moderno
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Iniciar la interfaz de Evaluación de Rendimiento
        new EvaluacionRendimiento();
    }
}
