/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.mock;

import ar.com.santanderrio.obp.servicios.comun.dao.entities.DatosSolicitanteEntity;

/**
 * The Class DatosSolicitanteEntityMock.
 *
 * @author florencia.n.martinez
 */
public class DatosSolicitanteEntityMock {

    /**
     * Completar datos solicitante entity.
     *
     * @return the datos solicitante entity
     */
    public static DatosSolicitanteEntity completarDatosSolicitanteEntity() {
        DatosSolicitanteEntity datosSolicitanteEntity = new DatosSolicitanteEntity();
        datosSolicitanteEntity.setNup("");
        datosSolicitanteEntity.setApellido("");
        datosSolicitanteEntity.setNombre("");
        datosSolicitanteEntity.setDoc("30882323");
        datosSolicitanteEntity.setDocTipo("N ");
        datosSolicitanteEntity.setEstadoCivil("S");
        datosSolicitanteEntity.setFechaNacimiento("01/01/1972");
        datosSolicitanteEntity.setNacionalidad("080");
        datosSolicitanteEntity.setPaisNacimiento("080");
        datosSolicitanteEntity.setSexo("");
        datosSolicitanteEntity.setCalle("");
        datosSolicitanteEntity.setCalleNro("");
        datosSolicitanteEntity.setPiso("");
        datosSolicitanteEntity.setDepto("");
        datosSolicitanteEntity.setLocalidad("");
        datosSolicitanteEntity.setCp("");
        datosSolicitanteEntity.setTelefono("");
        return datosSolicitanteEntity;
    }

}
