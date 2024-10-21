package ar.com.santanderrio.obp.servicios.transferencias.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.mock.AutentificacionDTOMock;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.constantes.BancoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO;
import ar.com.santanderrio.obp.servicios.comun.contrato.bo.impl.ContratoBOImpl;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.TipoContratoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConceptoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaNotifyResponseData;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import ar.com.santanderrio.obp.servicios.transferencias.bo.AgendaTransferenciaBO;
import ar.com.santanderrio.obp.servicios.transferencias.bo.impl.AgendaTransferenciaBOImpl;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.BancoDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.DestinatarioDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.FrecuenciaTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaEjecutadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.web.manager.impl.AgendaTransferenciaManagerImpl;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.AgendaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.AgendamientoTransferenciaView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.ConceptoView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaDetalleView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaAgendadaView;

/**
 * The Class AgendaTransferenciaManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class AgendaTransferenciaManagerTest {

    /** The manager. */
    @InjectMocks
    private AgendaTransferenciaManagerImpl manager;

    /** The autentificacion manager. */
    @Mock
    private AutentificacionManager autentificacionManager;

    /** The session parametros. */
    @Mock
    private SesionParametros sessionParametros;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The respuesta BO. */
    @Mock
    private AgendaTransferenciaBO agendaTransferenciaBO = new AgendaTransferenciaBOImpl();

    @Mock
    private ContratoBO contratoBO = new ContratoBOImpl();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    private MensajeBO mensajeBO;

    /** The mensaje Manager. */
    @Mock
    private MensajeManager mensajeManager;

    /** The mensaje Manager. */
    @Mock
    private RsaManager rsaManager;

    @Mock
    private Cliente mockCliente;

    List<Cuenta> listaCuentas = new ArrayList<Cuenta>();

    /** The Constant MSJ_SOFT_TOKEN_INPUT. */
    private static final String MSJ_SOFT_TOKEN_INPUT = " <p><b>¡Ya casi terminás!</b></p><p>Confirmá tu operación ingresando los 6 dígitos de seguridad.</p>";

    Cuenta cuenta1 = new Cuenta();
    Cuenta cuenta2 = new Cuenta();
    Cuenta cuenta3 = new Cuenta();

    /**
     * Inits the.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        CuentasView cuentasView = new CuentasView();
        List<CuentasAdhesionDebitoView> listCuentasView = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView cuentaView1 = new CuentasAdhesionDebitoView();
        CuentasAdhesionDebitoView cuentaView2 = new CuentasAdhesionDebitoView();

        cuentaView1.setIsCerrada(false);
        listCuentasView.add(cuentaView1);
        cuentaView2.setIsCerrada(false);
        listCuentasView.add(cuentaView2);
        cuentasView.setCuentas(listCuentasView);

        cuenta1.setEstadoTarjetaCredito("01");
        cuenta1.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta1.setTipoCuenta("01");
        cuenta1.setNroSucursal("152");
        cuenta1.setNroTarjetaCredito("4517660024736620");
        cuenta1.setNroCuentaProducto("0000000004583174");
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

        sesionCliente.setCliente(mockCliente);
    }

    /**
     * Ejecutar agendamiento transferencia recordatorio rio rio OK test.
     */
    @Test
    public void ejecutarAgendamientoTransferenciaRecordatorioRioRioOKTest() {
        AgendamientoTransferenciaView transferenciaView = obtenerAgendamientoTransferenciaView();

        TransferenciaDTO transferenciaDTO = obtenerTransferenciaDTO();
        transferenciaDTO.setBancoDestino(BancoEnum.SANTANDER_RIO.getDescripcion());
        transferenciaDTO.setHaciaOtroBanco(Boolean.FALSE);

        Respuesta<TransferenciaDTO> respuestaEsperada = new Respuesta<TransferenciaDTO>();
        respuestaEsperada.setRespuesta(transferenciaDTO);
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.OK);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("La transferencia a {0} se ah realizado por {1}");

        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionCliente.getCliente()).thenReturn(this.mockCliente);
        when(mockCliente.getCuentaPorNumero(Matchers.anyString())).thenReturn(this.cuenta1);
        when(agendaTransferenciaBO.ejecutarAgendamientoRecordatorioTransferencia(Matchers.any(TransferenciaDTO.class),
                Matchers.any(Cliente.class))).thenReturn(respuestaEsperada);

        Respuesta<AgendamientoTransferenciaView> respuesta = manager
                .ejecutarAgendamientoTransferencia(transferenciaView);

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void ejecutarAgendamientoTransferenciaRecordatorioOBOKTest() {
        AgendamientoTransferenciaView transferenciaView = obtenerAgendamientoTransferenciaView();

        TransferenciaDTO transferenciaDTO = obtenerTransferenciaDTO();
        transferenciaDTO.setBancoDestino("Banco Macro");
        transferenciaDTO.setHaciaOtroBanco(Boolean.TRUE);

        Respuesta<TransferenciaDTO> respuestaEsperada = new Respuesta<TransferenciaDTO>();
        respuestaEsperada.setRespuesta(transferenciaDTO);
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.OK);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("La transferencia a {0} se ah realizado por {1}");

        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionCliente.getCliente()).thenReturn(mockCliente);
        when(mockCliente.getCuentaPorNumero(Matchers.anyString())).thenReturn(this.cuenta1);
        when(agendaTransferenciaBO.ejecutarAgendamientoRecordatorioTransferencia(Matchers.any(TransferenciaDTO.class),
                Matchers.any(Cliente.class))).thenReturn(respuestaEsperada);

        Respuesta<AgendamientoTransferenciaView> respuesta = manager
                .ejecutarAgendamientoTransferencia(transferenciaView);

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void ejecutarAgendamientoTransferenciaRecordatorioRioRioERRORTest() {
        AgendamientoTransferenciaView transferenciaView = obtenerAgendamientoTransferenciaView();

        TransferenciaDTO transferenciaDTO = obtenerTransferenciaDTO();
        transferenciaDTO.setBancoDestino(BancoEnum.SANTANDER_RIO.getDescripcion()); // Setear nuevamente
                                                           // en tests para
                                                           // otros bancos
        transferenciaDTO.setHaciaOtroBanco(Boolean.FALSE);// Setear nuevamente
                                                          // en tests para
                                                          // otros bancos

        Respuesta<TransferenciaDTO> respuestaEsperada = new Respuesta<TransferenciaDTO>();
        respuestaEsperada.setRespuesta(transferenciaDTO);
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.WARNING);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("La transferencia a {0} se ah realizado por {1}");

        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        ContadorIntentos contador = new ContadorIntentos();
        String mensajeError = "Mensaje de error";
        contador.setIdView(transferenciaView.getIdSesion(), 2, mensajeError);

        when(sessionParametros.getContador()).thenReturn(contador);
        when(sesionCliente.getCliente()).thenReturn(mockCliente);
        when(mockCliente.getCuentaPorNumero(Matchers.anyString())).thenReturn(this.cuenta1);
        when(agendaTransferenciaBO.ejecutarAgendamientoRecordatorioTransferencia(Matchers.any(TransferenciaDTO.class),
                Matchers.any(Cliente.class))).thenReturn(respuestaEsperada);

        Respuesta<AgendamientoTransferenciaView> respuesta = manager
                .ejecutarAgendamientoTransferencia(transferenciaView);

        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }

    @Test
    public void ejecutarAgendamientoTransferenciaRecordatorioOBERRORTest() {
        AgendamientoTransferenciaView transferenciaView = obtenerAgendamientoTransferenciaView();

        TransferenciaDTO transferenciaDTO = obtenerTransferenciaDTO();
        transferenciaDTO.setBancoDestino(BancoEnum.SANTANDER_RIO.getDescripcion());
        transferenciaDTO.setHaciaOtroBanco(Boolean.TRUE);
        Respuesta<TransferenciaDTO> respuestaEsperada = new Respuesta<TransferenciaDTO>();
        respuestaEsperada.setRespuesta(transferenciaDTO);
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.WARNING);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("La transferencia a {0} se ah realizado por {1}");

        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        ContadorIntentos contador = new ContadorIntentos();
        String mensajeError = "Mensaje de error";
        contador.setIdView(transferenciaView.getIdSesion(), 2, mensajeError);

        when(sessionParametros.getContador()).thenReturn(contador);
        when(sesionCliente.getCliente()).thenReturn(mockCliente);
        when(mockCliente.getCuentaPorNumero(Matchers.anyString())).thenReturn(this.cuenta1);
        when(agendaTransferenciaBO.ejecutarAgendamientoRecordatorioTransferencia(Matchers.any(TransferenciaDTO.class),
                Matchers.any(Cliente.class))).thenReturn(respuestaEsperada);

        Respuesta<AgendamientoTransferenciaView> respuesta = manager
                .ejecutarAgendamientoTransferencia(transferenciaView);

        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }

    @Test
    public void ejecutarAgendamientoTransferenciaAutomaticoRioRioOKTest() throws IllegalAccessException {
        AgendamientoTransferenciaView transferenciaView = obtenerAgendamientoTransferenciaView();
        Respuesta<AutentificacionDTO> respuestaRSA = obtenerRespuestaAutentificacionDTONuevaTxOK();
        Respuesta<RsaNotifyResponseData> notifyRSA = obtenerRespuestaNotifyRSAOK();

        transferenciaView.setAutomatica(Boolean.TRUE);

        TransferenciaDTO transferenciaDTO = obtenerTransferenciaDTO();
        transferenciaDTO.setBancoDestino(BancoEnum.SANTANDER_RIO.getDescripcion());
        transferenciaDTO.setHaciaOtroBanco(Boolean.FALSE);

        FieldUtils.writeDeclaredField(manager, "valorDesafioTransferenciasAgendadas", "2", true);
        FieldUtils.writeDeclaredField(manager, "valorDesafioTransferencias", "2", true);
        Respuesta<TransferenciaDTO> respuestaEsperada = new Respuesta<TransferenciaDTO>();
        respuestaEsperada.setRespuesta(transferenciaDTO);
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.OK);

        ContadorIntentos contador = new ContadorIntentos();
        String mensajeError = "Mensaje de error";
        contador.setIdView(transferenciaView.getIdSesion(), 2, mensajeError);

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000045831774");
        cuentas.add(cuenta);
        Cliente cliente = new Cliente();
        cliente.setCuentas(cuentas);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("La transferencia a {0} se ah realizado por {1}");

        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("");
        when(rsaManager.notificar(Matchers.any(AutentificacionDTO.class), Matchers.anyInt())).thenReturn(notifyRSA);
        when(sessionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sessionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(sessionParametros.getContador()).thenReturn(contador);
        when(sesionCliente.getCliente()).thenReturn(mockCliente);
        when(mockCliente.getCuentaPorNumero(Matchers.anyString())).thenReturn(this.cuenta1);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respuestaRSA);
        when(agendaTransferenciaBO.ejecutarAgendamientoAutomaticoTransferencia(Matchers.any(TransferenciaDTO.class),
                Matchers.any(Cliente.class))).thenReturn(respuestaEsperada);

        Respuesta<AgendamientoTransferenciaView> respuesta = manager
                .ejecutarAgendamientoTransferencia(transferenciaView);

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void ejecutarAgendamientoTransferenciaAutomaticoOBOKTest() throws IllegalAccessException {
        Respuesta<AutentificacionDTO> respuestaRSA = obtenerRespuestaAutentificacionDTONuevaTxOK();
        Respuesta<RsaNotifyResponseData> notifyRSA = obtenerRespuestaNotifyRSAOK();

        AgendamientoTransferenciaView transferenciaView = obtenerAgendamientoTransferenciaView();
        transferenciaView.setAutomatica(Boolean.TRUE);

        TransferenciaDTO transferenciaDTO = obtenerTransferenciaDTO();
        transferenciaDTO.setBancoDestino("Banco Macro");
        transferenciaDTO.setHaciaOtroBanco(Boolean.TRUE);
        Respuesta<TransferenciaDTO> respuestaEsperada = new Respuesta<TransferenciaDTO>();
        respuestaEsperada.setRespuesta(transferenciaDTO);
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.OK);

        ContadorIntentos contador = new ContadorIntentos();
        String mensajeError = "Mensaje de error";
        contador.setIdView(transferenciaView.getIdSesion(), 2, mensajeError);

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000045831774");
        cuentas.add(cuenta);
        Cliente cliente = new Cliente();
        cliente.setCuentas(cuentas);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("La transferencia a {0} se ah realizado por {1}");
        FieldUtils.writeDeclaredField(manager, "valorDesafioTransferenciasAgendadas", "2", true);
        FieldUtils.writeDeclaredField(manager, "valorDesafioTransferencias", "2", true);
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("");

        when(rsaManager.notificar(Matchers.any(AutentificacionDTO.class), Matchers.anyInt())).thenReturn(notifyRSA);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sessionParametros.getContador()).thenReturn(contador);
        when(sessionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respuestaRSA);
        when(sesionCliente.getCliente()).thenReturn(mockCliente);
        when(mockCliente.getCuentaPorNumero(Matchers.anyString())).thenReturn(this.cuenta1);
        when(agendaTransferenciaBO.ejecutarAgendamientoAutomaticoTransferencia(Matchers.any(TransferenciaDTO.class),
                Matchers.any(Cliente.class))).thenReturn(respuestaEsperada);

        Respuesta<AgendamientoTransferenciaView> respuesta = manager
                .ejecutarAgendamientoTransferencia(transferenciaView);

        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    private Respuesta<RsaNotifyResponseData> obtenerRespuestaNotifyRSAOK() {
        Respuesta<RsaNotifyResponseData> respuesta = new Respuesta<RsaNotifyResponseData>();
        // actualizarSesionRsa(rsaNotifyRequestData.getRsaGenericRequestData(),
        // respuestaNotify.getRsaGenericResponseData());
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        // respuesta.setRespuesta(respuestaNotify);
        respuesta.setRespuestaVacia(false);
        return respuesta;
    }

    @Test
    public void ejecutarAgendamientoTransferenciaAutomaticoRioRioERRORTest() throws IllegalAccessException {
        Respuesta<AutentificacionDTO> respuestaRCA = obtenerRespuestaAutentificacionDTONuevaTxOK();

        AgendamientoTransferenciaView transferenciaView = obtenerAgendamientoTransferenciaView();
        transferenciaView.setAutomatica(Boolean.TRUE);

        TransferenciaDTO transferenciaDTO = obtenerTransferenciaDTO();
        transferenciaDTO.setBancoDestino(BancoEnum.SANTANDER_RIO.getDescripcion());
        transferenciaDTO.setHaciaOtroBanco(Boolean.FALSE);
        FieldUtils.writeDeclaredField(manager, "valorDesafioTransferenciasAgendadas", "2", true);
        FieldUtils.writeDeclaredField(manager, "valorDesafioTransferencias", "2", true);
        Respuesta<TransferenciaDTO> respuestaEsperada = new Respuesta<TransferenciaDTO>();
        respuestaEsperada.setRespuesta(transferenciaDTO);
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.WARNING);

        ContadorIntentos contador = new ContadorIntentos();
        String mensajeError = "Mensaje de error";
        contador.setIdView(transferenciaView.getIdSesion(), 2, mensajeError);

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000045831774");
        cuentas.add(cuenta);
        Cliente cliente = new Cliente();
        cliente.setCuentas(cuentas);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("La transferencia a {0} se ah realizado por {1}");
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("");

        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sessionParametros.getContador()).thenReturn(contador);
        when(sesionCliente.getCliente()).thenReturn(mockCliente);
        when(mockCliente.getCuentaPorNumero(Matchers.anyString())).thenReturn(this.cuenta1);
        when(sessionParametros.getRegistroSession()).thenReturn(registroSesion);

        when(agendaTransferenciaBO.ejecutarAgendamientoAutomaticoTransferencia(Matchers.any(TransferenciaDTO.class),
                Matchers.any(Cliente.class))).thenReturn(respuestaEsperada);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respuestaRCA);
        Respuesta<AgendamientoTransferenciaView> respuesta = manager
                .ejecutarAgendamientoTransferencia(transferenciaView);

        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }

    @Test
    public void ejecutarAgendamientoTransferenciaAutomaticoOBERRORTest() throws IllegalAccessException {
        AgendamientoTransferenciaView transferenciaView = obtenerAgendamientoTransferenciaView();
        Respuesta<AutentificacionDTO> respuestaRCA = obtenerRespuestaAutentificacionDTONuevaTxOK();
        Respuesta<RsaNotifyResponseData> notifyRSA = obtenerRespuestaNotifyRSAOK();

        transferenciaView.setAutomatica(Boolean.TRUE);

        TransferenciaDTO transferenciaDTO = obtenerTransferenciaDTO();
        transferenciaDTO.setBancoDestino(BancoEnum.SANTANDER_RIO.getDescripcion()); // Setear nuevamente
                                                           // en tests para
                                                           // otros bancos
        transferenciaDTO.setHaciaOtroBanco(Boolean.TRUE);// Setear nuevamente en
                                                         // tests para otros
                                                         // bancos
        FieldUtils.writeDeclaredField(manager, "valorDesafioTransferenciasAgendadas", "2", true);
        FieldUtils.writeDeclaredField(manager, "valorDesafioTransferencias", "2", true);
        Respuesta<TransferenciaDTO> respuestaEsperada = new Respuesta<TransferenciaDTO>();
        respuestaEsperada.setRespuesta(transferenciaDTO);
        respuestaEsperada.setEstadoRespuesta(EstadoRespuesta.WARNING);

        ContadorIntentos contador = new ContadorIntentos();
        String mensajeError = "Mensaje de error";
        contador.setIdView(transferenciaView.getIdSesion(), 2, mensajeError);

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000045831774");
        cuentas.add(cuenta);
        Cliente cliente = new Cliente();
        cliente.setCuentas(cuentas);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("La transferencia a {0} se ah realizado por {1}");
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("");
        when(rsaManager.notificar(Matchers.any(AutentificacionDTO.class), Matchers.anyInt())).thenReturn(notifyRSA);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sessionParametros.getContador()).thenReturn(contador);
        when(sessionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respuestaRCA);
        when(sesionCliente.getCliente()).thenReturn(mockCliente);
        when(mockCliente.getCuentaPorNumero(Matchers.anyString())).thenReturn(this.cuenta1);
        when(agendaTransferenciaBO.ejecutarAgendamientoAutomaticoTransferencia(Matchers.any(TransferenciaDTO.class),
                Matchers.any(Cliente.class))).thenReturn(respuestaEsperada);

        Respuesta<AgendamientoTransferenciaView> respuesta = manager
                .ejecutarAgendamientoTransferencia(transferenciaView);

        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }

    private TransferenciaDTO obtenerTransferenciaDTO() {
        TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
        transferenciaDTO.setFechaProgramada(new Date("20/08/2017"));
        transferenciaDTO.setFechaDeOperacion(new Date());
        transferenciaDTO.setCuentaOrigen(obtenerCuenta());
        transferenciaDTO.setTitular("VALERIANO PAUL TADEO COMIGNAGHI");
        transferenciaDTO.setMoneda(DivisaEnum.PESO);
        transferenciaDTO.setImporte(new BigDecimal("1414"));
        transferenciaDTO.setFrecuencia(FrecuenciaTransferenciaAgendada.CADA_3_MESES);
        transferenciaDTO.setCuit("20-34862917-5");
        transferenciaDTO.setCbuCuenta("");
        transferenciaDTO.setBancoDestino(BancoEnum.SANTANDER_RIO.getDescripcion());
        // transferenciaDTO.setNroComprobante("123123123");
        transferenciaDTO.setConcepto(ConceptoTransferenciaEnum.VARIOS);
        transferenciaDTO.setDescripcionAdicional("Varios");
        transferenciaDTO.setHaciaOtroBanco(Boolean.FALSE);
        IdentificacionCuenta ic = new IdentificacionCuenta();
        ic.setNroCuentaProducto("0639170");
        transferenciaDTO.setNumeroCuentaDestino(ic);
        transferenciaDTO.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS);
        return transferenciaDTO;
    }

    private AgendamientoTransferenciaView obtenerAgendamientoTransferenciaView() {
        AgendamientoTransferenciaView transferenciaView = new AgendamientoTransferenciaView();
        transferenciaView.setIdSesion("123123123");
        transferenciaView.setAutomatica(Boolean.FALSE);
        transferenciaView.setFechaEjecucion("17/08/2017");
        transferenciaView.setNroCuenta(obtenerCuenta().getNroCuentaProducto());
        transferenciaView.setIsRioRio(Boolean.TRUE);
        transferenciaView.setTipoCuentaDestino(TipoCuenta.CAJA_AHORRO_PESOS.getDescripcion());
        transferenciaView.setTipoCuentaDestinoDescripcion(TipoCuenta.CAJA_AHORRO_PESOS.getDescripcion());
        transferenciaView.setNroCuentaDestino("000-063917/0");
        transferenciaView.setImporte("1.234,23");
        transferenciaView.setTransferenciaManual(Boolean.FALSE);
        transferenciaView.setTitular("VALERIANO PAUL TADEO COMIGNAGHI");
        transferenciaView.setConcepto(new ConceptoView("Alquiler","8", "VAR", "Alquiler", 0));
        transferenciaView.setDescripcion("Varios");
        transferenciaView.setCuentaPropia(Boolean.TRUE);
        transferenciaView.setMoneda(DivisaEnum.PESO.getMoneda());
        transferenciaView.setBanco(BancoEnum.SANTANDER_RIO.getDescripcion());
        transferenciaView.setCuit("20-34862917-5");
        transferenciaView.setFrecuencia(FrecuenciaTransferenciaAgendada.CADA_6_MESES.getDescripcion());
        // transferenciaView.set
        return transferenciaView;
    }

    private Cuenta obtenerCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNroSucursal("0033");
        cuenta.setNroCuentaProducto("0000000003663936");
        cuenta.setTipoCuentaSinUnificar("09");
        cuenta.setNroOrdenFirmante("003");
        cuenta.setCodigoAplicacion("INFI");
        cuenta.setClaseCuenta("N");
        cuenta.setFormaDeOperar("02");
        cuenta.setNroTarjetaCredito("00000000000000000000");
        cuenta.setCodigoPaquete("2101");
        cuenta.setClasePaquete("14");
        cuenta.setSaldoCUPesos("126.60");
        cuenta.setSaldoCUDls("99997859.42");
        cuenta.setSaldoParaConformar("00000000000000+");
        cuenta.setDepositoEfectivo("00000000000000");
        cuenta.setSubproductoAltair("0001");
        cuenta.setMonedaAltair("ARS");
        cuenta.setGrupoAfinidad("000000");
        cuenta.setOficinaAltair("0201");
        cuenta.setContratoAltair("0000007003632381");
        cuenta.setSucursalPaquete("0201");
        cuenta.setNumeroPaquete("000090000131928");
        cuenta.setConvenioPAS(false);
        cuenta.setHabilitadaParaTransferir(false);
        cuenta.setNroSucursal("033");
        cuenta.setNroCuentaProducto("285345831774");
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        return cuenta;
    }

    @Test
    public void obtenerAgendaTransferenciasErrorSinCuentas() {
        MockitoAnnotations.initMocks(this);
        Cliente cliente = mock(Cliente.class);
        List<Cuenta> cuentasCliente = new ArrayList<Cuenta>();
        Mensaje mensaje = mock(Mensaje.class);

        when(sesionCliente.getCliente()).thenReturn(cliente);

        when(cliente.getCuentasMonetarias()).thenReturn(cuentasCliente);

        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensaje.getMensaje()).thenReturn("Error mensaje");

        Respuesta<AgendaView> respuestaManager = manager.obtenerAgendaTransferencias();

        assertNotNull(respuestaManager);
        assertNotNull(respuestaManager.getRespuesta());
        assertEquals(respuestaManager.getRespuesta().getCantidadAutomaticas(), "0");
        assertEquals(respuestaManager.getRespuesta().getCantidadRecordatorios(), "0");
        assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    /**
     * Obtener agenda transferencias ok test.
     *
     * @throws ParseException
     *             the parse exception
     */
    @Test
    public void obtenerAgendaTransferenciasOkTest() throws ParseException {
        MockitoAnnotations.initMocks(this);
        RegistroSesion registroSesion = mock(RegistroSesion.class);
        Respuesta<List<TransferenciaAgendadaDTO>> respTransferAgendadaOtrosBancos = mock(Respuesta.class);
        Respuesta<List<TransferenciaAgendadaDTO>> respTransferAgendadaRioRio = mock(Respuesta.class);
        Cliente cliente = mock(Cliente.class);
        List<Cuenta> cuentasCliente = new ArrayList<Cuenta>();
        cuentasCliente.add(new Cuenta());

        DestinatarioDTO destinatario = mock(DestinatarioDTO.class);

        TransferenciaAgendadaDTO transferenciaRecordatorio1 = new TransferenciaAgendadaDTO();
        TransferenciaAgendadaDTO transferenciaRecordatorio2 = new TransferenciaAgendadaDTO();
        TransferenciaAgendadaDTO transferenciaRecordatorio3 = new TransferenciaAgendadaDTO();

        TransferenciaAgendadaDTO transferenciaAgendadaProgramada1 = new TransferenciaAgendadaDTO();
        TransferenciaAgendadaDTO transferenciaAgendadaRecurrente = new TransferenciaAgendadaDTO();

        transferenciaRecordatorio1.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECORDATORIO);
        transferenciaRecordatorio1.setDestinatario(destinatario);
        transferenciaRecordatorio1.setImporte(new BigDecimal("9999"));
        transferenciaRecordatorio1.setMoneda(DivisaEnum.PESO);
        transferenciaRecordatorio1.setFechaEjecucion(ISBANStringUtils.parseFecha("17/05/2016"));
        transferenciaRecordatorio1.setFrecuencia(FrecuenciaTransferenciaAgendada.CADA_3_MESES);
        transferenciaRecordatorio1.setHaciaOtroBanco(Boolean.FALSE);

        transferenciaRecordatorio2.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECORDATORIO);
        transferenciaRecordatorio2.setDestinatario(destinatario);
        transferenciaRecordatorio2.setImporte(new BigDecimal("9999"));
        transferenciaRecordatorio2.setMoneda(DivisaEnum.PESO);
        transferenciaRecordatorio2.setFechaEjecucion(ISBANStringUtils.parseFecha("17/05/2016"));
        transferenciaRecordatorio2.setFrecuencia(FrecuenciaTransferenciaAgendada.CADA_3_MESES);
        transferenciaRecordatorio2.setHaciaOtroBanco(Boolean.FALSE);

        transferenciaRecordatorio3.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECORDATORIO);
        transferenciaRecordatorio3.setDestinatario(destinatario);
        transferenciaRecordatorio3.setImporte(new BigDecimal("9999"));
        transferenciaRecordatorio3.setMoneda(DivisaEnum.PESO);
        transferenciaRecordatorio3.setFechaEjecucion(ISBANStringUtils.parseFecha("17/05/2016"));
        transferenciaRecordatorio3.setFrecuencia(FrecuenciaTransferenciaAgendada.CADA_3_MESES);
        transferenciaRecordatorio3.setHaciaOtroBanco(Boolean.FALSE);

        transferenciaAgendadaProgramada1.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.PROGRAMADA);
        transferenciaAgendadaProgramada1.setDestinatario(destinatario);
        transferenciaAgendadaProgramada1.setImporte(new BigDecimal("9999"));
        transferenciaAgendadaProgramada1.setMoneda(DivisaEnum.PESO);
        transferenciaAgendadaProgramada1.setFechaEjecucion(ISBANStringUtils.parseFecha("17/05/2016"));
        transferenciaAgendadaProgramada1.setHaciaOtroBanco(Boolean.FALSE);

        transferenciaAgendadaRecurrente.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECURRENTE);
        transferenciaAgendadaRecurrente.setDestinatario(destinatario);
        transferenciaAgendadaRecurrente.setImporte(new BigDecimal("9999"));
        transferenciaAgendadaRecurrente.setMoneda(DivisaEnum.PESO);
        transferenciaAgendadaRecurrente.setFechaEjecucion(ISBANStringUtils.parseFecha("17/05/2016"));
        transferenciaAgendadaRecurrente.setFrecuencia(FrecuenciaTransferenciaAgendada.CADA_3_MESES);
        transferenciaAgendadaRecurrente.setHaciaOtroBanco(Boolean.FALSE);

        List<TransferenciaAgendadaDTO> transferenciasAgendadasRR = new ArrayList<TransferenciaAgendadaDTO>();
        transferenciasAgendadasRR.add(transferenciaRecordatorio1);
        transferenciasAgendadasRR.add(transferenciaRecordatorio2);
        transferenciasAgendadasRR.add(transferenciaAgendadaProgramada1);
        transferenciasAgendadasRR.add(transferenciaAgendadaRecurrente);

        List<TransferenciaAgendadaDTO> transferenciasAgendadasOB = new ArrayList<TransferenciaAgendadaDTO>();
        transferenciasAgendadasOB.add(transferenciaRecordatorio3);

        when(destinatario.getNombre()).thenReturn("NOMBRE APELLIDO");
        BancoDTO bancoDTO = new BancoDTO();
        bancoDTO.setNombre(BancoEnum.SANTANDER_RIO.getDescripcion());
        bancoDTO.setDetalle("Terceros Santander");

        when(destinatario.getBanco()).thenReturn(bancoDTO);

        when(sesionCliente.getCliente()).thenReturn(cliente);

        when(cliente.getCuentasMonetarias()).thenReturn(cuentasCliente);

        when(contratoBO.tieneContrato(Matchers.any(TipoContratoEnum.class), Matchers.any(Cliente.class)))
                .thenReturn(Boolean.TRUE);

        // when(agendaTransferenciaBO.obtenerTransferenciasAgendadasRioRio(Matchers.any(Cliente.class)))
        // .thenReturn(respuesta);
        // when(agendaTransferenciaBO.obtenerTransferenciasAgendadasOtrosBancos(Matchers.any(Cliente.class)))
        // .thenReturn(respuesta);

        when(respTransferAgendadaRioRio.getRespuesta()).thenReturn(transferenciasAgendadasRR);
        when(respTransferAgendadaRioRio.getEstadoRespuesta()).thenReturn(EstadoRespuesta.OK);
        when(respTransferAgendadaOtrosBancos.getRespuesta()).thenReturn(transferenciasAgendadasOB);
        when(respTransferAgendadaOtrosBancos.getEstadoRespuesta()).thenReturn(EstadoRespuesta.OK);

        when(agendaTransferenciaBO.obtenerTransferenciasAgendadasRioRio(Matchers.any(Cliente.class)))
                .thenReturn(respTransferAgendadaRioRio);
        when(agendaTransferenciaBO.obtenerTransferenciasAgendadasOtrosBancos(Matchers.any(Cliente.class)))
                .thenReturn(respTransferAgendadaOtrosBancos);

        // MOBILE
        when(sessionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(registroSesion.isMobile()).thenReturn(false);
        Respuesta<AgendaView> respuestaManager = manager.obtenerAgendaTransferencias();

        // --ASSERTS

        // conteo
        assertNotNull(respuestaManager);
        assertNotNull(respuestaManager.getRespuesta());
        assertEquals(respuestaManager.getRespuesta().getCantidadAutomaticas(), "2");
        assertEquals(respuestaManager.getRespuesta().getCantidadRecordatorios(), "3");
        assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertTrue(!respuestaManager.isRespuestaVacia());

        // lista
        assertNotNull(respuestaManager.getRespuesta().getTransferenciasAgendadas());
        assertEquals(respuestaManager.getRespuesta().getTransferenciasAgendadas().size(), 5);
        for (TransferenciaAgendadaView transferenciaAgendadaView : respuestaManager.getRespuesta()
                .getTransferenciasAgendadas()) {
            assertEquals(transferenciaAgendadaView.getDestinatarioNombre(), "Nombre Apellido");
            assertEquals(transferenciaAgendadaView.getDestinatarioBanco(), "Terceros Santander");
            assertEquals(transferenciaAgendadaView.getFecha(), "17/05/2016");
            assertEquals(transferenciaAgendadaView.getImporte(), "9.999,00");
            assertEquals(transferenciaAgendadaView.getDivisa(), "$");
            if (Boolean.TRUE.equals(transferenciaAgendadaView.getIsProgramada())) {
                assertEquals(transferenciaAgendadaView.getTipo(), "Transferencia automática");
            }
            if (Boolean.TRUE.equals(transferenciaAgendadaView.getIsRecurrente())) {
                assertEquals(transferenciaAgendadaView.getTipo(), "Transferencia automática");
                assertEquals(transferenciaAgendadaView.getFrecuencia(),
                        FrecuenciaTransferenciaAgendada.CADA_3_MESES.getDescripcion());
            }
            if (Boolean.TRUE.equals(transferenciaAgendadaView.getIsRecordatorio())) {
                assertEquals(transferenciaAgendadaView.getTipo(), "Recordatorio de transferencia");
                assertEquals(transferenciaAgendadaView.getFrecuencia(),
                        FrecuenciaTransferenciaAgendada.CADA_3_MESES.getDescripcion());
            }
        }

        // --VERIFY

        // estadisticas
        verify(estadisticaManager).add(
                EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_INICIO_GRILLA_TRANSFERENCIA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Obtener agenda transferencias error test.
     */
    @Test
    public void obtenerAgendaTransferenciasErrorTest() {

        Respuesta<List<TransferenciaAgendadaDTO>> respuesta = mock(Respuesta.class);
        Cliente cliente = mock(Cliente.class);
        List<Cuenta> cuentasCliente = new ArrayList<Cuenta>();
        cuentasCliente.add(new Cuenta());
        ItemMensajeRespuesta itemRespuesta = mock(ItemMensajeRespuesta.class);
        Respuesta<Mensaje> respuestaMensaje = mock(Respuesta.class);
        Mensaje mensaje = mock(Mensaje.class);
        when(itemRespuesta.getTipoError()).thenReturn(TipoError.ERROR_AGENDA_TRANSFERENCIAS.getDescripcion());
        List<ItemMensajeRespuesta> itemsRespuesta = new ArrayList<ItemMensajeRespuesta>();
        itemsRespuesta.add(itemRespuesta);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensaje.getMensaje()).thenReturn("Error mensaje");
        when(respuestaMensaje.getRespuesta()).thenReturn(mensaje);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cliente.getCuentasMonetarias()).thenReturn(cuentasCliente);

        when(contratoBO.tieneContrato(Matchers.any(TipoContratoEnum.class), Matchers.any(Cliente.class)))
                .thenReturn(Boolean.TRUE);
        when(respuesta.getEstadoRespuesta()).thenReturn(EstadoRespuesta.ERROR);
        when(respuesta.getItemsMensajeRespuesta()).thenReturn(itemsRespuesta);
        when(respuesta.isRespuestaVacia()).thenReturn(true);
        when(respuesta.getRespuesta()).thenReturn(null);
        when(agendaTransferenciaBO.obtenerTransferenciasAgendadasRioRio(Matchers.any(Cliente.class)))
                .thenReturn(respuesta);
        when(agendaTransferenciaBO.obtenerTransferenciasAgendadasOtrosBancos(Matchers.any(Cliente.class)))
                .thenReturn(respuesta);

        Respuesta<AgendaView> respuestaManager = manager.obtenerAgendaTransferencias();

        assertNotNull(respuestaManager);
        assertNotNull(respuestaManager.getRespuesta());
        assertEquals(respuestaManager.getRespuesta().getCantidadAutomaticas(), "-");
        assertEquals(respuestaManager.getRespuesta().getCantidadRecordatorios(), "-");
        assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertTrue(!respuestaManager.isRespuestaVacia());

        // estadisticas
        verify(estadisticaManager).add(
                EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_INICIO_GRILLA_TRANSFERENCIA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        // EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_INICIO_CABECERA_TRANSFERENCIA,
        // EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR
    }

    /**
     * Obtener agenda transferencias error test.
     */
    @Test
    public void obtenerAgendaTransferenciasWARNTest() {

        Respuesta<List<TransferenciaAgendadaDTO>> respuesta = mock(Respuesta.class);
        Cliente cliente = mock(Cliente.class);
        List<Cuenta> cuentasCliente = new ArrayList<Cuenta>();
        cuentasCliente.add(new Cuenta());
        ItemMensajeRespuesta itemRespuesta = mock(ItemMensajeRespuesta.class);
        Respuesta<Mensaje> respuestaMensaje = mock(Respuesta.class);
        Mensaje mensaje = mock(Mensaje.class);
        when(itemRespuesta.getTipoError()).thenReturn(TipoError.ERROR_AGENDA_TRANSFERENCIAS.getDescripcion());
        List<ItemMensajeRespuesta> itemsRespuesta = new ArrayList<ItemMensajeRespuesta>();
        itemsRespuesta.add(itemRespuesta);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensaje.getMensaje()).thenReturn("WARN mensaje");
        when(respuestaMensaje.getRespuesta()).thenReturn(mensaje);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cliente.getCuentasMonetarias()).thenReturn(cuentasCliente);
        when(contratoBO.tieneContrato(Matchers.any(TipoContratoEnum.class), Matchers.any(Cliente.class)))
                .thenReturn(Boolean.TRUE);
        when(respuesta.getEstadoRespuesta()).thenReturn(EstadoRespuesta.WARNING);
        when(respuesta.getItemsMensajeRespuesta()).thenReturn(itemsRespuesta);
        when(respuesta.isRespuestaVacia()).thenReturn(true);
        when(respuesta.getRespuesta()).thenReturn(null);
        when(agendaTransferenciaBO.obtenerTransferenciasAgendadasRioRio(Matchers.any(Cliente.class)))
                .thenReturn(respuesta);
        when(agendaTransferenciaBO.obtenerTransferenciasAgendadasOtrosBancos(Matchers.any(Cliente.class)))
                .thenReturn(respuesta);

        Respuesta<AgendaView> respuestaManager = manager.obtenerAgendaTransferencias();

        assertNotNull(respuestaManager);
        assertNotNull(respuestaManager.getRespuesta());
        assertEquals(respuestaManager.getRespuesta().getCantidadAutomaticas(), "-");
        assertEquals(respuestaManager.getRespuesta().getCantidadRecordatorios(), "-");
        assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        assertTrue(respuestaManager.isRespuestaVacia());

        // estadisticas
        verify(estadisticaManager).add(
                EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_INICIO_GRILLA_TRANSFERENCIA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        // EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_INICIO_CABECERA_TRANSFERENCIA,
        // EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR
    }

    /**
     * Obtener agenda transferencias test. caso de una lista con Warning y otra
     * con error.
     * 
     * @throws ParseException
     */
    @Test
    public void obtenerAgendaTransferenciasOKyWARNTest() throws ParseException {

        TransferenciaAgendadaDTO transferenciaRecordatorio1 = new TransferenciaAgendadaDTO();
        TransferenciaAgendadaDTO transferenciaRecordatorio2 = new TransferenciaAgendadaDTO();
        TransferenciaAgendadaDTO transferenciaAgendadaProgramada = new TransferenciaAgendadaDTO();
        TransferenciaAgendadaDTO transferenciaAgendadaRecurrente = new TransferenciaAgendadaDTO();

        DestinatarioDTO destinatario = mock(DestinatarioDTO.class);
        transferenciaRecordatorio1.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECORDATORIO);
        transferenciaRecordatorio1.setDestinatario(destinatario);
        transferenciaRecordatorio1.setImporte(new BigDecimal("9999"));
        transferenciaRecordatorio1.setMoneda(DivisaEnum.PESO);
        transferenciaRecordatorio1.setFechaEjecucion(ISBANStringUtils.parseFecha("17/05/2016"));
        transferenciaRecordatorio1.setFrecuencia(FrecuenciaTransferenciaAgendada.CADA_3_MESES);
        transferenciaRecordatorio1.setHaciaOtroBanco(Boolean.FALSE);

        transferenciaRecordatorio2.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECORDATORIO);
        transferenciaRecordatorio2.setDestinatario(destinatario);
        transferenciaRecordatorio2.setImporte(new BigDecimal("9999"));
        transferenciaRecordatorio2.setMoneda(DivisaEnum.PESO);
        transferenciaRecordatorio2.setFechaEjecucion(ISBANStringUtils.parseFecha("17/05/2016"));
        transferenciaRecordatorio2.setFrecuencia(FrecuenciaTransferenciaAgendada.CADA_3_MESES);
        transferenciaRecordatorio2.setHaciaOtroBanco(Boolean.FALSE);

        transferenciaAgendadaProgramada.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.PROGRAMADA);
        transferenciaAgendadaProgramada.setDestinatario(destinatario);
        transferenciaAgendadaProgramada.setImporte(new BigDecimal("9999"));
        transferenciaAgendadaProgramada.setMoneda(DivisaEnum.PESO);
        transferenciaAgendadaProgramada.setFechaEjecucion(ISBANStringUtils.parseFecha("17/05/2016"));
        transferenciaAgendadaProgramada.setHaciaOtroBanco(Boolean.FALSE);

        transferenciaAgendadaRecurrente.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECURRENTE);
        transferenciaAgendadaRecurrente.setDestinatario(destinatario);
        transferenciaAgendadaRecurrente.setImporte(new BigDecimal("9999"));
        transferenciaAgendadaRecurrente.setMoneda(DivisaEnum.PESO);
        transferenciaAgendadaRecurrente.setFechaEjecucion(ISBANStringUtils.parseFecha("17/05/2016"));
        transferenciaAgendadaRecurrente.setFrecuencia(FrecuenciaTransferenciaAgendada.CADA_3_MESES);
        transferenciaAgendadaRecurrente.setHaciaOtroBanco(Boolean.FALSE);

        List<TransferenciaAgendadaDTO> transferenciasAgendadas = new ArrayList<TransferenciaAgendadaDTO>();
        transferenciasAgendadas.add(transferenciaRecordatorio1);
        transferenciasAgendadas.add(transferenciaRecordatorio2);
        transferenciasAgendadas.add(transferenciaAgendadaProgramada);
        transferenciasAgendadas.add(transferenciaAgendadaRecurrente);

        Respuesta<List<TransferenciaAgendadaDTO>> respuestaOK = mock(Respuesta.class);
        Respuesta<List<TransferenciaAgendadaDTO>> respuestaWR = mock(Respuesta.class);
        Cliente cliente = mock(Cliente.class);
        List<Cuenta> cuentasCliente = new ArrayList<Cuenta>();
        cuentasCliente.add(new Cuenta());
        // ItemMensajeRespuesta itemRespuesta =
        // mock(ItemMensajeRespuesta.class);
        ItemMensajeRespuesta itemRespuesta = new ItemMensajeRespuesta();

        Respuesta<Mensaje> respuestaMensaje = mock(Respuesta.class);
        RegistroSesion reg = mock(RegistroSesion.class);
        Mensaje mensaje = mock(Mensaje.class);
        itemRespuesta.setTipoError("WARN_AGENDA_TRANSFERENCIAS");
        List<ItemMensajeRespuesta> itemsRespuesta = new ArrayList<ItemMensajeRespuesta>();
        itemsRespuesta.add(itemRespuesta);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensaje.getMensaje()).thenReturn("WARN mensaje");
        when(respuestaMensaje.getRespuesta()).thenReturn(mensaje);
        when(sessionParametros.getRegistroSession()).thenReturn(reg);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cliente.getCuentasMonetarias()).thenReturn(cuentasCliente);

        when(contratoBO.tieneContrato(Matchers.any(TipoContratoEnum.class), Matchers.any(Cliente.class)))
                .thenReturn(Boolean.TRUE);

        when(reg.isMobile()).thenReturn(false);
        when(respuestaOK.getEstadoRespuesta()).thenReturn(EstadoRespuesta.OK);
        when(respuestaOK.getItemsMensajeRespuesta()).thenReturn(itemsRespuesta);
        when(respuestaOK.isRespuestaVacia()).thenReturn(false);
        when(respuestaOK.getRespuesta()).thenReturn(transferenciasAgendadas);
        when(respuestaWR.getEstadoRespuesta()).thenReturn(EstadoRespuesta.WARNING);
        when(respuestaWR.getItemsMensajeRespuesta()).thenReturn(itemsRespuesta);
        when(respuestaWR.isRespuestaVacia()).thenReturn(true);
        when(respuestaWR.getRespuesta()).thenReturn(new ArrayList<TransferenciaAgendadaDTO>());
        when(agendaTransferenciaBO.obtenerTransferenciasAgendadasRioRio(Matchers.any(Cliente.class)))
                .thenReturn(respuestaOK);
        when(agendaTransferenciaBO.obtenerTransferenciasAgendadasOtrosBancos(Matchers.any(Cliente.class)))
                .thenReturn(respuestaWR);

        Respuesta<AgendaView> respuestaManager = manager.obtenerAgendaTransferencias();

        assertNotNull(respuestaManager);
        assertNotNull(respuestaManager.getRespuesta());
        assertEquals(respuestaManager.getRespuesta().getCantidadAutomaticas(), "2");
        assertEquals(respuestaManager.getRespuesta().getCantidadRecordatorios(), "2");
        assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertTrue(!respuestaManager.isRespuestaVacia());
        // estadisticas
        verify(estadisticaManager).add(
                EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_INICIO_GRILLA_TRANSFERENCIA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    /**
     * Obtener agenda transferencias test. caso de una lista con Warning y otra
     * con error.
     * 
     * @throws ParseException
     */
    @Test
    public void obtenerAgendaTransferenciasWARNyOKTest() throws ParseException {

        TransferenciaAgendadaDTO transferenciaRecordatorio1 = new TransferenciaAgendadaDTO();
        TransferenciaAgendadaDTO transferenciaRecordatorio2 = new TransferenciaAgendadaDTO();
        TransferenciaAgendadaDTO transferenciaAgendadaProgramada = new TransferenciaAgendadaDTO();
        TransferenciaAgendadaDTO transferenciaAgendadaRecurrente = new TransferenciaAgendadaDTO();

        DestinatarioDTO destinatario = mock(DestinatarioDTO.class);
        transferenciaRecordatorio1.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECORDATORIO);
        transferenciaRecordatorio1.setDestinatario(destinatario);
        transferenciaRecordatorio1.setImporte(new BigDecimal("9999"));
        transferenciaRecordatorio1.setMoneda(DivisaEnum.PESO);
        transferenciaRecordatorio1.setFechaEjecucion(ISBANStringUtils.parseFecha("17/05/2016"));
        transferenciaRecordatorio1.setFrecuencia(FrecuenciaTransferenciaAgendada.CADA_3_MESES);
        transferenciaRecordatorio1.setHaciaOtroBanco(Boolean.FALSE);

        transferenciaRecordatorio2.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECORDATORIO);
        transferenciaRecordatorio2.setDestinatario(destinatario);
        transferenciaRecordatorio2.setImporte(new BigDecimal("9999"));
        transferenciaRecordatorio2.setMoneda(DivisaEnum.PESO);
        transferenciaRecordatorio2.setFechaEjecucion(ISBANStringUtils.parseFecha("17/05/2016"));
        transferenciaRecordatorio2.setFrecuencia(FrecuenciaTransferenciaAgendada.CADA_3_MESES);
        transferenciaRecordatorio2.setHaciaOtroBanco(Boolean.FALSE);

        transferenciaAgendadaProgramada.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.PROGRAMADA);
        transferenciaAgendadaProgramada.setDestinatario(destinatario);
        transferenciaAgendadaProgramada.setImporte(new BigDecimal("9999"));
        transferenciaAgendadaProgramada.setMoneda(DivisaEnum.PESO);
        transferenciaAgendadaProgramada.setFechaEjecucion(ISBANStringUtils.parseFecha("17/05/2016"));
        transferenciaAgendadaProgramada.setHaciaOtroBanco(Boolean.FALSE);

        transferenciaAgendadaRecurrente.setTipoTransferenciaAgendada(TipoTransferenciaAgendada.RECURRENTE);
        transferenciaAgendadaRecurrente.setDestinatario(destinatario);
        transferenciaAgendadaRecurrente.setImporte(new BigDecimal("9999"));
        transferenciaAgendadaRecurrente.setMoneda(DivisaEnum.PESO);
        transferenciaAgendadaRecurrente.setFechaEjecucion(ISBANStringUtils.parseFecha("17/05/2016"));
        transferenciaAgendadaRecurrente.setFrecuencia(FrecuenciaTransferenciaAgendada.CADA_3_MESES);
        transferenciaAgendadaRecurrente.setHaciaOtroBanco(Boolean.FALSE);

        List<TransferenciaAgendadaDTO> transferenciasAgendadas = new ArrayList<TransferenciaAgendadaDTO>();
        transferenciasAgendadas.add(transferenciaRecordatorio1);
        transferenciasAgendadas.add(transferenciaRecordatorio2);
        transferenciasAgendadas.add(transferenciaAgendadaProgramada);
        transferenciasAgendadas.add(transferenciaAgendadaRecurrente);

        // Respuesta<List<TransferenciaAgendadaDTO>> respuestaOK =
        // mock(Respuesta.class);
        // Respuesta<List<TransferenciaAgendadaDTO>> respuestaWR =
        // mock(Respuesta.class);
        // Cliente cliente = mock(Cliente.class);
        // ItemMensajeRespuesta itemRespuesta =
        // mock(ItemMensajeRespuesta.class);
        // Respuesta<Mensaje> respuestaMensaje = mock(Respuesta.class);
        // RegistroSesion reg = mock(RegistroSesion.class);
        // Mensaje mensaje = mock(Mensaje.class);
        // itemRespuesta.setTipoError("WARN_AGENDA_TRANSFERENCIAS");
        // List<ItemMensajeRespuesta> itemsRespuesta = new
        // ArrayList<ItemMensajeRespuesta>();
        // itemsRespuesta.add(itemRespuesta);
        // when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        // when(mensaje.getMensaje()).thenReturn("WARN mensaje");
        // when(respuestaMensaje.getRespuesta()).thenReturn(mensaje);
        // when(sessionParametros.getRegistroSession()).thenReturn(reg);
        // when(sesionCliente.getCliente()).thenReturn(cliente);
        // when(reg.isMobile()).thenReturn(false);
        // when(respuestaOK.getEstadoRespuesta()).thenReturn(EstadoRespuesta.OK);
        // when(respuestaOK.getItemsMensajeRespuesta()).thenReturn(itemsRespuesta);
        // when(respuestaOK.isRespuestaVacia()).thenReturn(false);
        // when(respuestaOK.getRespuesta()).thenReturn(transferenciasAgendadas);
        // when(respuestaWR.getEstadoRespuesta()).thenReturn(EstadoRespuesta.WARNING);
        // when(respuestaWR.getItemsMensajeRespuesta()).thenReturn(itemsRespuesta);
        // when(respuestaWR.isRespuestaVacia()).thenReturn(true);
        // when(respuestaWR.getRespuesta()).thenReturn(null);
        // when(agendaTransferenciaBO.obtenerTransferenciasAgendadasRioRio(Matchers.any(Cliente.class)))
        // .thenReturn(respuestaOK);
        // when(agendaTransferenciaBO.obtenerTransferenciasAgendadasOtrosBancos(Matchers.any(Cliente.class)))
        // .thenReturn(respuestaWR);
        ////////////
        Respuesta<List<TransferenciaAgendadaDTO>> respuestaWARN = mock(Respuesta.class);
        Respuesta<List<TransferenciaAgendadaDTO>> respuestaOK = mock(Respuesta.class);
        Cliente cliente = mock(Cliente.class);
        List<Cuenta> cuentasCliente = new ArrayList<Cuenta>();
        cuentasCliente.add(new Cuenta());
        // ItemMensajeRespuesta itemRespuesta =
        // mock(ItemMensajeRespuesta.class);
        ItemMensajeRespuesta itemRespuesta = new ItemMensajeRespuesta();
        Respuesta<Mensaje> respuestaMensaje = mock(Respuesta.class);
        RegistroSesion reg = mock(RegistroSesion.class);
        Mensaje mensaje = mock(Mensaje.class);
        itemRespuesta.setTipoError("WARN_AGENDA_TRANSFERENCIAS");
        List<ItemMensajeRespuesta> itemsRespuesta = new ArrayList<ItemMensajeRespuesta>();
        itemsRespuesta.add(itemRespuesta);
        when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensaje.getMensaje()).thenReturn("WARN mensaje");
        when(respuestaMensaje.getRespuesta()).thenReturn(mensaje);
        when(sessionParametros.getRegistroSession()).thenReturn(reg);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cliente.getCuentasMonetarias()).thenReturn(cuentasCliente);
        when(contratoBO.tieneContrato(Matchers.any(TipoContratoEnum.class), Matchers.any(Cliente.class)))
                .thenReturn(Boolean.TRUE);
        when(reg.isMobile()).thenReturn(false);
        when(respuestaWARN.getEstadoRespuesta()).thenReturn(EstadoRespuesta.WARNING);
        when(respuestaWARN.getItemsMensajeRespuesta()).thenReturn(itemsRespuesta);
        when(respuestaWARN.isRespuestaVacia()).thenReturn(true);
        when(respuestaWARN.getRespuesta()).thenReturn(new ArrayList<TransferenciaAgendadaDTO>());
        when(respuestaOK.getEstadoRespuesta()).thenReturn(EstadoRespuesta.OK);
        when(respuestaOK.getItemsMensajeRespuesta()).thenReturn(itemsRespuesta);
        when(respuestaOK.isRespuestaVacia()).thenReturn(false);
        when(respuestaOK.getRespuesta()).thenReturn(transferenciasAgendadas);
        when(agendaTransferenciaBO.obtenerTransferenciasAgendadasRioRio(Matchers.any(Cliente.class)))
                .thenReturn(respuestaWARN);
        when(agendaTransferenciaBO.obtenerTransferenciasAgendadasOtrosBancos(Matchers.any(Cliente.class)))
                .thenReturn(respuestaOK);

        Respuesta<AgendaView> respuestaManager = manager.obtenerAgendaTransferencias();

        assertNotNull(respuestaManager);
        assertNotNull(respuestaManager.getRespuesta());
        assertEquals(respuestaManager.getRespuesta().getCantidadAutomaticas(), "2");
        assertEquals(respuestaManager.getRespuesta().getCantidadRecordatorios(), "2");
        assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertTrue(!respuestaManager.isRespuestaVacia());

        // estadisticas
        verify(estadisticaManager).add(
                EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_INICIO_GRILLA_TRANSFERENCIA,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        // EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_INICIO_CABECERA_TRANSFERENCIA,
        // EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR
    }

    /**
     * Obtener agenda transferencias vacia test.
     */
    @Test
    public void obtenerAgendaTransferenciasVaciaTest() {

        Respuesta<List<TransferenciaAgendadaDTO>> respuesta = mock(Respuesta.class);
        Respuesta<List<TransferenciaAgendadaDTO>> respuesta2 = mock(Respuesta.class);

        Cliente cliente = mock(Cliente.class);
        List<Cuenta> cuentasCliente = new ArrayList<Cuenta>();
        cuentasCliente.add(new Cuenta());
        ItemMensajeRespuesta itemRespuesta = mock(ItemMensajeRespuesta.class);

        Respuesta<Mensaje> respuestaMensaje = mock(Respuesta.class);
        Mensaje mensaje = mock(Mensaje.class);

        when(itemRespuesta.getTipoError()).thenReturn(TipoError.SIN_TRANSFERENCIAS_AGENDADAS.getDescripcion());
        List<ItemMensajeRespuesta> itemsRespuesta = new ArrayList<ItemMensajeRespuesta>();
        itemsRespuesta.add(itemRespuesta);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cliente.getCuentasMonetarias()).thenReturn(cuentasCliente);
        when(contratoBO.tieneContrato(Matchers.any(TipoContratoEnum.class), Matchers.any(Cliente.class)))
                .thenReturn(Boolean.TRUE);
        when(agendaTransferenciaBO.obtenerTransferenciasAgendadasRioRio(Matchers.any(Cliente.class)))
                .thenReturn(respuesta);
        when(agendaTransferenciaBO.obtenerTransferenciasAgendadasOtrosBancos(Matchers.any(Cliente.class)))
                .thenReturn(respuesta);
        when(respuesta.getEstadoRespuesta()).thenReturn(EstadoRespuesta.WARNING);
        when(respuesta.getItemsMensajeRespuesta()).thenReturn(itemsRespuesta);
        when(agendaTransferenciaBO.obtenerTransferenciasAgendadasRioRio(Matchers.any(Cliente.class)))
                .thenReturn(respuesta);
        when(agendaTransferenciaBO.obtenerTransferenciasAgendadasOtrosBancos(Matchers.any(Cliente.class)))
                .thenReturn(respuesta2);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(mensaje.getMensaje()).thenReturn("Error mensaje");
        when(respuestaMensaje.getRespuesta()).thenReturn(mensaje);

        Respuesta<AgendaView> respuestaManager = manager.obtenerAgendaTransferencias();

        assertNotNull(respuestaManager);
        assertNotNull(respuestaManager.getRespuesta());
        assertEquals(respuestaManager.getRespuesta().getCantidadAutomaticas(), "-");
        assertEquals(respuestaManager.getRespuesta().getCantidadRecordatorios(), "-");
        assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertTrue(!respuestaManager.isRespuestaVacia());
    }

    /**
     * ejecutar ransferencia recordatorio con respuesta OK.
     * 
     * @throws IllegalAccessException
     */
    //TODO: corregir destinoBanco
    @Test
    @Ignore
    public void transferenciaRecordatorioError() throws IllegalAccessException {
        TransferenciaAgendadaDetalleView transferenciaAgendada = new TransferenciaAgendadaDetalleView();
        transferenciaAgendada.setId("123");
        transferenciaAgendada.setEmail("lala@gmail.com");
        transferenciaAgendada.setMensajeEmail("Mensaje de Email");
        transferenciaAgendada.setImporte("1111");
        transferenciaAgendada.setConcepto(new ConceptoView("Alquiler","8", "VAR", "Alquiler", 0));
        transferenciaAgendada.setFecha("06/04/2017");
        transferenciaAgendada.setIsRioRio(Boolean.TRUE);
        transferenciaAgendada.setCuentaPropia("S");

        FieldUtils.writeDeclaredField(manager, "valorDesafioTransferenciasAgendadas", "2", true);
        Respuesta<TransferenciaEjecutadaDTO> respuestaEjecucionTransferenciaDTO = new Respuesta<TransferenciaEjecutadaDTO>();
        respuestaEjecucionTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(agendaTransferenciaBO.ejecutarTransferenciaAgendada(Matchers.any(TransferenciaAgendadaDTO.class),
                Matchers.any(Cliente.class))).thenReturn(respuestaEjecucionTransferenciaDTO);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);

        // TransferenciaAgendadaDTO transferenciaAgendadaDTO = new
        // TransferenciaAgendadaRecordatorioDTO();
        TransferenciaAgendadaDTO transferenciaAgendadaDTO = new TransferenciaAgendadaDTO();
        transferenciaAgendadaDTO.setId(123L);
        transferenciaAgendadaDTO.setHaciaOtroBanco(false);
        transferenciaAgendadaDTO.setFechaEjecucion(Calendar.getInstance().getTime());
        transferenciaAgendadaDTO.setCuentaOrigenTipo(TipoCuenta.VISA);
        DestinatarioDTO destinatarioDTO = new DestinatarioDTO();
        destinatarioDTO.setNombre("JUAN PEREZ");
        transferenciaAgendadaDTO.setDestinatario(destinatarioDTO);

        List<TransferenciaAgendadaDTO> listaTransferencia = new ArrayList<TransferenciaAgendadaDTO>();
        listaTransferencia.add(transferenciaAgendadaDTO);

        Respuesta<TransferenciaAgendadaDetalleView> respuestaFinal = new Respuesta<TransferenciaAgendadaDetalleView>();
        respuestaFinal.setEstadoRespuesta(EstadoRespuesta.OK);
        TransferenciaAgendadaDetalleView view = new TransferenciaAgendadaDetalleView();
        view.setDesafio(AutentificacionDTOMock.completarDesafioToken());
        respuestaFinal.setRespuesta(view);

        when(respuestaFactory.crearRespuestaOk(TransferenciaAgendadaDetalleView.class)).thenReturn(respuestaFinal);

        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("");
        when(sessionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(sessionParametros.getTransferenciasAgendadas()).thenReturn(listaTransferencia);
        Respuesta<TransferenciaAgendadaDetalleView> respuestaManager = manager
                .ejecutarTransferenciaAgendada(transferenciaAgendada);

        assertNotNull(respuestaManager);
        assertNotNull(respuestaManager.getRespuesta());
        assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    //TODO: corregir destinoBanco
    @Test
    @Ignore
    public void transferenciaRecordatorioOK() throws IllegalAccessException {
        TransferenciaAgendadaDetalleView transferenciaAgendada = new TransferenciaAgendadaDetalleView();
        transferenciaAgendada.setId("123");
        transferenciaAgendada.setEmail("lala@gmail.com");
        transferenciaAgendada.setMensajeEmail("Mensaje de Email");
        transferenciaAgendada.setImporte("1111");
        transferenciaAgendada.setConcepto(new ConceptoView("Alquiler","8", "VAR", "Alquiler", 0));
        transferenciaAgendada.setFecha("07/04/2017");
        transferenciaAgendada.setIsRioRio(Boolean.TRUE);
        transferenciaAgendada.setCuentaPropia("S");
        FieldUtils.writeDeclaredField(manager, "valorDesafioTransferenciasAgendadas", "2", true);
        Respuesta<TransferenciaAgendadaDetalleView> respuestaFinal = new Respuesta<TransferenciaAgendadaDetalleView>();
        respuestaFinal.setEstadoRespuesta(EstadoRespuesta.OK);
        TransferenciaAgendadaDetalleView view = new TransferenciaAgendadaDetalleView();
        view.setDesafio(AutentificacionDTOMock.completarDesafioToken());
        respuestaFinal.setRespuesta(view);

        when(respuestaFactory.crearRespuestaOk(TransferenciaAgendadaDetalleView.class)).thenReturn(respuestaFinal);

        Respuesta<TransferenciaEjecutadaDTO> respuestaEjecucionTransferenciaDTO = new Respuesta<TransferenciaEjecutadaDTO>();
        respuestaEjecucionTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.OK);

        TransferenciaEjecutadaDTO transferenciaDTO = new TransferenciaEjecutadaDTO();
        transferenciaDTO.setComprobanteBackEnd("12345");
        transferenciaDTO.setIdTransaccion("123");
        transferenciaDTO.setFechaCompensacion("20170704");

        respuestaEjecucionTransferenciaDTO.setRespuesta(transferenciaDTO);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        when(agendaTransferenciaBO.ejecutarTransferenciaAgendada(Matchers.any(TransferenciaAgendadaDTO.class),
                Matchers.any(Cliente.class))).thenReturn(respuestaEjecucionTransferenciaDTO);

        // TransferenciaAgendadaDTO transferenciaAgendadaDTO = new
        // TransferenciaAgendadaRecordatorioDTO();
        TransferenciaAgendadaDTO transferenciaAgendadaDTO = new TransferenciaAgendadaDTO();
        transferenciaAgendadaDTO.setId(123L);
        transferenciaAgendadaDTO.setHaciaOtroBanco(false);
        transferenciaAgendadaDTO.setCuentaOrigenTipo(TipoCuenta.VISA);
        transferenciaAgendadaDTO.setFechaEjecucion(Calendar.getInstance().getTime());
        DestinatarioDTO destinatarioDTO = new DestinatarioDTO();
        destinatarioDTO.setNombre("JUAN PEREZ");
        transferenciaAgendadaDTO.setDestinatario(destinatarioDTO);

        List<TransferenciaAgendadaDTO> listaTransferencia = new ArrayList<TransferenciaAgendadaDTO>();
        listaTransferencia.add(transferenciaAgendadaDTO);
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setIp("");
        when(sessionParametros.getRegistroSession()).thenReturn(registroSesion);
        when(sessionParametros.getTransferenciasAgendadas()).thenReturn(listaTransferencia);

        Respuesta<TransferenciaAgendadaDetalleView> respuestaManager = manager
                .ejecutarTransferenciaAgendada(transferenciaAgendada);
        assertNotNull(respuestaManager);
        assertNotNull(respuestaManager.getEstadoRespuesta());
        assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    public Respuesta<List<TransferenciaAgendadaDTO>> obtenerTransferencias() {
        Respuesta<List<TransferenciaAgendadaDTO>> respuestaTransferenciaAgendadaDTO = new Respuesta<List<TransferenciaAgendadaDTO>>();
        List<TransferenciaAgendadaDTO> trasnferenciaAgendadaDTOLis = new ArrayList<TransferenciaAgendadaDTO>();

        respuestaTransferenciaAgendadaDTO.setRespuesta(trasnferenciaAgendadaDTOLis);
        respuestaTransferenciaAgendadaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaTransferenciaAgendadaDTO.setRespuestaVacia(false);

        return respuestaTransferenciaAgendadaDTO;
    }

    @Test
    public void solicitarAgendarTransferenciaDesdeFeedbackTest() {
        AgendamientoTransferenciaView agendamientoTransferenciaView = new AgendamientoTransferenciaView();
        Respuesta<AgendamientoTransferenciaView> respuestaTansferenciaView = manager
                .solicitarAgendamientoTransferencia(agendamientoTransferenciaView);

        Assert.assertNotNull(respuestaTansferenciaView);
        Assert.assertNotNull(respuestaTansferenciaView.getRespuesta());
        Assert.assertNotNull(respuestaTansferenciaView.getRespuesta().getFrecuencias());
    }

    // /**
    // * Ejecutar modificacion transferencia OK.
    // */
    // @Test
    // public void ejecutarModificacionTransferencia_OK() {
    //
    // TransferenciaAgendadaDetalleView transferenciaAgendadaView = new
    // TransferenciaAgendadaDetalleView();
    // transferenciaAgendadaView.setId("7895790683756321608");
    // transferenciaAgendadaView.setAgendamientoTransferencia(false);
    // transferenciaAgendadaView.setAntiguedad(0);
    // transferenciaAgendadaView.setApodoTransferencia("Mazzarela");
    // transferenciaAgendadaView.setDestinatarioNombre("Mazzarela Irineo Numa");
    // transferenciaAgendadaView.setDestinatarioBanco("Terceros Santander Río");
    // transferenciaAgendadaView.setImporte("10,42");
    // transferenciaAgendadaView.setDivisa("$");
    // transferenciaAgendadaView.setIsRioRio(Boolean.TRUE);
    // // transferenciaAgendadaView.setIsProgramada(true);
    // // transferenciaAgendadaView.setIsRecordatorio(true);
    // transferenciaAgendadaView.setBanelco(false);
    // transferenciaAgendadaView.setCuentaPropia("N");
    // transferenciaAgendadaView.setIsRioRio(true);
    //
    // Mensaje mensaje = new Mensaje();
    // mensaje.setMensaje("mensaje");
    // when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
    //
    // Respuesta<TransferenciaAgendadaDTO> respuestaEjecucionTransferenciaDTO =
    // new Respuesta<TransferenciaAgendadaDTO>();
    // respuestaEjecucionTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
    // respuestaEjecucionTransferenciaDTO.setRespuestaVacia(false);
    //
    // when(agendaTransferenciaBO.ejecutarModificacionDeTransferenciaAgendada(
    // Matchers.any(TransferenciaAgendadaDTO.class), Matchers.any(String.class),
    // Matchers.any(Cliente.class)))
    // .thenReturn(respuestaEjecucionTransferenciaDTO);
    //
    // Respuesta<TransferenciaAgendadaDetalleView> respuestaManager = manager
    // .ejecutarModificacionDeTransferenciaAgendada(transferenciaAgendadaView);
    //
    // assertNotNull(respuestaManager);
    // assertNotNull(respuestaManager.getEstadoRespuesta());
    // assertEquals(respuestaManager.getEstadoRespuesta(), EstadoRespuesta.OK);
    // }

    // /**
    // * Ejecutar modificacion transferencia WARNING.
    // */
    // @Test
    // public void ejecutarModificacionTransferencia_WARNING() {
    //
    // TransferenciaAgendadaDetalleView transferenciaAgendadaView= new
    // TransferenciaAgendadaDetalleView();
    // transferenciaAgendadaView.setId("7895790683756321608");
    // transferenciaAgendadaView.setIdSesion("3374847512303072000");
    // transferenciaAgendadaView.setAgendamientoTransferencia(false);
    // transferenciaAgendadaView.setAntiguedad(0);
    // transferenciaAgendadaView.setApodoTransferencia("Mazzarela");
    // transferenciaAgendadaView.setDestinatarioNombre("Mazzarela Irineo Numa");
    // transferenciaAgendadaView.setDestinatarioBanco("Terceros Santander Río");
    // transferenciaAgendadaView.setImporte("10,42");
    // transferenciaAgendadaView.setMoneda("$");
    // transferenciaAgendadaView.setBanelco(false);
    // transferenciaAgendadaView.setCuentaPropia("N");
    // transferenciaAgendadaView.setIsRioRio(true);
    //
    // Mensaje mensaje = new Mensaje();
    // mensaje.setMensaje("mensaje");
    // when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
    //
    // Respuesta<TransferenciaAgendadaDTO> respuestaEjecucionTransferenciaDTO =
    // new Respuesta<TransferenciaAgendadaDTO>();
    // respuestaEjecucionTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
    //
    // ContadorIntentos contador = new ContadorIntentos();
    // String mensajeError = "Mensaje de error";
    // contador.setIdView(transferenciaAgendadaView.getIdSesion(), 2,
    // mensajeError);
    //
    // when(sessionParametros.getContador()).thenReturn(contador);
    //
    // when(agendaTransferenciaBO.ejecutarModificacionDeTransferenciaAgendada(Matchers.any(TransferenciaAgendadaDTO.class),
    // Matchers.any(String.class),
    // Matchers.any(Cliente.class))).thenReturn(respuestaEjecucionTransferenciaDTO
    // );
    //
    // Respuesta<TransferenciaAgendadaView> respuestaManager = manager
    // .ejecutarModificacionTransferenciaAgendada(transferenciaAgendadaView);
    //
    // Respuesta<TransferenciaAgendadaDetalleView> respuestaManager =
    // manager.ejecutarModificacionDeTransferenciaAgendada(transferenciaAgendadaView);
    //
    // assertNotNull(respuestaManager);
    // assertEquals(respuestaManager.getRespuesta(), null);
    // assertNotNull(respuestaManager.getEstadoRespuesta());
    // assertEquals(respuestaManager.getEstadoRespuesta(),
    // EstadoRespuesta.WARNING);
    // }

    // /**
    // * Ejecutar modificacion transferencia Error.
    // */
    // @Test
    // public void ejecutarModificacionTransferencia_ERROR() {
    //
    // TransferenciaAgendadaView transferenciaAgendadaView = new
    // TransferenciaAgendadaView();
    // transferenciaAgendadaView.setId("7895790683756321608");
    // transferenciaAgendadaView.setIdSesion("3374847512303072000");
    // transferenciaAgendadaView.setAgendamientoTransferencia(false);
    // transferenciaAgendadaView.setAntiguedad(0);
    // transferenciaAgendadaView.setApodoTransferencia("Mazzarela");
    // transferenciaAgendadaView.setDestinatarioNombre("Mazzarela Irineo Numa");
    // transferenciaAgendadaView.setDestinatarioBanco("Terceros Santander Río");
    // transferenciaAgendadaView.setImporte("10,42");
    // transferenciaAgendadaView.setMoneda("$");
    // transferenciaAgendadaView.setBanelco(false);
    // transferenciaAgendadaView.setCuentaPropia("N");
    // transferenciaAgendadaView.setIsRioRio(true);
    //
    // Mensaje mensaje = new Mensaje();
    // mensaje.setMensaje("mensaje");
    // when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
    //
    // Respuesta<TransferenciaEjecutadaDTO> respuestaEjecucionTransferenciaDTO =
    // new Respuesta<TransferenciaEjecutadaDTO>();
    // respuestaEjecucionTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    //
    // ContadorIntentos contador = new ContadorIntentos();
    // String mensajeError = "Mensaje de error";
    // contador.setIdView(transferenciaAgendadaView.getIdSesion(), 2,
    // mensajeError);
    //
    // when(sessionParametros.getContador()).thenReturn(contador);
    //
    // when(agendaTransferenciaBO.ejecutarModificacionTransferenciaAgendada(
    // Matchers.any(TransferenciaAgendadaDTO.class),
    // Matchers.any(Cliente.class)))
    // .thenReturn(respuestaEjecucionTransferenciaDTO);
    //
    // Respuesta<TransferenciaAgendadaView> respuestaManager = manager
    // .ejecutarModificacionTransferenciaAgendada(transferenciaAgendadaView);
    //
    // assertNotNull(respuestaManager);
    // assertNotNull(respuestaManager.getRespuesta());
    // assertNotNull(respuestaManager.getEstadoRespuesta());
    // assertEquals(respuestaManager.getEstadoRespuesta(),
    // EstadoRespuesta.ERROR);
    // }

    /**
     * Obtener respuesta autentificacion DTO Tx auto OK.
     *
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> obtenerRespuestaAutentificacionDTONuevaTxOK() {
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        respAutentificacionDTO.setRespuesta(AutentificacionDTOMock.completarDesafioToken());
        respAutentificacionDTO.setItemMensajeRespuesta(obtenerItemsMensajeTipoErrorDesafioToken());
        respAutentificacionDTO.setRespuestaVacia(false);
        return respAutentificacionDTO;
    }

    /**
     * Obtener items mensaje tipo error desafio token.
     *
     * @return the list
     */
    private List<ItemMensajeRespuesta> obtenerItemsMensajeTipoErrorDesafioToken() {
        ArrayList<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
        items.add(new ItemMensajeRespuesta());
        items.get(0).setTipoError(TipoError.DESAFIO.getDescripcion());
        items.get(0).setMensaje(MSJ_SOFT_TOKEN_INPUT);
        items.trimToSize();
        return items;
    }
}
