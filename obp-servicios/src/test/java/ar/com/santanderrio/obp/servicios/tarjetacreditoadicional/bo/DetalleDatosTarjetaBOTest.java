/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.perfil.bo.CambioDomicilioBO;
import ar.com.santanderrio.obp.servicios.perfil.bo.impl.CambioDomicilioBOImpl;
import ar.com.santanderrio.obp.servicios.perfil.dao.CambioDomicilioDAO;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDomiciliosInEntity;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.impl.EstadoYLimitesTarjetaCreditoBOImpl;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao.ConsultaTarjetaTitularDAO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.ConsultaDetalleDatosTarjetaInDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.ConsultaDetalleDatosTarjetaOutDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.TarjetaCandidataDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.ConsultaDetalleDatosTarjetaInEntity;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.entities.ConsultaDetalleDatosTarjetaOutEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;


@RunWith(MockitoJUnitRunner.class)
public class DetalleDatosTarjetaBOTest {
	
	private static final String ESTADO_TARJETA_ACTIVA = "10";
	
    @InjectMocks
    private EstadoYLimitesTarjetaCreditoBO estadoYLimitesTarjetaCreditoBO = new EstadoYLimitesTarjetaCreditoBOImpl();
    
    @Mock
    private ConsultaTarjetaTitularDAO consultaTarjetaTitularDAO; 
    
    @Mock
    private TarjetaCreditoAdicionalBO tarjetaCreditoAdicionalBO;
    
    @Mock
    private SesionCliente sesionCliente;
	
    @Test
    public void obtenerDetalleDatosTarjetaActivaYLimiteDeCompraExigidoOK() throws DAOException{
    	Cliente cliente = ClienteMock.completarInfoCliente();
    	ConsultaDetalleDatosTarjetaOutEntity consultaDetalleDatosTarjetaOutEntity = new ConsultaDetalleDatosTarjetaOutEntity ();
    	consultaDetalleDatosTarjetaOutEntity.setLimiteDeCompra("5000");
    	consultaDetalleDatosTarjetaOutEntity.setEstado(ESTADO_TARJETA_ACTIVA);
    	ConsultaDetalleDatosTarjetaInDTO consultaDetalleDatosTarjetaInDTO = new ConsultaDetalleDatosTarjetaInDTO();
    	consultaDetalleDatosTarjetaInDTO.setNroCuentaTarjeta("0412095313");
    	consultaDetalleDatosTarjetaInDTO.setTipoTarjeta("1");
    	
    	when(consultaTarjetaTitularDAO.obtenerDetalleDatosTarjeta(Matchers.any(ConsultaDetalleDatosTarjetaInEntity.class))).thenReturn(consultaDetalleDatosTarjetaOutEntity);
    	
    	ConsultaDetalleDatosTarjetaOutDTO respuesta = estadoYLimitesTarjetaCreditoBO.obtenerDetalleDatosTarjeta(consultaDetalleDatosTarjetaInDTO, cliente);
    	
    	Assert.assertNotNull(respuesta);
    	Assert.assertTrue(ESTADO_TARJETA_ACTIVA.equals(respuesta.getEstado()));
    	Assert.assertTrue(Integer.parseInt(respuesta.getLimiteDeCompra()) > 1);
    }
    
    

}