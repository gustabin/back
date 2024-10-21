package ar.com.santanderrio.obp.servicios.descuento.cheques.dao;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.impl.ArchivoDeRecursosDAOImpl;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.impl.EliminarOperacionDAOImpl;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.EliminarOperacionEntity;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		EliminarOperacionDAOTest.EliminarOperacionDAOTestConfiguration.class })
public class EliminarOperacionDAOTest extends IatxBaseDAOTest{

	@Autowired
	EliminarOperacionDAO eliminarOperacionDAO;
	
	@InjectMocks
    ArchivoDeRecursosDAO recursos = new ArchivoDeRecursosDAOImpl();
    
    /** The cliente. */
    private Cliente cliente = new Cliente();
    
    /**
     * Inits the.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Before
    public void init() throws ServiceException {
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

    @Configuration
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.descuento.cheques.dao",
            "ar.com.santanderrio.obp.base.comun" })
    public static class EliminarOperacionDAOTestConfiguration {
        @Bean
        EliminarOperacionDAO eliminarOperacionDAO() {
            return new EliminarOperacionDAOImpl();
        }
        
        @Bean
        ArchivoDeRecursosDAO archivoDeRecursosDAO() {
            return new ArchivoDeRecursosDAOImpl();
        }
    }
    
    @Test
    public void obtenerEliminarOperacionOkTest() throws IatxException, DAOException {
        String tramaResponsePrimera = "200000000000P04HTML0009900010303GSME08  ********00995138000000102017112211384500000000IBF004IN000000000000BAJESTTRA_1000000            03682408    00        00 000000000201711221138320000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0000900000000õ";

        when(iatxSender.send(Matchers.any(IatxRequest.class))).thenReturn(tramaResponsePrimera);
        
        EliminarOperacionEntity  datosCesion = eliminarOperacionDAO.eliminarOperacion(cliente, "");
        
        Assert.assertTrue(datosCesion!=null);
    }
	
}
