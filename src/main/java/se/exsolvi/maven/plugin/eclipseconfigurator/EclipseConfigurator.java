package se.exsolvi.maven.plugin.eclipseconfigurator;

import se.exsolvi.maven.plugin.eclipseconfigurator.configuration.CodeFormatterConfiguration;
import se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler.ProjectConfigurationHandler;
import se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler.WorkspaceConfigurationHandler;

public class EclipseConfigurator {

    private final WorkspaceConfigurationHandler wsConfigurationHandler;
    private final ProjectConfigurationHandler projConfigurationHandler;
    private final CodeFormatterConfiguration codeFormatterConfiguration;

    public EclipseConfigurator(EclipseEnvironment eclipseEnvironment) {
        wsConfigurationHandler = new WorkspaceConfigurationHandler(eclipseEnvironment.getWorkspaceDirectory());
        projConfigurationHandler = new ProjectConfigurationHandler(eclipseEnvironment.getProjectDirectory());
        codeFormatterConfiguration = new CodeFormatterConfiguration(wsConfigurationHandler, projConfigurationHandler);
    }

    public boolean validateCodeFormatter(String expectedCodeformatterName) {
        String activeCodeformatterName = codeFormatterConfiguration.getActiveCodeFormatterName();
        return expectedCodeformatterName.equals(activeCodeformatterName);
    }

    public boolean validateProjectCodeFormatter(String expectedCodeformatterName) {
        String activeCodeformatterName = codeFormatterConfiguration.getProjectCodeFormatterName();
        return expectedCodeformatterName.equals(activeCodeformatterName);
    }

    public boolean validateWorkspaceCodeFormatter(String expectedCodeformatterName) {
        String activeCodeformatterName = codeFormatterConfiguration.getWorkspaceCodeFormatterName();
        return expectedCodeformatterName.equals(activeCodeformatterName);
    }

    public String getActiveProjectCodeformatterName() {
        return codeFormatterConfiguration.getActiveCodeFormatterName();
    }

    public String getActiveWorkspaceCodeformatterName() {
        return codeFormatterConfiguration.getWorkspaceCodeFormatterName();
    }

    public String getActiveCodeformatterName() {
        return codeFormatterConfiguration.getProjectCodeFormatterName();
    }

}
