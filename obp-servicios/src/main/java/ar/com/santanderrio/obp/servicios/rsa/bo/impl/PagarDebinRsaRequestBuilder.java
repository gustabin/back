/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import java.math.BigDecimal;
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
import ar.com.santanderrio.obp.generated.webservices.rsa.FactList;
import ar.com.santanderrio.obp.generated.webservices.rsa.OtherAccountOwnershipType;
import ar.com.santanderrio.obp.generated.webservices.rsa.OtherAccountType;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransactionData;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransferMediumType;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.debinws.view.PagarDebinWSView;
import ar.com.santanderrio.obp.servicios.rsa.common.RSAConstants;

/**
 * The Class PagarDebinRsaRequestBuilder.
 *
 * @author Leandro_Ibaceta
 */
public class PagarDebinRsaRequestBuilder extends AbstractRsaRequestBuilder {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRsaRequestBuilder.class);

    /** The Constant DATE_FORMATTER. */
    private static final SimpleDateFormat DATE_FORMATTER_INPUT = new SimpleDateFormat("dd/MM/yyyy");

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilder#build(ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO)
     */
    @Override
    public EventData build(RsaDTO operacionDeRiesgo) {
        EventData ed = new EventData();
        PagarDebinWSView pagoDebin = (PagarDebinWSView) operacionDeRiesgo;
        ed.setClientDefinedEventType(RSAConstants.PAGO_DEBIN);
        ed.setEventType(EventType.PAYMENT);
        ed.setTransactionData(generarTransactionDataNuevoPagoDebin(pagoDebin));
        ed.setClientDefinedAttributeList(generarFactListPagoDebin(pagoDebin));
        return ed;
    }

    /**
     * Generar transaction data pago debin.
     *
     * @param pago
     *            the pago
     * @return the transaction data
     */
    private TransactionData generarTransactionDataNuevoPagoDebin(PagarDebinWSView pago) {
        TransactionData data = new TransactionData();
        Amount amount = new Amount();
        data.setAmount(amount);
        amount.setAmount(new BigDecimal(pago.getImporte()).multiply(FACTOR_CENTAVOS).longValue());
        amount.setCurrency(DivisaEnum.fromMonedaString(pago.getMoneda().toLowerCase()).getCodigo());

        Date fechaInput = null;
        try {
            fechaInput = DATE_FORMATTER_INPUT.parse(pago.getFechaEjecucion());
        } catch (ParseException e) {
            LOGGER.info("ERROR: La fecha de la transferencia vino nula");
        }
        String dueDate = (fechaInput == null ? null : DATE_FORMATTER.format(fechaInput));
        data.setDueDate(dueDate);
        data.setOtherAccountType(OtherAccountType.PERSONAL_ACCOUNT);
        data.setOtherAccountOwnershipType(pago.isCuentaPropia() ? OtherAccountOwnershipType.ME_TO_ME
                : OtherAccountOwnershipType.ME_TO_YOU);

        Date estimatedDeliveryInput = null;
        try {
            // TODO: EVALUAR SI ESTA FECHA, ES LA DE EJECUCION O ACREDITACION
            estimatedDeliveryInput = DATE_FORMATTER_INPUT.parse(pago.getFechaEjecucion());
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

        AccountData otherAccountData = new AccountData();
        data.setOtherAccountData(otherAccountData);
        otherAccountData.setAccountNumber(pago.getCbuVendedor());
        otherAccountData.setAccountNickName(pago.getNombreSolicitante());
        data.setTransferMediumType(TransferMediumType.BALANCE_TRANSFER);
        return data;
    }

    private FactList generarFactListPagoDebin(PagarDebinWSView pagarDebinWSView) {
	    FactList factList = new FactList();
	
        ClientDefinedFact cuitComprador = new ClientDefinedFact();
        cuitComprador.setName("cuit_comprador");
        if(pagarDebinWSView.getCuitComprador() != null) {
        	cuitComprador.setValue(!pagarDebinWSView.getCuitComprador().contains("-") ? ISBANStringUtils.agregarGuionesANumeroCuitCuil(pagarDebinWSView.getCuitComprador()) : "");
        } else {
        	cuitComprador.setValue("");
        }
        cuitComprador.setDataType(DataType.STRING);
        factList.getFact().add(cuitComprador);
        
        ClientDefinedFact cuitVendedor = new ClientDefinedFact();
        cuitVendedor.setName("cuit_vendedor");
        if(pagarDebinWSView.getCuitVendedor() != null ) {
        	cuitVendedor.setValue(!pagarDebinWSView.getCuitVendedor().contains("-") ? ISBANStringUtils.agregarGuionesANumeroCuitCuil(pagarDebinWSView.getCuitVendedor()) : pagarDebinWSView.getCuitVendedor());
        }	else {
        	cuitVendedor.setName("");
        }
        cuitVendedor.setDataType(DataType.STRING);
        factList.getFact().add(cuitVendedor);
        
        if(pagarDebinWSView.getCantDiasUltimoCambioClave() != null) {
	        ClientDefinedFact cantDiasClave = new ClientDefinedFact();
	        cantDiasClave.setName("cantDiasUltimoCambioClave");
	        cantDiasClave.setValue(String.valueOf(pagarDebinWSView.getCantDiasUltimoCambioClave()));
	        cantDiasClave.setDataType(DataType.INTEGER);
	        factList.getFact().add(cantDiasClave);
        }
        
        if(pagarDebinWSView.getCantDiasUltimoCambioToken() != null) {
	        ClientDefinedFact cantDiasToken = new ClientDefinedFact();
	        cantDiasToken.setName("cantDiasUltimoCambioToken");
	        cantDiasToken.setValue(String.valueOf(pagarDebinWSView.getCantDiasUltimoCambioToken()));
	        cantDiasToken.setDataType(DataType.INTEGER);
	        factList.getFact().add(cantDiasToken);
        }
	    return factList;
	}

}
