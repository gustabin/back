/*
 * 
 */
package ar.com.santanderrio.obp.servicios.segmento.dao.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.segmento.IClientData;

/**
 * Obtener el servicio para consultar el ws de Alertas CRM.
 * 
 * @author B025331
 *
 */
@Component("segmentoCliente")
public class SegmentoClienteGestionarWSImpl extends GestionarWSAbstract<IClientData> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return "SEGMENTO";
	}

}
