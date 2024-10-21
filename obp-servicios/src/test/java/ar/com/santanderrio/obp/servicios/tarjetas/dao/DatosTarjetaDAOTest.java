/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcherRellamado;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.impl.DatosTarjetaDAOImpl;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjeta;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.DatosTarjetaBuilder;

/**
 * DAO Test for <?PagosPendientesTarjeta?> Manuel Vargas.
 * 
 * @author B041299
 *
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		DatosTarjetaDAOTest.DatosTarjetaDAOImplTestConfiguration.class })
public class DatosTarjetaDAOTest extends IatxBaseDAOTest {

	/** The Constant SERVICIO_CNSTJCDATO. */
	private static final String SERVICIO_CNSTJCDATO = "CNSTJCDATO";

	/** The Constant VERSION_190. */
	private static final String VERSION_190 = "190";

	/** The Constant TRIPLE_CERO_STRING. */
	private static final String TRIPLE_CERO_STRING = "000";

	/** The simulador prestamo DAO. */
	@Autowired
	private DatosTarjetaDAO datosTarjetaDAO;

	/**
	 * The Class SimuladorPrestamoDAOTestConfiguration.
	 */
	@Configuration
	public static class DatosTarjetaDAOImplTestConfiguration {

		/**
		 * Datos tarjeta DAO.
		 *
		 * @return the datos tarjeta DAO
		 */
		@Bean
		public DatosTarjetaDAO datosTarjetaDAO() {
			return new DatosTarjetaDAOImpl();
		}
	}

	/**
	 * Dados un cliente y una cuenta, cuando se invoca al metodo
	 * "obtenerDatosTarjeta", obtengo una excepcion DAOException por un error
	 * con codigo extendido -1.
	 *
	 * @author florencia.n.martinez
	 * @throws DAOException
	 *             the DAO exception
	 * @throws IatxException
	 *             the iatx exception
	 */
	@SuppressWarnings("unused")
	@Test(expected = DAOException.class)
	public void dadosClienteYCuentaCuandoInvocaObtenerDatosTarjetaObtengoDAOExceptionPorErrorCodExtMenosUno()
			throws DAOException, IatxException {
		String tramacnstjcdato = "200000000000Q04HTML0009900010301PRQP31  ********00303277000000022017040712215300000000        00000000CNSTJCDATO1300000            01576531    00        00  IN000209566981969092200000        0000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ000240000õ4050710052641581õTõ";

		when(iatxSender
				.send(Matchers.argThat(new IatxMatcherRellamado(SERVICIO_CNSTJCDATO, VERSION_190, TRIPLE_CERO_STRING))))
						.thenReturn(tramacnstjcdato);

		DatosTarjetaBuilder datosTarjetaBuilder = datosTarjetaDAO.obtenerDatosTarjeta(ClienteMock.completarInfoCliente(),
				CuentaMock.completarInfoCuenta(), true);
	}

	/**
	 * Dados un cliente y una cuenta, cuando se invoca al metodo
	 * "obtenerDatosTarjeta", obtengo DAO exception.
	 *
	 * @author florencia.n.martinez
	 * @throws DAOException
	 *             the DAO exception
	 * @throws IatxException
	 *             the iatx exception
	 */
	@SuppressWarnings("unused")
	@Test(expected = DAOException.class)
	public void dadosClienteYCuentaCuandoInvocaObtenerDatosTarjetaObtengoIatxException()
			throws DAOException, IatxException {
		Mockito.when(iatxSender.send(Matchers.any(IatxRequest.class))).thenThrow(new IatxException("Iatx Exception."));
		DatosTarjetaBuilder datosTarjetaBuilder = datosTarjetaDAO.obtenerDatosTarjeta(ClienteMock.completarInfoCliente(),
				CuentaMock.completarInfoCuenta(), true);
	}

}
