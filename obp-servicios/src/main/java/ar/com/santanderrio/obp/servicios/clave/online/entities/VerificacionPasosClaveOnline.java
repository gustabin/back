package ar.com.santanderrio.obp.servicios.clave.online.entities;

/**
 * 
 * @author Itr
 *
 */
public class VerificacionPasosClaveOnline {
	/** The validarPreguntaTelefono. */
	private boolean validarPreguntaTelefono;
	/** The validarPreguntasSeguridad. */
	private boolean validarPreguntasSeguridad;
	/** The validarAutenticacion. */
	private boolean validarAutenticacion;
	/** The enviarPantallaActualizacionNumeroCelular. */
	private boolean enviarPantallaActualizacionNumeroCelular;

	/**
	 * metodo para verificar si falta validar algun paso de autenticacion para el
	 * cambio de clave. Si son todos false , se puede renovar la clave
	 * 
	 * @return
	 */
	public boolean faltanCompletarPasos() {
		if (!validarPreguntaTelefono && !validarPreguntasSeguridad && !validarAutenticacion) {
			return false;
		}
		return true;
	}
	
	

	/**
	 * @return the enviarPantallaActualizacionNumeroCelular
	 */
	public boolean isEnviarPantallaActualizacionNumeroCelular() {
		return enviarPantallaActualizacionNumeroCelular;
	}



	/**
	 * @param enviarPantallaActualizacionNumeroCelular the enviarPantallaActualizacionNumeroCelular to set
	 */
	public void setEnviarPantallaActualizacionNumeroCelular(boolean enviarPantallaActualizacionNumeroCelular) {
		this.enviarPantallaActualizacionNumeroCelular = enviarPantallaActualizacionNumeroCelular;
	}



	/**
	 * @return the validarPreguntaTelefono
	 */
	public boolean isValidarPreguntaTelefono() {
		return validarPreguntaTelefono;
	}

	/**
	 * @param validarPreguntaTelefono the validarPreguntaTelefono to set
	 */
	public void setValidarPreguntaTelefono(boolean validarPreguntaTelefono) {
		this.validarPreguntaTelefono = validarPreguntaTelefono;
	}

	/**
	 * @return the validarPreguntasSeguridad
	 */
	public boolean isValidarPreguntasSeguridad() {
		return validarPreguntasSeguridad;
	}

	/**
	 * @param validarPreguntasSeguridad the validarPreguntasSeguridad to set
	 */
	public void setValidarPreguntasSeguridad(boolean validarPreguntasSeguridad) {
		this.validarPreguntasSeguridad = validarPreguntasSeguridad;
	}

	/**
	 * @return the validarAutenticacion
	 */
	public boolean isValidarAutenticacion() {
		return validarAutenticacion;
	}

	/**
	 * @param validarAutenticacion the validarAutenticacion to set
	 */
	public void setValidarAutenticacion(boolean validarAutenticacion) {
		this.validarAutenticacion = validarAutenticacion;
	}

	



}
