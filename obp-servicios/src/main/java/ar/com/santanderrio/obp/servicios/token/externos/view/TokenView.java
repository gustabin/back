/*
 * 
 */
package ar.com.santanderrio.obp.servicios.token.externos.view;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class TokenView.
 */
@XmlRootElement(name = "tokenView", namespace = "bean")
@XmlAccessorType(XmlAccessType.FIELD)
public class TokenView implements Serializable {
    /** The Serial UID. */
    private static final long serialVersionUID = 436006943905186932L;

    /** The token firmado. */
    private String tokenFirmado;

    /** The url. */
    private String url;

    /**
     * Gets the token firmado.
     *
     * @return the tokenFirmado
     */
    public String getTokenFirmado() {
        return tokenFirmado;
    }

    /**
     * Sets the token firmado.
     *
     * @param tokenFirmado
     *            the tokenFirmado to set
     */
    public void setTokenFirmado(String tokenFirmado) {
        this.tokenFirmado = tokenFirmado;
    }

    /**
     * Gets the url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the url.
     *
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(tokenFirmado);
        hcb.append(url);
        return hcb.toHashCode();
    }

    /**
     * Equaals.
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
        TokenView other = (TokenView) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(tokenFirmado, other.getTokenFirmado());
        eb.append(url, other.getUrl());
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
        return new ToStringBuilder(this).append("url", url).append("tokenFirmado", tokenFirmado).toString();
    }
}
