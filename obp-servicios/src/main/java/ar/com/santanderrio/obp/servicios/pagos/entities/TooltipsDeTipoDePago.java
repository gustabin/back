/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;

/**
 * Construye un mapa de mensajes para tooltips. Menajes: 1380 PAGO_MANUAL "Este
 * pago se debe realizar manualmente" 1381 PAGO_DEBITADO_AUTOMATICO "Este pago
 * se debita automáticamente todos los meses" 1382 PAGO_UNICO_PROGRAMADO "Este
 * pago se efectuará por única vez el día de la programación"
 *
 * @author B041299
 * @see CodigoMensajeConstantes
 */
public class TooltipsDeTipoDePago {

	/** The tool tips. */
	Map<String, String> toolTips;

	/** The mensaje manager. */
	@Autowired
	private MensajeManager mensajeManager;

	/**
	 * construye el mapa con los mensajes listos.
	 */
	public TooltipsDeTipoDePago() {
		this.toolTips = new HashMap<String, String>();
	}

	/**
	 * List.
	 *
	 * @return value list
	 */
	public ArrayList<String> list() {
		return (ArrayList<String>) this.toolTips.values();
	}
}
