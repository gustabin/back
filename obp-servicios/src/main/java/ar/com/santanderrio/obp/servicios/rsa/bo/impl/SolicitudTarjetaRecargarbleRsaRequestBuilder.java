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
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.ComprobanteAltaSolicitudTarjetaRecargableView;

/**
 * The Class SolicitudTarjetaRecargarbleRsaRequestBuilder.
 */
public class SolicitudTarjetaRecargarbleRsaRequestBuilder extends AbstractRsaRequestBuilder {

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
		ComprobanteAltaSolicitudTarjetaRecargableView operacion = (ComprobanteAltaSolicitudTarjetaRecargableView) operacionDeRiesgo;
		ed.setEventType(EventType.REQUEST_NEW_CARD);
		ed.setTransactionData(generarTransactionalDataSolicitudTarjetaRecargable(operacion));
		return ed;
	}

	/**
	 * Generar transaction data solicitud tarjeta recargable.
	 *
	 * @param operacion
	 *            the operacion
	 * @return the transaction data
	 */
	private TransactionData generarTransactionalDataSolicitudTarjetaRecargable(
			ComprobanteAltaSolicitudTarjetaRecargableView operacion) {
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
