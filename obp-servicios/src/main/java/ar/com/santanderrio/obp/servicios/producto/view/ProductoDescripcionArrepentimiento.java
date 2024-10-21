package ar.com.santanderrio.obp.servicios.producto.view;

public class ProductoDescripcionArrepentimiento {

	private String producto;
	
	private Boolean tieneFlujoBaja;
	
	private String value;
	
	private String placeholder;
	
	private Boolean arrepentimientoGenerico;
	
	private String codOperacion;


	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public Boolean getTieneFlujoBaja() {
		return tieneFlujoBaja;
	}

	public void setTieneFlujoBaja(Boolean tieneFlujoBaja) {
		this.tieneFlujoBaja = tieneFlujoBaja;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public Boolean getArrepentimientoGenerico() {
		return arrepentimientoGenerico;
	}

	public void setArrepentimientoGenerico(Boolean arrepentimientoGenerico) {
		this.arrepentimientoGenerico = arrepentimientoGenerico;
	}

	public String getCodOperacion() {
		return codOperacion;
	}

	public void setCodOperacion(String codOperacion) {
		this.codOperacion = codOperacion;
	}
	
}
