/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DatosEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.FechaTarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.LimiteTarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.SaldoEnCuentaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaDocumentEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSResumenCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class ParseadorWSResumenCuentaImpl.
 *
 * @author sabrina.cis
 */
@Component
public class ParseadorWSResumenCuentaImpl extends ParseadorWSVisaImpl implements ParseadorWSResumenCuenta {

	/**
	 * Obtener saldo en cuenta. Tag tarjetas/tarjeta/document/saldoenCuenta/
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the saldo en cuenta
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public SaldoEnCuentaEntity obtenerSaldoEnCuenta(TarjetaEntity tarjeta) throws ParseadorVisaException {
		TarjetaDocumentEntity document = obtenerDocument(tarjeta);
		if (document.getSaldoEnCuenta() == null) {
			throw new ParseadorVisaException();
		}
		return document.getSaldoEnCuenta();
	}

	/**
	 * Obtener fecha de cierre formada. Tag
	 * tarjetas/tarjeta/document/saldoenCuenta/fechas/fecha..
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public List<FechaTarjetaEntity> obtenerFechas(TarjetaEntity tarjeta) throws ParseadorVisaException {
		SaldoEnCuentaEntity saldoEnCuenta = obtenerSaldoEnCuenta(tarjeta);
		if (saldoEnCuenta == null) {
			throw new ParseadorVisaException();
		}
		if (saldoEnCuenta.getFechas().getFechas() == null) {
			throw new ParseadorVisaException();
		}
		return saldoEnCuenta.getFechas().getFechas();
	}

	/**
	 * Obtener fecha de cierre formada. Tag
	 * tarjetas/tarjeta/document/saldoenCuenta/fechas/fecha..
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerFechaDeCierreFormada(TarjetaEntity tarjeta) throws ParseadorVisaException {
		List<FechaTarjetaEntity> v1 = obtenerFechas(tarjeta);
		if (!v1.isEmpty()) {
			return TarjetaBOUtils.obtenerFechaPorCierre(v1);
		} else {
			throw new ParseadorVisaException();
		}
	}

	/**
	 * Obtiene los datos de la fecha de vencimiento de la Tarjeta cumpliendo el
	 * formato. Tag tarjetas/tarjeta/document/saldoenCuenta/fechas/fecha..
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerFechaDeVencimientoFormada(TarjetaEntity tarjeta) throws ParseadorVisaException {
		List<FechaTarjetaEntity> fechas = obtenerFechas(tarjeta);
		if (!fechas.isEmpty()) {
			return TarjetaBOUtils.obtenerFechaPorVencimiento(fechas);
		} else {
			throw new ParseadorVisaException();
		}
	}

	/**
	 * Obteniene la FechaDesde. Tag tarjetas/tarjeta/document/datos/fechaDesde/
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerFechaDesde(TarjetaEntity tarjeta) throws ParseadorVisaException {
		DatosEntity datos = obtenerDatos(tarjeta);
		if (datos.getFechaDesde() == null) {
			throw new ParseadorVisaException();
		}
		return datos.getFechaDesde();
	}

	/**
	 * Obteniene la FechaDesde. Tag
	 * tarjetas/tarjeta/document/saldoenCuenta/limites/limites/
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the list
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public List<LimiteTarjetaEntity> obtenerLimites(TarjetaEntity tarjeta) throws ParseadorVisaException {
		SaldoEnCuentaEntity saldoEnCuenta = obtenerSaldoEnCuenta(tarjeta);
		if (saldoEnCuenta.getLimites() == null) {
			throw new ParseadorVisaException();
		}
		if (saldoEnCuenta.getLimites().getLimites() == null) {
			throw new ParseadorVisaException();
		}
		return saldoEnCuenta.getLimites().getLimites();
	}

	/**
	 * Obteniene pesos. Tag
	 * tarjetas/tarjeta/document/saldoenCuenta/limites/limites/pesos
	 *
	 * @param limiteTarjeta
	 *            the limite tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public String obtenerPesos(LimiteTarjetaEntity limiteTarjeta) throws ParseadorVisaException {
		if (limiteTarjeta == null) {
			throw new ParseadorVisaException();
		}
		if (limiteTarjeta.getPesos() == null || limiteTarjeta.getDescripcion() == null) {
			throw new ParseadorVisaException();
		}
		return limiteTarjeta.getPesos();
	}

	/**
	 * Obteniene pesos. Tag
	 * tarjetas/tarjeta/document/saldoenCuenta/limites/limites/pesos
	 *
	 * @param limiteTarjeta
	 *            the limite tarjeta
	 * @param tipoSaldo
	 *            the tipoSaldo
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public String obtenerPesos(LimiteTarjetaEntity limiteTarjeta, String tipoSaldo) throws ParseadorVisaException {
		if (limiteTarjeta == null) {
			throw new ParseadorVisaException();
		}
		if (limiteTarjeta.getPesos() == null || limiteTarjeta.getDescripcion() == null) {
			throw new ParseadorVisaException();
		}
		if (tipoSaldo.equals(limiteTarjeta.getDescripcion().trim())) {
			return limiteTarjeta.getPesos();
		}
		return null;
	}

	/**
	 * Obteniene la descripcion del limite. Tag
	 * tarjetas/tarjeta/document/saldoenCuenta/limites/limites/pesos
	 *
	 * @param limiteTarjeta
	 *            the limite tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public String obtenerDescripcion(LimiteTarjetaEntity limiteTarjeta) throws ParseadorVisaException {
		if (limiteTarjeta == null) {
			throw new ParseadorVisaException();
		}
		if (limiteTarjeta.getDescripcion() == null) {
			throw new ParseadorVisaException();
		}
		return limiteTarjeta.getDescripcion();
	}

	/**
	 * Obtener saldo tipo segun el tipo que ingresa por parametro. Tag
	 * tarjetas/tarjeta/document/saldoenCuenta/limites/limites/pesos
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
	@Override
	public BigDecimal obtenerSaldos(TarjetaEntity tarjeta, String tipoSaldo)
			throws TarjetaBOUtilsException, ParseadorVisaException {
		BigDecimal r1 = null;
		List<LimiteTarjetaEntity> limites = obtenerLimites(tarjeta);
		for (LimiteTarjetaEntity limiteTarjeta : limites) {
			if (tipoSaldo.equals(obtenerDescripcion(limiteTarjeta).trim())) {
				r1 = TarjetaBOUtils.convertirSaldo(obtenerPesos(limiteTarjeta));
				break;
			}
		}
		return r1;
	}

	/**
	 * Obteniene la FechaDesde. Tag
	 * tarjetas/tarjeta/document/saldoenCuenta/limitesUnificados/
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public String obtenerLimitesUnificados(TarjetaEntity tarjeta) throws ParseadorVisaException {
		SaldoEnCuentaEntity saldoEnCuenta = obtenerSaldoEnCuenta(tarjeta);
		if (saldoEnCuenta.getLimitesUnificados() == null) {
			throw new ParseadorVisaException();
		}
		return saldoEnCuenta.getLimitesUnificados();
	}

	/**
	 * Obtiene si la tarjeta tiene limite unificado.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public Boolean tieneLimiteUnificado(TarjetaEntity tarjeta) throws ParseadorVisaException {
		return esCategoriaTitular(tarjeta)
				&& TarjetaUtils.LIMITE_UNIFICADO.equals(obtenerLimitesUnificados(tarjeta).trim());
	}

	/**
	 * Obteniene la descripcion del limite. Tag
	 * tarjetas/tarjeta/document/saldoenCuenta/fechas/fecha/description
	 *
	 * @param fechaTarjetaEntity
	 *            the fecha tarjeta entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public String obtenerFechaDescripcion(FechaTarjetaEntity fechaTarjetaEntity) throws ParseadorVisaException {
		if (fechaTarjetaEntity == null) {
			throw new ParseadorVisaException();
		}
		if (fechaTarjetaEntity.getDescripcion() == null) {
			throw new ParseadorVisaException();
		}
		return fechaTarjetaEntity.getDescripcion();
	}

	/**
	 * Obteniene la descripcion del limite. Tag
	 * tarjetas/tarjeta/document/saldoenCuenta/fechas/fecha/cierre
	 *
	 * @param fechaTarjetaEntity
	 *            the fecha tarjeta entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public String obtenerFechaCierre(FechaTarjetaEntity fechaTarjetaEntity) throws ParseadorVisaException {
		if (fechaTarjetaEntity == null) {
			throw new ParseadorVisaException();
		}
		if (fechaTarjetaEntity.getCierre() == null) {
			throw new ParseadorVisaException();
		}
		return fechaTarjetaEntity.getCierre();
	}

	/**
	 * Obteniene la descripcion del limite. Tag
	 * tarjetas/tarjeta/document/saldoenCuenta/fechas/fecha/vencimiento
	 *
	 * @param fechaTarjetaEntity
	 *            the fecha tarjeta entity
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public String obtenerVencimiento(FechaTarjetaEntity fechaTarjetaEntity) throws ParseadorVisaException {
		if (fechaTarjetaEntity == null) {
			throw new ParseadorVisaException();
		}
		if (fechaTarjetaEntity.getVencimiento() == null) {
			throw new ParseadorVisaException();
		}
		return fechaTarjetaEntity.getVencimiento();
	}

	/**
	 * Obtener fecha por cierre.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerFechaPorCierre(TarjetaEntity tarjeta) throws ParseadorVisaException {
		List<FechaTarjetaEntity> fechas = obtenerFechas(tarjeta);
		for (FechaTarjetaEntity fechaTarjeta : fechas) {
			if (TarjetaUtils.FECHA_CIERRE.equals(obtenerFechaDescripcion(fechaTarjeta).trim())) {
				return obtenerFechaCierre(fechaTarjeta);
			}
		}
		throw new ParseadorVisaException();
	}

	/**
	 * Obtener fecha por vencimiento.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerFechaPorVencimiento(TarjetaEntity tarjeta) throws ParseadorVisaException {
		List<FechaTarjetaEntity> fechas = obtenerFechas(tarjeta);
		for (FechaTarjetaEntity fechaTarjeta : fechas) {
			if (TarjetaUtils.FECHA_VENCIMIENTO.equals(obtenerFechaDescripcion(fechaTarjeta).trim())) {
				return obtenerVencimiento(fechaTarjeta);
			}
		}
		throw new ParseadorVisaException();
	}

	/**
	 * Obtener saldo tipo. Tag
	 * tarjetas/tarjeta/document/saldoenCuenta/limites/limites
	 * descripcion={tipoSaldo}/pesos
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
	@Override
	public BigDecimal obtenerSaldoTipo(TarjetaEntity tarjeta, String tipoSaldo)
			throws ParseadorVisaException, TarjetaBOUtilsException {
		BigDecimal r1 = null;
		List<LimiteTarjetaEntity> v1 = this.obtenerLimites(tarjeta);
		for (LimiteTarjetaEntity limiteTarjeta : v1) {
			String saldo = obtenerPesos(limiteTarjeta, tipoSaldo);
			if (saldo != null) {
				r1 = TarjetaBOUtils.convertirSaldo(saldo);
				break;
			}
		}
		return r1;
	}

	/**
	 * Obtiene el limite unificado.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerLimiteUnificado(TarjetaEntity tarjeta) throws ParseadorVisaException {
		SaldoEnCuentaEntity saldoEnCuenta = obtenerSaldoEnCuenta(tarjeta);
		if (saldoEnCuenta.getLimitesUnificados() == null) {
			throw new ParseadorVisaException();
		}
		return saldoEnCuenta.getLimitesUnificados();
	}

}
