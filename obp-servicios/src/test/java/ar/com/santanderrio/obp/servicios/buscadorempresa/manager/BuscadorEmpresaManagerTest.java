package ar.com.santanderrio.obp.servicios.buscadorempresa.manager;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.SellerWithCategoryDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.SellerWithProvisionDTO;
import ar.com.santanderrio.obp.servicios.debinrecurrente.mock.DebinRecurrenteApiMock;
import ar.com.santanderrio.obp.servicios.debinrecurrente.mock.DebinRecurrenteArgMatcherMock;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.EmpresasDebinRecurrenteView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.VendedorPrestacionesView;
import ar.com.santanderrio.obp.servicios.debinrecurrente.view.VendedorView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.buscador.bo.BuscadorEmpresaBO;
import ar.com.santanderrio.obp.servicios.comun.buscador.web.manager.impl.BuscadorEmpresaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.pagos.entities.BuscadorEmpresaRta;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.EmpresaRecargaCelularView;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author mariano.g.pulera
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class BuscadorEmpresaManagerTest {

    @InjectMocks
    private BuscadorEmpresaManagerImpl buscadorEmpresaManager;

    @Mock
    private RespuestaFactory respuestaFactory;
   
    @Mock
    private BuscadorEmpresaBO buscadorEmpresaBO;
    
    @Mock
    private EstadisticaManager estadisticaManager;
    
    @Test
    public void searchByRubroEmpresa() throws DAOException {
        
        //When
        Respuesta<BuscadorEmpresaRta> respuesta = new Respuesta<BuscadorEmpresaRta>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        
        when(buscadorEmpresaBO.search(Matchers.anyString())).thenReturn(respuesta);
        
        //Then
        Respuesta<BuscadorEmpresaRta> respuestaView = buscadorEmpresaManager.searchByRubroEmpresa("rec");
        
        //Expected
        Assert.assertNotNull(respuestaView);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
        
    }
    
    
    @Test
    public void searchEmpresaPagoAutomatico() throws DAOException {
        
        //When
        Respuesta<BuscadorEmpresaRta> respuesta = new Respuesta<BuscadorEmpresaRta>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        
        when(buscadorEmpresaBO.searchPagoAutomatico(Matchers.anyString())).thenReturn(respuesta);
        
        //Then
        Respuesta<BuscadorEmpresaRta> respuestaView = buscadorEmpresaManager.searchEmpresaPagoAutomatico("rec");
        
        //Expected
        Assert.assertNotNull(respuestaView);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
        
    }
    
    
    @Test
    public void getByCodigo() throws DAOException {
        
        //When
        Respuesta<MedioPagoView> respuesta = new Respuesta<MedioPagoView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        
        when(buscadorEmpresaBO.getByCodigo(Matchers.anyString())).thenReturn(respuesta);
        
        //Then
        Respuesta<MedioPagoView> respuestaView = buscadorEmpresaManager.getByCodigo("rec");
        
        //Expected
        Assert.assertNotNull(respuestaView);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
        
    }
    
    
    @Test
    public void searchEmpresaRecargaCelulares() throws DAOException {
        
        //When
        Respuesta<EmpresaRecargaCelularView> respuesta = new Respuesta<EmpresaRecargaCelularView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        
        when(buscadorEmpresaBO.searchEmpresaRecargaCelulares()).thenReturn(respuesta);
        
        //Then
        Respuesta<EmpresaRecargaCelularView> respuestaView = buscadorEmpresaManager.searchEmpresaRecargaCelulares();
        
        //Expected
        Assert.assertNotNull(respuestaView);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
        
    }
    
    
    @Test
    public void searchEmpresaDebitoAutomaticoEnTarjeta() throws DAOException {
        
        //When
        Respuesta<EmpresaDebitoAutomaticoTarjetaView> respuesta = new Respuesta<EmpresaDebitoAutomaticoTarjetaView>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        
        when(buscadorEmpresaBO.searchEmpresaDebitoAutomaticoEnTarjeta(Matchers.anyString())).thenReturn(respuesta);
        
        //Then
        Respuesta<EmpresaDebitoAutomaticoTarjetaView> respuestaView = buscadorEmpresaManager.searchEmpresaDebitoAutomaticoEnTarjeta("bil");
        
        //Expected
        Assert.assertNotNull(respuestaView);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaView.getEstadoRespuesta());
        
    }

    @Test
    public void obtenerEmpresasOk() {

        //When
        String empresaBuscada = "empresa";
        List<SellerWithCategoryDTO> sellerList = DebinRecurrenteApiMock.getDefaultSellers();
        List<VendedorView> sellerListView = DebinRecurrenteApiMock.getDefaultVendedoresView(sellerList);
        EmpresasDebinRecurrenteView empresasDebinRecurrenteView = new EmpresasDebinRecurrenteView();
        empresasDebinRecurrenteView.setEmpresas(sellerListView);
        Respuesta<EmpresasDebinRecurrenteView> mockedBOResponse = new Respuesta<EmpresasDebinRecurrenteView>();
        mockedBOResponse.setRespuesta(empresasDebinRecurrenteView);
        mockedBOResponse.setEstadoRespuesta(EstadoRespuesta.OK);
        when(buscadorEmpresaBO.obtenerEmpresas(empresaBuscada)).thenReturn(mockedBOResponse);

        //Then
        Respuesta<EmpresasDebinRecurrenteView> actualResponse = buscadorEmpresaManager.obtenerEmpresas(empresaBuscada);

        //Expected
        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(mockedBOResponse.getEstadoRespuesta(), actualResponse.getEstadoRespuesta());
        Assert.assertEquals(mockedBOResponse.isRespuestaVacia(), actualResponse.isRespuestaVacia());
        Assert.assertEquals(mockedBOResponse.getRespuesta().getEmpresas().size(), actualResponse.getRespuesta().getEmpresas().size());
        Assert.assertEquals(mockedBOResponse.getRespuesta().getMensajeError(), actualResponse.getRespuesta().getMensajeError());
        Assert.assertEquals(mockedBOResponse.getRespuesta().getMensajeNoPermitePago(), actualResponse.getRespuesta().getMensajeNoPermitePago());
        Assert.assertEquals(mockedBOResponse.getRespuesta().getMensajeImporteLimite(), actualResponse.getRespuesta().getMensajeImporteLimite());
        Assert.assertEquals(mockedBOResponse.getRespuesta().getPagoComprasAyuda(), actualResponse.getRespuesta().getPagoComprasAyuda());
        Assert.assertEquals(mockedBOResponse.getRespuesta().getMensajeInformacionPagoAdebitar(), actualResponse.getRespuesta().getMensajeInformacionPagoAdebitar());
        verify(buscadorEmpresaBO, times(1)).obtenerEmpresas(empresaBuscada);
        verify(estadisticaManager, times(1)).add(EstadisticasConstants.CONSULTA_EMPRESAS_RECURRENCIA, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    @Test
    public void obtenerEmpresasOkWithNoResults() {

        //When
        String empresaBuscada = "empresa";
        List<SellerWithCategoryDTO> sellerList = new ArrayList<SellerWithCategoryDTO>();
        List<VendedorView> sellerListView = DebinRecurrenteApiMock.getDefaultVendedoresView(sellerList);
        EmpresasDebinRecurrenteView empresasDebinRecurrenteView = new EmpresasDebinRecurrenteView();
        empresasDebinRecurrenteView.setEmpresas(sellerListView);
        Respuesta<EmpresasDebinRecurrenteView> mockedBOResponse = new Respuesta<EmpresasDebinRecurrenteView>();
        mockedBOResponse.setRespuesta(empresasDebinRecurrenteView);
        mockedBOResponse.setEstadoRespuesta(EstadoRespuesta.WARNING);
        when(buscadorEmpresaBO.obtenerEmpresas(empresaBuscada)).thenReturn(mockedBOResponse);

        //Then
        Respuesta<EmpresasDebinRecurrenteView> actualResponse = buscadorEmpresaManager.obtenerEmpresas(empresaBuscada);

        //Expected
        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(mockedBOResponse.getEstadoRespuesta(), actualResponse.getEstadoRespuesta());
        Assert.assertEquals(mockedBOResponse.isRespuestaVacia(), actualResponse.isRespuestaVacia());
        Assert.assertEquals(mockedBOResponse.getRespuesta().getEmpresas().size(), actualResponse.getRespuesta().getEmpresas().size());
        Assert.assertEquals(mockedBOResponse.getRespuesta().getMensajeError(), actualResponse.getRespuesta().getMensajeError());
        Assert.assertEquals(mockedBOResponse.getRespuesta().getMensajeNoPermitePago(), actualResponse.getRespuesta().getMensajeNoPermitePago());
        Assert.assertEquals(mockedBOResponse.getRespuesta().getMensajeImporteLimite(), actualResponse.getRespuesta().getMensajeImporteLimite());
        Assert.assertEquals(mockedBOResponse.getRespuesta().getPagoComprasAyuda(), actualResponse.getRespuesta().getPagoComprasAyuda());
        Assert.assertEquals(mockedBOResponse.getRespuesta().getMensajeInformacionPagoAdebitar(), actualResponse.getRespuesta().getMensajeInformacionPagoAdebitar());
        verify(buscadorEmpresaBO, times(1)).obtenerEmpresas(empresaBuscada);
        verify(estadisticaManager, times(1)).add(EstadisticasConstants.CONSULTA_EMPRESAS_RECURRENCIA, EstadisticasConstants.CODIGO_ESTADISTICAS_WARNING);
    }

    @Test
    public void obtenerEmpresasFail() {

        //When
        String empresaBuscada = "empresa";
        Respuesta<EmpresasDebinRecurrenteView> mockedBOResponse = new Respuesta<EmpresasDebinRecurrenteView>();
        mockedBOResponse.setRespuesta(new EmpresasDebinRecurrenteView());
        mockedBOResponse.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ItemMensajeRespuesta itemRespuesta = new ItemMensajeRespuesta();
        itemRespuesta.setTag(StringUtils.EMPTY);
        itemRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        itemRespuesta.setMensaje("message string from db");
        mockedBOResponse.setRespuestaVacia(true);
        mockedBOResponse.add(itemRespuesta);
        when(buscadorEmpresaBO.obtenerEmpresas(empresaBuscada)).thenReturn(mockedBOResponse);

        //Then
        Respuesta<EmpresasDebinRecurrenteView> actualResponse = buscadorEmpresaManager.obtenerEmpresas(empresaBuscada);

        //Expected
        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(mockedBOResponse.getEstadoRespuesta(), actualResponse.getEstadoRespuesta());
        Assert.assertTrue(actualResponse.isRespuestaVacia());
        Assert.assertEquals(mockedBOResponse.getItemsMensajeRespuesta().size(), actualResponse.getItemsMensajeRespuesta().size());
        Assert.assertEquals(mockedBOResponse.getItemsMensajeRespuesta().get(0).getTag(), actualResponse.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals(mockedBOResponse.getItemsMensajeRespuesta().get(0).getTipoError(), actualResponse.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertEquals(mockedBOResponse.getItemsMensajeRespuesta().get(0).getMensaje(), actualResponse.getItemsMensajeRespuesta().get(0).getMensaje());
        verify(buscadorEmpresaBO, times(1)).obtenerEmpresas(empresaBuscada);
        verify(estadisticaManager, times(1)).add(EstadisticasConstants.CONSULTA_EMPRESAS_RECURRENCIA, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }

    @Test
    public void obtenerServiciosOk() {
        String cuitEmpresa = "uncuit";
        testingObtenerServiciosOK( DebinRecurrenteApiMock.getDefaultSellerWithProvisions(cuitEmpresa),
                                  cuitEmpresa,
                                  false);

    }

    @Test
    public void obtenerServiciosOk_empty() {
        String cuitEmpresa = "uncuit";

        testingObtenerServiciosOK(DebinRecurrenteApiMock.getDefaultSellerWithoutProvisions(cuitEmpresa),
                cuitEmpresa,
                true);
    }

    private void testingObtenerServiciosOK(  SellerWithProvisionDTO mockedBOResponse,String cuitEmpresa,
            boolean respuestaVacia){
        //When
        Respuesta<SellerWithProvisionDTO> sellerWithProvisions = new Respuesta<SellerWithProvisionDTO>();
        sellerWithProvisions.setRespuesta(mockedBOResponse);
        sellerWithProvisions.setEstadoRespuesta(EstadoRespuesta.OK);
        when(buscadorEmpresaBO.obtenerServicios(cuitEmpresa)).thenReturn(sellerWithProvisions);

        VendedorPrestacionesView mockVendedorPrestacionesView = DebinRecurrenteApiMock.getDefaultVendedorPrestacionesView(mockedBOResponse);
        ArgumentMatcher<VendedorPrestacionesView> matcher = DebinRecurrenteArgMatcherMock.listaMismoVendedorPrestaciones(mockVendedorPrestacionesView);
        Respuesta<VendedorPrestacionesView> mockedManagerResponse = new Respuesta<VendedorPrestacionesView>();
        mockedManagerResponse.setRespuesta(mockVendedorPrestacionesView);
        mockedManagerResponse.setEstadoRespuesta(EstadoRespuesta.OK);
        when(respuestaFactory.crearRespuestaOk(argThat(matcher))).thenReturn(mockedManagerResponse);

        //Then
        Respuesta<VendedorPrestacionesView> actualResponse = buscadorEmpresaManager.obtenerServicios(cuitEmpresa);

        //Expected
        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(EstadoRespuesta.OK, actualResponse.getEstadoRespuesta());
        Assert.assertEquals(mockedManagerResponse.getRespuesta().getCuit(), actualResponse.getRespuesta().getCuit());
        Assert.assertEquals(mockedManagerResponse.getRespuesta().getNombreFantasia(), actualResponse.getRespuesta().getNombreFantasia());
        Assert.assertEquals(mockedManagerResponse.getRespuesta().getServicios().size(), actualResponse.getRespuesta().getServicios().size());
        Assert.assertEquals(respuestaVacia, actualResponse.isRespuestaVacia());
        verify(buscadorEmpresaBO, times(1)).obtenerServicios(cuitEmpresa);
    }

    @Test
    public void obtenerServiciosFail() {

        //When
        String cuitEmpresa = "uncuit";
        Respuesta<SellerWithProvisionDTO> sellerWithProvisions = new Respuesta<SellerWithProvisionDTO>();
        sellerWithProvisions.setRespuesta(new SellerWithProvisionDTO());
        sellerWithProvisions.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(buscadorEmpresaBO.obtenerServicios(cuitEmpresa)).thenReturn(sellerWithProvisions);

        ItemMensajeRespuesta itemRespuesta = new ItemMensajeRespuesta();
        itemRespuesta.setTag(StringUtils.EMPTY);
        itemRespuesta.setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        itemRespuesta.setMensaje("message string from db");
        Respuesta<VendedorPrestacionesView> mockedManagerResponse = new Respuesta<VendedorPrestacionesView>();
        mockedManagerResponse.setEstadoRespuesta(EstadoRespuesta.ERROR);
        mockedManagerResponse.setRespuestaVacia(true);
        mockedManagerResponse.add(itemRespuesta);
        when(respuestaFactory.<VendedorPrestacionesView>crearRespuestaError(
                StringUtils.EMPTY,
                TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.ERROR_GENERICO)
        ).thenReturn(mockedManagerResponse);

        //Then
        Respuesta<VendedorPrestacionesView> actualResponse = buscadorEmpresaManager.obtenerServicios(cuitEmpresa);

        //Expected
        Assert.assertNotNull(actualResponse);
        Assert.assertEquals(mockedManagerResponse.getEstadoRespuesta(), actualResponse.getEstadoRespuesta());
        Assert.assertTrue(actualResponse.isRespuestaVacia());
        Assert.assertEquals(mockedManagerResponse.getItemsMensajeRespuesta().size(), actualResponse.getItemsMensajeRespuesta().size());
        Assert.assertEquals(mockedManagerResponse.getItemsMensajeRespuesta().get(0).getTag(), actualResponse.getItemsMensajeRespuesta().get(0).getTag());
        Assert.assertEquals(mockedManagerResponse.getItemsMensajeRespuesta().get(0).getTipoError(), actualResponse.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertEquals(mockedManagerResponse.getItemsMensajeRespuesta().get(0).getMensaje(), actualResponse.getItemsMensajeRespuesta().get(0).getMensaje());
        verify(buscadorEmpresaBO, times(1)).obtenerServicios(cuitEmpresa);
    }
}
