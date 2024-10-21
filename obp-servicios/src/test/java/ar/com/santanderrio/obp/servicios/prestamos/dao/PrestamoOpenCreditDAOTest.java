package ar.com.santanderrio.obp.servicios.prestamos.dao;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.cuotaspagas.entities.ConsultaCuotaPagaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.dao.impl.PrestamoOpenCreditDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.entity.ConsultaPrestamoOpenCreditInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.ConsultaPrestamoOpenCreditOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.mock.PrestamoOpenCreditObjectsMock;

/**
 * PrestamoOpenCredit DAO Test
 * @author Silvina_Luque
 *
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        PrestamoOpenCreditDAOTest.PrestamoOpenCreditDAOTestConfiguration.class })

public class PrestamoOpenCreditDAOTest extends IatxBaseDAOTest {

    
    /** The PrestamoOpenCreditDAO. */
    @Autowired
    @InjectMocks
    private PrestamoOpenCreditDAO prestamoOpenCreditDAO;
    
    /** The app context. */
    ApplicationContext appContext = new ClassPathXmlApplicationContext();
    
    
    /** The cliente. */
    private Cliente cliente = new Cliente();
    
    @Configuration
    public static class PrestamoOpenCreditDAOTestConfiguration {

        /**
         * PrestamoOpenCreditDAO.
         * @return the PrestamoOpenCreditDAO
         */
        @Bean
        public PrestamoOpenCreditDAO prestamoOpenCreditDAO() {
            return new PrestamoOpenCreditDAOImpl();
        }

    }

    
    /**
     * consultarPrestamoTest 
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void consultarPrestamoTest() throws IatxException, DAOException{
        String servicio = "CNSPERACTI";
        String version = "120";
        String tramaResponse ="200000000000P04HTML0009900010300AADG07  ********00425342000000102017102010154100000000IBF300NB000000000000CNSPERACTI1200000            00003607    00        00 000000000201710201013130000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0413600000000õ350080CO     P00   350082012ARSN00720082        00720082007200820072SU0082õ999920170405-D6-0003                                õ0000000õ2õ00000000090000000õ00000000100000000õ00000000010460600õ00000000009587300õ00000000009587300õ00000000000õ00000000000000000õ00000000050000000õ00000000000000000õ2017-04-052017-04-052017-04-052017-04-102017-04-102017-04-10õ0001-01-012018-03-102017-09-102017-10-102017-09-102017-10-10õ2017-04-100001-01-012017-10-102017-10-102017-10-102017-04-10õ0001-01-012018-03-102017-09-102017-10-102017-04-052017-09-102017-10-102017-04-05õ2017-04-050001-01-010001-01-01õ9999-12-319999-12-31õ0001-01-019999-12-310001-01-010001-01-010001-01-010001-01-019999-12-31õSS0N                N            õ0NNNISXEDN0NNS0NN 0  NNNNVNNSHN NN NN0NNNNNNNN0õ00700362343700760072  ARS070001õ          õ000õ000000000õNSNNõ00000000000000000õ0001-01-010001-01-019999-12-319999-12-310001-01-019999-12-319999-12-31õ0001-01-010001-01-010001-01-010001-01-019999-12-312017-08-109999-12-31õ007200829999-12-31õ2017-04-052017-04-050001-01-010001-01-0100000000õ00000000000000000õ00000000000000000õ00000000000000000õN  9999-12-31õ000õ00004õ   õ100000000õ00005õ000õ000õ026824179õ000000000õ000000000õ002õ00000000000000000õ9999-12-319999-12-31õ000õ    õ10õN 9999-12-31035100141173008200729999-12-31õ00700361677000820072  ARS2017-04-05õN9999-12-31õ00000000000000000õ000000000õ0001-01-01000000000000000000000000000000000000000000000000000000õ0001-01-019999-12-31õ9999-12-319999-12-319999-12-31õ00000000000000000õ00000000000000000õ00000000000000000õ Sõ024000000õ              õ         õ                  õ                  õ0001-01-01õ00000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000õ00000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ   õ00000000090000000õ00000000100000000õ00000000010460600õ00000000009587300õ00000000009587300õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000050000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ2017-04-05õ2018-03-10õ2001õ2001õ    õ    õ6õ   õNõPõ õ õ    õ õ õNõ00000õNõ000000000õ0000000õ000õSõ00000000000000000õNõ    õ    õ000000000õ õ000õ            õIõ000000000õDõ õ  õNõNõ000õSõCõDõNõLõ    õ   õNõ000õ000õAõNõNõVõ õ000õ000õ    õ000õ        000õ000000000õ00000000000000000õ000õVõ               õ000õNNõ000000000õNNõ   õ00000000000000000õ101õ2017-04-05õ9999-12-31õ001õ2012õ         õ   õ               õ00000000000000000õ99999999999999999õARSõ õ õ000000000õ000000000õVõ024000000õ020õ000000000õPõ2001õ3õ001õSõSõ00000000000õ õ õ00000000000000000õNõ õLQEõINTERES NORMAL      õARSõ00000000000000000õ99999999999999999õ00000000000000000õ00000000089539400õARSPESO ARGENTINO      õ00õ2017-04-05õ00õ0001-01-01õ024000000+õ00000000000287600+õ00000000000187800+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000049800+õ00000000000000000+õ00000000000001100+õ00000000000002200+õ00000000000528500+õ00000000009299700+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000009299700+õ002õ2017-10-10õ00006õ024000000+õ00000000000287600+õ00000000000187800+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000049800+õ00000000000000000+õ00000000000001100+õ00000000000002200+õ00000000000528500+õ0000000000958730000000000000000000000000000+00000000000000000+00000000000000000+000000000    õ00000000009299700+õ2017-11-10õ00007õ024000000+õ00000000000279000+õ00000000000186000+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000048600+õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000513600+õ0000000000929970000000000000000000000000000+00000000000000000+00000000000000000+000000000    õ00000000009020700+õ";
        ConsultaPrestamoOpenCreditInEntity consultaPrestamoOpenCreditInEntity = PrestamoOpenCreditObjectsMock.obtenerConsultaPrestamoOpenCreditInEntity();
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ConsultaPrestamoOpenCreditOutEntity respuesta = prestamoOpenCreditDAO.consultarPrestamo(consultaPrestamoOpenCreditInEntity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }
    
    
    /**
     * consultarPrestamoErrorTest 
     * @throws IatxException
     * @throws DAOException
     */
    @Test(expected = DAOException.class)
    public void consultarPrestamoErrorTest() throws IatxException, DAOException{
        String servicio = "CNSPERACTI";
        String version = "120";
        String tramaResponseWithError ="200000000000P04HTML0009900010300CRQJ37  ********00794833000000062017072114255600000000IBF0062E000000000000CMBDOMYTEL1000000            00276937    00        00 014210621201707211425470000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0034910000122õAQCõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õQCE0122 - LA LONGITUD DEL MENSAJE RECIBIDO DEL PS7 ES ERRONEAõ";
        ConsultaPrestamoOpenCreditInEntity consultaPrestamoOpenCreditInEntity = new ConsultaPrestamoOpenCreditInEntity();
        consultaPrestamoOpenCreditInEntity.setCliente(PrestamoOpenCreditObjectsMock.obtenerClienteConCuentaOpenCredit());
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponseWithError);
        ConsultaPrestamoOpenCreditOutEntity respuesta = prestamoOpenCreditDAO.consultarPrestamo(consultaPrestamoOpenCreditInEntity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }
    
    
    
    /**
     * obtenerCuotasPagasPrestamoTest
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void obtenerCuotasPagasPrestamoTest() throws IatxException, DAOException{
        String servicio = "CNSPMOHIST";
        String version = "120";
        String tramaResponse ="200000000000P04HTML0009900010300AADG07  ********00774989000000442017102410214300000000IBF306CR000000000000CNSPMOHIST1200000            00003607    00        00 000000000201710241019040000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0182600000000õNõ                          õ   õ000õ004õUG2YUM2017-08-3112331600õCOBRõCOBRO DE RECIBOS    õ2017-08-31õ2017-08-31õ2017-08-31õ2017-06-30õNõ0001-01-01õ0010õARSõ00000000001000000õ00000000000970000õUGDTMVRõARSõ00000000000000000õ õ                    õ00000000000052700õ1õCUENTA              õTRANõ00000000000000000+õ000000000õ00001õ00000000000030000õ00000000000015200õ00000000000000000õ00000000000005400õ00000000000000000õ00000000000000000õ024080000õ026923685õ000000000õ000000000õB038478 õUG2YUM2017-08-3112333000õCOBRõCOBRO DE RECIBOS    õ2017-08-31õ2017-08-31õ2017-08-31õ2017-07-30õNõ0001-01-01õ0010õARSõ00000000000970000õ00000000000940900õUGDTMVRõARSõ00000000000000000õ õ                    õ00000000000054600õ1õCUENTA              õTRANõ00000000000000000+õ000000000õ00002õ00000000000029100õ00000000000019200õ00000000000000000õ00000000000005300õ00000000000000000õ00000000000000000õ024080000õ026923685õ000000000õ000000000õB038478 õUG2YUM2017-08-3112334200õCOBRõCOBRO DE RECIBOS    õ2017-08-31õ2017-08-31õ2017-08-31õ2017-08-30õNõ0001-01-01õ0010õARSõ00000000000940900õ00000000000912700õUGDTMVRõARSõ00000000000000000õ õ                    õ00000000000052600õ1õCUENTA              õTRANõ00000000000000000+õ000000000õ00003õ00000000000028200õ00000000000019300õ00000000000000000õ00000000000005100õ00000000000000000õ00000000000000000õ024080000õ026923685õ000000000õ000000000õB038478 õUGZ6NB2017-08-3116561700õCOBRõCOBRO DE RECIBOS    õ2017-08-31õ2017-08-31õ2017-08-30õ2017-09-30õNõ0001-01-01õ0132õARSõ00000000000413300õ00000000000400900õUGDTMVRõARSõ00000000000000000õ õ                    õ00000000000022700õ0õCAJA                õTRANõ00000000000000000+õ000000000õ00004õ00000000000012400õ00000000000008200õ00000000000000000õ00000000000002100õ00000000000000000õ00000000000000000õ024080000õ026923685õ000000000õ000000000õB089005Kõ";
        ConsultaCuotaPagaInEntity consultaCuotaPagaEntity = PrestamoOpenCreditObjectsMock.obtenerConsultaCuotaPagaInEntity();
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ConsultaCuotaPagaOutEntity respuesta = prestamoOpenCreditDAO.obtenerCuotasPagasPrestamo(PrestamoOpenCreditObjectsMock.obtenerClienteConCuentaOpenCredit(), consultaCuotaPagaEntity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }
    
    /**
     * obtenerCuotasPagasPrestamoErrorTest
     * @throws IatxException
     * @throws DAOException
     */
    @Test(expected = DAOException.class)
    public void obtenerCuotasPagasPrestamoErrorTest() throws IatxException, DAOException{
        String servicio = "CNSPMOHIST";
        String version = "120";
        String tramaResponseWithError ="200000000000P04HTML0009900010300CRQJ37  ********00794833000000062017072114255600000000IBF0062E000000000000CMBDOMYTEL1000000            00276937    00        00 014210621201707211425470000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0034910000122õAQCõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õQCE0122 - LA LONGITUD DEL MENSAJE RECIBIDO DEL PS7 ES ERRONEAõ";
        ConsultaCuotaPagaInEntity consultaCuotaPagaEntity = new ConsultaCuotaPagaInEntity();
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponseWithError);
        ConsultaCuotaPagaOutEntity respuesta = prestamoOpenCreditDAO.obtenerCuotasPagasPrestamo(PrestamoOpenCreditObjectsMock.obtenerClienteConCuentaOpenCredit(), consultaCuotaPagaEntity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }
    
    /**
     * obtenerCuotasPagasPrestamoErrorIatxTest
     * @throws IatxException
     * @throws DAOException
     */
    @Test(expected = DAOException.class)
    public void obtenerCuotasPagasPrestamoErrorIatxTest() throws IatxException, DAOException{
        String servicio = "CNSPMOHIST";
        String version = "120";
        ConsultaCuotaPagaInEntity consultaCuotaPagaEntity = new ConsultaCuotaPagaInEntity();
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException());
        ConsultaCuotaPagaOutEntity respuesta = prestamoOpenCreditDAO.obtenerCuotasPagasPrestamo(cliente, consultaCuotaPagaEntity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }
    
}
