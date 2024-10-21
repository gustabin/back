package ar.com.santanderrio.obp.servicios.echeq.common;

public enum TiposGrilla {
	
	EMITIDOS ("EMITIDOS"),
	ENDOSADOS ("ENDOSADOS"),
	RECIBIDOS ("RECIBIDOS"),
	CEDIDOS("CEDIDOS"),
	EN_PROCESO ("EN_PROCESO");
	
	private final String id;
	
	TiposGrilla(String id){
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
