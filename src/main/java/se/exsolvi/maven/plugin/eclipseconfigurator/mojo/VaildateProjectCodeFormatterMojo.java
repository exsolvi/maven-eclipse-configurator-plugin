package se.exsolvi.maven.plugin.eclipseconfigurator.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import se.exsolvi.maven.plugin.eclipseconfigurator.EclipseConfigurator;
import se.exsolvi.maven.plugin.eclipseconfigurator.EclipseEnvironment;
import se.exsolvi.maven.plugin.eclipseconfigurator.EclipseEnvironment.EclipseVersion;

/**
 * @goal validate-project
 * 
 * @phase validate
 */
public class VaildateProjectCodeFormatterMojo extends AbstractMojo {

    /**
     * @parameter expression="${validate-project:workspaceDirectory}" default-value="${basedir}/.."
     */
    private String workspaceDirectory;

    /**
     * @parameter expression="${validate-project:projectDirectory}" default-value="${basedir}"
     */
    private String projectDirectory;

    /**
     * @parameter expression="${validate-project:coderformatterName}"
     */
    private String expectedCodeformatterName;

    /**
     * @parameter expression="${validate:failOnError}" default-value=false
     */
    private boolean failOnError;

    private final MojoParameters mojoParameters = new MojoParameters();

    /**
     * @parameter expression="${validate-project:coderformatterName}"
     */

    @Override
    public void execute() throws MojoExecutionException {
        mojoParameters.add("workspaceDirectory", workspaceDirectory, true, EclipseEnvironment.WorkspaceValidator.class);
        mojoParameters.add("projectDirectory", projectDirectory, true, EclipseEnvironment.ProjectValidator.class);
        mojoParameters.add("coderformatterName", expectedCodeformatterName, true);
        mojoParameters.validate();

        EclipseEnvironment eclipseEnvironment = new EclipseEnvironment(EclipseVersion.JUNO, workspaceDirectory,
                projectDirectory);

        EclipseConfigurator eclipseConfigurator = new EclipseConfigurator(eclipseEnvironment);
        if (!eclipseConfigurator.validateProjectCodeFormatter(expectedCodeformatterName)) {
            String errorMessage = "Wrong code formatter found, expecting '" + expectedCodeformatterName
                    + "' but found '" + eclipseConfigurator.getActiveProjectCodeformatterName() + "'";
            if (failOnError) {
                throw new RuntimeException(errorMessage);
            } else {
                getLog().warn(errorMessage);
            }
        }
    }
}
