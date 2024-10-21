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
public class MyaSuscripcion {
    @XmlAttribute(name = "id")
    private String idSuscripcion;
    @XmlElement(name = "NroSuscripcion")
    private String nroSuscripcion;
    @XmlElement(name = "DestinoVinculado")
    private MyaDestinoVinculado destinoVinculado;
    @XmlElement(name = "FrecuenciaVinculada")
    private String frcVinculada;
    @XmlElement(name = "DAPVinculada")
    private String dapVinculada;
    @XmlElement(name = "CodOperacion")
    private MyaCodOperacionEnum codigoOperacion;
    @XmlElement(name = "NroMensaje")
    private String nroMensajeSuscripcion;
    @XmlElement(name = "CodigoFrecuencia")
    private String codFrecSuscripcion;
    @XmlElement(name = "CodigoDAP")
    private String codDapSuscripcion;
    @XmlElement(name = "Cuenta")
    private String cuentaSuscripcion;
    @XmlElement(name = "TipoCuenta")
    private String tipoCuentaSuscripcion;

    @XmlElementWrapper(name = "ListadoAlias")
    @XmlElement(name = "Alias")
    private List<MyaAlias> listMyaAliasSusc;
    
    @XmlElementWrapper(name = "AtributosVinculados")
    @XmlElement(name = "AttrVinc")
    private List<MyaAtributosVinculados> listMyaAtributosVinculados;
    
    @XmlElementWrapper(name = "MensajesMultiples")
    @XmlElement(name = "msgMultiple")
    private List<MyaMsgMultiple> msgMultiple;

    /**
     * @return the idSuscripcion
     */
    public String getIdSuscripcion() {
        return idSuscripcion;
    }

    /**
     * @param idSuscripcion
     *            the idSuscripcion to set
     */
    public void setIdSuscripcion(String idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    /**
     * @return the nroSuscripcion
     */
    public String getNroSuscripcion() {
        return nroSuscripcion;
    }

    /**
     * @param nroSuscripcion
     *            the nroSuscripcion to set
     */
    public void setNroSuscripcion(String nroSuscripcion) {
        this.nroSuscripcion = nroSuscripcion;
    }

    /**
     * @return the destinoVinculado
     */
    public MyaDestinoVinculado getDestinoVinculado() {
        return destinoVinculado;
    }

    /**
     * @param destinoVinculado
     *            the destinoVinculado to set
     */
    public void setDestinoVinculado(MyaDestinoVinculado destinoVinculado) {
        this.destinoVinculado = destinoVinculado;
    }

    /**
     * @return the frcVinculada
     */
    public String getFrcVinculada() {
        return frcVinculada;
    }

    /**
     * @param frcVinculada
     *            the frcVinculada to set
     */
    public void setFrcVinculada(String frcVinculada) {
        this.frcVinculada = frcVinculada;
    }

    /**
     * @return the dapVinculada
     */
    public String getDapVinculada() {
        return dapVinculada;
    }

    /**
     * @param dapVinculada
     *            the dapVinculada to set
     */
    public void setDapVinculada(String dapVinculada) {
        this.dapVinculada = dapVinculada;
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

    /**
     * @return the nroMensajeSuscripcion
     */
    public String getNroMensajeSuscripcion() {
        return nroMensajeSuscripcion;
    }

    /**
     * @param nroMensajeSuscripcion
     *            the nroMensajeSuscripcion to set
     */
    public void setNroMensajeSuscripcion(String nroMensajeSuscripcion) {
        this.nroMensajeSuscripcion = nroMensajeSuscripcion;
    }

    /**
     * @return the codFrecSuscripcion
     */
    public String getCodFrecSuscripcion() {
        return codFrecSuscripcion;
    }

    /**
     * @param codFrecSuscripcion
     *            the codFrecSuscripcion to set
     */
    public void setCodFrecSuscripcion(String codFrecSuscripcion) {
        this.codFrecSuscripcion = codFrecSuscripcion;
    }

    /**
     * @return the codDapSuscripcion
     */
    public String getCodDapSuscripcion() {
        return codDapSuscripcion;
    }

    /**
     * @param codDapSuscripcion
     *            the codDapSuscripcion to set
     */
    public void setCodDapSuscripcion(String codDapSuscripcion) {
        this.codDapSuscripcion = codDapSuscripcion;
    }

    /**
     * @return the cuentaSuscripcion
     */
    public String getCuentaSuscripcion() {
        return cuentaSuscripcion;
    }

    /**
     * @param cuentaSuscripcion
     *            the cuentaSuscripcion to set
     */
    public void setCuentaSuscripcion(String cuentaSuscripcion) {
        this.cuentaSuscripcion = cuentaSuscripcion;
    }

    /**
     * @return the tipoCuentaSuscripcion
     */
    public String getTipoCuentaSuscripcion() {
        return tipoCuentaSuscripcion;
    }

    /**
     * @param tipoCuentaSuscripcion
     *            the tipoCuentaSuscripcion to set
     */
    public void setTipoCuentaSuscripcion(String tipoCuentaSuscripcion) {
        this.tipoCuentaSuscripcion = tipoCuentaSuscripcion;
    }

    /**
     * @return the listMyaAliasSusc
     */
    public List<MyaAlias> getListMyaAliasSusc() {
        return listMyaAliasSusc;
    }

    /**
     * @param listMyaAliasSusc
     *            the listMyaAliasSusc to set
     */
    public void setListMyaAlias(List<MyaAlias> listMyaAliasSusc) {
        this.listMyaAliasSusc = listMyaAliasSusc;
    }
    
    public List<MyaAtributosVinculados> getListMyaAtributosVinculados() {
        return listMyaAtributosVinculados;
    }

    public void setListMyaAtributosVinculados(List<MyaAtributosVinculados> listMyaAtributosVinculados) {
        this.listMyaAtributosVinculados = listMyaAtributosVinculados;
    }

    public void setListMyaAliasSusc(List<MyaAlias> listMyaAliasSusc) {
        this.listMyaAliasSusc = listMyaAliasSusc;
    }

    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(idSuscripcion);
        hcb.append(nroSuscripcion);
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

        MyaSuscripcion other = (MyaSuscripcion) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(idSuscripcion, other.getIdSuscripcion());
        eb.append(nroSuscripcion, other.getNroSuscripcion());
        return eb.isEquals();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(idSuscripcion).append(nroSuscripcion).toString();
    }

	public List<MyaMsgMultiple> getMsgMultiple() {
		return msgMultiple;
	}

	public void setMsgMultiple(List<MyaMsgMultiple> msgMultiple) {
		this.msgMultiple = msgMultiple;
	}

}
