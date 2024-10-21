package ar.com.santanderrio.obp.servicios.api.common.config.iatx;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IatxObpContext implements IatxContext {
    private String iatxUser;
    private String iatxPassword;

    @Value("${OBP.IATX.SEC.ID}")
    private String securityId;

    @Value("${OBP.IATX.CHANNEL.ID}")
    private String iatxChannelId;

    @Value("${OBP.IATX.CHANNEL.TYPE}")
    private String iatxChannelType;

    @Value("${OBP.IATX.SUBCHANNEL.ID}")
    private String subChannelId;

    @Value("${OBP.IATX.SUBCHANNEL.TYPE}")
    private String subChannelType;

    public String getSecurityId() {
        return securityId;
    }

    public String getIatxUser() {
        return iatxUser;
    }

    public void setIatxUser(String iatxUser) {
        this.iatxUser = iatxUser;
    }

    public String getIatxPassword() {
        return iatxPassword;
    }

    public void setIatxPassword(String iatxPassword) {
        this.iatxPassword = iatxPassword;
    }

    public String getIatxChannelId() {
        return iatxChannelId;
    }

    public String getIatxChannelType() {
        return iatxChannelType;
    }

    public String getSubChannelId() {
        return subChannelId;
    }

    public String getSubChannelType() {
        return subChannelType;
    }
}
