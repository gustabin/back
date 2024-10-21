package ar.com.santanderrio.obp.servicios.extraccionefectivo.bo;

import java.math.BigDecimal;

import javax.ws.rs.InternalServerErrorException;

import org.apache.cxf.jaxrs.client.WebClient;
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

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.base.mensaje.entities.MensajeMock;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.dao.ExtraccionEfectivoDAO;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.dto.CardlessWithdrawalResponse;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.dto.Destination;
import ar.com.santanderrio.obp.servicios.extraccionefectivo.view.ExtraccionEfectivoView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class ExtraccionEfectivoBOTest {

	private static final String MOCK_NRO_COMPROBANTE = "1";

	private static final String MOCK_MAIL = "pepeTarjota@santandertecnologia.com.ar";

	@Mock
	private ExtraccionEfectivoDAO extraccionEfectivoDAO;
	
	@InjectMocks
	private ExtraccionEfectivoBOImpl bo;

	@Mock
	private RestWebClient restWebClient;
	
	@Mock
	private WebClient client;

	@Mock
	private MensajeBO mensajeBO;
	
	@Mock
	private EstadisticaManager estadisticaManager;
	
	@Mock
	SesionParametros sesionParametros;
	
    @Mock
    private ContadorIntentos contador = new ContadorIntentos(2);
	
	@Spy
	@InjectMocks
	private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
	@InjectMocks
	private Cliente cliente = new Cliente();
	
	@InjectMocks
	private Cuenta cuenta = new Cuenta();
	
    @Before
    public void setUp() throws Exception {
    	MockitoAnnotations.initMocks(this);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.EXTRACCION_EFECTIVO_FEEDBACK_OK))
        .thenReturn(MensajeMock.completarInfoMensaje(CodigoMensajeConstantes.EXTRACCION_EFECTIVO_FEEDBACK_OK, 
        		CodigoMensajeConstantes.EXTRACCION_EFECTIVO_FEEDBACK_OK));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.EXTRACCION_EFECTIVO_ERROR_ADMITE_REINTENTOS))
        .thenReturn(MensajeMock.completarInfoMensaje(CodigoMensajeConstantes.EXTRACCION_EFECTIVO_ERROR_ADMITE_REINTENTOS, 
        		CodigoMensajeConstantes.EXTRACCION_EFECTIVO_ERROR_ADMITE_REINTENTOS));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.EXTRACCION_EFECTIVO_ERROR_NO_ESPECIFICADO))
        .thenReturn(MensajeMock.completarInfoMensaje(CodigoMensajeConstantes.EXTRACCION_EFECTIVO_ERROR_NO_ESPECIFICADO, 
        		CodigoMensajeConstantes.EXTRACCION_EFECTIVO_ERROR_NO_ESPECIFICADO));
        
        Mockito.when(sesionParametros.getContador()).thenReturn(contador);
        cliente.setTipoDocumento("N");
        cliente.setDni("33000222");
        cliente.setNup("111");
        cuenta.setTipoCuenta("001");
        cuenta.setCbu("123456");
        cuenta.setMonedaAltair("ARS");
        cuenta.setNroCuentaProducto("000001234567");
        cuenta.setNroSucursal("033");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cliente.getCuentas().add(cuenta);
    }
    
    @Test
    public void whenInvokeExtraccionEfectivoWithDataThenResponseIsSuccess() throws Exception {
        
    	CardlessWithdrawalResponse response = buildOKResponse();
                
		Mockito.when(sesionParametros.getPrimerAcceso()).thenReturn(true);

        Mockito.when(extraccionEfectivoDAO.ejecutarSolicitud(Matchers.any(Cuenta.class), Matchers.any(Cliente.class), Matchers.anyInt(), Matchers.anyString())).thenReturn(response);
        
    	Respuesta<ExtraccionEfectivoView> feedback = bo.ejecutarSolicitud(cuenta, cliente, 1000, MOCK_MAIL );    	
    	
    	Assert.assertEquals(EstadoRespuesta.OK, feedback.getEstadoRespuesta());
    	ExtraccionEfectivoView respuesta = feedback.getRespuesta();
    	Assert.assertEquals(MOCK_NRO_COMPROBANTE, respuesta.getNumeroComprobante());
    	Assert.assertEquals("01/01/2020", respuesta.getFechaVencimiento());
    	Assert.assertEquals(CodigoMensajeConstantes.EXTRACCION_EFECTIVO_FEEDBACK_OK, respuesta.getMensajeOK());
    }

    @Test
    public void whenInvokeExtraccionEfectivoThenResponseWithServerErrorWithRetry() throws Exception {
    	
    	Mockito.when(sesionParametros.getContador().permiteReintentar()).thenReturn(true);
    	Mockito.when(extraccionEfectivoDAO.ejecutarSolicitud(Matchers.any(Cuenta.class), Matchers.any(Cliente.class), Matchers.anyInt(), Matchers.anyString())).thenThrow(new InternalServerErrorException());
    	Mockito.when(sesionParametros.getContador().permiteReintentar()).thenReturn(true);
    	Respuesta<ExtraccionEfectivoView> feedback = bo.ejecutarSolicitud(cuenta, cliente, 1000, MOCK_MAIL );
    	Assert.assertEquals(EstadoRespuesta.ERROR, feedback.getEstadoRespuesta());
    	Assert.assertEquals(null, feedback.getRespuesta());
    	ItemMensajeRespuesta error = feedback.getItemsMensajeRespuesta().get(0);
    	Assert.assertEquals(TipoError.EXTRACCION_EFECTIVO_ERROR_PERMITE_REINTENTOS.name(), error.getTipoError());
		Assert.assertEquals(CodigoMensajeConstantes.EXTRACCION_EFECTIVO_ERROR_ADMITE_REINTENTOS, error.getMensaje());
    }
    
    @Test
    public void whenInvokeExtraccionEfectivoThenResponseWithServerErrorWithoutRetry() throws Exception {
    	
    	Mockito.when(extraccionEfectivoDAO.ejecutarSolicitud(Matchers.any(Cuenta.class), Matchers.any(Cliente.class), Matchers.anyInt(), Matchers.anyString())).thenThrow(new InternalServerErrorException());
    	Mockito.when(sesionParametros.getContador().permiteReintentar()).thenReturn(false);
    	Respuesta<ExtraccionEfectivoView> feedback = bo.ejecutarSolicitud(cuenta, cliente, 1000, MOCK_MAIL);
    	Assert.assertEquals(EstadoRespuesta.ERROR, feedback.getEstadoRespuesta());
    	Assert.assertEquals(null, feedback.getRespuesta());
    	ItemMensajeRespuesta error = feedback.getItemsMensajeRespuesta().get(0);
    	Assert.assertEquals(TipoError.EXTRACCION_EFECTIVO_ERROR_REINTENTOS_AGOTADOS.name(), error.getTipoError());
		Assert.assertEquals(CodigoMensajeConstantes.EXTRACCION_EFECTIVO_ERROR_NO_ESPECIFICADO, error.getMensaje());
    }
    
    @Test
    public void whenInvokeExtraccionEfectivoThenResponseWithProblems() throws Exception {
    	
    	Mockito.when(extraccionEfectivoDAO.ejecutarSolicitud(Matchers.any(Cuenta.class), Matchers.any(Cliente.class), Matchers.anyInt(), Matchers.anyString())).thenThrow(new Exception());
    	Respuesta<ExtraccionEfectivoView> feedback = bo.ejecutarSolicitud(cuenta, cliente, 1000, MOCK_MAIL);
    	Assert.assertEquals(null, feedback.getRespuesta());
    	ItemMensajeRespuesta error = feedback.getItemsMensajeRespuesta().get(0);
    	Assert.assertEquals(TipoError.ERROR_GENERICO.name(), error.getTipoError());
		Assert.assertEquals(CodigoMensajeConstantes.EXTRACCION_EFECTIVO_ERROR_NO_ESPECIFICADO, error.getMensaje());
    }
    
	private CardlessWithdrawalResponse buildOKResponse() {
		CardlessWithdrawalResponse response = new CardlessWithdrawalResponse();
        response.setAmount(new BigDecimal(1000));
        response.setFechaExpiracion("2020-01-01");
        response.setId(1);
        response.setNumeroComprobante(MOCK_NRO_COMPROBANTE);
        Destination destination = new Destination();
        destination.setEmail(MOCK_MAIL);
		destination.setFirstName("pepe");
		destination.setLastName("Tarjota");
		destination.setIdentificationNumber("1234");
		destination.setIdentificationType("T");
        response.setDestination(destination);
		return response;
	}
    
}