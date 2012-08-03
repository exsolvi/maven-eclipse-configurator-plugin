package se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class AbstractEclipseConfigurationHandler implements EclipseConfigurationHandler {

    private final Properties properties = new Properties();
    protected final Map<EclipseConfigurationContext, String> filenameMapping = new HashMap<EclipseConfigurationContext, String>();
    protected String settingsDirectory;

    @Override
    public EclipseConfigurationHandler setContext(EclipseConfigurationContext configurationContext) {
        try {
            properties.load(new FileReader(settingsDirectory + "/"
                    + filenameMapping.get(EclipseConfigurationContext.CODEFORMATTER)));
            // Used for debugging
            // properties.list(System.out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public String getValue(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            value = value.startsWith("_") ? value.substring(1) : value;
            return value.trim();
        } else {
            return null;
        }
    }

}
