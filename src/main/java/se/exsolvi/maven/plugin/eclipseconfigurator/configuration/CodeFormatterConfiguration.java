package se.exsolvi.maven.plugin.eclipseconfigurator.configuration;

import se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler.EclipseConfigurationContext;
import se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler.ProjectConfigurationHandler;
import se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler.WorkspaceConfigurationHandler;

public class CodeFormatterConfiguration {

    WorkspaceConfigurationHandler workspaceConfigurationHandler = new WorkspaceConfigurationHandler("..");
    ProjectConfigurationHandler projectConfigurationHandler = new ProjectConfigurationHandler(".");

    public String getActiveCodeFormatterName() {
        if (getProjectCodeFormatterName() != null) {
            return getProjectCodeFormatterName();
        } else {
            return getWorkspaceCodeFormatterName();
        }
    }

    public String getProjectCodeFormatterName() {
        projectConfigurationHandler.setContext(EclipseConfigurationContext.CODEFORMATTER);
        return projectConfigurationHandler.getValue("formatter_profile");
    }

    public String getWorkspaceCodeFormatterName() {
        workspaceConfigurationHandler.setContext(EclipseConfigurationContext.CODEFORMATTER);
        return workspaceConfigurationHandler.getValue("formatter_profile");
    }

}
