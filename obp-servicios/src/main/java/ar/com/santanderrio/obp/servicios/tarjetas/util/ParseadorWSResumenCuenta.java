/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util;

import java.math.BigDecimal;
import java.util.List;

import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.FechaTarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.SaldoEnCuentaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.impl.ParseadorVisaException;

/**
 * The Interface ParseadorWSResumenCuenta.
 *
 * @author sabrina.cis
 */
public interface ParseadorWSResumenCuenta {

	/**
	 * Obtener saldo en cuenta.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the saldo en cuenta
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	SaldoEnCuentaEntity obtenerSaldoEnCuenta(TarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Obtener fecha de cierre formada.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerFechaDeCierreFormada(TarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Obtener fechas.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	List<FechaTarjetaEntity> obtenerFechas(TarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Obtener fecha de vencimiento formada.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerFechaDeVencimientoFormada(TarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Obtener saldo tipo.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param tipoSaldo
	 *            the tipo saldo
	 * @return the big decimal
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	BigDecimal obtenerSaldos(TarjetaEntity tarjeta, String tipoSaldo)
			throws TarjetaBOUtilsException, ParseadorVisaException;

	/**
	 * Tiene limite unificado.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	Boolean tieneLimiteUnificado(TarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Obtener fecha por cierre.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerFechaPorCierre(TarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Obtener fecha por vencimiento.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerFechaPorVencimiento(TarjetaEntity tarjeta) throws ParseadorVisaException;

	/**
	 * Obtener saldo tipo.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @param tipoSaldo
	 *            the tipo saldo
	 * @return the big decimal
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	BigDecimal obtenerSaldoTipo(TarjetaEntity tarjeta, String tipoSaldo)
			throws ParseadorVisaException, TarjetaBOUtilsException;

	/**
	 * Obtener limite unificado.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	String obtenerLimiteUnificado(TarjetaEntity tarjeta) throws ParseadorVisaException;

}
