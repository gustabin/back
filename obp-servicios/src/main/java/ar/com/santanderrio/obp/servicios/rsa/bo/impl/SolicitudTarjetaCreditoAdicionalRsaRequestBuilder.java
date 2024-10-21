/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.AccountData;
import ar.com.santanderrio.obp.generated.webservices.rsa.AccountOwnershipType;
import ar.com.santanderrio.obp.generated.webservices.rsa.AccountRelationType;
import ar.com.santanderrio.obp.generated.webservices.rsa.AccountType;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransactionData;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosConfirmadosDelSolicitanteView;

/**
 * The Class SolicitudTarjetaCreditoAdicionalRsaRequestBuilder.
 */
public class SolicitudTarjetaCreditoAdicionalRsaRequestBuilder extends AbstractRsaRequestBuilder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilder#build(ar.
	 * com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO)
	 */
	@Override
	public EventData build(RsaDTO operacionDeRiesgo) {
		EventData ed = new EventData();
		DatosConfirmadosDelSolicitanteView operacion = (DatosConfirmadosDelSolicitanteView) operacionDeRiesgo;
		ed.setEventType(EventType.REQUEST_NEW_CARD);
		ed.setTransactionData(generarTransactionalDataSolicitudCreditoAdicional(operacion));
		return ed;
	}

	/**
	 * Generar transaction data solicitud credito adicional.
	 *
	 * @param operacion
	 *            the operacion
	 * @return the transaction data
	 */
	private TransactionData generarTransactionalDataSolicitudCreditoAdicional(
			DatosConfirmadosDelSolicitanteView operacion) {
		TransactionData data = new TransactionData();
		AccountData otherAccount = new AccountData();
		otherAccount.setAccountOwnershipType(AccountOwnershipType.BUSINESS);
		otherAccount.setAccountRelationType(AccountRelationType.PRIMARY_OWNER);
		otherAccount.setAccountType(AccountType.CREDIT_CARD);
		otherAccount.setClientDefinedAccountType("RECHARGEABLE");
		data.setOtherAccountData(otherAccount);
		return data;
	}
}
