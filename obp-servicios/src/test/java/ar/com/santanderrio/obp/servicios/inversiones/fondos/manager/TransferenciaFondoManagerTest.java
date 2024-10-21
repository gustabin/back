/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.manager;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

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
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.generated.webservices.inversiones.DatosEvaluacionRiesgo;
import ar.com.santanderrio.obp.generated.webservices.inversiones.EvaluacionDeRiesgoResponse;
import ar.com.santanderrio.obp.generated.webservices.inversiones.entities.Mensaje;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dao.InversionDAO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.manager.BaseManager;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosComprobante;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.FondoBO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.TransferenciaFondoBO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.consultas.dao.ConsultaFondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.ConfigTransferenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaTituloDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentasConsultaFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarTransferenciaBprivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FondoResumidoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ComprobanteSuscripcionView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigTransferenciaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigTransferenciaView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentaTituloView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.CuentasConsultaFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarTransferenciaFondoInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.FinalizarTransferenciaFondoView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionTransferenciaInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.SimulacionTransferenciaView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.TransferenciaView;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;

/**
 * The Class TransferenciaFondoManagerTest.
 *
 * @author
 */
@RunWith(MockitoJUnitRunner.class)
public class TransferenciaFondoManagerTest {

    /** The transferencia manager. */
    @InjectMocks
    private TransferenciaFondoManagerImpl transferenciaManager;

    /** The transferencia BO. */
    @Mock
    private TransferenciaFondoBO transferenciaBO;

    @Mock
    private Validator validator;
    
    @Spy
    private TransferenciaFondoManagerImpl transferenciaManagerMock;
    
    @Mock
    private SesionParametros sesionParametros;
    
    @Mock
    private SesionCliente sesionCliente;
    
    @Mock
    private EstadisticaManager estadisticaManager;
    
    @Mock
    private BaseManager baseManager;
    
    @Mock
    private ConsultaFondoDAO consultaFondoDAO;
    
    @Mock
    private InversionDAO inversionDAO;
    
    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory;

    @Mock
    private LegalBO legalBO;

    @Mock
    private FondoBO fondoBO;
    
    TransferenciaView transferenciaView;
    @Before
    public void init() throws ServiceException {
        transferenciaView = new TransferenciaView();
        transferenciaView.setCuentaTitulo("00000000/0");
        transferenciaView.setNumeroCertificado("123456");
        transferenciaView.setImporteNeto("300");
        transferenciaView.setCodigoFondo("1234567");
    }
    
    
    
    /**
     * Iniciar transferencia banca privada test caso OK
     */
    @SuppressWarnings("unchecked")
	@Test
    public void iniciarTransferenciaBPrivTestOk() {
//    	FieldUtils.writeField(rescateManager, "rescateBO", rescateBO, true);
    	
    	CuentasConsultaFondoView viewRequest = new CuentasConsultaFondoView();
        String tipoBanca = "BP";
		viewRequest.setTipoBanca(tipoBanca);
        List<CuentaTituloView> cuentasTitulo = new ArrayList<CuentaTituloView>();
        CuentaTituloView cuentaTitView = new CuentaTituloView();
        String nroCuenta = "12345678";
		cuentaTitView.setNroCuenta(nroCuenta);
        String sucursal = "250";
		cuentaTitView.setSucursal(sucursal);
        String nroCuentaFormateado = "250-1234567/8";
		cuentaTitView.setNroCuentaFormateado(nroCuentaFormateado);
        String cuentaOperativa = "12345678";
		cuentaTitView.setCuentaOperativa(cuentaOperativa);
        List<Interviniente> intervinientes = new ArrayList<Interviniente>();
        Interviniente interviniente = new Interviniente();
		intervinientes.add(interviniente);
		cuentaTitView.setIntervinientes(intervinientes);
		cuentasTitulo.add(cuentaTitView);
		viewRequest.setCuentasTitulo(cuentasTitulo);
		
	//	Respuesta<CuentasConsultaFondoView> respuestaValidator = new Respuesta<CuentasConsultaFondoView>();
		//respuestaValidator.setEstadoRespuesta(EstadoRespuesta.OK);
	//	Mockito.when(baseManager.validate(Matchers.any(CuentasConsultaFondoView.class), Matchers.any(CuentasConsultaFondoView.class))).thenReturn(respuestaValidator);
		
		Set<ConstraintViolation<CuentasConsultaFondoView>> respValidator = new HashSet<ConstraintViolation<CuentasConsultaFondoView>>();
		Mockito.when(validator.validate(Matchers.any(CuentasConsultaFondoView.class))).thenReturn(respValidator);
		
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());

		Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		CuentasConsultaFondoDTO respuesta = new CuentasConsultaFondoDTO();
		List<FondoResumidoDTO> fondosTotales = new ArrayList<FondoResumidoDTO>();
		FondoResumidoDTO fondoDTO = new FondoResumidoDTO();
		String importeMaximo = "999999";
		fondoDTO.setImporteMaximo(importeMaximo);
		String importeMinimo = "100";
		fondoDTO.setImporteMinimo(importeMinimo);
		String moneda = "ARS";
		fondoDTO.setMoneda(moneda);
		String nombreFondo = "Super Ahorro $";
		fondoDTO.setNombreFondo(nombreFondo);
		String saldo = "10500";
		fondoDTO.setSaldo(saldo);
		String grupo = "Money market";
		fondoDTO.setGrupo(grupo);
		String codigoFondo = "007";
		fondoDTO.setCodigoFondo(codigoFondo);
		String horario = "Horario";
		fondoDTO.setHorario(horario);
		String descripcionLarga = "Descripcion larga fondo";
		fondoDTO.setDescripcionLarga(descripcionLarga);
		String idMensajeGastos = "123";
		fondoDTO.setIdMensajeGastos(idMensajeGastos);
		fondosTotales.add(fondoDTO);
		respuesta.setFondosTotales(fondosTotales);
		
		List<CuentaTituloDTO> cuentasTituloDTO = new ArrayList<CuentaTituloDTO>();
		CuentaTituloDTO cuentaTitDTO = new CuentaTituloDTO();
		cuentaTitDTO.setNroCuenta(nroCuenta);
		cuentaTitDTO.setSucursal(sucursal);
		cuentaTitDTO.setNroCuentaFormateado(nroCuentaFormateado);
		cuentaTitDTO.setCuentaOperativa(cuentaOperativa);
		String cuentaOperativaSinFormatear = "123456";
		cuentaTitDTO.setCuentaOperativaSinFormatear(cuentaOperativaSinFormatear);
		cuentaTitDTO.setIntervinientes(intervinientes);
		cuentaTitDTO.setFondosSuscriptos(fondosTotales);
		cuentasTituloDTO.add(cuentaTitDTO);
		respuesta.setCuentasTitulo(cuentasTituloDTO);
		
		respuestaBO.setRespuesta(respuesta);
		Mockito.when(transferenciaBO.obtenerFondosSuscriptosYDisponiblesBpriv(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
		
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
		
		Respuesta<CuentasConsultaFondoView> respuestaViewOk = new Respuesta<CuentasConsultaFondoView>();
		respuestaViewOk.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(CuentasConsultaFondoView.class))).thenReturn(respuestaViewOk);
		
		Respuesta<CuentasConsultaFondoView> respuestaManager = transferenciaManager.iniciarTransferenciaBpriv(viewRequest);
		
		Assert.assertNotNull(respuestaManager);
    }
    
    /**
     * Iniciar transferencia banca privada test caso ERROR
     */
    @Test
    public void iniciarTransferenciaBPrivTestError() {
    	CuentasConsultaFondoView viewRequest = new CuentasConsultaFondoView();
        String tipoBanca = "BP";
		viewRequest.setTipoBanca(tipoBanca);
        List<CuentaTituloView> cuentasTitulo = new ArrayList<CuentaTituloView>();
        CuentaTituloView cuentaTit = new CuentaTituloView();
        String nroCuenta = "12345678";
		cuentaTit.setNroCuenta(nroCuenta);
        String sucursal = "250";
		cuentaTit.setSucursal(sucursal);
        String nroCuentaFormateado = "250-1234567/8";
		cuentaTit.setNroCuentaFormateado(nroCuentaFormateado);
        String cuentaOperativa = "12345678";
		cuentaTit.setCuentaOperativa(cuentaOperativa);
        List<Interviniente> intervinientes = new ArrayList<Interviniente>();
        Interviniente interviniente = new Interviniente();
		intervinientes.add(interviniente);
		cuentaTit.setIntervinientes(intervinientes);
		cuentasTitulo.add(cuentaTit);
		viewRequest.setCuentasTitulo(cuentasTitulo);
		
		Set<ConstraintViolation<CuentasConsultaFondoView>> respValidator = new HashSet<ConstraintViolation<CuentasConsultaFondoView>>();
		Mockito.when(validator.validate(Matchers.any(CuentasConsultaFondoView.class))).thenReturn(respValidator);
		
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());

		Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta mensaje = new ItemMensajeRespuesta();
		itemMensajeRespuesta.add(mensaje);
		respuestaBO.setItemMensajeRespuesta(itemMensajeRespuesta);
		Mockito.when(transferenciaBO.obtenerFondosSuscriptosYDisponiblesBpriv(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
		
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
		
		Respuesta<CuentasConsultaFondoView> respuestaViewError = new Respuesta<CuentasConsultaFondoView>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.eq(CuentasConsultaFondoView.class), Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaViewError);
		
		
		
		Respuesta<CuentasConsultaFondoView> respuesta = transferenciaManager.iniciarTransferenciaBpriv(viewRequest);
		
		Assert.assertNotNull(respuesta);
    }
    
   
    
    
    /**
     * Iniciar transferencia banca privada test caso WARNING
     */
	@Test
	@Ignore
    public void iniciarTransferenciaBPrivTestWarning() {
    	CuentasConsultaFondoView viewRequest = new CuentasConsultaFondoView();
        String tipoBanca = "BP";
		viewRequest.setTipoBanca(tipoBanca);
        List<CuentaTituloView> cuentasTitulo = new ArrayList<CuentaTituloView>();
        CuentaTituloView cuentaTit = new CuentaTituloView();
        String nroCuenta = "12345678";
		cuentaTit.setNroCuenta(nroCuenta);
        String sucursal = "250";
		cuentaTit.setSucursal(sucursal);
        String nroCuentaFormateado = "250-1234567/8";
		cuentaTit.setNroCuentaFormateado(nroCuentaFormateado);
        String cuentaOperativa = "12345678";
		cuentaTit.setCuentaOperativa(cuentaOperativa);
        List<Interviniente> intervinientes = new ArrayList<Interviniente>();
        Interviniente interviniente = new Interviniente();
		intervinientes.add(interviniente);
		cuentaTit.setIntervinientes(intervinientes);
		cuentasTitulo.add(cuentaTit);
		viewRequest.setCuentasTitulo(cuentasTitulo);
		
		Set<ConstraintViolation<CuentasConsultaFondoView>> respValidator = new HashSet<ConstraintViolation<CuentasConsultaFondoView>>();
		Mockito.when(validator.validate(Matchers.any(CuentasConsultaFondoView.class))).thenReturn(respValidator);
		
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());

		Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		Mockito.when(transferenciaBO.obtenerFondosSuscriptosYDisponiblesBpriv(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
		
		Respuesta<CuentasConsultaFondoView> respuestaViewWarning = new Respuesta<CuentasConsultaFondoView>();
		respuestaViewWarning.setEstadoRespuesta(EstadoRespuesta.WARNING);
		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.eq(CuentasConsultaFondoView.class), Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaViewWarning);
		
		Respuesta<CuentasConsultaFondoView> respuesta = transferenciaManager.iniciarTransferenciaBpriv(viewRequest);
		
		Assert.assertNotNull(respuesta);
    }
	
	/**
     * Iniciar transferencia banca privada test caso OK
     */
    @SuppressWarnings("unchecked")
	@Test
    public void iniciarTransferenciaTestOk() {
    	CuentasConsultaFondoView viewRequest = new CuentasConsultaFondoView();
        String tipoBanca = "BP";
		viewRequest.setTipoBanca(tipoBanca);
        List<CuentaTituloView> cuentasTitulo = new ArrayList<CuentaTituloView>();
        CuentaTituloView cuentaTitView = new CuentaTituloView();
        String nroCuenta = "12345678";
		cuentaTitView.setNroCuenta(nroCuenta);
        String sucursal = "250";
		cuentaTitView.setSucursal(sucursal);
        String nroCuentaFormateado = "250-1234567/8";
		cuentaTitView.setNroCuentaFormateado(nroCuentaFormateado);
        String cuentaOperativa = "12345678";
		cuentaTitView.setCuentaOperativa(cuentaOperativa);
        List<Interviniente> intervinientes = new ArrayList<Interviniente>();
        Interviniente interviniente = new Interviniente();
		intervinientes.add(interviniente);
		cuentaTitView.setIntervinientes(intervinientes);
		cuentasTitulo.add(cuentaTitView);
		viewRequest.setCuentasTitulo(cuentasTitulo);
		
		Set<ConstraintViolation<CuentasConsultaFondoView>> respValidator = new HashSet<ConstraintViolation<CuentasConsultaFondoView>>();
		Mockito.when(validator.validate(Matchers.any(CuentasConsultaFondoView.class))).thenReturn(respValidator);
		
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());

		Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		CuentasConsultaFondoDTO respuesta = new CuentasConsultaFondoDTO();
		List<FondoResumidoDTO> fondosTotales = new ArrayList<FondoResumidoDTO>();
		FondoResumidoDTO fondoDTO = new FondoResumidoDTO();
		String importeMaximo = "999999";
		fondoDTO.setImporteMaximo(importeMaximo);
		String importeMinimo = "100";
		fondoDTO.setImporteMinimo(importeMinimo);
		String moneda = "ARS";
		fondoDTO.setMoneda(moneda);
		String nombreFondo = "Super Ahorro $";
		fondoDTO.setNombreFondo(nombreFondo);
		String saldo = "10500";
		fondoDTO.setSaldo(saldo);
		String grupo = "Money market";
		fondoDTO.setGrupo(grupo);
		String codigoFondo = "007";
		fondoDTO.setCodigoFondo(codigoFondo);
		String horario = "Horario";
		fondoDTO.setHorario(horario);
		String descripcionLarga = "Descripcion larga fondo";
		fondoDTO.setDescripcionLarga(descripcionLarga);
		String idMensajeGastos = "123";
		fondoDTO.setIdMensajeGastos(idMensajeGastos);
		fondosTotales.add(fondoDTO);
		respuesta.setFondosTotales(fondosTotales);
		
		List<CuentaTituloDTO> cuentasTituloDTO = new ArrayList<CuentaTituloDTO>();
		CuentaTituloDTO cuentaTitDTO = new CuentaTituloDTO();
		cuentaTitDTO.setNroCuenta(nroCuenta);
		cuentaTitDTO.setSucursal(sucursal);
		cuentaTitDTO.setNroCuentaFormateado(nroCuentaFormateado);
		cuentaTitDTO.setCuentaOperativa(cuentaOperativa);
		String cuentaOperativaSinFormatear = "123456";
		cuentaTitDTO.setCuentaOperativaSinFormatear(cuentaOperativaSinFormatear);
		cuentaTitDTO.setIntervinientes(intervinientes);
		cuentaTitDTO.setFondosSuscriptos(fondosTotales);
		cuentasTituloDTO.add(cuentaTitDTO);
		respuesta.setCuentasTitulo(cuentasTituloDTO);
		
		respuestaBO.setRespuesta(respuesta);
		Mockito.when(transferenciaBO.obtenerFondosSuscriptosYDisponibles(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
		
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
		
		Respuesta<CuentasConsultaFondoView> respuestaViewOk = new Respuesta<CuentasConsultaFondoView>();
		respuestaViewOk.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(CuentasConsultaFondoView.class))).thenReturn(respuestaViewOk);
		
		Respuesta<CuentasConsultaFondoView> respuestaManager = transferenciaManager.iniciarTransferencia(viewRequest);
		
		Assert.assertNotNull(respuestaManager);
    }
    
    /**
     * Iniciar transferencia banca privada test caso ERROR
     */
    @Test
    public void iniciarTransferenciaTestError() {
    	CuentasConsultaFondoView viewRequest = new CuentasConsultaFondoView();
        String tipoBanca = "BP";
		viewRequest.setTipoBanca(tipoBanca);
        List<CuentaTituloView> cuentasTitulo = new ArrayList<CuentaTituloView>();
        CuentaTituloView cuentaTit = new CuentaTituloView();
        String nroCuenta = "12345678";
		cuentaTit.setNroCuenta(nroCuenta);
        String sucursal = "250";
		cuentaTit.setSucursal(sucursal);
        String nroCuentaFormateado = "250-1234567/8";
		cuentaTit.setNroCuentaFormateado(nroCuentaFormateado);
        String cuentaOperativa = "12345678";
		cuentaTit.setCuentaOperativa(cuentaOperativa);
        List<Interviniente> intervinientes = new ArrayList<Interviniente>();
        Interviniente interviniente = new Interviniente();
		intervinientes.add(interviniente);
		cuentaTit.setIntervinientes(intervinientes);
		cuentasTitulo.add(cuentaTit);
		viewRequest.setCuentasTitulo(cuentasTitulo);
		
		Set<ConstraintViolation<CuentasConsultaFondoView>> respValidator = new HashSet<ConstraintViolation<CuentasConsultaFondoView>>();
		Mockito.when(validator.validate(Matchers.any(CuentasConsultaFondoView.class))).thenReturn(respValidator);
		
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());

		Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta mensaje = new ItemMensajeRespuesta();
		itemMensajeRespuesta.add(mensaje);
		respuestaBO.setItemMensajeRespuesta(itemMensajeRespuesta);
		Mockito.when(transferenciaBO.obtenerFondosSuscriptosYDisponibles(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
		
		Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(true);
		
		Respuesta<CuentasConsultaFondoView> respuestaViewError = new Respuesta<CuentasConsultaFondoView>();
		respuestaViewError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.eq(CuentasConsultaFondoView.class), Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaViewError);
		
		Respuesta<CuentasConsultaFondoView> respuesta = transferenciaManager.iniciarTransferencia(viewRequest);
		
		Assert.assertNotNull(respuesta);
    }
    
    /**
     * Iniciar transferencia banca privada test caso WARNING
     */
	@Test
	@Ignore
    public void iniciarTransferenciaTestWarning() {
    	CuentasConsultaFondoView viewRequest = new CuentasConsultaFondoView();
        String tipoBanca = "BP";
		viewRequest.setTipoBanca(tipoBanca);
        List<CuentaTituloView> cuentasTitulo = new ArrayList<CuentaTituloView>();
        CuentaTituloView cuentaTit = new CuentaTituloView();
        String nroCuenta = "12345678";
		cuentaTit.setNroCuenta(nroCuenta);
        String sucursal = "250";
		cuentaTit.setSucursal(sucursal);
        String nroCuentaFormateado = "250-1234567/8";
		cuentaTit.setNroCuentaFormateado(nroCuentaFormateado);
        String cuentaOperativa = "12345678";
		cuentaTit.setCuentaOperativa(cuentaOperativa);
        List<Interviniente> intervinientes = new ArrayList<Interviniente>();
        Interviniente interviniente = new Interviniente();
		intervinientes.add(interviniente);
		cuentaTit.setIntervinientes(intervinientes);
		cuentasTitulo.add(cuentaTit);
		viewRequest.setCuentasTitulo(cuentasTitulo);
		
		Set<ConstraintViolation<CuentasConsultaFondoView>> respValidator = new HashSet<ConstraintViolation<CuentasConsultaFondoView>>();
		Mockito.when(validator.validate(Matchers.any(CuentasConsultaFondoView.class))).thenReturn(respValidator);
		
		Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());

		Respuesta<CuentasConsultaFondoDTO> respuestaBO = new Respuesta<CuentasConsultaFondoDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		Mockito.when(transferenciaBO.obtenerFondosSuscriptosYDisponibles(Matchers.any(CuentasConsultaFondoDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
		
		Respuesta<CuentasConsultaFondoView> respuestaViewWarning = new Respuesta<CuentasConsultaFondoView>();
		respuestaViewWarning.setEstadoRespuesta(EstadoRespuesta.WARNING);
		Mockito.when(respuestaFactory.crearRespuestaWarning(Matchers.eq(CuentasConsultaFondoView.class), Matchers.any(String.class), Matchers.any(String.class))).thenReturn(respuestaViewWarning);
		
		Respuesta<CuentasConsultaFondoView> respuesta = transferenciaManager.iniciarTransferencia(viewRequest);
		
		Assert.assertNotNull(respuesta);
    }
	
	@Test
	public void obtenerDatosConfigTestOK() throws BusinessException{
		Respuesta<ConfigTransferenciaDTO> respuestaBO = new Respuesta<ConfigTransferenciaDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		ConfigTransferenciaDTO respuestaDTO = new ConfigTransferenciaDTO();
		String contratoAceptado = "1";
		respuestaDTO.setContratoAceptado(contratoAceptado);
		respuestaBO.setRespuesta(respuestaDTO);
		Mockito.when(transferenciaBO.obtenerDatosConfig(Matchers.any(ConfigTransferenciaInView.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
		
		Respuesta<ConfigTransferenciaView> respuestaOK = new Respuesta<ConfigTransferenciaView>();
		respuestaOK.setEstadoRespuesta(EstadoRespuesta.OK);
		Mockito.when(respuestaFactory.crearRespuestaOk(new ConfigTransferenciaView())).thenReturn(respuestaOK);
		Mockito.when(fondoBO.obtenerReglamento(Matchers.anyString())).thenReturn("url");
		ConfigTransferenciaInView configTransferenciaInView = new ConfigTransferenciaInView();
		Respuesta<ConfigTransferenciaView> respuestaManager = transferenciaManager.obtenerDatosConfig(configTransferenciaInView);
		
		Assert.assertNotNull(respuestaManager);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void obtenerDatosConfigTestError(){
		Respuesta<ConfigTransferenciaDTO> respuestaBO = new Respuesta<ConfigTransferenciaDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		List<ItemMensajeRespuesta> itemMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta mensaje = new ItemMensajeRespuesta();
		itemMensajeRespuesta.add(mensaje);
		respuestaBO.setItemMensajeRespuesta(itemMensajeRespuesta);
		Mockito.when(transferenciaBO.obtenerDatosConfig(Matchers.any(ConfigTransferenciaInView.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO);
		
		Respuesta<ConfigTransferenciaView> respuestaError = new Respuesta<ConfigTransferenciaView>();
		respuestaError.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Matchers.eq(ConfigTransferenciaView.class), Matchers.anyList())).thenReturn(respuestaError);
		
		ConfigTransferenciaInView configTransferenciaInView = new ConfigTransferenciaInView();
		Respuesta<ConfigTransferenciaView> respuestaManager = transferenciaManager.obtenerDatosConfig(configTransferenciaInView);
		
		Assert.assertNotNull(respuestaManager);
	}
	
	
	@Test
	public void finalizarTransferenciaFondosBprivOK() throws DAOException{
		FinalizarTransferenciaFondoInView finalizarTransferenciaFondoInView = new FinalizarTransferenciaFondoInView();
		finalizarTransferenciaFondoInView.setImporteNeto("300");
		finalizarTransferenciaFondoInView.setCuentaTitulo("00000000/0");
		when(sesionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapFinalizarRescateFondosBpriv()));
		Cliente cliente = new Cliente();
		Respuesta<FinalizarTransferenciaBprivDTO> respuestaBO = new Respuesta<FinalizarTransferenciaBprivDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		FinalizarTransferenciaBprivDTO finalizarTransferenciaBprivDTO = new FinalizarTransferenciaBprivDTO();
		finalizarTransferenciaBprivDTO.setCuentaTitulo("00000000/0");
		finalizarTransferenciaBprivDTO.setNroCertificado("123456");
		finalizarTransferenciaBprivDTO.setImporte("300");
		finalizarTransferenciaBprivDTO.setCodigoFondo("1234567");
		ConsultaFondoOutEntity fondoOrigen = new ConsultaFondoOutEntity();
		fondoOrigen.setMoneda("ARS");
		respuestaBO.setRespuesta(finalizarTransferenciaBprivDTO );
		Mockito.when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondoOrigen);
		Mockito.when(transferenciaBO.finalizarTransferenciaBPriv(Matchers.any(FinalizarTransferenciaBprivDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO );
		Respuesta<FinalizarTransferenciaFondoView> respuestaTransferenciaFondoView = transferenciaManager.finalizarTransferenciaFondosBpriv(finalizarTransferenciaFondoInView);
		Assert.assertNotNull(respuestaTransferenciaFondoView);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaTransferenciaFondoView.getEstadoRespuesta());
	}
	
	
	
	@Test
	public void finalizarTransferenciaFondosBprivWarning() throws DAOException{
		FinalizarTransferenciaFondoInView finalizarTransferenciaFondoInView = new FinalizarTransferenciaFondoInView();
		finalizarTransferenciaFondoInView.setImporteNeto("300");
		finalizarTransferenciaFondoInView.setCuentaTitulo("00000000/0");
		Cliente cliente = new Cliente();
		Respuesta<FinalizarTransferenciaBprivDTO> respuestaBO = new Respuesta<FinalizarTransferenciaBprivDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		FinalizarTransferenciaBprivDTO finalizarTransferenciaBprivDTO = new FinalizarTransferenciaBprivDTO();
		finalizarTransferenciaBprivDTO.setCuentaTitulo("00000000/0");
		finalizarTransferenciaBprivDTO.setNroCertificado("123456");
		finalizarTransferenciaBprivDTO.setImporte("300");
		finalizarTransferenciaBprivDTO.setCodigoFondo("1234567");
		ConsultaFondoOutEntity fondoOrigen = new ConsultaFondoOutEntity();
		fondoOrigen.setMoneda("ARS");
		respuestaBO.setRespuesta(finalizarTransferenciaBprivDTO );
		Mockito.when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondoOrigen);
		when(sesionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapFinalizarRescateFondosBpriv()));
		Mockito.when(transferenciaBO.finalizarTransferenciaBPriv(Matchers.any(FinalizarTransferenciaBprivDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO );
		Respuesta<FinalizarTransferenciaFondoView> respuestaTransferenciaFondoView = transferenciaManager.finalizarTransferenciaFondosBpriv(finalizarTransferenciaFondoInView);
		Assert.assertNotNull(respuestaTransferenciaFondoView);
		Assert.assertEquals(EstadoRespuesta.WARNING, respuestaTransferenciaFondoView.getEstadoRespuesta());
	}
	
	@Test
	public void finalizarTransferenciaFondosBprivError() throws DAOException{
		FinalizarTransferenciaFondoInView finalizarTransferenciaFondoInView = new FinalizarTransferenciaFondoInView();
		finalizarTransferenciaFondoInView.setImporteNeto("300");
		finalizarTransferenciaFondoInView.setCuentaTitulo("00000000/0");
		Cliente cliente = new Cliente();
		Respuesta<FinalizarTransferenciaBprivDTO> respuestaBO = new Respuesta<FinalizarTransferenciaBprivDTO>();
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("");
		itemMensajeRespuesta.setTag("");
		itemMensajeRespuestaList.add(itemMensajeRespuesta);
		respuestaBO.setItemMensajeRespuesta(itemMensajeRespuestaList);
		FinalizarTransferenciaBprivDTO finalizarTransferenciaBprivDTO = new FinalizarTransferenciaBprivDTO();
		finalizarTransferenciaBprivDTO.setCuentaTitulo("00000000/0");
		finalizarTransferenciaBprivDTO.setNroCertificado("123456");
		finalizarTransferenciaBprivDTO.setImporte("300");
		finalizarTransferenciaBprivDTO.setCodigoFondo("1234567");
		ConsultaFondoOutEntity fondoOrigen = new ConsultaFondoOutEntity();
		fondoOrigen.setMoneda("ARS");
		respuestaBO.setRespuesta(finalizarTransferenciaBprivDTO );
		Mockito.when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondoOrigen);
		when(sesionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapFinalizarRescateFondosBpriv()));
		Mockito.when(transferenciaBO.finalizarTransferenciaBPriv(Matchers.any(FinalizarTransferenciaBprivDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaBO );
		Respuesta<FinalizarTransferenciaFondoView> respuestaTransferenciaFondoView = transferenciaManager.finalizarTransferenciaFondosBpriv(finalizarTransferenciaFondoInView);
		Assert.assertNotNull(respuestaTransferenciaFondoView);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaTransferenciaFondoView.getEstadoRespuesta());
	} 
	
	@Test
	public void finalizarTransferenciaFondos() throws DAOException{
		Respuesta<TransferenciaView> respuestaTransferenciaBO;
		Respuesta<TransferenciaDTO> respuestaTransferenciaDTO = new Respuesta<TransferenciaDTO>();
		TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
		transferenciaDTO.setCuentaTitulo("00000000/0");
		transferenciaDTO.setNumeroCertificado("123456");
		transferenciaDTO.setImporteNeto("300");
		transferenciaDTO.setCodigoFondo("1234567");
		respuestaTransferenciaDTO.setRespuesta(transferenciaDTO);
		respuestaTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		when(sesionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapFinalizarTransferenciaFondos()));
		Mockito.when(transferenciaBO.finalizarTransferirFondos(Matchers.any(TransferenciaDTO.class) , Matchers.any(Cliente.class))).thenReturn(respuestaTransferenciaDTO );
		ConsultaFondoOutEntity fondoOrigen = new ConsultaFondoOutEntity();
		fondoOrigen.setMoneda("ARS");
		Mockito.when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondoOrigen);
		respuestaTransferenciaBO =transferenciaManager.finalizarTransferenciaFondos(transferenciaView);
		Assert.assertNotNull(respuestaTransferenciaBO);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaTransferenciaBO.getEstadoRespuesta());
	}
	
	@Test
	public void finalizarTransferenciaFondosError() throws DAOException{
		Respuesta<TransferenciaView> respuestaTransferenciaBO;
		Respuesta<TransferenciaDTO> respuestaTransferenciaDTO = new Respuesta<TransferenciaDTO>();
		List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("");
		itemMensajeRespuesta.setTag("");
		itemMensajeRespuestaList.add(itemMensajeRespuesta);
		respuestaTransferenciaDTO.setItemMensajeRespuesta(itemMensajeRespuestaList);
		respuestaTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		when(sesionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapFinalizarTransferenciaFondos()));
		Mockito.when(transferenciaBO.finalizarTransferirFondos(Matchers.any(TransferenciaDTO.class) , Matchers.any(Cliente.class))).thenReturn(respuestaTransferenciaDTO );
		ConsultaFondoOutEntity fondoOrigen = new ConsultaFondoOutEntity();
		fondoOrigen.setMoneda("ARS");
		Mockito.when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondoOrigen);

		respuestaTransferenciaBO =transferenciaManager.finalizarTransferenciaFondos(transferenciaView);
		Assert.assertNotNull(respuestaTransferenciaBO);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuestaTransferenciaBO.getEstadoRespuesta());
	}
	
	@Test
	public void finalizarTransferenciaFondosWarning() throws DAOException{
		Respuesta<TransferenciaView> respuestaTransferenciaBO;
		Respuesta<TransferenciaDTO> respuestaTransferenciaDTO = new Respuesta<TransferenciaDTO>();
		List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
		ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
		itemMensajeRespuesta.setMensaje("");
		itemMensajeRespuesta.setTag("");
		itemMensajeRespuestaList.add(itemMensajeRespuesta);
		respuestaTransferenciaDTO.setItemMensajeRespuesta(itemMensajeRespuestaList);
		respuestaTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
		when(sesionParametros.getValidacionHash()).thenReturn(HashUtils.obtenerHash(crearMapFinalizarTransferenciaFondos()));
		Mockito.when(transferenciaBO.finalizarTransferirFondos(Matchers.any(TransferenciaDTO.class) , Matchers.any(Cliente.class))).thenReturn(respuestaTransferenciaDTO );
		ConsultaFondoOutEntity fondoOrigen = new ConsultaFondoOutEntity();
		fondoOrigen.setMoneda("ARS");
		Mockito.when(consultaFondoDAO.obtenerPorCodigo(Matchers.anyString())).thenReturn(fondoOrigen);

		respuestaTransferenciaBO =transferenciaManager.finalizarTransferenciaFondos(transferenciaView);
		Assert.assertNotNull(respuestaTransferenciaBO);
		Assert.assertEquals(EstadoRespuesta.WARNING, respuestaTransferenciaBO.getEstadoRespuesta());
	}
	
	
	@Test
    public void verComprobante () throws DAOException{
	    DatosComprobante datosComprobante = new DatosComprobante();
	    datosComprobante.setCuenta("00000000/0");
	    Respuesta<String> rtaLegal = new Respuesta<String>();
	    rtaLegal.setEstadoRespuesta(EstadoRespuesta.OK);
	    Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(rtaLegal);
	    Respuesta<ComprobanteSuscripcionView> rta  =transferenciaManager.verComprobante(datosComprobante);
	    Assert.assertNotNull(rta);
        Assert.assertEquals(EstadoRespuesta.OK, rta.getEstadoRespuesta());
	}
	
	@Test
    public void verComprobanteBPriv () throws DAOException{
        DatosComprobante datosComprobante = new DatosComprobante();
        datosComprobante.setCuenta("00000000/0");
        Respuesta<String> rtaLegal = new Respuesta<String>();
        rtaLegal.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(rtaLegal);
        Respuesta<ComprobanteSuscripcionView> rta  =transferenciaManager.verComprobanteBPriv(datosComprobante);
        Assert.assertNotNull(rta);
        Assert.assertEquals(EstadoRespuesta.OK, rta.getEstadoRespuesta());
    }
	
	
	
	@Test 
	public void simularTransferenciaBloqueante() throws DAOException{
    	Set<ConstraintViolation<SimulacionTransferenciaInView>> respValidator = new HashSet<ConstraintViolation<SimulacionTransferenciaInView>>();
		Mockito.when(validator.validate(Matchers.any(SimulacionTransferenciaInView.class))).thenReturn(respValidator);
		SimulacionTransferenciaInView inView = new SimulacionTransferenciaInView();
		inView.setCuentaTitulo("00000000/0");
		inView.setMoneda("$");
		inView.setImporteNeto("$ 1000");
		TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
		transferenciaDTO.setCuentaTitulo("00000000/0");
		transferenciaDTO.setNumeroCertificado("123456");
		transferenciaDTO.setImporteNeto("300");
		transferenciaDTO.setCodigoFondo("1234567");
		transferenciaDTO.setLegales("");
		transferenciaDTO.setMoneda("ARS");
		Cliente cliente = new Cliente();
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Respuesta<TransferenciaDTO> respuestaTransferenciaDTO = new Respuesta<TransferenciaDTO>();
		respuestaTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaTransferenciaDTO.setRespuesta(transferenciaDTO);
		Mockito.when(transferenciaBO.simularTransferencia(Matchers.any(TransferenciaDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaTransferenciaDTO);
		EvaluacionDeRiesgoResponse evaluacionResponse = new EvaluacionDeRiesgoResponse();
		evaluacionResponse.setCabecera("Cabecera");
		evaluacionResponse.setPie("Pie");
		evaluacionResponse.setTipoDisclaimer("2");
		evaluacionResponse.setIdEvaluacion("10");
		Mensaje mensaje = new Mensaje();
		evaluacionResponse.setMensaje(mensaje);
		mensaje.setCantidadDeDisclaimers(new Integer(2));
		Mockito.when(inversionDAO.evaluacionDeRiesgo(Matchers.any(DatosEvaluacionRiesgo.class))).thenReturn(evaluacionResponse);
		Respuesta<SimulacionTransferenciaView> respuestaSimulacion = transferenciaManager.simularTransferencia(inView);
		Assert.assertNotNull(respuestaSimulacion);
		Assert.assertEquals(EstadoRespuesta.WARNING, respuestaSimulacion.getEstadoRespuesta());
	}
	
	@Test 
	public void simularTransferenciaRiesgoMedio() throws DAOException{
    	Set<ConstraintViolation<SimulacionTransferenciaInView>> respValidator = new HashSet<ConstraintViolation<SimulacionTransferenciaInView>>();
		Mockito.when(validator.validate(Matchers.any(SimulacionTransferenciaInView.class))).thenReturn(respValidator);
		SimulacionTransferenciaInView inView = new SimulacionTransferenciaInView();
		inView.setCuentaTitulo("00000000/0");
		inView.setMoneda("$");
		inView.setImporteNeto("$ 1000");
		TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
		transferenciaDTO.setCuentaTitulo("00000000/0");
		transferenciaDTO.setNumeroCertificado("123456");
		transferenciaDTO.setImporteNeto("300");
		transferenciaDTO.setCodigoFondo("1234567");
		transferenciaDTO.setLegales("");
		transferenciaDTO.setMoneda("ARS");
		Cliente cliente = new Cliente();
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Respuesta<TransferenciaDTO> respuestaTransferenciaDTO = new Respuesta<TransferenciaDTO>();
		respuestaTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaTransferenciaDTO.setRespuesta(transferenciaDTO);
		Mockito.when(transferenciaBO.simularTransferencia(Matchers.any(TransferenciaDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaTransferenciaDTO);
		EvaluacionDeRiesgoResponse evaluacionResponse = new EvaluacionDeRiesgoResponse();
		evaluacionResponse.setCabecera("Cabecera");
		evaluacionResponse.setPie("Pie");
		evaluacionResponse.setTipoDisclaimer("1");
		evaluacionResponse.setIdEvaluacion("10");
		Mensaje mensaje = new Mensaje();
		evaluacionResponse.setMensaje(mensaje);
		mensaje.setCantidadDeDisclaimers(new Integer(2));
		Mockito.when(inversionDAO.evaluacionDeRiesgo(Matchers.any(DatosEvaluacionRiesgo.class))).thenReturn(evaluacionResponse);
		Respuesta<SimulacionTransferenciaView> respuestaSimulacion = transferenciaManager.simularTransferencia(inView);
		Assert.assertNotNull(respuestaSimulacion);
		Assert.assertEquals(EstadoRespuesta.WARNING, respuestaSimulacion.getEstadoRespuesta());

	}
	
	@Test 
	public void simularTransferenciaOK() throws DAOException{
    	Set<ConstraintViolation<SimulacionTransferenciaInView>> respValidator = new HashSet<ConstraintViolation<SimulacionTransferenciaInView>>();
		Mockito.when(validator.validate(Matchers.any(SimulacionTransferenciaInView.class))).thenReturn(respValidator);
		SimulacionTransferenciaInView inView = new SimulacionTransferenciaInView();
		inView.setCuentaTitulo("00000000/0");
		inView.setMoneda("$");
		inView.setImporteNeto("$ 1000");
		TransferenciaDTO transferenciaDTO = new TransferenciaDTO();
		transferenciaDTO.setCuentaTitulo("00000000/0");
		transferenciaDTO.setNumeroCertificado("123456");
		transferenciaDTO.setImporteNeto("300");
		transferenciaDTO.setCodigoFondo("1234567");
		transferenciaDTO.setLegales("");
		transferenciaDTO.setMoneda("ARS");
		Cliente cliente = new Cliente();
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Respuesta<TransferenciaDTO> respuestaTransferenciaDTO = new Respuesta<TransferenciaDTO>();
		respuestaTransferenciaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
		respuestaTransferenciaDTO.setRespuesta(transferenciaDTO);
		Mockito.when(transferenciaBO.simularTransferencia(Matchers.any(TransferenciaDTO.class), Matchers.any(Cliente.class))).thenReturn(respuestaTransferenciaDTO);
		EvaluacionDeRiesgoResponse evaluacionResponse = new EvaluacionDeRiesgoResponse();
		evaluacionResponse.setCabecera("Cabecera");
		evaluacionResponse.setPie("Pie");
		evaluacionResponse.setTipoDisclaimer("0");
		evaluacionResponse.setIdEvaluacion("10");
		Mensaje mensaje = new Mensaje();
		evaluacionResponse.setMensaje(mensaje);
		mensaje.setCantidadDeDisclaimers(new Integer(2));
		Mockito.when(inversionDAO.evaluacionDeRiesgo(Matchers.any(DatosEvaluacionRiesgo.class))).thenReturn(evaluacionResponse);
		Respuesta<SimulacionTransferenciaView> respuestaSimulacion = transferenciaManager.simularTransferencia(inView);
		Assert.assertNotNull(respuestaSimulacion);
		Assert.assertEquals(EstadoRespuesta.OK, respuestaSimulacion.getEstadoRespuesta());

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
	
	private Map<String, Object> crearMapFinalizarTransferenciaFondos() {
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("codigoFondo", "1234567");
		mapaAtributos.put("codigoFondoDestino", null);
		mapaAtributos.put("cuentaTitulos", "00000000/0");
		mapaAtributos.put("importe", "300");
		return mapaAtributos;
	}
	private Map<String, Object> crearMapFinalizarRescateFondosBpriv() {
		Map<String, Object> mapaAtributos = new HashMap<String, Object>();
		mapaAtributos.put("codigoFondo", null);
		mapaAtributos.put("codigoFondoDestino", null);
		mapaAtributos.put("cuentaBancaPrivada", "00000000/0");
		mapaAtributos.put("importe", "300");
		return mapaAtributos;
	}
	
	
}
