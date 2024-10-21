/*
 *
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoInformadoView;
import org.apache.commons.lang.StringUtils;

import static ar.com.santanderrio.obp.servicios.home.web.manager.impl.SeccionSolicitudesHomeManagerImpl.LOGGER;

/**
 * The Class PagoHaberesSimpleMultipleRsaRequestBuilder.
 */
public class PagoHaberesSimpleMultipleRsaRequestBuilder extends AbstractRsaRequestBuilder {

	private static final String PAGO_SUELDO = "Pago_Sueldo";

	public static final String CANT_DIAS_ULTIMO_CAMBIO_MAIL_CUSTOM_FACT = "cantDiasUltimoCambioMail";

	public static final String CANT_DIAS_ANTIGUEDAD_EMPLEADO = "AntModSldo";

	public static final String ERROR_PARSEO_FECHAS = "Ha ocurrido un error al formatear las fechas.";



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
		PagoInformadoView pagoInformadoView = (PagoInformadoView) operacionDeRiesgo;
		ed.setClientDefinedAttributeList(generarClientDefinedAttributeListPagoSueldo(operacionDeRiesgo, pagoInformadoView));
		TransactionData td = generarTransactionDataNuevoPago(pagoInformadoView);
		ed.setTransactionData(td);
		return ed;
	}

	/**
	 * Generar transaction data nuevo pago.
	 *
	 * @param pagoInformadoView
	 *            the pago informado view
	 * @return the transaction data
	 */
	private TransactionData generarTransactionDataNuevoPago(PagoInformadoView pagoInformadoView) {

		SimpleDateFormat formatFechaEntrada = new SimpleDateFormat("dd/MM/yyyy");
		TransactionData td = new TransactionData();

		// estimatedDeliveryDate
		try {
			Date fecha = formatFechaEntrada.parse(pagoInformadoView.getFechaPago());
			td.setEstimatedDeliveryDate(DATE_FORMATTER.format(fecha));

		} catch (ParseException e) {
			LOGGER.error(ERROR_PARSEO_FECHAS, e);
		}

		// executionSpeed
		td.setExecutionSpeed(ExecutionSpeed.REAL_TIME);

		// amount - currency
		Amount amt = new Amount();
        amt.setAmount(new Long(String.format("%.2f",Double.parseDouble(pagoInformadoView.getImporte()
                                .replace(",", "."))).replace(".", "").replace(" ", "")));
		amt.setCurrency(DivisaEnum.PESO.getCodigo());
		td.setAmount(amt);

		// Cuenta origen
		AccountData ad = new AccountData();
		Amount amtBalance = new Amount();
		amtBalance.setAmount(new Long(pagoInformadoView.getSaldoCuentaOrigen().replace(".", "").replace(",", "")
				.replace("$", "").replace(" ", "")));
		amtBalance.setCurrency(DivisaEnum.PESO.getCodigo());
		ad.setAccountBalance(amtBalance);

		// accountNumber
		ad.setAccountNumber(pagoInformadoView.getCuentaOrigen());

		// accountNickName
		ad.setAccountNickName(pagoInformadoView.getNickName());
		// accountType
		ad.setAccountType(AccountType.USER_DEFINED);
		td.setMyAccountData(ad);

		// Cuenta Destino
		AccountData otherAccount = new AccountData();
		otherAccount.setAccountNickName(pagoInformadoView.getDescripcionEmpleado());
		otherAccount.setAccountNumber(pagoInformadoView.getCuentaDestino());
		td.setOtherAccountData(otherAccount);

		// OtherAccountBankType
		td.setOtherAccountBankType(OtherAccountBankType.SAME_BANK);
		// otherAccountOwnershipType
		td.setOtherAccountOwnershipType(OtherAccountOwnershipType.ME_TO_YOU);
		// otherAccountType
		td.setOtherAccountType(OtherAccountType.PERSONAL_ACCOUNT);

		// schedule
		if (pagoInformadoView.isPagoProgramado()){
			td.setSchedule(Schedule.SCHEDULED);
		}else{
			td.setSchedule(Schedule.IMMEDIATE);
		}

		// transferMediumType
		td.setTransferMediumType(TransferMediumType.BALANCE_TRANSFER);

		return td;
	}

	private FactList generarClientDefinedAttributeListPagoSueldo(RsaDTO operacionDeRiesgo, PagoInformadoView pagoInformadoView) {
		FactList factList = new FactList();
		factList.getFact().add(generarClientDefinedFactPagoSueldo(operacionDeRiesgo));

		if(pagoInformadoView.getCantDiasUltimoCambioClave() != null) {
			 ClientDefinedFact clave = new ClientDefinedFact();
			 clave.setName("cantDiasUltimoCambioClave");
			 clave.setValue(String.valueOf(pagoInformadoView.getCantDiasUltimoCambioClave()));
			 clave.setDataType(DataType.INTEGER);
			 factList.getFact().add(clave);
		}

		if(pagoInformadoView.getCantDiasUltimoCambioToken() != null) {
			ClientDefinedFact token = new ClientDefinedFact();
			token.setName("cantDiasUltimoCambioToken");
			token.setValue(String.valueOf(pagoInformadoView.getCantDiasUltimoCambioToken()));
			token.setDataType(DataType.INTEGER);
			factList.getFact().add(token);
		}
        //Este custom Fact se utiliza para la validación del token en RSA para pago de haberes. False = RSA interpreta que se tiene que verificar el Token.
        // True= descarta el token y evalúa solo los datos duros
        ClientDefinedFact isChallenge = new ClientDefinedFact();
        isChallenge.setName("isChallenge");
        isChallenge.setValue(String.valueOf(pagoInformadoView.getChallenge()));
        isChallenge.setDataType(DataType.BOOLEAN);
        factList.getFact().add(isChallenge);

		ClientDefinedFact token = new ClientDefinedFact();
		token.setName("cuil");
		token.setValue(pagoInformadoView.getDocumento());
		token.setDataType(DataType.STRING);
		factList.getFact().add(token);

		ClientDefinedFact scoringDestinatario = new ClientDefinedFact();
		scoringDestinatario.setName("scoringDestinatario");
		scoringDestinatario.setValue(String.valueOf(pagoInformadoView.getScoringDestinatario()));
		scoringDestinatario.setDataType(DataType.FLOAT);
		factList.getFact().add(scoringDestinatario);

		if(pagoInformadoView.getCantDiasUltimoCambioMail() != -1) {
			ClientDefinedFact antiguedadMail = new ClientDefinedFact();
			antiguedadMail.setName(CANT_DIAS_ULTIMO_CAMBIO_MAIL_CUSTOM_FACT);
			antiguedadMail.setDataType(DataType.INTEGER);
			antiguedadMail.setValue(String.valueOf(pagoInformadoView.getCantDiasUltimoCambioMail()));
			factList.getFact().add(antiguedadMail);
		}

		ClientDefinedFact antiguedadEmpleado = new ClientDefinedFact();
		antiguedadEmpleado.setName(CANT_DIAS_ANTIGUEDAD_EMPLEADO);
		antiguedadEmpleado.setValue(String.valueOf(calcularAntiguedadEmpleado(pagoInformadoView.getFechaBaseRecurrencia(),pagoInformadoView.getNroRecurrencia())));
		antiguedadEmpleado.setDataType(DataType.INTEGER);
		factList.getFact().add(antiguedadEmpleado);

		BiocatchRsaRequestBuilder.buildBiocatchRsaRequestBuilder(pagoInformadoView.getBiocatchResponseData(), factList);
		ClientDefinedFact esPosibleFraude = new ClientDefinedFact();
		esPosibleFraude.setName("Edit_front_data");
		esPosibleFraude.setValue(String.valueOf(pagoInformadoView.isPif()));
		esPosibleFraude.setDataType(DataType.BOOLEAN);
		factList.getFact().add(esPosibleFraude);

		return factList;
	}

	private ClientDefinedFact generarClientDefinedFactPagoSueldo(RsaDTO operacionDeRiesgo) {
		ClientDefinedFact customFact = new ClientDefinedFact();
		customFact.setName(PAGO_SUELDO);
		customFact.setValue(String.valueOf(true));
		customFact.setDataType(DataType.BOOLEAN);
		return customFact;
	}

	private int calcularAntiguedadEmpleado(String fechaCreacion, String recurrencia) {
		int antiguedadEmpleado =0;

		if (StringUtils.isNotBlank(fechaCreacion)) {
			try {
				Date fechaInicio = ISBANStringUtils.parsearFechaIATX(fechaCreacion);
				Calendar calendario = Calendar.getInstance();
				calendario.setTime(fechaInicio);

				int nroRecurrencia = Integer.parseInt(recurrencia) - 1;
				calendario.add(Calendar.MONTH, -nroRecurrencia);
				Date nuevaFechaInicio = calendario.getTime();

				antiguedadEmpleado= ISBANStringUtils.diferenciaEnDias(new Date(), nuevaFechaInicio);
			} catch (ParseException e) {
				LOGGER.info("ERROR: No se pudo calcular la cantidad de dias de antiguedad", e);
			}
		}
		return antiguedadEmpleado;
	}
}