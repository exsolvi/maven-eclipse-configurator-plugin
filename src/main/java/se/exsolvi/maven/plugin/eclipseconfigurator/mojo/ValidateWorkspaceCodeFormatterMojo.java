package se.exsolvi.maven.plugin.eclipseconfigurator.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import se.exsolvi.maven.plugin.eclipseconfigurator.EclipseConfigurator;
import se.exsolvi.maven.plugin.eclipseconfigurator.EclipseEnvironment;
import se.exsolvi.maven.plugin.eclipseconfigurator.EclipseEnvironment.EclipseVersion;

/**
 * @goal validate-workspace
 * 
 * @phase validate
 */
public class ValidateWorkspaceCodeFormatterMojo extends AbstractMojo {

    /**
     * @parameter expression="${validate-workspace:workspaceDirectory}" default-value="${basedir}/.."
     */
    private String workspaceDirectory;

    /**
     * @parameter expression="${validate-workspace:projectDirectory}" default-value="${basedir}"
     */
    private String projectDirectory;

    /**
     * @parameter expression="${validate-workspace:coderformatterName}"
     */
    private String expectedCodeformatterName;

    private final MojoParameters mojoParameters = new MojoParameters();
    private final EclipseEnvironment eclipseEnvironment = new EclipseEnvironment(EclipseVersion.JUNO);

    @Override
    public void execute() throws MojoExecutionException {

        mojoParameters.add("workspaceDirectory", workspaceDirectory, true, EclipseEnvironment.WorkspaceValidator.class);
        mojoParameters.add("projectDirectory", projectDirectory, true, EclipseEnvironment.ProjectValidator.class);
        mojoParameters.add("coderformatterName", expectedCodeformatterName, true);
        mojoParameters.validate();

        eclipseEnvironment.setWorkspaceDirectory(workspaceDirectory);
        eclipseEnvironment.setProjectDirectory(projectDirectory);

        EclipseConfigurator eclipseConfigurator = new EclipseConfigurator(eclipseEnvironment);
        if (!eclipseConfigurator.validateWorkspaceCodeFormatter(expectedCodeformatterName)) {
            throw new RuntimeException("Wrong code formatter found, expecting '" + expectedCodeformatterName
                    + "' but found '" + eclipseConfigurator.getActiveWorkspaceCodeformatterName() + "'");
        }
    }
}
