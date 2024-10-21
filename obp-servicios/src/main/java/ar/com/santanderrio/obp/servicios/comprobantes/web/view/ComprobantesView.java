/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.base.web.view.View;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;

/**
 * The Class ComprobantesView.
 */
public class ComprobantesView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The quedan comprobantes. */
	private boolean hayMasComprobantes;

	/** The lista comprobantes. */
	private List<ComprobanteView> comprobantes;

	/** The limites PMC. */
	private String limitesPMC;

	/** The limite transferencias. */
	private String limiteTransferencias;

	/** The limite pago tarjetas. */
	private String limitePagoTarjetas;

	/**
	 * Instantiates a new comprobantes view.
	 */
	public ComprobantesView() {
		super();
	}

	/**
	 * Instantiates a new comprobantes view.
	 *
	 * @param id
	 *            the id
	 */
	public ComprobantesView(String id) {
		super(id);
	}

	/**
	 * Instantiates a new comprobantes view.
	 *
	 * @param comprobantes
	 *            the comprobantes
	 * @param hayMasComprobantes
	 *            the hay mas comprobantes
	 * @param isMobile
	 *            the is mobile
	 */
	public ComprobantesView(List<ComprobanteDTO> comprobantes, Boolean hayMasComprobantes, Boolean isMobile) {
		super();
		this.hayMasComprobantes = hayMasComprobantes;
		List<ComprobanteView> comprobantesView = new ArrayList<ComprobanteView>();
		for (ComprobanteDTO comprobante : comprobantes) {
			comprobantesView.add(new ComprobanteView(comprobante, isMobile));
		}
		this.comprobantes = comprobantesView;

	}

	/**
	 * Checks if is hay mas comprobantes.
	 *
	 * @return the hayMasComprobantes
	 */
	public boolean isHayMasComprobantes() {
		return hayMasComprobantes;
	}

	/**
	 * Checks if is quedan comprobantes.
	 *
	 * @return the quedanComprobantes
	 */
	public boolean hayMasComprobantes() {
		return hayMasComprobantes;
	}

	/**
	 * Sets the quedan comprobantes.
	 *
	 * @param hayMasComprobantes
	 *            the new hay mas comprobantes
	 */
	public void setHayMasComprobantes(boolean hayMasComprobantes) {
		this.hayMasComprobantes = hayMasComprobantes;
	}

	/**
	 * Gets the lista destinatarios.
	 *
	 * @return the listaDestinatarios
	 */
	public List<ComprobanteView> getComprobantes() {
		return comprobantes;
	}

	/**
	 * Sets the lista comprobantes.
	 *
	 * @param comprobantes
	 *            the new comprobantes
	 */
	public void setComprobantes(List<ComprobanteView> comprobantes) {
		this.comprobantes = comprobantes;
	}

	/**
	 * Gets the limites PMC.
	 *
	 * @return the limites PMC
	 */
	public String getLimitesPMC() {
		return limitesPMC;
	}

	/**
	 * Sets the limites PMC.
	 *
	 * @param limitesPMC
	 *            the new limites PMC
	 */
	public void setLimitesPMC(String limitesPMC) {
		this.limitesPMC = limitesPMC;
	}

	/**
	 * Gets the limite transferencias.
	 *
	 * @return the limite transferencias
	 */
	public String getLimiteTransferencias() {
		return limiteTransferencias;
	}

	/**
	 * Sets the limite transferencias.
	 *
	 * @param limiteTransferencias
	 *            the new limite transferencias
	 */
	public void setLimiteTransferencias(String limiteTransferencias) {
		this.limiteTransferencias = limiteTransferencias;
	}

	/**
	 * Gets the limite pago tarjetas.
	 *
	 * @return the limite pago tarjetas
	 */
	public String getLimitePagoTarjetas() {
		return limitePagoTarjetas;
	}

	/**
	 * Sets the limite pago tarjetas.
	 *
	 * @param limitePagoTarjetas
	 *            the new limite pago tarjetas
	 */
	public void setLimitePagoTarjetas(String limitePagoTarjetas) {
		this.limitePagoTarjetas = limitePagoTarjetas;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(comprobantes);
		hcb.append(hayMasComprobantes);
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
		ComprobantesView other = (ComprobantesView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(comprobantes, other.getComprobantes());
		eb.append(hayMasComprobantes, other.hayMasComprobantes());
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
		sb.append(comprobantes);
		sb.append(hayMasComprobantes);
		return sb.toString();
	}

}
