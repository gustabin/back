package ar.com.santanderrio.obp.servicios.segmento.dto;

public class SubscriptionResponseDTO {

	private int id;
	
	private int rewardedPercentage;
	
	private String rewardedUntil;
	
	private String schemaType;
	
	private String segment;
	
	private String status;
	
	private String suscribeDate;
	
	private Integer suscriberNup;
	
	private String unsuscribeDate;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRewardedPercentage() {
		return rewardedPercentage;
	}

	public void setRewardedPercentage(int rewardedPercentage) {
		this.rewardedPercentage = rewardedPercentage;
	}

	public String getRewardedUntil() {
		return rewardedUntil;
	}

	public void setRewardedUntil(String rewardedUntil) {
		this.rewardedUntil = rewardedUntil;
	}

	public String getSchemaType() {
		return schemaType;
	}

	public void setSchemaType(String schemaType) {
		this.schemaType = schemaType;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSuscribeDate() {
		return suscribeDate;
	}

	public void setSuscribeDate(String suscribeDate) {
		this.suscribeDate = suscribeDate;
	}

	public Integer getSuscriberNup() {
		return suscriberNup;
	}

	public void setSuscriberNup(Integer suscriberNup) {
		this.suscriberNup = suscriberNup;
	}

	public String getUnsuscribeDate() {
		return unsuscribeDate;
	}

	public void setUnsuscribeDate(String unsuscribeDate) {
		this.unsuscribeDate = unsuscribeDate;
	}
	
}
