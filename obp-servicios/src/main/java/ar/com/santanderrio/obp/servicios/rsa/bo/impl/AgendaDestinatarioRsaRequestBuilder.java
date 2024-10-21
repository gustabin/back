/**
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.AccountData;
import ar.com.santanderrio.obp.generated.webservices.rsa.Amount;
import ar.com.santanderrio.obp.generated.webservices.rsa.ClientDefinedFact;
import ar.com.santanderrio.obp.generated.webservices.rsa.DataType;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.generated.webservices.rsa.ExecutionSpeed;
import ar.com.santanderrio.obp.generated.webservices.rsa.FactList;
import ar.com.santanderrio.obp.generated.webservices.rsa.OtherAccountBankType;
import ar.com.santanderrio.obp.generated.webservices.rsa.OtherAccountOwnershipType;
import ar.com.santanderrio.obp.generated.webservices.rsa.OtherAccountType;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransactionData;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransferMediumType;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioRSADTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * The Class AgendaDestinatarioRsaRequestBuilder.
 *
 * @author sabrina.cis
 */
public class AgendaDestinatarioRsaRequestBuilder extends AbstractRsaRequestBuilder {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilder#build(ar.
	 * com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO)
	 */
	@Override
	public EventData build(RsaDTO operacionDeRiesgo) {

		AgendaDestinatarioRSADTO rsaDTO = (AgendaDestinatarioRSADTO) operacionDeRiesgo;
		EventData ed = new EventData();
		ed.setEventType(EventType.ADD_PAYEE);
		ed.setClientDefinedAttributeList(generarClientDefinedAttributeListAgendaDestinatario(rsaDTO));
		ed.setTransactionData(generarTransactionDataTransferencia(rsaDTO));
		return ed;
	}

	/**
	 * Generar transaction data transferencia.
	 *
	 * @param rsaDTO
	 *            the rsa DTO
	 * @return the transaction data
	 */
	private TransactionData generarTransactionDataTransferencia(AgendaDestinatarioRSADTO rsaDTO) {

		Amount amount = new Amount();
		amount.setCurrency(CURRENCY);

		AccountData otherAccountData = new AccountData();
		otherAccountData.setAccountNickName(rsaDTO.getNombreDestinatario());
		otherAccountData.setAccountNumber(rsaDTO.getNumeroCuentaDestinatario());

		TransactionData data = new TransactionData();

		data.setAmount(amount);
		data.setExecutionSpeed(ExecutionSpeed.REAL_TIME);
		if (TipoAgendaEnum.AGENDA_RIO.equals(rsaDTO.getTipoDestinatario())) {
			data.setOtherAccountBankType(OtherAccountBankType.SAME_BANK);
		} else if (TipoAgendaEnum.AGENDA_OTROS_BANCOS.equals(rsaDTO.getTipoDestinatario())) {
			data.setOtherAccountBankType(OtherAccountBankType.OTHER_BANK);
		}
		data.setOtherAccountData(otherAccountData);
		data.setOtherAccountOwnershipType(rsaDTO.getDestinatarioPropio() ? OtherAccountOwnershipType.ME_TO_ME
				: OtherAccountOwnershipType.ME_TO_YOU);
		data.setOtherAccountType(OtherAccountType.PERSONAL_ACCOUNT);
		data.setTransferMediumType(TransferMediumType.BALANCE_TRANSFER);

		return data;
	}
	
	private FactList generarClientDefinedAttributeListAgendaDestinatario(AgendaDestinatarioRSADTO rsaDTO) {
		FactList factList = new FactList();

		if (rsaDTO.getCuitDestinatario() != null) {
			 ClientDefinedFact customFactCuit = new ClientDefinedFact();
			 customFactCuit.setName("cuit");
			 customFactCuit.setValue(rsaDTO.getCuitDestinatario());
			 customFactCuit.setDataType(DataType.STRING);
			 factList.getFact().add(customFactCuit);
		}
		
		return factList;
	}

}
