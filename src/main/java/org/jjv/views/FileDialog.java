package org.jjv.views;

import org.jjv.instances.PathInstance;

import javax.swing.*;

public class FileDialog {
    public static void showFileDialog(JFrame parent, String title){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle(title);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        if (fileChooser.showOpenDialog(parent) != JFileChooser.CANCEL_OPTION){
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            PathInstance.create(path);
        }
    }
}
