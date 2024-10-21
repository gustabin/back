/**
 * 
 */
package ar.com.santanderrio.obp.base.security.credential;

import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating CredentialSecurity objects.
 */
public interface CredentialSecurityFactory {

    /**
     * Creates the.
     *
     * @return the credential security
     */
    CredentialSecurity create();

    /**
     * Gets the credential by sistema.
     *
     * @param sistema
     *            the sistema
     * @return the credential by sistema
     * @throws SQLException
     *             the SQL exception
     */
    Credential getCredentialBySistema(String sistema) throws SQLException;

    /**
     * Obtener la credencial de la base, la cual se cachea salvo que haya un error
     * en cuyo caso se retorna null.
     *
     * @param dbId
     *            the db id
     * @return the credential by id
     * @throws SQLException
     *             the SQL exception
     */
    Credential getCredentialById(String dbId) throws SQLException;

}
