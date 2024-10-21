package ar.com.santanderrio.obp.servicios.tarjetarecargable.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.dao.impl.TarjetaRecargableDAOImpl;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.entities.SolicitudTarjetaRecargableInEntity;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.entities.SolicitudTarjetaRecargableOutEntity;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.DatosComprobanteSolicitudTarjetaRecargableView;

@RunWith(MockitoJUnitRunner.class)
public class TarjetaRecargableDAOTest {
    
    @Mock
    private RespuestaFactory respuestaFactory;
    
    @InjectMocks
    @Spy
    private TarjetaRecargableDAOImpl tarjetaRecargableDAO = new TarjetaRecargableDAOImpl();

    /** The app context. */
    ApplicationContext appContext = new ClassPathXmlApplicationContext();

    @SuppressWarnings("unchecked")
    @Test
    public void altaSolicitudTarjetaRecargable() throws Exception {
        SolicitudTarjetaRecargableInEntity solicitudTarjetaRecargableInEntity = new SolicitudTarjetaRecargableInEntity();
        Map<String, Object> respuestaBD = obtenerRespuestaMapaDB(BigDecimal.ZERO, false);
        
        Respuesta<SolicitudTarjetaRecargableInEntity> respuestaEntity = new Respuesta<SolicitudTarjetaRecargableInEntity>();
        respuestaEntity.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.doReturn(respuestaBD).when(tarjetaRecargableDAO).consultarDB(Matchers.any(SqlParameterSource.class));
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(SolicitudTarjetaRecargableOutEntity.class))).thenReturn(respuestaEntity);
        
        Respuesta<SolicitudTarjetaRecargableOutEntity> respuesta = tarjetaRecargableDAO.altaSolicitudTarjetaRecargable(solicitudTarjetaRecargableInEntity);

        Assert.assertNotNull(respuesta);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void altaSolicitudTarjetaRecargableOkNull() throws Exception {
        SolicitudTarjetaRecargableInEntity solicitudTarjetaRecargableInEntity = new SolicitudTarjetaRecargableInEntity();
        Map<String, Object> respuestaBD = obtenerRespuestaMapaDB(BigDecimal.ZERO, true);
        
        Respuesta<SolicitudTarjetaRecargableInEntity> respuestaEntity = new Respuesta<SolicitudTarjetaRecargableInEntity>();
        respuestaEntity.setEstadoRespuesta(EstadoRespuesta.OK);

        Mockito.doReturn(respuestaBD).when(tarjetaRecargableDAO).consultar(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(), 
                Matchers.any(SqlParameterSource.class), Matchers.<SqlParameter>anyVararg());
        Mockito.doReturn(respuestaBD).when(tarjetaRecargableDAO).consultarFuncion(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(), 
                Matchers.any(SqlParameterSource.class), Matchers.<SqlParameter>anyVararg());
        Mockito.when(respuestaFactory.crearRespuestaOk(Matchers.any(Class.class), Matchers.any(SolicitudTarjetaRecargableOutEntity.class))).thenReturn(respuestaEntity);
        
        Respuesta<SolicitudTarjetaRecargableOutEntity> respuesta = tarjetaRecargableDAO.altaSolicitudTarjetaRecargable(solicitudTarjetaRecargableInEntity);

        Assert.assertNotNull(respuesta);
    }

    @Test
    public void altaSolicitudTarjetaRecargableDAOEx() throws DAOException, SQLException {
        SolicitudTarjetaRecargableInEntity solicitudTarjetaRecargableInEntity = new SolicitudTarjetaRecargableInEntity();
        Map<String, Object> respuestaBD = obtenerRespuestaMapaDB(BigDecimal.ONE, false);
        
        Mockito.doReturn(respuestaBD).when(tarjetaRecargableDAO).consultarDB(Matchers.any(SqlParameterSource.class));
        
        Respuesta<SolicitudTarjetaRecargableOutEntity> respuesta = new Respuesta<SolicitudTarjetaRecargableOutEntity>();
        
        try {
            respuesta = tarjetaRecargableDAO.altaSolicitudTarjetaRecargable(solicitudTarjetaRecargableInEntity);    
        } catch (Exception ex) {
            respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        }
        
        Assert.assertNotNull(respuesta);
    }
    
    @Test
    public void altaSolicitudTarjetaRecargableSQLEx() throws SQLException {
        Respuesta<SolicitudTarjetaRecargableOutEntity> respuesta = new Respuesta<SolicitudTarjetaRecargableOutEntity>();
        SolicitudTarjetaRecargableInEntity solicitudTarjetaRecargableInEntity = new SolicitudTarjetaRecargableInEntity();
        
        Mockito.doThrow(new SQLException()).when(tarjetaRecargableDAO).consultarDB(Matchers.any(SqlParameterSource.class));
        
        try {
            respuesta = tarjetaRecargableDAO.altaSolicitudTarjetaRecargable(solicitudTarjetaRecargableInEntity);    
        } catch (Exception ex) {
            respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        }
        
        Assert.assertNotNull(respuesta);
    }
    
    @Test
    public void altaSolicitudTarjetaRecargableEx() throws Exception {
        Respuesta<SolicitudTarjetaRecargableOutEntity> respuesta = new Respuesta<SolicitudTarjetaRecargableOutEntity>();
        SolicitudTarjetaRecargableInEntity solicitudTarjetaRecargableInEntity = new SolicitudTarjetaRecargableInEntity();
        
        Mockito.doThrow(new IllegalArgumentException("")).when(tarjetaRecargableDAO).consultarDB(Matchers.any(SqlParameterSource.class));
        
        try {
            respuesta = tarjetaRecargableDAO.altaSolicitudTarjetaRecargable(solicitudTarjetaRecargableInEntity);    
        } catch (Exception ex) {
            respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        }
        
        Assert.assertNotNull(respuesta);
    }
    
    @Test
    public void generarComprobante() throws Exception {
        Reporte reporte = null;
        DatosComprobanteSolicitudTarjetaRecargableView datosComprobante = new DatosComprobanteSolicitudTarjetaRecargableView();
        
        FieldUtils.writeField(tarjetaRecargableDAO, "logoCabecera", appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"),
                true);
        
        FieldUtils.writeField(tarjetaRecargableDAO, "fileJasperComprobanteSolicitudTarjetaRecargable",
                appContext.getResource("classpath:/report/tarjetarecargable/SolicitudTarjetaRecargable.jasper"), true);
        
        FieldUtils.writeField(tarjetaRecargableDAO, "logoCierre",
                appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
        
        FieldUtils.writeField(tarjetaRecargableDAO, "logoVisa",
                appContext.getResource("classpath:/report/comprobantes/logo_visa.png"), true);
        
        
        
        datosComprobante.setNombreAdic("Nombre");
        datosComprobante.setApellidoAdic("apellido");
        datosComprobante.setTipoDocumentoAdic("tipoDNI");
        datosComprobante.setNroDocumentoAdic("documento");
        datosComprobante.setFechaNacimientoAdic("fecha");
        datosComprobante.setNacionalidad("nacionalidad");
        datosComprobante.setSexo("sexo");
        datosComprobante.setEstadoCivil("estadoCivil");
        datosComprobante.setDomicilio("domicilio");
        datosComprobante.setNro("nro");
        datosComprobante.setPisoDepto("pisoDpto");
        datosComprobante.setLocalidadBarrio("localidad");
        datosComprobante.setCodigoPostal("codigoPostal");
        datosComprobante.setProvincia("provincia");
        datosComprobante.setCodArea("codigoArea");
        datosComprobante.setTelefono("telefono");
        datosComprobante.setNroGestion("gestion");
        datosComprobante.setFechaHora("Fecha");
        
        reporte = tarjetaRecargableDAO.comprobanteSolicitudTarjetaRecargable(datosComprobante);
        
        Assert.assertNotNull(reporte);
    }
    
    private Map<String, Object> obtenerRespuestaMapaDB(BigDecimal codRetorno, boolean isNull) {
        Map<String, Object> respuestaBD = new HashMap<String, Object>();
        respuestaBD.put("p_cod_error", codRetorno);
        respuestaBD.put("p_nro_gestion", isNull ? "" : "123");
        respuestaBD.put("p_desc_error", isNull ? "" : "error");
        return respuestaBD;
    }
}
