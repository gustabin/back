package ar.com.santanderrio.obp.servicios.prestamos.dao;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.comun.FilePath;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.impl.ArchivoDeRecursosDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoCanceladoMock;
import ar.com.santanderrio.obp.servicios.prestamos.dao.impl.PrestamoDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamosCanceladosOutEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		PrestamoDAOTest.PrestamoDAOTestConfiguration.class })
@TestPropertySource(properties = { 
		"SERVICIO.PREFIJO.PAGPMOCAAN = PAGPMOCAAN",
		"SERVICIO.VERSION.PAGPMOCAAN = 100"
		})
public class PrestamoDAOTest  extends IatxBaseDAOTest {
	

    /** The prestamo permitido DAO. */
    @Autowired
    @InjectMocks
    private PrestamoDAO prestamoDAO;

    
    /**
     * The Class TransferenciaDAOTestConfiguration.
     */
    @Configuration
    public static class PrestamoDAOTestConfiguration {

        /**
         * Prestamo permitido DAO.
         *
         * @return the prestamo permitido DAO
         */
        @Bean
        public PrestamoDAO PrestamoDAO() {
            return new PrestamoDAOImpl();
        }
        
        @Bean
        public ArchivoDeRecursosDAO ArchivoDeRecursosDAO() {
        	return new ArchivoDeRecursosDAOImpl();
        }
        
        @Bean
        public FilePath FilePath() {
        	return new FilePath();
        }
    }
    
    @Test
    public void cuandoConsultoPrestamosCerradosOk() throws IatxException, DAOException {
        String servicio = "CNSCONTCAN";
        String version = "120";
        String tramaResponse = (new PrestamoCanceladoMock()).getTrama();
        
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        PrestamosCanceladosOutEntity respuesta = prestamoDAO.obtenerCancelados(ClienteMock.completarInfoCliente());
        Assert.assertNotNull(respuesta);
    }

}
