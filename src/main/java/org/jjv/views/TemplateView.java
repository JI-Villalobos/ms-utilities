package org.jjv.views;

import javax.swing.*;

import java.awt.Color;

import static javax.swing.GroupLayout.*;
import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.LayoutStyle.*;

public class TemplateView extends JFrame {
    private JTextField exampleCodeTextField;
    private JComboBox<String> exampleComboBox;
    private JPanel examplePane;
    private JScrollPane exampleScrollPane;
    private JTextArea exampleTextArea;
    private JTextArea exampleTextArea1;
    private JButton generateExampleButton;
    private JButton generateTemplateButton;
    private JTabbedPane pane;
    private JTextField templateCodeTextField;
    private JComboBox<String> templateComboBox;
    private JScrollPane templateScrollPane;
    private JPanel templatesPane;

    public TemplateView(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Plantillas y Ejemplos");
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();
    }
    private void initComponents(){
        pane = new JTabbedPane();
        examplePane = new JPanel();
        exampleComboBox = new JComboBox<>();
        exampleCodeTextField = new JTextField();
        generateExampleButton = new JButton();
        exampleScrollPane = new JScrollPane();
        exampleTextArea = new JTextArea();
        templatesPane = new JPanel();
        templateComboBox = new JComboBox<>();
        templateCodeTextField = new JTextField();
        templateScrollPane = new JScrollPane();
        exampleTextArea1 = new JTextArea();
        generateTemplateButton = new JButton();

        exampleComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        exampleCodeTextField.setText("A001");

        generateExampleButton.setBackground(new Color(153, 0, 153));
        generateExampleButton.setForeground(new Color(255, 255, 255));
        generateExampleButton.setText("Generar");

        exampleTextArea.setColumns(20);
        exampleTextArea.setRows(5);
        exampleScrollPane.setViewportView(exampleTextArea);

        GroupLayout examplePaneLayout = new GroupLayout(examplePane);
        examplePane.setLayout(examplePaneLayout);
        examplePaneLayout.setHorizontalGroup(
                examplePaneLayout.createParallelGroup(LEADING)
                        .addGroup(examplePaneLayout.createSequentialGroup()
                                .addGap(138, 138, 138)
                                .addComponent(generateExampleButton)
                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(examplePaneLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(examplePaneLayout.createParallelGroup(LEADING)
                                        .addGroup(examplePaneLayout.createSequentialGroup()
                                                .addComponent(exampleComboBox, PREFERRED_SIZE, 187, PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(exampleCodeTextField, PREFERRED_SIZE, 42, PREFERRED_SIZE)
                                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(examplePaneLayout.createSequentialGroup()
                                                .addComponent(exampleScrollPane, PREFERRED_SIZE, 280, PREFERRED_SIZE)
                                                .addGap(0, 50, Short.MAX_VALUE))))
        );
        examplePaneLayout.setVerticalGroup(
                examplePaneLayout.createParallelGroup(LEADING)
                        .addGroup(examplePaneLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(examplePaneLayout.createParallelGroup(BASELINE)
                                        .addComponent(exampleComboBox, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(exampleCodeTextField, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addComponent(exampleScrollPane, PREFERRED_SIZE, 71, PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addComponent(generateExampleButton)
                                .addContainerGap())
        );

        pane.addTab("Ejemplos", examplePane);

        templateComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        templateCodeTextField.setText("B001");

        exampleTextArea1.setColumns(20);
        exampleTextArea1.setRows(5);
        templateScrollPane.setViewportView(exampleTextArea1);

        generateTemplateButton.setBackground(new Color(153, 0, 153));
        generateTemplateButton.setForeground(new Color(255, 255, 255));
        generateTemplateButton.setText("Generar");

        GroupLayout templatesPaneLayout = new GroupLayout(templatesPane);
        templatesPane.setLayout(templatesPaneLayout);
        templatesPaneLayout.setHorizontalGroup(
                templatesPaneLayout.createParallelGroup(LEADING)
                        .addGroup(templatesPaneLayout.createSequentialGroup()
                                .addGap(138, 138, 138)
                                .addComponent(generateTemplateButton)
                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(templatesPaneLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(templatesPaneLayout.createParallelGroup(LEADING)
                                        .addGroup(templatesPaneLayout.createSequentialGroup()
                                                .addComponent(templateComboBox, PREFERRED_SIZE, 187, PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(templateCodeTextField, PREFERRED_SIZE, 42, PREFERRED_SIZE)
                                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(templatesPaneLayout.createSequentialGroup()
                                                .addComponent(templateScrollPane, PREFERRED_SIZE, 280, PREFERRED_SIZE)
                                                .addGap(0, 50, Short.MAX_VALUE))))
        );
        templatesPaneLayout.setVerticalGroup(
                templatesPaneLayout.createParallelGroup(LEADING)
                        .addGroup(templatesPaneLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(templatesPaneLayout.createParallelGroup(BASELINE)
                                        .addComponent(templateComboBox, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(templateCodeTextField, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addGap(16, 16, 16)
                                .addComponent(templateScrollPane, PREFERRED_SIZE, 71, PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addComponent(generateTemplateButton)
                                .addContainerGap())
        );

        pane.addTab("Plantillas", templatesPane);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addComponent(pane)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addComponent(pane)
        );

        pack();
    }
}
