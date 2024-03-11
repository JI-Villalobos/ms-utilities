package org.jjv.views;

import org.jjv.instances.PathInstance;
import org.jjv.models.Template;
import org.jjv.operations.WriteOperation;
import org.jjv.templates.BasicTemplate;
import org.jjv.templates.DemoTemplates;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static javax.swing.GroupLayout.*;
import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.LayoutStyle.*;

public class TemplateView extends JFrame {
    private JTextField exampleCodeTextField;
    private JComboBox<String> exampleComboBox;
    private JPanel examplePane;
    private JScrollPane exampleScrollPane;
    private JTextArea exampleTextArea;
    private JTextArea basicTextArea;
    private JButton generateExampleButton;
    private JButton generateTemplateButton;
    private JTabbedPane pane;
    private JTextField templateCodeTextField;
    private JComboBox<String> templateComboBox;
    private JScrollPane templateScrollPane;
    private JPanel templatesPane;

    List<Template> basicTemplates = BasicTemplate.getBasicTemplates();
    List<Template> demoTemplates = DemoTemplates.getDemoTemplates();

    public TemplateView(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Plantillas y Ejemplos");
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();

        templateCodeTextField.setText(basicTemplates.get(0).code());
        exampleCodeTextField.setText(demoTemplates.get(0).code());

        basicTextArea.setText(basicTemplates.get(0).description());
        exampleTextArea.setText(demoTemplates.get(0).description());

        templateComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    Object item = e.getItem();
                    Template template = basicTemplates.stream()
                            .filter(t -> t.name().equals(item))
                            .findAny()
                            .orElse(null);
                    assert template != null;

                    basicTextArea.setText(template.description());
                    templateCodeTextField.setText(template.code());
                }
            }
        });
        loadTemplates();
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
        basicTextArea = new JTextArea();
        generateTemplateButton = new JButton();

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

        basicTextArea.setColumns(20);
        basicTextArea.setRows(5);
        templateScrollPane.setViewportView(basicTextArea);

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

    private void loadTemplates(){
        String[] demoTemplateNames = new String[demoTemplates.size()];
        String[] basicTemplateNames = new String[basicTemplates.size()];

        int i = 0;
        int j = 0;

        for (Template template : demoTemplates){
            demoTemplateNames[j] = template.name();
            j++;
        }
        for (Template template: basicTemplates) {
            basicTemplateNames[i] = template.name();
            i++;
        }

        exampleComboBox.setModel(new DefaultComboBoxModel<>(demoTemplateNames));
        templateComboBox.setModel(new DefaultComboBoxModel<>(basicTemplateNames));
        generateExampleButton.addActionListener(e -> {
            if (exampleCodeTextField.getText().equals("E001"))
                generateTemplate();
        });
    }

    private void generateTemplate(){
        boolean selectedPath = FileDialog.showFileDialog(this, "Selecciona ubicación del archivo a guardar");
        if (selectedPath){
            String path = PathInstance.getPath();
            String file = path.concat(".xlsx");
            PathInstance.create(file);

            try {
                WriteOperation.writeClientConfigTemplate();
                JOptionPane.showMessageDialog(
                        this,
                        "Archivo generado exitosamente.",
                        "Terceros", JOptionPane.INFORMATION_MESSAGE
                );
            } catch (IOException e){
                JOptionPane.showMessageDialog(this,
                        "Algun error inesperado provoco que no fuese posible generar el .xlsx",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE
                );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
