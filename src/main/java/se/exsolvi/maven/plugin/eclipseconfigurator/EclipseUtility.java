package se.exsolvi.maven.plugin.eclipseconfigurator;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

import org.apache.maven.plugin.MojoExecutionException;

public class EclipseUtility {
    public File detectWorkspace(String possibleWorkspace) throws MojoExecutionException {

        // Try if the supplied parameter is the workspace
        String dir = possibleWorkspace + "/.metadata";
        dir.replaceAll("//", "/");
        if (vaildateWorkspaceDirectory(dir)) {
            try {
                return new File(dir).getCanonicalFile();
            } catch (IOException e) {
                throw new MojoExecutionException("Internal error - can not find workspace");
            }
        }

        // Try if the supplied parameter has a workspace in the parent directory
        dir = possibleWorkspace + "/../.metadata";
        dir.replaceAll("//", "/");
        if (vaildateWorkspaceDirectory(dir)) {
            try {
                return new File(dir).getCanonicalFile();
            } catch (IOException e) {
                throw new InvalidParameterException("Internal error - can not find workspace");
            }
        } else {
            throw new InvalidParameterException("Error finding workspace");
        }

    }

    public static boolean vaildateWorkspaceDirectory(String dir) {
        String versionFile = dir + "/.metadata/version.ini";
        versionFile.replaceAll("//", "/");
        File workspace = new File(versionFile);
        return workspace.exists();
    }

    public static boolean vaildateProjectDirectory(String dir) {
        String versionFile = dir + "/.project";
        versionFile.replaceAll("//", "/");
        File workspace = new File(versionFile);
        return workspace.exists();
    }

}
