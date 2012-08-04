package se.exsolvi.maven.plugin.eclipseconfigurator;

import static se.exsolvi.maven.plugin.eclipseconfigurator.EclipseUtility.vaildateProjectDirectory;
import static se.exsolvi.maven.plugin.eclipseconfigurator.EclipseUtility.vaildateWorkspaceDirectory;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import se.exsolvi.maven.plugin.eclipseconfigurator.configuration.CodeFormatterConfiguration;
import se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler.ProjectConfigurationHandler;
import se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler.WorkspaceConfigurationHandler;

/**
 * Goal that validates workspace configuration
 * 
 * @goal validate-workspace
 * 
 * @phase validate
 */
public class VaildateWorkspaceMojo extends AbstractMojo {

    /**
     * Location of workspace
     * 
     * @parameter expression="${validate-workspace:projectDirectory}" default-value="${basedir}"
     */
    private String projectDirectory;

    /**
     * Location of workspace
     * 
     * @parameter expression="${validate-workspace:workspaceDirectory}" default-value="${basedir}/.."
     */
    private String workspaceDirectory;

    /**
     * Location of workspace
     * 
     * @parameter expression="${validate-workspace:coderformatterName}"
     */
    private String expectedCodeformatterName;

    private String canoicalWorkspaceDirectory;
    private String canoicalProjectDirectory;

    @Override
    public void execute() throws MojoExecutionException {

        if (expectedCodeformatterName == null || expectedCodeformatterName.equals("")) {
            throw new RuntimeException("Missing coderformatterName");
        }

        // TODO: Proper exception-handling
        // getLog().info("Default ws: " + workspaceDirectory);
        if (vaildateWorkspaceDirectory(workspaceDirectory)) {
            try {
                canoicalWorkspaceDirectory = new File(workspaceDirectory).getCanonicalPath();
                // getLog().info(canoicalWorkspaceDirectory);
            } catch (IOException e) {
                throw new RuntimeException("Invalid workspace directory");
            }
        } else {
            throw new RuntimeException("Invalid workspace directory");
        }

        // TODO: Proper exception-handling
        if (vaildateProjectDirectory(projectDirectory)) {
            try {
                canoicalProjectDirectory = new File(projectDirectory).getCanonicalPath();
            } catch (IOException e) {
                throw new RuntimeException("Invalid project directory)");
            }
        } else {
            throw new RuntimeException("Invalid project directory)");
        }

        getLog().info("Workspace directory is: " + canoicalWorkspaceDirectory);
        getLog().info("Project directory is:  " + canoicalProjectDirectory);

        WorkspaceConfigurationHandler wsConfigurationHandler = new WorkspaceConfigurationHandler(
                canoicalWorkspaceDirectory);
        ProjectConfigurationHandler projConfigurationHandler = new ProjectConfigurationHandler(canoicalProjectDirectory);
        CodeFormatterConfiguration codeFormatterConfiguration = new CodeFormatterConfiguration(wsConfigurationHandler,
                projConfigurationHandler);
        String activeCodeformatterName = codeFormatterConfiguration.getActiveCodeFormatterName();
        if (expectedCodeformatterName.equals(activeCodeformatterName)) {
            getLog().info("Correct codeformatter identified");
        } else {
            throw new RuntimeException("Wrong code formatter found, expecting '" + expectedCodeformatterName
                    + "' but found '" + activeCodeformatterName + "'");
        }
    }
}
