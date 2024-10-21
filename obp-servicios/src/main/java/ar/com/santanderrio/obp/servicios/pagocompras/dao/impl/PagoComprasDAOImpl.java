/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxBaseDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagocompras.dao.PagoComprasDAO;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasInDTO;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.DeudasPagoComprasEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.InstrumentoPagoEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoCompraConDeudaEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoCompraConDeudaInEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoCompraDeudaInEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.PagoCompraSinDeudaEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.exception.SinDeudasException;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * The Class PagoComprasDAOImpl.
 */
@Component
public class PagoComprasDAOImpl extends IatxBaseDAO implements PagoComprasDAO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PagoComprasDAOImpl.class);

    /** The Constant SERVICIO_PAGDEURECB. */
    private static final String SERVICIO_PAGDEURECB = "PAGDEURECB";

    /** The Constant VERSION_PAGDEURECB. */
    private static final String VERSION_PAGDEURECB = "130";

    /** The Constant ZERO_STRING. */
    private static final String ZERO_STRING = "0";

    /** The Constant RESPUESTA_OK. */
    private static final String RESPUESTA_OK_CON_PROCESO_TRAMA = "Respuesta OK, se procesa la trama.";

    /** The Constant CONSULTA_CNSDEUCLTT. */
    private static final String CONSULTA_CNSDEUCLTT = "Se genera la consulta para el servicio CNSDEUCLTT_110";

    /** The servicio consulta deuda. */
    private static final String SERVICIO_CONSULTA_DEUDA = "CNSDEUCLTT";

    /** The version consulta deuda. */
    private static final String VERSION_CONSULTA_DEUDA = "110";

    /** The Constant SERVICIO_CONSULTA_ADHESION. */
    private static final String SERVICIO_CONSULTA_ADHESION = "CNSADHEPIR";

    /** The Constant VERSION_CONSULTA_ADHESION. */
    private static final String VERSION_CONSULTA_ADHESION = "100";

    /** The Constant SERVICIO_ALTA_ADHESION. */
    private static final String SERVICIO_ALTA_ADHESION = "ACTADHEPIR";

    /** The Constant VERSION_ALTA_ADHESION. */
    private static final String VERSION_ALTA_ADHESION = "100";

    /** The Constant RESPUESTA_OK. */
    private static final String RESPUESTA_OK = "Respuesta OK.";

    /** The Constant RESPUESTA_OK_CON_ADHESIONES. */
    private static final String RESPUESTA_OK_CON_ADHESIONES = "Respuesta OK, tiene adhesiones.";

    /** The Constant RESPUESTA_SIN_ADHESIONES. */
    private static final String RESPUESTA_SIN_ADHESIONES = "Respuesta sin adhesiones.";

    /** The Constant ERROR_IATX. */
    private static final String ERROR_IATX = "Error en Iatx: {}.";

    /** The Constant SERVICIO_PAGSICEFUL. */
    private static final String SERVICIO_PAGSICEFUL = "PAGSICEFUL";

    /** The Constant VERSION_PAGSICEFUL. */
    private static final String VERSION_PAGSICEFUL = "100";

    /** The iatx comm. */
    @Autowired
    private IatxComm iatxComm;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagocompras.dao.PagoComprasDAO#
     * obtenerListaDeudas(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente, ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago,
     * java.lang.String)
     */
    @Override
    public DeudasPagoComprasEntity obtenerListaDeudas(Cliente cliente, MedioPago medioPago, String identificacion)
            throws DAOException {

        IatxRequest request = new IatxRequest(SERVICIO_CONSULTA_DEUDA, VERSION_CONSULTA_DEUDA);
        try {
            LOGGER.info(CONSULTA_CNSDEUCLTT);
            IatxRequestData requestData = generarRequestDataConsultaDeuda(cliente, medioPago, identificacion);
            request.setData(requestData);
            IatxResponse iatxResponse = iatxComm.exec(request);

            int codigoDeRetorno = iatxResponse.getErrorCode();
            if (codigoDeRetorno == 0) {
                LOGGER.info(RESPUESTA_OK_CON_PROCESO_TRAMA);
                return processTrama(iatxResponse.getTrama(), DeudasPagoComprasEntity.class);
            } else if (codigoDeRetorno == 10110001) {
                throw new SinDeudasException();
            } else {
                LOGGER.debug(ERROR_IATX, iatxResponse.getErrorCode());
                throw new DAOException(iatxResponse.getErrorMessage(), String.valueOf(iatxResponse.getErrorCode()));
            }

        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    /**
     * Generar request data consulta deuda.
     *
     * @param cliente
     *            the cliente
     * @param medioPago
     *            the medio pago
     * @param identificacion
     *            the identificacion
     * @return the iatx request data
     */
    private IatxRequestData generarRequestDataConsultaDeuda(Cliente cliente, MedioPago medioPago,
            String identificacion) {
        IatxRequestData requestData = new IatxRequestData(cliente);
        // A2 01 = sólo deuda impaga, 02 = toda la deuda
        requestData.addBodyValue("01");
        // N3 Cantidad de registros
        requestData.addBodyValue("020");
        // N12 CUIT de la empresa
        requestData.addBodyValue(medioPago.getPpdctaIdEmpAcuerdo());
        // N3 Número de Producto
        requestData.addBodyValue(StringUtils.leftPad(medioPago.getPpdctaCodProdAcuerdo(), 3, '0'));
        // N2 Número de Instancia Acuerdo Empresa
        requestData.addBodyValue(StringUtils.leftPad(medioPago.getPpdctaNroInstaCuerdo(), 2, '0'));
        // A15 Identificación del cliente ingresado
        requestData.addBodyValue(StringUtils.leftPad(identificacion, 15, '0'));
        // Para rellamado
        // A1 Tipo Comprobante deuda
        requestData.addBodyValue(" ");
        // A1 Numero comprobante deuda
        requestData.addBodyValue(" ");
        // N1 Numero de cuota
        requestData.addBodyValue("0");
        return requestData;
    }

    /**
     * Ejecuta el pago de compras sin deuda.
     *
     * @param cliente
     *            the cliente
     * @param inDTO
     *            the in DTO
     * @return the pago PC entity
     * @throws DAOException
     *             the DAO exception
     */
    @Override
    public PagoCompraSinDeudaEntity ejecutarPagoComprasSinDeuda(Cliente cliente, PagoComprasInDTO inDTO)
            throws DAOException {
        PagoCompraSinDeudaEntity pagoPC = new PagoCompraSinDeudaEntity();
        IatxRequest request = new IatxRequest(SERVICIO_PAGDEURECB, VERSION_PAGDEURECB);
        IatxRequestData requestData = generarRequestDataEjecucionPagoComprasSinDeuda(cliente, inDTO);
        request.setData(requestData);
        IatxResponse iatxResponse = new IatxResponse();

        try {
            iatxResponse = iatxComm.exec(request);
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }

        int codRetorno = iatxResponse.getErrorCode();
        if (codRetorno == 0) {
            pagoPC = processTrama(iatxResponse.getTrama(), PagoCompraSinDeudaEntity.class);
        } else {
            pagoPC.setCodigoRetornoExtendido(String.valueOf(codRetorno));
            pagoPC.setTieneError(Boolean.TRUE);
        }

        return pagoPC;
    }

    /**
     * Generar request data ejecucion pago compras sin deuda.
     *
     * @param cliente
     *            the cliente
     * @param inDTO
     *            the in DTO
     * @return the iatx request data
     */
    private IatxRequestData generarRequestDataEjecucionPagoComprasSinDeuda(Cliente cliente, PagoComprasInDTO inDTO) {
        IatxRequestData requestData = new IatxRequestData(cliente);

        // 1. N12 (idEmpresa)
        requestData.addBodyValue(inDTO.getIdEmpresa());
        // 2. N3 (codigoProductoAcuerdoEmpresa)
        requestData.addBodyValue(inDTO.getCodProductoAcuerdoEmpresa());
        // 3. N2 (nroInstanciaAcuerdoEmpresa)
        requestData.addBodyValue(inDTO.getNroInstanciaAcuerdoEmpresa());
        // 4. A15 (numeroCliente)
        requestData.addBodyValue(StringUtils.leftPad(cliente.getDni(), 15, ZERO_STRING));
        // 5. A15 (numeroBoleta)
        requestData.addBodyValue(StringUtils.leftPad(inDTO.getPid(), 15, ZERO_STRING));
        // 6. N3 (idBancoCorresponsal)
        requestData.addBodyValue(StringUtils.repeat(ZERO_STRING, 3));
        // 7. N14 (12e,2d - importeTotalPago)
        requestData.addBodyValue(inDTO.getMonto());
        // 8. N14 (12e,2d - cotizacionDolar)
        requestData.addBodyValue(StringUtils.repeat(ZERO_STRING, 14));
        // 9. N2 (cantidadInstrumentosPago)
        requestData.addBodyValue("01");
        // 10. Lista cantidadInstrumentosPago
        // 11. A2 (formaPagoDeuda)
        requestData.addBodyValue(inDTO.getFormaPagoDeuda());
        // 12. N3 (idBancoCuenta)
        requestData.addBodyValue("072");
        // 13. N5 (codigoPostal)
        requestData.addBodyValue(StringUtils.repeat(ZERO_STRING, 5));
        // 14. N8 (nroCheque)
        requestData.addBodyValue(StringUtils.repeat(ZERO_STRING, 8));
        // 15. N3 (sucursalCuenta)
        requestData.addBodyValue(inDTO.getSucursalCuenta());
        // 16. A1 (tipoCuenta)
        requestData.addBodyValue(inDTO.getTipoCuenta());
        // 17. N12 (nroCuentaGenerico)
        requestData.addBodyValue(StringUtils.right(inDTO.getNroCuentaGenerico(), 12));
        // 18. N14 (12e,2d - importeTotalPago)
        requestData.addBodyValue(inDTO.getMonto());
        // 19. N8 (fechaVencimientoCPD)
        requestData.addBodyValue(StringUtils.repeat(ZERO_STRING, 8));
        // 20. A1 (marLibreDisp)
        requestData.addBodyValue(StringUtils.repeat(" ", 1));
        // 21. N2 (cantidadComprobantesDeuda)
        requestData.addBodyValue("01");
        // 22. C2 (tipoComprobanteDeuda)
        requestData.addBodyValue("CR");
        // 23. C15 (nroComprobanteDeuda)
        requestData.addBodyValue(inDTO.getNumeroIdentificacion());
        // 24. C4 (nroCuotaDeuda)
        requestData.addBodyValue(StringUtils.repeat(ZERO_STRING, 4));
        // 25. N8 (fechaVencimientoDeuda)
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        requestData.addBodyValue(formatDate.format(date));
        // 26. N14 (importePagoDeuda)
        requestData.addBodyValue(inDTO.getMonto());
        // 27. C18 (texto1)
        requestData.addBodyValue(StringUtils.leftPad(cliente.getDni(), 18, ZERO_STRING));
        // 28. C15 (texto2)
        requestData.addBodyValue(StringUtils.repeat(" ", 15));
        // 29. C15 (texto3)
        requestData.addBodyValue(inDTO.getNumeroIdentificacion());

        return requestData;
    }

    /**
     * Ejecuta el pago de compras con deuda.
     *
     * @param cliente
     *            the cliente
     * @param inEntity
     *            the in entity
     * @return the pago compra con deuda entity
     * @throws DAOException
     *             the DAO exception
     */
    @Override
    public PagoCompraConDeudaEntity ejecutarPagoComprasConDeuda(Cliente cliente, PagoCompraConDeudaInEntity inEntity)
            throws DAOException {
        
        IatxRequest request = new IatxRequest(SERVICIO_PAGSICEFUL, VERSION_PAGSICEFUL);
        IatxRequestData requestData = generarRequestDataEjecucionPagoComprasConDeuda(cliente, inEntity);
        request.setData(requestData);
        IatxResponse iatxResponse = new IatxResponse();

        try {
            iatxResponse = iatxComm.exec(request);
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }

        PagoCompraConDeudaEntity pagoPC = new PagoCompraConDeudaEntity();
        int codRetorno = iatxResponse.getErrorCode();
        if (codRetorno == 0) {
            pagoPC = processTrama(iatxResponse.getTrama(), PagoCompraConDeudaEntity.class);
        } else {
            pagoPC.setCodigoRetornoExtendido(String.valueOf(codRetorno));
            pagoPC.setTieneError(Boolean.TRUE);
        }

        return pagoPC;
    }

    /**
     * Generar request data ejecucion pago compras con deuda.
     *
     * @param cliente
     *            the cliente
     * @param inEntity
     *            the in entity
     * @return the iatx request data
     */
    private IatxRequestData generarRequestDataEjecucionPagoComprasConDeuda(Cliente cliente,
            PagoCompraConDeudaInEntity inEntity) {
        IatxRequestData requestData = new IatxRequestData(cliente);
        // 1. N2 (funcion)
        requestData.addBodyValue("01");
        // 2. N11 (nroCuitEmpresa)
        requestData.addBodyValue(inEntity.getNroCuitEmpresa());
        // 3. N1 (nroDigEmpresa)
        requestData.addBodyValue(inEntity.getNroDigEmpresa());
        // 4. N3 (codigoProdAcEmpresa)
        requestData.addBodyValue(inEntity.getCodigoProdAcEmpresa());
        // 5. N2 (nroInstanciaAcEmpresa)
        requestData.addBodyValue(inEntity.getNroInstanciaAcEmpresa());
        // 6. A22 (idClienteTerceros)
        requestData.addBodyValue(inEntity.getIdClienteTerceros());
        // 7. N11 (cuitClienteTerceros)
        requestData.addBodyValue(inEntity.getCuitClienteTerceros());
        // 8. A1 (indicadorExcepcionComision)
        requestData.addBodyValue(inEntity.getIndicadorExcepcionComision());
        // 9. A1 (indicadorClientePropio)
        requestData.addBodyValue(inEntity.getIndicadorClientePropio());
        // 10. N3 (idBancoCorresponsal)
        requestData.addBodyValue(StringUtils.repeat(ZERO_STRING, 3));
        // 11. N14 (importeTotalPago)
        requestData.addBodyValue(inEntity.getMonto());
        // 12. N14 (cotizacionDolar)
        requestData.addBodyValue(StringUtils.repeat(ZERO_STRING, 14));
        // 13. A1 (condicionIva)
        requestData.addBodyValue(inEntity.getCondicionIva());
        // 14. A1 (tipoIngresosBrutos)
        requestData.addBodyValue(inEntity.getTipoIngresosBrutos());
        // 15. N14 (importeRetIngresosBrutos)
        requestData.addBodyValue(StringUtils.repeat(ZERO_STRING, 14));
        // 16. N14 (importeComisionTerceros)
        requestData.addBodyValue(StringUtils.repeat(ZERO_STRING, 14));
        // 17. N14 (importeIvaComision)
        requestData.addBodyValue(StringUtils.repeat(ZERO_STRING, 14));
        // 18. N14 (importeIvaAdicionalComision)
        requestData.addBodyValue(StringUtils.repeat(ZERO_STRING, 14));
        // 19. N2 (formaPagoComision)
        requestData.addBodyValue(StringUtils.repeat(ZERO_STRING, 2));
        // 20. N1 (codigoMoneda)
        requestData.addBodyValue(ZERO_STRING);
        // 21. A1 (ideCtaTerceros)
        requestData.addBodyValue(" ");
        // 22. N14 (importeComisionPago)
        requestData.addBodyValue(StringUtils.repeat(ZERO_STRING, 14));
        // 23. N2 (cantidadInstrumentosPago)
        requestData.addBodyValue(inEntity.cantidadInstrumentosPago());
        // Inicio lista completa con datos (instrumentos de pago)
        for (InstrumentoPagoEntity instrumentoPago: inEntity.getInstrumentosPago()) {
            // 24. A2 (formaPagoDeuda)
            requestData.addBodyValue(instrumentoPago.getFormPago());
            // 25. A1 (codMonedaPago)
            requestData.addBodyValue(instrumentoPago.getCodMoneda());
            // 26. A31 (idInstrumentoPago)
            requestData.addBodyValue(instrumentoPago.getNroInstru());
            // 27. 14 (impPago)
            requestData.addBodyValue(instrumentoPago.getImporte());
            // 28. N8 (fechaVtoCpd)
            requestData.addBodyValue(StringUtils.repeat(ZERO_STRING, 8));
            // 29. A1 (marLibreDisp)
            requestData.addBodyValue(" ");
        }
        // 30. N2 (cantidadComprobantes)
        requestData.addBodyValue(inEntity.cantidadDeudasPago());
        // Inicio lista completa con datos (comprobantes informados)
        for (PagoCompraDeudaInEntity deudaPago: inEntity.getDeudasPago()) {
            // 31. A2 (tipoComprobanteDeuda)
            requestData.addBodyValue(deudaPago.getTipoComprobanteDeuda());
            // 32. A15 (nroComprobanteDeuda)
            requestData.addBodyValue(deudaPago.getNroComprobanteDeuda());
            // 33. A4 (nroCuotaDeuda)
            requestData.addBodyValue(deudaPago.getNroCuotaDeuda());
            // 34. A2 (fechaVencimientoDeuda)
            requestData.addBodyValue(deudaPago.getFechaVencimientoDeuda());
            // 35. N14 (importePago)
            requestData.addBodyValue(deudaPago.getMonto());
            // 36. N14 (importePunitoriosDeuda)
            requestData.addBodyValue(deudaPago.getImportePunitoriosDeuda());
            // 37. N14 (importeIvaPago)
            requestData.addBodyValue(deudaPago.getImporteIvaPago());
            // 38. N14 (importeIvaAdicionalInteresesDeuda)
            requestData.addBodyValue(deudaPago.getImporteIvaAdicionalPago());
            // 39. A18 (texto1)
            requestData.addBodyValue(deudaPago.getTexto1());
            // 40. A15 (texto2)
            requestData.addBodyValue(deudaPago.getTexto2());
            // 41. A15 (texto3)
            requestData.addBodyValue(deudaPago.getTexto3());
            // 42. A15 (texto4)
            requestData.addBodyValue(deudaPago.getTexto4());
            // 43. A15 (texto5)
            requestData.addBodyValue(deudaPago.getTexto5());
            // 44. N1 (tipoCargaDatos)
            requestData.addBodyValue("2");
            // 45. N1 (codMonedaCprb)
            requestData.addBodyValue(" ");
        }
        return requestData;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagocompras.dao.PagoComprasDAO#
     * tieneAdhesion(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente, ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago)
     */
    @Override
    public boolean tieneAdhesion(Cliente cliente, MedioPago medioPago) throws DAOException {

        IatxRequest request = new IatxRequest(SERVICIO_CONSULTA_ADHESION, VERSION_CONSULTA_ADHESION);
        try {
            LOGGER.info("Se genera la consulta para el servicio: {}.",
                    SERVICIO_CONSULTA_ADHESION + "_" + VERSION_CONSULTA_ADHESION);
            IatxRequestData requestData = generarRequestDataTieneAdhesion(cliente, medioPago);
            request.setData(requestData);
            IatxResponse iatxResponse = iatxComm.exec(request);

            int codigoDeRetorno = iatxResponse.getErrorCode();
            if (codigoDeRetorno == 0) {
                LOGGER.info(RESPUESTA_OK_CON_ADHESIONES);
                return true;
            } else if (codigoDeRetorno == 10110001) {
                LOGGER.info(RESPUESTA_SIN_ADHESIONES);
                return false;
            } else {
                LOGGER.debug(ERROR_IATX, iatxResponse.getErrorCode());
                throw new DAOException(iatxResponse.getErrorMessage(), String.valueOf(iatxResponse.getErrorCode()));
            }
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DAOException(e);
        }
    }

    /**
     * Generar request data tiene adhesion.
     *
     * @param cliente
     *            the cliente
     * @param medioPago
     *            the medio pago
     * @return the iatx request data
     */
    private IatxRequestData generarRequestDataTieneAdhesion(Cliente cliente, MedioPago medioPago) {
        IatxRequestData requestData = new IatxRequestData(cliente);
        // N2 01 = Consulta
        requestData.addBodyValue("01");
        // N3 Cantidad de ítems solicitados
        requestData.addBodyValue("030");
        // N12 CUIT de la empresa
        requestData.addBodyValue(medioPago.getPpdctaIdEmpAcuerdo());
        // N3 Número de Producto
        requestData.addBodyValue(StringUtils.leftPad(medioPago.getPpdctaCodProdAcuerdo(), 3, '0'));
        // N2 Número de Instancia Acuerdo Empresa
        requestData.addBodyValue(StringUtils.leftPad(medioPago.getPpdctaNroInstaCuerdo(), 2, '0'));
        // N8 Número único de persona
        requestData.addBodyValue(StringUtils.leftPad(cliente.getNup(), 8, '0'));
        // Para rellamado
        // A22 Tipo Comprobante deuda
        requestData.addBodyValue(StringUtils.repeat(' ', 22));
        return requestData;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.pagocompras.dao.PagoComprasDAO#
     * altaAdhesion(ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
     * ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago,
     * java.lang.String)
     */
    @Override
    public void altaAdhesion(Cliente cliente, MedioPago medioPago, String identificacion) {

        IatxRequest request = new IatxRequest(SERVICIO_ALTA_ADHESION, VERSION_ALTA_ADHESION);
        try {
            LOGGER.info("Se genera la consulta para el servicio: {}.",
                    SERVICIO_CONSULTA_ADHESION + "_" + VERSION_ALTA_ADHESION);
            IatxRequestData requestData = generarRequestDataAltaAdhesion(cliente, medioPago, identificacion);
            request.setData(requestData);
            IatxResponse iatxResponse = iatxComm.exec(request);

            int codigoDeRetorno = iatxResponse.getErrorCode();
            if (codigoDeRetorno == 0) {
                LOGGER.info(RESPUESTA_OK);
            } else {
                LOGGER.debug(ERROR_IATX, iatxResponse.getErrorCode());
            }
        } catch (IatxException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Generar request data alta adhesion.
     *
     * @param cliente
     *            the cliente
     * @param medioPago
     *            the medio pago
     * @param identificacion
     *            the identificacion
     * @return the iatx request data
     */
    private IatxRequestData generarRequestDataAltaAdhesion(Cliente cliente, MedioPago medioPago,
            String identificacion) {
        IatxRequestData requestData = new IatxRequestData(cliente);
        // N2 01 = ALTA, 02 = BAJA
        requestData.addBodyValue("01");
        // N12 CUIT de la empresa
        requestData.addBodyValue(medioPago.getPpdctaIdEmpAcuerdo());
        // N3 Número de Producto
        requestData.addBodyValue(StringUtils.leftPad(medioPago.getPpdctaCodProdAcuerdo(), 3, '0'));
        // N2 Número de Instancia Acuerdo Empresa
        requestData.addBodyValue(StringUtils.leftPad(medioPago.getPpdctaNroInstaCuerdo(), 2, '0'));
        // N8 Número único de persona
        requestData.addBodyValue(StringUtils.leftPad(cliente.getNup(), 8, '0'));
        // A22 Identificación del cliente ingresado
        requestData.addBodyValue(StringUtils.rightPad(identificacion, 22));
        // A60 DESCRIPCION ADHESION
        requestData.addBodyValue(StringUtils.repeat(' ', 60));
        return requestData;
    }

}
