package ar.com.santanderrio.obp.servicios.pagos.bo;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.Pago;
import ar.com.santanderrio.obp.servicios.nuevopago.entities.PagoPMC;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.PagoMultipleBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.PagoMisCuentasDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoInEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoMultipleDTO;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleListView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoMultipleView;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import ar.com.santanderrio.obp.servicios.tarjetas.exceptions.SimulacionDAOException;

/**
 * Test para {@link PagoMultipleBO}.
 *
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @since Dec 29, 2016
 */
@RunWith(MockitoJUnitRunner.class)
public class PagoMultipleBOTest {

    /** The estadistica BO. */
    @Mock
    private EstadisticaBO estadisticaBO;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The mensaje dao. */
    @Mock
    private MensajeDAO mensajeDao;

    /** The pago multiple BO. */
    @InjectMocks
    private PagoMultipleBOImpl pagoMultipleBO;

    /** The pago mis cuentas DAO. */
    @Mock
    PagoMisCuentasDAO pagoMisCuentasDAO;

    /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The cliente mock. */
    @Mock
    Cliente clienteMock;

    /** The registro sesion mock. */
    @Mock
    RegistroSesion registroSesionMock;

    /** The Moc K medios pago BO. */
    @Mock
    private MediosPagoBO MocKMediosPagoBO;
    @Mock
    private BuscadorEmpresaDAO buscadorMediosPagoDAO;

    /** The rsa manager. */
    @Mock
    private RsaManager rsaManager;

    /**
     * Inits the mocks for cliente, deudas de prestamos, tarjetas y penidentes.
     *
     * @throws SimulacionDAOException
     *             the simulacion DAO exception
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Before
    public void initMocks() throws SimulacionDAOException, DAOException, BusinessException {
        MockitoAnnotations.initMocks(this);
        Mockito.when(sesionCliente.getCliente()).thenReturn(clienteMock);
        Mockito.when(estadisticaBO.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class),
                Matchers.any(Cliente.class))).thenReturn(Mockito.mock(Estadistica.class));
        Mensaje mensajeMock = Mockito.mock(Mensaje.class);
        Mockito.when(mensajeMock.getMensaje()).thenReturn("Mensaje");
        Mockito.when(mensajeDao.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeMock);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesionMock);
    }

    /**
     * Ejecutar pago multiple todos OK.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void ejecutarPagoMultipleTodosOK() throws DAOException, BusinessException {
        PagoMultipleListView pagoMultipleView = generarPagoMultipleViewIngreso();

        PagoMultipleDTO multipleDTORetorno = generarPagoMultipleDTORetorno();
        multipleDTORetorno.setTodosOK(Boolean.TRUE);
        multipleDTORetorno.setErrorUnico(Boolean.FALSE);

        MedioPago medioPago1 = new MedioPago();
        medioPago1.setNombreFantasia("Superman");
        medioPago1.setRubroFantasia("Acuaman");
        medioPago1.setTipoImporte("1");
        Respuesta<MedioPago> respuesta = new Respuesta<MedioPago>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(medioPago1);
        Mockito.when(MocKMediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respuesta);

        Mockito.when(pagoMisCuentasDAO.ejecutarPagoMultiple(Matchers.anyListOf(PagoInEntity.class),
                Matchers.any(Cliente.class))).thenReturn(multipleDTORetorno);
        Respuesta<ActionCode> resp = new Respuesta<ActionCode>();
        resp.setRespuesta(ActionCode.DENY);

        PagoInEntity pago = new PagoInEntity();
        pago.setEstadoPago(1);
        pago.setCodigoEmpresa("1111");
        pago.setEmpresaNombreFantasia("fantasia");
        pago.setFechaHoraBody("10102018:16");

        PagoInEntity pago1 = new PagoInEntity();

        when(rsaManager.analizar(Matchers.any(Pago.class), Matchers.any(TipoDesafioEnum.class))).thenReturn(resp);
    }

    /*
     * respuestaEjecutar = pagoMultipleBO.ejecutarPagoMultiple(pagoMultipleView,
     * 
     * 
     * 
     * when(pagoMultipleBO.ejecutarPagoMultiple(Matchers.any(PagoMultipleListView.
     * class), clienteMock )).thenReturn (respuestaEjecutar );
     * Assert.assertNotNull(respuestaEjecutar);
     * Assert.assertEquals(EstadoRespuesta.OK,
     * respuestaEjecutar.getEstadoRespuesta());
     * Assert.assertNotNull(respuestaEjecutar.getRespuesta());
     * Assert.assertTrue(respuestaEjecutar.getRespuesta().isTodosOK());
     * 
     * Estadistica estadistica = new Estadistica();
     * estadistica.setCodigoTransaccion(EstadisticasConstants.
     * PAGO_MULTIPLE_EJECUCION);
     * estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
     * estadistica.setImporte("666.66");
     * estadistica.setMoneda(DivisaEnum.PESO.getCodigo());
     * Mockito.verify(estadisticaBO).add(Matchers.eq(estadistica),
     * Matchers.eq(registroSesionMock), Matchers.eq(clienteMock)); }
     * 
     * /** Ejecutar pago multiple todos OK. Variacion de tipos de pago e importe.
     *
     * @throws DAOException the DAO exception
     * 
     * @throws BusinessException the business exception
     */
    @Test
    @Ignore
    public void ejecutarPagoMultipleTodosOKVariaciones1() throws DAOException, BusinessException {
        PagoMultipleListView pagoMultipleView = generarPagoMultipleViewConTipoPago();

        PagoMultipleDTO multipleDTORetorno = generarPagoMultipleDTORetorno();
        multipleDTORetorno.setTodosOK(Boolean.TRUE);
        multipleDTORetorno.setErrorUnico(Boolean.FALSE);

        MedioPago medioPago1 = new MedioPago();
        medioPago1.setNombreFantasia("Superman");
        medioPago1.setRubroFantasia("Acuaman");
        medioPago1.setTipoImporte("3");
        Respuesta<MedioPago> respuesta = new Respuesta<MedioPago>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(medioPago1);
        MedioPago empresa = new MedioPago();
        empresa.setNombreFantasia("Los Gastones");
        Mockito.when(buscadorMediosPagoDAO.getByCodigo(Matchers.anyString())).thenReturn(empresa);
        Mockito.when(MocKMediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respuesta);

        Mockito.when(pagoMisCuentasDAO.ejecutarPagoMultiple(Matchers.anyListOf(PagoInEntity.class),
                Matchers.any(Cliente.class))).thenReturn(multipleDTORetorno);

        Respuesta<PagoMultipleDTO> respuestaEjecutar = pagoMultipleBO.ejecutarPagoMultiple(pagoMultipleView,
                clienteMock);

        Assert.assertNotNull(respuestaEjecutar);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaEjecutar.getEstadoRespuesta());
        Assert.assertNotNull(respuestaEjecutar.getRespuesta());
        Assert.assertTrue(respuestaEjecutar.getRespuesta().isTodosOK());

        Estadistica estadistica = new Estadistica();
        estadistica.setCodigoTransaccion(EstadisticasConstants.PAGO_MULTIPLE_EJECUCION);
        estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        estadistica.setImporte("66666");
        estadistica.setMoneda(DivisaEnum.PESO.getCodigo());
        Mockito.verify(estadisticaBO).add(Matchers.eq(estadistica), Matchers.eq(registroSesionMock),
                Matchers.eq(clienteMock));
    }

    /**
     * Ejecutar pago multiple todos OK. Variacion de tipos de pago e importe.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void ejecutarPagoMultipleTodosOKVariaciones2() throws DAOException, BusinessException {
        PagoMultipleListView pagoMultipleView = generarPagoMultipleViewConTipoPago();

        PagoMultipleDTO multipleDTORetorno = generarPagoMultipleDTORetorno();
        multipleDTORetorno.setTodosOK(Boolean.TRUE);
        multipleDTORetorno.setErrorUnico(Boolean.FALSE);

        MedioPago medioPago1 = new MedioPago();
        medioPago1.setNombreFantasia("Superman");
        medioPago1.setRubroFantasia("Acuaman");
        medioPago1.setTipoImporte("2");
        Respuesta<MedioPago> respuesta = new Respuesta<MedioPago>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(medioPago1);
        MedioPago empresa = new MedioPago();
        empresa.setNombreFantasia("Los Gastones");
        Mockito.when(buscadorMediosPagoDAO.getByCodigo(Matchers.anyString())).thenReturn(empresa);
        Mockito.when(MocKMediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respuesta);

        Mockito.when(pagoMisCuentasDAO.ejecutarPagoMultiple(Matchers.anyListOf(PagoInEntity.class),
                Matchers.any(Cliente.class))).thenReturn(multipleDTORetorno);

        Respuesta<PagoMultipleDTO> respuestaEjecutar = pagoMultipleBO.ejecutarPagoMultiple(pagoMultipleView,
                clienteMock);

        Assert.assertNotNull(respuestaEjecutar);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaEjecutar.getEstadoRespuesta());
        Assert.assertNotNull(respuestaEjecutar.getRespuesta());
        Assert.assertTrue(respuestaEjecutar.getRespuesta().isTodosOK());

        Estadistica estadistica = new Estadistica();
        estadistica.setCodigoTransaccion(EstadisticasConstants.PAGO_MULTIPLE_EJECUCION);
        estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        estadistica.setImporte("66666");
        estadistica.setMoneda(DivisaEnum.PESO.getCodigo());
        Mockito.verify(estadisticaBO).add(Matchers.eq(estadistica), Matchers.eq(registroSesionMock),
                Matchers.eq(clienteMock));
    }

    /**
     * Ejecutar pago multiple todos OK. Variacion de tipos de pago e importe.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void ejecutarPagoMultipleTodosOKVariaciones3() throws DAOException, BusinessException {
        PagoMultipleListView pagoMultipleView = generarPagoMultipleViewConTipoPago();

        PagoMultipleDTO multipleDTORetorno = generarPagoMultipleDTORetorno();
        multipleDTORetorno.setTodosOK(Boolean.TRUE);
        multipleDTORetorno.setErrorUnico(Boolean.FALSE);

        MedioPago medioPago1 = new MedioPago();
        medioPago1.setNombreFantasia("Superman");
        medioPago1.setRubroFantasia("Acuaman");
        medioPago1.setTipoImporte("1");
        Respuesta<MedioPago> respuesta = new Respuesta<MedioPago>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(medioPago1);
        MedioPago empresa = new MedioPago();
        empresa.setNombreFantasia("Los Gastones");
        Mockito.when(buscadorMediosPagoDAO.getByCodigo(Matchers.anyString())).thenReturn(empresa);
        Mockito.when(MocKMediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respuesta);

        Mockito.when(pagoMisCuentasDAO.ejecutarPagoMultiple(Matchers.anyListOf(PagoInEntity.class),
                Matchers.any(Cliente.class))).thenReturn(multipleDTORetorno);

        Respuesta<PagoMultipleDTO> respuestaEjecutar = pagoMultipleBO.ejecutarPagoMultiple(pagoMultipleView,
                clienteMock);

        Assert.assertNotNull(respuestaEjecutar);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaEjecutar.getEstadoRespuesta());
        Assert.assertNotNull(respuestaEjecutar.getRespuesta());
        Assert.assertTrue(respuestaEjecutar.getRespuesta().isTodosOK());

        Estadistica estadistica = new Estadistica();
        estadistica.setCodigoTransaccion(EstadisticasConstants.PAGO_MULTIPLE_EJECUCION);
        estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        estadistica.setImporte("66666");
        estadistica.setMoneda(DivisaEnum.PESO.getCodigo());
        Mockito.verify(estadisticaBO).add(Matchers.eq(estadistica), Matchers.eq(registroSesionMock),
                Matchers.eq(clienteMock));
    }

    /**
     * Ejecutar pago multiple todos OK. Variacion de tipos de pago e importe. para
     * UNICEF.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void ejecutarPagoMultipleTodosOKVariaciones4() throws DAOException, BusinessException {
        PagoMultipleListView pagoMultipleView = generarPagoMultipleViewConTipoPago();
        pagoMultipleView.getPagos().get(0).setTipoPago("1");
        pagoMultipleView.getPagos().get(1).setTipoPago("3");

        PagoMultipleDTO multipleDTORetorno = generarPagoMultipleDTORetorno();
        multipleDTORetorno.setTodosOK(Boolean.TRUE);
        multipleDTORetorno.setErrorUnico(Boolean.FALSE);

        MedioPago medioPago1 = new MedioPago();
        medioPago1.setNombreFantasia("VEP");
        medioPago1.setRubroFantasia("VEP");
        medioPago1.setTipoImporte("2");
        Respuesta<MedioPago> respuesta = new Respuesta<MedioPago>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(medioPago1);
        MedioPago empresa = new MedioPago();
        empresa.setNombreFantasia("Los Gastones");
        Mockito.when(buscadorMediosPagoDAO.getByCodigo(Matchers.anyString())).thenReturn(empresa);
        Mockito.when(MocKMediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respuesta);

        Mockito.when(pagoMisCuentasDAO.ejecutarPagoMultiple(Matchers.anyListOf(PagoInEntity.class),
                Matchers.any(Cliente.class))).thenReturn(multipleDTORetorno);

        Respuesta<PagoMultipleDTO> respuestaEjecutar = pagoMultipleBO.ejecutarPagoMultiple(pagoMultipleView,
                clienteMock);

        Assert.assertNotNull(respuestaEjecutar);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaEjecutar.getEstadoRespuesta());
        Assert.assertNotNull(respuestaEjecutar.getRespuesta());
        Assert.assertTrue(respuestaEjecutar.getRespuesta().isTodosOK());

        Estadistica estadistica = new Estadistica();
        estadistica.setCodigoTransaccion(EstadisticasConstants.PAGO_MULTIPLE_EJECUCION);
        estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        estadistica.setImporte("66666");
        estadistica.setMoneda(DivisaEnum.PESO.getCodigo());
        Mockito.verify(estadisticaBO).add(Matchers.eq(estadistica), Matchers.eq(registroSesionMock),
                Matchers.eq(clienteMock));
    }

    /**
     * Ejecutar pago multiple todos OK. Variacion de tipos de pago e importe. para
     * VEP.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void ejecutarPagoMultipleTodosOKVariaciones5() throws DAOException, BusinessException {
        PagoMultipleListView pagoMultipleView = generarPagoMultipleViewConTipoPago();
        pagoMultipleView.getPagos().get(0).setTipoPago("3");
        pagoMultipleView.getPagos().get(1).setTipoPago("3");

        PagoMultipleDTO multipleDTORetorno = generarPagoMultipleDTORetorno();
        multipleDTORetorno.setTodosOK(Boolean.TRUE);
        multipleDTORetorno.setErrorUnico(Boolean.FALSE);

        MedioPago medioPago1 = new MedioPago();
        medioPago1.setNombreFantasia("VEP");
        medioPago1.setRubroFantasia("VEP");
        medioPago1.setTipoImporte("0");
        Respuesta<MedioPago> respuesta = new Respuesta<MedioPago>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(medioPago1);
        MedioPago empresa = new MedioPago();
        empresa.setNombreFantasia("Los Gastones");
        Mockito.when(buscadorMediosPagoDAO.getByCodigo(Matchers.anyString())).thenReturn(empresa);
        Mockito.when(MocKMediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respuesta);

        Mockito.when(pagoMisCuentasDAO.ejecutarPagoMultiple(Matchers.anyListOf(PagoInEntity.class),
                Matchers.any(Cliente.class))).thenReturn(multipleDTORetorno);

        Respuesta<PagoMultipleDTO> respuestaEjecutar = pagoMultipleBO.ejecutarPagoMultiple(pagoMultipleView,
                clienteMock);

        Assert.assertNotNull(respuestaEjecutar);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaEjecutar.getEstadoRespuesta());
        Assert.assertNotNull(respuestaEjecutar.getRespuesta());
        Assert.assertTrue(respuestaEjecutar.getRespuesta().isTodosOK());

        Estadistica estadistica = new Estadistica();
        estadistica.setCodigoTransaccion(EstadisticasConstants.PAGO_MULTIPLE_EJECUCION);
        estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        estadistica.setImporte("66666");
        estadistica.setMoneda(DivisaEnum.PESO.getCodigo());
        Mockito.verify(estadisticaBO).add(Matchers.eq(estadistica), Matchers.eq(registroSesionMock),
                Matchers.eq(clienteMock));
    }

    /**
     * Generar pago multiple view con tipo pago.
     *
     * @return the pago multiple list view
     */
    private PagoMultipleListView generarPagoMultipleViewConTipoPago() {
        PagoMultipleView pagoView1 = new PagoMultipleView();
        pagoView1.setNumeroCuenta("011-000111/1");
        pagoView1.setMoneda("ARS");
        pagoView1.setSimboloMoneda("$");
        pagoView1.setMonto("111.11");
        pagoView1.setMontoSinFormatear("111.11");
        pagoView1.setMontoInicial("111.11");
        pagoView1.setTipoPago("3");
        pagoView1.setDescripcionTipoCuenta("Cuenta única");
        PagoMultipleView pagoView2 = new PagoMultipleView();
        pagoView2.setNumeroCuenta("022-000222/2");
        pagoView2.setMoneda("ARS");
        pagoView2.setSimboloMoneda("$");
        pagoView2.setMonto("111.11");
        pagoView2.setMontoSinFormatear("222.22");
        pagoView2.setMontoInicial("111.11");
        pagoView2.setTipoPago("3");
        pagoView2.setDescripcionTipoCuenta("Cuenta única");
        PagoMultipleView pagoView3 = new PagoMultipleView();
        pagoView3.setNumeroCuenta("033-000333/3");
        pagoView3.setMoneda("ARS");
        pagoView3.setSimboloMoneda("$");
        pagoView3.setMonto("111.11");
        pagoView3.setMontoSinFormatear("333.33");
        pagoView3.setMontoInicial("111.11");
        pagoView3.setTipoPago("3");
        pagoView3.setDescripcionTipoCuenta("Cuenta única");
        List<PagoMultipleView> listaPagoMultipleView = new ArrayList<PagoMultipleView>();
        listaPagoMultipleView.add(pagoView1);
        listaPagoMultipleView.add(pagoView2);
        listaPagoMultipleView.add(pagoView3);

        PagoMultipleListView pagoMultipleView = new PagoMultipleListView();
        pagoMultipleView.setPagos(listaPagoMultipleView);

        return pagoMultipleView;
    }

    /**
     * Ejecutar pago multiple error unico.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void ejecutarPagoMultipleErrorUnico() throws DAOException, BusinessException {
        PagoMultipleListView pagoMultipleView = generarPagoMultipleViewIngreso();

        PagoMultipleDTO multipleDTORetorno = generarPagoMultipleDTORetorno();

        PagoInEntity pagoDTO0 = multipleDTORetorno.getPagos().get(0);
        String mensajeErrorUnico = "SALDO INSUFICIENTE";
        pagoDTO0.setMensaje(mensajeErrorUnico);
        pagoDTO0.setTipoError(TipoError.ERROR_SALDO_INSUFICIENTE);
        pagoDTO0.setReintentar(Boolean.TRUE);
        pagoDTO0.setSucursalCuenta("000");
        pagoDTO0.setNumeroCuenta("3607390");
        pagoDTO0.setMonto("10000");

        PagoInEntity pagoDTO1 = multipleDTORetorno.getPagos().get(1);
        pagoDTO1.setMensaje(mensajeErrorUnico);
        pagoDTO1.setTipoError(TipoError.ERROR_SALDO_INSUFICIENTE);
        pagoDTO1.setReintentar(Boolean.TRUE);
        pagoDTO1.setSucursalCuenta("000");
        pagoDTO1.setNumeroCuenta("3607390");
        pagoDTO1.setMonto("30000");

        PagoInEntity pagoDTO2 = multipleDTORetorno.getPagos().get(2);
        pagoDTO2.setMensaje(mensajeErrorUnico);
        pagoDTO2.setTipoError(TipoError.ERROR_SALDO_INSUFICIENTE);
        pagoDTO2.setReintentar(Boolean.TRUE);
        pagoDTO2.setSucursalCuenta("000");
        pagoDTO2.setNumeroCuenta("3607390");
        pagoDTO2.setMonto("12000");

        multipleDTORetorno.setTodosOK(Boolean.FALSE);
        multipleDTORetorno.setErrorUnico(Boolean.TRUE);

        MedioPago medioPago1 = new MedioPago();
        medioPago1.setNombreFantasia("Superman");
        medioPago1.setRubroFantasia("Acuaman");
        medioPago1.setTipoImporte("1");

        Respuesta<MedioPago> respuesta = new Respuesta<MedioPago>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(medioPago1);
        MedioPago empresa = new MedioPago();
        empresa.setNombreFantasia("Los Gastones");
        Mockito.when(buscadorMediosPagoDAO.getByCodigo(Matchers.anyString())).thenReturn(empresa);
        Mockito.when(MocKMediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respuesta);
        Mockito.when(pagoMisCuentasDAO.ejecutarPagoMultiple(Matchers.anyListOf(PagoInEntity.class),
                Matchers.any(Cliente.class))).thenReturn(multipleDTORetorno);

        Respuesta<PagoMultipleDTO> respuestaEjecutar = pagoMultipleBO.ejecutarPagoMultiple(pagoMultipleView,
                clienteMock);

        Assert.assertNotNull(respuestaEjecutar);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuestaEjecutar.getEstadoRespuesta());
        Assert.assertNotNull(respuestaEjecutar.getRespuesta());

        Assert.assertTrue(respuestaEjecutar.getRespuesta().isErrorUnico());
        Assert.assertEquals(respuestaEjecutar.getRespuesta().getMensajeErrorUnico(), mensajeErrorUnico);
        Assert.assertEquals(respuestaEjecutar.getRespuesta().getTipoErrorUnico(), TipoError.ERROR_SALDO_INSUFICIENTE);
        Assert.assertTrue(respuestaEjecutar.getRespuesta().isReintentarErrorUnico());

        // Estadistica estadistica = new Estadistica();
        // estadistica.setCodigoTransaccion(EstadisticasConstants.PAGO_MULTIPLE_EJECUCION);
        // estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        // estadistica.setImporte("666.66");
        // estadistica.setMoneda(DivisaEnum.PESO.getCodigo());
        // Mockito.verify(estadisticaBO).add(Matchers.eq(estadistica),
        // Matchers.eq(registroSesionMock),
        // Matchers.eq(clienteMock));

    }

    /**
     * Ejecutar pago multiple error distinto.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void ejecutarPagoMultipleErrorDistinto() throws DAOException, BusinessException {
        PagoMultipleListView pagoMultipleView = generarPagoMultipleViewIngreso();

        PagoMultipleDTO multipleDTORetorno = generarPagoMultipleDTORetorno();

        PagoInEntity pagoDTO0 = multipleDTORetorno.getPagos().get(0);
        pagoDTO0.setMensaje("SALDO INSUFICIENTE");
        pagoDTO0.setTipoError(TipoError.ERROR_SALDO_INSUFICIENTE);
        pagoDTO0.setReintentar(Boolean.TRUE);

        PagoInEntity pagoDTO1 = multipleDTORetorno.getPagos().get(1);
        pagoDTO1.setMensaje("PAGO EFECTUADO");
        pagoDTO1.setTipoError(TipoError.ERROR_PAGO_EFECTUADO);
        pagoDTO1.setReintentar(Boolean.FALSE);

        PagoInEntity pagoDTO2 = multipleDTORetorno.getPagos().get(2);
        pagoDTO2.setMensaje("SALDO INSUFICIENTE");
        pagoDTO2.setTipoError(TipoError.ERROR_SALDO_INSUFICIENTE);
        pagoDTO2.setReintentar(Boolean.TRUE);

        multipleDTORetorno.setTodosOK(Boolean.FALSE);
        multipleDTORetorno.setErrorUnico(Boolean.FALSE);
        MedioPago medioPago1 = new MedioPago();
        medioPago1.setNombreFantasia("Superman");
        medioPago1.setRubroFantasia("Acuaman");
        medioPago1.setTipoImporte("1");

        Respuesta<MedioPago> respuesta = new Respuesta<MedioPago>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(medioPago1);

        MedioPago empresa = new MedioPago();
        empresa.setNombreFantasia("Los Gastones");
        Mockito.when(buscadorMediosPagoDAO.getByCodigo(Matchers.anyString())).thenReturn(empresa);
        Mockito.when(MocKMediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respuesta);
        Mockito.when(pagoMisCuentasDAO.ejecutarPagoMultiple(Matchers.anyListOf(PagoInEntity.class),
                Matchers.any(Cliente.class))).thenReturn(multipleDTORetorno);

        Respuesta<PagoMultipleDTO> respuestaEjecutar = pagoMultipleBO.ejecutarPagoMultiple(pagoMultipleView,
                clienteMock);

        Assert.assertNotNull(respuestaEjecutar);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuestaEjecutar.getEstadoRespuesta());
        Assert.assertNotNull(respuestaEjecutar.getRespuesta());

        Assert.assertFalse(respuestaEjecutar.getRespuesta().isErrorUnico());
        Assert.assertNotNull(respuestaEjecutar.getRespuesta().getMensajeErrorUnico());
        Assert.assertNull(respuestaEjecutar.getRespuesta().getTipoErrorUnico());
        Assert.assertFalse(respuestaEjecutar.getRespuesta().isReintentarErrorUnico());

        // Estadistica estadistica = new Estadistica();
        // estadistica.setCodigoTransaccion(EstadisticasConstants.PAGO_MULTIPLE_EJECUCION);
        // estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        // estadistica.setImporte("666.66");
        // estadistica.setMoneda(DivisaEnum.PESO.getCodigo());
        // Mockito.verify(estadisticaBO).add(Matchers.eq(estadistica),
        // Matchers.eq(registroSesionMock),
        // Matchers.eq(clienteMock));

    }

    /**
     * Ejecutar pago multiple error parcial.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void ejecutarPagoMultipleErrorParcial() throws DAOException, BusinessException {
        PagoMultipleListView pagoMultipleView = generarPagoMultipleViewIngreso();

        PagoMultipleDTO multipleDTORetorno = generarPagoMultipleDTORetorno();

        PagoInEntity pagoDTO0 = multipleDTORetorno.getPagos().get(0);
        pagoDTO0.setMensaje("SALDO INSUFICIENTE");
        pagoDTO0.setTipoError(TipoError.ERROR_SALDO_INSUFICIENTE);
        pagoDTO0.setReintentar(Boolean.TRUE);

        PagoInEntity pagoDTO1 = multipleDTORetorno.getPagos().get(1);
        pagoDTO1.setComprobantePorServicio("1234567789");
        pagoDTO1.setNumeroControl("987654321");
        pagoDTO1.setFechaDePago(new Date());
        pagoDTO1.setTipoError(null);

        PagoInEntity pagoDTO2 = multipleDTORetorno.getPagos().get(2);
        pagoDTO2.setMensaje("PAGO EFECTUADO");
        pagoDTO2.setTipoError(TipoError.ERROR_PAGO_EFECTUADO);
        pagoDTO2.setReintentar(Boolean.FALSE);

        multipleDTORetorno.setTodosOK(Boolean.FALSE);
        multipleDTORetorno.setErrorUnico(Boolean.FALSE);
        MedioPago medioPago1 = new MedioPago();
        medioPago1.setNombreFantasia("Superman");
        medioPago1.setRubroFantasia("Acuaman");
        medioPago1.setTipoImporte("1");

        Respuesta<MedioPago> respuesta = new Respuesta<MedioPago>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(medioPago1);

        MedioPago empresa = new MedioPago();
        empresa.setNombreFantasia("Los Gastones");
        Mockito.when(buscadorMediosPagoDAO.getByCodigo(Matchers.anyString())).thenReturn(empresa);
        Mockito.when(MocKMediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respuesta);
        Mockito.when(pagoMisCuentasDAO.ejecutarPagoMultiple(Matchers.anyListOf(PagoInEntity.class),
                Matchers.any(Cliente.class))).thenReturn(multipleDTORetorno);

        Respuesta<PagoMultipleDTO> respuestaEjecutar = pagoMultipleBO.ejecutarPagoMultiple(pagoMultipleView,
                clienteMock);

        Assert.assertNotNull(respuestaEjecutar);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuestaEjecutar.getEstadoRespuesta());
        Assert.assertNotNull(respuestaEjecutar.getRespuesta());

        Assert.assertFalse(respuestaEjecutar.getRespuesta().isErrorUnico());
        Assert.assertNull(respuestaEjecutar.getRespuesta().getMensajeErrorUnico());
        Assert.assertNull(respuestaEjecutar.getRespuesta().getTipoErrorUnico());
        Assert.assertFalse(respuestaEjecutar.getRespuesta().isReintentarErrorUnico());

        // Estadistica estadistica = new Estadistica();
        // estadistica.setCodigoTransaccion(EstadisticasConstants.PAGO_MULTIPLE_EJECUCION);
        // estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR_PARCIAL_PAGO_MULTIPLE);
        // estadistica.setImporte("222.22");
        // estadistica.setMoneda(DivisaEnum.PESO.getCodigo());
        // Mockito.verify(estadisticaBO).add(Matchers.eq(estadistica),
        // Matchers.eq(registroSesionMock),
        // Matchers.eq(clienteMock));
    }

    /**
     * Ejecutar pago multiple DAO exception.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void ejecutarPagoMultipleDAOException() throws DAOException, BusinessException {
        PagoMultipleListView pagoMultipleView = generarPagoMultipleViewIngreso();
        MedioPago medioPago1 = null;

        Respuesta<MedioPago> respuesta = new Respuesta<MedioPago>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuesta.setRespuesta(medioPago1);
        respuesta.setRespuestaVacia(true);

        MedioPago empresa = new MedioPago();
        empresa.setNombreFantasia("Los Gastones");
        Mockito.when(buscadorMediosPagoDAO.getByCodigo(Matchers.anyString())).thenReturn(empresa);
        Mockito.when(MocKMediosPagoBO.getByCodigo(Matchers.anyString())).thenReturn(respuesta);
        Mockito.when(pagoMisCuentasDAO.ejecutarPagoMultiple(Matchers.anyListOf(PagoInEntity.class),
                Matchers.any(Cliente.class))).thenThrow(DAOException.class);

        Respuesta<PagoMultipleDTO> respuestaEjecutar = pagoMultipleBO.ejecutarPagoMultiple(pagoMultipleView,
                clienteMock);

        Assert.assertNotNull(respuestaEjecutar);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuestaEjecutar.getEstadoRespuesta());
        // Estadistica estadistica = new Estadistica();
        // estadistica.setCodigoTransaccion(EstadisticasConstants.PAGO_MULTIPLE_EJECUCION);
        // estadistica.setCodigoError(EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        // estadistica.setImporte("666.66");
        // estadistica.setMoneda(DivisaEnum.PESO.getCodigo());
        // Mockito.verify(estadisticaBO).add(Matchers.eq(estadistica),
        // Matchers.eq(registroSesionMock),
        // Matchers.eq(clienteMock));
    }

    /**
     * Generar pago multiple view ingreso.
     *
     * @return the pago multiple list view
     */
    private PagoMultipleListView generarPagoMultipleViewIngreso() {

        PagoMultipleView pagoView1 = new PagoMultipleView();
        pagoView1.setNumeroCuenta("011-000111/1");
        pagoView1.setMoneda("ARS");
        pagoView1.setSimboloMoneda("$");
        pagoView1.setMontoSinFormatear("111.11");
        pagoView1.setMontoInicial("111.11");
        pagoView1.setMonto("111.11");
        pagoView1.setTipoPago("2");
        pagoView1.setDescripcionTipoCuenta("Cuenta única");
        PagoMultipleView pagoView2 = new PagoMultipleView();
        pagoView2.setNumeroCuenta("022-000222/2");
        pagoView2.setMoneda("ARS");
        pagoView2.setSimboloMoneda("$");
        pagoView2.setMonto("111.11");
        pagoView2.setMontoSinFormatear("222.22");
        pagoView2.setMontoInicial("111.11");
        pagoView2.setTipoPago("2");
        pagoView2.setDescripcionTipoCuenta("Cuenta única");
        PagoMultipleView pagoView3 = new PagoMultipleView();
        pagoView3.setNumeroCuenta("033-000333/3");
        pagoView3.setMoneda("ARS");
        pagoView3.setSimboloMoneda("$");
        pagoView3.setMonto("111.11");
        pagoView3.setMontoInicial("111.11");
        pagoView3.setMontoSinFormatear("333.33");
        pagoView3.setTipoPago("2");
        pagoView3.setDescripcionTipoCuenta("Cuenta única");
        List<PagoMultipleView> listaPagoMultipleView = new ArrayList<PagoMultipleView>();
        listaPagoMultipleView.add(pagoView1);
        listaPagoMultipleView.add(pagoView2);
        listaPagoMultipleView.add(pagoView3);

        PagoMultipleListView pagoMultipleView = new PagoMultipleListView();
        pagoMultipleView.setPagos(listaPagoMultipleView);

        return pagoMultipleView;
    }

    /**
     * Generar pago multiple DTO retorno.
     *
     * @return the pago multiple DTO
     */
    private PagoMultipleDTO generarPagoMultipleDTORetorno() {

        PagoInEntity pagoDTO1 = new PagoPMC();
        pagoDTO1.setNumeroCuenta("011-000111/1");
        pagoDTO1.setMoneda("ARS");
        pagoDTO1.setMonto("111.11");
        PagoInEntity pagoDTO2 = new PagoPMC();
        pagoDTO2.setNumeroCuenta("022-000222/2");
        pagoDTO2.setMoneda("ARS");
        pagoDTO2.setMonto("222.22");
        PagoInEntity pagoDTO3 = new PagoPMC();
        pagoDTO3.setNumeroCuenta("033-000333/3");
        pagoDTO3.setMoneda("ARS");
        pagoDTO3.setMonto("333.33");

        List<PagoInEntity> listaDePagos = new ArrayList<PagoInEntity>();
        listaDePagos.add(pagoDTO1);
        listaDePagos.add(pagoDTO2);
        listaDePagos.add(pagoDTO3);

        PagoMultipleDTO pagoMultipleDTO = new PagoMultipleDTO();
        pagoMultipleDTO.setPagos(listaDePagos);

        return pagoMultipleDTO;
    }
}
