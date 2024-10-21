/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author sergio.e.goldentair
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class MyaMensaje {
    @XmlAttribute(name = "id")
    private String idMensaje;
    @XmlElement(name = "NroMensaje")
    private String nroMensaje;
    @XmlElement(name = "NombreMensaje")
    private String nombreMensaje;
    @XmlElement(name = "Descripcion")
    private String descripcionMensaje;
    @XmlElement(name = "FrecuenciaDefault")
    private String frecuenciaMensaje;
    @XmlElement(name = "DAPDefault")
    private String dapDefault;
    @XmlElement(name = "DestinosPermitidos")
    private MyaDestinosPermitidos destinosPermitidos;
    @XmlElementWrapper(name = "ListaFrecPer")
    @XmlElement(name = "FrecuenciaPermitida")
    private List<MyaFrecPermitida> listMyaFrecPermitida;
    @XmlElementWrapper(name = "ListaSuscripciones")
    @XmlElement(name = "Suscripcion")
    private List<MyaSuscripcion> listMyaSuscripcion;
    @XmlElementWrapper(name = "ListaDAPPer")
    @XmlElement(name = "DAPPermitido")
    private List<MyaDAPPer> listMyaDapPer;
    @XmlElementWrapper(name = "ListaAtributosPer")
    @XmlElement(name = "Atributo")
    private List<MyaAtributosPer> listMyaAtributosPer;
    @XmlElement(name = "msgMultiple")
    private List<MyaMsgMultiple> msgMultiple;
    
    

    public List<MyaDAPPer> getListMyaDapPer() {
        return listMyaDapPer;
    }

    public void setListMyaDapPer(List<MyaDAPPer> listMyaDapPer) {
        this.listMyaDapPer = listMyaDapPer;
    }

    /**
     * @return the idMensaje
     */
    public String getIdMensaje() {
        return idMensaje;
    }

    /**
     * @param idMensaje
     *            the idMensaje to set
     */
    public void setIdMensaje(String idMensaje) {
        this.idMensaje = idMensaje;
    }

    /**
     * @return the nroMensaje
     */
    public String getNroMensaje() {
        return nroMensaje;
    }

    /**
     * @param nroMensaje
     *            the nroMensaje to set
     */
    public void setNroMensaje(String nroMensaje) {
        this.nroMensaje = nroMensaje;
    }

    /**
     * @return the nombreMensaje
     */
    public String getNombreMensaje() {
        return nombreMensaje;
    }

    /**
     * @param nombreMensaje
     *            the nombreMensaje to set
     */
    public void setNombreMensaje(String nombreMensaje) {
        this.nombreMensaje = nombreMensaje;
    }

    /**
     * @return the descripcionMensaje
     */
    public String getDescripcionMensaje() {
        return descripcionMensaje;
    }

    /**
     * @param descripcionMensaje
     *            the descripcionMensaje to set
     */
    public void setDescripcionMensaje(String descripcionMensaje) {
        this.descripcionMensaje = descripcionMensaje;
    }

    /**
     * @return the frecuenciaMensaje
     */
    public String getFrecuenciaMensaje() {
        return frecuenciaMensaje;
    }

    /**
     * @param frecuenciaMensaje
     *            the frecuenciaMensaje to set
     */
    public void setFrecuenciaMensaje(String frecuenciaMensaje) {
        this.frecuenciaMensaje = frecuenciaMensaje;
    }

    /**
     * @return the dapDefault
     */
    public String getDapDefault() {
        return dapDefault;
    }

    /**
     * @param dapDefault
     *            the dapDefault to set
     */
    public void setDapDefault(String dapDefault) {
        this.dapDefault = dapDefault;
    }

    /**
     * @return the destinosPermitidos
     */
    public MyaDestinosPermitidos getDestinosPermitidos() {
        return destinosPermitidos;
    }

    /**
     * @param destinosPermitidos
     *            the destinosPermitidos to set
     */
    public void setDestinosPermitidos(MyaDestinosPermitidos destinosPermitidos) {
        this.destinosPermitidos = destinosPermitidos;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(idMensaje);
        hcb.append(nroMensaje);
        return hcb.toHashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
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

        MyaMensaje other = (MyaMensaje) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(idMensaje, other.getIdMensaje());
        eb.append(nroMensaje, other.getNroMensaje());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(idMensaje).append(nroMensaje).toString();
    }

    /**
     * @return the listMyaSuscripcion
     */
    public List<MyaSuscripcion> getListMyaSuscripcion() {
        return listMyaSuscripcion;
    }

    /**
     * @param listMyaSuscripcion
     *            the listMyaSuscripcion to set
     */
    public void setListMyaSuscripcion(List<MyaSuscripcion> listMyaSuscripcion) {
        this.listMyaSuscripcion = listMyaSuscripcion;
    }

    /**
     * @return the listMyaFrecPermitida
     */
    public List<MyaFrecPermitida> getListMyaFrecPermitida() {
        return listMyaFrecPermitida;
    }

    /**
     * @param listMyaFrecPermitida
     *            the listMyaFrecPermitida to set
     */
    public void setListMyaFrecPermitida(List<MyaFrecPermitida> listMyaFrecPermitida) {
        this.listMyaFrecPermitida = listMyaFrecPermitida;
    }

    public List<MyaAtributosPer> getListMyaAtributosPer() {
        return listMyaAtributosPer;
    }

    public void setListMyaAtributosPer(List<MyaAtributosPer> listMyaAtributosPer) {
        this.listMyaAtributosPer = listMyaAtributosPer;
    }

    public List<MyaMsgMultiple> getMsgMultiple() {
        return msgMultiple;
    }

    public void setMsgMultiple(List<MyaMsgMultiple> msgMultiple) {
        this.msgMultiple = msgMultiple;
    }
   
}