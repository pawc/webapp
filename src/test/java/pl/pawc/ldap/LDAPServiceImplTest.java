package pl.pawc.ldap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import junit.framework.TestCase;
import pl.pawc.config.WebappConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=WebappConfig.class)
@WebAppConfiguration
public class LDAPServiceImplTest extends TestCase {
	
	@Autowired
	LDAPService ldapService;
	
	@Test
	public void testAuthenticate() {
		boolean result = ldapService.isAuthenticated("", "");
		System.out.println(result);
	}

}