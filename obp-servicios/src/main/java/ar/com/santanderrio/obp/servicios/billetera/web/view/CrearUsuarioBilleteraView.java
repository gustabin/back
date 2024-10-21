/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.web.view;

import java.util.List;

/**
 * The Class CrearUsuarioBilleteraView.
 */
public class CrearUsuarioBilleteraView {

	/** The cod area. */
	private String codArea;

	/** The emp cel. */
	private String empCel;

	/** The empresas. */
	private List<EmpresaCelularView> empresas;

	/** The mail. */
	private String mail;

	/** The mensaje. */
	private String mensaje;
	
	/** The mensaje pass invalida. */
	private String mensajePassInvalida;
	
	/** The mensaje pass no coincide. */
	private String mensajePassNoCoincide;

	/** The nro cel. */
	private String nroCel;

	/** The preguntas seguridad. */
	private List<PreguntaSeguridadView> preguntasSeguridad;

	/**
	 * Gets the cod area.
	 *
	 * @return the codArea
	 */
	public String getCodArea() {
		return codArea;
	}

	/**
	 * Gets the emp cel.
	 *
	 * @return the empCel
	 */
	public String getEmpCel() {
		return empCel;
	}

	/**
	 * Gets the empresas.
	 *
	 * @return the empresas
	 */
	public List<EmpresaCelularView> getEmpresas() {
		return empresas;
	}

	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Gets the nro cel.
	 *
	 * @return the nroCel
	 */
	public String getNroCel() {
		return nroCel;
	}

	/**
	 * Gets the preguntas seguridad.
	 *
	 * @return the preguntasSeguridad
	 */
	public List<PreguntaSeguridadView> getPreguntasSeguridad() {
		return preguntasSeguridad;
	}

	/**
	 * Sets the cod area.
	 *
	 * @param codArea
	 *            the codArea to set
	 */
	public void setCodArea(String codArea) {
		this.codArea = codArea;
	}

	/**
	 * Sets the emp cel.
	 *
	 * @param empCel
	 *            the empCel to set
	 */
	public void setEmpCel(String empCel) {
		this.empCel = empCel;
	}

	/**
	 * Sets the empresas.
	 *
	 * @param empresas
	 *            the empresas to set
	 */
	public void setEmpresas(List<EmpresaCelularView> empresas) {
		this.empresas = empresas;
	}

	/**
	 * Sets the mail.
	 *
	 * @param mail
	 *            the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Sets the nro cel.
	 *
	 * @param nroCel
	 *            the nroCel to set
	 */
	public void setNroCel(String nroCel) {
		this.nroCel = nroCel;
	}

	/**
	 * Sets the preguntas seguridad.
	 *
	 * @param preguntasSeguridad
	 *            the preguntasSeguridad to set
	 */
	public void setPreguntasSeguridad(List<PreguntaSeguridadView> preguntasSeguridad) {
		this.preguntasSeguridad = preguntasSeguridad;
	}

    /**
	 * Gets the mensaje pass invalida.
	 *
	 * @return the mensaje pass invalida
	 */
    public String getMensajePassInvalida() {
        return mensajePassInvalida;
    }

    /**
	 * Sets the mensaje pass invalida.
	 *
	 * @param mensajePassInvalida
	 *            the new mensaje pass invalida
	 */
    public void setMensajePassInvalida(String mensajePassInvalida) {
        this.mensajePassInvalida = mensajePassInvalida;
    }

    /**
	 * Gets the mensaje pass no coincide.
	 *
	 * @return the mensaje pass no coincide
	 */
    public String getMensajePassNoCoincide() {
        return mensajePassNoCoincide;
    }

    /**
	 * Sets the mensaje pass no coincide.
	 *
	 * @param mensajePassNoCoincide
	 *            the new mensaje pass no coincide
	 */
    public void setMensajePassNoCoincide(String mensajePassNoCoincide) {
        this.mensajePassNoCoincide = mensajePassNoCoincide;
    }

}
