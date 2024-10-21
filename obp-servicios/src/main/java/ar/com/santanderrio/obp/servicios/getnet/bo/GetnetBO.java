package ar.com.santanderrio.obp.servicios.getnet.bo;

import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.getnet.dto.GetnetAdhesionDTO;
import ar.com.santanderrio.obp.servicios.getnet.dto.GetnetConfiguracionOutDTO;
import ar.com.santanderrio.obp.servicios.getnet.view.GetnetAdhesionOutView;

/**
 * The Interface GetnetBO.
 */
public interface GetnetBO {

	/**
     * Chequea si el nup esta habilitado.
     * 
     * @return the getnet configuracion out dto
     */
	Respuesta<GetnetConfiguracionOutDTO> configuracion();
	
	/**
     * Confirma la adhesion a GetNet
     * @param
     * 		the dto
     * @return the getnet adhesion out view
     */
	Respuesta<GetnetAdhesionOutView> confirmarAdhesion(GetnetAdhesionDTO dto);
	
	/**
     * Descarga comprobante adhesion
     *
     * @return the reporte
     */
    Respuesta<Reporte> descargaComprobanteAdhesion();
    
}
