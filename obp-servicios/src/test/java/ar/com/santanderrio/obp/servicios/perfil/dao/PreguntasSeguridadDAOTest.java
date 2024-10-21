package ar.com.santanderrio.obp.servicios.perfil.dao;

import static org.mockito.Mockito.when;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.perfil.dao.impl.PreguntasSeguridadPerfilDAOImpl;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaPreguntasSeguridadInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaPreguntasSeguridadOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosComprobanteEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ModificacionPreguntasSeguridadInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ModificacionPreguntasSeguridadOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.RespuestaSeguridadEntity;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		ar.com.santanderrio.obp.servicios.perfil.dao.PreguntasSeguridadDAOTest.PreguntasSeguridadDAOTestConfiguration.class })
public class PreguntasSeguridadDAOTest extends IatxBaseDAOTest {
    
    /** The PreguntasSeguridadPerfilDAO. */
    @Autowired
    @InjectMocks
    private PreguntasSeguridadPerfilDAO preguntasSeguridadDAO;
    
    /** The app context. */
    ApplicationContext appContext = new ClassPathXmlApplicationContext();
    
    
    /** The cliente. */
    private Cliente cliente = new Cliente();
    

    @Configuration
    public static class PreguntasSeguridadDAOTestConfiguration {

        /**
         * PreguntasSeguridadPerfilDAO.
         *
         * @return the PreguntasSeguridadPerfilDAO
         */
        @Bean
        public PreguntasSeguridadPerfilDAO preguntasSeguridadDAO() {
            return new PreguntasSeguridadPerfilDAOImpl();
        }

    }

    /**
     * Inits.
     */
    @Before
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        cliente = new Cliente();
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        cliente.setNombre("CONSTANCIO PERCY");
        cliente.setApellido1("MILANDO");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);        
    }
   
    @Test
    public void consultarPreguntasSeguridadTest() throws IatxException, DAOException{
        String servicio = "CNSNPHRECU";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010302GLPE92  ********00220132000000062017071717343500000000IBF00BXH000000000000CNSNPHRECU1000000            02615492    00        00 017312230201707171734260000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0072700000000õC õ13õ050õ"+
                               "Apellido de mi madreõ051õCanci¢n favoritaõ052õFECHA DE CASAMIENTO (DD/MM/AAAA)õ053õLugar favorito de vacacionesõ054õMarca y modelo de mi primer autoõ055õNombre de mi abuela paternaõ056õNombre de mi abuelo paternoõ057õNombre de mi colegio secundarioõ058õNombre de mi mam õ059õNombre de mi mascotaõ060õNombre de mi primer novio/noviaõ061õObra de arte favoritaõ062õPel¡cula favoritaõ"+
                               "000õõ000õõ000õõ000õõ000õõ000õõ000õõ000õõ000õõ000õõ000õõ000õõ000õõ000õõ000õõ000õõ000õõ00õ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ000õ00õõ";
        ConsultaPreguntasSeguridadInEntity consultaPreguntasSeguridadInEntity = new ConsultaPreguntasSeguridadInEntity();
        consultaPreguntasSeguridadInEntity.setCliente(cliente);
        consultaPreguntasSeguridadInEntity.setRecuperarRespuestas("N");
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ConsultaPreguntasSeguridadOutEntity respuesta = preguntasSeguridadDAO.consultaPreguntasSeguridad(consultaPreguntasSeguridadInEntity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }
    
    
    @Test
    public void guardarPreguntasSeguridadTest() throws IatxException, DAOException{
        String servicio = "ACTNPHRECU";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010302GLPE92  ********00220132000000072017071717350400000000IBF00BXX000000000000ACTNPHRECU1000000            02615492    00        00 017312393201707171734550000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0000900000000õ";
        
        ModificacionPreguntasSeguridadInEntity modificacionPreguntasSeguridadInEntity = new ModificacionPreguntasSeguridadInEntity();
        modificacionPreguntasSeguridadInEntity.setCliente(cliente);
        modificacionPreguntasSeguridadInEntity.setCantidadPreguntas("5");
        List<RespuestaSeguridadEntity> respuestasSeguridad = new ArrayList<RespuestaSeguridadEntity>();
        RespuestaSeguridadEntity r1 = new RespuestaSeguridadEntity();
        r1.setDescripcionRespuesta("143A301A304A273A265A36A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315AA");
        r1.setIdPregunta("050");
        RespuestaSeguridadEntity r2 = new RespuestaSeguridadEntity();
        r2.setDescripcionRespuesta("143A301A304A273A265A36A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315AA");
        r2.setIdPregunta("051");
        RespuestaSeguridadEntity r3 = new RespuestaSeguridadEntity();
        r3.setDescripcionRespuesta("143A301A304A273A265A36A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315AA");
        r3.setIdPregunta("052");
        RespuestaSeguridadEntity r4 = new RespuestaSeguridadEntity();
        r4.setDescripcionRespuesta("143A301A304A273A265A36A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315AA");
        r4.setIdPregunta("053");
        RespuestaSeguridadEntity r5 = new RespuestaSeguridadEntity();
        r5.setDescripcionRespuesta("143A301A304A273A265A36A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315AA");
        r5.setIdPregunta("054");
        respuestasSeguridad.add(r1);
        respuestasSeguridad.add(r2);
        respuestasSeguridad.add(r3);
        respuestasSeguridad.add(r4);
        respuestasSeguridad.add(r5);
        modificacionPreguntasSeguridadInEntity.setRespuestasSeguridad(respuestasSeguridad);
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ModificacionPreguntasSeguridadOutEntity respuesta = preguntasSeguridadDAO.guardarPreguntasSeguridad(modificacionPreguntasSeguridadInEntity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }
   
    /**
     * Test reporte.
     *
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    
    @Test
    public void testReporte() throws IOException, IllegalAccessException {
        DatosComprobanteEntity view = new DatosComprobanteEntity();
        view.setFecha("21/07/2009");
        view.setNroComprobante("123456789");
        FieldUtils.writeField(preguntasSeguridadDAO, "logoCabecera", appContext.getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
        FieldUtils.writeField(preguntasSeguridadDAO, "logoCierre", appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"), true);
        FieldUtils.writeField(preguntasSeguridadDAO, "fileJasperComprobantePreguntasSeguridad",
                appContext.getResource("classpath:/report/perfil/preguntasSeguridad/comprobantePreguntasSeguridad.jasper"), true);
        Reporte reporte = preguntasSeguridadDAO.descargarComprobante(view);
        Assert.assertNotNull(reporte.getBytes());

    }
    
}
