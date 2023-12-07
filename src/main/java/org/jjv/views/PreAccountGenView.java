package org.jjv.views;

import org.jjv.instances.ClientConfigInstance;
import org.jjv.utils.DocumentNature;

import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static javax.swing.GroupLayout.*;
import static javax.swing.GroupLayout.Alignment.*;

public class PreAccountGenView extends JFrame {
    private JToggleButton applyButton;
    private JTextField clientField;
    private JLabel clientLabel;
    private JLabel configLabel;
    private JPanel panel;
    private JTextField provField;
    private JLabel provLabel;
    public PreAccountGenView(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Subcuentas");
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();

        applyButton.addActionListener(e -> {
            if (!clientField.getText().isEmpty() && !provField.getText().isEmpty()){
                applyConfig();
            }
        });
    }

    private void initComponents(){
        panel = new JPanel();
        configLabel = new JLabel();
        clientLabel = new JLabel();
        clientField = new JTextField();
        provLabel = new JLabel();
        provField = new JTextField();
        applyButton = new JToggleButton();

        configLabel.setText("Es necesario indicar el numero inicial para generar las subcuentas");

        clientLabel.setText("Clientes");

        provLabel.setText("Proveedores");

        applyButton.setBackground(new java.awt.Color(153, 0, 153));
        applyButton.setForeground(new java.awt.Color(255, 255, 255));
        applyButton.setText("Continuar");

        GroupLayout panelLayout = new GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(LEADING)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(configLabel))
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGap(81, 81, 81)
                                                .addComponent(clientLabel)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addGroup(panelLayout.createParallelGroup(LEADING)
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addComponent(clientField, PREFERRED_SIZE, 25, PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(provLabel)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(provField, PREFERRED_SIZE, 25, PREFERRED_SIZE))
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addGap(11, 11, 11)
                                                                .addComponent(applyButton)))))
                                .addContainerGap(26, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(configLabel)
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(BASELINE)
                                        .addComponent(clientLabel)
                                        .addComponent(clientField, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(provLabel)
                                        .addComponent(provField, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(applyButton)
                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addComponent(panel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addComponent(panel, TRAILING, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
    }

    private void applyConfig(){
        Map<DocumentNature, Integer> tempConfig = new HashMap<>();
        tempConfig.put(DocumentNature.RECEIVED, Integer.parseInt(provField.getText()));
        tempConfig.put(DocumentNature.EMITTED, Integer.parseInt(clientField.getText()));
        ClientConfigInstance.create(tempConfig);
    }
}
