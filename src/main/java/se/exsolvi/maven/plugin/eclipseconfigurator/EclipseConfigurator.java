package se.exsolvi.maven.plugin.eclipseconfigurator;

import se.exsolvi.maven.plugin.eclipseconfigurator.configuration.CodeFormatterConfiguration;
import se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler.ProjectConfigurationHandler;
import se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler.WorkspaceConfigurationHandler;
import se.exsolvi.maven.plugin.eclipseconfigurator.mojo.MojoParameter;
import se.exsolvi.maven.plugin.eclipseconfigurator.mojo.MojoParameterValidator;

public class EclipseConfigurator {

    private enum ValidationMode {
        BOTH, PROJECT, WORKSPACE;
    }

    private ValidationMode validationMode = ValidationMode.BOTH;

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
        switch (validationMode) {
        case PROJECT:
            return codeFormatterConfiguration.getProjectCodeFormatterName();
        case WORKSPACE:
            return codeFormatterConfiguration.getWorkspaceCodeFormatterName();
        default:
            return codeFormatterConfiguration.getProjectCodeFormatterName();
        }
    }

    public void setValidationMode(String validationTarget) {
        if ("BOTH".equals(validationTarget)) {
            validationMode = ValidationMode.BOTH;
        } else if ("PROJECT".equals(validationTarget)) {
            validationMode = ValidationMode.PROJECT;
        } else if ("WORKSPACE".equals(validationTarget)) {
            validationMode = ValidationMode.WORKSPACE;
        }
        // TODO: What to do here?
    }

    public static class TargetValidator implements MojoParameterValidator {

        private MojoParameter mojoParameter;

        @Override
        public void validate() {
            String target = mojoParameter.getValue();
            if (!(target.equals("BOTH") || target.equals("PROJECT") || target.equals("WORKSPACE"))) {
                throw new RuntimeException("Can't find a project directory '" + mojoParameter.getValue() + "'");
            }
        }

        @Override
        public void setMojoParameter(MojoParameter mojoParameter) {
            this.mojoParameter = mojoParameter;
        }

    }

}
