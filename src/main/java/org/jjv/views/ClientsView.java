package org.jjv.views;

import org.apache.poi.ss.usermodel.Sheet;
import org.jjv.instances.ClientConfigInstance;
import org.jjv.instances.ClientInstance;
import org.jjv.instances.WorkBookInstance;
import org.jjv.models.Client;
import org.jjv.models.ClientConfig;
import org.jjv.operations.ExtractOperation;
import org.jjv.persistence.ClientConfigRepository;
import org.jjv.persistence.ClientRepository;
import org.jjv.persistence.Repository;
import org.jjv.readers.ClientReader;
import org.jjv.readers.Reader;
import org.jjv.utils.DefaultValues;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Short.*;
import static javax.swing.GroupLayout.*;
import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.LayoutStyle.ComponentPlacement.*;

public class ClientsView extends JFrame {
    private Repository<Client> clientRepository;
    private Repository<ClientConfig> clientConfigRepository;
    private JButton batchLoadButton;
    private JTextField clientSelectedField;
    private JPanel clientsPanel;
    private JButton confirmClientButton;
    private JButton newClientButton;
    private JPanel optionsPanel;
    private JPanel selectionPanel;
    private JTable clientsTable;
    public ClientsView(){
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Selecciona un cliente");
        setResizable(false);
        setSize(470, 500);
        clientRepository = new ClientRepository();
        clientConfigRepository = new ClientConfigRepository();
        refreshClients();
        loadClientConfigList();

        clientsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String id = clientsTable.getValueAt(clientsTable.getSelectedRow(), 0).toString();
                Client client = new Client(
                        Integer.parseInt(id),
                        clientsTable.getValueAt(clientsTable.getSelectedRow(), 1).toString(),
                        DefaultValues.ORGANIZATION_NUMBER
                );
                ClientInstance.create(client);
                clientSelectedField.setText(client.name());
            }
        });
        newClientButton.addActionListener(e -> addClient());
        batchLoadButton.addActionListener(e -> addClients());
        confirmClientButton.addActionListener(e -> {
            if (!clientSelectedField.getText().isEmpty()){
                ControllerView.connectClientOptionsView();
                dispose();
            }
        });
    }

    private void initComponents(){
        selectionPanel = new JPanel();
        clientSelectedField = new JTextField();
        confirmClientButton = new JButton();
        optionsPanel = new JPanel();
        batchLoadButton = new JButton();
        newClientButton = new JButton();
        clientsPanel = new JPanel();
        clientsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(clientsTable);

        clientSelectedField.setEnabled(false);

        confirmClientButton.setBackground(new Color(153, 0, 153));
        confirmClientButton.setForeground(new Color(255, 255, 255));
        confirmClientButton.setText("Confirmar");

        GroupLayout selectionPanelLayout = new GroupLayout(selectionPanel);
        selectionPanel.setLayout(selectionPanelLayout);
        selectionPanelLayout.setHorizontalGroup(
                selectionPanelLayout.createParallelGroup(LEADING)
                        .addGroup(selectionPanelLayout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(clientSelectedField, PREFERRED_SIZE, 186, PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(confirmClientButton)
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE))
        );
        selectionPanelLayout.setVerticalGroup(
                selectionPanelLayout.createParallelGroup(LEADING)
                        .addGroup(selectionPanelLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(selectionPanelLayout.createParallelGroup(BASELINE)
                                        .addComponent(clientSelectedField, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(confirmClientButton))
                                .addContainerGap(35, MAX_VALUE))
        );

        batchLoadButton.setBackground(new Color(153, 0, 153));
        batchLoadButton.setForeground(new Color(255, 255, 255));
        batchLoadButton.setText("Carga Masiva");

        newClientButton.setBackground(new Color(153, 0, 153));
        newClientButton.setForeground(new Color(255, 255, 255));
        newClientButton.setText("+ Client");

        GroupLayout optionsPanelLayout = new GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
                optionsPanelLayout.createParallelGroup(LEADING)
                        .addGroup(TRAILING, optionsPanelLayout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(batchLoadButton)
                                .addGap(88, 88, 88)
                                .addComponent(newClientButton, PREFERRED_SIZE, 97, PREFERRED_SIZE)
                                .addContainerGap(90, MAX_VALUE))
        );
        optionsPanelLayout.setVerticalGroup(
                optionsPanelLayout.createParallelGroup(LEADING)
                        .addGroup(TRAILING, optionsPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(optionsPanelLayout.createParallelGroup(LEADING)
                                        .addComponent(batchLoadButton, DEFAULT_SIZE, 44, MAX_VALUE)
                                        .addComponent(newClientButton, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE))
                                .addGap(21, 21, 21))
        );

        scrollPane.setViewportView(clientsTable);
        GroupLayout clientsPanelLayout = new GroupLayout(clientsPanel);
        clientsPanel.setLayout(clientsPanelLayout);
        clientsPanelLayout.setHorizontalGroup(
                clientsPanelLayout.createParallelGroup(LEADING)
                        .addGroup(TRAILING, clientsPanelLayout.createSequentialGroup()
                                .addContainerGap(DEFAULT_SIZE, MAX_VALUE)
                                .addComponent(scrollPane, PREFERRED_SIZE, 426, PREFERRED_SIZE)
                                .addContainerGap())
        );
        clientsPanelLayout.setVerticalGroup(
                clientsPanelLayout.createParallelGroup(LEADING)
                        .addGroup(clientsPanelLayout.createSequentialGroup()
                                .addComponent(scrollPane, PREFERRED_SIZE, 226, PREFERRED_SIZE)
                                .addGap(0, 270, MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addComponent(optionsPanel, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                        .addComponent(selectionPanel, TRAILING, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
                        .addComponent(clientsPanel, TRAILING, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(selectionPanel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addPreferredGap(RELATED)
                                .addComponent(optionsPanel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addPreferredGap(RELATED)
                                .addComponent(clientsPanel, DEFAULT_SIZE, DEFAULT_SIZE, MAX_VALUE))
        );
        pack();
    }

    private void refreshClients(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("CLIENTE");

        try {
            List<Client> clients = clientRepository.findAll();
            for (Client client : clients){
                Object[] data = {
                    client.id(),
                    client.name()
                };
                model.addRow(data);
            }
            clientsTable.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al obtener los clientes del servicio remoto de datos: " + e.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE
            );
            this.dispose();
        }
    }

    private void addClient(){
        JTextField nameField = new JTextField();
        Object[] fields = {
                "Nombre", nameField
        };

        int res = JOptionPane.showConfirmDialog(
                this,
                fields,
                "Agregar Cliente",
                JOptionPane.OK_CANCEL_OPTION
        );

        if (res == JOptionPane.OK_OPTION){
            Client client = new Client(
                    nameField.getText(),
                    DefaultValues.ORGANIZATION_NUMBER
            );
            try {
                clientRepository.save(client);
                refreshClients();
                JOptionPane.showMessageDialog(this,
                        "Cliente agregado correctamente",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this,
                        "Un Error inesperado causo la interrupción del registro del cliente",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addClients(){
        boolean pathIsSelected = FileDialog.showFileDialog(this, "Carga masiva de clientes");
        if (pathIsSelected){
            try {
                List<Client> clients = ExtractOperation.extractClients();
                clientRepository.saveAll(clients);
                JOptionPane.showMessageDialog(this,
                        clients.size() + " clientes se agregaron correctamente",
                        "Carga exitosa",
                        JOptionPane.INFORMATION_MESSAGE);
                refreshClients();
            } catch (IOException | SQLException e) {
                JOptionPane.showMessageDialog(this,
                        "Ocurrio un error inesperado: " +  e.getMessage(),
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadClientConfigList(){
        try {
            List<ClientConfig> configList = clientConfigRepository.findAllById(DefaultValues.ORGANIZATION_NUMBER);
            ClientConfigInstance.create(configList);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Un error inesperado inhabilito la carga de configuracion de clientes: " +  e.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
