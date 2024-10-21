package ar.com.santanderrio.obp.servicios.prestamos.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.prestamos.dto.LiquidacionPrestamoBaseDTO;

@JsonSerialize(include = Inclusion.NON_NULL)
public class SolicitudPrestamoOutView {

	private LiquidacionPrestamoBaseDTO prestamo;

	private String estadoDesafio;

	public SolicitudPrestamoOutView() {
		super();
	}

	public SolicitudPrestamoOutView(LiquidacionPrestamoBaseDTO prestamo) {
		super();
		this.prestamo = prestamo;
	}

	public SolicitudPrestamoOutView(String estadoDesafio) {
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
