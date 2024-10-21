package ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities;

public enum TipoPDFEnum {

	/** The peso. */
	CUENTAS_BANCA_PRIVADA("CUENTAS_BANCA_PRIVADA"),

	/** The dolar. */
	TITULOS_VALORES("TITULOS_VALORES"),
	
	FONDOS_COMUNES_INVERSION("FONDOS_COMUNES_INVERSION");
	

	/** The moneda. */
	private String titulo;
	
	
	TipoPDFEnum(String titulo) {
		this.titulo = titulo;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
