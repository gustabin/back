/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagos.dao.DeudaPagoAutomaticoDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.DebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.entities.DeudaPagoAutomaticoEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaAdheridaDebitoAutomatico;

/**
 * The Class DeudaPagoAutomaticoDAOImpl.
 */
@Component
/**
 * Conector con el servicio CNSPAUDEUD
 * 
 * @author b039920
 *
 */
public class DeudaPagoAutomaticoDAOImpl implements DeudaPagoAutomaticoDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DeudaPagoAutomaticoDAOImpl.class);

    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;

    /** The servicio cnspaudeud. */
    @Value("${SERVICIO.PREFIJO.CNSPAUDEUD}")
    private String servicioCnspaudeud;

    /** The version cnspaudeud. */
    @Value("${SERVICIO.VERSION.CNSPAUDEUD}")
    private String versionCnspaudeud;

    /** The servicio cns deb auto. */
    private String servicioCnsDebAuto = "CNSDEBAUTO";

    /** The version cns deb auto. */
    private String versionCnsDebAuto = "100";

    /** The Constant SUCURSAL_OFFSET. */
    private static final int SUCURSAL_OFFSET = 0;

    /** The Constant SUCURSAL_LENGTH. */
    private static final int SUCURSAL_LENGTH = 3;

    /** The Constant CUENTA_OFFSET. */
    private static final int CUENTA_OFFSET = 4;

    /** The Constant CUENTA_LENGTH. */
    private static final int CUENTA_LENGTH = 8;

    /** The Constant CON_MOVIMIETOS_CODIGO_RETORNO. */
    private static final int CON_MOVIMIETOS_CODIGO_RETORNO = 0;

    /** The Constant SIN_MOVIMIETOS_CODIGO_RETORNO. */
    private static final int SIN_MOVIMIETOS_CODIGO_RETORNO = 10000065;

    /** The Constant CODIGO_DE_ERROR_DESCONOCIDO_MSG. */
    private static final String CODIGO_DE_ERROR_DESCONOCIDO_MSG = "Codigo de error iatx desconocido";

    /** The Constant CODIGO_DE_ERROR_INICIO_STRING. */
    private static final String CODIGO_DE_ERROR_INICIO_STRING = "[codigo_error=";

    /** The Constant CODIGO_DE_ERROR_FIN_STRING. */
    private static final String CODIGO_DE_ERROR_FIN_STRING = "]";

    /** The Constant OK_CODIGO_RETORNO. */
    private static final int OK_CODIGO_RETORNO = 0;

    /** The Constant CODIGO_NO_HAY_DEBITOS. */
    private static final int CODIGO_NO_HAY_DEBITOS = 10001032;

    /** The Constant SIN_FECHA_1. */
    private static final String SIN_FECHA_1 = "********";

    /** The Constant SIN_FECHA_2. */
    private static final String SIN_FECHA_2 = "00000000";

    /** The Constant VACIO_STRING. */
    private static final String VACIO_STRING = "";

    /** The Constant ERROR_AL_FORMATEAR_FECHA. */
    private static final String ERROR_AL_FORMATEAR_FECHA = "Error al formatear fecha";

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.dao.DeudaPagoAutomaticoDAO#
     * consultarDeudaPagoAutomatico(ar.com.santanderrio.obp.servicios.clientes.
     * entities.Cliente)
     */
    @Override
    public List<DeudaPagoAutomaticoEntity> consultarDeudaPagoAutomatico(Cliente cliente) throws DAOException {

        List<DeudaPagoAutomaticoEntity> movimientos = null;
        IatxRequestData iatxRequestData = new IatxRequestData(cliente);
        IatxRequest request = new IatxRequest(servicioCnspaudeud, versionCnspaudeud);

        request.setData(iatxRequestData);
        try {
            IatxResponse iatxResponse = iatxComm.exec(request);
            int errorCode = iatxResponse.getErrorCode();
            switch (errorCode) {
            case CON_MOVIMIETOS_CODIGO_RETORNO:
                movimientos = parsearResponse(iatxResponse);
                break;
            case SIN_MOVIMIETOS_CODIGO_RETORNO:
                return new ArrayList<DeudaPagoAutomaticoEntity>();
            default:
                throw new DAOException(generarCodigoErrorDesconocidoMensaje(errorCode));
            }
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return movimientos;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.dao.DeudaPagoAutomaticoDAO#
     * obtenerDeudasConDebitoAutomatico(ar.com.santanderrio.obp.servicios.
     * clientes.entities.Cliente,
     * ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta)
     */
    @Override
    public List<DebitoAutomatico> obtenerDeudasConDebitoAutomatico(Cliente cliente, Cuenta cuenta) throws DAOException {
        List<DebitoAutomatico> listaPrestamoDebitoAutomatico;
        if (cuenta.esCuentaUnica()) {
            listaPrestamoDebitoAutomatico = obtenerDeudasConDebitoAutomatico(cliente, cuenta, "00");
            listaPrestamoDebitoAutomatico.addAll(obtenerDeudasConDebitoAutomatico(cliente, cuenta, "01"));
        } else {
            listaPrestamoDebitoAutomatico = obtenerDeudasConDebitoAutomatico(cliente, cuenta, null);
        }
        return listaPrestamoDebitoAutomatico;
    }

    /**
	 * Obtener deudas con debito automatico.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @param tipoSubcuentaCU
	 *            the tipo subcuenta CU
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
    private List<DebitoAutomatico> obtenerDeudasConDebitoAutomatico(Cliente cliente, Cuenta cuenta,
            String tipoSubcuentaCU) throws DAOException {
        List<DebitoAutomatico> listaPrestamoDebitoAutomatico = new ArrayList<DebitoAutomatico>();
        try {
            IatxRequest iatxRequest = new IatxRequest(servicioCnsDebAuto, versionCnsDebAuto);
            IatxRequestData iatxRequestData = generarIatxRequestDataDebitoAutomatico(cliente, cuenta, tipoSubcuentaCU);
            iatxRequest.setData(iatxRequestData);
            IatxResponse iatxResponse = iatxComm.exec(iatxRequest);
            int errorCode = iatxResponse.getErrorCode();
            if (OK_CODIGO_RETORNO == errorCode) {
                listaPrestamoDebitoAutomatico = parsearResponseDebitoAutomatico(iatxResponse, tipoSubcuentaCU);
            } else if (CODIGO_NO_HAY_DEBITOS != errorCode) {
                throw new DAOException(generarCodigoErrorDesconocidoMensaje(errorCode));
            }
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return listaPrestamoDebitoAutomatico;
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
     * Parsear response.
     *
     * @param iatxResponse
     *            the iatx response
     * @return the list
     * @throws IatxException
     *             the iatx exception
     */
    private List<DeudaPagoAutomaticoEntity> parsearResponse(IatxResponse iatxResponse) throws IatxException {

        List<DeudaPagoAutomaticoEntity> listaDeudaPagoAutomatico = new ArrayList<DeudaPagoAutomaticoEntity>();
        if (CON_MOVIMIETOS_CODIGO_RETORNO == iatxResponse.getErrorCode()) {
            String deudas = iatxResponse.getNextData();
            int cantidadDeudas = Integer.parseInt(deudas);
            DeudaPagoAutomaticoEntity deudaPagoAutomatico = null;
            for (int i = 1; i <= cantidadDeudas; i++) {
                try {
                    deudaPagoAutomatico = new DeudaPagoAutomaticoEntity();
                    deudaPagoAutomatico.setEmpresa(iatxResponse.getNextData());
                    deudaPagoAutomatico.setIdClienteEmpresa(iatxResponse.getNextData());
                    deudaPagoAutomatico.setVencimiento(formatearFecha(iatxResponse.getNextData(), "ddMMyyyy"));
                    String divisa = iatxResponse.getNextData().trim();
                    if (VACIO_STRING.equals(divisa)) {
                        deudaPagoAutomatico.setDivisa(DivisaEnum.PESO);
                    } else {
                        deudaPagoAutomatico.setDivisa(DivisaEnum.fromCodigoString(divisa));
                    }

                    BigDecimal importe = new BigDecimal(
                            ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getNextData()));

                    deudaPagoAutomatico.setImporte(importe);

                    deudaPagoAutomatico.setFactura(iatxResponse.getNextData());
                    deudaPagoAutomatico.setFechaStopDebit(formatearFecha(iatxResponse.getNextData(), "yyyyMMdd"));
                    deudaPagoAutomatico.setTipoCuenta(TipoCuenta.fromCodigo(iatxResponse.getNextData()));
                    deudaPagoAutomatico
                            .setIdentificacionCuenta(generarIdentificacionCuenta(iatxResponse.getNextData()));

                    BigDecimal tope = new BigDecimal(
                            ISBANStringUtils.importePtoFijo2Canonico(iatxResponse.getNextData()));

                    deudaPagoAutomatico.setTope(tope);
                } catch (DAOException e) {
                    LOGGER.error("Erro al parsear la respuesta del servicio.", e);
                    throw new IatxException(e);
                }
                listaDeudaPagoAutomatico.add(deudaPagoAutomatico);
            }
        }
        return listaDeudaPagoAutomatico;
    }

    /**
     * Generar identificacion cuenta.
     *
     * @param numeroDeCuenta
     *            the numero de cuenta
     * @return the identificacion cuenta
     */
    private IdentificacionCuenta generarIdentificacionCuenta(String numeroDeCuenta) {
        numeroDeCuenta = numeroDeCuenta.trim();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        if (!numeroDeCuenta.isEmpty()) {
            identificacionCuenta.setNroSucursal(
                    StringUtils.substring(numeroDeCuenta, SUCURSAL_OFFSET, SUCURSAL_OFFSET + SUCURSAL_LENGTH));
            identificacionCuenta.setNroCuentaProducto(
                    StringUtils.substring(numeroDeCuenta, CUENTA_OFFSET, CUENTA_OFFSET + CUENTA_LENGTH));
        }
        return identificacionCuenta;
    }

    /**
     * Formatear fecha.
     *
     * @param vencimientoString
     *            the vencimiento string
     * @param formato
     *            the formato
     * @return the date
     * @throws DAOException
     *             the DAO exception
     */
    private Date formatearFecha(String vencimientoString, String formato) throws DAOException {
        Date fecha = null;
        if (vencimientoString == null || StringUtils.isBlank(vencimientoString) || SIN_FECHA_1.equals(vencimientoString)
                || SIN_FECHA_2.equals(vencimientoString)) {
            fecha = null;
        } else {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat(formato);
                fecha = formatter.parse(vencimientoString);
            } catch (ParseException e) {
                LOGGER.error(ERROR_AL_FORMATEAR_FECHA, e);
                throw new DAOException(e);
            }
        }
        return fecha;
    }

    /**
	 * Parsear response debito automatico.
	 *
	 * @param iatxResponse
	 *            the iatx response
	 * @param tipoSubcuentaCU
	 *            the tipo subcuenta CU
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
    private List<DebitoAutomatico> parsearResponseDebitoAutomatico(IatxResponse iatxResponse, String tipoSubcuentaCU)
            throws DAOException {
        DebitoAutomatico prestamoDebitoAutomatico = new DebitoAutomatico();
        List<DebitoAutomatico> listaPrestamoDebitoAutomatico = new ArrayList<DebitoAutomatico>();
        int cantidadRepeticiones = Integer.parseInt(iatxResponse.getNextData());

        for (int i = 0; i < cantidadRepeticiones; i++) {
            prestamoDebitoAutomatico = new DebitoAutomatico();

            prestamoDebitoAutomatico.setCodigoServicio(iatxResponse.getNextData());
            prestamoDebitoAutomatico.setNombreServicio(iatxResponse.getNextData());
            prestamoDebitoAutomatico.setNumeroPartida(iatxResponse.getNextData());
            prestamoDebitoAutomatico.setEstadoCliente(iatxResponse.getNextData());
            prestamoDebitoAutomatico.setEstadoServicio(iatxResponse.getNextData());
            prestamoDebitoAutomatico.setFechaServicio(formatearFecha(iatxResponse.getNextData(), "ddMMyyyy"));
            prestamoDebitoAutomatico.setTipoFecha(iatxResponse.getNextData());
            prestamoDebitoAutomatico.setTipoStopDebit(iatxResponse.getNextData());
            prestamoDebitoAutomatico.setTipoSubcuentaCU(tipoSubcuentaCU);

            listaPrestamoDebitoAutomatico.add(prestamoDebitoAutomatico);
        }

        return listaPrestamoDebitoAutomatico;
    }

    /**
	 * Generar iatx request data debito automatico.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cuenta
	 *            the cuenta
	 * @param tipoSubcuentaCU
	 *            the tipo subcuenta CU
	 * @return the iatx request data
	 */
    private IatxRequestData generarIatxRequestDataDebitoAutomatico(Cliente cliente, Cuenta cuenta,
            String tipoSubcuentaCU) {

        IatxRequestData requestData = new IatxRequestData(cliente);

        if (cuenta.esCuentaUnica()) {
            requestData.addBodyValue(tipoSubcuentaCU);
        } else {
            requestData.addBodyValue(cuenta.getTipoCuenta());
        }

        String sucursalFormateada = cuenta.getNroSucursal().substring(1);
        requestData.addBodyValue(sucursalFormateada);

        String cuentaFormateada = cuenta.getNroCuentaProducto().substring(9);
        requestData.addBodyValue(cuentaFormateada);

        return requestData;
    }

    /**
     * Devuelve la cuenta aherida al pago de una tarjeta por debito automaticoda
     * a una sola cuenta de pago.
     *
     * @param cliente
     *            the cliente
     * @param numeroTarjeta
     *            the numero tarjeta
     * @return the cuenta adherida debito automatico
     * @throws DAOException
     *             the DAO exception
     */
    @Override
    public CuentaAdheridaDebitoAutomatico obtenerCuentaAdheridaDebitoAutomatico(Cliente cliente, String numeroTarjeta)
            throws DAOException {
        Cuenta cuentaTarjeta = cliente.getTarjeta(numeroTarjeta);

        for (Cuenta cuenta : cliente.getCuentasParaEfectuarPago()) {
            List<DebitoAutomatico> debitosAutomaticos = this.obtenerDeudasConDebitoAutomatico(cliente, cuenta);
            if (CollectionUtils.isEmpty(debitosAutomaticos)) {
                continue;
            }

            for (DebitoAutomatico debitoAutomatico : debitosAutomaticos) {
                if (debitoAutomatico.esProductoAdherido(cuentaTarjeta.getNroCuentaProducto())) {
                    return new CuentaAdheridaDebitoAutomatico(cuenta, debitoAutomatico);
                }
            }
        }
        return null;
    }

}
