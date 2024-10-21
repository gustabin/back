/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.util;

import org.apache.commons.collections.Predicate;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Movimiento;

/**
 * The Class PalabraClavePredicate.
 */
public class PalabraClavePredicate implements Predicate {

	/** The palabra clave. */
	private String palabraClave;

	/**
	 * Instantiates a new palabra clave predicate.
	 *
	 * @param palabraClave
	 *            the palabra clave
	 */
	public PalabraClavePredicate(String palabraClave) {
		this.palabraClave = palabraClave;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.collections.Predicate#evaluate(java.lang.Object)
	 */
	@Override
	public boolean evaluate(Object object) {
		Movimiento detalleMovimiento = (Movimiento) object;
		return ISBANStringUtils.normalizarCaraceteres(detalleMovimiento.getObservacion()).indexOf(palabraClave) != -1
				|| ISBANStringUtils.normalizarCaraceteres(detalleMovimiento.getDescripcionAdicionalMovimiento())
						.indexOf(palabraClave) != -1;
	}
}
