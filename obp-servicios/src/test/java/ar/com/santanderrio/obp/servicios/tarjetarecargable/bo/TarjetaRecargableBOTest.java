package ar.com.santanderrio.obp.servicios.tarjetarecargable.bo;

import static org.mockito.BDDMockito.given;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.QueryTimeoutException;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.bo.impl.TarjetaRecargableBOImpl;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.dao.TarjetaRecargableDAO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.dto.DatosSolicitudTarjetaRecargableDTO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.dto.DatosSolicitudTarjetaRecargableInDTO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.entities.SolicitudTarjetaRecargableInEntity;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.entities.SolicitudTarjetaRecargableOutEntity;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.DatosComprobanteSolicitudTarjetaRecargableView;

@RunWith(MockitoJUnitRunner.class)
public class TarjetaRecargableBOTest {

    @InjectMocks
    private TarjetaRecargableBOImpl tarjetaRecargableBO = new TarjetaRecargableBOImpl();
    
    @Mock
    private TarjetaRecargableDAO tarjetaRecargableDAO;

    @Mock
    EstadisticaManager estadisticaManager;
    
    @Mock
    private MensajeBO mensajeBO;
    
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    private static final String LOGGER_ERROR = "Error al llamar a Store Procedure para solicitar una tarjeta recargable.";
    
    @Test
    public void altaSolicitudTarjetaRecargableOkTest()
            throws DAOException, QueryTimeoutException {
        SolicitudTarjetaRecargableOutEntity outEntity = new SolicitudTarjetaRecargableOutEntity();
        outEntity.setCodRetorno("0");
        outEntity.setNroGestion("999999999");
        Respuesta<SolicitudTarjetaRecargableOutEntity> respuestaDAO = new Respuesta<SolicitudTarjetaRecargableOutEntity>();
        respuestaDAO.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaDAO.setRespuesta(outEntity);
        
        DatosSolicitudTarjetaRecargableInDTO datosSolicitud = obtenerDatosSolicitudTarjetaRecargableInDTO();
        
        
        Mockito.when(
                tarjetaRecargableDAO.altaSolicitudTarjetaRecargable(Matchers.any(SolicitudTarjetaRecargableInEntity.class))).thenReturn(respuestaDAO);
        
        Respuesta<DatosSolicitudTarjetaRecargableDTO> respuestaDTO = tarjetaRecargableBO.altaSolicitudTarjetaRecargable(datosSolicitud);

        Assert.assertNotNull(respuestaDTO);
        Assert.assertEquals(EstadoRespuesta.OK, respuestaDTO.getEstadoRespuesta());
        Assert.assertNotNull(respuestaDTO.getRespuesta());
        Assert.assertEquals("999999999", respuestaDTO.getRespuesta().getNroGestion());
    }
    
    @Test
    public void altaSolicitudTarjetaRecargableDAOExceptionRespuestaNullTest() throws DAOException, QueryTimeoutException {

        given(tarjetaRecargableDAO.altaSolicitudTarjetaRecargable(Matchers.any(SolicitudTarjetaRecargableInEntity.class))).willThrow(new DAOException());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<DatosSolicitudTarjetaRecargableDTO> respuestaDTO = tarjetaRecargableBO.altaSolicitudTarjetaRecargable(obtenerDatosSolicitudTarjetaRecargableInDTO());
        Assert.assertNotNull(respuestaDTO);
        Assert.assertNotNull(respuestaDTO.getItemsMensajeRespuesta().get(0).getTipoError());
        Assert.assertEquals(respuestaDTO.getItemsMensajeRespuesta().get(0).getTipoError(), TipoError.ERROR_GENERICO.getDescripcion());
    }
    
    @Test
    public void comprobanteSolicitudTarjetaRecargableOkTest() {
        DatosComprobanteSolicitudTarjetaRecargableView comprobanteInView = new DatosComprobanteSolicitudTarjetaRecargableView();
        Reporte reporteDAO = new Reporte();
        Mockito.when(tarjetaRecargableDAO.comprobanteSolicitudTarjetaRecargable(Matchers.any(DatosComprobanteSolicitudTarjetaRecargableView.class))).thenReturn(reporteDAO);
        Respuesta<Reporte> res = tarjetaRecargableBO.comprobanteSolicitudTarjetaRecargable(comprobanteInView);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertNotNull(res.getRespuesta());
    }
    
    
    private DatosSolicitudTarjetaRecargableInDTO obtenerDatosSolicitudTarjetaRecargableInDTO(){
        DatosSolicitudTarjetaRecargableInDTO datosSolicitud = new DatosSolicitudTarjetaRecargableInDTO();
        datosSolicitud.setApellido1Cliente("test");
        datosSolicitud.setApellido2Cliente("test");
        datosSolicitud.setApellidoAdic("test");
        datosSolicitud.setCodArea("test");
        datosSolicitud.setCircuito("test");
        datosSolicitud.setCodigoAplicacion("test");
        datosSolicitud.setCodigoPostal("test");
        datosSolicitud.setComentario("test");
        datosSolicitud.setDomicilio("");
        datosSolicitud.setEmail("");
        datosSolicitud.setEstadoCivil("");
        datosSolicitud.setFechaNacimientoAdic("fechanacimientocliente");
        datosSolicitud.setFechaNacimientoCliente("fechanacimientocliente");
        datosSolicitud.setLocalidadBarrio("");
        datosSolicitud.setMonedaAltair("");
        datosSolicitud.setNacionalidad("");
        datosSolicitud.setNombreAdic("");
        datosSolicitud.setNombreCliente("");
        datosSolicitud.setNro("");
        datosSolicitud.setNroCuentaProducto("");
        datosSolicitud.setNroDocumentoAdic("");
        datosSolicitud.setNroDocumentoCliente("");
        datosSolicitud.setNroOrdenFirmante("");
        datosSolicitud.setNroSucursal4("");
        datosSolicitud.setNupCliente("");
        datosSolicitud.setPisoDepto("");
        datosSolicitud.setProductoAltair("");
        datosSolicitud.setProvincia("");
        datosSolicitud.setSexo("");
        datosSolicitud.setSubproductoAltair("");
        datosSolicitud.setTelefono("");
        datosSolicitud.setTipoDocumentoAdic("");
        datosSolicitud.setTipoDocumentoCliente("");
        return datosSolicitud;
    }
    
}
