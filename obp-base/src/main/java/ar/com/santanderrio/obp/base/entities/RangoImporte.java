package ar.com.santanderrio.obp.base.entities;

import java.math.BigDecimal;

// TODO: Auto-generated Javadoc
/**
 * The Class RangoImporte.
 */
public class RangoImporte {

	/** The importe desde. */
	private BigDecimal importeDesde;

	/** The importe hasta. */
	private BigDecimal importeHasta;

	/**
	 * Instantiates a new rango importe.
	 *
	 * @param importeDesde
	 *            the importe desde
	 * @param importeHasta
	 *            the importe hasta
	 */
	public RangoImporte(BigDecimal importeDesde, BigDecimal importeHasta) {
		this.importeDesde = importeDesde;
		this.importeHasta = importeHasta;
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
	 * Setter para importe desde.
	 *
	 * @param bigDecimal
	 *            el nuevo importe desde
	 */
	public void setImporteDesde(BigDecimal bigDecimal) {
		this.importeDesde = bigDecimal;
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
	 * Setter para importe hasta.
	 *
	 * @param importeHasta
	 *            el nuevo importe hasta
	 */
	public void setImporteHasta(BigDecimal importeHasta) {
		this.importeHasta = importeHasta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((importeDesde == null) ? 0 : importeDesde.hashCode());
		result = prime * result + ((importeHasta == null) ? 0 : importeHasta.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RangoImporte other = (RangoImporte) obj;
		if (importeDesde == null) {
			if (other.importeDesde != null) {
				return false;
			}
		} else if (!importeDesde.equals(other.importeDesde)) {
			return false;
		}
		if (importeHasta == null) {
			if (other.importeHasta != null) {
				return false;
			}
		} else if (!importeHasta.equals(other.importeHasta)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RangoImporte [" + (importeDesde != null ? "importeDesde=" + importeDesde + ", " : "")
				+ (importeHasta != null ? "importeHasta=" + importeHasta : "") + "]";
	}

}
