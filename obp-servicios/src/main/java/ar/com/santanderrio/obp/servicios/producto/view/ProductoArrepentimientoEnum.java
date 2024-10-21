package ar.com.santanderrio.obp.servicios.producto.view;

public enum ProductoArrepentimientoEnum {

	CUENTA("1", "110", "Cuentas", true, "cuenta", "Ej. nro. de tarjeta / póliza de seguro, nombre del titular, etc."),
	
	CUENTA_TITULOS("1", "112", "Cuentas Títulos", true, "titulos", "Ej. nro. de tarjeta / póliza de seguro, nombre del titular, etc."),
	
	PRESTAMO_PERSONAL("106", "106", "Préstamo Personal", false, "prestamoPersonalPreacordado", "Ej: monto del préstamo, año en que lo obtuviste, etc."),

	PRESTAMO_PERSONAL_PREACORDADO("106", "106", "Préstamo Personal Preacordado", false, "prestamoPersonalPreacordado", "Ej: monto del préstamo, año en que lo obtuviste, etc."),

	MICROCREDITOS_SIS("106", "106", "Microcréditos SIS", false, "prestamoPersonalPreacordado", "Ej: monto del préstamo, año en que lo obtuviste, etc."),

	PRESTAMO_HIPOTECARIO("115", "115", "Préstamo Hipotecario", false, "prestamoHipotecario", "Ej: monto del préstamo, año en que lo obtuviste, etc."),

	PRESTAMO_PRENDARIO("115", "115", "Préstamo Prendario", false, "prestamoPrendario", "Ej: monto del préstamo, año en que lo obtuviste, etc."),

	OPEN_CREDIT("115", "115", "Open Credit", false, "openCredit", "Ej: monto del préstamo, año en que lo obtuviste, etc."),
	
	SEGUROS("107", "107", "Seguros", false, "seguros", "Ej. nro. de tarjeta / póliza de seguro, nombre del titular, etc."),

	SUSCRIPCION_SORPRESA("108", "108", "Suscripción Sorpresa", false, "suscripcionSorpresa", "Ej. nro. de tarjeta / póliza de seguro, nombre del titular, etc."),
	
	SUSCRIPCION_WOMEN("109", "109", "Suscripción Women", false, "suscripcionWomen", "Ej. nro. de tarjeta / póliza de seguro, nombre del titular, etc."),
	
	TARJETA_CREDITO_ADICIONAL("2", "113", "Tarjeta de Crédito Adicional", true, "tarjetaCreditoAdicional", "Ej. nro. de tarjeta / póliza de seguro, nombre del titular, etc."),
	
	TARJETA_CREDITO_RECARGABLE("2", "111", "Tarjeta de Crédito Recargable", true, "tarjetaCreditoRecargable", "Ej. nro. de tarjeta / póliza de seguro, nombre del titular, etc."),
	
	CAJA_DE_SEGURIDAD("118", "118", "Caja de Seguridad", false, "cajaSeguridad", null);
	
	
	private String codOperacion;
	
	private String codOperacionArrepentimiento;
	
	private String producto;
	
	private Boolean tieneFlujoBaja;
	
	private String value;
	
	private String placeholder;
	
	ProductoArrepentimientoEnum(String codOperacion, String codOperacionArrepentimiento, String producto, Boolean tieneFlujoBaja, String value, String placeholder) {
		this.codOperacion = codOperacion;
		this.codOperacionArrepentimiento = codOperacionArrepentimiento;
		this.producto = producto;
		this.tieneFlujoBaja = tieneFlujoBaja;
		this.value = value;
		this.placeholder = placeholder;
	}


	public static ProductoArrepentimientoEnum fromProductoString(String nombre) {
		ProductoArrepentimientoEnum[] values = ProductoArrepentimientoEnum.values();

		ProductoArrepentimientoEnum response = null;

		for (ProductoArrepentimientoEnum producto : values) {
			if (producto.getProducto().equals(nombre)) {
				response = producto;
			}
		}
		return response;
	}
	
		
	public String getCodOperacion() {
		return codOperacion;
	}


	public String getProducto() {
		return producto;
	}


	public Boolean getTieneFlujoBaja() {
		return tieneFlujoBaja;
	}


	public String getValue() {
		return value;
	}


	public String getPlaceholder() {
		return placeholder;
	}


	public String getCodOperacionArrepentimiento() {
		return codOperacionArrepentimiento;
	}


	public void setCodOperacionArrepentimiento(String codOperacionArrepentimiento) {
		this.codOperacionArrepentimiento = codOperacionArrepentimiento;
	}
				
}
