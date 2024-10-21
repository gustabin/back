package ar.com.santanderrio.obp.servicios.echeq.entities;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

public class AceptarRepudiarAnularCEDEcheqInEntity extends OperacionesECheqBeneficiarioInEntity
		implements IOperacionECheqInEntity<IatxRequestData> {

	
	 /** The Constant SERVICIO_ACTCLEACED. */
    private static final String SERVICIO_ACTCLEACED = "ACTCLEACED";

    /** The Constant VERSION_ACTCLEACED. */
    private static final String VERSION_ACTCLEACED = "100";
    
    private String idCesion;
    
    private String importe;
    
    private String tipoSolicitud;
    
    private String motivo;
    
    /**
     * obtiene valor de idCesion
     * @return
     */
    public String getIdCesion() {
		return idCesion;
	}

    /**
     * setea valor de idCesion
     * @param idCesion
     */
	public void setIdCesion(String idCesion) {
		this.idCesion = idCesion;
	}

	/**
	 * obtiene valor de tipoSolicitud
	 * @return
	 */
	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	/**
	 * setea valor de tipoSolicitud
	 * @param tipoSolicitud
	 */
	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	/**
	 * obtiene valor de importe
	 * @return
	 */
	public String getImporte() {
    	return importe;
    }
    
	/**
	 * setea valor de importe
	 * @param importe
	 */
    public void setImporte(String importe) {
    	this.importe = importe;
    }

    /**
     * obtiene valor de motivo
     * @return
     */
    public String getMotivo() {
		return motivo;
	}

    /**
     * setea valor a motivo
     * @param motivo
     */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}


	/**Default constructor*/
	public AceptarRepudiarAnularCEDEcheqInEntity() {
		super();
	}
	
	/**Constructor*/
	public AceptarRepudiarAnularCEDEcheqInEntity(String tipoCuenta, String sucursalCta, String nroCta, String idCheque, String idCesion
			, String nroCheque, String tipoDocCed, String nroDocCed, String tipoDocCes, String nroDocCes, String importe, String tipoSolicitud, 
			String motivo, String jSessionId) {
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
		super(idCheque, tipoDocCed, nroDocCed, tipoCuenta, sucursalCta, nroCta, nroCheque, null, null, jSessionId, tipoDocCes, nroDocCes, null);
		this.motivo = motivo;
		this.importe = importe;
		this.idCesion = idCesion;
		this.tipoSolicitud = tipoSolicitud;
	}
	
	/**Obtiene nombre del servicio*/
	@Override
	public String getNombreServicio() {
		return SERVICIO_ACTCLEACED;
	}
	
	/**Obtiene version del servicio*/
	@Override
	public String getVersionServicio() {
		return VERSION_ACTCLEACED;
	}
	
	/**
	 * Genera el request para invocar al servicio IATX ACTCLEACED100
	 */
	@Override
	public IatxRequestData generateRequestData(Cliente cliente, IOperacionECheqInEntity<?> operacionesECheqInEntity) {
		AceptarRepudiarAnularCEDEcheqInEntity aceptarRepudiarAnularCEDEcheqInEntity = (AceptarRepudiarAnularCEDEcheqInEntity) operacionesECheqInEntity;
		IatxRequestData iatxRequestData = new IatxRequestData(cliente);
		iatxRequestData.addBodyValue(aceptarRepudiarAnularCEDEcheqInEntity.getTipoCuenta());
		iatxRequestData.addBodyValue(aceptarRepudiarAnularCEDEcheqInEntity.getSucursalCuenta());
		iatxRequestData.addBodyValue(aceptarRepudiarAnularCEDEcheqInEntity.getNumeroCuenta());
		iatxRequestData.addBodyValue(aceptarRepudiarAnularCEDEcheqInEntity.getIdCheque());
		iatxRequestData.addBodyValue(aceptarRepudiarAnularCEDEcheqInEntity.getIdCesion());
		iatxRequestData.addBodyValue(aceptarRepudiarAnularCEDEcheqInEntity.getNroCheque());
		iatxRequestData.addBodyValue(aceptarRepudiarAnularCEDEcheqInEntity.getTipoDocumento());
		iatxRequestData.addBodyValue(aceptarRepudiarAnularCEDEcheqInEntity.getNroDocumento());
		iatxRequestData.addBodyValue(aceptarRepudiarAnularCEDEcheqInEntity.getTipoDocumentoBeneficiario());
		iatxRequestData.addBodyValue(aceptarRepudiarAnularCEDEcheqInEntity.getNroDocumentoBeneficiario());
		iatxRequestData.addBodyValue(aceptarRepudiarAnularCEDEcheqInEntity.getImporte());
		iatxRequestData.addBodyValue(aceptarRepudiarAnularCEDEcheqInEntity.getTipoSolicitud());
		iatxRequestData.addBodyValue(aceptarRepudiarAnularCEDEcheqInEntity.getMotivo());
		iatxRequestData.addBodyValue(aceptarRepudiarAnularCEDEcheqInEntity.getJSessionId());
		return iatxRequestData;
	}

}
