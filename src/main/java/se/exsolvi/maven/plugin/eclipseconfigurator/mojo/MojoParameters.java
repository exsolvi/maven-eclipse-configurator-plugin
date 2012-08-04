package se.exsolvi.maven.plugin.eclipseconfigurator.mojo;

import java.util.HashMap;
import java.util.Map;

public class MojoParameters {

    private final Map<String, MojoParameter> mojoParameters = new HashMap<String, MojoParameter>();

    public void add(String name, String value, boolean mandatory, Class<? extends MojoParameterValidator> validator) {
        MojoParameter mojoParameter = new MojoParameter(name, value, mandatory, validator);
        mojoParameters.put(name, mojoParameter);
    }

    public void add(String name, String value, boolean mandatory) {
        MojoParameter mojoParameter = new MojoParameter(name, value, mandatory);
        mojoParameters.put(name, mojoParameter);
    }

    public String getValue(String name) {
        return mojoParameters.get(name).getValue();
    }

    public boolean isMandatory(String name) {
        return mojoParameters.get(name).isMandatory();
    }

    public boolean isSet(String name) {
        return mojoParameters.get(name) != null;
    }

    public void validate() {
        for (MojoParameter param : mojoParameters.values()) {
            if (isMandatory(param.getName())) {
                if (!isSet(param.getName())) {
                    throw new RuntimeException("Missing mandatory parameter '" + param.getName() + "'");
                }
            }
            if (isSet(param.getName()) && param.getValidator() != null) {
                param.getValidator().validate();
            }
        }
    }

}
