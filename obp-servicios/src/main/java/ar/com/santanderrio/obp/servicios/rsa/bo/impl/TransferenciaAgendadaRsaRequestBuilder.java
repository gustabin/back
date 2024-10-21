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
import ar.com.santanderrio.obp.generated.webservices.rsa.TransactionData;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaDetalleView;

/**
 * Esta clase se encarga de construir el request para validar con RSA.
 *
 * @author emilio.watemberg
 * @see {@link AbstractRsaRequestBuilder}
 * @since Mar 23, 2017
 */
public class TransferenciaAgendadaRsaRequestBuilder extends AbstractRsaRequestBuilder {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TransferenciaAgendadaRsaRequestBuilder.class);

    /** The Constant ERROR_PARSEO_FECHAS. */
    private static final String ERROR_PARSEO_FECHAS = "Ha ocurrido un error al formatear las fechas.";

    /** The Constant DATE_FORMATTER. */
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    /** The Constant FORMATEAR_FECHA_BARRA. */
    private static final SimpleDateFormat FORMATEAR_FECHA_BARRA = new SimpleDateFormat("dd/MM/yyyy");

    /** The Constant FACTOR_CENTAVOS. */
    private static final BigDecimal FACTOR_CENTAVOS = new BigDecimal(100);

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
     * com.santanderrio.obp.servicios.comun.autentificacion.entities.rsaDTO)
     */


    @Override
    public EventData build(RsaDTO operacionDeRiesgo) {
        EventData ed = new EventData();
        TransferenciaAgendadaDetalleView transferencia = (TransferenciaAgendadaDetalleView) operacionDeRiesgo;
        ed.setEventType(EventType.PAYMENT);
        ed.setTransactionData(generarTransactionDataTransferencia(transferencia));
        ed.setClientDefinedAttributeList(generarFactListTransferencia(transferencia));
        return ed;
    }

    /**
     * Generar transaction data transferencia.
     *
     * @param transferencia
     *            the transferencia
     * @return the transaction data
     */
    private TransactionData generarTransactionDataTransferencia(TransferenciaAgendadaDetalleView transferencia) {
        TransactionData data = new TransactionData();
        Amount amount = new Amount();
        amount.setAmount(new BigDecimal(transferencia.getImporte()).multiply(FACTOR_CENTAVOS).longValue());
        amount.setCurrency(DivisaEnum.fromSimboloString(transferencia.getDivisa()).getCodigo());
        
        if(DivisaEnum.DOLAR.getMoneda().equals(amount.getCurrency())) {
        	amount.setAmountInUSD(new BigDecimal(transferencia.getImporte()).multiply(FACTOR_CENTAVOS).longValue());
        } 
        
        data.setAmount(amount);

        String dueDate = DATE_FORMATTER.format(new Date());
        data.setDueDate(dueDate);

        data.setOtherAccountBankType(OtherAccountBankType.SAME_BANK);
        data.setOtherAccountType(OtherAccountType.PERSONAL_ACCOUNT);
        data.setOtherAccountOwnershipType(OtherAccountOwnershipType.ME_TO_YOU);
        data.setExecutionSpeed(transferencia.getPlazoAcreditacion().equals(HORAS_48) ? ExecutionSpeed.FEW_HOURS
                : ExecutionSpeed.REAL_TIME);

        try {
            String estimatedDelivery = transferencia.getFecha() == null ? null
                    : DATE_FORMATTER.format(FORMATEAR_FECHA_BARRA.parse(transferencia.getFecha()));
            data.setEstimatedDeliveryDate(estimatedDelivery);
        } catch (ParseException e) {
            LOGGER.error(ERROR_PARSEO_FECHAS, e);
        }

        AccountData myAccountData = new AccountData();
        data.setMyAccountData(myAccountData);
        Amount myAccountBalance = new Amount();
        myAccountData.setAccountBalance(myAccountBalance);

        myAccountBalance.setAmount(new BigDecimal(formatearSaldoSinComaConUnPunto(transferencia.getOrigenSaldo()))
                .multiply(FACTOR_CENTAVOS).longValue());

        myAccountBalance.setCurrency(DivisaEnum.fromSimboloString(transferencia.getDivisa()).getCodigo());
        myAccountData.setAccountNumber(transferencia.getOrigenNumero());

        myAccountData.setAccountNickName(transferencia.getOrigenNombreTitular());
        myAccountData.setAccountType(AccountType.USER_DEFINED);

        AccountData otherAccountData = new AccountData();
        data.setOtherAccountData(otherAccountData);

        String cuentaDestino = transferencia.getDestinoNumero();
        otherAccountData.setAccountNumber(cuentaDestino);
        otherAccountData.setAccountNickName(transferencia.getDestinatarioNombre());
        return data;
    }

    /**
     * Formatear saldo sin coma con un punto.
     *
     * @param saldo
     *            the saldo
     * @return the string
     */
    private String formatearSaldoSinComaConUnPunto(String saldo) {
        return saldo.replace(COMA_DECIMAL, EMPTY_STRING).replace(COMA_STRING, COMA_DECIMAL);
    };

    /**
     * Generar fact list transferencia.
     *
     * @param transferencia
     *            the request data
     * @return the fact list
     */
    private FactList generarFactListTransferencia(TransferenciaAgendadaDetalleView transferencia) {
        FactList factList = new FactList();

        ClientDefinedFact antiguedad = new ClientDefinedFact();
        antiguedad.setName("antiguedad");
        if (null == transferencia.getFechaCreacionDestinatario())
            antiguedad.setValue("0");
        else
            antiguedad.setValue(FechaUtils.calcularAntiguedad(transferencia.getFechaCreacionDestinatario()));

        antiguedad.setDataType(DataType.INTEGER);
        factList.getFact().add(antiguedad);

        ClientDefinedFact banelco = new ClientDefinedFact();
        banelco.setName("banelco");
        banelco.setValue(String.valueOf(transferencia.isBanelco()));
        banelco.setDataType(DataType.BOOLEAN);
        factList.getFact().add(banelco);

        ClientDefinedFact celularMyA = new ClientDefinedFact();
        celularMyA.setName("celularmya");
        celularMyA.setValue(String.valueOf(transferencia.isTieneCelularMyA()));
        celularMyA.setDataType(DataType.BOOLEAN);
        factList.getFact().add(celularMyA);

        ClientDefinedFact validado = new ClientDefinedFact();
        celularMyA.setName("validado");
        celularMyA.setValue("true");
        celularMyA.setDataType(DataType.BOOLEAN);
        factList.getFact().add(validado);

        ClientDefinedFact esPosibleFraudeAgenda = new ClientDefinedFact();
        esPosibleFraudeAgenda.setName("Edit_front_data");
        esPosibleFraudeAgenda.setValue(String.valueOf(transferencia.isPif()));
        esPosibleFraudeAgenda.setDataType(DataType.BOOLEAN);
        factList.getFact().add(esPosibleFraudeAgenda);

        if(transferencia.getCantDiasUltimoCambioClave() != null) {
			 ClientDefinedFact clave = new ClientDefinedFact();
			 clave.setName("cantDiasUltimoCambioClave");
			 clave.setValue(String.valueOf(transferencia.getCantDiasUltimoCambioClave()));
			 clave.setDataType(DataType.INTEGER);
			 factList.getFact().add(clave);
		}
		
		if(transferencia.getCantDiasUltimoCambioToken() != null) {
			ClientDefinedFact token = new ClientDefinedFact();
			token.setName("cantDiasUltimoCambioToken");
			token.setValue(String.valueOf(transferencia.getCantDiasUltimoCambioToken()));
			token.setDataType(DataType.INTEGER);
			factList.getFact().add(token);
		}

        BiocatchRsaRequestBuilder.buildBiocatchRsaRequestBuilder(transferencia.getBiocatchRsaData(), factList);

        return factList;
    }

}
