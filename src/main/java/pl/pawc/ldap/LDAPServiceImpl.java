package pl.pawc.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.springframework.stereotype.Component;

@Component
public class LDAPServiceImpl implements LDAPService {

	@Override
	public boolean isAuthenticated(String user, String password) {

		String base = "CN=Users,CN=Partycja1,DC=pawc,DC=pl";
		String dn = "CN=" + user + "," + base;
		String ldapURL = "ldap://localhost:389";
		
		Hashtable<String, String> environment = 
			new Hashtable<String, String>();
		environment.put(Context.INITIAL_CONTEXT_FACTORY,
				"com.sun.jndi.ldap.LdapCtxFactory");
		environment.put(Context.PROVIDER_URL, ldapURL);
		environment.put(Context.SECURITY_AUTHENTICATION, "simple");
		environment.put(Context.SECURITY_PRINCIPAL, dn);
		environment.put(Context.SECURITY_CREDENTIALS, password);
		
		boolean result = false;
		DirContext authContext = null;

		try {
			authContext = new InitialDirContext(environment);
			result = true;
			if(authContext != null) authContext.close();
			
		}
		catch (NamingException ex){
			//ex.printStackTrace();
		}
		
		return result;
	}

}