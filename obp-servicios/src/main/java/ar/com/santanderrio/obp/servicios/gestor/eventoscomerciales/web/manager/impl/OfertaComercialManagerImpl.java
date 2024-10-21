/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.web.manager.impl;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo.GestorEventoComercialBO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.web.manager.OfertaComercialManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;

/**
 * The Class OfertaComercialManagerImpl.
 *
 * @author florencia.n.martinez
 */
@Component
public class OfertaComercialManagerImpl implements OfertaComercialManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OfertaComercialManagerImpl.class);

	/** The Constant DESKTOP. */
	private static final String DESKTOP = "desktop";

	/** The Constant MOBILE. */
	private static final String MOBILE = "mobile";

	/** The oferta comercial BO. */
	@Autowired
	private GestorEventoComercialBO gestroComercialBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The ModuloPermiso BO. */
	@Autowired
	private ModuloPermisoBO moduloPermisoBO;

	/**
	 * So obtienen las ofertas comerciales del cliente y se graban en sesion.
	 */
	@Override
	public void obtenerOfertasComerciales() {
		EventosComercialesDTO dto = new EventosComercialesDTO();
		if (tienePermisoOfertasComerciales()) {
			Cliente cliente = sesionCliente.getCliente();
			String dispositivo = obtenerDispositivo();

			LOGGER.info("Se invoca a BO de ofertas comerciales con el cliente: {} y dispositivo: {}.", cliente,
					dispositivo);
			dto = gestroComercialBO.obtenerOfertasComerciales(cliente, dispositivo);
			sesionCliente.getCliente().setProgramaBeneficios("S");
		} else {
		    LOGGER.info("Se habilita mostrar la opcion de superclub por default ya que no se pudo llamar al gestor");
		    dto.setMostrarCaja(Boolean.TRUE);
		}
		LOGGER.info("Se graba en sesion: {}", dto);
		sesionParametros.setOfertasComerciales(dto);
	}

	/**
	 * Tiene permiso ofertas comerciales.
	 *
	 * @return true, if successful
	 */
	private boolean tienePermisoOfertasComerciales() {
		return moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_SUPERCLUB).tienePermisoDeVisibilidad()
				&& moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.CARRUSEL).tienePermisoDeVisibilidad();
	}

	/**
	 * Obtiene el dispositivo.
	 *
	 * @return the string
	 */
	private String obtenerDispositivo() {
		return StringUtils.equals(DESKTOP, sesionParametros.getRegistroSession().getDispositivo())
				? DESKTOP.toUpperCase(Locale.getDefault()) : MOBILE.toUpperCase(Locale.getDefault());
	}
}
