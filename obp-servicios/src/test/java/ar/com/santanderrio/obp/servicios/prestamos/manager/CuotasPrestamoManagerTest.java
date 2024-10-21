package ar.com.santanderrio.obp.servicios.prestamos.manager;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoDTO;
import org.junit.Assert;
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
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.base.mensaje.entities.MensajeMock;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.CuentaView;
import ar.com.santanderrio.obp.servicios.pagos.bo.PreFormalizacionPrestamoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.CuentaPrestamoDebitoAdherido;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.pagos.entities.IntervinienteMock;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoMock;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamoMock;
import ar.com.santanderrio.obp.servicios.prestamos.bo.CuotasPrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaOutEntityMock;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CuotaPrestamoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.DeudaPrestamoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.exception.SinCuotasPagasException;
import ar.com.santanderrio.obp.servicios.prestamos.view.ProximasCuotasView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl.CuotasPrestamoManagerImpl;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.bo.DestinoPrestamoBO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;
import org.opensaml.xml.signature.P;

/**
 * The Class CuotasPrestamoManagerTest.
 *
 * @author mariano.g.pulera
 */

@RunWith(MockitoJUnitRunner.class)
public class CuotasPrestamoManagerTest {

    /** The cuotas prestamo manager. */
    @InjectMocks
    private CuotasPrestamoManagerImpl cuotasPrestamoManager;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The prestamo BO. */
    @Mock
    private PrestamoBO prestamoBO;

    /** The pre formalizacion prestamo BO. */
    @Mock
    private PreFormalizacionPrestamoBO preFormalizacionPrestamoBO;

    /** The cuotas prestamo BO. */
    @Mock
    private CuotasPrestamoBO cuotasPrestamoBO;

    /** The destino prestamo BO. */
    @Mock
    private DestinoPrestamoBO destinoPrestamoBO;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The legal BO. */
    @Mock
    private LegalBO legalBO;

    @Mock
    private CuentaBO cuentaBO;

    /**
     * Obtener proximas cuotas ok tipo prestamo personal.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerProximasCuotasOkTipoPrestamoPersonal() throws BusinessException {

        // When
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuentaPrestamo = new Cuenta();
        cuentaPrestamo.setClaseCuenta("4");
        cuentaPrestamo.setIndex(10);
        cuentaPrestamo.setNroCuentaProducto("0000000025798066");
        cuentaPrestamo.setNroSucursal("0221");
        cuentaPrestamo.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuentaPrestamo);
        cliente.setCuentas(cuentas);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("10");

        PrestamoDTO prestamoDTO = new PrestamoDTO();
        Prestamo prestamo = new Prestamo();
        PreFormalizacion preFormalizacion = new PreFormalizacion();

        prestamoDTO.setPrestamo(prestamo);
        prestamo.setImporteTotalCuota(new BigDecimal(700.11));
        prestamo.setNumeroRecibo("12");
        prestamo.setCuenta(cuentaPrestamo);

        Respuesta<PreFormalizacion> preFormalizacionRespuesta = new Respuesta<PreFormalizacion>();
        preFormalizacion.setPlazo("15");
        CuentaPrestamoDebitoAdherido prestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();
        prestamoDebitoAdherido.setMontoAPagar("00000000000000000");
        preFormalizacion.setPrestamoDebitoAdherido(prestamoDebitoAdherido);
        preFormalizacionRespuesta.setRespuesta(preFormalizacion);
        prestamoDTO.setPreFormalizacion(preFormalizacion);


        DeudaPrestamoEntity deudaPrestamoEntity = new DeudaPrestamoEntity();

        List<CuotaPrestamoEntity> lista = new ArrayList<CuotaPrestamoEntity>();
        CuotaPrestamoEntity cuotaPrestamoEntity = new CuotaPrestamoEntity();

        cuotaPrestamoEntity.setFeliqRec("2017-04-06");
        cuotaPrestamoEntity.setSaldoPrev("0000000000");
        cuotaPrestamoEntity.setCapRec("0000000000+");
        cuotaPrestamoEntity.setIntOrdRec("0000000000+");
        cuotaPrestamoEntity.setSegRec("000000000000+");
        cuotaPrestamoEntity.setImpTotRec("00000000000+");
        cuotaPrestamoEntity.setTasaRec("000000000");
        cuotaPrestamoEntity.setTae("000000000");
        cuotaPrestamoEntity.setCftna("000000000");
        cuotaPrestamoEntity.setCftnasi("000000000");
        cuotaPrestamoEntity.setImptRec("00000000000000000+");
        cuotaPrestamoEntity.setNumrecRec("0003");

        lista.add(cuotaPrestamoEntity);

        deudaPrestamoEntity.setListaRepeticiones(lista);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(prestamoBO.obtenerDetallePrestamo(Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(prestamoDTO);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cuenta.class)))
                .thenReturn(preFormalizacionRespuesta);
        when(cuotasPrestamoBO.consultarProximasCuotas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(deudaPrestamoEntity);
        when(destinoPrestamoBO.buscarDescripcionPorCodigoDestinoFondo(Matchers.anyString()))
                .thenReturn("Viajes y turismo");

        // Then
        Respuesta<ProximasCuotasView> respuesta = cuotasPrestamoManager.obtenerProximasCuotas(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener proximas cuotas ok tipo prestamo personal destino prestamo null.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerProximasCuotasOkTipoPrestamoPersonalDestinoPrestamoNull() throws BusinessException {

        // When
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuentaPrestamo = new Cuenta();
        cuentaPrestamo.setClaseCuenta("4");
        cuentaPrestamo.setIndex(10);
        cuentaPrestamo.setNroCuentaProducto("0000000025798066");
        cuentaPrestamo.setNroSucursal("0221");
        cuentaPrestamo.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuentaPrestamo);
        cliente.setCuentas(cuentas);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("10");

        Prestamo prestamo = new Prestamo();
        prestamo.setImporteTotalCuota(new BigDecimal(700.11));
        prestamo.setNumeroRecibo("12");
        prestamo.setCuenta(cuentaPrestamo);

        Respuesta<PreFormalizacion> preFormalizacionRespuesta = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        preFormalizacion.setPlazo("15");
        CuentaPrestamoDebitoAdherido prestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();
        prestamoDebitoAdherido.setMontoAPagar("00000000000000000");
        preFormalizacion.setPrestamoDebitoAdherido(prestamoDebitoAdherido);
        preFormalizacionRespuesta.setRespuesta(preFormalizacion);

        PrestamoDTO prestamoDTO = new PrestamoDTO(prestamo, preFormalizacion);

        DeudaPrestamoEntity deudaPrestamoEntity = new DeudaPrestamoEntity();

        List<CuotaPrestamoEntity> lista = new ArrayList<CuotaPrestamoEntity>();
        CuotaPrestamoEntity cuotaPrestamoEntity = new CuotaPrestamoEntity();

        cuotaPrestamoEntity.setFeliqRec("2017-04-06");
        cuotaPrestamoEntity.setSaldoPrev("0000000000");
        cuotaPrestamoEntity.setCapRec("0000000000+");
        cuotaPrestamoEntity.setIntOrdRec("0000000000+");
        cuotaPrestamoEntity.setSegRec("000000000000+");
        cuotaPrestamoEntity.setImpTotRec("00000000000+");
        cuotaPrestamoEntity.setTasaRec("000000000");
        cuotaPrestamoEntity.setTae("000000000");
        cuotaPrestamoEntity.setCftna("000000000");
        cuotaPrestamoEntity.setCftnasi("000000000");
        cuotaPrestamoEntity.setImptRec("00000000000000000+");
        cuotaPrestamoEntity.setNumrecRec("0003");

        lista.add(cuotaPrestamoEntity);

        deudaPrestamoEntity.setListaRepeticiones(lista);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(prestamoBO.obtenerDetallePrestamo(Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(prestamoDTO);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cuenta.class)))
                .thenReturn(preFormalizacionRespuesta);
        when(cuotasPrestamoBO.consultarProximasCuotas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(deudaPrestamoEntity);
        when(destinoPrestamoBO.buscarDescripcionPorCodigoDestinoFondo(Matchers.anyString())).thenReturn(null);

        // Then
        Respuesta<ProximasCuotasView> respuesta = cuotasPrestamoManager.obtenerProximasCuotas(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener proximas cuotas ok tipo prestamo prendario.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerProximasCuotasOkTipoPrestamoPrendario() throws BusinessException {

        // When
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuentaPrestamo = new Cuenta();
        cuentaPrestamo.setClaseCuenta("2");
        cuentaPrestamo.setIndex(10);
        cuentaPrestamo.setNroCuentaProducto("0000000025798066");
        cuentaPrestamo.setNroSucursal("0221");
        cuentaPrestamo.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuentaPrestamo);
        cliente.setCuentas(cuentas);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("10");

        Prestamo prestamo = new Prestamo();
        prestamo.setImporteTotalCuota(new BigDecimal(700.11));
        prestamo.setNumeroRecibo("12");
        prestamo.setCuenta(cuentaPrestamo);

        Respuesta<PreFormalizacion> preFormalizacionRespuesta = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        preFormalizacion.setPlazo("15");
        CuentaPrestamoDebitoAdherido prestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();
        prestamoDebitoAdherido.setMontoAPagar("00000000000000000");
        preFormalizacion.setPrestamoDebitoAdherido(prestamoDebitoAdherido);
        preFormalizacionRespuesta.setRespuesta(preFormalizacion);

        PrestamoDTO prestamoDTO = new PrestamoDTO(prestamo, preFormalizacion);

        DeudaPrestamoEntity deudaPrestamoEntity = new DeudaPrestamoEntity();

        List<CuotaPrestamoEntity> lista = new ArrayList<CuotaPrestamoEntity>();
        CuotaPrestamoEntity cuotaPrestamoEntity = new CuotaPrestamoEntity();

        cuotaPrestamoEntity.setFeliqRec("2017-04-06");
        cuotaPrestamoEntity.setSaldoPrev("0000000000");
        cuotaPrestamoEntity.setCapRec("0000000000+");
        cuotaPrestamoEntity.setIntOrdRec("0000000000+");
        cuotaPrestamoEntity.setSegRec("000000000000+");
        cuotaPrestamoEntity.setImpTotRec("00000000000+");
        cuotaPrestamoEntity.setTasaRec("000000000");
        cuotaPrestamoEntity.setTae("000000000");
        cuotaPrestamoEntity.setCftna("000000000");
        cuotaPrestamoEntity.setCftnasi("000000000");
        cuotaPrestamoEntity.setImptRec("00000000000000000+");
        cuotaPrestamoEntity.setNumrecRec("0003");

        lista.add(cuotaPrestamoEntity);

        deudaPrestamoEntity.setListaRepeticiones(lista);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(prestamoBO.obtenerDetallePrestamo(Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(prestamoDTO);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cuenta.class)))
                .thenReturn(preFormalizacionRespuesta);
        when(cuotasPrestamoBO.consultarProximasCuotas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(deudaPrestamoEntity);

        // Then
        Respuesta<ProximasCuotasView> respuesta = cuotasPrestamoManager.obtenerProximasCuotas(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener proximas cuotas ok tipo prestamo hipotecario.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerProximasCuotasOkTipoPrestamoHipotecario() throws BusinessException {

        // When
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuentaPrestamo = new Cuenta();
        cuentaPrestamo.setClaseCuenta("8");
        cuentaPrestamo.setIndex(10);
        cuentaPrestamo.setNroCuentaProducto("0000000025798066");
        cuentaPrestamo.setNroSucursal("0221");
        cuentaPrestamo.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuentaPrestamo);
        cliente.setCuentas(cuentas);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("10");

        Prestamo prestamo = new Prestamo();
        prestamo.setImporteTotalCuota(new BigDecimal(700.11));
        prestamo.setNumeroRecibo("12");
        prestamo.setCuenta(cuentaPrestamo);

        Respuesta<PreFormalizacion> preFormalizacionRespuesta = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        preFormalizacion.setPlazo("15");
        CuentaPrestamoDebitoAdherido prestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();
        prestamoDebitoAdherido.setMontoAPagar("00000000000000000");
        preFormalizacion.setPrestamoDebitoAdherido(prestamoDebitoAdherido);
        preFormalizacionRespuesta.setRespuesta(preFormalizacion);

        PrestamoDTO prestamoDTO = new PrestamoDTO();
        prestamoDTO.setPrestamo(prestamo);
        prestamoDTO.setPreFormalizacion(preFormalizacion);

        DeudaPrestamoEntity deudaPrestamoEntity = new DeudaPrestamoEntity();

        List<CuotaPrestamoEntity> lista = new ArrayList<CuotaPrestamoEntity>();
        CuotaPrestamoEntity cuotaPrestamoEntity = new CuotaPrestamoEntity();

        cuotaPrestamoEntity.setFeliqRec("2017-04-06");
        cuotaPrestamoEntity.setSaldoPrev("0000000000");
        cuotaPrestamoEntity.setCapRec("0000000000+");
        cuotaPrestamoEntity.setIntOrdRec("0000000000+");
        cuotaPrestamoEntity.setSegRec("000000000000+");
        cuotaPrestamoEntity.setImpTotRec("00000000000+");
        cuotaPrestamoEntity.setTasaRec("000000000");
        cuotaPrestamoEntity.setTae("000000000");
        cuotaPrestamoEntity.setCftna("000000000");
        cuotaPrestamoEntity.setCftnasi("000000000");
        cuotaPrestamoEntity.setImptRec("00000000000000000+");
        cuotaPrestamoEntity.setNumrecRec("0003");

        lista.add(cuotaPrestamoEntity);

        deudaPrestamoEntity.setListaRepeticiones(lista);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(prestamoBO.obtenerDetallePrestamo(Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(prestamoDTO);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cuenta.class)))
                .thenReturn(preFormalizacionRespuesta);
        when(cuotasPrestamoBO.consultarProximasCuotas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(deudaPrestamoEntity);

        // Then
        Respuesta<ProximasCuotasView> respuesta = cuotasPrestamoManager.obtenerProximasCuotas(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener proximas cuotas warning objeto prestamo null.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerProximasCuotasWarningObjetoPrestamoNull() throws BusinessException {

        // When
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuentaPrestamo = new Cuenta();
        cuentaPrestamo.setClaseCuenta("4");
        cuentaPrestamo.setIndex(10);
        cuentaPrestamo.setNroCuentaProducto("0000000025798066");
        cuentaPrestamo.setNroSucursal("0221");
        cuentaPrestamo.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuentaPrestamo);
        cliente.setCuentas(cuentas);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("10");

        Prestamo prestamo = new Prestamo();
        prestamo.setImporteTotalCuota(new BigDecimal(700.11));
        prestamo.setNumeroRecibo("12");
        prestamo.setCuenta(cuentaPrestamo);

        CuentaView cuenta = new CuentaView();
        cuenta.setAbreviaturaTipoCuenta("ATPM");
        cuenta.setAlias("");
        cuenta.setCbu("0720029820000001536054");
        cuenta.setDescripcionTipoCuenta("PRESTAMO");
        cuenta.setIntervinientes(null);
        cuenta.setIsFavorito(Boolean.FALSE);
        cuenta.setNumero("0000000025798066");
        cuenta.setNumeroFormateado("023-5698754/8");
        cuenta.setTituloCuenta("TI");

        TipoPrestamoEnum tipoPrestamo = TipoPrestamoEnum.fromIdString(cuentaPrestamo.getClaseCuenta());

        Respuesta<PreFormalizacion> preFormalizacionRespuesta = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        preFormalizacion.setPlazo("15");
        preFormalizacion.getPrestamoDebitoAdherido().setMontoAPagar("000000000040000");
        preFormalizacionRespuesta.setRespuesta(preFormalizacion);

        DeudaPrestamoEntity deudaPrestamoEntity = new DeudaPrestamoEntity();

        List<CuotaPrestamoEntity> lista = new ArrayList<CuotaPrestamoEntity>();
        CuotaPrestamoEntity cuotaPrestamoEntity = new CuotaPrestamoEntity();

        cuotaPrestamoEntity.setFeliqRec("2017-04-06");
        cuotaPrestamoEntity.setSaldoPrev("0000000000");
        cuotaPrestamoEntity.setCapRec("0000000000+");
        cuotaPrestamoEntity.setIntOrdRec("0000000000+");
        cuotaPrestamoEntity.setSegRec("000000000000+");
        cuotaPrestamoEntity.setImpTotRec("00000000000+");
        cuotaPrestamoEntity.setTasaRec("000000000");
        cuotaPrestamoEntity.setTae("000000000");
        cuotaPrestamoEntity.setCftna("000000000");
        cuotaPrestamoEntity.setCftnasi("000000000");
        cuotaPrestamoEntity.setImptRec("00000000000000000+");
        cuotaPrestamoEntity.setNumrecRec("0003");

        ProximasCuotasView proximasCuotasView = new ProximasCuotasView();
        proximasCuotasView.setProximasCuotas(lista, tipoPrestamo, prestamo, consultaPrestamo, null);

        lista.add(cuotaPrestamoEntity);

        deudaPrestamoEntity.setListaRepeticiones(lista);

        Mensaje mensajeError = new Mensaje();
        mensajeError.setMensaje("Error al obtener cuentas prestamo");

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(prestamoBO.obtenerDetallePrestamo(Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(null);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cuenta.class)))
                .thenReturn(preFormalizacionRespuesta);
        when(cuotasPrestamoBO.consultarProximasCuotas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(deudaPrestamoEntity);
        when(destinoPrestamoBO.buscarDescripcionPorCodigoDestinoFondo(Matchers.anyString())).thenReturn(null);
        when(mensajeBO.obtenerMensajePorCodigo("1104")).thenReturn(mensajeError);

        // Then
        Respuesta<ProximasCuotasView> respuesta = cuotasPrestamoManager.obtenerProximasCuotas(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }

    /**
     * Obtener proximas cuotas warning respuesta pre formalizacion error.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerProximasCuotasWarningRespuestaPreFormalizacionError() throws BusinessException {

        // When
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuentaPrestamo = new Cuenta();
        cuentaPrestamo.setClaseCuenta("8");
        cuentaPrestamo.setIndex(10);
        cuentaPrestamo.setNroCuentaProducto("0000000025798066");
        cuentaPrestamo.setNroSucursal("0221");
        cuentaPrestamo.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuentaPrestamo);
        cliente.setCuentas(cuentas);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("10");

        Prestamo prestamo = new Prestamo();
        prestamo.setImporteTotalCuota(new BigDecimal(700.11));
        prestamo.setNumeroRecibo("12");
        prestamo.setCuenta(cuentaPrestamo);

        Respuesta<PreFormalizacion> preFormalizacionRespuesta = new Respuesta<PreFormalizacion>();
        preFormalizacionRespuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        preFormalizacionRespuesta.setRespuestaVacia(true);

        PrestamoDTO prestamoDTO = new PrestamoDTO(prestamo, null);

        DeudaPrestamoEntity deudaPrestamoEntity = new DeudaPrestamoEntity();

        List<CuotaPrestamoEntity> lista = new ArrayList<CuotaPrestamoEntity>();
        CuotaPrestamoEntity cuotaPrestamoEntity = new CuotaPrestamoEntity();

        cuotaPrestamoEntity.setFeliqRec("2017-04-06");
        cuotaPrestamoEntity.setSaldoPrev("0000000000");
        cuotaPrestamoEntity.setCapRec("0000000000+");
        cuotaPrestamoEntity.setIntOrdRec("0000000000+");
        cuotaPrestamoEntity.setSegRec("000000000000+");
        cuotaPrestamoEntity.setImpTotRec("00000000000+");
        cuotaPrestamoEntity.setTasaRec("000000000");
        cuotaPrestamoEntity.setTae("000000000");
        cuotaPrestamoEntity.setCftna("000000000");
        cuotaPrestamoEntity.setCftnasi("000000000");
        cuotaPrestamoEntity.setImptRec("00000000000000000+");
        cuotaPrestamoEntity.setNumrecRec("0003");

        lista.add(cuotaPrestamoEntity);

        deudaPrestamoEntity.setListaRepeticiones(lista);

        Mensaje mensajeError = new Mensaje();
        mensajeError.setMensaje("Error al obtener objeto cuota");

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(prestamoBO.obtenerDetallePrestamo(Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(prestamoDTO);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cuenta.class)))
                .thenReturn(preFormalizacionRespuesta);
        when(cuotasPrestamoBO.consultarProximasCuotas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(deudaPrestamoEntity);
        when(destinoPrestamoBO.buscarDescripcionPorCodigoDestinoFondo(Matchers.anyString())).thenReturn(null);
        when(mensajeBO.obtenerMensajePorCodigo("1104")).thenReturn(mensajeError);

        // Then
        Respuesta<ProximasCuotasView> respuesta = cuotasPrestamoManager.obtenerProximasCuotas(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener proximas cuotas warning no se obtienen proximas cuotas.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerProximasCuotasWarningNoSeObtienenProximasCuotas() throws BusinessException {

        // When
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuentaPrestamo = new Cuenta();
        cuentaPrestamo.setClaseCuenta("8");
        cuentaPrestamo.setIndex(10);
        cuentaPrestamo.setNroCuentaProducto("0000000025798066");
        cuentaPrestamo.setNroSucursal("0221");
        cuentaPrestamo.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuentaPrestamo);
        cliente.setCuentas(cuentas);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("10");

        Respuesta<PreFormalizacion> preFormalizacionRespuesta = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        preFormalizacion.setPlazo("15");
        preFormalizacion.getPrestamoDebitoAdherido().setMontoAPagar("000000000040000");
        preFormalizacionRespuesta.setRespuesta(preFormalizacion);

        DeudaPrestamoEntity deudaPrestamoEntity = new DeudaPrestamoEntity();

        List<CuotaPrestamoEntity> lista = new ArrayList<CuotaPrestamoEntity>();
        deudaPrestamoEntity.setListaRepeticiones(lista);

        Mensaje mensajeError = new Mensaje();
        mensajeError.setMensaje("Error al obtener proximas cuotas");

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(prestamoBO.obtenerDetallePrestamo(Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(null);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cuenta.class)))
                .thenReturn(preFormalizacionRespuesta);
        when(cuotasPrestamoBO.consultarProximasCuotas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(deudaPrestamoEntity);
        when(destinoPrestamoBO.buscarDescripcionPorCodigoDestinoFondo(Matchers.anyString())).thenReturn(null);
        when(mensajeBO.obtenerMensajePorCodigo("1105")).thenReturn(mensajeError);

        // Then
        Respuesta<ProximasCuotasView> respuesta = cuotasPrestamoManager.obtenerProximasCuotas(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener proximas cuotas ok lista de cuotas mayor A cinco.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerProximasCuotasOkListaDeCuotasMayorACinco() throws BusinessException {

        // When
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuentaPrestamo = new Cuenta();
        cuentaPrestamo.setClaseCuenta("2");
        cuentaPrestamo.setIndex(10);
        cuentaPrestamo.setNroCuentaProducto("0000000025798066");
        cuentaPrestamo.setNroSucursal("0221");
        cuentaPrestamo.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuentaPrestamo);
        cliente.setCuentas(cuentas);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("10");

        Prestamo prestamo = new Prestamo();
        prestamo.setImporteTotalCuota(new BigDecimal(700.11));
        prestamo.setNumeroRecibo("12");
        prestamo.setCuenta(cuentaPrestamo);

        Respuesta<PreFormalizacion> preFormalizacionRespuesta = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        preFormalizacion.setPlazo("15");
        CuentaPrestamoDebitoAdherido prestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();
        prestamoDebitoAdherido.setMontoAPagar("00000000000000000");
        preFormalizacion.setPrestamoDebitoAdherido(prestamoDebitoAdherido);
        preFormalizacionRespuesta.setRespuesta(preFormalizacion);

        PrestamoDTO prestamoDTO = new PrestamoDTO(prestamo, preFormalizacion);

        DeudaPrestamoEntity deudaPrestamoEntity = new DeudaPrestamoEntity();

        List<CuotaPrestamoEntity> lista = new ArrayList<CuotaPrestamoEntity>();
        CuotaPrestamoEntity cuotaPrestamoEntity = new CuotaPrestamoEntity();

        cuotaPrestamoEntity.setFeliqRec("2017-04-06");
        cuotaPrestamoEntity.setSaldoPrev("0000000000");
        cuotaPrestamoEntity.setCapRec("0000000000+");
        cuotaPrestamoEntity.setIntOrdRec("0000000000+");
        cuotaPrestamoEntity.setSegRec("000000000000+");
        cuotaPrestamoEntity.setImpTotRec("00000000000+");
        cuotaPrestamoEntity.setTasaRec("000000000");
        cuotaPrestamoEntity.setTae("000000000");
        cuotaPrestamoEntity.setCftna("000000000");
        cuotaPrestamoEntity.setCftnasi("000000000");
        cuotaPrestamoEntity.setImptRec("00000000000000000+");
        cuotaPrestamoEntity.setNumrecRec("0003");

        for (int i = 0; i < 7; i++) {
            lista.add(cuotaPrestamoEntity);
        }

        deudaPrestamoEntity.setListaRepeticiones(lista);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(prestamoBO.obtenerDetallePrestamo(Matchers.anyString(), Matchers.any(Cliente.class))).thenReturn(prestamoDTO);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cuenta.class)))
                .thenReturn(preFormalizacionRespuesta);
        when(cuotasPrestamoBO.consultarProximasCuotas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(deudaPrestamoEntity);

        // Then
        Respuesta<ProximasCuotasView> respuesta = cuotasPrestamoManager.obtenerProximasCuotas(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);
    }

    /**
     * Obtener proximas cuotas error generico.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerProximasCuotasErrorGenerico() throws BusinessException {

        // When
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuentaPrestamo = new Cuenta();
        cuentaPrestamo.setClaseCuenta("2");
        cuentaPrestamo.setIndex(10);
        cuentaPrestamo.setNroCuentaProducto("0000000025798066");
        cuentaPrestamo.setNroSucursal("0221");
        cuentaPrestamo.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);

        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuentaPrestamo);
        cliente.setCuentas(cuentas);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("10");

        Respuesta<PreFormalizacion> preFormalizacionRespuesta = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        preFormalizacion.setPlazo("15");
        CuentaPrestamoDebitoAdherido prestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();
        prestamoDebitoAdherido.setMontoAPagar("00000000000000000");
        preFormalizacion.setPrestamoDebitoAdherido(prestamoDebitoAdherido);
        preFormalizacionRespuesta.setRespuesta(preFormalizacion);

        DeudaPrestamoEntity deudaPrestamoEntity = new DeudaPrestamoEntity();

        List<CuotaPrestamoEntity> lista = new ArrayList<CuotaPrestamoEntity>();
        CuotaPrestamoEntity cuotaPrestamoEntity = new CuotaPrestamoEntity();

        cuotaPrestamoEntity.setFeliqRec("2017-04-06");
        cuotaPrestamoEntity.setSaldoPrev("0000000000");
        cuotaPrestamoEntity.setCapRec("0000000000+");
        cuotaPrestamoEntity.setIntOrdRec("0000000000+");
        cuotaPrestamoEntity.setSegRec("000000000000+");
        cuotaPrestamoEntity.setImpTotRec("00000000000+");
        cuotaPrestamoEntity.setTasaRec("000000000");
        cuotaPrestamoEntity.setTae("000000000");
        cuotaPrestamoEntity.setCftna("000000000");
        cuotaPrestamoEntity.setCftnasi("000000000");
        cuotaPrestamoEntity.setImptRec("00000000000000000+");
        cuotaPrestamoEntity.setNumrecRec("0003");

        for (int i = 0; i < 7; i++) {
            lista.add(cuotaPrestamoEntity);
        }

        deudaPrestamoEntity.setListaRepeticiones(lista);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(prestamoBO.obtenerDetallePrestamo(Matchers.anyString(), Matchers.any(Cliente.class)))
                .thenThrow(new BusinessException("Error al obtener el detalle del prestamo."));
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cuenta.class)))
                .thenReturn(preFormalizacionRespuesta);
        when(cuotasPrestamoBO.consultarProximasCuotas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenThrow(new BusinessException("Error al obtener la deuda  del prestamo."));
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(MensajeMock.completarInfoMensaje(
                "1105",
                "<p>¡Lo sentimos!</p>No pudimos cargar el del detalle de tus próximas cuotas. Por favor, intentá nuevamente en unos minutos."));

        // Then
        Respuesta<ProximasCuotasView> respuesta = cuotasPrestamoManager.obtenerProximasCuotas(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    /**
     * Dado una consulta prestamo, cuando se invoca al metodo
     * "obtenerCuotasPagas", obtengo una respuesta proximas cuotas view para
     * cuotas pagas OK.
     *
     * @author florencia.n.martinez
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void dadoConsultaPrestamoCuandoInvocaObtenerCuotasPagasObtengoRespuestaProximasCuotasViewOK()
            throws BusinessException, SinCuotasPagasException {

        // When
        Cliente cliente = ClienteMock.completarInfoClientePrestamo();
        PreFormalizacion preformalizacion = crearPreformalizacion();

        Cuenta cuentaDebito = CuentaMock.completarInfoCuenta();
        cuentaDebito.setNroCuentaProducto("000001103548");
        cliente.getCuentas().add(cuentaDebito);
        cuentaDebito.setClaseCuenta("2");

        PrestamoDTO prestamoDTO = new PrestamoDTO();
        Prestamo prestamo = PrestamoMock.completarInfoPrestamo("Préstamo Personal", CuentaMock.completarInfoCuenta(),
                DivisaEnum.PESO, new BigDecimal(154.6900), new BigDecimal(80.5300), new BigDecimal(16.9100),
                new BigDecimal(3.2200), new BigDecimal(255.3500), new BigDecimal(3.2200), "00000000000169100",
                "0000035100616033", "00019", new BigDecimal(2842.1600), new BigDecimal(39.832127),
                new BigDecimal(24.000000), TipoPrestamoEnum.PERSONAL);
        prestamoDTO.setPrestamo(prestamo);
        prestamoDTO.setPreFormalizacion(preformalizacion);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(prestamoBO.obtenerDetallePrestamo(Matchers.anyString(), Matchers.any(Cliente.class)))
                .thenReturn(prestamoDTO);
        when(cuotasPrestamoBO.consultarCuotasPagas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class),
                Matchers.anyString())).thenReturn(ConsultaCuotaPagaOutEntityMock.obtenerConsultaCuotaPagaOutEntity());
        when(cuotasPrestamoBO.consultarIntervinientePrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(
                        IntervinienteMock.completarInfoInterviniente("Muñoz", "20132453678", "Alejandra", null, null));
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cliente.class), Matchers.anyString(),
                Matchers.anyString())).thenReturn(preformalizacion);
        when(legalBO.obtenerLegalesPorCodigo(Matchers.anyString())).thenReturn("Legales para cuotas pagas.");
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuentaDebito);

        // Then
        Respuesta<ProximasCuotasView> respCuotasPagas = cuotasPrestamoManager
                .obtenerCuotasPagas(ConsultaPrestamoMock.completarInfoConsultaPrestamoParaCuotasPagas());

        // Expected
        Assert.assertNotNull(respCuotasPagas.getRespuesta());
        Assert.assertEquals(EstadoRespuesta.OK, respCuotasPagas.getEstadoRespuesta());
    }

    /**
     * Dado una consulta prestamo, cuando se invoca al metodo
     * "obtenerCuotasPagas", obtengo una respuesta proximas cuotas view para
     * cuotas pagas con error de cabecera.
     *
     * @author florencia.n.martinez
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void dadoConsultaPrestamoCuandoInvocaObtenerCuotasPagasObtengoRespuestaProximasCuotasViewErrorCabecera()
            throws BusinessException, SinCuotasPagasException {

        // When
        PreFormalizacion preformalizacion = crearPreformalizacion();
        Cliente cliente = ClienteMock.completarInfoClientePrestamo();

        Cuenta cuentaDebito = CuentaMock.completarInfoCuenta();
        cuentaDebito.setNroCuentaProducto("000001103548");
        cuentaDebito.setClaseCuenta("0");
        cliente.getCuentas().add(cuentaDebito);

        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(prestamoBO.obtenerDetallePrestamo(Matchers.anyString(), Matchers.any(Cliente.class)))
                .thenReturn(null);
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuentaDebito);
        Mockito.when(cuotasPrestamoBO.consultarCuotasPagas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class),
                Matchers.anyString())).thenThrow(BusinessException.class);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1099",
                        "Ocurrió un error en nuestros servicios y la información no se muestra correctamente. Por favor, volvé a ingresar en unos minutos."));
        Mockito.when(cuotasPrestamoBO.consultarIntervinientePrestamo(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenReturn(new Interviniente());
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cliente.class), Matchers.anyString(),
                Matchers.anyString())).thenReturn(preformalizacion);

        // Then
        Respuesta<ProximasCuotasView> respCuotasPagas = cuotasPrestamoManager
                .obtenerCuotasPagas(ConsultaPrestamoMock.completarInfoConsultaPrestamoParaCuotasPagas());

        // Expected
        Assert.assertNotNull(respCuotasPagas);
        Assert.assertEquals(EstadoRespuesta.WARNING, respCuotasPagas.getEstadoRespuesta());
    }

    /**
     * Obtener cuotas pagas con preformalizacion nula test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCuotasPagasConPreformalizacionNulaTest() throws BusinessException, SinCuotasPagasException {
        // Given
        Cliente cliente = ClienteMock.completarInfoClientePrestamo();
        cliente.getCuentas().get(0).setClaseCuenta("5");

        // Cuenta cuentaDebito = CuentaMock.completarInfoCuenta();
        // cuentaDebito.setNroCuentaProducto("000001103548");
        // cliente.getCuentas().add(cuentaDebito);
        ConsultaPrestamo consultaPrestamo = ConsultaPrestamoMock.completarInfoConsultaPrestamoParaCuotasPagas();
        Cuenta cuenta = CuentaMock.completarInfoCuenta();
        cuenta.setNroCuentaProducto("000001103548");
        cuenta.setClaseCuenta("2");

        PreFormalizacion preFormalizacion = new PreFormalizacion();
        CuentaPrestamoDebitoAdherido cpda = new CuentaPrestamoDebitoAdherido();
        cpda.setFechaInicio("2010-09-13");
        preFormalizacion.setPrestamoDebitoAdherido(cpda);
        preFormalizacion.getPrestamoDebitoAdherido().setNroSucursal("000");
        preFormalizacion.getPrestamoDebitoAdherido().setNumero("001103548");

        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(prestamoBO.obtenerDetallePrestamo(Matchers.anyString(), Matchers.any(Cliente.class)))
                .thenThrow(new BusinessException("Error al obtener el detalle del prestamo."));
        Mockito.when(cuotasPrestamoBO.consultarCuotasPagas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class),
                Matchers.anyString()))
                .thenThrow(new BusinessException("Error al consultar las cuotas pagas del prestamo."));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(MensajeMock.completarInfoMensaje("1100",
                        "<p>¡Lo sentimos!</p> No pudimos cargar el detalle de cuotas pagas. Por favor, intentá nuevamente en unos minutos."));
        Mockito.when(cuotasPrestamoBO.consultarIntervinientePrestamo(Matchers.any(Cliente.class),
                Matchers.any(Cuenta.class))).thenReturn(new Interviniente());
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cliente.class), Matchers.anyString(),
                Matchers.anyString())).thenReturn(preFormalizacion);

        // Then
        Respuesta<ProximasCuotasView> respCuotasPagas = cuotasPrestamoManager.obtenerCuotasPagas(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respCuotasPagas);
        Assert.assertEquals(EstadoRespuesta.WARNING, respCuotasPagas.getEstadoRespuesta());
    }

    /**
     * Obtener cuotas de cancelado ok prestamo personal.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCuotasDeCanceladoOkPrestamoPersonalTest() throws BusinessException, SinCuotasPagasException {

        // When
        Cliente cliente = ClienteMock.completarInfoCliente();

        Cuenta cuentaPrestamo = new Cuenta();
        cuentaPrestamo.setNroSucursal("0022");
        cuentaPrestamo.setNroCuentaProducto("23456789");
        cuentaPrestamo.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("0022-2345678/9");
        consultaPrestamo.setTipoPrestamo("Super Préstamo Personal");
        consultaPrestamo.setFechaInicioPrestamo("20/06/2013");
        consultaPrestamo.setFalloPlazoImporte(false);

        Interviniente interviniente = new Interviniente();
        interviniente.setNombre("Pepe");
        interviniente.setApellido("Tarjota");
        interviniente.setCuitcuil("23331196332");

        PreFormalizacion preformalizacion = crearPreformalizacion();

        ConsultaCuotaPagaOutEntity consultaCuotaPagaOutEntity = new ConsultaCuotaPagaOutEntity();
        consultaCuotaPagaOutEntity.setCantidadOcurrencias(3L);

        when(cuotasPrestamoBO.consultarIntervinientePrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(interviniente);
        when(cuotasPrestamoBO.consultarCuotasPagas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class),
                Matchers.anyString())).thenReturn(consultaCuotaPagaOutEntity);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cliente.class), Matchers.anyString(),
                Matchers.anyString())).thenReturn(preformalizacion);

        // Then
        Respuesta<ProximasCuotasView> respuesta = cuotasPrestamoManager.obtenerCuotasDeCancelado(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);

    }

    /**
     * Obtener cuotas de cancelado ok prestamo hipotecario test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCuotasDeCanceladoOkPrestamoHipotecarioTest() throws BusinessException, SinCuotasPagasException {

        // When
        Cliente cliente = ClienteMock.completarInfoCliente();

        Cuenta cuentaPrestamo = new Cuenta();
        cuentaPrestamo.setNroSucursal("0022");
        cuentaPrestamo.setNroCuentaProducto("23456789");
        cuentaPrestamo.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("0022-2345678/9");
        consultaPrestamo.setTipoPrestamo("Super Préstamo Hipotecario");
        consultaPrestamo.setFechaInicioPrestamo("20/06/2013");
        consultaPrestamo.setFalloPlazoImporte(false);

        Interviniente interviniente = new Interviniente();
        interviniente.setNombre("Pepe");
        interviniente.setApellido("Tarjota");
        interviniente.setCuitcuil("23331196332");

        ConsultaCuotaPagaOutEntity consultaCuotaPagaOutEntity = new ConsultaCuotaPagaOutEntity();
        consultaCuotaPagaOutEntity.setCantidadOcurrencias(3L);

        when(cuotasPrestamoBO.consultarIntervinientePrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(interviniente);
        when(cuotasPrestamoBO.consultarCuotasPagas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class),
                Matchers.anyString())).thenReturn(consultaCuotaPagaOutEntity);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cliente.class), Matchers.anyString(),
                Matchers.anyString())).thenReturn(null);

        // Then
        Respuesta<ProximasCuotasView> respuesta = cuotasPrestamoManager.obtenerCuotasDeCancelado(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);

    }

    /**
     * Obtener cuotas de cancelado ok prestamo prendario test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCuotasDeCanceladoOkPrestamoPrendarioTest() throws BusinessException, SinCuotasPagasException {

        // When
        Cliente cliente = ClienteMock.completarInfoCliente();

        Cuenta cuentaPrestamo = new Cuenta();
        cuentaPrestamo.setNroSucursal("0022");
        cuentaPrestamo.setNroCuentaProducto("23456789");
        cuentaPrestamo.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("0022-2345678/9");
        consultaPrestamo.setTipoPrestamo("PRENDARIO");
        consultaPrestamo.setFechaInicioPrestamo("20/06/2013");
        consultaPrestamo.setFalloPlazoImporte(false);

        Interviniente interviniente = new Interviniente();
        interviniente.setNombre("Pepe");
        interviniente.setApellido("Tarjota");
        interviniente.setCuitcuil("23331196332");

        ConsultaCuotaPagaOutEntity consultaCuotaPagaOutEntity = new ConsultaCuotaPagaOutEntity();
        consultaCuotaPagaOutEntity.setCantidadOcurrencias(3L);

        when(cuotasPrestamoBO.consultarIntervinientePrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(interviniente);
        when(cuotasPrestamoBO.consultarCuotasPagas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class),
                Matchers.anyString())).thenReturn(consultaCuotaPagaOutEntity);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cliente.class), Matchers.anyString(),
                Matchers.anyString())).thenReturn(null);

        // Then
        Respuesta<ProximasCuotasView> respuesta = cuotasPrestamoManager.obtenerCuotasDeCancelado(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);

    }

    /**
     * Obtener cuotas de cancelado ok prestamo sola firma test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCuotasDeCanceladoOkPrestamoSolaFirmaTest() throws BusinessException, SinCuotasPagasException {

        // When
        Cliente cliente = ClienteMock.completarInfoCliente();

        Cuenta cuentaPrestamo = new Cuenta();
        cuentaPrestamo.setNroSucursal("0022");
        cuentaPrestamo.setNroCuentaProducto("23456789");
        cuentaPrestamo.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("0022-2345678/9");
        consultaPrestamo.setTipoPrestamo("SOLAFIRMA");
        consultaPrestamo.setFechaInicioPrestamo("20/06/2013");
        consultaPrestamo.setFalloPlazoImporte(false);

        Interviniente interviniente = new Interviniente();
        interviniente.setNombre("Pepe");
        interviniente.setApellido("Tarjota");
        interviniente.setCuitcuil("23331196332");

        ConsultaCuotaPagaOutEntity consultaCuotaPagaOutEntity = new ConsultaCuotaPagaOutEntity();
        consultaCuotaPagaOutEntity.setCantidadOcurrencias(3L);

        when(cuotasPrestamoBO.consultarIntervinientePrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(interviniente);
        when(cuotasPrestamoBO.consultarCuotasPagas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class),
                Matchers.anyString())).thenReturn(consultaCuotaPagaOutEntity);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cliente.class), Matchers.anyString(),
                Matchers.anyString())).thenReturn(null);

        // Then
        Respuesta<ProximasCuotasView> respuesta = cuotasPrestamoManager.obtenerCuotasDeCancelado(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);

    }

    /**
     * Obtener cuotas de cancelado error no obtiene cuotas.
     *
     * @throws BusinessException
     *             the business exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerCuotasDeCanceladoErrorNoObtieneCuotas() throws BusinessException, SinCuotasPagasException {

        // When
        Cliente cliente = ClienteMock.completarInfoCliente();
        Cuenta cuentaPrestamo = new Cuenta();
        cuentaPrestamo.setNroSucursal("0022");
        cuentaPrestamo.setNroCuentaProducto("23456789");
        cuentaPrestamo.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("0022-2345678/9");
        consultaPrestamo.setTipoPrestamo("HIPOTECARIO");
        consultaPrestamo.setFechaInicioPrestamo("20/06/2013");

        Interviniente interviniente = new Interviniente();
        interviniente.setNombre("Pepe");
        interviniente.setApellido("Tarjota");
        interviniente.setCuitcuil("23331196332");

        PreFormalizacion preformalizacion = crearPreformalizacion();

        Mensaje mensajeError = new Mensaje();
        mensajeError.setMensaje("No se recuperaron cuotas");

        when(cuotasPrestamoBO.consultarIntervinientePrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(interviniente);
        when(cuotasPrestamoBO.consultarCuotasPagas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class),
                Matchers.anyString())).thenThrow(BusinessException.class);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cliente.class), Matchers.anyString(),
                Matchers.anyString())).thenReturn(preformalizacion);
        when(mensajeBO.obtenerMensajePorCodigo("1155")).thenReturn(mensajeError);
        when(sesionCliente.getCliente()).thenReturn(cliente);

        // Then
        Respuesta<ProximasCuotasView> respuesta = cuotasPrestamoManager.obtenerCuotasDeCancelado(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);

    }

    /**
     * Obtener cuotas de cancelado error cabecera falta plazo prestamo.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCuotasDeCanceladoErrorCabeceraFaltaPlazoPrestamo()
            throws BusinessException, SinCuotasPagasException {

        // When
        Cliente cliente = ClienteMock.completarInfoCliente();

        Cuenta cuentaPrestamo = new Cuenta();
        cuentaPrestamo.setNroSucursal("0022");
        cuentaPrestamo.setNroCuentaProducto("23456789");
        cuentaPrestamo.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        consultaPrestamo.setNumeroPrestamo("0022-2345678/9");
        consultaPrestamo.setTipoPrestamo("PRENDARIO");
        consultaPrestamo.setFechaInicioPrestamo("20/06/2013");
        consultaPrestamo.setFalloPlazoImporte(true);

        PreFormalizacion preformalizacion = crearPreformalizacion();

        Interviniente interviniente = new Interviniente();
        interviniente.setNombre("Pepe");
        interviniente.setApellido("Tarjota");
        interviniente.setCuitcuil("23331196332");

        ConsultaCuotaPagaOutEntity consultaCuotaPagaOutEntity = new ConsultaCuotaPagaOutEntity();
        consultaCuotaPagaOutEntity.setCantidadOcurrencias(3L);

        Mensaje mensajeError = new Mensaje();
        mensajeError.setMensaje("No se cargaron datos de cabecera");

        when(cuotasPrestamoBO.consultarIntervinientePrestamo(Matchers.any(Cliente.class), Matchers.any(Cuenta.class)))
                .thenReturn(interviniente);
        when(cuotasPrestamoBO.consultarCuotasPagas(Matchers.any(Cliente.class), Matchers.any(Cuenta.class),
                Matchers.anyString())).thenReturn(consultaCuotaPagaOutEntity);
        when(mensajeBO.obtenerMensajePorCodigo("1154")).thenReturn(mensajeError);
        when(preFormalizacionPrestamoBO.obtenerPreFormalizacion(Matchers.any(Cliente.class), Matchers.anyString(),
                Matchers.anyString())).thenReturn(preformalizacion);
        when(sesionCliente.getCliente()).thenReturn(cliente);

        // Then
        Respuesta<ProximasCuotasView> respuesta = cuotasPrestamoManager.obtenerCuotasDeCancelado(consultaPrestamo);

        // Expected
        Assert.assertNotNull(respuesta);

    }

    /**
     * Grabar estadistica detalle cuota de cancelado.
     */
    @Test
    public void grabarEstadisticaDetalleCuotaDeCancelado() {

        // Then
        Respuesta<Void> respuesta = cuotasPrestamoManager.grabarEstadisticaDetalleCuotaDeCancelado();

        // Expected
        Assert.assertNull(respuesta);

    }

    /**
     * Crear preformalizacion.
     *
     * @return the pre formalizacion
     */
    private PreFormalizacion crearPreformalizacion() {

        PreFormalizacion preformalizacion = new PreFormalizacion();
        preformalizacion.setPlazo("6");
        preformalizacion.setCodigoDestinoPrestamo("35139");
        CuentaPrestamoDebitoAdherido cuentaPrestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();
        cuentaPrestamoDebitoAdherido.setFechaInicio("2013-08-122013-08-122013-08-122013-09-122013-09-122013-09-12");
        cuentaPrestamoDebitoAdherido.setImpconce("00000000059000000");
        cuentaPrestamoDebitoAdherido.setMontoAPagar("00000000059000000");
        cuentaPrestamoDebitoAdherido.setNroSucursal("000");
        cuentaPrestamoDebitoAdherido.setTipo("0");
        cuentaPrestamoDebitoAdherido.setNumero("000001103548");
        cuentaPrestamoDebitoAdherido.setId(Long.parseLong("1559555310755723000"));
        preformalizacion.setPrestamoDebitoAdherido(cuentaPrestamoDebitoAdherido);
        return preformalizacion;

    }

}
