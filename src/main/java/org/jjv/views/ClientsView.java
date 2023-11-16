package org.jjv.views;

import org.jjv.models.Client;
import org.jjv.persistence.ClientRepository;
import org.jjv.persistence.Repository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Short.*;
import static javax.swing.GroupLayout.*;
import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.LayoutStyle.ComponentPlacement.*;

public class ClientsView extends JFrame {
    private Repository<Client>clientRepository;
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
        refreshClients();
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
                    "Error al obtener los clientes del servicio remoto de datos",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
