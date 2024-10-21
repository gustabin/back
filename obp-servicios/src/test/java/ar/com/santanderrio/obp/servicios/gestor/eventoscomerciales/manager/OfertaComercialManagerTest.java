/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.bo.GestorEventoComercialBO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.OfertaComercialDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.mock.OfertaComercialDTOMock;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.web.manager.impl.OfertaComercialManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class OfertaComercialManagerTest.
 *
 * @author florencia.n.martinez
 */
@RunWith(MockitoJUnitRunner.class)
public class OfertaComercialManagerTest {

    /** The oferta comercial manager. */
    @InjectMocks
    private OfertaComercialManagerImpl ofertaComercialManager;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The modulo permiso BO. */
    @Mock
    private ModuloPermisoBO moduloPermisoBO;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The gestro comercial BO. */
    @Mock
    private GestorEventoComercialBO gestroComercialBO;

    /**
     * Obtener ofertas comerciales OK test.
     */
    @Test
    public void obtenerOfertasComercialesOKTest() {
        // Given
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        Cliente cliente = ClienteMock.infoCliente();
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setDispositivo("DESKTOP");
        EventosComercialesDTO eventosComercialesDTO = new EventosComercialesDTO();
        List<OfertaComercialDTO> ofertas = new ArrayList<OfertaComercialDTO>();
        ofertas.add(OfertaComercialDTOMock.completarInfoCarrusel());
        eventosComercialesDTO.setOfertas(ofertas);

        // When
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class)))
                .thenReturn(moduloPermiso);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(gestroComercialBO.obtenerOfertasComerciales(Matchers.any(Cliente.class), Matchers.anyString()))
                .thenReturn(eventosComercialesDTO);

        // Then
        ofertaComercialManager.obtenerOfertasComerciales();
    }
}
