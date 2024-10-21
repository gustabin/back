package ar.com.santanderrio.obp.servicios.blanqueopin.entities;

public enum BlanqueoPinEnum {

	NUMERICO ("numerico"), 
	
	ALFABETICO("alfabetico"), 
	
	AMBOS("ambos");
	
	
	BlanqueoPinEnum(String tipoBlanqueo) {
		this.tipoBlanqueo = tipoBlanqueo;
	}
	
	
	private String tipoBlanqueo;

	
	public String getTipoBlanqueo() {
		return tipoBlanqueo;
	}
	
	public static BlanqueoPinEnum buscarEnumPorTipo(String codigo) {
		BlanqueoPinEnum[] valores = BlanqueoPinEnum.values();

		BlanqueoPinEnum respuesta = null;

		for (BlanqueoPinEnum tipo : valores) {
			if (tipo.getTipoBlanqueo().equals(codigo)) {
				respuesta = tipo;
			}
		}
		return respuesta;
	}

}
