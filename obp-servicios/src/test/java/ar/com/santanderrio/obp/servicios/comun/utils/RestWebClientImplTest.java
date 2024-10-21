package ar.com.santanderrio.obp.servicios.comun.utils;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto.ListPromocionDto;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.AliasCuentaManagerTest;
import ar.com.santanderrio.obp.test.utils.TestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class RestWebClientImplTest {
	
	
	
	@Test
	public void obtenerPromociones() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
        File jsonResponse = TestUtils.getResourceFile("apimocks/promotions.json");
        ListPromocionDto promocionListDTO = mapper.readValue(jsonResponse, ListPromocionDto.class);
        Assert.assertNotNull(promocionListDTO);
		
	}

}
