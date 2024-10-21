package ar.com.santanderrio.obp.servicios.api.offersservice.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Link {
    private String goTo;
    private String deepLink;
    private String url;

    public String getGoTo() {
        return goTo;
    }
    public void setGoTo(String goTo) {
        this.goTo = goTo;
    }
    public String getDeepLink() {
        return deepLink;
    }
    public void setDeepLink(String deepLink) {
        this.deepLink = deepLink;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;

        Link that = (Link) o;
        return new EqualsBuilder()
                .append(goTo, that.goTo)
                .append(deepLink, that.deepLink)
                .append(url, that.url)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(goTo)
                .append(deepLink)
                .append(url)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Link{" +
            "goTo='" + goTo + '\'' +
            ", deepLink='" + deepLink + '\'' +
            ", url='" + url + '\'' +
            "}";
    }
}
