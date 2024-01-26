package org.jjv.views;

import org.jjv.instances.EntityInstance;
import org.jjv.instances.OperatorInstance;
import org.jjv.models.ClientEntity;
import org.jjv.models.ExtendedProviderEntity;
import org.jjv.utils.DocumentNature;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import static java.lang.Short.*;
import static javax.swing.GroupLayout.*;
import static javax.swing.GroupLayout.Alignment.*;

public class OperatorCreationView extends JFrame {
    private JLabel label;
    private JPanel mainPanel;
    private JButton saveButton;
    private JTable table;
    private JPanel topPanel;
    private JLabel typeOperatorLabel;
    private String type;

    public OperatorCreationView(DocumentNature nature){
        type = nature.equals(DocumentNature.EMITTED) ? "CLIENTES" : "PROVEEDORES";
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Procesar Terceros");
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();
        setSchema(nature);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                    Point point = e.getPoint();
                    int row = table.rowAtPoint(point);
                    String rfc = table.getValueAt(row, 1).toString();
                    if (nature.equals(DocumentNature.EMITTED)){
                        updateClientOperator(rfc);
                    } else {
                        updateProviderOperator(rfc);
                    }
                }
            }
        });
    }

    private void initComponents(){
        topPanel = new JPanel();
        label = new JLabel();
        saveButton = new JButton();
        typeOperatorLabel = new JLabel();
        mainPanel = new JPanel();
        table = new JTable();
        JScrollPane scrollPanel = new JScrollPane(table);

        label.setText("NOMBRE COMPLETO DEL CLIENTE");

        saveButton.setBackground(new Color(153, 0, 153));
        saveButton.setForeground(new Color(255, 255, 255));
        saveButton.setText("Guardar");

        typeOperatorLabel.setText("TERCEROS DEL TIPO: " + type);

        GroupLayout topPanelLayout = new GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
                topPanelLayout.createParallelGroup(LEADING)
                        .addGroup(topPanelLayout.createSequentialGroup()
                                .addGroup(topPanelLayout.createParallelGroup(LEADING)
                                        .addGroup(topPanelLayout.createSequentialGroup()
                                                .addGap(391, 391, 391)
                                                .addComponent(saveButton, PREFERRED_SIZE, 98, PREFERRED_SIZE))
                                        .addGroup(topPanelLayout.createSequentialGroup()
                                                .addGap(357, 357, 357)
                                                .addGroup(topPanelLayout.createParallelGroup(LEADING)
                                                        .addGroup(topPanelLayout.createSequentialGroup()
                                                                .addGap(10, 10, 10)
                                                                .addComponent(typeOperatorLabel))
                                                        .addComponent(label))))
                                .addContainerGap(369, MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
                topPanelLayout.createParallelGroup(LEADING)
                        .addGroup(topPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(typeOperatorLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(saveButton, DEFAULT_SIZE, 38, MAX_VALUE)
                                .addContainerGap())
        );
        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(LEADING)
                        .addComponent(scrollPanel, TRAILING)
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPanel, PREFERRED_SIZE, 288, PREFERRED_SIZE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addComponent(topPanel, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                        .addComponent(mainPanel, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(topPanel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mainPanel, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE))
        );
        pack();
    }

    private void setSchema(DocumentNature mode){
        if (mode.equals(DocumentNature.EMITTED)){
            List<ClientEntity> clientEntities = EntityInstance.getClientEntities();
            DefaultTableModel model = clientModel();
            for (ClientEntity clientEntity : clientEntities){
                model.addRow(setClientEntityData(clientEntity));
            }
            table.setModel(model);
        } else if (mode.equals(DocumentNature.RECEIVED)) {
            List<ExtendedProviderEntity> providerEntities = EntityInstance.getExtendedProviderEntityList();
            DefaultTableModel model = providerModel();
            for (ExtendedProviderEntity providerEntity : providerEntities){
                model.addRow(setProviderEntityData(providerEntity));
            }
            table.setModel(model);
        }
    }

    private static DefaultTableModel clientModel(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("NOMBRE");
        model.addColumn("RFC");
        model.addColumn("TIPO PERSONA");
        model.addColumn("REGIMEN FISCAL");
        model.addColumn("ES PROVEEDOR");
        model.addColumn("ES CLIENTE");
        model.addColumn("CUENTA CLIENTE");
        model.addColumn("CUENTA DE INGRESO");
        model.addColumn("TIPO POLIZA");
        model.addColumn("DESCRIPCION");
        model.addColumn("ES CONTRATANTE");

        return model;
    }

    private static DefaultTableModel providerModel(){
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("NOMBRE");
        model.addColumn("RFC");
        model.addColumn("TIPO PERSONA");
        model.addColumn("REGIMEN FISCAL");
        model.addColumn("TIPO");
        model.addColumn("ES PROVEEDOR");
        model.addColumn("CUENTA PROVEEDOR");
        model.addColumn("CUENTA GASTO");
        model.addColumn("TIPO POLIZA");
        model.addColumn("DESCRIPCION");
        model.addColumn("PAIS DE RESIDENCIA");
        model.addColumn("OPERACION PRED");
        model.addColumn("CLAVE IMPUESTO");
        model.addColumn("ES CLIENTE");
        model.addColumn("ES CONTRATANTE");

        return model;
    }

    private static Object[] setClientEntityData(ClientEntity clientEntity){
        return new Object[]{
                clientEntity.getName(),
                clientEntity.getRfc(),
                clientEntity.getNature(),
                clientEntity.getRegime(),
                clientEntity.isProvider(),
                clientEntity.isClient(),
                clientEntity.getClientAccount(),
                clientEntity.getIncomeAccount(),
                clientEntity.getPolicyType(),
                clientEntity.getDescription(),
                clientEntity.isContractor()
        };
    }

    private static Object[] setProviderEntityData(ExtendedProviderEntity providerEntity){
        return new Object[]{
                providerEntity.getName(),
                providerEntity.getRfc(),
                providerEntity.getNature(),
                providerEntity.getRegime(),
                providerEntity.getNationality(),
                providerEntity.isProvider(),
                providerEntity.getProviderAccount(),
                providerEntity.getExpenseAccount(),
                providerEntity.getPolicyType(),
                providerEntity.getDescription(),
                providerEntity.getCountry(),
                providerEntity.getDIOTOperation(),
                providerEntity.getTaxKey(),
                providerEntity.isClient(),
                providerEntity.isContractor()
        };
    }

    private void updateClientOperator(String rfc){
        JTextField nameField = new JTextField();
        JTextField regimeField = new JTextField();
        JTextField incomeField = new JTextField();

        List<ClientEntity> clientEntities = EntityInstance.getClientEntities();
        ClientEntity clientEntity = clientEntities.stream()
                .filter(cl -> cl.getRfc().equals(rfc))
                .findFirst()
                .orElse(null);

        int index;

        if (clientEntity != null){
            nameField.setText(clientEntity.getName());
            regimeField.setText(clientEntity.getRegime());
            incomeField.setText(clientEntity.getIncomeAccount());

            index = clientEntities.indexOf(clientEntity);

            Object[] fields = {
                    "Nombre del Tercero", nameField,
                    "Regimen Fiscal", regimeField,
                    "Cuenta de ventas", incomeField
            };

            int confirm = JOptionPane.showConfirmDialog(
                    this, fields, "Modificar Tercero", JOptionPane.OK_CANCEL_OPTION
            );

            if (confirm == JOptionPane.OK_OPTION){
                clientEntity.setName(nameField.getText());
                clientEntity.setRegime(regimeField.getText());
                clientEntity.setClientAccount(incomeField.getText());

                setSchema(DocumentNature.EMITTED);
            }
        }
    }

    private void updateProviderOperator(String rfc){
        JTextField nameField = new JTextField();
        JTextField regimeField = new JTextField();
        JTextField expenseField = new JTextField();

        List<ExtendedProviderEntity> providerEntities = EntityInstance.getExtendedProviderEntityList();
        ExtendedProviderEntity providerEntity = providerEntities.stream()
                .filter(cl -> cl.getRfc().equals(rfc))
                .findFirst()
                .orElse(null);

        int index;

        if (providerEntity != null){
            nameField.setText(providerEntity.getName());
            regimeField.setText(providerEntity.getRegime());
            expenseField.setText(providerEntity.getExpenseAccount());

            index = providerEntities.indexOf(providerEntity);

            Object[] fields = {
                    "Nombre del Tercero", nameField,
                    "Regimen Fiscal", regimeField,
                    "Cuenta de gastos", expenseField
            };

            int confirm = JOptionPane.showConfirmDialog(
                    this, fields, "Modificar Tercero", JOptionPane.OK_CANCEL_OPTION
            );

            if (confirm == JOptionPane.OK_OPTION){
                providerEntity.setName(nameField.getText());
                providerEntity.setRegime(regimeField.getText());
                providerEntity.setExpenseAccount(expenseField.getText());

                setSchema(DocumentNature.RECEIVED);
            }
        }
    }
}
