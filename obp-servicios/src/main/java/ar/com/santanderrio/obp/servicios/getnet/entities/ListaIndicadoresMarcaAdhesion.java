package ar.com.santanderrio.obp.servicios.getnet.entities;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.beanio.annotation.Field;

public class ListaIndicadoresMarcaAdhesion {

	/** indicador. */
	@Field
	private String indicador;
	
	/** valorIndicador. */
	@Field
	private String valorIndicador;
	
	/** usuarioAlta. */
	@Field
	private String usuarioAlta;
	
	/** fechaAlta. */
	@Field
	private String fechaAlta;
	
	/** usuarioModificacion. */
	@Field
	private String usuarioModificacion;
	
	/** terminalModificacion. */
	@Field
	private String terminalModificacion;
	
	/** sucursalModificacion. */
	@Field
	private String sucursalModificacion;
	
	/** timestamp. */
	@Field
	private String timestamp;

	public String getIndicador() {
		return indicador;
	}

	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	public String getValorIndicador() {
		return valorIndicador;
	}

	public void setValorIndicador(String valorIndicador) {
		this.valorIndicador = valorIndicador;
	}

	public String getUsuarioAlta() {
		return usuarioAlta;
	}

	public void setUsuarioAlta(String usuarioAlta) {
		this.usuarioAlta = usuarioAlta;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public String getTerminalModificacion() {
		return terminalModificacion;
	}

	public void setTerminalModificacion(String terminalModificacion) {
		this.terminalModificacion = terminalModificacion;
	}

	public String getSucursalModificacion() {
		return sucursalModificacion;
	}

	public void setSucursalModificacion(String sucursalModificacion) {
		this.sucursalModificacion = sucursalModificacion;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("indicador", indicador);
		builder.append("valorIndicador", valorIndicador);
		builder.append("usuarioAlta", usuarioAlta);
		builder.append("fechaAlta", fechaAlta);
		builder.append("usuarioModificacion", usuarioModificacion);
		builder.append("terminalModificacion", terminalModificacion);
		builder.append("sucursalModificacion", sucursalModificacion);
		builder.append("timestamp", timestamp);
		return builder.toString();
	}
	
	
}
