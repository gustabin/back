/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.manager;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

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

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.rsa.ActionCode;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RescateDesdeGrillaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.FondoBO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.RescateBO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaTituloDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentasConsultaFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateBPrivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateBPrivInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarRescateInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FondoResumidoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimulacionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularRescateInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularRescateOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RescateFondoRsa;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.sei.RescateSEIImpl;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfiguracionRescateBPrivInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfiguracionRescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfiguracionRescateOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateBPrivInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarRescateView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateDesdeGrilla;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.RescateView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionRescateInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionRescateOutView;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;

/**
 * The Class FondoManagerTest.
 *
 * @author
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore
public class RescateManagerTest {

    /** The fondo manager. */
    @InjectMocks
    private RescateManagerImpl rescateManager;

    /** The rescate SEI. */
    @Spy
    private RescateSEIImpl rescateSEI;

    /** The fondo BO. */
    @Mock
    private CuentaManager cuentaManager;

    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The legal BO. */
    @Mock
    private LegalBO legalBO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;
    
    /** The mensaje BO. */
    @Mock
    private FondoBO fondoBO;

    /** The rescate BO. */
    @Mock
    private RescateBO rescateBO;

    @Mock
    private EstadisticaManager estadisticaManager;
    
    @Mock
    private SesionParametros sesionParametros;
    
    @Mock
    private Validator validator;
    
    @Mock
    private RsaManager rsaManager;
    
    /**
     * Inits the.
     */
    @Before
    public void init() {
        this.rescateManager.validatorInit();
    }

    /**
     * Suscribir.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void configuracionRescateTestOK() throws IllegalAccessException {
        Respuesta<CuentasView> respuestaCuentaManager = new Respuesta<CuentasView>();
        respuestaCuentaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        CuentasView cuentasView = new CuentasView();
        respuestaCuentaManager.setRespuesta(cuentasView);
        List<CuentasAdhesionDebitoView> cuentas = new ArrayList<CuentasAdhesionDebitoView>();
        cuentasView.setCuentas(cuentas);
        CuentasAdhesionDebitoView cuenta = new CuentasAdhesionDebitoView();
        cuentas.add(cuenta);
        when(cuentaManager.getCuentasSaldoPorMoneda(Matchers.any(String.class))).thenReturn(respuestaCuentaManager);

        Respuesta<String> respuestaLegal = new Respuesta<String>();
        respuestaLegal.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegal.setRespuesta("Mensaje legal mockeado");
        when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegal);

        Mensaje respuestaMensaje = new Mensaje();
        respuestaMensaje.setMensaje("Mensaje minimo maximo mockeado");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(respuestaMensaje);

        Respuesta<ConfiguracionRescateOutView> respuestaConfiguracion = new Respuesta<ConfiguracionRescateOutView>();
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
                Matchers.any(ConfiguracionRescateOutView.class))).thenReturn(respuestaConfiguracion);

        ConfiguracionRescateInView viewRequest = new ConfiguracionRescateInView();
        viewRequest.setMoneda("$");
        // HAGO QUE EL SEI LLAME AL MANAGER REAL
        FieldUtils.writeField(rescateSEI, "rescateManager", rescateManager, true);

        Respuesta<ConfiguracionRescateOutView> respuestaManager = rescateSEI.configuracionRescate(viewRequest);

        Assert.assertNotNull(respuestaManager);
        // TODO: corregir test
        // Assert.assertEquals(respuestaConfiguracion, respuestaManager);

    }

    /**
     * Configuracion rescate test warning.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void configuracionRescateTestWarning() {
        Respuesta<CuentasView> respuestaCuentaManager = new Respuesta<CuentasView>();
        respuestaCuentaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        CuentasView cuentasView = new CuentasView();
        respuestaCuentaManager.setRespuesta(cuentasView);
        List<CuentasAdhesionDebitoView> cuentas = new ArrayList<CuentasAdhesionDebitoView>();
        cuentasView.setCuentas(cuentas);
        when(cuentaManager.getCuentasSaldoPorMoneda(Matchers.any(String.class))).thenReturn(respuestaCuentaManager);

        Respuesta<String> respuestaLegal = new Respuesta<String>();
        respuestaLegal.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegal.setRespuesta("Mensaje legal mockeado");
        when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegal);

        Mensaje respuestaMensaje = new Mensaje();
        respuestaMensaje.setMensaje("Mensaje minimo maximo mockeado");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(respuestaMensaje);

        Respuesta<ConfiguracionRescateOutView> respuestaConfiguracion = new Respuesta<ConfiguracionRescateOutView>();
        Mockito.when(respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(Matchers.any(Class.class),
                Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaConfiguracion);

        ConfiguracionRescateInView viewRequest = new ConfiguracionRescateInView();

        viewRequest.setMoneda("$");
        Respuesta<ConfiguracionRescateOutView> respuestaManager = rescateManager.configuracionRescate(viewRequest);

        Assert.assertNotNull(respuestaManager);
        Assert.assertEquals(respuestaConfiguracion, respuestaManager);

    }

    /**
     * Configuracion rescate test error.
     */
    @Test
    public void configuracionRescateTestError() {
        Respuesta<CuentasView> respuestaCuentaManager = new Respuesta<CuentasView>();
        respuestaCuentaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        CuentasView cuentasView = new CuentasView();
        respuestaCuentaManager.setRespuesta(cuentasView);
        List<CuentasAdhesionDebitoView> cuentas = new ArrayList<CuentasAdhesionDebitoView>();
        cuentasView.setCuentas(cuentas);
        when(cuentaManager.getCuentasSaldoPorMoneda(Matchers.any(String.class))).thenReturn(respuestaCuentaManager);

        Respuesta<String> respuestaLegal = new Respuesta<String>();
        respuestaLegal.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaLegal.setRespuesta("Mensaje legal mockeado");
        when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegal);

        Respuesta<Object> respuestaConfiguracion = new Respuesta<Object>();
        respuestaConfiguracion.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(String.class), Matchers.any(TipoError.class),
                Matchers.any(String.class))).thenReturn(respuestaConfiguracion);

        ConfiguracionRescateInView viewRequest = new ConfiguracionRescateInView();

        viewRequest.setMoneda("$");
        Respuesta<ConfiguracionRescateOutView> respuestaManager = rescateManager.configuracionRescate(viewRequest);

        Assert.assertNotNull(respuestaManager);
        Assert.assertEquals(respuestaConfiguracion, respuestaManager);

    }

    /**
     * Simular rescate fondo test ok.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void simularRescateFondoTestOk() throws IllegalAccessException {

        Respuesta<SimularRescateOutDTO> respuestaBO = new Respuesta<SimularRescateOutDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        SimularRescateOutDTO respuesta = new SimularRescateOutDTO();
        respuestaBO.setRespuesta(respuesta);
        String legal = "legal";
        respuesta.setLegales(legal);
        String cuotaPartes = "cuotaPartes";
        respuesta.setCuotaPartes(cuotaPartes);
        String importeRescateComision = "importeRescateComision";
        respuesta.setImporteRescateComision(importeRescateComision);
        String importeRescateNeto = "importeRescateNeto";
        respuesta.setImporteRescateNeto(importeRescateNeto);
        when(rescateBO.simularRescateFondo(Matchers.any(SimularRescateInDTO.class), Matchers.any(Cliente.class)))
                .thenReturn(respuestaBO);

        FieldUtils.writeField(rescateSEI, "rescateManager", rescateManager, true);

        Cliente clienteSesion = new Cliente();
        when(sesionCliente.getCliente()).thenReturn(clienteSesion);

        SimulacionRescateInView viewRequest = new SimulacionRescateInView();
        String nroCuentaTitulos = "12345678";
        Respuesta<Object> respuestaSimulacion = new Respuesta<Object>();
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
                Matchers.any(SimulacionRescateOutView.class))).thenReturn(respuestaSimulacion);
        
        viewRequest.setNroCuentaTitulos(nroCuentaTitulos);
        viewRequest.setImporteNeto("100.00");
        viewRequest.setNumeroCuenta("1000/02");
        String codigoFondo = "007";
		viewRequest.setCodigoFondo(codigoFondo);
        String tipoCuenta = "CU";
		viewRequest.setTipoCuenta(tipoCuenta);
		String sucursalCuenta = "123";
		viewRequest.setSucursalCuenta(sucursalCuenta);
		String numeroCuenta = "1234567/8";
		viewRequest.setNumeroCuenta(numeroCuenta);
		String moneda = "$";
		viewRequest.setMoneda(moneda);
        Respuesta<SimulacionRescateOutView> pepe = rescateSEI.simularRescateFondo(viewRequest);

        Assert.assertNotNull(pepe);
    }
    
    /**
     * Simular rescate fondo test ok.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void simularRescateFondoBPrivTestOk() throws IllegalAccessException {

        Respuesta<SimularRescateOutDTO> respuestaBO = new Respuesta<SimularRescateOutDTO>();
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        SimularRescateOutDTO respuesta = new SimularRescateOutDTO();
        respuestaBO.setRespuesta(respuesta);
        String legal = "legal";
        respuesta.setLegales(legal);
        String cuotaPartes = "cuotaPartes";
        respuesta.setCuotaPartes(cuotaPartes);
        String importeRescateComision = "importeRescateComision";
        respuesta.setImporteRescateComision(importeRescateComision);
        String importeRescateNeto = "importeRescateNeto";
        respuesta.setImporteRescateNeto(importeRescateNeto);
        when(rescateBO.simularRescateFondoBPriv(Matchers.any(SimulacionInDTO.class), Matchers.any(Cliente.class)))
                .thenReturn(respuestaBO);

        FieldUtils.writeField(rescateSEI, "rescateManager", rescateManager, true);

        Cliente clienteSesion = new Cliente();
        when(sesionCliente.getCliente()).thenReturn(clienteSesion);

        SimulacionRescateInView viewRequest = new SimulacionRescateInView();
        String nroCuentaTitulos = "12345678";
        Respuesta<Object> respuestaSimulacion = new Respuesta<Object>();
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
                Matchers.any(SimulacionRescateOutView.class))).thenReturn(respuestaSimulacion);
        
        viewRequest.setNroCuentaTitulos(nroCuentaTitulos);
        viewRequest.setImporteNeto("100.00");
        viewRequest.setNumeroCuenta("1000/02");
        String codigoFondo = "007";
		viewRequest.setCodigoFondo(codigoFondo);
        String tipoCuenta = "CU";
		viewRequest.setTipoCuenta(tipoCuenta);
		String sucursalCuenta = "123";
		viewRequest.setSucursalCuenta(sucursalCuenta);
		String numeroCuenta = "1234567/8";
		viewRequest.setNumeroCuenta(numeroCuenta);
		String moneda = "$";
		viewRequest.setMoneda(moneda);
        Respuesta<SimulacionRescateOutView> pepe = rescateSEI.simularRescateFondoBPriv(viewRequest);

        Assert.assertNotNull(pepe);
    }

    /**
     * configuracionRescateBPriv.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void configuracionRescateBPrivTestOK() throws IllegalAccessException {
        Respuesta<CuentasAdhesionDebitoView> respuestaCuentaManager = new Respuesta<CuentasAdhesionDebitoView>();
        respuestaCuentaManager.setEstadoRespuesta(EstadoRespuesta.OK);

        Respuesta<String> respuestaLegal = new Respuesta<String>();
        respuestaLegal.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegal.setRespuesta("Mensaje legal mockeado");
        when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegal);

        CuentasAdhesionDebitoView cuentasView = new CuentasAdhesionDebitoView();
        
        respuestaCuentaManager.setRespuesta(cuentasView);
        List<CuentasAdhesionDebitoView> cuentas = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView cuenta = new CuentasAdhesionDebitoView();
        cuentas.add(cuenta);
        when(fondoBO.obtenerSaldosCuentaOperativa(Matchers.any(String.class), Matchers.any(Cliente.class),Matchers.anyBoolean())).thenReturn(respuestaCuentaManager);

        Mensaje respuestaMensaje = new Mensaje();
        respuestaMensaje.setMensaje("mensaje error importe superior suscripcion mockeado");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(respuestaMensaje);
        Respuesta<ConfiguracionRescateOutView> respuestaConfiguracion = new Respuesta<ConfiguracionRescateOutView>();
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
                Matchers.any(ConfiguracionRescateOutView.class))).thenReturn(respuestaConfiguracion);

        ConfiguracionRescateBPrivInView viewRequest = new ConfiguracionRescateBPrivInView();
        viewRequest.setSucursal("250");
        viewRequest.setCuentaOperativa("7003523508");
        
        Cliente clienteSesion = new Cliente();
        when(sesionCliente.getCliente()).thenReturn(clienteSesion);

        FieldUtils.writeField(rescateSEI, "rescateManager", rescateManager, true);
        Respuesta<ConfiguracionRescateOutView> respuestaManager = rescateSEI.configuracionRescateBPriv(viewRequest);

        Assert.assertNotNull(respuestaManager);
        Assert.assertEquals(respuestaConfiguracion, respuestaManager);

    }

    /**
     * Configuracion rescate B priv test error.
     */
    @Test
    public void configuracionRescateBPrivTestError() {
        Respuesta<CuentasView> respuestaCuentaManager = new Respuesta<CuentasView>();
        respuestaCuentaManager.setEstadoRespuesta(EstadoRespuesta.OK);

        Respuesta<String> respuestaLegal = new Respuesta<String>();
        respuestaLegal.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaLegal.setRespuesta("Mensaje legal mockeado");
        when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegal);

        CuentasView cuentasView = new CuentasView();
        respuestaCuentaManager.setRespuesta(cuentasView);
        List<CuentasAdhesionDebitoView> cuentas = new ArrayList<CuentasAdhesionDebitoView>();
        cuentasView.setCuentas(cuentas);
      //  when(cuentaManager.getCuentaPrivada(Matchers.any(String.class))).thenReturn(respuestaCuentaManager);

        Respuesta<Object> respuestaConfiguracion = new Respuesta<Object>();
        respuestaConfiguracion.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(String.class), Matchers.any(TipoError.class),
                Matchers.any(String.class))).thenReturn(respuestaConfiguracion);

        ConfiguracionRescateBPrivInView viewRequest = new ConfiguracionRescateBPrivInView();
        viewRequest.setSucursal("250");
        viewRequest.setCuentaOperativa("7003523508");
        Respuesta<ConfiguracionRescateOutView> respuestaManager = rescateManager.configuracionRescateBPriv(viewRequest);

        Assert.assertNotNull(respuestaManager);
        Assert.assertEquals(respuestaConfiguracion, respuestaManager);

    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void verComprobanteBPrivTest() throws IllegalAccessException{
        Respuesta<String> respuestaLegal = new Respuesta<String>();
        respuestaLegal.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegal.setRespuesta("Mensaje legal mockeado");
        when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegal);
        
		boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaEstadistica);

		Respuesta<ConfiguracionRescateOutView> respuestaConfiguracion = new Respuesta<ConfiguracionRescateOutView>();
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),Matchers.any(ConfiguracionRescateOutView.class))).thenReturn(respuestaConfiguracion);

        FieldUtils.writeField(rescateSEI, "rescateManager", rescateManager, true);
        
        when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        
        when(sesionCliente.getCliente()).thenReturn(new Cliente());
        
        DatosComprobante viewRequest = new DatosComprobante();
        viewRequest.setNumeroComprobante("87654");
		Respuesta<ComprobanteSuscripcionView> respuestaComprobante = rescateSEI.verComprobanteRescateBPriv(viewRequest);
        
        Assert.assertNotNull(respuestaComprobante);
        Assert.assertEquals(respuestaConfiguracion, respuestaComprobante);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void verComprobanteTest() throws IllegalAccessException{
        Respuesta<String> respuestaLegal = new Respuesta<String>();
        respuestaLegal.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegal.setRespuesta("Mensaje legal mockeado");
        when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuestaLegal);
        
		boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaEstadistica);

		Respuesta<ConfiguracionRescateOutView> respuestaConfiguracion = new Respuesta<ConfiguracionRescateOutView>();
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),Matchers.any(ConfiguracionRescateOutView.class))).thenReturn(respuestaConfiguracion);

        FieldUtils.writeField(rescateSEI, "rescateManager", rescateManager, true);
        
        when(sesionParametros.getRegistroSession()).thenReturn(new RegistroSesion());
        
        when(sesionCliente.getCliente()).thenReturn(new Cliente());
        
        DatosComprobante viewRequest = new DatosComprobante();
        viewRequest.setNumeroComprobante("87654");
		Respuesta<ComprobanteSuscripcionView> respuestaComprobante = rescateSEI.verComprobanteRescate(viewRequest);
        
        Assert.assertNotNull(respuestaComprobante);
        Assert.assertEquals(respuestaConfiguracion, respuestaComprobante);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void finalizarRescateOkTest(){

    	Respuesta<ActionCode> respuestaRsaManager = Mockito.mock(Respuesta.class);
        Mockito.when(rsaManager.analizar(Matchers.any(RescateFondoRsa.class), null)).thenReturn(respuestaRsaManager);
        
    	Set<ConstraintViolation<FinalizarRescateInView>> respValidator = new HashSet<ConstraintViolation<FinalizarRescateInView>>();
		Mockito.when(validator.validate(Matchers.any(FinalizarRescateInView.class))).thenReturn(respValidator);

		when(sesionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapFinalizarRescateFondos()));

		Cliente clienteSesion = new Cliente();
        when(sesionCliente.getCliente()).thenReturn(clienteSesion);
        
		Respuesta<FinalizarRescateDTO> respuestaMockBO = new Respuesta<FinalizarRescateDTO>();
		respuestaMockBO.setEstadoRespuesta(EstadoRespuesta.OK);
		FinalizarRescateDTO rta = new FinalizarRescateDTO();
		rta.setImporteRescateNeto("1234");
		rta.setFechaHora("08/01/2018");
		rta.setTotalCuotasPartes("1234");
		rta.setNroRescate("123456789");
		respuestaMockBO.setRespuesta(rta);
		when(rescateBO.finalizarRescate(Matchers.any(FinalizarRescateInDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaMockBO);

		boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaEstadistica);

        Respuesta<FinalizarRescateView> respuestaConfiguracion = new Respuesta<FinalizarRescateView>();
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(FinalizarRescateView.class))).thenReturn(respuestaConfiguracion);
    	FinalizarRescateInView viewRequest = new FinalizarRescateInView();
    	String codigoFondo = "007";
		viewRequest.setCodigoFondo(codigoFondo );
    	String cuentaTitulo = "1234567";
		viewRequest.setCuentaTitulo(cuentaTitulo);
    	String tipoCtaCred = "CU";
		viewRequest.setTipoCtaCred(tipoCtaCred);
    	String sucursalCtaCred = "250";
		viewRequest.setSucursalCtaCred(sucursalCtaCred);
    	String numeroCtaCred = "7654321";
		viewRequest.setNumeroCtaCred(numeroCtaCred);
    	String moneda = "$";
		viewRequest.setMoneda(moneda);
    	String importe = "10500";
		viewRequest.setImporte(importe);
    	String importeRescateComision = "12345678901234";
		viewRequest.setImporteRescateComision(importeRescateComision);//14
    	String importeRescateNeto = "12345678901234";
		viewRequest.setImporteRescateNeto(importeRescateNeto);//14
    	String cuotaPartes = "50";
		viewRequest.setCuotaPartes(cuotaPartes);
    	
    	Respuesta<FinalizarRescateView> respuestaManager = rescateManager.finalizarRescate(viewRequest);
    	
    	Assert.assertNotNull(respuestaManager);
        Assert.assertEquals(respuestaConfiguracion, respuestaManager);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void obtenerFondosSuscriptosTestOK(){

    	Set<ConstraintViolation<RescateInView>> respValidator = new HashSet<ConstraintViolation<RescateInView>>();
		Mockito.when(validator.validate(Matchers.any(RescateInView.class))).thenReturn(respValidator);
    	
    	RescateInView viewRequest = new RescateInView();
    	viewRequest.setTipoBanca("BP");
    	ArrayList<CuentaTituloView> cuentasTituloView = new ArrayList<CuentaTituloView>();
    	CuentaTituloView cuentaTitulo = new CuentaTituloView();
    	String nroCuenta = "12345678";
		cuentaTitulo.setNroCuenta(nroCuenta);
    	String sucursal = "103";
		cuentaTitulo.setSucursal(sucursal);
    	String nroCuentaFormateado = "1234567/7";
		cuentaTitulo.setNroCuentaFormateado(nroCuentaFormateado);
    	String cuentaOperativa = "765432";
		cuentaTitulo.setCuentaOperativa(cuentaOperativa);
    	cuentaTitulo.setIntervinientes(new ArrayList<Interviniente>());
		cuentasTituloView.add(cuentaTitulo);
    	viewRequest.setCuentasTitulo(cuentasTituloView);
    	
    	Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
    	CuentasConsultaFondoDTO cuentasDTO = new CuentasConsultaFondoDTO();
    	String cabeceraStack = "Cabecera Stack";
		cuentasDTO.setCabeceraStack(cabeceraStack);
    	String tipoBanca = "Tipo Banca";
		cuentasDTO.setTipoBanca(tipoBanca);
    	
    	List<CuentaTituloDTO> cuentasTituloDTO = new ArrayList<CuentaTituloDTO>();
    	CuentaTituloDTO ctaTitDTO = new CuentaTituloDTO();
		cuentasTituloDTO.add(ctaTitDTO);
		cuentasDTO.setCuentasTitulo(cuentasTituloDTO);
		
		ctaTitDTO.setNroCuenta(nroCuentaFormateado);
		ctaTitDTO.setSucursal(sucursal);
		ctaTitDTO.setNroCuentaFormateado(nroCuentaFormateado);
		ctaTitDTO.setCuentaOperativa(cuentaOperativa);
		String cuentaOperativaSinFormatear = "8765432/1";
		ctaTitDTO.setCuentaOperativaSinFormatear(cuentaOperativaSinFormatear);
		List<Interviniente> intervinientes = new ArrayList<Interviniente>();
		ctaTitDTO.setIntervinientes(intervinientes);
		
		List<FondoResumidoDTO> fondosSuscriptos = new ArrayList<FondoResumidoDTO>();
		FondoResumidoDTO fondoDTO = new FondoResumidoDTO();
		fondosSuscriptos.add(fondoDTO);
		
		String importeMaximo = "99999";
		fondoDTO.setImporteMaximo(importeMaximo);
		String importeMinimo = "1";
		fondoDTO.setImporteMinimo(importeMinimo);
		String moneda = "ARS";
		fondoDTO.setMoneda(moneda);
		String nombreFondo = "Nombre fondo";
		fondoDTO.setNombreFondo(nombreFondo);
		String saldo = "125000";
		fondoDTO.setSaldo(saldo);
		String codigoFondo = "007";
		fondoDTO.setCodigoFondo(codigoFondo);
		String plazoEfectivo = "72";
		fondoDTO.setPlazoEfectivo(plazoEfectivo);
		String idMensajeGastos = "10056";
		fondoDTO.setIdMensajeGastos(idMensajeGastos);
		
		ctaTitDTO.setFondosSuscriptos(fondosSuscriptos);
    	
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaBO.setRespuesta(cuentasDTO);
		when(rescateBO.obtenerFondosSuscriptos(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
    	
		Respuesta<RescateView> respuesta = new Respuesta<RescateView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(RescateView.class))).thenReturn(respuesta);
		
		Respuesta<RescateView> rtaManager = rescateManager.obtenerFondosSuscriptos(viewRequest);
		Assert.assertNotNull(rtaManager);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void obtenerFondosSuscriptosTestWarning(){

    	Set<ConstraintViolation<RescateInView>> respValidator = new HashSet<ConstraintViolation<RescateInView>>();
		Mockito.when(validator.validate(Matchers.any(RescateInView.class))).thenReturn(respValidator);
    	
    	RescateInView viewRequest = new RescateInView();
    	viewRequest.setTipoBanca("BP");
    	ArrayList<CuentaTituloView> cuentasTituloView = new ArrayList<CuentaTituloView>();
    	CuentaTituloView cuentaTitulo = new CuentaTituloView();
    	String nroCuenta = "12345678";
		cuentaTitulo.setNroCuenta(nroCuenta);
    	String sucursal = "103";
		cuentaTitulo.setSucursal(sucursal);
    	String nroCuentaFormateado = "1234567/7";
		cuentaTitulo.setNroCuentaFormateado(nroCuentaFormateado);
    	String cuentaOperativa = "765432";
		cuentaTitulo.setCuentaOperativa(cuentaOperativa);
    	cuentaTitulo.setIntervinientes(new ArrayList<Interviniente>());
		cuentasTituloView.add(cuentaTitulo);
    	viewRequest.setCuentasTitulo(cuentasTituloView);
    	
    	Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta item = new ItemMensajeRespuesta("Mensaje respuesta");
		itemMensajeRespuesta.add(item);
		respuestaBO.setItemMensajeRespuesta(itemMensajeRespuesta);
		when(rescateBO.obtenerFondosSuscriptos(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
    	
		Respuesta<RescateView> respuesta = new Respuesta<RescateView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(Class.class), Matchers.any(RescateView.class), Matchers.anyListOf(ItemMensajeRespuesta.class))).thenReturn(respuesta);
		
		Respuesta<RescateView> rtaManager = rescateManager.obtenerFondosSuscriptos(viewRequest);
		Assert.assertNotNull(rtaManager);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void obtenerFondosSuscriptosTestError(){

    	Set<ConstraintViolation<RescateInView>> respValidator = new HashSet<ConstraintViolation<RescateInView>>();
		Mockito.when(validator.validate(Matchers.any(RescateInView.class))).thenReturn(respValidator);
    	
    	RescateInView viewRequest = new RescateInView();
    	viewRequest.setTipoBanca("BP");
    	ArrayList<CuentaTituloView> cuentasTituloView = new ArrayList<CuentaTituloView>();
    	CuentaTituloView cuentaTitulo = new CuentaTituloView();
    	String nroCuenta = "12345678";
		cuentaTitulo.setNroCuenta(nroCuenta);
    	String sucursal = "103";
		cuentaTitulo.setSucursal(sucursal);
    	String nroCuentaFormateado = "1234567/7";
		cuentaTitulo.setNroCuentaFormateado(nroCuentaFormateado);
    	String cuentaOperativa = "765432";
		cuentaTitulo.setCuentaOperativa(cuentaOperativa);
    	cuentaTitulo.setIntervinientes(new ArrayList<Interviniente>());
		cuentasTituloView.add(cuentaTitulo);
    	viewRequest.setCuentasTitulo(cuentasTituloView);
    	
    	Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta item = new ItemMensajeRespuesta("Mensaje respuesta");
		item.setTipoError("tipo error");
		itemMensajeRespuesta.add(item);
		respuestaBO.setItemMensajeRespuesta(itemMensajeRespuesta);
		when(rescateBO.obtenerFondosSuscriptos(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);

		boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaEstadistica);
		
		Respuesta<RescateView> respuesta = new Respuesta<RescateView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyString(), Matchers.anyString())).thenReturn(respuesta);
		
		Respuesta<RescateView> rtaManager = rescateManager.obtenerFondosSuscriptos(viewRequest);
		Assert.assertNotNull(rtaManager);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerFondosSuscriptosTestErrorValidacionCampos(){

    	Set<ConstraintViolation<RescateInView>> respValidator = new HashSet<ConstraintViolation<RescateInView>>();
		Mockito.when(validator.validate(Matchers.any(RescateInView.class))).thenReturn(respValidator);
		@SuppressWarnings("rawtypes")
        Respuesta respuesta = new Respuesta<RescateView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(String.class), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuesta);
        
    	RescateInView viewRequest = new RescateInView();
		Respuesta<RescateView> rtaManager = rescateManager.obtenerFondosSuscriptos(viewRequest);
		Assert.assertNotNull(rtaManager);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void obtenerFondosSuscriptosBPrivTestOK(){

    	Set<ConstraintViolation<RescateInView>> respValidator = new HashSet<ConstraintViolation<RescateInView>>();
		Mockito.when(validator.validate(Matchers.any(RescateInView.class))).thenReturn(respValidator);
    	
    	RescateInView viewRequest = new RescateInView();
    	viewRequest.setTipoBanca("BP");
    	ArrayList<CuentaTituloView> cuentasTituloView = new ArrayList<CuentaTituloView>();
    	CuentaTituloView cuentaTitulo = new CuentaTituloView();
    	String nroCuenta = "12345678";
		cuentaTitulo.setNroCuenta(nroCuenta);
    	String sucursal = "103";
		cuentaTitulo.setSucursal(sucursal);
    	String nroCuentaFormateado = "1234567/7";
		cuentaTitulo.setNroCuentaFormateado(nroCuentaFormateado);
    	String cuentaOperativa = "765432";
		cuentaTitulo.setCuentaOperativa(cuentaOperativa);
    	cuentaTitulo.setIntervinientes(new ArrayList<Interviniente>());
		cuentasTituloView.add(cuentaTitulo);
    	viewRequest.setCuentasTitulo(cuentasTituloView);
    	
    	Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
    	CuentasConsultaFondoDTO cuentasDTO = new CuentasConsultaFondoDTO();
    	String cabeceraStack = "Cabecera Stack";
		cuentasDTO.setCabeceraStack(cabeceraStack);
    	String tipoBanca = "Tipo Banca";
		cuentasDTO.setTipoBanca(tipoBanca);
    	
    	List<CuentaTituloDTO> cuentasTituloDTO = new ArrayList<CuentaTituloDTO>();
    	CuentaTituloDTO ctaTitDTO = new CuentaTituloDTO();
		cuentasTituloDTO.add(ctaTitDTO);
		cuentasDTO.setCuentasTitulo(cuentasTituloDTO);
		
		ctaTitDTO.setNroCuenta(nroCuentaFormateado);
		ctaTitDTO.setSucursal(sucursal);
		ctaTitDTO.setNroCuentaFormateado(nroCuentaFormateado);
		ctaTitDTO.setCuentaOperativa(cuentaOperativa);
		String cuentaOperativaSinFormatear = "8765432/1";
		ctaTitDTO.setCuentaOperativaSinFormatear(cuentaOperativaSinFormatear);
		List<Interviniente> intervinientes = new ArrayList<Interviniente>();
		ctaTitDTO.setIntervinientes(intervinientes);
		
		List<FondoResumidoDTO> fondosSuscriptos = new ArrayList<FondoResumidoDTO>();
		FondoResumidoDTO fondoDTO = new FondoResumidoDTO();
		fondosSuscriptos.add(fondoDTO);
		
		String importeMaximo = "99999";
		fondoDTO.setImporteMaximo(importeMaximo);
		String importeMinimo = "1";
		fondoDTO.setImporteMinimo(importeMinimo);
		String moneda = "ARS";
		fondoDTO.setMoneda(moneda);
		String nombreFondo = "Nombre fondo";
		fondoDTO.setNombreFondo(nombreFondo);
		String saldo = "125000";
		fondoDTO.setSaldo(saldo);
		String codigoFondo = "007";
		fondoDTO.setCodigoFondo(codigoFondo);
		String plazoEfectivo = "72";
		fondoDTO.setPlazoEfectivo(plazoEfectivo);
		String idMensajeGastos = "10056";
		fondoDTO.setIdMensajeGastos(idMensajeGastos);
		
		ctaTitDTO.setFondosSuscriptos(fondosSuscriptos);
    	
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaBO.setRespuesta(cuentasDTO);
		when(rescateBO.obtenerFondosSuscriptosBPriv(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
    	
		Respuesta<RescateView> respuesta = new Respuesta<RescateView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(RescateView.class))).thenReturn(respuesta);
		
		Respuesta<RescateView> rtaManager = rescateManager.obtenerFondosSuscriptosBPriv(viewRequest);
		Assert.assertNotNull(rtaManager);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void obtenerFondosSuscriptosBPrivTestWarning(){

    	Set<ConstraintViolation<RescateInView>> respValidator = new HashSet<ConstraintViolation<RescateInView>>();
		Mockito.when(validator.validate(Matchers.any(RescateInView.class))).thenReturn(respValidator);
    	
    	RescateInView viewRequest = new RescateInView();
    	viewRequest.setTipoBanca("BP");
    	ArrayList<CuentaTituloView> cuentasTituloView = new ArrayList<CuentaTituloView>();
    	CuentaTituloView cuentaTitulo = new CuentaTituloView();
    	String nroCuenta = "12345678";
		cuentaTitulo.setNroCuenta(nroCuenta);
    	String sucursal = "103";
		cuentaTitulo.setSucursal(sucursal);
    	String nroCuentaFormateado = "1234567/7";
		cuentaTitulo.setNroCuentaFormateado(nroCuentaFormateado);
    	String cuentaOperativa = "765432";
		cuentaTitulo.setCuentaOperativa(cuentaOperativa);
    	cuentaTitulo.setIntervinientes(new ArrayList<Interviniente>());
		cuentasTituloView.add(cuentaTitulo);
    	viewRequest.setCuentasTitulo(cuentasTituloView);
    	
    	Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta item = new ItemMensajeRespuesta("Mensaje respuesta");
		itemMensajeRespuesta.add(item);
		respuestaBO.setItemMensajeRespuesta(itemMensajeRespuesta);
		when(rescateBO.obtenerFondosSuscriptosBPriv(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
    	
		Respuesta<RescateView> respuesta = new Respuesta<RescateView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.any(Class.class), Matchers.any(RescateView.class), Matchers.anyListOf(ItemMensajeRespuesta.class))).thenReturn(respuesta);
		
		Respuesta<RescateView> rtaManager = rescateManager.obtenerFondosSuscriptosBPriv(viewRequest);
		Assert.assertNotNull(rtaManager);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void obtenerFondosSuscriptosBPrivTestError(){

    	Set<ConstraintViolation<RescateInView>> respValidator = new HashSet<ConstraintViolation<RescateInView>>();
		Mockito.when(validator.validate(Matchers.any(RescateInView.class))).thenReturn(respValidator);
    	
    	RescateInView viewRequest = new RescateInView();
    	viewRequest.setTipoBanca("BP");
    	ArrayList<CuentaTituloView> cuentasTituloView = new ArrayList<CuentaTituloView>();
    	CuentaTituloView cuentaTitulo = new CuentaTituloView();
    	String nroCuenta = "12345678";
		cuentaTitulo.setNroCuenta(nroCuenta);
    	String sucursal = "103";
		cuentaTitulo.setSucursal(sucursal);
    	String nroCuentaFormateado = "1234567/7";
		cuentaTitulo.setNroCuentaFormateado(nroCuentaFormateado);
    	String cuentaOperativa = "765432";
		cuentaTitulo.setCuentaOperativa(cuentaOperativa);
    	cuentaTitulo.setIntervinientes(new ArrayList<Interviniente>());
		cuentasTituloView.add(cuentaTitulo);
    	viewRequest.setCuentasTitulo(cuentasTituloView);
    	
    	Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta item = new ItemMensajeRespuesta("Mensaje respuesta");
		item.setTipoError("tipo error");
		itemMensajeRespuesta.add(item);
		respuestaBO.setItemMensajeRespuesta(itemMensajeRespuesta);
		when(rescateBO.obtenerFondosSuscriptosBPriv(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);

		boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaEstadistica);
		
		Respuesta<RescateView> respuesta = new Respuesta<RescateView>();
		respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyString(), Matchers.anyString())).thenReturn(respuesta);
		
		Respuesta<RescateView> rtaManager = rescateManager.obtenerFondosSuscriptosBPriv(viewRequest);
		Assert.assertNotNull(rtaManager);
    }
    
	@SuppressWarnings("unchecked")
    @Test
    public void obtenerFondosSuscriptosBPrivTestErrorValidacionCampos(){

    	Set<ConstraintViolation<RescateInView>> respValidator = new HashSet<ConstraintViolation<RescateInView>>();
		Mockito.when(validator.validate(Matchers.any(RescateInView.class))).thenReturn(respValidator);
		@SuppressWarnings("rawtypes")
        Respuesta respuesta = new Respuesta<RescateView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(String.class), Matchers.any(TipoError.class), Matchers.anyString())).thenReturn(respuesta);
        
		
		RescateInView viewRequest = new RescateInView();
		Respuesta<RescateView> rtaManager = rescateManager.obtenerFondosSuscriptosBPriv(viewRequest);
		Assert.assertNotNull(rtaManager);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void finalizarRescateBPrivTestOk(){

    	Respuesta<ActionCode> respuestaRsaManager = Mockito.mock(Respuesta.class);
        Mockito.when(rsaManager.analizar(Matchers.any(RescateFondoRsa.class), null)).thenReturn(respuestaRsaManager);
        
    	Set<ConstraintViolation<FinalizarRescateInView>> respValidator = new HashSet<ConstraintViolation<FinalizarRescateInView>>();
		Mockito.when(validator.validate(Matchers.any(FinalizarRescateInView.class))).thenReturn(respValidator);

		Cliente clienteSesion = new Cliente();
        when(sesionCliente.getCliente()).thenReturn(clienteSesion);
        
        when(sesionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapaDeLaVistaFinalizarSuscripcionBpriv()));
        
		Respuesta<FinalizarRescateBPrivDTO> respuestaMockBO = new Respuesta<FinalizarRescateBPrivDTO>();
		respuestaMockBO.setEstadoRespuesta(EstadoRespuesta.OK);
		FinalizarRescateBPrivDTO respuestaDTO = new FinalizarRescateBPrivDTO();
		String importe = "10500";
		respuestaDTO.setImporte(importe);
		String mensajeSuscripcion = "Mensaje Suscripcion";
		respuestaDTO.setMensajeSuscripcion(mensajeSuscripcion);
		String nroCertificado = "900032";
		respuestaDTO.setNroCertificado(nroCertificado);
		String fechaHora = "201705051008";
		respuestaDTO.setFechaHora(fechaHora);
		String cuotaPartes = "109872";
		respuestaDTO.setCuotaPartes(cuotaPartes);
		respuestaMockBO.setRespuesta(respuestaDTO);
		when(rescateBO.finalizarRescateBPriv(Matchers.any(FinalizarRescateBPrivInDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaMockBO);

		boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaEstadistica);

        Respuesta<FinalizarRescateView> respuestaConfiguracion = new Respuesta<FinalizarRescateView>();
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(FinalizarRescateView.class))).thenReturn(respuestaConfiguracion);
        
        FinalizarRescateBPrivInView viewRequest = new FinalizarRescateBPrivInView();
    	String codigoFondo = "007";
		viewRequest.setCodigoFondo(codigoFondo );
    	String numeroCtaCred = "7654321";
		viewRequest.setNumeroCtaCred(numeroCtaCred);
    	String moneda = "$";
		viewRequest.setMoneda(moneda);
		viewRequest.setImporte(importe);
		viewRequest.setCuotaPartes(cuotaPartes);
		String nombreFondo = "Super Ahorro $";
		viewRequest.setNombreFondo(nombreFondo);
		String dentroDelPerfil = "Dentro del perfil";
		viewRequest.setDentroDelPerfil(dentroDelPerfil);
		
    	Respuesta<FinalizarRescateView> respuestaManager = rescateManager.finalizarRescateBPriv(viewRequest);
    	
    	Assert.assertNotNull(respuestaManager);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void finalizarRescateBPrivTestError(){

    	Respuesta<ActionCode> respuestaRsaManager = Mockito.mock(Respuesta.class);
        Mockito.when(rsaManager.analizar(Matchers.any(RescateFondoRsa.class), null)).thenReturn(respuestaRsaManager);
        
    	Set<ConstraintViolation<FinalizarRescateInView>> respValidator = new HashSet<ConstraintViolation<FinalizarRescateInView>>();
		Mockito.when(validator.validate(Matchers.any(FinalizarRescateInView.class))).thenReturn(respValidator);

		Cliente clienteSesion = new Cliente();
        when(sesionCliente.getCliente()).thenReturn(clienteSesion);
        
        when(sesionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapaDeLaVistaFinalizarSuscripcionBpriv()));
        
		Respuesta<FinalizarRescateBPrivDTO> respuestaMockBO = new Respuesta<FinalizarRescateBPrivDTO>();
		List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
		itemMensajeRespuesta.add(new ItemMensajeRespuesta("Mensaje respuesta"));
		respuestaMockBO.setItemMensajeRespuesta(itemMensajeRespuesta);
		respuestaMockBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(rescateBO.finalizarRescateBPriv(Matchers.any(FinalizarRescateBPrivInDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaMockBO);

		boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaEstadistica);

        Respuesta<FinalizarRescateView> respuestaConfiguracion = new Respuesta<FinalizarRescateView>();
        respuestaConfiguracion.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyString(), Matchers.anyString())).thenReturn(respuestaConfiguracion);
        
        FinalizarRescateBPrivInView viewRequest = new FinalizarRescateBPrivInView();
    	String codigoFondo = "007";
		viewRequest.setCodigoFondo(codigoFondo );
    	String numeroCtaCred = "7654321";
		viewRequest.setNumeroCtaCred(numeroCtaCred);
    	String moneda = "$";
		viewRequest.setMoneda(moneda);
		String importe = "10500";
		viewRequest.setImporte(importe);
		String cuotaPartes = "109872";
		viewRequest.setCuotaPartes(cuotaPartes);
		String nombreFondo = "Super Ahorro $";
		viewRequest.setNombreFondo(nombreFondo);
		String dentroDelPerfil = "Dentro del perfil";
		viewRequest.setDentroDelPerfil(dentroDelPerfil);
		
    	Respuesta<FinalizarRescateView> respuestaManager = rescateManager.finalizarRescateBPriv(viewRequest);
    	
    	Assert.assertNotNull(respuestaManager);
    }
    
    @Test
    public void ObtenerRescateDesdeGrilla(){
        
        Respuesta<RescateDesdeGrilla> respuesta = new Respuesta<RescateDesdeGrilla>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
                
        Mockito.when(rescateBO.obtenerRescateDesdeGrilla(Matchers.any(RescateDesdeGrillaInView.class), Matchers.any(Cliente.class))).thenReturn(respuesta);
        
        RescateDesdeGrillaInView viewRequest = new RescateDesdeGrillaInView();
        viewRequest.setCodigoFondo("007");
        viewRequest.setNumeroCuentaOperativa("00000000000");
        Respuesta<RescateDesdeGrilla> respuestaFinal = rescateManager.obtenerRescateDesdeGrilla(viewRequest);
        Assert.assertNotNull(respuestaFinal);
    }
    
    @Test
    public void ObtenerRescateDesdeGrillaError(){
        
        Respuesta<RescateDesdeGrilla> respuesta = new Respuesta<RescateDesdeGrilla>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(TipoError.SERVICIO_FONDOS_DESHABILITADO.getDescripcion());
        respuesta.setItemMensajeRespuesta(Arrays.asList(item));

        Mockito.when(rescateBO.obtenerRescateDesdeGrilla(Matchers.any(RescateDesdeGrillaInView.class), Matchers.any(Cliente.class))).thenReturn(respuesta);

        Respuesta<Object> respuestaConfiguracion = new Respuesta<Object>();
        respuestaConfiguracion.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.any(String.class), Matchers.any(TipoError.class),
                Matchers.any(String.class))).thenReturn(respuestaConfiguracion);
        
        RescateDesdeGrillaInView viewRequest = new RescateDesdeGrillaInView();
        viewRequest.setCodigoFondo("007");
        viewRequest.setNumeroCuentaOperativa("00000000000");
        Respuesta<RescateDesdeGrilla> respuestaFinal = rescateManager.obtenerRescateDesdeGrilla(viewRequest);
        Assert.assertNotNull(respuestaFinal);
    }
    
    private Map<String, Object> crearMapFinalizarRescateFondos() {
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("codigoFondo", "007");
//		mapaAtributos.put("cuentaTitulos", null);
		mapaAtributos.put("importe", "10500");
		mapaAtributos.put("moneda", "$");
//		mapaAtributos.put("numeroCuentaDebito", null);
//		mapaAtributos.put("sucursalCuentaDebito", null);
		return mapaAtributos;
    }
    
    private Map<String, Object> crearMapaDeLaVistaFinalizarSuscripcionBpriv() {
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
	    mapaAtributos.put("codigoFondo", "007");
//		mapaAtributos.put("cuentaTitulos", null);
		mapaAtributos.put("importe", "10500");
		mapaAtributos.put("moneda", "$");
//  	mapaAtributos.put("numeroCuentaDebito", null);
//		mapaAtributos.put("sucursalCuentaDebito", null);
		return mapaAtributos;
	}

    @Test
    public void obtenerLegalSuperAhorroPesos(){
        SimulacionRescateInView viewRequest = new SimulacionRescateInView();
        viewRequest.setNroCuentaTitulos("12345678");
        viewRequest.setImporteNeto("100.00");
        viewRequest.setNumeroCuenta("1000/02");
        viewRequest.setCodigoFondo( "007");
        viewRequest.setTipoCuenta("CU");
        viewRequest.setSucursalCuenta("123");
        viewRequest.setNumeroCuenta("1234567/8");
        viewRequest.setMoneda("$");

        Respuesta<SimulacionRescateOutView> simularRescateFondo = rescateSEI.simularRescateFondo(viewRequest);
        String legalSuperAhorroPesos = simularRescateFondo.getRespuesta().getLegalSuperAhorroPesos();

        Respuesta<String> respuesta = new Respuesta<String>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta("Respuesta Legal Super Ahorro Pesos");
        Mockito.when(legalBO.buscarLegal(Matchers.any(String.class))).thenReturn(respuesta);

        Assert.assertNotNull(legalSuperAhorroPesos);
        Assert.assertEquals(respuesta.getRespuesta().getClass(), legalSuperAhorroPesos.getClass());
    }

}
