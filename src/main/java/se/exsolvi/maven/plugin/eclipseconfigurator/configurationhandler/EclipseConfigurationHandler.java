package se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler;


public interface EclipseConfigurationHandler {

    public abstract EclipseConfigurationHandler setContext(EclipseConfigurationContext configurationContext);

    public abstract String getValue(String key);

}