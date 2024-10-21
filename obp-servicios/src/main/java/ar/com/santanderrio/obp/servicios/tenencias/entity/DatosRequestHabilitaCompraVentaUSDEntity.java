package ar.com.santanderrio.obp.servicios.tenencias.entity;

import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RequestBaseFirmado;

/**
 * Detalle de consulta al servicio
 * PLTenenciaValuadaService/ConsultaHabilitacionCompraVentaBonos
 * 
 * @author A309331
 *
 */
public class DatosRequestHabilitaCompraVentaUSDEntity extends RequestBaseFirmado {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -101786720799030056L;

	@JsonProperty("Datos")
	private DatosHabilitaCompraVentaUSDEntity datos;

	public DatosHabilitaCompraVentaUSDEntity getDatos() {
		return datos;
	}

	public void setDatos(DatosHabilitaCompraVentaUSDEntity datos) {
		this.datos = datos;
	}

}
