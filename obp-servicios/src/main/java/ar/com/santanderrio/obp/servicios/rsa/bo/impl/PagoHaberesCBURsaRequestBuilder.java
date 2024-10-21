/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import java.text.ParseException;
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
import ar.com.santanderrio.obp.generated.webservices.rsa.OtherAccountBankType;
import ar.com.santanderrio.obp.generated.webservices.rsa.OtherAccountOwnershipType;
import ar.com.santanderrio.obp.generated.webservices.rsa.OtherAccountType;
import ar.com.santanderrio.obp.generated.webservices.rsa.Schedule;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransactionData;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransferMediumType;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobantePagoHaberesCBUEntity;

import static ar.com.santanderrio.obp.servicios.home.web.manager.impl.SeccionSolicitudesHomeManagerImpl.LOGGER;

/**
 * The Class PagoHaberesCBURsaRequestBuilder.
 */
public class PagoHaberesCBURsaRequestBuilder extends AbstractRsaRequestBuilder {

	private static final String PAGO_SUELDO = "Pago_Sueldo";

	public static final String CANT_DIAS_ULTIMO_CAMBIO_MAIL_CUSTOM_FACT = "cantDiasUltimoCambioMail";

	public static final String CANT_DIAS_ANTIGUEDAD_EMPLEADO = "CantDiasAntiguedadEmpleado";

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
		ed.setEventType(EventType.PAYMENT);
		// ed.setClientDefinedEventType(RSAConstants.EVT_CLTDEF_PAGODESUELDOS);
		// // Modificacion 20140910
		ComprobantePagoHaberesCBUEntity comprobantePagoHaberesCBU = (ComprobantePagoHaberesCBUEntity) operacionDeRiesgo;
		ed.setClientDefinedAttributeList(generarClientDefinedAttributeListPagoSueldo(operacionDeRiesgo, comprobantePagoHaberesCBU));
		TransactionData td = generarTransactionDataNuevoPago(comprobantePagoHaberesCBU);
		ed.setTransactionData(td);
		return ed;
	}

	/**
	 * Generar transaction data nuevo pago.
	 *
	 * @param comprobantePagoHaberesCBU
	 *            the comprobante pago haberes CBU
	 * @return the transaction data
	 */
	private TransactionData generarTransactionDataNuevoPago(ComprobantePagoHaberesCBUEntity comprobantePagoHaberesCBU) {

		TransactionData td = new TransactionData();
		// estimatedDeliveryDate 
		td.setEstimatedDeliveryDate(DATE_FORMATTER.format(new Date()));
		// executionSpeed
		td.setExecutionSpeed(ExecutionSpeed.REAL_TIME);

		// amount - currency
		Amount amt = new Amount();
		amt.setAmount(new Long(String.format("%.2f",Double.parseDouble(comprobantePagoHaberesCBU.getDatosDestinatarioView().getImporte()
                .replace(",", "."))).replace(".", "").replace(" ", "")));
		amt.setCurrency(DivisaEnum.PESO.getCodigo());
		td.setAmount(amt);

		// Cuenta origen
		AccountData ad = new AccountData();
		Amount amtBalance = new Amount();
	    amtBalance.setAmount(new Long(comprobantePagoHaberesCBU.getDatosDestinatarioView().getImporteCuentaOrigen().replace(",", ".").replace(".", "")));
		amtBalance.setCurrency(DivisaEnum.PESO.getCodigo());
		ad.setAccountBalance(amtBalance);

		// accountNumber
		ad.setAccountNumber(comprobantePagoHaberesCBU.getDatosDestinatarioView().getCuentaOrigen());

		// accountNickName
		// destinatario
		ad.setAccountNickName(comprobantePagoHaberesCBU.getDatosDestinatarioView().getNombreDestinatario());
		// accountType
		ad.setAccountType(AccountType.USER_DEFINED);
		td.setMyAccountData(ad);

		// Cuenta Destino
		AccountData otherAccount = new AccountData();
		otherAccount.setAccountNickName(comprobantePagoHaberesCBU.getDatosDestinatarioView().getNombreDestinatario());
		otherAccount.setAccountNumber(comprobantePagoHaberesCBU.getDatosDestinatarioView().getNumeroCBUDestino());
		td.setOtherAccountData(otherAccount);

		// OtherAccountBankType
		td.setOtherAccountBankType(OtherAccountBankType.SAME_BANK);
		// otherAccountOwnershipType
		td.setOtherAccountOwnershipType(OtherAccountOwnershipType.ME_TO_YOU);
		// otherAccountType
		td.setOtherAccountType(OtherAccountType.PERSONAL_ACCOUNT);
		// schedule
		td.setSchedule(Schedule.IMMEDIATE);
		// transferMediumType
		td.setTransferMediumType(TransferMediumType.BALANCE_TRANSFER);

		return td;
	}
	
	private FactList generarClientDefinedAttributeListPagoSueldo(RsaDTO operacionDeRiesgo, ComprobantePagoHaberesCBUEntity comprobantePagoHaberesCBU) {
		FactList factList = new FactList();
		factList.getFact().add(generarClientDefinedFactPagoSueldo(operacionDeRiesgo));
		
		if(comprobantePagoHaberesCBU.getCantDiasUltimoCambioClave() != null) {
			 ClientDefinedFact clave = new ClientDefinedFact();
			 clave.setName("cantDiasUltimoCambioClave");
			 clave.setValue(String.valueOf(comprobantePagoHaberesCBU.getCantDiasUltimoCambioClave()));
			 clave.setDataType(DataType.INTEGER);
			 factList.getFact().add(clave);
		}
		
		if(comprobantePagoHaberesCBU.getCantDiasUltimoCambioToken() != null) {
			ClientDefinedFact token = new ClientDefinedFact();
			token.setName("cantDiasUltimoCambioToken");
			token.setValue(String.valueOf(comprobantePagoHaberesCBU.getCantDiasUltimoCambioToken()));
			token.setDataType(DataType.INTEGER);
			factList.getFact().add(token);
		}
		
		ClientDefinedFact token = new ClientDefinedFact();
		token.setName("cuil");
		token.setValue(comprobantePagoHaberesCBU.getDatosDestinatarioView().getCuilCuitDestinatario());
		token.setDataType(DataType.STRING);
		factList.getFact().add(token);

		ClientDefinedFact scoringDestinatario = new ClientDefinedFact();
		scoringDestinatario.setName("scoringDestinatario");
		scoringDestinatario.setValue(String.valueOf(comprobantePagoHaberesCBU.getScoringDestinatario()));
		scoringDestinatario.setDataType(DataType.FLOAT);
		factList.getFact().add(scoringDestinatario);

		if(comprobantePagoHaberesCBU.getCantDiasUltimoCambioMail() != -1) {
			ClientDefinedFact antiguedadMail = new ClientDefinedFact();
			antiguedadMail.setName(CANT_DIAS_ULTIMO_CAMBIO_MAIL_CUSTOM_FACT);
			antiguedadMail.setDataType(DataType.INTEGER);
			antiguedadMail.setValue(String.valueOf(comprobantePagoHaberesCBU.getCantDiasUltimoCambioMail()));
			factList.getFact().add(antiguedadMail);
		}

		ClientDefinedFact antiguedadEmpleado = new ClientDefinedFact();
		antiguedadEmpleado.setName(CANT_DIAS_ANTIGUEDAD_EMPLEADO);
		antiguedadEmpleado.setValue("0");
		antiguedadEmpleado.setDataType(DataType.INTEGER);
		factList.getFact().add(antiguedadEmpleado);

		BiocatchRsaRequestBuilder.buildBiocatchRsaRequestBuilder(comprobantePagoHaberesCBU.getBiocatchResponseData(), factList);
		return factList;
	}

	private ClientDefinedFact generarClientDefinedFactPagoSueldo(RsaDTO operacionDeRiesgo) {
		ClientDefinedFact customFact = new ClientDefinedFact();
		customFact.setName(PAGO_SUELDO);
		customFact.setValue(String.valueOf(true));
		customFact.setDataType(DataType.BOOLEAN);
		return customFact;
	}
	
}