/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
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
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.impl.ArchivoDeRecursosDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.dao.impl.PrestamoDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.mock.PrestamoInEntityMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class SimuladorPrestamoDAOTest.
 *
 * @author florencia.n.martinez
 */

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        SimuladorPrestamoDAOTest.SimuladorPrestamoDAOTestConfiguration.class })
@TestPropertySource(properties = { 
		"SERVICIO.PREFIJO.PAGPMOCAAN = PAGPMOCAAN",
		"SERVICIO.VERSION.PAGPMOCAAN = 100"
		})
public class SimuladorPrestamoDAOTest extends IatxBaseDAOTest {

    /** The simulador prestamo DAO. */
    @Autowired
    private PrestamoDAO prestamoDAO;
    
    @Autowired
    private ArchivoDeRecursosDAO archivoRecursosDao;

    /**
     * The Class SimuladorPrestamoDAOTestConfiguration.
     */
    
    @Configuration
    public static class SimuladorPrestamoDAOTestConfiguration {
    
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

    /**
     * Dada la entidad simulador prestamo in entity OK, cuando se invoca al
     * metodo "obtenerSimulacionPrestamo", obtengo la entidad simulador prestamo
     * out entity OK.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Ignore
    @Test
    public void dadoSimuladorPrestamoInEntityOKCuandoInvocaObtenerSimulacionPrestamoObtengoSimuladorPrestamoOutEntityOK()
            throws IatxException, DAOException {
        String servicio = "ALTPMOPREA";
        String version = "120";
        String tramaResponse = "200000000000P04HTML0009900010301KOOT45  ********00512659000000222018102213000800000000IBF208SM000000000000ALTPMOPREA1200000            01044945    00        00 012522455201810221259490000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0043500000000õ0072õ0081õ035100265877õARSõ049000000õUGX8S42018-10-2212594400õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000669210000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000005848500õ00000000000660000õ00000000000000000õ00000000042775700õ00000000049284200õ059400000õ049090000õ061649479õ    õ00000000000õ          õ00000000000000000õ00000000000000000õ00000000000000000õ";
        PrestamoInEntity inEntity = PrestamoInEntityMock.obtenerPrestamoInEntityOKParaSimulador();
        Cliente cliente = ClienteMock.completarInfoCliente();
        
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        PrestamoOutEntity outEntity = prestamoDAO.simularAdquirir(cliente, inEntity);

        Assert.assertNotNull(outEntity);
        Assert.assertEquals("059400000", outEntity.getCftna());
        Assert.assertEquals("049090000", outEntity.getCftnasimp());
        Assert.assertEquals("00000000", outEntity.getCodigoRetornoExtendido());
        Assert.assertEquals("00000000042775700", outEntity.getCuotaPura());
        Assert.assertEquals("ARS", outEntity.getDivisa());
        Assert.assertEquals("0072", outEntity.getEntPrest());
        Assert.assertEquals(
                "200000000000P04HTML0009900010301KOOT45  ********00512659000000222018102213000800000000IBF208SM000000000000ALTPMOPREA1200000            01044945    00        00 012522455201810221259490000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DH",
                outEntity.getHeaderTrama());
        Assert.assertEquals("00000000669210000", outEntity.getImporteAbono());
        Assert.assertEquals("00000000000000000", outEntity.getImporteCargo());
        Assert.assertEquals("UGX8S42018-10-2212594400", outEntity.getNio());
        Assert.assertEquals("035100265877", outEntity.getNumPrest());
        Assert.assertEquals("0081", outEntity.getSucPrest());
        Assert.assertEquals("049000000", outEntity.getTasa());
        Assert.assertEquals("061649479", outEntity.getTea());
        Assert.assertEquals("00000000000000000", outEntity.getTotComisiones());
        Assert.assertEquals("00000000000000000", outEntity.getTotComisionesCuota());
        Assert.assertEquals("00000000049284200", outEntity.getTotCuotaTotal());
        Assert.assertEquals("00000000000000000", outEntity.getTotGastosCuota());
        Assert.assertEquals("00000000000000000", outEntity.getTotGastosPre());
        Assert.assertEquals("00000000000000000", outEntity.getTotIva());
        Assert.assertEquals("00000000000000000", outEntity.getTotIvaAdicional());
        Assert.assertEquals("00000000005848500", outEntity.getTotIvaCuota());
        Assert.assertEquals("00000000000000000", outEntity.getTotRestoImpu());
        Assert.assertEquals("00000000000660000", outEntity.getTotRestoImpuCuota());
        Assert.assertEquals("00000000000000000", outEntity.getTotSeguroCuota());
        Assert.assertEquals("          ", outEntity.getFechaCotiz());
        Assert.assertEquals("00000000000000000", outEntity.getImportePrimerCuotaPura());
        Assert.assertEquals("00000000000000000", outEntity.getImporteCuotaCTE());
        Assert.assertEquals("00000000000000000", outEntity.getImporteSolicitado());
        Assert.assertEquals("00000000000", outEntity.getCotizCambio());
    }

    /**
     * Dada la entidad simulador prestamo in entity con error, cuando se invoca
     * el metodo "obtenerSimulacionPrestamo", obtengo la entidad simulador
     * prestamo out entity OK con codigo de retorno extendido con error
     * servicio.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void dadoSimuladorPrestamoInEntityConErrorCuandoInvocaObtenerSimulacionPrestamoObtengoSimuladorPrestamoOutEntityOKConCodRetExtErrorServicio()
            throws IatxException, DAOException {
        String servicio = "ALTPMOPREA";
        String version = "120";
        String tramaResponse = "200000000000P04HTML0009900010303253431  ********00421096000000512016052311541200000000IBF37946000000000000ALTPMOPREA1100000            03253431    00        00 011591378201605231154090000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0035300000000õ0072õ0016õ035100608649õARSõ035000000õUGX9HU2016-05-2311540800õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000200000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000001248500õ00000000000197300õ00000000000311900õ00000000020104500õ00000000021862200õ044330000õ036960000õ041197998õ";
        PrestamoInEntity inEntity = PrestamoInEntityMock.obtenerPrestamoInEntityConErrorFormatoRegexp();
        Cliente cliente = ClienteMock.completarInfoCliente();

        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        PrestamoOutEntity outEntity = prestamoDAO.simularAdquirir(cliente, inEntity);

        Assert.assertNotNull(outEntity);
        Assert.assertEquals("001", outEntity.getCodigoRetornoExtendido());
    }

    /**
     * Dada la entidad simulador prestamo in entity OK, cuando se invoca al
     * metodo "obtenerSimulacionPrestamo", obtengo la entidad simulador prestamo
     * out entity con error de fecha no habil.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Ignore
    @Test
    public void dadoSimuladorPrestamoInEntityOKCuandoInvocaObtenerSimulacionPrestamoObtengoSimuladorPrestamoOutEntityErrorFechaNoHabil()
            throws IatxException, DAOException {
        String servicio = "ALTPMOPREA";
        String version = "120";
        String tramaResponse = "200000000000P04HTML0009900010302QLPO92  ********00908230000000242017021610365800000000IBF23636000000000000ALTPMOPREA1100000            02615492    00        00 010314027201702161036520000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0033610001306õUGEõ03õLa fecha del 1er. vencimiento no es una fecha habil.                                                                                                                                                                                                          õFECHA INCORRECTõ2017-03-18                                      õ";
        PrestamoInEntity inEntity = PrestamoInEntityMock.obtenerPrestamoInEntityOKParaSimulador();
        Cliente cliente = ClienteMock.completarInfoCliente();

        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        PrestamoOutEntity outEntity = prestamoDAO.simularAdquirir(cliente, inEntity);

        Assert.assertNotNull(outEntity);
        Assert.assertEquals("10001306", outEntity.getCodigoRetornoExtendido());
    }

    /**
     * Dada la entidad simulador prestamo in entity OK, cuando se invoca al
     * metodo "obtenerSimulacionPrestamo", obtengo la entidad simulador prestamo
     * out entity con error generico.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Ignore
    @Test
    public void dadoSimuladorPrestamoInEntityOKCuandoInvocaObtenerSimulacionPrestamoObtengoSimuladorPrestamoOutEntityErrorGenerico()
            throws IatxException, DAOException {
        String servicio = "ALTPMOPREA";
        String version = "120";
        String tramaResponse = "200000000000P04HTML0009900010302QLPO92  ********00908230000000262017021610413300000000IBF24078000000000000ALTPMOPREA1100000            02615492    00        00 010414221201702161041300000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0033610000001õALTõ03õLa fecha del primer vencimiento de cuota no podra ser inferior a 28 dias ni superior a 90 dias. Por favor ingrese otra fecha de primer vencimiento de cuota.                                                                                                  õfecha 1er vto. õPRIMER VENCIMIENTO INCOMPATIBLE CON LIMITE DE CAõ";
        PrestamoInEntity inEntity = PrestamoInEntityMock.obtenerPrestamoInEntityOKParaSimulador();
        Cliente cliente = ClienteMock.completarInfoCliente();

        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        PrestamoOutEntity outEntity = prestamoDAO.simularAdquirir(cliente, inEntity);

        Assert.assertNotNull(outEntity);
        Assert.assertEquals("10000001", outEntity.getCodigoRetornoExtendido());
    }

    /**
     * Dada la entidad simulador prestamo in entity OK, cuando se invoca al
     * metodo "obtenerSimulacionPrestamo", obtengo DAO exception.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Ignore
    @Test(expected = DAOException.class)
    public void dadoSimuladorPrestamoInEntityOKCuandoInvocaObtenerSimulacionPrestamoObtengoDAOException()
            throws IatxException, DAOException {
        String servicio = "ALTPMOPREA";
        String version = "120";
        PrestamoInEntity inEntity = PrestamoInEntityMock.obtenerPrestamoInEntityOKParaSimulador();
        Cliente cliente = ClienteMock.completarInfoCliente();

        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version))))
                .thenThrow(new IatxException("Error de Iatx."));

        PrestamoOutEntity outEntity = prestamoDAO.simularAdquirir(cliente, inEntity);

        Assert.assertNotNull(outEntity);
    }
}
