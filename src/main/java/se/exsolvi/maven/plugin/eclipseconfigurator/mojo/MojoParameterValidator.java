package se.exsolvi.maven.plugin.eclipseconfigurator.mojo;

public interface MojoParameterValidator {

    public void validate();

    public void setMojoParameter(MojoParameter param);

}
