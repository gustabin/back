package ar.com.santanderrio.obp.servicios.rsa.entities;

public class RsaUpdateUserRequestData extends RsaRequestData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8936803977772236680L;

	private String userStatus;
	
	/**
	 */
	public RsaUpdateUserRequestData(String userStatus) {
		super();
		this.userStatus = userStatus;
	}
	
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
}
