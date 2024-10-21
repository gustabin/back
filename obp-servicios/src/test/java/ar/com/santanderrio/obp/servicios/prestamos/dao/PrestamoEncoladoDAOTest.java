package ar.com.santanderrio.obp.servicios.prestamos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.prestamos.dao.impl.PrestamoEncoladoDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoEncoladoDebitAccountEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoEncoladoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamosEncoladosEntity;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.BadRequestException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PrestamoEncoladoDAOTest extends IatxBaseDAOTest {


    @InjectMocks
    private PrestamoEncoladoDAO prestamoEncoladoDAO = new PrestamoEncoladoDAOImpl();


    @Mock
    private RestWebClient restWebClient;

    @Mock
    private WebClient webClient;

    @Before
    public void init() throws SQLException, DAOException, IOException {
        Mockito.when(webClient.type(Matchers.any(String.class))).thenReturn(webClient);
        Mockito.when(webClient.acceptEncoding(Matchers.any(String.class))).thenReturn(webClient);
        Mockito.when(webClient.accept(Matchers.any(String.class))).thenReturn(webClient);
        Mockito.when(restWebClient.obtenerClienteRest(Matchers.any(String.class))).thenReturn(webClient);
    }

    @Test
    public void obtenerPrestamosEncoladosOk() throws DAOException {

        PrestamoEncoladoEntity prestamoEncolado = new PrestamoEncoladoEntity();
        prestamoEncolado.setId("123");

        Mockito.when(webClient.getCollection(Matchers.any(Class.class))).thenReturn(Collections.singletonList(prestamoEncolado));
        PrestamosEncoladosEntity respuesta = prestamoEncoladoDAO.getPrestamosEncolados("12341234");
        assertEquals("123", respuesta.getPrestamosEncolados().get(0).getId());
    }

    @Test(expected = DAOException.class)
    public void obtenerPrestamosEncoladosBadRequest() throws DAOException {

        Mockito.when(webClient.getCollection(Matchers.any(Class.class))).thenThrow(new BadRequestException());
        Object respuesta = prestamoEncoladoDAO.getPrestamosEncolados("12341234");
        assertEquals("hola", respuesta);
    }

    @Test
    public void obtenerNumeroCuentaPreaprobado()  {
        PrestamoEncoladoDebitAccountEntity prestamoEncoladoDebitAccountEntity = new PrestamoEncoladoDebitAccountEntity();
        prestamoEncoladoDebitAccountEntity.setContractNumber("00720123007001631671");
        String nroCuenta = prestamoEncoladoDebitAccountEntity.getNroPrestamo();
        assertEquals("123-163167/1", nroCuenta);

    }
    @Test
    public void obtenerNumeroCuentaPreacordado()  {
        PrestamoEncoladoDebitAccountEntity prestamoEncoladoDebitAccountEntity = new PrestamoEncoladoDebitAccountEntity();
        prestamoEncoladoDebitAccountEntity.setBranch("000");
        prestamoEncoladoDebitAccountEntity.setNumber("0631671");
        String nroCuenta = prestamoEncoladoDebitAccountEntity.getNroPrestamo();
        assertEquals("000-063167/1", nroCuenta);

    }
}
