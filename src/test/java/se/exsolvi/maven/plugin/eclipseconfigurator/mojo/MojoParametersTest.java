package se.exsolvi.maven.plugin.eclipseconfigurator.mojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import se.exsolvi.maven.plugin.eclipseconfigurator.EclipseEnvironment.WorkspaceValidator;

public class MojoParametersTest {

    @Ignore
    @Test
    public void testAddStringStringBooleanClassOfQextendsMojoParameterValidator() {
        fail("Not yet implemented");
    }

    @Ignore
    @Test
    public void testAddStringStringBoolean() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetValue() {
        MojoParameters mojoParameters = new MojoParameters();
        String test = "test";
        mojoParameters.add("test", test, true);
        assertEquals(test, mojoParameters.getValue("test"));
    }

    @Ignore
    @Test
    public void testIsMandatory() {
        fail("Not yet implemented");
    }

    @Ignore
    @Test
    public void testIsSet() {
        fail("Not yet implemented");
    }

    @Test
    public void testValidate() {
        MojoParameters mojoParameters = new MojoParameters();
        String test = "..";
        mojoParameters.add("test", test, true, WorkspaceValidator.class);
        mojoParameters.validate();
    }
}
