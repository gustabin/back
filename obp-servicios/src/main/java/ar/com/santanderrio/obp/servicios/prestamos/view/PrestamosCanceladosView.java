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
import org.springframework.util.CollectionUtils;

import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoCanceladoDTO;

/**
 * The Class PrestamosCanceladosView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include = Inclusion.NON_NULL)
public class PrestamosCanceladosView {

	/** The personales. */
	private List<PrestamoView> personales;

	/** The hipotecarios. */
	private List<PrestamoView> hipotecarios;

	/** The prendarios. */
	private List<PrestamoView> prendarios;

	/**
	 * Gets the personales.
	 *
	 * @return the personales
	 */
	public List<PrestamoView> getPersonales() {
		return personales;
	}

	/**
	 * Sets the personales.
	 *
	 * @param personales
	 *            the new personales
	 */
	public void setPersonales(List<PrestamoView> personales) {
		this.personales = personales;
	}

	/**
	 * Gets the hipotecarios.
	 *
	 * @return the hipotecarios
	 */
	public List<PrestamoView> getHipotecarios() {
		return hipotecarios;
	}

	/**
	 * Sets the hipotecarios.
	 *
	 * @param hipotecarios
	 *            the new hipotecarios
	 */
	public void setHipotecarios(List<PrestamoView> hipotecarios) {
		this.hipotecarios = hipotecarios;
	}

	/**
	 * Gets the prendarios.
	 *
	 * @return the prendarios
	 */
	public List<PrestamoView> getPrendarios() {
		return prendarios;
	}

	/**
	 * Sets the prendarios.
	 *
	 * @param prendarios
	 *            the new prendarios
	 */
	public void setPrendarios(List<PrestamoView> prendarios) {
		this.prendarios = prendarios;
	}

	/**
	 * Agregar prestamo.
	 *
	 * @param prestamo
	 *            the prestamo
	 */
	public void agregarPrestamo(PrestamoCanceladoDTO prestamo) {
		if (TipoPrestamoEnum.HIPOTECARIOS.equals(prestamo.getTipoDePrestamo())) {
			agregarPrestamoHipotecario(prestamo);
		}

		if (TipoPrestamoEnum.PRENDARIO.equals(prestamo.getTipoDePrestamo())) {
			apreparPrestamoPrendario(prestamo);
		}

		if (TipoPrestamoEnum.PERSONAL.equals(prestamo.getTipoDePrestamo())) {
			agregarPrestamoPersonal(prestamo);
		}
	}

	/**
	 * Agregar prestamo personal.
	 *
	 * @param prestamo
	 *            the prestamo
	 */
	public void agregarPrestamoPersonal(PrestamoCanceladoDTO prestamo) {
		if (CollectionUtils.isEmpty(this.personales)) {
			this.personales = new ArrayList<PrestamoView>();
		}

		this.personales.add(new PrestamoView(prestamo));
	}

	/**
	 * Aprepar prestamo prendario.
	 *
	 * @param prestamo
	 *            the prestamo
	 */
	public void apreparPrestamoPrendario(PrestamoCanceladoDTO prestamo) {
		if (CollectionUtils.isEmpty(this.prendarios)) {
			this.prendarios = new ArrayList<PrestamoView>();
		}

		this.prendarios.add(new PrestamoView(prestamo));
	}

	/**
	 * Agregar prestamo hipotecario.
	 *
	 * @param prestamo
	 *            the prestamo
	 */
	public void agregarPrestamoHipotecario(PrestamoCanceladoDTO prestamo) {
		if (CollectionUtils.isEmpty(this.hipotecarios)) {
			this.hipotecarios = new ArrayList<PrestamoView>();
		}

		this.hipotecarios.add(new PrestamoView(prestamo));
	}

}
