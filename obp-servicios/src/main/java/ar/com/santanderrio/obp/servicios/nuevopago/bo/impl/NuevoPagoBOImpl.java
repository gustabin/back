/*
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.bo.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.PagoMisCuentasSinDeudaBuilder;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.dao.DatosSolicitanteDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuit.entities.Cuit;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaInDTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaOutDTO;
import ar.com.santanderrio.obp.servicios.nuevopago.bo.NuevoPagoBO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.Factura;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.BanelcoDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.PagoMisCuentasDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.PrismaTimeOutException;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;

/**
 * Controller para manejar los nuevos pagos.
 * 
 * @author
 * 
 */
@Component
public class NuevoPagoBOImpl implements NuevoPagoBO {

    /** The Constant TIMEOUT_EXCEPTION. */
    private static final String TIMEOUT_EXCEPTION = "TIMEOUT";

	/** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(NuevoPagoBOImpl.class);

    /** The Constant TIPO_PAGO3. */
    private static final int TIPO_PAGO3 = 3;

    /** The Constant CARACTERES_A_BORRAR. */
    private static final int CARACTERES_A_BORRAR = 8;

    /** The Constant MSJ_ERROR_CONVERSION_IMPORTE. */
    private static final String MSJ_ERROR_CONVERSION_IMPORTE = "Error al convertir importe en validacon de importe de una factura";

    /** The banelco dao. */
    @Autowired
    private BanelcoDAO banelcoDao;

    /** The cuenta BO. */
    @Autowired
    private CuentaBO cuentaBO;

    /** The mensaje DAO. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The medios pago BO. */
    @Autowired
    private MediosPagoBO mediosPagoBO;

    /** The alta BO. */
    @Autowired
    private ScompBO altaBO;

    /** The pago mis cuentas DAO. */
    @Autowired
    private PagoMisCuentasDAO pagoMisCuentasDAO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The datos solicitante DAO. */
    @Autowired
    private DatosSolicitanteDAO datosSolicitanteDAO;

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.nuevopago.bo.NuevoPagoBO#obtenerCuentasYFacturas(
     * ar.com.santanderrio.obp.clientes.entities.Cliente,
     * ar.com.santanderrio.obp.pagos.entities.MedioPagoView)
     */
    @Override
    public Respuesta<MedioPagoSelectionView> obtenerPagos(Cliente cliente, MedioPagoView medioPagoView) {
        String codigoPagoElectronico = modificarCodigoPagoElectronicoSiVEP(medioPagoView);

        MedioPagoSelectionView medioPagoSelectionView = new MedioPagoSelectionView();
        LOGGER.info("Obtener las facturas para el cliente {}, empresa {} con el codigo {}.", cliente.getNup(),
                medioPagoView.getFiid(), codigoPagoElectronico);
        try {
            List<Factura> listaFacturas = obtenerListaFacturasOrdenada(cliente, medioPagoView, codigoPagoElectronico);

            sesionParametros.setFacturasPagosPendientes(listaFacturas);
            LOGGER.info("Arma el objeto que se va a devolver a la vista");
            medioPagoSelectionView.setListaFacturas(listaFacturas);
            medioPagoSelectionView.setCantidadFacturas(listaFacturas.size());

            if (medioPagoSelectionView.getCantidadFacturas() > 1) {
                medioPagoSelectionView
                        .setTextoSeleccionMultiplesFacturas(mensajeBO.obtenerMensajePorCodigo("1264").getMensaje());
            }

            return respuestaFactory.crearRespuestaOk(medioPagoSelectionView);
        } catch (DAOException e) {
            LOGGER.error("Error al armar la respuesta con las facturas.", e);
            return crearRespuestaError(medioPagoView, e.getErrorCode());
        } catch (Exception e) {
            LOGGER.error("Error al armar la respuesta con las facturas.", e);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "1056");
        }

    }

    /**
	 * Validar que el codigo de pago cumpla con el formato.
	 *
	 * @param medioPagoView
	 *            the medio pago view
	 * @return the boolean
	 */
    @Override
    public Boolean isFormatoCodigoPagoElectronicoValid(MedioPagoView medioPagoView) {
        final Integer largoMaximoSiNoEsCuit = 19;
        final Integer longitudMinimaSiNoEsCuit = 3;
        Boolean isValid = Boolean.TRUE;

        if (esCuit(medioPagoView.getDatosAdicionales(), medioPagoView.getTipoEmpresa())) {
            Cuit cuit = new Cuit(medioPagoView.getCodigoPagoElectronico());
            if (!cuit.esCuitValido()) {
                isValid = Boolean.FALSE;
            }
        } else {
            if (largoMaximoSiNoEsCuit <= StringUtils.length(medioPagoView.getCodigoPagoElectronico())
                    || longitudMinimaSiNoEsCuit >= StringUtils.length(medioPagoView.getCodigoPagoElectronico())) {
                isValid = Boolean.TRUE;
            }
        }
        return isValid;
    }

    /**
     * Determina si tiene que validar por cuit o es solo un codigo de pago.
     *
     * @param datosAdicionales
     *            the datos adicionales
     * @param tipoEmpresa
     *            the tipo empresa
     * @return true, if successful
     */
    private boolean esCuit(String datosAdicionales, String tipoEmpresa) {
        final String adicionalesPosibles = "1235";
        if (StringUtils.isNotBlank(datosAdicionales) && StringUtils.contains(adicionalesPosibles, datosAdicionales)) {
            return true;
        }
        final String empresasPosibles = "MF";
        if (StringUtils.isNotBlank(tipoEmpresa) && StringUtils.contains(empresasPosibles, tipoEmpresa)) {
            return true;
        }
        return false;
    }

    /**
     * Obtiene la lista de facturas y las ordena.
     *
     * @param cliente
     *            the cliente
     * @param medioPagoView
     *            the medio pago view
     * @param codigoPagoElectronico
     *            the codigo pago electronico
     * @return the list
     * @throws DAOException
     *             the DAO exception
     */
    private List<Factura> obtenerListaFacturasOrdenada(Cliente cliente, MedioPagoView medioPagoView,
            String codigoPagoElectronico) throws DAOException {
        List<Factura> listaFacturas = banelcoDao.obtenerListaFacturas(cliente, medioPagoView.getFiid(),
                codigoPagoElectronico);
        ordenarFacturas(listaFacturas);
        return listaFacturas;
    }

    /**
     * Ordena las facturas.
     *
     * @param listaFacturas
     *            the lista facturas
     */
    private void ordenarFacturas(List<Factura> listaFacturas) {
        Collections.sort(listaFacturas, new Comparator<Factura>() {
            @Override
            public int compare(Factura factura1, Factura factura2) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    return format.parse(factura1.getFechaVencimiento())
                            .compareTo(format.parse(factura2.getFechaVencimiento()));
                } catch (ParseException e) {
                    LOGGER.error("Error de parseo de fecha de vencimiento.", e);
                    return 0;
                }
            }
        });
    }

    /**
     * Creo la respuesta erronea de acuerdo a los siguientes casos: - Si hay un
     * CUIL/CUIT, envio mensaje 1204 - Si hay dos CUIL/CUIT, envio mensaje 1205
     * - Si hay codigo de pago electronico comun (no CUIL/CUIT) envio mensaje
     * 1206.
     *
     * @param medioPagoView
     *            the medio pago view
     * @param errorCode
     *            the error code
     * @return the respuesta
     */
    private Respuesta<MedioPagoSelectionView> crearRespuestaError(MedioPagoView medioPagoView, String errorCode) {
        String codErrorTimeout = "10000004";
        if (StringUtils.equals(codErrorTimeout, errorCode)) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.FALLO_DE_SERVICIO_ERROR);
        }
        Cuit cuit1 = new Cuit(medioPagoView.getCodigoPagoElectronico());
        Cuit cuit2 = obtenerCuit2(medioPagoView);

        if (cuit2 != null && cuit2.esCuitValido()) {
            return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_GENERICO, "1205");
        } else if (cuit2 == null && cuit1.esCuitValido()) {
            return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_GENERICO, "1204");
        } else {
            return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.FACTURA_SIN_DEUDA);
        }
    }

    /**
     * Obtiene el cuit 2.
     *
     * @param medioPagoView
     *            the medio pago view
     * @return the cuit
     */
    private Cuit obtenerCuit2(MedioPagoView medioPagoView) {
        if (medioPagoView.getCodigoPagoElectronico2() != null) {
            return new Cuit(medioPagoView.getCodigoPagoElectronico2());
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.nuevopago.bo.NuevoPagoBO#obtenerCuentas
     * (ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    /*
     * Obtengo la lista de cuentas del cliente, tambien se transforman las
     * Cuentas en CuentasView
     */
    @Override
    public MedioPagoSelectionView obtenerCuentas(Cliente cliente) throws BusinessException {
        List<Cuenta> listaCuentas = cuentaBO.obtenerCuentasBanelcoPesos(cliente);
        if (CollectionUtils.isEmpty(listaCuentas)) {
            throw new BusinessException();
        }
        MedioPagoSelectionView medioPagoSelectionView = new MedioPagoSelectionView();
        medioPagoSelectionView.setListaCuentasAView(listaCuentas);
        return medioPagoSelectionView;

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.nuevopago.bo.NuevoPagoBO#
     * obtenerFacturas(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente, ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago)
     */
    @Override
    public Respuesta<List<Factura>> obtenerFacturas(Cliente cliente, NuevoPago nuevoPago) {
        try {
            List<Factura> facturas = banelcoDao.obtenerListaFacturas(cliente, nuevoPago.getFiid(),
                    nuevoPago.getCodigoPagoElectronico());

            if (CollectionUtils.isNotEmpty(facturas)) {
                return crearRespuestaOKFacturas(facturas);
            } else {
                return crearRespuestaWarningFacturas("1061");
            }
        } catch (DAOException e) {
            LOGGER.error("Codigo de pago electronico {} Invalido!!!.", nuevoPago.getCodigoPagoElectronico(), e);
            return crearRespuestaWarningFacturas(CodigoMensajeConstantes.FACTURA_SIN_DEUDA);
        }
    }

    /**
     * Crear respuesta warning facturas.
     *
     * @param codigoMensaje
     *            the codigo mensaje
     * @return the respuesta
     */
    private Respuesta<List<Factura>> crearRespuestaWarningFacturas(String codigoMensaje) {
        Respuesta<List<Factura>> respuesta = respuestaFactory.crearRespuestaWarning("", TipoError.WARNING,
                codigoMensaje);
        respuesta.setRespuestaVacia(true);
        LOGGER.warn(respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
        return respuesta;
    }

    /**
     * Crear respuesta OK facturas.
     *
     * @param facturas
     *            the facturas
     * @return the respuesta
     */
    private Respuesta<List<Factura>> crearRespuestaOKFacturas(List<Factura> facturas) {
        return respuestaFactory.crearRespuestaOk(facturas);
    }

    /**
     * Valida los codigos de pago electronico.
     * 
     * @param cliente
     *            the cliente
     * @param nuevoPago
     *            the nuevo pago
     * @return the respuesta
     */
    @Override
    public Respuesta<Boolean> validarImporteFactura(Cliente cliente, NuevoPago nuevoPago) {

        // caso pago voluntario
        if (StringUtils.isNotBlank(nuevoPago.getCodigoPagoElectronico())
                && StringUtils.isNotBlank(nuevoPago.getCodigoPagoElectronico2())) {
            /// validar importe
            return crearRespuestaOKValidacionImporteFactura();
        }

        return procesarValidacionImporte(cliente, nuevoPago);
    }

    /**
     * Crear respuesta OK validacion importe factura.
     *
     * @return the respuesta
     */
    private Respuesta<Boolean> crearRespuestaOKValidacionImporteFactura() {
        return respuestaFactory.crearRespuestaOk(Boolean.class, true);
    }

    /**
     * Procesar validacion importe.
     *
     * @param cliente
     *            the cliente
     * @param nuevoPago
     *            the nuevo pago
     * @return the respuesta
     */
    private Respuesta<Boolean> procesarValidacionImporte(Cliente cliente, NuevoPago nuevoPago) {
        Respuesta<MedioPago> mediosPagoRta = mediosPagoBO.getByCodigo(nuevoPago.getFiid());

        if (mediosPagoRta.isRespuestaVacia()) {
            return crearRespuestaWarningBoolean(mediosPagoRta.getItemsMensajeRespuesta());
        }

        Respuesta<List<PagoPendiente>> pagosSesion = obtenerPagosSesion(cliente, nuevoPago);
        List<Factura> facturas = sesionParametros.getFacturasPagosPendientes();
        if (pagosSesion.isRespuestaVacia() && CollectionUtils.isEmpty(facturas)) {
            return crearRespuestaWarningBoolean(pagosSesion.getItemsMensajeRespuesta());
        }

        if (!pagosSesion.isRespuestaVacia()) {
            for (PagoPendiente pago : pagosSesion.getRespuesta()) {
                if (nuevoPago.getFacturaNumero().equals(pago.getIdentificacionDeFactura().trim())) {
                    return ejecutarValidacionImporte(pago.getImporte().toString(), nuevoPago.getMonto(),
                            mediosPagoRta.getRespuesta().getTipoImporte());
                }
            }
        }

        // obtengo facturas
        // Respuesta<List<Factura>> facturasRta = obtenerFacturas(cliente,
        // nuevoPago);
        for (Factura factura : facturas) {
            if (nuevoPago.getFacturaNumero().equals(factura.getNumeroFactura())) {
                return ejecutarValidacionImporte(factura.getMonto(), nuevoPago.getMonto(),
                        mediosPagoRta.getRespuesta().getTipoImporte());
            }
        }

        String mensajeError = "Tipo importe no coincide con la factura";
        LOGGER.debug(mensajeError);
        return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(Boolean.class, mensajeError,
                TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());
    }

    /**
     * Crear respuesta warning boolean.
     *
     * @param itemsMensajeRespuesta
     *            the items mensaje respuesta
     * @return the respuesta
     */
    private Respuesta<Boolean> crearRespuestaWarningBoolean(List<ItemMensajeRespuesta> itemsMensajeRespuesta) {
        return respuestaFactory.crearRespuestaWarning(Boolean.class, null, itemsMensajeRespuesta);
    }

    /**
     * Logica de validacion importe.
     *
     * @param montoFactura
     *            the monto factura
     * @param montoPago
     *            the monto pago
     * @param tipoImporte
     *            the tipo importe
     * @return the respuesta
     */
    private Respuesta<Boolean> ejecutarValidacionImporte(String montoFactura, String montoPago, String tipoImporte) {
        BigDecimal montoDato = null;
        BigDecimal montoValidar = null;

        try {
            montoDato = ISBANStringUtils.convertirImporte(montoFactura);
            montoValidar = ISBANStringUtils.convertirImporte(montoPago);
        } catch (ImporteConvertException e) {
            LOGGER.error(MSJ_ERROR_CONVERSION_IMPORTE, e);
            return crearRespuestaErrorFacturas(MSJ_ERROR_CONVERSION_IMPORTE);
        }
        final int igualCompareTo = 0;
        if (NuevoPagoBO.NO_PERMINTE_MODIFICACION.equals(tipoImporte)) {
            return montoDato.compareTo(montoValidar) == igualCompareTo ? crearRespuestaOKValidacionImporteFactura()
                    : respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(Boolean.class,
                            "El monto de pago no es igual", TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());
        }
        final int menorCompareTo = -1;
        final int mayorQueCero = 0;
        return ejecutarValidacionImporteRetorno(montoDato, montoValidar, tipoImporte, igualCompareTo, menorCompareTo,
                mayorQueCero);
    }

    /**
     * Crear respuesta error facturas.
     *
     * @param mensajeError
     *            the mensaje error
     * @return the respuesta
     */
    private Respuesta<Boolean> crearRespuestaErrorFacturas(String mensajeError) {
        return respuestaFactory.crearRespuestaError(Boolean.class, mensajeError, "");
    }

    /**
     * Validacion monto.
     *
     * @param montoFactura
     *            the monto factura
     * @param montoPago
     *            the monto pago
     * @param tipoImporte
     *            the tipo importe
     * @return the respuesta
     */
    public Respuesta<Boolean> validacionMonto(String montoFactura, String montoPago, String tipoImporte) {

        final int igualCompareTo = 0;
        final int menorCompareTo = -1;
        final int mayorQueCero = 0;

        try {
            BigDecimal montoDato = ISBANStringUtils.convertirImporte(montoFactura);
            BigDecimal montoValidar = ISBANStringUtils.convertirImporte(montoPago);
            if (NuevoPagoBO.NO_PERMINTE_MODIFICACION.equals(tipoImporte)) {
                if (montoDato.compareTo(montoValidar) == igualCompareTo) {
                    return crearRespuestaOKValidacionImporteFactura();
                } else {
                    return respuestaFactory.crearRespuestaError(Boolean.class, MSJ_ERROR_CONVERSION_IMPORTE, "");
                }
            }

            return ejecutarValidacionImporteRetorno(montoDato, montoValidar, tipoImporte, igualCompareTo,
                    menorCompareTo, mayorQueCero);
        } catch (ImporteConvertException e) {
            LOGGER.error(MSJ_ERROR_CONVERSION_IMPORTE, e);
            return respuestaFactory.crearRespuestaError(Boolean.class, MSJ_ERROR_CONVERSION_IMPORTE, "");
        }

    }

    /**
     * Ejecutar validacion importe retorno.
     *
     * @param montoDato
     *            the monto dato
     * @param montoValidar
     *            the monto validar
     * @param tipoImporte
     *            the tipo importe
     * @param igualCompareTo
     *            the igual compare to
     * @param menorCompareTo
     *            the menor compare to
     * @param mayorQueCero
     *            the mayor que cero
     * @return the respuesta
     */
    private Respuesta<Boolean> ejecutarValidacionImporteRetorno(BigDecimal montoDato, BigDecimal montoValidar,
            String tipoImporte, int igualCompareTo, int menorCompareTo, int mayorQueCero) {
        if (NuevoPagoBO.MODIFICA_SOLO_POR_IMPORTE_IGUAL_O_MAYOR.equals(tipoImporte)) {
            return montoDato.compareTo(montoValidar) == igualCompareTo
                    || montoDato.compareTo(montoValidar) == menorCompareTo
                            ? crearRespuestaOKValidacionImporteFactura()
                            : respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(Boolean.class,
                                    "El importe solo puede ser mayor",
                                    TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());
        }

        if (NuevoPagoBO.MODIFICA_POR_CUALQUIER_IMPORTE.equals(tipoImporte)) {
            return montoValidar.intValue() > mayorQueCero ? crearRespuestaOKValidacionImporteFactura()
                    : respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(Boolean.class,
                            "El importe debe ser mayor a cero", TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());
        }

        return respuestaFactory.crearRespuestaError(Boolean.class, "No existe el tipo de importe para la factura", "");
    }

    /**
     * Modificar codigo de pago electronico si es VEP. Este metodo valida si
     * corresponde modificar el codigo de pago electronico en caso de VEP
     * mediante los dos CUIL enviados, concatenando el primer cuil con el dni
     * del segundo
     * 
     * @param medioPagoView
     *            the medio pago view
     * @return el codigo de pago electronico que corresponde
     */
    public String modificarCodigoPagoElectronicoSiVEP(MedioPagoView medioPagoView) {
        Respuesta<MedioPago> empresa = mediosPagoBO.getByCodigo(medioPagoView.getFiid());

        if (TIPO_PAGO3 == empresa.getRespuesta().getTipoPago() && "F".equals(empresa.getRespuesta().getTipoEmpresa())) {

            LOGGER.info("Se formatea el segundo CUIL para poder armar el codigo de pago electronico VEP");
            String codigo2 = medioPagoView.getCodigoPagoElectronico2().substring(2);
            codigo2 = codigo2.substring(0, CARACTERES_A_BORRAR);
            String codigoPagoElectronicoVEP = medioPagoView.getCodigoPagoElectronico() + codigo2;

            LOGGER.info("Se devuelve el codigo de pago electronico formateado");
            return codigoPagoElectronicoVEP;

        }

        LOGGER.info("Se devuelve el codigo de pago electronico sin formato");
        return medioPagoView.getCodigoPagoElectronico();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.nuevopago.bo.NuevoPagoBO#pagarRecarga(
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente,
     * ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaInDTO)
     */
    @Override
    public NuevaRecargaOutDTO pagarRecarga(Cliente cliente, Cuenta cuenta, NuevaRecargaInDTO nuevaRecargaInDTO)
            throws BusinessException{

        PagoInEntity informacionPago = new PagoInEntity(nuevaRecargaInDTO, cuenta);
        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        pagos.add(informacionPago);
        try {
            PagoPMC response;
            if (StringUtils.isNotBlank(nuevaRecargaInDTO.getNroTarjeta())) {
                datosSolicitanteDAO.obtenerSexoCliente(cliente);
                response = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);
            } else {
                response = pagoMisCuentasDAO.invocarPagoMisCuentas(pagos, cliente);
            }
            if (response.getRespuestaOK()) {
                PagoMisCuentasSinDeudaBuilder request = new PagoMisCuentasSinDeudaBuilder(cliente)
                        .setPagoInEntity(informacionPago).setPagoResponse(response);
                altaBO.altaScompTransferencia(request);
            }
            return new NuevaRecargaOutDTO(response);
        } catch (DAOException e) {
            LOGGER.error("Error al efectuar la recarga.", e);
            throw new BusinessException();
        }

    }

    /**
     * Efectua el pago del servicio.
     *
     * @param medioPago
     *            the medio pago
     * @param pagoMisCuentasDTO
     *            the pago mis cuentas DTO
     * @param cliente
     *            the cliente
     * @return the nueva recarga out DTO
     * @throws BusinessException
     *             the business exception
     */
    @Override
    public NuevaRecargaOutDTO pagar(MedioPago medioPago, PagoMisCuentasDTO pagoMisCuentasDTO, Cliente cliente)
            throws BusinessException {
        TipoMedioPagoBO tipoMedioPagoBO = mediosPagoBO.obtenerTipoMedioPago(medioPago);

        List<PagoInEntity> pagos = new ArrayList<PagoInEntity>();
        pagos.add(tipoMedioPagoBO.generarPagoInEntity(pagoMisCuentasDTO));
        try {
            PagoPMC pago;
            if (StringUtils.isNotBlank(pagoMisCuentasDTO.getNroTarjeta())) {
                datosSolicitanteDAO.obtenerSexoCliente(cliente);
                pago = pagoMisCuentasDAO.invocarPMCConTarjetaCredito(pagos, cliente);
            } else {
                pago = pagoMisCuentasDAO.invocarPagoMisCuentas(pagos, cliente);
            }

            if (pago.getRespuestaOK()) {
                altaBO.altaScompTransferencia(
                        tipoMedioPagoBO.generarAltaScompRequest(cliente, pago, pagos.get(0), pagoMisCuentasDTO, null));
            }
            return new NuevaRecargaOutDTO(pago);
        }catch (PrismaTimeOutException e){
        	LOGGER.error("Error al invocar PagoMisCuentas.", e);
        	throw new BusinessException(TIMEOUT_EXCEPTION);
        } catch (DAOException e) {
            LOGGER.error("Error al invocar PagoMisCuentas.", e);
            throw new BusinessException(e.getMessage());
        } 

    }

    /**
     * valida si el cuit es correcto.
     *
     * @param cuit
     *            the cuit
     * @return the respuesta
     */
    @Override
    public Respuesta<String> validarCuit(String cuit) {
        Cuit unCuit = new Cuit(cuit);
        if (unCuit.esCuitValido()) {
            return respuestaFactory.crearRespuestaOk(String.class, "CUIT valido");
        } else {
            return respuestaFactory.crearRespuestaWarning(String.class,
                    mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.FACTURA_SIN_DEUDA).getMensaje(),
                    new ArrayList<ItemMensajeRespuesta>());
        }
    }

    /**
	 * Obtengo las facturas de la lista de deuda de sesion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param nuevoPago
	 *            the nuevo pago
	 * @return the respuesta
	 */
    public Respuesta<List<PagoPendiente>> obtenerPagosSesion(Cliente cliente, NuevoPago nuevoPago) {
        // List<Factura> facturas = banelcoDao.obtenerListaFacturas(cliente,
        // nuevoPago.getFiid(),nuevoPago.getCodigoPagoElectronico());
        Respuesta<List<PagoPendiente>> pagos = sesionParametros.getRespuestaPagosPendientes();
        List<PagoPendiente> pagosPendientes = filtrarPagos(pagos.getRespuesta(), nuevoPago.getFiid(),
                nuevoPago.getCodigoPagoElectronico());
        if (CollectionUtils.isNotEmpty(pagosPendientes)) {
            Respuesta<List<PagoPendiente>> rst = respuestaFactory.crearRespuestaOk(pagosPendientes);
            rst.setRespuestaVacia(false);
            return rst;
        } else {
            Respuesta<List<PagoPendiente>> respuesta = respuestaFactory.crearRespuestaWarning("", TipoError.WARNING,
                    "1061");
            respuesta.setRespuestaVacia(true);
            LOGGER.warn(respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
            return respuesta;

        }
        // LOGGER.error("Codigo de pago electronico {} Invalido!!!.",
        // nuevoPago.getCodigoPagoElectronico(), e);
        // return
        // crearRespuestaWarningFacturas(CodigoMensajeConstantes.FACTURA_SIN_DEUDA);

    }

    /**
	 * Me fijo si existe en sesion algun pago que concuerde con los parametros.
	 *
	 * @param pagos
	 *            the pagos
	 * @param fiid
	 *            the fiid
	 * @param codigoPagoElectronico
	 *            the codigo pago electronico
	 * @return the list
	 */
    private List<PagoPendiente> filtrarPagos(List<PagoPendiente> pagos, String fiid, String codigoPagoElectronico) {
        List<PagoPendiente> pagosEncontrados = new ArrayList<PagoPendiente>();
        for (PagoPendiente pago : pagos) {
            if (pago.getCodigoEmpresa().trim().equals(fiid.trim())
                    && pago.getCodigoClienteEmpresa().trim().equals(codigoPagoElectronico.trim())) {
                pagosEncontrados.add(pago);
            }
        }

        return pagosEncontrados;
    }

}
