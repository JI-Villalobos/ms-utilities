package org.jjv.views;

import javax.swing.*;
import java.awt.Color;

import static javax.swing.GroupLayout.*;

public class InitialView extends JFrame {
    private JPanel buttonPanel;
    private JButton clientButton;
    private JButton opButton;
    
    public InitialView(){
        setTitle("Microsip Utilities");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();

        clientButton.addActionListener(e -> ControllerView.connectClientsView(this));

        opButton.addActionListener(e -> ControllerView.connectOtherOpsView());
    }
    
    private void initComponents(){
        buttonPanel = new JPanel();
        clientButton = new JButton();
        opButton = new JButton();

        clientButton.setBackground(new Color(153, 0, 153));
        clientButton.setForeground(new Color(255, 255, 255));
        clientButton.setText("Mis Clientes");
        clientButton.setPreferredSize(new java.awt.Dimension(90, 35));

        opButton.setBackground(new Color(153, 0, 153));
        opButton.setForeground(new Color(255, 255, 255));
        opButton.setText("Otras OP.");
        opButton.setPreferredSize(new java.awt.Dimension(90, 35));

        GroupLayout buttonPanelLayout = new GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
                buttonPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(buttonPanelLayout.createSequentialGroup()
                                .addContainerGap(84, Short.MAX_VALUE)
                                .addComponent(clientButton, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(opButton, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addGap(76, 76, 76))
        );
        buttonPanelLayout.setVerticalGroup(
                buttonPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(buttonPanelLayout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(buttonPanelLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(opButton, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(clientButton, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addContainerGap(48, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(buttonPanel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(buttonPanel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
}
