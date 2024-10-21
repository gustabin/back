package ar.com.santanderrio.obp.base.iatx.helpers;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;

// TODO: Auto-generated Javadoc
/**
 * Levantar las credenciales para las invocaciones a iatx que aun no tienen las
 * del usuario en session y evitar tenerlas hardcodeadas.
 * 
 * The Class IatxInicial.
 */
public class IatxInicial {
    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(IatxInicial.class);

    /**
     * Hace referencia al RACF_ID = "FREEUSER" si estuviera hardcode.
     */
    private String racfInicialId = "";

    /**
     * Hace referencia al RACF_PWD = "FREEUSR0" si estuviera hardcode.
     */
    private String racfInicialPwd = "";

    /**
     * Instantiates a new iatx inicial.
     *
     * @param credentialSecurityFactory the credential security factory
     * @param credentialId the credential id
     */
    public IatxInicial(CredentialSecurityFactory credentialSecurityFactory, String credentialId) {
        try {
            Credential credential = credentialSecurityFactory.getCredentialById(credentialId);
            if (credential != null) {
                this.racfInicialId = credential.getUsuario();
                this.racfInicialPwd = credential.getPassword();
            } else {
                LOGGER.info("Error al obtener usuario RACF INICIAL para el id de seguridad {}.", credentialId);
            }
        } catch (SQLException e) {
            LOGGER.error("Error al obtener usuario RACF INICIAL para el id de seguridad {}.", credentialId, e);
        }
    }

    /**
     * Gets the racf inicial id.
     *
     * @return the racfInicialId
     */
    public String getRacfInicialId() {
        return racfInicialId;
    }

    /**
     * Gets the racf inicial pwd.
     *
     * @return the racfInicialPwd
     */
    public String getRacfInicialPwd() {
        return racfInicialPwd;
    }

}
