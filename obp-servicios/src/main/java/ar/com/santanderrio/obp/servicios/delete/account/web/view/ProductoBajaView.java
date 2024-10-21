package ar.com.santanderrio.obp.servicios.delete.account.web.view;

public class ProductoBajaView {

	private String descripcion;
	private String codigo;
	private String numero;
	private int cuenta;
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcionProducto) {
		this.descripcion = descripcionProducto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigoProducto) {
		this.codigo = codigoProducto;
	}

	public int getCuenta() {
		return cuenta;
	}

	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
