/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagoautomatico.view;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaTarjeta;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.tarjetas.util.TarjetaUtils;

/**
 * The Class AdhesionDebitoAutomaticoEnTarjetaView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonSerialize(include = Inclusion.NON_NULL)
public class AdhesionDebitoAutomaticoEnTarjetaView {

	/** The nombre empresa. */
	private String nombreEmpresa;

	/** The tarjetas. */
	private List<TarjetaAdhesionDebitoAutomaticoView> tarjetas;

	/** The identificacion. */
	private String identificacion;

	/** The gif factura. */
	private String gifFactura;

	/** The cuit empresa. */
	private String cuitEmpresa;

	/** The Constant VISA_HABILITADO. */
	private static final String VISA_HABILITADO = "S";
	
	private String mensajeInformacionPagoAdebitar;

	/**
	 * Instantiates a new adhesion debito automatico en tarjeta view.
	 */
	public AdhesionDebitoAutomaticoEnTarjetaView() {
		super();
	}

	/**
	 * Instantiates a new adhesion debito automatico en tarjeta view.
	 *
	 * @param empresa
	 *            the empresa
	 * @param cuentaTarjetas
	 *            the cuenta tarjetas
	 */
	public AdhesionDebitoAutomaticoEnTarjetaView(MedioPago empresa, List<Cuenta> cuentaTarjetas) {
		String cuit = empresa.getCuit();
		this.setCuitEmpresa(cuit.substring(0, 2) + "-" + cuit.substring(2, cuit.length() - 1) + "-"
				+ cuit.substring(cuit.length() - 1, cuit.length()));

		if (VISA_HABILITADO.equals(empresa.getVisaHabilitado())) {
			this.setGifFactura(empresa.getVisaGifFactura());
			this.setIdentificacion(empresa.getVisaIdentificador());
		} else {
			this.setGifFactura(empresa.getAmexGifFactura());
			this.setIdentificacion(empresa.getAmexIdentificador());
		}

		this.setNombreEmpresa(empresa.getNombreFantasia());
		List<TarjetaAdhesionDebitoAutomaticoView> listaTarjetas = crearListadoTarjetaView(cuentaTarjetas);
		this.setTarjetas(listaTarjetas);
	}

	/**
	 * Gets the nombre empresa.
	 *
	 * @return the nombre empresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * Sets the nombre empresa.
	 *
	 * @param nombreEmpresa
	 *            the new nombre empresa
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<TarjetaAdhesionDebitoAutomaticoView> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the new tarjetas
	 */
	public void setTarjetas(List<TarjetaAdhesionDebitoAutomaticoView> tarjetas) {
		this.tarjetas = tarjetas;
	}

	/**
	 * Gets the identificacion.
	 *
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * Sets the identificacion.
	 *
	 * @param identificacion
	 *            the new identificacion
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	/**
	 * Gets the gif factura.
	 *
	 * @return the gif factura
	 */
	public String getGifFactura() {
		return gifFactura;
	}

	/**
	 * Sets the gif factura.
	 *
	 * @param gifFactura
	 *            the new gif factura
	 */
	public void setGifFactura(String gifFactura) {
		this.gifFactura = gifFactura;
	}

	/**
	 * Gets the cuit empresa.
	 *
	 * @return the cuit empresa
	 */
	public String getCuitEmpresa() {
		return cuitEmpresa;
	}

	/**
	 * Sets the cuit empresa.
	 *
	 * @param cuitEmpresa
	 *            the new cuit empresa
	 */
	public void setCuitEmpresa(String cuitEmpresa) {
		this.cuitEmpresa = cuitEmpresa;
	}

	/**
	 * Crear listado tarjeta view.
	 *
	 * @param cuentaTarjetas
	 *            the cuenta tarjetas
	 * @return the list
	 */
	private List<TarjetaAdhesionDebitoAutomaticoView> crearListadoTarjetaView(List<Cuenta> cuentaTarjetas) {
		List<TarjetaAdhesionDebitoAutomaticoView> listaTarjetas = new ArrayList<TarjetaAdhesionDebitoAutomaticoView>();
		List<TarjetaAdhesionDebitoAutomaticoView> listaTarjetasAdicionales = new ArrayList<TarjetaAdhesionDebitoAutomaticoView>();

		for (Cuenta cuentaTarjeta : cuentaTarjetas) {

			TarjetaAdhesionDebitoAutomaticoView tarjetaView = armarNumeroTarjeta(cuentaTarjeta);
			if (TarjetaUtils.CODIGO_TITULARIDAD_TITULAR.equals(cuentaTarjeta.getCodigoTitularidad())) {
				listaTarjetas.add(tarjetaView);
			} else {
				listaTarjetasAdicionales.add(tarjetaView);
			}
		}
		listaTarjetas.addAll(listaTarjetasAdicionales);

		return listaTarjetas;
	}

	/**
	 * Armar numero tarjeta.
	 *
	 * @param cuentaTarjeta
	 *            the cuenta tarjeta
	 * @return the tarjeta adhesion debito automatico view
	 */
	private TarjetaAdhesionDebitoAutomaticoView armarNumeroTarjeta(Cuenta cuentaTarjeta) {
		TarjetaAdhesionDebitoAutomaticoView tarjetaView = new TarjetaAdhesionDebitoAutomaticoView();
		String numeroTarjeta = TarjetaUtils.crearMascaraNroTarjeta(cuentaTarjeta.getNroTarjetaCredito(),
				"Visa".equals(cuentaTarjeta.getTipoCuentaEnum().getAbreviatura()) ? TipoCuentaTarjeta.TIPOCTA_VISA
						: TipoCuentaTarjeta.TIPOCTA_AMEX);

		tarjetaView.setNumeroTarjeta(cuentaTarjeta.getTipoCuentaEnum().name() + " " + numeroTarjeta);
		if (cuentaTarjeta.getAlias() != null && !StringUtils.EMPTY.equals(cuentaTarjeta.getAlias())) {
			tarjetaView.setAlias(cuentaTarjeta.getTipoCuentaEnum().name() + " " + cuentaTarjeta.getAlias());
		}
		return tarjetaView;
	}

	public String getMensajeInformacionPagoAdebitar() {
		return mensajeInformacionPagoAdebitar;
	}

	public void setMensajeInformacionPagoAdebitar(String mensajeInformacionPagoAdebitar) {
		this.mensajeInformacionPagoAdebitar = mensajeInformacionPagoAdebitar;
	}

	
}
