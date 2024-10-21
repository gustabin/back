/**
 *
 */
package ar.com.santanderrio.obp.servicios.rsa.bo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.com.santanderrio.obp.servicios.rsa.bo.impl.BiocatchRsaRequestBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.rsa.bo.impl.AbstractRsaRequestBuilder;
import ar.com.santanderrio.obp.servicios.rsa.bo.impl.TransferenciaAgendadaRsaRequestBuilder;
import ar.com.santanderrio.obp.servicios.rsa.common.RSAConstants;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * Carga de request para RSA para agendar una transferencia.
 *
 * @author manuel.vargas B041299
 * @see {@link AbstractRsaRequestBuilder}
 * @since Jul 23, 2017
 */
public class AgendamientoTransferenciaRsaRequestBuilder extends AbstractRsaRequestBuilder {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TransferenciaAgendadaRsaRequestBuilder.class);

	/** The Constant FACTOR_CENTAVOS. */
	private static final BigDecimal FACTOR_CENTAVOS = new BigDecimal(100);

	/** The Constant ERROR_PARSEO_FECHAS. */
	private static final String ERROR_PARSEO_FECHAS = "Ha ocurrido un error al formatear las fechas.";

	/** The Constant DATE_FORMATTER. */
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

	/** The Constant FORMATEAR_FECHA_BARRA. */
	private static final SimpleDateFormat FORMATEAR_FECHA_BARRA = new SimpleDateFormat("dd/MM/yyyy");

	/** The Constant COMA_DECIMAL. */
	private static final String COMA_DECIMAL = ".";

	/** The Constant COMA_STRING. */
	private static final String COMA_STRING = ",";

	/** The Constant EMPTY_STRING. */
	private static final String EMPTY_STRING = "";

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
		TransferenciaView transferencia = (TransferenciaView) operacionDeRiesgo;
		ed.setEventType(EventType.PAYMENT);
		ed.setClientDefinedEventType(RSAConstants.EVT_CLTDEF_PAGOAGENDADO);
		ed.setTransactionData(generarTransactionDataTransferencia(transferencia));
		ed.setClientDefinedAttributeList(generarFactListTransferencia(transferencia));
		return ed;
	}

	/**
	 * Moneda debe ser USD o ARS.
	 *
	 * @param transferencia
	 *            the transferencia
	 * @return the transaction data
	 */
	private TransactionData generarTransactionDataTransferencia(TransferenciaView transferencia) {
		TransactionData data = new TransactionData();
		Amount amount = new Amount();
		String importe = formatearSaldoSinComaConUnPunto(transferencia.getImporte().replace(COMA_DECIMAL, ""));
		amount.setAmount(new BigDecimal(importe).multiply(FACTOR_CENTAVOS).longValue());
		amount.setCurrency(transferencia.getMonedaAltair());

		if(DivisaEnum.DOLAR.getMoneda().equals(amount.getCurrency())) {
			amount.setAmountInUSD(new BigDecimal(importe).multiply(FACTOR_CENTAVOS).longValue());
		}

		data.setAmount(amount);

		String dueDate = DATE_FORMATTER.format(new Date());
		data.setDueDate(dueDate);

		data.setOtherAccountBankType(OtherAccountBankType.SAME_BANK);
		data.setOtherAccountType(OtherAccountType.PERSONAL_ACCOUNT);
		data.setOtherAccountOwnershipType(OtherAccountOwnershipType.ME_TO_YOU);
		data.setExecutionSpeed(ExecutionSpeed.SEVERAL_DAYS);

		try {
			String estimatedDelivery = transferencia.getFechaEjecucion() == null ? null
					: DATE_FORMATTER.format(FORMATEAR_FECHA_BARRA.parse(transferencia.getFechaEjecucion()));
			data.setEstimatedDeliveryDate(estimatedDelivery);
		} catch (ParseException e) {
			LOGGER.error(ERROR_PARSEO_FECHAS, e);
		}

		AccountData myAccountData = new AccountData();
		data.setMyAccountData(myAccountData);
		Amount myAccountBalance = new Amount();
		myAccountData.setAccountBalance(myAccountBalance);
		myAccountBalance.setAmount(new BigDecimal(StringUtils.defaultIfEmpty(formatearSaldoSinComaConUnPunto(transferencia.getSaldoCuentaOrigen()), "0"))
				.multiply(FACTOR_CENTAVOS).longValue());
		myAccountBalance.setCurrency(transferencia.getMonedaAltair());
		myAccountData.setAccountNumber(transferencia.getNroCuenta());
		myAccountData.setAccountNickName(EMPTY_STRING);
		myAccountData.setAccountType(AccountType.USER_DEFINED);

		AccountData otherAccountData = new AccountData();
		data.setOtherAccountData(otherAccountData);
		String cuentaDestino = (transferencia.getNroCuentaDestino() != null
				&& transferencia.getNroCuentaDestino() != EMPTY_STRING) ? transferencia.getNroCuentaDestino()
				: transferencia.getCbu();
		otherAccountData.setAccountNumber(cuentaDestino);
		// TODO: nickName del destinatario (desc.cta.dest|titular)
		otherAccountData.setAccountNickName(transferencia.getTitular());

		data.setSchedule(Schedule.IMMEDIATE);
		data.setTransferMediumType(TransferMediumType.BALANCE_TRANSFER);

		return data;
	}

	/**
	 * Formatear saldo sin coma con un punto.
	 *
	 * @param saldoCuentaOrigen
	 *            the saldo cuenta origen
	 * @return the string
	 */
	private String formatearSaldoSinComaConUnPunto(String saldoCuentaOrigen) {
		return saldoCuentaOrigen.replace(COMA_DECIMAL, EMPTY_STRING).replace(COMA_STRING, COMA_DECIMAL);
	}

	/**
	 * Generar fact list agendamiento.
	 *
	 * @param transferencia
	 *            the request data
	 * @return the fact list
	 */
	private FactList generarFactListTransferencia(TransferenciaView transferencia) {
		FactList factList = new FactList();

		// la antiguedad siempre es cero.
		ClientDefinedFact antiguedad = new ClientDefinedFact();
		antiguedad.setName("antiguedad");
		// TODO EMILIO AGREGAR LA ANTIGUEDAD
		antiguedad.setValue("0");
		antiguedad.setDataType(DataType.INTEGER);
		factList.getFact().add(antiguedad);

		ClientDefinedFact banelco = new ClientDefinedFact();
		banelco.setName("banelco");
		banelco.setValue(String.valueOf(transferencia.isErrorBanelco()));
		banelco.setDataType(DataType.BOOLEAN);
		factList.getFact().add(banelco);

		ClientDefinedFact celularMyA = new ClientDefinedFact();
		celularMyA.setName("celularmya");
		celularMyA.setValue(String.valueOf(transferencia.isCelularMyA()));
		celularMyA.setDataType(DataType.BOOLEAN);
		factList.getFact().add(celularMyA);

		ClientDefinedFact validado = new ClientDefinedFact();
		validado.setName("validado");
		validado.setValue("true");
		validado.setDataType(DataType.BOOLEAN);
		factList.getFact().add(validado);

		BiocatchRsaRequestBuilder.buildBiocatchRsaRequestBuilder(transferencia.getBiocatchResponseData(), factList);

		return factList;
	}
}
