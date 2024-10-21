/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.AltaSolicitudAdhesionView;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.ConsultaGrupoAfinidadView;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.view.SolicitudCambioAfinidadView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

/**
 * The Interface CambioGrupoAfinidadManager.
 */
public interface CambioGrupoAfinidadManager {

	/**
	 * Consultar grupo afinidad.
	 *
	 * @return the respuesta
	 */
	Respuesta<ConsultaGrupoAfinidadView> consultarGrupoAfinidad();
	
	/**
	 * Obtener datos iniciales solicitud.
	 *
	 * @return the respuesta
	 */
	Respuesta<SolicitudCambioAfinidadView> obtenerDatosInicialesSolicitud();
	
	/**
	 * Alta solicitud adhesion.
	 *
	 * @param nroSocioAdvantage
	 *            the nro socio advantage
	 * @return the respuesta
	 */
	Respuesta<AltaSolicitudAdhesionView> altaSolicitudAdhesion(String nroSocioAdvantage);
	
	/**
	 * Generar comprobante cambio grupo afinidad.
	 *
	 * @return the respuesta
	 */
	Respuesta<ReporteView> generarComprobanteCambioGrupoAfinidad();
	
	Respuesta<String> obtenerGrupoAfinidadParaFlujos();
}
