/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.io.Serializable;
import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;

/**
 * The Class RecargaTarjetaInfoView.
 */
public class RecargaTarjetaInfoView implements Serializable {

	/** The importe minimo. */
	private String importeMinimo;

	/** The importe maximo. */
	private String importeMaximo;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The tarjetas recargables. */
	private List<TarjetaView> tarjetasRecargables;

	/** The CuentasAdhesionDebitoView. */
	private List<CuentasAdhesionDebitoView> cuentasAdhesionDebitoView;

	/**
	 * Instantiates a new pago tarjeta info view.
	 */
	public RecargaTarjetaInfoView() {
		super();
	}

	/**
	 * Gets the importe minimo.
	 *
	 * @return the importe minimo
	 */
	public String getImporteMinimo() {
		return importeMinimo;
	}

	/**
	 * Sets the importe minimo.
	 *
	 * @param importeMinimo
	 *            the new importe minimo
	 */
	public void setImporteMinimo(String importeMinimo) {
		this.importeMinimo = importeMinimo;
	}

	/**
	 * Gets the importe maximo.
	 *
	 * @return the importe maximo
	 */
	public String getImporteMaximo() {
		return importeMaximo;
	}

	/**
	 * Sets the importe maximo.
	 *
	 * @param importeMaximo
	 *            the new importe maximo
	 */
	public void setImporteMaximo(String importeMaximo) {
		this.importeMaximo = importeMaximo;
	}

	/**
	 * Gets the tarjetas recargables.
	 *
	 * @return the tarjetas recargables
	 */
	public List<TarjetaView> getTarjetasRecargables() {
		return tarjetasRecargables;
	}

	/**
	 * Sets the tarjetas recargables.
	 *
	 * @param tarjetasRecargables
	 *            the new tarjetas recargables
	 */
	public void setTarjetasRecargables(List<TarjetaView> tarjetasRecargables) {
		this.tarjetasRecargables = tarjetasRecargables;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Gets the cuentas adhesion debito view.
	 *
	 * @return the cuentas adhesion debito view
	 */
	public List<CuentasAdhesionDebitoView> getCuentasAdhesionDebitoView() {
		return cuentasAdhesionDebitoView;
	}

	/**
	 * Sets the cuentas adhesion debito view.
	 *
	 * @param cuentasAdhesionDebitoView
	 *            the new cuentas adhesion debito view
	 */
	public void setCuentasAdhesionDebitoView(List<CuentasAdhesionDebitoView> cuentasAdhesionDebitoView) {
		this.cuentasAdhesionDebitoView = cuentasAdhesionDebitoView;
	}

	/**
	 * Instantiates a new recarga tarjeta info view.
	 *
	 * @param importeMinimo
	 *            the importe minimo
	 * @param importeMaximo
	 *            the importe maximo
	 * @param tarjetasRecargables
	 *            the tarjetas recargables
	 * @param cuentasAdhesionDebitoView
	 *            the cuentas adhesion debito view
	 */
	public RecargaTarjetaInfoView(String importeMinimo, String importeMaximo, List<TarjetaView> tarjetasRecargables,
			List<CuentasAdhesionDebitoView> cuentasAdhesionDebitoView) {
		this.importeMinimo = importeMinimo;
		this.importeMaximo = importeMaximo;
		this.tarjetasRecargables = tarjetasRecargables;
		this.cuentasAdhesionDebitoView = cuentasAdhesionDebitoView;
	}

}