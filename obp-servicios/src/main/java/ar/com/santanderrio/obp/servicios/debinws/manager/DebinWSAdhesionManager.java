package ar.com.santanderrio.obp.servicios.debinws.manager;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.debinws.view.ConsultarAdhesionDebinesView;
import ar.com.santanderrio.obp.servicios.debinws.view.GestionarAdhesionDebinesView;

/**
 * The Interface DebinWSAdhesionManager.
 */
public interface DebinWSAdhesionManager {

    /**
     * gestionarAdhesionDebines.
     *
     * @return the respuesta
     */
    Respuesta<ConsultarAdhesionDebinesView> buscarCuentasParaAdhesionDebines();

    /**
     * Gestionar adhesion debines.
     *
     * @param gestionAdhesionInDTO the gestion adhesion in DTO
     * @return the respuesta
     */
    Respuesta<GestionarAdhesionDebinesView> gestionarAdhesionDebines(GestionarAdhesionDebinesView gestionAdhesionInDTO);

    /**
     * Descargar comprobante adhesion.
     *
     * @return the respuesta
     */
    Respuesta<Reporte> descargarComprobanteAdhesion();

}
