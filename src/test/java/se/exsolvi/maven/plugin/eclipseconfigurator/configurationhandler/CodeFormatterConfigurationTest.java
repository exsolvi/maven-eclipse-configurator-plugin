package se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import se.exsolvi.maven.plugin.eclipseconfigurator.configuration.CodeFormatterConfiguration;
import se.exsolvi.maven.plugin.eclipseconfigurator.configuration.EclipseCodeFormatter;

public class CodeFormatterConfigurationTest {

    private WorkspaceConfigurationHandler wsConfigurationHandler;
    private ProjectConfigurationHandler projConfigurationHandler;
    CodeFormatterConfiguration formatterConfiguration;

    @Before
    public void setUp() {
        wsConfigurationHandler = new WorkspaceConfigurationHandler("..");
        projConfigurationHandler = new ProjectConfigurationHandler(".");
        wsConfigurationHandler.setContext(EclipseConfigurationContext.CODEFORMATTER);
        projConfigurationHandler.setContext(EclipseConfigurationContext.CODEFORMATTER);
        formatterConfiguration = new CodeFormatterConfiguration(wsConfigurationHandler, projConfigurationHandler);
    }

    @Test
    public void codeFormatterStringIsNotNull() {
        String formatters = formatterConfiguration.getAvailableCodeFormatters();
        assertNotNull(formatters);
    }

    @Test
    public void testXMLParsing() {
        String formatters = formatterConfiguration.getAvailableCodeFormatters();
        Map<String, EclipseCodeFormatter> formatterMap = EclipseCodeFormatter.parseCodeFormatters(formatters);
        assertFalse(formatterMap.isEmpty());

    }
}
