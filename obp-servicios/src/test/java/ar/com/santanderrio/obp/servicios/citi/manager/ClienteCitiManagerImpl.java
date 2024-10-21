package ar.com.santanderrio.obp.servicios.citi.manager;

import static org.mockito.Mockito.verify;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.citi.bo.ClienteCitiBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.citi.manager.ClienteCitiMananager;

// TODO: Auto-generated Javadoc
/**
 * The Class CredencialesBOTest.
 */

@RunWith(MockitoJUnitRunner.class)
public class ClienteCitiManagerImpl {

	/** The Constant NUP. */
	private static final String NUP = "14331";

	/** The cliente citi BO impl. */
	@InjectMocks
	private ClienteCitiMananager clienteCitiMananager = new ClienteCitiMananagerImpl();

	/** The sesion cliente. */
	@Mock
	private SesionCliente sesionCliente;

	@Mock
	private ClienteCitiBO clienteCitiBO;

	@Mock
	EstadisticaManager estadisticaManager;

	/**
	 * Validar NUP sesion cliente.
	 *
	 * @throws DAOException
	 *             the DAO exception
	 */
	@Test
	public void validarSesionClienteNulo() throws DAOException {

		String nup = NUP;

		Cliente cliente = new Cliente();
		cliente.setIsExCiti(null);
		cliente.setNup(nup);

		Respuesta<Boolean> resp = new Respuesta<Boolean>();
		resp.setRespuesta(Boolean.FALSE);
		resp.setEstadoRespuesta(EstadoRespuesta.OK);

		Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

		Mockito.when(clienteCitiBO.isExCiti(nup)).thenReturn(resp);

		clienteCitiMananager.marcarClienteCiti();
		verify(estadisticaManager).add(EstadisticasConstants.CONSULTA_STORED_PROCEDURE_CLIENTES_EXCITI,
		        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

	}

}
