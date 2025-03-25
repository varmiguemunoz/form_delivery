package org.example.swiftawt;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Formulario de Pedido");
            SwingForm swingForm = new SwingForm();

            frame.setContentPane(swingForm.containerform);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setSize(600, 600);
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
        });
    }
}
