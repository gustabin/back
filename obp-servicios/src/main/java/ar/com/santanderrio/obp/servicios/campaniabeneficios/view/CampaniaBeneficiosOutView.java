package ar.com.santanderrio.obp.servicios.campaniabeneficios.view;

import java.util.ArrayList;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class CampaniaBeneficiosOutView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CampaniaBeneficiosOutView {

	/** The suscripto. */
	private String suscripto;

	/** Terminos y Condiciones. */
	private String legalTyC;
	/** Comisiones. */
	private String legalComisiones;

	/** The descripcion producto. */
	private String descripcionCampania;

	/** The servicios productos. */
	private ArrayList<ServiosCampaniaTextosView> serviciosCampania;
	
	/** The requisitos productos. */
	private ArrayList<ServiosCampaniaTextosView> requisitosCampania;

	/** The legales. */
	private String textoLegales;

	/**
	 * Gets the descripcion producto.
	 *
	 * @return the descripcion producto
	 */
	public String getDescripcionCampania() {
		return descripcionCampania;
	}

	/**
	 * Sets the descripcion producto.
	 *
	 * @param descripcionProducto the new descripcion producto
	 */
	public void setDescripcionCampania(String descripcionCampania) {
		this.descripcionCampania = descripcionCampania;
	}

	/**
	 * Gets the servicios productos.
	 *
	 * @return the servicios productos
	 */
	public ArrayList<ServiosCampaniaTextosView> getServiciosCampania() {
		return serviciosCampania;
	}

	/**
	 * Sets the servicios productos.
	 *
	 * @param serviciosProductos the new servicios productos
	 */
	public void setServiciosCampania(ArrayList<ServiosCampaniaTextosView> serviciosCampania) {
		this.serviciosCampania = serviciosCampania;
	}

	/**
	 * Gets the requisitos productos.
	 *
	 * @return the requisitos productos
	 */
	public ArrayList<ServiosCampaniaTextosView> getRequisitosCampania() {
		return requisitosCampania;
	}

	/**
	 * Sets the requisitos productos.
	 *
	 * @param serviciosProductos the new requisitos productos
	 */
	public void setRequisitosCampania(ArrayList<ServiosCampaniaTextosView> requisitosCampania) {
		this.requisitosCampania = requisitosCampania;
	}
	
	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getTextoLegales() {
		return textoLegales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales the new legales
	 */
	public void setTextoLegales(String textoLegales) {
		this.textoLegales = textoLegales;
	}

	/**
	 * Gets the suscripto.
	 *
	 * @return the suscripto
	 */
	public String getSuscripto() {
		return suscripto;
	}

	/**
	 * Sets the suscripto.
	 *
	 * @param suscripto the new suscripto
	 */
	public void setSuscripto(String suscripto) {
		this.suscripto = suscripto;
	}

	/**
	 * Gets the legal ty C.
	 *
	 * @return the legalTyC
	 */
	public String getLegalTyC() {
		return legalTyC;
	}

	/**
	 * Sets the legal ty C.
	 *
	 * @param legalTyC the legalTyC to set
	 */
	public void setLegalTyC(String legalTyC) {
		this.legalTyC = legalTyC;
	}

	/**
	 * Gets the legal comisiones.
	 *
	 * @return the legalComisiones
	 */
	public String getLegalComisiones() {
		return legalComisiones;
	}

	/**
	 * Sets the legal comisiones.
	 *
	 * @param legalComisiones the legalComisiones to set
	 */
	public void setLegalComisiones(String legalComisiones) {
		this.legalComisiones = legalComisiones;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append(suscripto).append(legalTyC).append(legalComisiones).toString();
	}
}
