/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.manager;

import java.util.List;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.CambioDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.ComprobanteCambioDomicilioView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.DatosDomicilioView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomiciliosDisponiblesView;

/**
 * Interface CambioDomicilioManager.
 *
 * @author Silvina_Luque
 */
public interface CambioDomicilioManager {

	/**
	 * Obtener datos adicionales de telefono y domicilio.
	 *
	 * @param cambioDomicilioView
	 *            the cambio domicilio view
	 * @return the respuesta
	 */
	Respuesta<FeedbackMensajeView> obtenerInfoAdicionalDomTel(CambioDomicilioView cambioDomicilioView);

	/**
	 * Guardar un cambio de domicilio.
	 *
	 * @param cambioDomicilioView
	 *            the cambio domicilio view
	 * @return the respuesta
	 */
	Respuesta<CambioDomicilioView> guardarCambioDomicilio(CambioDomicilioView cambioDomicilioView);

	/**
	 * Normalizar domicilio.
	 *
	 * @param domicilioModificadoView
	 *            the domicilio modificado view
	 * @return Lista de domicilios normalizados
	 */
	Respuesta<List<CambioDomicilioView>> normalizarDomicilio(CambioDomicilioView domicilioModificadoView);

	/**
	 * Consultar los domicilios del cliente.
	 *
	 * @param isFromReimpresion
	 *            the is from reimpresion
	 * @return Lista Domicilios y provincias
	 */
	Respuesta<DatosDomicilioView> obtenerDomiciliosContacto(Boolean isFromReimpresion);

	/**
	 * Comprobante CambioDomicilio.
	 *
	 * @return Comprobante View
	 */
	Respuesta<ComprobanteCambioDomicilioView> verComprobante();

	/**
	 * descargarComprobante.
	 *
	 * @return ReporteView
	 */
	Respuesta<ReporteView> descargarComprobante();

	/**
	 * Consulta de domicilio principal y laboral.
	 *
	 * @return DomiciliosDisponiblesView
	 */
	public Respuesta<DomiciliosDisponiblesView> consultarDomicilioPrincipalLaboral();
	
}
