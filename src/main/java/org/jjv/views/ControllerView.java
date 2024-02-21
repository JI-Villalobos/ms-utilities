package org.jjv.views;

import org.jjv.instances.AccountInstance;
import org.jjv.models.Account;
import org.jjv.operations.DataProcessor;
import org.jjv.operations.EntityProcessor;
import org.jjv.utils.Config;
import org.jjv.utils.DocumentNature;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class ControllerView {
    public static void selectInitialView(){
        if (!Config.checkIfConfigFileExists()){
            ConfigView configView = new ConfigView();
            configView.setVisible(true);
        } else {
            InitialView initialView = new InitialView();
            initialView.setVisible(true);
        }
    }

    public static void connectClientsView(JFrame parent){
        try {
            Config.getConfigFile();
            ClientsView clientsView = new ClientsView();
            clientsView.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    parent,
                    "Error al cargar configuracion de servicios remoto",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE
            );
            System.out.println("error: " + e.getMessage());
        }
    }

    public static void connectClientOptionsView(){
        ClientOptionsView clientOptionsView = new ClientOptionsView();
        clientOptionsView.setVisible(true);
    }

    public static void connectPreAccountGenView(){
        PreAccountGenView preAccountGenView = new PreAccountGenView();
        preAccountGenView.setVisible(true);
    }

    public static void connectAccountCreationView(JFrame parent){
        try {
          DataProcessor.processAccounts();
          //remove after test
            List<Account> accounts = AccountInstance.get();
            accounts.forEach(System.out::println);
          AccountCreationView accountCreationView = new AccountCreationView();
          accountCreationView.setVisible(true);
          parent.dispose();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    parent,
                    "Error al procesar CFDIs: " + e.getMessage(),
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    public static void connectEntityProcessorView(){
        EntityProcessorView entityProcessorView = new EntityProcessorView();
        entityProcessorView.setVisible(true);
    }

    public static void connectOperatorCreationView(DocumentNature nature){
        if (nature.equals(DocumentNature.EMITTED)){
            EntityProcessor.setClientEntities();
        } else {
            EntityProcessor.setExtendedProviderEntities();
        }
        OperatorCreationView operatorCreationView = new OperatorCreationView(nature);
        operatorCreationView.setVisible(true);
    }

    public static void connectOtherOpsView(){
        OtherOpsView otherOpsView = new OtherOpsView();
        otherOpsView.setVisible(true);
    }
}
