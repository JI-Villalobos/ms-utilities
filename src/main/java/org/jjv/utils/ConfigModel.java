package org.jjv.utils;

import java.io.Serial;
import java.io.Serializable;

public class ConfigModel {
    private String url;
    private String username;
    private String password;

    public ConfigModel(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public ConfigModel() {
    }

    public static String assembleUrl(String domain, String dbName){
        return "jdbc:mysql://" + domain + "/" + dbName + "?noAccessToProcedureBodies=true&useSSL=false";
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
