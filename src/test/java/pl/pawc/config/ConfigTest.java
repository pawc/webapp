package pl.pawc.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=WebappConfig.class)
@WebAppConfiguration
public class ConfigTest extends TestCase {
	
	@Test
	public void testA() {
		System.out.println("ok");
	}

}