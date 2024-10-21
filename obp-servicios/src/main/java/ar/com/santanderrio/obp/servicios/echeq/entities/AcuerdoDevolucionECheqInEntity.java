package ar.com.santanderrio.obp.servicios.echeq.entities;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class AcuerdoDevolucionEcheqInEntity.
 */
public class AcuerdoDevolucionECheqInEntity extends OperacionesECheqBeneficiarioInEntity
		implements IOperacionECheqInEntity<IatxRequestData> {
    
    
    /** The Constant SERVICIO_ANUCTAECHQ. */
    private static final String SERVICIO_ACTCTADEVC = "ACTCTADEVC";

    /** The Constant VERSION_ANUCTAECHQ. */
    private static final String VERSION_ACTCTADEVC = "100";

	/** The nro doc beneficiario. */
	private String importeCuenta;
	/** The cuit emisor. */
	private String cuitEmisor;

	/** The tipo doc beneficiario. */
	private String razonSocialEmisor;

	/** The Razon social beneficiario. */
	private String accion;

	/**
	 * Instantiates a new acuerdo devolucion echeq in entity.
	 */
	public AcuerdoDevolucionECheqInEntity() {
		super();
	}


	/**
	 * Instantiates a new acuerdo devolucion echeq in entity.
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
	 * @param importeCuenta             the importe cuenta
	 * @param cuitEmisor                the cuit emisor
	 * @param razonSocialEmisor         the razon social emisor
	 * @param accion                    the accion
	 */
	public AcuerdoDevolucionECheqInEntity(String idCheque, String tipoDocumento, String nroDocumento, String tipoCuenta,
			String sucursalCuenta, String numeroCuenta, String nroCheque, String fechaEmision, String fechaPago,
			String jSessionId, String tipoDocumentoBeneficiario, String nroDocumentoBeneficiario,
			String razonSocialBeneficiario, String importeCuenta, String cuitEmisor, String razonSocialEmisor,
			String accion) {
		super(idCheque, tipoDocumento, nroDocumento, tipoCuenta, sucursalCuenta, numeroCuenta, nroCheque, fechaEmision,
				fechaPago, jSessionId, tipoDocumentoBeneficiario, nroDocumentoBeneficiario, razonSocialBeneficiario);
		this.importeCuenta = importeCuenta;
		this.cuitEmisor = cuitEmisor;
		this.razonSocialEmisor = razonSocialEmisor;
		this.accion = accion;
	}

	/**
	 * Gets the importe cuenta.
	 *
	 * @return the importe cuenta
	 */
	public String getImporteCuenta() {
		return importeCuenta;
	}

	/**
	 * Sets the importe cuenta.
	 *
	 * @param importeCuenta the new importe cuenta
	 */
	public void setImporteCuenta(String importeCuenta) {
		this.importeCuenta = importeCuenta;
	}

	/**
	 * Gets the cuit emisor.
	 *
	 * @return the cuit emisor
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.echeq.entities.
	 * OperacionesECheqEmisorInEntity#getCuitEmisor()
	 */
	public String getCuitEmisor() {
		return cuitEmisor;
	}

	/**
	 * Sets the cuit emisor.
	 *
	 * @param cuitEmisor the new cuit emisor
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.echeq.entities.
	 * OperacionesECheqEmisorInEntity#setCuitEmisor(java.lang.String)
	 */
	public void setCuitEmisor(String cuitEmisor) {
		this.cuitEmisor = cuitEmisor;
	}

	/**
	 * Gets the razon social emisor.
	 *
	 * @return the razon social emisor
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.echeq.entities.
	 * OperacionesECheqEmisorInEntity#getRazonSocialEmisor()
	 */
	public String getRazonSocialEmisor() {
		return razonSocialEmisor;
	}

	/**
	 * Sets the razon social emisor.
	 *
	 * @param razonSocialEmisor the new razon social emisor
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.echeq.entities.
	 * OperacionesECheqEmisorInEntity#setRazonSocialEmisor(java.lang.String)
	 */
	public void setRazonSocialEmisor(String razonSocialEmisor) {
		this.razonSocialEmisor = razonSocialEmisor;
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
    public String getNombreServicio() {
        return SERVICIO_ACTCTADEVC;
    }


    @Override
    public String getVersionServicio() {
        return VERSION_ACTCTADEVC;
    }

	@Override
    public IatxRequestData generateRequestData(Cliente cliente, IOperacionECheqInEntity<?> inEntity) {
	    
	    AcuerdoDevolucionECheqInEntity operacionesECheqInEntity = (AcuerdoDevolucionECheqInEntity) inEntity;
        IatxRequestData iatxRequestData = new IatxRequestData(cliente);
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getTipoCuenta());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getSucursalCuenta());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getNumeroCuenta());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getImporteCuenta());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getIdCheque());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getNroCheque());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getFechaEmision());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getFechaPago());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getAccion());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getTipoDocumento());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getNroDocumento());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getCuitEmisor());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getRazonSocialEmisor());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getTipoDocumentoBeneficiario());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getNroDocumentoBeneficiario());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getRazonSocialBeneficiario());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getJSessionId());
        return iatxRequestData;
    }

}
