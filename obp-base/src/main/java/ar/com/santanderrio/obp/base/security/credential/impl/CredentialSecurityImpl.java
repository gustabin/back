/**
 * 
 */
package ar.com.santanderrio.obp.base.security.credential.impl;

import java.util.HashMap;
import java.util.Map;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurity;

// TODO: Auto-generated Javadoc
/**
 * The Class CredentialSecurityImpl.
 *
 * @author Jonatan_Bocian
 */
public class CredentialSecurityImpl implements CredentialSecurity {

	/** The credential map. */
	private Map<String, Credential> credentialMap = new HashMap<String, Credential>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.base.security.credential.CredentialSecurity#
	 * getCredential(java.lang.String)
	 */
	public Credential getCredential(String id) {
		return credentialMap.get(id);
	}

	/**
	 * Gets the credential map.
	 *
	 * @return the credentialMap
	 */
	public Map<String, Credential> getCredentialMap() {
		return credentialMap;
	}

	/**
	 * Adds the credential.
	 *
	 * @param id
	 *            the id
	 * @param credential
	 *            the credentialMap to set
	 */
	public void addCredential(String id, Credential credential) {
		this.credentialMap.put(id, credential);
	}

}
