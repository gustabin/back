package ar.com.santanderrio.obp.servicios.comun.estadistica.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.dao.EstadisticaDAO;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * The Class EstadisticaBOImpl.
 */
@Component(value = "logEstadisticaBO")
public class EstadisticaBOImpl implements EstadisticaBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(EstadisticaBOImpl.class);

    /** The Constant FECHA_DEFAULT. */
    private static final String FECHA_DEFAULT = "19000101";

    /** * The Documento  default **. */
    private static final String DNI_DEFAULT = "00000000";

    /** The canal default. */
    private static final String CANAL_DEFAULT = "R";

    /** The log estadistica dao. */
    @Autowired
    private EstadisticaDAO logEstadisticaDAO;
    
    @Autowired
    private ModuloPermisoBO moduloPermisoBO;
    
	@Autowired
	private SesionParametros sesionParametros;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.comun.estadistica.bo.EstadisticaBO#add(ar.com.
     * santanderrio.obp.comun.estadisticas.entities.Estadistica,
     * ar.com.santanderrio.obp.clientes.entities.RegistroSesion,
     * ar.com.santanderrio.obp.clientes.entities.Cliente)
     */
    @Override
    public Estadistica add(Estadistica estadistica, RegistroSesion registroSesion, Cliente cliente)
            throws BusinessException {
        if (registroSesion != null) {
            if (registroSesion.getNup() != null) {
                estadistica.setClienteNUP(registroSesion.getNup());
            }
            if (registroSesion.getSinonimo() != null) {
                estadistica.setFechaNacimiento(registroSesion.getSinonimo());
            } else {
                estadistica.setFechaNacimiento(FECHA_DEFAULT);
            }
            estadistica.setFileName(registroSesion.getLogFile());
            estadistica.setHostName(registroSesion.getHostName());
            if (registroSesion.getIp() != null) {
                estadistica.setIp(registroSesion.getIp());
            }
            if (registroSesion.getTipoTeclado() != null) {
                estadistica.setTipoTeclado(registroSesion.getTipoTeclado().getTipoTeclado());
            }
            estadistica.setDispositivo(registroSesion.getDispositivo());
            estadistica.setNavegador(registroSesion.getNavegador());
            estadistica.setClientePID(registroSesion.getPid());
            estadistica.setClienteDNI(registroSesion.getDni());
            estadistica.setCsId(sesionParametros.getCsid());
        }
        if (cliente != null) {
            estadistica.setClienteDNI(cliente.getDni().trim());
        }

        estadistica.setCanal(CANAL_DEFAULT);
        if (cliente == null && registroSesion == null) {
            estadistica.setClienteDNI(DNI_DEFAULT);
            estadistica.setFechaNacimiento(FECHA_DEFAULT);
            return estadistica;
        }

        LOGGER.info("Se obtiene el permiso para saber si grabar o no la estadistica");
        if (sesionParametros.getPermisoGrabadoEstadistica() == null) {
	        ModuloPermiso moduloPermiso = moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.GRABAR_ESTADISTICAS);
	        sesionParametros.setPermisoGrabadoEstadistica(moduloPermiso.tienePermisoDeVisibilidad());
        }
        if(sesionParametros.getPermisoGrabadoEstadistica() != null && sesionParametros.getPermisoGrabadoEstadistica()) {
        	add(estadistica);
        } else {
        	LOGGER.info("No está habilitado el grabado de estadisticas");
        }
        return estadistica;
    }

    /**
     * Adds the.
     *
     * @param entity
     *            the entity
     * @return the estadistica
     * @throws BusinessException
     *             the business exception
     */
    private void add(Estadistica entity) throws BusinessException {
        try {
            logEstadisticaDAO.add(entity);
        } catch (DAOException e) {
            LOGGER.error("Error al agregar la entidad", e);
            throw new BusinessException(e);
        }
    }

}
