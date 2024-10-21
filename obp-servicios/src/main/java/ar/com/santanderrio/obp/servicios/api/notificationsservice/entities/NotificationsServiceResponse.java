package ar.com.santanderrio.obp.servicios.api.notificationsservice.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import org.codehaus.jackson.annotate.JsonProperty;

public class NotificationsServiceResponse {
    @JsonProperty("topic")
    private String topic;
    @JsonProperty("cluster")
    private String cluster;
    @JsonProperty("crc32")
    private Long crc32;
    @JsonProperty("offset")
    private Long offset;
    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonProperty("partition")
    private Integer partition;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public Long getCrc32() {
        return crc32;
    }

    public void setCrc32(Long crc32) {
        this.crc32 = crc32;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getPartition() {
        return partition;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
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
        hcb.append(cluster);
        hcb.append(crc32);
        hcb.append(offset);
        hcb.append(timestamp);
        hcb.append(partition);
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
        NotificationsServiceResponse other = (NotificationsServiceResponse) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(topic, other.getTopic());
        eb.append(cluster, other.getCluster());
        eb.append(crc32, other.getCrc32());
        eb.append(offset, other.getOffset());
        eb.append(timestamp, other.getTimestamp());
        eb.append(partition, other.getPartition());
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
                .append("cluster", cluster)
                .append("crc32", crc32)
                .append("offset", offset)
                .append("timestamp", timestamp)
                .append("partition", partition).toString();
    }
}
