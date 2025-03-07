package ar.com.santanderrio.obp.servicios.pagohaberes.bo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.ws.rs.ProcessingException;


import ar.com.santanderrio.obp.servicios.pagohaberes.fixture.PagoHaberesFixture;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaOutCBUEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.CuentaInvalidaException;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.impl.MensajeBOImpl;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagohaberes.bo.impl.PagoHaberesBOImpl;
import ar.com.santanderrio.obp.servicios.pagohaberes.dao.impl.PagoHaberesDAOImpl;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobantePagoHaberesCBUEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.PagoHaberesEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.exceptions.CBUInvalidoDAOException;
import ar.com.santanderrio.obp.servicios.pagohaberes.exceptions.RecuperarDatosPorCBUDAOException;
import ar.com.santanderrio.obp.servicios.pagohaberes.exceptions.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.DatosDestinatarioView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoInformadoView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidacionesPagoPorCBUView;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.dao.SietePorVenticuatroV1DAO;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro.DATOSENTRADA;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional.DatosAdicionales;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro.DatosAdicional.DatosAdicionales.DatosSueldos;
import ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO;
import ar.com.santanderrio.obp.servicios.transferencias.dao.impl.TransferenciaModtrfe;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.exception.DestinatarioNoVerificadoException;

/**
 * InicioPagoHaberesBO Test.
 */
@RunWith(MockitoJUnitRunner.class)
public class PagoHaberesBOImplTest {

    /** The mock transfernecia DAO. */
    @Mock
    private PagoHaberesDAOImpl mockPagoHaberesDAO;
    
    /** The mock transfernecia DAO. */
    @Mock
    private TransferenciaDAO transferenciaDAO;

	@Mock
	private SesionCliente sesionCliente;
    
    /** The mock mensaje BO. */
    @Mock
    private MensajeBOImpl mockMensajeBO;

    /** The mock sesion parametros. */
    @Mock
    private SesionParametros mockSesionParametros;

    /** The pago haberes BO. */
    @InjectMocks
    private PagoHaberesBOImpl mockPagoHaberesBO;

    /** The siete por venticuatro V 1 DAO. */
    @Mock
    private SietePorVenticuatroV1DAO sietePorVenticuatroV1DAO;

    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    @Mock
    private LegalDAO legalDAO;

    /** The cliente respuesta OK. */
    Respuesta<Boolean> clienteRespuestaOK = new Respuesta<Boolean>();

    /** The cliente respuesta ERROR. */
    Respuesta<Boolean> respuestaEsperada = new Respuesta<Boolean>();

    /** The cliente respuesta WARNING. */
    Respuesta<Boolean> clienteRespuestaWARNING = new Respuesta<Boolean>();

    /** The transferencia respuesta WARNING. */
    Respuesta<TransferenciaDTO> transferenciaRespuestaWARNING = new Respuesta<TransferenciaDTO>();

    /** The transferencia respuesta OK. */
    Respuesta<TransferenciaDTO> transferenciaRespuestaOK = new Respuesta<TransferenciaDTO>();

    /** The transferencia respuesta ERROR. */
    Respuesta<TransferenciaDTO> transferenciaRespuestaERROR = new Respuesta<TransferenciaDTO>();

    /** The transferencia. */
    TransferenciaDTO transferencia = new TransferenciaDTO();

    /** The importe transferencia. */
    private BigDecimal importeTransferencia = BigDecimal.valueOf(300.23);

    /** The titular transferencia. */
    private String titularTransferencia = "Juan Jose Transferencia";

    /** The mensaje respuesta. */
    private Mensaje mensajeRespuesta = new Mensaje();

    /** The cuenta. */
    private Cuenta cuenta;

    /** The PAGHABCCI_100 response OK. */
    private String PAGHABCCI_100_ResponseOK = "200000000000P04HTML0009900010302QLPO92  ********00976793000000282017051909353900000000IBF21114000000010302PAGHABCCI_1000000            02615492    00        00 000000000201705190935340000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0004800000000õ302õ9990õ00010302õ00000005000õ20170522õ";

    /**
     * Init test.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        clienteRespuestaOK.setEstadoRespuesta(EstadoRespuesta.OK);
        clienteRespuestaOK.setRespuestaVacia(false);
        clienteRespuestaOK.setRespuesta(true);

        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaEsperada.setRespuestaVacia(false);
        respuestaEsperada.setRespuesta(true);

        clienteRespuestaWARNING.setEstadoRespuesta(EstadoRespuesta.WARNING);
        clienteRespuestaWARNING.setRespuestaVacia(false);
        clienteRespuestaWARNING.setRespuesta(false);

        transferenciaRespuestaWARNING.setEstadoRespuesta(EstadoRespuesta.WARNING);
        transferenciaRespuestaWARNING.setRespuestaVacia(false);

        transferenciaRespuestaOK.setEstadoRespuesta(EstadoRespuesta.OK);
        transferenciaRespuestaOK.setRespuestaVacia(true);

        transferenciaRespuestaERROR.setEstadoRespuesta(EstadoRespuesta.ERROR);
        transferenciaRespuestaERROR.setRespuestaVacia(true);

        this.transferencia.setTitular(this.titularTransferencia);
        this.transferencia.setImporte(this.importeTransferencia);
        this.transferencia.setMoneda(DivisaEnum.PESO);

        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        mensajeRespuesta.setMensaje("Mensaje ");
        respuestaMensaje.setRespuesta(this.mensajeRespuesta);

        Mockito.when(this.mockMensajeBO.obtenerMensajePorCodigo(Mockito.anyString())).thenReturn(this.mensajeRespuesta);

        RegistroSesion reg = new RegistroSesion();
        reg.setIp("198.13.3.3.13");
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(reg);
        ContadorIntentos contador = mock(ContadorIntentos.class);
        when(sesionParametros.getContador()).thenReturn(contador);
        when(contador.permiteReintentar()).thenReturn(true);
    }

    /**
     * Checks if is cliente adherido A pago de sueldos OK.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void isClienteAdheridoAPagoDeSueldosOK() throws DAOException {
		TransferenciaModtrfe transferenciaModtrfe = new TransferenciaModtrfe.TransferenciaModtrfeBuilder()
				.addModalidadTransferencia(StringUtils.EMPTY)
				.addIndicadorAdhesionBee(StringUtils.EMPTY)
				.addPosicionRespuesta("03")
				.build();
        Mockito.when(transferenciaDAO.ejecutarModTrfe(Matchers.any(Cliente.class))).thenReturn(transferenciaModtrfe);
    	
        Respuesta<Boolean> respuesta = mockPagoHaberesBO.isClienteAdheridoPagoHaberes(getCliente());
        Assert.assertNotNull(respuesta);

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Checks if is cliente adherido A pago de sueldos ERROR.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void isClienteAdheridoAPagoDeSueldosERROR() throws BusinessException, DAOException {
        Respuesta<Object> respuestaPagoHaberes = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Cliente inhabilitado para transferir");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaPagoHaberes.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaPagoHaberes.setItemMensajeRespuesta(itemMensajeRespuestaList);

        
		TransferenciaModtrfe transferenciaModtrfe = new TransferenciaModtrfe.TransferenciaModtrfeBuilder()
				.addModalidadTransferencia(StringUtils.EMPTY)
				.addIndicadorAdhesionBee(StringUtils.EMPTY)
				.addPosicionRespuesta("06")
				.build();
        Mockito.when(transferenciaDAO.ejecutarModTrfe(Matchers.any(Cliente.class))).thenReturn(transferenciaModtrfe);
         
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaPagoHaberes);
        Respuesta<Boolean> respuesta = mockPagoHaberesBO.isClienteAdheridoPagoHaberes(getCliente());

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener consulta agendamiento 7 x 24 ERROR.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerConsultaAgendamiento7x24ERROR() throws DAOException {
        Cliente cliente = createCliente();

        XMLResponseEntity xmlResponseError = new XMLResponseEntity();
        DATOSRESULTADO datosResultado = new DATOSRESULTADO();
        datosResultado.setSeveridad("1");
        xmlResponseError.setDATOSRESULTADO(datosResultado);

        Respuesta<Object> respuestaPagoHaberes = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaPagoHaberes.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaPagoHaberes.setItemMensajeRespuesta(itemMensajeRespuestaList);

        Mockito.when(sietePorVenticuatroV1DAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(xmlResponseError);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaPagoHaberes);

        Respuesta<PagoHaberesEntity> respuestaFinal = mockPagoHaberesBO.obtenerConsultaAgendamiento7x24(cliente);
        Assert.assertNotNull(respuestaFinal);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaFinal.getEstadoRespuesta());
        Assert.assertEquals("TIPO_ERROR_HARDCODEADO_EN_EL_TEST", respuestaFinal.getItemsMensajeRespuesta().iterator().next().getTipoError());
        Assert.assertEquals("Mensaje Error Hardcodeado en el Test", respuestaFinal.getItemsMensajeRespuesta().iterator().next().getMensaje());

    }

    /**
     * Obtener consulta agendamiento 7 x 24 ERROR con DAOException.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerConsultaAgendamiento7x24ERRORConDAOException() throws DAOException {
        Cliente cliente = createCliente();

        Respuesta<Object> respuestaPagoHaberes = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaPagoHaberes.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaPagoHaberes.setItemMensajeRespuesta(itemMensajeRespuestaList);

        Mockito.when(sietePorVenticuatroV1DAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenThrow(new DAOException());
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaPagoHaberes);

        Respuesta<PagoHaberesEntity> respuestaFinal = mockPagoHaberesBO.obtenerConsultaAgendamiento7x24(cliente);
        Assert.assertNotNull(respuestaFinal);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaFinal.getEstadoRespuesta());
        Assert.assertEquals("TIPO_ERROR_HARDCODEADO_EN_EL_TEST", respuestaFinal.getItemsMensajeRespuesta().iterator().next().getTipoError());
        Assert.assertEquals("Mensaje Error Hardcodeado en el Test", respuestaFinal.getItemsMensajeRespuesta().iterator().next().getMensaje());

    }

    /**
     * Obtener consulta agendamiento 7 x 24 ERROR con ProcessingException.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerConsultaAgendamiento7x24ERRORConProcessingException() throws ProcessingException, DAOException {
        Cliente cliente = createCliente();

        Respuesta<Object> respuestaPagoHaberes = new Respuesta<Object>();
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setMensaje("Mensaje Error Hardcodeado en el Test");
        itemMensajeRespuesta.setTipoError("TIPO_ERROR_HARDCODEADO_EN_EL_TEST");
        respuestaPagoHaberes.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaPagoHaberes.setItemMensajeRespuesta(itemMensajeRespuestaList);

        Mockito.when(sietePorVenticuatroV1DAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenThrow(new ProcessingException("", null));
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaPagoHaberes);

        Respuesta<PagoHaberesEntity> respuestaFinal = mockPagoHaberesBO.obtenerConsultaAgendamiento7x24(cliente);
        Assert.assertNotNull(respuestaFinal);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaFinal.getEstadoRespuesta());
        Assert.assertEquals("TIPO_ERROR_HARDCODEADO_EN_EL_TEST", respuestaFinal.getItemsMensajeRespuesta().iterator().next().getTipoError());
        Assert.assertEquals("Mensaje Error Hardcodeado en el Test", respuestaFinal.getItemsMensajeRespuesta().iterator().next().getMensaje());

    }

    /**
     * Obtener consulta agendamiento 7 x 24 OK.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerConsultaAgendamiento7x24OK() throws DAOException {
        Cliente cliente = createCliente();

        Registro registro1 = createRegistro();
        registro1.getDatosAdicionales().getDatosAdicionales().getDatosSueldos().setDestinatario(null);
        Registro registro2 = createRegistro();
        registro2.getDatosAdicionales().getDatosAdicionales().getDatosSueldos().setDestinatario(null);
        Registro registro3 = createRegistro();
        Registro registro4 = createRegistro();
        List<Registro> registroList = new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>();
        registroList.add(registro1);
        registroList.add(registro2);
        registroList.add(registro3);
        registroList.add(registro4);

        XMLResponseEntity xmlResponseOK = new XMLResponseEntity();
        DATOSRESULTADO datosResultado = new DATOSRESULTADO();
        datosResultado.setSeveridad("0");
        datosResultado.setRegistro(registroList);
        xmlResponseOK.setDATOSRESULTADO(datosResultado);

        Mockito.when(sietePorVenticuatroV1DAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(xmlResponseOK);
        Respuesta<PagoHaberesEntity> respuestaFinal = mockPagoHaberesBO.obtenerConsultaAgendamiento7x24(cliente);
        Assert.assertNotNull(respuestaFinal);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaFinal.getEstadoRespuesta());

    }

    /**
     * Obtener consulta agendamiento 7 x 24 OK con Exception.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerConsultaAgendamiento7x24OKConException() throws DAOException {
        Cliente cliente = createCliente();

        Registro registro = createRegistro();
        registro.setFechaEjecucion("aaaa");

        List<Registro> registroList = new ArrayList<XMLResponseEntity.DATOSRESULTADO.Registro>();
        registroList.add(registro);

        XMLResponseEntity xmlResponseOK = new XMLResponseEntity();
        DATOSRESULTADO datosResultado = new DATOSRESULTADO();
        datosResultado.setSeveridad("0");
        datosResultado.setRegistro(registroList);
        xmlResponseOK.setDATOSRESULTADO(datosResultado);

        Mockito.when(sietePorVenticuatroV1DAO.ejecutar(Matchers.any(XMLRequestEntity.class))).thenReturn(xmlResponseOK);
        Respuesta<PagoHaberesEntity> respuestaFinal = mockPagoHaberesBO.obtenerConsultaAgendamiento7x24(cliente);
        Assert.assertNotNull(respuestaFinal);
        Assert.assertEquals( EstadoRespuesta.OK, respuestaFinal.getEstadoRespuesta());

    }

    private Registro createRegistro() {
        Registro registro = new Registro();
        DatosAdicional datosAdicional = new DatosAdicional();
        DatosAdicionales datosAdicionales = new DatosAdicionales();
        DatosSueldos datosSueldos = new DatosSueldos();
        DATOSENTRADA datosEntrada = new DATOSENTRADA();
        registro.setDatosAdicionales(datosAdicional);
        registro.getDatosAdicionales().setDatosAdicionales(datosAdicionales);
        registro.getDatosAdicionales().getDatosAdicionales().setDatosSueldos(datosSueldos);
        registro.setDATOSENTRADA(datosEntrada);
        registro.getDatosAdicionales().getDatosAdicionales().getDatosSueldos().setDestinatario("destinatario");
        registro.getDatosAdicionales().getDatosAdicionales().getDatosSueldos().setTipoCUIL("tipoCUIL");
        registro.getDatosAdicionales().getDatosAdicionales().getDatosSueldos().setNumeroCUIL("numeroCuil");
        registro.getDATOSENTRADA().setTipoCtaCredito("tipoCtaCredito");
        registro.getDATOSENTRADA().setSucCtaCredito("sucCtaCredito");
        registro.getDATOSENTRADA().setNroCtaCredito("nroCtaCredito");
        registro.getDatosAdicionales().getDatosAdicionales().getDatosSueldos().setTipoPago("tipoPago");
        registro.getDatosAdicionales().getDatosAdicionales().getDatosSueldos().setDescripcionConcepto("descripcionConcepto");
        registro.getDATOSENTRADA().setImporte(1);
        registro.getDATOSENTRADA().setTipoPersona("tipoPersona");
        registro.getDATOSENTRADA().setTipoId("tipoId");
        registro.getDATOSENTRADA().setIdCliente("idCliente");
        registro.getDATOSENTRADA().setFechaNac("fechaNac");
        registro.getDATOSENTRADA().setComprobanteCredito("comprobanteCredito");
        registro.getDATOSENTRADA().setIndTransfDiferida("idTransaccion");
        registro.getDATOSENTRADA().setSucCtaDebito("sucCtaDebito");
        registro.getDATOSENTRADA().setNroCtaDebito("nroCtaDebtio");
        registro.getDATOSENTRADA().setTipoCtaDebito("tipoCtaDevito");
        registro.setNroRecurrencia("nroRecurrencia");
        registro.setFechaBaseRecurrencia("fechaBaseRecurrencia");
        registro.setFrecRecurrencia("frecRecurrencia");
        registro.setMaxRecurrencia("maxRecurrencia");
        registro.setTipoRecurrencia("tipoRecurrencia");
        registro.setTipoAgendamiento("E");
        registro.setFechaEjecucion("05092017");
        return registro;
    }

    private Cliente createCliente() {
        Cliente cliente = new Cliente();
        cliente.setTipoDocumento("N");
        cliente.setDni("00013238861");
        cliente.setFechaNacimiento("19591005");
        cliente.setNup("02615492");
        cliente.setUsuarioRacf("FREEUSER");
        cliente.setPasswordRacf("FREEUSR0");
        return cliente;
    }

    /**
     * Valida que el empleado existe
     * 
     * @throws DAOException
     * @throws CuentaInvalidaException
     */
    @Test
    public void testValidarEmpleadoOK() throws DAOException, CuentaInvalidaException {
        PagoInformadoView pagoInformadoView = new PagoInformadoView();
        pagoInformadoView.setTipoCuentaDestino("CCP");
        pagoInformadoView.setCuentaDestino("123-123456/7");

        Respuesta<PagoInformadoView> respuestaPagoInformado = new Respuesta<PagoInformadoView>();
        respuestaPagoInformado.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaPagoInformado.setRespuesta(pagoInformadoView);
        respuestaPagoInformado.setRespuestaVacia(false);
        Mockito.when(mockPagoHaberesDAO.isClienteValido(Matchers.any(Cliente.class), Matchers.any(PagoInformadoView.class))).thenReturn(pagoInformadoView);
        Respuesta<PagoInformadoView> respuestaEmpleado = this.mockPagoHaberesBO.validarEmpleado(getCliente(), pagoInformadoView);

        Assert.assertNotNull(respuestaEmpleado);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaEmpleado.getEstadoRespuesta());
    }

    /**
     * Valida que no se haya podido validar al empleado
     * 
     * @throws DAOException
     * @throws CuentaInvalidaException
     */
    @Test
    public void testValidarEmpleadoERROR() throws DAOException, CuentaInvalidaException {
        PagoInformadoView pagoInformadoView = new PagoInformadoView();
        pagoInformadoView.setTipoCuentaDestino("CCP");
        pagoInformadoView.setCuentaDestino("123-123456/7");

        Respuesta<Object> respuestaError = new Respuesta<Object>();
        ItemMensajeRespuesta itemRes = new ItemMensajeRespuesta();
        List<ItemMensajeRespuesta> listItemRes = new ArrayList<ItemMensajeRespuesta>();
        itemRes.setMensaje("Falló la validación");
        listItemRes.add(itemRes);
        respuestaError.setItemMensajeRespuesta(listItemRes);
        respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaError);
        Mockito.when(mockPagoHaberesDAO.isClienteValido(Matchers.any(Cliente.class), Matchers.any(PagoInformadoView.class))).thenReturn(null);
        Respuesta<PagoInformadoView> respuestaEmpleado = this.mockPagoHaberesBO.validarEmpleado(getCliente(), pagoInformadoView);

        Assert.assertNotNull(respuestaEmpleado);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaEmpleado.getEstadoRespuesta());

    }

    /**
     * Valida que el CBU destino a realizar la transferencia sea valido. En este
     * caso es un CBU valido
     * 
     * @throws DestinatarioNoVerificadoException
     * @throws DAOException
     */
    @Test
    public void testValidarCBUOK() throws DestinatarioNoVerificadoException, DAOException {
        ValidacionesPagoPorCBUView validacionesPagoPorCBU = new ValidacionesPagoPorCBUView();
        validacionesPagoPorCBU.setTipoCuentaOrigen("CU");
        validacionesPagoPorCBU.setNumeroCBUDestino("2850590940090418135201");
        validacionesPagoPorCBU.setTarjetaBanelco("4517660021778823");
        validacionesPagoPorCBU.setDireccionIP("180166094114");

        ValidacionCuentaOutCBUEntity datosCliente = new ValidacionCuentaOutCBUEntity();
        datosCliente.setTitular("Titular");
        datosCliente.setCuit("20-36396359-4");
        datosCliente.setBandes("SANTANDER");

        if (ISBANStringUtils.validarCBU(validacionesPagoPorCBU.getNumeroCBUDestino())) {
            Mockito.when(mockPagoHaberesDAO.validarCBU(Matchers.any(Cliente.class), Matchers.any(ValidacionesPagoPorCBUView.class))).thenReturn(datosCliente);
        }

        Respuesta<DatosDestinatarioView> respuestaDestinatario = mockPagoHaberesBO.validarCBU(getCliente(), validacionesPagoPorCBU);

        Assert.assertNotNull(respuestaDestinatario);
    }

    /**
     * Test que lanza una excepcion cuando no se pueden devolver datos desde la
     * capa DAO cuando el CBU es valido
     * 
     * @throws RecuperarDatosPorCBUDAOException
     * @throws DestinatarioNoVerificadoException
     * @throws DAOException
     */
    @Test
    public void testValidarCBU_RecuperarDatosPorCBUDAOException() throws RecuperarDatosPorCBUDAOException, DestinatarioNoVerificadoException, DAOException {
        ValidacionesPagoPorCBUView validacionesPagoPorCBU = new ValidacionesPagoPorCBUView();
        validacionesPagoPorCBU.setNumeroCBUDestino("");

        Respuesta<Object> respuestaError = new Respuesta<Object>();
        ItemMensajeRespuesta itemRes = new ItemMensajeRespuesta();
        List<ItemMensajeRespuesta> listItemRes = new ArrayList<ItemMensajeRespuesta>();
        itemRes.setMensaje("Error al recuperar los datos");
        listItemRes.add(itemRes);
        respuestaError.setItemMensajeRespuesta(listItemRes);
        respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);

        // Si el formato del CBU no es correcto
        if (!ISBANStringUtils.validarCBU(validacionesPagoPorCBU.getNumeroCBUDestino())) {
            Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaError);

        }
        Respuesta<DatosDestinatarioView> respuestaDestinatario = mockPagoHaberesBO.validarCBU(getCliente(), validacionesPagoPorCBU);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaDestinatario.getEstadoRespuesta());
    }

    @Test
    public void testValidarCBU_CBUInvalidoDAOException() throws CBUInvalidoDAOException, DestinatarioNoVerificadoException, DAOException {
        Cliente cliente = getCliente();
        ValidacionesPagoPorCBUView validacionesPagoPorCBU = new ValidacionesPagoPorCBUView();
        validacionesPagoPorCBU.setTipoCuentaOrigen("CU");
        validacionesPagoPorCBU.setNumeroCBUDestino("2590004220043918130135");
        validacionesPagoPorCBU.setTarjetaBanelco("1234567890");
        validacionesPagoPorCBU.setDireccionIP("180166094114");

        Respuesta<Object> respuestaError = new Respuesta<Object>();
        ItemMensajeRespuesta itemRes = new ItemMensajeRespuesta();
        List<ItemMensajeRespuesta> listItemRes = new ArrayList<ItemMensajeRespuesta>();
        itemRes.setMensaje("Error al recuperar los datos");
        listItemRes.add(itemRes);
        respuestaError.setItemMensajeRespuesta(listItemRes);
        respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);

        // Si el CBU esta bien formado, pero no existe
        if (ISBANStringUtils.validarCBU(validacionesPagoPorCBU.getNumeroCBUDestino())) {

            Mockito.when(mockPagoHaberesDAO.validarCBU(Matchers.any(Cliente.class), Matchers.any(ValidacionesPagoPorCBUView.class)))
                    .thenThrow(new CBUInvalidoDAOException("Error al recuperar los datos"));

            Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaError);

            Respuesta<DatosDestinatarioView> respuestaDatos = mockPagoHaberesBO.validarCBU(cliente, validacionesPagoPorCBU);

            Assert.assertEquals(respuestaError.getItemsMensajeRespuesta().get(0).getMensaje(), respuestaDatos.getItemsMensajeRespuesta().get(0).getMensaje());
        }
    }

    /**
     * Test que valida que se realiza el pago
     * 
     * @throws SaldoInsuficienteException
     * @throws DAOException
     */
    @Test
    public void testPagoHaberesCBUOK() throws SaldoInsuficienteException, DAOException {
        Cliente cliente = getCliente();

        IatxResponse response = new IatxResponse();
        response.setEstadoRespuesta(EstadoRespuesta.OK);
        Vector<String> vector = buildVectorPagoHaberes();
        response.setTrama(PAGHABCCI_100_ResponseOK);
        response.setIatxBody(vector);
        response.setErrorCode(00000000);

        DatosDestinatarioView datosDestinatarioView = getDatosDestinatariosView();

        cuenta = new Cuenta();
        cuenta.setTipoCuenta("00");
        cuenta.setNroSucursal("000");
        cuenta.setNroCuentaProducto("0000000");
        cuenta.setNroOrdenFirmante("01");

        Mockito.when(mockPagoHaberesDAO.pagoHaberesCBU(Matchers.any(Cliente.class), Matchers.any(DatosDestinatarioView.class), Matchers.any(Cuenta.class))).thenReturn(response);

        Respuesta<ComprobantePagoHaberesCBUEntity> respuesta = mockPagoHaberesBO.pagoHaberesCBU(cliente, datosDestinatarioView, cuenta);

        Assert.assertNotNull(respuesta);
    }

    /**
     * Test que lanza una exception de SaldoInsuficienteException al momento de
     * realizar el pago
     * 
     * @throws SaldoInsuficienteException
     * @throws DAOException
     */
    @Test
    public void testPagoHaberesCBUERROR_SaldoInsuficienteException() throws SaldoInsuficienteException, DAOException {
        IatxResponse response = new IatxResponse();
        response.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Vector<String> vector = buildVectorPagoHaberes();
        response.setTrama("");
        response.setIatxBody(vector);
        response.setErrorCode(10000122);

        Respuesta<Object> respuestaError = new Respuesta<Object>();
        respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Mockito.doThrow(SaldoInsuficienteException.class).when(mockPagoHaberesDAO).pagoHaberesCBU(Matchers.any(Cliente.class), Matchers.any(DatosDestinatarioView.class),
                Matchers.any(Cuenta.class));

        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaError);

        Respuesta<ComprobantePagoHaberesCBUEntity> respuesta = mockPagoHaberesBO.pagoHaberesCBU(getCliente(), getDatosDestinatariosView(), getCuenta());
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void testPagoHaberesCBUERROR_DAOException() throws DAOException {
        IatxResponse response = new IatxResponse();
        response.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Vector<String> vector = buildVectorPagoHaberes();
        response.setTrama("");
        response.setIatxBody(vector);
        response.setErrorCode(10000122);

        Respuesta<Object> respuestaError = new Respuesta<Object>();
        respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Mockito.doThrow(DAOException.class).when(mockPagoHaberesDAO).pagoHaberesCBU(Matchers.any(Cliente.class), Matchers.any(DatosDestinatarioView.class),
                Matchers.any(Cuenta.class));

        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuestaError);

        Respuesta<ComprobantePagoHaberesCBUEntity> respuesta = mockPagoHaberesBO.pagoHaberesCBU(getCliente(), getDatosDestinatariosView(), getCuenta());
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Gets the cliente.
     *
     * @return Cliente Mock
     */
    private Cliente getCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Jorge");
        cliente.setApellido1("Perez");
        cliente.setCuentas(new ArrayList<Cuenta>());
        Cuenta cuenta = new Cuenta();
        cuenta.setNroSucursal("012");
        cuenta.setNroCuentaProducto("231237123");
        cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta.setClaseCuenta("V");
        cliente.getCuentas().add(cuenta);
        return cliente;
    }

    private Vector<String> buildVectorPagoHaberes() {
        Vector<String> vector = new Vector<String>();
        vector.add("00000000");
        vector.add("A");
        vector.add("0001");
        vector.add("03");
        return vector;
    }

    private DatosDestinatarioView getDatosDestinatariosView() {
        DatosDestinatarioView datosDestinatarioView = new DatosDestinatarioView();
        datosDestinatarioView.setTipoCuentaOrigen("09");
        datosDestinatarioView.setCuentaOrigen("123456789012");
        datosDestinatarioView.setImporte("00000010000");
        datosDestinatarioView.setConcepto("CONCEPTO");
        datosDestinatarioView.setCuilCuitDestinatario("12345678901");
        datosDestinatarioView.setNumeroCBUDestino("00088000006391704");
        datosDestinatarioView.setBancoDestinatario("BANCO SANTANDER");
        datosDestinatarioView.setNombreDestinatario("ZIRUFFO RUBEN OSCAR");
        return datosDestinatarioView;
    }

    private Cuenta getCuenta() {
        Cuenta cuenta = new Cuenta();
        Cliente cliente = getCliente();
        cliente.setNombre("Pablo");
        cliente.setApellido1("Perez");
        cliente.setApellido2("Amarilla");
        cuenta.setNroSucursal("000");
        cuenta.setNroCuentaProducto("0123456");
        cuenta.setCliente(cliente);
        return cuenta;
    }

    @Test
    public void givenCBUWhenEsTipoDeClaveCVUReturnFalse() throws DestinatarioNoVerificadoException, DAOException {

        Boolean respuesta = mockPagoHaberesBO.esTipoDeClaveCVU(PagoHaberesFixture.getValidacionesPagoPorCBUViewCBU());

        Assert.assertFalse(respuesta);
    }

    @Test
    public void givenCBU2WhenEsTipoDeClaveCVUReturnFalse() throws DestinatarioNoVerificadoException, DAOException {

        Boolean respuesta = mockPagoHaberesBO.esTipoDeClaveCVU(PagoHaberesFixture.getValidacionesPagoPorCBUViewCBU2());

        Assert.assertFalse(respuesta);
    }

    @Test
    public void givenCVUWhenEsTipoDeClaveCVUReturnTrue() throws DestinatarioNoVerificadoException, DAOException {

        Boolean respuesta = mockPagoHaberesBO.esTipoDeClaveCVU(PagoHaberesFixture.getValidacionesPagoPorCBUViewCVU());

        Assert.assertTrue(respuesta);
    }

    @Test
    public void givenCVU2WhenEsTipoClaveCVU2ReturnTrue() throws DestinatarioNoVerificadoException, DAOException {

        Boolean respuesta = mockPagoHaberesBO.esTipoDeClaveCVU(PagoHaberesFixture.getValidacionesPagoPorCBUViewCVU2());

        Assert.assertTrue(respuesta);
    }

    @Test
    public void givenCVUWhenValidarCBUReturnTrue() throws DestinatarioNoVerificadoException, DAOException {
        Boolean rta = ISBANStringUtils.validarCBU(PagoHaberesFixture.getValidacionesPagoPorCBUViewCVU().getNumeroCBUDestino());
        Assert.assertTrue(rta);
    }

    @Test
    public void givenCVU2WhenValidarCBUReturnTrue() throws DestinatarioNoVerificadoException, DAOException {
        Boolean rta = ISBANStringUtils.validarCBU(PagoHaberesFixture.getValidacionesPagoPorCBUViewCVU2().getNumeroCBUDestino());
        Assert.assertTrue(rta);
    }

    @Test
    public void givenCBUWhenValidarCBUReturnTrue() throws DestinatarioNoVerificadoException, DAOException {
        Boolean rta = ISBANStringUtils.validarCBU(PagoHaberesFixture.getValidacionesPagoPorCBUViewCBU().getNumeroCBUDestino());
        Assert.assertTrue(rta);
    }

    @Test
    public void givenCBUSantanderWhenEsCBUSantanderReturnTrue() {
        boolean rta = mockPagoHaberesBO.esCBUSantander(PagoHaberesFixture.getValidacionesPagoPorCBUViewCBU2());
        Assert.assertTrue(rta);
    }

    @Test
    public void givenCBUOtrosBancosWhenEsCBUSantanderReturnFalse() {
        ValidacionesPagoPorCBUView validacionesPagoPorCBUView = new ValidacionesPagoPorCBUView();
        validacionesPagoPorCBUView.setNumeroCBUDestino("1230277588000036016518");

        boolean rta = mockPagoHaberesBO.esCBUSantander(validacionesPagoPorCBUView);
        Assert.assertFalse(rta);
    }
}
