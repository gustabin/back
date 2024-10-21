package ar.com.santanderrio.obp.servicios.echeq.entities;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequestData;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class SolicitarAnularSolicitudAcuerdoDevolucionECheqInEntity.
 */
public class SolicitarAnularSolicitudAcuerdoDevolucionECheqInEntity extends AceptarRepudiarAcuerdoDevolucionECheqInEntity
        implements IOperacionECheqInEntity<IatxRequestData> {

    /** The Constant SERVICIO_ACTCTAADEC. */
    private static final String SERVICIO_ACTCTAADEC = "ACTCTAADEC";
    
    /** The Constant VERSION_ACTCTAADEC. */
    private static final String VERSION_ACTCTAADEC = "100";

    private String tipoDocumentoBeneficiario;

    private String nroDocumentoBeneficiario;

    private String razonSocialBeneficiario;
    
    
    @Override
    public String getNombreServicio() {
        return SERVICIO_ACTCTAADEC;
    }


    @Override
    public String getVersionServicio() {
        return VERSION_ACTCTAADEC;
    }
    
    /**
     * Gets the tipo documento beneficiario.
     *
     * @return the tipo documento beneficiario
     */
    public String getTipoDocumentoBeneficiario() {
        return tipoDocumentoBeneficiario;
    }

    /**
     * Sets the tipo documento beneficiario.
     *
     * @param tipoDocumentoBeneficiario the new tipo documento beneficiario
     */
    public void setTipoDocumentoBeneficiario(String tipoDocumentoBeneficiario) {
        this.tipoDocumentoBeneficiario = tipoDocumentoBeneficiario;
    }

    /**
     * Gets the nro documento beneficiario.
     *
     * @return the nro documento beneficiario
     */
    public String getNroDocumentoBeneficiario() {
        return nroDocumentoBeneficiario;
    }

    /**
     * Sets the nro documento beneficiario.
     *
     * @param nroDocumentoBeneficiario the new nro documento beneficiario
     */
    public void setNroDocumentoBeneficiario(String nroDocumentoBeneficiario) {
        this.nroDocumentoBeneficiario = nroDocumentoBeneficiario;
    }

    /**
     * Gets the razon social beneficiario.
     *
     * @return the razon social beneficiario
     */
    public String getRazonSocialBeneficiario() {
        return razonSocialBeneficiario;
    }

    /**
     * Sets the razon social beneficiario.
     *
     * @param razonSocialBeneficiario the new razon social beneficiario
     */
    public void setRazonSocialBeneficiario(String razonSocialBeneficiario) {
        this.razonSocialBeneficiario = razonSocialBeneficiario;
    }

    @Override
    public IatxRequestData generateRequestData(Cliente cliente, IOperacionECheqInEntity<?> inEntity) {
        SolicitarAnularSolicitudAcuerdoDevolucionECheqInEntity operacionesECheqInEntity = (SolicitarAnularSolicitudAcuerdoDevolucionECheqInEntity) inEntity;
        IatxRequestData iatxRequestData = new IatxRequestData(cliente);
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getIdCheque());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getTipoDocumento());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getNroDocumento());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getTipoCuenta());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getSucursalCuenta());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getNumeroCuenta());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getNroCheque());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getImporteCuenta());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getFechaEmision());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getFechaPago());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getTipoSolicitud());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getCuitEmisor());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getRazonSocialEmisor());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getTipoDocumentoBeneficiario());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getNroDocumentoBeneficiario());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getRazonSocialBeneficiario());
        iatxRequestData.addBodyValue(operacionesECheqInEntity.getJSessionId());
        return iatxRequestData;
    }
}
