/**
 * 
 */
package ar.com.santanderrio.obp.base.security.keystore;

import ar.com.santanderrio.obp.base.dao.DAOException;

// TODO: Auto-generated Javadoc
/**
 * The Interface KeyStoreHelper.
 *
 * @author sergio.e.goldentair
 */
public interface KeyStoreHelper {

	/**
	 * Read key store.
	 *
	 * @param jks
	 *            the jks
	 * @return the KS store
	 * @throws DAOException
	 *             the DAO exception
	 */
	KSStore readKeyStore(String jks) throws DAOException;

	/**
	 * Der to pem PKCS 7.
	 *
	 * @param derObj
	 *            the der obj
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	String derToPemPKCS7(byte[] derObj) throws DAOException;

	/**
	 * Pem to der PKCS 7.
	 *
	 * @param pemObj
	 *            the pem obj
	 * @return the byte[]
	 * @throws DAOException
	 *             the DAO exception
	 */
	byte[] pemToDerPKCS7(String pemObj) throws DAOException;
}
