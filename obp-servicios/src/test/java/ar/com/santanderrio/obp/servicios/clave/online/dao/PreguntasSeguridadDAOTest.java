/**
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
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
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clave.online.dao.impl.PreguntasSeguridadDAOImpl;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntasSeguridadResponse;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorCicsException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorDb2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorModuloException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.FuncionInvalidaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.HayRespuestasErroneasException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.ErrorRutinaFechasException;

/**
 * The Class PreguntasSeguridadDAOTest.
 *
 * @author
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		ar.com.santanderrio.obp.servicios.clave.online.dao.PreguntasSeguridadDAOTest.PreguntasSeguridadDAOTestConfiguration.class })
public class PreguntasSeguridadDAOTest extends IatxBaseDAOTest {
	

    /** The PreguntasSeguridad DAO. */
    @Autowired
    @InjectMocks
    private PreguntasSeguridadDAO preguntasSeguridadDAO;

    /**
     * The Class PreguntasSeguridadDAOTestConfiguration.
     */
    @Configuration
    public static class PreguntasSeguridadDAOTestConfiguration {

        /**
         * PreguntasSeguridad DAO.
         *
         * @return the Preguntas SeguridadDAO
         */
        @Bean
        public PreguntasSeguridadDAO preguntasSeguridadDAO() {
            return new PreguntasSeguridadDAOImpl();
        }
    }

    /**
     * Consultar.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     * @throws ErrorModuloException 
     * @throws FuncionInvalidaException 
     * @throws ErrorCicsException 
     * @throws ErrorDb2Exception 
     * @throws ErrorRutinaFechasException 
     * @throws HayRespuestasErroneasException 
     */
    @Test
    public void preguntasSeguridad() throws IatxException, DAOException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException, HayRespuestasErroneasException {

    	
    	IdentificadorClienteInEntity cliente = new IdentificadorClienteInEntity();
    	cliente.setDni("22424141");
    	cliente.setIp("224.241.412.242");
    	String servicio = "ACTSGIDESF";
        String version = "100";
        String tramaResponse = "200000000000P04HTML00099000104FREEUSER  ********0049295900000008201703091623"
        		+ "4000000000IBF38379000000000000ACTSGIDESF1000000            01390639    00        0  "
        		+ "016222476201703091623350000000000                            00000000000000000000000000000"
        		+ "00000000000000000000000000000000000000000000000000000000000000000000DH"
        		+ "õ0133900000000"
        		+ "õ3"
        		+ "õB05"
        		+ "õ&iquest;Cu&aacute;l es la fecha de vencimiento de tu tarjeta Santander R&iacute;o d&eacute;bito terminada en 3719?                                                                  "
        		+ "õB02"
        		+ "õ&iquest;Cu&aacute;l de las terminaciones de tel&eacute;fono actuales o anteriores reconoc&eacute;s como propia?                                                                     "
        		+ "õB01"
        		+ "õ&iquest;Cu&aacute;l es tu fecha de nacimiento?                                                                                                                                      "
        		+ "õ3"
        		+ "õ07/13                                             "
        		+ "õ09/17                                             "
        		+ "õ10/14                                             "
        		+ "õ03/14                                             "
        		+ "õNINGUNO                                           "
        		+ "õ2"
        		+ "õXXXXXXX-3399                                      "
        		+ "õXXXXXXX-6598                                      "
        		+ "õXXXXXXX-4789                                      "
        		+ "õXXXXXXX-6547                                      "
        		+ "õNINGUNA                                           "
        		+ "õ2õ16-02-1931                                        õ16-05-1957                                        õ07-05-1968                                        õ02-07-1967                                        õNINGUNA                                           õ2õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        PreguntasSeguridadResponse respuesta = preguntasSeguridadDAO.obtenerPreguntasSeguridad(cliente);
        
        assertNotNull(respuesta);
        verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
        assertEquals(respuesta.getCantidadPreguntas(),new Integer(3)) ;
        String opcion2 = "XXXXXXX-6598                                      ";
        assertEquals(respuesta.getRespuestas().get(1).getOpcion2(),opcion2  );
        String pregunta = "&iquest;Cu&aacute;l de las terminaciones de tel&eacute;fono actuales o anteriores reconoc&eacute;s como propia?                                                                     ";
        assertEquals(respuesta.getPreguntas().get(1).getTexto(),pregunta);
    }

}
