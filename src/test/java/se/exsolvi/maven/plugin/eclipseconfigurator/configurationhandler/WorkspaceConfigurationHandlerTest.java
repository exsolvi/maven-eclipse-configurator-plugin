package se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import se.exsolvi.maven.plugin.eclipseconfigurator.configuration.CodeFormatterConfiguration;
import se.exsolvi.maven.plugin.eclipseconfigurator.configuration.EclipseCodeFormatter;

public class WorkspaceConfigurationHandlerTest {

    private WorkspaceConfigurationHandler wsConf;
    CodeFormatterConfiguration formatterConfiguration = new CodeFormatterConfiguration();

    @Before
    public void setUp() {
        wsConf = new WorkspaceConfigurationHandler("..");
        wsConf.setContext(EclipseConfigurationContext.CODEFORMATTER);
    }

    @Test
    public void testGetValue() {
        assertEquals(wsConf.getValue("formatter_profile"), "Exsolvi");
    }

    @Test
    public void codeFormatterStringIsNotNull() {
        String formatters = formatterConfiguration.getAvailableCodeFormatters();
        assertNotNull(formatters);
    }

    @Test
    public void testXMLParsing() {
        String formatters = formatterConfiguration.getAvailableCodeFormatters();
        System.out.println(formatters);
        Map<String, EclipseCodeFormatter> formatterMap = EclipseCodeFormatter.parseCodeFormatters(formatters);
        int i = 0;
        for (String name : formatterMap.keySet()) {
            System.out.println("Formatter " + i + ": " + name);
            i++;
        }
    }
}
