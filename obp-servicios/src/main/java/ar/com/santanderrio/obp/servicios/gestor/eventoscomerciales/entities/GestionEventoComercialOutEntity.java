/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class GestionEventoComercialOutEntity.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class GestionEventoComercialOutEntity {

    /** The nup. */
    @Pattern(regexp = "^\\d*$")
    @NotNull
    @JsonProperty("nup")
    private String nup;

    /** The cant notif sin leer. */
    @Pattern(regexp = "^\\d*$")
    @NotNull
    @JsonProperty("cant_notif_sin_leer")
    private String cantNotifSinLeer;

    /** The total notif. */
    @Pattern(regexp = "^\\d*$")
    @NotNull
    @JsonProperty("total_notif")
    private String totalNotif;

    /** The codigo error. */
    @Pattern(regexp = "ERROR000")
    @NotNull
    @JsonProperty("codigoError")
    private String codigoError;

    /** The descripcion error. */
    @JsonProperty("descripcionError")
    private String descripcionError;

    /** The notificaciones. */
    private List<NotificacionOutEntity> notificaciones;

    /**
     * Gets the nup.
     *
     * @return the nup
     */
    public String getNup() {
        return nup;
    }

    /**
     * Sets the nup.
     *
     * @param nup
     *            the new nup
     */
    public void setNup(String nup) {
        this.nup = nup;
    }

    /**
     * Gets the cant notif sin leer.
     *
     * @return the cant notif sin leer
     */
    public String getCantNotifSinLeer() {
        return cantNotifSinLeer;
    }

    /**
     * Sets the cant notif sin leer.
     *
     * @param cantNotifSinLeer
     *            the new cant notif sin leer
     */
    public void setCantNotifSinLeer(String cantNotifSinLeer) {
        this.cantNotifSinLeer = cantNotifSinLeer;
    }

    /**
     * Gets the total notif.
     *
     * @return the total notif
     */
    public String getTotalNotif() {
        return totalNotif;
    }

    /**
     * Sets the total notif.
     *
     * @param totalNotif
     *            the new total notif
     */
    public void setTotalNotif(String totalNotif) {
        this.totalNotif = totalNotif;
    }

    /**
     * Gets the codigo error.
     *
     * @return the codigo error
     */
    public String getCodigoError() {
        return codigoError;
    }

    /**
     * Sets the codigo error.
     *
     * @param codigoError
     *            the new codigo error
     */
    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    /**
     * Gets the descripcion error.
     *
     * @return the descripcion error
     */
    public String getDescripcionError() {
        return descripcionError;
    }

    /**
     * Sets the descripcion error.
     *
     * @param descripcionError
     *            the new descripcion error
     */
    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    /**
     * Gets the notificaciones.
     *
     * @return the notificaciones
     */
    public List<NotificacionOutEntity> getNotificaciones() {
        return notificaciones;
    }

    /**
     * Sets the notificaciones.
     *
     * @param notificaciones
     *            the new notificaciones
     */
    public void setNotificaciones(List<NotificacionOutEntity> notificaciones) {
        this.notificaciones = notificaciones;
    }

    /**
     * Tiene notificaciones.
     *
     * @return true, if successful
     */
    public boolean tieneNotificaciones() {
        return StringUtils.isNotBlank(this.totalNotif) && Integer.parseInt(this.totalNotif) > 0;
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(cantNotifSinLeer);
        hcb.append(codigoError);
        hcb.append(descripcionError);
        hcb.append(nup);
        hcb.append(totalNotif);
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GestionEventoComercialOutEntity other = (GestionEventoComercialOutEntity) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(cantNotifSinLeer, other.getCantNotifSinLeer());
        eb.append(codigoError, other.getCodigoError());
        eb.append(descripcionError, other.getDescripcionError());
        eb.append(nup, other.getNup());
        eb.append(totalNotif, other.getTotalNotif());
        return eb.isEquals();
    }

    /**
     * ToString.
     *
     * @return the string
     */
    @Override
    public String toString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return new ToStringBuilder(this).append("nup", nup).append("cantNotifSinLeer", cantNotifSinLeer)
                .append("totalNotif", totalNotif).append("codigoError", codigoError)
                .append("descripcionError", descripcionError).append("notificaciones", notificaciones).toString();
    }

    /**
     * Sin error de WS.
     *
     * @return true, if successful
     */
    public boolean sinErrorDeWS() {
        return "ERROR000".equals(this.codigoError);
    }

}
