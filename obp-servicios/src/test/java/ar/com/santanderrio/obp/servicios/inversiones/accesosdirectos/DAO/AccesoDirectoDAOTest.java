package ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.DAO;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.dao.AccesosDirectosDAOImpl;
import ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity.AccesoDirectoProductoEntity;

@RunWith(MockitoJUnitRunner.class)
public class AccesoDirectoDAOTest {
    /** Acceso Directo DAO. */
    @InjectMocks
    private AccesosDirectosDAOImpl accesoDirectoDAOImpl = new AccesosDirectosDAOImpl();


    /** The SesionCliente */
	@Mock
	private SesionCliente sesionCliente;
	
    @Test
    public void testObtenerAccesoDirectoError() throws DAOException{

        Cliente cliente = new Cliente();
        cliente.setNup("03007878");
        ResumenCliente resumen = new ResumenCliente();
        resumen.setUsuarioRacf("B000000");
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(sesionCliente.getResumenCliente()).thenReturn(resumen);
        AccesoDirectoProductoEntity respuestaDAO = accesoDirectoDAOImpl.obtenerAccesoDirecto(cliente.getNup());
        assertEquals(null, respuestaDAO);
    
    }
    
}