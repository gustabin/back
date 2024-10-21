package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
 
import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagoTarjetaCreditoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.DebitoAutomatico;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.bo.StopDebitTarjetasBO;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.entities.DatosStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;
import ar.com.santanderrio.obp.servicios.tarjetas.view.DatosCancelarStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.view.DatosInicioCancelarStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.view.InicioCancelarStopDebitDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl.CancelarStopDebitTarjetaManagerImpl;

@RunWith(MockitoJUnitRunner.class)
public class CancelarStopDebitTarjetaManagerTest {

    @InjectMocks
    CancelarStopDebitTarjetaManagerImpl cancelarStopDebitManager;

    @Mock
    StopDebitTarjetasBO stopDebitTarjetasBO;

    @Mock
    PagoTarjetaCreditoBO pagoTarjetaCreditoBO;

    @Mock
    private SesionCliente sesionCliente;

    @Mock
    private SesionParametros sesionParametros;

    @Mock
    private MensajeBO mensajeBO;

    @Mock
    private EstadisticaManager estadisticaManager;

    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Test
    public void inicioCancelarStopDebitOKVisaSinAlias() {

        // When
        Cliente cliente = mock(Cliente.class);
        Cuenta tarjetaElegida = CuentaMock.completarInfoCuentaVisa();

        DatosInicioCancelarStopDebit datos = new DatosInicioCancelarStopDebit();
        datos.setNroTarjeta("VISA XXXX-1234");

        Mensaje mensajeInformativo = new Mensaje();
        mensajeInformativo.setMensaje("Este es un mensaje de informacion");

        Mensaje mensajeInicio = new Mensaje();
        mensajeInicio.setMensaje("Este es un mensaje de inicio");

        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaElegida);
        when(mensajeBO.obtenerMensajePorCodigo("1656")).thenReturn(mensajeInformativo);
        when(mensajeBO.obtenerMensajePorCodigo("1285")).thenReturn(mensajeInicio);

        // Then
        Respuesta<InicioCancelarStopDebitDTO> respuesta = cancelarStopDebitManager.inicioCancelarStopDebit(cliente,
                datos);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    @Test
    public void inicioCancelarStopDebitOKAmexConAlias() {

        // When
        Cliente cliente = mock(Cliente.class);
        Cuenta tarjetaElegida = CuentaMock.completarInfoCuentaAmex();
        tarjetaElegida.setAlias("Mi tarjetita");

        DatosInicioCancelarStopDebit datos = new DatosInicioCancelarStopDebit();
        datos.setNroTarjeta("AMEX XXXX-12345");

        Mensaje mensajeInformativo = new Mensaje();
        mensajeInformativo.setMensaje("Este es un mensaje de informacion");

        Mensaje mensajeInicio = new Mensaje();
        mensajeInicio.setMensaje("Este es un mensaje de inicio");

        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaElegida);
        when(mensajeBO.obtenerMensajePorCodigo("1656")).thenReturn(mensajeInformativo);
        when(mensajeBO.obtenerMensajePorCodigo("1285")).thenReturn(mensajeInicio);

        // Then
        Respuesta<InicioCancelarStopDebitDTO> respuesta = cancelarStopDebitManager.inicioCancelarStopDebit(cliente,
                datos);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    @Test
    public void cancelarStopDebitOK() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);
        Cuenta tarjetaElegida = CuentaMock.completarInfoCuentaVisa();
        tarjetaElegida.setNroCuentaProducto("0000048438");

        DatosCancelarStopDebit datosCancelarStopDebit = new DatosCancelarStopDebit();
        datosCancelarStopDebit.setReintentar("false");
        datosCancelarStopDebit.setNroTarjeta("VISA XXXX-1234");

        List<DebitoAutomatico> listaDebitos = crearListaDebitosAutomaticos();

        ComprobanteFeedbackView comprobante = new ComprobanteFeedbackView();
        comprobante.setAccionRealizada(true);

        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaElegida);
        when(pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenReturn(listaDebitos);
        when(cliente.getCuentasParaEfectuarPago()).thenReturn(crearListaDeCuentasPago());
        when(stopDebitTarjetasBO.cancelarStopDebitTarjeta(Matchers.any(Cliente.class),
                Matchers.any(DatosStopDebit.class), Matchers.anyString())).thenReturn(comprobante);

        // Then
        Respuesta<ComprobanteFeedbackView> respuesta = cancelarStopDebitManager.cancelarStopDebit(cliente,
                datosCancelarStopDebit);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    @Test
    public void cancelarStopDebitNoHayCuentasPago() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);
        Cuenta tarjetaElegida = CuentaMock.completarInfoCuentaVisa();
        tarjetaElegida.setNroCuentaProducto("0000048438");

        DatosCancelarStopDebit datosCancelarStopDebit = new DatosCancelarStopDebit();
        datosCancelarStopDebit.setReintentar("true");
        datosCancelarStopDebit.setNroTarjeta("VISA XXXX-1234");

        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaElegida);
        when(cliente.getCuentasParaEfectuarPago()).thenReturn(new ArrayList<Cuenta>());

        // Then
        Respuesta<ComprobanteFeedbackView> respuesta = cancelarStopDebitManager.cancelarStopDebit(cliente,
                datosCancelarStopDebit);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    @Test
    public void cancelarStopDebitNoHayDebitos() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);
        Cuenta tarjetaElegida = CuentaMock.completarInfoCuentaVisa();
        tarjetaElegida.setNroCuentaProducto("0000048438");

        DatosCancelarStopDebit datosCancelarStopDebit = new DatosCancelarStopDebit();
        datosCancelarStopDebit.setReintentar("false");
        datosCancelarStopDebit.setNroTarjeta("VISA XXXX-1234");

        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaElegida);
        when(pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenReturn(new ArrayList<DebitoAutomatico>());
        when(cliente.getCuentasParaEfectuarPago()).thenReturn(crearListaDeCuentasPago());

        // Then
        Respuesta<ComprobanteFeedbackView> respuesta = cancelarStopDebitManager.cancelarStopDebit(cliente,
                datosCancelarStopDebit);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    @Test
    public void cancelarStopDebitNoHayDebitosDeTarjetas() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);
        Cuenta tarjetaElegida = CuentaMock.completarInfoCuentaVisa();
        tarjetaElegida.setNroCuentaProducto("0000048438");

        DatosCancelarStopDebit datosCancelarStopDebit = new DatosCancelarStopDebit();
        datosCancelarStopDebit.setReintentar("false");
        datosCancelarStopDebit.setNroTarjeta("VISA XXXX-1234");

        List<DebitoAutomatico> listaDebitos = crearListaDebitosAutomaticos();
        listaDebitos.get(0).setNumeroPartida("000086654");

        ComprobanteFeedbackView comprobante = new ComprobanteFeedbackView();
        comprobante.setAccionRealizada(true);

        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaElegida);
        when(pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenReturn(listaDebitos);
        when(cliente.getCuentasParaEfectuarPago()).thenReturn(crearListaDeCuentasPago());
        when(stopDebitTarjetasBO.cancelarStopDebitTarjeta(Matchers.any(Cliente.class),
                Matchers.any(DatosStopDebit.class), Matchers.anyString())).thenReturn(comprobante);

        // Then
        Respuesta<ComprobanteFeedbackView> respuesta = cancelarStopDebitManager.cancelarStopDebit(cliente,
                datosCancelarStopDebit);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    @SuppressWarnings("unchecked")
    @Test
    public void cancelarStopDebitErrorAlObtenerDeudas() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);
        Cuenta tarjetaElegida = CuentaMock.completarInfoCuentaVisa();
        tarjetaElegida.setNroCuentaProducto("0000048438");

        DatosCancelarStopDebit datosCancelarStopDebit = new DatosCancelarStopDebit();
        datosCancelarStopDebit.setReintentar("false");
        datosCancelarStopDebit.setNroTarjeta("VISA XXXX-1234");

        List<DebitoAutomatico> listaDebitos = crearListaDebitosAutomaticos();
        listaDebitos.get(0).setNumeroPartida("000086654");

        ComprobanteFeedbackView comprobante = new ComprobanteFeedbackView();
        comprobante.setAccionRealizada(true);

        ContadorIntentos contadorIntentos = new ContadorIntentos();

        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaElegida);
        when(cliente.getCuentasParaEfectuarPago()).thenReturn(crearListaDeCuentasPago());
        when(pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenThrow(BusinessException.class);
        when(sesionParametros.getContador()).thenReturn(contadorIntentos);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CANCELAR_STOP_DEBIT_TARJETA_ERROR_GENERICO))
                .thenReturn(new Mensaje());

        // Then
        Respuesta<ComprobanteFeedbackView> respuesta = cancelarStopDebitManager.cancelarStopDebit(cliente,
                datosCancelarStopDebit);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());

    }

    @Test
    public void cancelarStopDebitErrorAlCancelarStopDebitPermiteReintentos() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);
        Cuenta tarjetaElegida = CuentaMock.completarInfoCuentaVisa();
        tarjetaElegida.setNroCuentaProducto("0000048438");

        DatosCancelarStopDebit datosCancelarStopDebit = new DatosCancelarStopDebit();
        datosCancelarStopDebit.setReintentar("false");
        datosCancelarStopDebit.setNroTarjeta("VISA XXXX-1234");

        List<DebitoAutomatico> listaDebitos = crearListaDebitosAutomaticos();

        ComprobanteFeedbackView comprobante = new ComprobanteFeedbackView();
        comprobante.setAccionRealizada(true);

        BusinessException daoEx = new BusinessException(
                CodigoMensajeConstantes.CANCELAR_STOP_DEBIT_TARJETA_ERROR_GENERICO);

        ContadorIntentos contadorIntentos = new ContadorIntentos();

        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaElegida);
        when(pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenReturn(listaDebitos);
        when(cliente.getCuentasParaEfectuarPago()).thenReturn(crearListaDeCuentasPago());
        when(stopDebitTarjetasBO.cancelarStopDebitTarjeta(Matchers.any(Cliente.class),
                Matchers.any(DatosStopDebit.class), Matchers.anyString())).thenThrow(daoEx);
        when(sesionParametros.getContador()).thenReturn(contadorIntentos);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CANCELAR_STOP_DEBIT_TARJETA_ERROR_GENERICO))
                .thenReturn(new Mensaje());

        // Then
        Respuesta<ComprobanteFeedbackView> respuesta = cancelarStopDebitManager.cancelarStopDebit(cliente,
                datosCancelarStopDebit);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());

    }

    @Test
    public void cancelarStopDebitErrorAlCancelarStopDebitPermiteReintentosReintentosAgotados()
            throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);
        Cuenta tarjetaElegida = CuentaMock.completarInfoCuentaVisa();
        tarjetaElegida.setNroCuentaProducto("0000048438");

        DatosCancelarStopDebit datosCancelarStopDebit = new DatosCancelarStopDebit();
        datosCancelarStopDebit.setReintentar("false");
        datosCancelarStopDebit.setNroTarjeta("VISA XXXX-1234");

        List<DebitoAutomatico> listaDebitos = crearListaDebitosAutomaticos();

        ComprobanteFeedbackView comprobante = new ComprobanteFeedbackView();
        comprobante.setAccionRealizada(true);

        BusinessException daoEx = new BusinessException(
                CodigoMensajeConstantes.CANCELAR_STOP_DEBIT_TARJETA_ERROR_GENERICO);

        ContadorIntentos contadorIntentos = new ContadorIntentos(1);
        contadorIntentos.permiteReintentar();

        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaElegida);
        when(pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenReturn(listaDebitos);
        when(cliente.getCuentasParaEfectuarPago()).thenReturn(crearListaDeCuentasPago());
        when(stopDebitTarjetasBO.cancelarStopDebitTarjeta(Matchers.any(Cliente.class),
                Matchers.any(DatosStopDebit.class), Matchers.anyString())).thenThrow(daoEx);
        when(sesionParametros.getContador()).thenReturn(contadorIntentos);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CANCELAR_STOP_DEBIT_TARJETA_ERROR_GENERICO))
                .thenReturn(new Mensaje());

        // Then
        Respuesta<ComprobanteFeedbackView> respuesta = cancelarStopDebitManager.cancelarStopDebit(cliente,
                datosCancelarStopDebit);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

    }

    @Test
    public void cancelarStopDebitErrorAlCancelarStopDebitNoPermiteReintentos() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);
        Cuenta tarjetaElegida = CuentaMock.completarInfoCuentaVisa();
        tarjetaElegida.setNroCuentaProducto("0000048438");

        DatosCancelarStopDebit datosCancelarStopDebit = new DatosCancelarStopDebit();
        datosCancelarStopDebit.setReintentar("false");
        datosCancelarStopDebit.setNroTarjeta("VISA XXXX-1234");

        List<DebitoAutomatico> listaDebitos = crearListaDebitosAutomaticos();

        ComprobanteFeedbackView comprobante = new ComprobanteFeedbackView();
        comprobante.setAccionRealizada(true);

        BusinessException daoEx = new BusinessException(
                CodigoMensajeConstantes.CANCELAR_STOP_DEBIT_TARJETA_NO_HAY_STOP_DEBIT);

        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaElegida);
        when(pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenReturn(listaDebitos);
        when(cliente.getCuentasParaEfectuarPago()).thenReturn(crearListaDeCuentasPago());
        when(stopDebitTarjetasBO.cancelarStopDebitTarjeta(Matchers.any(Cliente.class),
                Matchers.any(DatosStopDebit.class), Matchers.anyString())).thenThrow(daoEx);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CANCELAR_STOP_DEBIT_TARJETA_NO_HAY_STOP_DEBIT))
                .thenReturn(new Mensaje());

        // Then
        Respuesta<ComprobanteFeedbackView> respuesta = cancelarStopDebitManager.cancelarStopDebit(cliente,
                datosCancelarStopDebit);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

    }

    @SuppressWarnings("unchecked")
    @Test
    public void cancelarStopDebitErrorGenerico() throws BusinessException {

        // When
        Cliente cliente = mock(Cliente.class);
        Cuenta tarjetaElegida = CuentaMock.completarInfoCuentaVisa();
        tarjetaElegida.setNroCuentaProducto("0000048438");

        DatosCancelarStopDebit datosCancelarStopDebit = new DatosCancelarStopDebit();
        datosCancelarStopDebit.setReintentar("false");
        datosCancelarStopDebit.setNroTarjeta("VISA XXXX-1234");

        List<DebitoAutomatico> listaDebitos = crearListaDebitosAutomaticos();

        ComprobanteFeedbackView comprobante = new ComprobanteFeedbackView();
        comprobante.setAccionRealizada(true);

        ContadorIntentos contadorIntentos = new ContadorIntentos();

        when(cliente.getTarjeta(Matchers.anyString())).thenReturn(tarjetaElegida);
        when(pagoTarjetaCreditoBO.obtenerDeudasConDebitoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenReturn(listaDebitos);
        when(cliente.getCuentasParaEfectuarPago()).thenReturn(crearListaDeCuentasPago());
        when(stopDebitTarjetasBO.cancelarStopDebitTarjeta(Matchers.any(Cliente.class),
                Matchers.any(DatosStopDebit.class), Matchers.anyString())).thenThrow(BusinessException.class);
        when(sesionParametros.getContador()).thenReturn(contadorIntentos);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CANCELAR_STOP_DEBIT_TARJETA_ERROR_GENERICO))
                .thenReturn(new Mensaje());

        // Then
        Respuesta<ComprobanteFeedbackView> respuesta = cancelarStopDebitManager.cancelarStopDebit(cliente,
                datosCancelarStopDebit);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());

    }

    @Test
    public void estadisticaVisualizacionComprobanteCancelacionStopDebit() {

        cancelarStopDebitManager.estadisticaVisualizacionComprobanteCancelacionStopDebit();

    }

    private List<Cuenta> crearListaDeCuentasPago() {

        List<Cuenta> listaCuentas = new ArrayList<Cuenta>();

        Cuenta cuenta1 = new Cuenta();
        cuenta1.setTipoCuenta("01");
        cuenta1.setNroCuentaProducto("000473737");
        cuenta1.setNroSucursal("022");

        listaCuentas.add(cuenta1);

        return listaCuentas;

    }

    private List<DebitoAutomatico> crearListaDebitosAutomaticos() {

        List<DebitoAutomatico> listaDebitos = new ArrayList<DebitoAutomatico>();

        DebitoAutomatico debito = new DebitoAutomatico();
        debito.setNumeroPartida("0000048438");
        debito.setCodigoServicio("383");

        listaDebitos.add(debito);

        return listaDebitos;
    }

}
