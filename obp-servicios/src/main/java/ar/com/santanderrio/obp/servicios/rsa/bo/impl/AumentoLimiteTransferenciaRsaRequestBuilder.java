/*
 *
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import ar.com.santanderrio.obp.generated.webservices.rsa.Schedule;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransactionData;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransferMediumType;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AumentoLimiteTransferenciaInOutView;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;

/**
 * The Class AumentoLimiteTransferenciaRsaRequestBuilder.
 */
public class AumentoLimiteTransferenciaRsaRequestBuilder extends AbstractRsaRequestBuilder {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRsaRequestBuilder.class);

	/** The Constant DATE_FORMATTER. */
	private static final SimpleDateFormat DATE_FORMATTER_INPUT = new SimpleDateFormat("dd/MM/yyyy");

	/** The Constant FACTOR_CENTAVOS. */
	private static final BigDecimal FACTOR_CENTAVOS = new BigDecimal(100);

	public static final String ANTIGUEDAD_CUSTOM_FACT = "antiguedad";
	public static final String CANT_DESTINO_CUSTOM_FACT = "cant_destino";
	public static final String ACUM_DESTINO_CUSTOM_FACT = "acum_destino";
	public static final String CANT_TOTAL_CUSTOM_FACT = "cant_total";
	public static final String ACUM_TOTAL_CUSTOM_FACT = "acum_total";
	public static final String CUIT_CUSTOM_FACT = "cuit";
	public static final String CANT_DIAS_ULTIMO_CAMBIO_CLAVE_CUSTOM_FACT = "cantDiasUltimoCambioClave";
	public static final String CANT_DIAS_ULTIMO_CAMBIO_TOKEN_CUSTOM_FACT = "cantDiasUltimoCambioToken";
	public static final String AUMENTO_LIMITE_CUSTOM_FACT = "Aumento_limite";
	public static final String CUIT_DIGIT = "-";
	public static final String CUIT_ORIGINANTE_CUSTOM_FACT = "CUIT_originante" ;
	public static final String SCORING_DESTINATARIO_CUSTOM_FACT = "scoringDestinatario";
	public static final String CANT_DIAS_ULTIMO_CAMBIO_MAIL_CUSTOM_FACT = "cantDiasUltimoCambioMail";

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

		AumentoLimiteTransferenciaInOutView transferencia = (AumentoLimiteTransferenciaInOutView) operacionDeRiesgo;
		ed.setEventType(EventType.PAYMENT);
		LOGGER.info("AumentoLimiteTransferenciaInOutView previo Custom Facts: {}", transferencia.toString());
		ed.setTransactionData(generarTransactionDataAumentoLimiteTransferencia(transferencia));
		ed.setClientDefinedAttributeList(generarFactListAumentoLimiteTransferencia(transferencia));
		return ed;
	}

	/**
	 * Generar transaction data solicitud aumento limite transferencia.
	 *
	 * @param transferencia
	 *            the AumentoLimiteTransferenciaInOutView
	 * @return the transaction data
	 */
	private TransactionData generarTransactionDataAumentoLimiteTransferencia(
			AumentoLimiteTransferenciaInOutView transferencia) {
		TransactionData data = new TransactionData();
		Amount amount = new Amount();
		data.setAmount(amount);
		// valor en centavos
		amount.setAmount(new BigDecimal(transferencia.getImporte()).multiply(FACTOR_CENTAVOS).longValue());
		amount.setCurrency(DivisaEnum.fromMonedaString(transferencia.getMoneda().toLowerCase()).getCodigo());

		Date fechaInput = null;
		try {
			fechaInput = DATE_FORMATTER_INPUT.parse(transferencia.getFechaEjecucion());
		} catch (ParseException e) {
			LOGGER.info("ERROR: La fecha de la transferencia vino nula");
		}
		String dueDate = (fechaInput == null ? null : DATE_FORMATTER.format(fechaInput));
		data.setDueDate(dueDate);
		data.setOtherAccountBankType(
				transferencia.getIsRioRio() ? OtherAccountBankType.SAME_BANK : OtherAccountBankType.OTHER_BANK);
		data.setOtherAccountType(OtherAccountType.PERSONAL_ACCOUNT);
		data.setSchedule(Schedule.SCHEDULED);
		data.setTransferMediumType(TransferMediumType.BALANCE_TRANSFER);
		data.setOtherAccountOwnershipType(transferencia.isCuentaPropia() ? OtherAccountOwnershipType.ME_TO_ME
				: OtherAccountOwnershipType.ME_TO_YOU);
		data.setExecutionSpeed(ExecutionSpeed.SEVERAL_DAYS);

		Date estimatedDeliveryInput = null;
		try {
			estimatedDeliveryInput = DATE_FORMATTER_INPUT.parse(transferencia.getFechaEjecucion());
		} catch (ParseException e) {
			LOGGER.info("ERROR: La fecha de la transferencia vino nula");
		}

		String estimatedDelivery = (estimatedDeliveryInput == null ? null
				: DATE_FORMATTER.format(estimatedDeliveryInput));
		data.setEstimatedDeliveryDate(estimatedDelivery);

		AccountData myAccountData = new AccountData();
		data.setMyAccountData(myAccountData);
		Amount myAccountBalance = new Amount();
		myAccountData.setAccountBalance(myAccountBalance);

		String saldoFormateado = transferencia.getSaldoCuentaOrigen().replace(".", "");
		myAccountBalance.setAmount((saldoFormateado != "" ? new Long(saldoFormateado) : null));
		myAccountBalance.setCurrency(transferencia.getMonedaAltair());

		myAccountData.setAccountNumber(transferencia.getNroCuenta());
		String nombreTitularOrigen = (transferencia.getTitular());
		myAccountData.setAccountNickName(nombreTitularOrigen);
		myAccountData.setAccountType(decodificarAccountType(transferencia.getTipoCuentaEnum()));

		AccountData otherAccountData = new AccountData();
		data.setOtherAccountData(otherAccountData);
		String cuentaDestino = transferencia.getNroCuentaDestino() != null ? transferencia.getNroCuentaDestino()
				: transferencia.getCbu();
		otherAccountData.setAccountNumber(cuentaDestino);
		otherAccountData.setAccountNickName(transferencia.getTitular());

		return data;
	}

	/**
	 * Generar fact list transferencia.
	 *
	 * @param transferencia
	 *            the request data
	 * @return the fact list
	 */
	private FactList generarFactListAumentoLimiteTransferencia(AumentoLimiteTransferenciaInOutView transferencia) {
		FactList factList = new FactList();
//		antiguedad
		addValidAntiguedadCustomFact(transferencia, factList);
//		cuit destino
		addValidCuitDestinoCustomFact(transferencia, factList);
//		cuit origen
		addValidCuitOriginanteCustomFact(transferencia, factList);
//		Aumento_limite
		addValidAumentoLimiteCustomFact(transferencia, factList);
//		cantDiasUltimoCambioClave
		addValidCustomFactCantDiasUltimoCambio(transferencia.getCantDiasUltimoCambioClave(), CANT_DIAS_ULTIMO_CAMBIO_CLAVE_CUSTOM_FACT, factList);
//		cantDiasUltimoCambioToken
		addValidCustomFactCantDiasUltimoCambio(transferencia.getCantDiasUltimoCambioToken(), CANT_DIAS_ULTIMO_CAMBIO_TOKEN_CUSTOM_FACT, factList);
//		acum_total
		addValidControlSumToFactList(transferencia, factList);
//		scoringDestinatario
		addScoringDestinatarioCustomFact(transferencia, factList);
//      cantDiasUltimoCambioMail
		addCantDiasUltimoCambioMail(transferencia, factList);

		BiocatchRsaRequestBuilder.buildBiocatchRsaRequestBuilder(transferencia.getBiocatchResponseDataDTO(), factList);

		return factList;
	}

	private static void addValidCuitDestinoCustomFact(AumentoLimiteTransferenciaInOutView transferencia, FactList factList) {
		ClientDefinedFact cuitFact = new ClientDefinedFact();
		cuitFact.setName(CUIT_CUSTOM_FACT);
		LOGGER.info("Custom Fact Cuit Destino transferencia.getCuit {}", transferencia.getCuit());
		if (transferencia.getCuit() != null && transferencia.getCuit().contains(CUIT_DIGIT)) {
			cuitFact.setValue(transferencia.getCuit());
		} else {
			cuitFact.setValue(ISBANStringUtils.agregarGuionesANumeroCuitCuil(transferencia.getCuit()));
		}
		cuitFact.setDataType(DataType.STRING);
		factList.getFact().add(cuitFact);
	}

	private static void addValidCuitOriginanteCustomFact(AumentoLimiteTransferenciaInOutView transferencia, FactList factList) {
		ClientDefinedFact cuitFact = new ClientDefinedFact();
		cuitFact.setName(CUIT_ORIGINANTE_CUSTOM_FACT);
		LOGGER.info("Custom Fact Cuit Origen transferencia.getCuitCliente {}", transferencia.getCuitCliente());
		if (transferencia.getCuitCliente() != null && transferencia.getCuitCliente().contains(CUIT_DIGIT)) {
			cuitFact.setValue(transferencia.getCuitCliente());
		} else {
			cuitFact.setValue(ISBANStringUtils.agregarGuionesANumeroCuitCuil(transferencia.getCuitCliente()));
		}
		cuitFact.setDataType(DataType.STRING);
		factList.getFact().add(cuitFact);
	}

	private static void addValidAntiguedadCustomFact(AumentoLimiteTransferenciaInOutView transferencia, FactList factList) {
		ClientDefinedFact antiguedad = new ClientDefinedFact();
		antiguedad.setName(ANTIGUEDAD_CUSTOM_FACT);

		if (transferencia.getFechaCreacionDestinatario() == null) {
			antiguedad.setValue("0");
		} else {
			String fechaCreacion = transferencia.getFechaCreacionDestinatario();
			try {
				Date fechaInicio = ISBANStringUtils.parsearTimestampIATX(fechaCreacion);
				Date fechaOperacion = null != transferencia.getFechaOperacion() && !transferencia.getFechaOperacion().isEmpty()? ISBANStringUtils.parsearTimestampIATX(transferencia.getFechaOperacion()) : new Date();
				Integer diferencia = ISBANStringUtils.diferenciaEnDias(fechaOperacion, fechaInicio);
				antiguedad.setValue(String.valueOf(diferencia));
			} catch (ParseException e) {
				LOGGER.info("ERROR: La fecha de alta / operación de transferencia se encuentra en un formato no válid", e);
			}
		}
		antiguedad.setDataType(DataType.INTEGER);
		factList.getFact().add(antiguedad);
	}

	private static void addValidAumentoLimiteCustomFact(AumentoLimiteTransferenciaInOutView transferencia, FactList factList) {
		ClientDefinedFact pedidoAumentoLimite = new ClientDefinedFact();
		pedidoAumentoLimite.setName(AUMENTO_LIMITE_CUSTOM_FACT);
		pedidoAumentoLimite.setValue(String.valueOf(transferencia.isPedidoAumentoLimite()));
		pedidoAumentoLimite.setDataType(DataType.BOOLEAN);
		factList.getFact().add(pedidoAumentoLimite);
	}

	private static void addValidCustomFactCantDiasUltimoCambio(Integer transferencia, String cantDiasUltimoCambioClave, FactList factList) {
		if (transferencia != null) {
			ClientDefinedFact clave = new ClientDefinedFact();
			clave.setName(cantDiasUltimoCambioClave);
			clave.setValue(String.valueOf(transferencia));
			clave.setDataType(DataType.INTEGER);
			factList.getFact().add(clave);
		}
	}
	private static void addValidControlSumToFactList(AumentoLimiteTransferenciaInOutView transferencia, FactList factList) {
		if (transferencia.getControlSum() != null) {
			addAcumTotalCustomFact(transferencia, factList);

			addValidCantTotalCustomFact(transferencia, factList);

			addValidAcumDestinoCustomFact(transferencia, factList);

			addValidCantDestinoCustomFact(transferencia, factList);

		}
	}

	private static void addValidCantDestinoCustomFact(AumentoLimiteTransferenciaInOutView transferencia, FactList factList) {
		ClientDefinedFact cantDestino = new ClientDefinedFact();
		cantDestino.setName(CANT_DESTINO_CUSTOM_FACT);
		if (null != transferencia.getControlSum() && null!= transferencia.getControlSum().getDestinationCuitQuantity()) {
			cantDestino.setValue(String.valueOf(transferencia.getControlSum().getDestinationCuitQuantity()));
		} else {
			cantDestino.setValue("0");
		}
		cantDestino.setDataType(DataType.INTEGER);
		factList.getFact().add(cantDestino);
	}

	private static void addValidAcumDestinoCustomFact(AumentoLimiteTransferenciaInOutView transferencia, FactList factList) {
		ClientDefinedFact acumDestino = new ClientDefinedFact();
		acumDestino.setName(ACUM_DESTINO_CUSTOM_FACT);
		if (null != transferencia.getControlSum() && null != transferencia.getControlSum().getDestinationCuitAmount()) {
			acumDestino.setValue(String.valueOf(transferencia.getControlSum().getDestinationCuitAmount().intValue()));
		} else {
			acumDestino.setValue("0");
		}
		acumDestino.setDataType(DataType.INTEGER);
		factList.getFact().add(acumDestino);
	}

	private static void addValidCantTotalCustomFact(AumentoLimiteTransferenciaInOutView transferencia, FactList factList) {
		ClientDefinedFact cantTotal = new ClientDefinedFact();
		cantTotal.setName(CANT_TOTAL_CUSTOM_FACT);
		if (null != transferencia.getControlSum() && null != transferencia.getControlSum().getNupQuantity()) {
			cantTotal.setValue(String.valueOf(transferencia.getControlSum().getNupQuantity()));
		} else {
			cantTotal.setValue("0");
		}
		cantTotal.setDataType(DataType.INTEGER);
		factList.getFact().add(cantTotal);
	}

	private static void addAcumTotalCustomFact(AumentoLimiteTransferenciaInOutView transferencia, FactList factList) {
		ClientDefinedFact acumTotal = new ClientDefinedFact();
		acumTotal.setName(ACUM_TOTAL_CUSTOM_FACT);
		if (null != transferencia.getControlSum() && null != transferencia.getControlSum().getNupAmount()) {
			acumTotal.setValue(String.valueOf(transferencia.getControlSum().getNupAmount().intValue()));
		} else {
			acumTotal.setValue("0");
		}
		acumTotal.setDataType(DataType.INTEGER);
		factList.getFact().add(acumTotal);
	}

	private void addScoringDestinatarioCustomFact(AumentoLimiteTransferenciaInOutView transferencia, FactList factList) {

		ClientDefinedFact scoringDestinatario = new ClientDefinedFact();

		scoringDestinatario.setName(SCORING_DESTINATARIO_CUSTOM_FACT);
		scoringDestinatario.setDataType(DataType.FLOAT);
		scoringDestinatario.setValue(String.valueOf(transferencia.getScoringDestinatario()));

		factList.getFact().add(scoringDestinatario);

	}


	private void addCantDiasUltimoCambioMail(AumentoLimiteTransferenciaInOutView transferencia, FactList factList) {

		if(transferencia.getCantDiasUltimoCambioMail() != null) {

			ClientDefinedFact antiguedad = new ClientDefinedFact();

			antiguedad.setName(CANT_DIAS_ULTIMO_CAMBIO_MAIL_CUSTOM_FACT);
			antiguedad.setDataType(DataType.INTEGER);
			antiguedad.setValue(String.valueOf(transferencia.getCantDiasUltimoCambioMail()));

			factList.getFact().add(antiguedad);
		}
	}
}