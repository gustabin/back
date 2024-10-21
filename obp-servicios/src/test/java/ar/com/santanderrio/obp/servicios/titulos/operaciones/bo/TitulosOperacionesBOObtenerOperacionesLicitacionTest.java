package ar.com.santanderrio.obp.servicios.titulos.operaciones.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.bo.TitulosBO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultarOrdenesDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.ConsultarOrdenesOutDTO;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.bo.impl.TitulosOperacionesBOImpl;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.dto.OperacionTitulosDTO;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView;

@RunWith(MockitoJUnitRunner.class)
public class TitulosOperacionesBOObtenerOperacionesLicitacionTest {
    
    @InjectMocks
    TitulosOperacionesBO titulosOperacionesBO = new TitulosOperacionesBOImpl();
        
    @Mock
    private TitulosBO titulosBO;

    @Spy
    RespuestaFactory respuestaFactory;

    @Test
    public void obtenerOperacionesCompraVentaTest() throws DAOException {

        ParametrosOperacionesView parametrosOperacionesView = new ParametrosOperacionesView();
        Cliente cliente = new Cliente();
        Respuesta<ConsultarOrdenesOutDTO> respuestaConsultarOrdenesOutDTO = new Respuesta<ConsultarOrdenesOutDTO>();
        ConsultarOrdenesOutDTO consultarOrdenesOutDTO = new ConsultarOrdenesOutDTO();
        List<ConsultarOrdenesDTO> listaConsultarOrdenesDTO = new ArrayList<ConsultarOrdenesDTO>();
        ConsultarOrdenesDTO consultarOrdenesDTO = new ConsultarOrdenesDTO();
        ConsultarOrdenesDTO consultarOrdenesDTO2 = new ConsultarOrdenesDTO();
        
        consultarOrdenesDTO.setDescripcion("Especie 1");
        consultarOrdenesDTO.setCantidadAdjudicada("20");
        consultarOrdenesDTO.setFecha("23/11/2018");
        consultarOrdenesDTO.setPrecioAdjudicada("$ 20");
        consultarOrdenesDTO.setNumeroOrden("12345");
        consultarOrdenesDTO2.setDescripcion("Especie 2");
        consultarOrdenesDTO2.setCantidadAdjudicada("30");
        consultarOrdenesDTO2.setFecha("23/11/2018");
        consultarOrdenesDTO2.setPrecioAdjudicada("$ 30");
        consultarOrdenesDTO2.setNumeroOrden("12346");
        listaConsultarOrdenesDTO.add(consultarOrdenesDTO);
        listaConsultarOrdenesDTO.add(consultarOrdenesDTO2);
        consultarOrdenesOutDTO.setList(listaConsultarOrdenesDTO);
        respuestaConsultarOrdenesOutDTO.setRespuesta(consultarOrdenesOutDTO);
        respuestaConsultarOrdenesOutDTO.setEstadoRespuesta(EstadoRespuesta.OK);

        when(titulosBO.consultarLicitaciones(Matchers.any(Cliente.class), Matchers.any(TipoBancaEnum.class), Matchers.anyBoolean())).thenReturn(respuestaConsultarOrdenesOutDTO);

        Respuesta<List<OperacionTitulosDTO>> respuesta = titulosOperacionesBO.obtenerOperacionesLicitacion(parametrosOperacionesView, cliente);

        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(respuesta.getRespuesta().get(0).getDescripcion(), "Especie 1");
        assertEquals(respuesta.getRespuesta().get(0).getTipoOperacion(), "L");
        assertEquals(respuesta.getRespuesta().get(0).getCantidadNominales(), Double.valueOf("20"));
        assertEquals(respuesta.getRespuesta().get(1).getDescripcion(), "Especie 2");
        assertEquals(respuesta.getRespuesta().get(1).getCantidadNominales(), Double.valueOf("30"));
    }

}
