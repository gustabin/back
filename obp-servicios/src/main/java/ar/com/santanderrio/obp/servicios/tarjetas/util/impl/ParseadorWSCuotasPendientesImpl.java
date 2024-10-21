/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.util.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtilsException;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.CuotasPendientesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.DetalleCuotaPendienteEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.RetornoTarjetasEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaCuotasPendientesEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity;
import ar.com.santanderrio.obp.servicios.tarjetas.util.ParseadorWSCuotasPendientes;

/**
 * Obtencion de datos desde la respuesta del DAO para Cuotas Pendientes.
 * 
 * @author federico.n.flores
 *
 */
@Component
public class ParseadorWSCuotasPendientesImpl extends ParseadorWSVisaImpl implements ParseadorWSCuotasPendientes {

	/** The Constant ERROR_SIN_CUOTAS_PENDIENTES. */
	private static final String ERROR_SIN_CUOTAS_PENDIENTES = "112107";

	/**
	 * Si no se encuentra la tarjeta seleccionada en el xml usando el tag
	 * tarjetas/tarjeta/document/datos/tarjetaActiva = tarjeta a mostrar. Y el
	 * xml retorno todos codigos de errores=112107, entonces la tarjeta no tiene
	 * consumos.
	 *
	 * @param retorno
	 *            the retorno
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public Boolean tarjetaNotieneConsumos(RetornoTarjetasEntity retorno) throws ParseadorVisaException {
		List<TarjetaEntity> tarjetas = obtenerTarjetas(retorno);
		boolean errorEnVisa = false;
		for (TarjetaEntity tarjeta : tarjetas) {
			if (!tieneErrorSinCuotasPendientes(tarjeta)) {
				errorEnVisa = true;
				break;
			}
		}
		if (errorEnVisa) {
			return false;
		}
		return true;
	}

	/**
	 * Devuelve si la tarjeta tiene error 112107, sin cuotas pendientes.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return true, if successful
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	public boolean tieneErrorSinCuotasPendientes(TarjetaEntity tarjeta) throws ParseadorVisaException {
		return tieneCodigoError(tarjeta, ERROR_SIN_CUOTAS_PENDIENTES);
	}

	/**
	 * Retorna la lista de los ultimos cuatro numeros del codigo de la tarjeta,
	 * con cuotas pendientes. El codigo viene encriptado con X, ej. XXXX XXXX
	 * XXXX3775, se obtiene del atributo codigoTarjeta del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/.
	 * 
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the boolean
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public List<String> obtenerCodigosDeTarjetas(TarjetaEntity tarjetaEntity) throws ParseadorVisaException {
		List<String> codigos = new ArrayList<String>();
		List<TarjetaCuotasPendientesEntity> listaCuotasPendientes = obtenerListaCuotasPendientes(tarjetaEntity);
		for (TarjetaCuotasPendientesEntity tarjeta : listaCuotasPendientes) {
			codigos.add(obtenerUltimosCuatroNumeros(obtenerCodigoTarjeta(tarjeta)));
		}
		return codigos;
	}

	/**
	 * Obtiene el total de la tarjeta seleccionada del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/totales/pesos.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the big decimal
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	@Override
	public BigDecimal obtenerTotalCuotasPendientes(TarjetaEntity tarjeta)
			throws ParseadorVisaException, TarjetaBOUtilsException {
		CuotasPendientesEntity cuotasPendientes = obtenerCuotasPendientes(tarjeta);
		if (cuotasPendientes == null || cuotasPendientes.getTotales() == null
				|| cuotasPendientes.getTotales().getPesos() == null) {
			throw new ParseadorVisaException();
		}
		return TarjetaBOUtils.convertirSaldo(cuotasPendientes.getTotales().getPesos());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.tarjetas.util.
	 * ParseadorWSCuotasPendientes#obtenerSubtotalCuotasPendientes(ar.com.
	 * santanderrio.obp.servicios.tarjetas.dao.entities.TarjetaEntity,
	 * java.lang.String)
	 */
	@Override
	public BigDecimal obtenerSubtotalCuotasPendientes(TarjetaEntity tarjeta, String codigo)
			throws ParseadorVisaException, TarjetaBOUtilsException {
		CuotasPendientesEntity cuotasPendientes = obtenerCuotasPendientes(tarjeta);
		if (cuotasPendientes == null || cuotasPendientes.getTotales() == null
				|| cuotasPendientes.getTotales().getPesos() == null) {
			throw new ParseadorVisaException();
		}
		BigDecimal total = null;
		for (TarjetaCuotasPendientesEntity tarjetaCuotasPendientes : cuotasPendientes.getTarjetaList()) {
			if (StringUtils.equals(StringUtils.right(tarjetaCuotasPendientes.getCodigoTarjeta(), 4), codigo)) {
				total = TarjetaBOUtils.convertirSaldo(tarjetaCuotasPendientes.getPesos());
				break;
			}
		}
		if (total == null) {
			throw new ParseadorVisaException();
		}
		return total;
	}

	/**
	 * Obtiene la lista de cuotas pendientes del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the cuotas pendientes entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public List<TarjetaCuotasPendientesEntity> obtenerListaCuotasPendientes(TarjetaEntity tarjeta)
			throws ParseadorVisaException {
		CuotasPendientesEntity cuotasPendientes = obtenerCuotasPendientes(tarjeta);
		if (cuotasPendientes == null || cuotasPendientes.getTarjetaList() == null) {
			throw new ParseadorVisaException();
		}
		return cuotasPendientes.getTarjetaList();
	}

	/**
	 * Obtiene las cuotas pendientes del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the cuotas pendientes entity
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public CuotasPendientesEntity obtenerCuotasPendientes(TarjetaEntity tarjeta) throws ParseadorVisaException {
		if (tarjeta.getTarjetaDocument() == null || tarjeta.getTarjetaDocument().getCuotasPendientes() == null) {
			throw new ParseadorVisaException();
		}
		return tarjeta.getTarjetaDocument().getCuotasPendientes();
	}

	/**
	 * Obtiene el total de la tarjeta seleccionada del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/codigoTarjeta.
	 *
	 * @param tarjeta
	 *            the tarjeta
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerCodigoTarjeta(TarjetaCuotasPendientesEntity tarjeta) throws ParseadorVisaException {
		if (tarjeta == null || tarjeta.getCodigoTarjeta() == null) {
			throw new ParseadorVisaException();
		}
		return tarjeta.getCodigoTarjeta();
	}

	/**
	 * Obtiene el comprobante del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/cuota/comprobante/.
	 *
	 * @param detalleCuota
	 *            the detalle cuota
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerNumeroComprobante(DetalleCuotaPendienteEntity detalleCuota) throws ParseadorVisaException {
		if (detalleCuota == null || detalleCuota.getComprobante() == null) {
			throw new ParseadorVisaException();
		}
		return detalleCuota.getComprobante();
	}

	/**
	 * Obtiene la cantidad de cuotas del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/cuota/cuotas/.
	 *
	 * @param detalleCuota
	 *            the detalle cuota
	 * @return the integer
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	@Override
	public Integer obtenerCuotas(DetalleCuotaPendienteEntity detalleCuota)
			throws ParseadorVisaException, TarjetaBOUtilsException {
		if (detalleCuota == null || detalleCuota.getCuotas() == null) {
			throw new ParseadorVisaException();
		}
		return TarjetaBOUtils.parsearEntero(detalleCuota.getCuotas());
	}

	/**
	 * Obtiene la cuotas pendientes del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/cuota/
	 * cuotasPendientes/.
	 *
	 * @param detalleCuota
	 *            the detalle cuota
	 * @return the integer
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	@Override
	public Integer obtenerCuotasPendientes(DetalleCuotaPendienteEntity detalleCuota)
			throws ParseadorVisaException, TarjetaBOUtilsException {
		if (detalleCuota == null || detalleCuota.getCuotas() == null) {
			throw new ParseadorVisaException();
		}
		return TarjetaBOUtils.parsearEntero(detalleCuota.getCuotasPendientes());
	}

	/**
	 * Obtiene el establecimiento del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/cuota/establecimiento
	 * /.
	 *
	 * @param detalleCuota
	 *            the detalle cuota
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerEstablecimiento(DetalleCuotaPendienteEntity detalleCuota) throws ParseadorVisaException {
		if (detalleCuota == null || detalleCuota.getEstablecimiento() == null) {
			throw new ParseadorVisaException();
		}
		return detalleCuota.getEstablecimiento();
	}

	/**
	 * Obtiene la fecha del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/cuota/fecha/.
	 *
	 * @param detalleCuota
	 *            the detalle cuota
	 * @return the date
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	@Override
	public Date obtenerFecha(DetalleCuotaPendienteEntity detalleCuota)
			throws ParseadorVisaException, TarjetaBOUtilsException {
		if (detalleCuota == null || detalleCuota.getFecha() == null) {
			throw new ParseadorVisaException();
		}
		return TarjetaBOUtils.parsearFechaConAnio(detalleCuota.getFecha());
	}

	/**
	 * Obtiene el importe del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/cuota/importe/.
	 *
	 * @param detalleCuota
	 *            the detalle cuota
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 * @throws TarjetaBOUtilsException
	 *             the tarjeta BO utils exception
	 */
	@Override
	public BigDecimal obtenerImporte(DetalleCuotaPendienteEntity detalleCuota)
			throws ParseadorVisaException, TarjetaBOUtilsException {
		if (detalleCuota == null || detalleCuota.getImporte() == null) {
			throw new ParseadorVisaException();
		}
		return TarjetaBOUtils.convertirSaldo(detalleCuota.getImporte());
	}

	/**
	 * Obtiene la moneda del tag
	 * /tarjetas/tarjeta/document/CuotasPendientes/tarjeta/cuota/moneda/.
	 *
	 * @param detalleCuota
	 *            the detalle cuota
	 * @return the string
	 * @throws ParseadorVisaException
	 *             the parseador visa exception
	 */
	@Override
	public String obtenerMoneda(DetalleCuotaPendienteEntity detalleCuota) throws ParseadorVisaException {
		if (detalleCuota == null || detalleCuota.getImporte() == null) {
			throw new ParseadorVisaException();
		}
		return detalleCuota.getMoneda();
	}

}
