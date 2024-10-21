/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monitoreo.bo.impl;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.clientes.dao.ClienteDAO;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteConSaldoResponse;
import ar.com.santanderrio.obp.servicios.monitoreo.bo.MonitoreoBO;
import ar.com.santanderrio.obp.servicios.monitoreo.dao.BaseMonitoreoDAO;

/**
 * The Class MonitoreoBOImpl.
 *
 * @author sergio.e.goldentair
 */
/** The credential security factory. */

@Component("monitoreoBO")
public class MonitoreoBOImpl implements MonitoreoBO {
    /**
     * The Cliente DAO.
     */
    @Autowired
    private ClienteDAO clienteDAO;
    /** Nup de prueba. */
    @Value("${MONITOR_NUP:02616647}")
    private String monitorNup;
    /** Documento de prueba. */
    @Value("${MONITOR_DOCUMENTO:12111211}")
    private String monitorDocumento;

    /** The credential security factory. */
    @Autowired
    private CredentialSecurityFactory credentialSecurityFactory;

    /** The id. */
    @Value("${DB.RACFINICIAL.ID}")
    private String id;

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoreoBOImpl.class);

    /**
     * DAO para consultar status DB.
     */
    @Autowired
    private BaseMonitoreoDAO baseMonitoreoDAO;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.monitoreo.bo.MonitoreoBO#validarBase()
     */
    @Override
    public boolean validarBase() {
        return baseMonitoreoDAO.isDBDisponible();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.monitoreo.bo.MonitoreoBO#validarIatx()
     */
    @Override
    public boolean validarIatx() {
        boolean estado = false;
        Credential credential;
        try {
            credential = credentialSecurityFactory.getCredentialById(this.id);
            ResumenCliente resumenCliente = new ResumenCliente();
            resumenCliente.setUsuarioRacf(credential.getUsuario());
            resumenCliente.setPasswordRacf(credential.getPassword());
            resumenCliente.setNup(monitorNup);
            resumenCliente.setDni(monitorDocumento);
            ClienteConSaldoResponse response = clienteDAO.obtenerClienteSinSessionUsuario(resumenCliente);
            estado = EstadoRespuesta.OK.equals(response.getEstadoRespuesta());
        } catch (SQLException e) {
            LOGGER.error("Error al obtener usuario RACF INICIAL para el id de seguridad {}.", this.id, e);
            estado = false;
        }
        return estado;
    }

}
