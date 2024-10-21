/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

/**
 * The Enum TipoDetalleComprobanteEnum.
 *
 * @author sabrina.cis
 */
public enum TipoDetalleComprobanteEnum {

	/** The iatx pmc con deuda. */
	IATX_PMC_PAGO_PUNTUAL(1, "IATX_PMC_PAGO_PUNTUAL", "PagoPuntual", "PMCConDeuda"),

	/** The iatx pmc con deuda. */
	IATX_PMC_CON_DEUDA(2, "IATX_PMC_CON_DEUDA", "PagoPuntual", "PMCConDeuda"),

	/** The iatx pmc sin deuda. */
	IATX_PMC_SIN_DEUDA(3, "IATX_PMC_SIN_DEUDA", "PagoPuntual", "PagoPuntual"),

	/** The iatx pmc afip. */
	IATX_PMC_AFIP(4, "IATX_PMC_AFIP", "PagoAfip", "PagoAfip"),

	/** The iatx pmc autonomo. */
	IATX_PMC_AUTONOMO(5, "IATX_PMC_AUTONOMO", "PagoAutonomo", "PagoAutonomo"),

	/** The iatx pmc vep. */
	IATX_PMC_VEP(6, "IATX_PMC_VEP", "PagoVep", "PagoMisCuentas"),

	/** The iatx pago tarjeta. */
	IATX_PAGO_TARJETA(7, "pagoTarjeta", "PagoTarjeta", "tarjetaVISAAMEX"),

	/** The iatx informe debitos automaticos en cuenta. */
	IATX_INFORME_DEBITOS_AUTOMATICOS_EN_CUENTA(8, "IATX_INFORME_DEBITOS_AUTOMATICOS_EN_CUENTA", "DebitoAutomaticoEnCuenta", "DebitoAutoTarjeta"),

	/** The visa debito en tarjeta. */
	VISA_DEBITO_EN_TARJETA(9, "VISA_DEBITO_EN_TARJETA", "DebitoAutomaticoEnTarjeta", "DebitoAutomaticoEnTarjeta"),

	/** The scomp pmc con deuda. */
	SCOMP_PMC_CON_DEUDA(10, "SCOMP_PMC_CON_DEUDA", "PagoPuntual", "PagoMisCuentas"),

	/** The scomp pmc sin deuda. */
	SCOMP_PMC_SIN_DEUDA(11, "SCOMP_PMC_SIN_DEUDA", "PagoPuntual", "PagoMisCuentas"),

	/** The scomp pmc afip. */
	SCOMP_PMC_AFIP(12, "SCOMP_PMC_AFIP", "PagoAfip", "PagoMisCuentas"),

	/** The scomp pmc vep. */
	SCOMP_PMC_VEP(13, "SCOMP_PMC_VEP", "PagoVep", "PagoMisCuentas"),

	/** The scomp transferencia inmediata rio rio. */
	SCOMP_TRANSFERENCIA_INMEDIATA_RIO_RIO(14, "SCOMP_TRANSFERENCIA_INMEDIATA_RIO_RIO", "Transferencia", "TransferenciaInmediataRioRio"),

	/** The scomp transferencia inmediata otros bancos. */
	SCOMP_TRANSFERENCIA_INMEDIATA_OTROS_BANCOS(15, "SCOMP_TRANSFERENCIA_INMEDIATA_OTROS_BANCOS", "Transferencia", "scompOB"),

	/** The scomp transferencia inmediata recarga tarjeta. */
	SCOMP_TRANSFERENCIA_INMEDIATA_RECARGA_TARJETA(16, "SCOMP_TRANSFERENCIA_INMEDIATA_RECARGA_TARJETA", "RecargaTarjeta", "scompTR"),

	/** The transferencia programada rio rio. */
	TRANSFERENCIA_PROGRAMADA_RIO_RIO(17, "TRANSFERENCIA_PROGRAMADA_RIO_RIO", "Transferencia", "7x24Rio"),

	/** The transferencia programada otros bancos. */
	TRANSFERENCIA_PROGRAMADA_OTROS_BANCOS(18, "TRANSFERENCIA_PROGRAMADA_OTROS_BANCOS", "Transferencia", "7x24OB"),

	/** The transferencia programada paghabon. */
	TRANSFERENCIA_PROGRAMADA_PAGHABON(19, "TRANSFERENCIA_PROGRAMADA_PAGHABON", "PagoDeHaberes", "7x24PAGHABON"),

	/** The compraventa dolares. */
	COMPRAVENTA_DOLARES(20, "COMPRAVENTA_DOLARES", "CompraVentaDolares", "ScompCompraVenta"),
	
	/** The iatx pago compras. */
	IATX_PAGO_COMPRAS(21, "IATX_PAGO_COMPRAS", "PagoCompras", "PagoCompras");

	/**
	 * Instantiates a new tipo detalle comprobante enum.
	 *
	 * @param id
	 *            the id
	 * @param etiqueta
	 *            the etiqueta
	 * @param tag
	 *            the tag
	 * @param tipoComprobante
	 *            the tipo comprobante
	 */
	TipoDetalleComprobanteEnum(Integer id, String etiqueta, String tag, String tipoComprobante) {
		this.id = id;
		this.etiqueta = etiqueta;
		this.tag = tag;
		this.tipoComprobante = tipoComprobante;
	}

	/** The id. */
	private Integer id;

	/** The etiqueta. */
	private String etiqueta;

	/** The tag. */
	private String tag;

	/** The tipo comprobante. */
	private String tipoComprobante;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Gets the etiqueta.
	 *
	 * @return the etiqueta
	 */
	public String getEtiqueta() {
		return etiqueta;
	}

	/**
	 * Gets the tag.
	 *
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Gets the tipo comprobante.
	 *
	 * @return the tipoComprobante
	 */
	public String getTipoComprobante() {
		return tipoComprobante;
	}

}
