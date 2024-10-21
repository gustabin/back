package ar.com.santanderrio.obp.servicios.tarjetas.web.view;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * 
 * The Class TarjetasView.
 * 
 * @author florencia.n.martinez
 *
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class StackTarjetasView {
	/** The tarjetas. */
	private List<StackTarjetaView> tarjetas;

	/**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<StackTarjetaView> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the new tarjetas
	 */
	public void setTarjetas(List<StackTarjetaView> tarjetas) {
		this.tarjetas = tarjetas;
	}	
}
