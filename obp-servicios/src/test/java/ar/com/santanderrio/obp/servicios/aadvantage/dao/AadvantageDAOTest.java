package ar.com.santanderrio.obp.servicios.aadvantage.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.aadvantage.dao.impl.AadvantageDAOImpl;
import ar.com.santanderrio.obp.servicios.aadvantage.dto.DetallePuntosAadvantageDTO;
import ar.com.santanderrio.obp.servicios.aadvantage.entities.SolicitudMillasAadvantageInEntity;
import ar.com.santanderrio.obp.servicios.aadvantage.entities.SolicitudMillasAadvantageOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

@RunWith(MockitoJUnitRunner.class)
public class AadvantageDAOTest {

	private static final String OUT_CODIGO_RESULTADO = "vcod_resultado";
	
	private static final String OUT_ERROR_TECNICO = "desc_error_tecnico";
	
	private static final String OUT_ERROR_AMIGABLE = "desc_error_amigable";
	
	private static final String OUT_CURSOR = "curpuntosacum";
	
	private static final String COD_RETORNO_OK = "0";
	
	private static final String COD_RETORNO_USER_EXCEPTION = "1";
	
	private static final String ERROR_TECNICO = "User-Defined Exception";
	
	private static final String ERROR_AMIGABLE = "No se encontraron datos con los filtros ingresados";

	@InjectMocks
	@Spy
	private AadvantageDAOImpl aadvantageDAO = new AadvantageDAOImpl();

	ApplicationContext appContext = new ClassPathXmlApplicationContext();
	
	@Test
	public void consultarMillasOKTest() throws DAOException, SQLException {
		
		SolicitudMillasAadvantageInEntity inEntity = new SolicitudMillasAadvantageInEntity();
		inEntity.setCantMeses(12);
		inEntity.setCliente(this.obtenerCliente());
		Map<String, Object> respuestaBD = obtenerRespuestaMapaDB(false);

		Mockito.doReturn(respuestaBD).when(aadvantageDAO).consultarDB(Matchers.any(SqlParameterSource.class));

		SolicitudMillasAadvantageOutEntity respuesta = aadvantageDAO
				.consultarMillas(inEntity);

		Assert.assertNotNull(respuesta.getListaDetalleMillas());
	}
	
	@Test
	public void consultarMillasErrorTecnicoTest() throws DAOException, SQLException {
		
		SolicitudMillasAadvantageInEntity inEntity = new SolicitudMillasAadvantageInEntity();
		inEntity.setCantMeses(12);
		inEntity.setCliente(this.obtenerCliente());
		Map<String, Object> respuestaBD = obtenerRespuestaMapaDB(true);

		Mockito.doReturn(respuestaBD).when(aadvantageDAO).consultarDB(Matchers.any(SqlParameterSource.class));

		SolicitudMillasAadvantageOutEntity respuesta = aadvantageDAO
				.consultarMillas(inEntity);

		Assert.assertTrue(respuesta.getIsSinMillas());
	}

	@Test
    public void consultaMillasSQLEx() throws SQLException, DAOException {
		SolicitudMillasAadvantageInEntity inEntity = new SolicitudMillasAadvantageInEntity();
		SolicitudMillasAadvantageOutEntity respuesta = new SolicitudMillasAadvantageOutEntity();
		inEntity.setCantMeses(12);
		inEntity.setCliente(this.obtenerCliente());

		Mockito.doThrow(new SQLException()).when(aadvantageDAO).consultarDB(Matchers.any(SqlParameterSource.class));
		
		try {
			respuesta = aadvantageDAO.consultarMillas(inEntity);  
        } catch (DAOException ex) {
            respuesta.setIsErrorTecnico(true);
        }

		Assert.assertTrue(respuesta.getIsErrorTecnico());
    }
	
	private Map<String, Object> obtenerRespuestaMapaDB(boolean userException) {
        Map<String, Object> respuestaBD = new HashMap<String, Object>();
        List<DetallePuntosAadvantageDTO> listaMillas = new ArrayList<DetallePuntosAadvantageDTO>();
        DetallePuntosAadvantageDTO millasMes = new DetallePuntosAadvantageDTO();
        listaMillas.add(millasMes);
        if(userException) {
        	respuestaBD.put(OUT_CODIGO_RESULTADO, COD_RETORNO_USER_EXCEPTION);
        	respuestaBD.put(OUT_ERROR_TECNICO, ERROR_TECNICO);
        	respuestaBD.put(OUT_ERROR_AMIGABLE, ERROR_AMIGABLE);
        }else {
        	respuestaBD.put(OUT_CODIGO_RESULTADO, COD_RETORNO_OK );
        	respuestaBD.put(OUT_CURSOR, listaMillas);
        }
        return respuestaBD;
    }
	
	private Cliente obtenerCliente() {
        Cliente cliente = new Cliente();
        cliente.setNup("12312312");
        return cliente;
    }
	
}
