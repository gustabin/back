package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

public enum AtrasoDeudaPromesaPagoEnum {

	TRAMO_1("1"),
	TRAMO_2("2"),
	TRAMO_3("3");

	private String codigo;
	
	AtrasoDeudaPromesaPagoEnum(String codigo) {
		
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
	
	
}
