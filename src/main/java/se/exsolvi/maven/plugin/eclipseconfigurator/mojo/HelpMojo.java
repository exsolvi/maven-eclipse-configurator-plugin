package se.exsolvi.maven.plugin.eclipseconfigurator.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

/**
 * Lists all available goals and a descriptions of their usage.
 * 
 * @goal help
 * 
 */
public class HelpMojo extends AbstractMojo {

    @Override
    public void execute() throws MojoExecutionException {

        System.out
                .println("This plugin has 2 goals:\n\neclipse-configurator:help\n  Description: Lists all available goals and a descriptions of their usage.\n\neclipse-configurator:validate\n  Description: (no description available)");

    }
}
