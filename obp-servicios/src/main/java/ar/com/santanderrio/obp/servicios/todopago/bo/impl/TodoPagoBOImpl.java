/*
 * 
 */
package ar.com.santanderrio.obp.servicios.todopago.bo.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.billetera.web.util.BilleteraUtils;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.clientes.entities.TipoIdentificacion;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.mail.OBPMailSender;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.todopago.bo.TodoPagoBO;
import ar.com.santanderrio.obp.servicios.todopago.dao.SolicitudAdhesionTodoPagoDAO;
import ar.com.santanderrio.obp.servicios.todopago.dto.AdhesionTodoPagoEnvioMailDTO;
import ar.com.santanderrio.obp.servicios.todopago.dto.AdhesionTodoPagoInDTO;
import ar.com.santanderrio.obp.servicios.todopago.dto.SolicitudTodoPagoDTO;
import ar.com.santanderrio.obp.servicios.todopago.entity.AdhesionTodoPagoInEntity;
import ar.com.santanderrio.obp.servicios.todopago.web.view.ComprobanteAdhesionTodoPagoView;
import ar.com.santanderrio.obp.servicios.todopago.web.view.TodoPagoView;

/**
 * The Class TodoPagoBOImpl.
 *
 */
@Component("todoPagoBO")
public class TodoPagoBOImpl implements TodoPagoBO {

    /** The solicitud adhesion TodoPago DAO. */
    @Autowired
    private SolicitudAdhesionTodoPagoDAO solicitudAdhesionTodoPagoDAO;

    /** The obp mail sender. */
    @Autowired
    private OBPMailSender obpMailSender;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The nup dao. */
    @Autowired
    private NUPDAO nupDao;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The tipos cta. */
    @Value("${BILLETERA.TIPOSCTA}")
    private String tiposCta;

    /** Formato de Fecha *. */
    private static final String FORMATO_FECHA = "yyyyMMdd";

    /**
     * Gets the cuentas acreditacion.
     *
     * @return the cuentas acreditacion
     */
    private List<Cuenta> getCuentasAcreditacion() {
        return BilleteraUtils.getCtas(sesionCliente.getCliente(), Arrays.asList(tiposCta.split("\\|")));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.todopago.bo.TodoPagoBO#adhesionTodoPago
     * (ar.com.santanderrio.obp.servicios.todopago.web.view.TodoPagoView)
     */
    public Respuesta<SolicitudTodoPagoDTO> adhesionTodoPago(TodoPagoView view) throws DAOException {

        SolicitudTodoPagoDTO solicitudTodoPagoDTO;

        if (!getCuentasAcreditacion().isEmpty()) {
            solicitudTodoPagoDTO = prcDatosFormulario(sesionCliente.getCliente(), sesionParametros.getRegistroSession());
        } else {
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_TODOPAGO_SIN_CUENTAS_OPERATIVAS,
                    CodigoMensajeConstantes.TODOPAGO_ADHESION_ERROR_CUENTAS);
        }

        Respuesta<SolicitudTodoPagoDTO> respuesta = new Respuesta<SolicitudTodoPagoDTO>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(solicitudTodoPagoDTO);
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.todopago.bo.TodoPagoBO#enviarMail(ar.
     * com.santanderrio.obp.servicios.todopago.dto.AdhesionTodoPagoEnvioMailDTO)
     */
    @Override
    public Respuesta<Boolean> enviarMail(AdhesionTodoPagoEnvioMailDTO envioMailDTO) {

        try {
            obpMailSender.sendMail(envioMailDTO.getMailFrom(), envioMailDTO.getMailTo(), "", envioMailDTO.getMailBcc(),
                    "", envioMailDTO.getMailSubject(), envioMailDTO.getMailBody());
        } catch (Exception ex) {
            return respuestaFactory.crearRespuestaError(Boolean.class, ex.getMessage(), null);
        }

        return respuestaFactory.crearRespuestaOk(Boolean.class, Boolean.TRUE);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.todopago.bo.TodoPagoBO#altaAdhesion(ar.
     * com.santanderrio.obp.servicios.todopago.dto.AdhesionTodoPagoInDTO)
     */
    @Override
    public Respuesta<ResultadoTransaccion> altaAdhesion(AdhesionTodoPagoInDTO inDto) {
        Respuesta<ResultadoTransaccion> respuestaDao;
        AdhesionTodoPagoInEntity inEntity = new AdhesionTodoPagoInEntity();
        BeanUtils.copyProperties(inDto, inEntity);

        try {
            respuestaDao = solicitudAdhesionTodoPagoDAO.grabarSolicitud(inEntity);
            if (respuestaDao.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
                ComprobanteAdhesionTodoPagoView comprobante = new ComprobanteAdhesionTodoPagoView();
                BeanUtils.copyProperties(inDto, comprobante);
                sesionParametros.setComprobanteAdhesionTodoPagoView(comprobante);
                return respuestaFactory.crearRespuestaOk(ResultadoTransaccion.class, respuestaDao.getRespuesta());
            } else {
                if (respuestaDao.getRespuesta() != null) {
                    return respuestaFactory.crearRespuestaError(ResultadoTransaccion.class,
                            respuestaDao.getRespuesta().getCodigoRespuesta().toString(), null);
                } else {
                    return respuestaFactory.crearRespuestaError(ResultadoTransaccion.class, null);
                }
            }
        } catch (DAOException ex) {
            return respuestaFactory.crearRespuestaError(ResultadoTransaccion.class, ex.getErrorCode(), null);
        }
    }

    /**
     * Prc datos formulario.
     *
     * @param clienteAdhesion
     *            the cliente adhesion
     * @param registroSesion
     *            the registro sesion
     * @return the solicitud TodoPago DTO
     * @throws DAOException
     *             the DAO exception
     */
    private SolicitudTodoPagoDTO prcDatosFormulario(Cliente clienteAdhesion, RegistroSesion registroSesion)
            throws DAOException {

        SolicitudTodoPagoDTO solicitudTodoPagoDTO = new SolicitudTodoPagoDTO();
        solicitudTodoPagoDTO.setApellido(clienteAdhesion.getApellido1() + " " + clienteAdhesion.getApellido2());
        solicitudTodoPagoDTO.setApellidoContacto(clienteAdhesion.getApellido1() + " " + clienteAdhesion.getApellido2());
        String cuil = obtenerCuil();
        solicitudTodoPagoDTO.setCuitDni(ISBANStringUtils.formatearCuil(cuil));
        solicitudTodoPagoDTO.setFechaNacimiento(
                FechaUtils.parsearFechaNacimiento(registroSesion.getFechaNacimiento(), FORMATO_FECHA));
        solicitudTodoPagoDTO.setNombre(clienteAdhesion.getNombre());
        solicitudTodoPagoDTO.setNombreContacto(clienteAdhesion.getNombre());
        solicitudTodoPagoDTO.setNumeroDocumento(
                ISBANStringUtils.formatearDocumento(ISBANStringUtils.eliminarCeros(clienteAdhesion.getDni())));
        solicitudTodoPagoDTO.setNup(clienteAdhesion.getNup());
        TipoIdentificacion ti = TipoIdentificacion.fromKey(clienteAdhesion.getTipoDocumento());
        solicitudTodoPagoDTO.setTipoDocumento(ti.getDescripcion());

        return solicitudTodoPagoDTO;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.todopago.bo.TodoPagoBO#obtenerCuil()
     */
    @Override
    public String obtenerCuil() throws DAOException {
        NupDTO nupDTO = nupDao.obtenerNUP(sesionCliente.getCliente());
        String[] tiposDoc = { NupDTO.TIPO_DOC_CUIT, NupDTO.TIPO_DOC_CUIL, NupDTO.TIPO_DOC_CDI };
        return nupDTO.getCuit(tiposDoc);
    }

    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.todopago.bo.TodoPagoBO#
     * generarComprobante(ar.com.santanderrio.obp.servicios.todopago.web.view.
     * ComprobanteAdhesionTodoPagoView)
     */
    @Override
    public Respuesta<Reporte> generarComprobante(ComprobanteAdhesionTodoPagoView view) {

        Reporte reporte = null;
        try {
            reporte = solicitudAdhesionTodoPagoDAO.generarComprobante(view);
        } catch (DAOException e) {
            return respuestaFactory.crearRespuestaError(null);
        }

        Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(reporte);
        return respuesta;
    }

}