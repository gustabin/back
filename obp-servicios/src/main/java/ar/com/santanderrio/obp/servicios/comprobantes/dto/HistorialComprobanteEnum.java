package ar.com.santanderrio.obp.servicios.comprobantes.dto;

import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;

public enum HistorialComprobanteEnum {

	/* pago servicios desde CNSPESPAGC */
	PAGO_SERVICIOS_A(EstadisticasConstants.HISTORIAL_COMPROBANTES_PAGO_SERVICIOS_A),
	/* pago servicios desde SCOMP tipo 2 subtipo 11,12,13,14,36,37,38,39 (pmc con/sin deuda, vep, afip) */
	PAGO_SERVICIOS_B(EstadisticasConstants.HISTORIAL_COMPROBANTES_PAGO_SERVICIOS_B),
	/* pago servicios desde SCOMP tipo 5 subtipo 0 (pago de compras) */
	PAGO_SERVICIOS_C(EstadisticasConstants.HISTORIAL_COMPROBANTES_PAGO_SERVICIOS_C),
	TRANSFERENCIA_RIORIO(EstadisticasConstants.HISTORIAL_COMPROBANTES_TRANSFERENCIA_RIORIO),
	TRANSFERENCIA_OTROSBANCOS(EstadisticasConstants.HISTORIAL_COMPROBANTES_TRANSFERENCIA_OTROSBANCOS),
	DEBITO_AUTOMATICO_PAGO_TARJETA(EstadisticasConstants.HISTORIAL_DEBITO_AUTOMATICO_PAGO_TARJETA),
	DEBITO_AUTOMATICO_CUENTA(EstadisticasConstants.HISTORIAL_DEBITO_AUTOMATICO_CUENTA),
	DEBITO_AUTOMATICO_TARJETA(EstadisticasConstants.HISTORIAL_DEBITO_AUTOMATICO_TARJETA),
	PAGO_TARJETA(EstadisticasConstants.HISTORIAL_PAGO_TARJETA),
	COMPRA_VENTA(EstadisticasConstants.HISTORIAL_COMPRAVENTA_DOLARES),
	PAGO_SUELDOS(EstadisticasConstants.HISTORIAL_PAGO_SUELDOS);

	private HistorialComprobanteEnum(String codigoEstadistica) {
		this.codigoEstadistica = codigoEstadistica;
	}

	private String codigoEstadistica;

	/**
	 * @return the codigoEstadistica
	 */
	public String getCodigoEstadistica() {
		return codigoEstadistica;
	}

}
