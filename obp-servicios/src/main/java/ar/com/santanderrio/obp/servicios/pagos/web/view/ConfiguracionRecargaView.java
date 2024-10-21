/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.web.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.base.web.view.View;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentaSeleccionView;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfiguracionRecargaView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include = Inclusion.NON_NULL)
public class ConfiguracionRecargaView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7363481196839411901L;

	/** The lista cuentas. */
	private List<CuentaSeleccionView> listaCuentas;

	/** The tipo nuevo pago. */
	private String tipoNuevoPago;

	/** The fecha. */
	private String fecha;

	/**
	 * Valores recarga celular: $20 - $30 - $40 - $50 - $60 - $70 - $80 - $90 -
	 * $100 - $150 - $200 Valores recarga SUBE: $50 - $100 - $150 - $200 - $250
	 * - $300.
	 */
	private List<MontoValorView> montosRecarga;

	/** The mensaje recarga sube. */
	private String mensajeRecargaSube;
	
	/** The alias numero celular. */
	private String aliasNumeroCelular;

	/**
	 * Instantiates a new configuracion recarga view.
	 */
	public ConfiguracionRecargaView() {
		this.fecha = ISBANStringUtils.formatearFecha(new Date());
	}

	/**
	 * Gets the lista cuentas.
	 *
	 * @return the lista cuentas
	 */
	public List<CuentaSeleccionView> getListaCuentas() {
		return listaCuentas;
	}

	/**
	 * Sets the lista cuentas.
	 *
	 * @param listaCuentas
	 *            the new lista cuentas
	 */
	public void setListaCuentas(List<CuentaSeleccionView> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

	/**
	 * Gets the tipo nuevo pago.
	 *
	 * @return the tipo nuevo pago
	 */
	public String getTipoNuevoPago() {
		return tipoNuevoPago;
	}

	/**
	 * Sets the tipo nuevo pago.
	 *
	 * @param tipoNuevoPago
	 *            the new tipo nuevo pago
	 */
	public void setTipoNuevoPago(String tipoNuevoPago) {
		this.tipoNuevoPago = tipoNuevoPago;
	}

	/**
	 * Gets the montos recarga.
	 *
	 * @return the montos recarga
	 */
	public List<MontoValorView> getMontosRecarga() {
		return montosRecarga;
	}

	/**
	 * Sets the montos recarga.
	 *
	 * @param montosRecarga
	 *            the new montos recarga
	 */
	public void setMontosRecarga(List<MontoValorView> montosRecarga) {
		this.montosRecarga = montosRecarga;
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
	 * Gets the mensaje recarga sube.
	 *
	 * @return the mensaje recarga sube
	 */
	public String getMensajeRecargaSube() {
		return mensajeRecargaSube;
	}

	/**
	 * Sets the mensaje recarga sube.
	 *
	 * @param mensajeRecargaSube
	 *            the new mensaje recarga sube
	 */
	public void setMensajeRecargaSube(String mensajeRecargaSube) {
		this.mensajeRecargaSube = mensajeRecargaSube;
	}

	/**
	 * Sets the montos recarga telefono.
	 */
	public void cargarMontosRecargaTelefono(String code) {

		if (code.equals("RENX")) {
			 cargarMontosRecargaTelefonoNextel();
		} else if (code.equals("QUAM")){
			cargarMontosRecargaTelefonoTuenti();
		} else if (code.equals("RECL")){
			cargarMontosRecargaTelefonoClaro();
		} else if (code.equals("REPE")){
			cargarMontosRecargaTelefonoPersonal();
		} else if (code.equals("REMO")){
			cargarMontosRecargaTelefonoMovistar();
		}
	}

	/**
	 * Cargar montos recarga telefono nextel.
	 */
	public void cargarMontosRecargaTelefonoNextel() {
		montosRecarga = new ArrayList<MontoValorView>();
		montosRecarga.add(new MontoValorView(BigDecimal.valueOf(20), "$ 20,00"));
		montosRecarga.add(new MontoValorView(BigDecimal.valueOf(30), "$ 30,00"));
		montosRecarga.add(new MontoValorView(BigDecimal.valueOf(40), "$ 40,00"));
		montosRecarga.add(new MontoValorView(BigDecimal.valueOf(50), "$ 50,00"));
		montosRecarga.add(new MontoValorView(BigDecimal.valueOf(60), "$ 60,00"));
		montosRecarga.add(new MontoValorView(BigDecimal.valueOf(70), "$ 70,00"));
		montosRecarga.add(new MontoValorView(BigDecimal.valueOf(80), "$ 80,00"));
		montosRecarga.add(new MontoValorView(BigDecimal.valueOf(90), "$ 90,00"));
		montosRecarga.add(new MontoValorView(BigDecimal.valueOf(100), "$ 100,00"));
		montosRecarga.add(new MontoValorView(BigDecimal.valueOf(150), "$ 150,00"));
		montosRecarga.add(new MontoValorView(BigDecimal.valueOf(200), "$ 200,00"));
	}
	/**
	 * Montos de recarga de telefono por proveedores
	 */
	public void cargarMontosRecargaTelefonoTuenti() {

		cargarMontosRecarga(Arrays.asList(20,50,80,100,150,200,300,400,500,600,645,650,670,700,800,900,1000,1140,1550,1650,1700,2500,3000,3500,4000,4500,4600,5000,5500,6000,6500,7000,7500,8000,8500,9000,9500));
	}

	public void cargarMontosRecargaTelefonoClaro() {

		cargarMontosRecarga(Arrays.asList(200,300,400,500,600,700,800,900,1000,1200,1500,2000,2500,3000,3500,4000,4500,5000,6000,7000,8000,10000,15000,20000,21000,35000,40000));
	}
	public void cargarMontosRecargaTelefonoMovistar() {

		cargarMontosRecarga(Arrays.asList(590,650,1120,1200,1280,1300,1590, 1890,1900,2070,2500,3000,3020,3800,4100,4800,5340,11400,17500,24000,34200));
	}

	public void cargarMontosRecargaTelefonoPersonal() {

		cargarMontosRecarga(Arrays.asList(100,1000,1500,2000,2500,3000,9000));
	}

	/**
	 * Sets the montos recarga sube.
	 */
	public void setMontosRecargaSube() {

		cargarMontosRecarga(Arrays.asList(500,600,700,800,900,1000,1100,1200,1300,1400,1500,2000,2500,3000,3500,4000,4500,5000,5500,6000,6500,7000,7500,8000,8500,9000));
	}

	public void cargarMontosRecarga(List<Integer> array) {

		montosRecarga = new ArrayList<MontoValorView>();

		for (Integer value : array) {

			montosRecarga.add(new MontoValorView(BigDecimal.valueOf(value), "$ " + value + ",00"));
		}
	}

	/**
	 * Sets the montos recarga transporte movi.
	 */
	public void setMontosRecargaTransporteMovi() {
		montosRecarga = new ArrayList<MontoValorView>();
		montosRecarga.add(new MontoValorView(BigDecimal.valueOf(50), "$ 50,00"));
		montosRecarga.add(new MontoValorView(BigDecimal.valueOf(100), "$ 100,00"));
		montosRecarga.add(new MontoValorView(BigDecimal.valueOf(1119.90), "$ 1119,90"));
		montosRecarga.add(new MontoValorView(BigDecimal.valueOf(2114.11), "$ 2114,11"));
	}

	/**
	 * Monto valido.
	 *
	 * @param monto
	 *            the monto
	 * @return true, if successful
	 */
	public boolean montoValido(String monto) {
		if (this.montosRecarga != null) {
			if (monto != null) {
				BigDecimal montoBD = new BigDecimal(monto.replace(",", "."));
				for (MontoValorView montoValorView : this.montosRecarga) {
					if (montoValorView.getId().equals(montoBD)) {
						return true;
					}
				}
			}
			return false;
		}
		return true;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(fecha);
		hcb.append(mensajeRecargaSube);
		hcb.append(montosRecarga);
		hcb.append(tipoNuevoPago);
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfiguracionRecargaView other = (ConfiguracionRecargaView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(fecha, other.getFecha());
		eb.append(mensajeRecargaSube, other.getMensajeRecargaSube());
		eb.append(montosRecarga, other.getMontosRecarga());
		eb.append(tipoNuevoPago, other.getTipoNuevoPago());
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
		return new ToStringBuilder(this).append("listaCuentas", listaCuentas).append("tipoNuevoPago", tipoNuevoPago)
				.append("fecha", fecha).append("montosRecarga", montosRecarga)
				.append("mensajeRecargaSube", mensajeRecargaSube).toString();
	}

	/**
	 * Gets the alias numero celular.
	 *
	 * @return the alias numero celular
	 */
	public String getAliasNumeroCelular() {
		return aliasNumeroCelular;
	}

	/**
	 * Sets the alias numero celular.
	 *
	 * @param aliasNumeroCelular the new alias numero celular
	 */
	public void setAliasNumeroCelular(String aliasNumeroCelular) {
		this.aliasNumeroCelular = aliasNumeroCelular;
	}
	
	

}
