/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionTarjetas;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.PagosPendientesBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.CasuisticaErrorTarjetasBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.ConsumoPendienteConfirmacionBo;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.SelectorYCabeceraBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.UltimosConsumosTarjetaBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.CasuisticaErrorTarjetasBOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetasDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LineaDetalleConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimosConsumosDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ErrorTarjetasEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TipoConsumoTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl.UltimosConsumosManagerImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumoTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumosView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.LineaDetalleConsumoTarjetaView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetasView;

/**
 * The Class UltimosConsumosManagerTest.
 *
 * @author sabrina.cis
 */
@RunWith(MockitoJUnitRunner.class)
public class UltimosConsumosManagerTest {

    /** The Constant SIN_ULTIMOS_CONSUMOS. */
    private static final String SIN_ULTIMOS_CONSUMOS = "Aún no tenés consumos en este período.";

    /** The Constant ERROR_SERVICIO_CONSUMOS_PENDIENTES. */
    private static final String ERROR_SERVICIO_CONSUMOS_PENDIENTES = "Ocurrió un error y no podemos verificar si tenés Consumos pendientes de confirmación    ";

    /** The Constant ERROR_SERVICIO_ULTIMOS_CONSUMOS. */
    private static final String ERROR_SERVICIO_ULTIMOS_CONSUMOS = "Ocurrió un error en nuestros servicios al consultar tus últimos consumos.Por favor, volvé a ingresar en unos minutos.  ";

    /** The Constant ERROR_GENERICO_ULTIMOS_CONSUMOS. */
    private static final String ERROR_GENERICO_ULTIMOS_CONSUMOS = "<p>¡Lo sentimos!</p><p><b>Hubo un error con la carga de tus consumos.</b>Por favor, volvé a intentar en unos minutos.</p>";

    /** The mensaje DAO. */
    @Mock
    MensajeDAO mensajeDAO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The sesion tarjetas. */
    @Mock
    private SesionTarjetas sesionTarjetas;

    /** The estadistica ultimosConsumosManager. */
    @Mock
    EstadisticaManager estadisticaManager;

    /** The sesion parametros. */
    @Mock
    SesionParametros sesionParametros;

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;

    /** The ultimosConsumosService. */
    @Mock
    private UltimosConsumosTarjetaBO ultimosConsumosBO;

    /** The consumoPendienteConfirmacion bo. */
    @Mock
    private ConsumoPendienteConfirmacionBo consumosPendientesBO;
    
    /** The selector Y cabecera BO. */
    @Mock
    SelectorYCabeceraBO selectorYCabeceraBO;
    
    @Mock
    private PagosPendientesBO pagosPendientesBO;
        
    /** The casuistica. */
    @InjectMocks
    @Spy
    private CasuisticaErrorTarjetasBO casuistica = new CasuisticaErrorTarjetasBOImpl();

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private UltimosConsumosManager ultimosConsumosManager = new UltimosConsumosManagerImpl();
    
    /** The mensaje. */
    Mensaje mensaje = new Mensaje();

    /**
     * Inits the mocks.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Before
    public void init() throws BusinessException {
        mensaje.setMensaje("Mensaje");
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        identificacionCuenta.setNroCuentaProducto("4660570052763245");
        identificacionCuenta.setNroSucursal("0000");
        Mockito.when(sesionCliente.getCliente()).thenReturn(obtenerCliente());
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(obtenerCuenta("00004660570052763245"));
        Mockito.when(ultimosConsumosBO.obtenerUltimosConsumos(obtenerCuenta("00004660570052763245")))
                .thenReturn(obtenerRespuesta(completarInfoUltimosConsumos(), EstadoRespuesta.OK, false));
        Mockito.when(consumosPendientesBO.obtenerConsumoPendiente(obtenerCuenta("00004660570052763245")))
                .thenReturn(obtenerRespuesta(completarInfoConsumoPendiente(), EstadoRespuesta.OK, false));
        Mockito.when(pagosPendientesBO.obtenerDatosUnaTarjeta(Matchers.any(Cliente.class), Matchers.any(Cuenta.class))).thenReturn(obtenerDatosTarjeta());
    }

    private DatosTarjeta obtenerDatosTarjeta() {
        DatosTarjeta datosTarjeta = new DatosTarjeta();
        datosTarjeta.setTipoPago("04");
        return datosTarjeta;
    }

    /**
     * Obtener ultimos consumos ok test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerUltimosConsumosOkTest() throws BusinessException, DAOException, ParseadorVisaException {
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(obtenerCuenta("00004660570052763245"));
        Mockito.when(ultimosConsumosBO.obtenerUltimosConsumos(Matchers.any(Cuenta.class)))
                .thenReturn(obtenerRespuesta(completarInfoUltimosConsumos(), EstadoRespuesta.OK, false));
        Mockito.when(consumosPendientesBO.obtenerConsumoPendiente(Matchers.any(Cuenta.class)))
                .thenReturn(obtenerRespuesta(completarInfoConsumoPendiente(), EstadoRespuesta.OK, false));
        Mockito.when(
                ultimosConsumosBO.generarUltimosConsumosDTO(Matchers.any(ArrayList.class), Matchers.any(Cuenta.class)))
                .thenReturn( completarInfoUltimosConsumos());

        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        identificacionCuenta.setNroCuentaProducto("4660570052763245");
        identificacionCuenta.setNroSucursal("0000");
        
        Respuesta<ConsumosView> res = ultimosConsumosManager.obtenerUltimosConsumos(identificacionCuenta);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener ultimos consumos business exception test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    // @Test
    public void obtenerUltimosConsumosBusinessExceptionTest()
            throws BusinessException, DAOException, ParseadorVisaException {
        Respuesta<UltimosConsumosDTO> res = ultimosConsumosBO
                .obtenerUltimosConsumos(obtenerCuenta("00004660570052763245"));
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Crear respuesta error test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    // @Test
    public void crearRespuestaErrorTest() throws BusinessException, DAOException, ParseadorVisaException {
        Mockito.when(ultimosConsumosBO.obtenerUltimosConsumos(Matchers.any(Cuenta.class)))
                .thenReturn(obtenerRespuesta(null, EstadoRespuesta.ERROR, false));

        Respuesta<UltimosConsumosDTO> res = ultimosConsumosBO.obtenerUltimosConsumos(new Cuenta());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Obtener respuesta de WS tarjeta entity null test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     * @throws ParseadorVisaException
     *             the parseador visa exception
     */
    // @Test
    public void obtenerRespuestaSinConsumosTest() throws BusinessException, DAOException, ParseadorVisaException {

        Mockito.when(ultimosConsumosBO.obtenerUltimosConsumos(Matchers.any(Cuenta.class)))
                .thenReturn(obtenerRespuesta(null, EstadoRespuesta.ERROR, false));
        Respuesta<UltimosConsumosDTO> res = ultimosConsumosBO.obtenerUltimosConsumos(new Cuenta());
        Assert.assertEquals("ERROR_SIN_CONSUMOS", res.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * No tiene ULTIMOS_CONSUMOS. Falla CONSUMOS_PENDIENTES. Se muestra la
     * grilla con mensaje de que no tiene consumos y mensaje de error. 1197 y
     * 1203
     * 
     */
    // @Test
    public void crearRespuestaErrorSinConsumosYFallaPendientes() {
        ConsumosView view = new ConsumosView(new ArrayList<ConsumoTarjetaView>(), null, EstadoRespuesta.ERROR, false, false);

        Respuesta<ConsumosView> rta = new Respuesta<ConsumosView>();
        rta.setRespuesta(view);
        rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        rta.setItemMensajeRespuesta(obtenerItemsSinConsumosYFallaPendientes());
        rta.setRespuestaVacia(true);

        Mensaje msg1 = new Mensaje();
        msg1.setMensaje(SIN_ULTIMOS_CONSUMOS);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo("1197")).thenReturn(msg1);

        mensaje.setMensaje(ERROR_SERVICIO_CONSUMOS_PENDIENTES);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo("1203")).thenReturn(mensaje);

        Respuesta<ConsumosView> res = ultimosConsumosManager.obtenerUltimosConsumos("221-579806/6");
        Assert.assertEquals(res, rta);

        generarJson(res, "-- Grilla ERROR (SIN_CONSUMOS_FALLA_PENDIENTES) --");
    }

    /**
     * Crear respuesta error sin consumos. No tiene Consumos. No tiene
     * pendientes. Se muestra la grilla con mensaje de que no tiene consumos.
     *
     */
    // @Test
    public void crearRespuestaErrorSinConsumos() {
        ConsumosView view = new ConsumosView(new ArrayList<ConsumoTarjetaView>(), null, EstadoRespuesta.OK, false, false);
        Respuesta<ConsumosView> rta = new Respuesta<ConsumosView>();
        rta.setRespuesta(view);
        rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        rta.setItemMensajeRespuesta(obtenerItemsSinConsumos());
        rta.setRespuestaVacia(true);

        mensaje.setMensaje(SIN_ULTIMOS_CONSUMOS);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<ConsumosView> respuesta = ultimosConsumosManager.obtenerUltimosConsumos("221-579806/6");
        Assert.assertEquals(respuesta, rta);

        generarJson(respuesta, "-- Grilla ERROR (SIN_CONSUMOS_SIN_PENDIENTES) --");
    }

    /**
     * Crear respuesta warning OK consumos FALLA pendientes.
     */
    // @Test
    public void crearRespuestaWarningConConsumosErrorEnPendientes() {
        ConsumosView view = obtenerConsumosView();
        view.setEstadoRespuestaConsumosPendientes(EstadoRespuesta.ERROR);
        view.setMuestraTarjetasConCabecera(true);
        Respuesta<ConsumosView> rta = new Respuesta<ConsumosView>();
        rta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        rta.setRespuesta(view);
        rta.setItemMensajeRespuesta(obtenerItemsErrorPendientes());
        rta.setRespuestaVacia(false);

        mensaje.setMensaje(ERROR_SERVICIO_CONSUMOS_PENDIENTES);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<ConsumosView> respuesta = ultimosConsumosManager.obtenerUltimosConsumos("221-579806/6");
        Assert.assertEquals(respuesta, rta);

        generarJson(respuesta, "-- Grilla WARNING (OK_CONSUMOS_FALLA_PENDIENTES) --");
    }

    /**
     * Crear respuesta warning FALLA consumos, OK pendientes.
     */
    // @Test
    public void crearRespuestaWarningErrorConsumosConConsumosPendientes() {
        ConsumosView view = obtenerConsumosView();
        view.setEstadoRespuestaConsumos(EstadoRespuesta.ERROR);
        view.setEstadoRespuestaConsumosPendientes(EstadoRespuesta.OK);
        view.setMuestraTarjetasConCabecera(true);
        Respuesta<ConsumosView> rta = new Respuesta<ConsumosView>();
        rta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        rta.setRespuesta(view);
        rta.setItemMensajeRespuesta(obtenerItemsErrorUltimosConsumos());
        rta.setRespuestaVacia(false);

        mensaje.setMensaje(ERROR_SERVICIO_ULTIMOS_CONSUMOS);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);

        Respuesta<ConsumosView> respuesta = ultimosConsumosManager.obtenerUltimosConsumos("221-579806/6");
        Assert.assertEquals(respuesta, rta);

        generarJson(respuesta, "-- Grilla WARNING (FALLA_CONSUMOS_OK_PENDIENTES) --");
    }

    /**
     * Crear respuesta error.
     *
     * @return the respuesta
     */
    // @Test
    public void crearRespuestaErrorGenerico() {
        ConsumosView view = new ConsumosView(new ArrayList<ConsumoTarjetaView>(), null, EstadoRespuesta.ERROR, false, false);
        Respuesta<ConsumosView> rta = new Respuesta<ConsumosView>();
        rta.setRespuesta(view);
        rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        rta.setItemMensajeRespuesta(obtenerItemsErrorGenerico());
        rta.setRespuestaVacia(true);

        mensaje.setMensaje(ERROR_GENERICO_ULTIMOS_CONSUMOS);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);

        Respuesta<ConsumosView> respuesta = ultimosConsumosManager.obtenerUltimosConsumos("221-579806/6");
        Assert.assertEquals(respuesta, rta);

        generarJson(respuesta, "-- Grilla ERROR (FALLA_CONSUMOS_FALLA_PENDIENTES) --");
    }

    /**
     * Obtener items error generico.
     *
     * @return the list
     */
    private List<ItemMensajeRespuesta> obtenerItemsErrorGenerico() {
        List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setMensaje(ERROR_GENERICO_ULTIMOS_CONSUMOS);
        item.setTag(ErrorTarjetasEnum.ERROR_GENERICO_ULTIMOS_CONSUMOS.getTag().getDescripcionTag());
        item.setTipoError(ErrorTarjetasEnum.ERROR_GENERICO_ULTIMOS_CONSUMOS.getTipoError().getDescripcion());
        items.add(item);
        return items;
    }

    /**
     * ErrorTarjetasEnum.ERROR_SERVICIO_ULTIMOS_CONSUMOS
     *
     * @return the list
     */
    private List<ItemMensajeRespuesta> obtenerItemsErrorUltimosConsumos() {
        List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setMensaje(ERROR_SERVICIO_ULTIMOS_CONSUMOS);
        item.setTag(ErrorTarjetasEnum.ERROR_SERVICIO_ULTIMOS_CONSUMOS.getTag().getDescripcionTag());
        item.setTipoError(ErrorTarjetasEnum.ERROR_SERVICIO_ULTIMOS_CONSUMOS.getTipoError().getDescripcion());
        items.add(item);
        return items;
    }

    /**
     * Obtener consumos view.
     *
     * @return the consumos view
     */
    private ConsumosView obtenerConsumosView() {
        ConsumosView res = new ConsumosView();
        List<ConsumoTarjetaView> consumosTarjetas = new ArrayList<ConsumoTarjetaView>();
        ConsumoTarjetaView consumoTarjetaView = obtenerConsumoTarjetaView();
        consumosTarjetas.add(consumoTarjetaView);
        res.setConsumosTarjetas(consumosTarjetas);
        res.setEstadoRespuestaConsumos(EstadoRespuesta.OK);
        res.setEstadoRespuestaConsumosPendientes(EstadoRespuesta.OK);
        res.setMuestraTarjetasConCabecera(true);
        return res;
    }

    /**
     * Obtener consumo tarjeta view.
     *
     * @return the consumo tarjeta view
     */
    private ConsumoTarjetaView obtenerConsumoTarjetaView() {
        ConsumoTarjetaView res = new ConsumoTarjetaView();
        res.setConsumoDolares(new BigDecimal(1200));
        res.setConsumoPesos(new BigDecimal(1200));
        res.setHasConsumoDolaresCero(false);
        res.setHasConsumoPesosCero(false);
        res.setHasError(false);
        res.setIsAdicional(false);
        res.setIsTitular(true);
        res.setLineas(obtenerLineas());
        res.setMarca("VISA");
        res.setNombreAdicional("Marcos");
        res.setNumero("221-579806/6");
        return res;
    }

    /**
     * ErrorTarjetasEnum.ERROR_SERVICIO_CONSUMOS_PENDIENTES
     *
     * @return the list
     */
    private List<ItemMensajeRespuesta> obtenerItemsErrorPendientes() {
        List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setMensaje(ERROR_SERVICIO_CONSUMOS_PENDIENTES);
        item.setTag(ErrorTarjetasEnum.ERROR_SERVICIO_CONSUMOS_PENDIENTES.getTag().getDescripcionTag());
        item.setTipoError(ErrorTarjetasEnum.ERROR_SERVICIO_CONSUMOS_PENDIENTES.getTipoError().getDescripcion());
        items.add(item);
        return items;
    }

    /**
     * ErrorTarjetasEnum.SIN_ULTIMOS_CONSUMOS
     *
     * @return the list
     */
    private List<ItemMensajeRespuesta> obtenerItemsSinConsumos() {
        List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setMensaje(SIN_ULTIMOS_CONSUMOS);
        item.setTag(ErrorTarjetasEnum.SIN_CONSUMOS.getTag().getDescripcionTag());
        item.setTipoError(ErrorTarjetasEnum.SIN_CONSUMOS.getTipoError().getDescripcion());
        items.add(item);
        return items;
    }

    /**
     * Obtener items sin consumos Y falla pendientes.
     *
     * @return the list
     */
    private List<ItemMensajeRespuesta> obtenerItemsSinConsumosYFallaPendientes() {
        List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setMensaje(SIN_ULTIMOS_CONSUMOS);
        item.setTag(ErrorTarjetasEnum.SIN_CONSUMOS.getTag().getDescripcionTag());
        item.setTipoError(ErrorTarjetasEnum.SIN_CONSUMOS.getTipoError().getDescripcion());
        items.add(item);

        ItemMensajeRespuesta item2 = new ItemMensajeRespuesta();
        item2.setMensaje(ERROR_SERVICIO_CONSUMOS_PENDIENTES);
        item2.setTag(ErrorTarjetasEnum.ERROR_SERVICIO_CONSUMOS_PENDIENTES.getTag().getDescripcionTag());
        item2.setTipoError(ErrorTarjetasEnum.ERROR_SERVICIO_CONSUMOS_PENDIENTES.getTipoError().getDescripcion());
        items.add(item2);

        return items;
    }

    /**
     * Obtener cuenta.
     *
     * @param numero
     *            the numero
     * @return the cuenta
     */
    private Cuenta obtenerCuenta(String numero) {
        Cuenta cuenta = new Cuenta();
        cuenta.setNroTarjetaCredito(numero);
        cuenta.setTipoCuenta("7");
        return cuenta;
    }

    /**
     * Completa la informacion del cliente.
     *
     * @return the cliente
     */
    public static Cliente obtenerCliente() {
        return ClienteMock.completarInfoCliente();
    }

    /**
     * Obtener resuesta.
     *
     * @param dto
     *            the dto
     * @param estado
     *            the estado
     * @param respuestaVacia
     *            the respuesta vacia
     * @return the respuesta
     */
    public Respuesta<UltimosConsumosDTO> obtenerRespuesta(UltimosConsumosDTO dto, EstadoRespuesta estado,
            boolean respuestaVacia) {
        Respuesta<UltimosConsumosDTO> respuesta = new Respuesta<UltimosConsumosDTO>();
        respuesta.setEstadoRespuesta(estado);
        respuesta.setRespuestaVacia(respuestaVacia);
        respuesta.setRespuesta(dto);
        return respuesta;
    }

    /**
     * Obtener retorno WS.
     *
     * @return the retorno WS
     */
    public UltimosConsumosDTO completarInfoUltimosConsumos() {
        UltimosConsumosDTO dto = new UltimosConsumosDTO();

        Boolean muestraTarjetasConCabecera = null;
        dto.setMuestraTarjetasConCabecera(muestraTarjetasConCabecera);
        List<ConsumoTarjetaDTO> ultimosConsumos = new ArrayList<ConsumoTarjetaDTO>();
        ultimosConsumos.add(obtenerConsumoTarjetaDTO("AMEX", "XXXX-76324", null, "19851.12", "9.99", true, false, false,
                false, false, 1, "4660570052763245"));
        ultimosConsumos.add(obtenerConsumoTarjetaDTO("AMEX", "XXXX-52346", null, "19851.12", "9.99", true, false, false,
                false, false, 1, "4660570052763245"));
        ultimosConsumos.add(obtenerConsumoTarjetaDTO("AMEX", "XXXX-76323", "Jorge J Perez", "19851.12", "9.99", true,
                false, false, false, false, 2, "4660570052763245"));
        ultimosConsumos.add(obtenerConsumoTarjetaDTO("AMEX", "XXXX-52345", null, "19851.12", "9.99", true, false, false,
                false, false, 3, "4660570052763245"));

        dto.setUltimosConsumos(ultimosConsumos);

        return dto;
    }

    /**
     * Completar info consumo pendiente.
     *
     * @return the ultimos consumos DTO
     */
    private UltimosConsumosDTO completarInfoConsumoPendiente() {
        UltimosConsumosDTO dto = new UltimosConsumosDTO();

        Boolean muestraTarjetasConCabecera = null;
        dto.setMuestraTarjetasConCabecera(muestraTarjetasConCabecera);
        List<ConsumoTarjetaDTO> ultimosConsumos = new ArrayList<ConsumoTarjetaDTO>();
        ultimosConsumos.add(obtenerConsumoTarjetaDTO("AMEX", "XXXX-76324", null, "3434.12", "3434.99", true, false,
                false, false, false, 1, "4660570052763245"));
        ultimosConsumos.add(obtenerConsumoTarjetaDTO("AMEX", "XXXX-52346", null, "19851.12", "9.99", true, false, false,
                false, false, 1, "4660570052763245"));

        dto.setUltimosConsumos(ultimosConsumos);

        return dto;
    }

    /**
     * Obtener consumo tarjeta DTO.
     *
     * @param marca
     *            the marca
     * @param numero
     *            the numero
     * @param nombreAdicional
     *            the nombre adicional
     * @param consumoPesos
     *            the consumo pesos
     * @param consumoDolares
     *            the consumo dolares
     * @param isTitular
     *            the is titular
     * @param isAdicional
     *            the is adicional
     * @param hasConsumoPesosCero
     *            the has consumo pesos cero
     * @param hasConsumoDolaresCero
     *            the has consumo dolares cero
     * @param hasError
     *            the has error
     * @param prioridad
     *            the prioridad
     * @param nroTarjeta
     *            the nro tarjeta
     * @return the consumo tarjeta DTO
     */
    private ConsumoTarjetaDTO obtenerConsumoTarjetaDTO(String marca, String numero, String nombreAdicional,
            String consumoPesos, String consumoDolares, boolean isTitular, boolean isAdicional,
            boolean hasConsumoPesosCero, boolean hasConsumoDolaresCero, boolean hasError, Integer prioridad,
            String nroTarjeta) {
        ConsumoTarjetaDTO consumoTarjetaDTO = new ConsumoTarjetaDTO();
        consumoTarjetaDTO.setConsumoDolares(new BigDecimal(consumoDolares));
        consumoTarjetaDTO.setConsumoPesos(new BigDecimal(consumoPesos));
        consumoTarjetaDTO.setConsumoDolaresPendientes(new BigDecimal(consumoDolares));
        consumoTarjetaDTO.setConsumoPesosPendientes(new BigDecimal(consumoPesos));
        consumoTarjetaDTO.setFechaDesde(new Date());
        consumoTarjetaDTO.setHasConsumoDolaresCero(hasConsumoDolaresCero);
        consumoTarjetaDTO.setHasConsumoPesosCero(hasConsumoDolaresCero);
        consumoTarjetaDTO.setHasError(hasError);
        consumoTarjetaDTO.setIsAdicional(isAdicional);
        consumoTarjetaDTO.setIsTitular(isTitular);
        consumoTarjetaDTO.setLineas(obtenerLineas(nroTarjeta));
        consumoTarjetaDTO.setMarca(marca);
        consumoTarjetaDTO.setNombreAdicional(nombreAdicional);
        consumoTarjetaDTO.setNumero(numero);
        consumoTarjetaDTO.setPrioridad(prioridad);
        return consumoTarjetaDTO;
    }

    /**
     * Obtener lineas.
     *
     * @param nroTarjeta
     *            the nro tarjeta
     * @return the list
     */
    private List<LineaDetalleConsumoTarjetaDTO> obtenerLineas(String nroTarjeta) {
        LineaDetalleConsumoTarjetaDTO linea = new LineaDetalleConsumoTarjetaDTO();
        linea.setCodigoEstablecimiento("0025705856");
        linea.setConsumoPesos(true);
        linea.setConsumoDolares(false);
        linea.setCuota("01/06");
        linea.setCuotasCanceladas(1);
        linea.setCuotasTotales(6);
        linea.setDescripcion("Wanama/cook");
        linea.setFecha(new Date());
        linea.setImporteDolares(new BigDecimal(0));
        linea.setImportePesos(new BigDecimal(119.85));
        linea.setNroComprobante("4660570052763245");
        linea.setNroReferencia("5878767   ");
        linea.setNroTarjeta(nroTarjeta);
        linea.setTieneCuota(true);
        linea.setTipoConsumo(TipoConsumoTarjeta.ULTIMO_CONSUMO);
        List<LineaDetalleConsumoTarjetaDTO> lineas = new ArrayList<LineaDetalleConsumoTarjetaDTO>();
        lineas.add(linea);
        return lineas;
    }

    /**
     * Obtener lineas.
     *
     * @return the list
     */
    private List<LineaDetalleConsumoTarjetaView> obtenerLineas() {
        List<LineaDetalleConsumoTarjetaView> lineas = new ArrayList<LineaDetalleConsumoTarjetaView>();
        LineaDetalleConsumoTarjetaView linea1 = new LineaDetalleConsumoTarjetaView();
        linea1.setCodigoEstablecimiento("2588");
        linea1.setConsumoDolares(false);
        linea1.setConsumoPesos(true);
        linea1.setCuota("6");
        linea1.setCuotasCanceladas(4);
        linea1.setCuotasTotales(12);
        linea1.setDescripcion("Colombraro");
        linea1.setFecha(new Date());
        linea1.setImporteDolares(null);
        linea1.setImportePesos(new BigDecimal(1000));
        linea1.setComprobante("0000001234");
        linea1.setNroReferencia("0000001234");
        linea1.setNroTarjeta("221-579806/6");
        linea1.setTieneCuota(true);
        lineas.add(linea1);

        LineaDetalleConsumoTarjetaView linea2 = new LineaDetalleConsumoTarjetaView();
        linea2.setCodigoEstablecimiento("2567");
        linea2.setConsumoDolares(false);
        linea2.setConsumoPesos(true);
        linea2.setCuota("3");
        linea2.setCuotasCanceladas(4);
        linea2.setCuotasTotales(7);
        linea2.setDescripcion("Prune");
        linea2.setFecha(new Date());
        linea2.setImporteDolares(null);
        linea2.setImportePesos(new BigDecimal(2000));
        linea2.setComprobante("0000001234");
        linea2.setNroReferencia("0000001234");
        linea2.setNroTarjeta("221-579806/6");
        linea2.setTieneCuota(true);
        lineas.add(linea2);
        return lineas;
    }

    /**
     * Generar json.
     *
     * @param respuesta
     *            the respuesta
     * @param titulo
     *            the titulo
     */
    private void generarJson(Respuesta<ConsumosView> respuesta, String titulo) {
        Gson gson = new Gson();
        String json = gson.toJson(respuesta);
        Assert.assertNotNull(json);
        System.out.println(titulo);
        System.out.println(json);
        System.out.println(" ");
    }

    /**
     * Exportar ultimos consumos ok test.
     */
    @Test
    public void exportarUltimosConsumosOkTest() {
        Respuesta<Reporte> resReporte = new Respuesta<Reporte>();
        resReporte.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(sesionTarjetas.obtenerConsumoTarjetas()).thenReturn(new ConsumoTarjetasDTO());
        Mockito.when(ultimosConsumosBO.obtenerReporte(Matchers.any(Object.class), Matchers.anyString(),
                Matchers.any(Cliente.class))).thenReturn(resReporte);
        Respuesta<Reporte> res = ultimosConsumosManager.exportarUltimosConsumos();
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Ver detalle ultimos consumos test.
     */
    @Test
    public void verDetalleUltimosConsumosTest() {
        Respuesta<Void> toRet = new Respuesta<Void>();
        toRet.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<Void> resVoid = ultimosConsumosManager.verDetalleUltimosConsumos();
        Assert.assertEquals(EstadoRespuesta.OK, resVoid.getEstadoRespuesta());
    }

    /**
     * Obtener ultimos consumos string ok test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerUltimosConsumosStringOkTest() throws BusinessException {
        Respuesta<UltimosConsumosDTO> resUltimosConsumosDTO = new Respuesta<UltimosConsumosDTO>();
        resUltimosConsumosDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        resUltimosConsumosDTO.setRespuesta(new UltimosConsumosDTO());
        resUltimosConsumosDTO.getRespuesta().setUltimosConsumos(new ArrayList<ConsumoTarjetaDTO>());

        UltimosConsumosDTO ultimosConsumosDTO = new UltimosConsumosDTO();
        ultimosConsumosDTO.setUltimosConsumos(new ArrayList<ConsumoTarjetaDTO>());

        Mockito.when(selectorYCabeceraBO.obtenerMarcaDeTarjeta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenReturn("");
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(obtenerCuenta("12345"));
        Mockito.when(ultimosConsumosBO.obtenerUltimosConsumos(Matchers.any(Cuenta.class)))
                .thenReturn(resUltimosConsumosDTO);
        Mockito.when(consumosPendientesBO.obtenerConsumoPendiente(Matchers.any(Cuenta.class))).thenReturn(resUltimosConsumosDTO);
        Mockito.when(
                ultimosConsumosBO.generarUltimosConsumosDTO(Matchers.any(ArrayList.class), Matchers.any(Cuenta.class)))
                .thenReturn(ultimosConsumosDTO);
        Mockito.when(sesionParametros.getTarjetasView()).thenReturn(new TarjetasView());
        Respuesta<ConsumosView> resConsumos = ultimosConsumosManager.obtenerUltimosConsumos("12-123124124-1243123");
        Assert.assertEquals(EstadoRespuesta.OK, resConsumos.getEstadoRespuesta());
    }

    /**
     * Unificar lista tarjetas ok test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void unificarListaTarjetasOkTest() throws BusinessException {
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        Respuesta<UltimosConsumosDTO> resUltimosConsumosDTO = new Respuesta<UltimosConsumosDTO>();
        resUltimosConsumosDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        resUltimosConsumosDTO.setRespuesta(new UltimosConsumosDTO());
        resUltimosConsumosDTO.getRespuesta().setUltimosConsumos(new ArrayList<ConsumoTarjetaDTO>());

        ConsumoTarjetaDTO consumoTarjetaDTO = new ConsumoTarjetaDTO();
        consumoTarjetaDTO.setNumero("123");
        consumoTarjetaDTO.setConsumoPesos(new BigDecimal(12));
        consumoTarjetaDTO.setConsumoDolares(new BigDecimal(12));

        UltimosConsumosDTO ultimosConsumosDTO = new UltimosConsumosDTO();
        ultimosConsumosDTO.setUltimosConsumos(new ArrayList<ConsumoTarjetaDTO>());
        ultimosConsumosDTO.getUltimosConsumos().add(consumoTarjetaDTO);

        resUltimosConsumosDTO.setRespuesta(ultimosConsumosDTO);

        Mockito.when(selectorYCabeceraBO.obtenerMarcaDeTarjeta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenReturn("");
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(obtenerCuenta("12345"));
        Mockito.when(ultimosConsumosBO.obtenerUltimosConsumos(Matchers.any(Cuenta.class)))
                .thenReturn(resUltimosConsumosDTO);
        Mockito.when(consumosPendientesBO.obtenerConsumoPendiente(Matchers.any(Cuenta.class))).thenReturn(resUltimosConsumosDTO);
        Mockito.when(
                ultimosConsumosBO.generarUltimosConsumosDTO(Matchers.any(ArrayList.class), Matchers.any(Cuenta.class)))
                .thenReturn(ultimosConsumosDTO);

        Respuesta<ConsumosView> resConsumos = ultimosConsumosManager.obtenerUltimosConsumos(identificacionCuenta);
        Assert.assertEquals(EstadoRespuesta.OK, resConsumos.getEstadoRespuesta());
    }

    /**
     * CASO OK: Grilla OK (OK_CONSUMOS_OK_PENDIENTES).
     *
     * @throws BusinessException
     *             the business exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void casoOK_grilla_OK() throws BusinessException {
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        Respuesta<UltimosConsumosDTO> resUltimosConsumosDTO = new Respuesta<UltimosConsumosDTO>();
        resUltimosConsumosDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        resUltimosConsumosDTO.setRespuesta(new UltimosConsumosDTO());
        resUltimosConsumosDTO.getRespuesta().setUltimosConsumos(new ArrayList<ConsumoTarjetaDTO>());

        ConsumoTarjetaDTO consumoTarjetaDTO = new ConsumoTarjetaDTO();
        consumoTarjetaDTO.setNumero("123");
        consumoTarjetaDTO.setConsumoPesos(new BigDecimal(12));
        consumoTarjetaDTO.setConsumoDolares(new BigDecimal(12));

        UltimosConsumosDTO ultimosConsumosDTO = new UltimosConsumosDTO();
        ultimosConsumosDTO.setUltimosConsumos(new ArrayList<ConsumoTarjetaDTO>());
        ultimosConsumosDTO.getUltimosConsumos().add(consumoTarjetaDTO);

        resUltimosConsumosDTO.setRespuesta(ultimosConsumosDTO);

        Mockito.when(selectorYCabeceraBO.obtenerMarcaDeTarjeta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenReturn("");
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(obtenerCuenta("2345"));
        Mockito.when(ultimosConsumosBO.obtenerUltimosConsumos(Matchers.any(Cuenta.class)))
                .thenReturn(resUltimosConsumosDTO);
        Mockito.when(consumosPendientesBO.obtenerConsumoPendiente(Matchers.any(Cuenta.class))).thenReturn(resUltimosConsumosDTO);
        Mockito.when(
                ultimosConsumosBO.generarUltimosConsumosDTO(Matchers.any(ArrayList.class), Matchers.any(Cuenta.class)))
                .thenReturn(ultimosConsumosDTO);

        Respuesta<ConsumosView> resConsumos = ultimosConsumosManager.obtenerUltimosConsumos(identificacionCuenta);
        Assert.assertEquals(EstadoRespuesta.OK, resConsumos.getEstadoRespuesta());
    }

    /**
     * CASO 1: Grilla WARNING (SIN_CONSUMOS_FALLA_PENDIENTES).
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void casuistica_Caso1_Error_SINConsumoFALLAPendientes() throws BusinessException {
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        Mockito.when(selectorYCabeceraBO.obtenerMarcaDeTarjeta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenReturn("");
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(new Cuenta());
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(ErrorTarjetasEnum.SIN_CONSUMOS.getCodigo().toString()))
                .thenReturn(crearMensaje(ErrorTarjetasEnum.SIN_CONSUMOS.getCodigo().toString(), SIN_ULTIMOS_CONSUMOS));

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class)))
                .thenReturn(crearMensaje(ErrorTarjetasEnum.ERROR_SERVICIO_CONSUMOS_PENDIENTES.getCodigo().toString(),
                        ERROR_SERVICIO_CONSUMOS_PENDIENTES));
        Respuesta<UltimosConsumosDTO> rtaConsumosDTO = crearRespuestaSinConsumos();
        Respuesta<UltimosConsumosDTO> rtaPendientesDTO = crearRespuestaFalla_Pendientes();

        Mockito.when(ultimosConsumosBO.obtenerUltimosConsumos(Matchers.any(Cuenta.class))).thenReturn(rtaConsumosDTO);
        Mockito.when(consumosPendientesBO.obtenerConsumoPendiente(Matchers.any(Cuenta.class))).thenReturn(rtaPendientesDTO);

        Respuesta<ConsumosView> resConsumos = ultimosConsumosManager.obtenerUltimosConsumos(identificacionCuenta);
        Assert.assertEquals(EstadoRespuesta.ERROR, resConsumos.getEstadoRespuesta());
    }

    /**
     * CASO 2: Grilla WARNING (SIN_CONSUMOS_SIN_PENDIENTES).
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void casuistica_Caso2_Warning_SINConsumoSINPendientes() throws BusinessException {
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        Mockito.when(selectorYCabeceraBO.obtenerMarcaDeTarjeta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenReturn("");
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(new Cuenta());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class)))
                .thenReturn(crearMensaje(ErrorTarjetasEnum.SIN_CONSUMOS.getCodigo().toString(), SIN_ULTIMOS_CONSUMOS));
        Respuesta<UltimosConsumosDTO> rtaConsumosDTO = crearRespuestaSinConsumos();
        Respuesta<UltimosConsumosDTO> rtaPendientesDTO = crearRespuestaSinConsumos();

        Mockito.when(ultimosConsumosBO.obtenerUltimosConsumos(Matchers.any(Cuenta.class))).thenReturn(rtaConsumosDTO);
        Mockito.when(consumosPendientesBO.obtenerConsumoPendiente(Matchers.any(Cuenta.class))).thenReturn(rtaPendientesDTO);

        Respuesta<ConsumosView> resConsumos = ultimosConsumosManager.obtenerUltimosConsumos(identificacionCuenta);
        Assert.assertEquals(EstadoRespuesta.ERROR, resConsumos.getEstadoRespuesta());
    }

    /**
     * CASO 3: Grilla WARNING (OK_CONSUMOS_FALLA_PENDIENTES).
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void casuistica_Caso3_Warning_OKConsumoFALLAPendientes() throws BusinessException {
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        Mockito.when(selectorYCabeceraBO.obtenerMarcaDeTarjeta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenReturn("");
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(new Cuenta());

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class)))
                .thenReturn(crearMensaje(ErrorTarjetasEnum.ERROR_SERVICIO_CONSUMOS_PENDIENTES.getCodigo().toString(),
                        ERROR_SERVICIO_CONSUMOS_PENDIENTES));

        Respuesta<UltimosConsumosDTO> rtaConsumosDTO = crearRespuestaOKConsumos();
        Respuesta<UltimosConsumosDTO> rtaPendientesDTO = crearRespuestaFalla_Pendientes();

        Mockito.when(ultimosConsumosBO.obtenerUltimosConsumos(Matchers.any(Cuenta.class))).thenReturn(rtaConsumosDTO);
        Mockito.when(consumosPendientesBO.obtenerConsumoPendiente(Matchers.any(Cuenta.class))).thenReturn(rtaPendientesDTO);

        Respuesta<ConsumosView> resConsumos = ultimosConsumosManager.obtenerUltimosConsumos(identificacionCuenta);
        Assert.assertEquals(EstadoRespuesta.WARNING, resConsumos.getEstadoRespuesta());
    }

    /**
     * CASO 4: Grilla WARNING (FALLA_CONSUMOS_OK_PENDIENTES).
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void casuistica_Caso4_Warning_FallaConsumoOkPendientes() throws BusinessException {
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        Mockito.when(selectorYCabeceraBO.obtenerMarcaDeTarjeta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenReturn("");
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(new Cuenta());

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class)))
                .thenReturn(crearMensaje(ErrorTarjetasEnum.ERROR_SERVICIO_ULTIMOS_CONSUMOS.getCodigo().toString(),
                        ERROR_SERVICIO_ULTIMOS_CONSUMOS));

        Respuesta<UltimosConsumosDTO> rtaConsumosDTO = crearRespuestaFalla_Consumos();
        Respuesta<UltimosConsumosDTO> rtaPendientesDTO = crearRespuestaOk_Pendientes();

        Mockito.when(ultimosConsumosBO.obtenerUltimosConsumos(Matchers.any(Cuenta.class))).thenReturn(rtaConsumosDTO);
        Mockito.when(consumosPendientesBO.obtenerConsumoPendiente(Matchers.any(Cuenta.class))).thenReturn(rtaPendientesDTO);

        Respuesta<ConsumosView> resConsumos = ultimosConsumosManager.obtenerUltimosConsumos(identificacionCuenta);
        Assert.assertEquals(EstadoRespuesta.WARNING, resConsumos.getEstadoRespuesta());

    }

    /**
     * CASO 5: Grilla ERROR (FALLA_CONSUMOS_FALLA_PENDIENTES).
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void casuistica_Caso5_Warning_FallaConsumoFallaPendientes() throws BusinessException {
        IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();
        Mockito.when(selectorYCabeceraBO.obtenerMarcaDeTarjeta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenReturn("");
        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(new Cuenta());
        Mockito.when(mensajeDAO
                .obtenerMensajePorCodigo(ErrorTarjetasEnum.ERROR_SERVICIO_ULTIMOS_CONSUMOS.getCodigo().toString()))
                .thenReturn(crearMensaje(ErrorTarjetasEnum.ERROR_SERVICIO_ULTIMOS_CONSUMOS.getCodigo().toString(),
                        ERROR_SERVICIO_ULTIMOS_CONSUMOS));

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class)))
                .thenReturn(crearMensaje(ErrorTarjetasEnum.ERROR_SERVICIO_CONSUMOS_PENDIENTES.getCodigo().toString(),
                        ERROR_SERVICIO_CONSUMOS_PENDIENTES));

        Respuesta<UltimosConsumosDTO> rtaConsumosDTO = crearRespuestaFalla_Consumos();
        Respuesta<UltimosConsumosDTO> rtaPendientesDTO = crearRespuestaFalla_Pendientes();

        Mockito.when(ultimosConsumosBO.obtenerUltimosConsumos(Matchers.any(Cuenta.class))).thenReturn(rtaConsumosDTO);
        Mockito.when(consumosPendientesBO.obtenerConsumoPendiente(Matchers.any(Cuenta.class))).thenReturn(rtaPendientesDTO);

        Respuesta<ConsumosView> resConsumos = ultimosConsumosManager.obtenerUltimosConsumos(identificacionCuenta);
        Assert.assertEquals(EstadoRespuesta.ERROR, resConsumos.getEstadoRespuesta());

    }

    /**
     * Crear respuesta ok pendientes.
     *
     * @return the respuesta
     */
    private Respuesta<UltimosConsumosDTO> crearRespuestaOk_Pendientes() {
        UltimosConsumosDTO dto = obtenerUltimosConsumosDTO();
        Respuesta<UltimosConsumosDTO> rta = new Respuesta<UltimosConsumosDTO>();
        rta.setEstadoRespuesta(EstadoRespuesta.OK);
        rta.setRespuesta(dto);
        rta.setRespuestaVacia(false);
        return rta;
    }

    /**
     * Crear respuesta OK consumos.
     *
     * @return the respuesta
     */
    private Respuesta<UltimosConsumosDTO> crearRespuestaOKConsumos() {
        UltimosConsumosDTO dto = obtenerUltimosConsumosDTO();
        Respuesta<UltimosConsumosDTO> rta = new Respuesta<UltimosConsumosDTO>();
        rta.setEstadoRespuesta(EstadoRespuesta.OK);
        rta.setRespuesta(dto);
        rta.setItemMensajeRespuesta(obtenerItemsErrorPendientes());
        rta.setRespuestaVacia(false);
        return rta;
    }

    /**
     * Crear respuesta sin consumos.
     *
     * @return the respuesta
     */
    private Respuesta<UltimosConsumosDTO> crearRespuestaSinConsumos() {
        return casuistica.crearRespuestaError(ErrorTarjetasEnum.SIN_CONSUMOS);
    }

    /**
     * Crear respuesta falla consumos.
     *
     * @return the respuesta
     */
    private Respuesta<UltimosConsumosDTO> crearRespuestaFalla_Consumos() {
        return casuistica.crearRespuestaError(ErrorTarjetasEnum.ERROR_SERVICIO_ULTIMOS_CONSUMOS);
    }

    /**
     * Crear respuesta falla pendientes.
     *
     * @return the respuesta
     */
    private Respuesta<UltimosConsumosDTO> crearRespuestaFalla_Pendientes() {
        return casuistica.crearRespuestaError(ErrorTarjetasEnum.ERROR_SERVICIO_CONSUMOS_PENDIENTES);
    }

    /**
     * Crear mensaje.
     *
     * @param codigo
     *            the codigo
     * @param msj
     *            the msj
     * @return the mensaje
     */
    private Mensaje crearMensaje(String codigo, String msj) {
        Mensaje msg = new Mensaje();
        msg.setCodigo(codigo);
        msg.setMensaje(msj);
        return msg;
    }


    /**
     * Obtener consumo tarjeta view.
     *
     * @return the consumo tarjeta view
     */
    private UltimosConsumosDTO obtenerUltimosConsumosDTO() {
        List<ConsumoTarjetaDTO> ultimosConsumos = obtenerListaConsumoTarjetaDTO();
        UltimosConsumosDTO dto = new UltimosConsumosDTO();
        dto.setUltimosConsumos(ultimosConsumos);
        dto.setMuestraTarjetasConCabecera(true);
        return dto;

    }

  

    /**
     * Obtener lista consumo tarjeta DTO.
     *
     * @return the list
     */
    private List<ConsumoTarjetaDTO> obtenerListaConsumoTarjetaDTO() {
        List<ConsumoTarjetaDTO> ultimosConsumos = new ArrayList<ConsumoTarjetaDTO>();
        ultimosConsumos.add(obtenerConsumoTarjetaDTO());
        return ultimosConsumos;
    }

     

    /**
     * Obtener consumo tarjeta DTO.
     *
     * @return the consumo tarjeta DTO
     */
    private ConsumoTarjetaDTO obtenerConsumoTarjetaDTO() {

        ConsumoTarjetaDTO res = new ConsumoTarjetaDTO();
        res.setConsumoDolares(new BigDecimal(1200));
        res.setConsumoPesos(new BigDecimal(1200));
        res.setConsumoDolaresPendientes(new BigDecimal(1200));
        res.setConsumoPesosPendientes(new BigDecimal(1200));
        res.setHasConsumoDolaresCero(false);
        res.setHasConsumoPesosCero(false);
        res.setHasError(false);
        res.setIsAdicional(false);
        res.setIsTitular(true);
        res.setLineas(obtenerLineasDTO());
        res.setMarca("VISA");
        res.setNombreAdicional("Marcos");
        res.setNumero("221-579806/6");
        return res;
    }
    
    /**
     * Obtener lineas.
     *
     * @return the list
     */
    private List<LineaDetalleConsumoTarjetaDTO> obtenerLineasDTO() {
        List<LineaDetalleConsumoTarjetaDTO> lineas = new ArrayList<LineaDetalleConsumoTarjetaDTO>();
        LineaDetalleConsumoTarjetaDTO linea1 = new LineaDetalleConsumoTarjetaDTO();
        linea1.setCodigoEstablecimiento("2588");
        linea1.setConsumoDolares(false);
        linea1.setConsumoPesos(true);
        linea1.setCuota("6");
        linea1.setCuotasCanceladas(4);
        linea1.setCuotasTotales(12);
        linea1.setDescripcion("Colombraro");
        linea1.setFecha(new Date());
        linea1.setImporteDolares(null);
        linea1.setImportePesos(new BigDecimal(1000));
        linea1.setNroComprobante("0000001234");
        linea1.setNroReferencia("0000001234");
        linea1.setNroTarjeta("221-579806/6");
        linea1.setTieneCuota(true);
        lineas.add(linea1);
        return lineas;
    }
}
