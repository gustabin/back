package ar.com.santanderrio.obp.servicios.getnet.dao;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.ws.rs.BadRequestException;

import ar.com.santanderrio.obp.servicios.getnet.exception.GetnetEmailValidationException;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.getnet.dao.impl.GetnetDAOImpl;
import ar.com.santanderrio.obp.servicios.getnet.dto.GetnetAdhesionDTO;
import ar.com.santanderrio.obp.servicios.getnet.entities.GetnetInEntity;
import ar.com.santanderrio.obp.servicios.getnet.exception.GetnetValidationException;
import ar.com.santanderrio.obp.servicios.getnet.view.GetnetAdhesionInView;

@RunWith(MockitoJUnitRunner.class)
public class GetnetDAOTest {
	
	@InjectMocks
	private GetnetDAO getnetDAO = new GetnetDAOImpl();

	@Mock
	private CredentialSecurityFactory credentialSecurityFactory;
	
	@Mock
	private RestWebClient restWebClient;
	
	@Mock
	private WebClient webClient;
	
	@Mock
	private javax.ws.rs.core.Response response ;
	
	@Mock
	private EstadisticaManager estadisticaManager;
	
	@Mock
	private BadRequestException badRequestException;
	
	@SuppressWarnings("unchecked")
	@Before
	public void init() throws SQLException, DAOException, IOException {
		Credential credential = new Credential();
		credential.setPassword("123abc");
		credential.setUsuario("user123");
	
		Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.any(String.class))).thenReturn(credential);
		Mockito.when(badRequestException.getResponse()).thenReturn(response);
		Mockito.when(response.getEntity()).thenReturn(new ByteArrayInputStream("{\"error\": {\"message\": \"Validation failed\",\"key\": \"person_validation\"}}".getBytes()));
		Mockito.when(webClient.post(Matchers.any(Object.class), Matchers.any(Class.class))).thenThrow(badRequestException);
		Mockito.when(webClient.type(Matchers.any(String.class))).thenReturn(webClient);
		Mockito.when(restWebClient.obtenerClienteRest(Matchers.any(String.class))).thenReturn(webClient);
	}	
	
	@Test(expected = GetnetValidationException.class)
	public void postPersons() throws ISBANRuntimeException, DAOException, IOException, GetnetValidationException, GetnetEmailValidationException {
		GetnetAdhesionInView view;
		GetnetAdhesionDTO dto;
		GetnetInEntity entity;
		view = new GetnetAdhesionInView();
		dto = new GetnetAdhesionDTO(view);
		dto.setEmail("user@mail.com.ar");
		dto.setCelular("3855905585");
		dto.setNombreFantasia("Empresa S.A");
		dto.setCbu("0720151288000039591018");
		dto.setActividad("Varios");
		dto.setIngreso(1234);
		entity = new GetnetInEntity(dto);
		
		getnetDAO.postPersons(entity, "11-123231-11");
	}	
}
