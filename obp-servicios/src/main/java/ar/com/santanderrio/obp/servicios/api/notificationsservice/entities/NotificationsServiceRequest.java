package ar.com.santanderrio.obp.servicios.api.notificationsservice.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.codehaus.jackson.annotate.JsonProperty;

public class NotificationsServiceRequest {
    @JsonProperty("topic")
    private String topic;
    @JsonProperty("message")
    private Object message;

    public NotificationsServiceRequest(String topic, Object message) {
        this.topic = topic;
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    /**
     * HashCode.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(topic);
        hcb.append(message);
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
        NotificationsServiceRequest other = (NotificationsServiceRequest) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(topic, other.getTopic());
        eb.append(message, other.getMessage());
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
            .append("topic", topic)
            .append("message", message)
            .toString();
    }
}
