/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monitoreo.manager.impl;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.monitoreo.bo.MonitoreoBO;
import ar.com.santanderrio.obp.servicios.monitoreo.manager.MonitoreoManager;

/**
 * The Class MonitoreoManagerImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("monitoreoManager")
public class MonitoreoManagerImpl implements MonitoreoManager {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoreoManagerImpl.class);
    /**
     * Monitor bo.
     */
    @Autowired
    private MonitoreoBO monitoreoBO;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.monitoreo.manager.MonitoreoManager#
     * obtenerHTML()
     */
    @Override
    public String obtenerHTML() {
        LOGGER.info("Invocar a iatx y a la base.");
        StringBuilder mensajeMonitor = new StringBuilder(
                "<html><head><title>Monitor de Servicios</title></head><body>");
        Boolean wasOK = agregarMensaje("WAS", Boolean.TRUE, mensajeMonitor);
        //Boolean iatxOK = agregarMensaje("IATX", validarIatx(), mensajeMonitor);
        Boolean baseOK = agregarMensaje("RIO147", validarBase(), mensajeMonitor);
        agregarMensajeChequeoServicios(mensajeMonitor, wasOK, baseOK);
        mensajeMonitor.append("</body></html>");
        return mensajeMonitor.toString();
    }

    /**
	 * Armar mensajes de estado servicio.
	 *
	 * @param servicio
	 *            the servicio
	 * @param estado
	 *            the estado
	 * @param mensajeMonitor
	 *            the mensaje monitor
	 * @return the boolean
	 */
    private Boolean agregarMensaje(String servicio, boolean estado, StringBuilder mensajeMonitor) {
        Boolean servicioOK = Boolean.TRUE;
        mensajeMonitor.append(servicio);
        if (estado) {
            mensajeMonitor.append(" <b>OK");
        } else {
            mensajeMonitor.append(" <b>NOK");
            servicioOK = Boolean.FALSE;
        }
        mensajeMonitor.append("</b><br>");
        return servicioOK;
    }

    /**
	 * Donde la línea de servicios será un resumen de las anteriores, o sea lo
	 * mínimo para que el canal funcione. En caso de que alguno este en NOK,
	 * dirá Servicios No Disponibles. Si están los 3 OK, dirá Servicios
	 * Disponibles
	 *
	 * @param mensajeMonitor
	 *            the mensaje monitor
	 * @param servicios
	 *            the servicios
	 */
    private void agregarMensajeChequeoServicios(StringBuilder mensajeMonitor, Boolean... servicios) {
        mensajeMonitor.append("<b>Servicios ");
        if (BooleanUtils.and(servicios)) {
            mensajeMonitor.append("Disponibles");
        } else {
            mensajeMonitor.append("NO Disponibles");
        }
        mensajeMonitor.append("</b><br>");
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.manager.MonitoreoManager#
     * validarIatx()
     */
    @Override
    public boolean validarIatx() {
        LOGGER.info("Obtener estado de iatx.");
        return monitoreoBO.validarIatx();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.login.manager.MonitoreoManager#
     * validarBase()
     */
    @Override
    public boolean validarBase() {
        LOGGER.info("Obtener estado de la base.");
        return monitoreoBO.validarBase();
    }

}
