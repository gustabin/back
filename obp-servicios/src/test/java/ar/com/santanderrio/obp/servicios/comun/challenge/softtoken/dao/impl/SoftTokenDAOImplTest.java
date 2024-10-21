package ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.dao.impl;

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
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.challenge.exception.SyncException;
import ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.dao.SoftTokenDAO;

/**
 * The Class SoftTokenDAOImplTest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        SoftTokenDAOImplTest.SoftTokenDAOImplTestConfiguration.class })
public class SoftTokenDAOImplTest extends IatxBaseDAOTest {

    /** The soft token DAO. */
    @Autowired
    @InjectMocks
    private SoftTokenDAO softTokenDAO;

    /**
     * The Class SoftTokenDAOImplTestConfiguration.
     */
    @Configuration
    public static class SoftTokenDAOImplTestConfiguration {

        /**
         * Soft token DAO.
         *
         * @return the soft token DAO
         */
        @Bean
        public SoftTokenDAO softTokenDAO() {
            return new SoftTokenDAOImpl();
        }
    }

    /**
     * Validar token ok.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void validarTokenOk() throws IatxException, DAOException {

        String iatxOkTrama = "200000000000P04HTML0009900010320064145  ********00610058000000142016083012422400000000IBF21734000000000000VALZATOKEN1000000            20064145    00        00 000000000201608301242190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0003600000000õ  õ00000000000õ           õ";
        Mockito.when(iatxSender.send(Matchers.any(IatxRequest.class))).thenReturn(iatxOkTrama);

        boolean ejecutarValiadacionToken = softTokenDAO.ejecutarValidacionToken("123456", "192168001001",
                Mockito.mock(Cliente.class));
        Assert.assertTrue(ejecutarValiadacionToken);

    }

    /**
     * Validar token sync exception.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws SyncException
     *             the sync exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test(expected = SyncException.class)
    public void validarTokenSyncException() throws IatxException, SyncException, DAOException {
        // String iatxErrorTrama = "200000000000P04HTML0009900010320064145
        // ********00610058000000122016083012405800000000IBF49890000000000000VALZATOKEN1000000
        // 20064145 00 00 000000000201608301240540000000000
        // 0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0037110000053õ
        // õ03õNo es posible acceder a la informacion solicitada. Por favor,
        // reintente mas tarde. õ õ00õAUTENTICACION FALLIDA õ";
        Mockito.when(iatxSender.send(Matchers.any(IatxRequest.class))).thenThrow(SyncException.class);
        softTokenDAO.ejecutarValidacionToken("123456", "192168001001", Mockito.mock(Cliente.class));
    }

    /**
     * Validar token iatx exception.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test(expected = DAOException.class)
    public void validarTokenIatxException() throws IatxException, DAOException {
        Mockito.when(iatxSender.send(Matchers.any(IatxRequest.class))).thenThrow(IatxException.class);
        softTokenDAO.ejecutarValidacionToken("123456", "192168001001", Mockito.mock(Cliente.class));
    }
}
