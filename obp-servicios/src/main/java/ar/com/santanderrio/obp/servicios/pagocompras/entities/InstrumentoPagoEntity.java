/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagocompras.entities;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.beanio.annotation.Field;

import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;

/**
 * The Class InstrumentoPagoEntity.
 *
 * @author florencia.n.martinez
 */
public class InstrumentoPagoEntity implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The form pago. */
    @Field
    private String formPago;

    /** The cod moneda. */
    @Field
    private String codMoneda;

    /** The nro instru. */
    @Field
    private String nroInstru;

    /** The importe. */
    @Field
    private String importe;

    /** The fec vto insr. */
    @Field
    private String fecVtoInsr;

    /** The mar libre disp. */
    @Field
    private String marLibreDisp;

    public InstrumentoPagoEntity() {
        super();
    }
    
    public InstrumentoPagoEntity(String monto, DivisaEnum divisa, String cuentaCbu) {
        this.formPago = StringUtils.equals(DivisaEnum.PESO.getCodigo(), divisa.getCodigo()) ? "11" : "37";
        this.codMoneda = StringUtils.equals(DivisaEnum.PESO.getCodigo(), divisa.getCodigo()) ? "0" : "2";
        this.nroInstru = "0054" + this.codMoneda + "0" + StringUtils.substring(cuentaCbu, 0, 8) + "000"
                + StringUtils.substring(cuentaCbu, 8);
        this.importe = monto;
    }

    /**
     * Gets the form pago.
     *
     * @return the form pago
     */
    public String getFormPago() {
        return formPago;
    }

    /**
     * Sets the form pago.
     *
     * @param formPago
     *            the new form pago
     */
    public void setFormPago(String formPago) {
        this.formPago = formPago;
    }

    /**
     * Gets the cod moneda.
     *
     * @return the cod moneda
     */
    public String getCodMoneda() {
        return codMoneda;
    }

    /**
     * Sets the cod moneda.
     *
     * @param codMoneda
     *            the new cod moneda
     */
    public void setCodMoneda(String codMoneda) {
        this.codMoneda = codMoneda;
    }

    /**
     * Gets the nro instru.
     *
     * @return the nro instru
     */
    public String getNroInstru() {
        return nroInstru;
    }

    /**
     * Sets the nro instru.
     *
     * @param nroInstru
     *            the new nro instru
     */
    public void setNroInstru(String nroInstru) {
        this.nroInstru = nroInstru;
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
     * @param importe
     *            the new importe
     */
    public void setImporte(String importe) {
        this.importe = importe;
    }

    /**
     * Gets the fec vto insr.
     *
     * @return the fec vto insr
     */
    public String getFecVtoInsr() {
        return fecVtoInsr;
    }

    /**
     * Sets the fec vto insr.
     *
     * @param fecVtoInsr
     *            the new fec vto insr
     */
    public void setFecVtoInsr(String fecVtoInsr) {
        this.fecVtoInsr = fecVtoInsr;
    }

    /**
     * Gets the mar libre disp.
     *
     * @return the mar libre disp
     */
    public String getMarLibreDisp() {
        return marLibreDisp;
    }

    /**
     * Sets the mar libre disp.
     *
     * @param marLibreDisp
     *            the new mar libre disp
     */
    public void setMarLibreDisp(String marLibreDisp) {
        this.marLibreDisp = marLibreDisp;
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(codMoneda);
        hcb.append(fecVtoInsr);
        hcb.append(formPago);
        hcb.append(importe);
        hcb.append(marLibreDisp);
        hcb.append(nroInstru);
        return hcb.toHashCode();
    }

    /**
     * Equals.
     *
     * @param obj
     *            the obj
     * @return true, if successful
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        InstrumentoPagoEntity other = (InstrumentoPagoEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(codMoneda, other.getCodMoneda());
        eb.append(fecVtoInsr, other.getFecVtoInsr());
        eb.append(formPago, other.getFormPago());
        eb.append(importe, other.getImporte());
        eb.append(marLibreDisp, other.getMarLibreDisp());
        eb.append(nroInstru, other.getNroInstru());
        return eb.isEquals();
    }

    /**
     * To String.
     *
     * @return the string
     */
    @Override
    public String toString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return new ToStringBuilder(this).append("formPago", formPago).append("codMoneda", codMoneda)
                .append("nroInstru", nroInstru).append("importe", importe).append("fecVtoInsr", fecVtoInsr)
                .append("marLibreDisp", marLibreDisp).toString();
    }
}
