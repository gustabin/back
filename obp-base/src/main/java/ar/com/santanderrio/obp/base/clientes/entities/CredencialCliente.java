/**
 * 
 */
package ar.com.santanderrio.obp.base.clientes.entities;

import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class CredencialCliente.
 *
 * @author Jonatan_Bocian
 */
public class CredencialCliente {

    /** The clave. */
    private String clave;

    /** The usuario. */
    private String usuario;

    /** The dni. */
    private String dni;

    /** The prefijo. */
    private String prefijo;

    /** The sufijo. */
    private String sufijo;

    /** The dni ori. */
    private String dniOri;

    /** The fecha nacimiento. */
    private Date fechaNacimiento;

    /** The usuario vacio. */
    private String usuarioNuevo;

    /** The clave vacia. */
    private String claveNueva;

    /** The ip. */
    private String ip;

    /** The dispositivo. */
    private String dispositivo;

    /** The navegador. */
    private String navegador;

    /** The instancia. */
    private String instancia;

    /** The id sesion. */
    private String idSesion;

    /** The host name. */
    private String hostName = "";

    /** The is alta. */
    private Boolean isAlta;

    /** The isMac. */
    private Boolean isMac;

    /** The is hononimo. */
    private Boolean isHononimo;

    /** The token. */
    private String token;
    
    private Boolean ingresoDesdeCrearUsuario;
    

    /**
     * Gets the clave.
     *
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * Setter para clave.
     *
     * @param clave
     *            the clave to set
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
     * Setter para usuario.
     *
     * @param usuario
     *            the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
     * Setter para prefijo.
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
     * Setter para sufijo.
     *
     * @param sufijo
     *            the sufijo to set
     */
    public void setSufijo(String sufijo) {
        this.sufijo = sufijo;
    }

    /**
     * Gets the dni ori.
     *
     * @return the dniOri
     */
    public String getDniOri() {
        return dniOri;
    }

    /**
     * Setter para dni ori.
     *
     * @param dniOri
     *            the dniOri to set
     */
    public void setDniOri(String dniOri) {
        this.dniOri = dniOri;
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
     * Setter para dni.
     *
     * @param dni
     *            the dni to set
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Gets the fecha nacimiento.
     *
     * @return the fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Setter para fecha nacimiento.
     *
     * @param fechaNacimiento
     *            the fechaNacimiento to set
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Gets the usuario vacio.
     *
     * @return the usuarioVacio
     */
    public String getUsuarioNuevo() {
        return usuarioNuevo;
    }

    /**
     * Setter para usuario vacio.
     *
     * @param usuarioNuevo
     *            the new usuario nuevo
     */
    public void setUsuarioNuevo(String usuarioNuevo) {
        this.usuarioNuevo = usuarioNuevo;
    }

    /**
     * Gets the clave vacia.
     *
     * @return the claveVacia
     */
    public String getClaveNueva() {
        return claveNueva;
    }

    /**
     * Setter para clave vacia.
     *
     * @param claveNueva
     *            the new clave nueva
     */
    public void setClaveNueva(String claveNueva) {
        this.claveNueva = claveNueva;
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
        return this.navegador;
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
     * Gets the id sesion.
     *
     * @return the idSesion
     */
    public String getIdSesion() {
        return idSesion;
    }

    /**
     * Setter para id sesion.
     *
     * @param idSesion
     *            the idSesion to set
     */
    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    /**
     * Gets the host name.
     *
     * @return the host name
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Setter para host name.
     *
     * @param hostName
     *            el nuevo host name
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        HashCodeBuilder hcb = new HashCodeBuilder();
        hcb.append(clave);
        hcb.append(dni);
        hcb.append(usuario);
        return hcb.toHashCode();

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

        CredencialCliente other = (CredencialCliente) obj;
        EqualsBuilder eb = new EqualsBuilder();
        eb.append(clave, other.getClave());
        eb.append(dni, other.getDni());
        eb.append(usuario, other.getUsuario());
        return eb.isEquals();
    }

    /**
     * To string.
     *
     * @return the string
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("usuario", usuario).append(" prefijo", prefijo)
                .append(" sufijo", sufijo).append(" dniOri", dniOri)
                .append(" fechaNacimiento", fechaNacimiento != null ? fechaNacimiento.toString() : "")
                .append(" usuarioVacio", usuarioNuevo).append(" claveVacia", claveNueva)
                .append(" dispositivo", dispositivo).append(" navegador", navegador).append(" instancia", instancia)
                .append(" idSesion", idSesion).append(" hostName", hostName).toString();
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
     * Gets the checks if is mac.
     *
     * @return the checks if is mac
     */
    public Boolean getIsMac() {
        return isMac;
    }

    /**
     * Sets the checks if is mac.
     *
     * @param isMac
     *            the new checks if is mac
     */
    public void setIsMac(Boolean isMac) {
        this.isMac = isMac;
    }

    /**
     * Gets the checks if is hononimo.
     *
     * @return the checks if is hononimo
     */
    public Boolean getIsHononimo() {
        return isHononimo;
    }

    /**
     * Sets the checks if is hononimo.
     *
     * @param isHononimo
     *            the new checks if is hononimo
     */
    public void setIsHononimo(Boolean isHononimo) {
        this.isHononimo = isHononimo;
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
     * Sets the token.
     *
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

	public Boolean getIngresoDesdeCrearUsuario() {
		return ingresoDesdeCrearUsuario;
	}

	public void setIngresoDesdeCrearUsuario(Boolean ingresoDesdeCrearUsuario) {
		this.ingresoDesdeCrearUsuario = ingresoDesdeCrearUsuario;
	}
        
}
