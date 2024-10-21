package ar.com.santanderrio.obp.servicios.transferencias.manager;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.DestinatarioAgendaDTO;
import ar.com.santanderrio.obp.servicios.api.accounts.AccountsApi;
import ar.com.santanderrio.obp.servicios.biocatch.BiocatchManager;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchTransferInfoDTO;
import ar.com.santanderrio.obp.servicios.biocatch.model.ActivityName;
import ar.com.santanderrio.obp.servicios.biocatch.model.ActivityType;
import ar.com.santanderrio.obp.servicios.api.transfers.scoring.ScoringApi;
import ar.com.santanderrio.obp.servicios.clientes.web.manager.ClienteManager;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ScompBO;
import ar.com.santanderrio.obp.servicios.comprobantes.entities.AltaComprobanteRequestBuilder;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaGenericRequestData;
import ar.com.santanderrio.obp.servicios.transferencias.bo.DestinatariosFrecuentesBO;
import ar.com.santanderrio.obp.servicios.transferencias.bo.MetricBuilder;
import ar.com.santanderrio.obp.servicios.transferencias.bo.MetricRegisterBO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.DatosTransferenciaDestino;
import ar.com.santanderrio.obp.servicios.transferencias.entities.DestinatariosFrecuentesDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.MetricTag;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.MetricType;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.MetricWithTags;
import ar.com.santanderrio.obp.servicios.transferencias.entities.metricas.TransferMetricInfoDTO;
import ar.com.santanderrio.obp.servicios.transferencias.feature.AccountEntityFeature;
import ar.com.santanderrio.obp.servicios.transferencias.feature.BiocatchResponseDataDTOFeature;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
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
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.bo.impl.ContratoBOImpl;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.TipoDeCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.CuentaManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaRiesgoTransferenciaDTO;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.impl.RsaManagerImpl;
import ar.com.santanderrio.obp.servicios.tarjetacoordenada.manager.impl.TarjetaCoordenadaManagerImpl;
import ar.com.santanderrio.obp.servicios.transferencias.bo.impl.TransferenciaBOImpl;
import ar.com.santanderrio.obp.servicios.transferencias.entities.PlazoAcreditacion;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.TransferenciaManager;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.impl.TransferenciaManagerImpl;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConceptoView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/**
 * Test para {@link TransferenciaManager}. Nueva factorización de solicitud.
 *
 * @author federico.juliano
 * @author Manuel.Vargas B041299
 * @since Jun 2 2017
 */
@RunWith(MockitoJUnitRunner.class)
public class TransferenciaManagerTest {

    /** The transferencia manager. */
    @InjectMocks
    private TransferenciaManagerImpl transferenciaManager;

    @Mock
    private MensajeBO mensajeBO;
    
    /** The mock transferencia BO. */
    @Mock
    private TransferenciaBOImpl mockTransferenciaBO;

    @Mock
    private MetricRegisterBO mockMetricRegisterBO;

    @Mock
    private MetricBuilder mockMetricBuilder;

    @Mock
    private DestinatariosFrecuentesBO mockDestinatariosFrecuentesBO;

    @Mock
    private AutentificacionManager mockAutentificacionManager;

    @Mock
    private BiocatchManager mockBiocatchManager;

    @Mock
    private AccountsApi mockAccountsApi;

    @Mock
    private ClienteManager mockClienteManager;

    /** The mock sesion parametros. */
    @Mock
    private SesionParametros mockSesionParametros;

    /** The mock cuenta manager. */
    @Mock
    private CuentaManagerImpl mockCuentaManager;

    /** The mock estadistica manager. */
    @Mock
    private EstadisticaManagerImpl mockEstadisticaManager;

    /** The mock sesion cliente. */
    @Mock
    private SesionCliente mockSesionCliente;

    /** The mock rsa manager. */
    @Mock
    private RsaManagerImpl mockRsaManager;

    /** The mock mensaje manager. */
    @Mock
    private MensajeManager mockMensajeManager;

    /** The transferencia view. */
    private TransferenciaView transferenciaView;

    /** The mock tarjeta coordenada manager. */
    @Mock
    private TarjetaCoordenadaManagerImpl mockTarjetaCoordenadaManager;

    @Mock
    private ScompBO altaBO;

    /** The contratos BO. */
    @Mock
    private ContratoBOImpl contratosBO;

    /** The mock identificacion cuenta. */
    @Mock
    private IdentificacionCuenta mockIdentificacionCuenta;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory;

    /** The transferencia respuesta OK. */
    Respuesta<TransferenciaView> transferenciaRespuestaOK = new Respuesta<TransferenciaView>();

    /** The transferencia respuesta ERROR. */
    Respuesta<TransferenciaView> transferenciaRespuestaERROR = new Respuesta<TransferenciaView>();

    /** The transferencia respuesta WARNING. */
    Respuesta<TransferenciaView> transferenciaRespuestaWARNING = new Respuesta<TransferenciaView>();

    /** The transferencia. */
    TransferenciaDTO transferencia = new TransferenciaDTO();

    /** The cbu no rio ok. */
    private String CBU_NO_RIO_OK = "0070002330004402734562";

    /** The importe transferencia. */
    private BigDecimal IMPORTE_TRANSFERENCIA = new BigDecimal(300.23);

    /** The titular transferencia. */
    private String TITULAR_TRANSFERENCIA = "Juan Jose Transferencia";

    String mensajeERROR_EN_SERVICIO_TIMEOUT_CUENTA = "<p>No pudimos {0} la <b>transferencia</b> a {1} por {2}.</p>";

    /** The mensaje respuesta. */
    private Mensaje mensajeRespuesta = new Mensaje();

    /** The mock mensaje BO. */
    @Mock
    private MensajeBO mockMensajeBO;

    @Mock
    private Cliente mockCliente;

    List<Cuenta> listaCuentas = new ArrayList<Cuenta>();

    @Mock
    private SesionParametros sesionParametros;

    @Mock
    private ScoringApi scoringApi;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        Respuesta<CuentasView> respuestaCuentasViewOk = new Respuesta<CuentasView>();
        CuentasView cuentasView = new CuentasView();
        List<CuentasAdhesionDebitoView> listCuentasView = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView cuentaView1 = new CuentasAdhesionDebitoView();
        CuentasAdhesionDebitoView cuentaView2 = new CuentasAdhesionDebitoView();

        cuentaView1.setIsCerrada(false);
        listCuentasView.add(cuentaView1);
        cuentaView2.setIsCerrada(false);
        listCuentasView.add(cuentaView2);
        cuentasView.setCuentas(listCuentasView);

        respuestaCuentasViewOk.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaCuentasViewOk.setRespuesta(cuentasView);

        transferenciaRespuestaERROR.setEstadoRespuesta(EstadoRespuesta.ERROR);
        transferenciaRespuestaERROR.setRespuestaVacia(false);

        transferenciaRespuestaWARNING.setEstadoRespuesta(EstadoRespuesta.WARNING);
        transferenciaRespuestaWARNING.setRespuestaVacia(true);

        this.transferencia.setTitular(this.TITULAR_TRANSFERENCIA);
        this.transferencia.setImporte(this.IMPORTE_TRANSFERENCIA);
        this.transferencia.setMoneda(DivisaEnum.PESO);

        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        respuestaMensaje.setRespuesta(this.mensajeRespuesta);

        Mockito.when(this.mockMensajeManager.obtenerMensajePorCodigo(Mockito.anyString()))
                .thenReturn(this.mensajeRespuesta);

        Mockito.when(scoringApi.getScoring(Mockito.anyString())).thenReturn(1F);

        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        Cuenta cuenta3 = new Cuenta();
        cuenta1.setEstadoTarjetaCredito("01");
        cuenta1.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta1.setTipoCuenta("01");
        cuenta1.setNroSucursal("152");
        cuenta1.setNroTarjetaCredito("4517660024736620");
        cuenta1.setNroCuentaProducto("0000000000639170");
        cuenta2.setEstadoTarjetaCredito("01");
        cuenta2.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cuenta2.setTipoCuenta("02");
        cuenta2.setNroSucursal("152");
        cuenta2.setNroTarjetaCredito("4517660024736620");
        cuenta2.setEstadoTarjetaCredito("01");
        cuenta3.setEstadoTarjetaCredito("01");
        cuenta3.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta3.setTipoCuenta("01");
        cuenta3.setNroSucursal("152");
        cuenta3.setNroTarjetaCredito("4517660024736620");
        cuenta3.setNroCuentaProducto("0000000000639170");
        cuenta3.setEstadoTarjetaCredito("01");

        listaCuentas.add(cuenta1);
        listaCuentas.add(cuenta2);
        listaCuentas.add(cuenta3);
        mockCliente.setCuentas(listaCuentas);
    }

    /**
     * Test para TransferenciaManager#ejecutarNuevaTransferencia().
     * Transferencia Programada.
     * 
     * @author florencia.n.martinez
     * @throws BusinessException
     */
    @Ignore
    @Test
    public void ejecutarNuevaTransferenciaProgramadaOKTest() throws BusinessException, DAOException {
        // Given
        Cliente cliente = new Cliente();
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        cuenta1.setEstadoTarjetaCredito("01");
        cuenta1.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta1.setNroSucursal("152");
        cuenta1.setNroTarjetaCredito("4517660024736620");
        cuenta1.setNroCuentaProducto("0000000000639170");
        cuenta1.setTipoCuenta("01");

        cuenta2.setEstadoTarjetaCredito("01");
        cuenta2.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cuenta2.setNroSucursal("152");
        cuenta2.setNroTarjetaCredito("4517660024736620");
        List<Cuenta> lista = new ArrayList<Cuenta>();
        lista.add(cuenta1);
        lista.add(cuenta2);
        cliente.setCuentas(lista);

        TransferenciaView transferenciaView = obtenerTransferenciaView();
        transferenciaView.setFechaEjecucion("07/06/2030");
        transferenciaView.setTipoCuentaDestino("Caja de Ahorro en Pesos");
        transferenciaView.setIsRioRio(true);
        transferenciaView.setNroCuentaDestino("000-063917/0");
        transferenciaView.setMoneda("peso");
        transferenciaView.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        transferenciaView.setMensaje("<p> La <b>transferencia</b> a <b>Nombre receptor</b> por <b>$ 123,00</b> fue programada con éxito. La próxima vez que quieras transferirle a este destinatario podrás encontrarlo tu agenda de destinatarios.");

        Respuesta<TransferenciaDTO> respuestaTransferenciaBO = new Respuesta<TransferenciaDTO>();
        TransferenciaDTO transferencia = generarDtoRta();
        respuestaTransferenciaBO.setRespuesta(transferencia);
        respuestaTransferenciaBO.setEstadoRespuesta(EstadoRespuesta.OK);

        String hash = HashUtils.obtenerHash(TransferenciaUtil.crearMapaDeTransferenciaView(transferenciaView));
        
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("<p>No pudimos {0} la <b>transferencia</b> a {1} por {2}.</p> Por favor, intentá nuevamente en unos minutos.");
        mensaje.setCodigo("1776");

        ReflectionTestUtils.setField(transferenciaManager, "errorBanelcoCoelsaHabilitado", "1");
        
        // When
        Mockito.when(mockSesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mockTransferenciaBO.ejecutarTransferenciaProgramada(Matchers.any(Cliente.class),
                Matchers.any(TransferenciaDTO.class), Matchers.any(Boolean.class), Matchers.any(Boolean.class)))
                .thenReturn(respuestaTransferenciaBO);
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn(hash);
        Mockito.when(mensajeBO
                .obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Mockito.when(sesionParametros.getRsaGenericRequestData()).thenReturn(new RsaGenericRequestData());
        Mockito.when(mockBiocatchManager.getScore(Matchers.anyString(),Matchers.anyString(),Matchers.<ActivityName>any(),Matchers.<ActivityType>any())).thenReturn(new BiocatchResponseDataDTO());

        // Then
        Respuesta<TransferenciaView> respuesta = transferenciaManager.ejecutarNuevaTransferencia(transferenciaView);

        // Expected
        Assert.assertNotNull(respuesta.getRespuesta());
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        Assert.assertEquals(transferenciaView.getMensaje(), respuesta.getRespuesta().getMensaje());
    }
    
    /**
     * Test para TransferenciaManager#ejecutarNuevaTransferencia().
     * Transferencia Programada con riesgo.
     * 
     * @author florencia.n.martinez
     * @throws BusinessException
     */
    @Ignore
    @Test
    public void ejecutarNuevaTransferenciaProgramadaConRiesgoOKTest() throws BusinessException, DAOException {
        // Given
        Cliente cliente = new Cliente();
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        cuenta1.setEstadoTarjetaCredito("01");
        cuenta1.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta1.setNroSucursal("152");
        cuenta1.setNroTarjetaCredito("4517660024736620");
        cuenta1.setNroCuentaProducto("0000000000639170");
        cuenta1.setTipoCuenta("01");

        cuenta2.setEstadoTarjetaCredito("01");
        cuenta2.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cuenta2.setNroSucursal("152");
        cuenta2.setNroTarjetaCredito("4517660024736620");
        List<Cuenta> lista = new ArrayList<Cuenta>();
        lista.add(cuenta1);
        lista.add(cuenta2);
        cliente.setCuentas(lista);

        TransferenciaView transferenciaView = obtenerTransferenciaView();
        transferenciaView.setFechaEjecucion("07/06/2030");
        transferenciaView.setTipoCuentaDestino("Caja de Ahorro en Pesos");
        transferenciaView.setIsRioRio(true);
        transferenciaView.setNroCuentaDestino("000-063917/0");
        transferenciaView.setMoneda("peso");
        transferenciaView.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        transferenciaView.setMensaje("<p> La <b>transferencia</b> a <b>Nombre receptor</b> por <b>$ 123,00</b> fue programada con éxito. La próxima vez que quieras transferirle a este destinatario podrás encontrarlo tu agenda de destinatarios.");

        Respuesta<TransferenciaDTO> respuestaTransferenciaBO = new Respuesta<TransferenciaDTO>();
        TransferenciaDTO transferencia = generarDtoRta();
        respuestaTransferenciaBO.setRespuesta(transferencia);
        respuestaTransferenciaBO.setEstadoRespuesta(EstadoRespuesta.OK);

        String hash = HashUtils.obtenerHash(TransferenciaUtil.crearMapaDeTransferenciaView(transferenciaView));
        
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("<p>No pudimos {0} la <b>transferencia</b> a {1} por {2}.</p> Por favor, intentá nuevamente en unos minutos.");
        mensaje.setCodigo("1776");

        RsaRiesgoTransferenciaDTO riesgoTransfDTO = new RsaRiesgoTransferenciaDTO(null, Boolean.TRUE, null);
        
        ReflectionTestUtils.setField(transferenciaManager, "errorBanelcoCoelsaHabilitado", "1");
        
        // When
        Mockito.when(mockSesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mockTransferenciaBO.ejecutarTransferenciaProgramada(Matchers.any(Cliente.class),
                Matchers.any(TransferenciaDTO.class), Matchers.any(Boolean.class), Matchers.any(Boolean.class)))
                .thenReturn(respuestaTransferenciaBO);
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn(hash);
        Mockito.when(sesionParametros.getRsaRiesgoTransferenciaDTO()).thenReturn(riesgoTransfDTO);
        Mockito.when(mensajeBO
                .obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(sesionParametros.getRsaGenericRequestData()).thenReturn(new RsaGenericRequestData());
        Mockito.when(mockBiocatchManager.getScore(Matchers.anyString(),Matchers.anyString(),Matchers.<ActivityName>any(),Matchers.<ActivityType>any())).thenReturn(new BiocatchResponseDataDTO());
        // Then
        Respuesta<TransferenciaView> respuesta = transferenciaManager.ejecutarNuevaTransferencia(transferenciaView);

        // Expected
        Assert.assertNotNull(respuesta.getRespuesta());
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        Assert.assertEquals(transferenciaView.getMensaje(), respuesta.getRespuesta().getMensaje());
    }
    
    /**
     * Test para TransferenciaManager#ejecutarNuevaTransferencia().
     * Transferencia Inmediata.
     * 
     * @author florencia.n.martinez
     * @throws BusinessException
     */
    //TODO Arreglar para que sea inmediata
    @Ignore
    @Test
    public void ejecutarNuevaTransferenciaInmediataOKTest() throws BusinessException, DAOException {
        // Given
        Cliente cliente = new Cliente();
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        cuenta1.setEstadoTarjetaCredito("01");
        cuenta1.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta1.setNroSucursal("152");
        cuenta1.setNroTarjetaCredito("4517660024736620");
        cuenta1.setNroCuentaProducto("0000000000639170");
        cuenta1.setTipoCuenta("01");

        cuenta2.setEstadoTarjetaCredito("01");
        cuenta2.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cuenta2.setNroSucursal("152");
        cuenta2.setNroTarjetaCredito("4517660024736620");
        List<Cuenta> lista = new ArrayList<Cuenta>();
        lista.add(cuenta1);
        lista.add(cuenta2);
        cliente.setCuentas(lista);

        TransferenciaView transferenciaView = obtenerTransferenciaView();
        Date date = new Date();
        SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");
        transferenciaView.setFechaEjecucion(fechaFormato.format(date));
        transferenciaView.setTipoCuentaDestino("Caja de Ahorro en Pesos");
        transferenciaView.setIsRioRio(true);
        transferenciaView.setNroCuentaDestino("000-063917/0");
        transferenciaView.setMoneda("peso");
        transferenciaView.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        transferenciaView.setMensaje("<p> La <b>transferencia</b> a <b>Nombre receptor</b> por <b>$ 123,00</b> fue programada con éxito. La próxima vez que quieras transferirle a este destinatario podrás encontrarlo tu agenda de destinatarios.");

        Respuesta<TransferenciaDTO> respuestaTransferenciaBO = new Respuesta<TransferenciaDTO>();
        TransferenciaDTO transferencia = generarDtoRta();
        respuestaTransferenciaBO.setRespuesta(transferencia);
        respuestaTransferenciaBO.setEstadoRespuesta(EstadoRespuesta.OK);

        String hash = HashUtils.obtenerHash(TransferenciaUtil.crearMapaDeTransferenciaView(transferenciaView));
        
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("<p>No pudimos {0} la <b>transferencia</b> a {1} por {2}.</p> Por favor, intentá nuevamente en unos minutos.");
        mensaje.setCodigo("1776");

        RsaRiesgoTransferenciaDTO riesgoTransfDTO = new RsaRiesgoTransferenciaDTO(null, Boolean.TRUE, null);

        ReflectionTestUtils.setField(transferenciaManager, "errorBanelcoCoelsaHabilitado", "1");
        
        // When
        Mockito.when(mockSesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mockTransferenciaBO.ejecutarTransferenciaProgramada(Matchers.any(Cliente.class),
                Matchers.any(TransferenciaDTO.class), Matchers.any(Boolean.class), Matchers.any(Boolean.class)))
                .thenReturn(respuestaTransferenciaBO);
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn(hash);
        Mockito.when(sesionParametros.getRsaRiesgoTransferenciaDTO()).thenReturn(riesgoTransfDTO);
        Mockito.when(mensajeBO
                .obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Mockito.when(sesionParametros.getRsaGenericRequestData()).thenReturn(new RsaGenericRequestData());
        Mockito.when(mockBiocatchManager.getScore(Matchers.anyString(),Matchers.anyString(),Matchers.<ActivityName>any(),Matchers.<ActivityType>any())).thenReturn(new BiocatchResponseDataDTO());

        // Then
        Respuesta<TransferenciaView> respuesta = transferenciaManager.ejecutarNuevaTransferencia(transferenciaView);

        // Expected
        Assert.assertNotNull(respuesta.getRespuesta());
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        Assert.assertEquals(transferenciaView.getMensaje(), respuesta.getRespuesta().getMensaje());
    }

    /**
     * Obtener transferencia view.
     *
     * @return the transferencia view
     */
    public TransferenciaView obtenerTransferenciaView() {
        TransferenciaView transferenciaView = new TransferenciaView();
        transferenciaView.setFechaEjecucion("06/06/2017");
        transferenciaView.setNroCuenta("000-063917/0");
        transferenciaView.setTipoCuentaDescripcion("Cuenta Única");
        transferenciaView.setTipoCuentaDestinoDescripcion("Cuenta Única");
        transferenciaView.setTipoCuentaDestino(TipoCuenta.CUENTA_UNICA.getDescripcion());
        transferenciaView.setNroCuentaDestino("201-363238/1");
        transferenciaView.setMoneda("PESO");
        transferenciaView.setImporte("4");
        transferenciaView.setTitular("Agostino De Flores  Antigua Be");
        transferenciaView.setConcepto(new ConceptoView("Alquiler", "8", "VAR", "Alquiler", 0));
        transferenciaView.setDescripcion("Varios");
        transferenciaView.setEmail("s.maximilianno@gmail.com");
        transferenciaView.setMensajeEmail("hola");
        transferenciaView.setCuentaPropia(true);
        transferenciaView.setIsRioRio(true);
        return transferenciaView;

    }

    /**
     * 
     * Nueva transferencia OK. **Usar para otra casuistica**
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    @Ignore
    // TODO: mock an static class with mockit:
    // "ContextHolder.getContext().getBean(ContadorIntentos.class)"
    public void nuevaTransferenciaOK() throws ServiceException {

        TransferenciaDTO transferenciaSesion = new TransferenciaDTO();
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();

        identificacionCuenta.setNroCuentaProducto("6666667");
        identificacionCuenta.setNroSucursal("333");

        transferenciaSesion.setCbuCuenta("0070002330004402734562");
        transferenciaSesion.setNumeroCuentaDestino(identificacionCuenta);
        transferenciaSesion.setTitular("Nombre Titular Cuenta");
        transferenciaSesion.setTipoCuentaDestino(null);

        Respuesta<CuentasView> respuestaCuentasViewOk = new Respuesta<CuentasView>();
        CuentasView cuentasView = new CuentasView();
        List<CuentasAdhesionDebitoView> listCuentasView = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView cuentaView1 = new CuentasAdhesionDebitoView();
        CuentasAdhesionDebitoView cuentaView2 = new CuentasAdhesionDebitoView();

        cuentaView1.setIsCerrada(false);
        listCuentasView.add(cuentaView1);
        cuentaView2.setIsCerrada(false);
        listCuentasView.add(cuentaView2);
        cuentasView.setCuentas(listCuentasView);

        respuestaCuentasViewOk.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaCuentasViewOk.setRespuesta(cuentasView);

        TransferenciaView transferenciaViewOk = new TransferenciaView();
        transferenciaViewOk.setCbu(this.CBU_NO_RIO_OK);
        transferenciaViewOk.setNroCuentaDestino("333-666666/7");
        transferenciaViewOk.setConceptoTransferencia(ConceptoTransferenciaEnum.getConceptoView());
        transferenciaViewOk.setTitular("Nombre Titular Cuenta");
        transferenciaViewOk.setTipoCuentaDestino("");
        transferenciaViewOk.setCuentasView(cuentasView);
        ArrayList<String> monedasDisponibles = new ArrayList<String>();
        monedasDisponibles.add("Pesos");
        transferenciaViewOk.setMonedasDisponibles(monedasDisponibles);

        transferenciaRespuestaOK.setRespuesta(transferenciaViewOk);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje(mensajeERROR_EN_SERVICIO_TIMEOUT_CUENTA);
        Mockito.when(mockMensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(mockCuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentasViewOk);
        Cliente cliente = new Cliente();
        cliente.setCuentas(listaCuentas);
        Mockito.when(mockSesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mockCliente.getCuentas()).thenReturn(listaCuentas);
        Respuesta<Boolean> respuestaClienteHabilitado = new Respuesta<Boolean>();
        respuestaClienteHabilitado.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaClienteHabilitado.setRespuesta(true);
        Mockito.when(mockTransferenciaBO.isClienteHabilitadoParaTransferir(Matchers.any(Cliente.class)))
                .thenReturn(respuestaClienteHabilitado);

        TransferenciaView transferenciaViewInput = new TransferenciaView();
        transferenciaViewInput.setMoneda("PESOS");
        transferenciaViewInput.setTitular("Manuel Titular");
        transferenciaViewInput.setImporte("4");
        transferenciaViewInput.setCbu("0070002330004402734562");
        transferenciaViewInput.setNroCuentaDestino("333-666666/7");
        Respuesta<TransferenciaView> respuesta = transferenciaManager
                .solicitarNuevaTransferencia(transferenciaViewInput, "");

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(transferenciaRespuestaOK, respuesta);

        Respuesta<CuentasView> respuestaCuentasViewError = new Respuesta<CuentasView>();
        respuestaCuentasViewOk.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(mockCuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentasViewError);

        Respuesta<TransferenciaView> respuestaError = transferenciaManager
                .solicitarNuevaTransferencia(transferenciaViewInput, "");
        transferenciaRespuestaERROR.add(new ItemMensajeRespuesta("Ha ocurrido un error"));
        Assert.assertNotNull(respuestaError);
        Assert.assertEquals(transferenciaRespuestaERROR, respuestaError);
    }

    /**
     * Obtener tipos de cuenta ok test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void obtenerTiposDeCuentaOkTest() throws ServiceException {

        Respuesta<List<TipoDeCuentaView>> respuestaOK = new Respuesta<List<TipoDeCuentaView>>();
        List<TipoDeCuentaView> lista = new ArrayList<TipoDeCuentaView>();

        transferenciaManager.setCuentaUnicaHabilitada("true");
        transferenciaManager.setCajaAhorroPesosHabilitada("true");
        transferenciaManager.setCajaAhorroDolaresHabilitada("true");
        transferenciaManager.setCuentaCorrientePesosHabilitada("true");
        transferenciaManager.setCuentaCorrienteDolaresHabilitada("true");

        lista.add(new TipoDeCuentaView(TipoCuenta.CUENTA_UNICA.getAbreviatura(),
                TipoCuenta.CUENTA_UNICA.getDescripcionConMoneda()));
        lista.add(new TipoDeCuentaView(TipoCuenta.CAJA_AHORRO_PESOS.getAbreviatura(),
                TipoCuenta.CAJA_AHORRO_PESOS.getDescripcionConMoneda()));
        lista.add(new TipoDeCuentaView(TipoCuenta.CAJA_AHORRO_DOLARES.getAbreviatura(),
                TipoCuenta.CAJA_AHORRO_DOLARES.getDescripcionConMoneda()));
        lista.add(new TipoDeCuentaView(TipoCuenta.CUENTA_CORRIENTE_PESOS.getAbreviatura(),
                TipoCuenta.CUENTA_CORRIENTE_PESOS.getDescripcionConMoneda()));
        lista.add(new TipoDeCuentaView(TipoCuenta.CUENTA_CORRIENTE_DOLARES.getAbreviatura(),
                TipoCuenta.CUENTA_CORRIENTE_DOLARES.getDescripcionConMoneda()));

        respuestaOK.setRespuesta(lista);
        respuestaOK.setEstadoRespuesta(EstadoRespuesta.OK);

        Respuesta<List<TipoDeCuentaView>> respuestaReal = transferenciaManager.obtenerTiposDeCuenta();

        Assert.assertNotNull(respuestaReal);
        Assert.assertEquals(respuestaOK, respuestaReal);
    }

    /**
     * Obtener tipos de cuenta error test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void obtenerTiposDeCuentaErrorTest() throws ServiceException {

        Respuesta<List<TipoDeCuentaView>> respuestaError = new Respuesta<List<TipoDeCuentaView>>();

        transferenciaManager.setCuentaUnicaHabilitada("false");
        transferenciaManager.setCajaAhorroPesosHabilitada("false");
        transferenciaManager.setCajaAhorroDolaresHabilitada("false");
        transferenciaManager.setCuentaCorrientePesosHabilitada("false");

        respuestaError.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaError.setRespuesta(new ArrayList<TipoDeCuentaView>());

        Respuesta<List<TipoDeCuentaView>> respuestaReal = transferenciaManager.obtenerTiposDeCuenta();

        Assert.assertNotNull(respuestaReal);
        Assert.assertEquals(respuestaError, respuestaReal);
    }

    /**
     * Consulta titular Error test en RioRio. Si el view en null. ser retorna un
     * TipoError.ERROR_EN_SERVICIO_TIMEOUT_CUENTA (1564), con
     * CodigoMensajeConstantes.ERROR_GENERICO
     * 
     * @throws BusinessException
     */
    @Test
    @Ignore
    public void consultaTitularERRORTest() throws BusinessException {
        transferenciaView = obtenerTransferenciaView();
        String nroTarjetaActiva = "4517660024736620";
        Cliente cliente = new Cliente();
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();
        cuenta1.setEstadoTarjetaCredito("01");
        cuenta1.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta1.setNroSucursal("152");
        cuenta1.setNroTarjetaCredito("4517660024736620");

        cuenta2.setEstadoTarjetaCredito("01");
        cuenta2.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuenta2.setNroSucursal("152");
        cuenta2.setNroTarjetaCredito(nroTarjetaActiva);
        List<Cuenta> lista = new ArrayList<Cuenta>();
        lista.add(cuenta1);
        lista.add(cuenta2);
        cliente.setCuentas(lista);

        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("");
        Respuesta<TransferenciaView> transferenciaViewConsultaEsperadaERR = respuestaFactory
                .crearRespuestaError(TransferenciaView.class, null, "", TipoError.ERROR_GENERICO, "");

        Respuesta<Cliente> cuentaDestinoVerificada = new Respuesta<Cliente>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setMensaje(mensajeERROR_EN_SERVICIO_TIMEOUT_CUENTA);
        cuentaDestinoVerificada.add(item);
        cuentaDestinoVerificada.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mensaje mensaje = new Mensaje();

        mensaje.setMensaje(mensajeERROR_EN_SERVICIO_TIMEOUT_CUENTA);

        Mockito.when(mockMensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(mockMensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(mockSesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(mockSesionParametros.isFromAgendaDestinatario()).thenReturn(true);
        Mockito.when(mockSesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mockCuentaManager.verificarCuenta(Matchers.any(TransferenciaView.class), Matchers.anyInt()))
                .thenReturn(cuentaDestinoVerificada);

        Respuesta<TransferenciaView> transferenciaViewConsultaERR = transferenciaManager
                .consultarTitularidad(transferenciaView, "");

        Assert.assertNotNull(transferenciaViewConsultaERR);
        Assert.assertEquals(transferenciaViewConsultaERR.getItemsMensajeRespuesta().get(0).getTipoError(),
                transferenciaViewConsultaEsperadaERR.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Consulta titular OK test.
     * 
     * @throws BusinessException
     */
    @Test
    public void consultaTitularOKTest() throws BusinessException {

        transferenciaView = obtenerTransferenciaView();
        transferenciaView.setBanco("banco");

        String nroTarjetaActiva = "4517660024736620";

        Cliente cliente = new Cliente();
        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();

        cuenta1.setEstadoTarjetaCredito("01");
        cuenta1.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta1.setNroSucursal("152");
        cuenta1.setNroTarjetaCredito("4517660024736620");

        cuenta2.setEstadoTarjetaCredito("01");
        cuenta2.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuenta2.setNroSucursal("152");
        cuenta2.setNroTarjetaCredito(nroTarjetaActiva);
        List<Cuenta> lista = new ArrayList<Cuenta>();
        lista.add(cuenta1);
        lista.add(cuenta2);
        cliente.setCuentas(lista);
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("");
        Respuesta<Cliente> respuestaCuenta = respuestaFactory.crearRespuestaOk(Cliente.class, null);
        Mockito.when(mockCuentaManager.verificarCuenta(Matchers.any(TransferenciaView.class), Matchers.anyInt()))
                .thenReturn(respuestaCuenta);
        Mockito.when(mockSesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(mockSesionCliente.getCliente()).thenReturn(cliente);
        Object transferenciaViewConsultaEsperadaOK = respuestaFactory.crearRespuestaOk(TransferenciaView.class,
                transferenciaView);
        Respuesta<TransferenciaView> transferenciaViewConsultaOK = transferenciaManager
                .consultarTitularidad(transferenciaView, "");
        Assert.assertNotNull(transferenciaViewConsultaOK);
        Assert.assertEquals(transferenciaViewConsultaOK, transferenciaViewConsultaEsperadaOK);
    }

    /**
     * Solisitar nueva transferencia Inmediata Rio-Rio con cuenta propia. Caso
     * de OK por cuenta destino valida.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    @Ignore
    public void solicitarNuevaTransferencia_RR_OK_EsCuentaPropia()
            throws ServiceException, IllegalAccessException, DAOException {
        String nroTarjetaActiva = "4517660024736620";

        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();

        TransferenciaDTO transferenciaSesion = new TransferenciaDTO();
        Respuesta<TransferenciaDTO> respuestTransferenciaDTO = new Respuesta<TransferenciaDTO>();
        respuestTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestTransferenciaDTO.setRespuesta(transferenciaSesion);

        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        identificacionCuenta.setNroCuentaProducto("666655");
        identificacionCuenta.setNroSucursal("333");
        BigDecimal importe = new BigDecimal("25000");
        transferenciaSesion.setImporte(importe);
        transferenciaSesion.setCbuCuenta(CBU_NO_RIO_OK);
        transferenciaSesion.setNumeroCuentaDestino(identificacionCuenta);
        transferenciaSesion.setTitular("Nombre Titular Cuenta");
        transferenciaSesion.setTipoCuentaDestino(null);
        transferenciaSesion.setIp("");
        transferenciaSesion.setCuentaOrigen(cuenta1);
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("");
        Mockito.when(mockEstadisticaManager.add(Matchers.any(Estadistica.class))).thenReturn(true);
        Mockito.when(mockSesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(contratosBO.buscarContratoAceptadoOld(Matchers.any(String.class), Matchers.any(String.class),
                Matchers.any(CampoEnum.class), Matchers.any(SinonimoEnum.class))).thenReturn("1");
        Respuesta<CuentasView> respuestaCuentasViewOk = new Respuesta<CuentasView>();
        CuentasView cuentasView = new CuentasView();
        List<CuentasAdhesionDebitoView> listCuentasView = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView cuentaView1 = new CuentasAdhesionDebitoView();
        CuentasAdhesionDebitoView cuentaView2 = new CuentasAdhesionDebitoView();
        cuentaView1.setNumero("333-666654/7");
        cuentaView1.setSaldoPesos("500000");
        cuentaView1.setSaldoDolares("35000");
        cuentaView1.setSignoSaldoPesos("");
        cuentaView1.setIsCerrada(false);
        cuentaView1.setDescripcionTipoCuenta("Caja de ahorro en $");
        listCuentasView.add(cuentaView1);
        cuentaView2.setIsCerrada(false);
        cuentaView2.setNumero("333-666655/7");
        cuentaView2.setSaldoPesos("500000");
        cuentaView2.setSaldoDolares("35000");
        cuentaView2.setSignoSaldoPesos("");
        cuentaView2.setDescripcionTipoCuenta("Caja de ahorro en $");
        listCuentasView.add(cuentaView2);
        cuentasView.setCuentas(listCuentasView);

        Respuesta<Cliente> respuestaClienteOk = new Respuesta<Cliente>();
        respuestaClienteOk.setEstadoRespuesta(EstadoRespuesta.OK);
        Cliente cliente = new Cliente();
        cuenta1.setEstadoTarjetaCredito("01");
        cuenta1.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta1.setNroSucursal("152");
        cuenta1.setNroTarjetaCredito("4517660024736620");

        cuenta2.setEstadoTarjetaCredito("01");
        cuenta2.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuenta2.setNroSucursal("152");
        cuenta2.setNroTarjetaCredito(nroTarjetaActiva);
        List<Cuenta> lista = new ArrayList<Cuenta>();
        lista.add(cuenta1);
        lista.add(cuenta2);
        cliente.setCuentas(lista);
        respuestaClienteOk.setRespuesta(cliente);

        respuestaCuentasViewOk.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaCuentasViewOk.setRespuesta(cuentasView);

        TransferenciaView transferenciaView = new TransferenciaView();
        Respuesta<TransferenciaView> transferenciaEsperada = new Respuesta<TransferenciaView>();
        transferenciaEsperada.setRespuesta(transferenciaView);
        transferenciaEsperada.setRespuestaVacia(true);
        transferenciaEsperada.setEstadoRespuesta(EstadoRespuesta.OK);

        Respuesta<AbstractCuenta> respuestaCuentaManager = new Respuesta<AbstractCuenta>();
        respuestaCuentaManager.setRespuesta(cuenta1);
        respuestaCuentaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(mockCuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentasViewOk);
        Mockito.when(mockSesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mockCuentaManager.obtenerCuentaPorId(Matchers.any(String.class)))
                .thenReturn(respuestaCuentaManager);
        Mockito.when(mockCuentaManager.obtenerCuentaPorId(Matchers.any(String.class)))
                .thenReturn(respuestaCuentaManager);
        transferenciaView.setNroCuentaDestino("168-356669/3");
        transferenciaView.setConceptoTransferencia(ConceptoTransferenciaEnum.getConceptoView());
        transferenciaView.setTitular("Nombre Titular Cuenta");
        transferenciaView.setTipoCuentaDestino("CU");
        transferenciaView.setCuentasView(cuentasView);
        ArrayList<String> monedasDisponibles = new ArrayList<String>();
        monedasDisponibles.add("Pesos");
        transferenciaView.setMonedasDisponibles(monedasDisponibles);
        Respuesta<Boolean> isHabilitado = new Respuesta<Boolean>();
        isHabilitado.setEstadoRespuesta(EstadoRespuesta.OK);
        isHabilitado.setRespuesta(true);
        Mockito.when(mockTransferenciaBO.isClienteHabilitadoParaTransferir(Matchers.any(Cliente.class)))
                .thenReturn(isHabilitado);
        Mockito.when(mockTransferenciaBO.validarOrigenDestinoTransferencia(Matchers.any(Cliente.class),
                Matchers.any(TransferenciaDTO.class), Matchers.anyString(), Matchers.anyString(),
                Matchers.any(Cuenta.class), Matchers.anyString())).thenReturn(respuestTransferenciaDTO);
        Mockito.when(mockCuentaManager.verificarCuenta(Matchers.any(TransferenciaView.class), Matchers.anyInt()))
                .thenReturn(respuestaClienteOk);

        transferenciaRespuestaOK = transferenciaManager.solicitarNuevaTransferencia(transferenciaView, "");

        Assert.assertNotNull(transferenciaRespuestaOK);
        Assert.assertNotNull(transferenciaRespuestaOK.getRespuesta());
        Assert.assertEquals(transferenciaEsperada.getEstadoRespuesta(), transferenciaRespuestaOK.getEstadoRespuesta());
        Assert.assertEquals(transferenciaEsperada.getRespuesta().getCbu(),
                transferenciaRespuestaOK.getRespuesta().getCbu());
    }

    /**
     * Solisitar nueva transferencia Inmediata Rio-Rio a cuenta no propia. Caso
     * de OK por cuenta destino valida.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    @Ignore
    public void solicitarNuevaTransferencia_RR_OK_NoEsCuentaPropia()
            throws ServiceException, IllegalAccessException, DAOException {
        String nroTarjetaActiva = "4517660024736620";

        Cuenta cuenta1 = new Cuenta();
        Cuenta cuenta2 = new Cuenta();

        TransferenciaDTO transferenciaSesion = new TransferenciaDTO();
        Respuesta<TransferenciaDTO> respuestTransferenciaDTO = new Respuesta<TransferenciaDTO>();
        respuestTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestTransferenciaDTO.setRespuesta(transferenciaSesion);

        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        identificacionCuenta.setNroCuentaProducto("6666667");
        identificacionCuenta.setNroSucursal("333");
        BigDecimal importe = new BigDecimal("25000");
        transferenciaSesion.setImporte(importe);
        transferenciaSesion.setCbuCuenta(CBU_NO_RIO_OK);
        transferenciaSesion.setNumeroCuentaDestino(identificacionCuenta);
        transferenciaSesion.setTitular("Nombre Titular Cuenta");
        transferenciaSesion.setTipoCuentaDestino(null);
        transferenciaSesion.setIp("");
        transferenciaSesion.setCuentaOrigen(cuenta1);
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("");

        Mockito.when(mockEstadisticaManager.add(Matchers.any(Estadistica.class))).thenReturn(true);
        Mockito.when(mockSesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(contratosBO.buscarContratoAceptadoOld(Matchers.any(String.class), Matchers.any(String.class),
                Matchers.any(CampoEnum.class), Matchers.any(SinonimoEnum.class))).thenReturn("1");
        Respuesta<CuentasView> respuestaCuentasViewOk = new Respuesta<CuentasView>();
        CuentasView cuentasView = new CuentasView();
        List<CuentasAdhesionDebitoView> listCuentasView = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView cuentaView1 = new CuentasAdhesionDebitoView();
        CuentasAdhesionDebitoView cuentaView2 = new CuentasAdhesionDebitoView();
        cuentaView1.setNumero("333-666654/7");
        cuentaView1.setSaldoPesos("500000");
        cuentaView1.setSaldoDolares("35000");
        cuentaView1.setSignoSaldoPesos("");
        cuentaView1.setIsCerrada(false);
        cuentaView1.setDescripcionTipoCuenta("Caja de ahorro en $");
        listCuentasView.add(cuentaView1);
        cuentaView2.setIsCerrada(false);
        cuentaView2.setNumero("333-666655/7");
        cuentaView2.setSaldoPesos("500000");
        cuentaView2.setSaldoDolares("35000");
        cuentaView2.setSignoSaldoPesos("");
        cuentaView2.setDescripcionTipoCuenta("Caja de ahorro en $");
        listCuentasView.add(cuentaView2);
        cuentasView.setCuentas(listCuentasView);

        Respuesta<Cliente> respuestaClienteOk = new Respuesta<Cliente>();
        respuestaClienteOk.setEstadoRespuesta(EstadoRespuesta.OK);
        Cliente cliente = new Cliente();
        cuenta1.setEstadoTarjetaCredito("01");
        cuenta1.setTipoCuentaEnum(TipoCuenta.BANELCO);
        cuenta1.setNroSucursal("152");
        cuenta1.setNroTarjetaCredito("4517660024736620");
        cuenta1.setNroCuentaProducto("3566692");
        cuenta2.setNroSucursal("168");
        cuenta2.setNroTarjetaCredito("4517660024736620");
        cuenta2.setNroCuentaProducto("3566693");
        cuenta2.setEstadoTarjetaCredito("01");
        cuenta2.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuenta2.setNroTarjetaCredito(nroTarjetaActiva);
        List<Cuenta> lista = new ArrayList<Cuenta>();
        lista.add(cuenta1);
        lista.add(cuenta2);
        cliente.setCuentas(lista);
        respuestaClienteOk.setRespuesta(cliente);

        respuestaCuentasViewOk.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaCuentasViewOk.setRespuesta(cuentasView);

        TransferenciaView transferenciaView = new TransferenciaView();
        Respuesta<TransferenciaView> transferenciaEsperada = new Respuesta<TransferenciaView>();
        transferenciaEsperada.setRespuesta(transferenciaView);
        transferenciaEsperada.setRespuestaVacia(true);
        transferenciaEsperada.setEstadoRespuesta(EstadoRespuesta.OK);

        Respuesta<AbstractCuenta> respuestaCuentaManager = new Respuesta<AbstractCuenta>();
        respuestaCuentaManager.setRespuesta(cuenta1);
        respuestaCuentaManager.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(mockCuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentasViewOk);
        Mockito.when(mockSesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mockCuentaManager.obtenerCuentaPorId(Matchers.any(String.class)))
                .thenReturn(respuestaCuentaManager);
        Mockito.when(mockCuentaManager.obtenerCuentaPorId(Matchers.any(String.class)))
                .thenReturn(respuestaCuentaManager);
        transferenciaView.setNroCuentaDestino("168-356669/3");
        transferenciaView.setConceptoTransferencia(ConceptoTransferenciaEnum.getConceptoView());
        transferenciaView.setTitular("Nombre Titular Cuenta");
        transferenciaView.setTipoCuentaDestino("CU");
        transferenciaView.setCuentasView(cuentasView);
        ArrayList<String> monedasDisponibles = new ArrayList<String>();
        monedasDisponibles.add("Pesos");
        transferenciaView.setMonedasDisponibles(monedasDisponibles);
        Respuesta<Boolean> isHabilitado = new Respuesta<Boolean>();
        isHabilitado.setEstadoRespuesta(EstadoRespuesta.OK);
        isHabilitado.setRespuesta(true);
        Mockito.when(mockTransferenciaBO.isClienteHabilitadoParaTransferir(Matchers.any(Cliente.class)))
                .thenReturn(isHabilitado);
        Mockito.when(mockTransferenciaBO.validarOrigenDestinoTransferencia(Matchers.any(Cliente.class),
                Matchers.any(TransferenciaDTO.class), Matchers.anyString(), Matchers.anyString(),
                Matchers.any(Cuenta.class), Matchers.anyString())).thenReturn(respuestTransferenciaDTO);
        Mockito.when(mockCuentaManager.verificarCuenta(Matchers.any(TransferenciaView.class), Matchers.anyInt()))
                .thenReturn(respuestaClienteOk);

        transferenciaRespuestaOK = transferenciaManager.solicitarNuevaTransferencia(transferenciaView, "");

        Assert.assertNotNull(transferenciaRespuestaOK);
        Assert.assertNotNull(transferenciaRespuestaOK.getRespuesta());
        Assert.assertEquals(transferenciaEsperada.getEstadoRespuesta(), transferenciaRespuestaOK.getEstadoRespuesta());
        Assert.assertEquals(transferenciaEsperada.getRespuesta().getNroCuenta(),
                transferenciaRespuestaOK.getRespuesta().getNroCuenta());
    }

    /**
     * Solisitar nueva transferencia Inmediata a otro banco. Caso de OK por
     * cuenta destino valida.
     *
     * @throws ServiceException
     *             the service exception
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    @Ignore
    public void solicitarNuevaTransferencia_OB_OK() throws ServiceException, IllegalAccessException, DAOException {

        Cuenta cuenta1 = new Cuenta();
        this.mensajeRespuesta.setMensaje("Mensaje de prueba");
        TransferenciaDTO transferenciaSesion = new TransferenciaDTO();
        Respuesta<TransferenciaDTO> respuestTransferenciaDTO = new Respuesta<TransferenciaDTO>();
        respuestTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestTransferenciaDTO.setRespuesta(transferenciaSesion);
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        identificacionCuenta.setNroCuentaProducto("6666667");
        identificacionCuenta.setNroSucursal("333");
        BigDecimal importe = new BigDecimal("25000");
        transferenciaSesion.setImporte(importe);
        transferenciaSesion.setCbuCuenta(CBU_NO_RIO_OK);
        transferenciaSesion.setNumeroCuentaDestino(identificacionCuenta);
        transferenciaSesion.setTitular("Nombre Titular Cuenta");
        transferenciaSesion.setTipoCuentaDestino(null);
        transferenciaSesion.setIp("");
        transferenciaSesion.setCuentaOrigen(cuenta1);
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("");
        Respuesta<AbstractCuenta> respuestaCuentaManager = new Respuesta<AbstractCuenta>();
        respuestaCuentaManager.setRespuesta(cuenta1);
        respuestaCuentaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<Boolean> isHabilitado = new Respuesta<Boolean>();
        isHabilitado.setEstadoRespuesta(EstadoRespuesta.OK);
        isHabilitado.setRespuesta(true);

        Respuesta<CuentasView> respuestaCuentasViewOk = armarlistaCuentaSaldoParaCuentaManager();
        Respuesta<TransferenciaView> transferenciaEsperada = armarTransferenciaEsperada();
        TransferenciaView transferenciaView = armarTransferenciaView();

        //
        Mockito.when(mockEstadisticaManager.add(Matchers.any(Estadistica.class))).thenReturn(true);
        Mockito.when(mockSesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(contratosBO.buscarContratoAceptadoOld(Matchers.any(String.class), Matchers.any(String.class),
                Matchers.any(CampoEnum.class), Matchers.any(SinonimoEnum.class))).thenReturn("1");
        Mockito.when(mockCliente.getCuentas()).thenReturn(this.listaCuentas);
        Mockito.when(mockSesionCliente.getCliente()).thenReturn(mockCliente);
        Mockito.when(mockCuentaManager.obtenerCuentaPorId(Matchers.any(String.class)))
                .thenReturn(respuestaCuentaManager);
        Mockito.when(mockCuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentasViewOk);
        Mockito.when(mockTransferenciaBO.isClienteHabilitadoParaTransferir(Matchers.any(Cliente.class)))
                .thenReturn(isHabilitado);
        Mockito.when(mockTransferenciaBO.validarOrigenDestinoTransferencia(Matchers.any(Cliente.class),
                Matchers.any(TransferenciaDTO.class), Matchers.anyString(), Matchers.anyString(),
                Matchers.any(Cuenta.class), Matchers.anyString())).thenReturn(respuestTransferenciaDTO);
        //
        transferenciaRespuestaOK = transferenciaManager.solicitarNuevaTransferencia(transferenciaView, "");
        //
        Assert.assertNotNull(transferenciaRespuestaOK);
        Assert.assertNotNull(transferenciaRespuestaOK.getRespuesta());
        Assert.assertEquals(transferenciaEsperada.getEstadoRespuesta(), transferenciaRespuestaOK.getEstadoRespuesta());
        Assert.assertEquals(transferenciaEsperada.getRespuesta().getCbu(),
                transferenciaRespuestaOK.getRespuesta().getCbu());
    }

    @Test
    public void ejecutarNuevaTransferenciaInmediataConRSAOfflineNoPasaValidacionTestBiocatchNull() throws DAOException {

        Cliente cliente = getCliente();

        TransferenciaView transferenciaView = getTransferenciaViewForRSAOfflineTest();

        Respuesta<DestinatariosFrecuentesDTO> respuestaDestinatariosFrecuentesBO = getRespuestaDestinatariosFrecuentesForValidationFail();

        String hash = HashUtils.obtenerHash(TransferenciaUtil.crearMapaDeTransferenciaView(transferenciaView));

        Mockito.when(mockSesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mockSesionCliente.getTieneTokenRSA()).thenReturn(false);
        Mockito.when(sesionParametros.getDatosTransferenciaDestino()).thenReturn(new DatosTransferenciaDestino());
        Mockito.when(mockMetricBuilder.createMetricTransfer(Matchers.any(TransferMetricInfoDTO.class))).thenReturn(getMetric());
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn(hash);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(mockDestinatariosFrecuentesBO.obtenerDestinatariosFrecuentes(cliente)).thenReturn(respuestaDestinatariosFrecuentesBO);

        Mockito.when(sesionParametros.getRsaGenericRequestData()).thenReturn(new RsaGenericRequestData());
        Mockito.when(mockBiocatchManager.getScoreTransferencia(Matchers.anyString(),Matchers.anyString(),Matchers.<ActivityName>any(),Matchers.<ActivityType>any(),Matchers.<BiocatchTransferInfoDTO>anyObject())).thenReturn(new BiocatchResponseDataDTO());
        Mockito.when(mockAccountsApi.getAccountByAccountId((Matchers.anyString()),Matchers.anyString())).thenReturn(AccountEntityFeature.getAccountEntity());

        Respuesta<TransferenciaView> respuesta = transferenciaManager.ejecutarNuevaTransferencia(transferenciaView);

        Assert.assertNotNull(respuesta.getRespuesta());
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        Assert.assertEquals("BIOCATCH_OFFLINE_VALIDACION_FALLIDA", respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    @Test
    public void ejecutarNuevaTransferenciaInmediataConRSAOfflinePasaValidacionTestAllow() throws DAOException {

        Cliente cliente = getCliente();

        TransferenciaView transferenciaView = getTransferenciaViewForRSAOfflineTest();

        Respuesta<RsaRiesgoTransferenciaDTO> respuestaRsaRiesgoTransferencia = getRespuestaRsaRiesgoTransferencia();

        Respuesta<DestinatariosFrecuentesDTO> respuestaDestinatariosFrecuentesBO = getRespuestaDestinatariosFrecuentesForValidationOK();

        String hash = HashUtils.obtenerHash(TransferenciaUtil.crearMapaDeTransferenciaView(transferenciaView));

        ReflectionTestUtils.setField(transferenciaManager, "valorDesafioTransferencias", "2");
        ReflectionTestUtils.setField(transferenciaManager, "errorBanelcoCoelsaHabilitado", "1");

        Respuesta<AutentificacionDTO> respuestaAutentificacion = getRespuestaAutentificacion();
        Mensaje msjMock =new Mensaje();
        msjMock.setMensaje("Allow");


        Respuesta<TransferenciaDTO> respuestaTransferenciaBO = new Respuesta<TransferenciaDTO>();
        TransferenciaDTO transferencia = generarDtoRta();
        respuestaTransferenciaBO.setRespuesta(transferencia);
        respuestaTransferenciaBO.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(mockSesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mockSesionCliente.getTieneTokenRSA()).thenReturn(false);
        Mockito.when(sesionParametros.getDatosTransferenciaDestino()).thenReturn(new DatosTransferenciaDestino());
        Mockito.when(mockMetricBuilder.createMetricTransfer(Matchers.any(TransferMetricInfoDTO.class))).thenReturn(getMetric());
        Mockito.when(mockAutentificacionManager.obtenerDesafioHabilitadoOperacion(Matchers.any(AutentificacionDTO.class))).thenReturn(TipoDesafioEnum.TOKEN);
        Mockito.when(mockRsaManager.analizarRiesgoTransferencia(Matchers.any(TransferenciaView.class), Matchers.any(TipoDesafioEnum.class))).thenReturn(respuestaRsaRiesgoTransferencia);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn(hash);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(msjMock);
        Mockito.when(mockAutentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class))).thenReturn(respuestaAutentificacion);
        Mockito.when(mockDestinatariosFrecuentesBO.obtenerDestinatariosFrecuentes(cliente)).thenReturn(respuestaDestinatariosFrecuentesBO);

        Mockito.when(sesionParametros.getRsaGenericRequestData()).thenReturn(new RsaGenericRequestData());
        Mockito.when(mockBiocatchManager.getScoreTransferencia(Matchers.anyString(),Matchers.anyString(),Matchers.<ActivityName>any(),Matchers.<ActivityType>any(),Matchers.<BiocatchTransferInfoDTO>anyObject())).thenReturn(BiocatchResponseDataDTOFeature.getBiocatchResponseDataDTOAllow());
        Mockito.when(mockTransferenciaBO.ejecutarTransferenciaInmediata(Matchers.any(Cliente.class),Matchers.any(TransferenciaDTO.class), Matchers.any(Boolean.class), Matchers.any(Boolean.class))).thenReturn(respuestaTransferenciaBO);
        Mockito.when(mockAccountsApi.getAccountByAccountId((Matchers.anyString()),Matchers.anyString())).thenReturn(AccountEntityFeature.getAccountEntity());

        Mockito.when(altaBO.altaScompTransferencia(Matchers.any(AltaComprobanteRequestBuilder.class)))
                .thenReturn(new Respuesta<Void>());

        Respuesta<TransferenciaView> respuesta = transferenciaManager.ejecutarNuevaTransferencia(transferenciaView);

        Assert.assertNotNull(respuesta.getRespuesta());
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    @Test
    public void ejecutarNuevaTransferenciaInmediataConRSAOfflinePasaValidacionTestBiocatchDeny() throws DAOException {

        Cliente cliente = getCliente();

        TransferenciaView transferenciaView = getTransferenciaViewForRSAOfflineTest();

        Respuesta<RsaRiesgoTransferenciaDTO> respuestaRsaRiesgoTransferencia = getRespuestaRsaRiesgoTransferencia();

        Respuesta<DestinatariosFrecuentesDTO> respuestaDestinatariosFrecuentesBO = getRespuestaDestinatariosFrecuentesForValidationOK();

        String hash = HashUtils.obtenerHash(TransferenciaUtil.crearMapaDeTransferenciaView(transferenciaView));

        ReflectionTestUtils.setField(transferenciaManager, "valorDesafioTransferencias", "2");

        Respuesta<AutentificacionDTO> respuestaAutentificacion = getRespuestaAutentificacion();

        Mockito.when(mockSesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mockSesionCliente.getTieneTokenRSA()).thenReturn(false);
        Mockito.when(sesionParametros.getDatosTransferenciaDestino()).thenReturn(new DatosTransferenciaDestino());
        Mockito.when(mockMetricBuilder.createMetricTransfer(Matchers.any(TransferMetricInfoDTO.class))).thenReturn(getMetric());
        Mockito.when(mockAutentificacionManager.obtenerDesafioHabilitadoOperacion(Matchers.any(AutentificacionDTO.class))).thenReturn(TipoDesafioEnum.TOKEN);
        Mockito.when(mockRsaManager.analizarRiesgoTransferencia(Matchers.any(TransferenciaView.class), Matchers.any(TipoDesafioEnum.class))).thenReturn(respuestaRsaRiesgoTransferencia);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn(hash);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(mockAutentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class))).thenReturn(respuestaAutentificacion);
        Mockito.when(mockDestinatariosFrecuentesBO.obtenerDestinatariosFrecuentes(cliente)).thenReturn(respuestaDestinatariosFrecuentesBO);

        Mockito.when(sesionParametros.getRsaGenericRequestData()).thenReturn(new RsaGenericRequestData());
        Mockito.when(mockBiocatchManager.getScoreTransferencia(Matchers.anyString(),Matchers.anyString(),Matchers.<ActivityName>any(),Matchers.<ActivityType>any(),Matchers.<BiocatchTransferInfoDTO>anyObject())).thenReturn(BiocatchResponseDataDTOFeature.getBiocatchResponseDataDTODeny());
        Mockito.when(mockAccountsApi.getAccountByAccountId((Matchers.anyString()),Matchers.anyString())).thenReturn(AccountEntityFeature.getAccountEntity());

        Respuesta<TransferenciaView> respuesta = transferenciaManager.ejecutarNuevaTransferencia(transferenciaView);

        Assert.assertNotNull(respuesta.getRespuesta());
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        Assert.assertEquals("RSA_OFFLINE_VALIDACION_FALLIDA", respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    @Test
    public void ejecutarNuevaTransferenciaInmediataConRSAOfflinePasaValidacionTestChallenge() throws DAOException {

        Cliente cliente = getCliente();

        TransferenciaView transferenciaView = getTransferenciaViewForRSAOfflineTest();

        Respuesta<RsaRiesgoTransferenciaDTO> respuestaRsaRiesgoTransferencia = getRespuestaRsaRiesgoTransferencia();

        Respuesta<DestinatariosFrecuentesDTO> respuestaDestinatariosFrecuentesBO = getRespuestaDestinatariosFrecuentesForValidationOK();

        String hash = HashUtils.obtenerHash(TransferenciaUtil.crearMapaDeTransferenciaView(transferenciaView));

        ReflectionTestUtils.setField(transferenciaManager, "valorDesafioTransferencias", "2");

        Respuesta<AutentificacionDTO> respuestaAutentificacion = getRespuestaAutentificacion();

        Mockito.when(mockSesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mockSesionCliente.getTieneTokenRSA()).thenReturn(false);
        Mockito.when(sesionParametros.getDatosTransferenciaDestino()).thenReturn(new DatosTransferenciaDestino());
        Mockito.when(mockMetricBuilder.createMetricTransfer(Matchers.any(TransferMetricInfoDTO.class))).thenReturn(getMetric());
        Mockito.when(mockAutentificacionManager.obtenerDesafioHabilitadoOperacion(Matchers.any(AutentificacionDTO.class))).thenReturn(TipoDesafioEnum.TOKEN);
        Mockito.when(mockRsaManager.analizarRiesgoTransferencia(Matchers.any(TransferenciaView.class), Matchers.any(TipoDesafioEnum.class))).thenReturn(respuestaRsaRiesgoTransferencia);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn(hash);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(mockAutentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class))).thenReturn(respuestaAutentificacion);
        Mockito.when(mockDestinatariosFrecuentesBO.obtenerDestinatariosFrecuentes(cliente)).thenReturn(respuestaDestinatariosFrecuentesBO);

        Mockito.when(sesionParametros.getRsaGenericRequestData()).thenReturn(new RsaGenericRequestData());
        Mockito.when(mockBiocatchManager.getScoreTransferencia(Matchers.anyString(),Matchers.anyString(),Matchers.<ActivityName>any(),Matchers.<ActivityType>any(),Matchers.<BiocatchTransferInfoDTO>anyObject())).thenReturn(BiocatchResponseDataDTOFeature.getBiocatchResponseDataDTOChallenge());
        Mockito.when(mockAccountsApi.getAccountByAccountId((Matchers.anyString()),Matchers.anyString())).thenReturn(AccountEntityFeature.getAccountEntity());
        Respuesta<TransferenciaView> respuesta = transferenciaManager.ejecutarNuevaTransferencia(transferenciaView);

        Assert.assertNotNull(respuesta.getRespuesta());
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());

    }

    private Cliente getCliente() {

        Cliente cliente = new Cliente();
        cliente.setNup("123456");
        Cuenta cuenta = new Cuenta();
        cuenta.setEstadoTarjetaCredito("01");
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta.setNroSucursal("152");
        cuenta.setNroTarjetaCredito("4517660024736620");
        cuenta.setNroCuentaProducto("0000000000639170");
        cuenta.setTipoCuenta("01");
        cliente.setDni("20123123");
        cliente.setNumeroCUILCUIT("20-25252525-6");

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);

        return cliente;
    }

    private TransferenciaView getTransferenciaViewForRSAOfflineTest() {

        SimpleDateFormat fechaFormato = new SimpleDateFormat("dd/MM/yyyy");

        TransferenciaView transferenciaView = obtenerTransferenciaView();
        transferenciaView.setCuentaPropia(false);
        transferenciaView.setInmediata(true);
        transferenciaView.setFechaEjecucion(fechaFormato.format(new Date()));
        transferenciaView.setIsRioRio(false);
        transferenciaView.setMoneda("peso");
        transferenciaView.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        transferenciaView.setCbu(CBU_NO_RIO_OK);

        return transferenciaView;

    }

    private Respuesta<DestinatariosFrecuentesDTO> getRespuestaDestinatariosFrecuentesForValidationFail() {

        SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss.ms");

        DestinatarioAgendaDTO destinatarioAgenda = new DestinatarioAgendaDTO();
        destinatarioAgenda.setCbu(CBU_NO_RIO_OK);
        destinatarioAgenda.setFechaCreacion(fechaFormato.format(new Date()));

        DestinatariosFrecuentesDTO destinatariosFrecuentesDTO = new DestinatariosFrecuentesDTO();
        destinatariosFrecuentesDTO.getDestinatariosFavoritos().add(destinatarioAgenda);

        Respuesta<DestinatariosFrecuentesDTO> respuestaDestinatariosFrecuentesBO = new Respuesta<DestinatariosFrecuentesDTO>();
        respuestaDestinatariosFrecuentesBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaDestinatariosFrecuentesBO.setRespuesta(destinatariosFrecuentesDTO);

        return respuestaDestinatariosFrecuentesBO;

    }

    private Respuesta<DestinatariosFrecuentesDTO> getRespuestaDestinatariosFrecuentesForValidationOK() {

        SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss.ms");

        DestinatarioAgendaDTO destinatarioAgenda = new DestinatarioAgendaDTO();
        destinatarioAgenda.setCbu(CBU_NO_RIO_OK);
        destinatarioAgenda.setFechaCreacion(fechaFormato.format(FechaUtils.obtenerFechaDesde(20)));

        DestinatariosFrecuentesDTO destinatariosFrecuentesDTO = new DestinatariosFrecuentesDTO();
        destinatariosFrecuentesDTO.getDestinatariosFavoritos().add(destinatarioAgenda);

        Respuesta<DestinatariosFrecuentesDTO> respuestaDestinatariosFrecuentesBO = new Respuesta<DestinatariosFrecuentesDTO>();
        respuestaDestinatariosFrecuentesBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaDestinatariosFrecuentesBO.setRespuesta(destinatariosFrecuentesDTO);

        return respuestaDestinatariosFrecuentesBO;

    }

    private Respuesta<RsaRiesgoTransferenciaDTO> getRespuestaRsaRiesgoTransferencia() {

        RsaRiesgoTransferenciaDTO rsaRiesgoTransferenciaDTO = new RsaRiesgoTransferenciaDTO(ActionCode.CHALLENGE, false, "Y");
        rsaRiesgoTransferenciaDTO.setRuleId("X");
        Respuesta<RsaRiesgoTransferenciaDTO> respuestaRsaRiesgoTransferencia = new Respuesta<RsaRiesgoTransferenciaDTO>();
        respuestaRsaRiesgoTransferencia.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaRsaRiesgoTransferencia.setRespuesta(rsaRiesgoTransferenciaDTO);

        return respuestaRsaRiesgoTransferencia;

    }

    private Respuesta<AutentificacionDTO> getRespuestaAutentificacion() {

        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setTipoDesafio(TipoDesafioEnum.TOKEN);
        Respuesta<AutentificacionDTO> respuestaAutentificacion = new Respuesta<AutentificacionDTO>();
        respuestaAutentificacion.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaAutentificacion.setRespuesta(autentificacionDTO);
        ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
        itemMensajeRespuesta.setTipoError(TipoError.ERROR_OTROERROR_TOKEN.getDescripcion());
        ArrayList<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
        itemMensajeRespuestaList.add(itemMensajeRespuesta);
        respuestaAutentificacion.setItemMensajeRespuesta(itemMensajeRespuestaList);

        return respuestaAutentificacion;

    }

    private MetricWithTags getMetric() {

        return new MetricWithTags("cmdb", "metrica", 1L, MetricType.COUNTER, new ArrayList<MetricTag>());

    }

    /**
     * @return
     */
    private Respuesta<TransferenciaView> armarTransferenciaEsperada() {
        Respuesta<TransferenciaView> transferenciaEsperada = new Respuesta<TransferenciaView>();
        transferenciaView = new TransferenciaView();
        transferenciaView.setCbu("0070002330004402734562");
        transferenciaEsperada.setRespuesta(transferenciaView);
        transferenciaEsperada.setRespuestaVacia(true);
        transferenciaEsperada.setEstadoRespuesta(EstadoRespuesta.OK);
        return transferenciaEsperada;
    }

    /**
     * @return
     */
    private TransferenciaView armarTransferenciaView() {
        TransferenciaView transferenciaView = new TransferenciaView();
        transferenciaView.setCbu(CBU_NO_RIO_OK);
        transferenciaView.setNroCuentaDestino("333-666666/7");
        transferenciaView.setConceptoTransferencia(ConceptoTransferenciaEnum.getConceptoView());
        transferenciaView.setTitular("Nombre Titular Cuenta");
        transferenciaView.setTipoCuentaDestino("CU");
        ArrayList<String> monedasDisponibles = new ArrayList<String>();
        monedasDisponibles.add("Pesos");
        transferenciaView.setMonedasDisponibles(monedasDisponibles);
        transferenciaView.setMoneda("pesos");
        transferenciaView.setImporte("123");
        return transferenciaView;
    }

    /**
     * Lista cuenta saldo para cuenta manager.
     *
     * @return the respuesta
     */
    private Respuesta<CuentasView> armarlistaCuentaSaldoParaCuentaManager() {
        Respuesta<CuentasView> respuestaCuentasViewOk = new Respuesta<CuentasView>();
        CuentasView cuentasView = new CuentasView();
        List<CuentasAdhesionDebitoView> listCuentasView = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView cuentaView1 = new CuentasAdhesionDebitoView();
        CuentasAdhesionDebitoView cuentaView2 = new CuentasAdhesionDebitoView();
        cuentaView1.setNumero("333-666654/7");
        cuentaView1.setSaldoPesos("500000");
        cuentaView1.setSaldoDolares("35000");
        cuentaView1.setIsCerrada(false);
        cuentaView1.setDescripcionTipoCuenta("Caja de ahorro en $");
        listCuentasView.add(cuentaView1);
        cuentaView2.setIsCerrada(false);
        cuentaView2.setNumero("333-666655/7");
        cuentaView2.setSaldoPesos("500000");
        cuentaView2.setSaldoDolares("35000");
        cuentaView2.setSignoSaldoPesos("");
        cuentaView2.setDescripcionTipoCuenta("Caja de ahorro en $");
        listCuentasView.add(cuentaView2);
        cuentasView.setCuentas(listCuentasView);
        respuestaCuentasViewOk.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaCuentasViewOk.setRespuesta(cuentasView);
        return respuestaCuentasViewOk;
    }

    /**
     * Generar DTO respuesta. utilidad.
     * 
     * @return the transferencia DTO
     */
    public TransferenciaDTO generarDtoRta() {
        TransferenciaDTO transferencia = new TransferenciaDTO();
        transferencia.setTitular("Agostino De Flores  Antigua Be");
        transferencia.setMoneda(DivisaEnum.PESO);
        transferencia.setImporte(new BigDecimal("4"));

        Cuenta cuentaOrigen = new Cuenta();
        cuentaOrigen.setNroSucursal("000");
        cuentaOrigen.setNroCuentaProducto("0639170");
        cuentaOrigen.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        transferencia.setCuentaOrigen(cuentaOrigen);
        transferencia.setCuit("20356729175");
        transferencia.setCbuCuenta("234234234234234");
        transferencia.setHaciaOtroBanco(false);
        transferencia.setFechaProgramada(new Date("06/06/2017"));
        transferencia.setPlazoAcreditacion(PlazoAcreditacion.INMEDIATO);
        transferencia.setConcepto(ConceptoTransferenciaEnum.VARIOS);
        transferencia.setDescripcionAdicional("Descripcion adicional");
        transferencia.setMensaje("Mensaje");
        transferencia.setEmail("Email");
        transferencia.setEmailMensaje("Email mensaje");
        IdentificacionCuenta ic = new IdentificacionCuenta();
        ic.setNroCuentaProducto("3632381");
        ic.setNroSucursal("201");
        transferencia.setNumeroCuentaDestino(ic);
        transferencia.setTipoCuentaDestino(TipoCuenta.CUENTA_UNICA_PESOS);
        // transferencia.setHacerChallenge(false);
        transferencia.setNombreReceptor("Jorge");
        transferencia.setFechaCompensacion("06/06/2017");
        transferencia.setIdRecibo("20170808454545");

        return transferencia;
    }
}
