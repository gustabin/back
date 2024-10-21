/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.DatosTarjetaDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjetaBuilder;

/**
 * The Class DatosTarjetaDAOImpl.
 */
@Component
public class DatosTarjetaDAOImpl implements DatosTarjetaDAO {

    /** The Constant NOMBRE_SERVICIO_IATX. */
    private static final String NOMBRE_SERVICIO_IATX = "CNSTJCDATO";

    /** The Constant VERSION_SERVICIO_IATX. */
    private static final String VERSION_SERVICIO_IATX = "190";

    /** The Constant SERVICIO_CNSTJCPAGP. */
    private static final String SERVICIO_CNSTJCPAGP = "CNSTJCPAGP";

    /** The Constant VERSION_SERVICIO_CNSTJCPAGP. */
    private static final String VERSION_SERVICIO_CNSTJCPAGP = "100";

    /** The Constant SUCURSAL_REQUEST_SIZE. */
    private static final int SUCURSAL_REQUEST_SIZE = 4;

    /** The Constant NRO_TARJETA_REQUEST_SIZE. */
    private static final int NRO_TARJETA_REQUEST_SIZE = 16;

    /** The Constant COD_TITULARIDAD_REQUEST_SIZE. */
    private static final int COD_TITULARIDAD_REQUEST_SIZE = 1;

    /** The Constant NRO_CUENTA_REQUEST_SIZE. */
    private static final int NRO_CUENTA_REQUEST_SIZE = 10;

    /** The Constant CERO_STRING. */
    private static final String CERO_STRING = "0";

    /** The Constant CODIGO_DE_ERROR_DESCONOCIDO_MSG. */
    private static final String CODIGO_DE_ERROR_DESCONOCIDO_MSG = "Codigo de error iatx desconocido";

    /** The Constant CODIGO_DE_ERROR_INICIO_STRING. */
    private static final String CODIGO_DE_ERROR_INICIO_STRING = "[codigo_error=";

    /** The Constant CODIGO_DE_ERROR_FIN_STRING. */
    private static final String CODIGO_DE_ERROR_FIN_STRING = "]";

    /** The Constant OK_CODIGO_RETORNO. */
    private static final int OK_CODIGO_RETORNO = 0;

    /** The Constant SIN_PAGOS. */
    private static final int SIN_PAGOS = 10000001;

    /** The Constant CODIGO_RETORNO_ERROR_COTIZACION. */
    private static final int CODIGO_RETORNO_ERROR_COTIZACION = 10000009;

    /** The Constant CODIGO_ERROR_TARJETA_CERRADA. */
    private static final int CODIGO_ERROR_TARJETA_CERRADA = 10000001;

    /** The Constant ERROR_AL_FORMATEAR_FECHA. */
    private static final String ERROR_AL_FORMATEAR_FECHA = "Error al formatear fecha";

    /** The date formatter. */
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");

    /** The date formatter pago programado. */
    private final SimpleDateFormat dateFormatterPagoProgramado = new SimpleDateFormat("yyyy-MM-dd");

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DatosTarjetaDAOImpl.class);

    /** The Constant AMERICAN_EXPRESS. */
    private static final String AMERICAN_EXPRESS = "42";

    /** The Constant VISA. */
    private static final String VISA = "07";

    /** The Constant MASTER. */
    private static final String MASTER = "06";

    /**
     * Este cambio se hace ya que el campo espera un solo digito y el codigo que
     * le corresponde a AMEX es 42.
     */
    private static final String REEMPLAZO_AMERICAN = "6";

    /** The Constant SIN_PAGOS_SISTEMA_ASOCIADO_AL_ERROR. */
    private static final String SIN_PAGOS_SISTEMA_ASOCIADO_AL_ERROR = "AZT";

    /** The Constant OFFSET_COTIZACION_COMPRADOR. */
    private static final int OFFSET_COTIZACION_COMPRADOR = 39;

    /** The Constant OFFSET_COTIZACION_VENDEDOR. */

    private static final int OFFSET_COTIZACION_VENDEDOR = 38;

    /** The Constant OFFSET_SALDO_PENDIENTE_DOLARES. */
    private static final int OFFSET_SALDO_PENDIENTE_DOLARES = 50;

    /** The Constant OFFSET_SALDO_PENDIENTE_PESOS. */
    private static final int OFFSET_SALDO_PENDIENTE_PESOS = 49;

    /** The Constant OFFSET_SALDO_TOTAL_LIQ_VIG_CONV_DOLARES. */
    private static final int OFFSET_SALDO_TOTAL_LIQ_VIG_CONV_DOLARES = 51;

    /** The Constant OFFSET_SALDO_TOTAL_LIQ_VIG_CONV_PESOS. */
    private static final int OFFSET_SALDO_TOTAL_LIQ_VIG_CONV_PESOS = 52;

    /** The Constant TIPO_CUENTA_OTRO. */
    private static final String TIPO_CUENTA_OTRO = "3";

    /** The Constant TIPO_CUENTA_VISA. */
    private static final String TIPO_CUENTA_VISA = "1";

    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.dao.DatosTarjetaDAO#
     * obtenerDatosTarjeta(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente, ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta, boolean)
     */
    @Override
    @Cacheable(cacheNames = CacheConstants.Names.CACHE_NYO_CNSTJCDATO, key = "#cliente.nup.concat(#cuenta.nroTarjetaCredito)", condition="#usarCache == true")
    public DatosTarjetaBuilder obtenerDatosTarjeta(Cliente cliente, Cuenta cuenta, boolean usarCache) throws DAOException {
        IatxRequest iatxRequest = generarIatxRequest(cliente, cuenta);
        IatxResponse iatxResponse = invocarServicio(iatxRequest);

        return procesarRespuesta(iatxResponse);
    }

    /**
     * CNSTJCPAGP, 100.
     *
     * @param cliente
     *            the cliente
     * @param cuenta
     *            the cuenta
     * @return the list
     * @throws DAOException
     *             the DAO exception
     * @see ar.com.santanderrio.obp.servicios.tarjetas.dao.DatosTarjetaDAO#
     *      obtenerPagosProgramados(ar.com.santanderrio.obp.servicios.clientes.
     *      entities.Cliente,
     *      ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta)
     */
    @Override
    public List<DatosTarjeta> obtenerPagosProgramados(Cliente cliente, Cuenta cuenta) throws DAOException {
        IatxRequest iatxRequest = generarIatxRequestPagosProgramados(cliente, cuenta);
        IatxResponse iatxResponse = invocarServicio(iatxRequest);
        return procesarRespuetaPagosProgramados(iatxResponse);
    }

    /**
     * Generar iatx request pagos programados.
     *
     * @param cliente
     *            the cliente
     * @param cuenta
     *            the cuenta
     * @return the iatx request
     */
    private IatxRequest generarIatxRequestPagosProgramados(Cliente cliente, Cuenta cuenta) {
        IatxRequest request = new IatxRequest(SERVICIO_CNSTJCPAGP, VERSION_SERVICIO_CNSTJCPAGP);
        IatxRequestData requestData = new IatxRequestData(cliente);
        /*
         * E1. Tipo_Tarjeta N01 E2. Nro_Tarjeta_Credito N16 E3. Fecha_Limite A10
         */
        String tipoTarjeta = StringUtils.leftPad(StringUtils.right(cuenta.getTipoCuentaSinUnificar().trim(), 1), 1,
                "0");
        if (cuenta.getTipoCuentaSinUnificar().trim().equalsIgnoreCase(AMERICAN_EXPRESS)) {
            tipoTarjeta = REEMPLAZO_AMERICAN;
        }
        requestData.addBodyValue(tipoTarjeta);
        String nroTarjetaCredito = StringUtils.leftPad(
                StringUtils.right(cuenta.getNroTarjetaCredito().trim(), NRO_TARJETA_REQUEST_SIZE),
                NRO_TARJETA_REQUEST_SIZE, CERO_STRING);
        requestData.addBodyValue(nroTarjetaCredito);
        String fechaLimite = dateFormatterPagoProgramado.format(new Date());
        requestData.addBodyValue(fechaLimite);

        request.setData(requestData);
        return request;
    }

    /**
     * Procesar respueta pagos programados.
     *
     * @param iatxResponse
     *            the iatx response
     * @return the list
     * @throws DAOException
     *             the DAO exception
     */
    private List<DatosTarjeta> procesarRespuetaPagosProgramados(IatxResponse iatxResponse) throws DAOException {
        List<DatosTarjeta> resultado = null;
        int codigoError = Integer.valueOf(iatxResponse.getErrorCode());
        switch (codigoError) {
        case OK_CODIGO_RETORNO:
            resultado = parsearResponsePagosProgramados(iatxResponse);
            break;
        case SIN_PAGOS:
            if (SIN_PAGOS_SISTEMA_ASOCIADO_AL_ERROR.equalsIgnoreCase(iatxResponse.getErrorSystem())) {
                resultado = new ArrayList<DatosTarjeta>();
            }
            break;
        default:
            throw new DAOException(generarCodigoErrorDesconocidoMensaje(codigoError));
        }
        return resultado;
    }

    /**
     * Parsear response pagos programados. servicio: CNSTJCPAGP, 100 version 0.2
     * Modificado por Manuel.Vargas
     * 
     * S1. Codigo retorno extendido N8 S2. Numero cuenta TJC N15 S3. Tipo tarjeta N1
     * S4. Nro tarjeta N16 S5. Fecha limite A10 S6. Cantidad ocurrencias N3 S7.
     * Fecha pago A10 S8. * Importe a pagar pesos N15 (13e,2d)+signo S9. Tipo cuenta
     * banco pesos A2 S10. Sucursal banco pesos N3 S11. Nro cuenta banco pesos N7
     * S12. * Importe a pagar dolares N15 (13e,2d)+signo S13. Tipo cuenta banco
     * dolares A2 S14. Sucursal banco dolares N3 S15. Nro cuenta banco dolares N7
     * S16. Tipo pago A1 S17. Momento pago A1 LOS CAMPOS CON "*" SE ALMACENAN EN
     * VARIABLES QUE YA SE ESTABAN UTILIZANDO ANTERIORMENTE PARA REUTILIZAR EL
     * CODIGO DE PAGOSPENDIENTESBOIMPL
     * 
     * @param iatxResponse
     *            the iatx response
     * @return the list
     * @throws DAOException
     *             the DAO exception
     */
    private List<DatosTarjeta> parsearResponsePagosProgramados(IatxResponse iatxResponse) throws DAOException {
        List<DatosTarjeta> datosTarjetaList = new ArrayList<DatosTarjeta>();

        String numeroCuentaTJC = iatxResponse.getNextData();
        iatxResponse.getNextData();
        iatxResponse.getNextData();
        iatxResponse.getNextData();
        int cantidadRepeticiones = Integer.valueOf(iatxResponse.getNextData());
        for (int i = 0; i < cantidadRepeticiones; i++) {
            DatosTarjeta datosTarjeta = new DatosTarjeta();
            datosTarjeta.setNumeroCuentaDeTarjetaCredito(numeroCuentaTJC);
            datosTarjeta.setFechaPagoProgramado(formatearFechaPagoProgramado(iatxResponse.getNextData()));
            datosTarjeta.setSaldoPesosTC(formatearImporte(iatxResponse.getNextData()));
            datosTarjeta.setTipoCuentaBancoPesos(iatxResponse.getNextData());
            datosTarjeta.setSucursalBancoPesos(Integer.valueOf(iatxResponse.getNextData()));
            datosTarjeta.setNumeroCuentaBancoPesos(Integer.valueOf(iatxResponse.getNextData()));
            datosTarjeta.setSaldoDolaresTC(formatearImporte(iatxResponse.getNextData()));
            datosTarjeta.setTipoCuentaBancoDolares(iatxResponse.getNextData());
            datosTarjeta.setSucursalBancoDolares(Integer.valueOf(iatxResponse.getNextData()));
            datosTarjeta.setNumeroCuentaBancoDolares(Integer.valueOf(iatxResponse.getNextData()));
            datosTarjeta.setTipoPago(iatxResponse.getNextData());
            datosTarjeta.setMomentoPago(iatxResponse.getNextData());
            datosTarjetaList.add(datosTarjeta);
        }
        return datosTarjetaList;
    }

    /**
     * Generar iatx request.
     *
     * @param cliente
     *            the cliente
     * @param cuenta
     *            the cuenta
     * @return the iatx request
     */
    private IatxRequest generarIatxRequest(Cliente cliente, Cuenta cuenta) {
        IatxRequest request = new IatxRequest(NOMBRE_SERVICIO_IATX, VERSION_SERVICIO_IATX);
        IatxRequestData requestData = new IatxRequestData(cliente);
        /*
         * E1. Suc_Cuenta_Tarjeta N4 E2. Nro_Tarjeta_Credito N16 E3. Codigo_Titularidad
         * A1
         */
        // Esto es ilegible
        String sucursalPaquete = StringUtils.leftPad(
                StringUtils.right(cuenta.getSucursalPaquete().trim(), SUCURSAL_REQUEST_SIZE), SUCURSAL_REQUEST_SIZE,
                CERO_STRING);
        requestData.addBodyValue(sucursalPaquete);
        String nroTarjetaCredito = StringUtils.leftPad(
                StringUtils.right(cuenta.getNroTarjetaCredito().trim(), NRO_TARJETA_REQUEST_SIZE),
                NRO_TARJETA_REQUEST_SIZE, CERO_STRING);
        requestData.addBodyValue(nroTarjetaCredito);
        String codigoTitularidad = StringUtils.left(cuenta.getCodigoTitularidad().trim(), COD_TITULARIDAD_REQUEST_SIZE);
        requestData.addBodyValue(codigoTitularidad);

        // Tipo de cambio unificado
        // Nuevos campos CNSTJCDATO190
        requestData.addBodyValue(TipoCuenta.VISA.equals(cuenta.getTipoCuentaEnum()) ? TIPO_CUENTA_VISA : TIPO_CUENTA_OTRO);
        requestData.addBodyValue(StringUtils.leftPad(cuenta.getNroCuentaProducto().substring(6), NRO_CUENTA_REQUEST_SIZE));

        request.setData(requestData);
        return request;
    }

    /**
     * Invocar servicio.
     *
     * @param iatxRequest
     *            the iatx request
     * @return the iatx response
     * @throws DAOException
     *             the DAO exception
     */
    private IatxResponse invocarServicio(IatxRequest iatxRequest) throws DAOException {
        IatxResponse response = null;
        try {
            response = iatxComm.exec(iatxRequest);
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return response;
    }

    /**
     * Generar codigo error desconocido mensaje.
     *
     * @param errorCode
     *            the error code
     * @return the string
     */
    private String generarCodigoErrorDesconocidoMensaje(int errorCode) {
        return new StringBuilder().append(CODIGO_DE_ERROR_DESCONOCIDO_MSG).append(CODIGO_DE_ERROR_INICIO_STRING)
                .append(errorCode).append(CODIGO_DE_ERROR_FIN_STRING).toString();
    }

    /**
     * Procesar respuesta.
     *
     * @param iatxResponse
     *            the iatx response
     * @return the datos tarjeta builder
     * @throws DAOException
     *             the DAO exception
     */
    private DatosTarjetaBuilder procesarRespuesta(IatxResponse iatxResponse) throws DAOException {
        int errorCode = iatxResponse.getErrorCode();
        DatosTarjeta resultado = null;
        switch (errorCode) {
        case OK_CODIGO_RETORNO:
            resultado = parsearResponse(iatxResponse);
            break;
        case CODIGO_RETORNO_ERROR_COTIZACION:
            resultado = new DatosTarjeta();
            resultado.setEstadoTarjetaCredito("29");
            resultado.setSaldoPesosTC(new BigDecimal("0"));
            resultado.setSaldoDolaresTC(new BigDecimal("0"));
            break;
        default:
            throw new DAOException(generarCodigoErrorDesconocidoMensaje(errorCode));
        }
        return new DatosTarjetaBuilder(resultado);
    }

    /**
     * Parsear response.
     *
     * @param iatxResponse
     *            the iatx response
     * @return the datos tarjeta
     * @throws DAOException
     *             the DAO exception
     */
    private DatosTarjeta parsearResponse(IatxResponse iatxResponse) throws DAOException {
        DatosTarjeta datos = new DatosTarjeta();

        // Nuevos campos CNSTJCDATO190
        // S1. Ceros 	N8

        // S2. Estado_Tarjeta_Credito	N2
        // S3. Ciclo_Tarjeta_Credito	N2
        // S4. Forma_Pago_Tarjeta_Credito	N2
        // S5. Fecha_Ultimo_Cierre_TC	AAAAMMDD
        // S6. Saldo_Ultimo_Cierre_$_TC	N15(13e,2d)+A1(signo)
        // S7. Pagos_$_TC	N15(13e,2d)
        // S8. Saldo_$_TC	N15(13e,2d)+A1(signo)
        // S9. Pago_Minimo_$_TC	N15(13e,2d)
        // S10. Fecha_Ultimo_Pago_TC	A8(AAAAMMDD)
        // S11. Fecha_Vencimiento_Liquidacion_TC	A8(AAAAMMDD)
        // S12. Saldo_Ultimo_Cierre_U$S_TC	N15(13e,2d)+A1(signo)
        // S13. Pagos_ U$S_TC	N15(13e,2d)
        // S14. Saldo_ U$S_TC	N15(13e,2d)+A1(signo)
        // S15. Pago_Minimo_Impago_TC	N15(13e,2d)
        // S16. Fecha_Proximo_Cierre_TC	A8(AAAAMMDD)
        // S17. Ajuste_Credito_$_TC	N15(13e,2d)
        // S18. Ajuste_Debito_$_TC	N15(13e,2d)
        // S19. Adelantos_$_TC	N15(13e,2d)
        // S20. Consumos_$_TC	N15(13e,2d)
        // S21. Saldo_Actual_$_TC	N15(13e,2d)+A1(signo)
        // S22. Limite_Compra_$_TC	N15(13e,2d)
        // S23. Fecha_Proximo_Vencimiento_TC	A8(AAAAMMDD)
        // S24. Ajuste_Credito_U$S_TC	N15(13e,2d)
        // S25. Ajuste_Debito_ U$S_TC	N15(13e,2d)
        // S26. Adelantos_ U$S_TC	N15(13e,2d)
        // S27. Consumos_ U$S_TC	N15(13e,2d)
        // S28. Saldo_Actual_ U$S_TC	N15(13e,2d)+A1(signo)
        // S29. Limite_Financiacion_TC	N15(13e,2d)
        // S30. Marca_vigencia_Vto0	A1
        // S31. Fecha_2do_Vencimiento	N8 (AAAAMMDD)
        // S32. Saldo_Ultimo_Resumen_$_2do_Vto	N15(13e,2d)+A1(signo)
        // S33. Saldo_Ultimo_Resumen_u$s_2do_Vto	N15(13e,2d)+A1(signo)
        // S34. Marca_vigencia_Vto2	A1
        // S35. Fecha_3er_Vencimiento	N8(AAAAMMDD)
        // S36. Saldo_Ultimo_Resumen_$_3er_Vto	N15(13e,2d)+A1(signo)
        // S37. Saldo_Ultimo_Resumen_u$s_3er_Vto	N15(13e,2d)+A1(signo)
        // S38. Marca_vigencia_Vto3	A1
        // S39. Cotizacion_vendedor	N13(9e,4d)
        // S40. Cotizacion_comprador	N13(9e,4d)
        // S41. Fecha_cotizacion	N8(AAAAMMDD)
        // S42. Fecha_Vencimiento_Vigente	N8 (AAAAMMDD)
        // S43. Saldo_Ultimo_Resumen_$_Vigente	N15(13e,2d)+A1(signo)
        // S44. Saldo_Ultimo_Resumen_u$s_Vigente	N15(13e,2d)+A1(signo)
        // S45. Pago_Minimo_u$s_TC	N15(13e,2d)
        // S46. Pago_Minimo_Pendiente_cancelar_$_TC	N15(13e,2d)
        // S47. Pago_Minimo_Pendiente_cancelar_u$s_TC	N15(13e,2d)
        // S48. Saldo_impago_$_UNIFICADO_TC	N15(13e,2d)+A1(signo)
        // S49. Saldo_impago_U$S_UNIFICADO_TC	N15(13e,2d)+A1(signo)
        // S50. Saldo_impago_$_vigente    	N15(13e,2d)+A1(signo)
        // S51. Saldo_impago_u$s_vigente    	N15(13e,2d)+A1(signo)
        // S52. Saldo_total_liq_Vigente_conv_Dolar	N15(13e,2d)+A1(signo)
        // S53. Saldo_total_liq_Vigente_conv_Pesos	N15(13e,2d)+A1(signo)
        // S54. Apellido_nombre_	A24
        // S55. FECHA-CIERRE-LIQ-ANT	N 8
        // S56. FECHA-VEN-RESUMEN-ANT	N 8
        // S57. SALDO-PESOS-ANT	N 17 (15e,2d)
        // S58. SALDO-DOLARES-ANT	N 17 (15e,2d)
        // S59. FECHA-PAGO-LIQ-ANT	N 8
        // S60. TIP-CTA-PAGO-LIQ-ACT	N 1
        // S61. NUM-CTA-PAGO-LIQ-ACT	N 10
        // S62. TIP-CUENTA-PAGO-LIQ- ANT	N 1
        // S63. NUM-CUENTA-PAGO-LIQ- ANT	N 10
        // S64. IMPORTE-PAGO-PESOS-ANT	N 17 (15e,2d)
        // S65. IMPORTE-PAGO-DOLARES-ANT	N 17 (15e,2d)
        // S66. IMPORTE-ULTIMO-PAGO	N 17 (13e,2d)
        // S67. IMPORTE-ULT-PAGODOL	N 17 (13e,2d)
        // S68. MARCA-INHAB-CL	A 01
        // S69. NO-EMISION-CARTA	A 01
        // S70. CANT-D-MORA	N 04
        datos.setEstadoTarjetaCredito(iatxResponse.getNextData());   //S2
        datos.setCicloTarjetaCredito(iatxResponse.getNextData());
        datos.setFormaPagoTarjetaCredito(iatxResponse.getNextData());
        datos.setFechaUltimoCierreTC(formatearFecha(iatxResponse.getNextData()));
        datos.setSaldoUltimoCierrePesosTC(formatearImporte(iatxResponse.getNextData()));
        datos.setPagosPesosTC(formatearImporte(iatxResponse.getNextData()));
        datos.setSaldoPesosTC(formatearImporte(iatxResponse.getNextData()));
        datos.setPagoMinimoPesosTC(formatearImporte(iatxResponse.getNextData()));
        datos.setFechaUltimoPagoTC(formatearFecha(iatxResponse.getNextData()));
        datos.setFechaVencimientoLiquidacionTC(formatearFecha(iatxResponse.getNextData()));
        datos.setSaldoUltimoCierreDolaresTC(formatearImporte(iatxResponse.getNextData()));
        datos.setPagosDolaresTC(formatearImporte(iatxResponse.getNextData()));
        datos.setSaldoDolaresTC(formatearImporte(iatxResponse.getNextData()));
        datos.setPagoMinimoImpagoTC(formatearImporte(iatxResponse.getNextData()));
        datos.setFechaProximoCierreTC(formatearFecha(iatxResponse.getNextData()));
        datos.setAjusteCreditoPesosTC(formatearImporte(iatxResponse.getNextData()));
        datos.setAjusteDebitoPesosTC(formatearImporte(iatxResponse.getNextData()));
        datos.setAdelantosPesosTC(formatearImporte(iatxResponse.getNextData()));
        datos.setConsumosPesosTC(formatearImporte(iatxResponse.getNextData()));
        datos.setSaldoActualPesosTC(formatearImporte(iatxResponse.getNextData()));
        datos.setLimiteCompraPesosTC(formatearImporte(iatxResponse.getNextData()));
        datos.setFechaProximoVencimientoTC(formatearFecha(iatxResponse.getNextData()));
        datos.setAjusteCreditoDolaresTC(formatearImporte(iatxResponse.getNextData()));
        datos.setAjusteDebitoDolaresTC(formatearImporte(iatxResponse.getNextData()));
        datos.setAdelantosDolaresTC(formatearImporte(iatxResponse.getNextData()));
        datos.setConsumosDolaresTC(formatearImporte(iatxResponse.getNextData()));
        datos.setSaldoActualDolaresTC(formatearImporte(iatxResponse.getNextData()));
        datos.setLimiteFinanciacionTC(formatearImporte(iatxResponse.getNextData()));
        datos.setCotizacionVendedor(formatearCotizacion(iatxResponse.getData(OFFSET_COTIZACION_VENDEDOR)));
        datos.setCotizacionComprador(formatearCotizacion(iatxResponse.getData(OFFSET_COTIZACION_COMPRADOR)));
        datos.setSaldoPendientePesos(formatearImporte(iatxResponse.getData(OFFSET_SALDO_PENDIENTE_PESOS)));
        datos.setSaldoPendienteDolares(formatearImporte(iatxResponse.getData(OFFSET_SALDO_PENDIENTE_DOLARES)));
        datos.setSaldoTotalConvPesos(formatearImporte(iatxResponse.getData(OFFSET_SALDO_TOTAL_LIQ_VIG_CONV_PESOS)));
        datos.setSaldoTotalConvDolares(formatearImporte(iatxResponse.getData(OFFSET_SALDO_TOTAL_LIQ_VIG_CONV_DOLARES)));

        return datos;
    }

    /**
     * Formatear cotizacion.
     *
     * @param cotizacion the cotizacion
     * @return the big decimal
     */
    private BigDecimal formatearCotizacion(String cotizacion) {
        if (cotizacion == null || cotizacion.trim().isEmpty()) {
            return null;
        }
        return ISBANStringUtils.stringToBigDecimal(cotizacion, 9, 4, false);
    }

    /**
     * Formatear fecha pago programado.
     *
     * @param fecha
     *            the fecha
     * @return the date
     * @throws DAOException
     *             the DAO exception
     */
    private Date formatearFechaPagoProgramado(String fecha) throws DAOException {
        Date date = null;
        try {
            if (fecha != null) {
                date = dateFormatterPagoProgramado.parse(fecha);
            }
        } catch (ParseException e) {
            LOGGER.error(ERROR_AL_FORMATEAR_FECHA, e);
            throw new DAOException(e, ERROR_AL_FORMATEAR_FECHA);
        }
        return date;
    }

    /**
     * Formatear fecha.
     *
     * @param fecha
     *            the fecha
     * @return the date
     * @throws DAOException
     *             the DAO exception
     */
    private Date formatearFecha(String fecha) throws DAOException {
        Date date = null;
        try {
            if (fecha != null) {
                date = dateFormatter.parse(fecha);
            }
        } catch (ParseException e) {
            LOGGER.error(ERROR_AL_FORMATEAR_FECHA, e);
            throw new DAOException(e, ERROR_AL_FORMATEAR_FECHA);
        }
        return date;
    }

    /**
     * Formatear importe.
     *
     * @param importe
     *            the importe
     * @return the big decimal
     */
    private BigDecimal formatearImporte(String importe) {
        if (importe == null || importe.trim().isEmpty()) {
            return null;
        }
        return new BigDecimal(ISBANStringUtils.importePtoFijo2Canonico(importe));
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.dao.DatosTarjetaDAO#
     * tienePagosProgramados(ar.com.santanderrio.obp.servicios.clientes.entities
     * .Cliente, ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta,
     * java.lang.String)
     */
    @Override
    public Boolean tienePagosProgramados(Cliente cliente, DatosTarjeta tarjeta, String fechaLimite)
            throws DAOException {
        IatxRequest request = new IatxRequest(SERVICIO_CNSTJCPAGP, VERSION_SERVICIO_CNSTJCPAGP);

		IatxRequestData requestData = new IatxRequestData(cliente);
		if (AMERICAN_EXPRESS.equals(tarjeta.getTipoCuentaTarjeta().getCodigo())) {
			requestData.addBodyValue("6");
		} else if (VISA.equals(tarjeta.getTipoCuentaTarjeta().getCodigo())) {
			requestData.addBodyValue("7");
		} else if (MASTER.equals(tarjeta.getTipoCuentaTarjeta().getCodigo())) {
			requestData.addBodyValue("3");
		}
		requestData.addBodyValue(tarjeta.getNumeroTarjeta());
		requestData.addBodyValue(fechaLimite);

        request.setData(requestData);
        IatxResponse iatxResponse;
        try {
            iatxResponse = iatxComm.exec(request);
            if (iatxResponse.getErrorCode() == CODIGO_ERROR_TARJETA_CERRADA) {
                return false;
            } else {
                return iatxResponse.getErrorCode() == 0;
            }
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetas.dao.DatosTarjetaDAO#
     * obtenerPagoProgramado(ar.com.santanderrio.obp.servicios.clientes.entities
     * .Cliente, ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta,
     * java.util.Date)
     */
    @Override
    public DatosTarjeta obtenerPagoProgramado(Cliente cliente, Cuenta cuentaTarjeta, Date fecha) throws DAOException {

        for (DatosTarjeta dato : this.obtenerPagosProgramados(cliente, cuentaTarjeta)) {
            if (DateTimeComparator.getDateOnlyInstance().compare(fecha, dato.getFechaPagoProgramado()) == 0) {
                return dato;
            }
        }

        return null;
    }

}
