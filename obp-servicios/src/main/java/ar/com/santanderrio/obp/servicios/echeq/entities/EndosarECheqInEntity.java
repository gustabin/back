package ar.com.santanderrio.obp.servicios.echeq.entities;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class EndosarEcheqInEntity.
 */
public class EndosarECheqInEntity extends OperacionesECheqBeneficiarioInEntity
		implements IOperacionECheqInEntity<IatxRequestData> {

	/** The Constant SERVICIO_ACTCTAEECH. */
	private static final String SERVICIO_ACTCTAEECH = "ACTCTAEECH";

	/** The Constant VERSION_ACTCTAEECH. */
	private static final String VERSION_ACTCTAEECH = "100";

	/** The tipo doc benef nvo. */
	private String tipoDocBenefNvo;

	/** The nro doc benef nvo. */
	private String nroDocBenefNvo;

	/** The razon soc benef nvo. */
	private String razonSocBenefNvo;

	/** The tipo doc benef ori. */
	private String tipoDocBenefOri;

	/** The nro doc benef ori. */
	private String nroDocBenefOri;

	/** The razon soc benef ori. */
	private String razonSocBenefOri;

	/** The importe. */
	private String importe;

	/** The cuit emisor. */
	private String cuitEmisor;

	/** The razon soc emisor. */
	private String razonSocEmisor;

	/** The tipo endoso. */
	private String tipoEndoso;

	/** The referencia. */
	private String referencia;

	/** The referencia valor. */
	private String referenciaValor;

	/**
	 * Instantiates a new endosar echeq in entity.
	 */
	public EndosarECheqInEntity() {
		super();
	}

	@Override
	public String getNombreServicio() {
		return SERVICIO_ACTCTAEECH;
	}

	@Override
	public String getVersionServicio() {
		return VERSION_ACTCTAEECH;
	}

	/**
	 * Instantiates a new endosar echeq in entity.
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
	 * @param tipoDocBenefNvo           the tipo doc benef nvo
	 * @param nroDocBenefNvo            the nro doc benef nvo
	 * @param razonSocBenefNvo          the razon soc benef nvo
	 * @param importe                   the importe
	 * @param cuitEmisor                the cuit emisor
	 * @param razonSocEmisor            the razon soc emisor
	 * @param tipoEndoso                the tipo endoso
	 * @param referencia                the referencia
	 * @param referenciaValor           the referencia valor
	 */
	public EndosarECheqInEntity(String idCheque, String tipoDocumento, String nroDocumento, String tipoCuenta,
			String sucursalCuenta, String numeroCuenta, String nroCheque, String fechaEmision, String fechaPago,
			String jSessionId, String tipoDocumentoBeneficiario, String nroDocumentoBeneficiario,
			String razonSocialBeneficiario, String tipoDocBenefNvo, String nroDocBenefNvo, String razonSocBenefNvo,
			String importe, String cuitEmisor, String razonSocEmisor, String tipoEndoso, String referencia,
			String referenciaValor) {
		super(idCheque, tipoDocumento, nroDocumento, tipoCuenta, sucursalCuenta, numeroCuenta, nroCheque, fechaEmision,
				fechaPago, jSessionId, tipoDocumentoBeneficiario, nroDocumentoBeneficiario, razonSocialBeneficiario);
		this.tipoDocBenefNvo = tipoDocBenefNvo;
		this.nroDocBenefNvo = nroDocBenefNvo;
		this.razonSocBenefNvo = razonSocBenefNvo;
		this.importe = importe;
		this.cuitEmisor = cuitEmisor;
		this.razonSocEmisor = razonSocEmisor;
		this.tipoEndoso = tipoEndoso;
		this.referencia = referencia;
		this.referenciaValor = referenciaValor;
	}

	/**
	 * Gets the tipo doc benef nvo.
	 *
	 * @return the tipo doc benef nvo
	 */
	public String getTipoDocBenefNvo() {
		return tipoDocBenefNvo;
	}

	/**
	 * Sets the tipo doc benef nvo.
	 *
	 * @param tipoDocBenefNvo the new tipo doc benef nvo
	 */
	public void setTipoDocBenefNvo(String tipoDocBenefNvo) {
		this.tipoDocBenefNvo = tipoDocBenefNvo;
	}

	/**
	 * Gets the nro doc benef nvo.
	 *
	 * @return the nro doc benef nvo
	 */
	public String getNroDocBenefNvo() {
		return nroDocBenefNvo;
	}

	/**
	 * Sets the nro doc benef nvo.
	 *
	 * @param nroDocBenefNvo the new nro doc benef nvo
	 */
	public void setNroDocBenefNvo(String nroDocBenefNvo) {
		this.nroDocBenefNvo = nroDocBenefNvo;
	}

	/**
	 * Gets the razon soc benef nvo.
	 *
	 * @return the razon soc benef nvo
	 */
	public String getRazonSocBenefNvo() {
		return razonSocBenefNvo;
	}

	/**
	 * Sets the razon soc benef nvo.
	 *
	 * @param razonSocBenefNvo the new razon soc benef nvo
	 */
	public void setRazonSocBenefNvo(String razonSocBenefNvo) {
		this.razonSocBenefNvo = razonSocBenefNvo;
	}

	/**
	 * Gets the tipo doc benef ori.
	 *
	 * @return the tipo doc benef ori
	 */
	public String getTipoDocBenefOri() {
		return tipoDocBenefOri;
	}

	/**
	 * Sets the tipo doc benef ori.
	 *
	 * @param tipoDocBenefOri
	 *     the new tipo doc benef ori
	 */
	public void setTipoDocBenefOri(String tipoDocBenefOri) {
		this.tipoDocBenefOri = tipoDocBenefOri;
	}

	/**
	 * Gets the nro doc benef ori.
	 *
	 * @return the nro doc benef ori
	 */
	public String getNroDocBenefOri() {
		return nroDocBenefOri;
	}

	/**
	 * Sets the nro doc benef ori.
	 *
	 * @param nroDocBenefOri
	 *     the new nro doc benef ori
	 */
	public void setNroDocBenefOri(String nroDocBenefOri) {
		this.nroDocBenefOri = nroDocBenefOri;
	}

	/**
	 * Gets the razon soc benef ori.
	 *
	 * @return the razon soc benef ori
	 */
	public String getRazonSocBenefOri() {
		return razonSocBenefOri;
	}

	/**
	 * Sets the razon soc benef ori.
	 *
	 * @param razonSocBenefOri the new razon soc benef ori
	 */
	public void setRazonSocBenefOri(String razonSocBenefOri) {
		this.razonSocBenefOri = razonSocBenefOri;
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
	 * Gets the cuit emisor.
	 *
	 * @return the cuit emisor
	 */
	public String getCuitEmisor() {
		return cuitEmisor;
	}

	/**
	 * Sets the cuit emisor.
	 *
	 * @param cuitEmisor the new cuit emisor
	 */
	public void setCuitEmisor(String cuitEmisor) {
		this.cuitEmisor = cuitEmisor;
	}

	/**
	 * Gets the razon soc emisor.
	 *
	 * @return the razon soc emisor
	 */
	public String getRazonSocEmisor() {
		return razonSocEmisor;
	}

	/**
	 * Sets the razon soc emisor.
	 *
	 * @param razonSocEmisor the new razon soc emisor
	 */
	public void setRazonSocEmisor(String razonSocEmisor) {
		this.razonSocEmisor = razonSocEmisor;
	}

	/**
	 * Gets the tipo endoso.
	 *
	 * @return the tipo endoso
	 */
	public String getTipoEndoso() {
		return tipoEndoso;
	}

	/**
	 * Sets the tipo endoso.
	 *
	 * @param tipoEndoso the new tipo endoso
	 */
	public void setTipoEndoso(String tipoEndoso) {
		this.tipoEndoso = tipoEndoso;
	}

	/**
	 * Gets the referencia.
	 *
	 * @return the referencia
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * Sets the referencia.
	 *
	 * @param referencia the new referencia
	 */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * Gets the referencia valor.
	 *
	 * @return the referencia valor
	 */
	public String getReferenciaValor() {
		return referenciaValor;
	}

	/**
	 * Sets the referencia valor.
	 *
	 * @param referenciaValor the new referencia valor
	 */
	public void setReferenciaValor(String referenciaValor) {
		this.referenciaValor = referenciaValor;
	}

	@Override
	public IatxRequestData generateRequestData(Cliente cliente, IOperacionECheqInEntity<?> operacionesECheqInEntity) {
		EndosarECheqInEntity endosarECheqInEntity = (EndosarECheqInEntity) operacionesECheqInEntity;
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getIdCheque());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getTipoDocumento());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getNroDocumento());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getTipoDocBenefNvo());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getNroDocBenefNvo());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getRazonSocBenefNvo());
     	iatxRequestData.addBodyValue(endosarECheqInEntity.getTipoCuenta());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getSucursalCuenta());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getNumeroCuenta());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getNroCheque());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getImporte());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getFechaEmision());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getFechaPago());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getCuitEmisor());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getRazonSocEmisor());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getTipoEndoso());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getReferencia());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getReferenciaValor());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getJSessionId());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getTipoDocBenefOri());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getNroDocBenefOri());
    	iatxRequestData.addBodyValue(endosarECheqInEntity.getRazonSocBenefOri());
		return iatxRequestData;
	}

}
