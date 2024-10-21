package ar.com.santanderrio.obp.servicios.citi.bo;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.citi.dao.ClienteCitiDAO;
import ar.com.santanderrio.obp.servicios.citi.entities.RepuestaSPClienteCitiEntity;
import ar.com.santanderrio.obp.servicios.citi.manager.ClienteCitiMananager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class CredencialesBOTest. 
 */

@RunWith(MockitoJUnitRunner.class) 
public class ClienteCitiBOImplTest {

	/** The Constant NUP. */
	private static final String NUP = "14331";

	/** The cliente citi BO impl. */
	@InjectMocks
	private ClienteCitiBOImpl clienteCitiBOImpl;

	@Autowired
	private ClienteCitiMananager clienteCitiMananager;

	/** The respuesta factory. */
	@Mock
	private RespuestaFactory respuestaFactory = new RespuestaFactory();

	/** The cliente citi DAO. */
	@Mock
	private ClienteCitiDAO clienteCitiDAO;

	/**
	 * Validar cliente citi.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void validarClienteCiti() throws DAOException {

		String nup = NUP;

		RepuestaSPClienteCitiEntity respuesta = new RepuestaSPClienteCitiEntity(nup, BigDecimal.ZERO, BigDecimal.ZERO);
		Mockito.when(clienteCitiDAO.consultarCitiClienteIdentificacion(nup)).thenReturn(respuesta);

		Respuesta<Boolean> resp = new Respuesta<Boolean>();
		resp.setRespuesta(Boolean.TRUE);

		Mockito.when(respuestaFactory.crearRespuestaOk(Boolean.TRUE)).thenReturn(resp);
		Respuesta<Boolean> isClienteCiti = clienteCitiBOImpl.isExCiti(nup);

		Assert.assertNotNull(isClienteCiti.getRespuesta());
		Assert.assertEquals(isClienteCiti.getRespuesta(), Boolean.TRUE);

	}

	/**
	 * Validar cliente citi nulo.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void validarClienteCitiNulo() throws DAOException {

		String nup = null; 

		RepuestaSPClienteCitiEntity respuesta = new RepuestaSPClienteCitiEntity(nup, BigDecimal.ZERO, BigDecimal.ZERO);
		Mockito.when(clienteCitiDAO.consultarCitiClienteIdentificacion(nup)).thenReturn(respuesta);
		Respuesta<Boolean> resp = new Respuesta<Boolean>();
		resp.setRespuesta(Boolean.FALSE);
		Mockito.when(respuestaFactory.crearRespuestaOk(Boolean.FALSE)).thenReturn(resp);
		Respuesta<Boolean> isClienteCiti = clienteCitiBOImpl.isExCiti(nup);
		Assert.assertNotNull(isClienteCiti.getRespuesta());
		Assert.assertEquals(isClienteCiti.getRespuesta(), Boolean.FALSE);

	}

	/**
	 * DAO exception.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void dAOExceptionTest() throws DAOException {

		String nup = null;

		// RepuestaSPClienteCitiEntity respuesta = new
		// RepuestaSPClienteCitiEntity(nup, BigDecimal.ZERO, BigDecimal.ZERO);
		Mockito.when(clienteCitiDAO.consultarCitiClienteIdentificacion(nup)).thenThrow(DAOException.class);
		Respuesta<Boolean> resp = new Respuesta<Boolean>();
		resp.setEstadoRespuesta(EstadoRespuesta.ERROR);
		Mockito.when(respuestaFactory.crearRespuestaError(Boolean.class, StringUtils.EMPTY, TipoError.ERROR_GENERICO,
		        StringUtils.EMPTY)).thenReturn(resp);
		Respuesta<Boolean> isClienteCiti = clienteCitiBOImpl.isExCiti(nup);
		Assert.assertNull(isClienteCiti.getRespuesta());
		Assert.assertEquals(isClienteCiti.getEstadoRespuesta(), EstadoRespuesta.ERROR);

	}

}
