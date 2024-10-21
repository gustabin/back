package ar.com.santanderrio.obp.servicios.home.entitites;

public enum TipoSubProductoEnum {
	BLACK("H","BLACK"),
	PLATINUM("L", "PLATINUM"),
	GOLD("P", "GOLD"),
	AMEX_MC_GOLD("O", "GOLD"),
	NORMAL("I", "CLASSIC");
	
	    
	private String tipoSubProducto;
	
	private String descripcion;
	
	TipoSubProductoEnum(String tipoSubProducto, String descripcion){
		this.tipoSubProducto = tipoSubProducto;
		this.descripcion = descripcion;
	}
	
	public String getTipoSubProducto() {
		return tipoSubProducto;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public static TipoSubProductoEnum fromCodigo(String codigo) {
		TipoSubProductoEnum[] values = TipoSubProductoEnum.values();

		TipoSubProductoEnum response = null;

		for (TipoSubProductoEnum tipoSubProducto : values) {
			if (tipoSubProducto.getTipoSubProducto().equals(codigo)) {
				response = tipoSubProducto;
			}
		}
		
		if (response == null) {
			response = TipoSubProductoEnum.NORMAL;
		}
		
		return response;
	}
}
