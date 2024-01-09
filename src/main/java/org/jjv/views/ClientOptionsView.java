package org.jjv.views;

import org.jjv.instances.ClientConfigInstance;
import org.jjv.instances.ClientInstance;
import org.jjv.instances.OperatorInstance;
import org.jjv.instances.PathInstance;
import org.jjv.models.ClientConfig;
import org.jjv.models.Operator;
import org.jjv.operations.ExtractOperation;
import org.jjv.persistence.ClientConfigRepository;
import org.jjv.persistence.OperatorRepository;
import org.jjv.persistence.Repository;
import org.jjv.utils.DefaultValues;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;

public class ClientOptionsView extends JFrame {
    private Repository<Operator> operatorRepository;
    private Repository<ClientConfig> clientConfigRepository;
    Integer clientId = ClientInstance.getSingle().id();
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

        operatorRepository = new OperatorRepository();
        clientConfigRepository = new ClientConfigRepository();
        clientField.setText(ClientInstance.getSingle().name());

        loadOperators();

        batchOperatorsButton.addActionListener(e -> addOperators());
        clientConfigurationButton.addActionListener(e -> setConfiguration());
        addClientAccountsButton.addActionListener(e -> setConfigOperation());
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

    private void addOperators(){
        boolean pathIsSelected = FileDialog.showFileDialog(this, "Selecciona archivo de terceros");
        if (pathIsSelected){
            try {
                List<Operator> operators = ExtractOperation.extractOperators();
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Se detectaron " + operators.size() + " terceros ¿Desea registrarlos?",
                        "Terceros", JOptionPane.OK_CANCEL_OPTION
                );

                if (confirm == JOptionPane.OK_OPTION){
                    operatorRepository.saveAll(operators);
                    JOptionPane.showMessageDialog(this,
                            "Terceros registrados Exitosamente",
                            "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException | SQLException e) {
                JOptionPane.showMessageDialog(this,
                        "Ocurrio un error al intentar el registro: " + e.getMessage(),
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void setConfiguration(){
        Optional<ClientConfig> clientConfig = ClientConfigInstance.getSingle(clientId);
        boolean isAnUpdate = clientConfig.isPresent();
        Integer id = null;
        JTextField sellerMainAccountField = new JTextField();
        JTextField sellerSATIdentifierField = new JTextField();
        JTextField buyerMainAccountField = new JTextField();
        JTextField buyerSATIdentifierField = new JTextField();
        JTextField expenseMainAccountField = new JTextField();
        JTextField minimumAmountField = new JTextField();

        if (isAnUpdate){
            ClientConfig config = clientConfig.get();
            sellerMainAccountField.setText(config.sellerMainAccount());
            sellerSATIdentifierField.setText(config.sellerSATIdentifier());
            buyerMainAccountField.setText(config.buyerMainAccount());
            buyerSATIdentifierField.setText(config.buyerSATIdentifier());
            expenseMainAccountField.setText(config.expenseMainAccount());
            minimumAmountField.setText(config.minimumAmountToApply().toString());
            id = config.id();
        }

        Object[] fields = {
                "Cuenta padre Proveedor", sellerMainAccountField,
                "Codigo SAT", sellerSATIdentifierField,
                "Cuenta principal ventas", buyerMainAccountField,
                "Codigo SAT", buyerSATIdentifierField,
                "Cuenta pricipal gastos", expenseMainAccountField,
                "Aplicar gastos a monto mayor a:", minimumAmountField,
        };

        int confirm = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Crear Configuracion",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (confirm == JOptionPane.OK_OPTION){

            ClientConfig config = createClientConfig(
                    sellerMainAccountField.getText(),
                    sellerSATIdentifierField.getText(),
                    buyerMainAccountField.getText(),
                    buyerSATIdentifierField.getText(),
                    expenseMainAccountField.getText(),
                    minimumAmountField.getText(),
                    isAnUpdate,
                    id
            );
            try {
                clientConfigRepository.save(config);
                JOptionPane.showMessageDialog(this,
                        "Configuracion registrada Exitosamente",
                        "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                updateClientConfigInstance();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this,
                        "Ocurrio un error al intentar el registro: " + e.getMessage(),
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    private ClientConfig createClientConfig(
            String sellerMainAccountField, String sellerSATIdentifierField,
            String buyerMainAccountField, String buyerSATIdentifierField,
            String expenseMainAccountField, String minimumAmountField,
            boolean isAnUpdate, Integer id){

        if (isAnUpdate){
            return new ClientConfig(
                    id,
                    clientId,
                    DefaultValues.ORGANIZATION_NUMBER,
                    sellerMainAccountField,
                    sellerSATIdentifierField,
                    buyerMainAccountField,
                    buyerSATIdentifierField,
                    expenseMainAccountField,
                    Double.parseDouble(minimumAmountField)
            );
        } else {
            return new ClientConfig(
                    clientId,
                    DefaultValues.ORGANIZATION_NUMBER,
                    sellerMainAccountField,
                    sellerSATIdentifierField,
                    buyerMainAccountField,
                    buyerSATIdentifierField,
                    expenseMainAccountField,
                    Double.parseDouble(minimumAmountField)
            );
        }
    }
    private void updateClientConfigInstance(){
        try {
            List<ClientConfig> configList =
                    clientConfigRepository.findAllById(DefaultValues.ORGANIZATION_NUMBER);
            ClientConfigInstance.create(configList);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Ocurrio un error al actualizar la lista general de configuraciones, reinicie el aplicativo: " + e.getMessage(),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void setConfigOperation(){
        boolean pathIsSelected = FileDialog.showFileDialog(this, "Selecciona Archivo de CFDIs");
        if (pathIsSelected){
            ControllerView.connectPreAccountGenView();
            this.dispose();
        }
    }

    private void loadOperators(){
        try {
            List<Operator> operators = operatorRepository.findAllById(ClientInstance.getSingle().id());
            Set<String> operatorRFC = new HashSet<>();
            operators.forEach(operator -> operatorRFC.add(operator.rfc()));
            OperatorInstance.create(operatorRFC);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Ocurrio un error al intentar cargar terceros: " + e.getMessage(),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
