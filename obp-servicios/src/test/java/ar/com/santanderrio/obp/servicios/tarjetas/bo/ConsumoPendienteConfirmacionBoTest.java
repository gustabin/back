package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
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
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.excel.dao.ReporteDAO;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.CasuisticaErrorTarjetasBOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.ConsumoPendienteConfirmacionBoImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.TarjetaDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.AutorizacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.AutorizacionesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ConsumoPendienteConfirmacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ErrorTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.EstablecimientoEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ImporteEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaDocumentEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimosConsumosDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSConsumosPendientes;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorWSConsumosPendientesImpl;

/**
 * The Class ConsumoPendienteConfirmacionBoTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConsumoPendienteConfirmacionBoTest {

	/** The consumo pendiente confirmacion bo. */
	@InjectMocks
	private ConsumoPendienteConfirmacionBo consumoPendienteConfirmacionBo = new ConsumoPendienteConfirmacionBoImpl();
	/** The reporte DAO *. */
	@Mock
	private ReporteDAO reporteDAO;

	/** The tarjeta DAO *. */
	@Mock
	private TarjetaDAO tarjetaDao;

	/** The mensaje DAO *. */
	@Mock
	private MensajeBO mensajeBO;

	/** The legal BO *. */
	@Mock
	private LegalBO legalBO;

	/** The respuesta factory. */
	@InjectMocks
	@Spy
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The respuesta factory. */
	@InjectMocks
	@Spy
	private CasuisticaErrorTarjetasBO casuistica = new CasuisticaErrorTarjetasBOImpl();

	/** The parseador. */
	@InjectMocks
	@Spy
	private ParseadorWSConsumosPendientes parseador = new ParseadorWSConsumosPendientesImpl();

	/** The mensaje. */
	Mensaje mensaje = new Mensaje();

	/** The identificacion cuenta. */
	private IdentificacionCuenta identificacionCuenta = new IdentificacionCuenta();

	/** The cliente. */
	private Cliente cliente;

	/**
	 * Inits the mocks.
	 */
	@Before
	public void init() {
		mensaje.setMensaje("Mensaje");
		Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

		identificacionCuenta.setNroCuentaProducto("4660570052763245");
		identificacionCuenta.setNroSucursal("0000");
		cliente = ClienteMock.completarInfoCliente();
	}

	/**
	 * Obtener ultimos consumos ok test.
	 *
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Test
	public void obtenerConsumoPendienteTest() throws BusinessException, DAOException, ParseadorVisaException {
		Mockito.when(tarjetaDao.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
		        Matchers.any(OperacionTarjetaWSEnum.class), Matchers.any(Cliente.class)))
		        .thenReturn(completarInfoRetornoWSAutorizaciones());

		Respuesta<UltimosConsumosDTO> res = consumoPendienteConfirmacionBo
		        .obtenerConsumoPendiente(obtenerCuenta("00004660570052763245"));
		Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
	}

	/**
	 * Obtener cuenta.
	 *
	 * @param numeroTarjetaCredito
	 *            the numero tarjeta credito
	 * @return the cuenta
	 */
	private Cuenta obtenerCuenta(String numeroTarjetaCredito) {
		Cuenta cuenta = new Cuenta();
		cuenta.setNroTarjetaCredito(numeroTarjetaCredito);
		cuenta.setTipoCuentaEnum(TipoCuenta.VISA);
		cuenta.setTipoCuenta("7");
		return cuenta;
	}

	/**
	 * Obtener retorno WS.
	 *
	 * @return the retorno WS
	 */
	public RetornoTarjetasEntity completarInfoRetornoWSAutorizaciones() {
		RetornoTarjetasEntity retornoWS = new RetornoTarjetasEntity();
		TarjetasEntity tarjetas = new TarjetasEntity();
		List<TarjetaEntity> tarjetaEntityList = new ArrayList<TarjetaEntity>();
		tarjetaEntityList.add(completarTarjetaEntity("0", "4660570052763245", completarAutorizaciones()));
		tarjetaEntityList.add(completarTarjetaEntity("0", "4050710082523460", completarAutorizaciones()));
		tarjetaEntityList.add(completarTarjetaEntity("1", "4660570052763237", completarAutorizaciones()));
		tarjetaEntityList.add(completarTarjetaEntity("1", "4660570052763252", completarAutorizaciones()));
		tarjetas.setTarjetaList(tarjetaEntityList);
		retornoWS.setTarjetas(tarjetas);
		return retornoWS;
	}

	/**
	 * Completar tarjeta entity.
	 *
	 * @param categoria
	 *            the categoria
	 * @param codigoTarjeta
	 *            the codigo tarjeta
	 * @param ultimosConsumosEntity
	 *            the ultimos consumos entity
	 * @return the tarjeta entity
	 */
	public TarjetaEntity completarTarjetaEntity(String categoria, String codigoTarjeta,
	        AutorizacionesEntity autorizaciones) {
		TarjetaEntity tarjetaEntity = completarTarjetaEntity(autorizaciones);
		tarjetaEntity.getTarjetaDocument().getDatos().setCategoria(categoria);
		tarjetaEntity.getTarjetaDocument().getDatos().setTarjetaActiva(codigoTarjeta);
		return tarjetaEntity;
	}

	/**
	 * Completa la informacion de cada tarjeta entity.
	 *
	 * @param ultimosMovimientos
	 *            the ultimos movimientos
	 * @return the tarjeta entity
	 */
	public TarjetaEntity completarTarjetaEntity(AutorizacionesEntity autorizaciones) {
		TarjetaEntity tarjetaEntity = new TarjetaEntity();
		TarjetaDocumentEntity tarjetaDocumentEntity = new TarjetaDocumentEntity();
		tarjetaDocumentEntity.setDatos(DatosMock.completarInfoDatos());
		tarjetaDocumentEntity.setAutorizaciones(autorizaciones);
		tarjetaEntity.setTarjetaDocument(tarjetaDocumentEntity);
		return tarjetaEntity;
	}

	/**
	 * Completar info retorno WS sin consumos.
	 *
	 * @return the retorno tarjetas entity
	 */
	public RetornoTarjetasEntity completarInfoRetornoWSSinConsumos() {
		RetornoTarjetasEntity retornoWS = new RetornoTarjetasEntity();
		TarjetasEntity tarjetas = new TarjetasEntity();
		List<TarjetaEntity> tarjetaEntityList = new ArrayList<TarjetaEntity>();
		TarjetaEntity tarjetaEntity = completarInfoTarjetaEntityError();
		tarjetaEntityList.add(tarjetaEntity);

		tarjetaEntity = completarInfoTarjetaEntityError();
		tarjetaEntityList.add(tarjetaEntity);

		tarjetaEntity = completarInfoTarjetaEntityError();
		tarjetaEntityList.add(tarjetaEntity);

		tarjetas.setTarjetaList(tarjetaEntityList);

		retornoWS.setTarjetas(tarjetas);
		return retornoWS;
	}

	/**
	 * Completa la informacion de cada tarjeta entity.
	 *
	 * @return the tarjeta entity
	 */
	public TarjetaEntity completarInfoTarjetaEntityError() {
		TarjetaEntity tarjetaEntity = new TarjetaEntity();
		ErrorTarjetasEntity error = new ErrorTarjetasEntity();
		error.setCodigo("112107");
		tarjetaEntity.setError(error);
		return tarjetaEntity;
	}

	/**
	 * Completar info movimientos.
	 *
	 * @return the ultimos consumos entity
	 */
	private AutorizacionesEntity completarAutorizaciones() {
		AutorizacionesEntity autorizacionesEntity = new AutorizacionesEntity();
		List<ConsumoPendienteConfirmacionEntity> consumoPendienteConfirmacionList = new ArrayList<ConsumoPendienteConfirmacionEntity>();
		consumoPendienteConfirmacionList.add(completarTarjetaUltimosConsumosEntity(""));
		consumoPendienteConfirmacionList.add(completarTarjetaUltimosConsumosEntity("4050710082523452"));
		consumoPendienteConfirmacionList.add(completarTarjetaUltimosConsumosEntity("4050710082523460"));
		consumoPendienteConfirmacionList.add(completarTarjetaUltimosConsumosEntity("4660570052763237"));
		consumoPendienteConfirmacionList.add(completarTarjetaUltimosConsumosEntity("4660570052763245"));
		autorizacionesEntity.setTarjetaPendienteConfirmacionList(consumoPendienteConfirmacionList);
		return autorizacionesEntity;
	}

	/**
	 * Completar info tarjeta ultimos consumos entity.
	 *
	 * @param codigoTarjeta
	 *            the codigo tarjeta
	 * @return the tarjeta ultimos consumos entity
	 */
	private ConsumoPendienteConfirmacionEntity completarTarjetaUltimosConsumosEntity(String codigoTarjeta) {

		List<AutorizacionEntity> autorizacionList = new ArrayList<AutorizacionEntity>();
		autorizacionList.add(completarAutorizacionEntity(""));
		autorizacionList.add(completarAutorizacionEntity("4050710082523452"));
		autorizacionList.add(completarAutorizacionEntity("4050710082523460"));
		autorizacionList.add(completarAutorizacionEntity("4660570052763237"));
		autorizacionList.add(completarAutorizacionEntity("4660570052763245"));
		autorizacionList.add(completarAutorizacionEntityDolares("4660570052763245"));

		ConsumoPendienteConfirmacionEntity entidad = new ConsumoPendienteConfirmacionEntity();
		entidad.setCodigoTarjeta(codigoTarjeta);
		entidad.setDolares("9.99");
		entidad.setPesos("119.85");
		entidad.setAutorizacionList(autorizacionList);
		return entidad;
	}

	/**
	 * Completar movimiento entity.
	 *
	 * @param codigoTarjeta
	 *            the codigo tarjeta
	 * @return the movimiento entity
	 */
	private AutorizacionEntity completarAutorizacionEntity(String codigoTarjeta) {

		EstablecimientoEntity establecimiento = new EstablecimientoEntity();
		establecimiento.setCodigo(codigoTarjeta);
		establecimiento.setDescripcion("MONEDERO TP VISA");
		ImporteEntity importe = new ImporteEntity();
		importe.setCodigoTarjeta("");
		importe.setValor("50,00");

		AutorizacionEntity autorizacionEntity = new AutorizacionEntity();
		autorizacionEntity.setEstablecimiento(establecimiento);
		autorizacionEntity.setFecha("13/04/2017");
		autorizacionEntity.setImporte(importe);
		autorizacionEntity.setCodigo("04307");
		autorizacionEntity.setDescripcion("CONS. $     ");
		autorizacionEntity.setInternacional(null);
		autorizacionEntity.setMoneda("pesos");
		autorizacionEntity.setTipo("M");
		return autorizacionEntity;
	}

	/**
	 * Completar movimiento entity dolares.
	 *
	 * @param codigoTarjeta
	 *            the codigo tarjeta
	 * @return the movimiento entity
	 */
	private AutorizacionEntity completarAutorizacionEntityDolares(String codigoTarjeta) {

		EstablecimientoEntity establecimiento = new EstablecimientoEntity();
		establecimiento.setCodigo(codigoTarjeta);
		establecimiento.setDescripcion("NETFLIX.COM");
		ImporteEntity importe = new ImporteEntity();
		importe.setCodigoTarjeta("");
		importe.setValor("9,99");

		AutorizacionEntity autorizacionEntity = new AutorizacionEntity();
		autorizacionEntity.setEstablecimiento(establecimiento);
		autorizacionEntity.setFecha("13/04/2017");
		autorizacionEntity.setImporte(importe);
		autorizacionEntity.setCodigo("04307");
		autorizacionEntity.setDescripcion("CONS. $     ");
		autorizacionEntity.setInternacional(null);
		autorizacionEntity.setMoneda("dolares");
		autorizacionEntity.setTipo("M");
		return autorizacionEntity;
	}

}
