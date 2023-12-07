package org.jjv.views;

import org.jjv.utils.Config;

import javax.swing.*;

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
}
