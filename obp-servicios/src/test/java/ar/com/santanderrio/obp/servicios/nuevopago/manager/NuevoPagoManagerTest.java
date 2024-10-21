package ar.com.santanderrio.obp.servicios.nuevopago.manager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.base.mensaje.entities.MensajeMock;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.mock.AutentificacionDTOMock;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.buscador.bo.BuscadorEmpresaBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentaSeleccionView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaOutDTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.mock.NuevaRecargaOutDTOMock;
import ar.com.santanderrio.obp.servicios.nuevopago.bo.NuevoPagoBO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.nuevopago.manager.impl.NuevoPagoManagerImpl;
import ar.com.santanderrio.obp.servicios.nuevopago.mock.ConsultaConfiguracionRecargaViewMock;
import ar.com.santanderrio.obp.servicios.nuevopago.mock.NuevoPagoMock;
import ar.com.santanderrio.obp.servicios.nuevopago.mock.PagoPendienteViewMock;
import ar.com.santanderrio.obp.servicios.pagos.bo.TipoMedioPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.CelularRecargaMedioPagoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.MediosPagoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.SubeRecargaMedioPagoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.mock.MedioPagoMock;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionRecargaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaConfiguracionView;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.PuntoDeAccesoView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;

/**
 * The Class NuevoPagoManagerTest.
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class NuevoPagoManagerTest {

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The nuevo pago BO. */
    @Mock
    private NuevoPagoBO nuevoPagoBO;

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;

    /** The medios pago BO. */
    @Mock
    private MediosPagoBOImpl mediosPagoBO;

    /** The respuesta erronea. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The rsa manager. */
    @Mock
    private RsaManager rsaManager;

    /** The session parametros. */
    @Mock
    private SesionParametros sessionParametros;

    /** The buscador empresa BO. */
    @Mock
    private BuscadorEmpresaBO buscadorEmpresaBO;

    /** The manager impl. */
    @InjectMocks
    NuevoPagoManagerImpl nuevoPagoManager;

    /** The cuit ok. */
    private String CUIT_OK = "20257080596";

    /** The autentificacion manager. */
    @Mock
    private AutentificacionManager autentificacionManager;

    /** The contador intentos. */
    @Mock
    private ContadorIntentos contadorIntentos;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The tipo medio pago BO. */
    @Mock
    private TipoMedioPagoBO tipoMedioPagoBO;
    
    @Mock
    private ClienteBO clienteBO;

    /**
     * Inits the mocks.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Before
    public void initMocks() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(nuevoPagoManager, "valorDesafio", "1", true);
    }

    /**
     * Obtener facturas ok.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerFacturasOk() throws ServiceException, BusinessException {
        Cliente cliente = new Cliente();
        cliente.setNup("Nup");
        Respuesta<MedioPagoSelectionView> resp;

        MedioPagoView medioPagoView = new MedioPagoView();
        medioPagoView.setCodigoPagoElectronico(CUIT_OK);
        medioPagoView.setDatosAdicionales("2");
        medioPagoView.setCodigoPagoElectronico("1243");

        Respuesta<MedioPagoSelectionView> respuestaOK = new Respuesta<MedioPagoSelectionView>();
        respuestaOK.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaOK.setRespuesta(new MedioPagoSelectionView());

        List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        listaCuentas.add(CuentaMock.completarInfoCuenta());

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(nuevoPagoBO.obtenerPagos(cliente, medioPagoView)).thenReturn(respuestaOK);
        when(nuevoPagoBO.isFormatoCodigoPagoElectronicoValid(medioPagoView)).thenReturn(true);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(new MedioPago());
        when(cuentaBO.obtenerCuentasBanelcoPesos(cliente)).thenReturn(listaCuentas);

        resp = nuevoPagoManager.obtenerFacturas(medioPagoView);

        Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());
    }

    /**
     * Validar cuit ok.
     */
    @Test
    public void validarCuitOk() {
        // When
        // Then
        Respuesta<String> resp = nuevoPagoManager.validarCuit(CUIT_OK);
        // Expected
        Assert.assertNotNull(resp);
        Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());
    }

    /**
     * Validar cuit warning.
     */
    @Test
    public void validarCuitWarning() {
        // When
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        // Then
        Respuesta<String> resp = nuevoPagoManager.validarCuit("10257080596");
        // Expected
        Assert.assertNotNull(resp);
        Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
    }

    /**
     * Validar cuit warning 2.
     */
    @Test
    public void validarCuitWarning2() {
        // When
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        // Then
        Respuesta<String> resp = nuevoPagoManager.validarCuit("");
        // Expected
        Assert.assertNotNull(resp);
        Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
    }

    /**
     * Obtener cuentas error.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCuentasError() throws BusinessException {
        Cliente cliente = new Cliente();
        cliente.setNup("Nup");
        ConsultaConfiguracionView consulta = new ConsultaConfiguracionView();
        consulta.setFiid("SEA");

        when(cuentaBO.obtenerCuentasBanelcoPesos(Matchers.any(Cliente.class)))
                .thenThrow(new BusinessException("Excepcion de la capa de BO."));
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(consulta.getFiid())).thenReturn(new MedioPago());

        Respuesta<MedioPagoSelectionView> resp = nuevoPagoManager.obtenerCuentas(consulta);
        Assert.assertEquals(EstadoRespuesta.ERROR, resp.getEstadoRespuesta());
    }

    /**
     * Obtener cuentas ok.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCuentasOk() throws IOException, BusinessException {
        ConsultaConfiguracionView consulta = new ConsultaConfiguracionView();
        consulta.setFiid("SEA");

        Cliente cliente = new Cliente();
        List<Cuenta> listCuentas = new ArrayList<Cuenta>();
        listCuentas.add(CuentaMock.completarInfoCuentaAmex());
        listCuentas.add(CuentaMock.completarInfoCuentaVisa());
        cliente.setCuentas(listCuentas);

        MedioPago medioPago = new MedioPago();
        medioPago.setMarcaPagoTc("5");

        List<Cuenta> listCuentasBanelco = new ArrayList<Cuenta>();
        listCuentasBanelco.add(CuentaMock.completarInfoCuentaUnicaPesos());

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.obtenerCuentasBanelcoPesos(Matchers.any(Cliente.class))).thenReturn(listCuentasBanelco);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(consulta.getFiid())).thenReturn(medioPago);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());

        Respuesta<MedioPagoSelectionView> resp = nuevoPagoManager.obtenerCuentas(consulta);
        Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());
    }

    /**
     * Obtener cuentas error sin cuentas.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCuentasErrorSinCuentasNiTarjetasTest() throws IOException, BusinessException {
        ConsultaConfiguracionView consulta = new ConsultaConfiguracionView();
        consulta.setFiid("SEA");

        List<Cuenta> list = new ArrayList<Cuenta>();

        when(cuentaBO.obtenerCuentasBanelcoPesos(Matchers.any(Cliente.class))).thenReturn(list);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(consulta.getFiid())).thenReturn(new MedioPago());
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());

        Respuesta<MedioPagoSelectionView> resp = nuevoPagoManager.obtenerCuentas(consulta);
        Assert.assertEquals(EstadoRespuesta.ERROR, resp.getEstadoRespuesta());
    }

    /**
     * Obtener cuentas error business exception falla idepesbane timeout test.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCuentasErrorBusinessExceptionFallaIdepesbaneTimeoutTest() throws IOException, BusinessException {
        // Given
        ConsultaConfiguracionView consulta = new ConsultaConfiguracionView();
        consulta.setFiid("SEA");
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1435",
                "<p><b>Ocurrió un error en nuestros servicios.</b>Por favor, volvé a intentar en unos minutos.</p>");

        // When
        Mockito.when(cuentaBO.obtenerCuentasBanelcoPesos(Matchers.any(Cliente.class)))
                .thenThrow(new BusinessException("Error obteniendo cuentas Banelco 10000004"));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<MedioPagoSelectionView> resp = nuevoPagoManager.obtenerCuentas(consulta);

        // Expected
        Assert.assertEquals(EstadoRespuesta.ERROR, resp.getEstadoRespuesta());
    }

    /**
     * Obtener cuentas error business exception falla idepesbane test.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCuentasErrorBusinessExceptionFallaIdepesbaneTest() throws IOException, BusinessException {
        // Given
        ConsultaConfiguracionView consulta = new ConsultaConfiguracionView();
        consulta.setFiid("SEA");
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1575",
                "No tenés medios de pago disponibles para ese servicio. Solicitá una cuenta de manera rápida y fácil.");

        // When
        Mockito.when(cuentaBO.obtenerCuentasBanelcoPesos(Matchers.any(Cliente.class)))
                .thenThrow(new BusinessException("Error obteniendo cuentas Banelco 10000025"));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<MedioPagoSelectionView> resp = nuevoPagoManager.obtenerCuentas(consulta);

        // Expected
        Assert.assertEquals(EstadoRespuesta.ERROR, resp.getEstadoRespuesta());
    }

    /**
     * Validar importe factura OK.
     */
    @Test
    public void validarImporteFacturaOK() {
        Respuesta<Boolean> respuesta = new Respuesta<Boolean>();
        respuesta.setRespuesta(Boolean.TRUE);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

        when(nuevoPagoBO.validarImporteFactura(Matchers.any(Cliente.class), Matchers.any(NuevoPago.class)))
                .thenReturn(respuesta);

        Respuesta<Boolean> res = nuevoPagoManager.validarImporteFactura(ClienteMock.completarInfoCliente(),
                NuevoPagoMock.completarInfoSinDesafio(Boolean.FALSE, "false"));

        Assert.assertNotNull(res);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertEquals(Boolean.TRUE, res.getRespuesta());
    }

    /**
     * Estadistica comprobante nuevo pago OK.
     */
    @Test
    public void estadisticaComprobanteNuevoPagoOK() {
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);

        nuevoPagoManager.estadisticaComprobanteNuevoPago(new CuentaSeleccionView());
    }

    /**
     * Pago puntual error time out.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void pagoPuntualErrorTimeOut() throws BusinessException {
        // Given
        NuevoPago nuevoPago = NuevoPagoMock.completarInfoConDesafio(Boolean.TRUE, "true");
        MedioPago medioPago = MedioPagoMock.completarInfo("DOMESTICO", 2, "1");
        Respuesta<Mensaje> resMensaje = new Respuesta<Mensaje>();
        resMensaje.setRespuesta(MensajeMock.completarInfoMensaje("Sin codigo.", "Sin mensaje."));
        resMensaje.setEstadoRespuesta(EstadoRespuesta.OK);
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("1234567890123456789012");
        cuenta.setNroCuentaProducto("0000000001234567");
        cuenta.setSucursalPaquete("0111");
        cuenta.setMonedaAltair("ALT");
        cuenta.setTipoCuenta("01");
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cliente.getCuentas().add(cuenta);
        NuevaRecargaOutDTO nuevaRecargaDTO = NuevaRecargaOutDTOMock.completarInfo(Boolean.FALSE, TipoError.TIMEOUT);
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<AutentificacionDTO> resAutentificacion = new Respuesta<AutentificacionDTO>();
        resAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);

        // When
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(nuevoPagoBO.pagar(Matchers.any(MedioPago.class), Matchers.any(PagoMisCuentasDTO.class),
                Matchers.any(Cliente.class))).thenReturn(nuevaRecargaDTO);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(sesionParametros.getValidacionHash()).thenReturn("220271548B54C533F59BBD0F01ACCD34");
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(resAutentificacion);
        when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(obtenerRespuestaPagoPendienteVacia());
        // Then
        Respuesta<NuevoPago> resp = nuevoPagoManager.pagoPuntual(nuevoPago);

        // Expected
        Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
    }

    /**
     * Pago puntual error cuenta.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void pagoPuntualErrorCuenta() throws BusinessException {
        // Given
        NuevoPago nuevoPago = NuevoPagoMock.completarInfoSinDesafio(Boolean.TRUE, "true");
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1268",
                "<p>No pudimos realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>");
        Cliente cliente = ClienteMock.completarInfoCliente();
        NuevaRecargaOutDTO nuevaRecargaDTO = NuevaRecargaOutDTOMock.completarInfo(Boolean.FALSE, TipoError.TIMEOUT);

        MedioPago medioPago = MedioPagoMock.completarInfo("DOMESTICO", 2, "1");
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);

        // When
        when(nuevoPagoBO.pagar(Matchers.any(MedioPago.class), Matchers.any(PagoMisCuentasDTO.class),
                Matchers.any(Cliente.class))).thenReturn(nuevaRecargaDTO);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(sesionParametros.getValidacionHash()).thenReturn("220271548B54C533F59BBD0F01ACCD34");
        // Then
        Respuesta<NuevoPago> resp = nuevoPagoManager.pagoPuntual(nuevoPago);

        // Expected
        Assert.assertEquals(EstadoRespuesta.ERROR, resp.getEstadoRespuesta());
    }

    /**
     * Pago puntual nuevo desafio RSA.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void pagoPuntualNuevoDesafioRSA() throws BusinessException {
        // Given
        NuevoPago nuevoPago = NuevoPagoMock.completarInfoSinDesafio(Boolean.FALSE, "false");
        MedioPago medioPago = MedioPagoMock.completarInfo("DOMESTICO", 2, "1");
        Respuesta<Mensaje> resMensaje = new Respuesta<Mensaje>();
        resMensaje.setRespuesta(MensajeMock.completarInfoMensaje("Sin codigo.", "Sin mensaje."));
        resMensaje.setEstadoRespuesta(EstadoRespuesta.OK);
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("1234567890123456789012");
        cuenta.setNroCuentaProducto("0000000001234567");
        cuenta.setSaldoCuenta("0000001112345667");
        cuenta.setSucursalPaquete("0111");
        cuenta.setMonedaAltair("ALT");
        cuenta.setTipoCuenta("01");
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta.setNroSucursal("0909");
        cliente.getCuentas().add(cuenta);
        cuenta.setCliente(cliente);
        NuevaRecargaOutDTO nuevaRecargaDTO = NuevaRecargaOutDTOMock.completarInfo(Boolean.FALSE, TipoError.TIMEOUT);
        Respuesta<AutentificacionDTO> resAutentificacion = new Respuesta<AutentificacionDTO>();
        resAutentificacion.setEstadoRespuesta(EstadoRespuesta.WARNING);
        resAutentificacion.setRespuesta(AutentificacionDTOMock.completarDesafioToken());
        resAutentificacion.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.WARNING);

        Respuesta<List<BigDecimal>> antiguedades = new Respuesta<List<BigDecimal>>();
        List<BigDecimal> listaValores = new ArrayList<BigDecimal>();
        
        BigDecimal valorUno = new BigDecimal("1");
        BigDecimal valorDos = new BigDecimal("2");
        
        listaValores.add(valorUno);
        listaValores.add(valorDos);
        antiguedades.setRespuesta(listaValores);
        
        // When
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(nuevoPagoBO.pagar(Matchers.any(MedioPago.class), Matchers.any(PagoMisCuentasDTO.class),
                Matchers.any(Cliente.class))).thenReturn(nuevaRecargaDTO);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(resAutentificacion);
        when(sesionParametros.getValidacionHash()).thenReturn("220271548B54C533F59BBD0F01ACCD34");
        when(clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Matchers.anyLong())).thenReturn(antiguedades);
        
        // Then
        Respuesta<NuevoPago> resp = nuevoPagoManager.pagoPuntual(nuevoPago);

        // Expected
        Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
    }

    /**
     * Pago puntual nuevo desafio RSA pago con tarjeta.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void pagoPuntualNuevoDesafioRSAPagoConTarjeta() throws BusinessException {
        // Given
        NuevoPago nuevoPago = NuevoPagoMock.completarInfoSinDesafio(Boolean.FALSE, "false");
        MedioPago medioPago = MedioPagoMock.completarInfo("DOMESTICO", 2, "1");
        Respuesta<Mensaje> resMensaje = new Respuesta<Mensaje>();
        resMensaje.setRespuesta(MensajeMock.completarInfoMensaje("Sin codigo.", "Sin mensaje."));
        resMensaje.setEstadoRespuesta(EstadoRespuesta.OK);
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("1234567890123456789012");
        cuenta.setNroTarjetaCredito("00003777920020216960");
        cuenta.setSaldoCuenta("0000001112345667");
        cuenta.setSucursalPaquete("0111");
        cuenta.setMonedaAltair("ALT");
        cuenta.setTipoCuenta("07");
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cuenta.setNroSucursal("0909");
        cliente.getCuentas().add(cuenta);
        cuenta.setCliente(cliente);
        NuevaRecargaOutDTO nuevaRecargaDTO = NuevaRecargaOutDTOMock.completarInfo(Boolean.FALSE, TipoError.TIMEOUT);
        Respuesta<AutentificacionDTO> resAutentificacion = new Respuesta<AutentificacionDTO>();
        resAutentificacion.setEstadoRespuesta(EstadoRespuesta.WARNING);
        resAutentificacion.setRespuesta(AutentificacionDTOMock.completarDesafioToken());
        resAutentificacion.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.WARNING);
        
        Respuesta<List<BigDecimal>> antiguedades = new Respuesta<List<BigDecimal>>();
        List<BigDecimal> listaValores = new ArrayList<BigDecimal>();
        
        BigDecimal valorUno = new BigDecimal("1");
        BigDecimal valorDos = new BigDecimal("2");
        
        listaValores.add(valorUno);
        listaValores.add(valorDos);
        antiguedades.setRespuesta(listaValores);

        // When
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(nuevoPagoBO.pagar(Matchers.any(MedioPago.class), Matchers.any(PagoMisCuentasDTO.class),
                Matchers.any(Cliente.class))).thenReturn(nuevaRecargaDTO);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(resAutentificacion);
        when(sesionParametros.getValidacionHash()).thenReturn("220271548B54C533F59BBD0F01ACCD34");
        when(clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Matchers.anyLong())).thenReturn(antiguedades);
        
        // Then
        Respuesta<NuevoPago> resp = nuevoPagoManager.pagoPuntual(nuevoPago);

        // Expected
        Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
    }

    /**
     * Pago puntual error sin desafio.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void pagoPuntualErrorSinDesafio() throws BusinessException {
        // Given
        NuevoPago nuevoPago = NuevoPagoMock.completarInfoSinDesafio(Boolean.FALSE, "false");
        Respuesta<MedioPago> resMedioPago = new Respuesta<MedioPago>();
        resMedioPago.setEstadoRespuesta(EstadoRespuesta.OK);
        MedioPago medioPago = MedioPagoMock.completarInfo("DOMESTICO", 2, "1");
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("1234567890123456789012");
        cuenta.setNroCuentaProducto("0000000001234567");
        cuenta.setSaldoCuenta("0000001112345667");
        cuenta.setSucursalPaquete("0111");
        cuenta.setMonedaAltair("ALT");
        cuenta.setTipoCuenta("01");
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cliente.getCuentas().add(cuenta);
        cuenta.setCliente(cliente);
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.ERROR);

        // When
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(sesionParametros.getValidacionHash()).thenReturn("220271548B54C533F59BBD0F01ACCD34");
        // Then
        Respuesta<NuevoPago> resp = nuevoPagoManager.pagoPuntual(nuevoPago);

        // Expected
        Assert.assertEquals(EstadoRespuesta.ERROR, resp.getEstadoRespuesta());
    }

    /**
     * Pago puntual nuevo desafio RSA sin monto.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void pagoPuntualNuevoDesafioRSASinMonto() throws BusinessException {
        // Given
        NuevoPago nuevoPago = NuevoPagoMock.completarInfoSinDesafio(Boolean.FALSE, "false");
        nuevoPago.setMonto(null);
        MedioPago medioPago = MedioPagoMock.completarInfo("DOMESTICO", 2, "1");
        Respuesta<Mensaje> resMensaje = new Respuesta<Mensaje>();
        resMensaje.setRespuesta(MensajeMock.completarInfoMensaje("Sin codigo.", "Sin mensaje."));
        resMensaje.setEstadoRespuesta(EstadoRespuesta.OK);
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("1234567890123456789012");
        cuenta.setNroCuentaProducto("0000000001234567");
        cuenta.setSaldoCuenta("0000001112345667");
        cuenta.setSucursalPaquete("0111");
        cuenta.setMonedaAltair("ALT");
        cuenta.setTipoCuenta("01");
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cuenta.setNroSucursal("0909");
        cliente.getCuentas().add(cuenta);
        cuenta.setCliente(cliente);
        NuevaRecargaOutDTO nuevaRecargaDTO = NuevaRecargaOutDTOMock.completarInfo(Boolean.FALSE, TipoError.TIMEOUT);
        Respuesta<AutentificacionDTO> resAutentificacion = new Respuesta<AutentificacionDTO>();
        resAutentificacion.setEstadoRespuesta(EstadoRespuesta.WARNING);
        resAutentificacion.setRespuesta(AutentificacionDTOMock.completarDesafioToken());
        resAutentificacion.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.WARNING);

        Respuesta<List<BigDecimal>> antiguedades = new Respuesta<List<BigDecimal>>();
        List<BigDecimal> listaValores = new ArrayList<BigDecimal>();
        
        BigDecimal valorUno = new BigDecimal("1");
        BigDecimal valorDos = new BigDecimal("2");
        
        listaValores.add(valorUno);
        listaValores.add(valorDos);
        antiguedades.setRespuesta(listaValores);
        
        // When
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(nuevoPagoBO.pagar(Matchers.any(MedioPago.class), Matchers.any(PagoMisCuentasDTO.class),
                Matchers.any(Cliente.class))).thenReturn(nuevaRecargaDTO);
        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(resAutentificacion);
        when(sesionParametros.getValidacionHash()).thenReturn("35F89A0B0E46A50B58701A38B9293915");
        when(clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Matchers.anyLong())).thenReturn(antiguedades);
        
        // Then
        Respuesta<NuevoPago> resp = nuevoPagoManager.pagoPuntual(nuevoPago);

        // Expected
        Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
    }

    /**
     * Pago puntual desafio en curso RSA.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void pagoPuntualDesafioEnCursoRSA() throws BusinessException {
        // Given
        NuevoPago nuevoPago = NuevoPagoMock.completarInfoConDesafio(Boolean.FALSE, "false");
        MedioPago medioPago = MedioPagoMock.completarInfo("DOMESTICO", 2, "1");
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1267",
                "<p>Tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b> se realizó exitosamente.</p>");
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("1234567890123456789012");
        cuenta.setNroCuentaProducto("0000000001234567");
        cuenta.setSaldoCuenta("0000001112345667");
        cuenta.setSucursalPaquete("0111");
        cuenta.setMonedaAltair("ALT");
        cuenta.setTipoCuenta("01");
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cliente.getCuentas().add(cuenta);
        cuenta.setCliente(cliente);
        NuevaRecargaOutDTO nuevaRecargaDTO = NuevaRecargaOutDTOMock.completarInfo(Boolean.TRUE, null);
        Respuesta<AutentificacionDTO> resAutentificacion = new Respuesta<AutentificacionDTO>();
        resAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        resAutentificacion.setRespuesta(AutentificacionDTOMock.completarDesafioToken());
        resAutentificacion.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);

        // When
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(nuevoPagoBO.pagar(Matchers.any(MedioPago.class), Matchers.any(PagoMisCuentasDTO.class),
                Matchers.any(Cliente.class))).thenReturn(nuevaRecargaDTO);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(resAutentificacion);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(sesionParametros.getValidacionHash()).thenReturn("220271548B54C533F59BBD0F01ACCD34");
        when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(obtenerRespuestaPagoPendienteVacia());
        when(sesionParametros.getOfertasComerciales()).thenReturn(new EventosComercialesDTO());
        // Then
        Respuesta<NuevoPago> resp = nuevoPagoManager.pagoPuntual(nuevoPago);

        // Expected
        Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());
    }

    /**
     * Pago puntual desafio en curso RSA tipo pago 3.
     *
     * @return the respuesta
     */
    private Respuesta<List<PagoPendiente>> obtenerRespuestaPagoPendiente() {
        Respuesta<List<PagoPendiente>> pagosRespuesta = obtenerRespuestaPagoPendienteVacia();
        PagoPendiente pago = new PagoPendiente();
        pago.setCodigoEmpresa("FFF");
        pago.setCodigoClienteEmpresa("23");
        pago.setIdentificacionDeFactura("3232");
        pago.setImporte(new BigDecimal(123));
        pagosRespuesta.getRespuesta().add(pago);
        return pagosRespuesta;
    }

    /**
     * Obtener respuesta pago pendiente vacia.
     *
     * @return the respuesta
     */
    private Respuesta<List<PagoPendiente>> obtenerRespuestaPagoPendienteVacia() {
        Respuesta<List<PagoPendiente>> pagosRespuesta = new Respuesta<List<PagoPendiente>>();
        List<PagoPendiente> pagos = new ArrayList<PagoPendiente>();
        pagosRespuesta.setRespuesta(pagos);
        return pagosRespuesta;
    }

    /**
     * Pago puntual desafio en curso RSA tipo pago 3.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void pagoPuntualDesafioEnCursoRSATipoPago3() throws BusinessException {
        // Given
        NuevoPago nuevoPago = NuevoPagoMock.completarInfoConDesafio(Boolean.FALSE, "false");
        MedioPago medioPago = MedioPagoMock.completarInfo("UNICEF", 0, "1");
        medioPago.setTipoPago(3);
        Respuesta<MedioPago> resMedioPago = new Respuesta<MedioPago>();
        resMedioPago.setEstadoRespuesta(EstadoRespuesta.OK);
        resMedioPago.setRespuesta(medioPago);
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1267",
                "<p>Tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b> se realizó exitosamente.</p>");
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("1234567890123456789012");
        cuenta.setNroCuentaProducto("0000000001234567");
        cuenta.setSaldoCuenta("0000001112345667");
        cuenta.setSucursalPaquete("0111");
        cuenta.setMonedaAltair("ALT");
        cuenta.setTipoCuenta("01");
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cliente.getCuentas().add(cuenta);
        cuenta.setCliente(cliente);
        NuevaRecargaOutDTO nuevaRecargaDTO = NuevaRecargaOutDTOMock.completarInfo(Boolean.TRUE, null);
        Respuesta<AutentificacionDTO> resAutentificacion = new Respuesta<AutentificacionDTO>();
        resAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        resAutentificacion.setRespuesta(AutentificacionDTOMock.completarDesafioToken());
        resAutentificacion.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        Respuesta<Boolean> resValidarImporte = new Respuesta<Boolean>();
        resValidarImporte.setRespuesta(Boolean.TRUE);
        resValidarImporte.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);

        Respuesta<List<PagoPendiente>> pagosRespuesta = obtenerRespuestaPagoPendiente();

        // When
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(nuevoPagoBO.pagar(Matchers.any(MedioPago.class), Matchers.any(PagoMisCuentasDTO.class),
                Matchers.any(Cliente.class))).thenReturn(nuevaRecargaDTO);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(resAutentificacion);
        when(nuevoPagoBO.validarImporteFactura(Matchers.any(Cliente.class), Matchers.any(NuevoPago.class)))
                .thenReturn(resValidarImporte);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(sesionParametros.getValidacionHash()).thenReturn("220271548B54C533F59BBD0F01ACCD34");
        when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(pagosRespuesta);
        when(sesionParametros.getOfertasComerciales()).thenReturn(new EventosComercialesDTO());
        
        // Then
        Respuesta<NuevoPago> resp = nuevoPagoManager.pagoPuntual(nuevoPago);

        // Expected
        Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());
    }

    /**
     * Pago puntual tipo pago VEP.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void pagoPuntualTipoPagoVEP() throws BusinessException {
        // Given
        // {"fiid":"SEA","codigoPagoElectronico":"20169406910","codigoPagoElectronico2":"13238861","cuentaSeleccionada":"0720100088000020052940","fechaPago":"22/02/2018","monto":700,"mes":"","anio":"","facturaNumero":"203357678540100000","isFromCalendario":true,"nombreEmpresa":"AFIP
        // - PAGO DE IMPUESTOS AFIP (VEP)"}

        NuevoPago nuevoPago = new NuevoPago();
        nuevoPago.setIsFromCalendario(true);
        nuevoPago.setFiid("SEA");
        nuevoPago.setCodigoPagoElectronico("20169406910");
        nuevoPago.setCodigoPagoElectronico2("13238861");
        nuevoPago.setCuentaSeleccionada("1234567890123456789012");
        nuevoPago.setMonto("700");
        nuevoPago.setFacturaNumero("203357678540100000");
        nuevoPago.setNombreEmpresa("AFIP - PAGO DE IMPUESTOS AFIP (VEP)");
        nuevoPago.setMes("");
        nuevoPago.setAnio("");
        nuevoPago.setDesafio(AutentificacionDTOMock.completarDesafioToken());
        
        MedioPago medioPago = MedioPagoMock.completarInfo("AFIP - PAGO DE IMPUESTOS AFIP (VEP)", 3, "F");
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1267",
                "<p>Tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b> se realizó exitosamente.</p>");
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setCbu("1234567890123456789012");
        cuenta.setNroCuentaProducto("0000000001234567");
        cuenta.setSaldoCuenta("0000001112345667");
        cuenta.setSucursalPaquete("0111");
        cuenta.setMonedaAltair("ALT");
        cuenta.setTipoCuenta("01");
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        cliente.getCuentas().add(cuenta);
        cuenta.setCliente(cliente);
        NuevaRecargaOutDTO nuevaRecargaDTO = NuevaRecargaOutDTOMock.completarInfo(Boolean.TRUE, null);
        Respuesta<AutentificacionDTO> resAutentificacion = new Respuesta<AutentificacionDTO>();
        resAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        resAutentificacion.setRespuesta(AutentificacionDTOMock.completarDesafioToken());
        resAutentificacion.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        Respuesta<Boolean> resValidarImporte = new Respuesta<Boolean>();
        resValidarImporte.setRespuesta(Boolean.TRUE);
        resValidarImporte.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<List<PagoPendiente>> pagosRespuesta = obtenerRespuestaPagoPendiente();

        // When
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(nuevoPagoBO.pagar(Matchers.any(MedioPago.class), Matchers.any(PagoMisCuentasDTO.class),
                Matchers.any(Cliente.class))).thenReturn(nuevaRecargaDTO);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(resAutentificacion);
        when(nuevoPagoBO.validarImporteFactura(Matchers.any(Cliente.class), Matchers.any(NuevoPago.class)))
                .thenReturn(resValidarImporte);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(sesionParametros.getValidacionHash()).thenReturn("04509C2A0CF1DC8CB324831CE2616A65");
        when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(pagosRespuesta);
        when(sesionParametros.getOfertasComerciales()).thenReturn(new EventosComercialesDTO());
        // Then
        Respuesta<NuevoPago> resp = nuevoPagoManager.pagoPuntual(nuevoPago);

        // Expected
        Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());
    }

    /**
     * Grabar estadistica pago puntual es recargar.
     */
    @Test
    public void grabarEstadisticaPagoPuntualEsRecargar() {
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);

        nuevoPagoManager.grabarEstadisticaPagoPuntual(PagoPendienteViewMock.completarInfo(Boolean.TRUE));
    }

    /**
     * Grabar estadistica pago puntual no es recargar.
     */
    @Test
    public void grabarEstadisticaPagoPuntualNoEsRecargar() {
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);

        nuevoPagoManager.grabarEstadisticaPagoPuntual(PagoPendienteViewMock.completarInfo(Boolean.FALSE));
    }

    /**
     * Grabar estadistica recarga ingreso numero nueva reacarga.
     */
    @Test
    public void grabarEstadisticaRecargaIngresoNumeroNuevaReacarga() {
        // When
        PuntoDeAccesoView puntoDeAcceso = new PuntoDeAccesoView();
        puntoDeAcceso.setPuntoDeAcceso("0");
        // Then
        Respuesta<Void> respuesta = nuevoPagoManager.grabarEstadisticaRecargaIngresoNumero(puntoDeAcceso);
        // Expected
        Assert.assertNotNull(respuesta);
    }

    /**
     * Grabar estadistica recarga ingreso numero nuevo pago.
     */
    @Test
    public void grabarEstadisticaRecargaIngresoNumeroNuevoPago() {
        // When
        PuntoDeAccesoView puntoDeAcceso = new PuntoDeAccesoView();
        puntoDeAcceso.setPuntoDeAcceso("1");
        // Then
        Respuesta<Void> respuesta = nuevoPagoManager.grabarEstadisticaRecargaIngresoNumero(puntoDeAcceso);
        // Expected
        Assert.assertNotNull(respuesta);
    }

    /**
     * Grabar estadistica recarga ingreso numero punto acceso distinto.
     */
    @Test
    public void grabarEstadisticaRecargaIngresoNumeroPuntoAccesoDistinto() {
        // When
        PuntoDeAccesoView puntoDeAcceso = new PuntoDeAccesoView();
        puntoDeAcceso.setPuntoDeAcceso("2");
        // Then
        Respuesta<Void> respuesta = nuevoPagoManager.grabarEstadisticaRecargaIngresoNumero(puntoDeAcceso);
        // Expected
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener configuracion recarga happy sube.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerConfiguracionRecargaHappySube() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);

        ConsultaConfiguracionView consultaConfiguracionRecargaView = new ConsultaConfiguracionView();
        consultaConfiguracionRecargaView.setFiid("SUBE");
        consultaConfiguracionRecargaView.setPuntoDeAcceso("0");

        MedioPago medioPago = new MedioPago();
        medioPago.setFiid("SUBE");
        TipoMedioPagoBO tipoMedioPagoBO = new SubeRecargaMedioPagoBOImpl();

        List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        listaCuentas.add(CuentaMock.completarInfoCuenta());

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje informativo SUBE");

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.obtenerCuentasBanelcoPesos(cliente)).thenReturn(listaCuentas);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.any(String.class))).thenReturn(medioPago);
        when(mensajeBO.obtenerMensajePorCodigo("1559")).thenReturn(mensaje);
        when(mediosPagoBO.obtenerTipoMedioPago(medioPago)).thenReturn(tipoMedioPagoBO);

        // Then
        Respuesta<ConfiguracionRecargaView> respuesta = nuevoPagoManager
                .obtenerConfiguracionRecarga(consultaConfiguracionRecargaView);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener configuracion recarga warning telefono.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerConfiguracionRecargaWarningTelefono() throws BusinessException {
        // When
        Cliente cliente = mock(Cliente.class);
        ConsultaConfiguracionView consultaConfiguracionRecargaView = new ConsultaConfiguracionView();
        consultaConfiguracionRecargaView.setFiid("RECL");
        consultaConfiguracionRecargaView.setPuntoDeAcceso("2");
        consultaConfiguracionRecargaView.setMonto("110");
        MedioPago medioPago = new MedioPago();
        medioPago.setRubroFantasia("RECARGA DE CELULARES");
        TipoMedioPagoBO tipoMedioPagoBO = new CelularRecargaMedioPagoBOImpl();

        List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        listaCuentas.add(CuentaMock.completarInfoCuenta());
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("El importe no es válido.");
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.obtenerCuentasBanelcoPesos(cliente)).thenReturn(listaCuentas);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.any(String.class))).thenReturn(medioPago);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        when(mediosPagoBO.obtenerTipoMedioPago(medioPago)).thenReturn(tipoMedioPagoBO);

        // Then
        Respuesta<ConfiguracionRecargaView> respuesta = nuevoPagoManager
                .obtenerConfiguracionRecarga(consultaConfiguracionRecargaView);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener configuracion recarga error cuentas.
     *
     * @throws BusinessException
     *             the business exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerConfiguracionRecargaErrorCuentas() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);
        ConsultaConfiguracionView consultaConfiguracionRecargaView = new ConsultaConfiguracionView();
        consultaConfiguracionRecargaView.setFiid("SUBE");
        consultaConfiguracionRecargaView.setPuntoDeAcceso("0");

        MedioPago medioPago = new MedioPago();
        medioPago.setFiid("SUBE");

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Ocurrió un error en nuestros servicios. Por favor volvé a ingresar en unos minutos.");
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.obtenerCuentasBanelcoPesos(cliente)).thenThrow(BusinessException.class);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.any(String.class))).thenReturn(medioPago);

        // Then
        Respuesta<ConfiguracionRecargaView> respuesta = nuevoPagoManager
                .obtenerConfiguracionRecarga(consultaConfiguracionRecargaView);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener configuracion recarga error no cuentas.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerConfiguracionRecargaErrorNoCuentas() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);
        ConsultaConfiguracionView consultaConfiguracionRecargaView = new ConsultaConfiguracionView();
        consultaConfiguracionRecargaView.setFiid("SUBE");
        consultaConfiguracionRecargaView.setPuntoDeAcceso("0");

        MedioPago medioPago = new MedioPago();
        medioPago.setFiid("SUBE");

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Ocurrió un error en nuestros servicios. Por favor volvé a ingresar en unos minutos.");
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.obtenerCuentasBanelcoPesos(cliente)).thenReturn(new ArrayList<Cuenta>());
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.any(String.class))).thenReturn(medioPago);

        // Then
        Respuesta<ConfiguracionRecargaView> respuesta = nuevoPagoManager
                .obtenerConfiguracionRecarga(consultaConfiguracionRecargaView);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener configuracion recarga happy distinto sube.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerConfiguracionRecargaHappyDistintoSube() throws BusinessException {
        // Given
        Cliente cliente = mock(Cliente.class);
        ConsultaConfiguracionView consultaConfiguracionRecargaView = ConsultaConfiguracionRecargaViewMock
                .completarInfo("Domestico", "1", "$ 12,00");
        MedioPago medioPago = MedioPagoMock.completarInfo("DOMESTICO", 2, "");
        List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        listaCuentas.add(CuentaMock.completarInfoCuenta());
        TipoMedioPagoBO tipoMedioPagoBO = new CelularRecargaMedioPagoBOImpl();

        // When
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.obtenerCuentasBanelcoPesos(cliente)).thenReturn(listaCuentas);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.any(String.class))).thenReturn(medioPago);
        when(mediosPagoBO.obtenerTipoMedioPago(medioPago)).thenReturn(tipoMedioPagoBO);

        // Then
        Respuesta<ConfiguracionRecargaView> respuesta = nuevoPagoManager
                .obtenerConfiguracionRecarga(consultaConfiguracionRecargaView);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener configuracion recarga happy distinto sube distinto acceso.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerConfiguracionRecargaHappyDistintoSubeDistintoAcceso() throws BusinessException {
        // Given
        Cliente cliente = mock(Cliente.class);
        ConsultaConfiguracionView consultaConfiguracionRecargaView = ConsultaConfiguracionRecargaViewMock
                .completarInfo("TELEFONICA COMUNIC. PERS. S.A.", "", "$ 12,00");
        MedioPago medioPago = MedioPagoMock.completarInfo("TELEFONICA COMUNIC. PERS. S.A.", 9, "");
        List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        listaCuentas.add(CuentaMock.completarInfoCuenta());
        TipoMedioPagoBO tipoMedioPagoBO = new CelularRecargaMedioPagoBOImpl();

        // When
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(cuentaBO.obtenerCuentasBanelcoPesos(cliente)).thenReturn(listaCuentas);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.any(String.class))).thenReturn(medioPago);
        when(mediosPagoBO.obtenerTipoMedioPago(medioPago)).thenReturn(tipoMedioPagoBO);

        // Then
        Respuesta<ConfiguracionRecargaView> respuesta = nuevoPagoManager
                .obtenerConfiguracionRecarga(consultaConfiguracionRecargaView);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Verificar factores autenticacion error test.
     */
    @Test
    public void verificarFactoresAutenticacionErrorTest() {

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje de error");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Respuesta<Boolean> respuestaRSA = respuestaFactory.crearRespuestaError("", TipoError.SIN_METODO_DESAFIO,
                CodigoMensajeConstantes.CODIGO_ERROR_SIN_DESAFIO_HABILITADO_SIN_TARJETA_BANELCO);

        when(autentificacionManager.tieneAlgunDesafioHabilitadoParaLaOperacion(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respuestaRSA);

        Respuesta<Boolean> respuesta = nuevoPagoManager.verificarFactoresAutenticacion();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        Assert.assertEquals(TipoError.SIN_METODO_DESAFIO.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertEquals("Mensaje de error", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    /**
     * Pago puntual tipo pago 1 con tarjeta credito OK test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Ignore
    @Test
    public void pagoPuntualTipoPago1ConTarjetaCreditoOKTest() throws BusinessException {
        // Given
        Cliente cliente = ClienteMock.completarInfoCliente();
        NuevoPago nuevoPago = new NuevoPago();
        nuevoPago.setFiid("UNCF");
        nuevoPago.setCodigoPagoElectronico("111111");
        nuevoPago.setCuentaSeleccionada("1601231202940105484600");
        nuevoPago.setMonto("20");
        nuevoPago.setFacturaNumero("                    ");
        nuevoPago.setReintentar("false");
        nuevoPago.setIsFromCalendario(Boolean.FALSE);
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000017024994");
        cuenta.setCbu("1601231202940105484600");
        cuenta.setNroSucursal("0037");
        cuenta.setMonedaAltair("ARS");
        cuenta.setTipoCuenta("07");
        cuenta.setNroTarjetaCredito("00004050710032218971");
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cliente.getCuentas().add(cuenta);
        cuenta.setCliente(cliente);
        String hash = HashUtils.obtenerHash(crearMapaDeLaVistaPagoServicios(nuevoPago));
        MedioPago medioPago = MedioPagoMock.completarInfo("UNICEF", 1, "1");
        PagoPMC pago = new PagoPMC();
        pago.setCodigoEmpresa("UNCF");
        pago.setComprobantePorServicio("000000002222");
        pago.setEstadoPago(0);
        pago.setFechaVencimiento("-/-/-");
        ;
        pago.setIdentificacion("111111");
        pago.setMoneda("ARS");
        pago.setMonto("00000000002000");
        pago.setNumeroControl("1234");
        pago.setNumeroCuenta("000017024994");
        pago.setNumeroFactura("                    ");
        pago.setOperacionDescripcion("pago");
        pago.setReintentar(Boolean.FALSE);
        pago.setRespuestaOK(Boolean.TRUE);
        pago.setSucursalCuenta("0037");
        pago.setTipoCuenta("07");
        pago.setTipoMonto("0");
        pago.setTipoSeleccion("I");
        Respuesta<Object> respuestaEstadoDesafio = respuestaFactory.crearRespuestaOk(Object.class);
        Respuesta<AutentificacionDTO> resAutentificacion = respuestaFactory.crearRespuestaOk(AutentificacionDTO.class,
                AutentificacionDTOMock.completarDesafioToken());
        Respuesta<List<PagoPendiente>> pagosRespuesta = obtenerRespuestaPagoPendiente();
        NuevaRecargaOutDTO nuevaRecargaDTO = NuevaRecargaOutDTOMock.completarInfo(Boolean.TRUE, null);
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1267",
                "<p>Tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b> fue realizado con éxito.</p>");

        // When
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getValidacionHash()).thenReturn(hash);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(resAutentificacion);
        when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(pagosRespuesta);
        when(nuevoPagoBO.pagar(Matchers.any(MedioPago.class), Matchers.any(PagoMisCuentasDTO.class),
                Matchers.any(Cliente.class))).thenReturn(nuevaRecargaDTO);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(sesionParametros.getValidacionHash()).thenReturn("449BC7897AF39E024E5C6F7930CDA598");
        when(sesionParametros.getOfertasComerciales()).thenReturn(new EventosComercialesDTO());

        // Then
        Respuesta<NuevoPago> resp = nuevoPagoManager.pagoPuntual(nuevoPago);

        // Expected
        Assert.assertNotNull(resp);
        Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());
    }

    /**
     * Pago puntual tipo pago 1 con tarjeta credito error 2 test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void pagoPuntualTipoPago1ConTarjetaCreditoError2Test() throws BusinessException {
        // Given
        Cliente cliente = ClienteMock.completarInfoCliente();
        NuevoPago nuevoPago = new NuevoPago();
        nuevoPago.setFiid("UNCF");
        nuevoPago.setCodigoPagoElectronico("111111");
        nuevoPago.setCuentaSeleccionada("1601231202940105484600");
        nuevoPago.setMonto("20");
        nuevoPago.setFacturaNumero("                    ");
        nuevoPago.setReintentar("false");
        nuevoPago.setIsFromCalendario(Boolean.FALSE);
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000017024994");
        cuenta.setCbu("1601231202940105484600");
        cuenta.setNroSucursal("0037");
        cuenta.setMonedaAltair("ARS");
        cuenta.setTipoCuenta("07");
        cuenta.setNroTarjetaCredito("00004050710032218971");
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cliente.getCuentas().add(cuenta);
        cuenta.setCliente(cliente);
        String hash = HashUtils.obtenerHash(crearMapaDeLaVistaPagoServicios(nuevoPago));
        MedioPago medioPago = MedioPagoMock.completarInfo("UNICEF", 1, "1");
        NuevaRecargaOutDTO pago = new NuevaRecargaOutDTO();
        pago.setCodigoEmpresa("UNCF");
        pago.setComprobantePorServicio("            ");
        pago.setEstadoPago(0);
        pago.setFechaVencimiento("-/-/-");
        pago.setIdentificacion("111111                          ");
        pago.setMensaje("<p>No pudimos realizar tu <b>pago</b> a <b>UNICEF</b> por <b>$ $ 20,00</b>.</p>");
        pago.setMoneda("00000000002000");
        pago.setMoneda(null);
        pago.setNroComprobante("            ");
        pago.setNroControl("    ");
        pago.setNumeroControl("    ");
        pago.setNumeroCuenta("000017024994");
        pago.setNumeroFactura("                    ");
        pago.setRespuestaOK(Boolean.FALSE);
        pago.setSucursalCuenta("0037");
        pago.setTipoCuenta("07");
        pago.setTipoMonto("0");
        pago.setTipoSeleccion("I");
        pago.setTipoError(TipoError.ERROR_GENERICO);
        Respuesta<Object> respuestaEstadoDesafio = respuestaFactory.crearRespuestaOk(Object.class);
        Respuesta<AutentificacionDTO> resAutentificacion = respuestaFactory.crearRespuestaOk(AutentificacionDTO.class,
                AutentificacionDTOMock.completarDesafioToken());
        Respuesta<List<PagoPendiente>> pagosRespuesta = obtenerRespuestaPagoPendiente();
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1267",
                "<p>Tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b> fue realizado con éxito.</p>");

        // When
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getValidacionHash()).thenReturn(hash);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(resAutentificacion);
        when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(pagosRespuesta);
        when(nuevoPagoBO.pagar(Matchers.any(MedioPago.class), Matchers.any(PagoMisCuentasDTO.class),
                Matchers.any(Cliente.class))).thenReturn(pago);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(sesionParametros.getValidacionHash()).thenReturn("449BC7897AF39E024E5C6F7930CDA598");

        // Then
        Respuesta<NuevoPago> resp = nuevoPagoManager.pagoPuntual(nuevoPago);

        // Expected
        Assert.assertNotNull(resp);
        Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
    }

    /**
     * Pago puntual tipo pago 1 con tarjeta credito error business exception test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void pagoPuntualTipoPago1ConTarjetaCreditoErrorBusinessExceptionTest() throws BusinessException {
        // Given
        Cliente cliente = ClienteMock.completarInfoCliente();
        NuevoPago nuevoPago = new NuevoPago();
        nuevoPago.setFiid("UNCF");
        nuevoPago.setCodigoPagoElectronico("111111");
        nuevoPago.setCuentaSeleccionada("1601231202940105484600");
        nuevoPago.setMonto("20");
        nuevoPago.setFacturaNumero("                    ");
        nuevoPago.setReintentar("false");
        nuevoPago.setIsFromCalendario(Boolean.FALSE);
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000000017024994");
        cuenta.setCbu("1601231202940105484600");
        cuenta.setNroSucursal("0037");
        cuenta.setMonedaAltair("ARS");
        cuenta.setTipoCuenta("07");
        cuenta.setNroTarjetaCredito("00004050710032218971");
        cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
        cliente.getCuentas().add(cuenta);
        cuenta.setCliente(cliente);
        String hash = HashUtils.obtenerHash(crearMapaDeLaVistaPagoServicios(nuevoPago));
        MedioPago medioPago = MedioPagoMock.completarInfo("UNICEF", 1, "1");
        Respuesta<Object> respuestaEstadoDesafio = respuestaFactory.crearRespuestaOk(Object.class);
        Respuesta<AutentificacionDTO> resAutentificacion = respuestaFactory.crearRespuestaOk(AutentificacionDTO.class,
                AutentificacionDTOMock.completarDesafioToken());
        Respuesta<List<PagoPendiente>> pagosRespuesta = obtenerRespuestaPagoPendiente();
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1268",
                "<p>No pudimos realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>");

        // When
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionParametros.getValidacionHash()).thenReturn(hash);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(resAutentificacion);
        when(sesionParametros.getRespuestaPagosPendientes()).thenReturn(pagosRespuesta);
        when(nuevoPagoBO.pagar(Matchers.any(MedioPago.class), Matchers.any(PagoMisCuentasDTO.class),
                Matchers.any(Cliente.class))).thenThrow(new BusinessException("Error generico!!"));
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(sesionParametros.getValidacionHash()).thenReturn("449BC7897AF39E024E5C6F7930CDA598");

        // Then
        Respuesta<NuevoPago> resp = nuevoPagoManager.pagoPuntual(nuevoPago);

        // Expected
        Assert.assertNotNull(resp);
        Assert.assertEquals(EstadoRespuesta.ERROR, resp.getEstadoRespuesta());
    }

    /**
     * Crear mapa de la vista pago servicios.
     *
     * @param nuevoPago
     *            the nuevo pago
     * @return the map
     */
    private Map<String, Object> crearMapaDeLaVistaPagoServicios(NuevoPago nuevoPago) {
        Map<String, Object> mapaAtributos = new HashMap<String, Object>();
        mapaAtributos.put("numeroIdentificacion", nuevoPago.getIdentificacion());
        mapaAtributos.put("numeroDeCuenta", nuevoPago.getCuentaSeleccionada());
        mapaAtributos.put("importe", nuevoPago.getMonto());
        mapaAtributos.put("codigoPagoElectronico", nuevoPago.getCodigoPagoElectronico());
        mapaAtributos.put("codigoPagoElectronico2", nuevoPago.getCodigoPagoElectronico2());
        mapaAtributos.put("numeroVep", nuevoPago.getNumeroVep());
        return mapaAtributos;
    }
}
