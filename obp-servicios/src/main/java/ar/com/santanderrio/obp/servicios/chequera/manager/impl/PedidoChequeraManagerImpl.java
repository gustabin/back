/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.chequera.bo.PedidoChequeraBO;
import ar.com.santanderrio.obp.servicios.chequera.dto.ChequeraInDTO;
import ar.com.santanderrio.obp.servicios.chequera.dto.ChequeraOutDTO;
import ar.com.santanderrio.obp.servicios.chequera.manager.PedidoChequeraManager;
import ar.com.santanderrio.obp.servicios.chequera.view.CantidadChequesComunView;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionInView;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionView;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraConfirmacionViewResponse;
import ar.com.santanderrio.obp.servicios.chequera.view.ChequeraViewResponse;
import ar.com.santanderrio.obp.servicios.chequera.view.CuentaView;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.combos.service.DatosSelectoresService;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaDetalleCuentaOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tenencias.view.ReporteView;

/**
 * The Class PedidoChequeraManagerImpl.
 */
@Component("pedidoChequeraManager")
public class PedidoChequeraManagerImpl implements PedidoChequeraManager {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PedidoChequeraManagerImpl.class);

    /** The pedido chequera BO. */
    @Autowired
    private PedidoChequeraBO pedidoChequeraBO;
    
    /** The cuenta BO. */
    @Autowired
    private CuentaBO cuentaBO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The datos selectores service. */
    @Autowired
    private DatosSelectoresService datosSelectoresService;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The Constant MONEDA_CHEQUERA. */
    private final static String MONEDA_CHEQUERA = "Pesos";

    /** The Constant RESPUESTA_MENSAJE_WARNING. */
    private final static String RESPUESTA_MENSAJE_WARNING = "No pudimos realizar todas tus solicitudes. Debajo podés ver el estado de cada uno de ellos.";

    /** The Constant RESPUESTA_MENSAJE_ERROR. */
    private final static String RESPUESTA_MENSAJE_ERROR = "Mensaje Error";

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.chequera.manager.PedidoChequeraManager#
     * getDatosChequera()
     */
    @Override
    public Respuesta<ChequeraViewResponse> getDatosChequera() {
        LOGGER.info("Inicio de PedidoChequeraManager - getDatosChequera() ");
        Respuesta<ChequeraViewResponse> respuestaView = null;
        Cliente cliente = sesionCliente.getCliente();
        List<Cuenta> cuentas = cliente.getCuentas();
        respuestaView = crearRespuestaDatosChequera(cuentas);
        return respuestaView;
    }

    /**
     * Crear respuesta datos chequera.
     *
     * @param cuentas
     *            the cuentas
     * @return the respuesta
     */
    private Respuesta<ChequeraViewResponse> crearRespuestaDatosChequera(List<Cuenta> cuentas) {
        ChequeraViewResponse chequeraViewResponse = new ChequeraViewResponse();
        chequeraViewResponse
                .setCantidadChequesComunView(dtoToView(datosSelectoresService.obtenerCantidadChequesComun()));
        chequeraViewResponse.setCantidadChequesPagoDiferidoView(
                dtoToViewChequesPagoDiferido(datosSelectoresService.obtenerCantidadChequesPagoDiferido()));
        chequeraViewResponse.setCantidadChequeraComunView(
                dtoToViewChequeraComun(datosSelectoresService.obtenerCantidadChequeraComun()));
        chequeraViewResponse.setCantidadChequeraPagoDiferidoView(
                dtoToViewChequeraPagoDiferido(datosSelectoresService.obtenerCantidadChequeraPagoDiferido()));
        chequeraViewResponse.setCuentas(dtoToViewCuentas(cuentas));
        chequeraViewResponse.setMonedaChequera(MONEDA_CHEQUERA);
        return getRespuestaFactory().crearRespuestaOk(ChequeraViewResponse.class, chequeraViewResponse);
    }

    /**
     * Dto to view.
     *
     * @param lista
     *            the lista
     * @return the list
     */
    private List<String> dtoToView(List<Opcion> lista) {
        List<String> listaRespuesta = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            Opcion opcion = lista.get(i);
            CantidadChequesComunView cantidadChequesComunView = new CantidadChequesComunView();
            cantidadChequesComunView.setCantidad(opcion.getOpcion());
            listaRespuesta.add(opcion.getOpcion());
        }
        return listaRespuesta;
    }

    /**
     * Dto to view cheques pago diferido.
     *
     * @param lista
     *            the lista
     * @return the list
     */
    private List<String> dtoToViewChequesPagoDiferido(List<Opcion> lista) {
        List<String> listaRespuesta = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            Opcion opcion = lista.get(i);
            listaRespuesta.add(opcion.getOpcion());
        }
        return listaRespuesta;
    }

    /**
     * Dto to view chequera comun.
     *
     * @param lista
     *            the lista
     * @return the list
     */
    private List<String> dtoToViewChequeraComun(List<Opcion> lista) {
        List<String> listaRespuesta = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            Opcion opcion = lista.get(i);
            listaRespuesta.add(opcion.getOpcion());
        }
        return listaRespuesta;
    }

    /**
     * Dto to view chequera pago diferido.
     *
     * @param lista
     *            the lista
     * @return the list
     */
    private List<String> dtoToViewChequeraPagoDiferido(List<Opcion> lista) {
        List<String> listaRespuesta = new ArrayList<String>();
        for (int i = 0; i < lista.size(); i++) {
            Opcion opcion = lista.get(i);
            listaRespuesta.add(opcion.getOpcion());
        }
        return listaRespuesta;
    }

    /**
     * Dto to view cuentas.
     *
     * @param cuentas
     *            the cuentas
     * @return the list
     */
    private List<CuentaView> dtoToViewCuentas(List<Cuenta> cuentas) {
        List<CuentaView> cuentasClienteView = new ArrayList<CuentaView>();
        for (Cuenta cuenta : cuentas) {
            if (TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(cuenta.getTipoCuentaEnum())) {
                CuentaView cuentaView = new CuentaView(cuenta);
                cuentasClienteView.add(cuentaView);
            } else if (TipoCuenta.CUENTA_UNICA_PESOS.equals(cuenta.getTipoCuentaEnum())) {
                try {
                    ConsultaDetalleCuentaOutEntity detalleCuenta = cuentaBO.obtenerDetalleCuenta(cuenta);
                    if ("S".equals(detalleCuenta.getIndSobregiro())) {
                        CuentaView cuentaView = new CuentaView(cuenta);
                        cuentasClienteView.add(cuentaView);
                    }
                } catch (DAOException e) {
                    LOGGER.error("No se puede verificar si la cuenta unica tiene Ind_sobregiro = S");
                }
            }
        }
        return cuentasClienteView;
    }

    /**
     * Gets the respuesta factory.
     *
     * @return the respuesta factory
     */
    public RespuestaFactory getRespuestaFactory() {
        return respuestaFactory;
    }

    /**
     * Gets the sesion cliente.
     *
     * @return the sesionCliente
     */
    public SesionCliente getSesionCliente() {
        return sesionCliente;
    }

    /**
     * Sets the sesion cliente.
     *
     * @param sesionCliente
     *            the sesionCliente to set
     */
    public void setSesionCliente(SesionCliente sesionCliente) {
        this.sesionCliente = sesionCliente;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.chequera.manager.PedidoChequeraManager#
     * confirmarPedidoChequera(ar.com.santanderrio.obp.servicios.chequera.view.
     * ChequeraConfirmacionInView)
     */
    @Override
    public Respuesta<ChequeraConfirmacionViewResponse> confirmarPedidoChequera(ChequeraConfirmacionInView viewRequest) {
        /* Aca deberia parsear la cuenta. */
        LOGGER.info("Inicio de PedidoChequeraManager - confirmarPedidoChequera() ");
        Respuesta<ChequeraConfirmacionViewResponse> respuestaChequeraConfirmacionResponse = new Respuesta<ChequeraConfirmacionViewResponse>();

        Respuesta<List<Respuesta<ChequeraOutDTO>>> respuestaDTO = pedidoChequeraBO
                .confirmarPedidoChequera(createInDTO(viewRequest));

        armarDetalleView(respuestaDTO, respuestaChequeraConfirmacionResponse, viewRequest);

        return respuestaChequeraConfirmacionResponse;
    }

    /**
     * Armar detalle view.
     *
     * @param respuestaDTO
     *            the respuesta DTO
     * @param respuestaChequeraConfirmacionResponse
     *            the respuesta chequera confirmacion response
     * @param viewRequest
     *            the view request
     */
    private void armarDetalleView(Respuesta<List<Respuesta<ChequeraOutDTO>>> respuestaDTO,
            Respuesta<ChequeraConfirmacionViewResponse> respuestaChequeraConfirmacionResponse,
            ChequeraConfirmacionInView viewRequest) {

        List<Respuesta<ChequeraOutDTO>> listaChequeraDTO = respuestaDTO.getRespuesta();
        ChequeraConfirmacionViewResponse chequeraConfirmacionViewResponse = new ChequeraConfirmacionViewResponse();
        if (respuestaDTO.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
            chequeraConfirmacionViewResponse.setSucursalEntrega(ISBANStringUtils
                    .formatearFraseInicialMayuscula(listaChequeraDTO.get(0).getRespuesta().getSucursalEntrega()));
            chequeraConfirmacionViewResponse.setDomicilioSucursal(ISBANStringUtils
                    .formatearFraseInicialMayuscula(listaChequeraDTO.get(0).getRespuesta().getDomicilioSucursal()));
            chequeraConfirmacionViewResponse.setLocalidadSucursal(ISBANStringUtils
                    .formatearFraseInicialMayuscula(listaChequeraDTO.get(0).getRespuesta().getLocalidadSucursal()));
            chequeraConfirmacionViewResponse.setChequeras(armarDetalleChequerasView(listaChequeraDTO, viewRequest));
            chequeraConfirmacionViewResponse.setMoneda(MONEDA_CHEQUERA);
            chequeraConfirmacionViewResponse.setFechaHora(ISBANStringUtils.formatearFecha(new Date()));
        } else if (respuestaDTO.getEstadoRespuesta().equals(EstadoRespuesta.WARNING)) {
            for (Respuesta<ChequeraOutDTO> e : listaChequeraDTO) {
                if (e.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
                    chequeraConfirmacionViewResponse.setSucursalEntrega(
                            ISBANStringUtils.formatearFraseInicialMayuscula(e.getRespuesta().getSucursalEntrega()));
                    chequeraConfirmacionViewResponse.setDomicilioSucursal(
                            ISBANStringUtils.formatearFraseInicialMayuscula(e.getRespuesta().getDomicilioSucursal()));
                    chequeraConfirmacionViewResponse.setLocalidadSucursal(
                            ISBANStringUtils.formatearFraseInicialMayuscula(e.getRespuesta().getLocalidadSucursal()));
                    chequeraConfirmacionViewResponse.setMoneda(MONEDA_CHEQUERA);
                } else {
                    respuestaChequeraConfirmacionResponse.setItemMensajeRespuesta(e.getItemsMensajeRespuesta());
                }
            }

            chequeraConfirmacionViewResponse.setChequeras(armarDetalleChequerasView(listaChequeraDTO, viewRequest));
        } else {
            for (Respuesta<ChequeraOutDTO> e : listaChequeraDTO) {
                respuestaChequeraConfirmacionResponse.setItemMensajeRespuesta(e.getItemsMensajeRespuesta());
            }
        }

        armarMensajeRespuesta(chequeraConfirmacionViewResponse, respuestaDTO.getEstadoRespuesta());

        respuestaChequeraConfirmacionResponse.setEstadoRespuesta(respuestaDTO.getEstadoRespuesta());
        respuestaChequeraConfirmacionResponse.setRespuesta(chequeraConfirmacionViewResponse);
    }

    /**
     * Armar mensaje respuesta.
     *
     * @param chequeraConfirmacionViewResponse
     *            the chequera confirmacion view response
     * @param estadoRespuesta
     *            the estado respuesta
     */
    private void armarMensajeRespuesta(ChequeraConfirmacionViewResponse chequeraConfirmacionViewResponse,
            EstadoRespuesta estadoRespuesta) {
        StringBuilder mensaje = new StringBuilder();

        switch (estadoRespuesta) {
        case OK:
            obtenerMensajeOk(chequeraConfirmacionViewResponse, mensaje);
            break;
        case WARNING:
            mensaje.append(RESPUESTA_MENSAJE_WARNING);
            break;
        default:
            mensaje.append(RESPUESTA_MENSAJE_ERROR);
            break;
        }

        chequeraConfirmacionViewResponse.setMensaje(mensaje.toString());
    }

    /**
     * Obtener mensaje ok.
     *
     * @param chequeraConfirmacionViewResponse
     *            the chequera confirmacion view response
     * @param mensaje
     *            the mensaje
     */
    private void obtenerMensajeOk(ChequeraConfirmacionViewResponse chequeraConfirmacionViewResponse,
            StringBuilder mensaje) {
        for (int i = 0; i < chequeraConfirmacionViewResponse.getChequeras().size(); i++) {
            if (i == 0) {
                mensaje.append("La solicitud de ")
                        .append(chequeraConfirmacionViewResponse.getChequeras().get(i).getCantidadChequera())
                        .append(" chequera/s de <strong>")
                        .append(chequeraConfirmacionViewResponse.getChequeras().get(i).getCantidadCheque())
                        .append(" cheques </strong>")
                        .append("00".equals(chequeraConfirmacionViewResponse.getChequeras().get(i).getTipoChequera())
                                ? "<strong>comunes</strong>" : "de <strong>pago diferido</strong>")
                        .append(" en <strong>pesos</strong>");
            } else {
                mensaje.append(" y ")
                        .append(chequeraConfirmacionViewResponse.getChequeras().get(i).getCantidadChequera())
                        .append(" chequera/s de <strong>")
                        .append(chequeraConfirmacionViewResponse.getChequeras().get(i).getCantidadCheque())
                        .append(" cheques </strong>")
                        .append("00".equals(chequeraConfirmacionViewResponse.getChequeras().get(i).getTipoChequera())
                                ? "<strong>comunes</strong> " : "de <strong>pago diferido</strong>")
                        .append(" en <strong>pesos</strong>");
            }
        }
        mensaje.append(" se realizó con éxito.");
    }

    /**
     * Armar detalle chequeras view.
     *
     * @param listaChequeraDTO
     *            the lista chequera DTO
     * @param viewRequest
     *            the view request
     * @return the list
     */
    private List<ChequeraConfirmacionView> armarDetalleChequerasView(List<Respuesta<ChequeraOutDTO>> listaChequeraDTO,
            ChequeraConfirmacionInView viewRequest) {
        List<ChequeraConfirmacionView> chequeras = new ArrayList<ChequeraConfirmacionView>();

        for (Respuesta<ChequeraOutDTO> e : listaChequeraDTO) {
            if (e.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
                ChequeraConfirmacionView chequeraConfirmacion = new ChequeraConfirmacionView();
                chequeraConfirmacion.setNumeroComprobante(e.getRespuesta().getNumeroComprobante());
                chequeraConfirmacion.setNumeroCuenta(viewRequest.getCuentaSeleccionada().getNumero());
                chequeraConfirmacion.setCantidadChequera(e.getRespuesta().getCantidadChequera());
                chequeraConfirmacion.setCantidadCheque(e.getRespuesta().getCantidadCheque());
                chequeraConfirmacion.setTipoChequera(e.getRespuesta().getTipoChequera());
                chequeraConfirmacion.setTipoCuenta(viewRequest.getCuentaSeleccionada().getDescripcionTipoCuenta());
                chequeraConfirmacion.setPedidoChequeraOk(true);
                chequeras.add(chequeraConfirmacion);

            }
        }
        return chequeras;
    }

    /**
     * Creates the in DTO.
     *
     * @param viewRequest
     *            the view request
     * @return the chequera in DTO
     */
    private ChequeraInDTO createInDTO(ChequeraConfirmacionInView viewRequest) {
        ChequeraInDTO inDTO = new ChequeraInDTO();
        inDTO.setCuenta(viewRequest.getCuentaSeleccionada().getNumero());
        inDTO.setCantidadChequeComun(viewRequest.getCantidadChequesComun());
        inDTO.setCantidadChequePagoDiferido(viewRequest.getCantidadChequesPagoDiferido());
        inDTO.setCantidadChequeraComun(viewRequest.getCantidadChequeraComun());
        inDTO.setCantidadChequeraPagoDiferido(viewRequest.getCantidadChequeraPagoDiferido());
        inDTO.setCliente(sesionCliente.getCliente());
        inDTO.setPedidoChequeraComun(viewRequest.isPedidoChequeraComun());
        inDTO.setPedidoChequeraPagoDiferido(viewRequest.isPedidoChequeraPagoDiferido());
        return inDTO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.chequera.manager.PedidoChequeraManager#
     * generarComprobanteChequera(ar.com.santanderrio.obp.servicios.chequera.
     * view.ChequeraConfirmacionViewResponse)
     */
    @Override
    public Respuesta<ReporteView> generarComprobanteChequera(
            ChequeraConfirmacionViewResponse chequeraConfirmacionView) {

        Respuesta<Reporte> reporteRespuesta = pedidoChequeraBO.generarComprobanteChequera(chequeraConfirmacionView);
        Respuesta<ReporteView> respuestaView = Respuesta.copy(ReporteView.class, reporteRespuesta);
        if (reporteRespuesta.getRespuesta() != null) {
            ReporteView reporteView = ReporteView.fromReporte(reporteRespuesta.getRespuesta());
            respuestaView.setRespuesta(reporteView);
        }
        estadisticaManager.add(EstadisticasConstants.DESCARGA_COMPROBANTE_PDF,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaView;
    }
}
