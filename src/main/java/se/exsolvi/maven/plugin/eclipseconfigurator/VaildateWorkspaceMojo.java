package se.exsolvi.maven.plugin.eclipseconfigurator;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler.EclipseConfigurationHandler;
import se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler.WorkspaceConfigurationHandler;

/**
 * Goal that validates workspace configuration
 * 
 * @goal validate-workspace
 * 
 * @phase validate
 */
public class VaildateWorkspaceMojo extends AbstractMojo {

    enum ConfigurationType {
        WORKSPACE, PROJECT;
    }

    /**
     * Location of workspace
     * 
     * @parameter expression="${touch:projectDirectory}" default-value="${basedir}"
     */
    private String projectDirectory;

    /**
     * Location of workspace
     * 
     * @parameter expression="${touch:workspaceDirectory}" default-value="${basedir}../"
     */
    private String workspaceDirectory;

    private File workspaceMetaDirectory;
    private File projectSettingsDirectory;

    @Override
    public void execute() throws MojoExecutionException {

        EclipseConfigurationHandler projConf;
        WorkspaceConfigurationHandler wsConf;

        workspaceMetaDirectory = detectWorkspace(workspaceDirectory);
        if (vaildateDirectory(projectDirectory, ConfigurationType.PROJECT)) {
            try {
                projectSettingsDirectory = new File(projectDirectory + "/.settings").getCanonicalFile();
            } catch (IOException e) {
                throw new InvalidParameterException("Invalid project directory");
            }
        } else {
            throw new InvalidParameterException("Invalid project directory");
        }
        getLog().info("Workspace meta directory is: " + workspaceMetaDirectory);
        getLog().info("Project settings directory is:  " + projectSettingsDirectory);
    }

    private File detectWorkspace(String possibleWorkspace) throws MojoExecutionException {

        // Try if the supplied parameter is the workspace
        String dir = possibleWorkspace + "/.metadata";
        dir.replaceAll("//", "/");
        if (vaildateDirectory(dir, ConfigurationType.WORKSPACE)) {
            try {
                return new File(dir).getCanonicalFile();
            } catch (IOException e) {
                throw new MojoExecutionException("Internal error - can not find workspace");
            }
        }

        // Try if the supplied parameter has a workspace in the parent directory
        dir = possibleWorkspace + "/../.metadata";
        dir.replaceAll("//", "/");
        if (vaildateDirectory(dir, ConfigurationType.WORKSPACE)) {
            try {
                return new File(dir).getCanonicalFile();
            } catch (IOException e) {
                throw new InvalidParameterException("Internal error - can not find workspace");
            }
        } else {
            throw new InvalidParameterException("Error finding workspace");
        }

    }

    private boolean vaildateDirectory(String dir, ConfigurationType config) {
        String versionFile = null;
        if (config == ConfigurationType.WORKSPACE) {
            versionFile = dir + "/version.ini";
        } else {
            versionFile = dir + "/.project";
        }
        File workspace = new File(versionFile);
        return workspace.exists();
    }
}
