package ar.com.santanderrio.obp.servicios.todopago.manager;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mail.OBPMailSender;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.bo.CambioDomicilioBO;
import ar.com.santanderrio.obp.servicios.perfil.dto.CambioDomicilioDTO;
import ar.com.santanderrio.obp.servicios.perfil.dto.ProductoDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;
import ar.com.santanderrio.obp.servicios.todopago.bo.TodoPagoBO;
import ar.com.santanderrio.obp.servicios.todopago.dao.SolicitudAdhesionTodoPagoDAO;
import ar.com.santanderrio.obp.servicios.todopago.dto.AdhesionTodoPagoEnvioMailDTO;
import ar.com.santanderrio.obp.servicios.todopago.dto.AdhesionTodoPagoInDTO;
import ar.com.santanderrio.obp.servicios.todopago.dto.SolicitudTodoPagoDTO;
import ar.com.santanderrio.obp.servicios.todopago.manager.impl.TodoPagoManagerImpl;
import ar.com.santanderrio.obp.servicios.todopago.web.utils.TodoPagoHelper;
import ar.com.santanderrio.obp.servicios.todopago.web.view.AdhesionRespuestaView;
import ar.com.santanderrio.obp.servicios.todopago.web.view.ComprobanteAdhesionTodoPagoView;
import ar.com.santanderrio.obp.servicios.todopago.web.view.TodoPagoView;

/**
 * The Class TodoPagoManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class TodoPagoManagerTest {

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The mensaje manager. */
    @Mock
    private MensajeManager mensajeManager;

    /** The nup dao. */
    @Mock
    private NUPDAO nupDao;

    /** The obp mail sender. */
    @Mock
    private OBPMailSender obpMailSender;

    /** The TodoPago BO. */
    @Mock
    private TodoPagoBO todoPagoBO;

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

    /** The TodoPago manager impl. */
    @InjectMocks
    private TodoPagoManagerImpl todoPagoManagerImpl = new TodoPagoManagerImpl();

    /** The cambio domicilio BO. */
    @Mock
    private CambioDomicilioBO cambioDomicilioBO;

    /** The selectores BO. */
    @Mock
    private DatosSelectoresBO selectoresBO;

    /** The todo pago helper. */
    @Mock
    private TodoPagoHelper todoPagoHelper;

    /** The cliente. */
    Cliente cliente;

    /** The registro session. */
    private RegistroSesion registroSession;

    /** The condiciones IIBB. */
    List<Opcion> condicionesIIBB = new ArrayList<Opcion>();

    /** The condiciones IVA. */
    List<Opcion> condicionesIVA = new ArrayList<Opcion>();

    /** The domicilios DTO. */
    List<CambioDomicilioDTO> domiciliosDTO = new ArrayList<CambioDomicilioDTO>();

    /** The sexo completo. */
    List<Opcion> sexoCompleto = new ArrayList<Opcion>();

    /**
     * Init.
     */
    @Before
    public void init() {
        CambioDomicilioDTO cdDTO = new CambioDomicilioDTO();
        cdDTO.setDomicilioId("99999999");
        cdDTO.setApt("1234");
        cdDTO.setCalle("Calle");
        cdDTO.setCodigoPostal("1234");
        cdDTO.setComuna("");
        cdDTO.setPiso("1");
        cdDTO.setDepartamento("A");
        cdDTO.setLocalidad("Avellaneda");
        cdDTO.setMarcaDomErroneo("");
        cdDTO.setObservaciones("");
        cdDTO.setPais("02");
        cdDTO.setSecuenciaDomicilio("001");
        cdDTO.setSucursal("");
        cdDTO.setProvincia("08");
        cdDTO.setTelefono("34567");
        cdDTO.setPrefijo("011");
        cdDTO.setCaracteristica("22");
        cdDTO.setNumeroTelefono("344543634543");
        cdDTO.setTipoDomicilio("PRI");
        cdDTO.setDescProvincia("Buenos Aires");
        cdDTO.setDescPais("Argentina");
        cdDTO.setListaProductos(new ArrayList<ProductoDTO>());
        domiciliosDTO.add(cdDTO);

        Opcion condicionIIBB1 = new Opcion();
        condicionIIBB1.setOpcion("Régimen simplificado");
        condicionIIBB1.setId("1");
        Opcion condicionIIBB2 = new Opcion();
        condicionIIBB2.setOpcion("Exento");
        condicionIIBB2.setId("2");
        Opcion condicionIIBB3 = new Opcion();
        condicionIIBB3.setOpcion("Contribuyente Local");
        condicionIIBB3.setId("3");
        Opcion condicionIIBB4 = new Opcion();
        condicionIIBB4.setOpcion("Convenio Multilateral");
        condicionIIBB4.setId("4");
        condicionesIIBB.add(condicionIIBB1);
        condicionesIIBB.add(condicionIIBB2);
        condicionesIIBB.add(condicionIIBB3);
        condicionesIIBB.add(condicionIIBB4);

        Opcion condicionIVA1 = new Opcion();
        condicionIVA1.setOpcion("Activo");
        condicionIVA1.setId("1");
        Opcion condicionIVA2 = new Opcion();
        condicionIVA2.setOpcion("Exento");
        condicionIVA2.setId("2");
        Opcion condicionIVA3 = new Opcion();
        condicionIVA3.setOpcion("Monotributo");
        condicionIVA3.setId("3");
        Opcion condicionIVA4 = new Opcion();
        condicionIVA4.setOpcion("No alcanzado");
        condicionIVA4.setId("4");
        Opcion condicionIVA5 = new Opcion();
        condicionIVA5.setOpcion("No inscripto");
        condicionIVA5.setId("5");
        Opcion condicionIVA6 = new Opcion();
        condicionIVA6.setOpcion("Consumidor Final");
        condicionIVA6.setId("6");
        condicionesIVA.add(condicionIVA1);
        condicionesIVA.add(condicionIVA2);
        condicionesIVA.add(condicionIVA3);
        condicionesIVA.add(condicionIVA4);
        condicionesIVA.add(condicionIVA5);
        condicionesIVA.add(condicionIVA6);

        registroSession = new RegistroSesion();
        registroSession.setFechaNacimiento("19700101");

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = CuentaMock.completarInfoCuenta("000001234567", "0370", TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta1.setTipoCuenta("01");
        cuenta1.setSaldoCuenta("2000.20");
        Cuenta cuenta2 = CuentaMock.completarInfoCuenta("000001234577", "0000", TipoCuenta.CUENTA_UNICA);
        cuenta2.setTipoCuenta("02");
        cuenta2.setSaldoCUPesos("8000.00");
        cuentas.add(cuenta1);
        cuentas.add(cuenta2);
        cliente = ClienteMock.obtenerClienteConCuentas(cuentas);
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
    public void adhesionTodoPagoSinCuentaTest() throws DAOException, IllegalAccessException {

        TodoPagoView todoPagoView = new TodoPagoView();

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = CuentaMock.completarInfoCuenta("000001234567", "0250", TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta1.setTipoCuenta("01");
        cuentas.add(cuenta1);
        Cliente clienteSinCtaHab = ClienteMock.obtenerClienteConCuentas(cuentas);

        Respuesta<SolicitudTodoPagoDTO> respuestaBO = new Respuesta<SolicitudTodoPagoDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);

        when(todoPagoBO.adhesionTodoPago(Matchers.any(TodoPagoView.class))).thenReturn(respuestaBO);
        when(sesionCliente.getCliente()).thenReturn(clienteSinCtaHab);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<TodoPagoView> respuesta = todoPagoManagerImpl.adhesionTodoPago(todoPagoView);

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

        Respuesta<SolicitudTodoPagoDTO> respuestaBO = new Respuesta<SolicitudTodoPagoDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBO.setRespuesta(obtenerRespuestaAdhesion());

        Respuesta<List<CambioDomicilioDTO>> respuestaDomicilioBO = new Respuesta<List<CambioDomicilioDTO>>();
        respuestaDomicilioBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaDomicilioBO.setRespuesta(domiciliosDTO);

        when(todoPagoBO.adhesionTodoPago(Matchers.any(TodoPagoView.class))).thenReturn(respuestaBO);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cambioDomicilioBO.consultarDomiciliosRegistrados()).thenReturn(respuestaDomicilioBO);
        when(selectoresBO.obtenerCondicionIVA()).thenReturn(condicionesIVA);
        when(selectoresBO.obtenerCondicionIIBB()).thenReturn(condicionesIIBB);
        when(selectoresBO.obtenerSexoCompleto()).thenReturn(sexoCompleto);
        FieldUtils.writeDeclaredField(todoPagoManagerImpl, "tiposCta", "00|01|02|03|04|09|10", true);
        FieldUtils.writeDeclaredField(todoPagoManagerImpl, "tycUrl",
                "https://www.santanderrio.com.ar/banco/online/landings/terminos-y-condiciones-billetera", true);
        Respuesta<TodoPagoView> respuesta = todoPagoManagerImpl.adhesionTodoPago(todoPagoView);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener respuesta adhesion.
     *
     * @return the solicitud TodoPago DTO
     */
    private SolicitudTodoPagoDTO obtenerRespuestaAdhesion() {
        SolicitudTodoPagoDTO dto = new SolicitudTodoPagoDTO();
        dto.setNombre("Nombre");
        dto.setApellido("Apellido");
        dto.setNumeroDocumento("12345678");
        dto.setCuitDni("20123456789");
        dto.setFechaNacimiento(new Date());
        return dto;
    }

    /**
     * Confirmar adhesion error test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     */
    @Test
    public void confirmarAdhesionErrorTest() throws DAOException, IllegalAccessException {

        TodoPagoView todoPagoView = obtenerTodoPagoView();

        Respuesta<Boolean> respuestaEnvioMailBO = new Respuesta<Boolean>();
        respuestaEnvioMailBO.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<ResultadoTransaccion> respuestaBO = new Respuesta<ResultadoTransaccion>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(todoPagoHelper.crearAdhesionTodoPagoDTO(Matchers.any(TodoPagoView.class)))
                .thenReturn(new AdhesionTodoPagoInDTO());
        when(todoPagoBO.enviarMail(Matchers.any(AdhesionTodoPagoEnvioMailDTO.class))).thenReturn(respuestaEnvioMailBO);
        when(todoPagoBO.altaAdhesion(Matchers.any(AdhesionTodoPagoInDTO.class))).thenReturn(respuestaBO);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        FieldUtils.writeDeclaredField(todoPagoManagerImpl, "tiposCta", "00|01|02|03|04|09|10", true);
        Respuesta<AdhesionRespuestaView> respuesta = todoPagoManagerImpl.confirmarAdhesion(todoPagoView);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener TodoPago view.
     *
     * @return the TodoPago view
     */
    private TodoPagoView obtenerTodoPagoView() {
        TodoPagoView todoPagoView = new TodoPagoView();
        todoPagoView.setActividad("Actividad");
        todoPagoView.setApellido("Apellido");
        todoPagoView.setApellidoContacto("ApellidoC");
        todoPagoView.setCbu("1234567890123456789012");
        todoPagoView.setCelular("1234567890");
        todoPagoView.setCondicionIIBB("Exento");
        todoPagoView.setCondicionIVA("Exento");
        todoPagoView.setCuitDni("20123456789");
        todoPagoView.setEmail("isban@isbanexternos.com.ar");
        todoPagoView.setEmpresaCelular("Movistar");
        todoPagoView.setEnvioMailSatisfactorio("S");
        todoPagoView.setFechaNacimiento(new Date());
        todoPagoView.setFechaSolicitud(new Date());
        todoPagoView.setNombre("Nombre");
        todoPagoView.setNombreContacto("NombreC");
        todoPagoView.setNumeroCuenta("0");
        todoPagoView.setNumeroDocumento("12345678");
        todoPagoView.setNup("12345678");
        todoPagoView.setRazonSocial("Razon Social");
        todoPagoView.setSexo("M");
        todoPagoView.setTelefonoFijo("99999999");
        todoPagoView.setTipoDocumento("N");
        return todoPagoView;
    }

    /**
     * Confirmar adhesion OK test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws IllegalAccessException
     */
    @Test
    public void confirmarAdhesionOKTest() throws DAOException, IllegalAccessException {

        TodoPagoView todoPagoView = obtenerTodoPagoView();

        Respuesta<Boolean> respuestaEnvioMailBO = new Respuesta<Boolean>();
        respuestaEnvioMailBO.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<ResultadoTransaccion> respuestaBO = new Respuesta<ResultadoTransaccion>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        AdhesionTodoPagoInDTO adhesionTodoPagoInDTO = new AdhesionTodoPagoInDTO();
        adhesionTodoPagoInDTO.setFechaSolicitud(new Date());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(todoPagoHelper.crearAdhesionTodoPagoDTO(Matchers.any(TodoPagoView.class)))
                .thenReturn(adhesionTodoPagoInDTO);
        when(todoPagoBO.enviarMail(Matchers.any(AdhesionTodoPagoEnvioMailDTO.class))).thenReturn(respuestaEnvioMailBO);
        when(todoPagoBO.altaAdhesion(Matchers.any(AdhesionTodoPagoInDTO.class))).thenReturn(respuestaBO);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        FieldUtils.writeDeclaredField(todoPagoManagerImpl, "tiposCta", "00|01|02|03|04|09|10", true);
        Respuesta<AdhesionRespuestaView> respuesta = todoPagoManagerImpl.confirmarAdhesion(todoPagoView);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Descarga comprobante adhesion test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws MessagingException
     *             the messaging exception
     */
    @Test
    public void descargaComprobanteAdhesionTest() throws DAOException, MessagingException {

        Respuesta<Reporte> respuestaBO = new Respuesta<Reporte>();
        Reporte reporte = new Reporte();
        reporte.setTipoArchivo(TipoArchivoEnum.PDF);
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBO.setRespuesta(reporte);

        when(sesionParametros.getComprobanteAdhesionTodoPagoView()).thenReturn(new ComprobanteAdhesionTodoPagoView());
        when(todoPagoBO.generarComprobante(Matchers.any(ComprobanteAdhesionTodoPagoView.class)))
                .thenReturn(respuestaBO);
        Respuesta<ReporteView> respuesta = todoPagoManagerImpl.descargaComprobanteAdhesion();

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

}
