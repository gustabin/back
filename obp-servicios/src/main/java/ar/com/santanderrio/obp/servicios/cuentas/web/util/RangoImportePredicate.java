/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.util;

import org.apache.commons.collections.Predicate;

import ar.com.santanderrio.obp.base.entities.RangoImporte;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Movimiento;

/**
 * The Class RangoImportePredicate.
 */
public class RangoImportePredicate implements Predicate {

	/** The rango importe. */
	private RangoImporte rangoImporte;

	/**
	 * Instantiates a new rango importe predicate.
	 *
	 * @param rangoImporte
	 *            the rango importe
	 */
	public RangoImportePredicate(RangoImporte rangoImporte) {
		this.rangoImporte = rangoImporte;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.commons.collections.Predicate#evaluate(java.lang.Object)
	 */
	@Override
	public boolean evaluate(Object object) {
		Movimiento detalleMovimiento = (Movimiento) object;
		int res1 = ISBANStringUtils.parseStringToBigDecimal(detalleMovimiento.getImporteMovimiento()).abs()
				.compareTo(rangoImporte.getImporteDesde());
		// res1 >= 0 - mayor o igual al importe desde
		int res2 = ISBANStringUtils.parseStringToBigDecimal(detalleMovimiento.getImporteMovimiento()).abs()
				.compareTo(rangoImporte.getImporteHasta());
		// res2 <=0 - menor o igual al importe hasta
		return res1 >= 0 && res2 <= 0;
	}

}
