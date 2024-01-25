package org.jjv.views;

import org.jjv.instances.TaskCompleteInstance;
import org.jjv.utils.DocumentNature;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import static java.lang.Short.*;
import static javax.swing.GroupLayout.*;
import static javax.swing.GroupLayout.Alignment.*;

public class EntityProcessorView extends JFrame {
    private JCheckBox clientCheckBox;
    private JButton completeTXTButton;
    private JLabel label;
    private JPanel panel;
    private JButton processClientsButton;
    private JButton processProvidersButton;
    private JCheckBox providerCheckBox;

    public EntityProcessorView(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Procesar Terceros");
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();
        TaskCompleteInstance.initialStatus();

        this.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                providerCheckBox.setSelected(TaskCompleteInstance.getProviderProcessStatus());
                clientCheckBox.setSelected(TaskCompleteInstance.getClientProcessStatus());
                processProvidersButton.setEnabled(true);
                processClientsButton.setEnabled(true);
                completeTXTButton.setEnabled(true);
            }
            @Override
            public void windowLostFocus(WindowEvent e) {
                processProvidersButton.setEnabled(false);
                processClientsButton.setEnabled(false);
                completeTXTButton.setEnabled(false);
            }
        });

        processClientsButton.addActionListener(
                e -> ControllerView.connectOperatorCreationView(DocumentNature.EMITTED));
        processProvidersButton.addActionListener(
                e -> ControllerView.connectOperatorCreationView(DocumentNature.RECEIVED));
    }

    private void initComponents(){
        panel = new JPanel();
        providerCheckBox = new JCheckBox();
        clientCheckBox = new JCheckBox();
        processProvidersButton = new JButton();
        processClientsButton = new JButton();
        label = new JLabel();
        completeTXTButton = new JButton();

        providerCheckBox.setText("Proveedores");
        providerCheckBox.setEnabled(false);

        clientCheckBox.setText("Clientes");
        clientCheckBox.setEnabled(false);

        processProvidersButton.setBackground(new Color(153, 0, 153));
        processProvidersButton.setForeground(new Color(255, 255, 255));
        processProvidersButton.setText("Procesar");

        processClientsButton.setBackground(new Color(153, 0, 153));
        processClientsButton.setForeground(new Color(255, 255, 255));
        processClientsButton.setText("Procesar");

        label.setText("Selecciona Cllientes o Proveedores para completar los datos de Terceros");

        completeTXTButton.setBackground(new Color(153, 0, 153));
        completeTXTButton.setForeground(new Color(255, 255, 255));
        completeTXTButton.setText("Crear TXT");

        GroupLayout panelLayout = new GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(LEADING)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(label))
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGap(107, 107, 107)
                                                .addGroup(panelLayout.createParallelGroup(LEADING)
                                                        .addComponent(providerCheckBox)
                                                        .addComponent(clientCheckBox))
                                                .addGap(18, 18, 18)
                                                .addGroup(panelLayout.createParallelGroup(LEADING)
                                                        .addComponent(processClientsButton)
                                                        .addComponent(processProvidersButton)))
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGap(142, 142, 142)
                                                .addComponent(completeTXTButton, PREFERRED_SIZE, 97, PREFERRED_SIZE)))
                                .addContainerGap(24, MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(label)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(BASELINE)
                                        .addComponent(providerCheckBox)
                                        .addComponent(processProvidersButton))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelLayout.createParallelGroup(BASELINE)
                                        .addComponent(clientCheckBox)
                                        .addComponent(processClientsButton))
                                .addGap(18, 18, 18)
                                .addComponent(completeTXTButton, DEFAULT_SIZE, 32, MAX_VALUE)
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addComponent(panel, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addComponent(panel, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
        );

        pack();
    }
}
