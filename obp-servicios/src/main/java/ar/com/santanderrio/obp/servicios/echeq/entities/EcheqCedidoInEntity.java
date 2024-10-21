package ar.com.santanderrio.obp.servicios.echeq.entities;

/**
 * EcheqCedidosInEntity class
 * @author A309331
 *
 */
public class EcheqCedidoInEntity extends ECheqEntity {

	/**
	 * Default construct
	 */
	public EcheqCedidoInEntity() {}
    
	/**
	 * Inicializa valores para EcheqCedidosInEntity
	 * @param cuit
	 * @param fechaEmisionDesde
	 * @param fechaEmisionHasta
	 * @param fechaPagoHasta
	 * @param estado
	 * @param chequeNumero
	 * @param emisorCuit
	 * @param numeroPagina
	 * @param cantidadRegistrosPagina
	 * @param orderby
	 * @param trkCnl
	 * @param trkScnl
	 * @param trkJsessionid
	 */
	public EcheqCedidoInEntity(String cuit, String fechaEmisionDesde, String fechaEmisionHasta, String fechaPagoHasta,
			String estado, String chequeNumero, String emisorCuit, String numeroPagina, String cantidadRegistrosPagina,
			String orderby, String trkCnl, String trkScnl) {
		super(cuit, fechaEmisionDesde, fechaEmisionHasta, fechaPagoHasta, fechaPagoHasta, estado, orderby, numeroPagina, emisorCuit, chequeNumero);
	}
    
}
