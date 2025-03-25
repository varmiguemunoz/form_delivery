package org.example.swiftawt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingForm {
    private JTextField input_name;
    public JPanel containerform;
    private JPanel container;
    private JLabel medication_label;
    private JLabel name_label;
    private JComboBox medication;
    private JLabel amount_label;
    private JTextField amount_input;
    private JPanel grid_pharmacy;
    private JRadioButton cofarmaRadioButton;
    private JRadioButton empsepharRadioButton;
    private JRadioButton cemefarRadioButton;
    private JLabel distributor;
    private JPanel distributor_panel;
    private JPanel sucursal_panel;
    private JCheckBox principal_checkbox;
    private JCheckBox secundaria_checkbox;
    private JLabel title;
    private JButton save;
    private JButton clean;

    public void SwingForm() {
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    showOrderSummary();
                }
            }
        });

        clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
    }

    private boolean validateForm() {
        String name = input_name.getText().trim();
        String amountStr = amount_input.getText().trim();
        String selectedMedication = (String) medication.getSelectedItem();

        if (name.isEmpty() || !name.matches("[a-zA-Z0-9 ]+")) {
            JOptionPane.showMessageDialog(null, "El nombre del medicamento es inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (amountStr.isEmpty() || !amountStr.matches("\\d+") || Integer.parseInt(amountStr) <= 0) {
            JOptionPane.showMessageDialog(null, "La cantidad debe ser un número entero positivo.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (selectedMedication == null || selectedMedication.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de medicamento.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!cofarmaRadioButton.isSelected() && !empsepharRadioButton.isSelected() && !cemefarRadioButton.isSelected()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un distribuidor.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!principal_checkbox.isSelected() && !secundaria_checkbox.isSelected()) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar al menos una sucursal.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void showOrderSummary() {
        String distributor = "";
        if (cofarmaRadioButton.isSelected()) distributor = "Cofarma";
        if (empsepharRadioButton.isSelected()) distributor = "Empsephar";
        if (cemefarRadioButton.isSelected()) distributor = "Cemefar";

        String sucursal = "";
        if (principal_checkbox.isSelected()) sucursal = "Calle de la Rosa n.28";
        if (secundaria_checkbox.isSelected()) sucursal = "Calle Alcazabilla n.3";

        String summary = "Pedido al distribuidor: " + distributor + "\n" +
                "Cantidad: " + amount_input.getText() + " unidades de " + input_name.getText() + "\n" +
                "Sucursal: " + sucursal;

        JFrame summaryFrame = new JFrame("Pedido al distribuidor " + distributor);
        summaryFrame.setSize(400, 200);
        summaryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        summaryFrame.setLayout(new GridLayout(4, 1));

        JLabel summaryLabel = new JLabel("<html>" + summary.replace("\n", "<br>") + "</html>");
        JButton cancelButton = new JButton("Cancelar");
        JButton confirmButton = new JButton("Confirmar");

        cancelButton.addActionListener(e -> summaryFrame.dispose());
        confirmButton.addActionListener(e -> {
            System.out.println("Pedido enviado.");
            summaryFrame.dispose();
        });

        summaryFrame.add(summaryLabel);
        summaryFrame.add(cancelButton);
        summaryFrame.add(confirmButton);
        summaryFrame.setVisible(true);
    }

    private void clearForm() {
        input_name.setText("");
        amount_input.setText("");
        medication.setSelectedIndex(0);
        cofarmaRadioButton.setSelected(false);
        empsepharRadioButton.setSelected(false);
        cemefarRadioButton.setSelected(false);
        principal_checkbox.setSelected(false);
        secundaria_checkbox.setSelected(false);
    }
}
