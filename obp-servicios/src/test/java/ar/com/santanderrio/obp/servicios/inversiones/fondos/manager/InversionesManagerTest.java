package ar.com.santanderrio.obp.servicios.inversiones.fondos.manager;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.AdministradorPermisos;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.manager.AnalisisCarteraManager;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadTotalInView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadTotalView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesBO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.DetalleCustodiaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.DetalleCustodiaDTOMock;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.TenenciaConsolidadaPorProductoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dto.TotalesTenenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.manager.InversionesManagerImpl;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaInView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.InicioInversionesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TarjetaTenenciaConsolidadaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaConsolidadaBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaConsolidadaBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaConsolidadaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorCuentaBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoInView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaProductosPorMonedaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaTituloDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FondoResumidoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.InicioFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.InicioFondoView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

@RunWith(MockitoJUnitRunner.class)
public class InversionesManagerTest {

    /** The inversiones manager. */
    @InjectMocks
    private InversionesManagerImpl inversionesManager;

    /** The inversiones BO. */
    @Mock
    private InversionesBO inversionesBO;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;
    
    @Mock
    private SesionParametros sesionParametros;

    /** The validator. */
    @Mock
    private Validator validator;

    @Mock
    private EstadisticaManager estadisticaManager;
    
    @Mock
	private AnalisisCarteraManager analisisCarteraManager;

    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The legal BO. */
    @Mock
    private LegalBO legalBO;

    /** The mensaje dao. */
    @Mock
    private MensajeBO mensajeBO;

    @Mock
    private ModuloPermisoBO moduloPermisoBO;

    @Mock
    private AdministradorPermisos administradorPermisos;

    @SuppressWarnings("unchecked")
    @Test
    public void inicioInversionesTestOk() {

        when(sesionCliente.getCliente()).thenReturn(new Cliente());

        Respuesta<InicioFondoDTO> respuestaBO = new Respuesta<InicioFondoDTO>();
        InicioFondoDTO respuestaDTO = new InicioFondoDTO();
        List<CuentaTituloDTO> cuentasBancaPersonal = new ArrayList<CuentaTituloDTO>();
        CuentaTituloDTO ctaTitDTO = new CuentaTituloDTO();

        List<FondoResumidoDTO> fondosSuscriptos = new ArrayList<FondoResumidoDTO>();
        ctaTitDTO.setFondosSuscriptos(fondosSuscriptos);
        List<Interviniente> intervinientes = new ArrayList<Interviniente>();
        ctaTitDTO.setIntervinientes(intervinientes);

        cuentasBancaPersonal.add(ctaTitDTO);
        respuestaDTO.setCuentasBancaPersonal(cuentasBancaPersonal);
        respuestaDTO.setCuentasBancaPrivada(cuentasBancaPersonal);
        respuestaBO.setRespuesta(respuestaDTO);
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        when(inversionesBO.inicioInversiones(Matchers.any(Cliente.class), Matchers.anyString()))
                .thenReturn(respuestaBO);

        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class)))
                .thenReturn(moduloPermiso);

        Respuesta<TotalesTenenciaDTO> respuestaTenencia = new Respuesta<TotalesTenenciaDTO>();
        respuestaTenencia.setEstadoRespuesta(EstadoRespuesta.OK);
        TotalesTenenciaDTO totalesDTO = new TotalesTenenciaDTO();
        respuestaTenencia.setRespuesta(totalesDTO);

        when(inversionesBO.obtenerTotalesTenencia(Matchers.any(Cliente.class), Matchers.anyString(), Matchers.any(TipoBancaEnum.class)))
                .thenReturn(respuestaTenencia);
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
        
        Respuesta<RentabilidadTotalView> rtaCartera = new Respuesta<RentabilidadTotalView>();
        rtaCartera.setEstadoRespuesta(EstadoRespuesta.OK);
        
        when(analisisCarteraManager.obtenerRentabilidadTotal(Matchers.any(RentabilidadTotalInView.class))).thenReturn(rtaCartera);

        Respuesta<InicioFondoView> rtaFactory = new Respuesta<InicioFondoView>();
        rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
        when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(InicioFondoView.class)))
                .thenReturn(rtaFactory);
        Mockito.doNothing().when(administradorPermisos).addNewGrant(AccionController.RENDIMIENTO_TENENCIA_INVERSIONES);
        Mockito.doNothing().when(administradorPermisos).removeGrant(AccionController.RENDIMIENTO_TENENCIA_INVERSIONES);

        InicioInversionesViewRequest request = new InicioInversionesViewRequest();
        request.setTipoDeOperacion("FCI");
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<InicioFondoView> rtaManager = inversionesManager.inicioInversiones(request);
        Assert.assertNotNull(rtaManager);
        Assert.assertEquals(EstadoRespuesta.OK, rtaManager.getEstadoRespuesta());
    }

    @SuppressWarnings({ "rawtypes" })
    @Test
    public void inicioInversionesTestWarning() {

        when(sesionCliente.getCliente()).thenReturn(new Cliente());

        Respuesta<InicioFondoDTO> respuestaBO = new Respuesta<InicioFondoDTO>();
        InicioFondoDTO respuestaDTO = new InicioFondoDTO();
        List<CuentaTituloDTO> cuentasBancaPersonal = new ArrayList<CuentaTituloDTO>();
        CuentaTituloDTO ctaTitDTO = new CuentaTituloDTO();

        List<FondoResumidoDTO> fondosSuscriptos = new ArrayList<FondoResumidoDTO>();
        ctaTitDTO.setFondosSuscriptos(fondosSuscriptos);
        List<Interviniente> intervinientes = new ArrayList<Interviniente>();
        ctaTitDTO.setIntervinientes(intervinientes);

        cuentasBancaPersonal.add(ctaTitDTO);
        respuestaDTO.setCuentasBancaPersonal(cuentasBancaPersonal);
        respuestaDTO.setCuentasBancaPrivada(cuentasBancaPersonal);
        respuestaBO.setRespuesta(respuestaDTO);
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        when(inversionesBO.inicioInversiones(Matchers.any(Cliente.class), Matchers.anyString()))
                .thenReturn(respuestaBO);

        Respuesta<RentabilidadTotalView> rtaCartera = new Respuesta<RentabilidadTotalView>();
        rtaCartera.setEstadoRespuesta(EstadoRespuesta.OK);
        
        when(analisisCarteraManager.obtenerRentabilidadTotal(Matchers.any(RentabilidadTotalInView.class))).thenReturn(rtaCartera);
        
        Respuesta<TotalesTenenciaDTO> respuestaTenencia = new Respuesta<TotalesTenenciaDTO>();
        respuestaTenencia.setEstadoRespuesta(EstadoRespuesta.WARNING);
        TotalesTenenciaDTO totalesDTO = new TotalesTenenciaDTO();
        respuestaTenencia.setRespuesta(totalesDTO);

        when(inversionesBO.obtenerTotalesTenencia(Matchers.any(Cliente.class), Matchers.anyString(), Matchers.any(TipoBancaEnum.class)))
                .thenReturn(respuestaTenencia);

        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);

        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class)))
                .thenReturn(moduloPermiso);

        Respuesta respuestaErrorPerfilInversor = new Respuesta();
        respuestaErrorPerfilInversor.setEstadoRespuesta(EstadoRespuesta.WARNING);

        InicioInversionesViewRequest request = new InicioInversionesViewRequest();
        request.setTipoDeOperacion("FCI");
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<InicioFondoView> rtaManager = inversionesManager.inicioInversiones(request);
        Assert.assertNotNull(rtaManager);
    }

    @Test
    public void obtenerTenenciaConsolidadaHome() {
        Respuesta<TarjetaTenenciaConsolidadaView> respuestaBO = new Respuesta<TarjetaTenenciaConsolidadaView>();

        TarjetaTenenciaConsolidadaView totalesTenencia = new TarjetaTenenciaConsolidadaView();

        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBO.setRespuesta(totalesTenencia);

        Mockito.when(inversionesBO.obtenerTotalesTenenciaHome(Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class)))
                .thenReturn(respuestaBO);
        InicioInversionesViewRequest request = new InicioInversionesViewRequest();
        request.setTipoDeOperacion("TAR");

        Respuesta<TarjetaTenenciaConsolidadaView> rtaManager = inversionesManager
                .obtenerTenenciaConsolidadaHome(request, TipoBancaEnum.BANCA_PERSONAL);
        Assert.assertNotNull(rtaManager);
        Assert.assertEquals(EstadoRespuesta.OK, rtaManager.getEstadoRespuesta());

    }

    @SuppressWarnings("unchecked")
    @Test
    @Ignore
    public void obtenerTenenciaPorProducto() {
        Respuesta<TenenciaConsolidadaPorProductoDTO> respuestaBO = new Respuesta<TenenciaConsolidadaPorProductoDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        TenenciaConsolidadaPorProductoDTO tenenciaDTO = new TenenciaConsolidadaPorProductoDTO();
        List<TenenciaPorProductoDTO> listTenenciaProd = new ArrayList<TenenciaPorProductoDTO>();
        TenenciaPorProductoDTO tenenciaProducto = new TenenciaPorProductoDTO();
        tenenciaProducto.setCodigoError("0");
        tenenciaProducto.setProducto("FCI");
        listTenenciaProd.add(tenenciaProducto);
        tenenciaDTO.setListaTenencia(listTenenciaProd);
        respuestaBO.setRespuesta(tenenciaDTO);

        Respuesta<String> respuestasLegales = new Respuesta<String>();
        respuestasLegales.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestasLegales);

        Mockito.when(inversionesBO.obtenerTenenciaConsolidadaPorProducto(Matchers.any(Cliente.class)))
                .thenReturn(respuestaBO);

        Respuesta<TenenciaConsolidadaView> rtaManager = new Respuesta<TenenciaConsolidadaView>();
        rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
                Matchers.any(TarjetaTenenciaConsolidadaView.class))).thenReturn(rtaManager);
        TenenciaPorProductoInView estadistica = new TenenciaPorProductoInView();
        estadistica.setEstadisticas("1");
        Respuesta<TenenciaConsolidadaView> respuesta = inversionesManager
                .obtenerTenenciaConsolidadaPorProducto(estadistica);
        Assert.assertNotNull(rtaManager);
        Assert.assertEquals(EstadoRespuesta.OK, rtaManager.getEstadoRespuesta());
    }

    @Test
    @Ignore
    public void obtenerTenenciaPorProductoRespuestaWarning() {
        Respuesta<TenenciaConsolidadaPorProductoDTO> respuestaBO = new Respuesta<TenenciaConsolidadaPorProductoDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        TenenciaConsolidadaPorProductoDTO tenenciaDTO = new TenenciaConsolidadaPorProductoDTO();
        List<TenenciaPorProductoDTO> listTenenciaProd = new ArrayList<TenenciaPorProductoDTO>();
        TenenciaPorProductoDTO tenenciaProducto = new TenenciaPorProductoDTO();
        tenenciaProducto.setCodigoError("2");
        tenenciaProducto.setProducto("FCI");
        listTenenciaProd.add(tenenciaProducto);
        tenenciaDTO.setListaTenencia(listTenenciaProd);
        respuestaBO.setRespuesta(tenenciaDTO);

        Respuesta<String> respuestasLegales = new Respuesta<String>();
        respuestasLegales.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestasLegales);

        Mockito.when(inversionesBO.obtenerTenenciaConsolidadaPorProducto(Matchers.any(Cliente.class)))
                .thenReturn(respuestaBO);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        TenenciaPorProductoInView estadistica = new TenenciaPorProductoInView();
        estadistica.setEstadisticas("1");
        Respuesta<TenenciaConsolidadaView> respuesta = inversionesManager
                .obtenerTenenciaConsolidadaPorProducto(estadistica);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());

    }

    @SuppressWarnings("rawtypes")
    @Test
    @Ignore
    public void obtenerTenenciaPorProductoError() {
        Respuesta<TenenciaConsolidadaPorProductoDTO> respuestaBO = new Respuesta<TenenciaConsolidadaPorProductoDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Respuesta respuestaError = new Respuesta();
        respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Respuesta<String> respuestasLegales = new Respuesta<String>();
        respuestasLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        Mensaje mensajeError = new Mensaje();
        mensajeError.setMensaje("Mensaje Error");

        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestasLegales);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeError);
        Mockito.when(inversionesBO.obtenerTenenciaConsolidadaPorProducto(Matchers.any(Cliente.class)))
                .thenReturn(respuestaBO);

        TenenciaPorProductoInView estadistica = new TenenciaPorProductoInView();
        estadistica.setEstadisticas("1");
        when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Respuesta<TenenciaConsolidadaView> respuesta = inversionesManager
                .obtenerTenenciaConsolidadaPorProducto(estadistica);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void obtenerTenenciaPorProductoLegalError() {
        Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);

        TenenciaPorProductoInView estadistica = new TenenciaPorProductoInView();
        estadistica.setEstadisticas("1");
        Mensaje mensajeError = new Mensaje();
        mensajeError.setMensaje("Mensaje Error");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeError);

        Respuesta<TenenciaConsolidadaView> respuesta = inversionesManager
                .obtenerTenenciaConsolidadaPorProducto(estadistica);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @SuppressWarnings("unchecked")
    @Test
    @Ignore
    public void obtenerTenenciaPorProductoBPriv() {
        Respuesta<TenenciaConsolidadaBPrivDTO> respuestaBO = new Respuesta<TenenciaConsolidadaBPrivDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        TenenciaConsolidadaBPrivDTO tenenciaDTO = new TenenciaConsolidadaBPrivDTO();
        List<TenenciaPorCuentaBPrivDTO> listTenenciaProd = new ArrayList<TenenciaPorCuentaBPrivDTO>();
        TenenciaPorCuentaBPrivDTO tenenciaProducto = new TenenciaPorCuentaBPrivDTO();
        tenenciaProducto.setNroCuenta("12345678");
        tenenciaProducto.setNroCuentaFormateado("250-1234567/8");

        TenenciaProductosPorMonedaDTO tenenciaDolares = new TenenciaProductosPorMonedaDTO();
        TenenciaProductosPorMonedaDTO tenenciaPesos = new TenenciaProductosPorMonedaDTO();

        List<TenenciaPorProductoBPrivDTO> listaTenenciaProductos = new ArrayList<TenenciaPorProductoBPrivDTO>();
        TenenciaPorProductoBPrivDTO tenenciaPorProductoBPrivDTO = new TenenciaPorProductoBPrivDTO();
        tenenciaPorProductoBPrivDTO.setProducto("FCI");
        tenenciaPorProductoBPrivDTO.setResultado("OK");
        tenenciaPorProductoBPrivDTO.setTenenciaValuadaCosto("2.00");
        tenenciaPorProductoBPrivDTO.setTenenciaValuadaHoy("134850.00");
        tenenciaPorProductoBPrivDTO.setTenenciaValuadaReexpresada("2.00");

        TenenciaPorProductoBPrivDTO tenenciaPorProductoBPrivDTO2 = new TenenciaPorProductoBPrivDTO();
        tenenciaPorProductoBPrivDTO2.setProducto("TV");
        tenenciaPorProductoBPrivDTO2.setResultado("OK");
        tenenciaPorProductoBPrivDTO2.setTenenciaValuadaCosto("2.00");
        tenenciaPorProductoBPrivDTO2.setTenenciaValuadaHoy("44950.00");
        tenenciaPorProductoBPrivDTO2.setTenenciaValuadaReexpresada("2.00");

        TenenciaPorProductoBPrivDTO tenenciaPorProductoBPrivDTO3 = new TenenciaPorProductoBPrivDTO();
        tenenciaPorProductoBPrivDTO3.setProducto("LIQ");
        tenenciaPorProductoBPrivDTO3.setResultado("OK");
        tenenciaPorProductoBPrivDTO3.setTenenciaValuadaCosto("2.00");
        tenenciaPorProductoBPrivDTO3.setTenenciaValuadaHoy("20000.00");
        tenenciaPorProductoBPrivDTO3.setTenenciaValuadaReexpresada("2.00");

        TenenciaPorProductoBPrivDTO tenenciaPorProductoBPrivDTO4 = new TenenciaPorProductoBPrivDTO();
        tenenciaPorProductoBPrivDTO4.setProducto("CUS");
        tenenciaPorProductoBPrivDTO4.setResultado("OK");
        tenenciaPorProductoBPrivDTO4.setTenenciaValuadaCosto("2.00");
        tenenciaPorProductoBPrivDTO4.setTenenciaValuadaHoy("2.00");
        tenenciaPorProductoBPrivDTO4.setTenenciaValuadaReexpresada("2.00");

        listaTenenciaProductos.add(tenenciaPorProductoBPrivDTO);
        listaTenenciaProductos.add(tenenciaPorProductoBPrivDTO2);
        listaTenenciaProductos.add(tenenciaPorProductoBPrivDTO3);
        listaTenenciaProductos.add(tenenciaPorProductoBPrivDTO4);

        tenenciaDolares.setListaTenenciaProductos(listaTenenciaProductos);
        tenenciaPesos.setListaTenenciaProductos(listaTenenciaProductos);
        tenenciaProducto.setTenenciaDolares(tenenciaDolares);
        tenenciaProducto.setTenenciaPesos(tenenciaPesos);

        listTenenciaProd.add(tenenciaProducto);
        tenenciaDTO.setListaTenenciaPorCuenta(listTenenciaProd);
        respuestaBO.setRespuesta(tenenciaDTO);
        Respuesta<String> respuestasLegales = new Respuesta<String>();
        respuestasLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestasLegales.setRespuesta("Mensaje legal mockeado");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestasLegales);

        Mockito.when(inversionesBO.obtenerTenenciaConsolidadaPorProductoBPriv(Matchers.any(Cliente.class)))
                .thenReturn(respuestaBO);

        Respuesta<TenenciaConsolidadaBPrivView> rtaManager = new Respuesta<TenenciaConsolidadaBPrivView>();
        rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
                Matchers.any(TarjetaTenenciaConsolidadaView.class))).thenReturn(rtaManager);
        TenenciaPorProductoInView estadistica = new TenenciaPorProductoInView();
        estadistica.setEstadisticas("1");
        Respuesta<TenenciaConsolidadaBPrivView> respuesta = inversionesManager
                .obtenerTenenciaConsolidadaPorProductoBPriv(estadistica);

        Assert.assertNotNull(rtaManager);
        Assert.assertEquals(EstadoRespuesta.OK, rtaManager.getEstadoRespuesta());
    }

    @Test
    public void obtenerTenenciaPorProductoBPrivRespuestaWarning() {
        Respuesta<TenenciaConsolidadaBPrivDTO> respuestaBO = new Respuesta<TenenciaConsolidadaBPrivDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.WARNING);
        TenenciaConsolidadaBPrivDTO tenenciaDTO = new TenenciaConsolidadaBPrivDTO();
        List<TenenciaPorCuentaBPrivDTO> listTenenciaProd = new ArrayList<TenenciaPorCuentaBPrivDTO>();
        TenenciaPorCuentaBPrivDTO tenenciaProducto = new TenenciaPorCuentaBPrivDTO();
        tenenciaProducto.setNroCuenta("12345678");
        tenenciaProducto.setNroCuentaFormateado("250-1234567/8");
        listTenenciaProd.add(tenenciaProducto);
        tenenciaDTO.setListaTenenciaPorCuenta(listTenenciaProd);
        respuestaBO.setRespuesta(tenenciaDTO);
        Respuesta<String> respuestasLegales = new Respuesta<String>();
        respuestasLegales.setEstadoRespuesta(EstadoRespuesta.OK);

        
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestasLegales);

        Mockito.when(inversionesBO.obtenerTenenciaConsolidadaPorProductoBPriv(Matchers.any(Cliente.class)))
                .thenReturn(respuestaBO);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        TenenciaPorProductoInView estadistica = new TenenciaPorProductoInView();
        estadistica.setEstadisticas("1");
        Respuesta<TenenciaConsolidadaBPrivView> respuesta = inversionesManager
                .obtenerTenenciaConsolidadaPorProductoBPriv(estadistica);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());

    }

    @SuppressWarnings("rawtypes")
    @Test
    public void obtenerTenenciaPorProductoBPrivError() {
        Respuesta<TenenciaConsolidadaBPrivDTO> respuestaBO = new Respuesta<TenenciaConsolidadaBPrivDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Respuesta respuestaError = new Respuesta();
        respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Respuesta<String> respuestasLegales = new Respuesta<String>();
        respuestasLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        Mensaje mensajeError = new Mensaje();
        mensajeError.setMensaje("Mensaje Error");

        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestasLegales);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeError);
        Mockito.when(inversionesBO.obtenerTenenciaConsolidadaPorProductoBPriv(Matchers.any(Cliente.class)))
                .thenReturn(respuestaBO);

        TenenciaPorProductoInView estadistica = new TenenciaPorProductoInView();
        estadistica.setEstadisticas("1");
        Respuesta<TenenciaConsolidadaBPrivView> respuesta = inversionesManager
                .obtenerTenenciaConsolidadaPorProductoBPriv(estadistica);

        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void obtenerDetalleCuentaCustodiaPorProductoOK() {

        // When
        Respuesta<String> respuestasLegales = new Respuesta<String>();
        respuestasLegales.setEstadoRespuesta(EstadoRespuesta.OK);

        DetalleCustodiaDTO detalleCustodiaDTO = DetalleCustodiaDTOMock.armarMockDetalleCustodiaDTO();

        DetalleCustodiaInView detalleCustodiaInView = new DetalleCustodiaInView();
        detalleCustodiaInView.setEsBancaPrivada(false);
        detalleCustodiaInView.setNumeroCuenta("03549140");

        when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestasLegales);
        when(inversionesBO.obtenerCustodia(Matchers.any(Cliente.class), Matchers.any(DetalleCustodiaInView.class)))
                .thenReturn(detalleCustodiaDTO);

        // Then
        Respuesta<DetalleCustodiaView> respuesta = inversionesManager
                .obtenerDetalleCuentaCustodiaPorProducto(detalleCustodiaInView);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    @Test
    public void obtenerDetalleCuentaCustodiaPorProductoErrorLegales() {

        // When
        Respuesta<String> respuestasLegales = new Respuesta<String>();
        respuestasLegales.setEstadoRespuesta(EstadoRespuesta.ERROR);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MENSAJE ERROR");

        DetalleCustodiaInView detalleCustodiaInView = new DetalleCustodiaInView();
        detalleCustodiaInView.setEsBancaPrivada(false);
        detalleCustodiaInView.setNumeroCuenta("03549140");

        when(inversionesBO.obtenerDatosCustodiaRespuestaError(Matchers.any(Cliente.class),
                Matchers.any(DetalleCustodiaInView.class)))
                        .thenReturn(DetalleCustodiaDTOMock.armarMockDetalleCustodiaDTOError());
        when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestasLegales);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<DetalleCustodiaView> respuesta = inversionesManager
                .obtenerDetalleCuentaCustodiaPorProducto(detalleCustodiaInView);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

    }

    @Test
    public void obtenerDetalleCuentaCustodiaPorProductoErrorServicio() {

        // When
        Respuesta<String> respuestasLegales = new Respuesta<String>();
        respuestasLegales.setEstadoRespuesta(EstadoRespuesta.OK);

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MENSAJE ERROR");

        DetalleCustodiaDTO detalleCustodiaDTO = new DetalleCustodiaDTO();
        detalleCustodiaDTO.setHayError(true);

        DetalleCustodiaInView detalleCustodiaInView = new DetalleCustodiaInView();
        detalleCustodiaInView.setEsBancaPrivada(false);
        detalleCustodiaInView.setNumeroCuenta("03549140");

        when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestasLegales);
        when(inversionesBO.obtenerCustodia(Matchers.any(Cliente.class), Matchers.any(DetalleCustodiaInView.class)))
                .thenReturn(detalleCustodiaDTO);
        when(inversionesBO.obtenerDatosCustodiaRespuestaError(Matchers.any(Cliente.class),
                Matchers.any(DetalleCustodiaInView.class)))
                        .thenReturn(DetalleCustodiaDTOMock.armarMockDetalleCustodiaDTOError());
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        // Then
        Respuesta<DetalleCustodiaView> respuesta = inversionesManager
                .obtenerDetalleCuentaCustodiaPorProducto(detalleCustodiaInView);

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

    }

}
