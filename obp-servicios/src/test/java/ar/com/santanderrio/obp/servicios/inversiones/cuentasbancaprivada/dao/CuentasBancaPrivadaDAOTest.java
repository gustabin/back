package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.dao;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasaldo.entity.CuentaSaldoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.entity.ConsultaSaldoCtasConAperturaOutEntity;


@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		CuentasBancaPrivadaDAOTest.CuentasBPrivDAOTestConfiguration.class })
@TestPropertySource(properties = { "DB.TIMEOUT = 30000", "INVERSIONES.CANAL.BANCAPRIVADA = 79", "INVERSIONES.SUBCANAL.BANCAPRIVADA = 0"})
public class CuentasBancaPrivadaDAOTest extends IatxBaseDAOTest {
	
	private static final String TIMEOUT_EXCEPTION = "iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ";
	
	/** The CuentasBancaPrivada DAO. */
    @Autowired
    @InjectMocks
    private CuentasBancaPrivadaDAO cuentasBancaPrivadaDAO;
	

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
    
    /**
     * The Class PlazoFijoDAOTestConfiguration.
     */
    @Configuration
    public static class CuentasBPrivDAOTestConfiguration {

        /**
         * CuentasBancaPrivada DAO.
         *
         * @return the CuentasBancaPrivadaDAO DAO
         */
        @Bean
        public CuentasBancaPrivadaDAO cuentasBPrivDAO() {
            return new CuentasBancaPrivadaDAOImpl();
        }

    }
    
    
    /**
     * Consultar Saldo Cuentas Con Apertura Test
     * 
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void consultarSaldoCtasConAperturaTest() throws IatxException, DAOException{
    	String servicio = "CNSSDCTABP";
        String version = "110";
        String tramaResponse ="200000000000P79HTML00000000103ONLINEBP  ********00256106000000132017121509442900000000IBF000E2000000000000CNSSDCTABP1100000            01576531    00        00 000000000201712150944160000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0035500000000õ000005000000000+õ000000000000000+õ000000000000000+õ0000000000000+õ000000000000000+õ000000000000000+õ000000000500000+õ0000000000000+õ00000õ000005000000000+õ000000000000000+õ000000000000000+õ0000000000000+õ000000000000000+õ000000000000000+õ000000000000000+õ0000000000000+õ00000õSõ000000000000000+õ000005000000000+õ000000000000000+õ000005000000000+õ";
        ConsultaSaldoCtasConAperturaInEntity inEntity = new ConsultaSaldoCtasConAperturaInEntity();
        inEntity.setCuenta("00720250007003523508");
        inEntity.setMoneda("ARS");
        CuentaSaldoInEntity cuentaSaldoInEntity = new CuentaSaldoInEntity();
        cuentaSaldoInEntity.setUsuario("usuario");
        cuentaSaldoInEntity.setPass("pass");
        
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        ConsultaSaldoCtasConAperturaOutEntity respuesta = cuentasBancaPrivadaDAO.consultarSaldoCtasConApertura(inEntity, cliente, cuentaSaldoInEntity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }
    
    
    
}
