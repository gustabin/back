package ar.com.santanderrio.obp.servicios.echeq.entities;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class CustodiarRescatarEcheqInEntity.
 */
public class CustodiarRescatarECheqInEntity  extends OperacionesECheqEmisorInEntity implements
		IOperacionECheqInEntity<IatxRequestData> {

	/** The Constant SERVICIO_ACTCTACREC. */
	private static final String SERVICIO_ACTCTACREC = "ACTCTACREC";

	/** The Constant VERSION_ACTCTACREC. */
	private static final String VERSION_ACTCTACREC = "100";

	/** The importe. */
	private String importe;

	/** The tipo accion. */
	private String tipoAccion;

	/** The tipo accion. */
	private String tipoCheque;

	/**
	 * Instantiates a new custodiar rescatar echeq in entity.
	 */
	public CustodiarRescatarECheqInEntity() {
		super();
	}

	@Override
	public String getNombreServicio() {
		return SERVICIO_ACTCTACREC;
	}

	@Override
	public String getVersionServicio() {
		return VERSION_ACTCTACREC;
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
	 * Gets the tipo accion.
	 *
	 * @return the tipo accion
	 */
	public String getTipoAccion() {
		return tipoAccion;
	}

	/**
	 * Sets the tipo accion.
	 *
	 * @param tipoAccion the new tipo accion
	 */
	public void setTipoAccion(String tipoAccion) {
		this.tipoAccion = tipoAccion;
	}

	/**
	 * Gets the tipo cheque.
	 *
	 * @return the tipo cheque
	 */
	public String getTipoCheque() {
		return tipoCheque;
	}

	/**
	 * Sets the tipo cheque.
	 *
	 * @param tipoCheque the new tipo cheque
	 */
	public void setTipoCheque(String tipoCheque) {
		this.tipoCheque = tipoCheque;
	}

	@Override
	public IatxRequestData generateRequestData(Cliente cliente, IOperacionECheqInEntity<?> operacionesECheqInEntity) {
		CustodiarRescatarECheqInEntity custodiarRescatarECheqInEntity = (CustodiarRescatarECheqInEntity) operacionesECheqInEntity;
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
    	iatxRequestData.addBodyValue(custodiarRescatarECheqInEntity.getIdCheque());
    	iatxRequestData.addBodyValue(custodiarRescatarECheqInEntity.getTipoDocumento());
    	iatxRequestData.addBodyValue(custodiarRescatarECheqInEntity.getNroDocumento());
    	iatxRequestData.addBodyValue(custodiarRescatarECheqInEntity.getTipoCuenta());
    	iatxRequestData.addBodyValue(custodiarRescatarECheqInEntity.getSucursalCuenta());
    	iatxRequestData.addBodyValue(custodiarRescatarECheqInEntity.getNumeroCuenta());
    	iatxRequestData.addBodyValue(custodiarRescatarECheqInEntity.getNroCheque());
    	iatxRequestData.addBodyValue(custodiarRescatarECheqInEntity.getImporte());
    	iatxRequestData.addBodyValue(custodiarRescatarECheqInEntity.getFechaEmision());
    	iatxRequestData.addBodyValue(custodiarRescatarECheqInEntity.getFechaPago());
    	iatxRequestData.addBodyValue(custodiarRescatarECheqInEntity.getTipoCheque());
    	iatxRequestData.addBodyValue(custodiarRescatarECheqInEntity.getTipoAccion());
    	iatxRequestData.addBodyValue(custodiarRescatarECheqInEntity.getCuitEmisor());
    	iatxRequestData.addBodyValue(custodiarRescatarECheqInEntity.getRazonSocialEmisor());
    	iatxRequestData.addBodyValue(custodiarRescatarECheqInEntity.getJSessionId());
		return iatxRequestData;
	}
}
