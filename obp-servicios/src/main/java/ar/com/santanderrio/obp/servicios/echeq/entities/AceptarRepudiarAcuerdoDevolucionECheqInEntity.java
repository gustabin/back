package ar.com.santanderrio.obp.servicios.echeq.entities;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class AceptarRepudiarAcuerdoDevolucionECheqInEntity.
 */
public class AceptarRepudiarAcuerdoDevolucionECheqInEntity extends OperacionesECheqEmisorInEntity 
		implements IOperacionECheqInEntity<IatxRequestData> {

    /** The Constant SERVICIO_ACTCTAARAD. */
    private static final String SERVICIO_ACTCTAARAD = "ACTCTAARAD";

    /** The Constant VERSION_ACTCTAARAD. */
    private static final String VERSION_ACTCTAARAD = "100";
    
	/** The importe cuenta. */
	private String importeCuenta;

	/** The tipo solicitud. */
	private String tipoSolicitud;

	/** The motivo repudio. */
	private String motivoRepudio;

	/**
	 * Instantiates a new aceptar repudiar acuerdo devolucion echeq in entity.
	 */
	public AceptarRepudiarAcuerdoDevolucionECheqInEntity() {
		super();
	}

	/**
	 * Instantiates a new aceptar repudiar acuerdo devolucion echeq in entity.
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
	 * @param tipoSolicitud     the tipo solicitud
	 * @param motivoRepudio     the motivo repudio
	 */
	public AceptarRepudiarAcuerdoDevolucionECheqInEntity(String idCheque, String tipoDocumento, String nroDocumento,
			String tipoCuenta, String sucursalCuenta, String numeroCuenta, String nroCheque, String fechaEmision,
			String fechaPago, String jSessionId, String cuitEmisor, String razonSocialEmisor, String importeCuenta,
			String tipoSolicitud, String motivoRepudio) {
		super(idCheque, tipoDocumento, nroDocumento, tipoCuenta, sucursalCuenta, numeroCuenta, nroCheque, fechaEmision,
				fechaPago, jSessionId, cuitEmisor, razonSocialEmisor);
		this.importeCuenta = importeCuenta;
		this.tipoSolicitud = tipoSolicitud;
		this.motivoRepudio = motivoRepudio;
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
	 * Gets the tipo solicitud.
	 *
	 * @return the tipo solicitud
	 */
	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	/**
	 * Sets the tipo solicitud.
	 *
	 * @param tipoSolicitud the new tipo solicitud
	 */
	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	/**
	 * Gets the motivo repudio.
	 *
	 * @return the motivo repudio
	 */
	public String getMotivoRepudio() {
		return motivoRepudio;
	}

	/**
	 * Sets the motivo repudio.
	 *
	 * @param motivoRepudio the new motivo repudio
	 */
	public void setMotivoRepudio(String motivoRepudio) {
		this.motivoRepudio = motivoRepudio;
	}
	
    @Override
    public String getNombreServicio() {
        return SERVICIO_ACTCTAARAD;
    }


    @Override
    public String getVersionServicio() {
        return VERSION_ACTCTAARAD;
    }

    @Override
    public IatxRequestData generateRequestData(Cliente cliente, IOperacionECheqInEntity<?> inEntity) {
        AceptarRepudiarAcuerdoDevolucionECheqInEntity operacionesECheqInEntity = (AceptarRepudiarAcuerdoDevolucionECheqInEntity) inEntity;
        IatxRequestData iatxRequestData = new IatxRequestData(cliente);
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getTipoCuenta());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getSucursalCuenta());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getNumeroCuenta());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getImporteCuenta());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getIdCheque());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getNroCheque());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getFechaEmision());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getFechaPago());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getTipoSolicitud());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getCuitEmisor());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getRazonSocialEmisor());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getMotivoRepudio());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getTipoDocumento());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getNroDocumento());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getJSessionId());
        return iatxRequestData;
    }
    


}
