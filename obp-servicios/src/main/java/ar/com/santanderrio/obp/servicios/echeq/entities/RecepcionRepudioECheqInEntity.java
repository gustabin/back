package ar.com.santanderrio.obp.servicios.echeq.entities;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class RecepcionRepudioEcheqInEntity.
 */
public class RecepcionRepudioECheqInEntity extends OperacionesECheqEmisorInEntity
		implements IOperacionECheqInEntity<IatxRequestData> {

	/** The importe. */
	private String importe;
	/** The tipo solicitud. */
	private String tipoSolicitud;

	/** The motivo repudio. */
	private String motivoRepudio;

	/**
	 * Instantiates a new recepcion repudio echeq in entity.
	 */
	public RecepcionRepudioECheqInEntity() {
		super();
	}

    /** The Constant SERVICIO_ACTCTAAREC. */
    private static final String SERVICIO_ACTCTAAREC = "ACTCTAAREC";

    /** The Constant VERSION_ACTCTAAREC. */
    private static final String VERSION_ACTCTAAREC = "100";
    
    @Override
    public String getNombreServicio() {
        return SERVICIO_ACTCTAAREC;
    }


    @Override
    public String getVersionServicio() {
        return VERSION_ACTCTAAREC;
    }


	/**
	 * Instantiates a new recepcion repudio echeq in entity.
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
	 * @param importe           the importe
	 * @param tipoSolicitud     the tipo solicitud
	 * @param motivoRepudio     the motivo repudio
	 */
	public RecepcionRepudioECheqInEntity(String idCheque, String tipoDocumento, String nroDocumento, String tipoCuenta,
			String sucursalCuenta, String numeroCuenta, String nroCheque, String fechaEmision, String fechaPago,
			String jSessionId, String cuitEmisor, String razonSocialEmisor, String importe, String tipoSolicitud,
			String motivoRepudio) {
		super(idCheque, tipoDocumento, nroDocumento, tipoCuenta, sucursalCuenta, numeroCuenta, nroCheque, fechaEmision,
				fechaPago, jSessionId, cuitEmisor, razonSocialEmisor);
		this.importe = importe;
		this.tipoSolicitud = tipoSolicitud;
		this.motivoRepudio = motivoRepudio;
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
    public IatxRequestData generateRequestData(Cliente cliente, IOperacionECheqInEntity<?> InEntity) {
        RecepcionRepudioECheqInEntity recepcionRepudioECheqInEntity =(RecepcionRepudioECheqInEntity) InEntity;
        IatxRequestData iatxRequestData = new IatxRequestData(cliente);
        iatxRequestData.addBodyValue(recepcionRepudioECheqInEntity.getIdCheque());
        iatxRequestData.addBodyValue(recepcionRepudioECheqInEntity.getTipoDocumento());
        iatxRequestData.addBodyValue(recepcionRepudioECheqInEntity.getNroDocumento());
        iatxRequestData.addBodyValue(ISBANStringUtils.fillStr(recepcionRepudioECheqInEntity.getTipoCuenta(), 2));
        iatxRequestData.addBodyValue(ISBANStringUtils.fillStr(recepcionRepudioECheqInEntity.getSucursalCuenta(), 3));
        iatxRequestData.addBodyValue(ISBANStringUtils.fillStr(recepcionRepudioECheqInEntity.getNumeroCuenta(), 7));
        iatxRequestData.addBodyValue(recepcionRepudioECheqInEntity.getNroCheque());
        iatxRequestData.addBodyValue(recepcionRepudioECheqInEntity.getImporte());
        iatxRequestData.addBodyValue(recepcionRepudioECheqInEntity.getFechaEmision());
        iatxRequestData.addBodyValue(recepcionRepudioECheqInEntity.getFechaPago());
        iatxRequestData.addBodyValue(recepcionRepudioECheqInEntity.getCuitEmisor());
        iatxRequestData.addBodyValue(ISBANStringUtils.fillStr(recepcionRepudioECheqInEntity.getRazonSocialEmisor(), 100));
        iatxRequestData.addBodyValue(recepcionRepudioECheqInEntity.getTipoSolicitud());
        iatxRequestData.addBodyValue(ISBANStringUtils.fillStr(recepcionRepudioECheqInEntity.getMotivoRepudio(), 500));
        iatxRequestData.addBodyValue(ISBANStringUtils.fillStr(recepcionRepudioECheqInEntity.getJSessionId(), 50));
        return iatxRequestData;
    }

}
