/**
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.entities;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.clave.online.view.DatosAutenticacion;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.TipoTeclado;

/**
 * The Class RegistroSesion.
 *
 * @author B039636
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RegistroSesion {

	/** The Constant DISPOSITIVO_MOBILE. */
	private static final String DISPOSITIVO_MOBILE = "phone";

	/** The id. */
	private String id;

	/** The token. */
	private String token;

	/** The fecha ultimo login. */
	private Date fechaUltimoLogin;

	/** The instancia. */
	private String instancia;

	/** The log file. */
	private String logFile;

	/** The tipo teclado. */
	private TipoTeclado tipoTeclado;

	/** The sinonimo. */
	private String sinonimo;

	/** The ip. */
	private String ip;

	/** The host name. */
	private String hostName;

	/** The nup. */
	private String nup = "";

	/** The dispositivo. */
	private String dispositivo = "";

	/** The navegador. */
	private String navegador = "";

	/** The pid. */
	private String pid;

	/** The invalidada. */
	private boolean invalidada = false;

	/** The fecha nacimiento. */
	private String fechaNacimiento;

	/** The clave. */
	private String clave;

	/** The usuario. */
	private String usuario;

	/** The dni. */
	private String dni;

	/** The clave nueva. */
	private String claveNueva;

	/** The usuario nuevo. */
	private String usuarioNuevo;

	/** The is alta. */
	private Boolean isAlta;

	/** The prefijo. */
	private String prefijo;

	/** The sufijo. */
	private String sufijo;

	/** The dni encriptado. */
	private String dniEncriptado;

	/** The cliente sinonimo. */
	private Boolean clienteSinonimo;

	/** The datos autenticacion. */
	private DatosAutenticacion datosAutenticacion;

	/** The clave nueva formateada. */
	private String claveNuevaFormateada;

	/**
	 * Checks if is mobile.
	 *
	 * @return true, if is mobile
	 */
	public boolean isMobile() {
		return DISPOSITIVO_MOBILE.equals(dispositivo);
	}

	/**
	 * Checks if is invalidada.
	 *
	 * @return the invalidada
	 */
	public boolean isInvalidada() {
		return invalidada;
	}

	/**
	 * Setter para invalidada.
	 *
	 * @param invalidada
	 *            the invalidada to set
	 */
	public void setInvalidada(boolean invalidada) {
		this.invalidada = invalidada;
	}

	/**
	 * Instantiates a new registro sesion.
	 */
	public RegistroSesion() {
		super();
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter para id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Setter para token.
	 *
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the instancia.
	 *
	 * @return the instancia
	 */
	public String getInstancia() {
		return instancia;
	}

	/**
	 * Setter para instancia.
	 *
	 * @param instancia
	 *            the instancia to set
	 */
	public void setInstancia(String instancia) {
		this.instancia = instancia;
	}

	/**
	 * Gets the fecha ultimo login.
	 *
	 * @return the fechaUltimoLogin
	 */
	public Date getFechaUltimoLogin() {
		Date vuelta = null;
		if (this.fechaUltimoLogin != null) {
			vuelta = new Date(this.fechaUltimoLogin.getTime());
		}
		return vuelta;
	}

	/**
	 * Setter para fecha ultimo login.
	 *
	 * @param fechaUltimoLogin
	 *            the fechaUltimoLogin to set
	 */
	public void setFechaUltimoLogin(Date fechaUltimoLogin) {
		Date inputFechaUltimoLogin = new Date(fechaUltimoLogin.getTime());
		this.fechaUltimoLogin = inputFechaUltimoLogin;
	}

	/**
	 * Gets the log file.
	 *
	 * @return the logFile
	 */
	public String getLogFile() {
		return logFile;
	}

	/**
	 * Setter para log file.
	 *
	 * @param logFile
	 *            the logFile to set
	 */
	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	/**
	 * Gets the teclado virtual.
	 *
	 * @return the tecladoVirtual
	 */
	public TipoTeclado getTipoTeclado() {
		return tipoTeclado;
	}

	/**
	 * Setter para teclado virtual.
	 *
	 * @param tipoTeclado
	 *            the new tipo teclado
	 */
	public void setTipoTeclado(TipoTeclado tipoTeclado) {
		this.tipoTeclado = tipoTeclado;
	}

	/**
	 * Gets the sinonimo.
	 *
	 * @return the sinonimo
	 */
	public String getSinonimo() {
		return sinonimo;
	}

	/**
	 * Setter para sinonimo.
	 *
	 * @param sinonimo
	 *            the sinonimo to set
	 */
	public void setSinonimo(String sinonimo) {
		this.sinonimo = sinonimo;
	}

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Setter para ip.
	 *
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Gets the host name.
	 *
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * Setter para host name.
	 *
	 * @param hostName
	 *            the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
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
	 * Setter para nup.
	 *
	 * @param nup
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
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
	 * Setter para dispositivo.
	 *
	 * @param dispositivo
	 *            the dispositivo to set
	 */
	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	/**
	 * Gets the navegador.
	 *
	 * @return the navegador
	 */
	public String getNavegador() {
		return navegador;
	}

	/**
	 * Setter para navegador.
	 *
	 * @param navegador
	 *            el nuevo navegador
	 */
	public void setNavegador(String navegador) {
		this.navegador = navegador;
	}

	/**
	 * Gets the pid.
	 *
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * Setter para pid.
	 *
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	/**
	 * Gets the clave.
	 *
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Sets the clave.
	 *
	 * @param clave
	 *            the new clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
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
	 *            the new usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Gets the usuario nuevo.
	 *
	 * @return the usuarioNuevo
	 */
	public String getUsuarioNuevo() {
		return usuarioNuevo;
	}

	/**
	 * Sets the usuario nuevo.
	 *
	 * @param usuarioNuevo
	 *            the usuarioNuevo to set
	 */
	public void setUsuarioNuevo(String usuarioNuevo) {
		this.usuarioNuevo = usuarioNuevo;
	}

	/**
	 * Gets the usuario vacio.
	 *
	 * @return the usuario vacio
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		final int booleanTrue = 1231;
		final int booleanFalse = 1237;
		result = prime * result + ((dispositivo == null) ? 0 : dispositivo.hashCode());
		result = prime * result + ((fechaUltimoLogin == null) ? 0 : fechaUltimoLogin.hashCode());
		result = prime * result + ((hostName == null) ? 0 : hostName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((instancia == null) ? 0 : instancia.hashCode());
		result = prime * result + (invalidada ? booleanTrue : booleanFalse);
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((logFile == null) ? 0 : logFile.hashCode());
		result = prime * result + ((navegador == null) ? 0 : navegador.hashCode());
		result = prime * result + ((nup == null) ? 0 : nup.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((sinonimo == null) ? 0 : sinonimo.hashCode());
		result = prime * result + ((tipoTeclado == null) ? 0 : tipoTeclado.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RegistroSesion other = (RegistroSesion) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (instancia == null) {
			if (other.instancia != null) {
				return false;
			}
		} else if (!instancia.equals(other.instancia)) {
			return false;
		}
		if (invalidada != other.invalidada) {
			return false;
		}
		if (ip == null) {
			if (other.ip != null) {
				return false;
			}
		} else if (!ip.equals(other.ip)) {
			return false;
		}
		if (nup == null) {
			if (other.nup != null) {
				return false;
			}
		} else if (!nup.equals(other.nup)) {
			return false;
		}
		if (pid == null) {
			if (other.pid != null) {
				return false;
			}
		} else if (!pid.equals(other.pid)) {
			return false;
		}
		return true;
	}

	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fecha nacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Sets the fecha nacimiento.
	 *
	 * @param fechaNacimiento
	 *            the new fecha nacimiento
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni
	 *            the new dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Gets the checks if is alta.
	 *
	 * @return the checks if is alta
	 */
	public Boolean getIsAlta() {
		return isAlta;
	}

	/**
	 * Sets the checks if is alta.
	 *
	 * @param isAlta
	 *            the new checks if is alta
	 */
	public void setIsAlta(Boolean isAlta) {
		this.isAlta = isAlta;
	}

	/**
	 * Gets the prefijo.
	 *
	 * @return the prefijo
	 */
	public String getPrefijo() {
		return prefijo;
	}

	/**
	 * Sets the prefijo.
	 *
	 * @param prefijo
	 *            the prefijo to set
	 */
	public void setPrefijo(String prefijo) {
		this.prefijo = prefijo;
	}

	/**
	 * Gets the sufijo.
	 *
	 * @return the sufijo
	 */
	public String getSufijo() {
		return sufijo;
	}

	/**
	 * Sets the sufijo.
	 *
	 * @param sufijo
	 *            the sufijo to set
	 */
	public void setSufijo(String sufijo) {
		this.sufijo = sufijo;
	}

	/**
	 * Gets the clave nueva.
	 *
	 * @return the claveNueva
	 */
	public String getClaveNueva() {
		return claveNueva;
	}

	/**
	 * Sets the clave nueva.
	 *
	 * @param claveNueva
	 *            the claveNueva to set
	 */
	public void setClaveNueva(String claveNueva) {
		this.claveNueva = claveNueva;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		appendToBuilderFirstDataSet(sb);
		sb.append(sinonimo != null ? "sinonimo=" + sinonimo + ", " : "").append(ip != null ? "ip=" + ip + ", " : "")
				.append(hostName != null ? "hostName=" + hostName + ", " : "")
				.append(nup != null ? "nup=" + nup + ", " : "")
				.append(dispositivo != null ? "dispositivo=" + dispositivo + ", " : "")
				.append(navegador != null ? "navegador=" + navegador + ", " : "")
				.append(pid != null ? "pid=" + pid + ", " : "").append("invalidada=" + invalidada + "]");

		return sb.toString();
	}

	/**
	 * Append to builder first data set.
	 *
	 * @param sb
	 *            the sb
	 */
	private void appendToBuilderFirstDataSet(StringBuilder sb) {
		sb.append("RegistroSesion [" + (id != null ? "id=" + id + ", " : ""))
				.append(token != null ? "token=" + token + ", " : "")
				.append(fechaUltimoLogin != null ? "fechaUltimoLogin=" + fechaUltimoLogin + ", " : "")
				.append(instancia != null ? "instancia=" + instancia + ", " : "")
				.append(logFile != null ? "logFile=" + logFile + ", " : "")
				.append(tipoTeclado != null ? "tecladoVirtual=" + tipoTeclado + ", " : "");
	}

	/**
	 * Gets the dni encriptado.
	 *
	 * @return the dni encriptado
	 */
	public String getDniEncriptado() {
		return dniEncriptado;
	}

	/**
	 * Sets the dni encriptado.
	 *
	 * @param dniEncriptado
	 *            the new dni encriptado
	 */
	public void setDniEncriptado(String dniEncriptado) {
		this.dniEncriptado = dniEncriptado;
	}

	/**
	 * Gets the cliente sinonimo.
	 *
	 * @return the cliente sinonimo
	 */
	public Boolean getClienteSinonimo() {
		return clienteSinonimo;
	}

	/**
	 * Sets the cliente sinonimo.
	 *
	 * @param clienteSinonimo
	 *            the new cliente sinonimo
	 */
	public void setClienteSinonimo(Boolean clienteSinonimo) {
		this.clienteSinonimo = clienteSinonimo;
	}

	/**
	 * Gets the datos autenticacion.
	 *
	 * @return the datos autenticacion
	 */
	public DatosAutenticacion getDatosAutenticacion() {
		return datosAutenticacion;
	}

	/**
	 * Sets the datos autenticacion.
	 *
	 * @param datosAutenticacion
	 *            the new datos autenticacion
	 */
	public void setDatosAutenticacion(DatosAutenticacion datosAutenticacion) {
		this.datosAutenticacion = datosAutenticacion;
	}

	/**
	 * Gets the clave nueva formateada.
	 *
	 * @return the clave nueva formateada
	 */
	public String getClaveNuevaFormateada() {
		return claveNuevaFormateada;
	}

	/**
	 * Sets the clave nueva formateada.
	 *
	 * @param claveNuevaFormateada
	 *            the new clave nueva formateada
	 */
	public void setClaveNuevaFormateada(String claveNuevaFormateada) {
		this.claveNuevaFormateada = claveNuevaFormateada;
	}

}
