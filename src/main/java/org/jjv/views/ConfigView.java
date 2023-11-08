package org.jjv.views;

import org.jjv.instances.ConfigInstance;
import org.jjv.persistence.DDBBVerifier;
import org.jjv.utils.Config;
import org.jjv.utils.ConfigModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class ConfigView extends JFrame {
    private JLabel configText;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPasswordField passField;
    private JLabel passLabel;
    private JTextField urlField;
    private JLabel urlLabel;
    private JTextField userField;
    private JLabel userLabel;
    private JButton verifyButton;

    public ConfigView() {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Requermientos");
        initComponents();
        verifyButton.addActionListener(e -> {
            prepareConfigFile(urlField.getText(), userField.getText(), Arrays.toString(passField.getPassword()));
            checkConnection();
        });
    }

    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        configText = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        urlLabel = new javax.swing.JLabel();
        urlField = new javax.swing.JTextField();
        userLabel = new javax.swing.JLabel();
        userField = new javax.swing.JTextField();
        passLabel = new javax.swing.JLabel();
        passField = new javax.swing.JPasswordField();
        verifyButton = new javax.swing.JButton();

        configText.setText("Ingresa las credenciales para acceder al aplicativo");

        /*TOP Panel*/
        GroupLayout topLayout = new GroupLayout(jPanel1);
        jPanel1.setLayout(topLayout);
        topLayout.setHorizontalGroup(
                topLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(topLayout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(configText)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
        );
        topLayout.setVerticalGroup(
                topLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, topLayout.createSequentialGroup()
                                .addContainerGap(15, Short.MAX_VALUE)
                                .addComponent(configText)
                                .addContainerGap())
        );

        /*CENTER Panel*/
        urlLabel.setText("DDBB URL: ");
        userLabel.setText("USERNAME:");
        passLabel.setText("PASSWORD:");

        verifyButton.setBackground(new Color(153, 0, 153));
        verifyButton.setForeground(new Color(255, 255, 255));
        verifyButton.setText("Verificar");

        GroupLayout centerPanel = new GroupLayout(jPanel2);
        jPanel2.setLayout(centerPanel);
        centerPanel.setHorizontalGroup(
                   centerPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(centerPanel.createSequentialGroup()
                                .addContainerGap(26, Short.MAX_VALUE)
                                .addGroup(centerPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, centerPanel.createSequentialGroup()
                                                .addGroup(centerPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(urlLabel)
                                                        .addComponent(userLabel)
                                                        .addComponent(passLabel))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(centerPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(urlField, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(centerPanel.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(passField, GroupLayout.Alignment.LEADING)
                                                                .addComponent(userField, GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addContainerGap())
                                        .addGroup(GroupLayout.Alignment.TRAILING, centerPanel.createSequentialGroup()
                                                .addComponent(verifyButton, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
                                                .addGap(139, 139, 139))))
        );
        centerPanel.setVerticalGroup(
                centerPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(centerPanel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(centerPanel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(urlLabel)
                                        .addComponent(urlField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(centerPanel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(userLabel)
                                        .addComponent(userField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(centerPanel.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(passLabel)
                                        .addComponent(passField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(verifyButton, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                                .addContainerGap())
        );

        GroupLayout centerLayout = new GroupLayout(getContentPane());
        getContentPane().setLayout(centerLayout);
        centerLayout.setHorizontalGroup(
                centerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        centerLayout.setVerticalGroup(
                centerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(centerLayout.createSequentialGroup()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void prepareConfigFile(String url, String user, String pass){
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
            DDBBVerifier.checkConnection();
        } catch (Exception e) {
            System.out.println("Database verification error");
            System.out.println(e.getMessage());
        }
    }

}
