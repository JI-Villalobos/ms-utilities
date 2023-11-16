package org.jjv.views;

import org.jjv.persistence.DDBBVerifier;
import org.jjv.utils.Config;
import org.jjv.utils.ConfigModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ConfigView extends JFrame {
    private JPanel centerPanel;
    private JLabel configText;
    private JTextField dbField;
    private JLabel dbLabel;
    private JTextField domainField;
    private JLabel domainLabel;
    private JPasswordField passField;
    private JLabel passLabel;
    private JPanel topPanel;
    private JTextField userField;
    private JLabel userLabel;
    private JButton verifyButton;

    public ConfigView() {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Requerimientos");
        setResizable(false);
        initComponents();
        verifyButton.addActionListener(e -> {
            prepareConfigFile(domainField.getText(), dbField.getText(), userField.getText(), buildPass(passField.getPassword()));
            checkConnection();
        });
    }

    private void initComponents() {
        topPanel = new JPanel();
        configText = new JLabel();
        centerPanel = new JPanel();
        domainLabel = new JLabel();
        domainField = new JTextField();
        userLabel = new JLabel();
        userField = new JTextField();
        passLabel = new JLabel();
        passField = new JPasswordField();
        verifyButton = new JButton();
        dbLabel = new JLabel();
        dbField = new JTextField();

        setPreferredSize(new Dimension(400, 300));

        configText.setText("Ingresa las credenciales para acceder al aplicativo");

        GroupLayout topPanelLayout = new GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
                topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(topPanelLayout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(configText)
                                .addContainerGap(87, Short.MAX_VALUE))
        );
        topPanelLayout.setVerticalGroup(
                topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                                .addContainerGap(15, Short.MAX_VALUE)
                                .addComponent(configText)
                                .addContainerGap())
        );

        domainLabel.setText("DOMAIN: ");
        
        userLabel.setText("USERNAME:");

        passLabel.setText("PASSWORD:");

        verifyButton.setBackground(new java.awt.Color(153, 0, 153));
        verifyButton.setForeground(new java.awt.Color(255, 255, 255));
        verifyButton.setText("Verificar");

        dbLabel.setText("DB NAME: ");

        GroupLayout centerPanelLayout = new GroupLayout(centerPanel);
        centerPanel.setLayout(centerPanelLayout);
        centerPanelLayout.setHorizontalGroup(
                centerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(centerPanelLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(verifyButton, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                                .addGap(143, 143, 143))
                        .addGroup(centerPanelLayout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addGroup(centerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(centerPanelLayout.createSequentialGroup()
                                                .addComponent(domainLabel, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(domainField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(centerPanelLayout.createSequentialGroup()
                                                .addGroup(centerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(userLabel)
                                                        .addComponent(passLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(centerPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(passField, GroupLayout.Alignment.LEADING)
                                                        .addComponent(userField, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(centerPanelLayout.createSequentialGroup()
                                                .addComponent(dbLabel, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(dbField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        centerPanelLayout.setVerticalGroup(
                centerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(centerPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(centerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(domainLabel)
                                        .addComponent(domainField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                .addGroup(centerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(dbLabel)
                                        .addComponent(dbField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(centerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(userLabel)
                                        .addComponent(userField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(centerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(passLabel)
                                        .addComponent(passField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(verifyButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(topPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(topPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(centerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }

    private void prepareConfigFile(String domain, String dbName, String user, String pass){
        String url = ConfigModel.assembleUrl(domain, dbName);
        ConfigModel config = new ConfigModel(url, user, pass);
        try {
            Config.generateConfigFile(config);
        } catch (IOException e) {
            System.out.println("Config creation error");
            System.out.println(e.getMessage());
        }
    }

    private void checkConnection(){
        try {
            Config.getConfigFile();
            DDBBVerifier.checkConnection();
            InitialView initialView = new InitialView();
            initialView.setVisible(true);
            this.dispose();
        } catch (Exception e) {
            System.out.println("Database verification error");
            System.out.println(e.getMessage());
        }
    }

    private String buildPass(char[] pass){
        return String.valueOf(pass);
    }
}
