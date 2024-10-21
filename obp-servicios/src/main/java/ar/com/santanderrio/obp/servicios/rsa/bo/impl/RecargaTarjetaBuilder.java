/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import java.util.Date;

import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.generated.webservices.rsa.AccountData;
import ar.com.santanderrio.obp.generated.webservices.rsa.AccountType;
import ar.com.santanderrio.obp.generated.webservices.rsa.Amount;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.generated.webservices.rsa.ExecutionSpeed;
import ar.com.santanderrio.obp.generated.webservices.rsa.OtherAccountOwnershipType;
import ar.com.santanderrio.obp.generated.webservices.rsa.OtherAccountType;
import ar.com.santanderrio.obp.generated.webservices.rsa.Schedule;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransactionData;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransferMediumType;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ComprobanteRecargaTarjetaView;

/**
 * The Class RecargaTarjetaBuilder.
 */
public class RecargaTarjetaBuilder extends AbstractRsaRequestBuilder {

    /** The Constant BLANCO. */
    private static final String BLANCO = " ";

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.rsa.bo.impl.RsaRequestBuilder#build(ar.
     * com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO)
     */
    @Override
    public EventData build(RsaDTO operacionDeRiesgo) {
        EventData eventData = new EventData();
        eventData.setEventType(EventType.PAYMENT);
        ComprobanteRecargaTarjetaView recargaTarjetaRSA = (ComprobanteRecargaTarjetaView) operacionDeRiesgo;
        TransactionData td = generarTransactionDataRecarga(recargaTarjetaRSA);
        eventData.setTransactionData(td);
        return eventData;
    }

    /**
     * Generar transaction data recarga.
     *
     * @param recargaTarjetaRSA
     *            the recarga tarjeta RSA
     * @return the transaction data
     */
    private TransactionData generarTransactionDataRecarga(ComprobanteRecargaTarjetaView recargaTarjetaRSA) {

        TransactionData td = new TransactionData();
        // estimatedDeliveryDate
        td.setEstimatedDeliveryDate(DATE_FORMATTER.format(new Date()));

        // executionSpeed
        td.setExecutionSpeed(ExecutionSpeed.SEVERAL_DAYS);

        // dueDate
        td.setDueDate(DATE_FORMATTER.format(new Date()));

        // amount - currency
        Amount amt = new Amount();
        try {
            amt.setAmount(ISBANStringUtils.convertirImporte(recargaTarjetaRSA.getImporteRecargaPesos()).longValue());
        } catch (ImporteConvertException e) {
        }

        amt.setCurrency(DivisaEnum.PESO.getCodigo());
        td.setAmount(amt);
        // amount - currency
        AccountData ad = new AccountData();
        Amount amtBalance = new Amount();
        amtBalance.setAmount(new Long(recargaTarjetaRSA.getSaldoCuentaOrigen()));
        amtBalance.setCurrency(DivisaEnum.PESO.getCodigo());
        ad.setAccountBalance(amtBalance);

        Cuenta cuentaOrigen = recargaTarjetaRSA.getCuentaOrigen();
        // accountNumber
        ad.setAccountNumber(formatearNumeroCuenta(cuentaOrigen));

        // accountNickName
        String nombreTitularOrigen = cuentaOrigen.getCliente().getNombre() + BLANCO
                + cuentaOrigen.getCliente().getApellido1() + BLANCO + cuentaOrigen.getCliente().getApellido2();
        ad.setAccountNickName(nombreTitularOrigen);
        // accountType
        ad.setAccountType(AccountType.USER_DEFINED);
        td.setMyAccountData(ad);

        // Nro de tarjeta destino
        AccountData otherAccount = new AccountData();
        otherAccount.setAccountNickName(recargaTarjetaRSA.getNumeroTarjetaDestino());
        td.setOtherAccountData(otherAccount);

        // otherAccountOwnershipType
        td.setOtherAccountOwnershipType(OtherAccountOwnershipType.ME_TO_YOU);
        // otherAccountType
        td.setOtherAccountType(OtherAccountType.BILLER);
        // schedule
        td.setSchedule(Schedule.IMMEDIATE);
        // transferMediumType
        td.setTransferMediumType(TransferMediumType.BILLPAY_ELEC);

        return td;
    }

}
