package ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto;

public class ProductoClienteEntity {

	private String codigoProducto;
	
	private String codigoSubproducto;
	
	private String nombreProducto;

	
	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getCodigoSubproducto() {
		return codigoSubproducto;
	}

	public void setCodigoSubproducto(String codigoSubproducto) {
		this.codigoSubproducto = codigoSubproducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
		
}
