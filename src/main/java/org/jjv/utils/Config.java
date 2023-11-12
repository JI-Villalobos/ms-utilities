package org.jjv.utils;

import com.google.gson.Gson;
import org.jjv.instances.ConfigInstance;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;

import static org.jjv.utils.DefaultValues.*;

public class Config {

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

    public static boolean checkIfConfigFileExists(){
        File file = new File(CONFIG_FILE_PATH);
        return file.exists();
    }
    public static void generateConfigFile(ConfigModel config) throws IOException {
        if (makeConfigDir())
            System.out.println("Creando directorio");
        Gson gson = new Gson();
        String json = gson.toJson(config);
        BufferedWriter bw = new BufferedWriter(new FileWriter(CONFIG_FILE_PATH));
        bw.write(json);
        bw.close();
    }

    public static void getConfigFile() throws Exception{
        Gson gson = new Gson();
        StringBuilder data = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new FileReader(CONFIG_FILE_PATH));
        while ((line = br.readLine()) != null){
            data.append(line);
        }
        ConfigModel configModel = gson.fromJson(String.valueOf(data), ConfigModel.class);
        System.out.println("method:  getconfigFile");
        System.out.println("printing config model: " + configModel.getUrl() + " | " + configModel.getUsername());
        ConfigInstance.create(configModel);
    }
}
