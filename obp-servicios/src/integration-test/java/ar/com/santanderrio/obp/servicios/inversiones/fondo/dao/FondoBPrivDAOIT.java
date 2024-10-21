package ar.com.santanderrio.obp.servicios.inversiones.fondo.dao;

import java.math.BigDecimal;

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
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.database.dao.HbAccionesBaseDAOIT;
import ar.com.santanderrio.obp.servicios.inversiones.comun.EnumFondosDisponiblesTipoOperacion;
import ar.com.santanderrio.obp.servicios.inversiones.fondo.dao.FondoBPrivDAOIT.FondoBPrivDAOITTConfiguration;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bancaprivada.dao.FondoBPrivDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SuscripcionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ConsultaFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EjecucionFondoBancaPrivadaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SimulacionFondoBancaPrivadaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.SimulacionFondoBancaPrivadaOutEntity;

/**
 * The Class FondoBPrivDAOIT.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { FondoBPrivDAOITTConfiguration.class,
        BackEndSecurityConfig.class })
@ActiveProfiles(value = Profiles.TEST)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = { "DB.SEGURIDAD.USER = seg02_user1", "DB.SEGURIDAD.PASSWORD = seg02_user1",
        "DB.SEGURIDAD.DS = jdbc/seguridadbd", "DB.BPRIV.ID = 41142", "DB.BPRIV.DS = jdbc/hbdbacciones",
        "DB.TIMEOUT = 30000", "DB.RACFINICIAL.ID = 20090" })
public class FondoBPrivDAOIT extends HbAccionesBaseDAOIT {
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(FondoBPrivDAOIT.class);

    /**
     * The Class FondoBPrivDAOITTConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.inversiones.fondos.bancaprivada.dao",
            "ar.com.santanderrio.obp.base.database", "ar.com.santanderrio.obp.base.security.datasource",
            "ar.com.santanderrio.obp.base.security.aop.aspect", "ar.com.santanderrio.obp.base.context",
            "ar.com.santanderrio.obp.base.log" })
    public static class FondoBPrivDAOITTConfiguration {
        
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

    /** The fondo B priv DAO. */
    @Autowired
    FondoBPrivDAO fondoBPrivDAO;

    /**
     * Test suscribir banca privada.
     *
     * @throws DAOException the DAO exception
     */
    @Test
    public void testSuscribirBancaPrivada() throws DAOException {
        SimulacionFondoBancaPrivadaInEntity suscripcionInEntity = new SimulacionFondoBancaPrivadaInEntity();

        suscripcionInEntity.setCodigoOperacion(EnumFondosDisponiblesTipoOperacion.SU.getCodigo());
        suscripcionInEntity.setCuentaTitulo("7003523508");
      
        suscripcionInEntity.setMoneda("ARG");
        suscripcionInEntity.setEspecie("BR07");
        suscripcionInEntity.setEspecieDestino("");
        suscripcionInEntity.setUssRacf("00MOKK00");
        suscripcionInEntity.setPassRacf("@DMZHVAQ");
        BigDecimal importe = BigDecimal.valueOf(12123);
        
        suscripcionInEntity.setCapital(importe);
        Cliente cliente = new Cliente();
        cliente.setNup("00240000");
        suscripcionInEntity.setCliente(cliente);

        SimulacionFondoBancaPrivadaOutEntity out = new SimulacionFondoBancaPrivadaOutEntity();
        out = fondoBPrivDAO.simularSuscripcionBPriv(suscripcionInEntity);
        
        org.junit.Assert.assertNotNull(out);
    }
    
    /**
     * Test ejecutar suscripcion banca privada error.
     */
    @Test
    public void testEjecutarSuscripcionBancaPrivadaError() {
    	EjecucionFondoBancaPrivadaInEntity daoIn = new EjecucionFondoBancaPrivadaInEntity();
		daoIn.setTipo(EnumFondosDisponiblesTipoOperacion.SU.getCodigo());
		SuscripcionInDTO request = new SuscripcionInDTO();
		daoIn.setCapital(request.getImporte());	
		daoIn.setIsPerfilInversion(request.getDentroDelPerfil());
		daoIn.setNroCuenta(request.getNroCuentaBancaPrivada());
		Credential credential = new Credential();
		daoIn.setPasswordRacf(credential.getPassword());
		daoIn.setUsuarioRacf(credential.getUsuario());
		
		ConsultaFondoInEntity requestDao = new ConsultaFondoInEntity();
		requestDao.setCodigoFondo(request.getCodigoFondo());
		
		ConsultaFondoOutEntity fondo = new ConsultaFondoOutEntity();
		daoIn.setEspecie(fondo.getEspecie());
		daoIn.setNombreFondo(fondo.getNombreFondo());
		daoIn.setMoneda(fondo.getMoneda());			
		daoIn.setMoneda(DivisaEnum.PESO.getCodigo());

        EjecucionFondoBancaPrivadaInEntity entity = new EjecucionFondoBancaPrivadaInEntity();
		try {
			fondoBPrivDAO.suscribir(entity);
		} catch (DAOException e) {
			e.printStackTrace();
		}
    }
}
