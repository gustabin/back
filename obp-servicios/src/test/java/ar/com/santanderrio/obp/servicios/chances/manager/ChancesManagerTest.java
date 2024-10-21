/**
 * 
 */
package ar.com.santanderrio.obp.servicios.chances.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.base.mensaje.entities.MensajeMock;
import ar.com.santanderrio.obp.servicios.chances.bo.ChancesBO;
import ar.com.santanderrio.obp.servicios.chances.manager.impl.ChancesManagerImpl;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ChanceDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ChancesDTO;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view.ChanceView;

/**
 * the class ChancesManagerTest
 */
@RunWith(MockitoJUnitRunner.class)
public class ChancesManagerTest {

	/** The chances manager. */
    @InjectMocks
    private ChancesManagerImpl chancesManager;
    
    /** The chances BO. */
    @Mock
    private ChancesBO chancesBO;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;
    
    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;
    
    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;
    
    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    /**
     * obtenerChancesMes: OK.
     */
    @Test
	public void obtenerChancesMesTestOK() {
    	//Datos del Cliente
    	Cliente cliente = new Cliente();
    	cliente.setNup("00276937");
    	//Datos respuesta ChancesDTO
    	Respuesta<ChancesDTO> respuestaDTO = new Respuesta<ChancesDTO>();
    	respuestaDTO.setEstadoRespuesta(EstadoRespuesta.OK);
    	//Datos ChancesDTO
    	ChancesDTO chancesDTO = new ChancesDTO();
    	chancesDTO.setTotal("80");
    	chancesDTO.setHeader(new ChanceDTO(ChanceDTO.ACCION,"","Chances Julio"));
    	List<ChanceDTO> listChanceDTO = new ArrayList<ChanceDTO>();
    	listChanceDTO.add(new ChanceDTO(ChanceDTO.COBRO_POR_SUELDO,"","20"));
    	listChanceDTO.add(new ChanceDTO(ChanceDTO.COMPRA_SANT_DEBITO,"","40"));
    	listChanceDTO.add(new ChanceDTO(ChanceDTO.COMPRA_SANT_VISA,"","0"));
    	listChanceDTO.add(new ChanceDTO(ChanceDTO.COMPRA_SANT_AMERICAN_EXPRESS,"","20"));
    	listChanceDTO.add(new ChanceDTO(ChanceDTO.TOTAL,"","80"));
    	chancesDTO.setListaChances(listChanceDTO);
    	respuestaDTO.setRespuesta(chancesDTO);
    	//Datos RegistroSesion
    	RegistroSesion resgistroSession = new RegistroSesion();
    	resgistroSession.setDispositivo("phone");
    	
    	//cuando. Reemplazo
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionParametros.getRegistroSession()).thenReturn(resgistroSession);
    	Mockito.when(chancesBO.obtenerChancesMes(Matchers.any(String.class), Matchers.any(Cliente.class), Matchers.any(String.class)))
    	    	.thenReturn(respuestaDTO);
    	
    	//datos input
    	String mesSeleccionado = "201907";
    	ChanceView chanceView = new ChanceView();
    	chanceView.setMesSeleccionado(mesSeleccionado);
    	Respuesta<ChanceView> respuestaView = chancesManager.obtenerChancesMes(chanceView);
    	
    	//expectativa
    	Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
    }
    
    /**
     * obtenerChancesMes: ERROR. El campo mesSelecionado tiene blanco
     */
    @Test
	public void obtenerChancesMesTestERROR() {
    	//Datos del Cliente
    	Cliente cliente = new Cliente();
    	cliente.setNup("00276937");
    	//Datos mensaje
    	Mensaje mensaje = MensajeMock.completarInfoMensaje(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO,
    			"<p>No pudimos cargar tus movimientos. Por favor, intentá nuevamente en unos minutos.</p>");
    	
    	//cuando. Reemplazo
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
    	
    	//datos input
    	String mesSeleccionado = "     ";
    	ChanceView chanceView = new ChanceView();
    	chanceView.setMesSeleccionado(mesSeleccionado);
    	Respuesta<ChanceView> respuestaView = chancesManager.obtenerChancesMes(chanceView);
    	
    	//expectativa
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuestaView.getEstadoRespuesta());
    }
    
    /**
     * obtenerChancesMes: ERROR. La respuesta que devuelve el BO tiene error
     */
    @Test
	public void obtenerChancesMesTestERRORRespuesta() {
    	//Datos del Cliente
    	Cliente cliente = new Cliente();
    	cliente.setNup("00276937");
    	//Datos mensaje
    	Mensaje mensaje = MensajeMock.completarInfoMensaje(CodigoMensajeConstantes.CODIGO_ERROR_GENERICO,
    			"<p>No pudimos cargar tus movimientos. Por favor, intentá nuevamente en unos minutos.</p>");
    	//Datos respuesta ChancesDTO
    	Respuesta<ChancesDTO> respuestaDTO = new Respuesta<ChancesDTO>();
    	respuestaDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
    	//Datos RegistroSesion
    	RegistroSesion resgistroSession = new RegistroSesion();
    	resgistroSession.setDispositivo("phone");
    	
    	//cuando. Reemplazo
    	Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    	Mockito.when(sesionParametros.getRegistroSession()).thenReturn(resgistroSession);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
    	Mockito.when(chancesBO.obtenerChancesMes(Matchers.any(String.class), Matchers.any(Cliente.class), Matchers.any(String.class)))
    	.thenReturn(respuestaDTO);
    	
    	//datos input
    	String mesSeleccionado = "201907";
    	ChanceView chanceView = new ChanceView();
    	chanceView.setMesSeleccionado(mesSeleccionado);
    	Respuesta<ChanceView> respuestaView = chancesManager.obtenerChancesMes(chanceView);
    	
    	//expectativa
    	Assert.assertEquals(EstadoRespuesta.ERROR, respuestaView.getEstadoRespuesta());
    }
}
