package ar.com.santanderrio.obp.servicios.obp.cotizacion.dao;

import org.junit.Before;
import org.junit.Test;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.cotizacion.dao.impl.CotizacionDAOImpl;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.iatx.dao.impl.IatxResponseParser;

/**
 * Class PagosTarjetaDaoTest.
 * 
 * @author mariano.g.pulera
 */
public class CotizacionDAOTest {

    /** The pagos tarjeta dao impl. */
    private CotizacionDAOImpl datosCotizacionDAOImpl;

    /**
     * Inits the.
     */
    @Before
    public void init() {
        datosCotizacionDAOImpl = new CotizacionDAOImpl();
    }

    /**
     * Este test le envia una trama al parseador, para chequear que esta armando
     * correctamente el objeto de datos de cotizacion.
     *
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void devuelveDatosDeCotizacionPorTrama() throws DAOException {

        String trama = "200000000000P04HTML0009900010360486686  ********00696983000000392016052714572700000000KU008259000000000000CNSTJCCOTI1000000            60486686    00        00 014535972201605271457230000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0014700000000õ000000582650066õ7õ4509950153080415õ000000000198719+õ000000000005817+õ000000000013896+õ000000000005817+õ0000000001430õUSDõ000000000019713+õ";

        IatxResponse response = IatxResponseParser.parseResponse(trama, null);

        // CotizacionDTO datosCotizacion =
        // datosCotizacionDAOImpl.parsearDatosCotizacion(response);
        // Assert.assertNotNull(datosCotizacion);
    }

}