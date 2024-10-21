package ar.com.santanderrio.obp.servicios.nuevarecarga.bo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.base.mensaje.entities.MensajeMock;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dao.AgendaCelularDAO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.entity.Celular;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.CelularView;

@RunWith(MockitoJUnitRunner.class)
public class AgendaCelularBOTest {

	@InjectMocks
	private AgendaCelularBOImpl agendaCelularBO = new AgendaCelularBOImpl();

	@Spy
	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();
	
    /** The alias favorito producto DAO. */
    @Mock
    private AgendaCelularDAO recargaCelularDao; 
	
    @Mock
	private MensajeBO mensajeBO;
    
    private Cliente cliente;
    
    @Mock 
    private EstadisticaManager estadisticaManager;
    
    @Before
    public void setUp() throws Exception {
    	MockitoAnnotations.initMocks(this);
        cliente = new Cliente();
        cliente.setNup("123");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Mockito.anyString()))
        .thenReturn(MensajeMock.completarInfoMensaje(CodigoMensajeConstantes.ERROR_GENERICO, 
        		CodigoMensajeConstantes.ERROR_GENERICO));
    }
    
	@Test
	public void whenProcedureIsOkActualizarActualizarCelularIsOk() {
		
		Respuesta<Void> response = agendaCelularBO.agregarCelular(cliente, "111", "celular1", "claro");
		Assert.assertEquals(response.getEstadoRespuesta(), EstadoRespuesta.OK);
	    Assert.assertEquals(response.isRespuestaVacia(), false);	
	}
	
	@Test
	public void whenProcedureIsNotOkActualizarActualizarCelularIsNotOk() throws DAOException {
		
        Mockito.doThrow(new DAOException()).when(recargaCelularDao).agregarCelular(Matchers.anyLong(),
        		Matchers.anyString(), Matchers.anyString(), Matchers.anyString());
        Respuesta<Void> response = agendaCelularBO.agregarCelular(cliente, "111", "celular1", "claro");
        Assert.assertEquals(response.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(response.isRespuestaVacia(), true);
	}
	
	@Test
	public void whenConsultarCelularIsOk() throws DAOException {
		
		Respuesta<List<CelularView>> response = agendaCelularBO.consultarCelulares(cliente);
		Assert.assertEquals(response.getEstadoRespuesta(), EstadoRespuesta.OK);
	    Assert.assertEquals(response.isRespuestaVacia(), false);
	    Assert.assertTrue(response.getRespuesta().isEmpty());
		
		List<CelularView> celularList = new ArrayList<CelularView>();
		CelularView celular = new CelularView();
		celular.setNumero("1234");
		celular.setAlias("un celular");
		celular.setCompania("si, claro");
		celularList.add(celular);
		
		Mockito.when(recargaCelularDao.obtenerCelularesNup(Mockito.anyLong())).thenReturn(celularList);
		
        response = agendaCelularBO.consultarCelulares(cliente);
        
		Assert.assertEquals(response.getEstadoRespuesta(), EstadoRespuesta.OK);
	    Assert.assertEquals(response.isRespuestaVacia(), false);
	    CelularView celularView = response.getRespuesta().get(0);
		Assert.assertEquals(celular.getNumero(), celularView.getNumero());
		Assert.assertEquals(celular.getCompania(), celularView.getCompania());
		Assert.assertEquals(celular.getAlias(), celularView.getAlias());
	}
	
	@Test
	public void whenProcedureIsOkAEliminarCelularIsOk() {
		
		Respuesta<Void> response = agendaCelularBO.eliminarCelular(cliente, "1234");
		Assert.assertEquals(response.getEstadoRespuesta(), EstadoRespuesta.OK);
	    Assert.assertEquals(response.isRespuestaVacia(), false);	
	}
	
	@Test
	public void whenProcedureIsNotOkEliminarCelularIsNotOk() throws DAOException {
		
        Mockito.doThrow(new DAOException()).when(recargaCelularDao).eliminarCelular(Matchers.anyLong(),
                Matchers.anyString());
        Respuesta<Void> response = agendaCelularBO.eliminarCelular(cliente, "111");
        Assert.assertEquals(response.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        Assert.assertEquals(response.isRespuestaVacia(), true);
	}
	
}
