/**
 * 
 */
package ar.com.santanderrio.obp.servicios.chances.bo;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.base.mensaje.entities.MensajeMock;
import ar.com.santanderrio.obp.servicios.chances.bo.impl.ChancesBOImpl;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dao.GestorEventoComercialDAO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ChancesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.ChanceGrillaOutEntity;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities.ChanceOutEntity;


/**
 * the class ChancesBOTest
 */
@RunWith(MockitoJUnitRunner.class)
public class ChancesBOTest {
	
	/** The gestor evento comercial BO. */
    @InjectMocks
    private ChancesBOImpl chancesBO;
    
    /** The gestor evento comercial DAO. */
    @Mock
    private GestorEventoComercialDAO gestorEventoComercialDAO;
    
    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;
    
    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /**
     * obtenerChancesMes OK.  
     *
     */
    @Test
    public void obtenerChancesMesTestOK() throws DAOException {
    	//Datos del Cliente
    	Cliente cliente = new Cliente();
    	cliente.setNup("00276937");  
    	//Datos String: dispositivo
    	String dispositivo = "MOBILE";
    	//mes seleccionado
    	String mesSelecionado = "201907";
    	//datos ChanceOutEntity
    	ChanceOutEntity chanceOutEntity = new ChanceOutEntity();
    	chanceOutEntity.setTotal_premiaciones("5");
		chanceOutEntity.setChancesGrillaOutEntity(new ArrayList<ChanceGrillaOutEntity>()); 
    	for (int i = 0; i < 5; i++) {
    		ChanceGrillaOutEntity grilla  = new ChanceGrillaOutEntity();
    		grilla.setChances_acreditacion(10 + i);
        	grilla.setChances_amex(20 + i);
        	grilla.setChances_td(30 + i);
        	grilla.setChances_visa(40 + i);
        	grilla.setTotal(100 + 4*i);
    		grilla.setPeriodo(String.valueOf(201904 + i));
    		grilla.setCaducidad("");
    		chanceOutEntity.getChancesGrillaOutEntity().add(grilla);
		}
    	
    	//cuando. Reemplazo
    	Mockito.when(gestorEventoComercialDAO.obtenerPremiaciones(Matchers.any(String.class), 
    			Matchers.any(Cliente.class))).thenReturn(chanceOutEntity);
    	

    	Respuesta<ChancesDTO> respuestaDTO = chancesBO.obtenerChancesMes(dispositivo,cliente,mesSelecionado);
    	
    	//expectativa
    	Assert.assertEquals(EstadoRespuesta.OK, respuestaDTO.getEstadoRespuesta());
    }
    
    /**
     * obtenerChancesMes Error. DAOException
     *
     */
    @Test
    public void obtenerChancesMesTestErrorDAOException() throws DAOException {
    	//Datos del Cliente
    	Cliente cliente = new Cliente();
    	cliente.setNup("00276937");  
    	//Datos String: dispositivo
    	String dispositivo = "MOBILE";
    	//mes seleccionado
    	String mesSelecionado = "201907";
    	//Datos mensaje
    	Mensaje mensaje = MensajeMock.completarInfoMensaje(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO,
    			"<p>No pudimos cargar tus movimientos. Por favor, intentá nuevamente en unos minutos.</p>");
    	
    	//cuando. Reemplazo
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
    	Mockito.when(gestorEventoComercialDAO.obtenerPremiaciones(Matchers.any(String.class), 
    			Matchers.any(Cliente.class))).thenThrow(new DAOException("DAO Exception."));
    	

    	Respuesta<ChancesDTO> respuestaDTO = chancesBO.obtenerChancesMes(dispositivo,cliente,mesSelecionado);
    	
    	//expectativa
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuestaDTO.getEstadoRespuesta());
    }
    
    /**
     * obtenerChancesMes Error. el mesSeleccionado no tiene chances
     *
     */
    @Test
    public void obtenerChancesMesTestErrorMesSeleccionado() throws DAOException {
    	//Datos del Cliente
    	Cliente cliente = new Cliente();
    	cliente.setNup("00276937");  
    	//Datos String: dispositivo
    	String dispositivo = "MOBILE";
    	//mes seleccionado
    	String mesSelecionado = "201902";
    	//Datos mensaje
    	Mensaje mensaje = MensajeMock.completarInfoMensaje(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO,
    			"<p>No pudimos cargar tus movimientos. Por favor, intentá nuevamente en unos minutos.</p>");
    	//datos ChanceOutEntity
    	ChanceOutEntity chanceOutEntity = new ChanceOutEntity();
    	chanceOutEntity.setTotal_premiaciones("5");
		chanceOutEntity.setChancesGrillaOutEntity(new ArrayList<ChanceGrillaOutEntity>()); 
    	for (int i = 0; i < 5; i++) {
    		ChanceGrillaOutEntity grilla  = new ChanceGrillaOutEntity();
    		grilla.setChances_acreditacion(10 + i);
        	grilla.setChances_amex(20 + i);
        	grilla.setChances_td(30 + i);
        	grilla.setChances_visa(40 + i);
        	grilla.setTotal(100 + 4*i);
    		grilla.setPeriodo(String.valueOf(201904 + i));
    		grilla.setCaducidad("");
    		chanceOutEntity.getChancesGrillaOutEntity().add(grilla);
		}
    	
    	//cuando. Reemplazo
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
    	Mockito.when(gestorEventoComercialDAO.obtenerPremiaciones(Matchers.any(String.class), 
    			Matchers.any(Cliente.class))).thenReturn(chanceOutEntity);
    	

    	Respuesta<ChancesDTO> respuestaDTO = chancesBO.obtenerChancesMes(dispositivo,cliente,mesSelecionado);
    	
    	//expectativa
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuestaDTO.getEstadoRespuesta());
    }
    
}
