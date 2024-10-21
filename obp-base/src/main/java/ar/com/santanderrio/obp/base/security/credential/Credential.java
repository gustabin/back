/**
 * 
 */
package ar.com.santanderrio.obp.base.security.credential;

// TODO: Auto-generated Javadoc
/**
 * The Class Credential.
 *
 * @author Jonatan_Bocian
 */
public class Credential {

	/** The usuario. */
	private String usuario;

	/** The password. */
	private String password;

	/** The jndi. */
	private String jndi;

	/**
	 * Gets the jndi.
	 *
	 * @return the jndi
	 */
	public String getJndi() {
		return jndi;
	}

	/**
	 * Setter para jndi.
	 *
	 * @param jndi
	 *            the jndi to set
	 */
	public void setJndi(String jndi) {
		this.jndi = jndi;
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
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter para password.
	 *
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jndi == null) ? 0 : jndi.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Credential other = (Credential) obj;
		if (jndi == null) {
			if (other.jndi != null) {
				return false;
			}
		} else if (!jndi.equals(other.jndi)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (usuario == null) {
			if (other.usuario != null) {
				return false;
			}
		} else if (!usuario.equals(other.usuario)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Credential [" + (usuario != null ? "usuario=" + usuario + ", " : "")
				+ (password != null ? "password=" + password + ", " : "") + (jndi != null ? "jndi=" + jndi : "") + "]";
	}

}
