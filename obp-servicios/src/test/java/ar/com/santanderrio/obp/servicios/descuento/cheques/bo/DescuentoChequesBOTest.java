package ar.com.santanderrio.obp.servicios.descuento.cheques.bo;

import java.util.ArrayList;

import ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.ConsultaPaquetesDAO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.PaqueteEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.bo.impl.DescuentoChequesBOImpl;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.ClienteHabilitadoChequesDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.CodigosBancariosDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.ConsultaTasasIndicativasDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.DescuentoChequesDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.DetalleOperacionesPrecargadasDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.EliminarOperacionDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.EntidadesDAO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DatosCesionDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DatosCesionEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DescuentoChequesEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleHistorialOperacionesDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleOperacionesPrecargadasDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DetalleOperacionesPrecargadasEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.EliminarOperacionEntity;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.OperacionesPrecargadasDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.TasaIndicativa;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.TasasIndicativasDTO;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.TasasIndicativasEntity;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(MockitoJUnitRunner.class)
public class DescuentoChequesBOTest {

	@InjectMocks
	private DescuentoChequesBO descuentoChequesBO = new DescuentoChequesBOImpl();
	@Mock
	private TransaccionesControllerHomeBO transaccionesControllerHomeBO;
	@Mock
	private ClienteHabilitadoChequesDAO cesionCliente;

	@Mock
	private ConsultaTasasIndicativasDAO consultaTasas;

	@Mock
	private ConsultaPaquetesDAO consulta;

	@Mock
	private EliminarOperacionDAO eliminarOperacionDao;

	@Mock
	private DescuentoChequesDAO descuentoChequesDao;

	@Mock
	private DetalleOperacionesPrecargadasDAO detallePrecargadaDao;

	@Mock
	private CodigosBancariosDAO codigosDAO;

	@Mock
	private EntidadesDAO entidadesDAO;

	@Mock
	private MensajeBO mensajeBO;

	@Mock
	private LegalDAO legalDao;

	@Mock
	private LegalBO legalBO;

	@Mock
	private SesionParametros sesionParametros;
	
	@Mock
	private SesionCliente sesionCliente;
	
	@Mock
    private NUPDAO nupDao;

	@Spy
	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	@Test
	public void obtenerMontoCalificadoCesionErrorServicioTest() throws DAOException {
		Mockito.when(cesionCliente.obtenerHabilitadoCesion(Matchers.any(Cliente.class))).thenThrow(new DAOException());
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		boolean aplicaCesion = transaccionesControllerHomeBO.aplicaDescuentoCheques(new Cliente());
		Assert.assertFalse(aplicaCesion);
		Respuesta<DatosCesionDTO> res = descuentoChequesBO.obtenerMontoCalificadoCesion(new Cliente());
		Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
		Assert.assertEquals(TipoError.NO_CALIFICADO_CABECERA_DESCUENTOCHEQUES.getDescripcion(),
				res.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	@Test
	public void obtenerMontoCalificadoCesionErrorNoCalificadoTest() throws DAOException {
		DatosCesionEntity datosCesion = new DatosCesionEntity();
		datosCesion.setCalificadoS("P");
		datosCesion.setCodigoRetornoExtendido("000000000");
		Mockito.when(cesionCliente.obtenerHabilitadoCesion(Matchers.any(Cliente.class))).thenReturn(datosCesion);
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Respuesta<DatosCesionDTO> res = descuentoChequesBO.obtenerMontoCalificadoCesion(new Cliente());
		Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
		Assert.assertEquals(TipoError.NO_CALIFICADO_CABECERA_DESCUENTOCHEQUES.getDescripcion(),
				res.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	@Test
	public void obtenerMontoCalificadoCesionErrorImporteTest() throws DAOException {
		DatosCesionEntity datosCesion = new DatosCesionEntity();
		datosCesion.setCalificadoS("S");
		datosCesion.setLineaS("20");
		datosCesion.setCodigoRetornoExtendido("000000000");
		Mockito.when(cesionCliente.obtenerHabilitadoCesion(Matchers.any(Cliente.class))).thenReturn(datosCesion);
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		boolean aplicaCesion = transaccionesControllerHomeBO.aplicaDescuentoCheques(new Cliente());
		Assert.assertFalse(aplicaCesion);
		Respuesta<DatosCesionDTO> res = descuentoChequesBO.obtenerMontoCalificadoCesion(new Cliente());
		Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
		Assert.assertNull(datosCesion.getMontoDisponibleS());
	}

	@Test
	public void obtenerMontoCalificadoCesionOkTest() throws DAOException, ImporteConvertException {
		DatosCesionEntity datosCesion = new DatosCesionEntity();
		Cliente cliente = new Cliente();
		cliente.setDni("33333333");
		datosCesion.setCalificadoS("S");
		datosCesion.setMontoDisponibleS("000000001453100");
		datosCesion.setLineaS("000000002000000");
		datosCesion.setCodigoRetornoExtendido("000000000");
		Mockito.when(cesionCliente.obtenerHabilitadoCesion(Matchers.any(Cliente.class))).thenReturn(datosCesion);
		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Respuesta<DatosCesionDTO> res = descuentoChequesBO.obtenerMontoCalificadoCesion(new Cliente());
		Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
		Assert.assertEquals(ISBANStringUtils.convertirStrToBigDecimal("2000000", 2),ISBANStringUtils.convertirStrToBigDecimal(datosCesion.getLineaS(),2));
		Assert.assertEquals(ISBANStringUtils.convertirStrToBigDecimal("1453100", 2),
				ISBANStringUtils.convertirStrToBigDecimal(datosCesion.getMontoDisponibleS(),2));
	}

	@Test
	public void obtenerTasasIndicativasSinCuentasTest() throws DAOException {
		Cliente cliente = new Cliente();
		cliente.setCuentas(new ArrayList<Cuenta>());
		TasasIndicativasEntity tasas = new TasasIndicativasEntity();
		tasas.setCodigoRetornoExtendido("000");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Mockito.when(consultaTasas.obtenerTasasIndicativas(Matchers.anyString(), Matchers.any(Cliente.class)))
				.thenReturn(tasas);
		Respuesta<TasasIndicativasDTO> res = descuentoChequesBO.obtenerTasasIndicativas(cliente);
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
	}

	@Test
	public void obtenerTasasIndicativasSinCuentasUnicasTest() {
		Cliente cliente = obtenerCliente();
		cliente.getCuentas().get(0).setTipoCuentaSinUnificar("07");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Respuesta<TasasIndicativasDTO> res = descuentoChequesBO.obtenerTasasIndicativas(cliente);
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_TASAS_INDICATIVAS.getDescripcion(),
				res.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	@Test
	public void obtenerTasasIndicativasSinTasasTest() throws DAOException {
		Cliente cliente = obtenerCliente();
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		TasasIndicativasEntity tasas = new TasasIndicativasEntity();
		tasas.setCodigoRetornoExtendido("0100");
		ConsultaPaquetesOutEntity consultaPaqueteOut = new ConsultaPaquetesOutEntity();
		consultaPaqueteOut.setCodigoRetornoExtendido("000000");
		consultaPaqueteOut.setPaquetes(new ArrayList<PaqueteEntity>());
		PaqueteEntity paqueteEntity = new PaqueteEntity();
		paqueteEntity.setTipoCuenta("09");
		paqueteEntity.setSubProductoPaquete("2");
		consultaPaqueteOut.getPaquetes().add(paqueteEntity);
		Mockito.when(consulta.consultar(Matchers.any(ConsultaPaquetesInEntity.class))).thenReturn(consultaPaqueteOut);
		Mockito.when(consultaTasas.obtenerTasasIndicativas(Matchers.anyString(), Matchers.any(Cliente.class)))
				.thenReturn(tasas);
		Respuesta<TasasIndicativasDTO> res = descuentoChequesBO.obtenerTasasIndicativas(cliente);
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
	}

	@Test
	public void obtenerTasasIndicativasConTasasTest() throws DAOException {
		Cliente cliente = obtenerCliente();
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		ConsultaPaquetesOutEntity consultaPaqueteOut = new ConsultaPaquetesOutEntity();
		consultaPaqueteOut.setCodigoRetornoExtendido("000000");
		consultaPaqueteOut.setPaquetes(new ArrayList<PaqueteEntity>());
		PaqueteEntity paqueteEntity = new PaqueteEntity();
		paqueteEntity.setTipoCuenta("09");
		paqueteEntity.setSubProductoPaquete("0351");
		consultaPaqueteOut.getPaquetes().add(paqueteEntity);
		Mockito.when(consulta.consultar(Matchers.any(ConsultaPaquetesInEntity.class))).thenReturn(consultaPaqueteOut);
		TasasIndicativasEntity tasasIndicativasEntity = new TasasIndicativasEntity();
		tasasIndicativasEntity.setCodigoRetornoExtendido("000000000");
		tasasIndicativasEntity.setCantidadRegistros(1L);
		tasasIndicativasEntity.setTasaIndicativa(new ArrayList<TasaIndicativa>());
		TasaIndicativa tasaIndicativa = new TasaIndicativa();
		tasaIndicativa.setTasa("00000249020");
		tasaIndicativa.setPlazo("0030");
		tasasIndicativasEntity.getTasaIndicativa().add(tasaIndicativa);
		Mockito.when(sesionParametros.getSubproductoPaquete()).thenReturn("0351");
		Mockito.when(legalDao.obtenerLegal(Matchers.anyString())).thenReturn("lala");
		Mockito.when(consultaTasas.obtenerTasasIndicativas(Matchers.anyString(), Matchers.any(Cliente.class)))
				.thenReturn(tasasIndicativasEntity);
		Respuesta<TasasIndicativasDTO> res = descuentoChequesBO.obtenerTasasIndicativas(cliente);
		Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
	}

	@Test
	public void eliminarOperacionOK() throws DAOException {
		EliminarOperacionEntity value = new EliminarOperacionEntity();
		value.setCodigoRetornoExtendido("000000");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Mockito.when(eliminarOperacionDao.eliminarOperacion(Matchers.any(Cliente.class), Matchers.anyString()))
				.thenReturn(value);
		Respuesta<Void> res = descuentoChequesBO.eliminarOperacion(obtenerCliente(), "1234115");
		Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
	}

	@Test
	public void eliminarOperacionError() throws DAOException {
		EliminarOperacionEntity value = new EliminarOperacionEntity();
		value.setCodigoRetornoExtendido("012213");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Mockito.when(eliminarOperacionDao.eliminarOperacion(Matchers.any(Cliente.class), Matchers.anyString()))
				.thenReturn(value);
		Respuesta<Void> res = descuentoChequesBO.eliminarOperacion(obtenerCliente(), "1234115");
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
	}

	@Test
	public void eliminarOperacionDAOException() throws DAOException {
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Mockito.when(eliminarOperacionDao.eliminarOperacion(Matchers.any(Cliente.class), Matchers.anyString()))
				.thenThrow(new DAOException());
		Respuesta<Void> res = descuentoChequesBO.eliminarOperacion(obtenerCliente(), "1234115");
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
	}

	@Test
	public void obtenerOperacionesPrecargadasOkTest() throws JsonSyntaxException, DAOException {
		// Obtengo el json de la respuesta real del dao para obtener una
		// respuesta ok como si viniera del servicio
		Gson gson = new Gson();
		String jsonResDao = "{\"headerTrama\":\"200000000000P04HTML0009900010303GSME08  ********00543346000000072017121311321700000000IBF0055U000000000000CNSLISTRA_1000000            03682408    00        00 011319268201712131132040000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH\",\"codigoRetornoExtendido\":\"00000000\",\"tieneRellamada\":\"S\",\"cantidadRegistros\":50,\"operaciones\":[{\"nroTramite\":\"0000130199\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000005056\",\"importeBruto\":\"000000000005100\",\"cantidadCheques\":\"02\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-12-06\",\"fechaRecalculo\":\"2017-12-07\",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-12-07\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130198\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000002479\",\"importeBruto\":\"000000000002500\",\"cantidadCheques\":\"02\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-30\",\"fechaRecalculo\":\"2017-12-07\",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-12-07\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130196\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000005380\",\"importeBruto\":\"000000000005400\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-30\",\"fechaRecalculo\":\"2017-11-30\",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-30\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130195\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000003189\",\"importeBruto\":\"000000000003200\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-30\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-30\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130194\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000498\",\"importeBruto\":\"000000000000500\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-29\",\"fechaRecalculo\":\"2017-11-30\",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-30\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130190\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000001195\",\"importeBruto\":\"000000000001200\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-29\",\"fechaRecalculo\":\"2017-11-29\",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-29\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130188\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000001101\",\"importeBruto\":\"000000000001105\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-27\",\"fechaRecalculo\":\"2017-11-30\",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-30\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130187\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000994\",\"importeBruto\":\"000000000001000\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-27\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-27\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130182\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000110797\",\"importeBruto\":\"000000000111100\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130180\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000002127\",\"importeBruto\":\"000000000002133\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130179\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000033243\",\"importeBruto\":\"000000000033333\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130178\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000032102\",\"importeBruto\":\"000000000032190\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130177\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000021621\",\"importeBruto\":\"000000000021680\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130176\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000043481\",\"importeBruto\":\"000000000043600\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130175\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000008783\",\"importeBruto\":\"000000000008800\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130174\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000010102\",\"importeBruto\":\"000000000010130\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130173\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000998\",\"importeBruto\":\"000000000001000\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130172\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000044378\",\"importeBruto\":\"000000000044499\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130164\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000044180\",\"importeBruto\":\"000000000044300\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130162\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000002\",\"importeBruto\":\"000000000000002\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130160\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000001\",\"importeBruto\":\"000000000000001\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130159\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000001\",\"importeBruto\":\"000000000000001\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130158\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000001\",\"importeBruto\":\"000000000000001\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130157\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000001\",\"importeBruto\":\"000000000000001\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130156\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000003989\",\"importeBruto\":\"000000000004000\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130154\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000003989\",\"importeBruto\":\"000000000004000\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130146\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000999\",\"importeBruto\":\"000000000001000\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130145\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000998\",\"importeBruto\":\"000000000001000\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130142\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000349\",\"importeBruto\":\"000000000000350\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130140\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000299\",\"importeBruto\":\"000000000000300\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130134\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000050\",\"importeBruto\":\"000000000000050\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130133\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000212778\",\"importeBruto\":\"000000000213166\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130131\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000021361\",\"importeBruto\":\"000000000021400\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130130\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000170\",\"importeBruto\":\"000000000000170\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130129\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000120\",\"importeBruto\":\"000000000000120\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130128\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000021294\",\"importeBruto\":\"000000000021333\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130127\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000309\",\"importeBruto\":\"000000000000310\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130126\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000042032\",\"importeBruto\":\"000000000042109\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130125\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000051927\",\"importeBruto\":\"000000000052021\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130124\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000439\",\"importeBruto\":\"000000000000440\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130123\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000085244\",\"importeBruto\":\"000000000085400\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130122\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000539\",\"importeBruto\":\"000000000000540\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130121\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000032042\",\"importeBruto\":\"000000000032100\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130120\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000449\",\"importeBruto\":\"000000000000450\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130119\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000090734\",\"importeBruto\":\"000000000090900\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130118\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000304\",\"importeBruto\":\"000000000000305\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130117\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000099718\",\"importeBruto\":\"000000000099900\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130116\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000299\",\"importeBruto\":\"000000000000300\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130115\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000077488\",\"importeBruto\":\"000000000077700\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"},{\"nroTramite\":\"0000130114\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000000200\",\"importeBruto\":\"000000000000200\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"03\",\"descripcionEstado\":\"PRECARGA                                          \",\"idUltimaModificacion\":\"HOMEBANK\",\"fechaAlta\":\"2017-11-21\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"INNAMORATO                    \",\"nombreCliente\":\"BAUDILIO MANSSUR              \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00022792232\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2017-11-21\",\"canal\":\"04\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"}]}";
		Mockito.when(descuentoChequesDao.obtenerOperaciones(Matchers.anyString(), Matchers.any(Cliente.class),
				Matchers.anyBoolean(), Matchers.anyString()))
				.thenReturn(gson.fromJson(jsonResDao, DescuentoChequesEntity.class));
		Respuesta<OperacionesPrecargadasDTO> res = descuentoChequesBO.obtenerOperacionesPrecargadas(obtenerCliente(),
				"");
		Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
	}

	@Test
	public void obtenerOperacionesPrecargadasErrorConRellamadoTest() throws JsonSyntaxException, DAOException {
		Gson gson = new Gson();
		String jsonResDao = "{\"codigoRetornoExtendido\":\"10000091\",\"operaciones\":[]}";
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Mockito.when(descuentoChequesDao.obtenerOperaciones(Matchers.anyString(), Matchers.any(Cliente.class),
				Matchers.anyBoolean(), Matchers.anyString()))
				.thenReturn(gson.fromJson(jsonResDao, DescuentoChequesEntity.class));
		Respuesta<OperacionesPrecargadasDTO> res = descuentoChequesBO.obtenerOperacionesPrecargadas(obtenerCliente(),
				"12412531");
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_SERVICIO_OP_PRECARGADAS_CON_OPERACIONES.getDescripcion(),
				res.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	@Test
	public void obtenerOperacionesPrecargadasErrorSinRellamadoTest() throws JsonSyntaxException, DAOException {
		Gson gson = new Gson();
		String jsonResDao = "{\"codigoRetornoExtendido\":\"10000091\",\"operaciones\":[]}";
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Mockito.when(descuentoChequesDao.obtenerOperaciones(Matchers.anyString(), Matchers.any(Cliente.class),
				Matchers.anyBoolean(), Matchers.anyString()))
				.thenReturn(gson.fromJson(jsonResDao, DescuentoChequesEntity.class));
		Respuesta<OperacionesPrecargadasDTO> res = descuentoChequesBO.obtenerOperacionesPrecargadas(obtenerCliente(),
				"          ");
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_OP_PRECARGADAS_SIN_OPERACIONES.getDescripcion(),
				res.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	@Test
	public void obtenerOperacionesPrecargadasErrorGenericoTest() throws JsonSyntaxException, DAOException {
		Gson gson = new Gson();
		String jsonResDao = "{\"codigoRetornoExtendido\":\"10044091\",\"operaciones\":[]}";
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Mockito.when(descuentoChequesDao.obtenerOperaciones(Matchers.anyString(), Matchers.any(Cliente.class),
				Matchers.anyBoolean(), Matchers.anyString()))
				.thenReturn(gson.fromJson(jsonResDao, DescuentoChequesEntity.class));
		Respuesta<OperacionesPrecargadasDTO> res = descuentoChequesBO.obtenerOperacionesPrecargadas(obtenerCliente(),
				"          ");
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
				res.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	@Test
	public void obtenerOperacionesPrecargadasDaoExceptionTest() throws JsonSyntaxException, DAOException {
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Mockito.when(descuentoChequesDao.obtenerOperaciones(Matchers.anyString(), Matchers.any(Cliente.class),
				Matchers.anyBoolean(), Matchers.anyString())).thenThrow(new DAOException());
		Respuesta<OperacionesPrecargadasDTO> res = descuentoChequesBO.obtenerOperacionesPrecargadas(obtenerCliente(),
				"          ");
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
				res.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	@Test
	public void obtenerDetalleOperacionesPrecargadasOkTest() throws JsonSyntaxException, DAOException {
		Gson gson = new Gson();
		String jsonResDao = "{\"headerTrama\":\"200000000000P04HTML0009900010303GSME08  ********00548595000000092017120711505300000000IBF005H3000000000000CNSDETTRA_1000000            03682408    00        00 011526016201712071150400000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH\",\"codigoRetornoExtendido\":\"00000000\",\"indRellamada\":\"N\",\"tipoCuentaCredito\":\"00\",\"sucCuentaCredito\":\"000\",\"nroCuentaCredito\":\"3604292\",\"estadoTramite\":\"03\",\"descEstTramite\":\"PRECARGA                                          \",\"divisa\":\"ARS\",\"apellidoClte\":\"INNAMORATO                    \",\"nombreClte\":\"BAUDILIO MANSSUR              \",\"fecRecalculo\":\"          \",\"form487\":\"0000000000\",\"motRechCliente\":\"                                                                                                    \",\"calificado\":\"S\",\"linea\":\"000000003800000\",\"disponible\":\"000000000591877\",\"tasaMaxima\":\"0000000000\",\"tasaMinima\":\"0000000000\",\"tasaSugerida\":\"0000264200\",\"tasaAplicada\":\"0000264200\",\"tasaTea\":\"0000304779\",\"tasaCFT\":\"0000378459\",\"destinoFondos\":\"     \",\"marcaGarantia\":\"N\",\"marcaAfip\":\"N\",\"marcaCLTEVinc\":\"S\",\"marcaBCRA\":\"N\",\"marcaProducto\":\"N\",\"marcaTasa\":\"N\",\"cantChqAcep\":\"01\",\"cantChqRech\":\"01\",\"sumImpChqAcep\":\"000000000003500\",\"sumComChqAcep\":\"000000000000000\",\"sumIntChqAcep\":\"000000000000048\",\"sumCfChqAcep\":\"000000000000000\",\"sumImpuChqAcep\":\"000000000000012\",\"impNetoChqAcep\":\"000000000003440\",\"impComisionAdic\":\"000000000000000\",\"impTotalAcred\":\"000000000003440\",\"impTotalRech\":\"000000000250000\",\"canalOrigenTramite\":\"04\",\"estTramiteDet\":\"08\",\"descEstTramiteDet\":\"CONFIRMADO                                        \",\"marcaRetGerente\":\"N\",\"marcaRetProducto\":\"N\",\"marcaDevolucSS\":\"N\",\"FechaDeAltaDelTramite\":\"2017-12-07\",\"MotRechCliAmig\":\"                                                                                                    \",\"cantidadRegistros\":2,\"operaciones\":[{\"bcoGirado\":\"015\",\"sucGirada\":\"112\",\"codPostal\":\"1059\",\"digVerif1\":\"7\",\"nroCheque\":\"49069253\",\"digVerif2\":\"4\",\"ctaGirada\":\"24300148244\",\"digVerif3\":\"8\",\"diasAdelantar\":\"019\",\"impCheque\":\"000000000003500\",\"fecVtoChq\":\"2017-12-22\",\"impComision\":\"000000000000000\",\"impInteres\":\"000000000000048\",\"impCFijo\":\"000000000000000\",\"impImpuesto\":\"000000000000012\",\"impNeto\":\"000000000003440\",\"tpoDocLibr1\":\"N\",\"nroDocLibr1\":\"00023568856\",\"tpoDocLibr2\":\" \",\"nroDocLibr2\":\"           \",\"estChq\":\"1\",\"mensRechCliente\":\"                                                                                                    \",\"mensajeRechTecnico\":\"                                                                                                    \",\"motivoRetSS\":\"                                                  \",\"porcInteresAdel\":\"0000013700\"},{\"bcoGirado\":\"072\",\"sucGirada\":\"000\",\"codPostal\":\"1000\",\"digVerif1\":\"2\",\"nroCheque\":\"51100044\",\"digVerif2\":\"1\",\"ctaGirada\":\"00003616534\",\"digVerif3\":\"6\",\"diasAdelantar\":\"013\",\"impCheque\":\"000000000250000\",\"fecVtoChq\":\"2017-12-20\",\"impComision\":\"000000000000000\",\"impInteres\":\"000000000002352\",\"impCFijo\":\"000000000000000\",\"impImpuesto\":\"000000000000601\",\"impNeto\":\"000000000247047\",\"tpoDocLibr1\":\"N\",\"nroDocLibr1\":\"00014253647\",\"tpoDocLibr2\":\" \",\"nroDocLibr2\":\"           \",\"estChq\":\"2\",\"mensRechCliente\":\"POR EL MOMENTO EL CHEQUE NO ES ACEPTADO PARA SER APLICADO A LA OPERACION                            \",\"mensajeRechTecnico\":\"N00014253647 SUPERA EL 30% DEL MONTO DE CESION                                                      \",\"motivoRetSS\":\"                                                  \",\"porcInteresAdel\":\"0000009400\"}]}";
		Mockito.when(detallePrecargadaDao.obtenerDetalleOperacionesPrecargadas(Matchers.any(Cliente.class),
				Matchers.anyString())).thenReturn(gson.fromJson(jsonResDao, DetalleOperacionesPrecargadasEntity.class));
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Mockito.when(codigosDAO.obtenerIndiceCodigoBancario(Matchers.anyString())).thenReturn(2);
		Mockito.when(entidadesDAO.obtenerIndiceCodigoBancario(2)).thenReturn("SANTANDER");
		Mockito.when(legalBO.obtenerLegalesPorCodigo(Matchers.anyString())).thenReturn("legal");
		Respuesta<DetalleOperacionesPrecargadasDTO> res = descuentoChequesBO
				.obtenerDetalleOperacionesPrecargadas(obtenerCliente(), "", "123-123412/7",false);
		Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
	}

	@Test
	public void obtenerDetalleOperacionesPrecargadasErrorTest() throws JsonSyntaxException, DAOException {
		Gson gson = new Gson();
		String jsonResDao = "{\"codigoRetornoExtendido\":\"10044091\"}";
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Mockito.when(detallePrecargadaDao.obtenerDetalleOperacionesPrecargadas(Matchers.any(Cliente.class),
				Matchers.anyString())).thenReturn(gson.fromJson(jsonResDao, DetalleOperacionesPrecargadasEntity.class));
		Respuesta<DetalleOperacionesPrecargadasDTO> res = descuentoChequesBO
				.obtenerDetalleOperacionesPrecargadas(obtenerCliente(), "", "123-123412/7",false);
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
				res.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	@Test
	public void obtenerDetalleOperacionesPrecargadasExceptionTest() throws JsonSyntaxException, DAOException {
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Mockito.when(detallePrecargadaDao.obtenerDetalleOperacionesPrecargadas(Matchers.any(Cliente.class),
				Matchers.anyString())).thenThrow(new DAOException());
		Respuesta<DetalleOperacionesPrecargadasDTO> res = descuentoChequesBO
				.obtenerDetalleOperacionesPrecargadas(obtenerCliente(), "", "123-123412/7",false);
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
				res.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	@Test
	public void obtenerHistorialOperacionesOkTest() throws JsonSyntaxException, DAOException {
		Gson gson = new Gson();
		String jsonResDao = "{\"headerTrama\":\"200000000000P04HTML0009900010304ALNJ54  ********00460791000000142018031210204000000000IBF001QQ000000000000CNSLISTRA_1000000            04013954    00        00 010212223201803121020240000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH\",\"codigoRetornoExtendido\":\"00000000\",\"tieneRellamada\":\"N\",\"cantidadRegistros\":1,\"operaciones\":[{\"nroTramite\":\"0000000303\",\"habilitaDetalle\":\"S\",\"importeAAcreditar\":\"000000000032955\",\"importeBruto\":\"000000000033300\",\"cantidadCheques\":\"01\",\"importeBrutoRechazado\":\"000000000000000\",\"cantidadChequesRechazados\":\"00\",\"estadoTramite\":\"15\",\"descripcionEstado\":\"ACREDITADA                                        \",\"idUltimaModificacion\":\"USUARIO \",\"fechaAlta\":\"2012-08-17\",\"fechaRecalculo\":\"          \",\"apellidoCliente\":\"BUSLJE                        \",\"nombreCliente\":\"DARIO RODOLFO                 \",\"tipoDocumento\":\"N\",\"nroDocumento\":\"00028536389\",\"segmento\":\"I  \",\"fechaUltimaModificacion\":\"2012-08-17\",\"canal\":\"22\",\"marcaSellStation\":\"N\",\"marcaGerente\":\"N\",\"marcaProductos\":\"N\"}]}";
		Mockito.when(descuentoChequesDao.obtenerOperaciones(Matchers.anyString(), Matchers.any(Cliente.class),
				Matchers.anyBoolean(), Matchers.anyString()))
				.thenReturn(gson.fromJson(jsonResDao, DescuentoChequesEntity.class));
		Respuesta<OperacionesPrecargadasDTO> res = descuentoChequesBO.obtenerHistorialOperaciones(obtenerCliente(), "",
				null);
		Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
	}

	// TODO:FALTAN TEST DE HISTORIAL

	@Test
	public void obtenerDetalleHistorialOperacionesOkTest() throws JsonSyntaxException, DAOException {
		Gson gson = new Gson();
		String jsonResDao = "{\"headerTrama\":\"200000000000P04HTML0009900010303GSME08  ********00339021000000122017120109164200000000IBF000AF000000000000CNSDETTRA_1000000            03682408    00        00 009108934201712010916290000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH\",\"codigoRetornoExtendido\":\"00000000\",\"indRellamada\":\"N\",\"tipoCuentaCredito\":\"00\",\"sucCuentaCredito\":\"000\",\"nroCuentaCredito\":\"3604292\",\"estadoTramite\":\"03\",\"descEstTramite\":\"PRECARGA                                          \",\"divisa\":\"ARS\",\"apellidoClte\":\"INNAMORATO                    \",\"nombreClte\":\"BAUDILIO MANSSUR              \",\"fecRecalculo\":\"          \",\"form487\":\"0000000000\",\"motRechCliente\":\"                                                                                                    \",\"calificado\":\"S\",\"linea\":\"000000003800000\",\"disponible\":\"000000001552477\",\"tasaMaxima\":\"0000000000\",\"tasaMinima\":\"0000000000\",\"tasaSugerida\":\"0000264200\",\"tasaAplicada\":\"0000264200\",\"tasaTea\":\"0000304652\",\"tasaCFT\":\"0000341629\",\"destinoFondos\":\"     \",\"marcaGarantia\":\"N\",\"marcaAfip\":\"N\",\"marcaCLTEVinc\":\"S\",\"marcaBCRA\":\"N\",\"marcaProducto\":\"N\",\"marcaTasa\":\"N\",\"cantChqAcep\":\"02\",\"cantChqRech\":\"00\",\"sumImpChqAcep\":\"000000000002500\",\"sumComChqAcep\":\"000000000000000\",\"sumIntChqAcep\":\"000000000000029\",\"sumCfChqAcep\":\"000000000000000\",\"sumImpuChqAcep\":\"000000000000008\",\"impNetoChqAcep\":\"000000000002463\",\"impComisionAdic\":\"000000000000000\",\"impTotalAcred\":\"000000000002463\",\"impTotalRech\":\"000000000000000\",\"canalOrigenTramite\":\"04\",\"estTramiteDet\":\"08\",\"descEstTramiteDet\":\"CONFIRMADO                                        \",\"marcaRetGerente\":\"N\",\"marcaRetProducto\":\"N\",\"marcaDevolucSS\":\"N\",\"FechaDeAltaDelTramite\":\"2017-11-30\",\"MotRechCliAmig\":\"                                                                                                    \",\"cantidadRegistros\":2,\"operaciones\":[{\"bcoGirado\":\"015\",\"sucGirada\":\"112\",\"codPostal\":\"1059\",\"digVerif1\":\"7\",\"nroCheque\":\"49069253\",\"digVerif2\":\"4\",\"ctaGirada\":\"24300148244\",\"digVerif3\":\"8\",\"diasAdelantar\":\"018\",\"impCheque\":\"000000000001000\",\"fecVtoChq\":\"2017-12-15\",\"impComision\":\"000000000000000\",\"impInteres\":\"000000000000013\",\"impCFijo\":\"000000000000000\",\"impImpuesto\":\"000000000000004\",\"impNeto\":\"000000000000983\",\"tpoDocLibr1\":\"N\",\"nroDocLibr1\":\"00028536389\",\"tpoDocLibr2\":\" \",\"nroDocLibr2\":\"           \",\"estChq\":\"1\",\"mensRechCliente\":\"                                                                                                    \",\"mensajeRechTecnico\":\"                                                                                                    \",\"motivoRetSS\":\"                                                  \",\"porcInteresAdel\":\"0000013000\"},{\"bcoGirado\":\"072\",\"sucGirada\":\"000\",\"codPostal\":\"1000\",\"digVerif1\":\"2\",\"nroCheque\":\"51100044\",\"digVerif2\":\"1\",\"ctaGirada\":\"00003616534\",\"digVerif3\":\"6\",\"diasAdelantar\":\"015\",\"impCheque\":\"000000000001500\",\"fecVtoChq\":\"2017-12-15\",\"impComision\":\"000000000000000\",\"impInteres\":\"000000000000016\",\"impCFijo\":\"000000000000000\",\"impImpuesto\":\"000000000000004\",\"impNeto\":\"000000000001480\",\"tpoDocLibr1\":\"N\",\"nroDocLibr1\":\"00023568856\",\"tpoDocLibr2\":\" \",\"nroDocLibr2\":\"           \",\"estChq\":\"1\",\"mensRechCliente\":\"                                                                                                    \",\"mensajeRechTecnico\":\"                                                                                                    \",\"motivoRetSS\":\"                                                  \",\"porcInteresAdel\":\"0000010600\"}]}";
		Mockito.when(detallePrecargadaDao.obtenerDetalleOperacionesPrecargadas(Matchers.any(Cliente.class),
				Matchers.anyString())).thenReturn(gson.fromJson(jsonResDao, DetalleOperacionesPrecargadasEntity.class));
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Mockito.when(codigosDAO.obtenerIndiceCodigoBancario(Matchers.anyString())).thenReturn(2);
		Mockito.when(entidadesDAO.obtenerIndiceCodigoBancario(2)).thenReturn("SANTANDER");
		Mockito.when(legalBO.obtenerLegalesPorCodigo(Matchers.anyString())).thenReturn("legal");
		Respuesta<DetalleHistorialOperacionesDTO> res = descuentoChequesBO
				.obtenerDetalleHistorialOperaciones(obtenerCliente(), "", "123-123412/7");
		Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
	}
	
	@Test
	public void obtenerDetalleHistorialOperacionesErrorTest() throws JsonSyntaxException, DAOException {
		Gson gson = new Gson();
		String jsonResDao = "{\"codigoRetornoExtendido\":\"10044091\"}";
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Mockito.when(detallePrecargadaDao.obtenerDetalleOperacionesPrecargadas(Matchers.any(Cliente.class),
				Matchers.anyString())).thenReturn(gson.fromJson(jsonResDao, DetalleOperacionesPrecargadasEntity.class));
		Respuesta<DetalleHistorialOperacionesDTO> res = descuentoChequesBO
				.obtenerDetalleHistorialOperaciones(obtenerCliente(), "", "123-123412/7");
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
				res.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	@Test
	public void obtenerDetalleHistorialOperacionesExceptionTest() throws JsonSyntaxException, DAOException {
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
		Mockito.when(detallePrecargadaDao.obtenerDetalleOperacionesPrecargadas(Matchers.any(Cliente.class),
				Matchers.anyString())).thenThrow(new DAOException());
		Respuesta<DetalleHistorialOperacionesDTO> res = descuentoChequesBO
				.obtenerDetalleHistorialOperaciones(obtenerCliente(), "", "123-123412/7");
		Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
		Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(),
				res.getItemsMensajeRespuesta().get(0).getTipoError());
	}

	private Cliente obtenerCliente() {
		Cliente cliente = new Cliente();
		cliente.setCuentas(new ArrayList<Cuenta>());
		Cuenta cuenta = new Cuenta();
		cuenta.setTipoCuentaSinUnificar("09");
		cuenta.setCodigoTitularidad("TI");
		cliente.getCuentas().add(cuenta);
		return cliente;
	}

}
