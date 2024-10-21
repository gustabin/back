/**
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.dao.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.direccionador.IDireccionador;

/**
 * The Class DireccionadorGestionarWSImpl.
 *
 * @author ITResources
 */
@Component("gestionDireccionador")
public class DireccionadorGestionarWSImpl extends GestionarWSAbstract<IDireccionador> {

	/**
	 * Obtiene el codigo del WS.
	 *
	 * @return the codigo WS
	 */
	@Override
	public String getCodigoWS() {
		return "CONSULTACITASVC";
	}

}
