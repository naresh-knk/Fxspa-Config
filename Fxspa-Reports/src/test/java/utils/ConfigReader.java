package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    Properties prop;

    public ConfigReader() {

        try {
            String path = System.getProperty("user.dir") + "/src/resources/config.properties";

            FileInputStream fis = new FileInputStream(path);

            prop = new Properties();
            prop.load(fis);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return prop.getProperty("username");
    }

    public String getPassword() {
        return prop.getProperty("password");
    }

    public String getUrl() {
        return prop.getProperty("url");
    }
}
