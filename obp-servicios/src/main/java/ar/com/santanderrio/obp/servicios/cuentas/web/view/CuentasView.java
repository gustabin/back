/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The Class CuentasView.
 */
public class CuentasView {

	/** The cuentas. */
	private List<CuentasAdhesionDebitoView> cuentas;

	/** The selected. */
	private int selected;

	/** The has cuentas activas. */
	private Boolean hasCuentasActivas = false;

	/**
	 * Gets the checks for cuentas activas.
	 *
	 * @return the checks for cuentas activas
	 */
	public Boolean getHasCuentasActivas() {
		return hasCuentasActivas;
	}

	/**
	 * Setter para checks for cuentas activas.
	 *
	 * @param hasCuentasActivas
	 *            el nuevo checks for cuentas activas
	 */
	public void setHasCuentasActivas(Boolean hasCuentasActivas) {
		this.hasCuentasActivas = hasCuentasActivas;
	}

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<CuentasAdhesionDebitoView> getCuentas() {
		return cuentas;
	}

	/**
	 * Setter para cuentas.
	 *
	 * @param cuentas
	 *            el nuevo cuentas
	 */
	public void setCuentas(List<CuentasAdhesionDebitoView> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * Gets the selected.
	 *
	 * @return the selected
	 */
	public int getSelected() {
		return selected;
	}

	/**
	 * Setter para selected.
	 *
	 * @param selected
	 *            el nuevo selected
	 */
	public void setSelected(int selected) {
		this.selected = selected;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 * 
	 * @author emilio.watemberg
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cuentas);
		hcb.append(hasCuentasActivas);
		hcb.append(selected);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * @author emilio.watemberg
	 */
	@Override
	public boolean equals(Object obj) {
		CuentasView other = (CuentasView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cuentas, other.getCuentas());
		eb.append(hasCuentasActivas, other.getHasCuentasActivas());
		eb.append(selected, other.getSelected());
		eb.append(cuentas, other.getCuentas());
		eb.append(cuentas, other.getCuentas());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CuentasView [cuentas=" + cuentas + ", selected=" + selected + ", hasCuentasActivas=" + hasCuentasActivas
				+ "]";
	}

}
