/*
 * 
 */
package ar.com.santanderrio.obp.servicios.ofuscardato.dto;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * The Interface CompraVentaSEI.
 *
 * @author Emanuel Diaz
 */
public class MostrarDatoDTO extends RsaDTO {

	private static final long serialVersionUID = 1L;

	public MostrarDatoDTO(OperacionesRSAEnum tipoOperacion) {
		super(tipoOperacion);
	}
	
    private Integer cantDiasUltimoCambioClave;
    
    private Integer cantDiasUltimoCambioToken;
    
	
	public Integer getCantDiasUltimoCambioClave() {
		return cantDiasUltimoCambioClave;
	}

	public void setCantDiasUltimoCambioClave(Integer cantDiasUltimoCambioClave) {
		this.cantDiasUltimoCambioClave = cantDiasUltimoCambioClave;
	}

	public Integer getCantDiasUltimoCambioToken() {
		return cantDiasUltimoCambioToken;
	}

	public void setCantDiasUltimoCambioToken(Integer cantDiasUltimoCambioToken) {
		this.cantDiasUltimoCambioToken = cantDiasUltimoCambioToken;
	}

}
