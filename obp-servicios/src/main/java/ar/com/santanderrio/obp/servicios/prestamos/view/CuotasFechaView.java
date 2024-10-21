/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class CuotasFechaView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include = Inclusion.NON_NULL)
public class CuotasFechaView {

	/** The fecha. */
	private String fecha;

	/** The cuotas. */
	private List<ProximaCuotaView> cuotas = new ArrayList<ProximaCuotaView>();

	/**
	 * Instantiates a new cuotas fecha view.
	 *
	 * @param fecha
	 *            the fecha
	 */
	public CuotasFechaView(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the new fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the cuotas.
	 *
	 * @return the cuotas
	 */
	public List<ProximaCuotaView> getCuotas() {
		return cuotas;
	}

	/**
	 * Sets the cuotas.
	 *
	 * @param cuotas
	 *            the new cuotas
	 */
	public void setCuotas(List<ProximaCuotaView> cuotas) {
		this.cuotas = cuotas;
	}

	/**
	 * Adds the cuota.
	 *
	 * @param cuota
	 *            the cuota
	 */
	public void addCuota(ProximaCuotaView cuota) {
		this.cuotas.add(cuota);
	}

}