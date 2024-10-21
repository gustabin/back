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

import ar.com.santanderrio.obp.generated.webservices.rsa.Amount;
import ar.com.santanderrio.obp.generated.webservices.rsa.ClientDefinedFact;
import ar.com.santanderrio.obp.generated.webservices.rsa.DataType;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.generated.webservices.rsa.EventType;
import ar.com.santanderrio.obp.generated.webservices.rsa.FactList;
import ar.com.santanderrio.obp.generated.webservices.rsa.TransactionData;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.debinws.view.SolicitarDebinView;
import ar.com.santanderrio.obp.servicios.rsa.common.RSAConstants;

/**
 * Esta clase se encarga de construir el request para validar con RSA.
 *
 * @author ignacio.valek
 * @see {@link AbstractRsaRequestBuilder}
 * @since Nov 11, 2016
 */
public class SolicitudDebinRsaRequestBuilder extends AbstractRsaRequestBuilder {

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
        SolicitarDebinView solicitudDebin = (SolicitarDebinView) operacionDeRiesgo;
        ed.setClientDefinedEventType(RSAConstants.GENERACION_DEBIN);
        ed.setEventType(EventType.PAYMENT);
        ed.setTransactionData(generarTransactionDataTransferencia(solicitudDebin));
        ed.setClientDefinedAttributeList(generarFactListSolicitarDebin(solicitudDebin));
        return ed;
    }

    /**
     * Generar transaction data transferencia.
     *
     * @param solicitudDebin
     *            the transferencia
     * @return the transaction data
     */
    private TransactionData generarTransactionDataTransferencia(SolicitarDebinView solicitudDebin) {
        TransactionData data = new TransactionData();
        Amount amount = new Amount();
        data.setAmount(amount);
        amount.setAmount(new BigDecimal(solicitudDebin.getImporte()).multiply(FACTOR_CENTAVOS).longValue());


        Date fechaInput = null;
        try {
            fechaInput = DATE_FORMATTER_INPUT.parse(solicitudDebin.getFechaVencimiento());
        } catch (ParseException e) {
            LOGGER.info("ERROR: La fecha de la transferencia vino nula");
        }
        String dueDate = (fechaInput == null ? null : DATE_FORMATTER.format(fechaInput));
        data.setDueDate(dueDate);

        return data;
    }

    /**
     * Generar fact list transferencia.
     *
     * @param solicitudDebin
     *            the request data
     * @return the fact list
     */
    private FactList generarFactListSolicitarDebin(SolicitarDebinView solicitudDebin) {
        FactList factList = new FactList();

      ClientDefinedFact celularMyA = new ClientDefinedFact();
        celularMyA.setName("celularmya");
        celularMyA.setValue(String.valueOf(solicitudDebin.isTieneCelularMyA()));
        celularMyA.setDataType(DataType.BOOLEAN);
        factList.getFact().add(celularMyA);
        
        ClientDefinedFact cuitComprador = new ClientDefinedFact();
        cuitComprador.setName("cuit_comprador");
        if(solicitudDebin.getCuitComprador() != null) {
        	cuitComprador.setValue(!solicitudDebin.getCuitComprador().contains("-") ? ISBANStringUtils.agregarGuionesANumeroCuitCuil(solicitudDebin.getCuitComprador()) : solicitudDebin.getCuitComprador());
        } else {
        	cuitComprador.setValue("");
        }
        cuitComprador.setDataType(DataType.STRING);
        factList.getFact().add(cuitComprador);
        
        ClientDefinedFact cuitVendedor = new ClientDefinedFact();
        cuitVendedor.setName("cuit_vendedor");
        if(solicitudDebin.getCuitVendedor() != null ) {
        	cuitVendedor.setValue(!solicitudDebin.getCuitVendedor().contains("-") ? ISBANStringUtils.agregarGuionesANumeroCuitCuil(solicitudDebin.getCuitVendedor()) : solicitudDebin.getCuitVendedor());
        }	else {
        	cuitVendedor.setName("");
        }
        cuitVendedor.setDataType(DataType.STRING);
        factList.getFact().add(cuitVendedor);
        
        if(solicitudDebin.getCantDiasUltimoCambioClave() != null) {
	        ClientDefinedFact cantDiasClave = new ClientDefinedFact();
	        cantDiasClave.setName("cantDiasUltimoCambioClave");
	        cantDiasClave.setValue(String.valueOf(solicitudDebin.getCantDiasUltimoCambioClave()));
	        cantDiasClave.setDataType(DataType.INTEGER);
	        factList.getFact().add(cantDiasClave);
        }
        
        if(solicitudDebin.getCantDiasUltimoCambioToken() != null) {
	        ClientDefinedFact cantDiasToken = new ClientDefinedFact();
	        cantDiasToken.setName("cantDiasUltimoCambioToken");
	        cantDiasToken.setValue(String.valueOf(solicitudDebin.getCantDiasUltimoCambioToken()));
	        cantDiasToken.setDataType(DataType.INTEGER);
	        factList.getFact().add(cantDiasToken);
        }
        
        return factList;
    }

}
