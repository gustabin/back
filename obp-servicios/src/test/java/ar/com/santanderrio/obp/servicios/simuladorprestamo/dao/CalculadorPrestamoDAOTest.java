package ar.com.santanderrio.obp.servicios.simuladorprestamo.dao;

import org.junit.Test;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.service.ServiceException;

/**
 * The Class CalculadorPrestamoDAOTest.
 */
public class CalculadorPrestamoDAOTest {

    /**
     * Calcular prestamo test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws ServiceException
     *             the service exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void calcularPrestamoTest() throws IatxException, ServiceException, DAOException {
        // String cnspmocacr = "200000000000P04HTML0009900010302AJFD74
        // ********00968037000000052016102511090800000000IBF39605000000000000CNSPMOCACR1300000
        // 02095374 00 00 000000000201610251109030000000000
        // 0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0033200000000õ000000000000015õ000000000000183õ000000000000561000õ000000000000000000õ003500õ001200õ000500õ00000000703600õ01070õ00000018300000õ00000000281440õ00000001199214õ00000000018443õ001õOK(APROBADO)
        // õ000000000000092õ000000000500000õ000000006000000õ000000018300000õ000000000000000õ000000003000000õ";
        // when(iatxSender.send(Matchers.argThat(new IatxMatcher("CNSPMOCACR",
        // "130")))).thenReturn(cnspmocacr);
        //
        // Cuenta cuenta = new Cuenta();

        // when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio,
        // version)))).thenReturn(tramaResponse);
        // FondoInEntity fondoInEntity = new FondoInEntity();
        // fondoInEntity.setCliente(cliente);
        // FondoOutEntity respuesta =
        // fondoDAO.consultarCotizaciones(fondoInEntity);
        // Assert.assertNotNull(respuesta);
        // Mockito.verify(iatxSender).send(Matchers.argThat(new
        // IatxMatcher(servicio, version)));

        // CalculadorPrestamo calculadorPrestamo = new CalculadorPrestamo();
        // Mockito.verify(iatxSender).send(Matchers.argThat(new
        // IatxMatcher("CNSPMOCACR", "130")));

    }
}
