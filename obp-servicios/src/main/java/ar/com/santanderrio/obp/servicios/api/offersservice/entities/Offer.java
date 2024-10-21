package ar.com.santanderrio.obp.servicios.api.offersservice.entities;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Offer {
    private Long id;
    private String segmentationOrigin;
    private String channelName;
    private String section;
    private Integer order;
    private Campaign campaign;
    private CommercialAction commercialAction;
    private List<String> feedbackAvailable;
    private Map<String,String> customerVariableData;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSegmentationOrigin() {
        return segmentationOrigin;
    }
    public void setSegmentationOrigin(String segmentationOrigin) {
        this.segmentationOrigin = segmentationOrigin;
    }
    public String getChannelName() {
        return channelName;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    public String getSection() {
        return section;
    }
    public void setSection(String section) {
        this.section = section;
    }
    public Integer getOrder() {
        return order;
    }
    public void setOrder(Integer order) {
        this.order = order;
    }
    public Campaign getCampaign() {
        return campaign;
    }
    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
    public CommercialAction getCommercialAction() {
        return commercialAction;
    }
    public void setCommercialAction(CommercialAction commercialAction) {
        this.commercialAction = commercialAction;
    }
    public List<String> getFeedbackAvailable() {
        return feedbackAvailable;
    }
    public void setFeedbackAvailable(List<String> feedbackAvailable) {
        this.feedbackAvailable = feedbackAvailable;
    }
    public Map<String, String> getCustomerVariableData() {
        return customerVariableData;
    }
    public void setCustomerVariableData(Map<String, String> customerVariableData) {
        this.customerVariableData = customerVariableData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;

        Offer that = (Offer) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(segmentationOrigin, that.segmentationOrigin)
                .append(channelName, that.channelName)
                .append(section, that.section)
                .append(order, that.order)
                .append(campaign, that.campaign)
                .append(commercialAction, that.commercialAction)
                .append(feedbackAvailable, that.feedbackAvailable)
                .append(customerVariableData, that.customerVariableData)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(segmentationOrigin)
                .append(channelName)
                .append(section)
                .append(order)
                .append(campaign)
                .append(commercialAction)
                .append(feedbackAvailable)
                .append(customerVariableData)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Offer{" +
            "id=" + id +
            ", segmentationOrigin='" + segmentationOrigin + '\'' +
            ", channelName='" + channelName + '\'' +
            ", section='" + section + '\'' +
            ", order=" + order +
            ", campaign=" + campaign +
            ", commercialAction=" + commercialAction +
            ", feedbackAvailable=" + feedbackAvailable +
            ", customerVariableData=" + customerVariableData +
            '}';
    }
}

