package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagoTarjetaCreditoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagosPendientesBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.DebitoAutomatico;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.AdhesionTarjetaDebitoAutomaticoBO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosAdhesionDebitoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.DatosAdhesionDebitoTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.InfoTarjetaAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.NroTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.Reintentar;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaModificacionAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl.AdhesionTarjetaDebitoAutomaticoManagerImpl;

/**
 * The Class AdhesionTarjetaDebitoAutomaticoManagerTest.
 *
 * @author mariano.g.pulera
 */
@RunWith(MockitoJUnitRunner.class)
public class AdhesionTarjetaDebitoAutomaticoManagerTest {

    /** The manager impl. */
    @InjectMocks
    private AdhesionTarjetaDebitoAutomaticoManagerImpl managerImpl = new AdhesionTarjetaDebitoAutomaticoManagerImpl();

    /** The pagos pendientes bo. */
    @Mock
    PagosPendientesBO pagosPendientesBO;

    /** The adhesion tarjeta debito automatico bo. */
    @Mock
    AdhesionTarjetaDebitoAutomaticoBO adhesionTarjetaDebitoAutomaticoBo;

    /** The mensaje bo. */
    @Mock
    MensajeBO mensajeBO;

    /** The estadistica manager. */
    @Mock
    EstadisticaManager estadisticaManager;

    /** The respuesta factory. */
    @Spy
    RespuestaFactory respuestaFactory;

    /** The cuenta manager. */
    @Mock
    CuentaManager cuentaManager;

    /** The sesion parametros. */
    @Mock
    SesionParametros sesionParametros;

    /** The pago tarjeta credito bo. */
    @Mock
    PagoTarjetaCreditoBO pagoTarjetaCreditoBo;

    /** The cuenta bo. */
    @Mock
    CuentaBO cuentaBo;

    /**
     * Tarjetas cuentas para debito sin errores.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void tarjetasCuentasParaDebitoSinErrores() throws BusinessException {

        // when
        Cliente cliente = mock(Cliente.class);
        Respuesta<CuentasView> respuestaCuentas = new Respuesta<CuentasView>();
        List<TarjetasAdhesionDebitoView> tarjetasView = new ArrayList<TarjetasAdhesionDebitoView>();
        List<CuentasAdhesionDebitoView> listaCuentas = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView cuentaPrueba = new CuentasAdhesionDebitoView();
        TarjetasAdhesionDebitoView visa = new TarjetasAdhesionDebitoView();
        TarjetasAdhesionDebitoView amex = new TarjetasAdhesionDebitoView();
        Reintentar reintentar = new Reintentar();
        reintentar.setReintentar("true");
        CuentasView cuentasView = new CuentasView();
        Mensaje mensaje = new Mensaje();

        respuestaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);

        cuentaPrueba.setAlias("Mi cuenta");
        cuentaPrueba.setCbu("38398892892");
        cuentaPrueba.setDescripcionTipoCuenta("Cuenta unica");
        cuentaPrueba.setNumero("223-4784893/8");
        cuentaPrueba.setSaldoPesos("220,00");
        cuentaPrueba.setSignoSaldoPesos("+");

        listaCuentas.add(cuentaPrueba);
        cuentasView.setCuentas(listaCuentas);
        respuestaCuentas.setRespuesta(cuentasView);

        visa.setAlias("Mi VISA");
        visa.setNumeroTarjeta("XXXX-1234");
        visa.setTipoTarjeta("07");

        amex.setAlias("Mi AMEX");
        amex.setNumeroTarjeta("XXXX-12345");
        amex.setTipoTarjeta("06");

        tarjetasView.add(visa);
        tarjetasView.add(amex);

        mensaje.setMensaje("Mensaje para test");

        when(pagosPendientesBO.obtenerDatosTarjetasPagoPuntual(Matchers.any(Cliente.class))).thenReturn(tarjetasView);
        when(cuentaManager.getCuentasSaldo()).thenReturn(respuestaCuentas);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // then
        Respuesta<InfoTarjetaAdhesionDebitoView> respuesta = managerImpl.tarjetasCuentasParaDebito(cliente, reintentar);

        // expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    /**
     * Tarjetas cuentas para debito error cuentas.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void tarjetasCuentasParaDebitoErrorCuentas() throws BusinessException {

        // when
        Cliente cliente = mock(Cliente.class);
        Respuesta<CuentasView> cuentas = new Respuesta<CuentasView>();
        CuentasView cuentasView = new CuentasView();
        List<CuentasAdhesionDebitoView> listaCuentasDebitoView = new ArrayList<CuentasAdhesionDebitoView>();
        Reintentar reintentar = new Reintentar();
        reintentar.setReintentar("true");
        List<ItemMensajeRespuesta> listaItemMensaje = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta itemMensaje = new ItemMensajeRespuesta();
        Mensaje mensaje = new Mensaje();

        cuentas.setEstadoRespuesta(EstadoRespuesta.ERROR);
        cuentasView.setCuentas(listaCuentasDebitoView);
        cuentas.setRespuesta(cuentasView);
        itemMensaje.setMensaje("Mensaje de error generico");
        itemMensaje.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        listaItemMensaje.add(itemMensaje);

        cuentas.setItemMensajeRespuesta(listaItemMensaje);

        mensaje.setMensaje("Mensaje de error generico");

        when(cuentaManager.getCuentasSaldo()).thenReturn(cuentas);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // then
        Respuesta<InfoTarjetaAdhesionDebitoView> respuesta = managerImpl.tarjetasCuentasParaDebito(cliente, reintentar);

        // expected
        assertEquals(respuestaFactory.crearRespuestaError(InfoTarjetaAdhesionDebitoView.class,
                cuentas.getItemsMensajeRespuesta()), respuesta);
    }

    /**
     * Tarjetas cuentas para debito error tarjetas.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void tarjetasCuentasParaDebitoErrorTarjetas() throws BusinessException {

        // when
        Cliente cliente = mock(Cliente.class);
        Respuesta<CuentasView> cuentas = new Respuesta<CuentasView>();
        CuentasView cuentasView = new CuentasView();
        List<CuentasAdhesionDebitoView> listaCuentasDebitoView = new ArrayList<CuentasAdhesionDebitoView>();
        Reintentar reintentar = new Reintentar();
        reintentar.setReintentar("true");
        Mensaje mensaje = new Mensaje();

        cuentas.setEstadoRespuesta(EstadoRespuesta.OK);
        cuentasView.setCuentas(listaCuentasDebitoView);
        cuentas.setRespuesta(cuentasView);

        when(cuentaManager.getCuentasSaldo()).thenReturn(cuentas);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(pagosPendientesBO.obtenerDatosTarjetasPagoPuntual(cliente)).thenThrow(new BusinessException());

        // then
        Respuesta<InfoTarjetaAdhesionDebitoView> respuesta = managerImpl.tarjetasCuentasParaDebito(cliente, reintentar);

        // expected
        assertSame(TipoError.ERROR_GENERICO.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    /**
     * Adherir tarjeta debito automatico sin errores.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void adherirTarjetaDebitoAutomaticoSinErrores() throws DAOException {

        // when
        Cliente cliente = mock(Cliente.class);
        List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        Cuenta cuentaSeleccionada = new Cuenta();
        ComprobanteFeedbackView comprobante = new ComprobanteFeedbackView();
        Cuenta tarjetaSeleccionada = new Cuenta();
        Reintentar reintentar = new Reintentar();
        reintentar.setReintentar("true");
        DatosAdhesionDebitoTarjetaView datosView = new DatosAdhesionDebitoTarjetaView();
        DatosAdhesionDebitoTarjetaView datosViewMock = mock(DatosAdhesionDebitoTarjetaView.class);
        Mensaje mensaje = new Mensaje();

        datosView.setCbu("84748392");
        datosView.setIsAdhesionMinimo(true);
        datosView.setIsAdhesionTotal(false);
        datosView.setNroCuentaFormateado("370-000310/8");
        datosView.setNroTarjetaEnmascarado("VISA XXXX-1767");

        when(cliente.getCuenta(Matchers.anyString())).thenReturn(cuentaSeleccionada);
        cuentaSeleccionada.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuentaSeleccionada.setNroSucursal("0370");
        cuentaSeleccionada.setNroOrdenFirmante("001");
        cuentaSeleccionada.setNroCuentaProducto("1234567");

        tarjetaSeleccionada.setTipoCuentaEnum(TipoCuenta.VISA);
        tarjetaSeleccionada.setTipoCuentaSinUnificar("07");
        tarjetaSeleccionada.setNroTarjetaCredito("00004050710053931767");
        tarjetaSeleccionada.setNroSucursal("0370");
        tarjetaSeleccionada.setNroCuentaProducto("0000000240088068");
        listaCuentas.add(tarjetaSeleccionada);

        mensaje.setMensaje("Mensaje OK");

        when(cliente.getCuentas()).thenReturn(listaCuentas);
        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaSeleccionada);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(datosViewMock.getNroCuentaFormateado()).thenReturn("370-000310/8");
        when(adhesionTarjetaDebitoAutomaticoBo.adherirTarjetaDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(DatosAdhesionDebitoTarjeta.class))).thenReturn(comprobante);

        // then
        Respuesta<ComprobanteFeedbackView> respuesta = managerImpl.adherirTarjetaDebitoAutomatico(cliente, datosView);

        // expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Adherir tarjeta debito automatico error permite reintento.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void adherirTarjetaDebitoAutomaticoErrorPermiteReintento() throws DAOException {

        // when
        Cliente cliente = mock(Cliente.class);
        ContadorIntentos contador = mock(ContadorIntentos.class);
        List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        Cuenta cuentaSeleccionada = new Cuenta();
        Cuenta tarjetaSeleccionada = new Cuenta();
        Reintentar reintentar = new Reintentar();
        reintentar.setReintentar("true");
        DatosAdhesionDebitoTarjetaView datosView = new DatosAdhesionDebitoTarjetaView();
        Mensaje mensaje = new Mensaje();

        datosView.setCbu("84748392");
        datosView.setIsAdhesionMinimo(true);
        datosView.setIsAdhesionTotal(false);
        datosView.setNroCuentaFormateado("370-000310/8");
        datosView.setNroTarjetaEnmascarado("VISA XXXX-1767");

        cuentaSeleccionada.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuentaSeleccionada.setNroSucursal("0370");
        cuentaSeleccionada.setNroOrdenFirmante("01");
        cuentaSeleccionada.setNroCuentaProducto("1234567");

        tarjetaSeleccionada.setTipoCuentaEnum(TipoCuenta.VISA);
        tarjetaSeleccionada.setTipoCuentaSinUnificar("07");
        tarjetaSeleccionada.setNroTarjetaCredito("00004050710053931767");
        tarjetaSeleccionada.setNroSucursal("0370");
        tarjetaSeleccionada.setNroCuentaProducto("0000000240088068");
        listaCuentas.add(tarjetaSeleccionada);

        when(cliente.getCuenta(Matchers.anyString())).thenReturn(cuentaSeleccionada);
        when(cliente.getCuentas()).thenReturn(listaCuentas);
        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaSeleccionada);
        when(adhesionTarjetaDebitoAutomaticoBo.adherirTarjetaDebitoAutomatico(Matchers.any(Cliente.class),
                (Matchers.any(DatosAdhesionDebitoTarjeta.class)))).thenThrow(new DAOException());
        when(sesionParametros.getContador()).thenReturn(contador);
        when(contador.permiteReintentar()).thenReturn(true);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // then
        Respuesta<ComprobanteFeedbackView> respuesta = managerImpl.adherirTarjetaDebitoAutomatico(cliente, datosView);

        // expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
        assertEquals("ERROR_REINTENTAR_ADHESION_DEBITO_AUTOMATICO_TARJETAS",
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    /**
     * Adherir tarjeta debito automatico error reintentos agotados.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void adherirTarjetaDebitoAutomaticoErrorReintentosAgotados() throws DAOException {

        // when
        Cliente cliente = mock(Cliente.class);
        ContadorIntentos contador = mock(ContadorIntentos.class);
        List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        Cuenta cuentaSeleccionada = new Cuenta();
        Cuenta tarjetaSeleccionada = new Cuenta();
        Reintentar reintentar = new Reintentar();
        reintentar.setReintentar("true");
        DatosAdhesionDebitoTarjetaView datosView = new DatosAdhesionDebitoTarjetaView();
        Mensaje mensaje = new Mensaje();

        datosView.setCbu("84748392");
        datosView.setIsAdhesionMinimo(true);
        datosView.setIsAdhesionTotal(false);
        datosView.setNroCuentaFormateado("370-000310/8");
        datosView.setNroTarjetaEnmascarado("VISA XXXX-1767");

        cuentaSeleccionada.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuentaSeleccionada.setNroSucursal("0370");
        cuentaSeleccionada.setNroOrdenFirmante("01");
        cuentaSeleccionada.setNroCuentaProducto("1234567");

        tarjetaSeleccionada.setTipoCuentaEnum(TipoCuenta.VISA);
        tarjetaSeleccionada.setTipoCuentaSinUnificar("07");
        tarjetaSeleccionada.setNroTarjetaCredito("00004050710053931767");
        tarjetaSeleccionada.setNroSucursal("0370");
        tarjetaSeleccionada.setNroCuentaProducto("0000000240088068");
        listaCuentas.add(tarjetaSeleccionada);

        when(cliente.getCuenta(Matchers.anyString())).thenReturn(cuentaSeleccionada);
        when(cliente.getCuentas()).thenReturn(listaCuentas);
        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaSeleccionada);
        when(adhesionTarjetaDebitoAutomaticoBo.adherirTarjetaDebitoAutomatico(Matchers.any(Cliente.class),
                (Matchers.any(DatosAdhesionDebitoTarjeta.class)))).thenThrow(new DAOException());
        when(sesionParametros.getContador()).thenReturn(contador);
        when(contador.permiteReintentar()).thenReturn(false);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // then
        Respuesta<ComprobanteFeedbackView> respuesta = managerImpl.adherirTarjetaDebitoAutomatico(cliente, datosView);

        // expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        assertEquals("ERROR_REINTENTAR_ADHESION_DEBITO_AUTOMATICO_TARJETAS",
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    /**
     * Inicio modificacion adhesion debito sin errores.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void inicioModificacionAdhesionDebitoSinErrores() throws BusinessException {

        // when
        Cliente cliente = mock(Cliente.class);
        List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        Cuenta cuentaSeleccionada = new Cuenta();
        Cuenta tarjetaSeleccionada = new Cuenta();
        NroTarjetaView nroTarjetaView = new NroTarjetaView();
        List<DebitoAutomatico> listaDebitos = new ArrayList<DebitoAutomatico>();
        DebitoAutomatico debito = new DebitoAutomatico();
        DatosTarjeta datosTarjeta = new DatosTarjeta();
        Mensaje mensaje = new Mensaje();

        cuentaSeleccionada.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuentaSeleccionada.setNroSucursal("0370");
        cuentaSeleccionada.setNroOrdenFirmante("01");
        cuentaSeleccionada.setNroCuentaProducto("1234567");
        cuentaSeleccionada.setSaldoCuenta("5000");
        cuentaSeleccionada.setCbu("100000000000000");
        cuentaSeleccionada.setTipoCuenta("9");
        listaCuentas.add(cuentaSeleccionada);

        nroTarjetaView.setNroTarjeta("12345667");

        tarjetaSeleccionada.setTipoCuentaEnum(TipoCuenta.VISA);
        tarjetaSeleccionada.setTipoCuentaSinUnificar("07");
        tarjetaSeleccionada.setTipoCuenta("07");
        tarjetaSeleccionada.setNroTarjetaCredito("00004050710053931767");
        tarjetaSeleccionada.setNroSucursal("0370");
        tarjetaSeleccionada.setNroCuentaProducto("0000000240088068");

        debito.setCodigoServicio("A");
        debito.setEstadoCliente("Activo");
        debito.setEstadoServicio("Activo");
        debito.setFechaServicio(new Date());
        debito.setNombreServicio("VISA");
        debito.setNumeroPartida("240088068");
        debito.setTipoFecha("");
        debito.setTipoStopDebit(" ");

        listaDebitos.add(debito);

        datosTarjeta.setFormaPagoTarjetaCredito("02");

        mensaje.setMensaje("Mensaje para test");

        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaSeleccionada);
        when(cliente.getCuentasParaEfectuarPago()).thenReturn(listaCuentas);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(pagoTarjetaCreditoBo.obtenerDeudasConDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenReturn(listaDebitos);
        when(cuentaBo.obtenerSaldoActualizado(Matchers.any(Cuenta.class))).thenReturn(cuentaSeleccionada);
        when(pagosPendientesBO.obtenerDatosUnaTarjeta(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(datosTarjeta);
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);

        // then
        Respuesta<TarjetaModificacionAdhesionDebitoView> respuesta = managerImpl
                .inicioModificacionAdhesionDebito(cliente, nroTarjetaView);

        // expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    /**
     * Inicio modificacion adhesion debito con error.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void inicioModificacionAdhesionDebitoConError() throws BusinessException {

        // when
        Cliente cliente = mock(Cliente.class);
        List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        Cuenta cuentaSeleccionada = new Cuenta();
        Cuenta tarjetaSeleccionada = new Cuenta();
        NroTarjetaView nroTarjetaView = new NroTarjetaView();
        Mensaje mensaje = new Mensaje();

        tarjetaSeleccionada.setTipoCuenta("07");
        listaCuentas.add(cuentaSeleccionada);

        mensaje.setMensaje("Mensaje para test");

        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaSeleccionada);
        when(cliente.getCuentasParaEfectuarPago()).thenReturn(listaCuentas);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(pagoTarjetaCreditoBo.obtenerDeudasConDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenThrow(new BusinessException());
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);

        // then
        Respuesta<TarjetaModificacionAdhesionDebitoView> respuesta = managerImpl
                .inicioModificacionAdhesionDebito(cliente, nroTarjetaView);

        // expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

    }

    /**
     * Modificar adhesion debito sin errores.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void modificarAdhesionDebitoSinErrores() throws DAOException {

        // when
        Cliente cliente = mock(Cliente.class);
        List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        Cuenta cuentaSeleccionada = new Cuenta();
        ComprobanteFeedbackView comprobante = new ComprobanteFeedbackView();
        Cuenta tarjetaSeleccionada = new Cuenta();
        Reintentar reintentar = new Reintentar();
        reintentar.setReintentar("true");
        DatosAdhesionDebitoTarjetaView datosView = new DatosAdhesionDebitoTarjetaView();
        DatosAdhesionDebitoTarjetaView datosViewMock = mock(DatosAdhesionDebitoTarjetaView.class);
        Mensaje mensaje = new Mensaje();

        datosView.setCbu("84748392");
        datosView.setIsAdhesionMinimo(true);
        datosView.setIsAdhesionTotal(false);
        datosView.setNroCuentaFormateado("370-000310/8");
        datosView.setNroTarjetaEnmascarado("VISA XXXX-1767");

        cuentaSeleccionada.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuentaSeleccionada.setNroSucursal("0370");
        cuentaSeleccionada.setNroOrdenFirmante("001");
        cuentaSeleccionada.setNroCuentaProducto("1234567");

        tarjetaSeleccionada.setTipoCuentaEnum(TipoCuenta.VISA);
        tarjetaSeleccionada.setTipoCuentaSinUnificar("07");
        tarjetaSeleccionada.setNroTarjetaCredito("00004050710053931767");
        tarjetaSeleccionada.setNroSucursal("0370");
        tarjetaSeleccionada.setNroCuentaProducto("0000000240088068");
        listaCuentas.add(tarjetaSeleccionada);

        mensaje.setMensaje("Mensaje para test");

        when(cliente.getCuentas()).thenReturn(listaCuentas);
        when(cliente.getCuenta(Matchers.anyString())).thenReturn(cuentaSeleccionada);
        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaSeleccionada);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(datosViewMock.getNroCuentaFormateado()).thenReturn("370-000310/8");
        when(adhesionTarjetaDebitoAutomaticoBo.adherirTarjetaDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(DatosAdhesionDebitoTarjeta.class))).thenReturn(comprobante);

        // then
        Respuesta<ComprobanteFeedbackView> respuesta = managerImpl.modificarAdhesionDebito(cliente, datosView);

        // expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Modificar adhesion debito error permite reintento.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void modificarAdhesionDebitoErrorPermiteReintento() throws DAOException {

        // when
        Cliente cliente = mock(Cliente.class);
        ContadorIntentos contador = mock(ContadorIntentos.class);
        List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        Cuenta cuentaSeleccionada = new Cuenta();
        Cuenta tarjetaSeleccionada = new Cuenta();
        Reintentar reintentar = new Reintentar();
        reintentar.setReintentar("true");
        DatosAdhesionDebitoTarjetaView datosView = new DatosAdhesionDebitoTarjetaView();
        Mensaje mensaje = new Mensaje();

        datosView.setCbu("84748392");
        datosView.setIsAdhesionMinimo(true);
        datosView.setIsAdhesionTotal(false);
        datosView.setNroCuentaFormateado("370-000310/8");
        datosView.setNroTarjetaEnmascarado("VISA XXXX-1767");

        cuentaSeleccionada.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuentaSeleccionada.setNroSucursal("0370");
        cuentaSeleccionada.setNroOrdenFirmante("01");
        cuentaSeleccionada.setNroCuentaProducto("1234567");

        tarjetaSeleccionada.setTipoCuentaEnum(TipoCuenta.VISA);
        tarjetaSeleccionada.setTipoCuentaSinUnificar("07");
        tarjetaSeleccionada.setNroTarjetaCredito("00004050710053931767");
        tarjetaSeleccionada.setNroSucursal("0370");
        tarjetaSeleccionada.setNroCuentaProducto("0000000240088068");
        listaCuentas.add(tarjetaSeleccionada);

        when(cliente.getCuenta(Matchers.anyString())).thenReturn(cuentaSeleccionada);
        when(cliente.getCuentas()).thenReturn(listaCuentas);
        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaSeleccionada);
        when(adhesionTarjetaDebitoAutomaticoBo.adherirTarjetaDebitoAutomatico(Matchers.any(Cliente.class),
                (Matchers.any(DatosAdhesionDebitoTarjeta.class)))).thenThrow(new DAOException());
        when(sesionParametros.getContador()).thenReturn(contador);
        when(contador.permiteReintentar()).thenReturn(true);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // then
        Respuesta<ComprobanteFeedbackView> respuesta = managerImpl.modificarAdhesionDebito(cliente, datosView);

        // expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
        assertEquals("ERROR_REINTENTAR_MODIFICACION_DEBITO_AUTOMATICO_TARJETAS",
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

    /**
     * Modificar adhesion debito error reintentos agotados.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void modificarAdhesionDebitoErrorReintentosAgotados() throws DAOException {

        // when
        Cliente cliente = mock(Cliente.class);
        ContadorIntentos contador = mock(ContadorIntentos.class);
        List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
        Cuenta cuentaSeleccionada = new Cuenta();
        Cuenta tarjetaSeleccionada = new Cuenta();
        Reintentar reintentar = new Reintentar();
        reintentar.setReintentar("true");
        DatosAdhesionDebitoTarjetaView datosView = new DatosAdhesionDebitoTarjetaView();
        Mensaje mensaje = new Mensaje();

        datosView.setCbu("84748392");
        datosView.setIsAdhesionMinimo(true);
        datosView.setIsAdhesionTotal(false);
        datosView.setNroCuentaFormateado("370-000310/8");
        datosView.setNroTarjetaEnmascarado("VISA XXXX-1767");

        cuentaSeleccionada.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuentaSeleccionada.setNroSucursal("0370");
        cuentaSeleccionada.setNroOrdenFirmante("01");
        cuentaSeleccionada.setNroCuentaProducto("1234567");

        tarjetaSeleccionada.setTipoCuentaEnum(TipoCuenta.VISA);
        tarjetaSeleccionada.setTipoCuentaSinUnificar("07");
        tarjetaSeleccionada.setNroTarjetaCredito("00004050710053931767");
        tarjetaSeleccionada.setNroSucursal("0370");
        tarjetaSeleccionada.setNroCuentaProducto("0000000240088068");
        listaCuentas.add(tarjetaSeleccionada);

        when(cliente.getCuenta(Matchers.anyString())).thenReturn(cuentaSeleccionada);
        when(cliente.getCuentas()).thenReturn(listaCuentas);
        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaSeleccionada);
        when(adhesionTarjetaDebitoAutomaticoBo.adherirTarjetaDebitoAutomatico(Matchers.any(Cliente.class),
                (Matchers.any(DatosAdhesionDebitoTarjeta.class)))).thenThrow(new DAOException());
        when(sesionParametros.getContador()).thenReturn(contador);
        when(contador.permiteReintentar()).thenReturn(false);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // then
        Respuesta<ComprobanteFeedbackView> respuesta = managerImpl.modificarAdhesionDebito(cliente, datosView);

        // expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        assertEquals("ERROR_REINTENTAR_MODIFICACION_DEBITO_AUTOMATICO_TARJETAS",
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());

    }

}
