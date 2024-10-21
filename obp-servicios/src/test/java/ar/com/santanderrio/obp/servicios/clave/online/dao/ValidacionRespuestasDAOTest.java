/**
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.dao;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clave.online.dao.impl.ValidacionRespuestasDAOImpl;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.ValidacionPreguntaIn;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteBloqueado2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorCicsException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorDb2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorModuloException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.FuncionInvalidaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.HayRespuestasErroneasException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ValidacionRespuestaNoReintentarException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ValidacionRespuestaReintentarException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.ErrorRutinaFechasException;

/**
 * The Class PreguntasSeguridadDAOTest.
 *
 * @author
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ValidacionRespuestasDAOTest.ValidacionRespuestasDAOTestConfiguration.class })
public class ValidacionRespuestasDAOTest extends IatxBaseDAOTest {

    /** The ValidacionRespuestas DAO. */
    @Autowired
    @InjectMocks
    private ValidacionRespuestasDAO validacionRespuestasDAO;
    
    /**
     * The Class ValidacionRespuestasDAOTestConfiguration.
     */
    @Configuration
    public static class ValidacionRespuestasDAOTestConfiguration {

        /**
         * ValidacionRespuestas DAO.
         *
         * @return the ValidacionRespuestasDAO
         */
        @Bean
        public ValidacionRespuestasDAO validacionRespuestasDAO() {
            return new ValidacionRespuestasDAOImpl();
        }
    }

    /**
     * Consultar.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     * @throws ServiceException 
     * @throws ValidacionRespuestaNoReintentarException 
     * @throws ValidacionRespuestaReintentarException 
     * @throws ClienteBloqueado2Exception 
     * @throws ErrorModuloException 
     * @throws FuncionInvalidaException 
     * @throws ErrorCicsException 
     * @throws ErrorDb2Exception 
     * @throws ErrorRutinaFechasException 
     * @throws HayRespuestasErroneasException 
     */
    @Test
    public void validacionRespuestasTest() throws IatxException, DAOException, ServiceException, ValidacionRespuestaReintentarException, ValidacionRespuestaNoReintentarException, ClienteBloqueado2Exception, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException, HayRespuestasErroneasException {

    	
    	IdentificadorClienteInEntity cliente = new IdentificadorClienteInEntity();
    	cliente.setDni("22424141");
    	cliente.setIp("224.241.412.242");
    	String servicio = "VALSGIDESF";
        String version = "100";
        String tramaResponse = "200000000000P04HTML00099000104FREEUSER  ********00688869000000042016110717562400000000"
        		+ "IBF34820000000000000VALSGIDESF1000000            02639403    00        0  0000000002016110717561"
        		+ "80000000000                            000000000000000000000000000000000000000000000000000000000"
        		+ "0000000000000000000000000000000000000000DH"
        		+ "õ0000900000000õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ValidacionPreguntaIn validacionPreguntaIn = new ValidacionPreguntaIn();
        validacionPreguntaIn.setDni("22424141");
        validacionPreguntaIn.setIp("224.241.412.242");
        
        validacionRespuestasDAO.validarRespuesta(validacionPreguntaIn);
        
//        assertNotNull(respuesta);
//        verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
//        assertEquals(respuesta.getCantidadPreguntas(),new Integer(3)) ;
//        String opcion2 = "XXXXXXX-6598                                      ";
//        assertEquals(respuesta.getRespuestas().get(1).getOpcion2(),opcion2  );
//        String pregunta = "&iquest;Cu&aacute;l de las terminaciones de tel&eacute;fono actuales o anteriores reconoc&eacute;s como propia?                                                                     ";
//        assertEquals(respuesta.getPreguntas().get(1).getTexto(),pregunta);
    }

}
