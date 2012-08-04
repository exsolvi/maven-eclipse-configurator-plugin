package se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler;

public class ProjectConfigurationHandler extends AbstractEclipseConfigurationHandler implements
        EclipseConfigurationHandler {

    private static final String PROJECT_SETTINGS_DIRECTORY = ".settings";

    public ProjectConfigurationHandler(String projectSettingsDirectory) {
        settingsDirectory = projectSettingsDirectory + "/" + PROJECT_SETTINGS_DIRECTORY;
        filenameMapping.put(EclipseConfigurationContext.CODEFORMATTER, "org.eclipse.jdt.ui.prefs");
    }
}
