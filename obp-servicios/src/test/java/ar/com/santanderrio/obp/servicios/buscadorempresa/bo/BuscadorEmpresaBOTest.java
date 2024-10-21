package ar.com.santanderrio.obp.servicios.buscadorempresa.bo;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dao.DebinRecurrenteDAO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.SellerWithCategoryDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.SellerWithProvisionDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.mock.DebinRecurrenteApiMock;
import ar.com.santanderrio.obp.servicios.debinrecurrente.mock.DebinRecurrenteArgMatcherMock;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.EmpresasDebinRecurrenteView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.VendedorView;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.buscadorempresa.entities.ListaEmpresasRecargaCelularMock;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.nuevopago.bo.NuevoPagoBO;
import ar.com.santanderrio.obp.servicios.nuevopago.bo.impl.BuscadorEmpresaBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.BuscadorEmpresaDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.BuscadorEmpresaRta;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaRecargaCelularView;

/**
 * 
 * @author mariano.g.pulera
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class BuscadorEmpresaBOTest {
	
	@Mock
	private BuscadorEmpresaDAO buscadorEmpresaDAO;

	@Mock
	private DebinRecurrenteDAO debinRecurrenteDAO;
	
	@Mock
	private MensajeBO mensajeBO;
	
	@Mock
	private MediosPagoBO mediosPagoBO;
	
	@Mock
	private NuevoPagoBO nuevoPagoBO;

    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    private BuscadorEmpresaBOImpl buscadorEmpresaBO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        buscadorEmpresaBO = new BuscadorEmpresaBOImpl(
                debinRecurrenteDAO,
                buscadorEmpresaDAO,
                mediosPagoBO,
                mensajeBO,
                respuestaFactory,
                "",
                ""
        );
    }
	
	
	   	@Test
	    public void searchOK() throws DAOException {
	        
	   		//When
	        Set<MedioPago> listaEmpresas = new HashSet<MedioPago>();
	        MedioPago movistar = ListaEmpresasRecargaCelularMock.generarEmpresaRecargaCelular();
	        movistar.setFiid("REMO");
	        movistar.setNombreFantasia("RECARGA MOVISTAR");
	        movistar.setAddiLeyendaIdentificacion("texto para este campo");
	        listaEmpresas.add(movistar);
	        
	        Mensaje mensajeSinCoincidencias = new Mensaje();
	        mensajeSinCoincidencias.setMensaje("NO HAY COINCIDENCIAS");

	        Mensaje mensajeImporteLimite = new Mensaje();
	        mensajeImporteLimite.setMensaje("MENSAJE IMPORTE LIMITE");

	        Mensaje mensajeErrorBuscador = new Mensaje();
	        mensajeErrorBuscador.setMensaje("MENSAJE ERROR BUSCADOR");
	        
            Mensaje mensajePagoComprasIdentificacionAyuda = new Mensaje();
            mensajePagoComprasIdentificacionAyuda.setMensaje("MENSAJE PAGO COMPRAS IDENTIFICACION AYUDA");
            
            Mensaje mensajePagoPendienteConfirmacion = new Mensaje();
            mensajePagoComprasIdentificacionAyuda.setMensaje("MENSAJE PAGO PENDIENTE CONFIRMACION");

	                    
	        when(buscadorEmpresaDAO.search(Matchers.anyString())).thenReturn(listaEmpresas);
            when(mensajeBO.obtenerMensajePorCodigo("1265")).thenReturn(mensajeImporteLimite);
            when(mensajeBO.obtenerMensajePorCodigo("1266")).thenReturn(mensajeErrorBuscador);
	        when(mensajeBO.obtenerMensajePorCodigo("1184")).thenReturn(mensajeSinCoincidencias);
            when(mensajeBO.obtenerMensajePorCodigo("1759")).thenReturn(mensajePagoComprasIdentificacionAyuda);
            when(mensajeBO.obtenerMensajePorCodigo("1895")).thenReturn(mensajePagoPendienteConfirmacion);
            
	        //Then
	        Respuesta<BuscadorEmpresaRta> respuesta = buscadorEmpresaBO.search("rec");
	        
	        //Expected
	        Assert.assertNotNull(respuesta);
	        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
       
	    }
	   
	   
       @SuppressWarnings("unchecked")
       @Test
       public void searchErrorException() throws DAOException {
           
           //When
           Mensaje mensajeError = new Mensaje();
           mensajeError.setMensaje("MENSAJE ERROR");

           Mensaje mensajeImporteLimite = new Mensaje();
           mensajeImporteLimite.setMensaje("MENSAJE IMPORTE LIMITE");

           Mensaje mensajeErrorBuscador = new Mensaje();
           mensajeErrorBuscador.setMensaje("MENSAJE ERROR BUSCADOR");
           
           when(buscadorEmpresaDAO.search(Matchers.anyString())).thenThrow(Exception.class);
           when(mensajeBO.obtenerMensajePorCodigo("1265")).thenReturn(mensajeImporteLimite);
           when(mensajeBO.obtenerMensajePorCodigo("1266")).thenReturn(mensajeErrorBuscador);
           when(mensajeBO.obtenerMensajePorCodigo("1277")).thenReturn(mensajeError);

           //Then
           Respuesta<BuscadorEmpresaRta> respuesta = buscadorEmpresaBO.search("rec");
           
           //Expected
           Assert.assertNotNull(respuesta);
           Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
      
       }
	   
	
       @Test
       @Ignore
       public void searchPagoAutomaticoOK() throws DAOException {
           
           //When
           Set<MedioPago> listaEmpresas = new HashSet<MedioPago>();
           MedioPago swiss = new MedioPago();
           swiss.setPesGifFactura("INGRESE LOS 8 DIGITOS");
           swiss.setAddiGifFactura(";texto para este campo");
           swiss.setPesIdentificacion("NUMERO DE SOCIO");
           swiss.setRubroFantasia("OBRA SOCIAL");
           swiss.setFiid("SWIS");
           swiss.setNombreFantasia("SWISS MEDICAL");
           swiss.setAddiLeyendaIdentificacion("texto para este campo");
           listaEmpresas.add(swiss);
           
           Mensaje mensajeSinCoincidencias = new Mensaje();
           mensajeSinCoincidencias.setMensaje("NO HAY COINCIDENCIAS PAGO AUTOMATICO");

           Mensaje mensajeImporteLimite = new Mensaje();
           mensajeImporteLimite.setMensaje("MENSAJE IMPORTE LIMITE");

           Mensaje mensajeErrorBuscador = new Mensaje();
           mensajeErrorBuscador.setMensaje("MENSAJE ERROR BUSCADOR");

           Mensaje mensajePagoComprasIdentificacionAyuda = new Mensaje();
           mensajePagoComprasIdentificacionAyuda.setMensaje("MENSAJE PAGO COMPRAS IDENTIFICACION AYUDA");
           
           Mensaje mensajePagoPendienteConfirmacion = new Mensaje();
           mensajePagoComprasIdentificacionAyuda.setMensaje("MENSAJE PAGO PENDIENTE CONFIRMACION");

           
           when(buscadorEmpresaDAO.searchPagoAutomatico(Matchers.anyString())).thenReturn(listaEmpresas);
           when(mensajeBO.obtenerMensajePorCodigo("1265")).thenReturn(mensajeImporteLimite);
           when(mensajeBO.obtenerMensajePorCodigo("1266")).thenReturn(mensajeErrorBuscador);
           when(mensajeBO.obtenerMensajePorCodigo("1279")).thenReturn(mensajeSinCoincidencias);
           when(mensajeBO.obtenerMensajePorCodigo("1759")).thenReturn(mensajePagoComprasIdentificacionAyuda);
           when(mensajeBO.obtenerMensajePorCodigo("1895")).thenReturn(mensajePagoPendienteConfirmacion);

           

           //Then
           Respuesta<BuscadorEmpresaRta> respuesta = buscadorEmpresaBO.searchPagoAutomatico("swi");
           
           //Expected
           Assert.assertNotNull(respuesta);
           Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
      
       }
       
       
       @SuppressWarnings("unchecked")
       @Test
       public void searchPagoAutomaticoErrorException() throws DAOException {
           
           //When
           Mensaje mensajeError = new Mensaje();
           mensajeError.setMensaje("MENSAJE ERROR");

           Mensaje mensajeImporteLimite = new Mensaje();
           mensajeImporteLimite.setMensaje("MENSAJE IMPORTE LIMITE");

           Mensaje mensajeErrorBuscador = new Mensaje();
           mensajeErrorBuscador.setMensaje("MENSAJE ERROR BUSCADOR");
           
           when(buscadorEmpresaDAO.search(Matchers.anyString())).thenThrow(Exception.class);
           when(mensajeBO.obtenerMensajePorCodigo("1265")).thenReturn(mensajeImporteLimite);
           when(mensajeBO.obtenerMensajePorCodigo("1266")).thenReturn(mensajeErrorBuscador);
           when(mensajeBO.obtenerMensajePorCodigo("1277")).thenReturn(mensajeError);

           //Then
           Respuesta<BuscadorEmpresaRta> respuesta = buscadorEmpresaBO.searchPagoAutomatico("swi");
           
           //Expected
           Assert.assertNotNull(respuesta);
           Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
      
       }
       
       
	@Test
	public void getByCodigoOK() throws BusinessException {
	    
	    //When
	    Respuesta<MedioPago> medioPagoRsta = new Respuesta<MedioPago>();
	    MedioPago empresa = ListaEmpresasRecargaCelularMock.generarEmpresaRecargaCelular();
	    empresa.setTipoImporte("12000");
	    medioPagoRsta.setRespuesta(empresa);
	    medioPagoRsta.setEstadoRespuesta(EstadoRespuesta.OK);
	    
	    when(mediosPagoBO.getByCodigo("REMO")).thenReturn(medioPagoRsta);
	    
	    //Then
	    Respuesta<MedioPagoView> respuesta = buscadorEmpresaBO.getByCodigo("REMO");

	    //Expected
	    Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

	}
	
   @Test
   public void getByCodigoErrorConsultaEmpresa() throws BusinessException {
            
        //When
        Respuesta<MedioPago> medioPagoRsta = new Respuesta<MedioPago>();
        MedioPago empresa = ListaEmpresasRecargaCelularMock.generarEmpresaRecargaCelular();
        medioPagoRsta.setRespuesta(empresa);
        medioPagoRsta.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        medioPagoRsta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        
        when(mediosPagoBO.getByCodigo("REMO")).thenReturn(medioPagoRsta);
        
        //Then
        Respuesta<MedioPagoView> respuesta = buscadorEmpresaBO.getByCodigo("REMO");
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    
    }


	@Test
	public void searchEmpresaRecargaCelularesOK() throws DAOException {
		
		//When
		Set<MedioPago> listaEmpresas = ListaEmpresasRecargaCelularMock.generarListaEmpresasRecargaCelular();
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("NO HAY COINCIDENCIAS");
		
		when(buscadorEmpresaDAO.searchRecargaCelulares()).thenReturn(listaEmpresas);
		when(mensajeBO.obtenerMensajePorCodigo("1184")).thenReturn(mensaje);
		
		//Then
		Respuesta<EmpresaRecargaCelularView> respuesta = buscadorEmpresaBO.searchEmpresaRecargaCelulares();
		
		//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
		for (int i = 0; i < respuesta.getRespuesta().getEmpresas().size(); i++) {
			Assert.assertEquals("RECARGA DE CELULARES", respuesta.getRespuesta().getEmpresas().get(i).getRubroFantasia());
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void searchEmpresaRecargaCelularesErrorDAOException() throws DAOException {
		
		//When
		Mensaje mensaje = new Mensaje();
		mensaje.setMensaje("ERROR BUSCADOR");
		
		when(buscadorEmpresaDAO.searchRecargaCelulares()).thenThrow(DAOException.class);
		when(mensajeBO.obtenerMensajePorCodigo("1418")).thenReturn(mensaje);
		
		//Then
		Respuesta<EmpresaRecargaCelularView> respuesta = buscadorEmpresaBO.searchEmpresaRecargaCelulares();
		
		//Expected
		Assert.assertNotNull(respuesta);
		Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
		
	}
	
	
	   @Test
	   public void searchEmpresaDebitoAutomaticoEnTarjetaOK() throws DAOException {
	        
	        //When
	        Set<MedioPago> listaEmpresas = new HashSet<MedioPago>();
	        MedioPago empresa = new MedioPago();
	        empresa.setRubroFantasia("CLUBES Y GIMNASIOS");        
	        empresa.setNombreFantasia("BILOG");
	        empresa.setCuit("74783873");
	        empresa.setVisaHabilitado("S");
	        empresa.setAmexHabilitado("N");
	        listaEmpresas.add(empresa);
	        
	        Mensaje mensaje = new Mensaje();
	        mensaje.setMensaje("NO HAY COINCIDENCIAS");
	        
	        when(buscadorEmpresaDAO.searchEmpresaDebitoAutomaticoEnTarjeta(Matchers.anyString())).thenReturn(listaEmpresas);
	        when(mensajeBO.obtenerMensajePorCodigo("1184")).thenReturn(mensaje);
	        
	        //Then
	        Respuesta<EmpresaDebitoAutomaticoTarjetaView> respuesta = buscadorEmpresaBO.searchEmpresaDebitoAutomaticoEnTarjeta("bil");
	        
	        //Expected
	        Assert.assertNotNull(respuesta);
	        
	    }
	   
	   
	   
       @SuppressWarnings("unchecked")
       @Test
       public void searchEmpresaDebitoAutomaticoEnTarjetaErrorDAOException() throws DAOException {
           
           //When
           Mensaje mensaje = new Mensaje();
           mensaje.setMensaje("ERROR BUSCADOR");
           
           when(buscadorEmpresaDAO.searchEmpresaDebitoAutomaticoEnTarjeta(Matchers.anyString())).thenThrow(DAOException.class);
           when(mensajeBO.obtenerMensajePorCodigo("1418")).thenReturn(mensaje);
           
           //Then
           Respuesta<EmpresaDebitoAutomaticoTarjetaView> respuesta = buscadorEmpresaBO.searchEmpresaDebitoAutomaticoEnTarjeta("bil");
           
           //Expected
           Assert.assertNotNull(respuesta);
           
       }

       @Test
       public void obtenerEmpresasPrimeraBusquedaVaciaSegundaOk() throws Exception {

           //When
           String empresaBuscada = "empresa";
           List<SellerWithCategoryDTO> mockedSellers = DebinRecurrenteApiMock.getDefaultSellers();
           when(debinRecurrenteDAO.obtenerEmpresas(empresaBuscada))
                   .thenReturn(new ArrayList<SellerWithCategoryDTO>());
           when(debinRecurrenteDAO.obtenerEmpresas(empresaBuscada.toUpperCase()))
                   .thenReturn(mockedSellers);

           List<VendedorView> mockedSellersView = DebinRecurrenteApiMock.getDefaultVendedoresView(mockedSellers);
           ArgumentMatcher<EmpresasDebinRecurrenteView> matcher = DebinRecurrenteArgMatcherMock.listaMismosVendedores(mockedSellersView);
           EmpresasDebinRecurrenteView mockResponse = new EmpresasDebinRecurrenteView();
           mockResponse.setEmpresas(mockedSellersView);
           Respuesta<EmpresasDebinRecurrenteView> mockedManagerResponse = new Respuesta<EmpresasDebinRecurrenteView>();
           mockedManagerResponse.setRespuesta(mockResponse);
           mockedManagerResponse.setRespuestaVacia(mockResponse.getEmpresas().isEmpty());
           mockedManagerResponse.setEstadoRespuesta(EstadoRespuesta.OK);
           doReturn(mockedManagerResponse)
                   .when(respuestaFactory)
                   .crearRespuestaOk(argThat(matcher));

           //Then
           Respuesta<EmpresasDebinRecurrenteView> actualResponse = buscadorEmpresaBO.obtenerEmpresas(empresaBuscada);

           //Expected
           Assert.assertNotNull(actualResponse);
           Assert.assertEquals(EstadoRespuesta.OK, actualResponse.getEstadoRespuesta());
           Assert.assertEquals(mockedManagerResponse.isRespuestaVacia(), actualResponse.isRespuestaVacia());
           Assert.assertEquals(mockedManagerResponse.getRespuesta().getEmpresas().size(), actualResponse.getRespuesta().getEmpresas().size());
           Assert.assertEquals(mockedManagerResponse.getRespuesta().getMensajeError(), actualResponse.getRespuesta().getMensajeError());
           Assert.assertEquals(mockedManagerResponse.getRespuesta().getMensajeNoPermitePago(), actualResponse.getRespuesta().getMensajeNoPermitePago());
           Assert.assertEquals(mockedManagerResponse.getRespuesta().getMensajeImporteLimite(), actualResponse.getRespuesta().getMensajeImporteLimite());
           Assert.assertEquals(mockedManagerResponse.getRespuesta().getPagoComprasAyuda(), actualResponse.getRespuesta().getPagoComprasAyuda());
           Assert.assertEquals(mockedManagerResponse.getRespuesta().getMensajeInformacionPagoAdebitar(), actualResponse.getRespuesta().getMensajeInformacionPagoAdebitar());
           verify(debinRecurrenteDAO, times(1)).obtenerEmpresas(empresaBuscada);
           verify(debinRecurrenteDAO, times(1)).obtenerEmpresas(empresaBuscada.toUpperCase());
       }

    @Test
    public void obtenerEmpresasPrimeraBusquedaOk() throws Exception {

        //When
        String empresaBuscada = "empresa";
        List<SellerWithCategoryDTO> mockedSellers = DebinRecurrenteApiMock.getDefaultSellers();
        when(debinRecurrenteDAO.obtenerEmpresas(empresaBuscada))
                .thenReturn(mockedSellers);

        List<VendedorView> mockedSellersView = DebinRecurrenteApiMock.getDefaultVendedoresView(mockedSellers);
        ArgumentMatcher<EmpresasDebinRecurrenteView> matcher = DebinRecurrenteArgMatcherMock.listaMismosVendedores(mockedSellersView);
        EmpresasDebinRecurrenteView mockResponse = new EmpresasDebinRecurrenteView();
        mockResponse.setEmpresas(mockedSellersView);
        Respuesta<EmpresasDebinRecurrenteView> mockedManagerResponse = new Respuesta<EmpresasDebinRecurrenteView>();
        mockedManagerResponse.setRespuesta(mockResponse);
        mockedManagerResponse.setRespuestaVacia(mockResponse.getEmpresas().isEmpty());
        mockedManagerResponse.setEstadoRespuesta(EstadoRespuesta.OK);
        doReturn(mockedManagerResponse)
                .when(respuestaFactory)
                .crearRespuestaOk(argThat(matcher));

        //Then
        Respuesta<EmpresasDebinRecurrenteView> actualResponse = buscadorEmpresaBO.obtenerEmpresas(empresaBuscada);

        //Expected
        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(EstadoRespuesta.OK, actualResponse.getEstadoRespuesta());
        Assert.assertEquals(mockedManagerResponse.isRespuestaVacia(), actualResponse.isRespuestaVacia());
        Assert.assertEquals(mockedManagerResponse.getRespuesta().getEmpresas().size(), actualResponse.getRespuesta().getEmpresas().size());
        Assert.assertEquals(mockedManagerResponse.getRespuesta().getMensajeError(), actualResponse.getRespuesta().getMensajeError());
        Assert.assertEquals(mockedManagerResponse.getRespuesta().getMensajeNoPermitePago(), actualResponse.getRespuesta().getMensajeNoPermitePago());
        Assert.assertEquals(mockedManagerResponse.getRespuesta().getMensajeImporteLimite(), actualResponse.getRespuesta().getMensajeImporteLimite());
        Assert.assertEquals(mockedManagerResponse.getRespuesta().getPagoComprasAyuda(), actualResponse.getRespuesta().getPagoComprasAyuda());
        Assert.assertEquals(mockedManagerResponse.getRespuesta().getMensajeInformacionPagoAdebitar(), actualResponse.getRespuesta().getMensajeInformacionPagoAdebitar());
        verify(debinRecurrenteDAO, times(1)).obtenerEmpresas(empresaBuscada);
    }

    @Test
    public void obtenerEmpresasPrimeraBusquedaVaciaSegundaFail() throws Exception {

        //When
        String empresaBuscada = "empresa";
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("ERROR BUSCADOR");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_GENERICO)).thenReturn(mensaje);
        when(debinRecurrenteDAO.obtenerEmpresas(empresaBuscada))
                .thenReturn(new ArrayList<SellerWithCategoryDTO>());
        when(debinRecurrenteDAO.obtenerEmpresas(empresaBuscada.toUpperCase()))
                .thenThrow(new DAOException("Error en el servicio DEBIN API /sellers/searchByName"));

        //Then
        Respuesta<EmpresasDebinRecurrenteView> actualResponse = buscadorEmpresaBO.obtenerEmpresas(empresaBuscada);

        //Expected
        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(EstadoRespuesta.ERROR, actualResponse.getEstadoRespuesta());
        Assert.assertTrue(actualResponse.isRespuestaVacia());
        Assert.assertEquals(1, actualResponse.getItemsMensajeRespuesta().size());
        Assert.assertEquals(StringUtils.EMPTY, actualResponse.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(), actualResponse.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertEquals(mensaje.getMensaje(), actualResponse.getItemsMensajeRespuesta().get(0).getMensaje());
        verify(debinRecurrenteDAO, times(1)).obtenerEmpresas(empresaBuscada);
        verify(debinRecurrenteDAO, times(1)).obtenerEmpresas(empresaBuscada.toUpperCase());
    }

    @Test
    public void obtenerEmpresasPrimeraBusquedaFail() throws Exception {

        //When
        String empresaBuscada = "empresa";
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("ERROR BUSCADOR");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_GENERICO)).thenReturn(mensaje);
        when(debinRecurrenteDAO.obtenerEmpresas(empresaBuscada))
                .thenThrow(new DAOException("Error en el servicio DEBIN API /sellers/searchByName"));

        //Then
        Respuesta<EmpresasDebinRecurrenteView> actualResponse = buscadorEmpresaBO.obtenerEmpresas(empresaBuscada);

        //Expected
        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(EstadoRespuesta.ERROR, actualResponse.getEstadoRespuesta());
        Assert.assertTrue(actualResponse.isRespuestaVacia());
        Assert.assertEquals(1, actualResponse.getItemsMensajeRespuesta().size());
        Assert.assertEquals(StringUtils.EMPTY, actualResponse.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(), actualResponse.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertEquals(mensaje.getMensaje(), actualResponse.getItemsMensajeRespuesta().get(0).getMensaje());
        verify(debinRecurrenteDAO, times(1)).obtenerEmpresas(empresaBuscada);
    }

    @Test
    public void obtenerEmpresasSinCoincidencias() throws Exception {

        //When
        String empresaBuscada = "empresa";
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("No se encontraron resultados");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.DEBINREC_NO_SELLERS_FOUND)).thenReturn(mensaje);
        when(debinRecurrenteDAO.obtenerEmpresas(empresaBuscada))
                .thenReturn(new ArrayList<SellerWithCategoryDTO>());
        when(debinRecurrenteDAO.obtenerEmpresas(empresaBuscada.toUpperCase()))
                .thenReturn(new ArrayList<SellerWithCategoryDTO>());

        //Then
        Respuesta<EmpresasDebinRecurrenteView> actualResponse = buscadorEmpresaBO.obtenerEmpresas(empresaBuscada);

        //Expected
        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(EstadoRespuesta.WARNING, actualResponse.getEstadoRespuesta());
        Assert.assertTrue(actualResponse.isRespuestaVacia());
        Assert.assertEquals(1, actualResponse.getItemsMensajeRespuesta().size());
        Assert.assertEquals(StringUtils.EMPTY, actualResponse.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals(TipoError.WARNING.getDescripcion(), actualResponse.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertEquals(mensaje.getMensaje(), actualResponse.getItemsMensajeRespuesta().get(0).getMensaje());
        verify(debinRecurrenteDAO, times(1)).obtenerEmpresas(empresaBuscada);
        verify(debinRecurrenteDAO, times(1)).obtenerEmpresas(empresaBuscada.toUpperCase());
    }

    @Test
    public void obtenerServiciosOk() throws Exception {

        //When
        String cuitEmpresa = "uncuit";
        SellerWithProvisionDTO mockedSellerWithProvisions = DebinRecurrenteApiMock.getDefaultSellerWithProvisions(cuitEmpresa);
        when(debinRecurrenteDAO.obtenerServicios(cuitEmpresa))
                .thenReturn(mockedSellerWithProvisions);

        //Then
        Respuesta<SellerWithProvisionDTO> actualResponse = buscadorEmpresaBO.obtenerServicios(cuitEmpresa);

        //Expected
        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(mockedSellerWithProvisions.getServicios().size(), actualResponse.getRespuesta().getServicios().size());
        verify(debinRecurrenteDAO, times(1)).obtenerServicios(cuitEmpresa);
    }

    @Test
    public void obtenerServiciosFail() throws Exception {

        //When
        String cuitEmpresa = "uncuit";
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("ERROR BUSCADOR");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_GENERICO)).thenReturn(mensaje);
        when(debinRecurrenteDAO.obtenerServicios(cuitEmpresa))
                .thenThrow(new DAOException("Error en el servicio DEBIN API /sellers/cuit/provisions"));

        //Then
        Respuesta<SellerWithProvisionDTO> actualResponse = buscadorEmpresaBO.obtenerServicios(cuitEmpresa);

        //Expected
        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(EstadoRespuesta.ERROR, actualResponse.getEstadoRespuesta());
        Assert.assertTrue(actualResponse.isRespuestaVacia());
        Assert.assertEquals(1, actualResponse.getItemsMensajeRespuesta().size());
        Assert.assertEquals(StringUtils.EMPTY, actualResponse.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals(TipoError.ERROR_GENERICO.getDescripcion(), actualResponse.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertEquals("ERROR BUSCADOR", actualResponse.getItemsMensajeRespuesta().get(0).getMensaje());
        verify(debinRecurrenteDAO, times(1)).obtenerServicios(cuitEmpresa);
    }
}
