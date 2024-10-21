/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.sei.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.sei.EstadisticaSEI;
import ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaPorAliasSEI;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.TransferenciaPorAliasManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * The Class TransferenciaSEIImpl.
 */
@Component("transferenciasPorAliasSEI")
public class TransferenciaPorAliasSEIImpl extends EstadisticaSEI<TransferenciaView>
		implements TransferenciaPorAliasSEI {

	/** The transferencia manager por alias. */
	@Autowired
	private TransferenciaPorAliasManager transferenciaPorAliasManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.transferencias.sei.TransferenciaSEI#
	 * getNuevaTransferencia(ar.com.santanderrio.obp.servicios.transferencias.
	 * web.view.TransferenciaView)
	 */
	@Override
	public Respuesta<TransferenciaView> solicitarNuevaTransferencia(TransferenciaView transferencia,
			org.apache.cxf.jaxrs.ext.MessageContext mc) {
		return transferenciaPorAliasManager.solicitarNuevaTransferencia(transferencia,
				mc.getHttpHeaders().getRequestHeaders().get("User-Agent").get(0));
	}

}
