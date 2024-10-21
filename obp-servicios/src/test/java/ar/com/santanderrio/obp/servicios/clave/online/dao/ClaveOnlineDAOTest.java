package ar.com.santanderrio.obp.servicios.clave.online.dao;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clave.online.dao.impl.ClaveOnlineDAOImpl;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.AutenticacionCvv2InactivaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.AutenticacionPinInactivaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.AutenticarTarjetaDebitoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.CLienteSinContratoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClaveOnlineNoEsPersonaFisicaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteBloqueado2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteBloqueadoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteSinAutValidoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteSinonimoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.DniInvalidoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorCicsException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorDb2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorModuloException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.FuncionInvalidaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.IpBloqueadaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.SinCelularRegistradoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.SistemaClaveNoDisponibleException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.AutenticacionSmsOtpInactivaException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.ErrorRutinaFechasException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.SinTarjetaValidaException;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		ClaveOnlineDAOTest.ClaveOnlineDAOTestConfiguration.class })
public class ClaveOnlineDAOTest extends IatxBaseDAOTest{
	
	/** The ClaveOnlineDAO. */
    @Autowired
    @InjectMocks
    private ClaveOnlineDAO claveOnlineDAO;

    /**
     * The Class ClaveOnlineDAOTestConfiguration.
     */
    @Configuration
    public static class ClaveOnlineDAOTestConfiguration {

        @Bean
        public ClaveOnlineDAO claveOnlineDAO() {
            return new ClaveOnlineDAOImpl();
        }
        
        @Bean
		public static ArchivoDeRecursosDAO archivoDeRecursosDAO() {
			return Mockito.mock(ArchivoDeRecursosDAO.class);
		}
    }

    @Test
    public void identificarClienteTest() throws IatxException, DAOException, DniInvalidoException, ClienteSinonimoException, ClienteSinAutValidoException, ClienteBloqueadoException, ClienteBloqueado2Exception, ClaveOnlineNoEsPersonaFisicaException, IpBloqueadaException, SistemaClaveNoDisponibleException, CLienteSinContratoException, SinTarjetaValidaException, SinCelularRegistradoException, AutenticacionPinInactivaException, AutenticacionSmsOtpInactivaException, AutenticacionCvv2InactivaException, ErrorRutinaFechasException, ErrorModuloException, ErrorDb2Exception, FuncionInvalidaException, AutenticarTarjetaDebitoException, ErrorCicsException {
    	
    	IdentificadorClienteInEntity identificadorCliente = new IdentificadorClienteInEntity();
    	identificadorCliente.setDni("22424141");
    	identificadorCliente.setIp("224.241.412.242");
    	String servicio = "IDESGICLIE";
        String version = "100";
        String tramaResponse = "200000000000P04HTML00099000104FREEUSER  ********00688869000000022016110717561700000000IBF34813000000000000IDESGICLIE1000000                        00        0  000000000201611071756110000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0064000000000õ02639403õ00001843õ2õ3õBANEõ4517660043834653    õ20õ81õ0012õTIõ0069õ000000035916õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ  õ    õ  õ    õ            õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
		claveOnlineDAO.identificarCliente(identificadorCliente);
        
    }

}
