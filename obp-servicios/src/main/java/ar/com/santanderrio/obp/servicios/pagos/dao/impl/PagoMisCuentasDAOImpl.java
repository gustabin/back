/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidationEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.pagos.dao.PagoMisCuentasDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoMultipleDTO;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoTarjetaInEntity;

/**
 * The Class PagoMisCuentasDAOImpl.
 */
@Component
public class PagoMisCuentasDAOImpl implements PagoMisCuentasDAO {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PagoMisCuentasDAOImpl.class);

    /**
     * The iatx comm.
     */
    @Autowired
    private IatxComm iatxComm;

    /**
     * The mensaje dao.
     */
    @Autowired
    private MensajeDAO mensajeDao;

    // /** nombre del servicio de pago mis cuentas. */
    /** The servicio pagmasscio. */
    private String servicioPagmasscio = "PAGMASSCIO";

    // /** version del servicio de pago mis cuentas. */
    /** The version pagmasscio. */
    private String versionPagmasscio = "100";

    /** The servicio pagmasscre. */
    private String servicioPagmasscre = "PAGMASSCRE";

    /** The version pagmasscre. */
    private String versionPagmasscre = "100";

    /** The error codigo desconocido. */
    private static final String ERROR_CODIGO_DESCONOCIDO = "Error del servicio {}, codigo desconocido: {}.";

    /** The error general. */
    private static final String ERROR_GENERAL = "Error general";
    
    /** The Constant ERROR_PRISMA_TIME_OUT. */
    private static final String ERROR_PRISMA_TIME_OUT = "Error prisma reintente en 5 minutos";
    
    /** The error parcial. */
    private static final String ERROR_PARCIAL = "Error parcial";

    /** The Constant CODIGO_ERROR_TIMEOUT. */
    private static final int CODIGO_ERROR_TIMEOUT = 20000000;
    
    /** The Constant CODIGO_ERROR_TIMEOUT_PRISMA. */
    private static final int CODIGO_ERROR_TIMEOUT_PRISMA = 10099975;
    
    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.dao.PagoMisCuentasDAO#
     * ejecutarPagoMultiple(java.util.List,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public PagoMultipleDTO ejecutarPagoMultiple(List<PagoInEntity> pagos, Cliente cliente) throws DAOException {
        IatxRequest request = new IatxRequest(servicioPagmasscio, versionPagmasscio);
        Integer codigoError = 0;
        PagoMultipleDTO pagoMultipleDTO = new PagoMultipleDTO();
        pagoMultipleDTO.setErrorUnico(Boolean.FALSE);
        try {
            request.setData(generateRequestData(pagos, cliente));
            IatxResponse iatxResponse = iatxComm.exec(request);
            if (iatxResponse.getErrorCode() == 0) {
                pagoMultipleDTO.setPagos(obtenerPagos(pagos, iatxResponse));
                pagoMultipleDTO.setTodosOK(Boolean.TRUE);
            } else {
                codigoError = iatxResponse.getErrorCode();
                String sCodigoError = codigoError.toString();
                if (iatxResponse.getErrorCode() == 10000000 || sCodigoError.startsWith("1")) {
                    LOGGER.debug(ERROR_GENERAL);
                    throw new IatxException();
                } else if (iatxResponse.getErrorCode() == CODIGO_ERROR_TIMEOUT || sCodigoError.startsWith("2")) {
                    LOGGER.debug(ERROR_GENERAL);
                    pagoMultipleDTO.setTodosOK(Boolean.FALSE);
                    pagoMultipleDTO.setPagos(obtenerPagos(pagos, iatxResponse));
                } else {
                    LOGGER.debug(ERROR_CODIGO_DESCONOCIDO, servicioPagmasscio, codigoError);
                    throw new IatxException();
                }
            }

        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            pagoMultipleDTO.setTipoErrorUnico(TipoError.TIMEOUT_PRISMA_PAGOS);
            pagoMultipleDTO.setErrorUnico(Boolean.TRUE);
            pagoMultipleDTO.setTodosOK(Boolean.FALSE);
        }

        return pagoMultipleDTO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagos.dao.PagoMisCuentasDAO#
     * invocarPagoMisCuentas(java.util.List,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public PagoPMC invocarPagoMisCuentas(List<PagoInEntity> informacionPago, Cliente cliente) throws DAOException, PrismaTimeOutException {
        IatxRequest request = new IatxRequest(servicioPagmasscio, versionPagmasscio);
        Integer codigoError = 0;
        PagoPMC pagoPMC = new PagoPMC();
        try {
            request.setData(generateRequestData(informacionPago, cliente));
            IatxResponse iatxResponse = iatxComm.exec(request);

            if (iatxResponse.getErrorCode() == 0) {
                pagoPMC.setPago(obtenerPagos(informacionPago, iatxResponse).get(0));
                pagoPMC.setRespuestaOK(Boolean.TRUE);
                pagoPMC.setFechaHoraBody(iatxResponse.getTrama().substring(64, 78));
            } else {
                codigoError = iatxResponse.getErrorCode();
                String sCodigoError = codigoError.toString();
                if (iatxResponse.getErrorCode() == 10000000 || iatxResponse.getErrorCode() == 1
                        || sCodigoError.startsWith("1")) {
                    LOGGER.debug(ERROR_GENERAL);
                    
                    if (iatxResponse.getErrorCode() == CODIGO_ERROR_TIMEOUT_PRISMA) {
                    	LOGGER.debug(ERROR_PRISMA_TIME_OUT);
                    	throw new PrismaTimeOutException();
					}
                    
                    throw new IatxException();
                } else if (iatxResponse.getErrorCode() == CODIGO_ERROR_TIMEOUT || iatxResponse.getErrorCode() == 2
                        || sCodigoError.startsWith("2")) {
                    LOGGER.debug(ERROR_GENERAL);
                    pagoPMC.setRespuestaOK(Boolean.FALSE);
                    pagoPMC.setErrorGeneralRollback(Boolean.FALSE);
                    pagoPMC.setPago(obtenerPagos(informacionPago, iatxResponse).get(0));
                } else {
                    LOGGER.debug(ERROR_CODIGO_DESCONOCIDO, servicioPagmasscio, codigoError);
                    throw new IatxException();
                }
            }

        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException();
		}
        return pagoPMC;
    }

    /**
     * Invocar PMC con tarjeta credito.
     *
     * @param informacionPago
     *            the informacion pago
     * @param cliente
     *            the cliente
     * @return the pago PMC
     * @throws DAOException
     *             the DAO exception
     */
    @Override
    public PagoPMC invocarPMCConTarjetaCredito(List<PagoInEntity> informacionPago, Cliente cliente)
            throws DAOException {
        List<PagoTarjetaInEntity> informacionPagoConTarjeta = construirParametrosEntrada(informacionPago, cliente);
        if (ValidationEntity.validate(informacionPagoConTarjeta.get(0))) {
            IatxRequest request = new IatxRequest(servicioPagmasscre, versionPagmasscre);
            Integer codigoError = 0;
            PagoPMC pagoPMC = new PagoPMC();
            try {
                IatxRequestData iatxRequestData = generarRequestDataPagmasscre(informacionPagoConTarjeta, cliente);
                request.setData(iatxRequestData);
                IatxResponse iatxResponse = iatxComm.exec(request);

                if (iatxResponse.getErrorCode() == 0) {
                    pagoPMC.setPago(obtenerPagosConTarjeta(informacionPago, iatxResponse).get(0));
                    pagoPMC.setRespuestaOK(Boolean.TRUE);
                    pagoPMC.setFechaHoraBody(iatxResponse.getTrama().substring(64, 78));
                } else {
                    codigoError = iatxResponse.getErrorCode();
                    String sCodigoError = codigoError.toString();
                    if (iatxResponse.getErrorCode() == 10000000 || iatxResponse.getErrorCode() == 1
                            || sCodigoError.startsWith("1")) {
                        LOGGER.debug(ERROR_GENERAL);
                        throw new IatxException();
                    } else if (iatxResponse.getErrorCode() == 20000000 || iatxResponse.getErrorCode() == 2
                            || sCodigoError.startsWith("2")) {
                        LOGGER.debug(ERROR_PARCIAL);
                        pagoPMC.setRespuestaOK(Boolean.FALSE);
                        pagoPMC.setErrorGeneralRollback(Boolean.FALSE);
                        pagoPMC.setPago(obtenerPagosConTarjeta(informacionPago, iatxResponse).get(0));
                    } else {
                        LOGGER.debug(ERROR_CODIGO_DESCONOCIDO, servicioPagmasscre, codigoError);
                        throw new IatxException();
                    }
                }

            } catch (IatxException e) {
                LOGGER.error(e.getMessage(), e);
                throw new DAOException();
            }

            return pagoPMC;
        } else {
            LOGGER.error("Parametros de entrada no complen con el formado indicado. {}", informacionPagoConTarjeta);
            throw new DAOException(
                    "Error de validacion de expresiones regulares: algun/os parametro/s de entrada no cumple con el formatos especificado.");
        }

    }

    /**
     * Construir parametros entrada.
     *
     * @param listaPagos
     *            the lista pagos
     * @param cliente
     *            the cliente
     * @return the list
     */
    private List<PagoTarjetaInEntity> construirParametrosEntrada(List<PagoInEntity> listaPagos, Cliente cliente) {
        ArrayList<PagoTarjetaInEntity> pagosConTarjeta = new ArrayList<PagoTarjetaInEntity>();
        for (int i = 0; i < listaPagos.size(); i++) {
            PagoTarjetaInEntity pago = new PagoTarjetaInEntity(listaPagos.get(i), cliente);
            pagosConTarjeta.add(pago);
        }
        pagosConTarjeta.trimToSize();
        return pagosConTarjeta;
    }

    /**
     * Generar request data pagmasscre.
     *
     * @param listaPagos
     *            the lista pagos
     * @param cliente
     *            the cliente
     * @return the iatx request data
     */
    private IatxRequestData generarRequestDataPagmasscre(List<PagoTarjetaInEntity> listaPagos, Cliente cliente) {
        IatxRequestData resquestData = new IatxRequestData(cliente);
        // Nro-Ocurrencias
        resquestData.addBodyValue(StringUtils.leftPad(String.valueOf(listaPagos.size()), 2, "0"));

        for (int i = 0; i < listaPagos.size(); i++) {
            // Empresa (A04)
            resquestData.addBodyValue(listaPagos.get(i).getCodigoEmpresa());
            // Identificacion (A19)
            resquestData.addBodyValue(listaPagos.get(i).getIdentificacion());
            // Tipo-Monto (A01)
            resquestData.addBodyValue(listaPagos.get(i).getTipoMonto());
            // Tipo-Seleccion (A01)
            resquestData.addBodyValue(listaPagos.get(i).getTipoSeleccion());
            // Factura (A20)
            resquestData.addBodyValue(listaPagos.get(i).getNumeroFactura());
            // Tipo.Cta (A02)
            resquestData.addBodyValue(listaPagos.get(i).getTipoCuenta());
            // NroTjt (A16)
            resquestData.addBodyValue(listaPagos.get(i).getNroTarjeta());
            // Moneda (A03)
            resquestData.addBodyValue(listaPagos.get(i).getMoneda());
            // Importe [N(12,2)]
            resquestData.addBodyValue(listaPagos.get(i).getMonto());
            // Modo-Alta (A01)
            resquestData.addBodyValue(listaPagos.get(i).getModoAlta());
            // Cuotas (A02)
            resquestData.addBodyValue(listaPagos.get(i).getCuotas());
            // Codigo (A02)
            resquestData.addBodyValue(listaPagos.get(i).getCodigo());
            // Vto (A04)
            resquestData.addBodyValue(listaPagos.get(i).getVto());
            // Nombre (A30)
            resquestData.addBodyValue(listaPagos.get(i).getNombre());
            // Sexo (A01)
            resquestData.addBodyValue(listaPagos.get(i).getSexo());
            // FecNac (A08)
            resquestData.addBodyValue(listaPagos.get(i).getFecNac());
            // Txnsps (A10)
            resquestData.addBodyValue(listaPagos.get(i).getTxnsps());
            // Nrocom (A08)
            resquestData.addBodyValue(listaPagos.get(i).getNrocom());
            // Usrfld (A21)
            resquestData.addBodyValue(listaPagos.get(i).getUsrfld());
        }
        // Ahora los vacios
        for (int i = 0; i < (10 - listaPagos.size()); i++) {
            resquestData.addBodyValue(StringUtils.repeat(" ", 4));
            resquestData.addBodyValue(StringUtils.repeat(" ", 19));
            resquestData.addBodyValue(StringUtils.repeat(" ", 1));
            resquestData.addBodyValue(StringUtils.repeat(" ", 1));
            resquestData.addBodyValue(StringUtils.repeat(" ", 20));
            resquestData.addBodyValue(StringUtils.repeat(" ", 2));
            resquestData.addBodyValue(StringUtils.repeat(" ", 16));
            resquestData.addBodyValue(StringUtils.repeat(" ", 3));
            resquestData.addBodyValue(StringUtils.repeat("0", 14));
            resquestData.addBodyValue(StringUtils.repeat(" ", 1));
            resquestData.addBodyValue(StringUtils.repeat(" ", 2));
            resquestData.addBodyValue(StringUtils.repeat(" ", 2));
            resquestData.addBodyValue(StringUtils.repeat(" ", 4));
            resquestData.addBodyValue(StringUtils.repeat(" ", 30));
            resquestData.addBodyValue(StringUtils.repeat(" ", 1));
            resquestData.addBodyValue(StringUtils.repeat(" ", 8));
            resquestData.addBodyValue(StringUtils.repeat(" ", 10));
            resquestData.addBodyValue(StringUtils.repeat(" ", 8));
            resquestData.addBodyValue(StringUtils.repeat(" ", 21));
        }
        return resquestData;
    }

    /**
     * Obtener pagos con tarjeta.
     *
     * @param pagos
     *            the pagos
     * @param iatxResponse
     *            the iatx response
     * @return the list
     */
    private List<PagoInEntity> obtenerPagosConTarjeta(List<PagoInEntity> pagos, IatxResponse iatxResponse) {
        int estadoDePagoIndex = 1;
        int codigoDeErrorIndex = 2;
        int numeroDeControlIndex = 6;
        int comprobanteIndex = 12;
        int proxRegistroIndex = 12;
        int codigoError;
        int offset;
        int index;
        if (iatxResponse.getErrorCode() == 0) {
            offset = 1;
            index = 1;
        } else {
            offset = 6;
            index = 6;
        }
        Integer cantOcurrencias = Integer.parseInt(iatxResponse.getData(offset));
        //
        for (int i = 0; i < cantOcurrencias; i++) {
            PagoInEntity pago = pagos.get(i);
            pago.setFechaHoraBody(iatxResponse.getTrama().substring(64, 78));
            pago.setEstadoPago(new Integer(iatxResponse.getData(index + estadoDePagoIndex))); // 1
            // CODIGO-ERROR-PAGO
            codigoError = new Integer(iatxResponse.getData(index + codigoDeErrorIndex)); // 2
            // NRO-DE-ERROR-PAGO
            if (pago.getEstadoPago() != 0) {
                obtenerMensajeErrorParaPagoConTarjeta(pago, codigoError);
            }
            pago.setNumeroControl(iatxResponse.getData(index + numeroDeControlIndex)); // 6
            // NRO-CONTROL
            pago.setComprobantePorServicio(iatxResponse.getData(index + comprobanteIndex)); // 12
            pago.setFechaDePago(armarFecha(iatxResponse.getFecha()));
            index = index + proxRegistroIndex;
        }
        return pagos;
    }

    /**
     * Obtener mensaje error para pago con tarjeta.
     *
     * @param pago
     *            the pago
     * @param codigoError
     *            the codigo error
     */
    private void obtenerMensajeErrorParaPagoConTarjeta(PagoInEntity pago, int codigoError) {
        switch (codigoError) {
        case 41:
            pago.setTipoError(TipoError.IMPORTE_ERRONEO);
            pago.setMensaje(MessageFormat.format(
                    mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_IMPORTE_INCORRECTO)
                            .getMensaje(),
                    pago.getEmpresaNombreFantasia(), pago.getMontoMensajeFeedback()));
            break;
        case 50:
            pago.setTipoError(TipoError.USUARIO_O_TARJETA_INVALIDA);
            pago.setMensaje(MessageFormat.format(
                    mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_ERROR_GENERICO)
                            .getMensaje(),
                    pago.getOperacionDescripcion(), pago.getEmpresaNombreFantasia(), pago.getMontoMensajeFeedback()));
            break;
        case 52:
            pago.setTipoError(TipoError.TARJETA_INHABILITADA_O_INEXISTENTE);
            pago.setMensaje(MessageFormat.format(
                    mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_ERROR_GENERICO)
                            .getMensaje(),
                    pago.getOperacionDescripcion(), pago.getEmpresaNombreFantasia(), pago.getMontoMensajeFeedback()));
            break;
        case 53:
            pago.setTipoError(TipoError.CLAVEINVALIDA);
            pago.setMensaje(MessageFormat.format(
                    mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_ERROR_GENERICO)
                            .getMensaje(),
                    pago.getOperacionDescripcion(), pago.getEmpresaNombreFantasia(), pago.getMontoMensajeFeedback()));
            break;
        case 57:
        case 68:
            pago.setTipoError(TipoError.ERROR_REINTENTOS_OPERACION);
            pago.setMensaje(MessageFormat.format(
                    mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_ERROR_GENERICO)
                            .getMensaje(),
                    pago.getOperacionDescripcion(), pago.getEmpresaNombreFantasia(), pago.getMontoMensajeFeedback()));
            break;
        case 58:
            pago.setTipoError(TipoError.ERROR_REINTENTOS_OPERACION);
            pago.setMensaje(mensajeDao
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.NUEVO_PAGO_CON_TARJETA_SALDO_INSUFICIENTE)
                    .getMensaje());
            break;
        case 61:
            pago.setTipoError(TipoError.ERROR_LIMITE_MAYOR_NUEVO_PAGO);
            pago.setMensaje(MessageFormat.format(
                    mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_PES_SUPERADO)
                            .getMensaje(),
                    pago.getOperacionDescripcion(), pago.getEmpresaNombreFantasia(), pago.getMontoMensajeFeedback()));
            break;
        case 65:
            pago.setTipoError(TipoError.SIN_PAGOS_INFORMADOS);
            pago.setMensaje(MessageFormat.format(
                    mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_ERROR_GENERICO)
                            .getMensaje(),
                    pago.getOperacionDescripcion(), pago.getEmpresaNombreFantasia(), pago.getMontoMensajeFeedback()));
            break;
        case 72:
        case 74:
            pago.setTipoError(TipoError.TIMEOUT);
            pago.setMensaje(MessageFormat.format(
                    mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_ERROR_GENERICO)
                            .getMensaje(),
                    pago.getOperacionDescripcion(), pago.getEmpresaNombreFantasia(), pago.getMontoMensajeFeedback()));
            break;
        default:
            pago.setTipoError(TipoError.ERROR_GENERICO);
            pago.setMensaje(MessageFormat.format(
                    mensajeDao
                            .obtenerMensajePorCodigo(
                                    CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_ERROR_GENERICO_NO_HUBO_PAGO)
                            .getMensaje(),
                    pago.getOperacionDescripcion(), pago.getEmpresaNombreFantasia(), pago.getMontoMensajeFeedback()));
            break;
        }
    }

    /**
     * Completa un IatxRequestData creado en base al cliente con la informacion de
     * informacionOperacionMultiple.
     * 
     * @param listaPagos
     *            the lista pagos
     * @param cliente
     *            the cliente
     * @return the IatxRequestData con la informacion de
     *         informacionOperacionMultiple en el body del mismo
     */
    private IatxRequestData generateRequestData(List<PagoInEntity> listaPagos, Cliente cliente) {
        IatxRequestData res = new IatxRequestData(cliente);
        res.addBodyValue(StringUtils.leftPad(String.valueOf(listaPagos.size()), 2, "0"));

        for (int i = 0; i < listaPagos.size(); i++) {
            // StringUtils.leftPad(adhesionPagoAutomatico.getFiid(), 4, " ")
            res.addBodyValue(StringUtils.rightPad(listaPagos.get(i).getCodigoEmpresa(), 4, " "));
            res.addBodyValue(StringUtils.rightPad(listaPagos.get(i).getIdentificacion(), 19, " "));
            res.addBodyValue(listaPagos.get(i).getTipoMonto());
            res.addBodyValue(listaPagos.get(i).getTipoSeleccion());
            if (listaPagos.get(i).getNumeroFactura() == null) {
                res.addBodyValue(StringUtils.rightPad("", 20, " "));
            } else {
                res.addBodyValue(StringUtils.rightPad(listaPagos.get(i).getNumeroFactura(), 20, " "));
            }

            String tipoCuenta = listaPagos.get(i).getTipoCuenta();
            if ("02".equals(tipoCuenta)) {
                res.addBodyValue("09");
            } else {
                res.addBodyValue(tipoCuenta);
            }

            String sucursal = ISBANStringUtils.formateadorConCerosIzq(listaPagos.get(i).getSucursalCuenta(), 4);
            res.addBodyValue(sucursal);

            String nroCuentaProducto = ISBANStringUtils
                    .formateadorConCerosIzq(listaPagos.get(i).getNumeroCuenta().replace("/", ""), 12);
            res.addBodyValue(nroCuentaProducto);
            res.addBodyValue(listaPagos.get(i).getMoneda());
            String montoConCeros = ISBANStringUtils
                    .formateadorConCerosIzq(listaPagos.get(i).getMonto().replace(".", "").replace(",", ""), 14);
            res.addBodyValue(montoConCeros);
        }
        // Ahora los vacios
        for (int i = 0; i < (10 - listaPagos.size()); i++) {
            res.addBodyValue(StringUtils.repeat(" ", 4));
            res.addBodyValue(StringUtils.repeat(" ", 19));
            res.addBodyValue(StringUtils.repeat(" ", 1));
            res.addBodyValue(StringUtils.repeat(" ", 1));
            res.addBodyValue(StringUtils.repeat(" ", 20));
            res.addBodyValue(StringUtils.repeat(" ", 2));
            res.addBodyValue(StringUtils.repeat(" ", 4));
            res.addBodyValue(StringUtils.repeat(" ", 12));
            res.addBodyValue(StringUtils.repeat(" ", 3));
            res.addBodyValue(StringUtils.repeat("0", 14));
        }
        return res;

    }

    /**
     * <p>
     * Obtener pagos.
     * </p>
     * </br>
     * <p>
     * 1: Resultado del procesamiento del host
     * </p>
     * <p>
     * 2: Cantidad de ocurrencias
     * </p>
     * <p>
     * 3: Codigo error pago
     * </p>
     * <p>
     * 4: Numero error pago
     * </p>
     * <p>
     * 5: Sistema error
     * </p>
     * <p>
     * 6: Cantidad error
     * </p>
     * <p>
     * 7: Descripcion error pago
     * </p>
     * <p>
     * 8: Numero control
     * </p>
     * <p>
     * 9: Leyenda recibo adhesion
     * </p>
     * <p>
     * 10: Leyenda recibo pago
     * </p>
     * <p>
     * 11: Resultado sorteo
     * </p>
     * <p>
     * 12: Tipo importe sorteo
     * </p>
     * <p>
     * 13: Importe sorteo
     * </p>
     * <p>
     * 14: Comprobante por servicio
     * </p>
     *
     * @param pagos
     *            the pagos
     * @param iatxResponse
     *            the iatx response
     * @return the item mensaje respuesta
     * @throws DAOException
     *             the DAO exception
     * @throws NumberFormatException
     *             the number format exception
     */
    private List<PagoInEntity> obtenerPagos(List<PagoInEntity> pagos, IatxResponse iatxResponse) throws DAOException {
        int estadoDePagoIndex = 1;
        int codigoDeErrorIndex = 2;
        int numeroDeControlIndex = 6;
        int comprobanteIndex = 12;
        int proxRegistroIndex = 12;
        int codigoError;
        int offset;
        int index;
        if (iatxResponse.getErrorCode() == 0) {
            offset = 1;
            index = 1;
        } else {
            offset = 6;
            index = 6;
        }
        Integer cantOcurrencias = Integer.parseInt(iatxResponse.getData(offset));
        //
        for (int i = 0; i < cantOcurrencias; i++) {
            PagoInEntity pago = pagos.get(i);
            pago.setFechaHoraBody(iatxResponse.getTrama().substring(64, 78));
            pago.setEstadoPago(new Integer(iatxResponse.getData(index + estadoDePagoIndex))); // 1
            // CODIGO-ERROR-PAGO
            codigoError = new Integer(iatxResponse.getData(index + codigoDeErrorIndex)); // 2
            // NRO-DE-ERROR-PAGO
            if (pago.getEstadoPago() != 0) {
                obtenerMensajeError(pago, codigoError);
            }
            pago.setNumeroControl(iatxResponse.getData(index + numeroDeControlIndex)); // 6
            // NRO-CONTROL
            pago.setComprobantePorServicio(iatxResponse.getData(index + comprobanteIndex)); // 12
            pago.setFechaDePago(armarFecha(iatxResponse.getFecha()));
            index = index + proxRegistroIndex;
        }
        return pagos;
    }

    /**
     * Armar fecha.
     *
     * @param fecha
     *            the fecha
     * @return the date
     */
    private Date armarFecha(String fecha) {
        Date runDate = new Date();
        String y = fecha.substring(0, 4);
        String m = fecha.substring(4, 6);
        String d = fecha.substring(6, fecha.length());
        String fechaFormateada = d + "/" + m + "/" + y;
        try {
            runDate = ISBANStringUtils.parseFecha(fechaFormateada);
        } catch (ParseException e1) {
            runDate = null;
            LOGGER.error(e1.getMessage(), e1);
        }
        return runDate;
    }

    /**
     * <p>
     * Obtener mensaje error en base a su codigo.
     * </p>
     * <br>
     * <p>
     * 63: supera el PES limite diario.
     * </p>
     * <p>
     * 58: saldo insuficiente.
     * </p>
     * <p>
     * 75/76: pagada con factura, pago duplicado.
     * </p>
     * <p>
     * 56: cuenta invalida.
     * </p>
     * <p>
     * 60: ha superado el limite de operaciones diarias de banelco.
     * </p>
     * <p>
     * 73: destinatario invalido.
     * </p>
     * <p>
     * 69: empresa inhabilitada.
     * </p>
     * <p>
     * 41: importe erroneo.
     * </p>
     *
     * @author emilio.watemberg
     * @param pago
     *            the pago
     * @param codigoErrorFuncional
     *            the codigo error funcional
     * @throws DAOException
     *             the DAO exception
     * @since Jan 17, 2017
     */
    private void obtenerMensajeError(PagoInEntity pago, Integer codigoErrorFuncional) throws DAOException {
		
		String monto = pago.getMontoMensajeFeedback();
		if (!StringUtils.contains(pago.getMontoMensajeFeedback(), '$')) {
		    if (DivisaEnum.PESO.getCodigo().equalsIgnoreCase(pago.getMoneda())) {
		        monto = DivisaEnum.PESO.getSimbolo() + " " + monto;
	        } else {
	            monto = DivisaEnum.DOLAR.getSimbolo() + " " + monto;
	        }
		}
		pago.setEsErrorContemplado(true);
		switch (codigoErrorFuncional) {
		case 4:
			pago.setTipoError(TipoError.ERROR_TIME_OUT);
			pago.setMensaje(mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.PRISMA_TIMEOUT_PAGOS).getMensaje());
			break;
        case 63:
            pago.setTipoError(TipoError.ERROR_LIMITE_MAYOR_NUEVO_PAGO);
            pago.setMensaje(MessageFormat.format(
                    mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_PES_SUPERADO)
                            .getMensaje(),
					pago.getOperacionDescripcion(), pago.getEmpresaNombreFantasia(), monto));
            break;
        case 44:
            pago.setTipoError(TipoError.ERROR_NUMERO_IDENTIFICADOR);
            pago.setMensaje(MessageFormat.format(mensajeDao
                    .obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_NUMERO_IDENTIFICADOR_INVALIDO).getMensaje(),
					pago.getEmpresaNombreFantasia(), monto));
            break;
        case 58:
            pago.setTipoError(TipoError.ERROR_SALDO_INSUFICIENTE);
            pago.setMensaje(
                    mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_SALDO_INSUFICIENTE)
                            .getMensaje());
            break;
        case 75:
        case 76:
            pago.setTipoError(TipoError.ERROR_PAGO_EFECTUADO);
            pago.setMensaje(
                    mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_PAGO_DUPLICADO)
                            .getMensaje());
            break;
        case 56:
            pago.setTipoError(TipoError.ERROR_CUENTA_INVALIDA);
            pago.setMensaje(MessageFormat.format(
                    mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_ERROR_GENERICO)
                            .getMensaje(),
					pago.getOperacionDescripcion(), pago.getEmpresaNombreFantasia(), monto));
            break;
        case 60:
            pago.setTipoError(TipoError.LIMITE_OPERACIONES_DIARIO_BANELCO_SUPERADO);
            pago.setMensaje(MessageFormat.format(
                    mensajeDao
                            .obtenerMensajePorCodigo(
                                    CodigoMensajeConstantes.PAGO_MULTIPLE_LIMITE_OPERACIONES_DIARIO_BANELCO_SUPERADO)
                            .getMensaje(),
					pago.getOperacionDescripcion(), pago.getEmpresaNombreFantasia(), monto));
            break;
        case 72:
            pago.setTipoError(TipoError.TIMEOUT);
            pago.setMensaje(MessageFormat.format(
                    mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_ERROR_GENERICO)
                            .getMensaje(),
					pago.getOperacionDescripcion(), pago.getEmpresaNombreFantasia(), monto));
            break;
        case 73:
            pago.setTipoError(TipoError.DESTINATARIO_INVALIDO);
            pago.setMensaje(MessageFormat.format(
                    mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_ERROR_GENERICO)
                            .getMensaje(),
					pago.getOperacionDescripcion(), pago.getEmpresaNombreFantasia(), monto));
            break;
        case 69:
            pago.setTipoError(TipoError.EMPRESA_INHABILITADA);
            pago.setMensaje(MessageFormat.format(
                    mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_ERROR_GENERICO)
                            .getMensaje(),
					pago.getOperacionDescripcion(), pago.getEmpresaNombreFantasia(), monto));
            break;
        case 41:
            pago.setTipoError(TipoError.IMPORTE_ERRONEO);
            pago.setMensaje(MessageFormat.format(
                    mensajeDao.obtenerMensajePorCodigo(CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_IMPORTE_INCORRECTO)
                            .getMensaje(),
					pago.getEmpresaNombreFantasia(), monto));
            break;
        default:
            pago.setTipoError(TipoError.ERROR_GENERICO);
            pago.setEsErrorContemplado(false);
            pago.setMensaje(MessageFormat.format(
                    mensajeDao
                            .obtenerMensajePorCodigo(
                                    CodigoMensajeConstantes.PAGO_MULTIPLE_MENSAJE_ERROR_GENERICO_NO_HUBO_PAGO)
                            .getMensaje(),
					pago.getOperacionDescripcion(), pago.getEmpresaNombreFantasia(), monto));
            break;
        }
    }
}
