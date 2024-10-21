/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.DetallePMCBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ComprobantesPagoMisCuentasDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.SubEmpresasDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ConsultaComprobanteInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ConsultaDetallePMCOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.DetallePMCEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesBOEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CuitDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CuitEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteIatxPMCAfipDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteIatxPMCAutonomoDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteIatxPMCConDeudaDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteIatxPMCSinDeudaDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteIatxPMCVepDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobantePagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.HistorialComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoDetalleComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * The Class DetallePMCBOImpl.
 *
 * @author luis.pedro.lopez
 */
@Component
public class DetallePMCBOImpl extends ComprobantesBOImpl implements DetallePMCBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DetallePMCBOImpl.class);

    /** The comprobante pago mis cuentas dao. */
    @Autowired
    private ComprobantesPagoMisCuentasDAO comprobantePagoMisCuentasDao;

    /** The MediosPagoBO. */
    @Autowired
    private MediosPagoBO medioPagoBO;

    /** The sub empresa. */
    @Autowired
    private SubEmpresasDAO subEmpresa;

    /** The Constant TIPO_CUENTA_TARJETA. */
    private static final String TIPO_CUENTA_TARJETA = "31";
    
    /** The Constant ERROR_MEDIOPAGO. */
    private static final String ERROR_MEDIOPAGO = "El medio de pago consultado retornó vacío.";
    

    /** The Constant TIPO_CUENTA_TARJETA_MAP. */
    private static final Map<String, TipoCuenta> TIPO_CUENTA_TARJETA_MAP = new HashMap<String, TipoCuenta>();
    static {
        TIPO_CUENTA_TARJETA_MAP.put("01", TipoCuenta.MASTERCARD);
        TIPO_CUENTA_TARJETA_MAP.put("02", TipoCuenta.AMEX);
        TIPO_CUENTA_TARJETA_MAP.put("04", TipoCuenta.VISA);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.DetallePMCBO#
     * obtenerDetallePMC(java.lang.String, java.lang.String, java.lang.String,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<DetalleComprobanteDTO> obtenerDetallePMC(String fiid, String codigoValidacion, String empresa,
            Cliente cliente) {
        LOGGER.info(ComprobantesBOEnum.OBTENER_DETALLE.getId());
        try {
            MedioPago medioPago = obtenerMedioDePagoPorFIID(fiid);
            if (medioPago != null) {
                ConsultaDetallePMCOutEntity entity = comprobantePagoMisCuentasDao
                        .consultarDetalle(prepararInEntity(fiid, medioPago, cliente));
                if (esCodigoRetornoExtendidoOK(entity)) {
                    DetalleComprobanteDTO dto = generarDetalleComprobanteEntity(entity, medioPago, codigoValidacion,
                            empresa);
                    return crearRespuesta(dto);
                } else if (sinComprobarDeuda(entity)) {
                    return respuestaFactory.crearRespuestaError("", TipoError.ERROR_DETALLE_SIN_DEUDAS,
                            ComprobantesBOEnum.ERROR_DETALLE_COMPROBANTES.getId());
                }
            }
        } catch (DAOException e) {
            LOGGER.error(ERROR_MEDIOPAGO, e);
        }
        return respuestaFactory.crearRespuestaError(ComprobantesBOEnum.ERROR_DETALLE_COMPROBANTES.getDetalle(),
                TipoError.ERROR_DETALLE_COMPROBANTES, ComprobantesBOEnum.ERROR_DETALLE_COMPROBANTES.getId());
    }

    /**
     * Es codigo retorno extendido OK. El retorno extendido es 00000000, significa
     * que la respuesta es ok.
     *
     * @param entity
     *            the entity
     * @return the boolean
     */
    private Boolean esCodigoRetornoExtendidoOK(ConsultaDetallePMCOutEntity entity) {
        return entity != null && ComprobantesBOEnum.PMC_DETALLE_CODIGO_RETORNO_EXTENDIDO.getId()
                .equals(entity.getCodigoRetornoExtendido());
    }

    /**
     * Verifica si el codigo de retorno extendido es 10000065, significa que no se
     * puede recuperar deuda.
     *
     * @param entity
     *            the entity
     * @return true, if successful
     */
    private Boolean sinComprobarDeuda(ConsultaDetallePMCOutEntity entity) {
        return (entity != null) && ComprobantesBOEnum.PMC_DETALLE_CODIGO_RETORNO_SIN_COMPROBAR_DEUDA.getId()
                .equals(entity.getCodigoRetornoExtendido());
    }

    /**
	 * Generar detalle comprobante entity.
	 *
	 * @param entity
	 *            the entity
	 * @param medioPago
	 *            the medio pago
	 * @param codigoValidacion
	 *            the codigo validacion
	 * @param empresa
	 *            the empresa
	 * @return the detalle comprobante entity
	 */
    private DetalleComprobanteDTO generarDetalleComprobanteEntity(ConsultaDetallePMCOutEntity entity,
            MedioPago medioPago, String codigoValidacion, String empresa) {
        for (DetallePMCEntity detalle : entity.getDestinatarios()) {
            if (esValidoDetalle(detalle, codigoValidacion)) {
                return generarDTO(detalle, empresa, medioPago, entity.getLeyendaRecibo());
            }
        }
        return null;
    }

    /**
     * Crear respuesta.
     *
     * @param dto
     *            the dto
     * @return the respuesta
     */
    private Respuesta<DetalleComprobanteDTO> crearRespuesta(DetalleComprobanteDTO dto) {
        if (dto != null) {
            return respuestaFactory.crearRespuestaOk(DetalleComprobanteDTO.class, dto);
        }
        return respuestaFactory.crearRespuestaError(ComprobantesBOEnum.ERROR_DETALLE_COMPROBANTES.getDetalle(),
                TipoError.ERROR_DETALLE_COMPROBANTES, ComprobantesBOEnum.ERROR_DETALLE_COMPROBANTES.getId());
    }

    /**
     * Crear respuesta.
     *
     * @param comprobantesDto
     *            the comprobantes dto
     * @return the respuesta
     */
    private Respuesta<ComprobantesDTO> crearRespuestaBusquedaPorEmpresas(ComprobantesDTO comprobantesDto) {
        if (comprobantesDto != null) {
            return respuestaFactory.crearRespuestaOk(ComprobantesDTO.class, comprobantesDto);
        }
        return respuestaFactory.crearRespuestaError(ComprobantesBOEnum.ERROR_DETALLE_COMPROBANTES.getDetalle(),
                TipoError.ERROR_DETALLE_COMPROBANTES, ComprobantesBOEnum.ERROR_DETALLE_COMPROBANTES.getId());
    }

    /**
     * Es valido el detalle.
     *
     * @param detalle
     *            the detalle
     * @param codigoValidacion
     *            the codigo validacion
     * @return true, if successful
     */
    private Boolean esValidoDetalle(DetallePMCEntity detalle, String codigoValidacion) {
        if (codigoValidacion != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(detalle.getIdClienteEmpresa());
            sb.append(StringUtils.right(detalle.getFechaPago(), 4));
            sb.append(StringUtils.substring(detalle.getFechaPago(), 2, 4));
            sb.append(StringUtils.left(detalle.getFechaPago(), 2));
            sb.append(detalle.getHoraPago());
            if (StringUtils.equals(detalle.getMoneda(), ComprobantesBOEnum.MONEDA_ARG.getId())) {
                sb.append(detalle.getMoneda());
            } else {
                sb.append(ComprobantesBOEnum.MONEDA_USD.getId());
            }
            sb.append(detalle.getImporte());
            sb.append(detalle.getControlTicket());
            sb.append(StringUtils.right(detalle.getNroSecuencia().trim(), 6));
            String datosE = sb.toString();
            return datosE.equals(codigoValidacion);
        }
        return true;
    }

    /**
	 * Generar DTO.
	 *
	 * @param detalleEntity
	 *            the detalle
	 * @param empresa
	 *            the empresa
	 * @param medioPago
	 *            the medio pago
	 * @param leyendaEmpresa
	 *            the leyenda empresa
	 * @return the detalle comprobante entity
	 */
    private DetalleComprobanteDTO generarDTO(DetallePMCEntity detalleEntity, String empresa, MedioPago medioPago,
            String leyendaEmpresa) {
        Boolean esEmpresaTipoM = false;
        DetalleComprobantePagoMisCuentasDTO detalleDTO = crearDetalleComprobanteDTOPorTipo(medioPago);
        detalleDTO.setTituloComprobante(obtenerTituloArchivo(TipoOperacionComprobanteEnum.PAGO_SERVICIOS, false));
        detalleDTO.setFiid(medioPago.getFiid());
        Date fechaFormateada = ISBANStringUtils.formatearFecha(detalleEntity.getFechaPago(),
                ISBANStringUtils.FORMATO_FECHA_SIN_BARRAS);
        detalleDTO.setFechaDePago(fechaFormateada);

        detalleDTO.setEmpresa(empresa);
        detalleDTO.setHoraDePago(StringUtils.left(detalleEntity.getHoraPago(), 2) + ":"
                + StringUtils.right(detalleEntity.getHoraPago(), 2));
        detalleDTO.setTransaccion(StringUtils.right(detalleEntity.getNroSecuencia().trim(), 5));
        if (!ComprobantesBOEnum.PMC_AFIP.getId().equals(detalleDTO.getTipoPMC())) {
            detalleDTO.setIdentificacion(detalleEntity.getIdClienteEmpresa());
        } else {
            esEmpresaTipoM = setearIdentificacionAFIP(medioPago, detalleDTO, detalleEntity);
        }
        detalleDTO.setIdClienteEmpresa(detalleEntity.getIdClienteEmpresa());
        setearCuitAFIP(medioPago, detalleDTO, detalleEntity);

        detalleDTO.setImporte(ISBANStringUtils.convertirStrToBigDecimalSinException(detalleEntity.getImporte(), 2));

        String nroCuenta;
        TipoCuenta tipoCuenta;
        if (TIPO_CUENTA_TARJETA.equals(detalleEntity.getTipoCuenta())) {
            tipoCuenta = TIPO_CUENTA_TARJETA_MAP.get(StringUtils.substring(detalleEntity.getNroCuenta().trim(), -2));
            nroCuenta = new StringBuilder()
                    .append(StringUtils.upperCase(tipoCuenta.getAbreviatura()))
                    .append(" XXXX-").append(detalleEntity.getSucursal()).toString();
        } else {
            tipoCuenta = TipoCuenta.fromCodigo(detalleEntity.getTipoCuenta());
            nroCuenta = new IdentificacionCuenta(StringUtils.right(detalleEntity.getSucursal(), 3),
                    detalleEntity.getNroCuenta()).toString();
        }
        detalleDTO.setNroCuentaOrigen(nroCuenta);
        detalleDTO.setTipoCuentaOrigen(tipoCuenta);

        setearLeyenda(detalleDTO, detalleEntity, medioPago, esEmpresaTipoM);
        detalleDTO.setLeyendaEmpresa(leyendaEmpresa);
        detalleDTO.setNroControl(detalleEntity.getControlTicket());
        if (StringUtils.equals(ComprobantesBOEnum.MONEDA_ARG.getId(), detalleEntity.getMoneda())) {
            detalleDTO.setMoneda(ComprobantesBOEnum.MONEDA_ARG.getDetalle());
        } else {
            detalleDTO.setMoneda(ComprobantesBOEnum.MONEDA_USD.getDetalle());
        }
        return detalleDTO;
    }

    /**
	 * Crear detalle comprobante DTO por tipo.
	 *
	 * @param medioPago
	 *            the medio pago
	 * @return the detalle comprobante pago mis cuentas DTO
	 */
    private DetalleComprobantePagoMisCuentasDTO crearDetalleComprobanteDTOPorTipo(MedioPago medioPago) {
        DetalleComprobantePagoMisCuentasDTO detalle = null;
        if (!ComprobantesBOEnum.FIID_SUSS.getId().equals(medioPago.getFiid())) {
            switch (medioPago.getTipoPago()) {
            case 1:
                detalle = new DetalleComprobanteIatxPMCSinDeudaDTO();
                detalle.setTipoPMC(ComprobantesBOEnum.PMC_SIN_DEUDA.getId());
                detalle.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PMC_SIN_DEUDA);
                break;
            case 2:
                detalle = new DetalleComprobanteIatxPMCAfipDTO();
                detalle.setTipoPMC(ComprobantesBOEnum.PMC_AFIP.getId());
                detalle.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PMC_AFIP);
                break;
            case 3:
                detalle = crearDetalleParaMediosDePagoConDeuda(medioPago);
                break;
            default:
                detalle = new DetalleComprobantePagoMisCuentasDTO();
                break;
            }
        } else {
            detalle = new DetalleComprobanteIatxPMCAutonomoDTO();
            detalle.setTipoPMC(ComprobantesBOEnum.PMC_AUTONOMO.getId());
            detalle.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PMC_AUTONOMO);
        }
        return detalle;
    }

    /**
     * Crear detalle para medios de pago con deuda.
     *
     * @param medioPago
     *            the medio pago
     * @return the detalle comprobante pago mis cuentas DTO
     */
    private DetalleComprobantePagoMisCuentasDTO crearDetalleParaMediosDePagoConDeuda(MedioPago medioPago) {
        String tipoEmpresa = medioPago.getTipoEmpresa();
        DetalleComprobantePagoMisCuentasDTO detalle;
        if ("F".equals(tipoEmpresa)) {
            detalle = new DetalleComprobanteIatxPMCVepDTO();
            detalle.setTipoPMC(ComprobantesBOEnum.PMC_VEP.getId());
            detalle.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PMC_VEP);
        } else {
            detalle = new DetalleComprobanteIatxPMCConDeudaDTO();
            detalle.setTipoPMC(ComprobantesBOEnum.PMC_CON_DEUDA.getId());
            detalle.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_PMC_CON_DEUDA);
        }
        return detalle;
    }

    /**
     * Preparar in entity.
     *
     * @param fiid
     *            the fiid
     * @param medioPago
     *            the medio pago
     * @param cliente
     *            the cliente
     * @return the consulta comprobante in entity
     */
    private ConsultaComprobanteInEntity prepararInEntity(String fiid, MedioPago medioPago, Cliente cliente) {
        ConsultaComprobanteInEntity inEntity = new ConsultaComprobanteInEntity();
        inEntity.setCliente(cliente);
        inEntity.setEmpresa(fiid);
        inEntity.setTipoMonto(obtenerTipoMontoPorFIID(medioPago));
        return inEntity;
    }

    /**
     * Obtener tipo monto por FIID.
     *
     * @param medioPago
     *            the medio pago
     * @return the string
     */
    private String obtenerTipoMontoPorFIID(MedioPago medioPago) {
        if (ComprobantesBOEnum.S.getId().equals(medioPago.getPesPrepago())) {
            return ComprobantesBOEnum.R.getId();
        }
        return ComprobantesBOEnum.CERO.getId();
    }

    /**
     * Obtener medio de pago por FIID.
     *
     * @param fiid
     *            the fiid
     * @return the medio pago
     */
    private MedioPago obtenerMedioDePagoPorFIID(String fiid) {
        return medioPagoBO.obtenerMedioPagoPorCodigo(fiid);
    }

    /**
     * Generar leyenda factura.
     *
     * @param mensaje
     *            the mensaje
     * @return the list
     */
    private List<String> generarLeyendaFactura(String mensaje) {
        List<String> leyenda = new ArrayList<String>();
        String menos = "-";
        String primeraLinea = ComprobantesBOEnum.CERO.getId() + StringUtils.substring(mensaje, 1, 12);
        String segundaLinea = StringUtils.substring(mensaje, 34, 40) + " ICS: " + StringUtils.substring(mensaje, 0, 1)
                + StringUtils.substring(mensaje, 12, 15) + menos + StringUtils.substring(mensaje, 15, 18) + menos
                + StringUtils.substring(mensaje, 18, 21);
        String terceraLinea = StringUtils.substring(mensaje, 25, 31) + ComprobantesBOEnum.ANTICIPO_CUOTA.getId()
                + StringUtils.substring(mensaje, 31, 34);
        leyenda.addAll(Arrays.asList(primeraLinea, segundaLinea, terceraLinea));
        return leyenda;
    }

    /**
     * Setear leyenda.
     *
     * @param dto
     *            the dto
     * @param detalle
     *            the detalle
     * @param medioPago
     *            the medio pago
     * @param tipoM
     *            the tipo M
     * @return the list
     */
    private void setearLeyenda(DetalleComprobantePagoMisCuentasDTO dto, DetallePMCEntity detalle, MedioPago medioPago,
            boolean tipoM) {
        List<String> leyenda = new ArrayList<String>();
        Integer tipoPago = medioPago.getTipoPago();
        String tipoEmpresa = medioPago.getTipoEmpresa();
        if (tipoPago.equals(3) && "F".equals(tipoEmpresa)) {
            leyenda = generarLeyendaFactura(detalle.getDescripcionRecibo());
            String nroCuit = ISBANStringUtils.agregarGuionesANumeroCuitCuil(detalle.getIdClienteEmpresa());
            CuitDTO cuit = new CuitDTO(CuitEnum.CUIT, nroCuit);
            dto.setCuit(cuit);
            dto.setLeyendaFactura(leyenda);
        } else {
            if (ComprobantesBOEnum.PMC_AFIP.getId().equals(dto.getTipoPMC()) && !tipoM) {
                leyenda.add(null);
                dto.setLeyendaFactura(leyenda);
            }
            leyenda.addAll(Arrays.asList(detalle.getDescripcionRecibo()));
            dto.setLeyendaFactura(leyenda);
        }
    }

    /**
     * Setear cuit AFIP.
     *
     * @param medioPago
     *            the medio pago
     * @param dto
     *            the dto
     * @param detalle
     *            the detalle
     */
    void setearCuitAFIP(MedioPago medioPago, DetalleComprobanteDTO dto, DetallePMCEntity detalle) {
        if ("DOAC".equals(medioPago.getPescodigorubro()) || "DOJU".equals(medioPago.getPescodigorubro())
                || "DOME".equals(medioPago.getPescodigorubro())) {
            String nroCuit = ISBANStringUtils.formatearCuil(detalle.getDescripcionRecibo().trim());
            CuitDTO cuit = new CuitDTO(CuitEnum.CUIT_EMPLEADOR, nroCuit);
            dto.setCuit(cuit);
        }
    }

    /**
     * Setear identificacion AFIP.
     *
     * @param medioPago
     *            the medio pago
     * @param dto
     *            the dto
     * @param detalle
     *            the detalle
     * @return the boolean
     * @throws NumberFormatException
     *             the number format exception
     */
    private Boolean setearIdentificacionAFIP(MedioPago medioPago, DetalleComprobanteDTO dto, DetallePMCEntity detalle) {
        Boolean tipoM = false;
        String datosAdicionales;
        if (ComprobantesBOEnum.C.getId().equals(medioPago.getDatosAdicionales())) {
            datosAdicionales = ComprobantesBOEnum.TRES.getId();
        } else {
            datosAdicionales = medioPago.getDatosAdicionales();
        }
        if (!ComprobantesBOEnum.M.getId().equals(medioPago.getTipoEmpresa())) {
            if (!StringUtils.isNotBlank(datosAdicionales)) {
                dto.setIdentificacion(detalle.getIdClienteEmpresa().trim());
            } else {
                switch (Integer.parseInt(datosAdicionales)) {
                case 1:
                    dto.setIdentificacion(StringUtils.substring(detalle.getIdClienteEmpresa(), 0, 11) + " "
                            + detalle.getIdClienteEmpresa().charAt(11));
                    break;
                case 2:
                case 3:
                    dto.setIdentificacion(StringUtils.substring(detalle.getIdClienteEmpresa(), 0, 11) + " "
                            + StringUtils.substring(detalle.getIdClienteEmpresa(), 11, 13) + "/"
                            + StringUtils.substring(detalle.getIdClienteEmpresa(), 13, 17));
                    break;
                case 5:
                    dto.setIdentificacion(StringUtils.substring(detalle.getIdClienteEmpresa(), 0, 11) + " "
                            + StringUtils.substring(detalle.getIdClienteEmpresa(), 11, 14));
                    break;
                default:
                    break;
                }
            }
        } else {
            dto.setIdentificacion(StringUtils.substring(detalle.getIdClienteEmpresa(), 0, 11) + " "
                    + StringUtils.substring(detalle.getIdClienteEmpresa(), 11, 13) + "/"
                    + StringUtils.substring(detalle.getIdClienteEmpresa(), 13, 17));
            tipoM = true;
        }
        return tipoM;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.DetallePMCBO#
     * obtenerComprobantesPorEmpresas(ar.com.santanderrio.obp.servicios.
     * comprobantes.dto.TransaccionDTO,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<ComprobantesDTO> obtenerComprobantesPorEmpresas(TransaccionDTO transaccion, Cliente cliente) {
        try {
            MedioPago medioPago = obtenerMedioDePagoPorFIID(transaccion.getEmpresa());
            if (medioPago == null) {
                throw new BusinessException("No existe el medio de pago.");
            }
            ConsultaDetallePMCOutEntity entity = comprobantePagoMisCuentasDao
                    .consultarDetalle(prepararInEntity(transaccion.getEmpresa(), medioPago, cliente));
            if (esCodigoRetornoExtendidoOK(entity)) {
                List<ComprobanteDTO> comprobantes = generarListaDeComprobantesPorEmpresa(entity, medioPago);
                if (transaccion.tieneFiltroImporte()) {
                    comprobantes = filtrarComprobantesPorImporte(comprobantes, transaccion.getImporteDesde(),
                            transaccion.getImporteHasta());
                }
                return crearRespuestaBusquedaPorEmpresas(new ComprobantesDTO(comprobantes));
            } else if (sinComprobarDeuda(entity)) {
                return respuestaFactory.crearRespuestaOk(ComprobantesDTO.class, new ComprobantesDTO());

            }
        } catch (DAOException e) {
            LOGGER.error(ERROR, e);
        } catch (BusinessException e) {
            LOGGER.error(ERROR, e);
        }
        return respuestaFactory.crearRespuestaError(ComprobantesBOEnum.ERROR_DETALLE_COMPROBANTES.getDetalle(),
                TipoError.ERROR_DETALLE_COMPROBANTES, ComprobantesBOEnum.ERROR_DETALLE_COMPROBANTES.getId());
    }

    /**
     * Generar lista de comprobantes por empresa.
     *
     * @param entity
     *            the entity
     * @param medioPago
     *            the medio pago
     * @return the linked list
     */
    private LinkedList<ComprobanteDTO> generarListaDeComprobantesPorEmpresa(ConsultaDetallePMCOutEntity entity,
            MedioPago medioPago) {
        LinkedList<ComprobanteDTO> comprobantes = new LinkedList<ComprobanteDTO>();
        for (DetallePMCEntity detalle : entity.getDestinatarios()) {
            ComprobanteDTO comprobante = crearComprobante(detalle, medioPago, entity.getLeyendaRecibo());
            comprobantes.add(comprobante);
        }
        return comprobantes;
    }

    /**
     * Crear comprobante.
     *
     * @param detalleEntity
     *            the detalle entity
     * @param medioPago
     *            the medio pago
     * @param leyenda
     *            the leyenda
     * @return the comprobante DTO
     */
    private ComprobanteDTO crearComprobante(DetallePMCEntity detalleEntity, MedioPago medioPago, String leyenda) {
        ComprobanteDTO comprobante = new ComprobanteDTO();
        comprobante.setFecha(ISBANStringUtils.formatearFecha(detalleEntity.getFechaPago(),
                ISBANStringUtils.FORMATO_FECHA_SIN_BARRAS));
        comprobante.setTipoOperacion(TipoOperacionComprobanteEnum.PAGO_SERVICIOS_FILTRADOS);
        comprobante.setDestinatario(obtenerDestinatarioPorSubEmpresa(medioPago.getFiid()));
        setearMedioDePagoEImporte(comprobante, detalleEntity);

        DetalleComprobanteDTO detalle = generarDTO(detalleEntity, comprobante.getDestinatario(), medioPago, leyenda);
        comprobante.setDetalle(detalle);
        comprobante.setHistorial(HistorialComprobanteEnum.PAGO_SERVICIOS_A);
        return comprobante;
    }

    /**
     * Obtener destinatario por sub empresa.
     *
     * @param fiid
     *            the fiid
     * @return the string
     */
    private String obtenerDestinatarioPorSubEmpresa(String fiid) {
        try {
            String codigoEmpresa = subEmpresa.obtenerSubEmpresa(fiid);
            if (codigoEmpresa != null) {
                return obtenerNombreFantasiaByFIID(codigoEmpresa);
            }
        } catch (DAOException e) {
            LOGGER.error("DAOException: ", e);
        } catch (NullPointerException e) {
            LOGGER.error("NullPointerException: ", e);
        }
        return obtenerNombreFantasiaByFIID(fiid);
    }

    /**
     * Obtener medio de pago por FIID.
     *
     * @param empresa
     *            the empresa
     * @return the string
     */
    private String obtenerNombreFantasiaByFIID(String empresa) {
        MedioPago medioPago = medioPagoBO.obtenerMedioPagoPorCodigo(empresa);
        if (medioPago != null) {
            return medioPago.getNombreFantasia();
        }
        return empresa;
    }

    /**
	 * Setear medio de pago.
	 *
	 * @param comprobanteDTO
	 *            the comprobante DTO
	 * @param comprobanteEntity
	 *            the comprobante entity
	 */
    private void setearMedioDePagoEImporte(ComprobanteDTO comprobanteDTO, DetallePMCEntity comprobanteEntity) {

        String nroCuenta;
        TipoCuenta tipoCuenta;
        if (TIPO_CUENTA_TARJETA.equals(comprobanteEntity.getTipoCuenta())) {
            tipoCuenta = TIPO_CUENTA_TARJETA_MAP.get(StringUtils.substring(comprobanteEntity.getNroCuenta().trim(), -2));
            nroCuenta = new StringBuilder()
                    .append(StringUtils.upperCase(tipoCuenta.getAbreviatura()))
                    .append(" XXXX-").append(comprobanteEntity.getSucursal()).toString();
        } else {
            tipoCuenta = TipoCuenta.fromCodigo(comprobanteEntity.getTipoCuenta());
            nroCuenta = new IdentificacionCuenta(StringUtils.right(comprobanteEntity.getSucursal(), 3),
                    comprobanteEntity.getNroCuenta()).toString();
        }

        if (StringUtils.equals(ComprobantesBOEnum.MONEDA_ARG.getId(), comprobanteEntity.getMoneda())) {
            comprobanteDTO.setTipoCtaMedioDePagoPesos(tipoCuenta);
            comprobanteDTO.setCtaMedioDePagoPesos(nroCuenta);
            comprobanteDTO
                    .setImportePesos(ISBANStringUtils.convertirStrToBigDecimalSinException(comprobanteEntity.getImporte(), 2));
        } else {
            comprobanteDTO.setTipoCtaMedioDePagoDolares(tipoCuenta);
            comprobanteDTO.setCtaMedioDePagoDolares(nroCuenta);
            comprobanteDTO
                    .setImporteDolares(ISBANStringUtils.convertirStrToBigDecimalSinException(comprobanteEntity.getImporte(), 2));
        }
    }
}
