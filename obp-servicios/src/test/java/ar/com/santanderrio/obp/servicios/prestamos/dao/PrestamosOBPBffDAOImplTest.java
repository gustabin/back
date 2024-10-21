package ar.com.santanderrio.obp.servicios.prestamos.dao;

import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.prestamos.dao.impl.PrestamosOBPBffDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelacionAnticipadaRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelacionAnticipadaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.GenericRestResponseDto;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoCuotaRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoCuotaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ValidateTokenSmsRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ValidateTokenSmsResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.GenericRestException;

@RunWith(MockitoJUnitRunner.class)
public class PrestamosOBPBffDAOImplTest extends AbstractPrestamosBffDAOImplTest {

	private static final String NUP = "12345678";

	@InjectMocks
	private PrestamosOBPBffDAOImpl prestamosOBPBffDAO;

	@Override
	PrestamosBffDAO getPrestamosDao() {
		return prestamosOBPBffDAO;
	}

	@Test
	public void pagarCuotaPrestamoOk() throws DAOException {
		PagoCuotaResponseDTO mockResponseDTO = Mockito.mock(PagoCuotaResponseDTO.class);
		PagoCuotaRequestDTO mockRequestDTO = Mockito.mock(PagoCuotaRequestDTO.class);

		Response mockResponse = Mockito.mock(Response.class);
		Mockito.when(mockResponse.readEntity(PagoCuotaResponseDTO.class)).thenReturn(mockResponseDTO);

		Mockito.when(webClient.path(Mockito.anyString())).thenReturn(webClient);
		Mockito.when(webClient.path(Mockito.anyString()).post(Mockito.any())).thenReturn(mockResponse);
		PagoCuotaResponseDTO response = prestamosOBPBffDAO.pagarCuota(NUP, mockRequestDTO);
		assertNotNull(response);
	}

	@Test
	public void pagarCuotaPrestamoError() throws DAOException {
		GenericRestResponseDto mockResponseDTO = Mockito.mock(GenericRestResponseDto.class);
		PagoCuotaRequestDTO mockRequestDTO = Mockito.mock(PagoCuotaRequestDTO.class);

		Response mockResponse = Mockito.mock(Response.class);

		Mockito.when(mockResponse.getStatus()).thenReturn(400);
		Mockito.when(mockResponse.readEntity(GenericRestResponseDto.class)).thenReturn(mockResponseDTO);

		Mockito.when(webClient.path(Mockito.anyString())).thenReturn(webClient);
		Mockito.when(webClient.path(Mockito.anyString()).post(Mockito.any())).thenReturn(mockResponse);

		thrown.expect(GenericRestException.class);
		prestamosOBPBffDAO.pagarCuota(NUP, mockRequestDTO);
	}

	@Test
	public void cancelarAnticipadamenteOk() throws DAOException {
		CancelacionAnticipadaResponseDTO mockResponseDTO = Mockito.mock(CancelacionAnticipadaResponseDTO.class);
		CancelacionAnticipadaRequestDTO mockRequestDTO = Mockito.mock(CancelacionAnticipadaRequestDTO.class);

		Response mockResponse = Mockito.mock(Response.class);
		Mockito.when(mockResponse.readEntity(CancelacionAnticipadaResponseDTO.class)).thenReturn(mockResponseDTO);

		Mockito.when(webClient.path(Mockito.anyString())).thenReturn(webClient);
		Mockito.when(webClient.path(Mockito.anyString()).post(Mockito.any())).thenReturn(mockResponse);
		CancelacionAnticipadaResponseDTO response = prestamosOBPBffDAO.cancelarAnticipadamente(NUP, mockRequestDTO);
		assertNotNull(response);
	}

	@Test
	public void cancelarAnticipadamenteError() throws DAOException {
		GenericRestResponseDto mockResponseDTO = Mockito.mock(GenericRestResponseDto.class);
		CancelacionAnticipadaRequestDTO mockRequestDTO = Mockito.mock(CancelacionAnticipadaRequestDTO.class);

		Response mockResponse = Mockito.mock(Response.class);

		Mockito.when(mockResponse.getStatus()).thenReturn(400);
		Mockito.when(mockResponse.readEntity(GenericRestResponseDto.class)).thenReturn(mockResponseDTO);

		Mockito.when(webClient.path(Mockito.anyString())).thenReturn(webClient);
		Mockito.when(webClient.path(Mockito.anyString()).post(Mockito.any())).thenReturn(mockResponse);

		thrown.expect(GenericRestException.class);
		prestamosOBPBffDAO.cancelarAnticipadamente(NUP, mockRequestDTO);
	}
	
	@Test
	public void isValidTokenOk() throws DAOException {
		ValidateTokenSmsResponseDTO mockResponseDTO = Mockito.mock(ValidateTokenSmsResponseDTO.class);
		ValidateTokenSmsRequestDTO mockRequestDTO = Mockito.mock(ValidateTokenSmsRequestDTO.class);

		Response mockResponse = Mockito.mock(Response.class);

		Mockito.when(mockResponse.getStatus()).thenReturn(200);
		Mockito.when(mockResponse.readEntity(ValidateTokenSmsResponseDTO.class)).thenReturn(mockResponseDTO);

		Mockito.when(webClient.path(Mockito.anyString())).thenReturn(webClient);
		Mockito.when(webClient.path(Mockito.anyString()).post(Mockito.any())).thenReturn(mockResponse);

		ValidateTokenSmsResponseDTO response = prestamosOBPBffDAO.validarTokenSms(NUP, "11111111");
		assertNotNull(response);
	}
	
	@Test
	public void isValidTokenError() throws DAOException {
		GenericRestResponseDto mockResponseDTO = Mockito.mock(GenericRestResponseDto.class);
		ValidateTokenSmsRequestDTO mockRequestDTO = Mockito.mock(ValidateTokenSmsRequestDTO.class);

		Response mockResponse = Mockito.mock(Response.class);

		Mockito.when(mockResponse.getStatus()).thenReturn(400);
		Mockito.when(mockResponse.readEntity(GenericRestResponseDto.class)).thenReturn(mockResponseDTO);

		Mockito.when(webClient.path(Mockito.anyString())).thenReturn(webClient);
		Mockito.when(webClient.path(Mockito.anyString()).post(Mockito.any())).thenReturn(mockResponse);

		thrown.expect(GenericRestException.class);
		prestamosOBPBffDAO.validarTokenSms(NUP, "11111111");
	}
}
