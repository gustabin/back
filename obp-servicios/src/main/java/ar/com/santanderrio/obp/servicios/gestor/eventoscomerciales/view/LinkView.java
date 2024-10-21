/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class LinkView.
 *
 * @author florencia.n.martinez
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class LinkView {

    /** The url. */
    private String url;

    /** The tipo acceso. */
    private String tipoAcceso;
    
    private String dataUrl;

    /**
     * Instantiates a new link view.
     */
    public LinkView() {
        super();
    }

    /**
     * Instantiates a new link view.
     *
     * @param url
     *            the url
     * @param tipoAcceso
     *            the tipo acceso
     */
    public LinkView(String url, String tipoAcceso) {
        this.url = url;
        this.tipoAcceso = tipoAcceso;
    }

    /**
     * Es link externo.
     *
     * @return the boolean
     */
    public Boolean esLinkExterno() {
        if (StringUtils.equals(this.tipoAcceso, "E")) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
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
     *            the new url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Gets the tipo acceso.
     *
     * @return the tipo acceso
     */
    public String getTipoAcceso() {
        return tipoAcceso;
    }

    /**
     * Sets the tipo acceso.
     *
     * @param tipoAcceso
     *            the new tipo acceso
     */
    public void setTipoAcceso(String tipoAcceso) {
        this.tipoAcceso = tipoAcceso;
    }
        
    public String getDataUrl() {
		return dataUrl;
	}

	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	/**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(tipoAcceso);
        hcb.append(url);
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
        LinkView other = (LinkView) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(tipoAcceso, other.getTipoAcceso());
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
        return new ToStringBuilder(this).append("url", url).append("tipoAcceso", tipoAcceso).toString();
    }

}
