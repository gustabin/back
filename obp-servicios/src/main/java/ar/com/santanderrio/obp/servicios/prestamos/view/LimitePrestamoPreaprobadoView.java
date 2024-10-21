package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.math.BigDecimal;
import java.util.Date;

import ar.com.santanderrio.obp.servicios.prestamos.sei.RangoCuota;

public class LimitePrestamoPreaprobadoView extends LimitesPrestamoView {

	private static final long serialVersionUID = 2354899280416960376L;

	private Long id;
	private String tna;
	private BigDecimal entidad;
	private BigDecimal producto;
	private BigDecimal subProducto;
	
	public LimitePrestamoPreaprobadoView() {
	}

	public LimitePrestamoPreaprobadoView(Long id, String tipoTasa, RangoCuota rangoCuota, BigDecimal entidad, BigDecimal producto, BigDecimal subProducto, String tna) {
		super(tipoTasa, rangoCuota);
		this.id = id;
		this.tna = tna;
		this.entidad = entidad;
		this.producto = producto;
		this.subProducto = subProducto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getTna() {
		return tna;
	}

	public void setTna(String tna) {
		this.tna = tna;
	}

	public BigDecimal getEntidad() {
		return entidad;
	}

	public void setEntidad(BigDecimal entidad) {
		this.entidad = entidad;
	}

	public BigDecimal getProducto() {
		return producto;
	}

	public void setProducto(BigDecimal producto) {
		this.producto = producto;
	}

	public BigDecimal getSubProducto() {
		return subProducto;
	}

	public void setSubProducto(BigDecimal subProducto) {
		this.subProducto = subProducto;
	}

}
