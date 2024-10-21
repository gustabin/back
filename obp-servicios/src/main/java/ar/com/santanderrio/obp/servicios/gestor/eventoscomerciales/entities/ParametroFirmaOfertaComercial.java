/*
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class ParametroFirmaOfertaComercial.
 *
 * @author florencia.n.martinez
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ParametroFirmaOfertaComercial {

	/** The cod evento. */
	@JsonProperty("cod_evento")
	private String codEvento;

	/** The nombre evento. */
	@JsonProperty("nombre_evento")
	private String nombreEvento;

	/** The nup. */
	@JsonProperty("nup")
	private String nup;

	/** The canal originante. */
	@JsonProperty("canal_originante")
	private String canalOriginante;

	/** The subcanal originante. */
	@JsonProperty("subcanal_originante")
	private String subcanalOriginante;

	/** The flag tbanco. */
	@JsonProperty("flag_tbanco")
	private String flagTbanco;

	/** The usuario. */
	@JsonProperty("usuario")
	private String usuario;

	/** The password. */
	@JsonProperty("password")
	private String password;

	/** The dispositivo. */
	@JsonProperty("dispositivo")
	private String dispositivo;

	/** The nro id cliente. */
	@JsonProperty("nro_id_cli")
	private String nroIdCliente;

	/** The tipo id cliente. */
	@JsonProperty("tipo_id_cli")
	private String tipoIdCliente;

	/** The tipo cliente. */
	@JsonProperty("tipo_cliente")
	private String tipoCliente;

	/** The fecha nacimiento. */
	@JsonProperty("fecha_nac")
	private String fechaNacimiento;

	/** The cant cuentas. */
	@JsonProperty("cant_cuentas")
	private String cantCuentas;

	/** The cant tarjetas. */
	@JsonProperty("cant_tarjetas")
	private String cantTarjetas;

	/** The tarjetas. */
	@JsonProperty("tarjetas")
	private List<TarjetaOfertaComercial> tarjetas;

	/** The cuentas. */
	@JsonProperty("cuentas")
	private List<CuentaOfertaComercial> cuentas;

	/**
	 * Instantiates a new parametro firma oferta comercial.
	 */
	public ParametroFirmaOfertaComercial() {
		super();
	}

	/**
	 * Instantiates a new parametro firma oferta comercial.
	 *
	 * @param cliente
	 *            the cliente
	 * @param dispositivo
	 *            the dispositivo
	 */
	public ParametroFirmaOfertaComercial(Cliente cliente, String dispositivo) {
		this.codEvento = "5";
		this.nombreEvento = "obtenerOfertasComerciales";
		this.nup = cliente.getNup();
		this.canalOriginante = "04";
		this.subcanalOriginante = "99";
		this.flagTbanco = "S";
		this.usuario = cliente.getUsuarioRacf();
		this.password = cliente.getPasswordRacf();
		this.dispositivo = dispositivo;
		this.nroIdCliente = cliente.getDni();
		this.tipoIdCliente = cliente.getTipoDocumento();
		this.tipoCliente = cliente.getTipoPersona();
		this.fechaNacimiento = cliente.getFechaNacimiento();
		this.tarjetas = obtenerTarjetasOfertaComercial(cliente.getCuentasTarjetaDeCredito());
		this.cantTarjetas = String.valueOf(this.tarjetas.size());
		this.cuentas = obtenerCuentasOfertaComercial(cliente.getCuentasMonetarias());
		this.cantCuentas = String.valueOf(this.cuentas.size());
	}

	/**
	 * Obtener tarjetas oferta comercial.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 */
	private List<TarjetaOfertaComercial> obtenerTarjetasOfertaComercial(List<Cuenta> cuentas) {
		ArrayList<TarjetaOfertaComercial> tarjetasOfertaComercial = new ArrayList<TarjetaOfertaComercial>();
		for (Cuenta cuenta : cuentas) {
			if (!StringUtils.equals("T", cuenta.getClaseCuenta())) {
				tarjetasOfertaComercial.add(new TarjetaOfertaComercial(cuenta));
			}
		}
		tarjetasOfertaComercial.trimToSize();
		return tarjetasOfertaComercial;
	}

	/**
	 * Obtener cuentas oferta comercial.
	 *
	 * @param cuentas
	 *            the cuentas
	 * @return the list
	 */
	private List<CuentaOfertaComercial> obtenerCuentasOfertaComercial(List<Cuenta> cuentas) {
		ArrayList<CuentaOfertaComercial> cuentasOfertaComercial = new ArrayList<CuentaOfertaComercial>();
		for (Cuenta cuenta : cuentas) {
			cuentasOfertaComercial.add(new CuentaOfertaComercial(cuenta));
			if (cuenta.esCuentaUnicaPesos()) {
				Cuenta cuentaClon = new Cuenta(cuenta, TipoCuenta.CUENTA_UNICA_DOLARES);
				cuentasOfertaComercial.add(new CuentaOfertaComercial(cuentaClon));
			} else {
				if (cuenta.esCuentaUnicaDolares()) {
					Cuenta cuentaClon = new Cuenta(cuenta, TipoCuenta.CUENTA_UNICA_PESOS);
					cuentasOfertaComercial.add(new CuentaOfertaComercial(cuentaClon));
				}
			}
		}
		cuentasOfertaComercial.trimToSize();
		return cuentasOfertaComercial;
	}

	/**
	 * Gets the cod evento.
	 *
	 * @return the codEvento
	 */
	public String getCodEvento() {
		return codEvento;
	}

	/**
	 * Sets the cod evento.
	 *
	 * @param codEvento
	 *            the codEvento to set
	 */
	public void setCodEvento(String codEvento) {
		this.codEvento = codEvento;
	}

	/**
	 * Gets the nombre evento.
	 *
	 * @return the nombreEvento
	 */
	public String getNombreEvento() {
		return nombreEvento;
	}

	/**
	 * Sets the nombre evento.
	 *
	 * @param nombreEvento
	 *            the nombreEvento to set
	 */
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

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
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the canal originante.
	 *
	 * @return the canalOriginante
	 */
	public String getCanalOriginante() {
		return canalOriginante;
	}

	/**
	 * Sets the canal originante.
	 *
	 * @param canalOriginante
	 *            the canalOriginante to set
	 */
	public void setCanalOriginante(String canalOriginante) {
		this.canalOriginante = canalOriginante;
	}

	/**
	 * Gets the subcanal originante.
	 *
	 * @return the subcanalOriginante
	 */
	public String getSubcanalOriginante() {
		return subcanalOriginante;
	}

	/**
	 * Sets the subcanal originante.
	 *
	 * @param subcanalOriginante
	 *            the subcanalOriginante to set
	 */
	public void setSubcanalOriginante(String subcanalOriginante) {
		this.subcanalOriginante = subcanalOriginante;
	}

	/**
	 * Gets the flag tbanco.
	 *
	 * @return the flagTbanco
	 */
	public String getFlagTbanco() {
		return flagTbanco;
	}

	/**
	 * Sets the flag tbanco.
	 *
	 * @param flagTbanco
	 *            the flagTbanco to set
	 */
	public void setFlagTbanco(String flagTbanco) {
		this.flagTbanco = flagTbanco;
	}

	/**
	 * Gets the usuario.
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Sets the usuario.
	 *
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the dispositivo.
	 *
	 * @return the dispositivo
	 */
	public String getDispositivo() {
		return dispositivo;
	}

	/**
	 * Sets the dispositivo.
	 *
	 * @param dispositivo
	 *            the dispositivo to set
	 */
	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	/**
	 * Gets the nro id cliente.
	 *
	 * @return the nroIdCliente
	 */
	public String getNroIdCliente() {
		return nroIdCliente;
	}

	/**
	 * Sets the nro id cliente.
	 *
	 * @param nroIdCliente
	 *            the nroIdCliente to set
	 */
	public void setNroIdCliente(String nroIdCliente) {
		this.nroIdCliente = nroIdCliente;
	}

	/**
	 * Gets the tipo id cliente.
	 *
	 * @return the tipoIdCliente
	 */
	public String getTipoIdCliente() {
		return tipoIdCliente;
	}

	/**
	 * Sets the tipo id cliente.
	 *
	 * @param tipoIdCliente
	 *            the tipoIdCliente to set
	 */
	public void setTipoIdCliente(String tipoIdCliente) {
		this.tipoIdCliente = tipoIdCliente;
	}

	/**
	 * Gets the tipo cliente.
	 *
	 * @return the tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * Sets the tipo cliente.
	 *
	 * @param tipoCliente
	 *            the tipoCliente to set
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento
	 *            the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Gets the cant cuentas.
	 *
	 * @return the cantCuentas
	 */
	public String getCantCuentas() {
		return cantCuentas;
	}

	/**
	 * Sets the cant cuentas.
	 *
	 * @param cantCuentas
	 *            the cantCuentas to set
	 */
	public void setCantCuentas(String cantCuentas) {
		this.cantCuentas = cantCuentas;
	}

	/**
	 * Gets the cant tarjetas.
	 *
	 * @return the cantTarjetas
	 */
	public String getCantTarjetas() {
		return cantTarjetas;
	}

	/**
	 * Sets the cant tarjetas.
	 *
	 * @param cantTarjetas
	 *            the cantTarjetas to set
	 */
	public void setCantTarjetas(String cantTarjetas) {
		this.cantTarjetas = cantTarjetas;
	}

	/**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<TarjetaOfertaComercial> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the tarjetas to set
	 */
	public void setTarjetas(List<TarjetaOfertaComercial> tarjetas) {
		this.tarjetas = tarjetas;
	}

	/**
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<CuentaOfertaComercial> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas
	 *            the cuentas to set
	 */
	public void setCuentas(List<CuentaOfertaComercial> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cantCuentas);
		hcb.append(cantTarjetas);
		hcb.append(dispositivo);
		hcb.append(fechaNacimiento);
		hcb.append(nroIdCliente);
		hcb.append(nup);
		hcb.append(password);
		hcb.append(tipoCliente);
		hcb.append(usuario);
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
		ParametroFirmaOfertaComercial other = (ParametroFirmaOfertaComercial) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cantCuentas, other.getCantCuentas());
		eb.append(cantTarjetas, other.getCantTarjetas());
		eb.append(dispositivo, other.getDispositivo());
		eb.append(fechaNacimiento, other.getFechaNacimiento());
		eb.append(nroIdCliente, other.getNroIdCliente());
		eb.append(nup, other.getNup());
		eb.append(password, other.getPassword());
		eb.append(tipoCliente, other.getTipoCliente());
		eb.append(usuario, other.getUsuario());
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
		return new ToStringBuilder(this).append("codEvento", codEvento).append("nombreEvento", nombreEvento)
				.append("nup", nup).append("canalOriginante", canalOriginante)
				.append("subcanalOriginante", subcanalOriginante).append("flagTbanco", flagTbanco)
				.append("usuario", usuario).append("password", password).append("dispositivo", dispositivo)
				.append("nroIdCliente", nroIdCliente).append("tipoIdCliente", tipoIdCliente)
				.append("tipoCliente", tipoCliente).append("fechaNacimiento", fechaNacimiento)
				.append("cantCuentas", cantCuentas).append("cantTarjetas", cantTarjetas).append("tarjetas", tarjetas)
				.append("cuentas", cuentas).toString();
	}

}
