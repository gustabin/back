package ar.com.santanderrio.obp.servicios.comex.transfext.bo;

import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfConceptosTipo;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfDetalleTrfImagen;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfOperacion;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ArrayOfRechazo;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ConceptosTipo;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.DetalleTrfImagen;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ObjectFactory;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.Operacion;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.Rechazo;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.BorrarDocResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.CargaDocResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConceptosPorTipoResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaDetalleTrfOBPResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ConsultaOperacionesResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ProcesarTransferenciaOBPResponse;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ArrayOfBanco;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.Banco;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.response.consultas.ConsultaBancosResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comex.transfext.bo.impl.ComexCanalesBOImpl;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexCanalesDAO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dao.ComexConsultasDAO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConceptoPorTipoOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaDetalleTrfOBPInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaDetalleTrfOBPOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaOperacionesInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaOperacionesOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ProcesarTransferenciaComexInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ProcesarTransferenciaComexOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.AdjuntarArchivosInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaBancosInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaConceptosPorTipoInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaDetalleTrfOBPInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ConsultaOperacionesInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.entities.ProcesarTransferenciaComexInEntity;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaOperacionesView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ReporteView;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class ComexCanalesBOTest {

	/** ComexCanales WS DAO */
	@Mock
	private ComexCanalesDAO comexCanalesDAO;

	/** ComexConsultas WS DAO */
	@Mock
	private ComexConsultasDAO comexConsultasDAO;

	/** The respuesta factory. */
	@Spy
	@InjectMocks
	private RespuestaFactory respuestaFactory;

	/** The ComexCanales BO. */
	@InjectMocks
	private ComexCanalesBO comexCanalesBO = new ComexCanalesBOImpl();

	/** The sesion cliente. */
	@Mock
	private SesionCliente sesionCliente;

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
	public void consultaDetalleTrfOBPOk() throws DAOException {
		ConsultaDetalleTrfOBPInDTO detalleInDTO = new ConsultaDetalleTrfOBPInDTO();
		detalleInDTO.setNroTransferencia(new Long(402990));

		ConsultaDetalleTrfOBPResponse response = new ConsultaDetalleTrfOBPResponse();

		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObjectFactory
		factory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObjectFactory();

		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory
		errorFactory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory();

		ObjectFactory imagenFactory = new ObjectFactory();
		ArrayOfDetalleTrfImagen arrayImagenes = new ArrayOfDetalleTrfImagen();
		DetalleTrfImagen detalleImagen = new DetalleTrfImagen();
		ArrayOfRechazo arrayRechazo = new ArrayOfRechazo();
 
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.domain.ObjectFactory
		rechazoFactory = new ObjectFactory();
		Rechazo rechazo = new Rechazo();
		rechazo.setMotivoRechazo(rechazoFactory.createRechazoMotivoRechazo(""));
		rechazo.setObservaciones(rechazoFactory.createRechazoObservaciones(""));
		arrayRechazo.getRechazo().add(rechazo);
		
		
		detalleImagen.setIdImagen(imagenFactory.createDetalleTrfImagenIdImagen(1));
		detalleImagen.setNombreArchivo(imagenFactory.createDetalleTrfImagenNombreArchivo("pabloPrueba"));
		detalleImagen.setTipoArchivo(imagenFactory.createDetalleTrfImagenTipoArchivo(".txt"));
		arrayImagenes.getDetalleTrfImagen().add(detalleImagen);	
		response.setRechazos(factory.createConsultaDetalleTrfOBPResponseRechazos(arrayRechazo));
		response.setAceptaDdjj(factory.createConsultaDetalleTrfOBPResponseAceptaDdjj(1));
		response.setBancoBeneficiario(factory.createConsultaDetalleTrfOBPResponseBancoBeneficiario("CITIUS33GRP"));
		response.setBancoIntermediario(factory.createConsultaDetalleTrfOBPResponseBancoIntermediario("CITIUS33CTA"));
		response.setBeneficiario(factory.createConsultaDetalleTrfOBPResponseBeneficiario("Prueba Guardar 3"));
		response.setBeneficiarioDomicilio(factory.createConsultaDetalleTrfOBPResponseBeneficiarioDomicilio("calle 99, localidad, Argentina"));
		response.setBeneficiarioPais(factory.createConsultaDetalleTrfOBPResponseBeneficiarioPais("AR"));
		response.setCodigoConcepto(factory.createConsultaDetalleTrfOBPResponseCodigoConcepto("I07"));
		response.setConcepto(factory.createConsultaDetalleTrfOBPResponseConcepto("I07 TRANSFERENCIAS PERSONALES"));
		response.setCtaAltair(factory.createConsultaDetalleTrfOBPResponseCtaAltair("000-02-3580475"));
		response.setCtaDebito(factory.createConsultaDetalleTrfOBPResponseCtaDebito("CLIE00013580475"));
		response.setCuentaBeneficiario(factory.createConsultaDetalleTrfOBPResponseCuentaBeneficiario("3423343434"));
		response.setCuentaBcoIntermediario(factory.createConsultaDetalleTrfOBPResponseCuentaBcoIntermediario("1234567"));
		response.setCuentaDebito(factory.createConsultaDetalleTrfOBPResponseCuentaDebito("CLIE00013580475"));
		response.setEstadoTransferencia(factory.createConsultaDetalleTrfOBPResponseEstadoTransferencia("G"));
		response.setGastos(factory.createConsultaDetalleTrfOBPResponseGastos(new BigDecimal(1)));
		response.setIdCT(factory.createConsultaDetalleTrfOBPResponseIdCT(new Long(0)));
		response.setIdConcepto(factory.createConsultaDetalleTrfOBPResponseIdConcepto(1387));
		response.setImagenes(factory.createConsultaDetalleTrfOBPResponseImagenes(arrayImagenes));
		response.setImporteTransferencia(factory.createConsultaDetalleTrfOBPResponseImporteTransferencia(new BigDecimal(999)));
		response.setInstRecibido(factory.createConsultaDetalleTrfOBPResponseInstRecibido("07"));
		response.setInstVendido(factory.createConsultaDetalleTrfOBPResponseInstVendido("02"));
		response.setMoneda(factory.createConsultaDetalleTrfOBPResponseMoneda("080"));
		response.setNroDocumentoCliente(factory.createConsultaDetalleTrfOBPResponseNroDocumentoCliente("00017138279"));
		response.setNroSolicitud(factory.createConsultaDetalleTrfOBPResponseNroSolicitud("402990"));
		response.setTipoDocumentoCliente(factory.createConsultaDetalleTrfOBPResponseTipoDocumentoCliente("DNI"));
		response.setTipoTransferencia(factory.createConsultaDetalleTrfOBPResponseTipoTransferencia((short) 5));
		response.setTrfVigente(factory.createConsultaDetalleTrfOBPResponseTrfVigente("S"));
		response.setVinculo(factory.createConsultaDetalleTrfOBPResponseVinculo("Hermano"));
		response.setErrorInterface(errorFactory.createBaseResponseErrorInterface(null));
		response.setErrorSistema(errorFactory.createBaseResponseErrorSistema(null));

		//		response.setImagenes(factory.);

		ConsultaBancosResponse responseBanco = new ConsultaBancosResponse();

		Banco banco = new Banco();
		ArrayOfBanco arrayBanco = new ArrayOfBanco();
		ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ObjectFactory 
		bancoFactory = new ar.com.santanderrio.obp.generated.webservices.comex.consultas.domain.ObjectFactory();
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
		responseBanco.setErrorInterface(respuestaFactory.createBaseResponseErrorInterface(null));
		responseBanco.setErrorSistema(respuestaFactory.createBaseResponseErrorSistema(null));
		responseBanco.setCantReg(new BigDecimal(2));
		responseBanco.setRegistros(bancoFactory.createArrayOfBanco(arrayBanco));

		//Se genera cuenta y cliente para prueba
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		Cuenta cuenta = new Cuenta();
		cuenta.setNroSucursal("000");
		cuenta.setTipoCuenta("02");
		cuenta.setNroCuentaProducto("003580475");
		cuenta.setTipoCuentaEnum(TipoCuenta.fromCodigo(1));
		cuentas.add(cuenta);

		Cliente cliente = new Cliente();
		cliente.setNombre("Nombre");
		cliente.setApellido1("Prueba");
		cliente.setApellido2("Prueba 2");
		cliente.setNup("12345678");
		cliente.setCuentas(cuentas);
		when(sesionCliente.getCliente()).thenReturn(cliente);
		when(comexCanalesDAO.consultaDetalleTrf(Matchers.any(ConsultaDetalleTrfOBPInEntity.class))).thenReturn(response);
		when(comexConsultasDAO.consultaBancos(Matchers.any(ConsultaBancosInEntity.class))).thenReturn(responseBanco);

		ConsultaOperacionesView operacion = new ConsultaOperacionesView();
		operacion.setCuentaCliente("000-02-3580475");
		Respuesta<ConsultaDetalleTrfOBPOutDTO> respuesta = comexCanalesBO.consultaDetalleTrf(detalleInDTO, operacion);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	@Test
	public void consultaDetalleTrfOBPErrorDAOException() throws DAOException {
		ConsultaDetalleTrfOBPInDTO detalleInDTO = new ConsultaDetalleTrfOBPInDTO();
		detalleInDTO.setNroTransferencia(new Long(402990));


		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");

		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		when(comexCanalesDAO.consultaDetalleTrf(Matchers.any(ConsultaDetalleTrfOBPInEntity.class))).thenThrow(new DAOException());

		ConsultaOperacionesView operacion = new ConsultaOperacionesView();
		Respuesta<ConsultaDetalleTrfOBPOutDTO> respuesta = comexCanalesBO.consultaDetalleTrf(detalleInDTO, operacion);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	@Test
	public void procesarTransferenciaOBPOk() throws DAOException{
		ProcesarTransferenciaComexInDTO procesarInDTO = generarProcesarTransferenciaComexInDTO();


		ProcesarTransferenciaOBPResponse response = new ProcesarTransferenciaOBPResponse();

		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObjectFactory
		factory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObjectFactory();

		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory
		errorFactory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory();

		response.setNroTransf(factory.createProcesarTransferenciaOBPResponseNroTransf(402890));
		response.setErrorInterface(errorFactory.createBaseResponseErrorInterface(null));
		response.setErrorSistema(errorFactory.createBaseResponseErrorSistema(null));

		when(comexCanalesDAO.procesarTransferenciaComex(Matchers.any(ProcesarTransferenciaComexInEntity.class))).thenReturn(response);


		Respuesta<ProcesarTransferenciaComexOutDTO> respuesta = comexCanalesBO.procesarTransferenciaComex(procesarInDTO);
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void procesarTransferenciaOBPErroServicio() throws DAOException{
		ProcesarTransferenciaComexInDTO procesarInDTO = generarProcesarTransferenciaComexInDTO();


		ProcesarTransferenciaOBPResponse response = new ProcesarTransferenciaOBPResponse();

		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObjectFactory
		factory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObjectFactory();

		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory
		errorFactory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory();

		response.setNroTransf(factory.createProcesarTransferenciaOBPResponseNroTransf(null));
		response.setErrorInterface(errorFactory.createBaseResponseErrorInterface("Firma invalida"));
		response.setErrorSistema(errorFactory.createBaseResponseErrorSistema("Tipo de firma inválida"));

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");

		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		when(comexCanalesDAO.procesarTransferenciaComex(Matchers.any(ProcesarTransferenciaComexInEntity.class))).thenReturn(response);
		Respuesta<ProcesarTransferenciaComexOutDTO> respuesta = comexCanalesBO.procesarTransferenciaComex(procesarInDTO);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void procesarTransferenciaOBPErrorDAOException() throws DAOException{
		ProcesarTransferenciaComexInDTO procesarInDTO = generarProcesarTransferenciaComexInDTO();

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");

		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		when(comexCanalesDAO.procesarTransferenciaComex(Matchers.any(ProcesarTransferenciaComexInEntity.class))).thenThrow(new DAOException());
		Respuesta<ProcesarTransferenciaComexOutDTO> respuesta = comexCanalesBO.procesarTransferenciaComex(procesarInDTO);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	@Test
	public void consultaOperacionesOk() throws DAOException {
		ConsultaOperacionesResponse consultaOperaciones = new ConsultaOperacionesResponse();
		ArrayOfOperacion arrayOperacion = new ArrayOfOperacion();
		Operacion opEstadoA = new Operacion();
		ObjectFactory operacionFactory = new ObjectFactory();
		opEstadoA.setBeneficiario(operacionFactory.createOperacionBeneficiario("PRUEBA 3"));
		opEstadoA.setCodEstado(operacionFactory.createOperacionCodEstado("A"));
		opEstadoA.setCodMoneda(operacionFactory.createOperacionCodMoneda(null));
		opEstadoA.setCuentaCliente(operacionFactory.createOperacionCuentaCliente("000-02-3580475"));
		opEstadoA.setFechaOp(operacionFactory.createOperacionFechaOp("17/04/2018"));
		opEstadoA.setIdCt(operacionFactory.createOperacionIdCt(new Long(0)));
		opEstadoA.setMonto(operacionFactory.createOperacionMonto(null));
		opEstadoA.setNroForm(operacionFactory.createOperacionNroForm(new Long(402808)));
		opEstadoA.setTipoOp(operacionFactory.createOperacionTipoOp(new Short((short) 5)));
		opEstadoA.setVigente(operacionFactory.createOperacionVigente("S"));

		Operacion opEstadoH = new Operacion();
		opEstadoH.setBeneficiario(operacionFactory.createOperacionBeneficiario("PRUEBA 3"));
		opEstadoH.setCodEstado(operacionFactory.createOperacionCodEstado("H"));
		opEstadoH.setCodMoneda(operacionFactory.createOperacionCodMoneda(null));
		opEstadoH.setCuentaCliente(operacionFactory.createOperacionCuentaCliente("000-02-3580475"));
		opEstadoH.setFechaOp(operacionFactory.createOperacionFechaOp("17/04/2018"));
		opEstadoH.setIdCt(operacionFactory.createOperacionIdCt(new Long(0)));
		opEstadoH.setMonto(operacionFactory.createOperacionMonto(new BigDecimal(10000)));
		opEstadoH.setNroForm(operacionFactory.createOperacionNroForm(new Long(402807)));
		opEstadoH.setTipoOp(operacionFactory.createOperacionTipoOp(new Short((short) 5)));
		opEstadoH.setVigente(operacionFactory.createOperacionVigente("S"));

		Operacion opEstadoL = new Operacion();
		opEstadoL.setBeneficiario(operacionFactory.createOperacionBeneficiario(null));
		opEstadoL.setCodEstado(operacionFactory.createOperacionCodEstado("L"));
		opEstadoL.setCodMoneda(operacionFactory.createOperacionCodMoneda(null));
		opEstadoL.setCuentaCliente(operacionFactory.createOperacionCuentaCliente(null));
		opEstadoL.setFechaOp(operacionFactory.createOperacionFechaOp("08/05/2018"));
		opEstadoL.setIdCt(operacionFactory.createOperacionIdCt(new Long(0)));
		opEstadoL.setMonto(operacionFactory.createOperacionMonto(null));
		opEstadoL.setNroForm(operacionFactory.createOperacionNroForm(new Long(402823)));
		opEstadoL.setTipoOp(operacionFactory.createOperacionTipoOp(null));
		opEstadoL.setVigente(operacionFactory.createOperacionVigente("S"));

		Operacion opEstadoG = new Operacion();
		opEstadoG.setBeneficiario(operacionFactory.createOperacionBeneficiario(null));
		opEstadoG.setCodEstado(operacionFactory.createOperacionCodEstado("G"));
		opEstadoG.setCodMoneda(operacionFactory.createOperacionCodMoneda("002"));
		opEstadoG.setCuentaCliente(operacionFactory.createOperacionCuentaCliente("000-02-3580475"));
		opEstadoG.setFechaOp(operacionFactory.createOperacionFechaOp("17/04/2018"));
		opEstadoG.setIdCt(operacionFactory.createOperacionIdCt(new Long(0)));
		opEstadoG.setMonto(operacionFactory.createOperacionMonto(new BigDecimal(10000)));
		opEstadoG.setNroForm(operacionFactory.createOperacionNroForm(new Long(402804)));
		opEstadoG.setTipoOp(operacionFactory.createOperacionTipoOp(null));
		opEstadoG.setVigente(operacionFactory.createOperacionVigente("S"));

		Operacion opEstadoK = new Operacion();
		opEstadoK.setBeneficiario(operacionFactory.createOperacionBeneficiario(null));
		opEstadoK.setCodEstado(operacionFactory.createOperacionCodEstado("K"));
		opEstadoK.setCodMoneda(operacionFactory.createOperacionCodMoneda("002"));
		opEstadoK.setCuentaCliente(operacionFactory.createOperacionCuentaCliente("033-02-3612532"));
		opEstadoK.setFechaOp(operacionFactory.createOperacionFechaOp("09/05/2018"));
		opEstadoK.setIdCt(operacionFactory.createOperacionIdCt(new Long(0)));
		opEstadoK.setMonto(operacionFactory.createOperacionMonto(new BigDecimal(5)));
		opEstadoK.setNroForm(operacionFactory.createOperacionNroForm(new Long(402833)));
		opEstadoK.setTipoOp(operacionFactory.createOperacionTipoOp(new Short((short) 5)));
		opEstadoK.setVigente(operacionFactory.createOperacionVigente("S"));

		Operacion opEstadoJ = new Operacion();
		opEstadoJ.setBeneficiario(operacionFactory.createOperacionBeneficiario("La Prueba OBP"));
		opEstadoJ.setCodEstado(operacionFactory.createOperacionCodEstado("J"));
		opEstadoJ.setCodMoneda(operacionFactory.createOperacionCodMoneda("002"));
		opEstadoJ.setCuentaCliente(operacionFactory.createOperacionCuentaCliente("000-02-3580475"));
		opEstadoJ.setFechaOp(operacionFactory.createOperacionFechaOp("13/06/2018"));
		opEstadoJ.setIdCt(operacionFactory.createOperacionIdCt(new Long(0)));
		opEstadoJ.setMonto(operacionFactory.createOperacionMonto(new BigDecimal(5)));
		opEstadoJ.setNroForm(operacionFactory.createOperacionNroForm(new Long(402890)));
		opEstadoJ.setTipoOp(operacionFactory.createOperacionTipoOp(new Short((short) 5)));
		opEstadoJ.setVigente(operacionFactory.createOperacionVigente("S"));


		arrayOperacion.getOperacion().add(opEstadoA);
		arrayOperacion.getOperacion().add(opEstadoH);
		arrayOperacion.getOperacion().add(opEstadoL);
		arrayOperacion.getOperacion().add(opEstadoG);
		arrayOperacion.getOperacion().add(opEstadoK);
		arrayOperacion.getOperacion().add(opEstadoJ);



		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObjectFactory 
		factory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObjectFactory();

		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory
		errorFactory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory();

		
		
		consultaOperaciones.setErrorInterface(errorFactory.createBaseResponseErrorInterface(null));
		consultaOperaciones.setErrorSistema(errorFactory.createBaseResponseErrorSistema(null));
		consultaOperaciones.setRegistros(factory.createConsultaOperacionesResponseRegistros(arrayOperacion));
		consultaOperaciones.setCantReg(new BigDecimal(1));
		when(comexCanalesDAO.consultaOperaciones(Matchers.any(ConsultaOperacionesInEntity.class))).thenReturn(consultaOperaciones);

		ConsultaOperacionesInDTO consultaOperacionesInDTO = new ConsultaOperacionesInDTO();

		Respuesta<ConsultaOperacionesOutDTO> respuesta = comexCanalesBO.consultaOperaciones(consultaOperacionesInDTO);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void consultaOperacionesErrorServicio() throws DAOException {
		ConsultaOperacionesResponse consultaOperaciones = new ConsultaOperacionesResponse();


		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.
		ObjectFactory factory = new 
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObjectFactory();
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory
		errorFactory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory();

		consultaOperaciones.setErrorInterface(errorFactory.createBaseResponseErrorInterface("Firma invalida"));
		consultaOperaciones.setErrorSistema(errorFactory.createBaseResponseErrorSistema("Formato de firma inválido"));
		consultaOperaciones.setCantReg(new BigDecimal(1));
		consultaOperaciones.setRegistros(factory.createConsultaOperacionesResponseRegistros(null));

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");

		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		when(comexCanalesDAO.consultaOperaciones(Matchers.any(ConsultaOperacionesInEntity.class))).thenReturn(consultaOperaciones);

		ConsultaOperacionesInDTO consultaOperacionesInDTO = new ConsultaOperacionesInDTO();

		Respuesta<ConsultaOperacionesOutDTO> respuesta = comexCanalesBO.consultaOperaciones(consultaOperacionesInDTO);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void consultaOperacionesErrorDAOException() throws DAOException {
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");

		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		when(comexCanalesDAO.consultaOperaciones(Matchers.any(ConsultaOperacionesInEntity.class))).thenThrow(new DAOException());

		ConsultaOperacionesInDTO consultaOperacionesInDTO = new ConsultaOperacionesInDTO();

		Respuesta<ConsultaOperacionesOutDTO> respuesta = comexCanalesBO.consultaOperaciones(consultaOperacionesInDTO);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}

	@Test
	public void consultaConceptoPorTipoOk() throws DAOException {
		ConceptosPorTipoResponse respuestaConceptos = new ConceptosPorTipoResponse();
		ArrayOfConceptosTipo arrayConceptos= new ArrayOfConceptosTipo();
		ConceptosTipo concepto = new ConceptosTipo();
		ObjectFactory conceptoFactory = new ObjectFactory();
		concepto.setConcepto(conceptoFactory.createConceptosTipoConcepto("I07 TRANSFERENCIAS PERSONALES"));
		concepto.setIdConcepto(conceptoFactory.createConceptosTipoIdConcepto(new BigDecimal(1387)));

		ConceptosTipo concepto2 = new ConceptosTipo();
		concepto2.setConcepto(conceptoFactory.createConceptosTipoConcepto("I08 OTRAS TRANSFERENCIAS CORRIENTES"));
		concepto2.setIdConcepto(conceptoFactory.createConceptosTipoIdConcepto(new BigDecimal(1388)));

		arrayConceptos.getConceptosTipo().add(concepto);
		arrayConceptos.getConceptosTipo().add(concepto2);

		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.
		ObjectFactory factory = new 
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObjectFactory();
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory
		errorFactory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory();

		respuestaConceptos.setErrorInterface(errorFactory.createBaseResponseErrorInterface(null));
		respuestaConceptos.setErrorSistema(errorFactory.createBaseResponseErrorSistema(null));
		respuestaConceptos.setRegistros(factory.createConceptosPorTipoResponseRegistros(arrayConceptos));
		
		ConsultaConceptosPorTipoInEntity consultaConceptosPorTipoInEntity = new ConsultaConceptosPorTipoInEntity();
		consultaConceptosPorTipoInEntity.setProducto("12");
		consultaConceptosPorTipoInEntity.setTipoConcepto("5");

		when(comexCanalesDAO.consultaConceptosPorTipo(Matchers.any(ConsultaConceptosPorTipoInEntity.class))).thenReturn(respuestaConceptos);

		Respuesta<List<ConceptoPorTipoOutDTO>> respuesta = comexCanalesBO.consultaConceptoPorTipo(consultaConceptosPorTipoInEntity);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}

	@Test
	public void consultaConceptoPorTipoErrorDAO() throws DAOException {
		ConceptosPorTipoResponse respuestaConceptos = new ConceptosPorTipoResponse();


		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.
		ObjectFactory factory = new 
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObjectFactory();
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory
		errorFactory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory();

		respuestaConceptos.setErrorInterface(errorFactory.createBaseResponseErrorInterface("Firma invalida"));
		respuestaConceptos.setErrorSistema(errorFactory.createBaseResponseErrorSistema("Formato de firma inválido"));
		respuestaConceptos.setRegistros(factory.createConceptosPorTipoResponseRegistros(null));

		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("PRUEBA");
		
		ConsultaConceptosPorTipoInEntity consultaConceptosPorTipoInEntity = new ConsultaConceptosPorTipoInEntity();
		consultaConceptosPorTipoInEntity.setProducto("12");
		consultaConceptosPorTipoInEntity.setTipoConcepto("5");

		when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS)).thenReturn(mensaje);
		when(comexCanalesDAO.consultaConceptosPorTipo(Matchers.any(ConsultaConceptosPorTipoInEntity.class))).thenThrow(new DAOException());

		Respuesta<List<ConceptoPorTipoOutDTO>> respuesta = comexCanalesBO.consultaConceptoPorTipo(consultaConceptosPorTipoInEntity);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
	}
	
	@Test
	public void adjuntarArchivosOK() throws UnsupportedEncodingException, DAOException{
		
		AdjuntarArchivosDTO adjuntarArchivosDTO = new AdjuntarArchivosDTO();
		adjuntarArchivosDTO.setArchivo(new ReporteView());
		adjuntarArchivosDTO.setNroTransferencia(420891);
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.
		ObjectFactory factory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObjectFactory();
		
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory
		errorFactory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory();
		
		CargaDocResponse response = new CargaDocResponse();

		response.setErrorInterface(errorFactory.createBaseResponseErrorInterface(null));
		response.setErrorSistema(errorFactory.createBaseResponseErrorSistema(null));
		response.setHoja(factory.createCargaDocResponseHoja(1));
		response.setNroTransferencia(factory.createCargaDocResponseNroTransferencia(403082));
		
		when(comexCanalesDAO.adjuntarArchivos(Matchers.any(AdjuntarArchivosInEntity.class))).thenReturn(response);

		Respuesta<Integer> respuesta = comexCanalesBO.adjuntarArchivos(adjuntarArchivosDTO);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void adjuntarArchivosExceptionOK() throws UnsupportedEncodingException, DAOException{
		ReporteView archivo = new ReporteView();
		archivo.setNombre("pabloPrueba.txt");
		AdjuntarArchivosDTO adjuntarArchivosDTO = new AdjuntarArchivosDTO();
		adjuntarArchivosDTO.setArchivo(archivo);
		adjuntarArchivosDTO.setNroTransferencia(420891);
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.
		ObjectFactory factory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.response.canales.ObjectFactory();
		
		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory
		errorFactory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory();
		
		CargaDocResponse response = new CargaDocResponse();

		response.setErrorInterface(errorFactory.createBaseResponseErrorInterface(null));
		response.setErrorSistema(errorFactory.createBaseResponseErrorSistema(null));
		response.setHoja(factory.createCargaDocResponseHoja(1));
		response.setNroTransferencia(factory.createCargaDocResponseNroTransferencia(403082));
		
		ConsultaDetalleTrfOBPResponse responseConsultaDetalle = new ConsultaDetalleTrfOBPResponse();
		ObjectFactory imagenFactory = new ObjectFactory();
		ArrayOfDetalleTrfImagen arrayImagenes = new ArrayOfDetalleTrfImagen();
		DetalleTrfImagen detalleImagen = new DetalleTrfImagen();
		
		detalleImagen.setIdImagen(imagenFactory.createDetalleTrfImagenIdImagen(1));
		detalleImagen.setNombreArchivo(imagenFactory.createDetalleTrfImagenNombreArchivo("pabloPrueba.txt"));
		detalleImagen.setTipoArchivo(imagenFactory.createDetalleTrfImagenTipoArchivo(".txt"));
		arrayImagenes.getDetalleTrfImagen().add(detalleImagen);
		responseConsultaDetalle.setImagenes(factory.createConsultaDetalleTrfOBPResponseImagenes(arrayImagenes));
		
		when(comexCanalesDAO.adjuntarArchivos(Matchers.any(AdjuntarArchivosInEntity.class))).thenThrow(new DAOException());
		when(comexCanalesDAO.consultaDetalleTrf(Matchers.any(ConsultaDetalleTrfOBPInEntity.class))).thenReturn(responseConsultaDetalle);
//		response = comexCanalesDAO.consultaDetalleTrf(consultaDetalleTrfOBPInEntity);

		Respuesta<Integer> respuesta = comexCanalesBO.adjuntarArchivos(adjuntarArchivosDTO);

		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	@Test
	public void borrarDoc() throws DAOException {
		AdjuntarArchivosDTO adjuntarArchivosInDTO = new AdjuntarArchivosDTO();
		BorrarDocResponse response = new BorrarDocResponse();

		ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory
		errorFactory = new ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.datacontract.response.ObjectFactory();
		
		response.setErrorInterface(errorFactory.createBaseResponseErrorInterface(null));
		response.setErrorSistema(errorFactory.createBaseResponseErrorSistema(null));
		when(comexCanalesDAO.borrarDoc(Matchers.any(AdjuntarArchivosInEntity.class))).thenReturn(response);
		
		Respuesta<Boolean> respuesta = comexCanalesBO.borrarDoc(adjuntarArchivosInDTO);
		
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
	}
	
	private ProcesarTransferenciaComexInDTO generarProcesarTransferenciaComexInDTO() {
		ProcesarTransferenciaComexInDTO procesarInDTO = new ProcesarTransferenciaComexInDTO();
		procesarInDTO.setAceptaDDJJ("1");
		procesarInDTO.setCodMoneda("002");
		procesarInDTO.setImporteTransferencia(new BigDecimal(500));
		procesarInDTO.setNombreBeneficiario("La Prueba OBP");
		procesarInDTO.setVinculo("Hermano");
		procesarInDTO.setDomicilio("calle 000, localidad, Argentina");
		procesarInDTO.setRazonSocial("ADRIANA CASANDRA BELLADONNA");
		procesarInDTO.setIdConcepto("1387");
		procesarInDTO.setGastoACargo("1");
		procesarInDTO.setBancoBeneficiario("CITIUS33GRP");
		procesarInDTO.setBancoIntermediario(null);
		procesarInDTO.setCuentaBcoIntermediario(null);
		procesarInDTO.setCuentaBeneficiario("453535334");
		procesarInDTO.setCuentaAltair("000-02-3580475");
		procesarInDTO.setCuentaDebito("CLIE00013580475");
		procesarInDTO.setVinculante(true);
		return procesarInDTO;
	}
}
