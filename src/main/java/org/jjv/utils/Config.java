package org.jjv.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;

import static org.jjv.utils.DefaultValues.*;

public class Config implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static boolean checkIfDirectoryExist(){
        File file = new File(CONFIG_DIR_PATH);
        return file.exists();
    }

    private static boolean makeConfigDir(){
        if (!checkIfDirectoryExist()) {
            return new File(CONFIG_DIR_PATH).mkdir();
        } else {
            return false;
        }
    }

    public static void generateConfigFile(ConfigModel config) throws IOException {
        if (makeConfigDir()) {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(CONFIG_FILE_PATH));
            output.writeObject(config);
            output.close();
        }
    }

    public static ConfigModel getConfigFile() throws Exception{
        ConfigModel config;
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(CONFIG_FILE_PATH));
        config = (ConfigModel) inputStream.readObject();

        return config;
    }
}
