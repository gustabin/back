package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EntityBase;

/**
 * Datos para consulta de servicio
 * PLTenenciaValuadaService/ConsultaHabilitacionCompraVentaBonos
 * 
 * @author A309331
 *
 */
public class DatosHabilitaCompraVentaUSDEntity extends EntityBase {

	/** The nup. */
	@JsonProperty("Nup")
	private String nup;

	public String getNup() {
		return nup;
	}

	public void setNup(String nup) {
		this.nup = nup;
	}

}
