/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.bo;

import org.junit.Assert;
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
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.UltimoResumenBoImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.TarjetaDAO;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.OperacionTarjetaWSEnum;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.RetornoTarjetasEntityMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.TarjetaEntityMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimoResumenDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.UltimoResumenFilaBean;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSUltimoResumen;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorUltimaLiquidacionException;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;

/**
 * The Class UltimoResumenBOTest.
 *
 * @author florencia.n.martinez
 */
@RunWith(MockitoJUnitRunner.class)
public class UltimoResumenBOTest {

	/** The ultimo resumen BO. */
	@InjectMocks
	private UltimoResumenBo ultimoResumenBO = new UltimoResumenBoImpl();

	/** The cuenta BO. */
	@Mock
	private CuentaBO cuentaBO;

	/** The tarjeta DAO. */
	@Mock
	private TarjetaDAO tarjetaDAO;

	/** The parseador. */
	@Mock
	private ParseadorWSUltimoResumen parseador;

	/** The respuesta factory. */
	@Spy
	private RespuestaFactory respuestaFactory;

	/** The legal BO. */
	@Mock
	private LegalBO legalBO;

	/** The modulo permiso BO. */
	@Mock
	private ModuloPermisoBO moduloPermisoBO;

	/**
	 * Dado un id de cuenta y un cliente OK, cuando se invoca al metodo
	 * "obtenerUltimoResumen", obtengo una respuesta de UltimoResumenDTO con
	 * estado OK.
	 *
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 * @throws ParseadorUltimaLiquidacionException
	 *             the parseador ultima liquidacion exception
	 * @throws DAOException
	 *             the DAO exception
	 * @throws BusinessException
	 *             the business exception
	 */
	@Test
	public void dadoIdCuentaYClienteOKCuandoInvocaObtenerUltimoResumenObtengoRespuestaUltimoResumenDTOOK()
			throws TarjetaBOUtilsException, ParseadorVisaException, ParseadorUltimaLiquidacionException, DAOException,
			BusinessException {
		Cliente cliente = new Cliente();
		IdentificacionCuenta id = new IdentificacionCuenta();

		Mockito.when(cuentaBO.buscarCuentaPorId(Matchers.any(Cliente.class), Matchers.any(IdentificacionCuenta.class)))
				.thenReturn(CuentaMock.completarInfoCuenta());
		Mockito.when(tarjetaDAO.obtenerTarjetasDeVisaWS(Matchers.any(Cuenta.class),
				Matchers.any(OperacionTarjetaWSEnum.class), Matchers.any(Cliente.class)))
				.thenReturn(RetornoTarjetasEntityMock.completarInfoRetornoWS());
		Mockito.when(parseador.tieneErrorDeCredenciales(Matchers.any(RetornoTarjetasEntity.class)))
				.thenReturn(Boolean.FALSE);
		Mockito.when(parseador.noTieneUltimoResumen(Matchers.any(RetornoTarjetasEntity.class)))
				.thenReturn(Boolean.FALSE);
		Mockito.when(parseador.obtenerTarjetaPorNroTarjetaActivaUltimaLiquidacion(
				Matchers.any(RetornoTarjetasEntity.class), Matchers.anyString()))
				.thenReturn(TarjetaEntityMock.completarInfoTarjetaEntity());
		Mockito.when(parseador.obtenerFechaCierreActual(Matchers.any(TarjetaEntity.class))).thenReturn("22/09/2016");
		Mockito.when(parseador.obtenerFechaVencimientoActual(Matchers.any(TarjetaEntity.class)))
				.thenReturn("05/10/2016");
		Mockito.when(parseador.obtenerFechaVencimientoAnterior(Matchers.any(TarjetaEntity.class)))
				.thenReturn("07/09/2016");
		Mockito.when(parseador.obtenerFechaCierreAnterior(Matchers.any(TarjetaEntity.class))).thenReturn("25/08/2016");
		Mockito.when(parseador.obtenerFechaProximoVencimiento(Matchers.any(TarjetaEntity.class)))
				.thenReturn("02/11/2016");
		Mockito.when(parseador.obtenerFechaProximoCierre(Matchers.any(TarjetaEntity.class))).thenReturn("20/10/2016");
		Mockito.when(parseador.obtenerSaldoEnPesos(Matchers.any(TarjetaEntity.class))).thenReturn("3.560,54");
		Mockito.when(parseador.obtenerSaldoEnDolares(Matchers.any(TarjetaEntity.class))).thenReturn("13,98");
		Mockito.when(parseador.obtenerPagoMinimo(Matchers.any(TarjetaEntity.class))).thenReturn("190,00");
		Mockito.when(parseador.obtenerLimiteFinanciacion(Matchers.any(TarjetaEntity.class))).thenReturn("18.900,00");
		Mockito.when(parseador.obtenerLimiteCompra(Matchers.any(TarjetaEntity.class))).thenReturn("21.000,00");
		Mockito.when(parseador.obtenerLimiteCompraEnCuotas(Matchers.any(TarjetaEntity.class))).thenReturn("31.500,00");
		Mockito.when(parseador.obtenerTasaNominalAnualPesos(Matchers.any(TarjetaEntity.class))).thenReturn("38,00");
		Mockito.when(parseador.obtenerTasaNominalAnualDolares(Matchers.any(TarjetaEntity.class))).thenReturn("38,00");
		Mockito.when(parseador.obtenerTasaEfectivaMensualPesos(Matchers.any(TarjetaEntity.class))).thenReturn("3,12");
		Mockito.when(parseador.obtenerTasaEfectivaMensualDolares(Matchers.any(TarjetaEntity.class))).thenReturn("0,00");
		Mockito.when(legalBO.buscarLegal(Matchers.anyString())).thenReturn(completarInfoRespuestaLegal());
		Mockito.when(parseador.obtenerCamposDetalleLiquidacion(Matchers.anyString(), Matchers.anyString()))
				.thenReturn(completarInfoUltimoResumenFilaBean());
		Mockito.when(parseador.esMovimiento(Matchers.any(UltimoResumenFilaBean.class))).thenReturn(Boolean.TRUE);
		Mockito.when(parseador.tieneTotales(Matchers.any(UltimoResumenFilaBean.class))).thenReturn(Boolean.FALSE);
		Mockito.when(parseador.obtenerDescripcion(Matchers.any(UltimoResumenFilaBean.class))).thenReturn("");
		Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class)))
				.thenReturn(new ModuloPermiso());

		Respuesta<UltimoResumenDTO> respuesta = ultimoResumenBO.obtenerUltimoResumen(cliente, id);

		Assert.assertEquals("AMEX", respuesta.getRespuesta().getMarca());

		Assert.assertEquals(Boolean.FALSE, respuesta.getRespuesta().getSonLimitesDolar());
	}

	/**
	 * Completar info ultimo resumen fila bean.
	 *
	 * @return the ultimo resumen fila bean
	 */
	private UltimoResumenFilaBean completarInfoUltimoResumenFilaBean() {
		UltimoResumenFilaBean filaBean = new UltimoResumenFilaBean();
		filaBean.setFecha("25/07/2017");
		filaBean.setDescripcion("Kevinstone");
		filaBean.setPesos("1.500,00");
		filaBean.setDolares("0,00");
		filaBean.setTotal("1.500,00");
		filaBean.setTotalPesos("1.500,00");
		filaBean.setTotalDolares("0,00");
		filaBean.setLegal("");
		return filaBean;
	}

	/**
	 * Completa la informacion del objeto Respuesta<Legal>.
	 *
	 * @return the respuesta
	 */
	private Respuesta<String> completarInfoRespuestaLegal() {
		Respuesta<String> respuesta = new Respuesta<String>();
		respuesta.setRespuesta("Información obtenida de VISA@Home (S.E.U.O.)");
		return respuesta;
	}
}
