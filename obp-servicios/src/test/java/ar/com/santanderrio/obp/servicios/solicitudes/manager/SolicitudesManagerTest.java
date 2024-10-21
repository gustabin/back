package ar.com.santanderrio.obp.servicios.solicitudes.manager;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.solicitudes.dto.TransferiSueldoRequestDTO;
import ar.com.santanderrio.obp.servicios.solicitudes.entities.Form;
import ar.com.santanderrio.obp.servicios.solicitudes.entities.Product;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.web.manager.CambioGrupoAfinidadManager;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sucursales.bo.ConsultarSucursalesBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestiondecasos.connector.CasoSFServiceConnector;
import ar.com.santanderrio.obp.servicios.gestiondecasos.connector.models.InformationObpCardsDto;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.segmento.bo.SegmentoClienteBO;
import ar.com.santanderrio.obp.servicios.solicitudes.bo.SolicitudesBO;
import ar.com.santanderrio.obp.servicios.solicitudes.dto.SolicitudesDTO;
import ar.com.santanderrio.obp.servicios.solicitudes.manager.impl.SolicitudesManagerImpl;
import ar.com.santanderrio.obp.servicios.solicitudes.view.BeneficioTransferiTuSueldoView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.SolicitudesView;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class SolicitudesManagerTest {

    @InjectMocks
    private SolicitudesManagerImpl solicitudesManager;

    @Mock
    private CasoSFServiceConnector casoSFServiceConnector;
    
    @Mock
    private SolicitudesBO solicitudesBO;
    
    @Mock
    private SesionCliente sesionCliente;
    
    @Mock
    private ConsultarSucursalesBO consultarSucursalesBO;
    
    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    private EstadisticaManager estadisticaManager;
    
    @Mock
    private SegmentoClienteBO segmentoClienteBO;
    
    @Mock
    private SesionParametros sesionParametros;

    @Mock
    private CambioGrupoAfinidadManager cambioGrupoAfinidadManager;

    @Mock
    private ModuloPermisoBO moduloPermisoBO;
    
	@Mock
	private LegalBO legalBO;

    @Mock
    private final static String TYPIFICATION = "TRANSFERI";

    @Mock
    private final static String RESOLUTION_TYPE = "RPA";
       
    @InjectMocks
    @Spy
    RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Before
    public void initMocks() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(solicitudesManager, "beneficioMonto", "Black,$ 292.000|Platinum,$ 202.000|Infinity Gold,$ 117.000|Infinity,$ 90.000|Supercuenta 3,$ 60.000|Supercuenta,$ 40.000", true);
    }


    
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerCuentasYPaquetes() {
        
        //When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        List<Cuenta> listaCuenta = new ArrayList<Cuenta>();
        List<String> gestiones = new ArrayList<String>();
        List<String> actionSheet = new ArrayList<String>();
        gestiones.add("tarjetaVisa");
        actionSheet.add("tarjetaCredito");
        SolicitudesDTO solicitudesDTO = new SolicitudesDTO(gestiones, actionSheet);
        
        Segmento segmentoCliente = new Segmento();
        segmentoCliente.setEjecutivo("PEPE TARJOTA");
        segmentoCliente.setSelect(true);
        
        Respuesta<Segmento> respuestaSegmento = new Respuesta<Segmento>(); 
        respuestaSegmento.setRespuesta(segmentoCliente);
        
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(solicitudesBO.obtenerCuentasYPaquetes(Matchers.any(listaCuenta.getClass()))).thenReturn(solicitudesDTO);
        when(segmentoClienteBO.obtenerSegmento(Matchers.any(Cliente.class))).thenReturn(respuestaSegmento);
        
        
        //Then
        Respuesta<SolicitudesView> respuesta = solicitudesManager.obtenerCuentasYPaquetes();
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerTarjetas() {
        
        //When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        List<Cuenta> listaCuenta = new ArrayList<Cuenta>();
        List<String> gestiones = new ArrayList<String>();
        List<String> actionSheet = new ArrayList<String>();
        List<InformationObpCardsDto> response = new ArrayList<InformationObpCardsDto>();
        InformationObpCardsDto cards = new InformationObpCardsDto();
        cards.setType("CARDS");
        cards.setBeforeTitle("Mock Antetitulo");
        cards.setLabel("Mock label");
        cards.setTitle("Mock Titulo");
        cards.setDescription("Mock Descripcion");
        cards.setActionLabel("Mock Accion");
        cards.setIcon("Mock Icono");
        cards.setAction("Mock Accion");
        response.add(cards);
        

        gestiones.add("tarjetaVisa");
        actionSheet.add("tarjetaCredito");
        SolicitudesDTO solicitudesDTO = new SolicitudesDTO(gestiones, actionSheet);
        
        Segmento segmentoCliente = new Segmento();
        segmentoCliente.setEjecutivo("PEPE TARJOTA");
        segmentoCliente.setSelect(true);
        
        Respuesta<Segmento> respuestaSegmento = new Respuesta<Segmento>(); 
        respuestaSegmento.setRespuesta(segmentoCliente);
        
        Respuesta<String> resp = new Respuesta<String>(); 
        resp.setRespuesta("SELECT");

        //When     
        when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(true);
        when(cambioGrupoAfinidadManager.obtenerGrupoAfinidadParaFlujos()).thenReturn(resp);
        when(casoSFServiceConnector.getObpCards()).thenReturn(response);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(solicitudesBO.obtenerTarjetas(Matchers.any(Cliente.class), Matchers.any(listaCuenta.getClass()))).thenReturn(solicitudesDTO);
        when(segmentoClienteBO.obtenerSegmento(Matchers.any(Cliente.class))).thenReturn(respuestaSegmento);
        
        
        //Then
        Respuesta<SolicitudesView> respuesta = solicitudesManager.obtenerTarjetas();
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertNotNull(respuesta.getRespuesta().getCardsGestionDeCasos());
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    
    @Test
    public void inicioBeneficioTransferiTuSueldoOk() throws DAOException {
    	
    	//When
    	Mensaje mensajeInicio = new Mensaje();
    	mensajeInicio.setMensaje("Mensaje inicio transferi tu sueldo");
    	Mensaje mensajeCuerpo = new Mensaje();
    	mensajeCuerpo.setMensaje("Mensaje Cuerpo transferi tu sueldo");
    	
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeInicio);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeCuerpo);
    	when(legalBO.obtenerLegal(Matchers.anyString())).thenReturn("legal");
    	    	
        //Then
        Respuesta<BeneficioTransferiTuSueldoView> respuesta = solicitudesManager.inicioBeneficioTransferiTuSueldo();
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    	
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void inicioBeneficioTransferiTuSueldoError() throws DAOException {
    	
    	//When
    	Mensaje mensajeInicio = new Mensaje();
    	mensajeInicio.setMensaje("Mensaje inicio transferi tu sueldo");
    	Mensaje mensajeCuerpo = new Mensaje();
    	mensajeCuerpo.setMensaje("Mensaje Cuerpo transferi tu sueldo");
    	
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeInicio);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeCuerpo);
    	when(legalBO.obtenerLegal(Matchers.anyString())).thenThrow(DAOException.class);
    	    	
        //Then
        Respuesta<BeneficioTransferiTuSueldoView> respuesta = solicitudesManager.inicioBeneficioTransferiTuSueldo();
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    	
    }

    @Test
    @Ignore
    public void solicitarBeneficioTransferiTuSueldoOk() throws BusinessException {
    	
    	//When
    	Mensaje feedbackTitulo = new Mensaje();
    	feedbackTitulo.setMensaje("Feedback titulo ok");
    	Mensaje feedbackParrafo = new Mensaje();
    	feedbackParrafo.setMensaje("Feedback parrafo ok");
    	Mensaje feedbackPie = new Mensaje();
    	feedbackPie.setMensaje("Feedback pie ok");

    	boolean valorRespuesta = true;

        TransferiSueldoRequestDTO transferiSueldoRequestDTO = new TransferiSueldoRequestDTO();
        transferiSueldoRequestDTO.setTypification(TYPIFICATION);
        transferiSueldoRequestDTO.setResolutionType(RESOLUTION_TYPE);
        transferiSueldoRequestDTO.setForms(new ArrayList<Form>());
        Form form = new Form();
        form.setNup("01234567");
        form.setDni("12345678");
        form.setFullName("Pepe Donaruma");
        form.setEmail("POLICARPOREY@PAREDESBORDO.COM.AR");
        form.product.setAccountId("01230123001000012345");
        form.product.setBranch("0123");
        form.product.setAccountNumber("123-123456/7");
        transferiSueldoRequestDTO.getForms().add(form);

    	
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(feedbackTitulo);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(feedbackParrafo);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(feedbackPie);
    	when(solicitudesBO.transferiSueldo(transferiSueldoRequestDTO)).thenReturn(valorRespuesta);
    	
        //Then
        Respuesta<BeneficioTransferiTuSueldoView> respuesta = solicitudesManager.solicitarBeneficioTransferiTuSueldo();
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
	@Test
    @Ignore
    public void solicitarBeneficioTransferiTuSueldoError() throws BusinessException {
    	
    	//When
    	Mensaje feedbackTitulo = new Mensaje();
    	feedbackTitulo.setMensaje("Feedback titulo ok");
    	Mensaje feedbackParrafo = new Mensaje();
    	feedbackParrafo.setMensaje("Feedback parrafo ok");
    	Mensaje feedbackPie = new Mensaje();
    	feedbackPie.setMensaje("Feedback pie ok");

        TransferiSueldoRequestDTO transferiSueldoRequestDTO = new TransferiSueldoRequestDTO();
        transferiSueldoRequestDTO.setTypification(TYPIFICATION);
        transferiSueldoRequestDTO.setResolutionType(RESOLUTION_TYPE);
        transferiSueldoRequestDTO.setForms(new ArrayList<Form>());
        Form form = new Form();
        form.setNup("01234567");
        form.setDni("12345678");
        form.setFullName("Pepe Donaruma");
        form.setEmail("POLICARPOREY@PAREDESBORDO.COM.AR");
        form.product.setAccountId("01230123001000012345");
        form.product.setBranch("0123");
        form.product.setAccountNumber("123-123456/7");
        transferiSueldoRequestDTO.getForms().add(form);
    	  	
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(feedbackTitulo);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(feedbackParrafo);
    	when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(feedbackPie);
    	when(solicitudesBO.transferiSueldo(transferiSueldoRequestDTO)).thenThrow(DAOException.class);
    	
        //Then
        Respuesta<BeneficioTransferiTuSueldoView> respuesta = solicitudesManager.solicitarBeneficioTransferiTuSueldo();
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
}
