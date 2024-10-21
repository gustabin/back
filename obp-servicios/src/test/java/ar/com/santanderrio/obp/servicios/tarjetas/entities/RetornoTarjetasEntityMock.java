/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetasEntity;

/**
 * The Class RetornoTarjetasEntityMock.
 *
 * @author florencia.n.martinez
 */
public final class RetornoTarjetasEntityMock {

    private RetornoTarjetasEntityMock() {
        throw new IllegalAccessError("Clase para testing");
    }

    /**
     * Obtener retorno WS.
     *
     * @return the retorno WS
     */
    public static RetornoTarjetasEntity completarInfoRetornoWS() {
        RetornoTarjetasEntity retornoWS = new RetornoTarjetasEntity();
        retornoWS.setTarjetas(completarInfoTarjetasEntity());
        return retornoWS;
    }

    /**
     * Obtener tarjetas.
     *
     * @return the tarjetas
     */
    public static TarjetasEntity completarInfoTarjetasEntity() {
        TarjetasEntity tarjetas = new TarjetasEntity();
        tarjetas.setTarjetaList(TarjetaEntityMock.completarInfoTarjetaEntityList());
        return tarjetas;
    }
}