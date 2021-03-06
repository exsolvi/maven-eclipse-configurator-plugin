package se.exsolvi.maven.plugin.eclipseconfigurator.configurationhandler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ProjectConfigurationHandlerTest {

	private ProjectConfigurationHandler projConf;

	@Before
	public void setUp() {
		projConf = new ProjectConfigurationHandler(".");
		projConf.setContext(EclipseConfigurationContext.CODEFORMATTER);
	}

	@Test
	public void testGetValue() {
		assertEquals(projConf.getValue("formatter_profile"), "Knurra");
	}

}
