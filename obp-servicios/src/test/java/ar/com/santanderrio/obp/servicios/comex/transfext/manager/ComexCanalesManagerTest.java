package ar.com.santanderrio.obp.servicios.comex.transfext.manager;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexCanalesBO;
import ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexConsultasBO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaBancosInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaBancosOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaDetalleTrfOBPInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaDetalleTrfOBPOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaMonedaOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaOperacionesDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaOperacionesInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaOperacionesOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ProcesarTransferenciaComexInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ProcesarTransferenciaComexOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.manager.impl.ComexCanalesManagerImpl;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ArchivoTransferenciaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaDetalleTrfOBPInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaDetalleTrfOBPOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaOperacionesInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaOperacionesOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaOperacionesView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DatosBeneficiarioView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DatosTransferenciaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DocumentacionAdjuntaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarTransferenciaComexView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ReporteView;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionFactory;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class ComexCanalesManagerTest {
	/** The respuesta factory. */
	@Spy
	@InjectMocks
	private RespuestaFactory respuestaFactory;

	/** The comex Consultas Manager. */
	@InjectMocks
	private ComexCanalesManager comexCanalesManager= new ComexCanalesManagerImpl();

	/** The sesion parametros. */
	@Mock
	private SesionParametros sesionParametros;

	/** The sesion cliente. */
	@Mock
	private SesionCliente sesionCliente;
	
	/** The ComexCanales BO. */
	@Mock
	private ComexCanalesBO comexCanalesBO;
	
	/** The Cuenta BO. */
	@Mock
	private CuentaBO cuentaBO;
	
	/** The Cuenta BO. */
	@Mock
	private DesafioOperacionRSA<ProcesarTransferenciaComexView> desafioOperacionRSA;
		
	/**
	 * Inits.
	 */
	@Before
	public void init() throws ServiceException {
		MockitoAnnotations.initMocks(this);
	}
	
	/** The mensaje bo. */
	@Mock
	private MensajeBO mensajeBO;

	/** The ComexCanales BO. */
	@Mock
	private ComexConsultasBO comexConsultasBO;
	
	/** The estadistica manager. */
	@Mock
	private EstadisticaManager estadisticaManager;
	
	/** The autentificacion manager. */
	@Mock
	protected AutentificacionManager autentificacionManager;
	
    /** The autentificacion factory. */
	@Mock
    private AutentificacionFactory autentificacionFactory;
	
    @Mock
    private LegalBO legalBO;
	
	@Test
	public void consultaDetalleTrfOK() {
		ConsultaDetalleTrfOBPInView consultaDetalleTrfOBPInView = new ConsultaDetalleTrfOBPInView();
		consultaDetalleTrfOBPInView.setNroTransferencia(new Long("96565"));
		ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();
		Respuesta<ConsultaDetalleTrfOBPOutDTO> respuestaBO = new Respuesta<ConsultaDetalleTrfOBPOutDTO>();
		ConsultaDetalleTrfOBPOutDTO consultaDetalle = new ConsultaDetalleTrfOBPOutDTO();
		consultaDetalle.setCodigoBanco("SWIFT");
		consultaDetalle.setCodigoBancoIntermediario("ABA");
		consultaDetalle.setCodMoneda("092");
		consultaDetalle.setCodPais("");
		consultaDetalle.setCodTipoCuenta("09");
		consultaDetalle.setConceptoCodigo("A07");
		consultaDetalle.setConceptoDescripcion("DEPOSITOS DE RESIDENTES EN EL EXTERIOR");
		consultaDetalle.setDescripcionBanco("CITIUS33GRP");
		consultaDetalle.setDescripcionBancoIntermediario("067015355");
		consultaDetalle.setDestino("127312");
		consultaDetalle.setDestinoBancoIntermediario("12345");
		consultaDetalle.setDomicilioCalle("");
		consultaDetalle.setDomicilioLocalidad("");
		consultaDetalle.setDomicilioNumero("");
		consultaDetalle.setDomicilioPais("");
		consultaDetalle.setEstadoTransferencia("A");
		consultaDetalle.setFechaOperacion("09/10/2018");
		consultaDetalle.setGastoACargo("1");
		consultaDetalle.setImporteTransferencia("50,00");
		consultaDetalle.setNombreBeneficiario("Abrahan Gonzales");
		consultaDetalle.setOrigen("000-358047/5");
		consultaDetalle.setTipoCuentaOrigen("Cuenta única en $");
		consultaDetalle.setVinculo("");
		consultaDetalle.setMotivoRechazo("");
		consultaDetalle.setDescripcionMotivo("");
		
		
		respuestaBO.setRespuesta(consultaDetalle);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Respuesta<List<ConsultaBancosOutDTO>> respuestaBancoBO = new Respuesta<List<ConsultaBancosOutDTO>>();
		List<ConsultaBancosOutDTO> listConsultaBancosOutDTO = new ArrayList<ConsultaBancosOutDTO>();
		ConsultaBancosOutDTO consultaBancosOutDTO = new ConsultaBancosOutDTO();
		consultaBancosOutDTO.setDomicilioBanco("N/D");
		consultaBancosOutDTO.setLocalidadBanco("N/D");
		consultaBancosOutDTO.setNombreBanco("CITIBANK N.A.");
		consultaBancosOutDTO.setPaisBanco("ESTADOSUNIDOS");
		consultaBancosOutDTO.setSwiftBanco("CITIUS33GRP");

		listConsultaBancosOutDTO.add(consultaBancosOutDTO);
		respuestaBancoBO.setRespuesta(listConsultaBancosOutDTO);
		respuestaBancoBO.setEstadoRespuesta(EstadoRespuesta.OK);
		Respuesta<List<ConsultaBancosOutDTO>> respuestaBancoBO2 = new Respuesta<List<ConsultaBancosOutDTO>>();
		List<ConsultaBancosOutDTO> listConsultaBancosOutDTO2 = new ArrayList<ConsultaBancosOutDTO>();
		ConsultaBancosOutDTO consultaBancosOutDTO2 = new ConsultaBancosOutDTO();
		consultaBancosOutDTO2.setDomicilioBanco("N/D");
		consultaBancosOutDTO2.setLocalidadBanco("N/D");
		consultaBancosOutDTO2.setNombreBanco("BANCO DE CREDITO DEL PERU");
		consultaBancosOutDTO2.setPaisBanco("N/D");
		consultaBancosOutDTO2.setAbaBanco("/FW067015355");
		listConsultaBancosOutDTO2.add(consultaBancosOutDTO2);
		respuestaBancoBO2.setRespuesta(listConsultaBancosOutDTO2);
		respuestaBancoBO2.setEstadoRespuesta(EstadoRespuesta.OK);
		
				
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		when(comexCanalesBO.consultaDetalleTrf(Matchers.any(ConsultaDetalleTrfOBPInDTO.class),Matchers.any(ConsultaOperacionesView.class))).thenReturn(respuestaBO);
		when(comexConsultasBO.consultaBancos(Matchers.any(ConsultaBancosInDTO.class))).thenReturn(respuestaBancoBO,respuestaBancoBO2);
		when(sesionParametros.getMonedasComexMap()).thenReturn(generarMapaMonedas());
		Respuesta<ConsultaDetalleTrfOBPOutView> respuesta = comexCanalesManager.consultaDetalleTrf(consultaDetalleTrfOBPInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void consultaDetalleTrfConCuentaOK() {
		ConsultaDetalleTrfOBPInView consultaDetalleTrfOBPInView = new ConsultaDetalleTrfOBPInView();
		consultaDetalleTrfOBPInView.setNroTransferencia(new Long("96655"));
		ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();
		Respuesta<ConsultaDetalleTrfOBPOutDTO> respuestaBO = new Respuesta<ConsultaDetalleTrfOBPOutDTO>();
		ConsultaDetalleTrfOBPOutDTO consultaDetalle = new ConsultaDetalleTrfOBPOutDTO();
		consultaDetalle.setCodigoBanco("SWIFT");
		consultaDetalle.setCodigoBancoIntermediario("ABA");
		consultaDetalle.setCodMoneda("092");
		consultaDetalle.setCodPais("");
		consultaDetalle.setCodTipoCuenta("10");
		consultaDetalle.setConceptoCodigo("A07");
		consultaDetalle.setConceptoDescripcion("DEPOSITOS DE RESIDENTES EN EL EXTERIOR");
		consultaDetalle.setDescripcionBanco("CITIUS33GRP");
		consultaDetalle.setDescripcionBancoIntermediario("067015355");
		consultaDetalle.setDestino("127312");
		consultaDetalle.setDestinoBancoIntermediario("12345");
		consultaDetalle.setDomicilioCalle("");
		consultaDetalle.setDomicilioLocalidad("");
		consultaDetalle.setDomicilioNumero("");
		consultaDetalle.setDomicilioPais("");
		consultaDetalle.setEstadoTransferencia("A");
		consultaDetalle.setFechaOperacion("09/10/2018");
		consultaDetalle.setGastoACargo("1");
		consultaDetalle.setImporteTransferencia("50,00");
		consultaDetalle.setNombreBeneficiario("Abrahan Gonzales");
		consultaDetalle.setOrigen("000-358047/5");
		consultaDetalle.setTipoCuentaOrigen("Cuenta única en $");
		consultaDetalle.setVinculo("");
		
		
		
		
		respuestaBO.setRespuesta(consultaDetalle);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Respuesta<List<ConsultaBancosOutDTO>> respuestaBancoBO = new Respuesta<List<ConsultaBancosOutDTO>>();
		List<ConsultaBancosOutDTO> listConsultaBancosOutDTO = new ArrayList<ConsultaBancosOutDTO>();
		ConsultaBancosOutDTO consultaBancosOutDTO = new ConsultaBancosOutDTO();
		consultaBancosOutDTO.setDomicilioBanco("N/D");
		consultaBancosOutDTO.setLocalidadBanco("N/D");
		consultaBancosOutDTO.setNombreBanco("CITIBANK N.A.");
		consultaBancosOutDTO.setPaisBanco("ESTADOSUNIDOS");
		consultaBancosOutDTO.setSwiftBanco("CITIUS33GRP");

		listConsultaBancosOutDTO.add(consultaBancosOutDTO);
		respuestaBancoBO.setRespuesta(listConsultaBancosOutDTO);
		respuestaBancoBO.setEstadoRespuesta(EstadoRespuesta.OK);
		Respuesta<List<ConsultaBancosOutDTO>> respuestaBancoBO2 = new Respuesta<List<ConsultaBancosOutDTO>>();
		List<ConsultaBancosOutDTO> listConsultaBancosOutDTO2 = new ArrayList<ConsultaBancosOutDTO>();
		ConsultaBancosOutDTO consultaBancosOutDTO2 = new ConsultaBancosOutDTO();
		consultaBancosOutDTO2.setDomicilioBanco("N/D");
		consultaBancosOutDTO2.setLocalidadBanco("N/D");
		consultaBancosOutDTO2.setNombreBanco("BANCO DE CREDITO DEL PERU");
		consultaBancosOutDTO2.setPaisBanco("N/D");
		consultaBancosOutDTO2.setAbaBanco("/FW067015355");
		listConsultaBancosOutDTO2.add(consultaBancosOutDTO2);
		respuestaBancoBO2.setRespuesta(listConsultaBancosOutDTO2);
		respuestaBancoBO2.setEstadoRespuesta(EstadoRespuesta.OK);
		
				
		List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
	    detalleCuentas.add(getDetalleCuentas("000", "000011", "1", "1999", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "0", "2214", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "3", "14124", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "2", "14124", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "3580475", "02", "14124", null, null));
	    

	    
		when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		when(comexCanalesBO.consultaDetalleTrf(Matchers.any(ConsultaDetalleTrfOBPInDTO.class),Matchers.any(ConsultaOperacionesView.class))).thenReturn(respuestaBO);
		when(comexConsultasBO.consultaBancos(Matchers.any(ConsultaBancosInDTO.class))).thenReturn(respuestaBancoBO,respuestaBancoBO2);
		when(sesionParametros.getMonedasComexMap()).thenReturn(generarMapaMonedas());

		Respuesta<ConsultaDetalleTrfOBPOutView> respuesta = comexCanalesManager.consultaDetalleTrf(consultaDetalleTrfOBPInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void consultaDetalleTrfErrorBO() {
		ConsultaDetalleTrfOBPInView consultaDetalleTrfOBPInView = new ConsultaDetalleTrfOBPInView();
		consultaDetalleTrfOBPInView.setNroTransferencia(new Long("96565"));
		ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();
		Respuesta<ConsultaDetalleTrfOBPOutDTO> respuestaBO = new Respuesta<ConsultaDetalleTrfOBPOutDTO>();
		
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		
		
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO)).thenReturn(mensaje);
		when(comexCanalesBO.consultaDetalleTrf(Matchers.any(ConsultaDetalleTrfOBPInDTO.class),Matchers.any(ConsultaOperacionesView.class))).thenReturn(respuestaBO);
		
		Respuesta<ConsultaDetalleTrfOBPOutView> respuesta = comexCanalesManager.consultaDetalleTrf(consultaDetalleTrfOBPInView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
		
	}
	@Test
	public void consultaDetalleTrfErrorSinOperacion() {
		ConsultaDetalleTrfOBPInView consultaDetalleTrfOBPInView = new ConsultaDetalleTrfOBPInView();
		consultaDetalleTrfOBPInView.setNroTransferencia(new Long("95672"));
		ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();		
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_CONSULTA_GENERICO)).thenReturn(mensaje);

		
		Respuesta<ConsultaDetalleTrfOBPOutView> respuesta = comexCanalesManager.consultaDetalleTrf(consultaDetalleTrfOBPInView);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void consultaOperacionesOK() throws DAOException {
		ConsultaOperacionesInView consultaOperacionesInView = new ConsultaOperacionesInView();
		Respuesta<ConsultaOperacionesOutDTO> responseBO = new Respuesta<ConsultaOperacionesOutDTO>();
		ConsultaOperacionesOutDTO consultaOperaciones = new ConsultaOperacionesOutDTO();
		ConsultaOperacionesDTO operacion1 = new ConsultaOperacionesDTO();
		List<ConsultaOperacionesDTO> listaOperaciones = new ArrayList<ConsultaOperacionesDTO>();
		operacion1.setCodEstado("G");
		operacion1.setCodMoneda("001");
		operacion1.setCuentaCliente("000-09-3580475");
		operacion1.setDestinatario("Ricardo Marllo");
		operacion1.setEstadoDescripcion("Pendiente");
		operacion1.setFechaOperacion("18/10/2018");
		operacion1.setMonto("15,00");
		operacion1.setNroForm(new Long ("96655"));
		listaOperaciones.add(operacion1);
		ConsultaOperacionesDTO operacion2 = new ConsultaOperacionesDTO();
		operacion2.setCodEstado("G");
		operacion2.setCodMoneda("002");
		operacion2.setCuentaCliente("000-10-3580475");
		operacion2.setDestinatario("COMEX10");
		operacion2.setEstadoDescripcion("Pendiente");
		operacion2.setFechaOperacion("18/10/2018");
		operacion2.setMonto("12,00");
		operacion2.setNroForm(new Long ("96654"));
		listaOperaciones.add(operacion2);
		ConsultaOperacionesDTO operacion3 = new ConsultaOperacionesDTO();
		operacion3.setCodEstado("G");
		operacion3.setCodMoneda("015");
		operacion3.setCuentaCliente("000-09-3580475");
		operacion3.setDestinatario("COMEX09");
		operacion3.setEstadoDescripcion("Pendiente");
		operacion3.setFechaOperacion("18/10/2018");
		operacion3.setMonto("12,00");
		operacion3.setNroForm(new Long ("96653"));
		listaOperaciones.add(operacion3);
		ConsultaOperacionesDTO operacion4 = new ConsultaOperacionesDTO();
		operacion4.setCodEstado("A");
		operacion4.setCodMoneda("092");
		operacion4.setCuentaCliente("000-09-3580475");
		operacion4.setDestinatario("Abrahan Gonzales");
		operacion4.setEstadoDescripcion("Guardada");
		operacion4.setFechaOperacion("09/10/2018");
		operacion4.setMonto("50,00");
		operacion4.setNroForm(new Long ("96565"));
		listaOperaciones.add(operacion4);
		consultaOperaciones.setCantidadTransferenciasGuardadas(3);
		consultaOperaciones.setCantidadTransferenciasPendientes(1);
		consultaOperaciones.setCantidadTransferenciasRechazadas(0);
		consultaOperaciones.setOperaciones(listaOperaciones);
		
		responseBO.setRespuesta(consultaOperaciones);
		
		responseBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Respuesta<List<ConsultaMonedaOutDTO>> responseMonedasBO = new Respuesta<List<ConsultaMonedaOutDTO>>();
		
		responseMonedasBO.setRespuesta(generarListaMonedas());
		responseMonedasBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		when(comexCanalesBO.consultaOperaciones(Matchers.any(ConsultaOperacionesInDTO.class))).thenReturn(responseBO);
		when(sesionParametros.getMonedasComexMap()).thenReturn(null);
		when(comexConsultasBO.consultaMonedas()).thenReturn(responseMonedasBO);
		when(estadisticaManager.add(EstadisticasConstants.CONSULTA_OPERACIONES_COMEX, EstadisticasConstants.CODIGO_ESTADISTICAS_OK)).thenReturn(true);
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(generarConsultaOperacionesOutView());
		when(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_AVISO_TRANSFERENCIAS_EXTERIOR)).thenReturn("LEGAL");

		Respuesta<ConsultaOperacionesOutView> respuesta = comexCanalesManager.consultaOperaciones(consultaOperacionesInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void consultaOperacionesErrorBO() {
		ConsultaOperacionesInView consultaOperacionesInView = new ConsultaOperacionesInView();
		Respuesta<ConsultaOperacionesOutDTO> responseBO = new Respuesta<ConsultaOperacionesOutDTO>();
		ConsultaOperacionesOutDTO consultaOperaciones = new ConsultaOperacionesOutDTO();
		
		responseBO.setRespuesta(consultaOperaciones);
		responseBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		when(sesionParametros.getMonedasComexMap()).thenReturn(generarMapaMonedas());
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_CONSULTA_GENERICO)).thenReturn(mensaje);
		when(comexCanalesBO.consultaOperaciones(Matchers.any(ConsultaOperacionesInDTO.class))).thenReturn(responseBO);
		
		Respuesta<ConsultaOperacionesOutView> respuesta = comexCanalesManager.consultaOperaciones(consultaOperacionesInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void consultaOperacionesErrorBOConsultaMonedas() {
		ConsultaOperacionesInView consultaOperacionesInView = new ConsultaOperacionesInView();


		Respuesta<List<ConsultaMonedaOutDTO>> respuestaBOConsultaMonedas = new Respuesta<List<ConsultaMonedaOutDTO>>();
		
		respuestaBOConsultaMonedas.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		when(sesionParametros.getMonedasComexMap()).thenReturn(null);
		when(comexConsultasBO.consultaMonedas()).thenReturn(respuestaBOConsultaMonedas);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_CONSULTA_GENERICO)).thenReturn(mensaje);

		
		Respuesta<ConsultaOperacionesOutView> respuesta = comexCanalesManager.consultaOperaciones(consultaOperacionesInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void consultaOperacionesVacioBuscador() {
		ConsultaOperacionesInView consultaOperacionesInView = new ConsultaOperacionesInView();
		consultaOperacionesInView.setEsBuscador(true);
		Respuesta<ConsultaOperacionesOutDTO> responseBO = new Respuesta<ConsultaOperacionesOutDTO>();
		ConsultaOperacionesOutDTO consultaOperaciones = new ConsultaOperacionesOutDTO();
		List<ConsultaOperacionesDTO> listaOperaciones = new ArrayList<ConsultaOperacionesDTO>();
		consultaOperaciones.setCantidadTransferenciasGuardadas(0);
		consultaOperaciones.setCantidadTransferenciasPendientes(0);
		consultaOperaciones.setCantidadTransferenciasRechazadas(0);
		consultaOperaciones.setOperaciones(listaOperaciones);
		responseBO.setRespuesta(consultaOperaciones);
		responseBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		when(sesionParametros.getMonedasComexMap()).thenReturn(generarMapaMonedas());
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_CONSULTA_LISTA_VACIA_BUSCADOR)).thenReturn(mensaje);
		when(comexCanalesBO.consultaOperaciones(Matchers.any(ConsultaOperacionesInDTO.class))).thenReturn(responseBO);
		
		Respuesta<ConsultaOperacionesOutView> respuesta = comexCanalesManager.consultaOperaciones(consultaOperacionesInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
	}
	
	@Test
	public void consultaOperacionesVacio() {
		ConsultaOperacionesInView consultaOperacionesInView = new ConsultaOperacionesInView();
		Respuesta<ConsultaOperacionesOutDTO> responseBO = new Respuesta<ConsultaOperacionesOutDTO>();
		ConsultaOperacionesOutDTO consultaOperaciones = new ConsultaOperacionesOutDTO();
		List<ConsultaOperacionesDTO> listaOperaciones = new ArrayList<ConsultaOperacionesDTO>();
		consultaOperaciones.setCantidadTransferenciasGuardadas(0);
		consultaOperaciones.setCantidadTransferenciasPendientes(0);
		consultaOperaciones.setCantidadTransferenciasRechazadas(0);
		consultaOperaciones.setOperaciones(listaOperaciones);
		responseBO.setRespuesta(consultaOperaciones);
		responseBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		when(sesionParametros.getMonedasComexMap()).thenReturn(generarMapaMonedas());
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_CONSULTA_LISTA_VACIA)).thenReturn(mensaje);
		when(comexCanalesBO.consultaOperaciones(Matchers.any(ConsultaOperacionesInDTO.class))).thenReturn(responseBO);
		
		Respuesta<ConsultaOperacionesOutView> respuesta = comexCanalesManager.consultaOperaciones(consultaOperacionesInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
	}
	@Test
	public void consultaOperacionesMostrarMas() {
		ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();
		
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		
		Respuesta<ConsultaOperacionesOutView> respuesta = comexCanalesManager.consultaOperacionesMostrarMas();
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void consultaOperacionesMostrarMasError() {
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
	
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_CONSULTA_GENERICO)).thenReturn(mensaje);
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(null);
		
		Respuesta<ConsultaOperacionesOutView> respuesta = comexCanalesManager.consultaOperacionesMostrarMas();
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	@Test
	public void procesarTransferenciaGuardarOk() {
		ProcesarTransferenciaComexView procesarInView = generarProcesarInView();
		
		Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO = new Respuesta<ProcesarTransferenciaComexOutDTO>();
		
		ProcesarTransferenciaComexOutDTO procesarOutDTO = new ProcesarTransferenciaComexOutDTO();
		
		procesarOutDTO.setNroTransferencia(96567);
		
		respuestaBO.setRespuesta(procesarOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		Cliente cliente = generarCliente();
		cliente.setIsCuentaMigrada(true);
		cliente.setApellido2("apellido 2       M");
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(comexCanalesBO.procesarTransferenciaComex(Matchers.any(ProcesarTransferenciaComexInDTO.class))).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_MSG_INFO_OK)).thenReturn(mensaje);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_OK)).thenReturn(mensaje);
		
		Respuesta<ProcesarTransferenciaComexView> respuesta = comexCanalesManager.procesarTransferenciaGuardar(procesarInView);
		
				
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void procesarTransferenciaGuardarOkSimilar() {
		ProcesarTransferenciaComexView procesarInView = generarProcesarInView();
		procesarInView.getDatosTransferencia().setConceptoCodigo("I07");
		procesarInView.getDatosTransferencia().setConceptoDescripcion("TRANSFERENCIAS PERSONALES");
		procesarInView.setNroFormRel(new Long("96565"));
		
		Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO = new Respuesta<ProcesarTransferenciaComexOutDTO>();
		
		ProcesarTransferenciaComexOutDTO procesarOutDTO = new ProcesarTransferenciaComexOutDTO();
		
		procesarOutDTO.setNroTransferencia(96590);
		
		respuestaBO.setRespuesta(procesarOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
	    detalleCuentas.add(getDetalleCuentas("000", "000011", "1", "1999", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "0", "2214", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "3", "14124", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "2", "14124", null, null));
	    
		

		when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);
		
		when(sesionCliente.getCliente()).thenReturn(generarCliente());
		when(sesionParametros.getMonedasComexMap()).thenReturn(generarMapaMonedas());
		when(comexCanalesBO.procesarTransferenciaComex(Matchers.any(ProcesarTransferenciaComexInDTO.class))).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_MSG_INFO_OK)).thenReturn(mensaje);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_OK)).thenReturn(mensaje);
		
		Respuesta<ProcesarTransferenciaComexView> respuesta = comexCanalesManager.procesarTransferenciaGuardar(procesarInView);
		
				
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	@Test
	public void procesarTransferenciaGuardarOkModificacion() {
		ProcesarTransferenciaComexView procesarInView = generarProcesarInView();
		procesarInView.getDatosTransferencia().setIdCuentaOrigen(0);
		procesarInView.setAceptaDDJJ(true);
		procesarInView.setIgnorarRSA(true);
		procesarInView.setNroTransferencia(96565);

		Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO = new Respuesta<ProcesarTransferenciaComexOutDTO>();
		
		ProcesarTransferenciaComexOutDTO procesarOutDTO = new ProcesarTransferenciaComexOutDTO();
		
		procesarOutDTO.setNroTransferencia(96565);
		
		respuestaBO.setRespuesta(procesarOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
	    detalleCuentas.add(getDetalleCuentas("000", "000011", "1", "1999", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "0", "2214", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "3", "14124", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "2", "14124", null, null));
	    ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();
		
		Cliente cliente = generarCliente();
		cliente.setIsCuentaMigrada(true);
		cliente.setApellido2("M");
	    
		when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(sesionParametros.getMonedasComexMap()).thenReturn(generarMapaMonedas());
		when(comexCanalesBO.procesarTransferenciaComex(Matchers.any(ProcesarTransferenciaComexInDTO.class))).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_MSG_INFO_OK)).thenReturn(mensaje);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_OK)).thenReturn(mensaje);
		
		Respuesta<ProcesarTransferenciaComexView> respuesta = comexCanalesManager.procesarTransferenciaGuardar(procesarInView);
		
				
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void procesarTransferenciaGuardarErrorSinOperacion() {
		ProcesarTransferenciaComexView procesarInView = generarProcesarInView();
		procesarInView.getDatosTransferencia().setIdCuentaOrigen(0);
		procesarInView.setNroTransferencia(231);
		
		Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO = new Respuesta<ProcesarTransferenciaComexOutDTO>();
		
		ProcesarTransferenciaComexOutDTO procesarOutDTO = new ProcesarTransferenciaComexOutDTO();
		
		procesarOutDTO.setNroTransferencia(231);
		
		respuestaBO.setRespuesta(procesarOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
	    detalleCuentas.add(getDetalleCuentas("000", "000011", "1", "1999", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "0", "2214", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "3", "14124", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "2", "14124", null, null));
	    
	    ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();

		when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		when(sesionCliente.getCliente()).thenReturn(generarCliente());
		when(sesionParametros.getMonedasComexMap()).thenReturn(generarMapaMonedas());
		when(comexCanalesBO.procesarTransferenciaComex(Matchers.any(ProcesarTransferenciaComexInDTO.class))).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO)).thenReturn(mensaje);
		
		Respuesta<ProcesarTransferenciaComexView> respuesta = comexCanalesManager.procesarTransferenciaGuardar(procesarInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void procesarTransferenciaAltaOk() {
		
		ProcesarTransferenciaComexView procesarInView = generarProcesarInView();
		procesarInView.getDatosTransferencia().setIdCuentaOrigen(0);

		Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO = new Respuesta<ProcesarTransferenciaComexOutDTO>();
		
		ProcesarTransferenciaComexOutDTO procesarOutDTO = new ProcesarTransferenciaComexOutDTO();
		
		procesarOutDTO.setNroTransferencia(96567);
		
		respuestaBO.setRespuesta(procesarOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
	    detalleCuentas.add(getDetalleCuentas("000", "000011", "1", "1999", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "0", "2214", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "3", "14124", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "2", "14124", null, null));
	    
		Respuesta<ProcesarTransferenciaComexView> respuestaRSA = new Respuesta<ProcesarTransferenciaComexView>();
		respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
		when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(ProcesarTransferenciaComexView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
		asignarEstadisticasDeAutenticacion();
	    
		DocumentacionAdjuntaView docView = new DocumentacionAdjuntaView();
		docView.setArchivos(new ArrayList<ReporteView>());
		procesarInView.setDocumentacionAdjuntaView(docView);
		
		when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);
		when(sesionCliente.getCliente()).thenReturn(generarCliente());
		when(sesionParametros.getMonedasComexMap()).thenReturn(generarMapaMonedas());
		when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(ProcesarTransferenciaComexView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
		when(comexCanalesBO.procesarTransferenciaComex(Matchers.any(ProcesarTransferenciaComexInDTO.class))).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_MSG_INFO_OK)).thenReturn(mensaje);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_OK)).thenReturn(mensaje);
		
		Respuesta<ProcesarTransferenciaComexView> respuesta = comexCanalesManager.procesarTransferenciaAlta(procesarInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void procesarTransferenciaAltaWarningRSA() {
		ProcesarTransferenciaComexView procesarInView = generarProcesarInView();
		procesarInView.getDatosTransferencia().setIdCuentaOrigen(0);

		Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO = new Respuesta<ProcesarTransferenciaComexOutDTO>();
		
		ProcesarTransferenciaComexOutDTO procesarOutDTO = new ProcesarTransferenciaComexOutDTO();
		
		procesarOutDTO.setNroTransferencia(96567);
		
		respuestaBO.setRespuesta(procesarOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
	    detalleCuentas.add(getDetalleCuentas("000", "000011", "1", "1999", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "0", "2214", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "3", "14124", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "2", "14124", null, null));
	    
	    Respuesta<ProcesarTransferenciaComexView> respuestaRSA = new Respuesta<ProcesarTransferenciaComexView>();
		respuestaRSA.setEstadoRespuesta(EstadoRespuesta.WARNING);
		asignarEstadisticasDeAutenticacion();
		AutentificacionDTO autentificacion = new AutentificacionDTO();
		autentificacion.setTipoDesafio(TipoDesafioEnum.BANELCO);
		respuestaRSA.add(new ItemMensajeRespuesta());
//		autentificacion.setRsaDTO();
//		respuestaRSA.setRespuesta(autentificacion);

		
		
	    
		when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);
		when(sesionCliente.getCliente()).thenReturn(generarCliente());
		when(sesionParametros.getMonedasComexMap()).thenReturn(generarMapaMonedas());
		when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(ProcesarTransferenciaComexView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
		when(comexCanalesBO.procesarTransferenciaComex(Matchers.any(ProcesarTransferenciaComexInDTO.class))).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_MSG_INFO_OK)).thenReturn(mensaje);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_OK)).thenReturn(mensaje);
		
		Respuesta<ProcesarTransferenciaComexView> respuesta = comexCanalesManager.procesarTransferenciaAlta(procesarInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
	}
	@Test
	public void procesarTransferenciaAltaOkSimilar() {
		ProcesarTransferenciaComexView procesarInView = generarProcesarInView();
		procesarInView.getDatosTransferencia().setIdCuentaOrigen(0);
		procesarInView.getDatosTransferencia().setConceptoCodigo("I07");
		procesarInView.getDatosTransferencia().setConceptoDescripcion("TRANSFERENCIAS PERSONALES");
		procesarInView.setNroFormRel(new Long("96565"));
		
		Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO = new Respuesta<ProcesarTransferenciaComexOutDTO>();
		
		ProcesarTransferenciaComexOutDTO procesarOutDTO = new ProcesarTransferenciaComexOutDTO();
		
		procesarOutDTO.setNroTransferencia(96567);
		
		respuestaBO.setRespuesta(procesarOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Respuesta<ProcesarTransferenciaComexView> respuestaRSA = new Respuesta<ProcesarTransferenciaComexView>();
		respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
		asignarEstadisticasDeAutenticacion();
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
	    detalleCuentas.add(getDetalleCuentas("000", "000011", "1", "1999", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "0", "2214", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "3", "14124", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "2", "14124", null, null));
	    
		DocumentacionAdjuntaView docView = new DocumentacionAdjuntaView();
		docView.setArchivos(new ArrayList<ReporteView>());
		procesarInView.setDocumentacionAdjuntaView(docView);

//		autentificacionManager.setCodigoEstadisticaSolicitudBanelco();
//		autentificacionManager.setCodigoEstadisticaValidacionBanelco();

		when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);
		when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(ProcesarTransferenciaComexView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
		when(sesionCliente.getCliente()).thenReturn(generarCliente());
		when(sesionParametros.getMonedasComexMap()).thenReturn(generarMapaMonedas());

		when(comexCanalesBO.procesarTransferenciaComex(Matchers.any(ProcesarTransferenciaComexInDTO.class))).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_MSG_INFO_OK)).thenReturn(mensaje);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_OK)).thenReturn(mensaje);
		
		Respuesta<ProcesarTransferenciaComexView> respuesta = comexCanalesManager.procesarTransferenciaAlta(procesarInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void procesarTransferenciaErrorRSA() {
		ProcesarTransferenciaComexView procesarInView = generarProcesarInView();
		procesarInView.getDatosTransferencia().setIdCuentaOrigen(0);

		Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO = new Respuesta<ProcesarTransferenciaComexOutDTO>();
		
		ProcesarTransferenciaComexOutDTO procesarOutDTO = new ProcesarTransferenciaComexOutDTO();
		
		procesarOutDTO.setNroTransferencia(96567);
		
		respuestaBO.setRespuesta(procesarOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
	
		Respuesta<ProcesarTransferenciaComexView> respuestaRSA = new Respuesta<ProcesarTransferenciaComexView>();
		respuestaRSA.setEstadoRespuesta(EstadoRespuesta.ERROR);
		
		List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
	    detalleCuentas.add(getDetalleCuentas("000", "000011", "1", "1999", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "0", "2214", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "3", "14124", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "2", "14124", null, null));
	    
		when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);
		when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(ProcesarTransferenciaComexView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
		
		Respuesta<ProcesarTransferenciaComexView> respuesta = comexCanalesManager.procesarTransferenciaAlta(procesarInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void procesarTransferenciaAltaOkModificacion() {
		ProcesarTransferenciaComexView procesarInView = generarProcesarInView();
		procesarInView.getDatosTransferencia().setIdCuentaOrigen(0);		
		procesarInView.setNroTransferencia(96565);
		AutentificacionDTO autentificacionView = new AutentificacionDTO();
		autentificacionView.setTipoDesafio(TipoDesafioEnum.BANELCO);
		procesarInView.setDesafio(autentificacionView);
		
		Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO = new Respuesta<ProcesarTransferenciaComexOutDTO>();
		
		ProcesarTransferenciaComexOutDTO procesarOutDTO = new ProcesarTransferenciaComexOutDTO();
		
		procesarOutDTO.setNroTransferencia(96565);
		
		respuestaBO.setRespuesta(procesarOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		Respuesta<ProcesarTransferenciaComexView> respuestaRSA = new Respuesta<ProcesarTransferenciaComexView>();
		respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
		
		
		List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
	    detalleCuentas.add(getDetalleCuentas("000", "000011", "1", "1999", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "0", "2214", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "3", "14124", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "2", "14124", null, null));
	    ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();
	    
		DocumentacionAdjuntaView docView = new DocumentacionAdjuntaView();
		docView.setArchivos(new ArrayList<ReporteView>());
		procesarInView.setDocumentacionAdjuntaView(docView);

		when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		when(sesionCliente.getCliente()).thenReturn(generarCliente());
		when(sesionParametros.getMonedasComexMap()).thenReturn(generarMapaMonedas());
		when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(ProcesarTransferenciaComexView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
		when(comexCanalesBO.procesarTransferenciaComex(Matchers.any(ProcesarTransferenciaComexInDTO.class))).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_MSG_INFO_OK)).thenReturn(mensaje);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_OK)).thenReturn(mensaje);

		Respuesta<ProcesarTransferenciaComexView> respuesta = comexCanalesManager.procesarTransferenciaAlta(procesarInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void procesarTransferenciaAltaErrorSinOperacion() {
		ProcesarTransferenciaComexView procesarInView = generarProcesarInView();
		procesarInView.getDatosTransferencia().setIdCuentaOrigen(0);		
		procesarInView.setNroTransferencia(231);
		
		Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO = new Respuesta<ProcesarTransferenciaComexOutDTO>();
		
		ProcesarTransferenciaComexOutDTO procesarOutDTO = new ProcesarTransferenciaComexOutDTO();
		
		procesarOutDTO.setNroTransferencia(231);
		
		respuestaBO.setRespuesta(procesarOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
	    detalleCuentas.add(getDetalleCuentas("000", "000011", "1", "1999", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "0", "2214", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "3", "14124", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "2", "14124", null, null));
	    
	    ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();
	    
		Respuesta<ProcesarTransferenciaComexView> respuestaRSA = new Respuesta<ProcesarTransferenciaComexView>();
		respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
		asignarEstadisticasDeAutenticacion();

		when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		when(sesionCliente.getCliente()).thenReturn(generarCliente());
		when(sesionParametros.getMonedasComexMap()).thenReturn(generarMapaMonedas());
		when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(ProcesarTransferenciaComexView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
		when(comexCanalesBO.procesarTransferenciaComex(Matchers.any(ProcesarTransferenciaComexInDTO.class))).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO)).thenReturn(mensaje);
		
		Respuesta<ProcesarTransferenciaComexView> respuesta = comexCanalesManager.procesarTransferenciaAlta(procesarInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void procesarTransferenciaAltaErrorBO() {
		ProcesarTransferenciaComexView procesarInView = generarProcesarInView();
		procesarInView.getDatosTransferencia().setIdCuentaOrigen(0);

		Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO = new Respuesta<ProcesarTransferenciaComexOutDTO>();
		
		ProcesarTransferenciaComexOutDTO procesarOutDTO = new ProcesarTransferenciaComexOutDTO();

		respuestaBO.setRespuesta(procesarOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		ItemMensajeRespuesta item = new ItemMensajeRespuesta();
		item.setTipoError("ERROR_SERVICIO");
		respuestaBO.add(item);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		List<ResumenDetalleCuenta> detalleCuentas = new ArrayList<ResumenDetalleCuenta>();
	    detalleCuentas.add(getDetalleCuentas("000", "000011", "1", "1999", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "0", "2214", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "3", "14124", null, null));
	    detalleCuentas.add(getDetalleCuentas("000", "000021", "2", "14124", null, null));
	    
		Respuesta<ProcesarTransferenciaComexView> respuestaRSA = new Respuesta<ProcesarTransferenciaComexView>();
		respuestaRSA.setEstadoRespuesta(EstadoRespuesta.OK);
		asignarEstadisticasDeAutenticacion();
	    
		when(sesionParametros.getCuentasComex()).thenReturn(detalleCuentas);
		when(sesionCliente.getCliente()).thenReturn(generarCliente());
		when(sesionParametros.getMonedasComexMap()).thenReturn(generarMapaMonedas());
		when(desafioOperacionRSA.validarOperacionRSA(Matchers.any(ProcesarTransferenciaComexView.class),Matchers.any(Integer.class), Matchers.any(AutentificacionCodEstDTO.class))).thenReturn(respuestaRSA);
		when(comexCanalesBO.procesarTransferenciaComex(Matchers.any(ProcesarTransferenciaComexInDTO.class))).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_ERROR)).thenReturn(mensaje);
		
		Respuesta<ProcesarTransferenciaComexView> respuesta = comexCanalesManager.procesarTransferenciaAlta(procesarInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void procesarTransferenciaBajaOk() {
		ProcesarTransferenciaComexView procesarInView = generarProcesarInView();
		procesarInView.setNroTransferencia(96565);
		
		ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();
		
		Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO = new Respuesta<ProcesarTransferenciaComexOutDTO>();
		ProcesarTransferenciaComexOutDTO procesarOutDTO = new ProcesarTransferenciaComexOutDTO();
		
		procesarOutDTO.setNroTransferencia(96565);
		
		respuestaBO.setRespuesta(procesarOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		when(comexCanalesBO.procesarTransferenciaComex(Matchers.any(ProcesarTransferenciaComexInDTO.class))).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_BAJA_TRANSFERENCIA)).thenReturn(mensaje);
		
		Respuesta<ProcesarTransferenciaComexView> respuesta = comexCanalesManager.procesarTransferenciaBaja(procesarInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void procesarTransferenciaBajaErrorOperacion() {
		ProcesarTransferenciaComexView procesarInView = generarProcesarInView();
		procesarInView.setNroTransferencia(96505);
		
		ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();
		
		Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO = new Respuesta<ProcesarTransferenciaComexOutDTO>();
		ProcesarTransferenciaComexOutDTO procesarOutDTO = new ProcesarTransferenciaComexOutDTO();
		
		procesarOutDTO.setNroTransferencia(96505);
		
		respuestaBO.setRespuesta(procesarOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		when(comexCanalesBO.procesarTransferenciaComex(Matchers.any(ProcesarTransferenciaComexInDTO.class))).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_BAJA_TRANSFERENCIA_ERROR)).thenReturn(mensaje);
		
		Respuesta<ProcesarTransferenciaComexView> respuesta = comexCanalesManager.procesarTransferenciaBaja(procesarInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void procesarTransferenciaBajaErrorBO() {
		ProcesarTransferenciaComexView procesarInView = generarProcesarInView();
		procesarInView.setNroTransferencia(96565);
		
		ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();
		
		Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO = new Respuesta<ProcesarTransferenciaComexOutDTO>();
		ProcesarTransferenciaComexOutDTO procesarOutDTO = new ProcesarTransferenciaComexOutDTO();
		
		procesarOutDTO.setNroTransferencia(96565);
		
		respuestaBO.setRespuesta(procesarOutDTO);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		when(comexCanalesBO.procesarTransferenciaComex(Matchers.any(ProcesarTransferenciaComexInDTO.class))).thenReturn(respuestaBO);
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_BAJA_TRANSFERENCIA_ERROR)).thenReturn(mensaje);
		
		Respuesta<ProcesarTransferenciaComexView> respuesta = comexCanalesManager.procesarTransferenciaBaja(procesarInView);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void borrarArchivoOk() {
		ArchivoTransferenciaView archivoIn = new ArchivoTransferenciaView();
		ReporteView archivo = new ReporteView();
		archivo.setId("1");
		archivo.setNombre("20_iso100_105mm.jpg");
		archivoIn.setArchivo(archivo);
		archivoIn.setNroTransferencia(96565);

		Respuesta<Boolean> respuestaBO = new Respuesta<Boolean>();
		respuestaBO.setRespuesta(Boolean.TRUE);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
	
		ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();
	 
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		when(comexCanalesBO.borrarDoc(Matchers.any(AdjuntarArchivosDTO.class))).thenReturn(respuestaBO);
		
		Respuesta<Void> respuesta = comexCanalesManager.borrarArchivo(archivoIn);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void borrarArchivoErrorSinOperacion() {
		ArchivoTransferenciaView archivoIn = new ArchivoTransferenciaView();
		ReporteView archivo = new ReporteView();
		archivo.setId("1");
		archivo.setNombre("20_iso100_105mm.jpg");
		archivoIn.setArchivo(archivo);
		archivoIn.setNroTransferencia(99999);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		Respuesta<Boolean> respuestaBO = new Respuesta<Boolean>();
		respuestaBO.setRespuesta(Boolean.TRUE);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();
	
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_BAJA_TRANSFERENCIA_ERROR)).thenReturn(mensaje);
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		when(comexCanalesBO.borrarDoc(Matchers.any(AdjuntarArchivosDTO.class))).thenReturn(respuestaBO);
		
		Respuesta<Void> respuesta = comexCanalesManager.borrarArchivo(archivoIn);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void borrarArchivoErrorBO() {
		ArchivoTransferenciaView archivoIn = new ArchivoTransferenciaView();
		ReporteView archivo = new ReporteView();
		archivo.setId("1");
		archivo.setNombre("20_iso100_105mm.jpg");
		archivoIn.setArchivo(archivo);
		archivoIn.setNroTransferencia(96565);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		Respuesta<Boolean> respuestaBO = new Respuesta<Boolean>();
		respuestaBO.setRespuesta(Boolean.FALSE);
		respuestaBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();
	
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TRANSFEXT_ADJUNTAR_ERROR_ELIMINAR)).thenReturn(mensaje);
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		when(comexCanalesBO.borrarDoc(Matchers.any(AdjuntarArchivosDTO.class))).thenReturn(respuestaBO);
		
		Respuesta<Void> respuesta = comexCanalesManager.borrarArchivo(archivoIn);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void borrarArchivoErrorSinID() {
		ArchivoTransferenciaView archivoIn = new ArchivoTransferenciaView();
		ReporteView archivo = new ReporteView();

		archivo.setNombre("20_iso100_105mm.jpg");
		archivoIn.setArchivo(archivo);

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
	
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TRANSFEXT_ADJUNTAR_ERROR_ELIMINAR)).thenReturn(mensaje);
		
		Respuesta<Void> respuesta = comexCanalesManager.borrarArchivo(archivoIn);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void obtenerArchivoWsComexOk(){
		Respuesta<AdjuntarArchivosDTO> responseBO = new Respuesta<AdjuntarArchivosDTO>();
		responseBO.setEstadoRespuesta(EstadoRespuesta.OK);
		
		ArchivoTransferenciaView archivoIn = new ArchivoTransferenciaView();
		ReporteView archivo = new ReporteView();
		archivo.setId("1");
		archivoIn.setArchivo(archivo);
		archivoIn.setNroTransferencia(96565);
		
		AdjuntarArchivosDTO archivoDTO = new AdjuntarArchivosDTO();
		archivoDTO.setArchivo(archivo);
		archivoDTO.setNroTransferencia(96565);
		
		responseBO.setRespuesta(archivoDTO);
		ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();
		
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		when(comexCanalesBO.consultaImagenTrf(Matchers.any(AdjuntarArchivosDTO.class))).thenReturn(responseBO);
		
		Respuesta<AdjuntarArchivosDTO> respuesta = comexCanalesManager.obtenerArchivoWsComex(archivoIn);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void obtenerArchivoWsComexErrorSinOperacion(){
		
		ArchivoTransferenciaView archivoIn = new ArchivoTransferenciaView();
		ReporteView archivo = new ReporteView();
		archivo.setId("1");
		archivoIn.setArchivo(archivo);
		archivoIn.setNroTransferencia(99999);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();
		
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_BAJA_TRANSFERENCIA_ERROR)).thenReturn(mensaje);
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		
		Respuesta<AdjuntarArchivosDTO> respuesta = comexCanalesManager.obtenerArchivoWsComex(archivoIn);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void obtenerArchivoWsComexErrorSinNroTransferencia(){
		
		ArchivoTransferenciaView archivoIn = new ArchivoTransferenciaView();
		ReporteView archivo = new ReporteView();
		archivo.setId("1");
		archivoIn.setArchivo(archivo);
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		ConsultaOperacionesOutView consultaOperacionesOutView = generarConsultaOperacionesOutView();
		
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_COMEX_BAJA_TRANSFERENCIA_ERROR)).thenReturn(mensaje);
		when(sesionParametros.getConsultaOperacionesOutView()).thenReturn(consultaOperacionesOutView);
		
		Respuesta<AdjuntarArchivosDTO> respuesta = comexCanalesManager.obtenerArchivoWsComex(archivoIn);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	private ConsultaOperacionesOutView generarConsultaOperacionesOutView() {
		ConsultaOperacionesOutView consultaOperacionesOutView = new ConsultaOperacionesOutView();
		List<ConsultaOperacionesView> listOperaciones = new ArrayList<ConsultaOperacionesView>();
		
		ConsultaOperacionesView operacion1 = new ConsultaOperacionesView();
		operacion1.setCodEstado("A");
		operacion1.setCuentaCliente("");
		operacion1.setDestinatario("Abrahan Gonzales");
		operacion1.setEstadoDescripcion("Guardada");
		operacion1.setFechaOperacion("09/10/2018");
		operacion1.setMonedaDescripcion("BRL");
		operacion1.setMonto("50,00");
		operacion1.setNroForm(new Long("96565"));

		consultaOperacionesOutView.setOperaciones(listOperaciones);
		ConsultaOperacionesView operacion2 = new ConsultaOperacionesView();
		operacion2.setCodEstado("G");
		operacion2.setCuentaCliente("000-10-3580475");
		operacion2.setDestinatario("Ricardo Marllo");
		operacion2.setEstadoDescripcion("Pendiente");
		operacion2.setFechaOperacion("18/10/2018");
		operacion2.setMonedaDescripcion("LIBRAS ESTERLINAS");
		operacion2.setMonto("15,00");
		operacion2.setNroForm(new Long ("96655"));

		ConsultaOperacionesView operacion3 = new ConsultaOperacionesView();
		operacion3.setCodEstado("G");
		operacion3.setCuentaCliente("000-10-3580475");
		operacion3.setDestinatario("COMEX10");
		operacion3.setEstadoDescripcion("Pendiente");
		operacion3.setFechaOperacion("18/10/2018");
		operacion3.setMonedaDescripcion("DOLARES");
		operacion3.setMonto("12,00");
		operacion3.setNroForm(new Long ("96654"));
		
		ConsultaOperacionesView operacion4 = new ConsultaOperacionesView();
		operacion4.setCodEstado("G");
		operacion4.setCuentaCliente("000-09-3580475");
		operacion4.setDestinatario("COMEX09");
		operacion4.setEstadoDescripcion("Pendiente");
		operacion4.setFechaOperacion("18/10/2018");
		operacion4.setMonedaDescripcion("CORONAS DANESAS");
		operacion4.setMonto("12,00");
		operacion4.setNroForm(new Long ("96653"));
		
		ConsultaOperacionesView operacion5 = new ConsultaOperacionesView();
		operacion5.setCodEstado("A");
		operacion5.setCuentaCliente("000-09-3580475");
		operacion5.setDestinatario("Abrahan Gonzales");
		operacion5.setEstadoDescripcion("Guardada");
		operacion5.setFechaOperacion("09/10/2018");
		operacion4.setMonedaDescripcion("REALES");
		operacion5.setMonto("50,00");
		operacion5.setNroForm(new Long ("96565"));
		
		listOperaciones.add(operacion1);
		listOperaciones.add(operacion2);
		listOperaciones.add(operacion3);
		listOperaciones.add(operacion4);
		listOperaciones.add(operacion5);
		
		return consultaOperacionesOutView;
	}
	
	private List<ConsultaMonedaOutDTO> generarListaMonedas(){
		ConsultaMonedaOutDTO libras = new ConsultaMonedaOutDTO();
		libras.setCodBCRAMoneda("GBP");
		libras.setCodigoMoneda("001");
		libras.setDescripcionMoneda("LIBRAS ESTERLINAS");
		ConsultaMonedaOutDTO dolares = new ConsultaMonedaOutDTO();
		dolares.setCodBCRAMoneda("USD");
		dolares.setCodigoMoneda("002");
		dolares.setDescripcionMoneda("DOLARES");

		ConsultaMonedaOutDTO euro = new ConsultaMonedaOutDTO();
		euro.setCodBCRAMoneda("EUR");
		euro.setCodigoMoneda("098");
		euro.setDescripcionMoneda("MONEDA COMUN EUROPEA");
		
		ConsultaMonedaOutDTO real = new ConsultaMonedaOutDTO();
		real.setCodBCRAMoneda("BRL");
		real.setCodigoMoneda("092");
		real.setDescripcionMoneda("REALES");
		
		ConsultaMonedaOutDTO coronas = new ConsultaMonedaOutDTO();
		coronas.setCodBCRAMoneda("DKK");
		coronas.setCodigoMoneda("015");
		coronas.setDescripcionMoneda("CORONAS DANESAS");
		
		ConsultaMonedaOutDTO pesosUY = new ConsultaMonedaOutDTO();
		pesosUY.setCodBCRAMoneda("UYP");
		pesosUY.setCodigoMoneda("010");
		pesosUY.setDescripcionMoneda("PESOS URUGUAYOS");
		List<ConsultaMonedaOutDTO> listaMonedas = new ArrayList<ConsultaMonedaOutDTO>();
		listaMonedas.add(libras);
		listaMonedas.add(dolares);
		listaMonedas.add(euro);
		listaMonedas.add(real);
		listaMonedas.add(coronas);
		listaMonedas.add(pesosUY);
		return listaMonedas;
	}
	
	private Map<String, ConsultaMonedaOutDTO> generarMapaMonedas(){
		Map<String, ConsultaMonedaOutDTO> monedasMap = new HashMap<String, ConsultaMonedaOutDTO>();
		for (ConsultaMonedaOutDTO moneda : generarListaMonedas()) {
			monedasMap.put(moneda.getCodigoMoneda(), moneda);
		}
		return monedasMap;
	}
	
	private AutentificacionCodEstDTO asignarEstadisticasDeAutenticacion() { 
//		autentificacionManager.setCodigoEstadisticaSinDesaf
		autentificacionManager.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.COMEX_SOLICITUD_TOKEN); 
		autentificacionManager.setCodigoEstadisticaValidacionToken(EstadisticasConstants.COMEX_VALIDACION_TOKEN);
		autentificacionManager.setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.COMEX_SOLICITUD_COORDENADAS);
		autentificacionManager.setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.COMEX_VALIDACION_COORDENADAS);
		autentificacionManager.setCodigoEstadisticaSolicitudBanelco(EstadisticasConstants.COMEX_SOLICITUD_BANELCO);
		autentificacionManager.setCodigoEstadisticaValidacionBanelco(EstadisticasConstants.COMEX_VALIDACION_BANELCO);
		return new AutentificacionCodEstDTO();
	}
	
	private Cliente generarCliente() {
		Cliente cliente = new Cliente();
		cliente.setNombre("Nombre");
		cliente.setApellido1("Prueba");
		cliente.setApellido2("Prueba 2");
		cliente.setNup("12345678");
		return cliente;
	}
	
	private ProcesarTransferenciaComexView generarProcesarInView() {
		ProcesarTransferenciaComexView procesarInView = new ProcesarTransferenciaComexView();
		DatosBeneficiarioView datosBeneficiario = new DatosBeneficiarioView();
		DatosTransferenciaView datosTransferencia = new DatosTransferenciaView();
		datosBeneficiario.setNombreBeneficiario("Jesus Fajardo");
		
		datosTransferencia.setConceptoCodigo("B05");
		datosTransferencia.setConceptoDescripcion("PAGOS ANTICIPADOS DE IMPORTACIONES DE BIENES");
		procesarInView.setAceptaDDJJ(false);
		procesarInView.setCodMoneda("010");
		procesarInView.setDatosBeneficiario(datosBeneficiario);
		procesarInView.setDatosTransferencia(datosTransferencia);
		
//		procesarInView.setDesafio(desafio);
//		procesarInView.setDocumentacionAdjuntaView(documentacionAdjuntaView);
//		procesarInView.setFecha(fecha);
//		procesarInView.setId(id);
//		procesarInView.setIdSesion(idSesion);
		procesarInView.setIgnorarRSA(false);
//		procesarInView.setMensajeFeedback(mensajeFeedback);
//		procesarInView.setMensajeInfo(mensajeInfo);
		procesarInView.setMonto(new BigDecimal("15"));
//		procesarInView.setNroFormRel(nroFormRel);
//		procesarInView.setNroTransferencia(nroTransferencia);
		procesarInView.setTipoOperacion(OperacionesRSAEnum.TRANSFERENCIA_COMEX);
		return procesarInView;
	}
	/**
     * Gets the detalle cuentas.
     *
     * @param sucursal
     *            the sucursal
     * @param nroCuentaProducto
     *            the nro cuenta producto
     * @param tipoCuenta
     *            the tipo cuenta
     * @param saldo
     *            the saldo
     * @param sobregiro
     *            the sobregiro
     * @param limiteDescubierto
     *            the limite descubierto
     * @return the detalle cuentas
     */
    private ResumenDetalleCuenta getDetalleCuentas(String sucursal, String nroCuentaProducto, String tipoCuenta,
            String saldo, String sobregiro, BigDecimal limiteDescubierto) {
        ResumenDetalleCuenta resumenDetalleCuenta = new ResumenDetalleCuenta();
        resumenDetalleCuenta.setNroSucursal(sucursal);
        resumenDetalleCuenta.setNroCuentaProducto(nroCuentaProducto);
        resumenDetalleCuenta.setTipoCuenta(tipoCuenta);
        resumenDetalleCuenta.setCuentaCerrada(false);
        resumenDetalleCuenta.setCuentaUnica(false);
        resumenDetalleCuenta.setCuentaPrincipal(true);
        resumenDetalleCuenta.setAlias("Cuenta1");
        resumenDetalleCuenta.setFechaDesdeMovimiento("10/10/2010");
        resumenDetalleCuenta.setFechaHastaMovimiento("10/10/2010");
        resumenDetalleCuenta.setIndicadorSobregiro(sobregiro);
        resumenDetalleCuenta.setLimiteDescubierto(limiteDescubierto);
        Cliente cliente = new Cliente();
        cliente.setNombre("Pepe");
        cliente.setApellido1("Luis");
        cliente.setTipoDocumento("N");
        cliente.setDni("33333333");
        resumenDetalleCuenta.setCliente(cliente);

        if ("2".equals(tipoCuenta)) {
            resumenDetalleCuenta.setSaldoPesos(new BigDecimal(Integer.valueOf(saldo)));
            resumenDetalleCuenta.setSaldoDolares(new BigDecimal(Integer.valueOf(saldo)));
        }
        return resumenDetalleCuenta;
    }
}
