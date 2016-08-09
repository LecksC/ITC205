package datamanagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppProperties
{
    private static AppProperties self_ = null;
    private Properties properties_;



    public static AppProperties getInstance()
    {
        if (self_ == null) {
            self_ = new AppProperties();
        }
        return self_;
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
