package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.config.SecurityProviderConfig;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.ConsultaDatosTarjetasIn;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.entities.ConsultaDatosTarjetasOut;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { SecurityProviderConfig.class })
@ActiveProfiles(Profiles.TEST)
public class ReimpresionTarjetasDAOIT {
	
	 @Autowired
	 private ReimpresionTarjetasDAO reimpresionTarjetasDAO;
	 
	 @Test
	 public void testConsultaDatos(){
		 ConsultaDatosTarjetasIn in = new ConsultaDatosTarjetasIn();
		 try {
			ConsultaDatosTarjetasOut out = reimpresionTarjetasDAO.consultaDatosTarjetas(in);
		} catch (RobotException e) {
			TestCase.fail();
		}
	 }

}
