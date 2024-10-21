package ar.com.santanderrio.obp.servicios.echeq.entities;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class AltaEcheqInEntity.
 */
public class AltaECheqInEntity implements IOperacionECheqInEntity<IatxRequestData> {

	/** The Constant SERVICIO_ACTCTAECHF. */
	private static final String SERVICIO_ACTCTAECHF = "ACTCTAECHF";

	/** The Constant VERSION_ACTCTAECHF. */
	private static final String VERSION_ACTCTAECHF = "100";

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The sucursal cuenta. */
	private String sucursalCuenta;

	/** The nro cuenta. */
	private String nroCuenta;

	/** The cant talonarios. */
	private String cantTalonarios;

	/** The cant cheques. */
	private String cantCheques;

	/** The ind papel electronico. */
	private String indPapelElectronico;

	/** The tipo cheque. */
	private String tipoCheque;

	/** The importe. */
	private String importe;

	/** The fecha pago. */
	private String fechaPago;

	/** The tipo doc benef. */
	private String tipoDocBenef;

	/** The nro doc benef. */
	private String nroDocBenef;

	/** The nombre benef. */
	private String nombreBenef;

	/** The orden. */
	private String orden;

	/** The motivo. */
	private String motivo;

	/** The concepto. */
	private String concepto;

	/** The cruzado. */
	private String cruzado;

	/** The mail beneficiario. */
	private String mailBeneficiario;

	/** The tipo doc. */
	private String tipoDoc;

	/** The nro doc. */
	private String nroDoc;

	/** The referencia. */
	private String referencia;

	/** The referencia valor. */
	private String referenciaValor;

	/** The j session id. */
	private String jSessionId;

	/**
	 * Instantiates a new alta echeq in entity.
	 */
	public AltaECheqInEntity() {
		super();
	}

	/**
	 * Instantiates a new alta echeq in entity.
	 *
	 * @param tipoCuenta          the tipo cuenta
	 * @param sucursalCuenta      the sucursal cuenta
	 * @param nroCuenta           the nro cuenta
	 * @param cantTalonarios      the cant talonarios
	 * @param cantCheques         the cant cheques
	 * @param indPapelElectronico the ind papel electronico
	 * @param tipoCheque          the tipo cheque
	 * @param importe             the importe
	 * @param fechaPago           the fecha pago
	 * @param tipoDocBenef        the tipo doc benef
	 * @param nroDocBenef         the nro doc benef
	 * @param nombreBenef         the nombre benef
	 * @param orden               the orden
	 * @param motivo              the motivo
	 * @param concepto            the concepto
	 * @param cruzado             the cruzado
	 * @param mailBeneficiario    the mail beneficiario
	 * @param tipoDoc             the tipo doc
	 * @param nroDoc              the nro doc
	 * @param referencia          the referencia
	 * @param referenciaValor     the referencia valor
	 * @param jSessionId          the j session Id
	 */
	public AltaECheqInEntity(String tipoCuenta, String sucursalCuenta, String nroCuenta, String cantTalonarios,
			String cantCheques, String indPapelElectronico, String tipoCheque, String importe, String fechaPago,
			String tipoDocBenef, String nroDocBenef, String nombreBenef, String orden, String motivo, String concepto,
			String cruzado, String mailBeneficiario, String tipoDoc, String nroDoc, String referencia,
			String referenciaValor, String jSessionId) {
		super();
		this.tipoCuenta = tipoCuenta;
		this.sucursalCuenta = sucursalCuenta;
		this.nroCuenta = nroCuenta;
		this.cantTalonarios = cantTalonarios;
		this.cantCheques = cantCheques;
		this.indPapelElectronico = indPapelElectronico;
		this.tipoCheque = tipoCheque;
		this.importe = importe;
		this.fechaPago = fechaPago;
		this.tipoDocBenef = tipoDocBenef;
		this.nroDocBenef = nroDocBenef;
		this.nombreBenef = nombreBenef;
		this.orden = orden;
		this.motivo = motivo;
		this.concepto = concepto;
		this.cruzado = cruzado;
		this.mailBeneficiario = mailBeneficiario;
		this.tipoDoc = tipoDoc;
		this.nroDoc = nroDoc;
		this.referencia = referencia;
		this.referenciaValor = referenciaValor;
		this.jSessionId = jSessionId;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursal cuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta the new sucursal cuenta
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta the new nro cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the cant talonarios.
	 *
	 * @return the cant talonarios
	 */
	public String getCantTalonarios() {
		return cantTalonarios;
	}

	/**
	 * Sets the cant talonarios.
	 *
	 * @param cantTalonarios the new cant talonarios
	 */
	public void setCantTalonarios(String cantTalonarios) {
		this.cantTalonarios = cantTalonarios;
	}

	/**
	 * Gets the cant cheques.
	 *
	 * @return the cant cheques
	 */
	public String getCantCheques() {
		return cantCheques;
	}

	/**
	 * Sets the cant cheques.
	 *
	 * @param cantCheques the new cant cheques
	 */
	public void setCantCheques(String cantCheques) {
		this.cantCheques = cantCheques;
	}

	/**
	 * Gets the ind papel electronico.
	 *
	 * @return the ind papel electronico
	 */
	public String getIndPapelElectronico() {
		return indPapelElectronico;
	}

	/**
	 * Sets the ind papel electronico.
	 *
	 * @param indPapelElectronico the new ind papel electronico
	 */
	public void setIndPapelElectronico(String indPapelElectronico) {
		this.indPapelElectronico = indPapelElectronico;
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
	 * Gets the fecha pago.
	 *
	 * @return the fecha pago
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * Sets the fecha pago.
	 *
	 * @param fechaPago the new fecha pago
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * Gets the tipo doc benef.
	 *
	 * @return the tipo doc benef
	 */
	public String getTipoDocBenef() {
		return tipoDocBenef;
	}

	/**
	 * Sets the tipo doc benef.
	 *
	 * @param tipoDocBenef the new tipo doc benef
	 */
	public void setTipoDocBenef(String tipoDocBenef) {
		this.tipoDocBenef = tipoDocBenef;
	}

	/**
	 * Gets the nro doc benef.
	 *
	 * @return the nro doc benef
	 */
	public String getNroDocBenef() {
		return nroDocBenef;
	}

	/**
	 * Sets the nro doc benef.
	 *
	 * @param nroDocBenef the new nro doc benef
	 */
	public void setNroDocBenef(String nroDocBenef) {
		this.nroDocBenef = nroDocBenef;
	}

	/**
	 * Gets the nombre benef.
	 *
	 * @return the nombre benef
	 */
	public String getNombreBenef() {
		return nombreBenef;
	}

	/**
	 * Sets the nombre benef.
	 *
	 * @param nombreBenef the new nombre benef
	 */
	public void setNombreBenef(String nombreBenef) {
		this.nombreBenef = nombreBenef;
	}

	/**
	 * Gets the orden.
	 *
	 * @return the orden
	 */
	public String getOrden() {
		return orden;
	}

	/**
	 * Sets the orden.
	 *
	 * @param orden the new orden
	 */
	public void setOrden(String orden) {
		this.orden = orden;
	}

	/**
	 * Gets the motivo.
	 *
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}

	/**
	 * Sets the motivo.
	 *
	 * @param motivo the new motivo
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	/**
	 * Gets the concepto.
	 *
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Sets the concepto.
	 *
	 * @param concepto the new concepto
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/**
	 * Gets the cruzado.
	 *
	 * @return the cruzado
	 */
	public String getCruzado() {
		return cruzado;
	}

	/**
	 * Sets the cruzado.
	 *
	 * @param cruzado the new cruzado
	 */
	public void setCruzado(String cruzado) {
		this.cruzado = cruzado;
	}

	/**
	 * Gets the mail beneficiario.
	 *
	 * @return the mail beneficiario
	 */
	public String getMailBeneficiario() {
		return mailBeneficiario;
	}

	/**
	 * Sets the mail beneficiario.
	 *
	 * @param mailBeneficiario the new mail beneficiario
	 */
	public void setMailBeneficiario(String mailBeneficiario) {
		this.mailBeneficiario = mailBeneficiario;
	}

	/**
	 * Gets the tipo doc.
	 *
	 * @return the tipo doc
	 */
	public String getTipoDoc() {
		return tipoDoc;
	}

	/**
	 * Sets the tipo doc.
	 *
	 * @param tipoDoc the new tipo doc
	 */
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	/**
	 * Gets the nro doc.
	 *
	 * @return the nro doc
	 */
	public String getNroDoc() {
		return nroDoc;
	}

	/**
	 * Sets the nro doc.
	 *
	 * @param nroDoc the new nro doc
	 */
	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
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

	/**
	 * Gets the j session id.
	 *
	 * @return the j session id
	 */
	public String getJSessionId() {
		return jSessionId;
	}

	/**
	 * Sets the j sessionid.
	 *
	 * @param jSessionid the new j sessionid
	 */
	public void setJSessionId(String jSessionId) {
		this.jSessionId = jSessionId;
	}

	@Override
	public String getNombreServicio() {
		return SERVICIO_ACTCTAECHF;
	}

	@Override
	public String getVersionServicio() {
		return VERSION_ACTCTAECHF;
	}

	@Override
	public IatxRequestData generateRequestData(Cliente cliente, IOperacionECheqInEntity<?> operacionesECheqInEntity) {
		AltaECheqInEntity altaECheqInEntity = (AltaECheqInEntity) operacionesECheqInEntity;
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
     	iatxRequestData.addBodyValue(altaECheqInEntity.getTipoCuenta());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getSucursalCuenta());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getNroCuenta());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getCantTalonarios());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getCantCheques());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getIndPapelElectronico());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getTipoCheque());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getImporte());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getFechaPago());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getTipoDocBenef());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getNroDocBenef());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getNombreBenef());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getOrden());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getMotivo());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getConcepto());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getCruzado());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getMailBeneficiario());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getTipoDoc());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getNroDoc());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getReferencia());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getReferenciaValor());
    	iatxRequestData.addBodyValue(altaECheqInEntity.getJSessionId());
		return iatxRequestData;
	}

}
