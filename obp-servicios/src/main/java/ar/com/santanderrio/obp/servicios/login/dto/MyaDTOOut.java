/*
 * 
 */
package ar.com.santanderrio.obp.servicios.login.dto;

import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteEstadoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.TipoCompaniaEnum;

/**
 * The Class MyaDTOOut.
 */
public class MyaDTOOut {

	/** The celular id. */
	private String celularId;

	/** The celular. */
	private String celular;
	
	/** The destino validado celular. */
	private String destinoValidadoCelular;

	/** The celular secundario id. */
	private String celularSecundarioId;

	/** The celular secundario. */
	private String celularSecundario;

	/** The destino validado celular secundario. */
	private String destinoValidadoCelularSecundario;
	
	/** The email id. */
	private String emailId;

	/** The email. */
	private String email;

	/** The destino validado email. */
	private String destinoValidadoEmail;
	
	/** The email secundario id. */
	private String emailSecundarioId;

	/** The email secundario. */
	private String emailSecundario;

	/** The destino validado email secundario. */
	private String destinoValidadoEmailSecundario;

	/** The cliente estado enum. */
	private ClienteEstadoEnum clienteEstadoEnum;

	/** The tipo compania enum. */
	private TipoCompaniaEnum tipoCompaniaEnum;

	/** The tipo compania secundario enum. */
	private TipoCompaniaEnum tipoCompaniaSecundarioEnum;

	/** The fecha alta celular. */
	private String fechaAltaCelular;
	
	/** The fecha modificado celular. */
	private String fechaModificadoCelular;
	
	/** The canal celular. */
	private String canalCelular;
	
	/** The sub canal celular. */
	private String subCanalCelular;
	
	/** The fecha alta email. */
	private String fechaAltaEmail;
	
	/** The fecha modificado email. */
	private String fechaModificadoEmail;
	
	/** The canal email. */
	private String canalEmail;
	
	/** The sub canal email. */
	private String subCanalEmail;
	
	/** The fecha alta celular secundario. */
	private String fechaAltaCelularSecundario;
	
	/** The fecha modificado celular secundario. */
	private String fechaModificadoCelularSecundario;
	
	/** The canal celular secundario. */
	private String canalCelularSecundario;
	
	/** The sub canal celular secundario. */
	private String subCanalCelularSecundario;
	
	/** The fecha alta email secundario. */
	private String fechaAltaEmailSecundario;
	
	/** The fecha modificado email secundario. */
	private String fechaModificadoEmailSecundario;
	
	/** The canal email secundario. */
	private String canalEmailSecundario;
	
	/** The sub canal email secundario. */
	private String subCanalEmailSecundario;

	/**
	 * Gets the celular.
	 *
	 * @return the celular
	 */
	public String getCelular() {
		return celular;
	}

	/**
	 * Sets the celular.
	 *
	 * @param celular the new celular
	 */
	public void setCelular(String celular) {
		this.celular = celular;
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
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the cliente estado enum.
	 *
	 * @return the cliente estado enum
	 */
	public ClienteEstadoEnum getClienteEstadoEnum() {
		return clienteEstadoEnum;
	}

	/**
	 * Sets the cliente estado enum.
	 *
	 * @param clienteEstadoEnum the new cliente estado enum
	 */
	public void setClienteEstadoEnum(ClienteEstadoEnum clienteEstadoEnum) {
		this.clienteEstadoEnum = clienteEstadoEnum;
	}

	/**
	 * Gets the tipo compania enum.
	 *
	 * @return the tipo compania enum
	 */
	public TipoCompaniaEnum getTipoCompaniaEnum() {
		return tipoCompaniaEnum;
	}

	/**
	 * Sets the tipo compania enum.
	 *
	 * @param tipoCompaniaEnum the new tipo compania enum
	 */
	public void setTipoCompaniaEnum(TipoCompaniaEnum tipoCompaniaEnum) {
		this.tipoCompaniaEnum = tipoCompaniaEnum;
	}

	/**
	 * Gets the celular id.
	 *
	 * @return the celular id
	 */
	public String getCelularId() {
		return celularId;
	}

	/**
	 * Sets the celular id.
	 *
	 * @param celularId the new celular id
	 */
	public void setCelularId(String celularId) {
		this.celularId = celularId;
	}

	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Sets the email id.
	 *
	 * @param emailId the new email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Gets the celular secundario id.
	 *
	 * @return the celular secundario id
	 */
	public String getCelularSecundarioId() {
		return celularSecundarioId;
	}

	/**
	 * Sets the celular secundario id.
	 *
	 * @param celularSecundarioId the new celular secundario id
	 */
	public void setCelularSecundarioId(String celularSecundarioId) {
		this.celularSecundarioId = celularSecundarioId;
	}

	/**
	 * Gets the celular secundario.
	 *
	 * @return the celular secundario
	 */
	public String getCelularSecundario() {
		return celularSecundario;
	}

	/**
	 * Sets the celular secundario.
	 *
	 * @param celularSecundario the new celular secundario
	 */
	public void setCelularSecundario(String celularSecundario) {
		this.celularSecundario = celularSecundario;
	}

	/**
	 * Gets the email secundario id.
	 *
	 * @return the email secundario id
	 */
	public String getEmailSecundarioId() {
		return emailSecundarioId;
	}

	/**
	 * Sets the email secundario id.
	 *
	 * @param emailSecundarioId the new email secundario id
	 */
	public void setEmailSecundarioId(String emailSecundarioId) {
		this.emailSecundarioId = emailSecundarioId;
	}

	/**
	 * Gets the email secundario.
	 *
	 * @return the email secundario
	 */
	public String getEmailSecundario() {
		return emailSecundario;
	}

	/**
	 * Sets the email secundario.
	 *
	 * @param emailSecundario the new email secundario
	 */
	public void setEmailSecundario(String emailSecundario) {
		this.emailSecundario = emailSecundario;
	}

	/**
	 * Gets the tipo compania secundario enum.
	 *
	 * @return the tipo compania secundario enum
	 */
	public TipoCompaniaEnum getTipoCompaniaSecundarioEnum() {
		return tipoCompaniaSecundarioEnum;
	}

	/**
	 * Sets the tipo compania secundario enum.
	 *
	 * @param tipoCompaniaSecundarioEnum the new tipo compania secundario enum
	 */
	public void setTipoCompaniaSecundarioEnum(TipoCompaniaEnum tipoCompaniaSecundarioEnum) {
		this.tipoCompaniaSecundarioEnum = tipoCompaniaSecundarioEnum;
	}

	/**
	 * Gets the destino validado celular.
	 *
	 * @return the destino validado celular
	 */
	public String getDestinoValidadoCelular() {
		return destinoValidadoCelular;
	}

	/**
	 * Sets the destino validado celular.
	 *
	 * @param destinoValidadoCelular the new destino validado celular
	 */
	public void setDestinoValidadoCelular(String destinoValidadoCelular) {
		this.destinoValidadoCelular = destinoValidadoCelular;
	}

	/**
	 * Gets the destino validado celular secundario.
	 *
	 * @return the destino validado celular secundario
	 */
	public String getDestinoValidadoCelularSecundario() {
		return destinoValidadoCelularSecundario;
	}

	/**
	 * Sets the destino validado celular secundario.
	 *
	 * @param destinoValidadoCelularSecundario the new destino validado celular secundario
	 */
	public void setDestinoValidadoCelularSecundario(String destinoValidadoCelularSecundario) {
		this.destinoValidadoCelularSecundario = destinoValidadoCelularSecundario;
	}

	/**
	 * Gets the destino validado email.
	 *
	 * @return the destino validado email
	 */
	public String getDestinoValidadoEmail() {
		return destinoValidadoEmail;
	}

	/**
	 * Sets the destino validado email.
	 *
	 * @param destinoValidadoEmail the new destino validado email
	 */
	public void setDestinoValidadoEmail(String destinoValidadoEmail) {
		this.destinoValidadoEmail = destinoValidadoEmail;
	}

	/**
	 * Gets the destino validado email secundario.
	 *
	 * @return the destino validado email secundario
	 */
	public String getDestinoValidadoEmailSecundario() {
		return destinoValidadoEmailSecundario;
	}

	/**
	 * Sets the destino validado email secundario.
	 *
	 * @param destinoValidadoEmailSecundario the new destino validado email secundario
	 */
	public void setDestinoValidadoEmailSecundario(String destinoValidadoEmailSecundario) {
		this.destinoValidadoEmailSecundario = destinoValidadoEmailSecundario;
	}

	/**
	 * Gets the fecha alta celular.
	 *
	 * @return the fecha alta celular
	 */
	public String getFechaAltaCelular() {
		return fechaAltaCelular;
	}

	/**
	 * Sets the fecha alta celular.
	 *
	 * @param fechaAltaCelular the new fecha alta celular
	 */
	public void setFechaAltaCelular(String fechaAltaCelular) {
		this.fechaAltaCelular = fechaAltaCelular;
	}

	/**
	 * Gets the fecha modificado celular.
	 *
	 * @return the fecha modificado celular
	 */
	public String getFechaModificadoCelular() {
		return fechaModificadoCelular;
	}

	/**
	 * Sets the fecha modificado celular.
	 *
	 * @param fechaModificadoCelular the new fecha modificado celular
	 */
	public void setFechaModificadoCelular(String fechaModificadoCelular) {
		this.fechaModificadoCelular = fechaModificadoCelular;
	}

	/**
	 * Gets the canal celular.
	 *
	 * @return the canal celular
	 */
	public String getCanalCelular() {
		return canalCelular;
	}

	/**
	 * Sets the canal celular.
	 *
	 * @param canalCelular the new canal celular
	 */
	public void setCanalCelular(String canalCelular) {
		this.canalCelular = canalCelular;
	}

	/**
	 * Gets the sub canal celular.
	 *
	 * @return the sub canal celular
	 */
	public String getSubCanalCelular() {
		return subCanalCelular;
	}

	/**
	 * Sets the sub canal celular.
	 *
	 * @param subCanalCelular the new sub canal celular
	 */
	public void setSubCanalCelular(String subCanalCelular) {
		this.subCanalCelular = subCanalCelular;
	}

	/**
	 * Gets the fecha alta email.
	 *
	 * @return the fecha alta email
	 */
	public String getFechaAltaEmail() {
		return fechaAltaEmail;
	}

	/**
	 * Sets the fecha alta email.
	 *
	 * @param fechaAltaEmail the new fecha alta email
	 */
	public void setFechaAltaEmail(String fechaAltaEmail) {
		this.fechaAltaEmail = fechaAltaEmail;
	}

	/**
	 * Gets the fecha modificado email.
	 *
	 * @return the fecha modificado email
	 */
	public String getFechaModificadoEmail() {
		return fechaModificadoEmail;
	}

	/**
	 * Sets the fecha modificado email.
	 *
	 * @param fechaModificadoEmail the new fecha modificado email
	 */
	public void setFechaModificadoEmail(String fechaModificadoEmail) {
		this.fechaModificadoEmail = fechaModificadoEmail;
	}

	/**
	 * Gets the canal email.
	 *
	 * @return the canal email
	 */
	public String getCanalEmail() {
		return canalEmail;
	}

	/**
	 * Sets the canal email.
	 *
	 * @param canalEmail the new canal email
	 */
	public void setCanalEmail(String canalEmail) {
		this.canalEmail = canalEmail;
	}

	/**
	 * Gets the sub canal email.
	 *
	 * @return the sub canal email
	 */
	public String getSubCanalEmail() {
		return subCanalEmail;
	}

	/**
	 * Sets the sub canal email.
	 *
	 * @param subCanalEmail the new sub canal email
	 */
	public void setSubCanalEmail(String subCanalEmail) {
		this.subCanalEmail = subCanalEmail;
	}

	/**
	 * Gets the fecha alta celular secundario.
	 *
	 * @return the fecha alta celular secundario
	 */
	public String getFechaAltaCelularSecundario() {
		return fechaAltaCelularSecundario;
	}

	/**
	 * Sets the fecha alta celular secundario.
	 *
	 * @param fechaAltaCelularSecundario the new fecha alta celular secundario
	 */
	public void setFechaAltaCelularSecundario(String fechaAltaCelularSecundario) {
		this.fechaAltaCelularSecundario = fechaAltaCelularSecundario;
	}

	/**
	 * Gets the fecha modificado celular secundario.
	 *
	 * @return the fecha modificado celular secundario
	 */
	public String getFechaModificadoCelularSecundario() {
		return fechaModificadoCelularSecundario;
	}

	/**
	 * Sets the fecha modificado celular secundario.
	 *
	 * @param fechaModificadoCelularSecundario the new fecha modificado celular secundario
	 */
	public void setFechaModificadoCelularSecundario(String fechaModificadoCelularSecundario) {
		this.fechaModificadoCelularSecundario = fechaModificadoCelularSecundario;
	}

	/**
	 * Gets the canal celular secundario.
	 *
	 * @return the canal celular secundario
	 */
	public String getCanalCelularSecundario() {
		return canalCelularSecundario;
	}

	/**
	 * Sets the canal celular secundario.
	 *
	 * @param canalCelularSecundario the new canal celular secundario
	 */
	public void setCanalCelularSecundario(String canalCelularSecundario) {
		this.canalCelularSecundario = canalCelularSecundario;
	}

	/**
	 * Gets the sub canal celular secundario.
	 *
	 * @return the sub canal celular secundario
	 */
	public String getSubCanalCelularSecundario() {
		return subCanalCelularSecundario;
	}

	/**
	 * Sets the sub canal celular secundario.
	 *
	 * @param subCanalCelularSecundario the new sub canal celular secundario
	 */
	public void setSubCanalCelularSecundario(String subCanalCelularSecundario) {
		this.subCanalCelularSecundario = subCanalCelularSecundario;
	}

	/**
	 * Gets the fecha alta email secundario.
	 *
	 * @return the fecha alta email secundario
	 */
	public String getFechaAltaEmailSecundario() {
		return fechaAltaEmailSecundario;
	}

	/**
	 * Sets the fecha alta email secundario.
	 *
	 * @param fechaAltaEmailSecundario the new fecha alta email secundario
	 */
	public void setFechaAltaEmailSecundario(String fechaAltaEmailSecundario) {
		this.fechaAltaEmailSecundario = fechaAltaEmailSecundario;
	}

	/**
	 * Gets the fecha modificado email secundario.
	 *
	 * @return the fecha modificado email secundario
	 */
	public String getFechaModificadoEmailSecundario() {
		return fechaModificadoEmailSecundario;
	}

	/**
	 * Sets the fecha modificado email secundario.
	 *
	 * @param fechaModificadoEmailSecundario the new fecha modificado email secundario
	 */
	public void setFechaModificadoEmailSecundario(String fechaModificadoEmailSecundario) {
		this.fechaModificadoEmailSecundario = fechaModificadoEmailSecundario;
	}

	/**
	 * Gets the canal email secundario.
	 *
	 * @return the canal email secundario
	 */
	public String getCanalEmailSecundario() {
		return canalEmailSecundario;
	}

	/**
	 * Sets the canal email secundario.
	 *
	 * @param canalEmailSecundario the new canal email secundario
	 */
	public void setCanalEmailSecundario(String canalEmailSecundario) {
		this.canalEmailSecundario = canalEmailSecundario;
	}

	/**
	 * Gets the sub canal email secundario.
	 *
	 * @return the sub canal email secundario
	 */
	public String getSubCanalEmailSecundario() {
		return subCanalEmailSecundario;
	}

	/**
	 * Sets the sub canal email secundario.
	 *
	 * @param subCanalEmailSecundario the new sub canal email secundario
	 */
	public void setSubCanalEmailSecundario(String subCanalEmailSecundario) {
		this.subCanalEmailSecundario = subCanalEmailSecundario;
	}

}