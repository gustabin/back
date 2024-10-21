package ar.com.santanderrio.obp.servicios.echeq.entities;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class solicitarCertificadoEcheqInEntity.
 */
public class EmitirCertificadoECheqInEntity extends OperacionesECheqEmisorInEntity
		implements IOperacionECheqInEntity<IatxRequestData> {
    
    /** The Constant SERVICIO_ACTCTACERT. */
    private static final String SERVICIO_ACTCTACERT = "ACTCTACERT";

    /** The Constant VERSION_ACTCTACERT. */
    private static final String VERSION_ACTCTACERT = "100";

	/** The importe cuenta. */
	private String importeCuenta;

	/**
	 * Instantiates a new solicitar certificado echeq in entity.
	 */
	public EmitirCertificadoECheqInEntity() {
		super();
	}

	/**
	 * Instantiates a new solicitar certificado echeq in entity.
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
	 * @param importeCuenta     the importe cuenta
	 */
	public EmitirCertificadoECheqInEntity(String idCheque, String tipoDocumento, String nroDocumento,
			String tipoCuenta, String sucursalCuenta, String numeroCuenta, String nroCheque, String fechaEmision,
			String fechaPago, String jSessionId, String cuitEmisor, String razonSocialEmisor, String importeCuenta) {
		super(idCheque, tipoDocumento, nroDocumento, tipoCuenta, sucursalCuenta, numeroCuenta, nroCheque, fechaEmision,
				fechaPago, jSessionId, cuitEmisor, razonSocialEmisor);
		this.importeCuenta = importeCuenta;
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
	
	
    @Override
    public String getNombreServicio() {
        return SERVICIO_ACTCTACERT;
    }


    @Override
    public String getVersionServicio() {
        return VERSION_ACTCTACERT;
    }
    
    
	@Override
    public IatxRequestData generateRequestData(Cliente cliente, IOperacionECheqInEntity<?> inEntity) {
	    EmitirCertificadoECheqInEntity solicitarCertificadoECheqInEntity = (EmitirCertificadoECheqInEntity) inEntity;
        IatxRequestData iatxRequestData = new IatxRequestData(cliente);
        iatxRequestData.addBodyValue(solicitarCertificadoECheqInEntity.getIdCheque());
        iatxRequestData.addBodyValue(solicitarCertificadoECheqInEntity.getTipoDocumento());
        iatxRequestData.addBodyValue(solicitarCertificadoECheqInEntity.getNroDocumento());
        iatxRequestData.addBodyValue(solicitarCertificadoECheqInEntity.getTipoCuenta());
        iatxRequestData.addBodyValue(solicitarCertificadoECheqInEntity.getSucursalCuenta());
        iatxRequestData.addBodyValue(solicitarCertificadoECheqInEntity.getNumeroCuenta());
        iatxRequestData.addBodyValue(solicitarCertificadoECheqInEntity.getNroCheque());
        iatxRequestData.addBodyValue(solicitarCertificadoECheqInEntity.getImporteCuenta());
        iatxRequestData.addBodyValue(solicitarCertificadoECheqInEntity.getFechaEmision());
        iatxRequestData.addBodyValue(solicitarCertificadoECheqInEntity.getFechaPago());
        iatxRequestData.addBodyValue(solicitarCertificadoECheqInEntity.getCuitEmisor());
        iatxRequestData.addBodyValue(solicitarCertificadoECheqInEntity.getRazonSocialEmisor());
        iatxRequestData.addBodyValue(solicitarCertificadoECheqInEntity.getJSessionId());
        return iatxRequestData;
    }

}
