/*
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.bo.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.CompraVentaCasuisticaRespuestaBO;
import ar.com.santanderrio.obp.servicios.compraventa.bo.CompraVentaDolaresBO;
import ar.com.santanderrio.obp.servicios.compraventa.bo.CompraVentaDolaresUtil;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCompraVenta;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;

// TODO: Auto-generated Javadoc
/**
 * The Class CompraVentaDolaresBOImpl.
 *
 * @author sabrina.cis
 */
@Component
public class CompraVentaDolaresBOImpl implements CompraVentaDolaresBO {

    /** The Constant SERVICIO_DEVOLVIO_LOG_LABEL. */
    public static final String SERVICIO_DEVOLVIO_LOG_LABEL = "ERROR al obtener los datos del servicio";

    /** The Constant ERROR_CON_DATOS_SIMULACION. */
    public static final String ERROR_DOCUMENTO = "ERROR al obtener el documento";

    /** The Constant LEGALES_1. */
    public static final String LEGALES_1 = "1005";

    /** The Constant LEGALES_2. */
    public static final String LEGALES_2 = "1003";

    /** The Constant COD_ERROR_SUCURSAL_FUERA_HORARIO. */
    static final int COD_ERROR_SUCURSAL_FUERA_HORARIO = 10000077;

    /** The Constant COD_ERROR_CAMBIO_COTIZACION. */
    static final int COD_ERROR_CAMBIO_COTIZACION = 10000006;

    /** The Constant COD_ERROR_ACTUALIZACION_DATOS_CUENTA. */
    static final int COD_ERROR_ACTUALIZACION_DATOS_CUENTA = 10000004;

    /** The Constant COD_ERROR_EXCEDE_LIMITE_2. */
    static final int COD_ERROR_EXCEDE_LIMITE_2 = 10000002;
    
    /** The Constant COD_ERROR_EXCEDE_MAXIMO. */
    static final int COD_ERROR_EXCEDE_MAXIMO = 10000000;

    /** The Constant COD_ERROR_CUENTA_INHABILITADA_1. */
    static final int COD_ERROR_CUENTA_INHABILITADA_1 = 10002001;

    /** The Constant COD_ERROR_CUENTA_INHABILITADA_2. */
    static final int COD_ERROR_CUENTA_INHABILITADA_2 = 10000008;

    /** The Constant COD_ERROR_CUENTA_INHABILITADA_3. */
    static final int COD_ERROR_CUENTA_INHABILITADA_3 = 10002065;

    /** The Constant COD_ERROR_SALDO_INSUFICIENTE_1. */
    static final int COD_ERROR_SALDO_INSUFICIENTE_1 = 10002122;

    /** The Constant COD_ERROR_SALDO_INSUFICIENTE_2. */
    static final int COD_ERROR_SALDO_INSUFICIENTE_2 = 10000515;

    /** The Constant COD_ERROR_CUENTA_180_SIN_OPERAR. */
    static final int COD_ERROR_CUENTA_180_SIN_OPERAR = 10000117;
    
    /** The Constant COD_DOCUMENTACION_RESPALDATORIA_INSUFICIENTE. */
    static final int COD_DOCUMENTACION_RESPALDATORIA_INSUFICIENTE = 10000071;

    static final int COD_ERROR_OPERA_USD_SUB_ANSES = 10000771;
    static final int COD_ERROR_OPERA_USD_EXCEDE_LIMITE_ATESORAMIENTO = 10000772;
    static final int COD_ERROR_OPERA_USD_EXCEDE_LIMITE_TRJ = 10000773;
    static final int COD_ERROR_OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO = 10000774;
    static final int COD_ERROR_OPERA_USD_YA_REGISTRA_OPERACION_MENSUAL = 10000775;
    static final int COD_ERROR_OPERA_USD_VENDE_BONOS_USD = 10000776;
    /** The Constant COD_ERROR_CUENTA_180_SIN_OPERAR. */
    static final int COD_ERROR_BOTON_PANICO = 10000777;
    static final int COD_ONLINE_BCRA = 10000778;
    static final int COD_ERROR_OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO_2 = 10000779;
    static final int COD_ERROR_OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO_3 = 10000780;
    
    /** The Constant COD_ERROR_OPERACION_NO_PERMITIDA. */
    static final int COD_ERROR_OPERACION_NO_PERMITIDA = 10000123;
    
    static final int COD_ERROR_NORMATIVO_7105 = 10009077;
    
    static final int COD_ERROR_ERROR_COMPRA_USD_NO_DOCUMENTA_INGRESOS = 10000161 ;

	static final int COD_ERROR_INHABILITADO_BCRA = 10000005;
	
	static final int ERROR_OPERA_USD_FUNCIONARIO_PUBLICO = 10000770;

	static final int ERROR_OPERA_USD_CERTIFICACION_POSITIVA = 10009078;
	
	static final int ERROR_INHABILITADO_BCRA_AMEC_21 = 10000021;
	
	static final int ERROR_INHABILITADO_BCRA_AMEC_43 = 10000043;
	
	static final int ERROR_INHABILITADO_BCRA_AMEC_42 = 10000042;
	
	static final int COD_CUENTA_ALFA_NO_VERIFICADA_NUNCA_OPERO_USD = 10009079;
	
	static final int COD_CUENTA_ALFA_NO_VERIFICADA = 10009080;
    
    /** The nupDAO dao. */
    @Autowired
    private NUPDAO nupDAO;

    /** The detalle CompraVentaDolaresUtil. */
    @Autowired
    private CompraVentaDolaresUtil compraVentaDolaresUtil;

    /** The legal bo. */
    @Autowired
    private LegalBO legalBO;

    /** The casuistica BO. */
    @Autowired
    protected CompraVentaCasuisticaRespuestaBO casuistica;

    /** The cuenta BO. */
    @Autowired
    private CuentaBO cuentaBO;

    /** The SesionCompraVenta *. */
    @Autowired
    private SesionCompraVenta sesionCompraVenta;
    
    @Autowired
    private MensajeBO mensajeBO;

    /**
     * Gets the compra venta dolares util.
     *
     * @return the compra venta dolares util
     */
    public CompraVentaDolaresUtil getCompraVentaDolaresUtil() {
        return compraVentaDolaresUtil;
    }

    /**
     * Gets the nup DAO.
     *
     * @return the nupDAO
     */
    public NUPDAO getNupDAO() {
        return nupDAO;
    }

    /**
     * Gets the legal BO.
     *
     * @return the legalBO
     */
    public LegalBO getLegalBO() {
        return legalBO;
    }

    /**
     * Gets the cuenta BO.
     *
     * @return the cuentaBO
     */
    public CuentaBO getCuentaBO() {
        return cuentaBO;
    }

    /**
     * Leer el mensaje de la respuesta recibida.
     * 
     * @param codigoLegal
     *            the codigo legal
     * @return the string
     */
    public String obtenerLegal(String codigoLegal) {
        Respuesta<String> respuestaLegal = legalBO.buscarLegal(codigoLegal);
        if (EstadoRespuesta.OK.equals(respuestaLegal.getEstadoRespuesta())) {
            return respuestaLegal.getRespuesta();
        } else {
            return respuestaLegal.getItemsMensajeRespuesta().get(0).getMensaje();
        }
    }

    /**
     * Obtiene la descripcion del tipo cuenta.
     *
     * @param cuenta
     *            the cuenta
     * @param isDolar
     *            the is dolar
     * @return the string
     */
    public String obtenerDescripcionTipoCuenta(Cuenta cuenta, boolean isDolar) {
        if (cuenta.getTipoCuentaEnum().equals(TipoCuenta.CUENTA_UNICA_PESOS)
                || cuenta.getTipoCuentaEnum().equals(TipoCuenta.CUENTA_UNICA_DOLARES)) {
            if (isDolar) {
                return cuenta.getTipoCuentaEnum().getDescripcion() + " en u$s";
            } else {
                return cuenta.getTipoCuentaEnum().getDescripcionConMoneda();
            }
        } else {
            return cuenta.getTipoCuentaEnum().getDescripcionConMoneda();
        }
    }

    /**
     * Calcula el tipo de error en base a la respuesta de Iatx segun el codigo
     * de error retornado por el servicio de Iatx SIMCPVTACN110 Simulación de
     * venta (Banco Vende / Cliente Compra) de dólares.
     *
     * @param codError
     *            the cod error
     * @param esCompra
     *            the es compra
     * @return the error compra venta enum
     */
    public ErrorCompraVentaEnum obtenerDatosDelError(Integer codError, boolean esCompra) {

        ErrorCompraVentaEnum error;
        switch (codError) {
        case COD_ERROR_SUCURSAL_FUERA_HORARIO:
            error = ErrorCompraVentaEnum.OPERACION_FUERA_HORARIO;
            break;
        case COD_ERROR_CAMBIO_COTIZACION:
            error = ErrorCompraVentaEnum.CAMBIO_LA_COTIZACION;
            break;
        case COD_ERROR_ACTUALIZACION_DATOS_CUENTA:
            error = ErrorCompraVentaEnum.ACTUALIZACION_DATOS_CUENTA;
            break;
        case COD_ERROR_EXCEDE_MAXIMO:
            error = ErrorCompraVentaEnum.MONTO_EXCEDE_MAXIMO_PERMITIDO;
            break;
        case COD_ERROR_EXCEDE_LIMITE_2:
            error = ErrorCompraVentaEnum.MONTO_EXCEDE_EL_PERMITIDO_2;
            break;
        case COD_ERROR_CUENTA_INHABILITADA_1:
            error = ErrorCompraVentaEnum.CUENTA_INHABILITADA_1;
            break;
        case COD_ERROR_CUENTA_INHABILITADA_2:
            error = ErrorCompraVentaEnum.CUENTA_INHABILITADA_2;
            break;
        case COD_ERROR_CUENTA_INHABILITADA_3:
            error = ErrorCompraVentaEnum.CUENTAINHABILITADA_3;
            break;
        case COD_ERROR_OPERACION_NO_PERMITIDA:
            error = ErrorCompraVentaEnum.CODIGO_ONLINE_BCRA_OPERACION_NO_PERMITIDA;
            break;
        case COD_ERROR_NORMATIVO_7105:
        	error = ErrorCompraVentaEnum.OPERACION_NO_PERMITIDA_COD_7105;
        	break;
        case COD_ERROR_ERROR_COMPRA_USD_NO_DOCUMENTA_INGRESOS:
        	error = ErrorCompraVentaEnum.ERROR_COMPRA_USD_NO_DOCUMENTA_INGRESOS;
        	break;
        case ERROR_INHABILITADO_BCRA_AMEC_43:
            error = ErrorCompraVentaEnum.ERROR_INHABILITADO_BCRA_AMEC_43;
            break;     
        case COD_CUENTA_ALFA_NO_VERIFICADA_NUNCA_OPERO_USD:
        	error = ErrorCompraVentaEnum.COD_CUENTA_ALFA_NO_VERIFICADA_NUNCA_OPERO_USD;
        	break;
        case COD_CUENTA_ALFA_NO_VERIFICADA:
        	error = ErrorCompraVentaEnum.COD_CUENTA_ALFA_NO_VERIFICADA;
        	break;
        case ERROR_INHABILITADO_BCRA_AMEC_42:
            error = ErrorCompraVentaEnum.ERROR_INHABILITADO_BCRA_AMEC_42;
            break;   
        default:
            error = obtenerDatosDelError2(codError, esCompra);
            break;
        }
        return error;
    }

    /**
     * Obtener datos del error 2.
     *
     * @param codError
     *            the cod error
     * @param esCompra
     *            the es compra
     * @return the error compra venta enum
     */
    private ErrorCompraVentaEnum obtenerDatosDelError2(Integer codError, boolean esCompra) {
        ErrorCompraVentaEnum error;
        switch (codError) {
        case COD_ERROR_SALDO_INSUFICIENTE_1:
            error = obtenerErrorDeCase(esCompra, true);
            break;
        case COD_ERROR_SALDO_INSUFICIENTE_2:
            error = obtenerErrorDeCase(esCompra, false);
            break;
        case COD_ERROR_CUENTA_180_SIN_OPERAR:
            error = ErrorCompraVentaEnum.CUENTA_180_DIAS_SIN_OPERAR;
            break;
        case COD_DOCUMENTACION_RESPALDATORIA_INSUFICIENTE:
        	error = obtenerErrorDocRespaldatoriaInsuficiente(esCompra);
        	break;
        
        case COD_ERROR_OPERA_USD_SUB_ANSES:
        	error = ErrorCompraVentaEnum.ERROR_OPERA_USD_SUB_ANSES;
        	break;
        case COD_ERROR_OPERA_USD_EXCEDE_LIMITE_ATESORAMIENTO:
        	error = ErrorCompraVentaEnum.ERROR_OPERA_USD_EXCEDE_LIMITE_ATESORAMIENTO;
            break;
        case COD_ERROR_OPERA_USD_EXCEDE_LIMITE_TRJ:
        	error = ErrorCompraVentaEnum.ERROR_OPERA_USD_EXCEDE_LIMITE_TRJ;
            break;
        case COD_ERROR_OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO:
        	error = ErrorCompraVentaEnum.ERROR_OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO;
            break;
        case COD_ERROR_OPERA_USD_YA_REGISTRA_OPERACION_MENSUAL:
        	error = ErrorCompraVentaEnum.ERROR_OPERA_USD_YA_REGISTRA_OPERACION_MENSUAL;
            break;
        case COD_ERROR_OPERA_USD_VENDE_BONOS_USD:
        	error = ErrorCompraVentaEnum.ERROR_OPERA_USD_VENDE_BONOS_USD;
        	break;
        case COD_ERROR_BOTON_PANICO:
        	error = ErrorCompraVentaEnum.CODIGO_BOTON_PANICO;
        	break;
        case COD_ONLINE_BCRA:
        	error = ErrorCompraVentaEnum.CODIGO_ONLINE_BCRA;
        	break;
        case COD_ERROR_OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO_2:
        	error = ErrorCompraVentaEnum.ERROR_OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO_2;
        	break;
        case COD_ERROR_OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO_3:
        	error = ErrorCompraVentaEnum.ERROR_OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO_3;
        	break;
        case COD_ERROR_INHABILITADO_BCRA:
        	error = ErrorCompraVentaEnum.ERROR_OPERA_USD_ERROR_INHABILITADO_BCRA;
        	break;
        case ERROR_OPERA_USD_FUNCIONARIO_PUBLICO:
            error = ErrorCompraVentaEnum.ERROR_OPERA_USD_FUNCIONARIO_PUBLICO;
            break;
        case ERROR_OPERA_USD_CERTIFICACION_POSITIVA:
            error = ErrorCompraVentaEnum.ERROR_OPERA_USD_NO_PERMITIDA_CERTIFICACION_POSITIVA;
            break;
        case ERROR_INHABILITADO_BCRA_AMEC_21:
            error = ErrorCompraVentaEnum.ERROR_OPERA_USB_INHABILITADO_BCRA_AMEC_21;
            break;             
        default:
            error = ErrorCompraVentaEnum.NO_SE_PUEDE_REALIZAR_LA_OPERACION;
            break;
        }
        return error;
    }
//    ERROR_OPERA_USD_FUNCIONARIO_PUBLICO("ERROR_OPERA_USD_FUNCIONARIO_PUBLICO"),
//    ERROR_OPERA_USD_CERTIFICACION_POSITIVA("ERROR_OPERA_USD_CERTIFICACION_POSITIVA");
    /**
     * Obtener error de case.
     *
     * @param esCompra
     *            the es compra
     * @param esCase1
     *            the es case 1
     * @return the error compra venta enum
     */
    private ErrorCompraVentaEnum obtenerErrorDeCase(boolean esCompra, boolean esCase1) {
        if (tamanoCuentaOrigenMayor(esCompra)) {
            return ErrorCompraVentaEnum.SALDO_INSUFICIENTE_3;
        }
        if (esCase1) {
            return ErrorCompraVentaEnum.SALDO_INSUFICIENTE_1;
        } else {
            return ErrorCompraVentaEnum.SALDO_INSUFICIENTE_2;
        }
    }

    /**
     * Tamaño cuenta origen mayor.
     *
     * @param esCompra
     *            the es compra
     * @return true, if successful
     */
    private boolean tamanoCuentaOrigenMayor(boolean esCompra) {
        if (esCompra) {
            return sesionCompraVenta.getCuentasPesos().size() > 1;
        }
        return sesionCompraVenta.getCuentasDolares().size() > 1;
    }

    /**
     * Obtener documento valido.
     *
     * @param cliente
     *            the cliente
     * @return the detalle documento DTO
     * @throws DAOException
     *             the DAO exception
     */
    @Override
    public DetalleDocumentoDTO obtenerDocumentoValido(Cliente cliente) throws DAOException {
        DetalleDocumentoDTO dto;
        NupDTO nupDTO = getNupDAO().obtenerNUP(cliente);
        dto = obtenerDetalleDocumentoValido(nupDTO.getDetalleDocumento());
        if (dto == null) {
            dto = new DetalleDocumentoDTO(ErrorCompraVentaEnum.NO_SE_ENCONTRO_DOC_VALIDO);
        }
        return dto;
    }

    /**
     * Retorna un tipo de documento valido.
     *
     * @param detalleDocumento
     *            the detalle documento
     * @return the detalle documento DTO
     */
    private DetalleDocumentoDTO obtenerDetalleDocumentoValido(Map<String, DetalleDocumentoDTO> detalleDocumento) {
        for (DetalleDocumentoDTO d1 : detalleDocumento.values()) {
            if (compraVentaDolaresUtil.obtenerTipoDocumento(d1.getTipoDocumento()) != null) {
                return d1;
            }
        }
        return null;
    }

    /**
     * Crear respuesta error.
     *
     * @param <T>
     *            the generic type
     * @param error
     *            the error
     * @param tipoOperacion
     *            the tipo operacion
     * @param cuenta
     *            the cuenta
     * @return the respuesta
     */
    @Override
    public <T> Respuesta<T> crearRespuestaError(ErrorCompraVentaEnum error, String tipoOperacion, Cuenta cuenta, String mensajeError) {
        Respuesta<T> respuesta = casuistica.crearRespuestaError(error);
        if (error.getTag().equals(CompraVentaStringUtil.SUCURSAL_ORIGEN_FUERA_HORARIO)
                || error.getTag().equals(CompraVentaStringUtil.CUENTA_SIN_OPERAR)) {
            if (!error.getTag().equals(CompraVentaStringUtil.CUENTA_SIN_OPERAR)
                    && tieneCuentaEnDistintaSucursal(cuentasDependiendoTipoOperacion(tipoOperacion), cuenta)) {
                respuesta = casuistica.crearRespuestaError(error.getTag(), error.getTipoError(),
                        CodigoMensajeConstantes.ERROR_OPERACION_NO_DISPONIBLE_HORARIO_SUCURSAL_VARIAS_SUCURSALES);
                String mensaje = StringUtils.replace(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), "{0}",
                        CompraVentaDolaresUtil.obtenerDetalleOperacion(tipoOperacion));
                respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensaje);
            } else {
                String mensajeParseado = StringUtils.replace(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(),
                        "{0}", CompraVentaDolaresUtil.obtenerDetalleOperacion(tipoOperacion));
                respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeParseado);
                if(error.getTag().equals(CompraVentaStringUtil.CUENTA_SIN_OPERAR)){
                    return respuesta;
                }
            }
            respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        } else if (error.getTag().equals(CompraVentaStringUtil.SALDO_INSUFICIENTE)
                || error.getTag().equals(CompraVentaStringUtil.SALDO_INSUFICIENTE2)){
        	String mensaje = StringUtils.replace(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), "{0}",
        			ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta.getNroCuentaProducto(),cuenta.getNroSucursal()));
            respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensaje);
        	respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        	
        } else if (error.getTag().equals(CompraVentaStringUtil.CAMBIO_COTIZACION_DOLAR)) {
            respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);

        } else if(error.getTag().equals(CompraVentaStringUtil.DOCUMENTACION_INSUFICIENTE )){
        	if ("C".equals(tipoOperacion)) {
        		respuesta = casuistica.crearRespuestaError(error.getTag(), error.getTipoError(),
                        CodigoMensajeConstantes.CODIGO_DOCUMENTACION_INSUFICIENTE_COMPRA);
			}else{
				respuesta = casuistica.crearRespuestaError(error.getTag(), error.getTipoError(),
                        CodigoMensajeConstantes.CODIGO_DOCUMENTACION_INSUFICIENTE_VENTA);
			}
        } else if(error.getTag().equals(CompraVentaStringUtil.MONTO_EXCEDE_TRANSACCION_PERMITIDA )){
        	if ("C".equals(tipoOperacion)) {
        		respuesta = casuistica.crearRespuestaError(error.getTag(), error.getTipoError(),
                        CodigoMensajeConstantes.CODIGO_ERROR_MONTO_EXCEDE_TRANSACCION_PERMITIDA);
			}
        } else if(error.getTag().equals(CompraVentaStringUtil.BOTON_PANICO)) {
        	respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeError);
        	respuesta.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_BOTON_PANICO.getDescripcion());
        } else if(error.getTag().equals(CompraVentaStringUtil.CODIGO_ONLINE_BCRA)) {
        	respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeError);
        	respuesta.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.CODIGO_ONLINE_BCRA.getDescripcion());
        } else if (error.getTag().equals(CompraVentaStringUtil.OPERACION_INHABILITADA_NORMATIVO_7105)) {
        	respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.COMPRA_DOLARES_INVITACION_CARGA_DOCUMENTACION).getMensaje());
        	respuesta.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.OPERACION_NO_PERMITIDA_COD_7105.getDescripcion());
        } else if (error.getTag().equals(CompraVentaStringUtil.COMPRA_USD_NO_DOCUMENTA_INGRESOS)) {
        	respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.COMPRA_DOLARES_INVITACION_CARGA_DOCUMENTACION).getMensaje());
        	respuesta.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_COMPRA_USD_NO_DOCUMENTA_INGRESOS.getDescripcion());
        } else if (error.getTag().equals(CompraVentaStringUtil.OPERA_USD_EXCEDE_LIMITE_ATESORAMIENTO)) {
        	respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_OPERA_USD_EXCEDE_LIM_CONSUMIDO_CLIENTE).getMensaje());
        	respuesta.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_OPERA_USD_EXCEDE_LIMITE_ATESORAMIENTO.getDescripcion());
        } else if (error.getTag().equals(CompraVentaStringUtil.OPERA_USD_EXCEDE_LIMITE_TRJ)) {
        	respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_OPERA_USD_EXCEDE_LIMITE_CLIENTE_TRJ).getMensaje());
        	respuesta.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_OPERA_USD_EXCEDE_LIMITE_TRJ.getDescripcion());
        } else if (error.getTag().equals(CompraVentaStringUtil.OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO_2)) {
        	respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_OPERA_USD_EXCEDE_LIM_CONSUMIDO_CLIENTE_TRJ).getMensaje());
        	respuesta.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO_2.getDescripcion());
        } else if (error.getTag().equals(CompraVentaStringUtil.OPERA_USD_EXCEDE_EXCEDE_CONSUMIDO_CLIENTE_AMBOS)) {
        	respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_OPERA_USD_EXCEDE_CONSUMIDO_CLIENTE_AMBOS).getMensaje());
        	respuesta.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_OPERA_USD_EXCEDE_TRJ_Y_ATESORAMIENTO_3.getDescripcion());
        } else if (error.getTag().equals(CompraVentaStringUtil.OPERA_USD_SUB_ANSES)) {
        	respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_OPERA_USD_SUBSIDIO_ANSES).getMensaje());
        	respuesta.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_OPERA_USD_SUB_ANSES.getDescripcion());
        } else if (error.getTag().equals(CompraVentaStringUtil.OPERA_USD_VENDE_BONOS_USD)) {
        	respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_OPERA_USD_VERIFICAR_COMP_A).getMensaje());
        	respuesta.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_OPERA_USD_VENDE_BONOS_USD.getDescripcion());
        } else if (error.getTag().equals(CompraVentaStringUtil.OPERA_USD_YA_REGISTRA_OPERACION_MENSUAL)) {
        	respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_OPERA_USD_OPER_OTRO_TITULAR).getMensaje());
        	respuesta.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_OPERA_USD_YA_REGISTRA_OPERACION_MENSUAL.getDescripcion());
        } else if (error.getTag().equals(CompraVentaStringUtil.ERROR_INHABILITADO_BCRA)) {
        	respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_OPERA_USD_ERROR_INHABILITADO_BCRA).getMensaje());
        	respuesta.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_INHABILITADO_BCRA.getDescripcion());
        } else if (error.getTag().equals(CompraVentaStringUtil.ERROR_INHABILITADO_BCRA_AMEC_21)) {
        	respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_OPERA_USB_INHABILITADO_BCRA_AMEC_21).getMensaje());
        	respuesta.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_INHABILITADO_BCRA_AMEC_21.getDescripcion());
        }  else if (error.getTag().equals(CompraVentaStringUtil.ERROR_INHABILITADO_BCRA_AMEC_43)) {
        	respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_OPERA_USB_INHABILITADO_BCRA_AMEC_43).getMensaje());
        	respuesta.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_INHABILITADO_BCRA_AMEC_43.getDescripcion());
        } else if (error.getTag().equals(CompraVentaStringUtil.ERROR_INHABILITADO_BCRA_AMEC_42)) {
        	respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_OPERA_USB_INHABILITADO_BCRA_AMEC_42).getMensaje());
        	respuesta.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_INHABILITADO_BCRA_AMEC_42.getDescripcion());
        } 
        return respuesta;
    }

    /**
     * Alias getter.
     *
     * @param cuenta
     *            the cuenta
     * @return the string
     */
    public String aliasGetter(Cuenta cuenta) {
        if ("".equals(cuenta.getAlias())) {
            return null;
        } else {
            return cuenta.getAlias();
        }
    }

    /**
     * Cuentas dependiendo tipo operacion.
     *
     * @param tipoOperacion
     *            the tipo operacion
     * @return the list
     */
    private List<CuentasAdhesionDebitoView> cuentasDependiendoTipoOperacion(String tipoOperacion) {
        if ("C".equals(tipoOperacion)) {
            return sesionCompraVenta.getCuentasPesos();
        } else {
            return sesionCompraVenta.getCuentasDolares();
        }
    }

    /**
     * Tiene cuenta en distinta sucursal.
     *
     * @param listaCuentasView
     *            the lista cuentas view
     * @param cuentaOrigen
     *            the cuenta origen
     * @return the boolean
     */
    private Boolean tieneCuentaEnDistintaSucursal(List<CuentasAdhesionDebitoView> listaCuentasView,
            Cuenta cuentaOrigen) {
        int nroSucursalSize = cuentaOrigen.getNroSucursal().length();
        for (CuentasAdhesionDebitoView cuenta : listaCuentasView) {
            if (cuenta.getShowSaldoPesos() && !coincideSucursal(cuenta,
                    cuentaOrigen.getNroSucursal().substring(nroSucursalSize - 3, nroSucursalSize))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Coincide sucursal.
     *
     * @param cuenta
     *            the cuenta
     * @param sucursalCuentaOrigen
     *            the sucursal cuenta origen
     * @return the boolean
     */
    private Boolean coincideSucursal(CuentasAdhesionDebitoView cuenta, String sucursalCuentaOrigen) {
        IdentificacionCuenta idCuenta = new IdentificacionCuenta(cuenta.getNumero());
        if (idCuenta.getNroSucursal().equals(sucursalCuentaOrigen)) {
            return true;
        }
        return false;
    }

    /**
     * Obtener cuenta por id.
     *
     * @param identificacionCuenta
     *            the identificacion cuenta
     * @param cliente
     *            the cliente
     * @return the cuenta
     */
    @Override
    public Cuenta obtenerCuentaPorId(IdentificacionCuenta identificacionCuenta, Cliente cliente) {
        return (Cuenta) cuentaBO.buscarCuentaPorId(cliente, identificacionCuenta);
    }
    
    /**
	 * Obtener error doc respaldatoria insuficiente.
	 *
	 * @param esCompra
	 *            the es compra
	 * @return the error compra venta enum
	 */
    private ErrorCompraVentaEnum obtenerErrorDocRespaldatoriaInsuficiente(boolean esCompra) {
        if (esCompra) {
            // Modificacion 20191021
            return ErrorCompraVentaEnum.DOCUMENTACION_INSUFICIENTE_COMPRA;
            //return ErrorCompraVentaEnum.MONTO_EXCEDE_MAXIMO_PERMITIDO;
        }
      return ErrorCompraVentaEnum.DOCUMENTACION_INSUFICIENTE_VENTA;
    }
}