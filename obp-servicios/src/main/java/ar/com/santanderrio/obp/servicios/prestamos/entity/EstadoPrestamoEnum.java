package ar.com.santanderrio.obp.servicios.prestamos.entity;

public enum EstadoPrestamoEnum {
	
	ENQUEUE("Enqueue"), ENQUEUE_TIENDAS("EnqueueTiendas"), PAY_OFF("PayOff"), TO_PAY_OFF("ToPayOff"), CANCEL("Cancel");

	private String nombre;
	
	private EstadoPrestamoEnum(String nombre) {
		this.nombre = nombre;
	}
	
	public String toString() {
		return this.nombre;
	}
}
