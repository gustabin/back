/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.manager;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.inversiones.ConfirmacionOrdenResponse;
import ar.com.santanderrio.obp.generated.webservices.inversiones.ParametroDatosConfirmacionOrden;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dao.InversionDAO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.FondoBO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao.ConsultaFondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaConTenenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaTituloDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentasConsultaFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FondoResumidoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.GraficoFondosBPrivOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.GraficoFondosRTLOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.GraficosFondosPorCuentaBPriv;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.PorcentajeGraficoFondos;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimulacionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularSuscripcionOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SuscripcionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SuscripcionOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaFondosSuscritosDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaPorFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciasFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TipoBancaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.MovimientoFondoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.MovimientosFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigFondoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConsultaHorariosYHonorariosView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CotizacionDeFondosView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentasConsultaFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.DetalleDeFondoIn;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.DetalleDeFondoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.GraficoFondosBPrivOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.GraficoFondosRTLOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.MovimientosInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.MovimientosView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionSuscripcionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SuscripcionInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SuscripcionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.TenenciasFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.TipoBancaView;

/**
 * The Class FondoManagerTest.
 *
 * @author
 */
@RunWith(MockitoJUnitRunner.class)
public class FondoManagerTest {

    /** The fondo manager. */
    @InjectMocks
    private FondoManagerImpl fondoManager;

    /** The fondo BO. */
    @Mock
    private FondoBO fondoBO;

    /** The legal BO. */
    @Mock
    private LegalBO legalBO;
    
    @Mock
    private MensajeBO mensajeBO;

    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory;

    /** The fondo BO. */
    @Mock
    private SesionCliente sesionCliente;
    
    @Mock
    private SesionParametros sesionParametros;
    
    @Mock
    private EstadisticaManager estadisticaManager;
    
    @Mock
    private InversionDAO inversionDAO;

    @Mock
    private Validator validator;

    @Mock
    private CuentaManager cuentaManager;
    
    @Mock
    private ConsultaFondoDAO consultaFondoDAO;
    
    @Mock
    private InversionWSHelper inversionWSHelper;

    /**
     * Suscribir.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void suscribir() {
        // Request
        FondoView viewRequest = new FondoView();
        // Mockear el bo.
        Respuesta<FondoDTO> respuestaBO = new Respuesta<FondoDTO>();
        respuestaBO.setRespuesta(new FondoDTO());
        when(fondoBO.suscribir(Matchers.any(FondoDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
        when(sesionCliente.getCliente()).thenReturn(new Cliente());
        // consumir el manager.
        Respuesta<FondoView> view = new  Respuesta<FondoView> ();
        view.setEstadoRespuesta(EstadoRespuesta.OK);
        when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(FondoView.class))).thenReturn(view);
        
     
        Respuesta<FondoView> respuesta = fondoManager.suscribir(viewRequest);
        // asserts o validate mocks
        Assert.assertNotNull(respuesta);
     
    }
     
    @SuppressWarnings("unchecked")
	@Test
     public void obtenerFondosSuscriptosYDisponiblesTestOk(){
    	 
    	Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
    	CuentasConsultaFondoDTO respuestaDTO = new CuentasConsultaFondoDTO();
    	List<FondoResumidoDTO> fondosTotales = new ArrayList<FondoResumidoDTO>();
    	FondoResumidoDTO fondoDTO2 = new FondoResumidoDTO();
    	fondoDTO2.setIdMensajeGastos("1039");
		fondosTotales.add(fondoDTO2);
		respuestaDTO.setFondosTotales(fondosTotales);
    	List<CuentaTituloDTO> cuentasTitulo = new ArrayList<CuentaTituloDTO>();
    	CuentaTituloDTO ctaTit = new CuentaTituloDTO();
    	List<FondoResumidoDTO> fondosSuscriptos = new ArrayList<FondoResumidoDTO>();
    	FondoResumidoDTO fondoDTO = new FondoResumidoDTO();
    	fondoDTO.setIdMensajeGastos("1039");
		fondosSuscriptos.add(fondoDTO);
		ctaTit.setFondosSuscriptos(fondosSuscriptos);
		ctaTit.setCuentaOperativaSinFormatear("13");
		cuentasTitulo.add(ctaTit);
		respuestaDTO.setCuentasTitulo(cuentasTitulo);
		respuestaBO.setRespuesta(respuestaDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		when(fondoBO.obtenerFondosSuscriptosYDisponibles(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class),Matchers.anyBoolean(),Matchers.anyBoolean())).thenReturn(respuestaBO);

		Respuesta<CuentasConsultaFondoView> rtaFactory = new Respuesta<CuentasConsultaFondoView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(CuentasConsultaFondoView.class))).thenReturn(rtaFactory);
    	 
		CuentasConsultaFondoView viewRequest = new CuentasConsultaFondoView();
		List<CuentaTituloView> cuentasTituloView = new ArrayList<CuentaTituloView>();
		cuentasTituloView.add(new CuentaTituloView());
		viewRequest.setCuentasTitulo(cuentasTituloView);
		Respuesta<CuentasConsultaFondoView> rtaManager = fondoManager.obtenerFondosSuscriptosYDisponibles(viewRequest);
    	 
		Assert.assertNotNull(rtaManager);
     }
     
    @SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void obtenerFondosSuscriptosYDisponiblesTestWarning(){
    	 
    	Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
    	CuentasConsultaFondoDTO respuestaDTO = new CuentasConsultaFondoDTO();
    	respuestaDTO.setFondosTotales(new ArrayList<FondoResumidoDTO>());
    	List<CuentaTituloDTO> cuentasTitulo = new ArrayList<CuentaTituloDTO>();
    	CuentaTituloDTO ctaTit = new CuentaTituloDTO();
    	List<FondoResumidoDTO> fondosSuscriptos = new ArrayList<FondoResumidoDTO>();
    	fondosSuscriptos.add(new FondoResumidoDTO());
		ctaTit.setFondosSuscriptos(fondosSuscriptos);
		ctaTit.setCuentaOperativaSinFormatear("13");
		cuentasTitulo.add(ctaTit);
		respuestaDTO.setCuentasTitulo(cuentasTitulo);
		respuestaBO.setRespuesta(respuestaDTO);
    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		when(fondoBO.obtenerFondosSuscriptosYDisponibles(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class),Matchers.anyBoolean(),Matchers.anyBoolean())).thenReturn(respuestaBO);
		
		Respuesta<CuentasConsultaFondoView> rtaFactory = new Respuesta<CuentasConsultaFondoView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.WARNING);
		when(respuestaFactory.crearRespuestaWarning(Matchers.any(Class.class), Matchers.any(CuentasConsultaFondoView.class), Matchers.anyListOf(ItemMensajeRespuesta.class))).thenReturn(rtaFactory);
		
		CuentasConsultaFondoView viewRequest = new CuentasConsultaFondoView();
		viewRequest.setCuentasTitulo(new ArrayList<CuentaTituloView>());
		Respuesta<CuentasConsultaFondoView> rtaManager = fondoManager.obtenerFondosSuscriptosYDisponibles(viewRequest);
    	 
		Assert.assertNotNull(rtaManager);
     }
     
    @SuppressWarnings("unchecked")
	@Test
     public void obtenerFondosSuscriptosYDisponiblesTestError(){
    	 
    	Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
    	itemMensajeRespuesta.add(new ItemMensajeRespuesta("Mensaje Itemmensajerespuesta"));
		respuestaBO.setItemMensajeRespuesta(itemMensajeRespuesta);
		when(fondoBO.obtenerFondosSuscriptosYDisponibles(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class),Matchers.anyBoolean(),Matchers.anyBoolean())).thenReturn(respuestaBO);
		
		Respuesta<CuentasConsultaFondoView> rtaFactory = new Respuesta<CuentasConsultaFondoView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyString(), Matchers.anyString())).thenReturn(rtaFactory);
		
		CuentasConsultaFondoView viewRequest = new CuentasConsultaFondoView();
		viewRequest.setCuentasTitulo(new ArrayList<CuentaTituloView>());
		Respuesta<CuentasConsultaFondoView> rtaManager = fondoManager.obtenerFondosSuscriptosYDisponibles(viewRequest);
    	 
		Assert.assertNotNull(rtaManager);
     }
    
    @SuppressWarnings("unchecked")
	@Test
     public void obtenerFondosSuscriptosYDisponiblesBPrivTestOk(){
    	 
    	Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
    	CuentasConsultaFondoDTO respuestaDTO = new CuentasConsultaFondoDTO();
    	List<FondoResumidoDTO> fondosTotales = new ArrayList<FondoResumidoDTO>();
    	FondoResumidoDTO fondoDTO2 = new FondoResumidoDTO();
    	fondoDTO2.setIdMensajeGastos("1039");
		fondosTotales.add(fondoDTO2);
		respuestaDTO.setFondosTotales(fondosTotales);
    	List<CuentaTituloDTO> cuentasTitulo = new ArrayList<CuentaTituloDTO>();
    	CuentaTituloDTO ctaTit = new CuentaTituloDTO();
    	List<FondoResumidoDTO> fondosSuscriptos = new ArrayList<FondoResumidoDTO>();
    	FondoResumidoDTO fondoDTO = new FondoResumidoDTO();
    	fondoDTO.setIdMensajeGastos("1039");
		fondosSuscriptos.add(fondoDTO);
		ctaTit.setFondosSuscriptos(fondosSuscriptos);
		ctaTit.setCuentaOperativaSinFormatear("13");
		cuentasTitulo.add(ctaTit);
		respuestaDTO.setCuentasTitulo(cuentasTitulo);
		respuestaBO.setRespuesta(respuestaDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		when(fondoBO.obtenerFondosSuscriptosYDisponiblesBPriv(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class),Matchers.anyBoolean())).thenReturn(respuestaBO);

		Respuesta<CuentasConsultaFondoView> rtaFactory = new Respuesta<CuentasConsultaFondoView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(CuentasConsultaFondoView.class))).thenReturn(rtaFactory);
    	 
		CuentasConsultaFondoView viewRequest = new CuentasConsultaFondoView();
		List<CuentaTituloView> cuentasTituloView = new ArrayList<CuentaTituloView>();
		cuentasTituloView.add(new CuentaTituloView());
		viewRequest.setCuentasTitulo(cuentasTituloView);
		Respuesta<CuentasConsultaFondoView> rtaManager = fondoManager.obtenerFondosSuscriptosYDisponiblesBPriv(viewRequest);
    	 
		Assert.assertNotNull(rtaManager);
     }
     
    @SuppressWarnings("unchecked")
	@Test
	@Ignore
	public void obtenerFondosSuscriptosYDisponiblesBPrivTestWarning(){
    	 
    	Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
    	CuentasConsultaFondoDTO respuestaDTO = new CuentasConsultaFondoDTO();
    	respuestaDTO.setFondosTotales(new ArrayList<FondoResumidoDTO>());
    	List<CuentaTituloDTO> cuentasTitulo = new ArrayList<CuentaTituloDTO>();
    	CuentaTituloDTO ctaTit = new CuentaTituloDTO();
    	List<FondoResumidoDTO> fondosSuscriptos = new ArrayList<FondoResumidoDTO>();
    	fondosSuscriptos.add(new FondoResumidoDTO());
		ctaTit.setFondosSuscriptos(fondosSuscriptos);
		ctaTit.setCuentaOperativaSinFormatear("13");
		cuentasTitulo.add(ctaTit);
		respuestaDTO.setCuentasTitulo(cuentasTitulo);
		respuestaBO.setRespuesta(respuestaDTO);
    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		when(fondoBO.obtenerFondosSuscriptosYDisponiblesBPriv(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class),Matchers.anyBoolean())).thenReturn(respuestaBO);
		
		Respuesta<CuentasConsultaFondoView> rtaFactory = new Respuesta<CuentasConsultaFondoView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.WARNING);
		when(respuestaFactory.crearRespuestaWarning(Matchers.any(Class.class), Matchers.any(CuentasConsultaFondoView.class), Matchers.anyListOf(ItemMensajeRespuesta.class))).thenReturn(rtaFactory);
		
		CuentasConsultaFondoView viewRequest = new CuentasConsultaFondoView();
		viewRequest.setCuentasTitulo(new ArrayList<CuentaTituloView>());
		Respuesta<CuentasConsultaFondoView> rtaManager = fondoManager.obtenerFondosSuscriptosYDisponiblesBPriv(viewRequest);
    	 
		Assert.assertNotNull(rtaManager);
     }
     
    @SuppressWarnings("unchecked")
	@Test
     public void obtenerFondosSuscriptosYDisponiblesBPrivTestError(){
    	 
    	Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
    	itemMensajeRespuesta.add(new ItemMensajeRespuesta("Mensaje Itemmensajerespuesta"));
		respuestaBO.setItemMensajeRespuesta(itemMensajeRespuesta);
		when(fondoBO.obtenerFondosSuscriptosYDisponiblesBPriv(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class),Matchers.anyBoolean())).thenReturn(respuestaBO);
		
		Respuesta<CuentasConsultaFondoView> rtaFactory = new Respuesta<CuentasConsultaFondoView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyString(), Matchers.anyString())).thenReturn(rtaFactory);
		
		CuentasConsultaFondoView viewRequest = new CuentasConsultaFondoView();
		viewRequest.setCuentasTitulo(new ArrayList<CuentaTituloView>());
		Respuesta<CuentasConsultaFondoView> rtaManager = fondoManager.obtenerFondosSuscriptosYDisponiblesBPriv(viewRequest);
    	 
		Assert.assertNotNull(rtaManager);
     }
    
    @SuppressWarnings("unchecked")
	@Test
    public void finalizarSuscribirFondos() throws DAOException{
    	
    	Respuesta<FondoDTO> respuestaBO = new Respuesta<FondoDTO>();
    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
    	FondoDTO respuestaDTO = new FondoDTO();
    	respuestaDTO.setNumeroCertificado("10773256");
    	respuestaDTO.setImporte("1000");
		respuestaBO.setRespuesta(respuestaDTO);
		when(fondoBO.finalizarSuscribirFondos(Matchers.any(FondoDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
		when(sesionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapaDeLaVistaFinalizarSuscripcion()));
		
		boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaEstadistica);
		
		when(sesionParametros.getIdEv()).thenReturn("1040");

		when(inversionDAO.confirmacionOrden(Matchers.any(ParametroDatosConfirmacionOrden.class))).thenReturn(new ConfirmacionOrdenResponse());
		
		Respuesta<FondoView> rtaFactory = new Respuesta<FondoView>();
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(FondoView.class))).thenReturn(rtaFactory);
    	
    	FondoView viewRequest = new FondoView();
		Respuesta<FondoView> rtaManager = fondoManager.finalizarSuscribirFondos(viewRequest);
		Assert.assertNotNull(rtaManager);
    }
    
	private Map<String, Object> crearMapaDeLaVistaFinalizarSuscripcion() {
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
        mapaAtributos.put("codigoFondo", null);
        mapaAtributos.put("cuentaTitulos", null);
        mapaAtributos.put("importe", null);
        mapaAtributos.put("moneda", null);
        mapaAtributos.put("numeroCuentaDebito", null);
        mapaAtributos.put("sucursalCuentaDebito", null);
        mapaAtributos.put("tipoCuentaDebito", null);
		return mapaAtributos;
	}
	
	private Map<String, Object> crearMapaDeLaVistaFinalizarSuscripcionBpriv() {
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("codigoFondo", "103");
		mapaAtributos.put("importe", new BigDecimal(String.valueOf("10500")));
		mapaAtributos.put("nroCuentaBancaPrivada", "700123456");
		return mapaAtributos;
	}
	
	
    @SuppressWarnings("unchecked")
	@Test
    public void finalizarSuscribirFondosWarning(){
    	
    	Respuesta<FondoDTO> respuestaBO = new Respuesta<FondoDTO>();
    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.WARNING);
    	respuestaBO.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
		when(fondoBO.finalizarSuscribirFondos(Matchers.any(FondoDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
		when(sesionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapaDeLaVistaFinalizarSuscripcion()));
		
		Respuesta<FondoView> rtaFactory = new Respuesta<FondoView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.WARNING);
		when(respuestaFactory.crearRespuestaWarning(Matchers.any(Class.class), Matchers.any(FondoView.class), Matchers.anyListOf(ItemMensajeRespuesta.class))).thenReturn(rtaFactory);
		
    	FondoView viewRequest = new FondoView();
		Respuesta<FondoView> rtaManager = fondoManager.finalizarSuscribirFondos(viewRequest);
		Assert.assertNotNull(rtaManager);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void finalizarSuscribirFondosError(){
    	
    	Respuesta<FondoDTO> respuestaBO = new Respuesta<FondoDTO>();
    	respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
    	ItemMensajeRespuesta item = new ItemMensajeRespuesta();
    	item.setTag("tag itemmensajerespuesta");
    	item.setMensaje("mensaje itemmensajerespuesta");
		itemMensajeRespuesta.add(item);
		respuestaBO.setItemMensajeRespuesta(itemMensajeRespuesta);
		when(fondoBO.finalizarSuscribirFondos(Matchers.any(FondoDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
		when(sesionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapaDeLaVistaFinalizarSuscripcion()));
    	
		boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaEstadistica);
		
		Respuesta<FondoView> rtaFactory = new Respuesta<FondoView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyString(), Matchers.anyString())).thenReturn(rtaFactory);
		
    	FondoView viewRequest = new FondoView();
		Respuesta<FondoView> rtaManager = fondoManager.finalizarSuscribirFondos(viewRequest);
		Assert.assertNotNull(rtaManager);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void finalizarSuscribirFondosBPrivOK(){

    	boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaEstadistica);
		
    	when(sesionCliente.getCliente()).thenReturn(new Cliente());
    	
    	Set<ConstraintViolation<SuscripcionInView>> respValidator = new HashSet<ConstraintViolation<SuscripcionInView>>();
		when(validator.validate(Matchers.any(SuscripcionInView.class))).thenReturn(respValidator);
    	
		when(sesionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapaDeLaVistaFinalizarSuscripcionBpriv()));
		
		Respuesta<SuscripcionOutDTO> respuestaBO = new Respuesta<SuscripcionOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		SuscripcionOutDTO rta = new SuscripcionOutDTO();
		rta.setImporte("123456");
		respuestaBO.setRespuesta(rta);
		when(fondoBO.finalizarSuscribirFondosBPriv(Matchers.any(SuscripcionInDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
		
		Respuesta<SuscripcionOutView> rtaFactory = new Respuesta<SuscripcionOutView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
		when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(SuscripcionOutView.class))).thenReturn(rtaFactory);
		
    	SuscripcionInView viewRequest = new SuscripcionInView();
    	viewRequest.setNroCuentaBancaPrivada("700123456");
    	viewRequest.setCodigoFondo("103");
    	viewRequest.setImporte(BigDecimal.valueOf(Long.valueOf("10500")));
    	viewRequest.setDentroDelPerfil("Dentro del perfil");
    	viewRequest.setNombreFondo("Super Ahorro $");
    	viewRequest.setCuentaTitulos("123456789");
    	
		Respuesta<SuscripcionOutView> rtaManager = fondoManager.finalizarSuscribirFondosBPriv(viewRequest);
		Assert.assertNotNull(rtaManager);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void finalizarSuscribirFondosBPrivWarning(){
    	
    	when(sesionCliente.getCliente()).thenReturn(new Cliente());
    	
    	Set<ConstraintViolation<SuscripcionInView>> respValidator = new HashSet<ConstraintViolation<SuscripcionInView>>();
		when(validator.validate(Matchers.any(SuscripcionInView.class))).thenReturn(respValidator);
		
		when(sesionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapaDeLaVistaFinalizarSuscripcionBpriv()));
    	
		Respuesta<SuscripcionOutDTO> respuestaBO = new Respuesta<SuscripcionOutDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		respuestaBO.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
		when(fondoBO.finalizarSuscribirFondosBPriv(Matchers.any(SuscripcionInDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
		
		Respuesta<SuscripcionOutView> rtaFactory = new Respuesta<SuscripcionOutView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.WARNING);
		when(respuestaFactory.crearRespuestaWarning(Matchers.any(Class.class), Matchers.any(SuscripcionOutView.class), Matchers.anyListOf(ItemMensajeRespuesta.class))).thenReturn(rtaFactory);
		
    	SuscripcionInView viewRequest = new SuscripcionInView();
    	viewRequest.setNroCuentaBancaPrivada("700123456");
    	viewRequest.setCodigoFondo("103");
    	viewRequest.setImporte(BigDecimal.valueOf(Long.valueOf("10500")));
    	viewRequest.setDentroDelPerfil("Dentro del perfil");
    	viewRequest.setNombreFondo("Super Ahorro $");
    	
		Respuesta<SuscripcionOutView> rtaManager = fondoManager.finalizarSuscribirFondosBPriv(viewRequest);
		Assert.assertNotNull(rtaManager);
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void finalizarSuscribirFondosBPrivError(){
    	
    	when(sesionCliente.getCliente()).thenReturn(new Cliente());

    	boolean respuestaEstadistica = true;
		when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaEstadistica);
		
    	Set<ConstraintViolation<SuscripcionInView>> respValidator = new HashSet<ConstraintViolation<SuscripcionInView>>();
		when(validator.validate(Matchers.any(SuscripcionInView.class))).thenReturn(respValidator);
		
		when(sesionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapaDeLaVistaFinalizarSuscripcionBpriv()));
    	
		Respuesta<SuscripcionOutDTO> respuestaBO = new Respuesta<SuscripcionOutDTO>();
		List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
    	ItemMensajeRespuesta item = new ItemMensajeRespuesta();
    	item.setTag("tag itemmensajerespuesta");
    	item.setMensaje("mensaje itemmensajerespuesta");
		itemMensajeRespuesta.add(item);
		respuestaBO.setItemMensajeRespuesta(itemMensajeRespuesta);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(fondoBO.finalizarSuscribirFondosBPriv(Matchers.any(SuscripcionInDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);

		Respuesta<SuscripcionOutView> rtaFactory = new Respuesta<SuscripcionOutView>();
		rtaFactory.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyString(), Matchers.anyString())).thenReturn(rtaFactory);
		
    	SuscripcionInView viewRequest = new SuscripcionInView();
    	viewRequest.setNroCuentaBancaPrivada("700123456");
    	viewRequest.setCodigoFondo("103");
    	viewRequest.setImporte(BigDecimal.valueOf(Long.valueOf("10500")));
    	viewRequest.setDentroDelPerfil("Dentro del perfil");
    	viewRequest.setNombreFondo("Super Ahorro $");
    	
		Respuesta<SuscripcionOutView> rtaManager = fondoManager.finalizarSuscribirFondosBPriv(viewRequest);
		Assert.assertNotNull(rtaManager);
    }
     
    @Test
    public void ultimosMovimientosTest() throws DAOException{
    	Respuesta<MovimientosFondoOutEntity> responseMovimientos = new Respuesta<MovimientosFondoOutEntity>();
    	responseMovimientos.setEstadoRespuesta(EstadoRespuesta.OK);
    	List<MovimientoFondoEntity> movimientoFondo  = new ArrayList<MovimientoFondoEntity>();
    	MovimientoFondoEntity mov = new MovimientoFondoEntity();
    	mov.setDescripcionMovimiento("Prueba");
    	mov.setImporte("10000");
    	mov.setFechaDeConversion("03/07/2017");
    	mov.setCantidadCuotaPartes("123123");
    	mov.setValorCuota("456456");
    	MovimientosFondoOutEntity movimientoFondos = new MovimientosFondoOutEntity();
    	movimientoFondo.add(mov);
    	movimientoFondos.setMovimientos(movimientoFondo);
    	responseMovimientos.setRespuesta(movimientoFondos);
    	when(fondoBO.consultarMovimientos(Matchers.any(Cliente.class), Matchers.any(MovimientosInView.class))).thenReturn(responseMovimientos);

		TenenciasFondoDTO tenencias = new TenenciasFondoDTO();
		tenencias.setListaCuentas(new ArrayList<CuentaConTenenciaDTO>());
		Mockito.when(sesionParametros.getTenenciasFondoDTO()).thenReturn(tenencias);
		
		ConsultaFondoOutEntity fondo = new ConsultaFondoOutEntity();
		when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondo);

    	MovimientosInView viewRequest = new MovimientosInView();
    	Respuesta<MovimientosView> ultimosMovimientos = fondoManager.ultimosMovimientos(viewRequest);
    	Assert.assertNotNull(ultimosMovimientos);
    	Assert.assertEquals(EstadoRespuesta.OK, ultimosMovimientos.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void ultimosMovimientosRespuestaErrorSinMovimientos() throws DAOException{
    	Respuesta<MovimientosFondoOutEntity> responseMovimientos = new Respuesta<MovimientosFondoOutEntity>();
    	responseMovimientos.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	ItemMensajeRespuesta item = new ItemMensajeRespuesta();
    	item.setTipoError(TipoError.SIN_MOVIMIENTOS.getDescripcion());
    	List<ItemMensajeRespuesta> lista = new ArrayList<ItemMensajeRespuesta>();
    	lista.add(item);
    	responseMovimientos.setItemMensajeRespuesta(lista);
    	List<MovimientoFondoEntity> movimientoFondo  = new ArrayList<MovimientoFondoEntity>();
    	MovimientoFondoEntity mov = new MovimientoFondoEntity();
    	mov.setDescripcionMovimiento("Prueba");
    	mov.setImporte("10000");
    	mov.setFechaDeConversion("03/07/2017");
    	MovimientosFondoOutEntity movimientoFondos = new MovimientosFondoOutEntity();
    	movimientoFondo.add(mov);
    	movimientoFondos.setMovimientos(movimientoFondo);
    	responseMovimientos.setRespuesta(movimientoFondos);
    	when(fondoBO.consultarMovimientos(Matchers.any(Cliente.class), Matchers.any(MovimientosInView.class))).thenReturn(responseMovimientos);
    	Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje de favorito");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	
    	respuestaFactory.crearRespuestaError(new ArrayList<DatoItemMensaje>());
    	
    	Respuesta<MovimientosView> rtaMov= new Respuesta<MovimientosView> ();
    	rtaMov.setEstadoRespuesta(EstadoRespuesta.OK);
        when(respuestaFactory.crearRespuestaError(Matchers.anyList())).thenReturn(rtaMov);
        
        ConsultaFondoOutEntity fondo = new ConsultaFondoOutEntity();
        when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondo);
    	
		TenenciasFondoDTO tenencias = new TenenciasFondoDTO();
		tenencias.setListaCuentas(new ArrayList<CuentaConTenenciaDTO>());
		Mockito.when(sesionParametros.getTenenciasFondoDTO()).thenReturn(tenencias);

    	MovimientosInView viewRequest = new MovimientosInView();
    	Respuesta<MovimientosView> ultimosMovimientos = fondoManager.ultimosMovimientos(viewRequest);
    	Assert.assertNotNull(ultimosMovimientos);
    	Assert.assertEquals(EstadoRespuesta.ERROR, ultimosMovimientos.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void ultimosMovimientosRespuestaErrorGenerico() throws DAOException{
    	Respuesta<MovimientosFondoOutEntity> responseMovimientos = new Respuesta<MovimientosFondoOutEntity>();
    	responseMovimientos.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	ItemMensajeRespuesta item = new ItemMensajeRespuesta();
    	item.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
    	List<ItemMensajeRespuesta> lista = new ArrayList<ItemMensajeRespuesta>();
    	lista.add(item);
    	responseMovimientos.setItemMensajeRespuesta(lista);
    	List<MovimientoFondoEntity> movimientoFondo  = new ArrayList<MovimientoFondoEntity>();
    	MovimientoFondoEntity mov = new MovimientoFondoEntity();
    	mov.setDescripcionMovimiento("Prueba");
    	mov.setImporte("10000");
    	mov.setFechaDeConversion("03/07/2017");
    	MovimientosFondoOutEntity movimientoFondos = new MovimientosFondoOutEntity();
    	movimientoFondo.add(mov);
    	movimientoFondos.setMovimientos(movimientoFondo);
    	responseMovimientos.setRespuesta(movimientoFondos);
    	when(fondoBO.consultarMovimientos(Matchers.any(Cliente.class), Matchers.any(MovimientosInView.class))).thenReturn(responseMovimientos);
    	Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("Mensaje de favorito");
		when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
		
		TenenciasFondoDTO tenencias = new TenenciasFondoDTO();
		tenencias.setListaCuentas(new ArrayList<CuentaConTenenciaDTO>());
		Mockito.when(sesionParametros.getTenenciasFondoDTO()).thenReturn(tenencias);
		
		ConsultaFondoOutEntity fondo = new ConsultaFondoOutEntity();
        when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondo);

    	MovimientosInView viewRequest = new MovimientosInView();
    	
        Respuesta<MovimientosView> rtaMov= new Respuesta<MovimientosView> ();
        rtaMov.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(respuestaFactory.crearRespuestaError(Matchers.anyList())).thenReturn(rtaMov);
    	
    	Respuesta<MovimientosView> ultimosMovimientos = fondoManager.ultimosMovimientos(viewRequest);
    	Assert.assertNotNull(ultimosMovimientos);
    	Assert.assertEquals(EstadoRespuesta.ERROR, ultimosMovimientos.getEstadoRespuesta());
    }

    @Test
    public void ultimosMovimientosRespuestaErrorImporteConver() throws DAOException{
        Respuesta<MovimientosFondoOutEntity> responseMovimientos = new Respuesta<MovimientosFondoOutEntity>();
        responseMovimientos.setEstadoRespuesta(EstadoRespuesta.OK);
        List<MovimientoFondoEntity> movimientoFondo  = new ArrayList<MovimientoFondoEntity>();
        MovimientoFondoEntity mov = new MovimientoFondoEntity();
        mov.setDescripcionMovimiento("Prueba");
        mov.setImporte("AS10000EE");
        mov.setFechaDeConversion("03/07/2017");
        MovimientosFondoOutEntity movimientoFondos = new MovimientosFondoOutEntity();
        movimientoFondo.add(mov);
        movimientoFondos.setMovimientos(movimientoFondo);
        responseMovimientos.setRespuesta(movimientoFondos);
        when(fondoBO.consultarMovimientos(Matchers.any(Cliente.class), Matchers.any(MovimientosInView.class))).thenReturn(responseMovimientos);

		TenenciasFondoDTO tenencias = new TenenciasFondoDTO();
		tenencias.setListaCuentas(new ArrayList<CuentaConTenenciaDTO>());
		Mockito.when(sesionParametros.getTenenciasFondoDTO()).thenReturn(tenencias);
		
		ConsultaFondoOutEntity fondo = new ConsultaFondoOutEntity();
        when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondo);

        MovimientosInView viewRequest = new MovimientosInView();
        
        Respuesta<Object> rta = new Respuesta<Object>();
        rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
        imr.setTipoError("ERROR");
        Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_FALLO_SERVICIOS)).thenReturn(rta);
        
        Respuesta<MovimientosView> ultimosMovimientos = fondoManager.ultimosMovimientos(viewRequest);
        Assert.assertNotNull(ultimosMovimientos);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void obtenerDetalleDeFondo(){    	
    	
    	DetalleDeFondoIn entrada = new DetalleDeFondoIn();
    	entrada.setCodigoFondo("007");
    	Respuesta<DetalleDeFondoOutView> detallesTest = new Respuesta<DetalleDeFondoOutView>();
    	DetalleDeFondoOutView respuestaTest = new DetalleDeFondoOutView();
    	respuestaTest.setCotizacion("0000011");
    	respuestaTest.setStatusCotizacion("00001067+");
    	respuestaTest.setAdministrarFondos("00,00");
    	respuestaTest.setEntrada("00,00");
    	respuestaTest.setSalida("00,00");
    	respuestaTest.setHorarios("7.30 a 16.00hs"); 
    	detallesTest.setRespuesta(respuestaTest);
    	detallesTest.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(DetalleDeFondoOutView.class))).thenReturn(detallesTest);
    	
    	
    	Respuesta<DetalleDeFondoOutView> detalleDeFondoRta = new Respuesta<DetalleDeFondoOutView>();
    	detalleDeFondoRta.setEstadoRespuesta(EstadoRespuesta.OK);
    	
    	 when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(DetalleDeFondoOutView.class))).thenReturn(detalleDeFondoRta);
    	
		Respuesta<String> respuestasLegales = new Respuesta<String>();
		String mensajeTest = "10442";
        respuestasLegales.setRespuesta(mensajeTest);
        respuestasLegales.setEstadoRespuesta(EstadoRespuesta.OK);
		TenenciasFondoDTO tenencias = new TenenciasFondoDTO();
		tenencias.setListaCuentas(new ArrayList<CuentaConTenenciaDTO>());
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestasLegales);
		Mockito.when(fondoBO.obtenerDetalleDeFondo(Matchers.any(DetalleDeFondoIn.class), Matchers.any(Cliente.class),
				Matchers.any(TenenciaFondosSuscritosDTO.class))).thenReturn(detallesTest);
		Mockito.when(sesionParametros.getTenenciasFondoDTO()).thenReturn(tenencias);
        
    	detallesTest = fondoManager.obtenerDetalleDeFondo(entrada);
		Assert.assertNotNull(detallesTest);
    	
    }

    @Test
    public void obtenerDetalleDeFondoErrorLegales(){
        Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.ERROR);
        
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
        
        Respuesta<Object> rta = new Respuesta<Object>();
        rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
        imr.setTipoError("ERROR");
        
        Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(rta);
        DetalleDeFondoIn viewRequest = new DetalleDeFondoIn();
        viewRequest.setCodigoFondo("007");
        Respuesta<DetalleDeFondoOutView> detalleDeFondo = fondoManager.obtenerDetalleDeFondo(viewRequest);
        Assert.assertNotNull(detalleDeFondo);
    }
    
    @Test
    public void obtenerDetalleDeFondoError(){
        Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta("respuesta");
        
        Respuesta<DetalleDeFondoOutView> respuestaDetalleDeFondoOutView = new Respuesta<DetalleDeFondoOutView>();
        DetalleDeFondoOutView detalleDeFondoOutView = new DetalleDeFondoOutView();
        detalleDeFondoOutView.setAdministrarFondos("administrarFondos");
        detalleDeFondoOutView.setCotizacion("cotizacion");
        detalleDeFondoOutView.setEntrada("entrada");
        detalleDeFondoOutView.setSalida("salida");
        detalleDeFondoOutView.setHorarios("horarios");
        detalleDeFondoOutView.setPlazoDePagos("plazoDePagos");
        detalleDeFondoOutView.setStatusCotizacion("statusCotizacion");
        respuestaDetalleDeFondoOutView.setRespuesta(detalleDeFondoOutView);
        respuestaDetalleDeFondoOutView.setEstadoRespuesta(EstadoRespuesta.ERROR);
		TenenciasFondoDTO tenencias = new TenenciasFondoDTO();
		tenencias.setListaCuentas(new ArrayList<CuentaConTenenciaDTO>());
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
		Mockito.when(fondoBO.obtenerDetalleDeFondo(Matchers.any(DetalleDeFondoIn.class), Matchers.any(Cliente.class),
				Matchers.any(TenenciaFondosSuscritosDTO.class))).thenReturn(respuestaDetalleDeFondoOutView);
		Mockito.when(sesionParametros.getTenenciasFondoDTO()).thenReturn(tenencias);
        
        Respuesta<Object> rta = new Respuesta<Object>();
        rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
        imr.setTipoError("ERROR");
        
        Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(rta);
        DetalleDeFondoIn viewRequest = new DetalleDeFondoIn();
        viewRequest.setCodigoFondo("007");
        Respuesta<DetalleDeFondoOutView> detalleDeFondo = fondoManager.obtenerDetalleDeFondo(viewRequest);
        Assert.assertNotNull(detalleDeFondo);
    }
    
    @Test
    public void busquedaMovimientosTest() throws DAOException{
    	Respuesta<MovimientosFondoOutEntity> responseMovimientos = new Respuesta<MovimientosFondoOutEntity>();
    	responseMovimientos.setEstadoRespuesta(EstadoRespuesta.OK);
    	List<MovimientoFondoEntity> movimientoFondo  = new ArrayList<MovimientoFondoEntity>();
    	MovimientoFondoEntity mov = new MovimientoFondoEntity();
    	mov.setDescripcionMovimiento("Prueba");
    	mov.setImporte("10000");
    	mov.setFechaDeConversion("03/07/2017");
    	mov.setValorCuota("1234567");
    	mov.setCantidadCuotaPartes("876876");
    	MovimientosFondoOutEntity movimientoFondos = new MovimientosFondoOutEntity();
    	movimientoFondo.add(mov);
    	movimientoFondos.setMovimientos(movimientoFondo);
    	responseMovimientos.setRespuesta(movimientoFondos);
    	when(fondoBO.consultarMovimientos(Matchers.any(Cliente.class), Matchers.any(MovimientosInView.class))).thenReturn(responseMovimientos);
		TenenciasFondoDTO tenencias = new TenenciasFondoDTO();
		tenencias.setListaCuentas(new ArrayList<CuentaConTenenciaDTO>());
		Mockito.when(sesionParametros.getTenenciasFondoDTO()).thenReturn(tenencias);
    	MovimientosInView viewRequest = new MovimientosInView();
    	ConsultaFondoOutEntity fondo = new ConsultaFondoOutEntity();
        when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondo);
    	Respuesta<MovimientosView> ultimosMovimientos = fondoManager.busquedaMovimientos(viewRequest);
    	Assert.assertNotNull(ultimosMovimientos);
    	Assert.assertEquals(EstadoRespuesta.OK, ultimosMovimientos.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerTenenciasTest() {
        Cliente cliente = new Cliente();
        cliente.setNup("12345678");
        Respuesta<String> respuestasLegales = new Respuesta<String>();
        respuestasLegales.setRespuesta("Prueba");
        respuestasLegales.setEstadoRespuesta(EstadoRespuesta.OK);

        TipoBancaView requestView = new TipoBancaView();
        requestView.setTipoBanca("BR");

        TenenciasFondoDTO tenencia = new TenenciasFondoDTO();
        List<CuentaConTenenciaDTO> listaCuentas = new ArrayList<CuentaConTenenciaDTO>();
        CuentaConTenenciaDTO cuenta = new CuentaConTenenciaDTO();
        cuenta.setNumeroCuentaTitulo("123456");
        Respuesta<TenenciaPorFondoDTO> respCuenta = new Respuesta<TenenciaPorFondoDTO>();
        respCuenta.setEstadoRespuesta(EstadoRespuesta.OK);
        cuenta.setRespuesta(respCuenta);
        listaCuentas.add(cuenta);
        tenencia.setListaCuentas(listaCuentas);
        Respuesta<TenenciasFondoDTO> respuesta = new Respuesta<TenenciasFondoDTO>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(tenencia);
        Mensaje msj = new Mensaje();
        msj.setMensaje("mensaje");

        Respuesta<TenenciasFondoView> respuestaTenencias = new Respuesta<TenenciasFondoView>();

        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestasLegales);
        Mockito.when(fondoBO.obtenerTenencias(Matchers.any(TipoBancaDTO.class), Matchers.any(Cliente.class)))
                .thenReturn(respuesta);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(msj);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

        when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(TenenciasFondoView.class)))
                .thenReturn(respuestaTenencias);

        Respuesta<TenenciasFondoView> respManager = fondoManager.obtenerTenencias(requestView);

        Assert.assertNotNull(respManager);

    }
  
    @SuppressWarnings("unchecked")
    @Test
    public void busquedaMovimientosError() throws DAOException{
        Respuesta<MovimientosFondoOutEntity> responseMovimientos = new Respuesta<MovimientosFondoOutEntity>();
        responseMovimientos.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<MovimientoFondoEntity> movimientoFondo  = new ArrayList<MovimientoFondoEntity>();
        MovimientoFondoEntity mov = new MovimientoFondoEntity();
        mov.setDescripcionMovimiento("Prueba");
        mov.setImporte("10000");
        mov.setFechaDeConversion("03/07/2017");
        MovimientosFondoOutEntity movimientoFondos = new MovimientosFondoOutEntity();
        movimientoFondo.add(mov);
        movimientoFondos.setMovimientos(movimientoFondo);
        responseMovimientos.setRespuesta(movimientoFondos);
        List<ItemMensajeRespuesta> list = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(TipoError.SIN_MOVIMIENTOS.getDescripcion());
        list.add(item);
        responseMovimientos.setItemMensajeRespuesta(list);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		Respuesta<Object> rta = new Respuesta<Object>();
		rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
		imr.setTipoError("ERROR");

		rta.add(imr);

		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyList())).thenReturn(rta);
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyString(), Matchers.anyString(), Matchers.anyString())).thenReturn(rta);

        Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.SIN_MOVIMIENTOS_BUSQUEDA, CodigoMensajeConstantes.ERROR_FONDO_SIN_MOVIMIENTOS_MAYOR_30_DIAS)).thenReturn(rta);
         
        Mockito.when(fondoBO.consultarMovimientos(Matchers.any(Cliente.class), Matchers.any(MovimientosInView.class))).thenReturn(responseMovimientos);
       
		TenenciasFondoDTO tenencias = new TenenciasFondoDTO();
		tenencias.setListaCuentas(new ArrayList<CuentaConTenenciaDTO>());
		Mockito.when(sesionParametros.getTenenciasFondoDTO()).thenReturn(tenencias);
		
		ConsultaFondoOutEntity fondo = new ConsultaFondoOutEntity();
        when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondo);

        MovimientosInView viewRequest = new MovimientosInView();
        viewRequest.setFechaDesde("20052017");
        viewRequest.setFechaHasta("15072017");
        Respuesta<MovimientosView> ultimosMovimientos = fondoManager.busquedaMovimientos(viewRequest);
        
        Assert.assertNotNull(ultimosMovimientos);
        Assert.assertEquals(EstadoRespuesta.ERROR, ultimosMovimientos.getEstadoRespuesta()); 
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void busquedaMovimientosErrorParseo() throws DAOException {
        Respuesta<MovimientosFondoOutEntity> responseMovimientos = new Respuesta<MovimientosFondoOutEntity>();
        responseMovimientos.setEstadoRespuesta(EstadoRespuesta.ERROR);
        List<MovimientoFondoEntity> movimientoFondo = new ArrayList<MovimientoFondoEntity>();
        MovimientoFondoEntity mov = new MovimientoFondoEntity();
        mov.setDescripcionMovimiento("Prueba");
        mov.setImporte("10000");
        mov.setFechaDeConversion("03/07/2017");
        MovimientosFondoOutEntity movimientoFondos = new MovimientosFondoOutEntity();
        movimientoFondo.add(mov);
        movimientoFondos.setMovimientos(movimientoFondo);
        responseMovimientos.setRespuesta(movimientoFondos);
        List<ItemMensajeRespuesta> list = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(TipoError.ERROR_SERVICIO.getDescripcion());
        list.add(item);
        responseMovimientos.setItemMensajeRespuesta(list);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        Respuesta<Object> rta = new Respuesta<Object>();
        rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
        imr.setTipoError("ERROR");

        rta.add(imr);

        Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_FALLO_SERVICIOS)).thenReturn(rta);
        
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyList())).thenReturn(rta);

        Mockito.when(fondoBO.consultarMovimientos(Matchers.any(Cliente.class), Matchers.any(MovimientosInView.class)))
                .thenReturn(responseMovimientos);

		TenenciasFondoDTO tenencias = new TenenciasFondoDTO();
		tenencias.setListaCuentas(new ArrayList<CuentaConTenenciaDTO>());
		Mockito.when(sesionParametros.getTenenciasFondoDTO()).thenReturn(tenencias);
		
		ConsultaFondoOutEntity fondo = new ConsultaFondoOutEntity();
        when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondo);

        MovimientosInView viewRequest = new MovimientosInView();
        viewRequest.setFechaDesde("a052017");
        viewRequest.setFechaHasta("b152017");
        Respuesta<MovimientosView> ultimosMovimientos = fondoManager.busquedaMovimientos(viewRequest);

        Assert.assertNotNull(ultimosMovimientos);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void busquedaMovimientosErrorImporteConvert() throws DAOException {
        Respuesta<MovimientosFondoOutEntity> responseMovimientos = new Respuesta<MovimientosFondoOutEntity>();
        responseMovimientos.setEstadoRespuesta(EstadoRespuesta.OK);
        List<MovimientoFondoEntity> movimientoFondo = new ArrayList<MovimientoFondoEntity>();
        MovimientoFondoEntity mov = new MovimientoFondoEntity();
        mov.setDescripcionMovimiento("Prueba");
        mov.setImporte("we10000a");
        mov.setFechaDeConversion("03/07/2017");
        MovimientosFondoOutEntity movimientoFondos = new MovimientosFondoOutEntity();
        movimientoFondo.add(mov);
        movimientoFondos.setMovimientos(movimientoFondo);
        responseMovimientos.setRespuesta(movimientoFondos);
        List<ItemMensajeRespuesta> list = new ArrayList<ItemMensajeRespuesta>();
        ItemMensajeRespuesta item = new ItemMensajeRespuesta();
        item.setTipoError(TipoError.ERROR_SERVICIO.getDescripcion());
        list.add(item);
        responseMovimientos.setItemMensajeRespuesta(list);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("mensaje");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<Object> rta = new Respuesta<Object>();
        rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
        imr.setTipoError("ERROR");

        rta.add(imr);

        Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_FALLO_SERVICIOS)).thenReturn(rta);

        Mockito.when(fondoBO.consultarMovimientos(Matchers.any(Cliente.class), Matchers.any(MovimientosInView.class)))
                .thenReturn(responseMovimientos);
        
        Respuesta<Object> rtaDatoItemMensaje = new Respuesta<Object>();
        Mockito.when(respuestaFactory.crearRespuestaError(Matchers.anyList())).thenReturn(rtaDatoItemMensaje);

		TenenciasFondoDTO tenencias = new TenenciasFondoDTO();
		tenencias.setListaCuentas(new ArrayList<CuentaConTenenciaDTO>());
		Mockito.when(sesionParametros.getTenenciasFondoDTO()).thenReturn(tenencias);
		
		ConsultaFondoOutEntity fondo = new ConsultaFondoOutEntity();
        when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondo);

        MovimientosInView viewRequest = new MovimientosInView();
        viewRequest.setFechaDesde("200520170330");
        viewRequest.setFechaHasta("150720170123");
        Respuesta<MovimientosView> ultimosMovimientos = fondoManager.busquedaMovimientos(viewRequest);

        Assert.assertNotNull(ultimosMovimientos);
    }

    @Test
    public void consultarHorariosYHonorarios() {
        Respuesta<ConsultaHorariosYHonorariosView> consultarHorariosYHonorarios = new Respuesta<ConsultaHorariosYHonorariosView>();
        consultarHorariosYHonorarios.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.when(fondoBO.obtenerFondosHorariosYHonorarios()).thenReturn(consultarHorariosYHonorarios);

        Respuesta<ConsultaHorariosYHonorariosView> consultarHorariosYHonorarios1 = fondoBO
                .obtenerFondosHorariosYHonorarios();
        Assert.assertNotNull(consultarHorariosYHonorarios1);

    }

    @SuppressWarnings("unchecked")
    @Test
    @Ignore
    public void consultarCotizaciones() {
        Respuesta<CotizacionDeFondosView> respuestaView = new Respuesta<CotizacionDeFondosView>();
        respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(fondoBO.consultarCotizaciones()).thenReturn(respuestaView);

        when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(CotizacionDeFondosView.class)))
                .thenReturn(respuestaView);

        Respuesta<CotizacionDeFondosView> respuestaView1 = fondoManager.consultarCotizaciones();
        Assert.assertNotNull(respuestaView1);
    }

    @Test
    @Ignore
    public void consultarCotizacionesError() {
        Respuesta<CotizacionDeFondosView> respuestaView = new Respuesta<CotizacionDeFondosView>();
        respuestaView.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(fondoBO.consultarCotizaciones()).thenReturn(respuestaView);

        Respuesta<Object> rta = new Respuesta<Object>();
        rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
        imr.setTipoError("ERROR");

        rta.add(imr);

        Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(rta);

        Respuesta<CotizacionDeFondosView> respuestaView1 = fondoManager.consultarCotizaciones();
        Assert.assertNotNull(respuestaView1);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void verComprobante() {
        Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);

        Respuesta<ComprobanteSuscripcionView> comprobanteSuscripcionView = new Respuesta<ComprobanteSuscripcionView>();
        DatosComprobante viewRequest = new DatosComprobante();
        viewRequest.setNumeroComprobante("12345");
        when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
                Matchers.any(ComprobanteSuscripcionView.class))).thenReturn(comprobanteSuscripcionView);

        Respuesta<ComprobanteSuscripcionView> comprobanteSuscripcionView1 = fondoManager.verComprobante(viewRequest);
        Assert.assertNotNull(comprobanteSuscripcionView1);

    }
    
    @Test
    public void verComprobanteError() {
        Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);

        DatosComprobante viewRequest = new DatosComprobante();
        viewRequest.setNumeroComprobante("12345");

        Respuesta<Object> rta = new Respuesta<Object>();
        rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
        imr.setTipoError("ERROR");
        rta.add(imr);

        Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(rta);

        Respuesta<ComprobanteSuscripcionView> comprobanteSuscripcionView1 = fondoManager.verComprobante(viewRequest);
        Assert.assertNotNull(comprobanteSuscripcionView1);

    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void verComprobanteBPriv() {
        Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);

        Respuesta<ComprobanteSuscripcionView> comprobanteSuscripcionView = new Respuesta<ComprobanteSuscripcionView>();
        DatosComprobante viewRequest = new DatosComprobante();
        viewRequest.setNumeroComprobante("12345");
        when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
                Matchers.any(ComprobanteSuscripcionView.class))).thenReturn(comprobanteSuscripcionView);

        Respuesta<ComprobanteSuscripcionView> comprobanteSuscripcionView1 = fondoManager
                .verComprobanteBPriv(viewRequest);
        Assert.assertNotNull(comprobanteSuscripcionView1);

    }

    @Test
    public void verComprobanteBPrivError() {
        Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);

        DatosComprobante viewRequest = new DatosComprobante();
        viewRequest.setNumeroComprobante("12345");

        Respuesta<Object> rta = new Respuesta<Object>();
        rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
        imr.setTipoError("ERROR");
        rta.add(imr);

        Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(rta);

        Respuesta<ComprobanteSuscripcionView> comprobanteSuscripcionView1 = fondoManager
                .verComprobanteBPriv(viewRequest);
        Assert.assertNotNull(comprobanteSuscripcionView1);

    }

    @SuppressWarnings("unchecked")
    @Test
    @Ignore
    public void obtenerDatosConfigBpriv() throws BusinessException {
        
        Respuesta<Boolean> client = new Respuesta<Boolean>();
        client.setRespuesta(true);
        
        Respuesta<CuentasAdhesionDebitoView> obtenerSaldosCuentaOperativa = new Respuesta<CuentasAdhesionDebitoView>();
        
        Mockito.when(fondoBO.obtenerMarcaContrato(Matchers.any(Cliente.class))).thenReturn(client);
        
        Mockito.when(fondoBO
                .obtenerSaldosCuentaOperativa(Matchers.anyString(),Matchers.any(Cliente.class),Matchers.anyBoolean())).thenReturn(obtenerSaldosCuentaOperativa);
        
        Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
        
        Respuesta<ConfigFondoBPrivView> respuestaObtenerDatosConfigBpriv  = new Respuesta<ConfigFondoBPrivView>();
        when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
                Matchers.any(ComprobanteSuscripcionView.class))).thenReturn(respuestaObtenerDatosConfigBpriv);
        
        ConfigFondoBPrivView requestView = new ConfigFondoBPrivView();
        requestView.setTieneGastos("true");
        
        when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Respuesta<ConfigFondoBPrivView> obtenerDatosConfigBpriv = fondoManager.obtenerDatosConfigBpriv(requestView);
        Assert.assertNotNull(obtenerDatosConfigBpriv);
        
    }
    
    @Test
    @Ignore
    public void obtenerDatosConfigBprivError() throws BusinessException {
        
        Respuesta<Boolean> client = new Respuesta<Boolean>();
        client.setRespuesta(true);
        
        Respuesta<CuentasAdhesionDebitoView> obtenerSaldosCuentaOperativa = new Respuesta<CuentasAdhesionDebitoView>();
        
        Mockito.when(fondoBO.obtenerMarcaContrato(Matchers.any(Cliente.class))).thenReturn(client);
        
        Mockito.when(fondoBO
                .obtenerSaldosCuentaOperativa(Matchers.anyString(),Matchers.any(Cliente.class),Matchers.anyBoolean())).thenReturn(obtenerSaldosCuentaOperativa);
        
        Respuesta<String> respuestaLegales = new Respuesta<String>();
        respuestaLegales.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaLegales.setRespuesta("Respuesta");
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(respuestaLegales);
        
        Respuesta<Object> rta = new Respuesta<Object>();
        rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
        imr.setTipoError("ERROR");
        rta.add(imr);

        Mockito.when(respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(rta);
        
        ConfigFondoBPrivView requestView = new ConfigFondoBPrivView();
        requestView.setTieneGastos("true");
        
        when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Respuesta<ConfigFondoBPrivView> obtenerDatosConfigBpriv = fondoManager.obtenerDatosConfigBpriv(requestView);
        Assert.assertNotNull(obtenerDatosConfigBpriv);
        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void simularSuscripcionBPrivOk() {

        boolean respuestaEstadistica = true;
        when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class)))
                .thenReturn(respuestaEstadistica);

        when(sesionCliente.getCliente()).thenReturn(new Cliente());

        Set<ConstraintViolation<SimulacionInView>> respValidator = new HashSet<ConstraintViolation<SimulacionInView>>();
        when(validator.validate(Matchers.any(SimulacionInView.class))).thenReturn(respValidator);

        Respuesta<SimularSuscripcionOutDTO> rtaBO = new Respuesta<SimularSuscripcionOutDTO>();
        rtaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        rtaBO.setRespuesta(new SimularSuscripcionOutDTO());
        Mockito.when(fondoBO.simularSuscripcionBPriv(Matchers.any(SimulacionInDTO.class), Matchers.any(Cliente.class)))
                .thenReturn(rtaBO);

        Respuesta<SimulacionSuscripcionOutView> rtaManager = new Respuesta<SimulacionSuscripcionOutView>();
        rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
                Matchers.any(SimulacionSuscripcionOutView.class))).thenReturn(rtaManager);

        SimulacionInView viewRequest = new SimulacionInView();
        viewRequest.setNroCuentaBancaPrivada("700123456");
        viewRequest.setCodigoFondo("103");
        viewRequest.setImporte(BigDecimal.valueOf(Long.valueOf("10500")));

        Respuesta<SimulacionSuscripcionOutView> resultado = fondoManager.simularSuscripcionBPriv(viewRequest);
        Assert.assertNotNull(resultado);

    }
    
    @Ignore
    @SuppressWarnings("unchecked")
    @Test
    public void simularSuscripcionBPrivError() {

        boolean respuestaEstadistica = true;
        when(estadisticaManager.add(Matchers.any(String.class), Matchers.any(String.class)))
                .thenReturn(respuestaEstadistica);

        when(sesionCliente.getCliente()).thenReturn(new Cliente());

        Set<ConstraintViolation<SimulacionInView>> respValidator = new HashSet<ConstraintViolation<SimulacionInView>>();
        when(validator.validate(Matchers.any(SimulacionInView.class))).thenReturn(respValidator);

        Respuesta<SimularSuscripcionOutDTO> rtaBO = new Respuesta<SimularSuscripcionOutDTO>();
        rtaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        rtaBO.setRespuesta(new SimularSuscripcionOutDTO());
        Mockito.when(fondoBO.simularSuscripcionBPriv(Matchers.any(SimulacionInDTO.class), Matchers.any(Cliente.class)))
                .thenReturn(rtaBO);

        Respuesta<SimulacionSuscripcionOutView> rta = new Respuesta<SimulacionSuscripcionOutView>();
        rta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta imr = new ItemMensajeRespuesta();
        imr.setTipoError("ERROR");
        rta.add(imr);

        when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyString(),
                Matchers.anyString())).thenReturn(rta);

        SimulacionInView viewRequest = new SimulacionInView();
        viewRequest.setNroCuentaBancaPrivada("700123456");
        viewRequest.setCodigoFondo("103");
        viewRequest.setImporte(BigDecimal.valueOf(Long.valueOf("10500")));

        Respuesta<SimulacionSuscripcionOutView> resultado = fondoManager.simularSuscripcionBPriv(viewRequest);
        Assert.assertNotNull(resultado);

    }

    @SuppressWarnings("unchecked")
    @Test
    public void aceptarContratoOk() throws BusinessException{
        
        Respuesta<Boolean> contratoAceptado = new Respuesta<Boolean>();
        contratoAceptado.setEstadoRespuesta(EstadoRespuesta.OK);
        contratoAceptado.setRespuesta(true);
        
        Mockito.when(fondoBO.aceptarContrato(Matchers.any(Cliente.class))).thenReturn(contratoAceptado);
        
        Respuesta<ConfigFondoView> rtaManager = new Respuesta<ConfigFondoView>();
        rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
                Matchers.any(ConfigFondoView.class))).thenReturn(rtaManager);
        
        ConfigFondoView requestView = new ConfigFondoView();
        requestView.setContratoAceptado(true);
        
        Respuesta<ConfigFondoView> respuesta = fondoManager.aceptarContrato(requestView);
        Assert.assertNotNull(respuesta);
        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void aceptarContratoError(){
        
        ConfigFondoView requestView = new ConfigFondoView();
        requestView.setContratoAceptado(false);
        
        Respuesta<ConfigFondoView> rtaFactory = new Respuesta<ConfigFondoView>();
        rtaFactory.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(respuestaFactory.crearRespuestaError(Matchers.any(Class.class), Matchers.anyString(), Matchers.anyString())).thenReturn(rtaFactory);
        
        Respuesta<ConfigFondoView> respuesta = fondoManager.aceptarContrato(requestView);
        Assert.assertNotNull(respuesta);
    
    }

    @Ignore 
    @Test
    public void obtenerDatosConfig() throws BusinessException{
        
        Respuesta<Boolean> marcaDeContrato = new Respuesta<Boolean>();
        marcaDeContrato.setRespuesta(true);
        Mockito.when(fondoBO.obtenerMarcaContrato(Matchers.any(Cliente.class))).thenReturn(marcaDeContrato);

        Respuesta<CuentasView> cuentaM = new Respuesta<CuentasView>();
        CuentasView cuenta1 = new CuentasView();
        List<CuentasAdhesionDebitoView> listaAdD = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView adD1 = new CuentasAdhesionDebitoView();
        adD1.setAbreviaturaTipoCuenta("TC");
        adD1.setAlias("alias");
        cuenta1.setCuentas(listaAdD);
        cuentaM.setRespuesta(cuenta1);

        List<Cuenta> cuentasCepo = new ArrayList<Cuenta>();        
        Mockito.when(cuentaManager.getCuentasSaldoPorMoneda(Matchers.anyString())).thenReturn(cuentaM);
        Mockito.when(cuentaManager.getCuentasCepo(Matchers.any(Cliente.class))).thenReturn(cuentasCepo);
        
        ConfigFondoView requestView = new ConfigFondoView();
        requestView.setMoneda("moneda");
        
        Respuesta<ConfigFondoView> obtenerDatosConfig2 = fondoManager.obtenerDatosConfig(requestView);
        Assert.assertNotNull(obtenerDatosConfig2);

    }
    
    @Test
    public void convertirStrToBigDecimal() throws ImporteConvertException{
        String cantidad = "00000000052283000";
        int decimales = 6;
        BigDecimal resultado = null;
        resultado = convertirStrToBigDecimalVC(cantidad,decimales);
        
       System.out.println("Resultado= "+resultado);
        
    }
    
    public static BigDecimal convertirStrToBigDecimalVC(String imp, int cantDecimales) throws ImporteConvertException {
        String importe = imp.substring(0, 15);
        if (importe == null || importe.equalsIgnoreCase("")) {
            throw new ImporteConvertException();
        }

        BigDecimal importeDecimal = null;
        StringBuffer result = new StringBuffer();
        for (int i = importe.length(); i > 0; i--) {
            result.append(importe.charAt(i - 1));
            if (result.length() == cantDecimales) {
                result = result.append(".");
            }
        }

        try {
            importeDecimal = new BigDecimal(StringUtils.reverse(result.toString()));
        } catch (NumberFormatException e) {
            throw new ImporteConvertException(e);
        }
        return importeDecimal;
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerGraficosRTLOk(){
        Respuesta<GraficoFondosRTLOutDTO> rtaBO = new Respuesta<GraficoFondosRTLOutDTO>();
        GraficoFondosRTLOutDTO graficoFondosRTLOutDTO = new GraficoFondosRTLOutDTO();
        List<PorcentajeGraficoFondos> listGraficos = new ArrayList<PorcentajeGraficoFondos>();
        PorcentajeGraficoFondos porcentajes = new PorcentajeGraficoFondos();
        porcentajes.setTipoFondo("MONEY_MARKET");
        porcentajes.setTenenciaValuadaTotalPesos(new BigDecimal("12345.78"));
        porcentajes.setTenenciaValuadaTotalDolares(new BigDecimal("54321.87"));
        porcentajes.setPorcentajeTenenciaPesos(43);
        porcentajes.setPorcentajeTenenciaDolares(57);
        listGraficos.add(porcentajes);
        graficoFondosRTLOutDTO.setPorcentajeTenenciaPesos(43);
        graficoFondosRTLOutDTO.setListaPorcentaje(listGraficos);
        rtaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        rtaBO.setRespuesta(graficoFondosRTLOutDTO);
        Mockito.when(fondoBO.obtenerGraficosRTL(Matchers.any(Cliente.class))).thenReturn(rtaBO);
        
        Respuesta<GraficoFondosRTLOutView> rtaManager = new Respuesta<GraficoFondosRTLOutView>();
        rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
                Matchers.any(GraficoFondosRTLOutView.class))).thenReturn(rtaManager);
        
        Respuesta<GraficoFondosRTLOutView> rta = fondoManager.obtenerGraficosRTL();

        Assert.assertNotNull(rta); 
        Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    @Test
    public void obtenerGraficosRTLError(){
        Respuesta<GraficoFondosRTLOutDTO> rtaBO = new Respuesta<GraficoFondosRTLOutDTO>();
        rtaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(fondoBO.obtenerGraficosRTL(Matchers.any(Cliente.class))).thenReturn(rtaBO);
        
        Respuesta<GraficoFondosRTLOutView> rta = fondoManager.obtenerGraficosRTL();
        Assert.assertNotNull(rta);
        Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.ERROR);             
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerGraficosBprivOk(){
        Respuesta<GraficoFondosBPrivOutDTO> rtaBO = new Respuesta<GraficoFondosBPrivOutDTO>();
        GraficoFondosBPrivOutDTO graficoFondosBPrivOutDTO = new GraficoFondosBPrivOutDTO();
        List<GraficosFondosPorCuentaBPriv> list = new ArrayList<GraficosFondosPorCuentaBPriv>();
        GraficosFondosPorCuentaBPriv graficosFondosPorCuentaBPriv = new GraficosFondosPorCuentaBPriv();
        List<PorcentajeGraficoFondos> listGraficos = new ArrayList<PorcentajeGraficoFondos>();
        PorcentajeGraficoFondos porcentajes = new PorcentajeGraficoFondos();
        porcentajes.setTipoFondo("MONEY_MARKET");
        porcentajes.setTenenciaValuadaTotalPesos(new BigDecimal("12345.78"));
        porcentajes.setTenenciaValuadaTotalDolares(new BigDecimal("54321.87"));
        porcentajes.setPorcentajeTenenciaPesos(43);
        porcentajes.setPorcentajeTenenciaDolares(57);
        listGraficos.add(porcentajes);
        graficosFondosPorCuentaBPriv.setNroCuentaTitulo("12345678");
        graficosFondosPorCuentaBPriv.setPorcentajeTenenciaPesos(43);
        graficosFondosPorCuentaBPriv.setListaPorcentaje(listGraficos);
        list.add(graficosFondosPorCuentaBPriv);
        graficoFondosBPrivOutDTO.setListGraficosPorCuenta(list);
        rtaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        rtaBO.setRespuesta(graficoFondosBPrivOutDTO);
        
        Mockito.when(fondoBO.obtenerGraficosBpriv(Matchers.any(Cliente.class))).thenReturn(rtaBO);
        
        Respuesta<GraficoFondosBPrivOutView> rtaManager = new Respuesta<GraficoFondosBPrivOutView>();
        rtaManager.setEstadoRespuesta(EstadoRespuesta.OK);
        when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class),
                Matchers.any(GraficoFondosBPrivOutView.class))).thenReturn(rtaManager);
        
        Respuesta<GraficoFondosBPrivOutView> rta = fondoManager.obtenerGraficosBPriv();

        Assert.assertNotNull(rta); 
        Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    @Test
    public void obtenerGraficosBPrivError(){
        Respuesta<GraficoFondosBPrivOutDTO> rtaBO = new Respuesta<GraficoFondosBPrivOutDTO>();
        rtaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mockito.when(fondoBO.obtenerGraficosBpriv(Matchers.any(Cliente.class))).thenReturn(rtaBO);
        
        Respuesta<GraficoFondosBPrivOutView> rta = fondoManager.obtenerGraficosBPriv();
        Assert.assertNotNull(rta);
        Assert.assertEquals(rta.getEstadoRespuesta(), EstadoRespuesta.ERROR);             
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerFondosUltimosMovimientos() {
        Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
        CuentasConsultaFondoDTO respuestaDTO = new CuentasConsultaFondoDTO();
        List<FondoResumidoDTO> fondosTotales = new ArrayList<FondoResumidoDTO>();
        FondoResumidoDTO fondoDTO2 = new FondoResumidoDTO();
        fondoDTO2.setIdMensajeGastos("1039");
        fondosTotales.add(fondoDTO2);
        respuestaDTO.setFondosTotales(fondosTotales);
        List<CuentaTituloDTO> cuentasTitulo = new ArrayList<CuentaTituloDTO>();
        CuentaTituloDTO ctaTit = new CuentaTituloDTO();
        List<FondoResumidoDTO> fondosSuscriptos = new ArrayList<FondoResumidoDTO>();
        FondoResumidoDTO fondoDTO = new FondoResumidoDTO();
        fondoDTO.setIdMensajeGastos("1039");
        fondosSuscriptos.add(fondoDTO);
        ctaTit.setFondosSuscriptos(fondosSuscriptos);
        ctaTit.setCuentaOperativaSinFormatear("13");
        cuentasTitulo.add(ctaTit);
        respuestaDTO.setCuentasTitulo(cuentasTitulo);
        respuestaBO.setRespuesta(respuestaDTO);
        respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
        when(fondoBO.obtenerFondosSuscriptosYDisponibles(Matchers.any(CuentasConsultaFondoDTO.class),
                Matchers.any(Cliente.class), Matchers.anyBoolean(),Matchers.anyBoolean())).thenReturn(respuestaBO);

        Respuesta<CuentasConsultaFondoView> rtaFactory = new Respuesta<CuentasConsultaFondoView>();
        rtaFactory.setEstadoRespuesta(EstadoRespuesta.OK);
        when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(CuentasConsultaFondoView.class)))
                .thenReturn(rtaFactory);

        CuentasConsultaFondoView viewRequest = new CuentasConsultaFondoView();
        List<CuentaTituloView> cuentasTituloView = new ArrayList<CuentaTituloView>();
        cuentasTituloView.add(new CuentaTituloView());
        viewRequest.setCuentasTitulo(cuentasTituloView);
        Respuesta<CuentasConsultaFondoView> rtaManager = fondoManager.obtenerFondosSuscriptosYDisponibles(viewRequest);

        Assert.assertNotNull(rtaManager);
    }
    
}
