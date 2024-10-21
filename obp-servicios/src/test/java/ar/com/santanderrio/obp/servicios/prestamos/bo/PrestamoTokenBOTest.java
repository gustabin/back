package ar.com.santanderrio.obp.servicios.prestamos.bo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.prestamos.bo.impl.PrestamoManagerBOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamosOBPBffDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.GenericRestResponseDto;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ValidateTokenSmsResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.GenericRestException;

@RunWith(MockitoJUnitRunner.class)
public class PrestamoTokenBOTest {

    @InjectMocks
    private PrestamoManagerBOImpl prestamoTokenBOImpl;

    @Mock
    PrestamosOBPBffDAO prestamoBffDAO;
    
    @Mock
    SesionCliente sesionCliente;

    @Test
    public void isValidTokenSuccessTest() throws DAOException {
        String token = "12345678";
        String nup = "11221122";
        Cliente cliente = Mockito.mock(Cliente.class);

        // When
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(cliente.getNup()).thenReturn(nup);
        Mockito.when(prestamoBffDAO.validarTokenSms(nup, token)).thenReturn(new ValidateTokenSmsResponseDTO(true));

        // Then
        assertTrue(prestamoTokenBOImpl.isValidToken(token));
    }

    @Test
    public void isInvalidTokenReturnFalseTest() throws DAOException {
        String token = "12345678";
        String invalidToken = "1111111";
        String nup = "11221122";

        // When
        Mockito.when(prestamoBffDAO.validarTokenSms(nup, token)).thenThrow(new GenericRestException(new GenericRestResponseDto("errorCode", "errorMessage", "errorTitle")));

        // Then
        assertFalse(prestamoTokenBOImpl.isValidToken(invalidToken));
    }

    @Test
    public void isValidTokenWithEmptyTokenReturnFalse() {
        String token = "";

        // Then
        assertFalse(prestamoTokenBOImpl.isValidToken(token));
    }
}