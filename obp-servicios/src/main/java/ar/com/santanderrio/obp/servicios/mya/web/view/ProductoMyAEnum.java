/*
 * 
 */
package ar.com.santanderrio.obp.servicios.mya.web.view;

import org.apache.commons.lang3.StringUtils;

/**
 * Productos seleccionables en pantalla de inicio de suscripción a
 * notificaciones mensajes MyA.
 */
public enum ProductoMyAEnum {

	/** The cuentas. */
	CUENTAS("Cuentas", 1),
	/** The pagos transferencias. */
	PAGOS_TRANSFERENCIAS("Pagos y Transferencias", 5),
	/** The tarjetas. */
	TARJETAS("Tarjetas", 3),
	/** The sorpresa. */
	SORPRESA("Sorpresa", 15),
	/** The inversiones. */
	INVERSIONES("Inversiones", 2);

	/** The label. */
	private String label;

	/** The id. */
	private Integer id;

	/**
	 * Instantiates a new producto my A enum.
	 *
	 * @param label
	 *            the label
	 * @param id
	 *            the id
	 */
	private ProductoMyAEnum(String label, Integer id) {
		this.label = label;
		this.id = id;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Obtiene el ProductoMyAEnum correspondiente al label recibido.
	 *
	 * @param label
	 *            the label
	 * @return ProductoMyAEnum
	 */
	public static ProductoMyAEnum obtenerProductoMyAEnum(String label) {
		ProductoMyAEnum[] productos = ProductoMyAEnum.values();
		for (ProductoMyAEnum producto : productos) {
			if (StringUtils.equals(label, producto.getLabel())) {
				return producto;
			}
		}
		return null;
	}

}
