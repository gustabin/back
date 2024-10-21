/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.manager;

import org.apache.cxf.jaxrs.ext.MessageContext;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TenenciasPMCView;

/**
 * The Interface ResumenImpositivoTokenManager.
 */
public interface ResumenImpositivoTokenManager {

	/**
	 * Obtener el token junto a la url que se debe utilizar para abrir pago mis
	 * cuentas.
	 *
	 * @param tenenciasView
	 *            the tenencias view
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
    Respuesta<TenenciasPMCView> obtenerTokenEncriptado(TenenciasPMCView tenenciasView, MessageContext mc);

    /**
	 * Verificar que en caso de que el cliente no tenga asociados token, tarjeta
	 * de coordenadas ni tarjeta de débito recuperar el mensaje con código 1575
	 * en caso de tenerlos retornar true.
	 *
	 * @return the respuesta
	 */
    Respuesta<Boolean> habilitado();
	
}
