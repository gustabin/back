/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ConsumoPendienteConfirmacionEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.TarjetaEntityMock;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorConsumosPendientesException;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorWSConsumosPendientesImpl;

/**
 * The Class ParseadorWSConsumosPendientesTest.
 *
 * @author florencia.n.martinez
 */
public class ParseadorWSConsumosPendientesTest {

    /** The parseador. */
    private ParseadorWSConsumosPendientesImpl parseador = new ParseadorWSConsumosPendientesImpl();

    /**
     * Obtener autorizaciones de tarjeta entity.
     *
     * @throws ParseadorConsumosPendientesException
     *             the parseador consumos pendientes exception
     * @throws ParseadorVisaException 
     */
    @Test
    public void obtenerAutorizacionesDeTarjetaEntity() throws ParseadorVisaException {
        List<ConsumoPendienteConfirmacionEntity> consumosPendientes = parseador
                .obtenerAutorizacionesDeTarjetaEntity(TarjetaEntityMock.completarInfoTarjetaEntity());

        Assert.assertEquals("1.500,00", consumosPendientes.get(0).getPesos());

    }

}
