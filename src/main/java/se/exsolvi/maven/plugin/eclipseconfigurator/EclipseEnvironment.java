package se.exsolvi.maven.plugin.eclipseconfigurator;

import java.io.File;
import java.io.IOException;

import se.exsolvi.maven.plugin.eclipseconfigurator.mojo.MojoParameter;
import se.exsolvi.maven.plugin.eclipseconfigurator.mojo.MojoParameterValidator;

public class EclipseEnvironment {

    public enum EclipseVersion {
        HELIOS, GANYMEDE, INDIGO, JUNO;
    }

    private EclipseVersion eclipseVersion = EclipseVersion.JUNO;

    private String workspaceDirectory;
    private String projectDirectory;

    public EclipseEnvironment(EclipseVersion eclipseVersion) {
        this.eclipseVersion = eclipseVersion;
    }

    public EclipseEnvironment(EclipseVersion eclipseVersion, String workspaceDirectory, String projectDirectory) {
        this(eclipseVersion);
        setWorkspaceDirectory(workspaceDirectory);
        setProjectDirectory(projectDirectory);
    }

    public String getWorkspaceDirectory() {
        return workspaceDirectory;

    }

    public String getProjectDirectory() {
        return projectDirectory;
    }

    public EclipseVersion getEclipseVersion() {
        return eclipseVersion;
    }

    public EclipseEnvironment setEclipseVersion(EclipseVersion eclipseVersion) {
        this.eclipseVersion = eclipseVersion;
        return this;
    }

    public EclipseEnvironment setWorkspaceDirectory(String directory) {
        if (EclipseUtility.validateWorkspaceDirectory(directory)) {
            try {
                workspaceDirectory = new File(directory).getCanonicalPath();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Can't find directory: " + directory);
        }
        return this;
    }

    public EclipseEnvironment setProjectDirectory(String directory) {
        if (EclipseUtility.validateProjectDirectory(directory)) {
            try {
                projectDirectory = new File(directory).getCanonicalPath();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Can't find directory: " + directory);
        }
        return this;
    }

    public static class WorkspaceValidator implements MojoParameterValidator {

        private MojoParameter mojoParameter;

        @Override
        public void validate() {
            if (EclipseUtility.validateWorkspaceDirectory(mojoParameter.getValue())) {
                try {
                    new File(mojoParameter.getValue()).getCanonicalPath();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new RuntimeException("Can't find a workspace in directory '" + mojoParameter.getValue() + "'");
            }
        }

        @Override
        public void setMojoParameter(MojoParameter mojoParameter) {
            this.mojoParameter = mojoParameter;

        }

    }

    public static class ProjectValidator implements MojoParameterValidator {

        private MojoParameter mojoParameter;

        @Override
        public void validate() {
            if (EclipseUtility.validateProjectDirectory(mojoParameter.getValue())) {
                try {
                    new File(mojoParameter.getValue()).getCanonicalPath();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new RuntimeException("Can't find a project directory '" + mojoParameter.getValue() + "'");
            }
        }

        @Override
        public void setMojoParameter(MojoParameter mojoParameter) {
            this.mojoParameter = mojoParameter;

        }

    }

}
