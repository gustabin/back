/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.contratos.mya.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.BackEndSecurityConfig;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.dao.ContratosMyaDAOIT.ContratosMyaDAOITConfiguration;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.ConsultaCliente;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.ConsultaClienteParam;
import ar.com.santanderrio.obp.servicios.comun.contratos.mya.entity.ContratoParam;
import ar.com.santanderrio.obp.servicios.database.dao.HbankBaseDAOIT;

/**
 * testConDS.setURL("jdbc:oracle:thin:@LXDESAORA3.ar.bsch:1521:RIO147D");
 * ds.setURL("jdbc:oracle:thin:@localhost:1521:XE");
 * 
 * @author sergio.e.goldentair
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ContratosMyaDAOITConfiguration.class,
        BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.ESTADISTICAS.ID = 40011", "DB.ESTADISTICAS.DS = jdbc/hbalias",
        "MENSAJE_ERROR_GENERICO=Error generico", "DB.TIMEOUT = 30000" })

@Ignore
public class ContratosMyaDAOIT extends HbankBaseDAOIT {
	
	/** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ContratosMyaDAOIT.class);

    /** The estadistica DAO. */
    @Autowired
    private ContratosMyaDAO contratosMyaDAO;

    /**
     * The Class MyaDAOITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.comun.contratos.mya.dao",
            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
            "ar.com.santanderrio.obp.base.log" })
    public static class ContratosMyaDAOITConfiguration {
        
        /**
         * Property configurer.
         *
         * @return the property sources placeholder configurer
         */
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }
    }
    
    /** The sucursal origen. */
    String  dni = "4740988",
    		fechaActual = "20170208",
    		fechaNacimiento = "19591005",
    		nup = "26154492",
    		aceptacionContrato = "",
    		canalActivacion = "",
    		fechaInicioUso = "",
    		aceptacion ="",
    		altaRegistro ="",
    		apellido ="",
    		canalVenta ="",
    		fechaAlta ="",
    		fechaInicio ="",
    		nombre ="",
    		sucursalOrigen ="";
    
    
    
    /**
     * Adds the estadistica.
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void actualizarContrato() throws DAOException {
    	ContratoParam contrato = new ContratoParam();
    	
    	contrato.setDni(dni);
    	//contrato.setFechaNacimiento(fechaNacimiento);
    	//contrato.setNup(nup);
    	
		boolean respuesta = contratosMyaDAO.actualizarContrato(contrato);
		System.out.println("respuesta:= " + respuesta);
    }
    
    
    /**
     * Consulta cliente.
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void consultaCliente() throws DAOException{
		ConsultaClienteParam cc = new ConsultaClienteParam();
		cc.setDni(dni);
		cc.setFechaNacimiento(fechaNacimiento);
		cc.setNup(nup);
		ConsultaCliente consultaCliente = contratosMyaDAO.consultaClientes(cc);	
	    System.out.println("aceptacionContrato:= "+consultaCliente.getAceptacionContrato()+" canal activacion := "+ consultaCliente.getCanalActivacion());
	    System.out.println("fechaInicio:= "+consultaCliente.getFechaInicioUso()+" fecha de nacimiento := "+ consultaCliente.getFechaNacimiento());
    }
    
   
}
