package ar.com.santanderrio.obp.servicios.pagohaberes.web.view;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonIgnore;

public class UltimosDiasCambioClaveTokenView {

    /** Se usa en RSA */
    @JsonIgnore
    private Integer cantDiasUltimoCambioClave;

    /** Se usa en RSA */
    @JsonIgnore
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
