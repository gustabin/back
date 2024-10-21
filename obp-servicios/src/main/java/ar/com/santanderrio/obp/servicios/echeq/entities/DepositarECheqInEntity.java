package ar.com.santanderrio.obp.servicios.echeq.entities;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class DepositarEcheqInEntity.
 */
public class DepositarECheqInEntity extends OperacionesECheqEmisorInEntity
		implements IOperacionECheqInEntity<IatxRequestData> {

	/** The Constant SERVICIO_ACTCTADEC. */
	private static final String SERVICIO_ACTCTADEC = "ACTCTADEC_";

	/** The Constant VERSION_ACTCTADEC. */
	private static final String VERSION_ACTCTADEC = "100";

	/** The importe. */
	private String importe;

	/** The tipo cheque. */
	private String tipoCheque;

	/**
	 * Instantiates a new depositar echeq in entity.
	 */
	public DepositarECheqInEntity() {
		super();
	}

	@Override
	public String getNombreServicio() {
		return SERVICIO_ACTCTADEC;
	}

	@Override
	public String getVersionServicio() {
		return VERSION_ACTCTADEC;
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
		DepositarECheqInEntity depositarECheqInEntity = (DepositarECheqInEntity) operacionesECheqInEntity;
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
    	iatxRequestData.addBodyValue(depositarECheqInEntity.getTipoCuenta());
    	iatxRequestData.addBodyValue(depositarECheqInEntity.getSucursalCuenta());
    	iatxRequestData.addBodyValue(depositarECheqInEntity.getNumeroCuenta());
    	iatxRequestData.addBodyValue(depositarECheqInEntity.getImporte());
    	iatxRequestData.addBodyValue(depositarECheqInEntity.getIdCheque());
    	iatxRequestData.addBodyValue(depositarECheqInEntity.getNroCheque());
    	iatxRequestData.addBodyValue(depositarECheqInEntity.getFechaEmision());
    	iatxRequestData.addBodyValue(depositarECheqInEntity.getFechaPago());
    	iatxRequestData.addBodyValue(depositarECheqInEntity.getTipoDocumento());
    	iatxRequestData.addBodyValue(depositarECheqInEntity.getNroDocumento());
    	iatxRequestData.addBodyValue(depositarECheqInEntity.getCuitEmisor());
    	iatxRequestData.addBodyValue(depositarECheqInEntity.getRazonSocialEmisor());
    	iatxRequestData.addBodyValue(depositarECheqInEntity.getTipoCheque());
    	iatxRequestData.addBodyValue(depositarECheqInEntity.getJSessionId());
		return iatxRequestData;
	}

}
