package se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler;

public interface EclipseConfigurationHandler {

    public EclipseConfigurationHandler setContext(EclipseConfigurationContext configurationContext);

    public String getValue(String key);

}