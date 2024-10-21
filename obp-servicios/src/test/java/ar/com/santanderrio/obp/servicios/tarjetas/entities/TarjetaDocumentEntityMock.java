package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaDocumentEntity;

/**
 * The Class TarjetaDocumentEntityMock.
 *
 * @author florencia.n.martinez
 */
public final class TarjetaDocumentEntityMock {

    private TarjetaDocumentEntityMock() {
        throw new IllegalAccessError("Clase para testing");
    }
    
    /**
     * Completar info tarjeta document.
     *
     * @return the tarjeta document entity
     */
    public static TarjetaDocumentEntity completarInfoTarjetaDocument() {
        TarjetaDocumentEntity tarjetaDocumentEntity = new TarjetaDocumentEntity();
        tarjetaDocumentEntity.setDatos(DatosMock.completarInfoDatos());
        tarjetaDocumentEntity.setAutorizaciones(AutorizacionesMock.completarInfoAutorizaciones());
        return tarjetaDocumentEntity;
    }

}