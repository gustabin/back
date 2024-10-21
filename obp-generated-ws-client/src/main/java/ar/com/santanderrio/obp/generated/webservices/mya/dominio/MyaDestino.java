/**
 * 
 */
package ar.com.santanderrio.obp.generated.webservices.mya.dominio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author sergio.e.goldentair
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class MyaDestino {
    /** Destino tipo mail. **/
    public static final String MAIL = "MAIL";
    /** Destino tipo cel. **/
    public static final String CEL = "CEL";

    @XmlElement(name = "id")
    private String id;
    @XmlElement(name = "CodOperacion")
    private MyaCodOperacionEnum codigoOperacion;
    @XmlElement(name = "DestinoTipo")
    private String tipo;
    @XmlElement(name = "DestinoSecuencia")
    private String secuencia;
    @XmlElement(name = "DestinoDescripcion")
    private String descripcion;
    @XmlElement(name = "DestinoEmpresaCel")
    private String empresaCel;
    @XmlElement(name = "DestinoEstado")
    private String estado;
    @XmlElement(name = "DestinoValidado")
    private String destinoValidado;
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo
     *            the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the secuencia
     */
    public String getSecuencia() {
        return secuencia;
    }

    /**
     * @param secuencia
     *            the secuencia to set
     */
    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion
     *            the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the empresaCel
     */
    public String getEmpresaCel() {
        return empresaCel;
    }

    /**
     * @param empresaCel
     *            the empresaCel to set
     */
    public void setEmpresaCel(String empresaCel) {
        this.empresaCel = empresaCel;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado
     *            the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the codigoOperacion
     */
    public MyaCodOperacionEnum getCodigoOperacion() {
        return codigoOperacion;
    }

    /**
     * @param codigoOperacion
     *            the codigoOperacion to set
     */
    public void setCodigoOperacion(MyaCodOperacionEnum codigoOperacion) {
        this.codigoOperacion = codigoOperacion;
    }

    public String getDestinoValidado() {
		return destinoValidado;
	}

	public void setDestinoValidado(String destinoValidado) {
		this.destinoValidado = destinoValidado;
	}

	/*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(id);
        hcb.append(tipo);
        hcb.append(codigoOperacion);
        hcb.append(secuencia);
        hcb.append(descripcion);
        hcb.append(empresaCel);
        hcb.append(estado);
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

        MyaDestino other = (MyaDestino) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(id, other.getId());
        eb.append(tipo, other.getTipo());
        eb.append(codigoOperacion, other.getCodigoOperacion());
        eb.append(secuencia, other.getSecuencia());
        eb.append(descripcion, other.getDescripcion());
        eb.append(empresaCel, other.getEmpresaCel());
        eb.append(estado, other.getEstado());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(id).append(tipo).append(codigoOperacion).append(secuencia)
                .append(descripcion).append(empresaCel).append(estado).toString();
    }

}
