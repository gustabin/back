package ar.com.santanderrio.obp.servicios.api.transfers.recipients.model;

public class BaseRecipient {

    protected String email;

    protected String creationDate;

    protected String activationDate;

    protected String deletionDate;

    protected String channel;

    protected String subchannel;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(String activationDate) {
        this.activationDate = activationDate;
    }

    public String getDeletionDate() {
        return deletionDate;
    }

    public void setDeletionDate(String deletionDate) {
        this.deletionDate = deletionDate;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSubchannel() {
        return subchannel;
    }

    public void setSubchannel(String subchannel) {
        this.subchannel = subchannel;
    }

}
