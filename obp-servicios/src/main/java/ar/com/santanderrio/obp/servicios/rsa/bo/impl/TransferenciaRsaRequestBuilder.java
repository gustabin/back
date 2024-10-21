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
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * Esta clase se encarga de construir el request para validar con RSA.
 *
 * @author ignacio.valek
 * @see {@link AbstractRsaRequestBuilder}
 * @since Nov 11, 2016
 */

public class TransferenciaRsaRequestBuilder extends AbstractRsaRequestBuilder {

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
        TransferenciaView transferencia = (TransferenciaView) operacionDeRiesgo;
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
    private TransactionData generarTransactionDataTransferencia(TransferenciaView transferencia) {
        TransactionData data = new TransactionData();
        Amount amount = new Amount();
        data.setAmount(amount);
        amount.setAmount(new BigDecimal(transferencia.getImporte()).multiply(FACTOR_CENTAVOS).longValue());
        amount.setCurrency(DivisaEnum.fromMonedaString(transferencia.getMoneda().toLowerCase()).getCodigo());
        
        if(DivisaEnum.DOLAR.getMoneda().equals(amount.getCurrency())) {
        	amount.setAmountInUSD(new BigDecimal(transferencia.getImporte()).multiply(FACTOR_CENTAVOS).longValue());
        } 
        
        Date fechaInput = null;
        try {
            fechaInput = DATE_FORMATTER_INPUT.parse(transferencia.getFechaEjecucion());
        } catch (ParseException e) {
            LOGGER.info("ERROR: La fecha de la transferencia vino nula");
        }
        String dueDate = (fechaInput == null ? null : DATE_FORMATTER.format(fechaInput));
        data.setDueDate(dueDate);
        data.setOtherAccountBankType(
                !transferencia.getIsRioRio() ? OtherAccountBankType.OTHER_BANK : OtherAccountBankType.SAME_BANK);
        data.setOtherAccountType(OtherAccountType.PERSONAL_ACCOUNT);
        data.setOtherAccountOwnershipType(transferencia.isCuentaPropia() ? OtherAccountOwnershipType.ME_TO_ME
                : OtherAccountOwnershipType.ME_TO_YOU);
        data.setExecutionSpeed(transferencia.getFechaAcreditacion().equals(HORAS_48) ? ExecutionSpeed.FEW_HOURS
                : ExecutionSpeed.REAL_TIME);
        data.setSchedule(transferencia.isInmediata() ? Schedule.IMMEDIATE : Schedule.SCHEDULED);

        Date estimatedDeliveryInput = null;
        try {
            // TODO: EVALUAR SI ESTA FECHA, ES LA DE EJECUCION O ACREDITACION
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
        myAccountData.setAccountNickName(transferencia.getTitularOrigen());
        myAccountData.setAccountType(decodificarAccountType(transferencia.getTipoCuentaEnum()));

        AccountData otherAccountData = new AccountData();
        data.setOtherAccountData(otherAccountData);
        String cuentaDestino = transferencia.getNroCuentaDestino() == null
                || "".equals(transferencia.getNroCuentaDestino()) ? transferencia.getCbu()
                        : transferencia.getNroCuentaDestino();
        otherAccountData.setAccountNumber(cuentaDestino);
        otherAccountData.setAccountNickName(transferencia.getTitular());
        data.setTransferMediumType(TransferMediumType.BALANCE_TRANSFER);
        return data;
    }

    /**
     * Generar fact list transferencia.
     *
     * @param transferencia
     *            the request data
     * @return the fact list
     */
    private FactList generarFactListTransferencia(TransferenciaView transferencia) {
        FactList factList = new FactList();

        ClientDefinedFact antiguedad = new ClientDefinedFact();
        antiguedad.setName("antiguedad");

        if (transferencia.getFechaCreacionDestinatario() == null) {
            antiguedad.setValue("0");
        } else {
            String fechaCreacion = transferencia.getFechaCreacionDestinatario();

            try {
                Date fechaInicio = ISBANStringUtils.parsearTimestampIATX(fechaCreacion);
                Integer diferencia = ISBANStringUtils.diferenciaEnDias(new Date(), fechaInicio);
                antiguedad.setValue(new String(diferencia.toString()));
            } catch (ParseException e) {
                LOGGER.info("ERROR: La fecha de alta de transferencia", e);
            }
        }
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
        
        ClientDefinedFact cuitFact = new ClientDefinedFact();
        cuitFact.setName("cuit");
        if(transferencia.getCuitCuil() != null && transferencia.getCuitCuil().contains("-")) {
        	cuitFact.setValue(transferencia.getCuitCuil());
        } else {
        	cuitFact.setValue(ISBANStringUtils.agregarGuionesANumeroCuitCuil(transferencia.getCuitCuil()));
        }
        cuitFact.setDataType(DataType.STRING);
        factList.getFact().add(cuitFact);

        ClientDefinedFact scoringDestinatario = new ClientDefinedFact();
        scoringDestinatario.setName("scoringDestinatario");
        scoringDestinatario.setValue(String.valueOf(transferencia.getScoringDestinatario()));
        scoringDestinatario.setDataType(DataType.FLOAT);
        factList.getFact().add(scoringDestinatario);


        ClientDefinedFact esPosibleFraude = new ClientDefinedFact();
        esPosibleFraude.setName("Edit_front_data");
        esPosibleFraude.setValue(String.valueOf(transferencia.isPif()));
        esPosibleFraude.setDataType(DataType.BOOLEAN);
        factList.getFact().add(esPosibleFraude);


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

        if(transferencia.getControlSum() != null) {
            ClientDefinedFact acumTotal = new ClientDefinedFact();
            acumTotal.setName("acum_total");
            acumTotal.setValue(String.valueOf(transferencia.getControlSum().getNupAmount().intValue()));
            acumTotal.setDataType(DataType.INTEGER);
            factList.getFact().add(acumTotal);

            ClientDefinedFact cantTotal = new ClientDefinedFact();
            cantTotal.setName("cant_total");
            cantTotal.setValue(String.valueOf(transferencia.getControlSum().getNupQuantity()));
            cantTotal.setDataType(DataType.INTEGER);
            factList.getFact().add(cantTotal);

            ClientDefinedFact acumDestino = new ClientDefinedFact();
            acumDestino.setName("acum_destino");
            acumDestino.setValue(String.valueOf(transferencia.getControlSum().getDestinationCuitAmount().intValue()));
            acumDestino.setDataType(DataType.INTEGER);
            factList.getFact().add(acumDestino);

            ClientDefinedFact cantDestino = new ClientDefinedFact();
            cantDestino.setName("cant_destino");
            cantDestino.setValue(String.valueOf(transferencia.getControlSum().getDestinationCuitQuantity()));
            cantDestino.setDataType(DataType.INTEGER);
            factList.getFact().add(cantDestino);


            ClientDefinedFact primerDigito = new ClientDefinedFact();
            primerDigito.setName("primer_digito");
            primerDigito.setValue(transferencia.getImporte().substring(0, 1));
            primerDigito.setDataType(DataType.INTEGER);
            factList.getFact().add(primerDigito);
        }


        if(transferencia.getCuitCliente() != null) {

            String cuitCliente = transferencia.getCuitCliente();

            if (!cuitCliente.matches("^[0-9]{2}-[0-9]{8}-[0-9]")) {

                cuitCliente = ISBANStringUtils.agregarGuionesANumeroCuitCuil(cuitCliente);
            }

            ClientDefinedFact cuitOriginante = new ClientDefinedFact();
            cuitOriginante.setName("CUIT_originante");
            cuitOriginante.setValue(cuitCliente);
            cuitOriginante.setDataType(DataType.STRING);

            factList.getFact().add(cuitOriginante);
        }

        ClientDefinedFact moneda = new ClientDefinedFact();
        moneda.setName("MonedaUSD");
        moneda.setValue(String.valueOf(transferencia.isMoneyTypeUSD()));
        moneda.setDataType(DataType.BOOLEAN);
        factList.getFact().add(moneda);

        ClientDefinedFact bcaPrivada = new ClientDefinedFact();
        bcaPrivada.setName("Bca_Privada");
        bcaPrivada.setValue(String.valueOf(transferencia.isBcaPrivada()));
        bcaPrivada.setDataType(DataType.BOOLEAN);
        factList.getFact().add(bcaPrivada);

        BiocatchRsaRequestBuilder.buildBiocatchRsaRequestBuilder(transferencia.getBiocatchResponseData(), factList);

        return factList;
    }

}
