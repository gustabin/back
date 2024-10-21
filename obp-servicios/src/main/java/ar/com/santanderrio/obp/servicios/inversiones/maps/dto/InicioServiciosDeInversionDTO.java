package ar.com.santanderrio.obp.servicios.inversiones.maps.dto;

public class InicioServiciosDeInversionDTO {

	private boolean rtl;
	private boolean bpriv;

	public InicioServiciosDeInversionDTO(boolean rtl, boolean bpriv) {
		this.rtl = rtl;
		this.bpriv = bpriv;
	}

	public boolean isRtl() {
		return rtl;
	}

	public void setRtl(boolean rtl) {
		this.rtl = rtl;
	}

	public boolean isBpriv() {
		return bpriv;
	}

	public void setBpriv(boolean bpriv) {
		this.bpriv = bpriv;
	}

}
