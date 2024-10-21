package ar.com.santanderrio.obp.servicios.echeq.entities;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

public class AltaCEDEcheqInEntity extends OperacionesECheqBeneficiarioInEntity
		implements IOperacionECheqInEntity<IatxRequestData> {

    private static final String SERVICIO_ACTCLEECED = "ACTCLEECED";
    private static final String VERSION_ACTCLEECED = "100";
    
    
    private String importe;
    
    private String domicilioBeneficiario;
    
    public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}
	
	public String getDomicilioBeneficiario() {
		return domicilioBeneficiario;
	}

	public void setDomicilioBeneficiario(String domicilioBeneficiario) {
		this.domicilioBeneficiario = domicilioBeneficiario;
	}

	public AltaCEDEcheqInEntity() {}
    
    public AltaCEDEcheqInEntity(String tipoCuenta, String sucursalCta, String nroCta, String idCheque, 
    		String tipoDocCed, String nroDocCed, String tipoDocCes, String nroDocCes, String importe, 
    		String nombreCes, String domicilioCes, String jSessionId) {
    	/*
		 * idCheque 
		 * the id chequetipoDocumento 
		 * the tipo documentonroDocumento 
		 * the nro documentotipoCuenta 
		 * the tipo cuentasucursalCuenta 
		 * the sucursal cuentanumeroCuenta 
		 * the numero cuentanroCheque 
		 * the nro chequefechaEmision 
		 * the fecha emisionfechaPago 
		 * the fecha pagojSessionId 
		 * the j session idtipoDocumentoBeneficiario 
		 * the tipo documento beneficiarionroDocumentoBeneficiario 
		 * the nro documento beneficiariorazonSocialBeneficiario 
		 * the razon social beneficiario
		 */
    	super(idCheque, tipoDocCed, nroDocCed, sucursalCta, nroCta, null, idCheque, null, null, jSessionId, tipoDocCes, nroDocCes, nombreCes);
    	this.importe = importe;
    	this.domicilioBeneficiario = domicilioCes;
	}
	
    /**
	 * Genera el request para invocar al servicio IATX ACTCLEECED100
	 */
	@Override
	public IatxRequestData generateRequestData(Cliente cliente, IOperacionECheqInEntity operacionesECheqInEntity) {
		AltaCEDEcheqInEntity altaCedInEntity = (AltaCEDEcheqInEntity) operacionesECheqInEntity;
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(altaCedInEntity.getTipoCuenta());
		iatxRequestData.addBodyValue(altaCedInEntity.getSucursalCuenta());
		iatxRequestData.addBodyValue(altaCedInEntity.getNumeroCuenta());
		iatxRequestData.addBodyValue(altaCedInEntity.getIdCheque());
		iatxRequestData.addBodyValue(altaCedInEntity.getTipoDocumento());
		iatxRequestData.addBodyValue(altaCedInEntity.getNroDocumento());
		iatxRequestData.addBodyValue(altaCedInEntity.getTipoDocumentoBeneficiario());
		iatxRequestData.addBodyValue(altaCedInEntity.getNroDocumentoBeneficiario());
		iatxRequestData.addBodyValue(altaCedInEntity.getRazonSocialBeneficiario());
		iatxRequestData.addBodyValue(altaCedInEntity.getDomicilioBeneficiario());
		iatxRequestData.addBodyValue(altaCedInEntity.getImporte());
		iatxRequestData.addBodyValue(altaCedInEntity.getJSessionId());
		return iatxRequestData;
	}
	
	@Override
	public String getNombreServicio() {
		return SERVICIO_ACTCLEECED;
	}
	
	@Override
	public String getVersionServicio() {
		return VERSION_ACTCLEECED;
	}

}
