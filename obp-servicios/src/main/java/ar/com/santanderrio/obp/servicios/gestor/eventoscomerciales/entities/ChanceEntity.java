/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonProperty;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ChanceEntity.
 */
public class ChanceEntity {

	/** The nup. */
	@NotNull
	@JsonProperty("nup")
	private String nup;
	
	/** The cod evento. */
	@NotNull
	@JsonProperty("cod_evento")
	private String codEvento;

	/** The nombre evento. */
	@NotNull
	@JsonProperty("nombre_evento")
	private String nombreEvento;
	
	/** The id encuesta. */
	@NotNull
	@JsonProperty("dispositivo")
	private String dispositivo;
	
	/** The usuario. */
	@JsonProperty("usuario")
	private String usuario;
	
	/** The password. */
	@JsonProperty("password")
	private String password;
	
	/** The nro id cli. */
	@JsonProperty("nro_id_cli")
	private String nroIdCli;
	
	/** The tipo id cli. */
	@JsonProperty("tipo_id_cli")
	private String tipoIdCli;
	
	/** The tipo cliente. */
	@JsonProperty("tipo_cliente")
	private String tipoCliente;
	
	/** The fecha nacimiento. */
	@JsonProperty("fecha_nac")
	private String fechaNac;

	public ChanceEntity(String dispositivo, Cliente cliente) {
		this.nup = cliente.getNup();
		this.codEvento = "21";
		this.nombreEvento = "obtenerPremiaciones";
		this.dispositivo = dispositivo;
		this.nroIdCli = cliente.getDni();
		this.tipoIdCli = cliente.getTipoDocumento();
		this.tipoCliente = cliente.getTipoPersona();
		this.fechaNac = cliente.getFechaNacimiento();
	}

	/**
	 *Gets nup
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 *Sets nup
	 *
	 * @param the nup 
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 *Gets codEvento
	 *
	 * @return the codEvento
	 */
	public String getCodEvento() {
		return codEvento;
	}

	/**
	 *Sets codEvento
	 *
	 * @param the codEvento 
	 */
	public void setCodEvento(String codEvento) {
		this.codEvento = codEvento;
	}

	/**
	 *Gets nombreEvento
	 *
	 * @return the nombreEvento
	 */
	public String getNombreEvento() {
		return nombreEvento;
	}

	/**
	 *Sets nombreEvento
	 *
	 * @param the nombreEvento 
	 */
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}

	/**
	 *Gets dispositivo
	 *
	 * @return the dispositivo
	 */
	public String getDispositivo() {
		return dispositivo;
	}

	/**
	 *Sets dispositivo
	 *
	 * @param the dispositivo 
	 */
	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	/**
	 *Gets usuario
	 *
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 *Sets usuario
	 *
	 * @param the usuario 
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 *Gets password
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 *Sets password
	 *
	 * @param the password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 *Gets nroIdCli
	 *
	 * @return the nroIdCli
	 */
	public String getNroIdCli() {
		return nroIdCli;
	}

	/**
	 *Sets nroIdCli
	 *
	 * @param the nroIdCli 
	 */
	public void setNroIdCli(String nroIdCli) {
		this.nroIdCli = nroIdCli;
	}

	/**
	 *Gets tipoIdCli
	 *
	 * @return the tipoIdCli
	 */
	public String getTipoIdCli() {
		return tipoIdCli;
	}

	/**
	 *Sets tipoIdCli
	 *
	 * @param the tipoIdCli 
	 */
	public void setTipoIdCli(String tipoIdCli) {
		this.tipoIdCli = tipoIdCli;
	}

	/**
	 *Gets tipoCliente
	 *
	 * @return the tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 *Sets tipoCliente
	 *
	 * @param the tipoCliente 
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	/**
	 *Gets fechaNac
	 *
	 * @return the fechaNac
	 */
	public String getFechaNac() {
		return fechaNac;
	}

	/**
	 *Sets fechaNac
	 *
	 * @param the fechaNac 
	 */
	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("nup", nup).append("codEvento", codEvento).append("nombreEvento", nombreEvento)
				.append("dispositivo", dispositivo).append("nroIdCli", nroIdCli).append("tipoIdCli", tipoIdCli)
				.append("tipoCliente", tipoCliente).append("fechaNac", fechaNac).toString();
	}

}
