/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosDetalleFondoCarteraRequestEntity.
 *
 * @author Miguel.Muller
 **/

public class DatosTenenciaValuadaCarteraRequest extends EntityBase {

	/** The nup. */
	@JsonProperty("Nup")
	private String nup;

	/** The CodigoProducto. */
	@JsonProperty("CodigoProducto")
	private String codigoProducto;

	/** Lista Cuentas RTL. */
	@JsonProperty("ListaCuentasRTL")
	private List<CuentaOPEntity> listaCuentasRTL = new ArrayList<CuentaOPEntity>();

	/** Lista Cuentas BP. */
	@JsonProperty("ListaCuentasBP")
	private List<CuentaOPEntity> listaCuentasBP = new ArrayList<CuentaOPEntity>();

	/** The datos servicios. */
	@JsonProperty("DatosServicios")
	private DatosServiciosEntity datosServicios;

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the codigoProducto.
	 *
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * Sets the codigoProducto.
	 *
	 * @param codigoProducto
	 *            the new codigo producto
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * Gets the ListaCuentasRTL.
	 *
	 * @return the ListaCuentasRTL
	 */
	public List<CuentaOPEntity> getListaCuentasRTL() {
		return listaCuentasRTL;
	}

	/**
	 * Sets the listaCuentasRTL.
	 *
	 * @param listaCuentasRTL
	 *            the new lista cuentas RTL
	 */
	public void setListaCuentasRTL(List<CuentaOPEntity> listaCuentasRTL) {
		this.listaCuentasRTL = listaCuentasRTL;
	}

	/**
	 * Gets the lista cuentas BP.
	 *
	 * @return the lista cuentas BP
	 */
	public List<CuentaOPEntity> getListaCuentasBP() {
		return listaCuentasBP;
	}

	/**
	 * Sets the listaCuentasBP.
	 *
	 * @param listaCuentasBP
	 *            the new lista cuentas BP
	 */
	public void setListaCuentasBP(List<CuentaOPEntity> listaCuentasBP) {
		this.listaCuentasBP = listaCuentasBP;
	}

	/**
	 * Gets the datos servicios.
	 *
	 * @return the datos servicios
	 */
	public DatosServiciosEntity getDatosServicios() {
		return datosServicios;
	}

	/**
	 * Sets the datos servicios.
	 *
	 * @param datosServicios
	 *            the new datos servicios
	 */
	public void setDatosServicios(DatosServiciosEntity datosServicios) {
		this.datosServicios = datosServicios;
	}

}
