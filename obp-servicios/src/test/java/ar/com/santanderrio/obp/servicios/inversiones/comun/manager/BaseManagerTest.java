package ar.com.santanderrio.obp.servicios.inversiones.comun.manager;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesBO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.dao.InversionDAO;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.DatosPerfilInversorResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.entity.PerfilInversorResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.ConsultaPerfilInversorViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DatosTestPerfilViewResponse;

/**
 * 
 * @author marcelo.ruiz
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BaseManagerTest {

	/** The plazo fijo manager. */
    @InjectMocks
    BaseManager baseManager;
    
    /** The SessionCliente*/
    @Mock
    private SesionCliente sesionCliente;
    
    @Mock
    private InversionesBO inversionesBO;
    
    @Mock
    private InversionDAO inversionDAO;
    
    
    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Test
    public void obtenerDatosTestPerfil(){
        Cliente cliente = new Cliente();
        cliente.setNup("123456");

        when(sesionCliente.getCliente()).thenReturn(cliente);
       Respuesta<DatosTestPerfilViewResponse> rta = null;
       rta= baseManager.obtenerDatosTestPerfil(false);
       
       Assert.assertNotNull(rta);
    }
    
    @Test
    public void consultarPerfilInversor(){
        Cliente cliente = new Cliente();
        cliente.setNup("123456");
        cliente.setUsuarioRacf("kusgf");
        
        Respuesta<ConsultaPerfilInversorViewResponse> respuesta = new Respuesta<ConsultaPerfilInversorViewResponse>();

        ConsultaPerfilInversorViewResponse response = new ConsultaPerfilInversorViewResponse();
        response.setDescripcionLarga("Agresivo");
        response.setIdPerfil("1");
        
        DatosPerfilInversorResponse datos = new DatosPerfilInversorResponse();
        datos.setIdPerfil(1);
        datos.setDescripcion("Agresivo");
        datos.setDescripcionLarga("Agresivo");
        datos.setDiasVencimiento(5);
        
        PerfilInversorResponse perfil = new PerfilInversorResponse();
        perfil.setCodigo(0);
        perfil.setMensaje("OK");
        perfil.setDatos(datos);
        perfil.setMensajeTecnico(null);
        response.setPerfil("Agresivo");                
        
        when(sesionCliente.getCliente()).thenReturn(cliente);
       
        Respuesta<PerfilInversorResponse> rta = new  Respuesta<PerfilInversorResponse>();
        rta.setRespuesta(perfil);
        rta.setId(1L);
        rta.setEstadoRespuesta(EstadoRespuesta.OK);
        
        when(inversionesBO.consultarPerfilInversor( false)).thenReturn(rta);
        
         respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
         respuesta.setId(1L);
         respuesta.setRespuestaVacia(false);
         respuesta.setRespuesta(response);

        Respuesta<ConsultaPerfilInversorViewResponse> rtaManager= baseManager.consultarPerfilInversor(false);
       Assert.assertNotNull(rtaManager);
       Assert.assertEquals(respuesta.getEstadoRespuesta(),rtaManager.getEstadoRespuesta());
       Assert.assertEquals(respuesta.getRespuesta().getPerfil(),rtaManager.getRespuesta().getPerfil());
       Assert.assertEquals(respuesta.getRespuesta().getIdPerfil(),rtaManager.getRespuesta().getIdPerfil());
    }
    
    @Test
    public void formato(){
    	
    	String importe = "$ 123.876,00";
    	
    	importe = baseManager.formatearImporteHash(importe);
    	
    	System.out.println(importe);
    }
    
    
    
}
