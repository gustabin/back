package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;

/**
 * The Class TarjetaEntityMock.
 *
 * @author florencia.n.martinez
 */
public final class TarjetaEntityMock {

    private TarjetaEntityMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Completa la informacion de la lista de tarjetas entity.
     *
     * @return the list
     */
    public static List<TarjetaEntity> completarInfoTarjetaEntityList() {
        List<TarjetaEntity> tarjetaEntityList = new ArrayList<TarjetaEntity>();
        tarjetaEntityList.add(completarInfoTarjetaEntity());
        return tarjetaEntityList;
    }

    /**
     * Completa la informacion de cada tarjeta entity.
     *
     * @return the tarjeta entity
     */
    public static TarjetaEntity completarInfoTarjetaEntity() {
        TarjetaEntity tarjetaEntity = new TarjetaEntity();
        tarjetaEntity.setUltimaLiquidacion(UltimaLiquidacionEntityMock.completarInfoUltimaLiquidacion());
        tarjetaEntity.setTarjetaDocument(TarjetaDocumentEntityMock.completarInfoTarjetaDocument());
        return tarjetaEntity;
    }

}