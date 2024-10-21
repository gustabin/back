package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.com.santanderrio.obp.servicios.rsa.common.RSAConstants;
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
import ar.com.santanderrio.obp.generated.webservices.rsa.Schedule;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransactionData;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransferMediumType;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.prestamos.dto.SimuladorPrestamoDTO;

/**
 * The Class PrestamosRsaRequestBuilder.
 */
public class PrestamosRsaRequestBuilder implements RsaRequestBuilder {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRsaRequestBuilder.class);

	/** The Constant FORMAT_DD_MM_YYYY. */
	private static final String FORMAT_DD_MM_YYYY = "dd/MM/yyyy";

	/** The Constant FORMAT_YYYY_MM_DD. */
	private static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	/** The Constant FACTOR_CENTAVOS. */
	protected static final BigDecimal FACTOR_CENTAVOS = new BigDecimal(100);

	private static final String LIQUIDAR_ENCOLADO = "3";

	private static final String FASE_CANCELAR_USUARIO = "4";

	@Override
	public EventData build(RsaDTO operacionDeRiesgo) {
		EventData ed = new EventData();
		ed.setEventType(EventType.REQUEST_CREDIT);
		SimuladorPrestamoDTO simuladorPrestamoDTO = (SimuladorPrestamoDTO) operacionDeRiesgo;
		if (LIQUIDAR_ENCOLADO.equals(simuladorPrestamoDTO.getFase())) {
			ed.setClientDefinedEventType(RSAConstants.LIQ_INMEDIATA);
		} else if (FASE_CANCELAR_USUARIO.equals(simuladorPrestamoDTO.getFase())) {
			ed.setClientDefinedEventType(RSAConstants.ANULACION);
		}
		TransactionData td = generarTransactionDataPrestamo(simuladorPrestamoDTO);
		ed.setClientDefinedAttributeList(generarFactListTransferencia(simuladorPrestamoDTO));
		ed.setTransactionData(td);
		return ed;
	}

	/**
	 * Generar transaction data prestamo.
	 *
	 * @param simuladorPrestamoDTO the simulador prestamo DTO
	 * @return the transaction data
	 */
	private TransactionData generarTransactionDataPrestamo(SimuladorPrestamoDTO simuladorPrestamoDTO) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);
		SimpleDateFormat sdf2 = new SimpleDateFormat(FORMAT_DD_MM_YYYY);
		TransactionData td = new TransactionData();
		if(simuladorPrestamoDTO.getFechaSeleccionada() != null && simuladorPrestamoDTO.getFechaSeleccionada().contains("-")) {
			td.setEstimatedDeliveryDate(simuladorPrestamoDTO.getFechaSeleccionada());
		} else {
			Date estimatedDeliveryDate = null;
			try {
				estimatedDeliveryDate = sdf2.parse(simuladorPrestamoDTO.getFechaSeleccionada());
			} catch (ParseException e) {
				LOGGER.info("ERROR: La fecha seleccionada para el prestamo no es valida");
			}
			td.setEstimatedDeliveryDate(estimatedDeliveryDate == null ? null : sdf.format(estimatedDeliveryDate));
		}
		td.setExecutionSpeed(ExecutionSpeed.REAL_TIME);

		Amount amt = new Amount();
		BigDecimal importe = new BigDecimal(simuladorPrestamoDTO.getImporteSeleccionado());
		amt.setAmount(importe.multiply(FACTOR_CENTAVOS).longValue());
		amt.setCurrency(DivisaEnum.PESO.getCodigo());
		td.setAmount(amt);

		// Cuenta origen
		AccountData ad = new AccountData();

		Amount amtBalance = new Amount();
		Cuenta cuenta = simuladorPrestamoDTO.getCuentaSeleccionada();
		BigDecimal saldoPesos = null;
		if (TipoCuenta.CUENTA_UNICA.getCodigo() == Integer.valueOf(cuenta.getTipoCuenta())) {
			saldoPesos = new BigDecimal(cuenta.getSaldoCUPesos());
        } else if (esCuentaPesos(cuenta.getTipoCuentaEnum())) {
        	saldoPesos = new BigDecimal(cuenta.getSaldoCuenta());
        }
		amtBalance.setAmount(saldoPesos.multiply(FACTOR_CENTAVOS).longValue());
		amtBalance.setCurrency(DivisaEnum.PESO.getCodigo());
		ad.setAccountBalance(amtBalance);

		String numeroCuenta = ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal())
                .concat(ISBANStringUtils.GUION_STRING)
                .concat(ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto()));
		ad.setAccountNumber(numeroCuenta);
		ad.setAccountNickName(simuladorPrestamoDTO.getNyaCliente());
		ad.setAccountType(AccountType.USER_DEFINED);

		Amount amtCreditCreditLimit = new Amount();
		BigDecimal maxImpPrest = ISBANStringUtils.stringToBigDecimal(simuladorPrestamoDTO.getMaxImpPrest(), 13, 4, false);
		amtCreditCreditLimit.setAmount(maxImpPrest.multiply(FACTOR_CENTAVOS).longValue());
		amtCreditCreditLimit.setCurrency(DivisaEnum.PESO.getCodigo());
		ad.setAccountCreditLimit(amtCreditCreditLimit);

		Amount amtCreditUsed = new Amount();
		amtCreditUsed.setAmount(new Long(0));
		amtCreditUsed.setCurrency(DivisaEnum.PESO.getCodigo());
		ad.setAccountCreditsUsed(amtCreditUsed);

		td.setMyAccountData(ad);

		// Schedule
		td.setSchedule(Schedule.IMMEDIATE);

		// TransferMediumType
		td.setTransferMediumType(TransferMediumType.BALANCE_TRANSFER);

		return td;
	}
	
	public static void main(String[] args) {
		Amount amtCreditCreditLimit = new Amount();
		BigDecimal maxImpPrest = ISBANStringUtils.stringToBigDecimal(ISBANStringUtils.ajustadorBigDecimalIatx(new BigDecimal(80000), 17, 4), 13, 4, false);
		amtCreditCreditLimit.setAmount(maxImpPrest.multiply(FACTOR_CENTAVOS).longValue());
		amtCreditCreditLimit.setCurrency(DivisaEnum.PESO.getCodigo());
	}

	/**
	 * Obtiene lista de custom facts
	 * @param simuladorPrestamoDTO
	 * @return
	 */
	private FactList generarFactListTransferencia(SimuladorPrestamoDTO simuladorPrestamoDTO) {
		FactList factList = new FactList();
		if(simuladorPrestamoDTO.getCantDiasUltimoCambioClave() != null) {
			 ClientDefinedFact clave = new ClientDefinedFact();
			 clave.setName("cantDiasUltimoCambioClave");
			 clave.setValue(String.valueOf(simuladorPrestamoDTO.getCantDiasUltimoCambioClave()));
			 clave.setDataType(DataType.INTEGER);
			 factList.getFact().add(clave);
		}
		
		if(simuladorPrestamoDTO.getCantDiasUltimoCambioToken() != null) {
			ClientDefinedFact token = new ClientDefinedFact();
			token.setName("cantDiasUltimoCambioToken");
			token.setValue(String.valueOf(simuladorPrestamoDTO.getCantDiasUltimoCambioToken()));
			token.setDataType(DataType.INTEGER);
			factList.getFact().add(token);
		}
		
		if (simuladorPrestamoDTO.getTipoPrestamo() != null) {
			ClientDefinedFact token = new ClientDefinedFact();
			token.setName("Tipo_Prestamo");
			token.setValue(simuladorPrestamoDTO.getTipoPrestamo());
			token.setDataType(DataType.STRING);
			factList.getFact().add(token);
		}
		
		if (simuladorPrestamoDTO.getSubproductoPrestamo() != null) {
			ClientDefinedFact token = new ClientDefinedFact();
			token.setName("subproducto_prestamo");
			token.setValue(simuladorPrestamoDTO.getSubproductoPrestamo());
			token.setDataType(DataType.STRING);
			factList.getFact().add(token);
		}
		
		if (simuladorPrestamoDTO.getAnioNacimiento() != null) {
			ClientDefinedFact token = new ClientDefinedFact();
			token.setName("nacimiento_aaaa");
			token.setValue(simuladorPrestamoDTO.getAnioNacimiento());
			token.setDataType(DataType.INTEGER);
			factList.getFact().add(token);
		}
		
		if (simuladorPrestamoDTO.getCantidadDiasCambioCel() != null) {
			ClientDefinedFact cambioCel = new ClientDefinedFact();
			cambioCel.setName("cantDiasUltimoCambioCelu");
			cambioCel.setValue(String.valueOf(simuladorPrestamoDTO.getCantidadDiasCambioCel()));
			cambioCel.setDataType(DataType.INTEGER);
			factList.getFact().add(cambioCel);
		}
		
		if (simuladorPrestamoDTO.getCantidadDiasCambioMail() != null) {
			ClientDefinedFact cambioMail = new ClientDefinedFact();
			cambioMail.setName("cantDiasUltimoCambioMail");
			cambioMail.setValue(String.valueOf(simuladorPrestamoDTO.getCantidadDiasCambioMail()));
			cambioMail.setDataType(DataType.INTEGER);
			factList.getFact().add(cambioMail);
		}
		
       return factList;
	}
	

	/**
	 * Es cuenta pesos.
	 *
	 * @param tipoCuenta the tipo cuenta
	 * @return the boolean
	 */
	private Boolean esCuentaPesos(TipoCuenta tipoCuenta) {
		return tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_PESOS) || tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_PESOS)
				|| tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_PESOS);
	}

}
