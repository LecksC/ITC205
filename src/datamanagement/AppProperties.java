package datamanagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppProperties
{
    private Properties properties_;
    private final static AppProperties instance_ = new AppProperties();


    public static AppProperties getInstance()
    {
        return instance_;
    }


    private AppProperties()
    {
        properties_ = new Properties();

        try {
            properties_.load(new FileInputStream("Properties.prop"));
        }
        catch (IOException e) {
            throw new RuntimeException("Could not read property file");
        }
    }



    public Properties getProperties()
    {
        return properties_;
    }
}
