package ar.com.santanderrio.obp.servicios.echeq.entities;

/**
 * The Class ECheqRecibido.
 */
public class ECheqRecibidoInEntity extends ECheqEntity {

	/**
	 * Instantiates a new e cheq recibido in entity.
	 */
	public ECheqRecibidoInEntity() {
		super();
	}

	/**
	 * Instantiates a new e cheq recibido in entity.
	 *
	 * @param cuit the cuit
	 * @param fechaEmisionDesde the fecha emision desde
	 * @param fechaEmisionHasta the fecha emision hasta
	 * @param fechaPagoDesde the fecha pago desde
	 * @param fechaPagoHasta the fecha pago hasta
	 * @param estado the estado
	 * @param orderby the orderby
	 * @param nroPagina the nro pagina
	 * @param emisorCuit the emisor cuit
	 * @param chequeNumero the cheque numero
	 */
	public ECheqRecibidoInEntity(String cuit, String fechaEmisionDesde, String fechaEmisionHasta, String fechaPagoDesde,
			String fechaPagoHasta, String estado, String orderby, String nroPagina, String emisorCuit,
			String chequeNumero) {
		super(cuit, fechaEmisionDesde, fechaEmisionHasta, fechaPagoDesde, fechaPagoHasta, estado, orderby, nroPagina,
				emisorCuit, chequeNumero);
	}

}
