/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author
 *
 */
public class ConsultaAgendaDestinatarioInEntity {

	/** The cliente. */
	private Cliente cliente;

	/** The Constant VALOR_DEFECTO_A02. */
	private static final String VALOR_DEFECTO_A02 = "  ";

	/** The Constant VALOR_DEFECTO_A04. */
	private static final String VALOR_DEFECTO_A04 = "    ";

	/** The Constant VALOR_DEFECTO_A11. */
	private static final String VALOR_DEFECTO_A11 = "           ";

	/** The Constant VALOR_DEFECTO_A12. */
	private static final String VALOR_DEFECTO_A12 = "            ";

	/** The Constant VALOR_DEFECTO_A22. */
	private static final String VALOR_DEFECTO_A22 = "                      ";

	/** tipoConsulta. A01 */
	@Pattern(regexp = "P|L")
	private String tipoConsulta = TipoConsultaAgendamientoEnum.LISTADO.getCampo();

	/** llamada. A02 */
	@Pattern(regexp = "PR|CN")
	private String llamada = LlamadaAgendamientoEnum.PRIMERA.getCampo();

	/** tipoCuentaCredito. A02 */
	@Pattern(regexp = "^00|01|02|03|04|09|10|  $")
	private String tipoCuentaCredito = TipoCuentaCreditoEnum.TIPO_CUENTA_CREDITO_VOID.getCampo();

	/** sucursalCuentaCredito. A04 */
	@Pattern(regexp = "^[a-zA-Z0-9]{4}|    $")
	private String sucursalCuentaCredito = VALOR_DEFECTO_A04;

	/** numeroCuentaCredito.A12 */
	@Pattern(regexp = "^[a-zA-Z0-9]{12}|[ ]{12}$")
	private String numeroCuentaCredito = VALOR_DEFECTO_A12;

	/** cbu.A22 */
	@Pattern(regexp = "^[a-zA-Z0-9]{22}|[ ]{22}$")
	private String cbu = VALOR_DEFECTO_A22;

	/** tipoDocumentoDestino. A02 */
	@Pattern(regexp = "^[a-zA-Z0-9 ]{2}|[ ]{2}$")
	private String tipoDocumentoDestinatario = VALOR_DEFECTO_A02;

	/** documentoDestino.A11 */
	@Pattern(regexp = "^[a-zA-Z0-9]{11}|[ ]{11}$")
	private String documentoDestino = VALOR_DEFECTO_A11;

	/** tipoAgenda. */
	@Pattern(regexp = "RIO|OB |EXT|TOD")
	private String tipoAgenda = TipoAgendaEnum.TODAS_LAS_AGENDAS.getCampo();

	/** The realizar rellamado. */
	@NotNull
	private Boolean realizarRellamado = Boolean.TRUE;

	/** The alias. */
	private String alias;
	
	
	/** The tiene Cueta BancaP. */
	private Boolean tieneCuetaBancaP = Boolean.FALSE;
	/**
	 * Instantiates a new consulta agenda destinatario in entity.
	 */
	public ConsultaAgendaDestinatarioInEntity() {
	    super();
    }

	/**
	 * Pago puntual para Santander.
	 *
	 * @param tipoCuentaCredito the tipo cuenta credito
	 * @param sucursalCuentaCredito the sucursal cuenta credito
	 * @param nroCuentaCredito the nro cuenta credito
	 */
	public ConsultaAgendaDestinatarioInEntity(String tipoCuentaCredito, String sucursalCuentaCredito, String nroCuentaCredito) {
	    pagoPuntual(); 
	    this.tipoCuentaCredito = tipoCuentaCredito;
	    this.sucursalCuentaCredito = sucursalCuentaCredito;
	    this.numeroCuentaCredito = nroCuentaCredito;
	    this.tipoAgenda = TipoAgendaEnum.AGENDA_RIO.getCampo();
	}
	
    /**
     * Pago puntual para otros bancos.
     *
     * @param cbu the cbu
     */
	public ConsultaAgendaDestinatarioInEntity(String cbu) {
	    pagoPuntual();
	    this.cbu = cbu;
	    this.tipoAgenda = TipoAgendaEnum.AGENDA_OTROS_BANCOS.getCampo();
	}

	/**
	 * Pago puntual.
	 */
	private void pagoPuntual() {
	    this.tipoConsulta = TipoConsultaAgendamientoEnum.PUNTUAL.getCampo();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	/*
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(cliente).append(tipoConsulta).append(llamada).append(tipoCuentaCredito)
				.append(sucursalCuentaCredito).append(numeroCuentaCredito).append(cbu).append(tipoDocumentoDestinatario)
				.append(documentoDestino).append(tipoAgenda).toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/*
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		ConsultaAgendaDestinatarioInEntity other = (ConsultaAgendaDestinatarioInEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb.append(cliente, other.cliente).append(tipoConsulta, other.tipoConsulta).append(llamada, other.llamada)
				.append(tipoCuentaCredito, other.tipoCuentaCredito)
				.append(sucursalCuentaCredito, other.sucursalCuentaCredito)
				.append(numeroCuentaCredito, other.numeroCuentaCredito).append(cbu, other.cbu)
				.append(tipoDocumentoDestinatario, other.tipoDocumentoDestinatario)
				.append(documentoDestino, other.documentoDestino).append(tipoAgenda, other.tipoAgenda).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	/*
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append(cliente).append(tipoConsulta).append(llamada).append(tipoCuentaCredito)
				.append(sucursalCuentaCredito).append(numeroCuentaCredito).append(cbu).append(tipoDocumentoDestinatario)
				.append(documentoDestino).append(tipoAgenda).toString();
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
	 * Gets the tipo consulta.
	 *
	 * @return the tipoConsulta
	 */
	public String getTipoConsulta() {
		return tipoConsulta;
	}

	/**
	 * Sets the tipo consulta.
	 *
	 * @param tipoConsulta
	 *            the tipoConsulta to set
	 */
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	/**
	 * Gets the llamada.
	 *
	 * @return the llamada
	 */
	public String getLlamada() {
		return llamada;
	}

	/**
	 * Sets the llamada.
	 *
	 * @param llamada
	 *            the llamada to set
	 */
	public void setLlamada(String llamada) {
		this.llamada = llamada;
	}

	/**
	 * Gets the tipo cuenta credito.
	 *
	 * @return the tipoCuentaCredito
	 */
	public String getTipoCuentaCredito() {
		return tipoCuentaCredito;
	}

	/**
	 * Sets the tipo cuenta credito.
	 *
	 * @param tipoCuentaCredito
	 *            the tipoCuentaCredito to set
	 */
	public void setTipoCuentaCredito(String tipoCuentaCredito) {
		this.tipoCuentaCredito = tipoCuentaCredito;
	}

	/**
	 * Gets the sucursal cuenta credito.
	 *
	 * @return the sucursalCuentaCredito
	 */
	public String getSucursalCuentaCredito() {
		return sucursalCuentaCredito;
	}

	/**
	 * Sets the sucursal cuenta credito.
	 *
	 * @param sucursalCuentaCredito
	 *            the sucursalCuentaCredito to set
	 */
	public void setSucursalCuentaCredito(String sucursalCuentaCredito) {
		this.sucursalCuentaCredito = sucursalCuentaCredito;
	}

	/**
	 * Gets the numero cuenta credito.
	 *
	 * @return the numeroCuentaCredito
	 */
	public String getNumeroCuentaCredito() {
		return numeroCuentaCredito;
	}

	/**
	 * Sets the numero cuenta credito.
	 *
	 * @param numeroCuentaCredito
	 *            the numeroCuentaCredito to set
	 */
	public void setNumeroCuentaCredito(String numeroCuentaCredito) {
		this.numeroCuentaCredito = numeroCuentaCredito;
	}

	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the cbu to set
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the tipo documento destinatario.
	 *
	 * @return the tipoDocumentoDestinatario
	 */
	public String getTipoDocumentoDestinatario() {
		return tipoDocumentoDestinatario;
	}

	/**
	 * Sets the tipo documento destinatario.
	 *
	 * @param tipoDocumentoDestinatario
	 *            the tipoDocumentoDestinatario to set
	 */
	public void setTipoDocumentoDestinatario(String tipoDocumentoDestinatario) {
		this.tipoDocumentoDestinatario = tipoDocumentoDestinatario;
	}

	/**
	 * Gets the documento destino.
	 *
	 * @return the documentoDestino
	 */
	public String getDocumentoDestino() {
		return documentoDestino;
	}

	/**
	 * Sets the documento destino.
	 *
	 * @param documentoDestino
	 *            the documentoDestino to set
	 */
	public void setDocumentoDestino(String documentoDestino) {
		this.documentoDestino = documentoDestino;
	}

	/**
	 * Gets the tipo agenda.
	 *
	 * @return the tipoAgenda
	 */
	public String getTipoAgenda() {
		return tipoAgenda;
	}

	/**
	 * Sets the tipo agenda.
	 *
	 * @param tipoAgenda
	 *            the tipoAgenda to set
	 */
	public void setTipoAgenda(String tipoAgenda) {
		this.tipoAgenda = tipoAgenda;
	}

	/**
	 * Gets the realizar rellamado.
	 *
	 * @return the realizarRellamado
	 */
	public Boolean getRealizarRellamado() {
		return realizarRellamado;
	}

	/**
	 * Sets the realizar rellamado.
	 *
	 * @param realizarRellamado
	 *            the realizarRellamado to set
	 */
	public void setRealizarRellamado(Boolean realizarRellamado) {
		this.realizarRellamado = realizarRellamado;
	}

	/**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Sets the alias.
	 *
	 * @param alias
	 *            the new alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Gets the Tiene Cueta BancaP.
	 *
	 ** @return the Tiene Cueta BancaP
	 */
	public Boolean getTieneCuetaBancaP() {
		return tieneCuetaBancaP;
	}

	/**
	 * Sets the Tiene Cueta BancaP.
	 *
	 * @param Tiene Cueta BancaP
	 *            the new Tiene Cueta BancaP
	 */
	public void setTieneCuetaBancaP(Boolean tieneCuetaBancaP) {
		this.tieneCuetaBancaP = tieneCuetaBancaP;
	}

}
