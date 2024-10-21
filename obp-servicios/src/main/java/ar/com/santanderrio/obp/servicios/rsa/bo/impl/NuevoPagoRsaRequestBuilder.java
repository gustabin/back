/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.generated.webservices.rsa.AccountData;
import ar.com.santanderrio.obp.generated.webservices.rsa.AccountType;
import ar.com.santanderrio.obp.generated.webservices.rsa.Amount;
import ar.com.santanderrio.obp.generated.webservices.rsa.ClientDefinedFact;
import ar.com.santanderrio.obp.generated.webservices.rsa.DataType;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.generated.webservices.rsa.ExecutionSpeed;
import ar.com.santanderrio.obp.generated.webservices.rsa.FactList;
import ar.com.santanderrio.obp.generated.webservices.rsa.Schedule;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransactionData;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransferMediumType;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaRSADTO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.Pago;
import ar.com.santanderrio.obp.servicios.nuevopago.manager.impl.NuevoPagoUtils;
import ar.com.santanderrio.obp.servicios.rsa.common.RSAConstants;

/**
 * Esta clase se encarga de construir el request para validar con RSA.
 *
 * @author ignacio.valek
 * @see {@link AbstractRsaRequestBuilder}
 * @since Nov 11, 2016
 */
public class NuevoPagoRsaRequestBuilder extends AbstractRsaRequestBuilder {

	/** The Constant USER_DEFINE para ws:accountType. */
	private static final String USER_DEFINE = "USER_DEFINE";
	
	private static final String CELULAR_MYA = "celularmya";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilder#build(ar.
	 * com.santanderrio.obp.servicios.comun.autentificacion.entities.rsaDTO)
	 */
	@Override
	public EventData build(RsaDTO operacionDeRiesgo) {
		EventData ed = new EventData();
		ed.setEventType(EventType.PAYMENT);
		Pago pago = (Pago) operacionDeRiesgo;
		TransactionData td = generarTransactionDataNuevoPago(pago);
		ed.setClientDefinedEventType(RSAConstants.EVT_CLTDEF_PAGOAGENDADO);
		ed.setTransactionData(td);
		ed.setClientDefinedAttributeList(generarClientDefinedAttributeListNuevaRecarga(pago));

		return ed;
	}

	/**
	 * Generar transaction data nuevo pago.
	 *
	 * @param pago
	 *            the pago
	 * @return the transaction data
	 */
	private TransactionData generarTransactionDataNuevoPago(Pago pago) {
		TransactionData data = new TransactionData();
		Amount amount = new Amount();
		//amount.setAmount(pago.getAmount());
		String samount = pago.getAmount().replace(".", "").replace(",", ".");
		amount.setAmount(new BigDecimal(samount).multiply(FACTOR_CENTAVOS).longValue());
		amount.setCurrency(pago.getDivisa().getCodigo());
		data.setAmount(amount);
		data.setExecutionSpeed(ExecutionSpeed.FEW_HOURS);
		data.setSchedule(Schedule.IMMEDIATE);
		data.setEstimatedDeliveryDate(DATE_FORMATTER.format(pago.getDeliveryDate()));
		data.setDueDate(pago.getDueDate() == null ? null : DATE_FORMATTER.format(pago.getDueDate()));
		//
		
        AccountData myAccountData = new AccountData();
        data.setMyAccountData(myAccountData);
        Amount myAccountBalance = new Amount();
        myAccountData.setAccountBalance(myAccountBalance);
        String saldoFormateado = pago.getSaldoCuentaOrigen().replace(".", "").replace(",", ".");
        myAccountBalance.setAmount((saldoFormateado != "" ? new BigDecimal(saldoFormateado).multiply(FACTOR_CENTAVOS).longValue() : null));        
        myAccountBalance.setCurrency(pago.getDivisa().getCodigo());
        myAccountData.setAccountNumber(pago.getNumeroCuentaSinFormato());
        myAccountData.setAccountNickName(pago.getAccountNickNameOrigen());
        myAccountData.setAccountType(AccountType.USER_DEFINED);

        AccountData otherAccountData = new AccountData();
        data.setOtherAccountData(otherAccountData);
//        otherAccountData.setAccountNickName(pago.getAccountNickNameDestino());
//        otherAccountData.setAccountNumber(pago.getAccountNumber());
        String nombreDestinatario = pago.getNombreFantasia() + "_" + pago.getIdentificacionCliente();
		if (pago.getNombreFantasia() != null && pago.getNombreFantasia().equals(NuevoPagoUtils.PAGOS_AFIP_VEP_SIN_PARENTESIS)) {
			nombreDestinatario = NuevoPagoUtils.PAGOS_AFIP_VEP_REPLACE + "_" + pago.getIdentificacionCliente();
		}
		otherAccountData.setAccountNickName(nombreDestinatario);
        otherAccountData.setAccountNumber(nombreDestinatario);
        data.setTransferMediumType(TransferMediumType.BALANCE_TRANSFER);

		return data;
	}
	
	private FactList generarClientDefinedAttributeListNuevaRecarga(Pago pago) {
		FactList factList = new FactList();
		factList.getFact().add(generarClienteDefinedFactNuevaRecaga(pago));
		
		if(pago.getCantDiasUltimoCambioClave() != null) {
			 ClientDefinedFact clave = new ClientDefinedFact();
			 clave.setName("cantDiasUltimoCambioClave");
			 clave.setValue(String.valueOf(pago.getCantDiasUltimoCambioClave()));
			 clave.setDataType(DataType.INTEGER);
			 factList.getFact().add(clave);
		}
		
		if(pago.getCantDiasUltimoCambioToken() != null) {
			ClientDefinedFact token = new ClientDefinedFact();
			token.setName("cantDiasUltimoCambioToken");
			token.setValue(String.valueOf(pago.getCantDiasUltimoCambioToken()));
			token.setDataType(DataType.INTEGER);
			factList.getFact().add(token);
		}
		
		ClientDefinedFact token = new ClientDefinedFact();
		token.setName("Empresa_Servicio");
		token.setValue(pago.getNombreFantasia());
		token.setDataType(DataType.STRING);
		factList.getFact().add(token);
		
		ClientDefinedFact factTipoDesafio = new ClientDefinedFact();
		factTipoDesafio.setName("tipodesafio");
		factTipoDesafio.setValue("TOKEN");
		factTipoDesafio.setDataType(DataType.STRING);
		
		return factList;
	}
	
	private ClientDefinedFact generarClienteDefinedFactNuevaRecaga(Pago pago) {
		ClientDefinedFact celularMyA = new ClientDefinedFact();
		celularMyA.setName(CELULAR_MYA);
		celularMyA.setValue(String.valueOf(pago.getTieneCelularMyA()));
		celularMyA.setDataType(DataType.BOOLEAN);
		return celularMyA;
	}

}
