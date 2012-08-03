package se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler;


public class WorkspaceConfigurationHandler extends AbstractEclipseConfigurationHandler implements EclipseConfigurationHandler {
    private static final String WORKSPACE_SETTINGS_DIRECTORY = ".metadata/.plugins";

    public WorkspaceConfigurationHandler(String workspaceDirectory) {
        settingsDirectory = workspaceDirectory + "/" + WORKSPACE_SETTINGS_DIRECTORY;
        filenameMapping.put(EclipseConfigurationContext.CODEFORMATTER,
                "org.eclipse.core.runtime/.settings/org.eclipse.jdt.ui.prefs");
    }
}
