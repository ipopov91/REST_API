package utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    protected static FileInputStream fileInputStream;
    protected static Properties PROPERTIES;
    static final String PROPERTIES_FILE_PATH = "src/test/resources/config.properties";

    static {
        try {
            //specifying the path to the properties file
            fileInputStream = new FileInputStream(PROPERTIES_FILE_PATH);
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            //handling a possible exception
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    /**
     * return string with value from properties file
     */

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
