package ar.com.santanderrio.obp.servicios.biocatch.dto;

import ar.com.santanderrio.obp.servicios.biocatch.model.ActivityName;
import ar.com.santanderrio.obp.servicios.biocatch.model.Brand;
import ar.com.santanderrio.obp.servicios.biocatch.model.PlatformType;

public class InitRequestDataDTO extends BaseRequestDataDTO {
    private ActivityName activityName;

    public InitRequestDataDTO(ActivityName activityName, String customerSessionId, String nup, String ip, String userAgent, PlatformType platformType, Brand brand) {
        super(customerSessionId, nup, ip, userAgent, platformType, brand);
        this.activityName = activityName;
    }

    public ActivityName getActivityName() {
        return activityName;
    }

    public void setActivityName(ActivityName activityName) {
        this.activityName = activityName;
    }

}
