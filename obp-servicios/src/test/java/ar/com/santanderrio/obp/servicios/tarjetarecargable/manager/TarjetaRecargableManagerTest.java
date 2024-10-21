package ar.com.santanderrio.obp.servicios.tarjetarecargable.manager;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.combos.service.DatosSelectoresService;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.bo.TarjetaRecargableBO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.dto.DatosSolicitudTarjetaRecargableDTO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.dto.DatosSolicitudTarjetaRecargableInDTO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.entities.SolicitudTarjetaRecargableOutEntity;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.manager.TarjetaRecargableManager;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.manager.impl.TarjetaRecargableManagerImpl;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.ComprobanteAltaSolicitudTarjetaRecargableView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.DatosComprobanteSolicitudTarjetaRecargableView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.DatosSolicitanteTarjetaRecargableView;

@RunWith(MockitoJUnitRunner.class)
public class TarjetaRecargableManagerTest {
    
    @InjectMocks
    private TarjetaRecargableManager tarjetaRecargableManager = new TarjetaRecargableManagerImpl();
    
    @Mock
    private TarjetaRecargableBO tarjetaRecargableBO;
    
    @Mock
    private DatosSelectoresService datosSelectoresService;

    @Mock
    private SesionParametros sesionParametros;
    
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Mock
    private AutentificacionManager autentificacionManager;
    
    @Mock
    private SesionCliente sesionCliente;
    
    @Mock
    private EstadisticaManager estadisticaManager;
    
    @Mock
    private DesafioOperacionRSA<ComprobanteAltaSolicitudTarjetaRecargableView> desafioOperacionRSA;

    @Test
    public void testObtenerDatosIniciales(){
        
        List<Opcion> opciones = obtenerOpciones();
        
        Respuesta<DatosSolicitanteTarjetaRecargableView> respuesta = new Respuesta<DatosSolicitanteTarjetaRecargableView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(datosSelectoresService.obtenerNacionalidad()).thenReturn(opciones);
        Mockito.when(datosSelectoresService.obtenerSexo()).thenReturn(opciones);
        Mockito.when(datosSelectoresService.obtenerEstadoCivil()).thenReturn(opciones);
        Mockito.when(datosSelectoresService.obtenerProvincias()).thenReturn(opciones);
        Mockito.when(datosSelectoresService.obtenerTiposDeDocumento()).thenReturn(opciones);
        
        respuesta = tarjetaRecargableManager.obtenerDatosIniciales();
        
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        Assert.assertNotNull(respuesta.getRespuesta());
    }
    
    @Test
    public void testAltaSolicitudTarjetaRecargable() throws Exception {
        Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> respuestaView = obtenerRespuestaParametrizada(EstadoRespuesta.OK, EstadoRespuesta.OK, true);
        Assert.assertNotNull(respuestaView);
    }
    
    @Test
    public void testAltaSolicitudTarjetaRecargableError() throws Exception {
        Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> respuestaView = obtenerRespuestaParametrizada(EstadoRespuesta.ERROR, EstadoRespuesta.ERROR, false);
        Assert.assertNotNull(respuestaView);
    }
    
    @Test
    public void testAltaSolicitudTarjetaRecargableErrorConAuth() throws Exception {
        Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> respuestaView = obtenerRespuestaParametrizada(EstadoRespuesta.OK, EstadoRespuesta.ERROR, false);
        Assert.assertNotNull(respuestaView);
    }
    
    @Test
    public void testAltaSolicitudTarjetaRecargableWarning() throws Exception {
        Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> respuestaView = obtenerRespuestaParametrizada(EstadoRespuesta.OK, EstadoRespuesta.WARNING, false);
        Assert.assertNotNull(respuestaView);
    }

    @Test
    public void generarComprobante() {
        Reporte reporte = new Reporte();
        reporte.setTipoArchivo(TipoArchivoEnum.PDF);
        reporte.setNombre("NOMBRE.PDF");
        
        Respuesta<Reporte> respuestaBO = new Respuesta<Reporte>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaBO.setRespuesta(reporte);
        
        
        Mockito.when(tarjetaRecargableBO.comprobanteSolicitudTarjetaRecargable(Matchers.any(DatosComprobanteSolicitudTarjetaRecargableView.class))).thenReturn(respuestaBO);
        Mockito.when(sesionParametros.getDatosComprobanteSolicitudTarjetaRecargableView()).thenReturn(new DatosComprobanteSolicitudTarjetaRecargableView());
        
        Respuesta<ReporteView> respuesta = new Respuesta<ReporteView>(); 
        respuesta = tarjetaRecargableManager.comprobanteSolicitudTarjetaRecargable();
        
        Assert.assertNotNull(respuesta);
    }
    
    private SolicitudTarjetaRecargableOutEntity obtenerTarjetaRecargableOutEntity() {
        SolicitudTarjetaRecargableOutEntity tarjetaRecargableOutEntity = new SolicitudTarjetaRecargableOutEntity();
        tarjetaRecargableOutEntity.setCodRetorno("1");
        tarjetaRecargableOutEntity.setDescError("");
        tarjetaRecargableOutEntity.setNroGestion("1");
        return tarjetaRecargableOutEntity;
    }
    
    private Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> obtenerRespuestaParametrizada(EstadoRespuesta estadoRespuestaAutentificacion, 
            EstadoRespuesta estadoTarjetaRecargable, boolean isClienteGold) throws IllegalAccessException {
        Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> respuestaView = new Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView>();
        
        FieldUtils.writeField(tarjetaRecargableManager, "valorDesafio", 10,
                true);
      
        Cliente cliente = obtenerCliente(isClienteGold);
        
        ComprobanteAltaSolicitudTarjetaRecargableView comprobanteAltaTarjRecargableView = obtenerComprobanteAltaSolicitudTarjetaRecargableView();
        
        
        SolicitudTarjetaRecargableOutEntity tarjetaRecargableOutEntity = obtenerTarjetaRecargableOutEntity();
        
        DatosSolicitudTarjetaRecargableDTO tarjetaRecargableDTO = new DatosSolicitudTarjetaRecargableDTO(tarjetaRecargableOutEntity);
        
        Respuesta<DatosSolicitudTarjetaRecargableDTO> respuestaAltaTarjetaRecargable = new Respuesta<DatosSolicitudTarjetaRecargableDTO>();
        respuestaAltaTarjetaRecargable.setEstadoRespuesta(estadoTarjetaRecargable);
        respuestaAltaTarjetaRecargable.setRespuesta(tarjetaRecargableDTO);
        
        Respuesta<AutentificacionDTO> respuestaAutentificacion = new Respuesta<AutentificacionDTO>();
        respuestaAutentificacion.setEstadoRespuesta(estadoRespuestaAutentificacion);
        
        Mockito.when(autentificacionManager.analizarRSAyObtenerAutenticacionValida(Matchers.any(AutentificacionDTO.class))).thenReturn(respuestaAutentificacion);
        Mockito.when(tarjetaRecargableBO.altaSolicitudTarjetaRecargable(Matchers.any(DatosSolicitudTarjetaRecargableInDTO.class))).thenReturn(respuestaAltaTarjetaRecargable);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

        Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> respuestaRSA = new Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView>();
        respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
        when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(ComprobanteAltaSolicitudTarjetaRecargableView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);

        respuestaView = tarjetaRecargableManager.altaSolicitudTarjetaRecargable(comprobanteAltaTarjRecargableView);
        return respuestaView;
    }
    
    private ComprobanteAltaSolicitudTarjetaRecargableView obtenerComprobanteAltaSolicitudTarjetaRecargableView() {
        ComprobanteAltaSolicitudTarjetaRecargableView comprobanteAltaTarjRecargableView = new ComprobanteAltaSolicitudTarjetaRecargableView();

        return comprobanteAltaTarjRecargableView;
    }

    private Cliente obtenerCliente(Boolean isTieneCuentaGold){
        
        Cliente cliente = new Cliente();
        cliente.setNup("12345");
        cliente.setTipoDocumento("N");
        cliente.setDni("123456");
        cliente.setApellido1("apellido1");
        cliente.setApellido2("apellido2");
        cliente.setNombre("nombre");
        cliente.setFechaNacimiento("fechaNacimiento");
        
        if(isTieneCuentaGold){
            
            List<Cuenta> cuentas = new ArrayList<Cuenta>();
            Cuenta cuenta = new Cuenta();
            cuenta.setTipoCuenta("2");
            cuenta.setCodigoPaquete("100");
            cuenta.setCodigoAplicacion("codAplicacion");
            cuenta.setNroSucursal("000");
            cuenta.setNroCuentaProducto("nroCuentaProducto");
            cuenta.setMonedaAltair("monedaAltair");
            cuenta.setNroOrdenFirmante("nroOrdenFirmante");
            cuenta.setProductoAltair("productoAltair");
            cuenta.setSubproductoAltair("subproductoAltair");
            cuentas.add(cuenta);
            cliente.setCuentas(cuentas);
        }        
        
        return cliente;
    }
    
    private List<Opcion> obtenerOpciones() {
        List<Opcion> opciones = new ArrayList<Opcion>();
        Opcion opcion = new Opcion();
        opcion.setId("1");
        opcion.setOpcion("opcion");
        opciones.add(opcion);
        return opciones;
    }
}
