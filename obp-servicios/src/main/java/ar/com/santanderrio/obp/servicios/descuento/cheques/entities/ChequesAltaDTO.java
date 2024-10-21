/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.entities;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;

/**
 * The Class ChequesAltaDTO.
 */
public class ChequesAltaDTO extends ChequesSimuladosDTO{
	
	/** The cuenta credito. */
	private IdentificacionCuenta cuentaCredito;
	
	/** The importe total cheque. */
	private BigDecimal importeTotalCheque;
	
	/** The importe A acreditar. */
	private BigDecimal importeAAcreditar;
	

	/**
	 * Gets the cuenta credito.
	 *
	 * @return the cuenta credito
	 */
	public IdentificacionCuenta getCuentaCredito() {
		return cuentaCredito;
	}

	/**
	 * Sets the cuenta credito.
	 *
	 * @param cuentaCredito
	 *            the new cuenta credito
	 */
	public void setCuentaCredito(IdentificacionCuenta cuentaCredito) {
		this.cuentaCredito = cuentaCredito;
	}

	/**
	 * Gets the importe total cheque.
	 *
	 * @return the importe total cheque
	 */
	public BigDecimal getImporteTotalCheque() {
		return importeTotalCheque;
	}

	/**
	 * Sets the importe total cheque.
	 *
	 * @param importeTotalCheque
	 *            the new importe total cheque
	 */
	public void setImporteTotalCheque(BigDecimal importeTotalCheque) {
		this.importeTotalCheque = importeTotalCheque;
	}

	/**
	 * Gets the importe A acreditar.
	 *
	 * @return the importe A acreditar
	 */
	public BigDecimal getImporteAAcreditar() {
		return importeAAcreditar;
	}

	/**
	 * Sets the importe A acreditar.
	 *
	 * @param importeAAcreditar
	 *            the new importe A acreditar
	 */
	public void setImporteAAcreditar(BigDecimal importeAAcreditar) {
		this.importeAAcreditar = importeAAcreditar;
	}
	
	

}
