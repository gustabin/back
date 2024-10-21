package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.dao;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.DatosPerfilInversorEntity;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RecomendacionResponseEntity;

public class PlazoFijoDAOImplTest extends IatxBaseDAOTest {

	@InjectMocks
	private PlazoFijoDAOImpl plazoFijoDAOImpl;

	@Ignore
	@Test
	public void testConsultaRecomendacion() throws BusinessException, DAOException {
		PerfilInversorRequestEntity request = new PerfilInversorRequestEntity();
		DatosPerfilInversorEntity datos = new DatosPerfilInversorEntity();
				
		datos.setDaysCount(90);
		datos.setNup("03007878");
		datos.setIp("1.1.1.1");
		datos.setUsuario("B999999");
		
		request.setCanal("04");
		request.setSubCanal("0099");
		request.setTipoHash("0");
		request.setDato("Prueba");
		request.setFirma("");
		request.setDatos(datos);
		//when(plazoFijoDAOImpl.generarFirma("")).thenReturn("Firma");
		RecomendacionResponseEntity response = plazoFijoDAOImpl.consultaRecomendacion(request);
		assertEquals(null, response);
	}
	
	@Test
	public void testGenerarFirma() {
		
		String firma = plazoFijoDAOImpl.generarFirma("");
		assertEquals("",firma);
		
	}
}