package se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler.EclipseConfigurationContext;
import se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler.WorkspaceConfigurationHandler;

public class WorkspaceConfigurationHandlerTest {

    private WorkspaceConfigurationHandler wsConf;

    @Before
    public void setUp() {
        wsConf = new WorkspaceConfigurationHandler("..");
        wsConf.setContext(EclipseConfigurationContext.CODEFORMATTER);
    }

    @Test
    public void testGetValue() {
        assertEquals(wsConf.getValue("formatter_profile"), "Exsolvi");
    }

}
