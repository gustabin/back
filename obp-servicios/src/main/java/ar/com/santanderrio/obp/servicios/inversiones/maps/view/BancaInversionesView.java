package ar.com.santanderrio.obp.servicios.inversiones.maps.view;

import javax.validation.constraints.NotNull;

public class BancaInversionesView {

	@NotNull
	private String banca;

	public String getBanca() {
		return banca;
	}

	public void setBanca(String banca) {
		this.banca = banca;
	}

}
