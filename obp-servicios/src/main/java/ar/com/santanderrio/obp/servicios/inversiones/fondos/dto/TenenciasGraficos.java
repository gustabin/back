/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class TenenciasGraficos.
 */
public class TenenciasGraficos {

	/** The tenencia productos. */
	private List<TenenciaGrafico> tenenciaProductos = new ArrayList<TenenciaGrafico>();

	/** The total ars. */
	private double totalArs;

	/** The total usd. */
	private double totalUsd;
	
	/** The valor null. */
	private boolean valorNull;

	/**
	 * Gets the tenencia productos.
	 *
	 * @return the tenencia productos
	 */
	public List<TenenciaGrafico> getTenenciaProductos() {
		return tenenciaProductos;
	}

	/**
	 * Sets the tenencia productos.
	 *
	 * @param tenenciaProductos
	 *            the new tenencia productos
	 */
	public void setTenenciaProductos(List<TenenciaGrafico> tenenciaProductos) {
		this.tenenciaProductos = tenenciaProductos;
	}

	/**
	 * Gets the total ars.
	 *
	 * @return the total ars
	 */
	public double getTotalArs() {
		return totalArs;
	}

	/**
	 * Sets the total ars.
	 *
	 * @param totalArs
	 *            the new total ars
	 */
	public void setTotalArs(double totalArs) {
		this.totalArs = totalArs;
	}

	/**
	 * Gets the total usd.
	 *
	 * @return the total usd
	 */
	public double getTotalUsd() {
		return totalUsd;
	}

	/**
	 * Sets the total usd.
	 *
	 * @param totalUsd
	 *            the new total usd
	 */
	public void setTotalUsd(double totalUsd) {
		this.totalUsd = totalUsd;
	}

    /**
	 * Checks if is valor null.
	 *
	 * @return true, if is valor null
	 */
    public boolean isValorNull() {
        return valorNull;
    }

    /**
	 * Sets the valor null.
	 *
	 * @param valorNull
	 *            the new valor null
	 */
    public void setValorNull(boolean valorNull) {
        this.valorNull = valorNull;
    }

}
