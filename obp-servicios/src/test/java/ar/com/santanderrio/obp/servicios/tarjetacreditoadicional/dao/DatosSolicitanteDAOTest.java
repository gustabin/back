package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.dao.DatosSolicitanteDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteCriterioEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.impl.DatosSolicitanteDAOImpl;
import ar.com.santanderrio.obp.servicios.monedero.exception.OperacionFueraHorarioSucursalException;
import ar.com.santanderrio.obp.servicios.monedero.exception.SinAccesoALaInformacionException;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        DatosSolicitanteDAOTest.DatosSolicitanteDAOTestConfiguration.class })
public class DatosSolicitanteDAOTest extends IatxBaseDAOTest {

    /** The fondo DAO. */
    @Autowired
    private DatosSolicitanteDAO datosSolicitanteDAO;

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
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao" })
    public static class DatosSolicitanteDAOTestConfiguration {
        @Bean
        DatosSolicitanteDAO datosSolicitanteDAO() {
            return new DatosSolicitanteDAOImpl();
        }
    }

    @Test(expected = DAOException.class)
    public void solicitanteNoExisteEnAltairTest() throws IatxException, DAOException, SinAccesoALaInformacionException,
            OperacionFueraHorarioSucursalException {

        String servicio = "CNSPERSFIS";
        String version = "110";
        String tramaResponse = "200000000000P04HTML0009900010300FPQG25  ********00216043000000032017080319391400000000IBF00H8B000000000000CNSPERSFIS1100000            00556625    00        00 000000000201708031939050000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810000232õAPEõ03õPor favor, verifique los datos ingresados.                                                                                                                                                                                                                    õDoc.inexistenteõNO EXISTE PERSONA CON ESE DOCUMENTO                                             õ";

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        DatosSolicitanteCriterioEntity datosSolicitanteCriterioEntity = new DatosSolicitanteCriterioEntity();
        datosSolicitanteCriterioEntity.setDocTipo("N ");
        datosSolicitanteCriterioEntity.setDoc("99999999");
        datosSolicitanteDAO.getDatosDelSolicitante(datosSolicitanteCriterioEntity, cliente);

    }

    @Test
    public void solicitanteExisteEnAltairTest() throws IatxException, DAOException, SinAccesoALaInformacionException,
            OperacionFueraHorarioSucursalException {

        String servicio = "CNSPERSFIS";
        String version = "110";
        String tramaResponse = "200000000000P04HTML0009900010300FPQG25  ********00216044000000122017080322325000000000IBF00HJ2000000000000CNSPERSFIS1100000            00556625    00        00 000000000201708032232410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0231900000000õ70501054õ                              õCONQUISTA           õ                    õVITOLDO SOFANOR ALAIN                   õ18072017õ0158õSõMõFõ05061972õ01010001õ06 õ000õ080õ00õA158õ    õ    õ    õ    õ080õ080õ001õ09  õ002     õ000õ  õ   õCOMõ   õNõ õ010õNCLõNõ    õNõNõNõNõNõNõNõNõNõ     õINDõ   õ2017-07-18-22.10.34.951982õN õ00022873422õ01õ               õ31129999õ õPõ080õ01010001õ       õ2017-07-18-22.10.34.837458õT õ27228734220õ01õ               õ31129999õ õ õ   õ01010001õ       õ2017-07-18-22.10.35.486553õPRIõ  õAV.CALLAO                                         õ    õ    õ   õ    õ    õ   õ õ                              õ                              õ    õ01500     õ       õBARRIO NORTE                  õ     õ                              õ1425õCõAAGõ000000000õ01õ080õ  õ01010001õSõNõNõ  õ                            õ  õ   õ        õ2017-07-18-22.10.34.885742õ   õ   õ  õ                                                  õ   õ    õ    õ   õ õ                              õ                              õ    õ    õ                            õ  õ   õ        õ    õ          õ       õ                              õ     õ                              õ    õ õ   õ         õ  õ   õ  õ        õ õ õ õ  õ                          õ001õ003õ001õ011    õ5367õ6532õ                    õ                              õ2017-07-18-22.10.35.430576õ   õ   õ   õ   õ       õ    õ    õ                    õ                              õ                          õ   õ   õ   õ   õ       õ    õ    õ                    õ                              õ                          õ   õ   õ   õ   õ       õ    õ    õ                    õ                              õ                          õ   õ   õ õ                                        õ    õ    õ  õ        õ   õ õ     õ00000õ00õ                                                            õ                                                            õ õ           õ  õ        õ õ   õ  õ       õ  õ   õ        õ  õ    õ                          õ   õ        õ   õ000000000000000000õ000000000000000000õ                          õ   õ        õ   õ000000000000000000õ000000000000000000õ                          õ   õ        õ   õ000000000000000000õ000000000000000000õ                          õ01õ11õ0009õ0021õ0001õ0000õ2017-07-18-22.10.34.971099õ        õ";

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        DatosSolicitanteCriterioEntity datosSolicitanteCriterioEntity = new DatosSolicitanteCriterioEntity();
        datosSolicitanteCriterioEntity.setDocTipo("N ");
        datosSolicitanteCriterioEntity.setDoc("22873422");
        DatosSolicitanteEntity respuesta = datosSolicitanteDAO.getDatosDelSolicitante(datosSolicitanteCriterioEntity,
                cliente);
        Assert.assertEquals("70501054", respuesta.getNup().trim());
        Assert.assertNotNull(respuesta);
    }

}