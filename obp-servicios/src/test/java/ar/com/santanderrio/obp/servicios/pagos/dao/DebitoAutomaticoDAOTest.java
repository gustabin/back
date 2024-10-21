package ar.com.santanderrio.obp.servicios.pagos.dao;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import org.junit.Assert;
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
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.DebitoAutomaticoDAOImpl;

/**
 * The Class DebitoAutomaticoDAOTest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        DebitoAutomaticoDAOTest.DebitoAutomaticoDAOTestConfiguration.class })
public class DebitoAutomaticoDAOTest extends IatxBaseDAOTest {

    /** The debito automatico DAO. */
    @Autowired
    @InjectMocks
    private DebitoAutomaticoDAO debitoAutomaticoDAO;

    /** The mensaje dao. */
    @Autowired
    private MensajeDAO mensajeDao;

    /** The legal dao. */
    @Autowired
    private LegalDAO legalDao;
    
    @Autowired
    private RespuestaFactory respuestaFactory;
    
    @Autowired
    private MensajeBO mensajeBO;
    

    /**
     * The Class DebitoAutomaticoDAOTestConfiguration.
     */
    @Configuration
    public static class DebitoAutomaticoDAOTestConfiguration {

        /**
         * Debito automatico DAO.
         *
         * @return the debito automatico DAO
         */
        @Bean
        public DebitoAutomaticoDAO debitoAutomaticoDAO() {
            return new DebitoAutomaticoDAOImpl();
        }

        /**
         * Mensaje dao.
         *
         * @return the mensaje DAO
         */
        @Bean
        public MensajeDAO mensajeDao() {
            return Mockito.mock(MensajeDAO.class);
        }

        /**
         * Legal dao.
         *
         * @return the legal DAO
         */
        @Bean
        public LegalDAO legalDao() {
            return Mockito.mock(LegalDAO.class);
        }

        @Bean
        public RespuestaFactory respuestaFactory() {
            return Mockito.mock(RespuestaFactory.class);
        }
        
        @Bean
        public MensajeBO mensajeBO() {
            return Mockito.mock(MensajeBO.class);
        }
        
    }

    /**
     * Test baja adhesion ok.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void testBajaAdhesionOk() throws IatxException, DAOException {

        String bajddiadheResponseOk = "200000000000P04HTML0009900010301193074  ********00086151000000892016052609295700000000IBF17347000000000000BAJDDIADHE1000000            01193074    00        00 009272077201605260929540000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0007700000000õ02õ127õ03628302õ00010439002õHARDOY  LILIANA ESTER         õ19520519õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("BAJDDIADHE", "100")))).thenReturn(bajddiadheResponseOk);

        ResultadoTransaccion resultadoOk = new ResultadoTransaccion();
        resultadoOk.setEstadoRespuesta(EstadoRespuesta.OK);

        ResultadoTransaccion resultadoWarning = new ResultadoTransaccion();
        resultadoWarning.setEstadoRespuesta(EstadoRespuesta.WARNING);

        Cliente cliente = new Cliente();
        Segmento segmento = new Segmento();

        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);

        cliente.setNombre("CONSTANCIO PERCY");
        cliente.setApellido1("MILANDO");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);

        AdhesionDebitoAutomatico adhesion = new AdhesionDebitoAutomatico();

        Mockito.when(mensajeDao.obtenerMensajeDescripcion("1280"))
                .thenReturn("<b>¡Listo!</b><p>La adhesión de {0} al {1} de tu cuenta {2} se realizo con éxito.</p>");
        Mockito.when(mensajeDao.obtenerMensajeDescripcion("1281"))
                .thenReturn("<b>¡Lo sentimos!</b><p>No pudimos adherir {0} al {1} en tu cuenta {2}.</p>");
        Mockito.when(legalDao.obtenerLegal("1005"))
                .thenReturn("<p>Conserve este ticket como</p><p>comprobante S.E.U.O.</p>");

        ResultadoTransaccion resultado = debitoAutomaticoDAO.bajaAdhesion(cliente, adhesion);
        Assert.assertNotNull(resultado);

        String bajddiadheResponseWarning = "200000000000P04HTML0009900010300MRQT37  ********00473537000000812016052415473200000000IBF29243000000000000BAJDDIADHE1000000            00276937    00        00 000000000201605241547290000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810000170õDDIõ03õLa baja de la adhesión solicitada ya se encuentra ingresada.                                                                                                                                                                                                  õERROR BAJA CON õERROR BAJA CON OPERACION DE APLICACION PENDIENTE                                õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("BAJDDIADHE", "100"))))
                .thenReturn(bajddiadheResponseWarning);

        resultado = debitoAutomaticoDAO.bajaAdhesion(cliente, adhesion);
        Assert.assertNotNull(resultado);

        String bajddiadheResponseWarning2 = "200000000000P04HTML0009900010300MRQT37  ********00473537000000812016052415473200000000IBF29243000000000000BAJDDIADHE1000000            00276937    00        00 000000000201605241547290000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810000500õDDIõ03õLa baja de la adhesión solicitada ya se encuentra ingresada.                                                                                                                                                                                                  õERROR BAJA CON õERROR BAJA CON OPERACION DE APLICACION PENDIENTE                                õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher("BAJDDIADHE", "100"))))
                .thenReturn(bajddiadheResponseWarning2);

        resultado = debitoAutomaticoDAO.bajaAdhesion(cliente, adhesion);
        Assert.assertNotNull(resultado);

        given(iatxSender.send(Matchers.argThat(new IatxMatcher("BAJDDIADHE", "100")))).willThrow(new IatxException());
        try {
            resultado = debitoAutomaticoDAO.bajaAdhesion(cliente, adhesion);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(resultado);

    }

}
