/*
 * 
 */
package ar.com.santanderrio.obp.servicios.producto.dao;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.productos.WSGC;

/**
 * The Class SolicitudProductoGestionarWSImpl.
 */
@Component("solicitudProductoWS")
public class SolicitudProductoGestionarWSImpl extends GestionarWSAbstract<WSGC> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return "SOLICITUDES";
	}

}
