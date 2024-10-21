package ar.com.santanderrio.obp.servicios.prestamos.dto;

public class EncolamientoResponseDTO extends LiquidacionPrestamoBaseDTO {

	private String id;
	private String numeroPrestamo;
	
	public String getNumeroPrestamo() {
		return numeroPrestamo;
	}

	public void setNumeroPrestamo(String numeroPrestamo) {
		this.numeroPrestamo = numeroPrestamo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
