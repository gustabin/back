/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.entities;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dto.DatoClienteDebitoTarjetaInDTO;

/**
 * The Class ClienteDebitoTarjetaInEntity.
 *
 * @author florencia.n.martinez
 */
public class ClienteDebitoTarjetaInEntity {

	/** The Constant CERO_STRING. */
	private static final String CERO_STRING = "0";

	/** The cliente. */
	private Cliente cliente;

	/** The nro cuenta visa. */
	private String nroCuentaVisa;

	/** The nro tarjeta visa. */
	private String nroTarjetaVisa;

	/** The rubro. */
	private String rubro;

	/** The empresa servicio. */
	private String empresaServicio;

	/** The identificador. */
	private String identificador;

	/** The cod area telefono. */
	private String codAreaTelefono;

	/** The nro telefono. */
	private String nroTelefono;

	/** The monto maximo. */
	private String montoMaximo;

	/** The email. */
	private String email;

	/**
	 * Instantiates a new cliente debito tarjeta in entity.
	 */
	public ClienteDebitoTarjetaInEntity() {
		super();
	}

	/**
	 * Inicializa el objeto ClienteDebitoTarjetaInEntity para la llamada al
	 * store procedure del debito automatico en tarjeta de credito.
	 *
	 * @param cliente
	 *            the cliente
	 * @param inDTO
	 *            the dto
	 */
	public ClienteDebitoTarjetaInEntity(Cliente cliente, DatoClienteDebitoTarjetaInDTO inDTO) {
		this.cliente = cliente;
		this.nroCuentaVisa = inDTO.getNroCuentaVisa();
		this.nroTarjetaVisa = inDTO.getNroTarjetaVisa();
		this.rubro = StringUtils.EMPTY;
		this.empresaServicio = inDTO.getEmpresaServicio();
		this.identificador = inDTO.getIdentificador();
		this.codAreaTelefono = CERO_STRING;
		this.nroTelefono = CERO_STRING;
		this.montoMaximo = CERO_STRING;
		this.email = StringUtils.EMPTY;
	}

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the nro cuenta visa.
	 *
	 * @return the nroCuentaVisa
	 */
	public String getNroCuentaVisa() {
		return nroCuentaVisa;
	}

	/**
	 * Sets the nro cuenta visa.
	 *
	 * @param nroCuentaVisa
	 *            the nroCuentaVisa to set
	 */
	public void setNroCuentaVisa(String nroCuentaVisa) {
		this.nroCuentaVisa = nroCuentaVisa;
	}

	/**
	 * Gets the nro tarjeta visa.
	 *
	 * @return the nroTarjetaVisa
	 */
	public String getNroTarjetaVisa() {
		return nroTarjetaVisa;
	}

	/**
	 * Sets the nro tarjeta visa.
	 *
	 * @param nroTarjetaVisa
	 *            the nroTarjetaVisa to set
	 */
	public void setNroTarjetaVisa(String nroTarjetaVisa) {
		this.nroTarjetaVisa = nroTarjetaVisa;
	}

	/**
	 * Gets the rubro.
	 *
	 * @return the rubro
	 */
	public String getRubro() {
		return rubro;
	}

	/**
	 * Sets the rubro.
	 *
	 * @param rubro
	 *            the rubro to set
	 */
	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	/**
	 * Gets the empresa servicio.
	 *
	 * @return the empresaServicio
	 */
	public String getEmpresaServicio() {
		return empresaServicio;
	}

	/**
	 * Sets the empresa servicio.
	 *
	 * @param empresaServicio
	 *            the empresaServicio to set
	 */
	public void setEmpresaServicio(String empresaServicio) {
		this.empresaServicio = empresaServicio;
	}

	/**
	 * Gets the identificador.
	 *
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * Sets the identificador.
	 *
	 * @param identificador
	 *            the identificador to set
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	/**
	 * Gets the cod area telefono.
	 *
	 * @return the codAreaTelefono
	 */
	public String getCodAreaTelefono() {
		return codAreaTelefono;
	}

	/**
	 * Sets the cod area telefono.
	 *
	 * @param codAreaTelefono
	 *            the codAreaTelefono to set
	 */
	public void setCodAreaTelefono(String codAreaTelefono) {
		this.codAreaTelefono = codAreaTelefono;
	}

	/**
	 * Gets the nro telefono.
	 *
	 * @return the nroTelefono
	 */
	public String getNroTelefono() {
		return nroTelefono;
	}

	/**
	 * Sets the nro telefono.
	 *
	 * @param nroTelefono
	 *            the nroTelefono to set
	 */
	public void setNroTelefono(String nroTelefono) {
		this.nroTelefono = nroTelefono;
	}

	/**
	 * Gets the monto maximo.
	 *
	 * @return the montoMaximo
	 */
	public String getMontoMaximo() {
		return montoMaximo;
	}

	/**
	 * Sets the monto maximo.
	 *
	 * @param montoMaximo
	 *            the montoMaximo to set
	 */
	public void setMontoMaximo(String montoMaximo) {
		this.montoMaximo = montoMaximo;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cliente);
		hcb.append(codAreaTelefono);
		hcb.append(email);
		hcb.append(empresaServicio);
		hcb.append(identificador);
		hcb.append(montoMaximo);
		hcb.append(nroCuentaVisa);
		hcb.append(nroTarjetaVisa);
		hcb.append(nroTelefono);
		hcb.append(rubro);
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
		ClienteDebitoTarjetaInEntity other = (ClienteDebitoTarjetaInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cliente, other.getCliente());
		eb.append(codAreaTelefono, other.getCodAreaTelefono());
		eb.append(email, other.getEmail());
		eb.append(empresaServicio, other.getEmpresaServicio());
		eb.append(identificador, other.getIdentificador());
		eb.append(montoMaximo, other.getMontoMaximo());
		eb.append(nroCuentaVisa, other.getNroCuentaVisa());
		eb.append(nroTarjetaVisa, other.getNroTarjetaVisa());
		eb.append(nroTelefono, other.getNroTelefono());
		eb.append(rubro, other.getRubro());
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
		return new ToStringBuilder(this).append("cliente", cliente).append("nroCuentaVisa", nroCuentaVisa)
				.append("nroTarjetaVisa", nroTarjetaVisa).append("rubro", rubro)
				.append("empresaServicio", empresaServicio).append("identificador", identificador)
				.append("codAreaTelefono", codAreaTelefono).append("nroTelefono", nroTelefono)
				.append("montoMaximo", montoMaximo).append("email", email).toString();
	}

}
