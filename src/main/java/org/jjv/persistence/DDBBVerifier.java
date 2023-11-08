package org.jjv.persistence;

import org.jjv.instances.ConfigInstance;
import org.jjv.utils.Config;
import org.jjv.utils.ConfigModel;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DDBBVerifier {
    public static void checkConnection() throws Exception {
        ConfigModel config = Config.getConfigFile();
        System.out.println(config.getUrl());
        Connection connection = null;
        connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
        connection.close();
    }
}
