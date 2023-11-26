package org.jjv.views;

import org.jjv.instances.ClientInstance;

import javax.swing.*;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;

public class ClientOptionsView extends JFrame {
    private JButton addClientAccountsButton;
    private JButton batchOperatorsButton;
    private JLabel clienLabel;
    private JPanel clientOptionsPanel;
    private JButton clientConfigurationButton;
    private JTextField clientField;

    public ClientOptionsView(){
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Operaciones con Clientes");
        setResizable(false);
        clientField.setText(ClientInstance.getSingle().name());
    }

    private void initComponents(){
        clientOptionsPanel = new JPanel();
        clienLabel = new JLabel();
        clientField = new JTextField();
        batchOperatorsButton = new JButton();
        clientConfigurationButton = new JButton();
        addClientAccountsButton = new JButton();
        
        clienLabel.setText("CLIENTE:");

        clientField.setEditable(false);

        batchOperatorsButton.setBackground(new java.awt.Color(153, 0, 153));
        batchOperatorsButton.setForeground(new java.awt.Color(255, 255, 255));
        batchOperatorsButton.setText("<html><p>Carga </p> <p>Terceros</p></html>\n");

        clientConfigurationButton.setBackground(new java.awt.Color(153, 0, 153));
        clientConfigurationButton.setForeground(new java.awt.Color(255, 255, 255));
        clientConfigurationButton.setText("Configuracion");

        addClientAccountsButton.setBackground(new java.awt.Color(153, 0, 153));
        addClientAccountsButton.setForeground(new java.awt.Color(255, 255, 255));
        addClientAccountsButton.setText("+Cuentas");

        GroupLayout clientOptionsPanelLayout = new GroupLayout(clientOptionsPanel);
        clientOptionsPanel.setLayout(clientOptionsPanelLayout);
        clientOptionsPanelLayout.setHorizontalGroup(
                clientOptionsPanelLayout.createParallelGroup(LEADING)
                        .addGroup(clientOptionsPanelLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addGroup(clientOptionsPanelLayout.createParallelGroup(TRAILING)
                                        .addComponent(clienLabel, PREFERRED_SIZE, 50, PREFERRED_SIZE)
                                        .addComponent(batchOperatorsButton, PREFERRED_SIZE, 92, PREFERRED_SIZE))
                                .addGroup(clientOptionsPanelLayout.createParallelGroup(LEADING)
                                        .addGroup(clientOptionsPanelLayout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(clientField, PREFERRED_SIZE, 182, PREFERRED_SIZE))
                                        .addGroup(clientOptionsPanelLayout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(clientConfigurationButton, PREFERRED_SIZE, 92, PREFERRED_SIZE)
                                                .addGap(28, 28, 28)
                                                .addComponent(addClientAccountsButton, PREFERRED_SIZE, 92, PREFERRED_SIZE)))
                                .addContainerGap(39, Short.MAX_VALUE))
        );
        clientOptionsPanelLayout.setVerticalGroup(
                clientOptionsPanelLayout.createParallelGroup(LEADING)
                        .addGroup(clientOptionsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(clientOptionsPanelLayout.createParallelGroup(BASELINE)
                                        .addComponent(clienLabel)
                                        .addComponent(clientField, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                                .addGroup(clientOptionsPanelLayout.createParallelGroup(LEADING)
                                        .addComponent(batchOperatorsButton, TRAILING, PREFERRED_SIZE, 48, PREFERRED_SIZE)
                                        .addGroup(TRAILING, clientOptionsPanelLayout.createParallelGroup(BASELINE)
                                                .addComponent(clientConfigurationButton, PREFERRED_SIZE, 48, PREFERRED_SIZE)
                                                .addComponent(addClientAccountsButton, PREFERRED_SIZE, 51, PREFERRED_SIZE)))
                                .addGap(22, 22, 22))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addComponent(clientOptionsPanel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addComponent(clientOptionsPanel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        pack();
    }
}
