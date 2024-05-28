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
    public static void checkConnection() throws SQLException {
        ConfigModel config = ConfigInstance.getSingle();
        Connection connection;
        connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
        connection.close();
    }
}
