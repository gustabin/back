package ar.com.santanderrio.obp.servicios.titulos.operaciones.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.SessionUsuarioData;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao.OrdenesTitulosDAO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarOperacionesTextResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultarOperacionesTextResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.OperacionesTextResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.RequestConsultarOperacionesTextEntity;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.bo.impl.TitulosOperacionesBOImpl;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.dto.OperacionTitulosDTO;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView;

@RunWith(MockitoJUnitRunner.class)
public class TitulosOperacionesBOObtenerOperacionesCompraVentaTest {

    @InjectMocks
    TitulosOperacionesBO titulosOperacionesBO = new TitulosOperacionesBOImpl();
        
    @Mock
    private OrdenesTitulosDAO ordenesTitulosDAO;

    @Spy
    RespuestaFactory respuestaFactory;

    @Test
    public void obtenerOperacionesCompraVentaTest() throws DAOException, IllegalAccessException {

    	
		ApplicationContext ctx = Mockito.mock(ApplicationContext.class);
		SessionUsuarioData request = Mockito.mock(SessionUsuarioData.class);
		FieldUtils.writeDeclaredStaticField(ContextHolder.class, "context", ctx, true);

        ConsultarOperacionesTextResponse consultarOperacionesTextResponse = new ConsultarOperacionesTextResponse();
        ParametrosOperacionesView parametrosOperacionesView = new ParametrosOperacionesView();
        parametrosOperacionesView.setNumeroCuenta("123456/7");
        Cliente cliente = new Cliente();
        DatosConsultarOperacionesTextResponse datos = new DatosConsultarOperacionesTextResponse();
        List<OperacionesTextResponse> listaOperacionesText = new ArrayList<OperacionesTextResponse>();
        OperacionesTextResponse operacionesTextResponse = new OperacionesTextResponse();
        OperacionesTextResponse operacionesTextResponse2 = new OperacionesTextResponse();
        
        
        operacionesTextResponse.setFechaOrden("2018/11/23");
        operacionesTextResponse.setEspecie("Especie 1");
        operacionesTextResponse.setTipoOperacion("C");
        operacionesTextResponse.setNeto("20");
        listaOperacionesText.add(operacionesTextResponse);
        operacionesTextResponse2.setFechaOrden("2018/11/23");
        operacionesTextResponse2.setEspecie("Especie 2");
        operacionesTextResponse2.setTipoOperacion("V");
        operacionesTextResponse2.setNeto("20");
        listaOperacionesText.add(operacionesTextResponse2);
        datos.setListaOperacionesText(listaOperacionesText);
        consultarOperacionesTextResponse.setDatos(datos);

		when(ctx.getBean(SessionUsuarioData.class)).thenReturn(request);

        when(ordenesTitulosDAO.consultarOperacionesText(Matchers.any(RequestConsultarOperacionesTextEntity.class)))
                .thenReturn(consultarOperacionesTextResponse);

        Respuesta<List<OperacionTitulosDTO>> respuesta = titulosOperacionesBO.obtenerOperacionesCompraVenta(parametrosOperacionesView, cliente);

        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(respuesta.getRespuesta().get(0).getDescripcion(), "Especie 1");
        assertEquals(respuesta.getRespuesta().get(0).getTipoOperacion(), "C");
        assertEquals(respuesta.getRespuesta().get(1).getDescripcion(), "Especie 2");
        assertEquals(respuesta.getRespuesta().get(1).getTipoOperacion(), "V");
    }

}
