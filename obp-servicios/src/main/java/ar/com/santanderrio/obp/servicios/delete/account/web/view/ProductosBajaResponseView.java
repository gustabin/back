package ar.com.santanderrio.obp.servicios.delete.account.web.view;

import java.util.List;

public class ProductosBajaResponseView {
	private List<ProductoBajaView> productos;

	public List<ProductoBajaView> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoBajaView> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "ProductosBajaResponseView [productos=" + productos + "]";
	}
}
