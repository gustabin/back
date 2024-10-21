/**
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.home.web.manager.PuntoSuperclubHomeManager;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomePuntosSuperclubView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;

/**
 * The Class PuntoSuperclubHomeManagerImpl.
 *
 * @author florencia.n.martinez
 */
@Component
public class PuntoSuperclubHomeManagerImpl extends AbstractHomeManager implements PuntoSuperclubHomeManager {

    /** The Constant TEXTO_LINK_CAJA_SUPERCLUB. */
    private static final String TEXTO_LINK_CAJA_SUPERCLUB= "Ver catálogo";
    
	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/**
	 * Verifica si el grupo aplica al usuario.
	 *
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Boolean> aplicaGrupo() {
		return respuestaFactory.crearRespuestaOk(Boolean.TRUE);
	}

	/**
	 * Fija la accion a tomar.
	 *
	 * @return the accion controller
	 */
	@Override
	public AccionController obtenerAccion() {
		return AccionController.IR_SUPERCLUB;
	}

	/**
	 * Obtiene la caja de superclub.
	 *
	 * @return the list
	 */
	@Override
	protected GrupoCajaHomeView obtenerCajas() {
		ArrayList<Caja> cajas = new ArrayList<Caja>();
		GrupoCajaHomeView grupoCajaHomeView = new GrupoCajaHomeView();
		grupoCajaHomeView.setSinError(Boolean.TRUE);
		if (sesionParametros.getOfertasComerciales() == null
				|| !sesionParametros.getOfertasComerciales().getMostrarCaja()) {
			return grupoCajaHomeView;
		}
		EventosComercialesDTO ofertasComerciales = sesionParametros.getOfertasComerciales();
		CajaHomePuntosSuperclubView caja = new CajaHomePuntosSuperclubView(ofertasComerciales.getCantidadPuntos(),
				ofertasComerciales.getDescripcion());
		caja.setTextoLink(TEXTO_LINK_CAJA_SUPERCLUB);
		cajas.add(caja);
		cajas.trimToSize();
		grupoCajaHomeView.setCajas(cajas);
		return grupoCajaHomeView;
	}

}
