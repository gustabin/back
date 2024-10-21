/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.web.view;

import java.util.List;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.dto.CompraVentaInicioDTO;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;

/**
 * The Class CompraVentaDolarView.
 *
 * @author florencia.n.martinez
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompraVentaDolarView {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CompraVentaDolarView.class);

	/** The cotizacion. */
	@Pattern(regexp = "[0-9]{2}.[0-9]{1,2}")
	private Double cotizacion;

	/** The cotizacion string. */
	@Pattern(regexp = "[0-9]{2},[0-9]{2}")
	private String cotizacionString;

	/** The cuentas origen. */
	// TODO Faltan validaciones de CuentaView
	private List<CuentasAdhesionDebitoView> cuentasOrigen;

	/** The cuentas destino. */
	// TODO Faltan validaciones de CuentaView
	private List<CuentasAdhesionDebitoView> cuentasDestino;

	/** The selected. */
	private int selected;

	/** The tipo operacion inicial. */
	private String tipoOperacionInicial = "compra";

	/** The w T. */
	private String wT;

	/** The n. */
	private String n;

	/**
	 * Instantiates a new compra venta dolar view.
	 */
	public CompraVentaDolarView() {
		super();
	}

	/**
	 * Instantiates a new compra venta dolar view.
	 *
	 * @param dto
	 *            the dto
	 * @param cuentasOrigenView
	 *            the cuentas origen view
	 * @param cuentasDestinoView
	 *            the cuentas destino view
	 * @throws CompraVentaDolaresException
	 *             the compra venta dolares exception
	 */
	public CompraVentaDolarView(CompraVentaInicioDTO dto, List<CuentasAdhesionDebitoView> cuentasOrigenView,
			List<CuentasAdhesionDebitoView> cuentasDestinoView) throws CompraVentaDolaresException {
		try {
			this.setCotizacion(dto.getCotizacion());
			this.setCotizacionString(dto.getCotizacionString());
			if (cuentasOrigenView != null) {
				this.setCuentasOrigen(cuentasOrigenView);
			}
			if (cuentasDestinoView != null) {
				this.setCuentasDestino(cuentasDestinoView);
			}
			if (dto.getTipoOperacionInicial() != null && dto.getTipoOperacionInicial().equals("V")) {
				this.setTipoOperacionInicial("venta");
			} else {
				this.setTipoOperacionInicial("compra");
			}
		} catch (Exception e) {
			LOGGER.error("Error en la conversion desde DTO a View.", e);
			throw new CompraVentaDolaresException(new Throwable());
		}
	}

	/**
	 * Instantiates a new compra venta dolar view.
	 *
	 * @param cotizacion
	 *            the cotizacion
	 */
	public CompraVentaDolarView(String cotizacion) {
		this.setCotizacion(null);
		this.setCotizacionString(cotizacion);
	}

	/**
	 * Gets the cotizacion.
	 *
	 * @return the cotizacion
	 */
	public Double getCotizacion() {
		return cotizacion;
	}

	/**
	 * Sets the cotizacion.
	 *
	 * @param cotizacion
	 *            the cotizacion to set
	 */
	public void setCotizacion(Double cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * Gets the cotizacion string.
	 *
	 * @return the cotizacionString
	 */
	public String getCotizacionString() {
		return cotizacionString;
	}

	/**
	 * Sets the cotizacion string.
	 *
	 * @param cotizacionString
	 *            the cotizacionString to set
	 */
	public void setCotizacionString(String cotizacionString) {
		this.cotizacionString = cotizacionString;
	}

	/**
	 * Gets the selected.
	 *
	 * @return the selected
	 */
	public int getSelected() {
		return selected;
	}

	/**
	 * Sets the selected.
	 *
	 * @param selected
	 *            the selected to set
	 */
	public void setSelected(int selected) {
		this.selected = selected;
	}

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<CuentasAdhesionDebitoView> getCuentasOrigen() {
		return cuentasOrigen;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentasOrigen
	 *            the new cuentas origen
	 */
	public void setCuentasOrigen(List<CuentasAdhesionDebitoView> cuentasOrigen) {
		this.cuentasOrigen = cuentasOrigen;
	}

	/**
	 * Gets the cuentas destino.
	 *
	 * @return the cuentas destino
	 */
	public List<CuentasAdhesionDebitoView> getCuentasDestino() {
		return cuentasDestino;
	}

	/**
	 * Sets the cuentas destino.
	 *
	 * @param cuentasDestino
	 *            the new cuentas destino
	 */
	public void setCuentasDestino(List<CuentasAdhesionDebitoView> cuentasDestino) {
		this.cuentasDestino = cuentasDestino;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cotizacion);
		hcb.append(cotizacionString);
		hcb.append(selected);
		hcb.append(wT);
		hcb.append(n);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (!this.getClass().equals(obj.getClass())) {
			return false;
		}
		CompraVentaDolarView other = (CompraVentaDolarView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cotizacion, other.cotizacion);
		eb.append(cotizacionString, other.getCotizacionString());
		eb.append(selected, other.getSelected());
		eb.append(wT, other.getwT());
		eb.append(n, other.getN());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("cotizacion", cotizacion).append(" cotizacionString", cotizacionString)
				.append(" cuentasOrigen", cuentasOrigen).append(" cuentasDestino", cuentasDestino)
				.append(" selected", selected).append(" wT", wT).append(" n", n).toString();
	}

	/**
	 * Gets the tipo operacion inicial.
	 *
	 * @return the tipo operacion inicial
	 */
	public String getTipoOperacionInicial() {
		return tipoOperacionInicial;
	}

	/**
	 * Sets the tipo operacion inicial.
	 *
	 * @param tipoOperacionInicial
	 *            the new tipo operacion inicial
	 */
	public void setTipoOperacionInicial(String tipoOperacionInicial) {
		this.tipoOperacionInicial = tipoOperacionInicial;
	}

	/**
	 * Gets the w T.
	 *
	 * @return the w T
	 */
	public String getwT() {
		return wT;
	}

	/**
	 * Sets the w T.
	 *
	 * @param wT the new w T
	 */
	public void setwT(String wT) {
		this.wT = wT;
	}

	/**
	 * Gets the n.
	 *
	 * @return the n
	 */
	public String getN() {
		return n;
	}

	/**
	 * Sets the n.
	 *
	 * @param n the new n
	 */
	public void setN(String n) {
		this.n = n;
	}

}