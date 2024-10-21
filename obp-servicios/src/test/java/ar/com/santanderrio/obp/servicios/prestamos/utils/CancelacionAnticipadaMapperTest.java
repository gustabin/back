package ar.com.santanderrio.obp.servicios.prestamos.utils;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelacionAnticipadaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.view.CancelacionAnticipadaOutView;

@RunWith(MockitoJUnitRunner.class)
public class CancelacionAnticipadaMapperTest {

	CancelacionAnticipadaMapper mapper = new CancelacionAnticipadaMapper();

	@Test
	public void given_CancelacionAnticipadaResponseDTO_when_from_return_anInstanceOfCancelacionAnticipadaOutViewDTO() {
		CancelacionAnticipadaResponseDTO response = new CancelacionAnticipadaResponseDTO();
		CancelacionAnticipadaOutView out = mapper.from(response);
		assertNotNull(out);
	}
}
