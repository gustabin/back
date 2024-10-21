/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.sei.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.sei.CambioGrupoAfinidadSEI;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.manager.CambioGrupoAfinidadManager;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.AltaSolicitudAdhesionView;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.ConfirmarCambioAfinidadInView;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.ConsultaGrupoAfinidadView;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.SolicitudCambioAfinidadView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Class CambioGrupoAfinidadSEIImpl.
 */
@Component("cambioGrupoAfinidadSEI")
public class CambioGrupoAfinidadSEIImpl implements CambioGrupoAfinidadSEI {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CambioGrupoAfinidadSEIImpl.class);
	
	/** The cambio grupo afinidad manager. */
	@Autowired
	private CambioGrupoAfinidadManager cambioGrupoAfinidadManager;
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.sei.CambioGrupoAfinidadSEI#consultarGrupoAfinidad()
	 */
	public Respuesta<ConsultaGrupoAfinidadView> consultarGrupoAfinidad(){
		LOGGER.info("Post OK: /consultaGrupoAfinidad.");
		return cambioGrupoAfinidadManager.consultarGrupoAfinidad();
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.sei.CambioGrupoAfinidadSEI#obtenerDatosInicialesSolicitud()
	 */
	@Override
	public Respuesta<SolicitudCambioAfinidadView> obtenerDatosInicialesSolicitud() {
		LOGGER.info("Post OK: /solicitarCambioAfinidad.");
		return cambioGrupoAfinidadManager.obtenerDatosInicialesSolicitud();
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.sei.CambioGrupoAfinidadSEI#confirmarCambioAfinidad(ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.ConfirmarCambioAfinidadInView)
	 */
	@Override
	public Respuesta<AltaSolicitudAdhesionView> confirmarCambioAfinidad(ConfirmarCambioAfinidadInView inView){
		LOGGER.info("Post OK : /confirmarCambioAfinidad.");
		return cambioGrupoAfinidadManager.altaSolicitudAdhesion(inView.getNroSocioAdvantage());
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.sei.CambioGrupoAfinidadSEI#generarComprobanteCambioAfinidad()
	 */
	@Override
	public Respuesta<ReporteView> generarComprobanteCambioAfinidad(){
		LOGGER.info("Post OK : /generarComprobante.");
		return cambioGrupoAfinidadManager.generarComprobanteCambioGrupoAfinidad();
	}
	
}
