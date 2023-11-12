package org.jjv.views;

import org.jjv.utils.Config;

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
}
