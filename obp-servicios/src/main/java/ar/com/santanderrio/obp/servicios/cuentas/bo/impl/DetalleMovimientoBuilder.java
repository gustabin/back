/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.bo.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Movimiento;

/**
 * The Class UltimosMovimientosBuilder.
 */
public class DetalleMovimientoBuilder {

	/** The Constant INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO. */
	private static final String INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO = "01";

	/** The Constant INDICADOR_MOVIMIENTO_CUENTA_CORRIENTE. */
	private static final String INDICADOR_MOVIMIENTO_CUENTA_CORRIENTE = "00";

	/** The movimiento. */
	private Movimiento movimiento;

	/**
	 * Gets the movimiento.
	 *
	 * @return the movimiento
	 */
	public Movimiento getMovimiento() {
		return movimiento;
	}

	/**
	 * Sets the movimiento.
	 *
	 * @param movimiento
	 *            the new movimiento
	 */
	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}

	/**
	 * Builds the.
	 *
	 * @param isCuentaUnica
	 *            the is cuenta unica
	 * @param movimiento
	 *            the movimiento
	 * @return the detalle movimiento entity
	 * @throws ParseException
	 *             the parse exception
	 */
	public static DetalleMovimientoEntity build(Boolean isCuentaUnica, Movimiento movimiento) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String fechaActual = ISBANStringUtils.formatearFechaIATX(new Date());
		DetalleUltimosMovimientos detallesUltimosMovimientos = new DetalleUltimosMovimientos();
		detallesUltimosMovimientos.setDetalleMovimiento(new ArrayList<DetalleMovimientoEntity>());

		DetalleMovimientoEntity detalleMovimiento = new DetalleMovimientoEntity();
		detalleMovimiento.setId(UUID.randomUUID().toString());
		detalleMovimiento.setDelDia(movimiento.getDelDia());
		detalleMovimiento.setDescripcion(movimiento.getDescripcionMovimiento());
		detalleMovimiento.setDescripcionAdicional(movimiento.getDescripcionAdicionalMovimiento());
		detalleMovimiento.setFecha(sf.parse(movimiento.getFechaValor()));
		detalleMovimiento
				.setImporteMovimiento(ISBANStringUtils.parseStringToBigDecimal(movimiento.getImporteMovimiento()));
		detalleMovimiento.setSaldoCuenta(ISBANStringUtils.parseStringToBigDecimal(movimiento.getSaldoCuenta()));
		detalleMovimiento
				.setIsMovimientoEnDolares(movimiento.getMonedaMovimiento().trim().equals(DivisaEnum.DOLAR.getCodigo()));
		if (isCuentaUnica) {
			detalleMovimiento.setIsCajaDeAhoroEnPesos(
					movimiento.getIndicadorMovimiento().trim().equals(INDICADOR_MOVIMIENTO_CAJA_DE_AHORRO) ? true
							: false);
			detalleMovimiento.setIsCuentaCorrienteEnPesos(
					movimiento.getIndicadorMovimiento().trim().equals(INDICADOR_MOVIMIENTO_CUENTA_CORRIENTE) ? true
							: false);
		}
		detalleMovimiento.setRechazado(false);
		detalleMovimiento.setCheque(false);
		completarDetalleMovimientoData(detalleMovimiento, movimiento);
		// FIN enviar a builder
		detallesUltimosMovimientos.getDetalleMovimiento().add(detalleMovimiento);

		return detalleMovimiento;
	}

	/**
	 * Completar detalle movimiento data.
	 *
	 * @param detalleMovimiento
	 *            the detalle movimiento
	 * @param movimiento
	 *            the movimiento
	 */
	private static void completarDetalleMovimientoData(DetalleMovimientoEntity detalleMovimiento,
			Movimiento movimiento) {
		detalleMovimiento.setNumeroReferencia(movimiento.getNumeroComprobante());
		detalleMovimiento.setHora(formatearHora(movimiento.getHoraMovimiento()));
		detalleMovimiento.setNumeroSucursal(movimiento.getSucursalOrigen());
		detalleMovimiento.setDescripcionAdicional(StringUtils.isBlank(movimiento.getDescripcionAdicionalMovimiento())
				? "" : movimiento.getDescripcionAdicionalMovimiento());
		detalleMovimiento
				.setObservacion(StringUtils.isBlank(movimiento.getObservacion()) ? "" : movimiento.getObservacion());

	}

	/**
	 * Formatear hora.
	 *
	 * @param horaSinFormato
	 *            the hora sin formato
	 * @return the string
	 */
	private static String formatearHora(String horaSinFormato) {
		String hora = horaSinFormato.substring(0, horaSinFormato.length() - 2);
		String minuto = horaSinFormato.substring(horaSinFormato.length() - 2);
		return hora + ":" + minuto;
	}

}
