/**
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
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasRSADTO;

/**
 * The Class PagoCompraRsaRequestBuilder.
 *
 * @author florencia.n.martinez
 */
public class PagoCompraRsaRequestBuilder extends AbstractRsaRequestBuilder {

    /** The Constant CELULAR_MYA. */
    private static final String CELULAR_MYA = "celularmya";

    /** The Constant PURCHASE_PAYMENT. */
    private static final String PURCHASE_PAYMENT = "PURCHASE_PAYMENT";

    /**
     * Build.
     *
     * @param operacionDeRiesgo
     *            the operacion de riesgo
     * @return the event data
     */
    @Override
    public EventData build(RsaDTO operacionDeRiesgo) {
        PagoComprasRSADTO rsaDTO = (PagoComprasRSADTO) operacionDeRiesgo;
        EventData ed = new EventData();
        ed.setClientDefinedAttributeList(generarClientDefinedAttributeListPagoCompra(rsaDTO));
        ed.setClientDefinedEventType(PURCHASE_PAYMENT);
        ed.setEventType(EventType.PAYMENT);
        ed.setTransactionData(generarTransactionDataPagoCompra(rsaDTO));
        return ed;
    }

    /**
     * Generar client defined attribute list pago compra.
     *
     * @param rsaDTO
     *            the rsa DTO
     * @return the fact list
     */
    private FactList generarClientDefinedAttributeListPagoCompra(PagoComprasRSADTO rsaDTO) {
        FactList factList = new FactList();
        factList.getFact().add(generarClienteDefinedFactPagoCompra(rsaDTO));
        return factList;
    }

    /**
     * Generar cliente defined fact pago compra.
     *
     * @param rsaDTO
     *            the rsa DTO
     * @return the client defined fact
     */
    private ClientDefinedFact generarClienteDefinedFactPagoCompra(PagoComprasRSADTO rsaDTO) {
        ClientDefinedFact celularMyA = new ClientDefinedFact();
        celularMyA.setName(CELULAR_MYA);
        celularMyA.setValue(String.valueOf(rsaDTO.getTieneCelularMyA()));
        celularMyA.setDataType(DataType.BOOLEAN);
        return celularMyA;
    }

    /**
     * Generar transaction data pago compra.
     *
     * @param rsaDTO
     *            the rsa DTO
     * @return the transaction data
     */
    private TransactionData generarTransactionDataPagoCompra(PagoComprasRSADTO rsaDTO) {
        TransactionData data = new TransactionData();
        data.setAmount(generarAmountPagoCompra(rsaDTO.getMonto(), rsaDTO.getMoneda()));
        data.setDueDate(DATE_FORMATTER.format(new Date()));
        data.setEstimatedDeliveryDate(DATE_FORMATTER.format(new Date()));
        data.setExecutionSpeed(ExecutionSpeed.REAL_TIME);
        data.setMyAccountData(generarAccountDataPagoCompra(rsaDTO));
        data.setOtherAccountData(generarOtherAccountDataPagoCompra(rsaDTO));
        data.setOtherAccountOwnershipType(OtherAccountOwnershipType.ME_TO_YOU);
        data.setOtherAccountType(OtherAccountType.BILLER);
        data.setSchedule(Schedule.IMMEDIATE);
        data.setTransferMediumType(TransferMediumType.BILLPAY_ELEC);
        return data;
    }

    /**
     * Generar other account data pago compra.
     *
     * @param rsaDTO
     *            the rsa DTO
     * @return the account data
     */
    private AccountData generarOtherAccountDataPagoCompra(PagoComprasRSADTO rsaDTO) {
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
    private AccountData generarAccountDataPagoCompra(PagoComprasRSADTO rsaDTO) {
        AccountData accountData = new AccountData();
        accountData.setAccountBalance(generarAmountPagoCompra(rsaDTO.getCuentaOrigen().getSaldo(), rsaDTO.getMoneda()));
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
	 * @param moneda
	 *            the moneda
	 * @return the amount
	 */
    private Amount generarAmountPagoCompra(Long importe, String moneda) {
        Amount amount = new Amount();
        amount.setAmount(importe);
        amount.setCurrency(moneda);
        return amount;
    }
}
