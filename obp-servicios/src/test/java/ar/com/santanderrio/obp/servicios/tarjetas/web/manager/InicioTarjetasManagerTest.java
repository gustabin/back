package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import static org.junit.Assert.assertEquals;

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
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.manager.CambioGrupoAfinidadManager;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionTarjetas;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.contracargos.service.ContracargosService;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.GotoLink;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.AliasFavoritoTarjetaBo;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.CasuisticaErrorTarjetasBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.ConsumoPendienteConfirmacionBo;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.LimitesYDisponiblesBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.SelectorYCabeceraBO;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.CasuisticaErrorTarjetasBOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoPendienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ConsumoTarjetaMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DetalleTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DisponiblesYConsumoDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LimitesYDisponiblesDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.LimitesYDisponiblesDTOMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ResumenTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ResumenTarjetaDTOMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimosConsumosDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.view.TarjetaSeleccionada;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl.InicioTarjetasManagerImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.ConsumosView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.LimitesYDisponiblesView;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetasView;

/**
 * The Class InicioTarjetasManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class InicioTarjetasManagerTest {

    /** The sesion tarjetas. */
    @Mock
    private SesionTarjetas sesionTarjetas;

    /** The cliente. */
    @Mock
    private Cliente cliente;

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;

    /** The tarjeta BO. */
    @Mock
    private SelectorYCabeceraBO selectorYCabeceraBO;
    

    /** The ultimos consumos BO. */
    @Mock
    private UltimosConsumosManager ultimosConsumosManager;

    /** The consumos pendientes de confirmacion BO. */
    @Mock
    private ConsumoPendienteConfirmacionBo consumosPendientesDeConfirmacionBO;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The SesionCliente sesionCliente. */
    @Mock
    private SesionCliente sesionCliente;

    @Mock
	private SesionParametros sesionParametros;
    
    /** The Modulo permiso BO. */
    @Mock
    private ModuloPermisoBO moduloPermisoBO;

    /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The legal BO. */
    @Mock
    private LegalBO legalBO;

    /** The casuistica. */
    @Spy
    @InjectMocks
    private CasuisticaErrorTarjetasBO casuistica = new CasuisticaErrorTarjetasBOImpl();

    /** The manager. */
    @InjectMocks
    private InicioTarjetasManager tarjetaManager = new InicioTarjetasManagerImpl();

    /** The AliasFavoritoTarjetaBo aliasFavoritoTarjetaBo. **/
    @Mock
    private AliasFavoritoTarjetaBo aliasFavoritoTarjetaBo;

    /** The LimitesYDisponiblesBO limitesYDisponiblesBO. **/
    @Mock
    private LimitesYDisponiblesBO limitesYDisponiblesBO;
    
    /** The consultar sucursales service. */
    @Mock
	private ConsultarSucursalesBO consultarSucursalesBO;

    /** The mensaje. */
    Mensaje mensaje = new Mensaje();
    
    @Mock
    private CambioGrupoAfinidadManager cambioGrupoAfinidadManager;
    
    @Mock
    private ContracargosService contracargosService;

    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
    }

    /**
     * Obtener cuotas pendientes OK test.
     *
     * @return the tarjetas OK test
     * @throws IllegalAccessException
     *             the illegal access exception
     * @throws BusinessException
     *             the business exception
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    @Test
    public void getTarjetasOKTest() throws IllegalAccessException, BusinessException, TarjetaBOUtilsException {
        Date date = new Date();
        TarjetaSeleccionada tarjetaSeleccionada = new TarjetaSeleccionada();
        Respuesta<TarjetasView> respuestaView = new Respuesta<TarjetasView>();
        respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
        Cuenta cuenta = CuentaMock.completarInfoCuenta();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        cuentas.add(cuenta);
        List<LimitesYDisponiblesDTO> limitesYDisponibles = obtenerLimitesYDisponiblesDTO();
        IdentificacionCuenta idCuenta = new IdentificacionCuenta();
        Cliente cliente = new Cliente();
        cliente.setCuentas(cuentas);
        cliente.setNup("00000000");
        
        Respuesta<ResumenDetalleCuenta> favorita = new Respuesta<ResumenDetalleCuenta>();
        favorita.setEstadoRespuesta(EstadoRespuesta.ERROR);
        
        Mockito.when(ultimosConsumosManager.obtenerUltimosConsumos(Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(obtenerRespuestaUltimosConsumosView(date));
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.doNothing().when(sesionTarjetas).setLimitesYDisponibles(limitesYDisponibles);
        Mockito.doNothing().when(sesionTarjetas)
                .setConsumosTarjeta(obtenerListConsumoTarjeta(date).getUltimosConsumos());
        Mockito.doNothing().when(sesionTarjetas).setConsumoPendiente(ConsumoPendienteMock.obtenerConsumosPendientes());
        Mockito.when(estadisticaManager.add("", "")).thenReturn(true);

        Mockito.when(selectorYCabeceraBO.obtenerTooltipFavorito()).thenReturn("");
        Mockito.when(selectorYCabeceraBO.obtenerTooltipNoFavorito()).thenReturn("");
        Mockito.when(sesionTarjetas.getLimitesYDisponibles()).thenReturn(obtenerLimitesYDisponiblesDTO());
        Mockito.when(selectorYCabeceraBO.obtenerTarjetas(Matchers.any(Cliente.class)))
                .thenReturn(obtenerRespuesta(date));
        Mockito.when(consumosPendientesDeConfirmacionBO.obtenerConsumoPendiente(Matchers.any(Cuenta.class)))
                .thenReturn(obtenerRespuestaConsumosPendientes(date));
        Mockito.when(cuentaBO.obtenerCuentaFavorita(Matchers.any(Cliente.class))).thenReturn(favorita);
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class)))
        		.thenReturn(new ModuloPermiso());

        Respuesta<String> resp = new Respuesta<String>(); 
        resp.setRespuesta("SELECT");
        Mockito.when(cambioGrupoAfinidadManager.obtenerGrupoAfinidadParaFlujos()).thenReturn(resp);
		Mockito.when(contracargosService.getOptionContracargos(cliente.getNup())).thenReturn(false);
        Respuesta<TarjetasView> retorno = tarjetaManager.getTarjetas(tarjetaSeleccionada);
        // view esperado view generado
        Assert.assertNotNull(retorno);
    }

    /**
     * Obtener respuesta.
     *
     * @param date
     *            the date
     * @return the respuesta
     */
    private Respuesta<DisponiblesYConsumoDTO> obtenerRespuesta(Date date) {
        Respuesta<DisponiblesYConsumoDTO> respuesta = new Respuesta<DisponiblesYConsumoDTO>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setItemMensajeRespuesta(null);
        respuesta.setRespuesta(obtenerDisponiblesYConsumoDTO(date));
        respuesta.setRespuestaVacia(false);
        return respuesta;
    }

    /**
     * Obtener respuesta ultimos consumos.
     *
     * @param date
     *            the date
     * @return the respuesta
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    private Respuesta<UltimosConsumosDTO> obtenerRespuestaUltimosConsumos(Date date) throws TarjetaBOUtilsException {
        Respuesta<UltimosConsumosDTO> respuesta = new Respuesta<UltimosConsumosDTO>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setItemMensajeRespuesta(null);
        respuesta.setRespuesta(obtenerListConsumoTarjeta(date));
        respuesta.setRespuestaVacia(false);
        return respuesta;
    }

    /**
     * Obtener respuesta ultimos consumos view.
     *
     * @param date
     *            the date
     * @return the respuesta
     * @throws TarjetaBOUtilsException
     *             the tarjeta BO utils exception
     */
    private Respuesta<ConsumosView> obtenerRespuestaUltimosConsumosView(Date date) throws TarjetaBOUtilsException {
        Respuesta<ConsumosView> respuesta = new Respuesta<ConsumosView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setItemMensajeRespuesta(null);
        respuesta.setRespuesta(new ConsumosView());
        respuesta.setRespuestaVacia(false);
        return respuesta;
    }

    /**
     * Obtener respuesta consumos pendientes.
     *
     * @param date
     *            the date
     * @return the respuesta
     */
    private Respuesta<UltimosConsumosDTO> obtenerRespuestaConsumosPendientes(Date date) {
        Respuesta<UltimosConsumosDTO> respuestaConsumosPendientes = new Respuesta<UltimosConsumosDTO>();
        respuestaConsumosPendientes.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaConsumosPendientes.setId(null);
        respuestaConsumosPendientes.setItemMensajeRespuesta(null);
        respuestaConsumosPendientes.setRespuesta(obtenerConsumosPendientesDTO());
        respuestaConsumosPendientes.setRespuestaVacia(false);
        return respuestaConsumosPendientes;
    }

    /**
     * Obtener list consumo tarjeta.
     *
     * @param date
     *            the date
     * @return the ultimos consumos DTO
     */
    private UltimosConsumosDTO obtenerListConsumoTarjeta(Date date) {
        List<ConsumoTarjetaDTO> consumos = new ArrayList<ConsumoTarjetaDTO>();
        consumos.add(ConsumoTarjetaMock.obtenerConsumoTarjeta());
        UltimosConsumosDTO dto = new UltimosConsumosDTO();
        dto.setUltimosConsumos(consumos);
        return dto;
    }

    /**
     * Obtener list resumen tarjeta DTO.
     *
     * @param date
     *            the date
     * @return the list
     */
    private List<ResumenTarjetaDTO> obtenerListResumenTarjetaDTO(Date date) {
        List<ResumenTarjetaDTO> resumenes = new ArrayList<ResumenTarjetaDTO>();
        resumenes.add(ResumenTarjetaDTOMock.obtenerResumenTarjetaDTO());
        return resumenes;
    }

    /**
     * Obtener consumos pendientes DTO.
     *
     * @return the ultimos consumos DTO
     */
    private UltimosConsumosDTO obtenerConsumosPendientesDTO() {
        List<ConsumoTarjetaDTO> lista = new ArrayList<ConsumoTarjetaDTO>();
        lista.add(ConsumoPendienteMock.obtenerConsumoPendiente());
        UltimosConsumosDTO consumosPendientesDTO = new UltimosConsumosDTO(lista);
        return consumosPendientesDTO;
    }

    /**
     * Obtener limites Y disponibles DTO.
     *
     * @return the list
     */
    private List<LimitesYDisponiblesDTO> obtenerLimitesYDisponiblesDTO() {
        List<LimitesYDisponiblesDTO> tarjetasActivas = new ArrayList<LimitesYDisponiblesDTO>();
        tarjetasActivas.add(LimitesYDisponiblesDTOMock.obtenerLimitesYDisponiblesDTO());
        return tarjetasActivas;
    }

    /**
     * Obtener disponibles Y consumo DTO.
     *
     * @param date
     *            the date
     * @return the disponibles Y consumo DTO
     */
    private DisponiblesYConsumoDTO obtenerDisponiblesYConsumoDTO(Date date) {
        DisponiblesYConsumoDTO dto = new DisponiblesYConsumoDTO();
        dto.setResumenes(obtenerListResumenTarjetaDTO(date));
        dto.setLimitesYDisponiblesDTO(obtenerLimitesYDisponiblesDTO());
        return dto;
    }

    /**
     * Actualizar alias exception test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void actualizarAliasExceptionTest() throws ServiceException {
        Assert.assertNull(tarjetaManager.actualizarAlias("", ""));
    }

    /**
     * Actualizar alia reporte alias ok sin cuentas test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void actualizarAliaReporteAliasOkSinCuentasTest() throws BusinessException {
        Respuesta<Void> reporteAlias = new Respuesta<Void>();
        reporteAlias.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(new Cuenta());
        Mockito.when(aliasFavoritoTarjetaBo.actualizarAlias(Matchers.any(Cuenta.class), Matchers.anyString()))
                .thenReturn(reporteAlias);
        Mockito.when(sesionCliente.getCliente()).thenReturn(clienteSinCuentas());
        Respuesta<Void> res = tarjetaManager.actualizarAlias("123-123123-124", "");
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Actualizar alia reporte alias ok cuenta en sesion test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void actualizarAliaReporteAliasOkCuentaEnSesionTest() throws BusinessException {
        Respuesta<Void> reporteAlias = new Respuesta<Void>();
        reporteAlias.setEstadoRespuesta(EstadoRespuesta.OK);
        Cliente cliente = clienteSinCuentas();
        Cuenta cuenta = new Cuenta();

        cliente.getCuentas().add(cuenta);

        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta);
        Mockito.when(aliasFavoritoTarjetaBo.actualizarAlias(Matchers.any(Cuenta.class), Matchers.anyString()))
                .thenReturn(reporteAlias);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Respuesta<Void> res = tarjetaManager.actualizarAlias("123-123123-124", "");
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Actualizar alia reporte alias ok cuenta no en sesion test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void actualizarAliaReporteAliasOkCuentaNoEnSesionTest() throws BusinessException {
        Respuesta<Void> reporteAlias = new Respuesta<Void>();
        reporteAlias.setEstadoRespuesta(EstadoRespuesta.OK);
        Cliente cliente = clienteSinCuentas();
        Cuenta cuenta = new Cuenta();
        Cuenta cuenta2 = new Cuenta();

        cliente.getCuentas().add(cuenta);
        cuenta2.setCbu("duajwdkaj");

        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(cuenta2);
        Mockito.when(aliasFavoritoTarjetaBo.actualizarAlias(Matchers.any(Cuenta.class), Matchers.anyString()))
                .thenReturn(reporteAlias);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Respuesta<Void> res = tarjetaManager.actualizarAlias("123-123123-124", "");
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Actualizar alia reporte alias not ok test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void actualizarAliaReporteAliasNotOkTest() throws BusinessException {
        Respuesta<Void> reporteAlias = new Respuesta<Void>();
        reporteAlias.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(new Cuenta());
        Mockito.when(aliasFavoritoTarjetaBo.actualizarAlias(Matchers.any(Cuenta.class), Matchers.anyString()))
                .thenReturn(reporteAlias);
        Respuesta<Void> res = tarjetaManager.actualizarAlias("123-123123-124", "");
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Actualizar tarjeta favorita business exception test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void actualizarTarjetaFavoritaBusinessExceptionTest() throws ServiceException, BusinessException {
        Mockito.when(aliasFavoritoTarjetaBo.marcarFavorita(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenThrow(new BusinessException());
        Respuesta<Void> res = tarjetaManager.actualizarTarjetaFavorita("123-123123-124");
        Assert.assertNull(res);

    }

    /**
     * Actualizar tarjeta favorita ok test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void actualizarTarjetaFavoritaOkTest() throws ServiceException, BusinessException {
        Respuesta<Void> toRet = new Respuesta<Void>();
        toRet.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(aliasFavoritoTarjetaBo.marcarFavorita(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenReturn(toRet);
        Respuesta<Void> res = tarjetaManager.actualizarTarjetaFavorita("123-123123-124");
        assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());

    }

    /**
     * Actualizar tarjeta favorita not ok test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void actualizarTarjetaFavoritaNotOkTest() throws ServiceException, BusinessException {
        Respuesta<Void> toRet = new Respuesta<Void>();
        toRet.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Mockito.when(aliasFavoritoTarjetaBo.marcarFavorita(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenReturn(toRet);
        Respuesta<Void> res = tarjetaManager.actualizarTarjetaFavorita("123-123123-124");
        assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());

    }

    /**
     * Gets the tarjetas exception test.
     *
     * @return the tarjetas exception test
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void getTarjetasExceptionTest() throws ServiceException {
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Cliente clienteObj = new Cliente();
        clienteObj.setNup("123");
		Mockito.when(sesionCliente.getCliente()).thenReturn(clienteObj );
        assertEquals(tarjetaManager.getTarjetas(conseguirTarjetaSeleccionada()).getEstadoRespuesta(),
                EstadoRespuesta.ERROR);
    }

    /**
     * Obtener limites Y disponibles exception test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void obtenerLimitesYDisponiblesExceptionTest() throws ServiceException {
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        assertEquals(tarjetaManager.obtenerLimitesYDisponibles("").getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }

    /**
     * Obtener limites Y disponibles ok test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerLimitesYDisponiblesOkTest() throws ServiceException, BusinessException {
        LimitesYDisponiblesDTO limitesYDisponiblesDTO = new LimitesYDisponiblesDTO();
        limitesYDisponiblesDTO.setCodigoError(null);

        Respuesta<String> reString = new Respuesta<String>();
        reString.setRespuesta("");

        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(selectorYCabeceraBO.obtenerMarcaDeTarjeta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenReturn("");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(reString);
        Mockito.when(limitesYDisponiblesBO.buscarLimiteYDisponibleDeCuenta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class), Matchers.any(ArrayList.class))).thenReturn(limitesYDisponiblesDTO);
        Respuesta<LimitesYDisponiblesView> res = tarjetaManager.obtenerLimitesYDisponibles("123-123123-124");
        assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Obtener respuesta limites Y disponibles error code not null test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerRespuestaLimitesYDisponiblesErrorCodeNotNullTest() throws ServiceException, BusinessException {
        LimitesYDisponiblesDTO limitesYDisponiblesDTO = new LimitesYDisponiblesDTO();
        limitesYDisponiblesDTO.setCodigoError("5");

        Respuesta<String> reString = new Respuesta<String>();
        reString.setRespuesta("");

        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(selectorYCabeceraBO.obtenerMarcaDeTarjeta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenReturn("");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(reString);
        Mockito.when(limitesYDisponiblesBO.buscarLimiteYDisponibleDeCuenta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class), Matchers.any(ArrayList.class))).thenReturn(limitesYDisponiblesDTO);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<LimitesYDisponiblesView> res = tarjetaManager.obtenerLimitesYDisponibles("123-123123-124");
        assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Obtener respuesta limites Y disponibles limites Y disponibles DTO null
     * test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerRespuestaLimitesYDisponiblesLimitesYDisponiblesDTONullTest()
            throws ServiceException, BusinessException {
        Respuesta<String> reString = new Respuesta<String>();
        reString.setRespuesta("");

        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(selectorYCabeceraBO.obtenerMarcaDeTarjeta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class))).thenReturn("");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(reString);
        Mockito.when(limitesYDisponiblesBO.buscarLimiteYDisponibleDeCuenta(Matchers.any(IdentificacionCuenta.class),
                Matchers.any(Cliente.class), Matchers.any(ArrayList.class))).thenReturn(null);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<LimitesYDisponiblesView> res = tarjetaManager.obtenerLimitesYDisponibles("123-123123-124");
        assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Gets the tarjetas ok test.
     *
     * @return the tarjetas ok test
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     */
    @Test
    public void getTarjetasOkTest() throws ServiceException, BusinessException, DAOException {

        Cliente cliente = Mockito.mock(Cliente.class);
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = CuentaMock.completarInfoCuenta();
        cuenta.setTipoCuenta("02");
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);

        Respuesta<ResumenDetalleCuenta> favorita = new Respuesta<ResumenDetalleCuenta>();
        favorita.setEstadoRespuesta(EstadoRespuesta.ERROR);
        
        Respuesta<DisponiblesYConsumoDTO> resDisponibles = new Respuesta<DisponiblesYConsumoDTO>();
        resDisponibles.setEstadoRespuesta(EstadoRespuesta.OK);
        resDisponibles.setRespuesta(new DisponiblesYConsumoDTO());
        resDisponibles.getRespuesta().setResumenes(new ArrayList<ResumenTarjetaDTO>());

        ResumenTarjetaDTO resumenTarjetaDTO = new ResumenTarjetaDTO();
        resumenTarjetaDTO.setDetalle(new DetalleTarjetaDTO());
        resumenTarjetaDTO.getDetalle().setNroSucursal("012");
        resumenTarjetaDTO.getDetalle().setNroCuentaProducto("123125471254");
        resDisponibles.getRespuesta().getResumenes().add(resumenTarjetaDTO);

        Respuesta<ConsumosView> resConsumosView = new Respuesta<ConsumosView>();
        resConsumosView.setEstadoRespuesta(EstadoRespuesta.OK);
        resConsumosView.setRespuesta(new ConsumosView());
        Mockito.when(cuentaBO.obtenerCuentaFavorita(Matchers.any(Cliente.class))).thenReturn(favorita);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(ultimosConsumosManager.obtenerUltimosConsumos(Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(resConsumosView);
        Mockito.when(selectorYCabeceraBO.obtenerTarjetas(Matchers.any(Cliente.class))).thenReturn(resDisponibles);
        Mockito.when(legalBO.obtenerLegalesPorCodigo(Matchers.anyString())).thenReturn("Legales.");
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class))).
        		thenReturn(new ModuloPermiso());

        Respuesta<String> resp = new Respuesta<String>(); 
        resp.setRespuesta("SELECT");
        Mockito.when(cambioGrupoAfinidadManager.obtenerGrupoAfinidadParaFlujos()).thenReturn(resp);
        Respuesta<TarjetasView> res = tarjetaManager.getTarjetas(new TarjetaSeleccionada());
        assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());

    }

    /**
     * Gets the tarjetasdatos selector de tarjeta id null test.
     *
     * @return the tarjetasdatos selector de tarjeta id null test
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void getTarjetasdatosSelectorDeTarjetaIdNullTest() throws BusinessException {
        Respuesta<DisponiblesYConsumoDTO> resDisponibles = new Respuesta<DisponiblesYConsumoDTO>();
        resDisponibles.setEstadoRespuesta(EstadoRespuesta.ERROR);
        resDisponibles.setRespuesta(new DisponiblesYConsumoDTO());
        resDisponibles.getRespuesta().setResumenes(new ArrayList<ResumenTarjetaDTO>());

        ResumenTarjetaDTO resumenTarjetaDTO = new ResumenTarjetaDTO();
        resumenTarjetaDTO.setDetalle(new DetalleTarjetaDTO());
        resumenTarjetaDTO.getDetalle().setNroSucursal("012");
        resumenTarjetaDTO.getDetalle().setNroCuentaProducto("123125471254");
        resDisponibles.getRespuesta().getResumenes().add(resumenTarjetaDTO);
        Cliente clienteObj = new Cliente();
        clienteObj.setNup("123");
		Mockito.when(sesionCliente.getCliente()).thenReturn(clienteObj );
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Mockito.when(selectorYCabeceraBO.obtenerTarjetas(Matchers.any(Cliente.class))).thenReturn(resDisponibles);
        Respuesta<TarjetasView> res = tarjetaManager.getTarjetas(new TarjetaSeleccionada());
        assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Gets the tarjetas lista tarjetas view empty test.
     *
     * @return the tarjetas lista tarjetas view empty test
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void getTarjetasListaTarjetasViewEmptyTest() throws BusinessException {
        Respuesta<DisponiblesYConsumoDTO> resDisponibles = new Respuesta<DisponiblesYConsumoDTO>();
        resDisponibles.setEstadoRespuesta(EstadoRespuesta.ERROR);
        resDisponibles.setRespuesta(new DisponiblesYConsumoDTO());
        resDisponibles.getRespuesta().setResumenes(new ArrayList<ResumenTarjetaDTO>());
        Cliente clienteObj = new Cliente();
        clienteObj.setNup("123");
		Mockito.when(sesionCliente.getCliente()).thenReturn(clienteObj );
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Mockito.when(selectorYCabeceraBO.obtenerTarjetas(Matchers.any(Cliente.class))).thenReturn(resDisponibles);
        Respuesta<TarjetasView> res = tarjetaManager.getTarjetas(new TarjetaSeleccionada());
        assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Gets the tarjetas error respuesta consumos view test.
     *
     * @return the tarjetas error respuesta consumos view test
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void getTarjetasErrorRespuestaConsumosViewTest() throws BusinessException {

        Cliente cliente = Mockito.mock(Cliente.class);
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta = CuentaMock.completarInfoCuenta();
        cuenta.setTipoCuenta("02");
        cuentas.add(cuenta);
        cliente.setCuentas(cuentas);

        Respuesta<DisponiblesYConsumoDTO> resDisponibles = new Respuesta<DisponiblesYConsumoDTO>();
        resDisponibles.setEstadoRespuesta(EstadoRespuesta.OK);
        resDisponibles.setRespuesta(new DisponiblesYConsumoDTO());
        resDisponibles.getRespuesta().setResumenes(new ArrayList<ResumenTarjetaDTO>());

        ResumenTarjetaDTO resumenTarjetaDTO = new ResumenTarjetaDTO();
        resumenTarjetaDTO.setDetalle(new DetalleTarjetaDTO());
        resumenTarjetaDTO.getDetalle().setNroSucursal("012");
        resumenTarjetaDTO.getDetalle().setNroCuentaProducto("123125471254");
        resDisponibles.getRespuesta().getResumenes().add(resumenTarjetaDTO);

        Respuesta<ConsumosView> resConsumosView = new Respuesta<ConsumosView>();
        resConsumosView.setEstadoRespuesta(EstadoRespuesta.ERROR);
        resConsumosView.setRespuesta(new ConsumosView());
        resConsumosView.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());

        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(ultimosConsumosManager.obtenerUltimosConsumos(Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(resConsumosView);
        Mockito.when(selectorYCabeceraBO.obtenerTarjetas(Matchers.any(Cliente.class))).thenReturn(resDisponibles);
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class)))
        		.thenReturn(new ModuloPermiso());

        Respuesta<String> resp = new Respuesta<String>(); 
        resp.setRespuesta("SELECT");
        Mockito.when(cambioGrupoAfinidadManager.obtenerGrupoAfinidadParaFlujos()).thenReturn(resp);
        Respuesta<TarjetasView> res = tarjetaManager.getTarjetas(new TarjetaSeleccionada());
        assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }

    /**
     * Generar tarjeta view tarjeta seleccionada con id test.
     *
     * @throws ServiceException
     *             the service exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
	@Ignore
    public void generarTarjetaViewTarjetaSeleccionadaConIdTest() throws ServiceException, BusinessException {
        TarjetaSeleccionada tarjetaSeleccionada = new TarjetaSeleccionada();
        tarjetaSeleccionada.setId("123");

        Respuesta<DisponiblesYConsumoDTO> resDisponibles = new Respuesta<DisponiblesYConsumoDTO>();
        resDisponibles.setEstadoRespuesta(EstadoRespuesta.OK);
        resDisponibles.setRespuesta(new DisponiblesYConsumoDTO());
        resDisponibles.getRespuesta().setResumenes(new ArrayList<ResumenTarjetaDTO>());
        resDisponibles.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());

        ResumenTarjetaDTO resumenTarjetaDTO = new ResumenTarjetaDTO();
        resumenTarjetaDTO.setDetalle(new DetalleTarjetaDTO());
        resumenTarjetaDTO.getDetalle().setNroSucursal("012");
        resumenTarjetaDTO.getDetalle().setNroCuentaProducto("123125471254");
        resDisponibles.getRespuesta().getResumenes().add(resumenTarjetaDTO);

        Respuesta<ConsumosView> resConsumosView = new Respuesta<ConsumosView>();
        resConsumosView.setEstadoRespuesta(EstadoRespuesta.OK);
        resConsumosView.setRespuesta(new ConsumosView());
        
        Mockito.when(ultimosConsumosManager.obtenerUltimosConsumos(Matchers.any(IdentificacionCuenta.class)))
                .thenReturn(resConsumosView);
        Mockito.when(selectorYCabeceraBO.obtenerTarjetas(Matchers.any(Cliente.class))).thenReturn(resDisponibles);
        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<TarjetasView> res = tarjetaManager.getTarjetas(tarjetaSeleccionada);
        assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());

    }

    /**
     * Guardar estadistica tarjetas test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void guardarEstadisticaTarjetasTest() throws ServiceException {
        tarjetaManager.guardarEstadisticaTarjetas("12");
    }

    /**
     * Conseguir tarjeta seleccionada.
     *
     * @return the tarjeta seleccionada
     */
    private TarjetaSeleccionada conseguirTarjetaSeleccionada() {
        TarjetaSeleccionada tarjetaSeleccionada = new TarjetaSeleccionada();
        return tarjetaSeleccionada;
    }

    /**
     * Cliente sin cuentas.
     *
     * @return the cliente
     */
    private Cliente clienteSinCuentas() {
        Cliente cliente = new Cliente();
        cliente.setCuentas(new ArrayList<Cuenta>());
        return cliente;
    }
    
    /**
     * Validar Reemplazo de tarjetas Text OK.
     *
     * @return void
     */
    @Test
    public void validarReemplazoDeTarjetasTestOK() {
    	// crear los variables para hacer el test, segun el metodo hecho.
    	
    	//Datos del Cliente
    	Cliente cliente = new Cliente();
    	Cuenta cuenta = CuentaMock.completarInfoCuenta();
    	cuenta.setJerarquiaCuenta("P");
    	cuenta.setTipoCuenta("33");
    	List<Cuenta> cuentas = new ArrayList<Cuenta>();
    	cuentas.add(cuenta);
    	cuenta.setJerarquiaCuenta("P");
    	cuenta.setTipoCuenta("09");
    	cliente.setCuentas(cuentas);
    	
    	//Datos de Ofertas
    	OfertaComercialDTO oferta = new OfertaComercialDTO();
    	oferta.setVariable1Char("S");
    	GotoLink link = new GotoLink("gotoRecambioChip()");
    	oferta.setGotoLink(link);
    	List<OfertaComercialDTO> ofertas = new ArrayList<OfertaComercialDTO>();
    	ofertas.add(oferta);
    	
    	//Datos para OfertasComerciales
    	EventosComercialesDTO ofertasComerciales = new EventosComercialesDTO();
    	ofertasComerciales.setOfertas(ofertas);
    	
    	Respuesta<TarjetasView> resUno = new Respuesta<TarjetasView>();
    	resUno.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	Respuesta<Sucursal> respuestaSucursal = new Respuesta<Sucursal>();
    	resUno.setEstadoRespuesta(EstadoRespuesta.OK);
    	Sucursal sucursalCuenta = new Sucursal();
    	sucursalCuenta.setTipoDeOficina("comodo");
    	sucursalCuenta.setIdSucursal("213");
    	sucursalCuenta.setDireccion("calle false 123");
    	sucursalCuenta.setDescripcion("");
    	respuestaSucursal.setRespuesta(sucursalCuenta);
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionParametros.getOfertasComerciales()).thenReturn(ofertasComerciales);
        ModuloPermiso permiso = new ModuloPermiso();
        permiso.setModuloEstado(ModuloEstado.MOSTRAR);
    	Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_INICIO_RESUMEN_ANUAL)).thenReturn(permiso);
    	Mockito.when(consultarSucursalesBO.consultarSucursalPorId(Matchers.any(String.class))).thenReturn(respuestaSucursal);
    	
    	Respuesta<TarjetasView> resDos = tarjetaManager.validarReemplazoDeTarjetas();
        assertEquals(EstadoRespuesta.OK, resDos.getEstadoRespuesta());
    }
    
    /**
     * Validar Reemplazo de tarjetas Text ERROR.
     * Error por tipo de cuenta errorea, el tipo de cuenta es BANELCO
     * no es valida
     * @return void
     */
    @Test
    public void validarReemplazoDeTarjetasTestERRORTipoCuenta() {
    	// crear los variables para hacer el test, segun el metodo hecho.
    	
    	//Datos del Cliente
    	Cliente cliente = new Cliente();
    	Cuenta cuenta = CuentaMock.completarInfoCuenta();
    	cuenta.setTipoCuentaEnum(TipoCuenta.BANELCO);
    	List<Cuenta> cuentas = new ArrayList<Cuenta>();
    	cuentas.add(cuenta);
    	cliente.setCuentas(cuentas);
    	
    	//Datos de Ofertas
    	OfertaComercialDTO oferta = new OfertaComercialDTO();
    	oferta.setVariable1Char("S");
    	GotoLink link = new GotoLink("gotoRecambioChip()");
    	oferta.setGotoLink(link);
    	List<OfertaComercialDTO> ofertas = new ArrayList<OfertaComercialDTO>();
    	ofertas.add(oferta);
    	
    	//Datos para OfertasComerciales
    	EventosComercialesDTO ofertasComerciales = new EventosComercialesDTO();
    	ofertasComerciales.setOfertas(ofertas);
    	
    	Respuesta<TarjetasView> resUno = new Respuesta<TarjetasView>();
    	resUno.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionParametros.getOfertasComerciales()).thenReturn(ofertasComerciales);
        ModuloPermiso permiso = new ModuloPermiso();
        permiso.setModuloEstado(ModuloEstado.MOSTRAR);
    	Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_INICIO_RESUMEN_ANUAL)).thenReturn(permiso);
    	
    	Respuesta<TarjetasView> resDos = tarjetaManager.validarReemplazoDeTarjetas();
        assertEquals(EstadoRespuesta.ERROR, resDos.getEstadoRespuesta());
    }
    
    /**
     * Validar Reemplazo de tarjetas Text ERROR.
     * Error por Titular errorea, el titular no es el correcto
     * es invalido
     * @return void
     */
    @Test
    public void validarReemplazoDeTarjetasTestERRORTitular() {
    	// crear los variables para hacer el test, segun el metodo hecho.
    	
    	//Datos del Cliente
    	Cliente cliente = new Cliente();
    	Cuenta cuenta = CuentaMock.completarInfoCuenta();
    	cuenta.setCodigoTitularidad("AS");
    	List<Cuenta> cuentas = new ArrayList<Cuenta>();
    	cuentas.add(cuenta);
    	cliente.setCuentas(cuentas);
    	
    	//Datos de Ofertas
    	OfertaComercialDTO oferta = new OfertaComercialDTO();
    	oferta.setVariable1Char("S");
    	GotoLink link = new GotoLink("gotoRecambioChip()");
    	oferta.setGotoLink(link);
    	List<OfertaComercialDTO> ofertas = new ArrayList<OfertaComercialDTO>();
    	ofertas.add(oferta);
    	
    	//Datos para OfertasComerciales
    	EventosComercialesDTO ofertasComerciales = new EventosComercialesDTO();
    	ofertasComerciales.setOfertas(ofertas);
    	
    	Respuesta<TarjetasView> resUno = new Respuesta<TarjetasView>();
    	resUno.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionParametros.getOfertasComerciales()).thenReturn(ofertasComerciales);
        ModuloPermiso permiso = new ModuloPermiso();
        permiso.setModuloEstado(ModuloEstado.MOSTRAR);
    	Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_INICIO_RESUMEN_ANUAL)).thenReturn(permiso);
    	
    	Respuesta<TarjetasView> resDos = tarjetaManager.validarReemplazoDeTarjetas();
        assertEquals(EstadoRespuesta.ERROR, resDos.getEstadoRespuesta());
    }
    
    /**
     * Validar Reemplazo de tarjetas Text ERROR.
     * Error por el estado de la tarjeta de credito, el estado de la tarjeta
     * de credito no es valido
     * @return void
     */
    @Test
    public void validarReemplazoDeTarjetasTestERROREstadoTarjetaCredito() {
    	// crear los variables para hacer el test, segun el metodo hecho.
    	
    	//Datos del Cliente
    	Cliente cliente = new Cliente();
    	Cuenta cuenta = CuentaMock.completarInfoCuenta();
    	cuenta.setEstadoTarjetaCredito("23");
    	List<Cuenta> cuentas = new ArrayList<Cuenta>();
    	cuentas.add(cuenta);
    	cliente.setCuentas(cuentas);
    	
    	//Datos de Ofertas
    	OfertaComercialDTO oferta = new OfertaComercialDTO();
    	oferta.setVariable1Char("S");
    	GotoLink link = new GotoLink("gotoRecambioChip()");
    	oferta.setGotoLink(link);
    	List<OfertaComercialDTO> ofertas = new ArrayList<OfertaComercialDTO>();
    	ofertas.add(oferta);
    	
    	//Datos para OfertasComerciales
    	EventosComercialesDTO ofertasComerciales = new EventosComercialesDTO();
    	ofertasComerciales.setOfertas(ofertas);
    	
    	Respuesta<TarjetasView> resUno = new Respuesta<TarjetasView>();
    	resUno.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionParametros.getOfertasComerciales()).thenReturn(ofertasComerciales);
        ModuloPermiso permiso = new ModuloPermiso();
        permiso.setModuloEstado(ModuloEstado.MOSTRAR);
    	Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.IR_INICIO_RESUMEN_ANUAL)).thenReturn(permiso);
    	
    	Respuesta<TarjetasView> resDos = tarjetaManager.validarReemplazoDeTarjetas();
        assertEquals(EstadoRespuesta.ERROR, resDos.getEstadoRespuesta());
    }
    
}
