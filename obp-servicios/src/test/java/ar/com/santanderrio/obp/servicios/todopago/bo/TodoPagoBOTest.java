package ar.com.santanderrio.obp.servicios.todopago.bo;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.mail.OBPMailSender;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;
import ar.com.santanderrio.obp.servicios.todopago.bo.impl.TodoPagoBOImpl;
import ar.com.santanderrio.obp.servicios.todopago.dao.SolicitudAdhesionTodoPagoDAO;
import ar.com.santanderrio.obp.servicios.todopago.dto.AdhesionTodoPagoEnvioMailDTO;
import ar.com.santanderrio.obp.servicios.todopago.dto.AdhesionTodoPagoInDTO;
import ar.com.santanderrio.obp.servicios.todopago.dto.SolicitudTodoPagoDTO;
import ar.com.santanderrio.obp.servicios.todopago.entity.AdhesionTodoPagoInEntity;
import ar.com.santanderrio.obp.servicios.todopago.web.view.TodoPagoView;

/**
 * The Class TodoPagoBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class TodoPagoBOTest {

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The nup dao. */
    @Mock
    private NUPDAO nupDao;

    /** The obp mail sender. */
    @Mock
    private OBPMailSender obpMailSender;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The solicitud adhesion TodoPago DAO. */
    @Mock
    private SolicitudAdhesionTodoPagoDAO solicitudAdhesionTodoPagoDAO;

    /** The TodoPago bo impl. */
    @InjectMocks
    private TodoPagoBOImpl todoPagoBOImpl = new TodoPagoBOImpl();

    /** The nup DTO. */
    private NupDTO nupDTO;

    /** The registro session. */
    private RegistroSesion registroSession;

    /**
     * Init.
     */
    @Before
    public void init() {

        registroSession = new RegistroSesion();
        registroSession.setFechaNacimiento("19700101");

        nupDTO = new NupDTO();
        DetalleDocumentoDTO detalleDocumentoDTO = new DetalleDocumentoDTO();
        detalleDocumentoDTO.setTipoDocumento(NupDTO.TIPO_DOC_CUIL);
        detalleDocumentoDTO.setNroDocumento("12345678");
        nupDTO.getDetalleDocumento().put(detalleDocumentoDTO.getTipoDocumento(), detalleDocumentoDTO);
    }

    /**
     * Adhesion TodoPago sin cuenta test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void adhesionTodoPagoSinCuentaTest() throws DAOException, IllegalAccessException {

        TodoPagoView todoPagoView = new TodoPagoView();

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = CuentaMock.completarInfoCuenta("000001234567", "0250", TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta1.setTipoCuenta("01");
        cuentas.add(cuenta1);
        Cliente cliente = ClienteMock.obtenerClienteConCuentas(cuentas);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSession);
        when(nupDao.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(nupDTO);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        FieldUtils.writeDeclaredField(todoPagoBOImpl, "tiposCta", "00|01|02|03|04|09|10", true);
        Respuesta<SolicitudTodoPagoDTO> respuesta = todoPagoBOImpl.adhesionTodoPago(todoPagoView);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        Assert.assertEquals(TipoError.ERROR_TODOPAGO_SIN_CUENTAS_OPERATIVAS.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Adhesion TodoPago OK test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Test
    public void adhesionTodoPagoOKTest() throws DAOException, IllegalAccessException {

        TodoPagoView todoPagoView = new TodoPagoView();

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = CuentaMock.completarInfoCuenta("000001234567", "0370", TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta1.setTipoCuenta("01");
        Cuenta cuenta2 = CuentaMock.completarInfoCuenta("000001234577", "0000", TipoCuenta.CUENTA_UNICA);
        cuenta2.setTipoCuenta("02");
        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        Cliente cliente = ClienteMock.obtenerClienteConCuentas(cuentas);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getRegistroSession()).thenReturn(registroSession);
        when(nupDao.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(nupDTO);
        FieldUtils.writeDeclaredField(todoPagoBOImpl, "tiposCta", "00|01|02|03|04|09|10", true);
        Respuesta<SolicitudTodoPagoDTO> respuesta = todoPagoBOImpl.adhesionTodoPago(todoPagoView);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Alta adhesion error test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void altaAdhesionErrorTest() throws DAOException {

        AdhesionTodoPagoInDTO inDTO = obtenerAdhesionDTO();

        Respuesta<ResultadoTransaccion> respuestaDao = new Respuesta<ResultadoTransaccion>();
        respuestaDao.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.doThrow(DAOException.class).when(solicitudAdhesionTodoPagoDAO)
                .grabarSolicitud(Matchers.any(AdhesionTodoPagoInEntity.class));
        Respuesta<ResultadoTransaccion> respuesta = todoPagoBOImpl.altaAdhesion(inDTO);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Alta adhesion OK test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void altaAdhesionOKTest() throws DAOException {

        AdhesionTodoPagoInDTO inDTO = obtenerAdhesionDTO();

        Respuesta<ResultadoTransaccion> respuestaDao = new Respuesta<ResultadoTransaccion>();
        respuestaDao.setEstadoRespuesta(EstadoRespuesta.OK);

        when(solicitudAdhesionTodoPagoDAO.grabarSolicitud(Matchers.any(AdhesionTodoPagoInEntity.class)))
                .thenReturn(respuestaDao);
        Respuesta<ResultadoTransaccion> respuesta = todoPagoBOImpl.altaAdhesion(inDTO);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener adhesion DTO.
     *
     * @return the adhesion TodoPago in DTO
     */
    private AdhesionTodoPagoInDTO obtenerAdhesionDTO() {
        AdhesionTodoPagoInDTO inDTO = new AdhesionTodoPagoInDTO();
        inDTO.setActividad("Actividad");
        inDTO.setApellido("Apellido");
        inDTO.setApellidoContacto("ApellidoC");
        inDTO.setCbu("1234567890123456789012");
        inDTO.setCelular("1234567890");
        inDTO.setCondicionIIBB("Exento");
        inDTO.setCondicionIVA("Exento");
        inDTO.setCuitDni("20123456789");
        inDTO.setEmail("isban@isbanexternos.com.ar");
        inDTO.setEmpresaCelular("Movistar");
        inDTO.setEnvioMailSatisfactorio("S");
        inDTO.setFechaNacimiento(new Date());
        inDTO.setFechaSolicitud(new Date());
        inDTO.setNombre("Nombre");
        inDTO.setNombreContacto("NombreC");
        inDTO.setNumeroCuenta("0");
        inDTO.setNumeroDocumento("12345678");
        inDTO.setNup("12345678");
        inDTO.setPaisOrigen("Argentina");
        inDTO.setRazonSocial("Razon Social");
        inDTO.setSexo("M");
        inDTO.setTelefonoFijo("99999999");
        inDTO.setTipoDocumento("N");
        return inDTO;
    }

    /**
     * Enviar mail error test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws MessagingException
     *             the messaging exception
     */
    @Test
    public void enviarMailErrorTest() throws DAOException, MessagingException {

        AdhesionTodoPagoEnvioMailDTO inDTO = new AdhesionTodoPagoEnvioMailDTO();
        inDTO.setMailBcc("");
        inDTO.setMailBody("");
        inDTO.setMailFrom("");
        inDTO.setMailSubject("");
        inDTO.setMailTo("");

        Mockito.doThrow(MessagingException.class).when(obpMailSender).sendMail(Matchers.anyString(),
                Matchers.anyString(), Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
                Matchers.anyString(), Matchers.anyString());
        Respuesta<Boolean> respuesta = todoPagoBOImpl.enviarMail(inDTO);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Enviar mail OK test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws MessagingException
     *             the messaging exception
     */
    @Test
    public void enviarMailOKTest() throws DAOException, MessagingException {

        AdhesionTodoPagoEnvioMailDTO inDTO = new AdhesionTodoPagoEnvioMailDTO();
        inDTO.setMailBcc("");
        inDTO.setMailBody("");
        inDTO.setMailFrom("");
        inDTO.setMailSubject("");
        inDTO.setMailTo("");

        Mockito.doNothing().when(obpMailSender).sendMail(Matchers.anyString(), Matchers.anyString(),
                Matchers.anyString(), Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
                Matchers.anyString());
        Respuesta<Boolean> respuesta = todoPagoBOImpl.enviarMail(inDTO);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
}
