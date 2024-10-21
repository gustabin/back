package ar.com.santanderrio.obp.servicios.cuentas.entities;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;

public class NupDTO extends Entity {
    private static final long serialVersionUID = 3325476958168533266L;

    public static final String TIPO_DOC_CDI = "D";
    public static final String TIPO_DOC_CUIL = "L";
    public static final String TIPO_DOC_CUIT = "T";

    //TODO: safe to remove?
    private String tipoDocumento;
    private String numeroDocumento;
    private String marcaDocumentoPpal;
    private Map<String, DetalleDocumentoDTO> detalleDocumento;

    private String idSistema;

    private String cantDescStatusResultado;
    private String descripcionStatusResultado;

    private ErrorCompraVentaEnum error;
    private Boolean tieneError;

    public NupDTO(ErrorCompraVentaEnum error) {
        super();
        this.error = error;
        this.tieneError = Boolean.TRUE;
        this.detalleDocumento = new HashMap<String, DetalleDocumentoDTO>();
    }

    public NupDTO() {
        super();
        this.tieneError = Boolean.FALSE;
        this.detalleDocumento = new HashMap<String, DetalleDocumentoDTO>();
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNroDocumento() {
        return numeroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.numeroDocumento = nroDocumento;
    }

    public String getMarcaDocumentoPpal() {
        return marcaDocumentoPpal;
    }

    public void setMarcaDocumentoPpal(String marcaDocumentoPpal) {
        this.marcaDocumentoPpal = marcaDocumentoPpal;
    }

    public String getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(String idSistema) {
        this.idSistema = idSistema;
    }

    public String getCantDescStatusResultado() {
        return cantDescStatusResultado;
    }

    public void setCantDescStatusResultado(String cantDescStatusResultado) {
        this.cantDescStatusResultado = cantDescStatusResultado;
    }

    public String getDescripcionStatusResultado() {
        return descripcionStatusResultado;
    }

    public void setDescripcionStatusResultado(String descripcionStatusResultado) {
        this.descripcionStatusResultado = descripcionStatusResultado;
    }

    public Map<String, DetalleDocumentoDTO> getDetalleDocumento() {
        return detalleDocumento;
    }

    public void setDetalleDocumento(Map<String, DetalleDocumentoDTO> detalleDocumento) {
        this.detalleDocumento = detalleDocumento;
    }

    public ErrorCompraVentaEnum getError() {
        return error;
    }

    public void setError(ErrorCompraVentaEnum error) {
        this.error = error;
    }

    public Boolean getTieneError() {
        return tieneError;
    }

    public void setTieneError(Boolean tieneError) {
        this.tieneError = tieneError;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(cantDescStatusResultado);
        hcb.append(descripcionStatusResultado);
        hcb.append(detalleDocumento);
        hcb.append(idSistema);
        hcb.append(marcaDocumentoPpal);
        hcb.append(numeroDocumento);
        hcb.append(tipoDocumento);
        hcb.append(error);
        hcb.append(tieneError);
        return hcb.toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NupDTO other = (NupDTO) obj;
        return new EqualsBuilder().append(cantDescStatusResultado, other.cantDescStatusResultado)
                .append(descripcionStatusResultado, other.descripcionStatusResultado)
                .append(detalleDocumento, other.detalleDocumento).append(idSistema, other.idSistema)
                .append(marcaDocumentoPpal, other.marcaDocumentoPpal).append(numeroDocumento, other.numeroDocumento)
                .append(tipoDocumento, other.tipoDocumento).append(error, other.error)
                .append(tieneError, other.tieneError).isEquals();
    }

    @Override
    public String toString() {
        return "NupDTO{tipoDocumento='" + tipoDocumento + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", marcaDocumentoPpal='" + marcaDocumentoPpal + '\'' +
                ", detalleDocumento=" + detalleDocumento +
                ", idSistema='" + idSistema + '\'' +
                ", cantDescStatusResultado='" + cantDescStatusResultado + '\'' +
                ", descripcionStatusResultado='" + descripcionStatusResultado + '\'' +
                ", error=" + error +
                ", tieneError=" + tieneError + '}';
    }

    /**
	 * Obtiene CUIT de la persona solicitando el tipo de documento en el
	 * siguiente orden de los argumentos recibidos o retorna cadena vacia.
	 *
	 * @param tiposDoc
	 *            the tipos doc
	 * @return CUIT/CUIL/CDI resultante (de acuerdo a los datos obtenidos desde
	 *         CNSDOCXNUP)
	 */
    public String getCuit(String... tiposDoc) {
        for (String tipoDoc : tiposDoc) {
            String nroDoc = getNroPorTipoDoc(tipoDoc);
            if (StringUtils.isNotEmpty(nroDoc)) {
                return nroDoc;
            }
        }
        return StringUtils.EMPTY;
    }

    /**
	 * Obtiene tipo de documento de la persona solicitando el tipo de documento en el
	 * siguiente orden de los argumentos recibidos o retorna cadena vacia.
	 *
	 * @param tiposDoc
	 *            the tipos doc
	 * @return CUIT/CUIL/CDI resultante (de acuerdo a los datos obtenidos desde
	 *         CNSDOCXNUP)
	 */
    public String getTipoDocumento(String... tiposDoc) {
        for (String tipoDoc : tiposDoc) {
            String nroDoc = getNroPorTipoDoc(tipoDoc);
            if (StringUtils.isNotEmpty(nroDoc)) {
                return tipoDoc;
            }
        }
        return StringUtils.EMPTY;
    }

    /**
	 * Filtra los documentos segun el tipo solicitado o vacio.
	 *
	 * @param tipoDoc
	 *            the tipo doc
	 * @return the nro por tipo doc
	 */
    private String getNroPorTipoDoc(String tipoDoc) {
        DetalleDocumentoDTO detalleDocumentoDTO = detalleDocumento.get(tipoDoc);
        if (detalleDocumentoDTO != null) {
            return detalleDocumentoDTO.getNroDocumento();
        }
        return StringUtils.EMPTY;
    }

//    public getDocumentByType(String documentType) {
//
//    }
}
