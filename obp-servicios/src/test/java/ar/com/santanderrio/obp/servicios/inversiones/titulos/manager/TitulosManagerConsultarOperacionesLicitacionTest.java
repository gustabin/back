package ar.com.santanderrio.obp.servicios.inversiones.titulos.manager;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.TitulosBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.TitulosManager;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.TitulosManagerImpl;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class TitulosManagerConsultarOperacionesLicitacionTest {
    
    @Mock
    SesionCliente sesionCliente;
    
    @Mock
    TitulosBO licitacionesBO;
    
    @Mock
    EstadisticaManager estadisticaManager;
    
    @Spy
    RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @InjectMocks
    TitulosManager titulosManager = new TitulosManagerImpl();
    
//    @Test
//    public void consultarOperacionesLicitacionTest(){
//        
//        Respuesta<ConsultarOrdenesOutDTO> respuestaConsultarOrdenesOutDTO = new Respuesta<ConsultarOrdenesOutDTO>();
//        respuestaConsultarOrdenesOutDTO.setEstadoRespuesta(EstadoRespuesta.OK);
//        ConsultarOrdenesOutDTO consultarOrdenesOutDTO = new ConsultarOrdenesOutDTO();
//        List<ConsultarOrdenesDTO> listaConsultarOrdenesDTO = new ArrayList<ConsultarOrdenesDTO>();
//        consultarOrdenesOutDTO.setList(listaConsultarOrdenesDTO );
//        ConsultarOrdenesDTO consultarOrdenesDTO = new ConsultarOrdenesDTO();
//        consultarOrdenesDTO.setMoneda("ARS");
//        consultarOrdenesDTO.setPrecioAdjudicada("$ 1400");
//        listaConsultarOrdenesDTO.add(consultarOrdenesDTO);
//        
//        respuestaConsultarOrdenesOutDTO.setRespuesta(consultarOrdenesOutDTO);
//        
//        when(licitacionesBO.consultarOrdenes(Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class), Matchers.anyBoolean())).thenReturn(respuestaConsultarOrdenesOutDTO);
//        
//        Respuesta<ConsultaOperacionesView> respuesta = titulosManager.consultarOperacionesLicitacion(TipoBancaEnum.BANCA_PERSONAL);
//        
//        assertNotNull(respuesta);
//        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
//        assertEquals(respuesta.getRespuesta().getCuentasTitulo().get(0).getOperaciones().get(0).getPrecio(), "1400");
//        assertEquals(respuesta.getRespuesta().getCuentasTitulo().get(0).getOperaciones().get(0).getCodMoneda(), "ARS");
//        assertEquals(respuesta.getRespuesta().getCuentasTitulo().get(0).getOperaciones().get(0).getSigno(), "$");
//    }

}
