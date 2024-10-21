package ar.com.santanderrio.obp.servicios.echeq.entities;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class AceptarRepudiarPedidoDevolucionEcheqInEntity.
 */
public class AceptarRepudiarPedidoDevolucionECheqInEntity extends OperacionesECheqEmisorInEntity
		implements IOperacionECheqInEntity<IatxRequestData> {
    
    /** The Constant SERVICIO_ACTCTAARDC. */
    private static final String SERVICIO_ACTCTAARDC = "ACTCTAARDC";

    /** The Constant VERSION_ACTCTAARDC. */
    private static final String VERSION_ACTCTAARDC = "100";

	/** The importe. */
	private String importe;

	/** The accion. */
	private String accion;

	/**
	 * Instantiates a new aceptar repudiar pedido devolucion echeq in entity.
	 */
	public AceptarRepudiarPedidoDevolucionECheqInEntity() {
		super();
	}

	/**
	 * Instantiates a new aceptar repudiar pedido devolucion echeq in entity.
	 *
	 * @param idCheque          the id cheque
	 * @param tipoDocumento     the tipo documento
	 * @param nroDocumento      the nro documento
	 * @param tipoCuenta        the tipo cuenta
	 * @param sucursalCuenta    the sucursal cuenta
	 * @param numeroCuenta      the numero cuenta
	 * @param nroCheque         the nro cheque
	 * @param fechaEmision      the fecha emision
	 * @param fechaPago         the fecha pago
	 * @param jSessionId        the j session id
	 * @param cuitEmisor        the cuit emisor
	 * @param razonSocialEmisor the razon social emisor
	 * @param importe           the importe
	 * @param accion            the accion
	 */
	public AceptarRepudiarPedidoDevolucionECheqInEntity(String idCheque, String tipoDocumento, String nroDocumento,
			String tipoCuenta, String sucursalCuenta, String numeroCuenta, String nroCheque, String fechaEmision,
			String fechaPago, String jSessionId, String cuitEmisor, String razonSocialEmisor, String importe,
			String accion) {
		super(idCheque, tipoDocumento, nroDocumento, tipoCuenta, sucursalCuenta, numeroCuenta, nroCheque, fechaEmision,
				fechaPago, jSessionId, cuitEmisor, razonSocialEmisor);
		this.importe = importe;
		this.accion = accion;
	}

	
    @Override
    public String getNombreServicio() {
        return SERVICIO_ACTCTAARDC;
    }


    @Override
    public String getVersionServicio() {
        return VERSION_ACTCTAARDC;
    }
	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe the new importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the accion.
	 *
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Sets the accion.
	 *
	 * @param accion the new accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

    @Override
    public IatxRequestData generateRequestData(Cliente cliente, IOperacionECheqInEntity<?> InEntity) {
        AceptarRepudiarPedidoDevolucionECheqInEntity aceptarRepudiarPedidoDevolucionECheqInEntity =(AceptarRepudiarPedidoDevolucionECheqInEntity) InEntity;
        IatxRequestData iatxRequestData = new IatxRequestData(cliente);
        iatxRequestData.addBodyValue(aceptarRepudiarPedidoDevolucionECheqInEntity.getIdCheque());
        iatxRequestData.addBodyValue(aceptarRepudiarPedidoDevolucionECheqInEntity.getTipoDocumento());
        iatxRequestData.addBodyValue(aceptarRepudiarPedidoDevolucionECheqInEntity.getNroDocumento());
        iatxRequestData.addBodyValue(aceptarRepudiarPedidoDevolucionECheqInEntity.getTipoCuenta());
        iatxRequestData.addBodyValue(aceptarRepudiarPedidoDevolucionECheqInEntity.getSucursalCuenta());
        iatxRequestData.addBodyValue(aceptarRepudiarPedidoDevolucionECheqInEntity.getNumeroCuenta());
        iatxRequestData.addBodyValue(aceptarRepudiarPedidoDevolucionECheqInEntity.getNroCheque());
        iatxRequestData.addBodyValue(aceptarRepudiarPedidoDevolucionECheqInEntity.getImporte());
        iatxRequestData.addBodyValue(aceptarRepudiarPedidoDevolucionECheqInEntity.getFechaEmision());
        iatxRequestData.addBodyValue(aceptarRepudiarPedidoDevolucionECheqInEntity.getFechaPago());
        iatxRequestData.addBodyValue(aceptarRepudiarPedidoDevolucionECheqInEntity.getAccion());
        iatxRequestData.addBodyValue(aceptarRepudiarPedidoDevolucionECheqInEntity.getCuitEmisor());
        iatxRequestData.addBodyValue(aceptarRepudiarPedidoDevolucionECheqInEntity.getRazonSocialEmisor());
        iatxRequestData.addBodyValue(aceptarRepudiarPedidoDevolucionECheqInEntity.getJSessionId());
        return iatxRequestData;
    }
}
