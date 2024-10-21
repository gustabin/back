/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagos.dao.PagoTarjetaCreditoDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.DatosPagoTC;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoMonedaPagoTCEnum;
import ar.com.santanderrio.obp.servicios.pagos.entities.TipoPagoTCEnum;

/**
 * The Class PagoTarjetaCreditoDAOImpl.
 * 
 * @author marcelo.ruiz
 */
@Component
public class PagoTarjetaCreditoDAOImpl implements PagoTarjetaCreditoDAO {
	/** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PagoTarjetaCreditoDAOImpl.class);

    /** The Constant TIMEOUT_EXCEPTION. */
    private static final String TIMEOUT_EXCEPTION = "iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ";

    /** Constante del nombre del servcio de Baja de TJ. */
    private static final String NOMBRE_SERVICIO_BAJTJPAGPR = "BAJTJPAGPR";

    /** numerod de version de BAJA. */
    private static final String VERSION_SERVICIO_BAJTJPAGPR = "100";

    /** The Constant OK_CODIGO_RETORNO. */
    private static final int OK_CODIGO_RETORNO = 0;

    /** The Constant MONEDA_PESO. */
    private static final String MONEDA_PESO = "0";

    /** The Constant MONEDA_DOLAR. */
    private static final String MONEDA_DOLAR = "1";

    /** The Constant MONEDA_AMBAS. */
    private static final String MONEDA_AMBAS = "2";

    /** The Constant ERROR_IATX. */
    private static final String ERROR_IATX = "Error de IATX.";

    /** The Constant CODIGO_AMEX_42. */
    private static final String CODIGO_AMEX_42 = "42";

    /** The Constant CODIGO_AMEX_6. */
    private static final String CODIGO_AMEX_6 = "6";


    /** The Constant BLANK. */
    private static final String BLANK = " ";

    /** The Constant COD_EMP_VISA_SIZE. */
    private static final int COD_EMP_VISA_SIZE = 4;

    /** The Constant DEN_EMP_VISA_SIZE. */
    private static final int DEN_EMP_VISA_SIZE = 30;

    /** The Constant DEN_CTA_VISA_SIZE. */
    private static final int DEN_CTA_VISA_SIZE = 40;

    /** The Constant PRODUCTO_VISA_SIZE. */
    private static final int PRODUCTO_VISA_SIZE = 60;
	
    /** The Constant COTIZACION_DECIMALES_SIZE. */
    private static final int COTIZACION_DECIMALES_SIZE = 4;
	
    /** The Constant COTIZACION_TOTAL_SIZE. */
    private static final int COTIZACION_TOTAL_SIZE = 13;
	
    /** The Constant SALDO_DECIMALES_SIZE. */
    private static final int SALDO_DECIMALES_SIZE = 2;

    /** The Constant SALDO_TOTAL_SIZE. */
    private static final int SALDO_TOTAL_SIZE = 13;

    /** The Constant NYA_SIZE. */
    private static final int NYA_SIZE = 40;

    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;

    /** The servicio pag tjc. */
    @Value("${SERVICIO.PREFIJO.PAGTJC}")
    private String servicioPagTjc;

    /** The version pag tjc. */
    @Value("${SERVICIO.VERSION.PAGTJC}")
    private String versionPagTjc;

    /** The servicio programar pago. */
    @Value("${SERVICIO.PREFIJO.ALTTJCAGPA}")
    private String servicioProgramarPago;

    /** The version programar pago. */
    @Value("${SERVICIO.VERSION.ALTTJCAGPA}")
    private String versionProgramarPago;

    /**
     * Paga la Tarjeta de Credito.
     *
     * @param cliente
     *            the cliente
     * @param pago
     *            the pago
     * @return the string
     * @throws DAOException
     *             the DAO exception
     */
    @Override
    public String pagar(Cliente cliente, DatosPagoTC pago) throws DAOException {
        IatxRequest request = new IatxRequest(servicioPagTjc, versionPagTjc);
        request.setData(generateRequestData(cliente, pago));
        return ejecutarServicio(request);
    }

    /**
     * Programa el pago de una Tarjeta de Credito.
     *
     * @param cliente
     *            the cliente
     * @param datosPagoTC
     *            the datos pago TC
     * @return the string
     * @throws DAOException
     *             the DAO exception
     */
    @Override
    public String programarPago(Cliente cliente, DatosPagoTC datosPagoTC) throws DAOException {
        IatxRequest request = new IatxRequest(servicioProgramarPago, versionProgramarPago);
        request.setData(generateRequestDataProgramarPago(cliente, datosPagoTC));
        return ejecutarServicio(request);
    }

    /**
     * Realiza el llamado al servicio de pago con el request que se arma para
     * cada servicio, pago y pago programado.
     *
     * @param request
     *            the request
     * @return the string
     * @throws DAOException
     *             the DAO exception
     */
    private String ejecutarServicio(IatxRequest request) throws DAOException {
        try {
            IatxResponse iatxResponse = iatxComm.exec(request);
            if (iatxResponse.getErrorCode() == 0) {
                return iatxResponse.getNroComprobante();
            } else {
                throw new DAOException(String.valueOf(iatxResponse.getErrorMessage()),
                        String.valueOf(iatxResponse.getErrorCode()));
            }
        } catch (IatxException e) {
            LOGGER.error(ERROR_IATX, e);
            if (TIMEOUT_EXCEPTION.equals(e.getMessage())) {
                String timeOutException = "TimeOutException. Se envio la transaccion al CICS pero no se recibio respuesta";
                LOGGER.error(timeOutException, e);
                throw new DAOException(timeOutException, "TIME_OUT");
            } else {
                LOGGER.error(e.getMessage(), e);
                throw new DAOException(e.getMessage());
            }
        }
    }

    /**
     * Genera el requestData para programar un pago, llamado al servicio iatx.
     * 
     * @param cliente
     *            the cliente
     * @param datosPagoTC
     *            the datos pago TC
     * @return the iatx request data
     */
    private IatxRequestData generateRequestDataProgramarPago(Cliente cliente, DatosPagoTC datosPagoTC) {
        IatxRequestData requestData = new IatxRequestData(cliente);

        requestData.addBodyValue(
                datosPagoTC.getNroCuentaTarjeta().substring(datosPagoTC.getNroCuentaTarjeta().length() - 15));
        requestData.addBodyValue(datosPagoTC.getTipoTarjeta());
        requestData.addBodyValue(datosPagoTC.getNroTarjeta().substring(datosPagoTC.getNroTarjeta().length() - 16));
        requestData.addBodyValue(FechaUtils.transformarFormatoFecha(datosPagoTC.getFechaPagoProgramado()));

        if (MONEDA_PESO.equals(datosPagoTC.getCodigoMoneda()) || MONEDA_AMBAS.equals(datosPagoTC.getCodigoMoneda())) {
            String importePagoTC = ISBANStringUtils.formatearConComaYVariosDecimales2(datosPagoTC.getImportePagoTC(), 2);
            importePagoTC = importePagoTC.replaceAll(",", "").replaceAll("\\.", "");
            importePagoTC = StringUtils.leftPad(importePagoTC, 15, "0");
            requestData.addBodyValue(importePagoTC);
        } else {
            requestData.addBodyValue(StringUtils.repeat("0", 15));
        }

        if ("10".equals(datosPagoTC.getTipoCuentaDebito())) {
            requestData.addBodyValue("03"); // no se sabe porq va asi, pero es
                                            // asi
        } else if ("09".equals(datosPagoTC.getTipoCuentaDebito())) {
            requestData.addBodyValue("00");// no se sabe porq va asi, pero es
                                           // asi
        } else {
            requestData.addBodyValue(datosPagoTC.getTipoCuentaDebito());
        }

        requestData.addBodyValue(
                datosPagoTC.getSucursalCuentaDebito().substring(datosPagoTC.getSucursalCuentaDebito().length() - 3));
        requestData.addBodyValue(
                datosPagoTC.getNroCuentaDebito().substring(datosPagoTC.getNroCuentaDebito().length() - 7));

        if (MONEDA_DOLAR.equals(datosPagoTC.getCodigoMoneda()) || MONEDA_AMBAS.equals(datosPagoTC.getCodigoMoneda())) {
            String importeDolares = ISBANStringUtils.formatearConComaYVariosDecimales2(datosPagoTC.getImporteDolares(), 2);
            importeDolares = importeDolares.replaceAll(",", "").replaceAll("\\.", "");
            importeDolares = StringUtils.leftPad(importeDolares, 15, "0");
            requestData.addBodyValue(importeDolares);
        } else {
            requestData.addBodyValue(StringUtils.repeat("0", 15));
        }

        if ("10".equals(datosPagoTC.getTipoCuentaBancoDolares())) { // porq no
                                                                    // se, pero
                                                                    // es asi
            requestData.addBodyValue("03");
        } else {
            requestData.addBodyValue(datosPagoTC.getTipoCuentaBancoDolares());
        }
        requestData.addBodyValue(
                datosPagoTC.getSucursalBancoDolares().substring(datosPagoTC.getSucursalBancoDolares().length() - 3));
        requestData.addBodyValue(
                datosPagoTC.getNroCuentaBancoDolares().substring(datosPagoTC.getNroCuentaBancoDolares().length() - 7));

        if ("1".equals(datosPagoTC.getTipoPagoTC())) {
            requestData.addBodyValue("2");
        } else {
            requestData.addBodyValue(datosPagoTC.getTipoPagoTC());
        }

        requestData.addBodyValue(datosPagoTC.getMomentoPagoTC());
        return requestData;
    }

    /**
     * Genera el requestData para el llamado al servicio iatx.
     * 
     * @param cliente
     *            the cliente
     * @param pago
     *            the pago
     * @return the iatx request data
     */
    private IatxRequestData generateRequestData(Cliente cliente, DatosPagoTC pago) {
        IatxRequestData requestData = new IatxRequestData(cliente);
        // Tipo de cambio unificado
        requestData.addBodyValue(StringUtils.repeat(BLANK, COD_EMP_VISA_SIZE));		// Codigo Empresa Visa
        requestData.addBodyValue(StringUtils.repeat(BLANK, DEN_EMP_VISA_SIZE));		// Denominacion Empresa Visa
        requestData.addBodyValue(StringUtils.repeat(BLANK, PRODUCTO_VISA_SIZE));	// Producto Visa
        requestData.addBodyValue(pago.getTipoTarjeta());
        String nroCuentaTarjeta = StringUtils.leftPad(ISBANStringUtils.eliminarCeros(pago.getNroCuentaTarjeta()), 10, "0");
        requestData.addBodyValue(nroCuentaTarjeta);
        requestData.addBodyValue(StringUtils.repeat(BLANK, DEN_CTA_VISA_SIZE));		// Denominacion cuenta Visa
        requestData.addBodyValue(getTipoCuentaDebito(pago.getTipoCuentaDebito()));
        String nroCuentaDebito = ISBANStringUtils.eliminarCeros(pago.getNroCuentaDebito());
        requestData.addBodyValue(StringUtils.leftPad(nroCuentaDebito, 7, "0"));
        String sucursalCuentaDebito = ISBANStringUtils.formatearSucursal(pago.getSucursalCuentaDebito());
        requestData.addBodyValue(sucursalCuentaDebito);
        requestData.addBodyValue(pago.getTipoPagoTC());
        if (TipoPagoTCEnum.OTRO.getValorServicio().equals(pago.getTipoPagoTC())) {
        	String importePago = "0";
        	if (TipoMonedaPagoTCEnum.DOLARES.getValue().equals(pago.getCodigoMoneda())) {
        		importePago = pago.getImportePagoDolares();
        	} else {
        		importePago = pago.getImportePagoPesos();
        	}
        	requestData.addBodyValue(ISBANStringUtils.ajustadorBigDecimalIatx(new BigDecimal(importePago), 15));
        } else {
        	requestData.addBodyValue(StringUtils.repeat("0", 15));
        }
        requestData.addBodyValue("  ");  	// Codigo Moneda
        requestData.addBodyValue(StringUtils.repeat("0", 8));	// Fecha de vencimiento
        String nroTarjeta = ISBANStringUtils.eliminarCeros(pago.getNroTarjeta());
        requestData.addBodyValue(nroTarjeta);
		String sucCtaTarj = ISBANStringUtils.eliminarCeros(pago.getSucursalCuentaTarjeta());
        requestData.addBodyValue(StringUtils.leftPad(sucCtaTarj, 3, "0"));
        requestData.addBodyValue(pago.getCodigoTitularidad());
        requestData.addBodyValue(pago.getMomentoPagoTC());
        String nya = StringUtils.rightPad(cliente.getNombre().trim() + BLANK + cliente.getApellido1().trim(), NYA_SIZE);
        requestData.addBodyValue(nya.length() <= NYA_SIZE ? nya : nya.substring(0, NYA_SIZE));
        requestData.addBodyValue(cliente.getNup());
        requestData.addBodyValue(pago.getModoPagoTC());
        requestData.addBodyValue(pago.getNroSobre());
        requestData.addBodyValue(pago.getCantCheques());
        requestData.addBodyValue(getTipoCuentaDebito(pago.getTipoCuentaBancoDolares()));
        if (TipoMonedaPagoTCEnum.AMBOS.getValue().equals(pago.getCodigoMoneda())) {
        	String sucursalCuentaBancoDolares = ISBANStringUtils.formatearSucursal(pago.getSucursalBancoDolares());
        	requestData.addBodyValue(sucursalCuentaBancoDolares);
        	
        	String nroCuentaBancoDolares = ISBANStringUtils.eliminarCeros(pago.getNroCuentaBancoDolares());
        	
        	requestData.addBodyValue(StringUtils.leftPad(nroCuentaBancoDolares, 7, "0"));
        	
        	if (TipoPagoTCEnum.OTRO.getValorServicio().equals(pago.getTipoPagoTC())) {
        		BigDecimal bigImporteDolares = new BigDecimal(pago.getImportePagoDolares());
        		String importeDolares = ISBANStringUtils.ajustadorBigDecimalIatx(bigImporteDolares, 15);
        		requestData.addBodyValue(importeDolares);
        	} else {
        		requestData.addBodyValue(StringUtils.repeat("0", 15));
        	}
        } else {
        	requestData.addBodyValue("000");
        	requestData.addBodyValue(StringUtils.repeat("0", 7));
        	requestData.addBodyValue(StringUtils.repeat("0", 15));
        }
        // Cotizacion comprador
        requestData.addBodyValue(ISBANStringUtils.formatearNumericosParaIatxSeparadoPorPunto(pago.getCotizacionComprador(),
        		COTIZACION_TOTAL_SIZE, COTIZACION_DECIMALES_SIZE));
        // Cotizacion vendedor
        requestData.addBodyValue(ISBANStringUtils.formatearNumericosParaIatxSeparadoPorPunto(pago.getCotizacionVendedor(),
        		COTIZACION_TOTAL_SIZE, COTIZACION_DECIMALES_SIZE));
        // Saldo pendiente en dolares
        requestData.addBodyValue(ISBANStringUtils.formatearNumericosParaIatxSeparadoPorPunto(pago.getSaldoPendienteDolares(),
        		SALDO_TOTAL_SIZE, SALDO_DECIMALES_SIZE));
        // Saldo pendiente en pesos
        requestData.addBodyValue(ISBANStringUtils.formatearNumericosParaIatxSeparadoPorPunto(pago.getSaldoPendientePesos(),
        		SALDO_TOTAL_SIZE, SALDO_DECIMALES_SIZE));

        return requestData;
    }

    /**
     * Transforma el tipo cuenta de debito para ser enviado al servicio iatx.
     * 
     * @param tipoCuentaDebito
     *            the tipo cuenta debito
     * @return the tipo cuenta debito
     */
    private String getTipoCuentaDebito(String tipoCuentaDebito) {
        if ("10".equals(tipoCuentaDebito)) {
            return "03";
        } else if ("09".equals(tipoCuentaDebito)) {
            return "00";
        } else {
            return tipoCuentaDebito;
        }
    }

    /**
     * Ejecutar baja pago programado de tarjeta credito. iatx: CNSTJCPAGP
     *
     * @author B041299, Manuel.Vargas
     * @param pagoPendiente
     *            the pago pendiente
     * @param cliente
     *            the cliente
     * @return the resultado transaccion
     * @throws DAOException
     *             the DAO exception
     * @see ar.com.santanderrio.obp.servicios.pagos.dao.PagoTarjetaCreditoDAO#ejecutarBajaPagoProgramadoDeTarjetaCredito(ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView,
     *      ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<ResultadoTransaccion> ejecutarBajaPagoProgramadoDeTarjetaCredito(PagoPendiente pagoPendiente,
            Cliente cliente) throws DAOException {

        Respuesta<ResultadoTransaccion> respuestaResultado = new Respuesta<ResultadoTransaccion>();
        try {
            IatxRequest iatxRequest = new IatxRequest(NOMBRE_SERVICIO_BAJTJPAGPR, VERSION_SERVICIO_BAJTJPAGPR);
            IatxRequestData iatxRequestData = generarRequestDataBajaPagoProgTCredito(cliente, pagoPendiente);

            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
            int errorCode = iatxResponse.getErrorCode();
            if (OK_CODIGO_RETORNO == errorCode) {
                respuestaResultado.setEstadoRespuesta(EstadoRespuesta.OK);
            } else {
                respuestaResultado.setEstadoRespuesta(EstadoRespuesta.ERROR);
            }
            return respuestaResultado;
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    /**
     * Generar request data baja pago prog T credito.
     *
     * @param cliente
     *            the cliente
     * @param pagoPendiente
     *            the pago pendiente
     * @return the iatx request data
     */
    private IatxRequestData generarRequestDataBajaPagoProgTCredito(Cliente cliente, PagoPendiente pagoPendiente) {
        IatxRequestData requestData = new IatxRequestData(cliente);
        // 1. Nro_Cuenta_Tarjeta N15 Número de cuenta de la tarjeta de crédito.
        requestData.addBodyValue(pagoPendiente.getNumeroCuentaDeTarjetaCredito());
        // 2. Tipo_Tarjeta N01 Tipo de tarjeta
        if (pagoPendiente.getTipoCuentaTarjeta().getCodigo().length() == 1) {
            requestData.addBodyValue(pagoPendiente.getTipoCuentaTarjeta().getCodigo());
        } else if (pagoPendiente.getTipoCuentaTarjeta().getCodigo().equals(CODIGO_AMEX_42)) {
            // antes de impactar BAJTJPAGPR, debemos cambiar el codigo de amex
            // de 42 a 6
            requestData.addBodyValue(CODIGO_AMEX_6);
        } else {
            requestData.addBodyValue(pagoPendiente.getTipoCuentaTarjeta().getCodigo().substring(1));
        }
        // 3. Nro_Tarjeta N16 Número de tarjeta
        if (pagoPendiente.getNroTarjetaCredito().length() == 16) {
            requestData.addBodyValue(pagoPendiente.getNroTarjetaCredito());
        } else {
            requestData.addBodyValue(pagoPendiente.getNroTarjetaCredito().substring(4));
        }
        // 4. Fecha_Pago_Programado A10 Fecha de pago programado
        SimpleDateFormat dateFormatterPagoProgramado = new SimpleDateFormat("yyyy-MM-dd");
        String fechaLimite = dateFormatterPagoProgramado.format(pagoPendiente.getFechaPagoProgramado());
        requestData.addBodyValue(fechaLimite);
        // pesos
        // 5. Importe_Pesos N15 Importe a pagar en Pesos - Formateo
        String importe = ISBANStringUtils.ajustadorBigDecimalIatx(pagoPendiente.getImporte(), 15);
        requestData.addBodyValue(importe);
        // 6. Tipo_Cuenta_Pesos A02 Tipo Cuenta Pesos : ´
        requestData.addBodyValue(pagoPendiente.getTipoCuentaBancoPesos());
        // 7. Sucursal_Pesos N03 Sucursal
        requestData.addBodyValue(ISBANStringUtils.formateadorConCerosIzq(pagoPendiente.getSucursalBancoPesos(), 3));
        // 8. Nro_Cuenta_Pesos N07 Nro de cuenta en Pesos
        requestData.addBodyValue(ISBANStringUtils
                .formateadorConCerosIzq(Integer.toString(new Integer(pagoPendiente.getNumeroCuentaBancoPesos())), 7));
        // dolares
        // 9. Importe_Dolares N15 Importe a pagar en Dólares - formateo
        String importeUSS = ISBANStringUtils.ajustadorBigDecimalIatx(pagoPendiente.getImporteUSS(), 15);
        requestData.addBodyValue(importeUSS);
        // 10. Tipo_Cuenta_Dolar A02 Tipo Cuenta Dolar :
        requestData.addBodyValue(pagoPendiente.getTipoCuentaBancoDolares());
        // 11. Sucursal_ Dólar N03 Sucursal
        requestData.addBodyValue(ISBANStringUtils.formateadorConCerosIzq(pagoPendiente.getSucursalBancoDolares(), 3));
        // 12. Nro_Cuenta_Dolar N07 Nro de cuenta en Dolares
        requestData.addBodyValue(ISBANStringUtils
                .formateadorConCerosIzq(Integer.toString(new Integer(pagoPendiente.getNumeroCuentaBancoDolares())), 7));
        return requestData;
    }

}
