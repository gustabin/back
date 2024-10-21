package ar.com.santanderrio.obp.servicios.debinws.manager;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.debinws.view.CuentasAdheridasDebinOutView;
import ar.com.santanderrio.obp.servicios.debinws.view.SolicitarDebinView;
import ar.com.santanderrio.obp.servicios.debinws.view.ValidarCbuAliasDebinInView;
import ar.com.santanderrio.obp.servicios.debinws.view.ValidarCbuAliasDebinOutView;


/**
 * The Interface DebinWSSolicitudesManager.
 */
public interface DebinWSSolicitudesManager  {
    
    /**
	 * 
	 * Inicio solicitud de debin, validacion de cuentas adheridas y token.
	 *
	 * @return the respuesta
	 */
    Respuesta<CuentasAdheridasDebinOutView> ingresoSolicitarDebin();
    
    /**
	 * Validacion del cbu o alias de destino que se ingreso.
	 *
	 * @param validarCbuAliasInView
	 *            the validar cbu alias in view
	 * @param agent
	 *            the agent
	 * @return the respuesta
	 */
    Respuesta<ValidarCbuAliasDebinOutView> validarCbuAliasDebin(ValidarCbuAliasDebinInView validarCbuAliasInView,String agent);

    /**
	 * Generacion de la solicitud de debin.
	 *
	 * @param solicitarDebinInView
	 *            the solicitar debin in view
	 * @return the respuesta
	 */
    Respuesta<SolicitarDebinView> solicitarDebin(SolicitarDebinView solicitarDebinInView);

    /**
	 * Descargar comprobante solicitar.
	 *
	 * @return the respuesta
	 */
    Respuesta<Reporte> descargarComprobanteSolicitar();
    
}
