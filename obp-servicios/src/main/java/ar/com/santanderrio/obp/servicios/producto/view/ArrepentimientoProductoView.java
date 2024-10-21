package ar.com.santanderrio.obp.servicios.producto.view;

import java.util.List;

public class ArrepentimientoProductoView {

	private List<ProductoDescripcionArrepentimiento> listaProductosArrepentimiento;
	
	private List<ProductoDescripcionArrepentimiento> listaProductosBaja;
	
	private String legal;

	private String legalBaja;
	

	public List<ProductoDescripcionArrepentimiento> getListaProductosArrepentimiento() {
		return listaProductosArrepentimiento;
	}

	public void setListaProductosArrepentimiento(List<ProductoDescripcionArrepentimiento> listaProductosArrepentimiento) {
		this.listaProductosArrepentimiento = listaProductosArrepentimiento;
	}

	public List<ProductoDescripcionArrepentimiento> getListaProductosBaja() {
		return listaProductosBaja;
	}

	public void setListaProductosBaja(List<ProductoDescripcionArrepentimiento> listaProductosBaja) {
		this.listaProductosBaja = listaProductosBaja;
	}

	public String getLegal() {
		return legal;
	}

	public void setLegal(String legal) {
		this.legal = legal;
	}

	public String getLegalBaja() {
		return legalBaja;
	}

	public void setLegalBaja(String legalBaja) {
		this.legalBaja = legalBaja;
	}	
			
}
