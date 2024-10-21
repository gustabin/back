package ar.com.santanderrio.obp.servicios.api.offersservice.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CommercialAction {
    private Integer id;
    private String title;
    private String message;
    private String callToAction;
    private Link link;
    private String image;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getCallToAction() {
        return callToAction;
    }
    public void setCallToAction(String callToAction) {
        this.callToAction = callToAction;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Link getLink() {
        return link;
    }
    public void setLink(Link link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommercialAction)) return false;

        CommercialAction that = (CommercialAction) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(title, that.title)
                .append(message, that.message)
                .append(callToAction, that.callToAction)
                .append(link, that.link)
                .append(image, that.image)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(title)
                .append(message)
                .append(callToAction)
                .append(link)
                .append(image)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "CommercialAction{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", message='" + message + '\'' +
            ", callToAction='" + callToAction + '\'' +
            ", link=" + link +
            ", image='" + image + '\'' +
            "}";
    }
}
