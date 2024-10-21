package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonProperty;

public class FeedbackMessage {
    @JsonProperty("channel")
    private String channel;
    @JsonProperty("nup")
    private String nup;
    @JsonProperty("section")
    private String section;
    @JsonProperty("action")
    private String action;
    @JsonProperty("offerId")
    private Long offerId;
    @JsonProperty("idContent")
    private Integer idContent;
    @JsonProperty("order")
    private Integer order;
    public FeedbackMessage(String channel, String nup, String section, String action, Long offerId, Integer idContent,Integer order) {
        this.channel = channel;
        this.nup = nup;
        this.section = section;
        this.action = action;
        this.offerId = offerId;
        this.idContent = idContent;
        this.order=order;
    }
    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getNup() {
        return nup;
    }

    public void setNup(String nup) {
        this.nup = nup;
    }

    public String getSection() {
        return section;
    }
    
    public void setSection(String section) {
        this.section = section;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Integer getIdContent() {
        return idContent;
    }

    public void setIdContent(Integer idContent) {
        this.idContent = idContent;
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(channel);
        hcb.append(nup);
        hcb.append(section);
        hcb.append(action);
        hcb.append(offerId);
        hcb.append(idContent);
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
        FeedbackMessage other = (FeedbackMessage) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(channel, other.getChannel());
        eb.append(nup, other.getNup());
        eb.append(section, other.getSection());
        eb.append(action, other.getAction());
        eb.append(offerId, other.getOfferId());
        eb.append(idContent, other.getIdContent());
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
        return new ToStringBuilder(this)
                .append("channel", channel)
                .append("nup", nup)
                .append("section", section)
                .append("action", action)
                .append("offerId", offerId)
                .append("idContent", idContent).toString();
    }
}
