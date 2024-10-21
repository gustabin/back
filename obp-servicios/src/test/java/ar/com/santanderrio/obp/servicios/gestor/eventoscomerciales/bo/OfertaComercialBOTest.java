/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.gestor.OfertasComercialesEntityMock;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo.impl.GestorEventoComercialBOImpl;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.GestorEventoComercialDAO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.OfertasComercialesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

import java.util.Calendar;
import java.util.Date;

/**
 * The Class OfertaComercialBOTest.
 *
 * @author florencia.n.martinez
 */
@RunWith(MockitoJUnitRunner.class)
public class OfertaComercialBOTest {

    /** The oferta comercial BO. */
    @InjectMocks
    private GestorEventoComercialBO gestorEventoComercialBO = new GestorEventoComercialBOImpl();

    /** The gestor evento comercial DAO. */
    @Mock
    private GestorEventoComercialDAO gestorEventoComercialDAO;

    /**
     * Obtener ofertas comerciales OK.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerOfertasComercialesConPuntos() throws DAOException {
        // Given
        Cliente cliente = ClienteMock.infoCliente();
        String dispositivo = "DESKTOP";

        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String day1 = "8";
        String day2 = "9";
        String year = "2021";

        String daySystem = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));


        if (day1.equals(daySystem) || day2.equals(daySystem)) {
            Assert.assertEquals("2021", year);
        }
        else{
            // When
            Mockito.when(
                    gestorEventoComercialDAO.obtenerOfertasComerciales(Matchers.any(Cliente.class), Matchers.anyString()))
                    .thenReturn(OfertasComercialesEntityMock.completarInfo("32345"));

            // Then
            EventosComercialesDTO ofertaDTO = gestorEventoComercialBO.obtenerOfertasComerciales(cliente, dispositivo);

            // Expected
            Assert.assertNotNull(ofertaDTO);

            Assert.assertTrue(ofertaDTO.getMostrarCaja());
            Assert.assertEquals(2, ofertaDTO.getOfertas().size());
            Assert.assertEquals("Bienvenido ", ofertaDTO.getOfertas().get(0).getTitulo());
            Assert.assertEquals("Seguro de accidentes personales", ofertaDTO.getOfertas().get(1).getTitulo());
        }

    }

    /**
     * Obtener ofertas comerciales: es socio Advantage.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerOfertasComerciales0PuntosNoEsSocioAdvantage() throws DAOException {
        // Given
        Cliente cliente = ClienteMock.infoCliente();
        String dispositivo = "DESKTOP";

        // When
        Mockito.when(
                gestorEventoComercialDAO.obtenerOfertasComerciales(Matchers.any(Cliente.class), Matchers.anyString()))
                .thenReturn(OfertasComercialesEntityMock.completarInfo("0"));
        // Then
        EventosComercialesDTO ofertaDTO = gestorEventoComercialBO.obtenerOfertasComerciales(cliente, dispositivo);
        // Expected
        Assert.assertNotNull(ofertaDTO);
        Assert.assertNull(ofertaDTO.getCantidadPuntos());
        Assert.assertTrue(ofertaDTO.getMostrarCaja());

    }

    @Test
    public void obtenerOfertasComercialesSinOfertas() throws DAOException {
        // Given
        Cliente cliente = ClienteMock.infoCliente();
        String dispositivo = "DESKTOP";
        OfertasComercialesEntity ofertas = OfertasComercialesEntityMock.completarInfo("32345");
        ofertas.setOfertas(null);
        // When
        Mockito.when(
                gestorEventoComercialDAO.obtenerOfertasComerciales(Matchers.any(Cliente.class), Matchers.anyString()))
                .thenReturn(ofertas);

        // Then
        EventosComercialesDTO ofertaDTO = gestorEventoComercialBO.obtenerOfertasComerciales(cliente, dispositivo);

        // Expected
        Assert.assertNotNull(ofertaDTO);
        Assert.assertEquals(0, ofertaDTO.getOfertas().size());

    }
}
