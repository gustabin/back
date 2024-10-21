/**
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.monedero.dao.impl.ConsultaUnidadControlDAOImpl;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlInEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.ConsultaUnidadControlOutEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class ConsultaUnidadControlDAOIT.
 *
 * @author florencia.n.martinez
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ConsultaUnidadControlDAOIT.ConsultaUnidadControlDAOITConfiguration.class })
public class ConsultaUnidadControlDAOIT extends IatxBaseDAOTest {

    /** The consulta unidad control DAO. */
    @Autowired
    private ConsultaUnidadControlDAOImpl consultaUnidadControlDAO;

    /** The response OK. */
    private static String responseOK;

    /**
     * The Class ConsultaUnidadControlDAOITConfiguration.
     */
    @Configuration
    public static class ConsultaUnidadControlDAOITConfiguration {

        /**
         * Consulta unidad control DAO.
         *
         * @return the consulta unidad control DAO
         */
        @Bean
        public ConsultaUnidadControlDAO consultaUnidadControlDAO() {
            return new ConsultaUnidadControlDAOImpl();
        }
    }

    /**
     * Inits the.
     */
    @Before
    public void init() {
        responseOK = "200000000000P04HTML0009900010300GMRH92  ********00899564000000232018052417292000000000IBF01BWY000000000000CNSUNICTRL1000000            00627792    00        00 017257686201805241729020000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0006700000000õ0099õ01õ12õ0008õ0040õ0001õ0000õ2013-03-22-18.31.30.921976õ";
    }

    /**
     * Consulta UC de monedero OK.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void consultaUCMonederoOKTest() throws IatxException, DAOException {
        // Given
        Cliente cliente = ClienteMock.infoCliente();
        ConsultaUnidadControlInEntity inEntity = new ConsultaUnidadControlInEntity();
        inEntity.setCliente(cliente);
        inEntity.setNroDocumento(cliente.getDni());
        inEntity.setNup(cliente.getNup());
        inEntity.setTipoDocumento(cliente.getTipoDocumento());

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSUNICTRL", "100")))).thenReturn(responseOK);

        // Then
        ConsultaUnidadControlOutEntity outEntity = consultaUnidadControlDAO.consultaUC(inEntity);

        // Expected
        Assert.assertNotNull(outEntity);
    }

    /**
     * Consulta UC de TC adicional OK.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void consultaUCTCAdicionalOKTest() throws IatxException, DAOException {
        // Given
        Cliente cliente = ClienteMock.infoCliente();
        ConsultaUnidadControlInEntity inEntity = new ConsultaUnidadControlInEntity();
        inEntity.setCliente(cliente);
        inEntity.setNup(cliente.getNup());

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSUNICTRL", "100")))).thenReturn(responseOK);

        // Then
        ConsultaUnidadControlOutEntity outEntity = consultaUnidadControlDAO.consultaUC(inEntity);

        // Expected
        Assert.assertNotNull(outEntity);
    }

    /**
     * Consulta UC de TC adicional cod error distinto cero.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void consultaUCTCAdicionalCodErrorDistintoCeroTest() throws IatxException, DAOException {
        // Given
        Cliente cliente = ClienteMock.infoCliente();
        ConsultaUnidadControlInEntity inEntity = new ConsultaUnidadControlInEntity();
        inEntity.setCliente(cliente);
        inEntity.setNup(cliente.getNup());
        String tramaResponse = "200000000000P04HTML0009900010300GMRH92  ********00899564000000232018052417292000000000IBF01BWY000000000000CNSUNICTRL1000000            00627792    00        00 017257686201805241729020000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0006720000000õ0099õ01õ12õ0008õ0040õ0001õ0000õ2013-03-22-18.31.30.921976õ";

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSUNICTRL", "100")))).thenReturn(tramaResponse);

        // Then
        ConsultaUnidadControlOutEntity outEntity = consultaUnidadControlDAO.consultaUC(inEntity);

        // Expected
        Assert.assertNull(outEntity);
    }

    /**
     * Consulta UC de TC adicional DAO exception.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @SuppressWarnings("unused")
    @Test(expected = DAOException.class)
    public void consultaUCTCAdicionalDAOExceptionTest() throws IatxException, DAOException {
        // Given
        Cliente cliente = ClienteMock.infoCliente();
        ConsultaUnidadControlInEntity inEntity = new ConsultaUnidadControlInEntity();
        inEntity.setCliente(cliente);
        inEntity.setNup(cliente.getNup());

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSUNICTRL", "100"))))
                .thenThrow(new IatxException("IatxException!!"));

        // Then
        ConsultaUnidadControlOutEntity outEntity = consultaUnidadControlDAO.consultaUC(inEntity);
    }
}
