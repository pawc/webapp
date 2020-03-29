package pl.pawc.ldap;

public interface LDAPService {
	
	boolean isAuthenticated(String user, String password);

}