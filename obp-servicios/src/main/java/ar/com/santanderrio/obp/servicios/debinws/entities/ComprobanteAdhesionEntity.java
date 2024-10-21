package ar.com.santanderrio.obp.servicios.debinws.entities;

import java.util.List;

import ar.com.santanderrio.obp.servicios.debin.entities.ElementoComprobanteAdhesionReporte;

/**
 * The Class ComprobanteAdhesionEntity.
 */
public class ComprobanteAdhesionEntity {
	
	private String titulo;
	
	private String subtitulo;
	
	private String fecha;
	
	private String numeroComprobante;

	List<ElementoComprobanteAdhesionReporte> cuentasAdhesion;
	
	List<ElementoComprobanteAdhesionReporte> cuentasBaja;
		
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * @return the subtitulo
	 */
	public String getSubtitulo() {
		return subtitulo;
	}

	/**
	 * @param subtitulo the subtitulo to set
	 */
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * @return the cuentasAdhesion
	 */
	public List<ElementoComprobanteAdhesionReporte> getCuentasAdhesion() {
		return cuentasAdhesion;
	}

	/**
	 * @param cuentasAdhesion the cuentasAdhesion to set
	 */
	public void setCuentasAdhesion(List<ElementoComprobanteAdhesionReporte> cuentasAdhesion) {
		this.cuentasAdhesion = cuentasAdhesion;
	}

	/**
	 * @return the cuentasBaja
	 */
	public List<ElementoComprobanteAdhesionReporte> getCuentasBaja() {
		return cuentasBaja;
	}

	/**
	 * @param cuentasBaja the cuentasBaja to set
	 */
	public void setCuentasBaja(List<ElementoComprobanteAdhesionReporte> cuentasBaja) {
		this.cuentasBaja = cuentasBaja;
	}

	
}
