package org.jjv.views;

import org.jjv.generators.TxtGenerator;
import org.jjv.instances.AccountInstance;
import org.jjv.instances.PathInstance;
import org.jjv.models.Account;
import org.jjv.utils.TaxRate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;

import static javax.swing.GroupLayout.*;
import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.LayoutStyle.ComponentPlacement.*;

public class AccountCreationView extends JFrame {
    private JPanel bottomPanel;
    private JButton createTXTButton;
    private JTextField infoField;
    private JLabel clientNameLabel;
    private JButton operatorModelButton;
    private JPanel topPanel;
    private JTable accountsTable;

    public AccountCreationView(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Crear Cuentas");
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();
        setAccountSchema();
        loadInfo();

        accountsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                    Point point = e.getPoint();
                    int row = accountsTable.rowAtPoint(point);
                    String rfc = accountsTable.getValueAt(row, 4).toString();
                    updateAccount(rfc);
                }
            }
        });
        createTXTButton.addActionListener(e -> generateTXT());
        operatorModelButton.addActionListener(e -> {
            ControllerView.connectEntityProcessorView();
            this.dispose();
        });
    }

    private void initComponents(){
        topPanel = new JPanel();
        clientNameLabel = new JLabel();
        createTXTButton = new JButton();
        operatorModelButton = new JButton();
        infoField = new JTextField();
        bottomPanel = new JPanel();
        accountsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(accountsTable);

        clientNameLabel.setText("CLIENT NAME");

        createTXTButton.setBackground(new java.awt.Color(153, 0, 153));
        createTXTButton.setForeground(new java.awt.Color(255, 255, 255));
        createTXTButton.setText("Descargar TXT");

        operatorModelButton.setBackground(new java.awt.Color(153, 0, 153));
        operatorModelButton.setForeground(new java.awt.Color(255, 255, 255));
        operatorModelButton.setText("Generar Modelos");
        
        GroupLayout topPanelLayout = new GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
                topPanelLayout.createParallelGroup(LEADING)
                        .addGroup(TRAILING, topPanelLayout.createSequentialGroup()
                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clientNameLabel, PREFERRED_SIZE, 223, PREFERRED_SIZE)
                                .addGap(137, 137, 137))
                        .addGroup(topPanelLayout.createSequentialGroup()
                                .addGroup(topPanelLayout.createParallelGroup(LEADING)
                                        .addGroup(topPanelLayout.createSequentialGroup()
                                                .addGap(112, 112, 112)
                                                .addComponent(createTXTButton, PREFERRED_SIZE, 118, PREFERRED_SIZE)
                                                .addGap(41, 41, 41)
                                                .addComponent(operatorModelButton, PREFERRED_SIZE, 118, PREFERRED_SIZE))
                                        .addGroup(topPanelLayout.createSequentialGroup()
                                                .addGap(73, 73, 73)
                                                .addComponent(infoField, PREFERRED_SIZE, 350, PREFERRED_SIZE)))
                                .addContainerGap(83, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
                topPanelLayout.createParallelGroup(LEADING)
                        .addGroup(topPanelLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(clientNameLabel)
                                .addGap(18, 18, 18)
                                .addGroup(topPanelLayout.createParallelGroup(BASELINE)
                                        .addComponent(createTXTButton, PREFERRED_SIZE, 40, PREFERRED_SIZE)
                                        .addComponent(operatorModelButton, PREFERRED_SIZE, 40, PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(infoField, DEFAULT_SIZE, 59, Short.MAX_VALUE)
                                .addContainerGap())
        );

        GroupLayout bottomPanelLayout = new GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
                bottomPanelLayout.createParallelGroup(LEADING)
                        .addComponent(scrollPane, DEFAULT_SIZE, 506, Short.MAX_VALUE)
        );
        bottomPanelLayout.setVerticalGroup(
                bottomPanelLayout.createParallelGroup(LEADING)
                        .addGroup(bottomPanelLayout.createSequentialGroup()
                                .addComponent(scrollPane, PREFERRED_SIZE, 240, PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(LEADING)
                        .addComponent(topPanel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bottomPanel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(topPanel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addPreferredGap(RELATED)
                                .addComponent(bottomPanel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        pack();
    }

    private void setAccountSchema(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("CUENTA MADRE");
        model.addColumn("IDENTIFICADOR");
        model.addColumn("SUBCUENTA");
        model.addColumn("NOMBRE");
        model.addColumn("RFC");
        model.addColumn("REGIMEN");
        model.addColumn("TIPO");
        model.addColumn("NATURALEZA");
        model.addColumn("SAT");
        model.addColumn("IVA CERO/EXCENTO");

        List<Account> accounts = AccountInstance.get();
        for (Account account : accounts){
            Object[] data = {
                account.mainAccount(),
                account.identifier(),
                account.subAccount(),
                account.name(),
                account.rfc(),
                account.regime(),
                account.accountType(),
                account.accountNature(),
                account.satCode(),
                account.taxRate()
            };
            model.addRow(data);
        }
        accountsTable.setModel(model);
    }

    private void updateAccount(String rfc){
        List<Account> accounts = AccountInstance.get();
        Account account = accounts.stream().filter(a -> a.rfc().equals(rfc)).findFirst().orElse(null);
        int index;

        if (account != null){
            JTextField mainAccount = new JTextField(account.mainAccount());
            JTextField identifier = new JTextField(Integer.toString(account.identifier()));
            JTextField subAccount = new JTextField(account.subAccount());
            JTextField satCode = new JTextField(account.satCode());
            JTextField name = new JTextField(account.name());
            index = accounts.indexOf(account);
            Object[] fields = {
                    "Cuenta Padre", mainAccount,
                    "Identificador", identifier,
                    "Subcuenta", subAccount,
                    "Cuenta SAT", satCode,
                    "Nombre", name
            };

            int confirm = JOptionPane.showConfirmDialog(
                    this, fields, "Modificar Cuenta", JOptionPane.OK_CANCEL_OPTION
            );
            if (confirm == JOptionPane.OK_OPTION){
                Account updatedAccount = new Account(
                        account.nature(),
                        mainAccount.getText(),
                        Integer.parseInt(identifier.getText()),
                        subAccount.getText(),
                        name.getText(),
                        account.rfc(),
                        account.regime(),
                        account.taxRate(),
                        account.accountType(),
                        account.accountNature(),
                        satCode.getText(),
                        account.total()
                );
                accounts.set(index, updatedAccount);
                setAccountSchema();
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Algun error inesperado ocaciono la perdida de informacion al seleccionar la cuenta",
                    "Cuenta no encontrada",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void loadInfo(){
        List<Account> accounts = AccountInstance.get();
        int taxZero = (int) accounts
                .stream()
                .filter(account -> account.taxRate().equals(TaxRate.TAX_0.toString()))
                .count();

        if (taxZero > 0){
            infoField.setText(
                    "- Se detectaron " + taxZero + " cuentas cuyas facturas estan excentas de IVA."
            );
        } else {
            infoField.setText(
                    "- Se cargaron " + accounts.size() + " cuentas."
            );
        }
    }

    private void generateTXT(){
        boolean selectedPath = FileDialog.showFileDialog(this, "Selecciona ubicación del archivo a guardar");
        if (selectedPath){
            String path = PathInstance.getPath();
            String outputPath = path.concat(".txt");
            PathInstance.create(outputPath);
            try {
                TxtGenerator.generateAccountFile();
                JOptionPane.showMessageDialog(this,
                        "Archivo de cuentas generado exitosamente",
                        "Archivo de cuentas generado",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Algun error inesperado provoco que no fuse posible generar el .txt",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
