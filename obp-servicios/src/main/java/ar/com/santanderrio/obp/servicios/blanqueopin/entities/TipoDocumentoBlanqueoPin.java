package ar.com.santanderrio.obp.servicios.blanqueopin.entities;

import org.apache.commons.lang3.StringUtils;

public enum TipoDocumentoBlanqueoPin {

	DNI("N", "1"), CEDULA("I", "2"), PASAPORTE("P", "3"), LIBRETA_CIVICA("C", "4"), LIBRETA_ENROLAMIENTO("E",
			"5"), CUIT("6", "T"), DNI_EXTRANJERO("X", "1");

	private String datoIDECLTSDO180;
	private String datoParaBanelco;

	private TipoDocumentoBlanqueoPin(String datoIDECLTSDO180, String datoParaBanelco) {
		this.datoIDECLTSDO180 = datoIDECLTSDO180;
		this.datoParaBanelco = datoParaBanelco;
	}

	public String getDatoIDECLTSDO180() {
		return datoIDECLTSDO180;
	}

	public void setDatoIDECLTSDO180(String datoIDECLTSDO180) {
		this.datoIDECLTSDO180 = datoIDECLTSDO180;
	}

	public String getDatoParaBanelco() {
		return datoParaBanelco;
	}

	public void setDatoParaBanelco(String datoParaBanelco) {
		this.datoParaBanelco = datoParaBanelco;
	}

	public static String obtenerDatoParaBanelco(String datoIDECLTSDO180) {
		for (TipoDocumentoBlanqueoPin tipo : TipoDocumentoBlanqueoPin.values()) {
			if (StringUtils.equals(datoIDECLTSDO180, tipo.getDatoIDECLTSDO180())) {
				return tipo.getDatoParaBanelco();
			}
		}
		return null;
	}

}
