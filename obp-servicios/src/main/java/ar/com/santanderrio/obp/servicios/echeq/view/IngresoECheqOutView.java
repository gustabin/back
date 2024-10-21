package ar.com.santanderrio.obp.servicios.echeq.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class ConfiguracionGrillaECheqView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class IngresoECheqOutView {
	
	/** The nombre. */
	private String nombre;
	
	/** The apellido. */
	private String apellido;
	
	/** The cuit. */
	private String cuit;
	
	/** The legal. */
	private String legal;

	/** The estados emitidos. */
	private List<ComboEstadoView> estadosEmitidos;
	
	/** The estados recibidos. */
	private List<ComboEstadoView> estadosRecibidos;
	
	/** The estados endosados. */
	private List<ComboEstadoView> estadosEndosados;
	
	/** The intervalo buscador. */
	private List<Integer> intervaloBuscador;
	
	/** The rango fecha buscador. */
	private Integer rangoFechaBuscador;

	/** The habilita emitir echeq. */
	private Boolean habilitaEmitirEcheq;

	/** The habilita buscador. */
	private Boolean habilitaBuscador;

	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the apellido.
	 *
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Sets the apellido.
	 *
	 * @param apellido the new apellido
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Gets the cuit.
	 *
	 * @return the cuit
	 */
	public String getCuit() {
		return cuit;
	}

	/**
	 * Sets the cuit.
	 *
	 * @param cuit the new cuit
	 */
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	/**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal the new legal
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}

	/**
	 * Gets the estados emitidos.
	 *
	 * @return the estadosEmitidos
	 */
	public List<ComboEstadoView> getEstadosEmitidos() {
		return estadosEmitidos;
	}

	/**
	 * Sets the estados emitidos.
	 *
	 * @param estadosEmitidos the estadosEmitidos to set
	 */
	public void setEstadosEmitidos(List<ComboEstadoView> estadosEmitidos) {
		this.estadosEmitidos = estadosEmitidos;
	}

	/**
	 * Gets the estados recibidos.
	 *
	 * @return the estadosRecibidos
	 */
	public List<ComboEstadoView> getEstadosRecibidos() {
		return estadosRecibidos;
	}

	/**
	 * Sets the estados recibidos.
	 *
	 * @param estadosRecibidos the estadosRecibidos to set
	 */
	public void setEstadosRecibidos(List<ComboEstadoView> estadosRecibidos) {
		this.estadosRecibidos = estadosRecibidos;
	}

	/**
	 * Gets the estados endosados.
	 *
	 * @return the estadosEndosados
	 */
	public List<ComboEstadoView> getEstadosEndosados() {
		return estadosEndosados;
	}

	/**
	 * Sets the estados endosados.
	 *
	 * @param estadosEndosados the estadosEndosados to set
	 */
	public void setEstadosEndosados(List<ComboEstadoView> estadosEndosados) {
		this.estadosEndosados = estadosEndosados;
	}

	/**
	 * Gets the intervalo buscador.
	 *
	 * @return the intervaloBuscador
	 */
	public List<Integer> getIntervaloBuscador() {
		return intervaloBuscador;
	}

	/**
	 * Sets the intervalo buscador.
	 *
	 * @param intervaloBuscador the intervaloBuscador to set
	 */
	public void setIntervaloBuscador(List<Integer> intervaloBuscador) {
		this.intervaloBuscador = intervaloBuscador;
	}

	/**
	 * Gets the rango fecha buscador.
	 *
	 * @return the rangoFechaBuscador
	 */
	public Integer getRangoFechaBuscador() {
		return rangoFechaBuscador;
	}

	/**
	 * Sets the rango fecha buscador.
	 *
	 * @param rangoFechaBuscador the rangoFechaBuscador to set
	 */
	public void setRangoFechaBuscador(Integer rangoFechaBuscador) {
		this.rangoFechaBuscador = rangoFechaBuscador;
	}

	/**
	 * @return the habilitaEmitirEcheq
	 */
	public Boolean getHabilitaEmitirEcheq() {
		return habilitaEmitirEcheq;
	}

	/**
	 * @param habilitaEmitirEcheq the habilitaEmitirEcheq to set
	 */
	public void setHabilitaEmitirEcheq(Boolean habilitaEmitirEcheq) {
		this.habilitaEmitirEcheq = habilitaEmitirEcheq;
	}

	/**
	 * @return the habilitaBuscador
	 */
	public Boolean getHabilitaBuscador() {
		return habilitaBuscador;
	}

	/**
	 * @param habilitaBuscador the habilitaBuscador to set
	 */
	public void setHabilitaBuscador(Boolean habilitaBuscador) {
		this.habilitaBuscador = habilitaBuscador;
	}

}
