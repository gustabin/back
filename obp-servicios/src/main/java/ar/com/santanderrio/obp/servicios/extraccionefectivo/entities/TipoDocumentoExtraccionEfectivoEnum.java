package ar.com.santanderrio.obp.servicios.extraccionefectivo.entities;

public enum TipoDocumentoExtraccionEfectivoEnum {

	DNI("N", "DNI"),

	LIBRETA_CIVICA("C", "LC"),

	LIBRETA_DE_ENROLAMIENTO("E", "LE"),

	CEDULA_DE_IDENTIDAD("I", "CI"),

	PASAPORTE("P", "PAS"),

	DNI_EXTRANJERO("X", "DNX");


	String campo;

	String descripcion;

	TipoDocumentoExtraccionEfectivoEnum(String campo, String descripcion) {
		this.campo = campo;
		this.descripcion = descripcion;
	}

	public String getCampo() {
		return campo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static TipoDocumentoExtraccionEfectivoEnum obtenerTipoDocumento(String campo) {
		TipoDocumentoExtraccionEfectivoEnum[] values = TipoDocumentoExtraccionEfectivoEnum.values();
		for (TipoDocumentoExtraccionEfectivoEnum tipoDocumento : values) {
			if (campo.trim().equalsIgnoreCase(tipoDocumento.getCampo())) {
				return tipoDocumento;
			}
		}
		return null;
	}
	
}
