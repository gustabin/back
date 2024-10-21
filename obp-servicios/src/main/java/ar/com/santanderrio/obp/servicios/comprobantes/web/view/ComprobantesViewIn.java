/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.base.web.view.View;

/**
 * The Class ComprobantesViewIn.
 * 
 * @author sabrina.cis
 */
public class ComprobantesViewIn extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id comprobante. */
	private String idComprobante;

	/** The recarga pagina. */
	private Boolean recargaPagina;

	/** The fecha desde. */
	private String fechaDesde;

	/** The fecha hasta. */
	private String fechaHasta;

	/** The transacciones. */
	private List<TransaccionViewIn> transacciones;

	/** The importe desde. */
	private BigDecimal importeDesde;

	/** The importe hasta. */
	private BigDecimal importeHasta;

	/** The empresa. */
	private String empresa;

	/**
	 * Instantiates a new comprobantes view in.
	 */
	public ComprobantesViewIn() {
		super();
	}

	/**
	 * Instantiates a new comprobantes view in.
	 * 
	 * @param id
	 *            the id
	 */
	public ComprobantesViewIn(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new comprobantes view in.
	 * 
	 * @param recargaPagina
	 *            the recarga pagina
	 * @param fechaDesde
	 *            the fecha desde
	 * @param fechaHasta
	 *            the fecha hasta
	 * @param transacciones
	 *            the transacciones
	 * @param importeDesde
	 *            the importe desde
	 * @param importeHasta
	 *            the importe hasta
	 * @param empresa
	 *            the empresa
	 */
	public ComprobantesViewIn(Boolean recargaPagina, String fechaDesde, String fechaHasta,
			List<TransaccionViewIn> transacciones, BigDecimal importeDesde, BigDecimal importeHasta, String empresa) {
		super();
		this.recargaPagina = recargaPagina;
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.transacciones = transacciones;
		this.importeDesde = importeDesde;
		this.importeHasta = importeHasta;
		this.empresa = empresa;
	}

	/**
	 * Gets the id comprobante.
	 * 
	 * @return the id comprobante
	 */
	public String getIdComprobante() {
		return idComprobante;
	}

	/**
	 * Sets the id comprobante.
	 * 
	 * @param idComprobante
	 *            the new id comprobante
	 */
	public void setIdComprobante(String idComprobante) {
		this.idComprobante = idComprobante;
	}

	/**
	 * Gets the recarga pagina.
	 * 
	 * @return the recargaPagina
	 */
	public Boolean getRecargaPagina() {
		return recargaPagina;
	}

	/**
	 * Sets the recarga pagina.
	 * 
	 * @param recargaPagina
	 *            the recargaPagina to set
	 */
	public void setRecargaPagina(Boolean recargaPagina) {
		this.recargaPagina = recargaPagina;
	}

	/**
	 * Gets the fecha desde.
	 * 
	 * @return the fecha desde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Sets the fecha desde.
	 * 
	 * @param fechaDesde
	 *            the new fecha desde
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fecha hasta.
	 * 
	 * @return the fecha hasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Sets the fecha hasta.
	 * 
	 * @param fechaHasta
	 *            the new fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	/**
	 * Gets the transacciones.
	 * 
	 * @return the transacciones
	 */
	public List<TransaccionViewIn> getTransacciones() {
		return transacciones;
	}

	/**
	 * Sets the transacciones.
	 * 
	 * @param transacciones
	 *            the new transacciones
	 */
	public void setTransacciones(List<TransaccionViewIn> transacciones) {
		this.transacciones = transacciones;
	}

	/**
	 * Gets the importe desde.
	 * 
	 * @return the importe desde
	 */
	public BigDecimal getImporteDesde() {
		return importeDesde;
	}

	/**
	 * Sets the importe desde.
	 * 
	 * @param importeDesde
	 *            the new importe desde
	 */
	public void setImporteDesde(BigDecimal importeDesde) {
		this.importeDesde = importeDesde;
	}

	/**
	 * Gets the importe hasta.
	 * 
	 * @return the importe hasta
	 */
	public BigDecimal getImporteHasta() {
		return importeHasta;
	}

	/**
	 * Sets the importe hasta.
	 * 
	 * @param importeHasta
	 *            the new importe hasta
	 */
	public void setImporteHasta(BigDecimal importeHasta) {
		this.importeHasta = importeHasta;
	}

	/**
	 * Gets the empresa.
	 * 
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * Sets the empresa.
	 * 
	 * @param empresa
	 *            the new empresa
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(idComprobante);
		hcb.append(recargaPagina);
		hcb.append(fechaDesde);
		hcb.append(fechaHasta);
		hcb.append(transacciones);
		hcb.append(importeDesde);
		hcb.append(importeHasta);
		hcb.append(empresa);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComprobantesViewIn other = (ComprobantesViewIn) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(idComprobante, other.getIdComprobante());
		eb.append(recargaPagina, other.getRecargaPagina());
		eb.append(fechaDesde, other.getFechaDesde());
		eb.append(fechaHasta, other.getFechaHasta());
		eb.append(transacciones, other.getTransacciones());
		eb.append(importeDesde, other.getImporteDesde());
		eb.append(importeHasta, other.getImporteHasta());
		eb.append(empresa, other.getEmpresa());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(idComprobante);
		sb.append(recargaPagina);
		sb.append(fechaDesde);
		sb.append(fechaHasta);
		sb.append(transacciones);
		sb.append(importeDesde);
		sb.append(importeHasta);
		sb.append(empresa);
		return sb.toString();
	}

}
