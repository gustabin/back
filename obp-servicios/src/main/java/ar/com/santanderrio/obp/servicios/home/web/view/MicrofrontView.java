/*
 *
 */
package ar.com.santanderrio.obp.servicios.home.web.view;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The Class SeccionView.
 */
public class MicrofrontView {
    /**
     * The Name.
     */
    private String name;

    /**
     * The url.
     */
    private String url;

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Name.
     *
     * @param name the new Name
     */
    public void setName(String name) {
        this.name = name;
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
     * @param url the new url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public MicrofrontView(String name, String url) {
        this.name = name;
        this.url = url;
    }

    /**
     * To string.
     *
     * @return the string
     */
    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("Microfront name", name).append("Microfront url", url)
                .toString();
    }

}
