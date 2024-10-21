package ar.com.santanderrio.obp.servicios.ofuscardato.web.view;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;

public class MostrarMailView {
	
	private AutentificacionDTO desafio;
	private String mailPrincipal;
	private String mailSecundario;
	private boolean openStack; 
	private boolean isMail;
	
	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	/**
	 * @param mailSecundario the mailSecundario to set
	 */
	public void setMailSecundario(String mailSecundario) {
		this.mailSecundario = mailSecundario;
	}

	/**
	 * @return the mailPrincipal
	 */
	public String getMailPrincipal() {
		return mailPrincipal;
	}
	
	/**
	 * @return the mailSecundario
	 */
	public String getMailSecundario() {
		return mailSecundario;
	}

	/**
	 * @return the openStack
	 */
	public boolean getOpenStack() {
		return openStack;
	}

	/**
	 * @return the isMail
	 */
	public boolean getIsMail() {
		return isMail;
	}

	/**
	 * @param mailPrincipal the mailPrincipal to set
	 */
	public void setMailPrincipal(String mailPrincipal) {
		this.mailPrincipal = mailPrincipal;
	}

	/**
	 * @param openStack the openStack to set
	 */
	public void setOpenStack(boolean openStack) {
		this.openStack = openStack;
	}

	/**
	 * @param isMail the isMail to set
	 */
	public void setIsMail(boolean isMail) {
		this.isMail = isMail;
	}

}
