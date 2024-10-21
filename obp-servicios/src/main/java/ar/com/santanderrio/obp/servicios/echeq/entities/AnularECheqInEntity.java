package ar.com.santanderrio.obp.servicios.echeq.entities;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class AnularEcheqEntity.
 */
public class AnularECheqInEntity extends OperacionesECheqBeneficiarioInEntity
		implements IOperacionECheqInEntity<IatxRequestData> {

	/** The Constant SERVICIO_ANUCTAECHQ. */
	private static final String SERVICIO_ANUCTAECHQ = "ANUCTAECHQ";

	/** The Constant VERSION_ANUCTAECHQ. */
	private static final String VERSION_ANUCTAECHQ = "100";

	/** The importe. */
	private String importe;

	/** The motivo anulacion. */
	private String motivoAnulacion;

	/**
	 * Instantiates a new anular echeq entity.
	 */
	public AnularECheqInEntity() {
		super();
	}

	/**
	 * Instantiates a new anular echeq entity.
	 *
	 * @param idCheque                  the id cheque
	 * @param tipoDocumento             the tipo documento
	 * @param nroDocumento              the nro documento
	 * @param tipoCuenta                the tipo cuenta
	 * @param sucursalCuenta            the sucursal cuenta
	 * @param numeroCuenta              the numero cuenta
	 * @param nroCheque                 the nro cheque
	 * @param fechaEmision              the fecha emision
	 * @param fechaPago                 the fecha pago
	 * @param jSessionId                the j session id
	 * @param tipoDocumentoBeneficiario the tipo documento beneficiario
	 * @param nroDocumentoBeneficiario  the nro documento beneficiario
	 * @param razonSocialBeneficiario   the razon social beneficiario
	 * @param importe                   the importe
	 * @param motivoAnulacion           the motivo anulacion
	 */
	public AnularECheqInEntity(String idCheque, String tipoDocumento, String nroDocumento, String tipoCuenta,
			String sucursalCuenta, String numeroCuenta, String nroCheque, String fechaEmision, String fechaPago,
			String jSessionId, String tipoDocumentoBeneficiario, String nroDocumentoBeneficiario,
			String razonSocialBeneficiario, String importe, String motivoAnulacion) {
		super(idCheque, tipoDocumento, nroDocumento, tipoCuenta, sucursalCuenta, numeroCuenta, nroCheque, fechaEmision,
				fechaPago, jSessionId, tipoDocumentoBeneficiario, nroDocumentoBeneficiario, razonSocialBeneficiario);
		this.importe = importe;
		this.motivoAnulacion = motivoAnulacion;
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
	 * Gets the motivo anulacion.
	 *
	 * @return the motivo anulacion
	 */
	public String getMotivoAnulacion() {
		return motivoAnulacion;
	}

	/**
	 * Sets the motivo anulacion.
	 *
	 * @param motivoAnulacion the new motivo anulacion
	 */
	public void setMotivoAnulacion(String motivoAnulacion) {
		this.motivoAnulacion = motivoAnulacion;
	}

	@Override
	public String getNombreServicio() {
		return SERVICIO_ANUCTAECHQ;
	}

	@Override
	public String getVersionServicio() {
		return VERSION_ANUCTAECHQ;
	}

	@Override
	public IatxRequestData generateRequestData(Cliente cliente, IOperacionECheqInEntity<?> operacionesECheqInEntity) {
		AnularECheqInEntity anularECheqinEntity = (AnularECheqInEntity) operacionesECheqInEntity;
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
    	iatxRequestData.addBodyValue(anularECheqinEntity.getIdCheque());
    	iatxRequestData.addBodyValue(anularECheqinEntity.getTipoDocumento());
    	iatxRequestData.addBodyValue(anularECheqinEntity.getNroDocumento());
    	iatxRequestData.addBodyValue(anularECheqinEntity.getMotivoAnulacion());
     	iatxRequestData.addBodyValue(anularECheqinEntity.getTipoCuenta());
    	iatxRequestData.addBodyValue(anularECheqinEntity.getSucursalCuenta());
    	iatxRequestData.addBodyValue(anularECheqinEntity.getNumeroCuenta());
    	iatxRequestData.addBodyValue(anularECheqinEntity.getNroCheque());
    	iatxRequestData.addBodyValue(anularECheqinEntity.getImporte());
    	iatxRequestData.addBodyValue(anularECheqinEntity.getFechaEmision());
    	iatxRequestData.addBodyValue(anularECheqinEntity.getFechaPago());
    	iatxRequestData.addBodyValue(anularECheqinEntity.getTipoDocumentoBeneficiario());
    	iatxRequestData.addBodyValue(anularECheqinEntity.getNroDocumentoBeneficiario());
    	iatxRequestData.addBodyValue(anularECheqinEntity.getRazonSocialBeneficiario());
    	iatxRequestData.addBodyValue(anularECheqinEntity.getJSessionId());
		return iatxRequestData;
	}
}
