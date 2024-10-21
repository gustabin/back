/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import java.util.Date;

import ar.com.santanderrio.obp.generated.webservices.rsa.AccountData;
import ar.com.santanderrio.obp.generated.webservices.rsa.AccountType;
import ar.com.santanderrio.obp.generated.webservices.rsa.Amount;
import ar.com.santanderrio.obp.generated.webservices.rsa.ClientDefinedFact;
import ar.com.santanderrio.obp.generated.webservices.rsa.DataType;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.generated.webservices.rsa.ExecutionSpeed;
import ar.com.santanderrio.obp.generated.webservices.rsa.FactList;
import ar.com.santanderrio.obp.generated.webservices.rsa.OtherAccountOwnershipType;
import ar.com.santanderrio.obp.generated.webservices.rsa.OtherAccountType;
import ar.com.santanderrio.obp.generated.webservices.rsa.Schedule;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransactionData;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransferMediumType;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaRSADTO;
import ar.com.santanderrio.obp.servicios.nuevopago.manager.impl.NuevoPagoUtils;

/**
 * The Class NuevaRecargaRsaRequestBuilder.
 * 
 * @author florencia.n.martinez
 */
public class NuevaRecargaRsaRequestBuilder extends AbstractRsaRequestBuilder {

	/** The Constant CURRENCY. */
	private static final String CURRENCY = "ARS";

	/** The Constant NEW_PAYMENT. */
	private static final String NEW_PAYMENT = "NEW_PAYMENT";
	
	private static final String SCHEDULED_PAYMENT = "SCHEDULED_PAYMENT";

	/** The Constant CELULAR_MYA. */
	private static final String CELULAR_MYA = "celularmya";

	/**
	 * Construye el objeto EventData para nueva recarga.
	 *
	 * @param operacionDeRiesgo
	 *            the operacion de riesgo
	 * @return the event data
	 */
	@Override
	public EventData build(RsaDTO operacionDeRiesgo) {
		NuevaRecargaRSADTO rsaDTO = (NuevaRecargaRSADTO) operacionDeRiesgo;
		EventData ed = new EventData();
		ed.setClientDefinedAttributeList(generarClientDefinedAttributeListNuevaRecarga(rsaDTO));
		ed.setClientDefinedEventType(rsaDTO.isFromCalendario() ? SCHEDULED_PAYMENT: NEW_PAYMENT);
		ed.setEventType(EventType.PAYMENT);
		ed.setTransactionData(generarTransactionDataNuevaRecarga(rsaDTO));
		return ed;
	}

	/**
	 * Genera el client defined attribute list para nueva recarga.
	 *
	 * @param rsaDTO
	 *            the rsa DTO
	 * @return the fact list
	 */
	private FactList generarClientDefinedAttributeListNuevaRecarga(NuevaRecargaRSADTO rsaDTO) {
		FactList factList = new FactList();
		factList.getFact().add(generarClienteDefinedFactNuevaRecaga(rsaDTO));
		
		if(rsaDTO.getCantDiasUltimoCambioClave() != null) {
			 ClientDefinedFact clave = new ClientDefinedFact();
			 clave.setName("cantDiasUltimoCambioClave");
			 clave.setValue(String.valueOf(rsaDTO.getCantDiasUltimoCambioClave()));
			 clave.setDataType(DataType.INTEGER);
			 factList.getFact().add(clave);
		}
		
		if(rsaDTO.getCantDiasUltimoCambioToken() != null) {
			ClientDefinedFact token = new ClientDefinedFact();
			token.setName("cantDiasUltimoCambioToken");
			token.setValue(String.valueOf(rsaDTO.getCantDiasUltimoCambioToken()));
			token.setDataType(DataType.INTEGER);
			factList.getFact().add(token);
		}
		
		ClientDefinedFact token = new ClientDefinedFact();
		token.setName("Empresa_Servicio");
		token.setValue(rsaDTO.getNombreDestinatario());
		token.setDataType(DataType.STRING);
		factList.getFact().add(token);
		
		return factList;
	}

	/**
	 * Genera el cliente defined fact para nueva recaga.
	 *
	 * @param rsaDTO
	 *            the rsa DTO
	 * @return the client defined fact
	 */
	private ClientDefinedFact generarClienteDefinedFactNuevaRecaga(NuevaRecargaRSADTO rsaDTO) {
		ClientDefinedFact celularMyA = new ClientDefinedFact();
		celularMyA.setName(CELULAR_MYA);
		celularMyA.setValue(String.valueOf(rsaDTO.getTieneCelularMyA()));
		celularMyA.setDataType(DataType.BOOLEAN);
		return celularMyA;
	}

	/**
	 * Genera el transaction data para nueva recarga.
	 *
	 * @param rsaDTO
	 *            the rsa DTO
	 * @return the transaction data
	 */
	private TransactionData generarTransactionDataNuevaRecarga(NuevaRecargaRSADTO rsaDTO) {
		TransactionData data = new TransactionData();
		data.setAmount(generarAmountNuevaRecarga(rsaDTO.getMonto()));
		data.setDueDate(DATE_FORMATTER.format(new Date()));
		data.setEstimatedDeliveryDate(DATE_FORMATTER.format(new Date()));
		data.setExecutionSpeed(ExecutionSpeed.REAL_TIME);
		data.setMyAccountData(generarAccountDataNuevaRecarga(rsaDTO));
		data.setOtherAccountData(generarOtherAccountDataNuevaRecarga(rsaDTO));
		data.setOtherAccountOwnershipType(OtherAccountOwnershipType.ME_TO_YOU);
		data.setOtherAccountType(OtherAccountType.BILLER);
		data.setSchedule(Schedule.IMMEDIATE);
		data.setTransferMediumType(TransferMediumType.BILLPAY_ELEC);
		
        AccountData otherAccountData = new AccountData();
        String nombreDestinatario = rsaDTO.getNombreDestinatario() + "_" + rsaDTO.getCodigoPagoElectronico();
		if (rsaDTO.getNombreDestinatario() != null && rsaDTO.getNombreDestinatario().equals(NuevoPagoUtils.PAGOS_AFIP_VEP_SIN_PARENTESIS)) {
			nombreDestinatario = NuevoPagoUtils.PAGOS_AFIP_VEP_REPLACE + "_" + rsaDTO.getCodigoPagoElectronico();
		}
		otherAccountData.setAccountNickName(nombreDestinatario);
        otherAccountData.setAccountNumber(nombreDestinatario);
        data.setOtherAccountData(otherAccountData);
        
		return data;
		
	}

	/**
	 * Genera el other account data para nueva recarga.
	 *
	 * @param rsaDTO
	 *            the rsa DTO
	 * @return the account data
	 */
	private AccountData generarOtherAccountDataNuevaRecarga(NuevaRecargaRSADTO rsaDTO) {
		AccountData otherAccountData = new AccountData();
		otherAccountData.setAccountNickName(rsaDTO.getNombreDestinatario());
		return otherAccountData;
	}

	/**
	 * Genera el account data para nueva recarga.
	 *
	 * @param rsaDTO
	 *            the rsa DTO
	 * @return the account data
	 */
	private AccountData generarAccountDataNuevaRecarga(NuevaRecargaRSADTO rsaDTO) {
		AccountData accountData = new AccountData();
		accountData.setAccountBalance(generarAmountNuevaRecarga(rsaDTO.getCuentaOrigen().getSaldo()));
		accountData.setAccountNickName(rsaDTO.getCuentaOrigen().getNombreCliente());
		accountData.setAccountNumber(rsaDTO.getCuentaOrigen().getNumero());
		accountData.setAccountType(AccountType.USER_DEFINED);
		return accountData;
	}

	/**
	 * Genera un amount para nueva recarga.
	 *
	 * @param importe
	 *            the importe
	 * @return the amount
	 */
	private Amount generarAmountNuevaRecarga(Long importe) {
		Amount amount = new Amount();
		amount.setAmount(importe);
		amount.setCurrency(CURRENCY);
		return amount;
	}

}
