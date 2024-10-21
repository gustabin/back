/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import ar.com.santanderrio.obp.servicios.compraventa.dto.VentaUSDDTO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * Esta clase se encarga de construir el request para validar con RSA.
 *
 */
public class VentaUSDRsaRequestBuilder extends AbstractRsaRequestBuilder {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRsaRequestBuilder.class);

    /** The Constant DATE_FORMATTER. */
    private static final SimpleDateFormat DATE_FORMATTER_INPUT = new SimpleDateFormat("dd/MM/yyyy");

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
        VentaUSDDTO ventaUsdDto = (VentaUSDDTO) operacionDeRiesgo;
        ed.setEventType(EventType.PAYMENT);
        ed.setTransactionData(generarTransactionDataVentaUSD(ventaUsdDto));
        ed.setClientDefinedAttributeList(generarFactListVenta(ventaUsdDto));
        return ed;
    }

    /**
     * Generar transaction data venta de USD.
     *
     * @param 
     *            the 
     * @return the transaction data
     */
    private TransactionData generarTransactionDataVentaUSD(VentaUSDDTO venta) {
        TransactionData data = new TransactionData();
        Amount amount = new Amount();
        amount.setAmount((ISBANStringUtils.convertirABigDecimal(venta.getImporte())).multiply(FACTOR_CENTAVOS).longValue());
        amount.setAmountInUSD((ISBANStringUtils.convertirABigDecimal(venta.getImporteUSD())).multiply(FACTOR_CENTAVOS).longValue());
        amount.setCurrency(venta.getMoneda());
        data.setAmount(amount);
        
        Date fechaInput = null;
        try {
            fechaInput = DATE_FORMATTER_INPUT.parse(venta.getFechaEjecucion());
        } catch (ParseException e) {
            LOGGER.info("ERROR: La fecha de ejecución vino nula");
        }
        String dueDate = (fechaInput == null ? null : DATE_FORMATTER.format(fechaInput));
        data.setDueDate(dueDate);
        data.setOtherAccountBankType(OtherAccountBankType.SAME_BANK);
        data.setOtherAccountType(OtherAccountType.PERSONAL_ACCOUNT);
        data.setOtherAccountOwnershipType(OtherAccountOwnershipType.ME_TO_ME);
        data.setExecutionSpeed(ExecutionSpeed.REAL_TIME);
        data.setSchedule(Schedule.IMMEDIATE);

        Date estimatedDeliveryInput = null;
        try {
            estimatedDeliveryInput = DATE_FORMATTER_INPUT.parse(venta.getFechaEjecucion());
        } catch (ParseException e) {
            LOGGER.info("ERROR: La fecha de ejecución vino nula");
        }

        String estimatedDelivery = (estimatedDeliveryInput == null ? null
                : DATE_FORMATTER.format(estimatedDeliveryInput));
        data.setEstimatedDeliveryDate(estimatedDelivery);
        AccountData myAccountData = new AccountData();
        data.setMyAccountData(myAccountData);
        Amount myAccountBalance = new Amount();
        myAccountData.setAccountBalance(myAccountBalance);

        String saldoFormateado = venta.getSaldoCuentaOrigen().replace(".", "");
        myAccountBalance.setAmount((saldoFormateado != "" ? new Long(saldoFormateado) : null));
        myAccountBalance.setCurrency(venta.getMonedaAltair());

        myAccountData.setAccountNumber(venta.getNroCuenta());
        myAccountData.setAccountNickName(venta.getTitular());
        myAccountData.setAccountType(decodificarAccountType(venta.getTipoCuentaEnum()));

        AccountData otherAccountData = new AccountData();
        data.setOtherAccountData(otherAccountData);
        String cuentaDestino = venta.getNroCuentaDestino() == null
                || "".equals(venta.getNroCuentaDestino()) ? venta.getCbu()
                        : venta.getNroCuentaDestino();
        otherAccountData.setAccountNumber(cuentaDestino);
        otherAccountData.setAccountNickName(venta.getTitular());
        data.setTransferMediumType(TransferMediumType.BALANCE_TRANSFER);
        return data;
    }

    /**
     * Generar fact list venta USD.
     *
     * @param ventaDto
     *            the request data
     * @return the fact list
     */
    private FactList generarFactListVenta(VentaUSDDTO ventaDto) {
        FactList factList = new FactList();

        ClientDefinedFact ventaUsdFact = new ClientDefinedFact();
        ventaUsdFact.setName("ventadolares");
        ventaUsdFact.setValue(Boolean.TRUE.toString());
        ventaUsdFact.setDataType(DataType.BOOLEAN);
        factList.getFact().add(ventaUsdFact);
        
        if(ventaDto.getCantDiasUltimoCambioClave() != null) {
	        ClientDefinedFact cantDiasClave = new ClientDefinedFact();
	        cantDiasClave.setName("cantDiasUltimoCambioClave");
	        cantDiasClave.setValue(String.valueOf(ventaDto.getCantDiasUltimoCambioClave()));
	        cantDiasClave.setDataType(DataType.INTEGER);
	        factList.getFact().add(cantDiasClave);
        }
        
        if(ventaDto.getCantDiasUltimoCambioToken() != null) {
	        ClientDefinedFact cantDiasToken = new ClientDefinedFact();
	        cantDiasToken.setName("cantDiasUltimoCambioToken");
	        cantDiasToken.setValue(String.valueOf(ventaDto.getCantDiasUltimoCambioToken()));
	        cantDiasToken.setDataType(DataType.INTEGER);
	        factList.getFact().add(cantDiasToken);
        }
        
        ClientDefinedFact superaAcumulado = new ClientDefinedFact();
        superaAcumulado.setName("acumdiariousd");
        superaAcumulado.setValue(String.valueOf(ventaDto.isAcumdiariousd()));
        superaAcumulado.setDataType(DataType.BOOLEAN);
        factList.getFact().add(superaAcumulado);
        
        ClientDefinedFact totalAcumuladoVenta = new ClientDefinedFact();
        totalAcumuladoVenta.setName("montoAcumuladoUSD");
        totalAcumuladoVenta.setValue(ventaDto.getMontoAcumuladoUSD() == null ? "0" : ventaDto.getMontoAcumuladoUSD());
        totalAcumuladoVenta.setDataType(DataType.DOUBLE);
        factList.getFact().add(totalAcumuladoVenta);
        
        return factList;
    }

}
