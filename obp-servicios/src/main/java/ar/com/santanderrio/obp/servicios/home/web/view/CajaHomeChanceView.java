package ar.com.santanderrio.obp.servicios.home.web.view;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.PeriodoActualDTO;

/**
 * The Class CajaHomeChanceView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CajaHomeChanceView extends Caja{
	
	/** The Constant CHANCE. */
	private static final String CHANCE_TITULO = "BENEFICIOS POR COBRAR TU SUELDO";
	
	/** The Constant CHANCE_ENCABEZADO. */
	private static final String CHANCE_ENCABEZADO = "Chances del mes";
	
	/** The Constant GUION. */
	private static final String GUION = "-";
	
	/** The Boolean is chance. */
	private Boolean isChances;
	
	/** The Boolean recargar. */
	private Boolean recargar;
	
	/** The String titulo. */
	private String titulo;
	
	/** The String encabezado. */
	private String encabezado;
	
	/** The cantidad. */
	private String cantidad;
	
	/** The fecha corte. */
	private String fechaCorte;
	
	/**
     * Instantiates a new caja home chance view.
     */
	public CajaHomeChanceView(Boolean hayCuentaSueldo) {
		super();
		this.titulo = CHANCE_TITULO;
		this.encabezado = CHANCE_ENCABEZADO;
		this.isChances = hayCuentaSueldo;
		this.recargar = true;
	}

	/**
     * Instantiates a new caja home chance view.
     */
	public CajaHomeChanceView(PeriodoActualDTO periodoActualDTO) {
		if (!StringUtils.isBlank(periodoActualDTO.getTotalChancesPeriodo())) {
			this.cantidad = periodoActualDTO.getTotalChancesPeriodo();
		}
		else {
			this.cantidad = GUION;
		}
		this.fechaCorte = periodoActualDTO.getFechaCorte();
	}
	
	/**
     * Gets the isChances.
     *
	 * @return the isChances
	 */
	public Boolean getIsChances() {
		return isChances;
	}

	/**
     * Sets the isChances.
     *
	 * @param isChance the isChances to set
	 */
	public void setIsChances(Boolean isChances) {
		this.isChances = isChances;
	}

	/**
     * Gets the recargar.
     *
	 * @return the recargar
	 */
	public Boolean getRecargar() {
		return recargar;
	}

	/**
     * Sets the recargar.
     *
	 * @param recargar the recargar to set
	 */
	public void setRecargar(Boolean recargar) {
		this.recargar = recargar;
	}

	/**
     * Gets the titulo.
     *
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
     * Sets the titulo.
     *
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
     * Gets the encabezado.
     *
	 * @return the encabezado
	 */
	public String getEncabezado() {
		return encabezado;
	}

	/**
     * Sets the encabezado.
     *
	 * @param encabezado the encabezado to set
	 */
	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

	/**
     * Gets the cantidad.
     *
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}

	/**
     * Sets the cantidad.
     *
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	
	/**
     * Gets the fecha corte.
     *
	 * @return the fecha corte
	 */
	public String getFechaCorte() {
		return fechaCorte;
	}

	/**
     * Sets the fecha corte.
     *
	 * @param fechaCorte
	 * 		the fecha corte to set
	 */
	public void setFechaCorte(String fechaCorte) {
		this.fechaCorte = fechaCorte;
	}
	
}
