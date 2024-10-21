package ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dao;

import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import ar.com.santanderrio.obp.base.dao.BaseDatoDAO;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.ProductoClienteEntity;

@RunWith(MockitoJUnitRunner.class)
public class BonificacionesVigentesDAOTest {

	@InjectMocks
	@Spy
	BonificacionesVigentesDAOImpl bonificacionesVigentesDAO = new BonificacionesVigentesDAOImpl();
	
	@Mock
	private BaseDatoDAO baseDatoDAO;
	
	@Mock
	private CredentialSecurityFactory credentialSecurityFactory;
	
	
	@Test
	public void obtenerCredenciales() throws DAOException, SQLException {
		
		//When
		Credential credencial = new Credential();
		credencial.setUsuario("pepe");
		credencial.setPassword("tarjota");
		
		when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credencial);

		//Then
		Credential credenciales = bonificacionesVigentesDAO.getCredentials();
		
		//Expected
		Assert.assertNotNull(credenciales);
		
	}
	
	@Test
	public void obtenerProductoBonificado() throws DAOException, SQLException {
		
		List<ProductoClienteEntity> listaProductos = new ArrayList<ProductoClienteEntity>();
		ProductoClienteEntity producto = new ProductoClienteEntity();
		producto.setCodigoProducto("90");
		producto.setCodigoSubproducto("0001");
		producto.setNombreProducto("SuperCuenta");
		listaProductos.add(producto);
		
		Map<String, Object> respuesta = new LinkedHashMap<String, Object>();
		respuesta.put("p_resultado", "0");
		respuesta.put("p_cursor", listaProductos);
						
		//When
        Mockito.doReturn(respuesta).when(bonificacionesVigentesDAO).consultar(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(), 
                Matchers.any(SqlParameterSource.class), Matchers.<SqlParameter>anyVararg());
        Mockito.doReturn(respuesta).when(bonificacionesVigentesDAO).consultarFuncion(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(), 
                Matchers.any(SqlParameterSource.class), Matchers.<SqlParameter>anyVararg());
        
		//Then
		ProductoClienteEntity productoRespuesta = bonificacionesVigentesDAO.obtenerProductoBonificado("90", "0001");
		
		//Expected
		Assert.assertNotNull(productoRespuesta);
		
		
		
		
	}
	
	
	
}
