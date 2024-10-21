/**
 * 
 */
package ar.com.santanderrio.obp.base.security.credential;

import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Interface CredentialSecurity.
 *
 * @author Jonatan_Bocian
 */
public interface CredentialSecurity {

	/**
	 * Gets the credential.
	 *
	 * @param id
	 *            the id
	 * @return the credential
	 */
	Credential getCredential(String id);

	/**
	 * Adds the credential.
	 *
	 * @param id
	 *            the id
	 * @param credential
	 *            the credential
	 */
	void addCredential(String id, Credential credential);

	/**
	 * Gets the credential map.
	 *
	 * @return the credential map
	 */
	Map<String, Credential> getCredentialMap();

}
