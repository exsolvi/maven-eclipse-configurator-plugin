package se.exsolvi.maven.plugin.eclipseconfigurator.mojo;

public class MojoParameter {

    private final String name;
    private final String value;
    private boolean mandatory = false;
    private Class<? extends MojoParameterValidator> validator;

    public MojoParameter(String name, String value, boolean mandatory) {
        this.name = name;
        this.value = value;
        this.mandatory = mandatory;
    }

    public MojoParameter(String name, String value, boolean mandatory, Class<? extends MojoParameterValidator> validator) {
        this(name, value, mandatory);
        this.validator = validator;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public Class<? extends MojoParameterValidator> getValidatorClass() {
        return validator;
    }

    public MojoParameterValidator getValidator() {
        MojoParameterValidator validatorInstance = null;
        if (validator != null) {
            try {
                validatorInstance = validator.newInstance();
                validatorInstance.setMojoParameter(this);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
        return validatorInstance;
    }
}
