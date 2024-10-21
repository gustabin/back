package ar.com.santanderrio.obp.servicios.prestamos.utils;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoCuotaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagoCuotaOutView;

@RunWith(MockitoJUnitRunner.class)
public class PagoCuotaMapperTest {

	PagoCuotaMapper mapper = new PagoCuotaMapper();

	@Test
	public void given_PagoCuotaResponseDTO_when_from_return_anInstanceOfPagoCuotaOutViewDTO() {
		PagoCuotaResponseDTO response = new PagoCuotaResponseDTO();
		PagoCuotaOutView out = mapper.from(response);
		assertNotNull(out);
	}
}
