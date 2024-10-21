package ar.com.santanderrio.obp.servicios.comex.transfext.bo;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfBanco;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfMoneda;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfPais;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.Banco;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.Moneda;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ObjectFactory;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.Pais;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaBancosResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaMonedasResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaPaisesResponse;
import ar.com.santanderrio.obp.servicios.comex.transfext.bo.impl.ComexConsultasBOImpl;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexConsultasDAO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ComprobanteComexDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaBancosInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaBancosOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaMonedaOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaPaisesOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.AdjuntarArchivosInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ComprobanteComexInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaBancosInEntity;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.dao.ConsultaPadronOCuitDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class ComexConsultasBOTest {

	/** ComexConsultas WS DAO */
	@Mock
	private ComexConsultasDAO comexConsultasDAO;

	/** The respuesta factory. */
	@Spy
	@InjectMocks
	private RespuestaFactory respuestaFactory;

	/** The consulta padron cuit DAO. */
	@Mock
	private ConsultaPadronOCuitDAO consultaPadronOCuitDAO;

	/** The ComexConsultas BO. */
	@InjectMocks
	private ComexConsultasBO comexConsultasBO = new ComexConsultasBOImpl();

	/** The sesion parametros. */
	@Mock
	private SesionParametros sesionParametros;

    /** The mensaje bo. */
    @Mock
    private MensajeBO mensajeBO;

	/**
	 * Inits.
	 */
	@Before
	public void init() throws ServiceException {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void obtenerComprobanteConBancoIntermediarioOk() throws DAOException {
		ComprobanteComexDTO comprobanteDTO = new ComprobanteComexDTO();
		comprobanteDTO.setMotivo("Transferencias Personales");
		comprobanteDTO.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		comprobanteDTO.setLegales("Prueba");
		comprobanteDTO.setGastosACargo("Ordenante");
		comprobanteDTO.setNombre("Magalí Romero");
		comprobanteDTO.setImporte("USD 100,00");
		comprobanteDTO.setIdConcepto("1387");
		comprobanteDTO.setVinculo("Hermano");
		comprobanteDTO.setDomicilio("Park Avenue 42");
		comprobanteDTO.setPais(ISBANStringUtils.normalizarCaraceteres("CA, Estados Unidos"));
		comprobanteDTO.setNombreEmisor("Magalí Romero");
		comprobanteDTO.setCuentaOrigen("Cuenta".concat(" ").concat("000-358047/5"));
		comprobanteDTO.setDescripcionCuentaOrigen("Cuenta única en $");
		comprobanteDTO.setCuentaDestino("72834196582");
		comprobanteDTO.setCodigoBancario("CITIUS33XXX");
		comprobanteDTO.setIdGastosACargo("1");
		comprobanteDTO.setBancoIntermediario("123456789");
		comprobanteDTO.setCodigoIntermediario("CITIUS33XXX");
		comprobanteDTO.setNombreDocumentacion("Documentacion");
		comprobanteDTO.setNumeroComprobante("4500");

		Reporte outDAO = new Reporte();
		outDAO.setNombre("asd");


		when(comexConsultasDAO.generarComprobante(Matchers.any(ComprobanteComexInEntity.class))).thenReturn(outDAO);

		Respuesta<Reporte> respuesta = comexConsultasBO.obtenerComprobante(comprobanteDTO);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

	}

	@Test
	public void consultaBancosOk() throws DAOException{
		ConsultaBancosInDTO  consultaBancosInDTO = new ConsultaBancosInDTO();
		consultaBancosInDTO.setCodigoBancario("CITIUS");
		consultaBancosInDTO.setTipoCodigo("SWIFT");

		ConsultaBancosResponse response = new ConsultaBancosResponse();

		Banco banco = new Banco();
		ArrayOfBanco arrayBanco = new ArrayOfBanco();
		ObjectFactory bancoFactory = new ObjectFactory();
		banco.setAbaBanco(bancoFactory.createBancoAbaBanco(null));
		banco.setDomicilioBanco(bancoFactory.createBancoDomicilioBanco("N/D"));
		banco.setLocalidadBanco(bancoFactory.createBancoLocalidadBanco("N/D"));
		banco.setNombreBanco(bancoFactory.createBancoNombreBanco("CITIBANK N.A."));
		banco.setPaisBanco(bancoFactory.createBancoPaisBanco("ESTADOSUNIDOS"));
		banco.setSwiftBanco(bancoFactory.createBancoSwiftBanco("CITIUS33GRP"));

		Banco banco2 = new Banco();
		banco2.setAbaBanco(bancoFactory.createBancoAbaBanco(null));
		banco2.setDomicilioBanco(bancoFactory.createBancoDomicilioBanco("N/D"));
		banco2.setLocalidadBanco(bancoFactory.createBancoLocalidadBanco("N/D"));
		banco2.setNombreBanco(bancoFactory.createBancoNombreBanco("CITIBANK N.A."));
		banco2.setPaisBanco(bancoFactory.createBancoPaisBanco("ESTADOSUNIDOS"));
		banco2.setSwiftBanco(bancoFactory.createBancoSwiftBanco("CITIUS33CTA"));
		bancoFactory.createBanco(banco);

		arrayBanco.getBanco().add(banco);
		arrayBanco.getBanco().add(banco2);
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.
		ObjectFactory respuestaFactory= new 
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory();
		response.setErrorInterface(respuestaFactory.createBaseResponseErrorInterface(null));
		response.setErrorSistema(respuestaFactory.createBaseResponseErrorSistema(null));
		response.setCantReg(new BigDecimal(2));
		response.setRegistros(bancoFactory.createArrayOfBanco(arrayBanco));

		when(comexConsultasDAO.consultaBancos(Matchers.any(ConsultaBancosInEntity.class))).thenReturn(response);

		Respuesta<List<ConsultaBancosOutDTO>> respuesta = comexConsultasBO.consultaBancos(consultaBancosInDTO);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);  
	}

	@Test
	public void consultaBancosVacio() throws DAOException{

		ConsultaBancosInDTO  consultaBancosInDTO = new ConsultaBancosInDTO();
		consultaBancosInDTO.setCodigoBancario("CITIUS");
		consultaBancosInDTO.setTipoCodigo("SWIFT");
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");

		ConsultaBancosResponse response = new ConsultaBancosResponse();
		ObjectFactory bancoFactory = new ObjectFactory();


	
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.
		ObjectFactory respuestaFactory= new 
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory();
		response.setErrorInterface(respuestaFactory.createBaseResponseErrorInterface(null));
		response.setErrorSistema(respuestaFactory.createBaseResponseErrorSistema(null));
		response.setCantReg(new BigDecimal(0));
		response.setRegistros(bancoFactory.createArrayOfBanco(null));

		
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		when(comexConsultasDAO.consultaBancos(Matchers.any(ConsultaBancosInEntity.class))).thenReturn(response);

		Respuesta<List<ConsultaBancosOutDTO>> respuesta = comexConsultasBO.consultaBancos(consultaBancosInDTO);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void consultaBancosError() throws DAOException{
		ConsultaBancosInDTO  consultaBancosInDTO = new ConsultaBancosInDTO();
		consultaBancosInDTO.setCodigoBancario("CITIUS");
		consultaBancosInDTO.setTipoCodigo("SWIFT");
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");

		ConsultaBancosResponse response = new ConsultaBancosResponse();
		ObjectFactory bancoFactory = new ObjectFactory();


	
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory
		respuestaFactory= new 
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory();
		response.setErrorInterface(respuestaFactory.createBaseResponseErrorInterface("Firma invalida"));
		response.setErrorSistema(respuestaFactory.createBaseResponseErrorSistema("Tipo de firma inválida"));
		response.setCantReg(new BigDecimal(-1));
		response.setRegistros(bancoFactory.createArrayOfBanco(null));

		
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		when(comexConsultasDAO.consultaBancos(Matchers.any(ConsultaBancosInEntity.class))).thenReturn(response);

		Respuesta<List<ConsultaBancosOutDTO>> respuesta = comexConsultasBO.consultaBancos(consultaBancosInDTO);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void consultaBancosDAOException() throws DAOException{
		ConsultaBancosInDTO  consultaBancosInDTO = new ConsultaBancosInDTO();
		consultaBancosInDTO.setCodigoBancario("CITIUS");
		consultaBancosInDTO.setTipoCodigo("SWIFT");
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");

		ConsultaBancosResponse response = new ConsultaBancosResponse();
		ObjectFactory bancoFactory = new ObjectFactory();


	
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory
		respuestaFactory= new 
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory();
		response.setErrorInterface(respuestaFactory.createBaseResponseErrorInterface("Firma invalida"));
		response.setErrorSistema(respuestaFactory.createBaseResponseErrorSistema("Tipo de firma inválida"));
		response.setCantReg(new BigDecimal(-1));
		response.setRegistros(bancoFactory.createArrayOfBanco(null));

		
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		when(comexConsultasDAO.consultaBancos(Matchers.any(ConsultaBancosInEntity.class))).thenThrow(new DAOException());

		Respuesta<List<ConsultaBancosOutDTO>> respuesta = comexConsultasBO.consultaBancos(consultaBancosInDTO);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void consultaPaisesOk() throws DAOException{
		ConsultaPaisesResponse response = new ConsultaPaisesResponse();
		
		ObjectFactory paisFactory = new ObjectFactory();
		Pais pais = new Pais();
		pais.setCodigoPais(paisFactory.createPaisCodigoPais("AE"));
		pais.setDescripcionPais(paisFactory.createPaisDescripcionPais("U.A.E."));

		Pais pais2 = new Pais();
		pais2.setCodigoPais(paisFactory.createPaisCodigoPais("AF"));
		pais2.setDescripcionPais(paisFactory.createPaisDescripcionPais("AFGHANISTAN"));
		

		ArrayOfPais arrayPais = new ArrayOfPais();
		arrayPais.getPais().add(pais);
		arrayPais.getPais().add(pais2);
		
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory
		respuestaFactory= new 
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory();
		response.setErrorInterface(respuestaFactory.createBaseResponseErrorInterface(null));
		response.setErrorSistema(respuestaFactory.createBaseResponseErrorSistema(null));
		response.setRegistros(paisFactory.createArrayOfPais(arrayPais));
		
		when(comexConsultasDAO.consultaPaises()).thenReturn(response);
		
		Respuesta<List<ConsultaPaisesOutDTO>> respuesta = comexConsultasBO.consultaPaises();
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void consultaPaisesError() throws DAOException{
		ConsultaPaisesResponse response = new ConsultaPaisesResponse();
		
		ObjectFactory paisFactory = new ObjectFactory();
		
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory
		respuestaFactory= new 
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory();
		response.setErrorInterface(respuestaFactory.createBaseResponseErrorInterface("Firma invalida"));
		response.setErrorSistema(respuestaFactory.createBaseResponseErrorSistema("Tipo de firma inválida"));
		response.setRegistros(paisFactory.createArrayOfPais(null));
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		when(comexConsultasDAO.consultaPaises()).thenReturn(response);
		
		Respuesta<List<ConsultaPaisesOutDTO>> respuesta = comexConsultasBO.consultaPaises();
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void consultaMonedasOk() throws DAOException{
		ConsultaMonedasResponse response = new ConsultaMonedasResponse();
		ObjectFactory monedaFactory = new ObjectFactory();
		
		Moneda moneda = new Moneda();
		moneda.setCodBCRAMoneda(monedaFactory.createMonedaCodBCRAMoneda("GBP"));
		moneda.setCodigoMoneda(monedaFactory.createMonedaCodigoMoneda("001"));
		moneda.setDescripcionMoneda(monedaFactory.createMonedaDescripcionMoneda("LIBRAS ESTERLINAS"));
		
		Moneda moneda2 = new Moneda();
		moneda2.setCodBCRAMoneda(monedaFactory.createMonedaCodBCRAMoneda("USD"));
		moneda2.setCodigoMoneda(monedaFactory.createMonedaCodigoMoneda("002"));
		moneda2.setDescripcionMoneda(monedaFactory.createMonedaDescripcionMoneda("DOLARES"));
		
		ArrayOfMoneda arrayMoneda = new ArrayOfMoneda();
		arrayMoneda.getMoneda().add(moneda);
		arrayMoneda.getMoneda().add(moneda2);
		
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory
		respuestaFactory= new 
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory();
		response.setErrorInterface(respuestaFactory.createBaseResponseErrorInterface(null));
		response.setErrorSistema(respuestaFactory.createBaseResponseErrorSistema(null));
		response.setRegistros(monedaFactory.createArrayOfMoneda(arrayMoneda));
		
		when(comexConsultasDAO.consultaMonedas()).thenReturn(response);
		
		Respuesta<List<ConsultaMonedaOutDTO>> respuesta = comexConsultasBO.consultaMonedas();
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void consultaPaisesDAOException() throws DAOException{
		ConsultaPaisesResponse response = new ConsultaPaisesResponse();
		
		ObjectFactory paisFactory = new ObjectFactory();
		
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory
		respuestaFactory= new 
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory();
		response.setErrorInterface(respuestaFactory.createBaseResponseErrorInterface("Firma invalida"));
		response.setErrorSistema(respuestaFactory.createBaseResponseErrorSistema("Tipo de firma inválida"));
		response.setRegistros(paisFactory.createArrayOfPais(null));
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		when(comexConsultasDAO.consultaPaises()).thenThrow(new DAOException());
		
		Respuesta<List<ConsultaPaisesOutDTO>> respuesta = comexConsultasBO.consultaPaises();
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void consultaMonedasError() throws DAOException{
		ConsultaMonedasResponse response = new ConsultaMonedasResponse();
		ObjectFactory monedaFactory = new ObjectFactory();
				
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory
		respuestaFactory= new 
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory();
		response.setErrorInterface(respuestaFactory.createBaseResponseErrorInterface("Firma invalida"));
		response.setErrorSistema(respuestaFactory.createBaseResponseErrorSistema("Tipo de firma inválida"));
		response.setRegistros(monedaFactory.createArrayOfMoneda(null));
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		when(comexConsultasDAO.consultaMonedas()).thenReturn(response);

		Respuesta<List<ConsultaMonedaOutDTO>> respuesta = comexConsultasBO.consultaMonedas();
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	
	@Test
	public void consultaMonedasDAOException() throws DAOException{
		ConsultaMonedasResponse response = new ConsultaMonedasResponse();
		ObjectFactory monedaFactory = new ObjectFactory();
				
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory
		respuestaFactory= new 
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.datacontract.response.ObjectFactory();
		response.setErrorInterface(respuestaFactory.createBaseResponseErrorInterface("Firma invalida"));
		response.setErrorSistema(respuestaFactory.createBaseResponseErrorSistema("Tipo de firma inválida"));
		response.setRegistros(monedaFactory.createArrayOfMoneda(null));
		
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		when(comexConsultasDAO.consultaMonedas()).thenThrow(new DAOException());

		Respuesta<List<ConsultaMonedaOutDTO>> respuesta = comexConsultasBO.consultaMonedas();
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	 
	
	@Test
	public void verificarArchivo() {
		AdjuntarArchivosDTO adjuntarArchivosInDto = new AdjuntarArchivosDTO();
		Respuesta<Boolean> response = new Respuesta<Boolean>();
		response.setEstadoRespuesta(EstadoRespuesta.OK);
		response.setRespuesta(Boolean.TRUE);
		when(comexConsultasDAO.verificarVirusArchivoFs(Matchers.any(AdjuntarArchivosInEntity.class))).thenReturn(response);
		
		Respuesta<Boolean> respuesta = comexConsultasBO.verificarArchivo(adjuntarArchivosInDto);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK); 
	}
	
}
