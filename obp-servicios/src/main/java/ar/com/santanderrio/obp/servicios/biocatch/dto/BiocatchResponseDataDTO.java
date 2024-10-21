package ar.com.santanderrio.obp.servicios.biocatch.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BiocatchResponseDataDTO {

    private Integer score;
    private String policyAction;
    private Integer policyID;
    private String policyName;
    private Boolean isBot;
    private Boolean isEmulator;
    private Boolean isRat;
    private Boolean isRecentRat;
    private Boolean isMobileRat;
    private Integer voiceScam;
    private Integer max30DayScore;
    private List<String> riskFactor;
    private List<String> genFactor;
    public BiocatchResponseDataDTO() {
    }

    public BiocatchResponseDataDTO(Integer score, String policyAction, Integer policyID, String policyName, Boolean isBot, Boolean isEmulator, Boolean isRat, Boolean isRecentRat, Boolean isMobileRat, Integer voiceScam, Integer max30DayScore, List<String> riskFactor, List<String> genFactor) {
        this.score = score;
        this.policyAction = policyAction;
        this.policyID = policyID;
        this.policyName = policyName;
        this.isBot = isBot;
        this.isEmulator = isEmulator;
        this.isRat = isRat;
        this.isRecentRat = isRecentRat;
        this.isMobileRat = isMobileRat;
        this.voiceScam = voiceScam;
        this.max30DayScore = max30DayScore;
        this.riskFactor = riskFactor;
        this.genFactor = genFactor;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getPolicyAction() {
        return policyAction;
    }

    public void setPolicyAction(String policyAction) {
        this.policyAction = policyAction;
    }

    public Integer getPolicyID() {
        return policyID;
    }

    public void setPolicyID(Integer policyID) {
        this.policyID = policyID;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public Boolean isBot() {
        return isBot;
    }

    public void setIsBot(Boolean bot) {
        isBot = bot;
    }

    public Boolean isEmulator() {
        return isEmulator;
    }

    public void setIsEmulator(Boolean emulator) {
        isEmulator = emulator;
    }

    public Boolean isRat() {
        return isRat;
    }

    public void setIsRat(Boolean rat) {
        isRat = rat;
    }

    public Boolean isRecentRat() {
        return isRecentRat;
    }

    public void setIsRecentRat(Boolean recentRat) {
        isRecentRat = recentRat;
    }

    public Boolean isMobileRat() {
        return isMobileRat;
    }

    public void setIsMobileRat(Boolean mobileRat) {
        isMobileRat = mobileRat;
    }

    public Integer getVoiceScam() {
        return voiceScam;
    }

    public void setVoiceScam(Integer voiceScam) {
        this.voiceScam = voiceScam;
    }

    public Integer getMax30DayScore() {
        return max30DayScore;
    }

    public void setMax30DayScore(Integer max30DayScore) {
        this.max30DayScore = max30DayScore;
    }

    public List<String> getRiskFactor() {
        return riskFactor;
    }

    public void setRiskFactor(List<String> riskFactor) {
        this.riskFactor = riskFactor;
    }

    public List<String> getGenFactor() {
        return genFactor;
    }

    public void setGenFactor(List<String> genFactor) {
        this.genFactor = genFactor;
    }

    @Override
    public String toString() {
        return "BiocatchResponseDataDTO{" +
                "score=" + score +
                ", policyAction='" + policyAction + '\'' +
                ", policyID=" + policyID +
                ", policyName='" + policyName + '\'' +
                ", isBot=" + isBot +
                ", isEmulator=" + isEmulator +
                ", isRat=" + isRat +
                ", isRecentRat=" + isRecentRat +
                ", isMobileRat=" + isMobileRat +
                ", voiceScam=" + voiceScam +
                ", max30DayScore=" + max30DayScore +
                ", riskFactor=" + riskFactor +
                ", genFactor=" + genFactor +
                '}';
    }

}