package org.jjv.views;

import javax.swing.*;
import java.awt.Color;

import static javax.swing.GroupLayout.*;
import static javax.swing.GroupLayout.Alignment.*;

public class OtherOpsView extends JFrame {
    private JButton otherOpsButton;
    private JPanel panel;
    private JButton templatesButton;

    public OtherOpsView(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Otras Operaciones");
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();

        templatesButton.addActionListener(e -> ControllerView.connectTemplateView());
    }

    private void initComponents(){
        panel = new JPanel();
        otherOpsButton = new JButton();
        templatesButton = new JButton();

        otherOpsButton.setBackground(new Color(153, 0, 153));
        otherOpsButton.setForeground(new Color(255, 255, 255));
        otherOpsButton.setText("Otras operaciones");

        templatesButton.setBackground(new Color(153, 0, 153));
        templatesButton.setForeground(new Color(255, 255, 255));
        templatesButton.setText("Plantillas");

        GroupLayout panelLayout = new GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(LEADING)
                        .addGroup(TRAILING, panelLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(templatesButton, PREFERRED_SIZE, 133, PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                                .addComponent(otherOpsButton, PREFERRED_SIZE, 122, PREFERRED_SIZE)
                                .addGap(36, 36, 36))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(panelLayout.createParallelGroup(BASELINE)
                                        .addComponent(otherOpsButton, PREFERRED_SIZE, 41, PREFERRED_SIZE)
                                        .addComponent(templatesButton, PREFERRED_SIZE, 41, PREFERRED_SIZE))
                                .addContainerGap(105, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addComponent(panel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addComponent(panel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
}
