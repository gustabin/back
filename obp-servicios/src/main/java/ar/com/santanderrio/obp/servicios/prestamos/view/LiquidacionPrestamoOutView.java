package ar.com.santanderrio.obp.servicios.prestamos.view;

import ar.com.santanderrio.obp.servicios.prestamos.dto.LiquidacionPrestamoBaseDTO;

public class LiquidacionPrestamoOutView {

	private LiquidacionPrestamoBaseDTO prestamo;

	private String estadoDesafio;

	public LiquidacionPrestamoOutView() {
		super();
	}

	public LiquidacionPrestamoOutView(LiquidacionPrestamoBaseDTO prestamo) {
		super();
		this.prestamo = prestamo;
	}

	public LiquidacionPrestamoOutView(String estadoDesafio) {
		super();
		this.estadoDesafio = estadoDesafio;
	}

	public LiquidacionPrestamoBaseDTO getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(LiquidacionPrestamoBaseDTO prestamo) {
		this.prestamo = prestamo;
	}

	public String getEstadoDesafio() {
		return estadoDesafio;
	}

	public void setEstadoDesafio(String estadoDesafio) {
		this.estadoDesafio = estadoDesafio;
	}

}
