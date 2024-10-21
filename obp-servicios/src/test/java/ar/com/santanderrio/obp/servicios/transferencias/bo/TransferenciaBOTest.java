package ar.com.santanderrio.obp.servicios.transferencias.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;

import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AltaDestinatarioBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao.AgendaDestinatarioDAO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioInEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.impl.EstadisticaBOImpl;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.transferencias.bo.impl.TransferenciaBOImpl;
import ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO;
import ar.com.santanderrio.obp.servicios.transferencias.dao.impl.TransferenciaModtrfe;
import ar.com.santanderrio.obp.servicios.transferencias.entities.IndicadorFuncion;
import ar.com.santanderrio.obp.servicios.transferencias.entities.PlazoAcreditacion;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TipoCuentaBanelco;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.exception.BeneficiarioNoVerificadException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaOrigenSinBanelcoException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaSinOperarException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.DestinatarioNoVerificadoERRORenCNSException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.DestinatarioNoVerificadoException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.ImporteLimiteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TitularidadNoVerificadaException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TransferenciaGenericaException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaAlfaNoVerificadaNuncaOperoUsdException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaAlfaNoVerificadaException;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.DestinatarioDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * Test para {@link TransferenciaBO}.
 *
 * @author emilio.watemberg
 * @since Aug 18, 2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class TransferenciaBOTest {

    /** The transferencia BO. */
    @InjectMocks
    private TransferenciaBOImpl transferenciaBO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The agenda destinatario DAO. */
    @Mock
    private AgendaDestinatarioDAO agendaDestinatarioDAO;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The transferencia DAO. */
    @Mock
    private TransferenciaDAO transferenciaDAO;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

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

    /** The numero comprobante no vacio. */
    private String NUMERO_COMPROBANTE_NO_VACIO = "NCNV";

    /** The importe transferencia. */
    private BigDecimal IMPORTE_TRANSFERENCIA = new BigDecimal(300.23);

    /** The titular transferencia. */
    private String TITULAR_TRANSFERENCIA = "Juan Jose Transferencia";

    /** The mensaje respuesta. */
    private Mensaje mensajeRespuesta = new Mensaje();

    /** The mock estadistica manager. */
    @Mock
    private EstadisticaBOImpl mockEstadisticaBO;

    /** The alta destinatario BO. */
    @Mock
    private AltaDestinatarioBO altaDestinatarioBO;

    // /** The Constant REALIZAR. */
    // private static final String REALIZAR = "realizar";

    /**
     * Init test.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        clienteRespuestaOK.setEstadoRespuesta(EstadoRespuesta.OK);
        clienteRespuestaOK.setRespuestaVacia(false);
        clienteRespuestaOK.setRespuesta(Boolean.TRUE);

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

        this.transferencia.setTitular(this.TITULAR_TRANSFERENCIA);
        this.transferencia.setImporte(this.IMPORTE_TRANSFERENCIA);
        this.transferencia.setMoneda(DivisaEnum.PESO);

        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        respuestaMensaje.setRespuesta(this.mensajeRespuesta);
        ReflectionTestUtils.setField(transferenciaBO, "errorBanelcoCoelsaHabilitado", "1");

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Mockito.anyString())).thenReturn(this.mensajeRespuesta);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString())).thenReturn(this.mensajeRespuesta);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
    }

    /**
     * Checks if is cliente habilitado para transferir OK.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void isClienteHabilitadoParaTransferirOK() throws BusinessException, DAOException {
        Cliente cliente = new Cliente();
        Respuesta<Boolean> respuestaOK = new Respuesta<Boolean>();
        respuestaOK.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaOK.setRespuestaVacia(false);
        respuestaOK.setRespuesta(Boolean.TRUE);
        Respuesta<Boolean> respuestaBoolean = new Respuesta<Boolean>();
        respuestaBoolean.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(respuestaFactory.crearRespuestaOk(Boolean.class)).thenReturn(respuestaBoolean);
        
        TransferenciaModtrfe transferenciaModtrfe = new TransferenciaModtrfe.TransferenciaModtrfeBuilder()
		.addModalidadTransferencia("03")
		.addIndicadorAdhesionBee("S")
		.addPosicionRespuesta("02").build();

        Mockito.when(transferenciaDAO.ejecutarModTrfe(Matchers.any(Cliente.class))).thenReturn(transferenciaModtrfe);
        Respuesta<Boolean> respuesta = transferenciaBO.isClienteHabilitadoParaTransferir(cliente);
        Assert.assertNotNull(respuesta);
        assertEquals(respuestaOK, respuesta);
    }

    /**
     * Checks if is cliente habilitado para transferir ERROR.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void isClienteHabilitadoParaTransferirERROR() throws BusinessException, DAOException {

        Cliente cliente = new Cliente();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Cliente inhabilitado para transferir");
        Respuesta<Mensaje> mensajeRespuesta = new Respuesta<Mensaje>();
        mensajeRespuesta.setRespuesta(mensaje);
        Respuesta<Boolean> respuestaBoolean = new Respuesta<Boolean>();
        respuestaBoolean.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(respuestaFactory.crearRespuestaOk(Boolean.class)).thenReturn(respuestaBoolean);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        
        TransferenciaModtrfe transferenciaModtrfe = new TransferenciaModtrfe.TransferenciaModtrfeBuilder()
		.addModalidadTransferencia("")
		.addIndicadorAdhesionBee("")
		.addPosicionRespuesta("").build();

        Mockito.when(transferenciaDAO.ejecutarModTrfe(Matchers.any(Cliente.class))).thenReturn(transferenciaModtrfe);
        Respuesta<Boolean> respuesta = transferenciaBO.isClienteHabilitadoParaTransferir(cliente);
        Respuesta<Boolean> respuestaOKConFalse = new Respuesta<Boolean>();
        respuestaOKConFalse.setRespuestaVacia(false);
        respuestaOKConFalse.setRespuesta(Boolean.FALSE);
        respuestaOKConFalse.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaOKConFalse.setId(1L);
        respuesta.setId(1L);
        Assert.assertNotNull(respuesta);
        assertEquals(respuestaOKConFalse, respuesta);
    }

    /**
     * Checks if is cliente habilitado para transferir DAO exception.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void isClienteHabilitadoParaTransferirDAOException() throws DAOException, BusinessException {

        this.mensajeRespuesta.setMensaje(
                "<p>¡Lo sentimos!</p><p>No se pudo realizar tu transferencia a {0} por {1}.Por favor, intentá nuevamente en unos minutos.</p>");

        given(transferenciaDAO.ejecutarModTrfe(Matchers.any(Cliente.class)))
                .willThrow(new DAOException());
        Respuesta<Boolean> respuestaBoolean = new Respuesta<Boolean>();
        respuestaBoolean.setEstadoRespuesta(EstadoRespuesta.WARNING);
        Mockito.when(respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(Matchers.any(Class.class),
                Mockito.anyString(), Mockito.anyString())).thenReturn(respuestaBoolean);

        Cliente cliente = new Cliente();
        TransferenciaView transferenciaView = new TransferenciaView();
        transferenciaView.setImporte(IMPORTE_TRANSFERENCIA.toString());
        transferenciaView.setMoneda("$");
        transferenciaView.setTitular(TITULAR_TRANSFERENCIA);
        Respuesta<Boolean> respuestaObtenida = transferenciaBO.isClienteHabilitadoParaTransferir(cliente);

        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(MessageFormat.format(
                "<p>¡Lo sentimos!</p><p>No se pudo realizar tu transferencia a {0} por {1}.Por favor, intentá nuevamente en unos minutos.</p>",
                this.TITULAR_TRANSFERENCIA,
                "$ " + this.IMPORTE_TRANSFERENCIA.setScale(2, RoundingMode.FLOOR).toString().replaceAll("\\.", "\\,")));
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        respuestaEsperada.add(itemMensajeRespuesta);
        respuestaEsperada.setRespuesta(null);
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaEsperada.setRespuestaVacia(true);

        Assert.assertNotNull(respuestaObtenida);
        assertEquals(respuestaEsperada.getEstadoRespuesta(), respuestaObtenida.getEstadoRespuesta());
    }

    /**
     * Validar origen destino transferencia OK.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     * @throws DestinatarioNoVerificadoException
     *             the destinatario no verificado exception
     * @throws TitularidadNoVerificadaException
     *             the titularidad no verificada exception
     * @throws BeneficiarioNoVerificadException
     *             the beneficiario no verificad exception
     * @throws DestinatarioNoVerificadoERRORenCNSException
     *             the destinatario no verificado ERRO ren CNS exception
     * @throws CuentaOrigenSinBanelcoException
     *             the cuenta origen sin banelco exception
     */
    @Test
    public void validarOrigenDestinoTransferenciaOK() throws DAOException, BusinessException,
            DestinatarioNoVerificadoException, TitularidadNoVerificadaException, BeneficiarioNoVerificadException,
            DestinatarioNoVerificadoERRORenCNSException, CuentaOrigenSinBanelcoException {

        String nroTarjetaActiva = "";
        TransferenciaDTO transferenciaRetornada = new TransferenciaDTO();

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("VERIFICAR_CBU");
        Respuesta<Mensaje> mensajeRespuesta = new Respuesta<Mensaje>();
        mensajeRespuesta.setRespuesta(mensaje);

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        transferenciaRespuestaOK.setRespuestaVacia(true);
        transferenciaRespuestaOK.setRespuesta(transferenciaRetornada);

        Cliente cliente = new Cliente();
        TransferenciaDTO transferencia = new TransferenciaDTO();

        Mockito.when(transferenciaDAO.validarOrigenDestinoTransferencia(Matchers.any(Cliente.class),
                Matchers.any(TransferenciaDTO.class), Mockito.anyString())).thenReturn(transferenciaRetornada);
        Respuesta<TransferenciaDTO> respuesta = transferenciaBO.validarOrigenDestinoTransferencia(cliente,
                transferencia, nroTarjetaActiva, new String(), new Cuenta(), new String());
        respuesta.setRespuestaVacia(true);

        Assert.assertNotNull(respuesta);
        assertEquals(transferenciaRespuestaOK, respuesta);
    }

    /**
     * Validar origen destino transferencia DAO exception.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     * @throws DestinatarioNoVerificadoException
     *             the destinatario no verificado exception
     * @throws TitularidadNoVerificadaException
     *             the titularidad no verificada exception
     * @throws BeneficiarioNoVerificadException
     *             the beneficiario no verificad exception
     * @throws DestinatarioNoVerificadoERRORenCNSException
     *             the destinatario no verificado ERRO ren CNS exception
     * @throws CuentaOrigenSinBanelcoException
     *             the cuenta origen sin banelco exception
     */
    @Test
    public void validarOrigenDestinoTransferenciaDAOException() throws DAOException, BusinessException,
            DestinatarioNoVerificadoException, TitularidadNoVerificadaException, BeneficiarioNoVerificadException,
            DestinatarioNoVerificadoERRORenCNSException, CuentaOrigenSinBanelcoException {

        String nroTarjetaActiva = "";
        this.mensajeRespuesta.setMensaje(
                "<p>¡Lo sentimos!</p><p>No se pudo realizar tu transferencia a {0} por {1}.Por favor, intentá nuevamente en unos minutos.</p>");
        TransferenciaDTO transferenciaRetornada = new TransferenciaDTO();
        transferenciaRetornada.setErrorBanelco(true);
        transferenciaRetornada.setTransferenciaInmediata(false);

        given(transferenciaDAO.validarOrigenDestinoTransferencia(Mockito.any(Cliente.class),
                Mockito.any(TransferenciaDTO.class), Mockito.anyString())).willThrow(new DAOException());

        Cliente cliente = new Cliente();
        TransferenciaDTO transferencia = new TransferenciaDTO();
        Respuesta<TransferenciaDTO> respuesta = transferenciaBO.validarOrigenDestinoTransferencia(cliente,
                transferencia, nroTarjetaActiva, new String(), new Cuenta(), new String());

        transferenciaRespuestaERROR.setRespuestaVacia(true);
        transferenciaRespuestaERROR.setRespuesta(transferenciaRetornada);
        transferenciaRespuestaERROR.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(MessageFormat.format(
                "<p>¡Lo sentimos!</p><p>No se pudo realizar tu transferencia a {0} por {1}.Por favor, intentá nuevamente en unos minutos.</p>",
                this.TITULAR_TRANSFERENCIA, this.transferencia.getMoneda().getSimbolo() + this.IMPORTE_TRANSFERENCIA
                        .setScale(2, RoundingMode.FLOOR).toString().replaceAll("\\.", "\\,")));
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        transferenciaRespuestaERROR.add(itemMensajeRespuesta);

        Assert.assertNotNull(respuesta);
        assertEquals(transferenciaRespuestaERROR.getEstadoRespuesta(), respuesta.getEstadoRespuesta());
        assertEquals(transferenciaRespuestaERROR.isRespuestaVacia(), respuesta.isRespuestaVacia());
    }

    /**
     * Validar origen destino transferencia null.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     * @throws DestinatarioNoVerificadoException
     *             the destinatario no verificado exception
     * @throws TitularidadNoVerificadaException
     *             the titularidad no verificada exception
     * @throws BeneficiarioNoVerificadException
     *             the beneficiario no verificad exception
     * @throws DestinatarioNoVerificadoERRORenCNSException
     *             the destinatario no verificado ERRO ren CNS exception
     * @throws CuentaOrigenSinBanelcoException
     *             the cuenta origen sin banelco exception
     */
    @Test
    public void validarOrigenDestinoTransferenciaDestinatarioNoVerificado() throws DAOException, BusinessException,
            DestinatarioNoVerificadoException, TitularidadNoVerificadaException, BeneficiarioNoVerificadException,
            DestinatarioNoVerificadoERRORenCNSException, CuentaOrigenSinBanelcoException {
        String nroTarjetaActiva = "";

        TransferenciaDTO transferenciaRetornada = new TransferenciaDTO();
        Respuesta<TransferenciaDTO> respuesta = new Respuesta<TransferenciaDTO>();

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("VERIFICAR_CBU");
        Respuesta<Mensaje> mensajeRespuesta = new Respuesta<Mensaje>();
        mensajeRespuesta.setRespuesta(mensaje);

        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(
                mensajeRespuesta.getRespuesta().getMensaje());
        itemMensajeRespuesta.setTipoError(TipoError.WARNINGOK.getDescripcion());

        transferenciaRetornada.setPlazoAcreditacion(PlazoAcreditacion.PLAZO_24_HS);
        transferenciaRetornada.setErrorBanelco(true);
        transferenciaRetornada.setTransferenciaInmediata(false);

        transferenciaRespuestaWARNING.add(itemMensajeRespuesta);
        transferenciaRespuestaWARNING.setRespuestaVacia(true);
        transferenciaRespuestaWARNING.setRespuesta(transferenciaRetornada);

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);

        Cliente cliente = new Cliente();
        TransferenciaDTO transferencia = new TransferenciaDTO();

        given(transferenciaDAO.validarOrigenDestinoTransferencia(Mockito.any(Cliente.class),
                Mockito.any(TransferenciaDTO.class), Mockito.anyString()))
                        .willThrow(new DestinatarioNoVerificadoException());
        respuesta = transferenciaBO.validarOrigenDestinoTransferencia(cliente, transferencia, nroTarjetaActiva,
                new String(), new Cuenta(), new String());
        respuesta.setRespuestaVacia(true);

        Assert.assertNotNull(respuesta);
        Assert.assertNotNull(respuesta.getRespuesta());
        assertEquals(transferenciaRespuestaWARNING.getEstadoRespuesta(), respuesta.getEstadoRespuesta());
    }

    /**
     * Generar comprobante transferencia OK.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void generarComprobanteTransferenciaOK() throws DAOException {

        Mockito.when(transferenciaDAO.generarComprobanteTransferencia(Matchers.any(TransferenciaDTO.class)))
                .thenReturn(null);

        TransferenciaDTO transferencia = new TransferenciaDTO();
        Respuesta<Reporte> respuesta = transferenciaBO.generarComprobanteTransferencia(transferencia);

        Respuesta<Reporte> reporteRespuestaOK = new Respuesta<Reporte>();
        reporteRespuestaOK.setEstadoRespuesta(EstadoRespuesta.OK);
        reporteRespuestaOK.setRespuesta(null);

        Assert.assertNotNull(respuesta);
        assertEquals(reporteRespuestaOK, respuesta);
    }

    /**
     * Realizar transferencia OK.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws CuentaOrigenSinBanelcoException
     *             the cuenta origen sin banelco exception
     */
    @Test
    public void realizarTransferenciaOK() throws DAOException, CuentaOrigenSinBanelcoException {
        // Hacia banco propio
        Mensaje mensajeOk = new Mensaje();
        mensajeOk.setMensaje("<p>¡Listo!</p><p>Tu transferencia a {0} por {1} se realizó con éxito.</p>");
        Respuesta<Mensaje> respuestaMensajeOk = new Respuesta<Mensaje>();
        respuestaMensajeOk.setRespuesta(mensajeOk);

        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setNroCuentaProducto("000000003023003");

        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setHaciaOtroBanco(Boolean.TRUE);
        transferencia.setMoneda(DivisaEnum.PESO);
        transferencia.setImporte(this.IMPORTE_TRANSFERENCIA);
        transferencia.setTitular(this.TITULAR_TRANSFERENCIA);
        transferencia.setCuentaOrigen(cuentaOrigen);
        transferencia.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);

        ContadorIntentos contadorIntentos = new ContadorIntentos();

        transferencia.setNumeroComprobante(this.NUMERO_COMPROBANTE_NO_VACIO);
        Mockito.when(this.mensajeBO.obtenerMensajePorCodigo(Mockito.anyString())).thenReturn(mensajeOk);
        Mockito.when(transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(Mockito.any(Cliente.class),
                Mockito.any(TransferenciaDTO.class), Mockito.any(IndicadorFuncion.class))).thenReturn(transferencia);
        Mockito.when(transferenciaDAO.realizarTransferenciaInmediataRioRio(Mockito.any(Cliente.class),
                Mockito.any(TransferenciaDTO.class))).thenReturn(transferencia);
        Mockito.when(sesionParametros.getContador()).thenReturn(contadorIntentos);
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(new AgendaDestinatarioOutEntity());
        Cliente cliente = new Cliente();
        Respuesta<TransferenciaDTO> respuestaObtenida = transferenciaBO.ejecutarTransferenciaInmediata(cliente,
                transferencia, Boolean.FALSE, Boolean.FALSE);
        Respuesta<TransferenciaDTO> respuestaEsperada = new Respuesta<TransferenciaDTO>();
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaEsperada.setRespuestaVacia(false);
        respuestaEsperada.setRespuesta(transferencia);
        transferencia.setMensaje("<p>¡Listo!</p><p>Tu transferencia a {0} por {1} se realizó con éxito.</p>");
        Assert.assertNotNull(respuestaObtenida);
        assertEquals(respuestaEsperada, respuestaObtenida);

        // Hacia otro banco
        transferencia.setHaciaOtroBanco(Boolean.FALSE);
        respuestaObtenida = transferenciaBO.ejecutarTransferenciaInmediata(cliente, transferencia, Boolean.FALSE,
                Boolean.FALSE);
        Assert.assertNotNull(respuestaObtenida);
        assertEquals(respuestaObtenida, respuestaEsperada);
    }

    /**
     * Realizar transferencia cuenta sin operar exception.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     * @throws CuentaOrigenSinBanelcoException
     *             the cuenta origen sin banelco exception
     */
    @Test
    public void realizarTransferenciaCuentaSinOperarException()
            throws DAOException, BusinessException, CuentaOrigenSinBanelcoException {
        Mensaje mensajeError = new Mensaje();
        mensajeError.setMensaje(
                "<p>¡Lo sentimos!</p><p>No se realizó la transferencia debido a que la cuenta de origen posee más de 180 días sin operar.</p><p>Acercate a tu sucursal o comunicate con Superlínea para más información.</p>");
        Respuesta<Mensaje> respuestaMensajeError = new Respuesta<Mensaje>();
        respuestaMensajeError.setRespuesta(mensajeError);

        IdentificacionCuenta identificacionCuentaDestino = new IdentificacionCuenta("000", "064517");
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setNroCuentaProducto("000000003023003");

        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setHaciaOtroBanco(Boolean.TRUE);
        transferencia.setImporte(new BigDecimal("20"));
        transferencia.setNumeroCuentaDestino(identificacionCuentaDestino);
        transferencia.setCuentaOrigen(new Cuenta());
        transferencia.setMoneda(DivisaEnum.PESO);

        ContadorIntentos contadorIntentos = new ContadorIntentos();
        Mockito.when(this.mensajeBO.obtenerMensajePorCodigo(Mockito.anyString())).thenReturn(mensajeError);
        given(transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(Mockito.any(Cliente.class),
                Mockito.any(TransferenciaDTO.class), Mockito.any(IndicadorFuncion.class)))
                        .willThrow(new CuentaSinOperarException(""));
        Mockito.when(sesionParametros.getContador()).thenReturn(contadorIntentos);

        Mockito.when(mockEstadisticaBO.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class),
                Matchers.any(Cliente.class))).thenReturn(new Estadistica());

        Respuesta<TransferenciaDTO> respuestaObtenida = transferenciaBO.ejecutarTransferenciaInmediata(
                Mockito.mock(Cliente.class), transferencia, Boolean.FALSE, Boolean.FALSE);
        Respuesta<TransferenciaDTO> respuestaEsperada = new Respuesta<TransferenciaDTO>();
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaEsperada.setRespuesta(new TransferenciaDTO());
        respuestaEsperada.setRespuestaVacia(Boolean.TRUE);
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(
                "<p>¡Lo sentimos!</p><p>No se realizó la transferencia debido a que la cuenta de origen posee más de 180 días sin operar.</p><p>Acercate a tu sucursal o comunicate con Superlínea para más información.</p>");
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_SOLO_MENSAJE.getDescripcion());
        respuestaEsperada.add(itemMensajeRespuesta);

        Assert.assertNotNull(respuestaObtenida);
        assertEquals(respuestaEsperada, respuestaObtenida);
    }

    /**
     * Realizar transferencia importe limite exception.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws CuentaOrigenSinBanelcoException
     *             the cuenta origen sin banelco exception
     */
    @Test
    public void realizarTransferenciaImporteLimiteException() throws DAOException, CuentaOrigenSinBanelcoException {
        Mensaje mensajeError = new Mensaje();
        mensajeError.setMensaje(
                "<b>ATENCIÓN</b><p>El importe ingresado es superior al limite diario por cuenta.</p><p>Mas información</p> ");
        Respuesta<Mensaje> respuestaMensajeError = new Respuesta<Mensaje>();
        respuestaMensajeError.setRespuesta(mensajeError);

        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setNroCuentaProducto("000000003023003");
        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setHaciaOtroBanco(Boolean.TRUE);
        transferencia.setImporte(new BigDecimal("20"));
        transferencia.setMoneda(DivisaEnum.PESO);
        transferencia.setCuentaOrigen(cuentaOrigen);
        Mockito.when(this.mensajeBO.obtenerMensajePorCodigo(Mockito.anyString())).thenReturn(mensajeError);
        given(transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(Mockito.any(Cliente.class),
                Mockito.any(TransferenciaDTO.class), Mockito.any(IndicadorFuncion.class)))
                        .willThrow(new ImporteLimiteException(""));
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(new AgendaDestinatarioOutEntity());
        Respuesta<TransferenciaDTO> respuestaObtenida = transferenciaBO.ejecutarTransferenciaInmediata(
                Mockito.mock(Cliente.class), transferencia, Boolean.FALSE, Boolean.FALSE);
        Respuesta<TransferenciaDTO> respuestaEsperada = new Respuesta<TransferenciaDTO>();
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaEsperada.setRespuesta(new TransferenciaDTO());
        respuestaEsperada.setRespuestaVacia(Boolean.TRUE);
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(
                "<b>ATENCIÓN</b><p>El importe ingresado es superior al limite diario por cuenta.</p><p>Mas información</p> ");
        itemMensajeRespuesta.setTag("importe");
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_IMPORTE_LIMITE_TRANSFERENCIA.getDescripcion());
        respuestaEsperada.add(itemMensajeRespuesta);

        // eliminamos el segundo mensaje para el test
        respuestaObtenida.getItemsMensajeRespuesta().remove(1);

        Assert.assertNotNull(respuestaObtenida);
        assertEquals(respuestaEsperada, respuestaObtenida);
    }

    /**
     * Realizar transferencia saldo insuficiente exception.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws CuentaOrigenSinBanelcoException
     *             the cuenta origen sin banelco exception
     */
    @Test
    public void realizarTransferenciaSaldoInsuficienteException() throws DAOException, CuentaOrigenSinBanelcoException {
        Mensaje mensajeError = new Mensaje();
        mensajeError.setMensaje("<b>Atención</b><p>El saldo de la cuenta seleccionada es insuficiente.</p>");
        Respuesta<Mensaje> respuestaMensajeError = new Respuesta<Mensaje>();
        respuestaMensajeError.setRespuesta(mensajeError);
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setNroCuentaProducto("000000003023003");
        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setHaciaOtroBanco(Boolean.TRUE);
        transferencia.setImporte(new BigDecimal("20"));
        transferencia.setMoneda(DivisaEnum.PESO);
        transferencia.setCuentaOrigen(cuentaOrigen);
        Mockito.when(this.mensajeBO.obtenerMensajePorCodigo(Mockito.anyString())).thenReturn(mensajeError);
        given(transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(Mockito.any(Cliente.class),
                Mockito.any(TransferenciaDTO.class), Mockito.any(IndicadorFuncion.class)))
                        .willThrow(new SaldoInsuficienteException(""));
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(new AgendaDestinatarioOutEntity());
        Respuesta<TransferenciaDTO> respuestaObtenida = transferenciaBO.ejecutarTransferenciaInmediata(
                Mockito.mock(Cliente.class), transferencia, Boolean.FALSE, Boolean.FALSE);
        Respuesta<TransferenciaDTO> respuestaEsperada = new Respuesta<TransferenciaDTO>();
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaEsperada.setRespuesta(new TransferenciaDTO());
        respuestaEsperada.setRespuestaVacia(Boolean.TRUE);
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(
                "<b>Atención</b><p>El saldo de la cuenta seleccionada es insuficiente.</p>");
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());
        itemMensajeRespuesta.setTag("cuenta");
        respuestaEsperada.add(itemMensajeRespuesta);

        Assert.assertNotNull(respuestaObtenida);
        assertEquals(respuestaEsperada, respuestaObtenida);
    }

    /**
     * Realizar transferencia transferencia generica exception.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws CuentaOrigenSinBanelcoException
     *             the cuenta origen sin banelco exception
     */
    @Test
    public void realizarTransferenciaTransferenciaGenericaException()
            throws DAOException, CuentaOrigenSinBanelcoException {
        this.mensajeRespuesta.setMensaje("<p>No pudimos {0} la <b>transferencia</b> a {1} por {2}.</p>");

        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setNroCuentaProducto("000000003023003");
        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setHaciaOtroBanco(Boolean.TRUE);
        transferencia.setMoneda(DivisaEnum.PESO);
        transferencia.setImporte(this.IMPORTE_TRANSFERENCIA);
        transferencia.setNombreReceptor(this.TITULAR_TRANSFERENCIA);
        transferencia.setCuentaOrigen(cuentaOrigen);

        ContadorIntentos contadorIntentos = new ContadorIntentos();

        given(transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(Mockito.any(Cliente.class),
                Mockito.any(TransferenciaDTO.class), Mockito.any(IndicadorFuncion.class)))
                        .willThrow(new TransferenciaGenericaException(""));
        Mockito.when(sesionParametros.getContador()).thenReturn(contadorIntentos);
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(new AgendaDestinatarioOutEntity());

        Respuesta<TransferenciaDTO> respuestaObtenida = transferenciaBO.ejecutarTransferenciaInmediata(
                Mockito.mock(Cliente.class), transferencia, Boolean.FALSE, Boolean.FALSE);
        Respuesta<TransferenciaDTO> respuestaEsperada = new Respuesta<TransferenciaDTO>();
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaEsperada.setRespuesta(new TransferenciaDTO());
        respuestaEsperada.setRespuestaVacia(Boolean.TRUE);
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(
                "<p>No pudimos {0} la <b>transferencia</b> a {1} por {2}.</p>");
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_CONOCIDO_TRANSFERENCIA.getDescripcion());
        respuestaEsperada.add(itemMensajeRespuesta);

        Assert.assertNotNull(respuestaObtenida);
        assertEquals(respuestaEsperada, respuestaObtenida);
    }

    /**
     * Realizar transferencia error reintento.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws CuentaOrigenSinBanelcoException
     *             the cuenta origen sin banelco exception
     */
    @Test
    public void realizarTransferenciaErrorReintento() throws DAOException, CuentaOrigenSinBanelcoException {
        this.mensajeRespuesta.setMensaje("<p>No pudimos {0} la <b>transferencia</b> a {1} por {2}.</p>");

        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setNroCuentaProducto("000000003023003");

        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setHaciaOtroBanco(Boolean.TRUE);
        transferencia.setMoneda(DivisaEnum.PESO);
        transferencia.setImporte(this.IMPORTE_TRANSFERENCIA);
        transferencia.setNombreReceptor(this.TITULAR_TRANSFERENCIA);
        transferencia.setCuentaOrigen(cuentaOrigen);

        ContadorIntentos contadorIntentos = new ContadorIntentos();

        given(transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(Mockito.any(Cliente.class),
                Mockito.any(TransferenciaDTO.class), Mockito.any(IndicadorFuncion.class)))
                        .willThrow(new TransferenciaGenericaException(""));
        Mockito.when(sesionParametros.getContador()).thenReturn(contadorIntentos);
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(new AgendaDestinatarioOutEntity());
        transferenciaBO.ejecutarTransferenciaInmediata(Mockito.mock(Cliente.class), transferencia, Boolean.FALSE,
                Boolean.FALSE);

        Respuesta<TransferenciaDTO> respuestaObtenida = transferenciaBO.ejecutarTransferenciaInmediata(
                Mockito.mock(Cliente.class), transferencia, Boolean.FALSE, Boolean.FALSE);
        Respuesta<TransferenciaDTO> respuestaEsperada = new Respuesta<TransferenciaDTO>();
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaEsperada.setRespuesta(new TransferenciaDTO());
        respuestaEsperada.setRespuestaVacia(Boolean.TRUE);
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(
                "<p>No pudimos {0} la <b>transferencia</b> a {1} por {2}.</p>");
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_CONOCIDO_TRANSFERENCIA.getDescripcion());
        respuestaEsperada.add(itemMensajeRespuesta);

        Assert.assertNotNull(respuestaObtenida);
        assertEquals(respuestaEsperada, respuestaObtenida);
    }

    /**
     * Realizar transferencia DAO exception.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws CuentaOrigenSinBanelcoException
     *             the cuenta origen sin banelco exception
     */
    @Test
    public void realizarTransferenciaDAOException() throws DAOException, CuentaOrigenSinBanelcoException {
        this.mensajeRespuesta.setMensaje("<p>No pudimos {0} la <b>transferencia</b> a {1} por {2}.</p>");
        ContadorIntentos contador = new ContadorIntentos();
        RegistroSesion registroSesion = new RegistroSesion();

        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(this.sesionParametros.getContador()).thenReturn(contador);
        Mockito.when(this.sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        try {
            Mockito.when(mockEstadisticaBO.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class),
                    Matchers.any(Cliente.class))).thenReturn(new Estadistica());
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        given(transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(Mockito.any(Cliente.class),
                Mockito.any(TransferenciaDTO.class), Mockito.any(IndicadorFuncion.class)))
                        .willThrow(new DAOException());
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(new AgendaDestinatarioOutEntity());
        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setMoneda(DivisaEnum.PESO);
        transferencia.setImporte(this.IMPORTE_TRANSFERENCIA);
        transferencia.setNombreReceptor(this.TITULAR_TRANSFERENCIA);
        transferencia.setHaciaOtroBanco(true);
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("123123123");
        transferencia.setCuentaOrigen(cuenta);

        Respuesta<TransferenciaDTO> respuestaObtenida = transferenciaBO.ejecutarTransferenciaInmediata(
                Mockito.mock(Cliente.class), transferencia, Boolean.FALSE, Boolean.FALSE);
        Respuesta<TransferenciaDTO> respuestaEsperada = new Respuesta<TransferenciaDTO>();
        respuestaEsperada.setRespuesta(new TransferenciaDTO());
        respuestaEsperada.setRespuestaVacia(Boolean.TRUE);
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.WARNING);
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(
                "<p>No pudimos {0} la <b>transferencia</b> a {1} por {2}.</p>");
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_CONOCIDO_TRANSFERENCIA.getDescripcion());
        respuestaEsperada.add(itemMensajeRespuesta);
        Assert.assertNotNull(respuestaObtenida);
        assertEquals(respuestaEsperada, respuestaObtenida);
    }

    /**
     * Realizar transferencia cuenta alfa no verificada nunca opero exception.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws CuentaOrigenSinBanelcoException
     *             the cuenta origen sin banelco exception
     */
    @Test
    public void realizarTransferenciaCuentaAlfaNoVerificadaNuncaOperoException() throws DAOException, CuentaOrigenSinBanelcoException {
        this.mensajeRespuesta.setMensaje("<p><strong>Para operar en dólares es necesario que completes la apertura de tu cuenta.</strong></p><p>Cuando se generó el alta de tu cuenta sueldo te enviamos un email, SMS o notificación por la App. Por favor, buscá alguno de estos mensajes y seguí los pasos para que puedas terminar la apertura de tu cuenta 100% online. Si no encontrás ninguno de estos mensajes, podés sacar un turno y acercarte a la sucursal más cercana con tu DNI.</p>");

        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setNroCuentaProducto("000000003023003");
        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setHaciaOtroBanco(Boolean.TRUE);
        transferencia.setMoneda(DivisaEnum.PESO);
        transferencia.setImporte(this.IMPORTE_TRANSFERENCIA);
        transferencia.setNombreReceptor(this.TITULAR_TRANSFERENCIA);
        transferencia.setCuentaOrigen(cuentaOrigen);
      
        given(transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(Mockito.any(Cliente.class),
                Mockito.any(TransferenciaDTO.class), Mockito.any(IndicadorFuncion.class)))
                        .willThrow(new CuentaAlfaNoVerificadaNuncaOperoUsdException(""));
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(new AgendaDestinatarioOutEntity());

        Respuesta<TransferenciaDTO> respuestaObtenida = transferenciaBO.ejecutarTransferenciaInmediata(
                Mockito.mock(Cliente.class), transferencia, Boolean.FALSE, Boolean.FALSE);
        Respuesta<TransferenciaDTO> respuestaEsperada = new Respuesta<TransferenciaDTO>();
        respuestaEsperada.setRespuesta(new TransferenciaDTO());
        respuestaEsperada.setRespuestaVacia(Boolean.TRUE);
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.WARNING);
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(
                "<p><strong>Para operar en dólares es necesario que completes la apertura de tu cuenta.</strong></p><p>Cuando se generó el alta de tu cuenta sueldo te enviamos un email, SMS o notificación por la App. Por favor, buscá alguno de estos mensajes y seguí los pasos para que puedas terminar la apertura de tu cuenta 100% online. Si no encontrás ninguno de estos mensajes, podés sacar un turno y acercarte a la sucursal más cercana con tu DNI.</p>");
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_OPERACION_INHABILITADA.getDescripcion());
        itemMensajeRespuesta.setTag("cuenta");
        respuestaEsperada.add(itemMensajeRespuesta);

        Assert.assertNotNull(respuestaObtenida);
        assertEquals(respuestaEsperada, respuestaObtenida);
    }

    /**
     * Realizar transferencia cuenta alfa no verificada exception.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws CuentaOrigenSinBanelcoException
     *             the cuenta origen sin banelco exception
     */
    @Test
    public void realizarTransferenciaCuentaAlfaNoVerificadaException() throws DAOException, CuentaOrigenSinBanelcoException {
        this.mensajeRespuesta.setMensaje("<p><strong>Te recordamos que es requisito normativo que completes la apertura de tu cuenta para seguir operando en dólares de manera habitual. </strong></p><p>Por tal motivo, cuando se generó el alta de tu cuenta sueldo te enviamos un email, SMS o notificación por la App. Por favor, buscá alguno de estos mensajes y seguí los pasos para que puedas terminar la apertura de cuenta 100% online. Si no encontrás ninguno de estos mensajes, podés sacar un turno y acercarte a la sucursal más cercana con tu DNI.</p>");

        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setNroCuentaProducto("000000003023003");
        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setHaciaOtroBanco(Boolean.TRUE);
        transferencia.setMoneda(DivisaEnum.PESO);
        transferencia.setImporte(this.IMPORTE_TRANSFERENCIA);
        transferencia.setNombreReceptor(this.TITULAR_TRANSFERENCIA);
        transferencia.setCuentaOrigen(cuentaOrigen);

        given(transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(Mockito.any(Cliente.class),
                Mockito.any(TransferenciaDTO.class), Mockito.any(IndicadorFuncion.class)))
                        .willThrow(new CuentaAlfaNoVerificadaException(""));
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class)))
                .thenReturn(new AgendaDestinatarioOutEntity());

        Respuesta<TransferenciaDTO> respuestaObtenida = transferenciaBO.ejecutarTransferenciaInmediata(
                Mockito.mock(Cliente.class), transferencia, Boolean.FALSE, Boolean.FALSE);
        Respuesta<TransferenciaDTO> respuestaEsperada = new Respuesta<TransferenciaDTO>();
        respuestaEsperada.setRespuesta(new TransferenciaDTO());
        respuestaEsperada.setRespuestaVacia(Boolean.TRUE);
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.WARNING);
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta(
                "<p><strong>Te recordamos que es requisito normativo que completes la apertura de tu cuenta para seguir operando en dólares de manera habitual. </strong></p><p>Por tal motivo, cuando se generó el alta de tu cuenta sueldo te enviamos un email, SMS o notificación por la App. Por favor, buscá alguno de estos mensajes y seguí los pasos para que puedas terminar la apertura de cuenta 100% online. Si no encontrás ninguno de estos mensajes, podés sacar un turno y acercarte a la sucursal más cercana con tu DNI.</p>");
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_OPERACION_INHABILITADA.getDescripcion());
        itemMensajeRespuesta.setTag("cuenta");
        respuestaEsperada.add(itemMensajeRespuesta);

        Assert.assertNotNull(respuestaObtenida);
        assertEquals(respuestaEsperada, respuestaObtenida);
    }

    /**
     * Realizar transferencia rio OK agendamiento automatico OK test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws CuentaOrigenSinBanelcoException
     *             the cuenta origen sin banelco exception
     */
    @Test
    public void realizarTransferenciaRioOKAgendamientoAutomaticoOKTest()
            throws DAOException, CuentaOrigenSinBanelcoException {
        // Given
        Mensaje mensajeOk = new Mensaje();
        mensajeOk.setMensaje(
                "<p> La <b>transferencia</b> a <b>{0}</b> por <b>{1}</b> fue {2} con éxito. La próxima vez que quieras transferirle a este destinatario podrás encontrarlo tu agenda de destinatarios.");
        Respuesta<Mensaje> respuestaMensajeOk = new Respuesta<Mensaje>();
        respuestaMensajeOk.setRespuesta(mensajeOk);

        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setNroCuentaProducto("000000003023003");

        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setHaciaOtroBanco(Boolean.TRUE);
        transferencia.setMoneda(DivisaEnum.PESO);
        transferencia.setImporte(this.IMPORTE_TRANSFERENCIA);
        transferencia.setTitular(this.TITULAR_TRANSFERENCIA);
        transferencia.setCuentaOrigen(cuentaOrigen);
        transferencia.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);

        Cliente cliente = new Cliente();
        ContadorIntentos contadorIntentos = new ContadorIntentos();

        transferencia.setNumeroComprobante(this.NUMERO_COMPROBANTE_NO_VACIO);

        AgendaDestinatarioOutEntity agenda = new AgendaDestinatarioOutEntity();
        agenda.setCodigoRetornoExtendido("00000000");

        // When
        Mockito.when(this.mensajeBO.obtenerMensajePorCodigo(Mockito.anyString())).thenReturn(mensajeOk);
        Mockito.when(transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(Mockito.any(Cliente.class),
                Mockito.any(TransferenciaDTO.class), Mockito.any(IndicadorFuncion.class))).thenReturn(transferencia);
        Mockito.when(transferenciaDAO.realizarTransferenciaInmediataRioRio(Mockito.any(Cliente.class),
                Mockito.any(TransferenciaDTO.class))).thenReturn(transferencia);
        Mockito.when(sesionParametros.getContador()).thenReturn(contadorIntentos);
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenReturn(agenda);

        // Then
        Respuesta<TransferenciaDTO> respuestaObtenida = transferenciaBO.ejecutarTransferenciaInmediata(cliente,
                transferencia, Boolean.FALSE, Boolean.FALSE);

        // Expected
        Assert.assertNotNull(respuestaObtenida);
        assertEquals(EstadoRespuesta.OK, respuestaObtenida.getEstadoRespuesta());
    }

    /**
     * Realizar transferencia rio OK agendamiento automatico DAO exception test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws CuentaOrigenSinBanelcoException
     *             the cuenta origen sin banelco exception
     */
    @Test
    public void realizarTransferenciaRioOKAgendamientoAutomaticoDAOExceptionTest()
            throws DAOException, CuentaOrigenSinBanelcoException {
        // Given
        Mensaje mensajeOk = new Mensaje();
        mensajeOk.setMensaje(
                "<p> La <b>transferencia</b> a <b>{0}</b> por <b>{1}</b> fue {2} con éxito. La próxima vez que quieras transferirle a este destinatario podrás encontrarlo tu agenda de destinatarios.");
        Respuesta<Mensaje> respuestaMensajeOk = new Respuesta<Mensaje>();
        respuestaMensajeOk.setRespuesta(mensajeOk);

        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setNroCuentaProducto("000000003023003");

        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setHaciaOtroBanco(Boolean.TRUE);
        transferencia.setMoneda(DivisaEnum.PESO);
        transferencia.setImporte(this.IMPORTE_TRANSFERENCIA);
        transferencia.setTitular(this.TITULAR_TRANSFERENCIA);
        transferencia.setCuentaOrigen(cuentaOrigen);
        transferencia.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);

        Cliente cliente = new Cliente();
        ContadorIntentos contadorIntentos = new ContadorIntentos();

        transferencia.setNumeroComprobante(this.NUMERO_COMPROBANTE_NO_VACIO);

        DAOException daoException = new DAOException("DAO Exception!");

        // When
        Mockito.when(this.mensajeBO.obtenerMensajePorCodigo(Mockito.anyString())).thenReturn(mensajeOk);
        Mockito.when(transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(Mockito.any(Cliente.class),
                Mockito.any(TransferenciaDTO.class), Mockito.any(IndicadorFuncion.class))).thenReturn(transferencia);
        Mockito.when(transferenciaDAO.realizarTransferenciaInmediataRioRio(Mockito.any(Cliente.class),
                Mockito.any(TransferenciaDTO.class))).thenReturn(transferencia);
        Mockito.when(sesionParametros.getContador()).thenReturn(contadorIntentos);
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenThrow(daoException);

        // Then
        Respuesta<TransferenciaDTO> respuestaObtenida = transferenciaBO.ejecutarTransferenciaInmediata(cliente,
                transferencia, Boolean.FALSE, Boolean.FALSE);

        // Expected
        Assert.assertNotNull(respuestaObtenida);
        assertEquals(EstadoRespuesta.OK, respuestaObtenida.getEstadoRespuesta());
    }

    /**
     * Realizar transferencia error reintento agendamiento OK test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws CuentaOrigenSinBanelcoException
     *             the cuenta origen sin banelco exception
     */
    @Test
    public void realizarTransferenciaErrorReintentoAgendamientoOKTest()
            throws DAOException, CuentaOrigenSinBanelcoException {
        // Given
        this.mensajeRespuesta.setMensaje("<p>No pudimos {0} la <b>transferencia</b> a {1} por {2}.</p>");

        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setNroCuentaProducto("000000003023003");

        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setHaciaOtroBanco(Boolean.TRUE);
        transferencia.setMoneda(DivisaEnum.PESO);
        transferencia.setImporte(this.IMPORTE_TRANSFERENCIA);
        transferencia.setNombreReceptor(this.TITULAR_TRANSFERENCIA);
        transferencia.setCuentaOrigen(cuentaOrigen);

        ContadorIntentos contadorIntentos = new ContadorIntentos();

        AgendaDestinatarioOutEntity agenda = new AgendaDestinatarioOutEntity();
        agenda.setCodigoRetornoExtendido("00000000");

        given(transferenciaDAO.ejecutarTransferenciaInmediataOtrosBancos(Mockito.any(Cliente.class),
                Mockito.any(TransferenciaDTO.class), Mockito.any(IndicadorFuncion.class)))
                        .willThrow(new TransferenciaGenericaException(""));

        // When
        Mockito.when(sesionParametros.getContador()).thenReturn(contadorIntentos);
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenReturn(agenda);
        Mockito.when(altaDestinatarioBO.existeDestinatarioVacio(Matchers.any(AgendaDestinatarioInEntity.class)))
                .thenReturn(Boolean.FALSE);

        // Then
        Respuesta<TransferenciaDTO> respuestaObtenida = transferenciaBO.ejecutarTransferenciaInmediata(
                Mockito.mock(Cliente.class), transferencia, Boolean.FALSE, Boolean.FALSE);

        // Expected
        Assert.assertNotNull(respuestaObtenida);
        assertEquals(EstadoRespuesta.WARNING, respuestaObtenida.getEstadoRespuesta());
        assertEquals(TipoError.ERROR_CONOCIDO_TRANSFERENCIA.getDescripcion(),
                respuestaObtenida.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Ejecutar transferencia programada OK agendamiento destinatario OK test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarTransferenciaProgramadaOKAgendamientoDestinatarioOKTest() throws DAOException {
        // Given
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
        transferenciaDTO.setNombreReceptor("Nombre receptor");
        transferenciaDTO.setImporte(BigDecimal.valueOf(123));
        transferenciaDTO.setIsFromAgendaDestinatario(Boolean.FALSE);
        transferenciaDTO.setRiesgoAlto(Boolean.FALSE);
        transferenciaDTO.setHaciaOtroBanco(Boolean.FALSE);
        transferenciaDTO.setHaciaCuentaPropia(Boolean.FALSE);
        IdentificacionCuenta numeroCuentaDestino = new IdentificacionCuenta();
        numeroCuentaDestino.setNroCuentaProducto("000880501763");
        numeroCuentaDestino.setNroSucursal("088");
        transferenciaDTO.setNumeroCuentaDestino(numeroCuentaDestino);
        transferenciaDTO.setCbuCuenta("1234567890123456789012");
        transferenciaDTO.setMoneda(DivisaEnum.PESO);
        transferenciaDTO.setNumeroComprobante("1234343543");
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setNroCuentaProducto("000844256287");
        transferenciaDTO.setCuentaOrigen(cuentaOrigen);
        transferenciaDTO.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        DestinatarioDTO destinatarioDTO = new DestinatarioDTO();
        destinatarioDTO.setBancoReceptor(BancoEnum.SANTANDER_RIO.getDescripcion());
        destinatarioDTO.setCuentaDestinoBanelco("088-050176/3");
        destinatarioDTO.setCuit("20076042080");
        destinatarioDTO.setCuit2("");
        destinatarioDTO.setCuit3("");
        destinatarioDTO.setLongCuentaDestinoBanelco("5");
        destinatarioDTO.setTipoDeCuentaFromBanelco(TipoCuentaBanelco.TIPO_00);
        destinatarioDTO.setTipoDeCuentaToBanelco(TipoCuentaBanelco.TIPO_02);
        destinatarioDTO.setBancoDestino(BancoEnum.SANTANDER_RIO.getDescripcion());
        destinatarioDTO.setTitular("Abad Perez Agonal Braulio    ");
        destinatarioDTO.setUser("");
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1167");
        mensaje.setMensaje(
                "<p> La <b>transferencia</b> a <b>Nombre receptor</b> por <b>$ 123,00</b> fue programada con éxito. La próxima vez que quieras transferirle a este destinatario podrás encontrarlo tu agenda de destinatarios.");
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity.setCodigoRetornoExtendido("00000000");
        Cliente cliente = new Cliente();
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("1232433453454");

        // When
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
                Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(sesionParametros.getDestinatarioView()).thenReturn(destinatarioDTO);
        Mockito.when(transferenciaDAO.ejecutarAgendarTransferenciaProgramada(Matchers.any(TransferenciaDTO.class),
                Matchers.any(Cliente.class))).thenReturn(transferenciaDTO);
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenReturn(outEntity);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(altaDestinatarioBO.existeDestinatarioVacio(Matchers.any(AgendaDestinatarioInEntity.class)))
                .thenReturn(Boolean.FALSE);

        // Then
        Respuesta<TransferenciaDTO> resp = transferenciaBO.ejecutarTransferenciaProgramada(cliente, transferenciaDTO,
                false, true);

        // Expected
        Assert.assertNotNull(resp);
        assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());
        assertEquals(mensaje.getMensaje(), resp.getRespuesta().getMensaje());
    }

    /**
     * Ejecutar transferencia programada OK agendamiento destinatario error test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarTransferenciaProgramadaOKAgendamientoDestinatarioErrorTest() throws DAOException {
        // Given
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
        transferenciaDTO.setNombreReceptor("Nombre receptor");
        transferenciaDTO.setImporte(BigDecimal.valueOf(123));
        transferenciaDTO.setIsFromAgendaDestinatario(Boolean.FALSE);
        transferenciaDTO.setRiesgoAlto(Boolean.FALSE);
        transferenciaDTO.setHaciaOtroBanco(Boolean.FALSE);
        transferenciaDTO.setHaciaCuentaPropia(Boolean.FALSE);
        IdentificacionCuenta numeroCuentaDestino = new IdentificacionCuenta();
        numeroCuentaDestino.setNroCuentaProducto("000880501763");
        numeroCuentaDestino.setNroSucursal("088");
        transferenciaDTO.setNumeroCuentaDestino(numeroCuentaDestino);
        transferenciaDTO.setCbuCuenta("1234567890123456789012");
        transferenciaDTO.setMoneda(DivisaEnum.PESO);
        transferenciaDTO.setNumeroComprobante("1234343543");
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setNroCuentaProducto("000844256287");
        transferenciaDTO.setCuentaOrigen(cuentaOrigen);
        transferenciaDTO.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        DestinatarioDTO destinatarioDTO = new DestinatarioDTO();
        destinatarioDTO.setBancoReceptor(BancoEnum.SANTANDER_RIO.getDescripcion());
        destinatarioDTO.setCuentaDestinoBanelco("088-050176/3");
        destinatarioDTO.setCuit("20076042080");
        destinatarioDTO.setCuit2("");
        destinatarioDTO.setCuit3("");
        destinatarioDTO.setLongCuentaDestinoBanelco("5");
        destinatarioDTO.setTipoDeCuentaFromBanelco(TipoCuentaBanelco.TIPO_00);
        destinatarioDTO.setTipoDeCuentaToBanelco(TipoCuentaBanelco.TIPO_02);
        destinatarioDTO.setBancoDestino(BancoEnum.SANTANDER_RIO.getDescripcion());
        destinatarioDTO.setTitular("Abad Perez Agonal Braulio    ");
        destinatarioDTO.setUser("");
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1167");
        mensaje.setMensaje(
                "<p> La <b>transferencia</b> a <b>Nombre receptor</b> por <b>$ 123,00</b> fue programada con éxito. La próxima vez que quieras transferirle a este destinatario podrás encontrarlo tu agenda de destinatarios.");
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity.setCodigoRetornoExtendido("00001111");
        Cliente cliente = new Cliente();
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("1232433453454");

        // When
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
                Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(sesionParametros.getDestinatarioView()).thenReturn(destinatarioDTO);
        Mockito.when(transferenciaDAO.ejecutarAgendarTransferenciaProgramada(Matchers.any(TransferenciaDTO.class),
                Matchers.any(Cliente.class))).thenReturn(transferenciaDTO);
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenThrow(new DAOException());
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(altaDestinatarioBO.existeDestinatarioVacio(Matchers.any(AgendaDestinatarioInEntity.class)))
                .thenReturn(Boolean.FALSE);

        // Then
        Respuesta<TransferenciaDTO> resp = transferenciaBO.ejecutarTransferenciaProgramada(cliente, transferenciaDTO,
                false, true);

        // Expected
        Assert.assertNotNull(resp);
        assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());
        assertEquals(mensaje.getMensaje(), resp.getRespuesta().getMensaje());
    }

    /**
     * Ejecutar transferencia programada error sin reintentos agendamiento
     * destinatario OK test.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void ejecutarTransferenciaProgramadaErrorSinReintentosAgendamientoDestinatarioOKTest() throws DAOException {
        // Given
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
        transferenciaDTO.setNombreReceptor("Nombre receptor");
        transferenciaDTO.setImporte(BigDecimal.valueOf(123));
        transferenciaDTO.setIsFromAgendaDestinatario(Boolean.FALSE);
        transferenciaDTO.setRiesgoAlto(Boolean.FALSE);
        transferenciaDTO.setHaciaOtroBanco(Boolean.FALSE);
        transferenciaDTO.setHaciaCuentaPropia(Boolean.FALSE);
        IdentificacionCuenta numeroCuentaDestino = new IdentificacionCuenta();
        numeroCuentaDestino.setNroCuentaProducto("000880501763");
        numeroCuentaDestino.setNroSucursal("088");
        transferenciaDTO.setNumeroCuentaDestino(numeroCuentaDestino);
        transferenciaDTO.setCbuCuenta("1234567890123456789012");
        transferenciaDTO.setMoneda(DivisaEnum.PESO);
        transferenciaDTO.setNumeroComprobante("1234343543");
        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setNroCuentaProducto("000844256287");
        transferenciaDTO.setCuentaOrigen(cuentaOrigen);
        transferenciaDTO.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        DestinatarioDTO destinatarioDTO = new DestinatarioDTO();
        destinatarioDTO.setBancoReceptor(BancoEnum.SANTANDER_RIO.getDescripcion());
        destinatarioDTO.setCuentaDestinoBanelco("088-050176/3");
        destinatarioDTO.setCuit("20076042080");
        destinatarioDTO.setCuit2("");
        destinatarioDTO.setCuit3("");
        destinatarioDTO.setLongCuentaDestinoBanelco("5");
        destinatarioDTO.setTipoDeCuentaFromBanelco(TipoCuentaBanelco.TIPO_00);
        destinatarioDTO.setTipoDeCuentaToBanelco(TipoCuentaBanelco.TIPO_02);
        destinatarioDTO.setBancoDestino(BancoEnum.SANTANDER_RIO.getDescripcion());
        destinatarioDTO.setTitular("Abad Perez Agonal Braulio    ");
        destinatarioDTO.setUser("");
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1167");
        mensaje.setMensaje(
                "<p>No pudimos programar la <b>transferencia</b> a Abad Perez Agonal Braulio por $ 123,00.</p> Por favor, intentá nuevamente en unos minutos. La próxima vez que quieras transferirle a este destinatario podrás encontrarlo desde tu agenda de destinatarios.");
        AgendaDestinatarioOutEntity outEntity = new AgendaDestinatarioOutEntity();
        outEntity.setCodigoRetornoExtendido("00000000");
        Cliente cliente = new Cliente();
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("1232433453454");

        // When
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
                Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(sesionParametros.getDestinatarioView()).thenReturn(destinatarioDTO);
        Mockito.when(transferenciaDAO.ejecutarAgendarTransferenciaProgramada(Matchers.any(TransferenciaDTO.class),
                Matchers.any(Cliente.class))).thenThrow(new DAOException("DAO Exception!!"));
        Mockito.when(agendaDestinatarioDAO.eliminarUAgregar(Matchers.any(AgendaDestinatarioInEntity.class),
                Matchers.anyString(), Matchers.any(TipoOperacionACTAGEDESTEnum.class))).thenReturn(outEntity);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(),
                Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(altaDestinatarioBO.existeDestinatarioVacio(Matchers.any(AgendaDestinatarioInEntity.class)))
                .thenReturn(Boolean.TRUE);

        // Then
        Respuesta<TransferenciaDTO> resp = transferenciaBO.ejecutarTransferenciaProgramada(cliente, transferenciaDTO,
                false, true);

        // Expected
        Assert.assertNotNull(resp);
        assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
        assertEquals(mensaje.getMensaje(), resp.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void seteoIpSinPuntosXmlRequestEntityTestOK(){
        String ip = "192.168.10.1";
        XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
        parametros.setiPMaquina(ip);
        assertEquals("192168010001", parametros.getiPMaquina());
    }
    @Test
    public void seteoIpSinPuntosXmlRequestEntityTestFail(){
        String ipNueveDigito = "192.168.10.1";
        XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
        parametros.setiPMaquina(ipNueveDigito);
        assertNotEquals("192168101", parametros.getiPMaquina());
    }
    @Test
    public void seteoIpSinPuntosXmlRequestEntityOchoDigitosTestOK(){
        String ipOchoDigitos = "192.168.1.1";
        XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
        parametros.setiPMaquina(ipOchoDigitos);
        assertEquals("192168001001", parametros.getiPMaquina());
    }

    @Test
    public void seteoIpSinPuntosXmlRequestEntityDoceDigitosTestOK(){
        String ipDoceDigitos = "192.168.102.200";
        XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
        parametros.setiPMaquina(ipDoceDigitos);
        assertEquals("192168102200", parametros.getiPMaquina());
    }

}
