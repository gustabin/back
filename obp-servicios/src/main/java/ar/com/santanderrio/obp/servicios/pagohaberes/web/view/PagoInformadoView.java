/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.web.view;

import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;
import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * The Class PagoInformadoView.
 */
public class PagoInformadoView extends RsaDTO {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new pago informado view.
     */
    public PagoInformadoView() {
        super(OperacionesRSAEnum.PAGODESUELDOS);
    }

    /** The id. */
    private String id;

    /** The descripcion Empleado. */
    private String descripcionEmpleado;

    /** The nick name. */
    private String nickName;

    /** The tipo Cuenta Destino. */
    private String tipoCuentaDestino;

    /** The Abreviatura Destino. */
    private String abreviaturaDestino;

    /** The cuenta Destino. */
    private String cuentaDestino;

    /** The tipo Pago. */
    private String tipoPago;

    /** The concepto. */
    private String concepto;

    /** The tipo Documento. */
    private String tipoDocumento;

    /** The documento. */
    private String documento;

    /** The importe. */
    private String importe;

    /** The tipo Persona. */
    private String tipoPersona;

    /** The tipo Id. */
    private String tipoId;

    /** The id Cliente. */
    private String idCliente;

    /** The fecha Nac. */
    private String fechaNac;

    /** The nup. */
    private String nup;

    /** The id Transaccion. */
    private String idTransaccion;

    /** The abreviatura. */
    private String abreviatura;

    /** The divisa. */
    private String divisa;

    /** The alias Origen. */
    private String aliasOrigen;

    /** The tipo Cuenta Origen. */
    private String tipoCuentaOrigen;

    /** The Abreviatura Origen. */
    private String abreviaturaOrigen;

    /** The cuenta Origen. */
    private String cuentaOrigen;

    /** The nro recurrencia. */
    private String nroRecurrencia;

    /** The cuenta alias Destino. */
    private String aliasDestino;

    /** The cuenta tipo Cuil. */
    private String tipoCuil;

    /** The cuenta Cuil. */
    private String cuil;

    /** The primeraVez. */
    private Boolean primeraVez;

    /** The fechaBaseRecurrencia. */
    private String fechaBaseRecurrencia;

    /** The frecRecurrencia. */
    private String frecRecurrencia;

    /** The maxRecurrencia. */
    private String maxRecurrencia;

    /** The tipoRecurrencia. */
    private String tipoRecurrencia;

    /** The saldoCuentaOrigen. */
    private String saldoCuentaOrigen;

    /** The importeText. */
    private String importeText;

    /** The cb. */
    private boolean cb;

    /** The tiene Pago Programado. */
    private Boolean tienePagoProgramado;

    /** The tiene boton Habilitadoo. */
    private Boolean botonHabilitado;

    /** The alias Cuenta Origen. */
    private String aliasCuentaOrigen;

    /** The nro Cuenta Origen. */
    private String nroCuentaOrigen;

    /** The is Saldo Pesos Negativo. */
    private Boolean isSaldoPesosNegativo;

    /** The desafio. */
    @JsonManagedReference
    private AutentificacionDTO desafio;
    
    /** Se usa en RSA */
    @JsonIgnore
    private Integer cantDiasUltimoCambioClave;

    /** Se usa en RSA */
    @JsonIgnore
    private Integer cantDiasUltimoCambioToken;

    private Boolean isChallenge = Boolean.TRUE;

    /** Se usa en RSA. */
    @JsonIgnore
    private BiocatchResponseDataDTO biocatchResponseData;

    @JsonIgnore
    private float scoringDestinatario;
    @JsonIgnore
    private Integer cantDiasUltimoCambioMail;

    @JsonIgnore
    private boolean isPagoProgramado;

    @JsonIgnore
    private String fechaPago;



    @JsonIgnore
    private boolean pif = false;
    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the descripcion empleado.
     *
     * @return the descripcionEmpleado
     */
    public String getDescripcionEmpleado() {
        return descripcionEmpleado;
    }

    /**
     * Sets the descripcion empleado.
     *
     * @param descripcionEmpleado
     *            the descripcionEmpleado to set
     */
    public void setDescripcionEmpleado(String descripcionEmpleado) {
        this.descripcionEmpleado = descripcionEmpleado;
    }

    /**
     * Gets the tipo pago.
     *
     * @return the tipoPago
     */
    public String getTipoPago() {
        return tipoPago;
    }

    /**
     * Sets the tipo pago.
     *
     * @param tipoPago
     *            the tipoPago to set
     */
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    /**
     * Gets the concepto.
     *
     * @return the concepto
     */
    public String getConcepto() {
        return concepto;
    }

    /**
     * Sets the concepto.
     *
     * @param concepto
     *            the concepto to set
     */
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    /**
     * Gets the tipo documento.
     *
     * @return the tipoDocumento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Sets the tipo documento.
     *
     * @param tipoDocumento
     *            the tipoDocumento to set
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Gets the documento.
     *
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Sets the documento.
     *
     * @param documento
     *            the documento to set
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Gets the importe.
     *
     * @return the importe
     */
    public String getImporte() {
        return importe;
    }

    /**
     * Sets the importe.
     *
     * @param importe
     *            the importe to set
     */
    public void setImporte(String importe) {
        this.importe = importe;
    }

    /**
     * Gets the tipo persona.
     *
     * @return the tipo persona
     */
    public String getTipoPersona() {
        return tipoPersona;
    }

    /**
     * Sets the tipo persona.
     *
     * @param tipoPersona
     *            the new tipo persona
     */
    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    /**
     * Gets the tipo id.
     *
     * @return the tipo id
     */
    public String getTipoId() {
        return tipoId;
    }

    /**
     * Sets the tipo id.
     *
     * @param tipoId
     *            the new tipo id
     */
    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    /**
     * Gets the id cliente.
     *
     * @return the id cliente
     */
    public String getIdCliente() {
        return idCliente;
    }

    /**
     * Sets the id cliente.
     *
     * @param idCliente
     *            the new id cliente
     */
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Gets the fecha nac.
     *
     * @return the fecha nac
     */
    public String getFechaNac() {
        return fechaNac;
    }

    /**
     * Sets the fecha nac.
     *
     * @param fechaNac
     *            the new fecha nac
     */
    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
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
     * Sets the nup.
     *
     * @param nup
     *            the new nup
     */
    public void setNup(String nup) {
        this.nup = nup;
    }

    /**
     * Gets the id transaccion.
     *
     * @return the id transaccion
     */
    public String getIdTransaccion() {
        return idTransaccion;
    }

    /**
     * Sets the id transaccion.
     *
     * @param idTransaccion
     *            the new id transaccion
     */
    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    /**
     * Gets the abreviatura.
     *
     * @return the abreviatura
     */
    public String getAbreviatura() {
        return abreviatura;
    }

    /**
     * Sets the abreviatura.
     *
     * @param abreviatura
     *            the new abreviatura
     */
    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    /**
     * Gets the divisa.
     *
     * @return the divisa
     */
    public String getDivisa() {
        return divisa;
    }

    /**
     * Sets the divisa.
     *
     * @param divisa
     *            the new divisa
     */
    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    /**
     * Gets the alias origen.
     *
     * @return the alias origen
     */
    public String getAliasOrigen() {
        return aliasOrigen;
    }

    /**
     * Sets the alias origen.
     *
     * @param aliasOrigen
     *            the new alias origen
     */
    public void setAliasOrigen(String aliasOrigen) {
        this.aliasOrigen = aliasOrigen;
    }

    /**
     * Gets the cuenta destino.
     *
     * @return the cuenta destino
     */
    public String getCuentaDestino() {
        return cuentaDestino;
    }

    /**
     * Sets the cuenta destino.
     *
     * @param cuentaDestino
     *            the new cuenta destino
     */
    public void setCuentaDestino(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    /**
     * Gets the cuenta origen.
     *
     * @return the cuenta origen
     */
    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    /**
     * Sets the cuenta origen.
     *
     * @param cuentaOrigen
     *            the new cuenta origen
     */
    public void setCuentaOrigen(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    /**
     * Gets the tipo cuenta destino.
     *
     * @return the tipo cuenta destino
     */
    public String getTipoCuentaDestino() {
        return tipoCuentaDestino;
    }

    /**
     * Sets the tipo cuenta destino.
     *
     * @param tipoCuentaDestino
     *            the new tipo cuenta destino
     */
    public void setTipoCuentaDestino(String tipoCuentaDestino) {
        this.tipoCuentaDestino = tipoCuentaDestino;
    }

    /**
     * Gets the tipo cuenta origen.
     *
     * @return the tipo cuenta origen
     */
    public String getTipoCuentaOrigen() {
        return tipoCuentaOrigen;
    }

    /**
     * Sets the tipo cuenta origen.
     *
     * @param tipoCuentaOrigen
     *            the new tipo cuenta origen
     */
    public void setTipoCuentaOrigen(String tipoCuentaOrigen) {
        this.tipoCuentaOrigen = tipoCuentaOrigen;
    }

    /**
     * Gets the nro recurrencia.
     *
     * @return the nro recurrencia
     */
    public String getNroRecurrencia() {
        return nroRecurrencia;
    }

    /**
     * Sets the nro recurrencia.
     *
     * @param nroRecurrencia
     *            the new nro recurrencia
     */
    public void setNroRecurrencia(String nroRecurrencia) {
        this.nroRecurrencia = nroRecurrencia;
    }

    /**
     * Gets the alias destino.
     *
     * @return the alias destino
     */
    public String getAliasDestino() {
        return aliasDestino;
    }

    /**
     * Sets the alias destino.
     *
     * @param aliasDestino
     *            the new alias destino
     */
    public void setAliasDestino(String aliasDestino) {
        this.aliasDestino = aliasDestino;
    }

    /**
     * Gets the tipo cuil.
     *
     * @return the tipoCuil
     */
    public String getTipoCuil() {
        return tipoCuil;
    }

    /**
     * Sets the tipo cuil.
     *
     * @param tipoCuil
     *            the new tipo cuil
     */
    public void setTipoCuil(String tipoCuil) {
        this.tipoCuil = tipoCuil;
    }

    /**
     * Gets the cuil.
     *
     * @return the cuil
     */
    public String getCuil() {
        return cuil;
    }

    /**
     * Sets the cuil.
     *
     * @param cuil
     *            the new cuil
     */
    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    /**
     * Gets the primera vez.
     *
     * @return the primera vez
     */
    public Boolean getPrimeraVez() {
        return primeraVez;
    }

    /**
     * Sets the primera vez.
     *
     * @param primeraVez
     *            the new primera vez
     * @return the primera vez
     */
    public void setPrimeraVez(Boolean primeraVez) {
        this.primeraVez = primeraVez;
    }

    /**
     * Gets the fecha base recurrencia.
     *
     * @return the fechaBaseRecurrencia
     */
    public String getFechaBaseRecurrencia() {
        return fechaBaseRecurrencia;
    }

    /**
     * Sets the fecha base recurrencia.
     *
     * @param fechaBaseRecurrencia
     *            the fechaBaseRecurrencia to set
     */
    public void setFechaBaseRecurrencia(String fechaBaseRecurrencia) {
        this.fechaBaseRecurrencia = fechaBaseRecurrencia;
    }

    /**
     * Gets the frec recurrencia.
     *
     * @return the frecRecurrencia
     */
    public String getFrecRecurrencia() {
        return frecRecurrencia;
    }

    /**
     * Sets the frec recurrencia.
     *
     * @param frecRecurrencia
     *            the frecRecurrencia to set
     */
    public void setFrecRecurrencia(String frecRecurrencia) {
        this.frecRecurrencia = frecRecurrencia;
    }

    /**
     * Gets the max recurrencia.
     *
     * @return the maxRecurrencia
     */
    public String getMaxRecurrencia() {
        return maxRecurrencia;
    }

    /**
     * Sets the max recurrencia.
     *
     * @param maxRecurrencia
     *            the maxRecurrencia to set
     */
    public void setMaxRecurrencia(String maxRecurrencia) {
        this.maxRecurrencia = maxRecurrencia;
    }

    /**
     * Gets the tipo recurrencia.
     *
     * @return the tipoRecurrencia
     */
    public String getTipoRecurrencia() {
        return tipoRecurrencia;
    }

    /**
     * Sets the tipo recurrencia.
     *
     * @param tipoRecurrencia
     *            the tipoRecurrencia to set
     */
    public void setTipoRecurrencia(String tipoRecurrencia) {
        this.tipoRecurrencia = tipoRecurrencia;
    }

    /**
     * Gets the desafio.
     *
     * @return the desafio
     */
    public AutentificacionDTO getDesafio() {
        return desafio;
    }

    /**
     * Sets the desafio.
     *
     * @param desafio
     *            the new desafio
     */
    public void setDesafio(AutentificacionDTO desafio) {
        this.desafio = desafio;
    }

    /**
     * Checks if is cb.
     *
     * @return true, if is cb
     */
    public boolean isCb() {
        return cb;
    }

    /**
     * Sets the cb.
     *
     * @param cb
     *            the new cb
     */
    public void setCb(boolean cb) {
        this.cb = cb;
    }

    /**
     * Gets the saldo cuenta origen.
     *
     * @return the saldo cuenta origen
     */
    public String getSaldoCuentaOrigen() {
        return saldoCuentaOrigen;
    }

    /**
     * Sets the saldo cuenta origen.
     *
     * @param saldoCuentaOrigen
     *            the new saldo cuenta origen
     */
    public void setSaldoCuentaOrigen(String saldoCuentaOrigen) {
        this.saldoCuentaOrigen = saldoCuentaOrigen;
    }

    /**
     * Gets the abreviatura destino.
     *
     * @return the abreviatura destino
     */
    public String getAbreviaturaDestino() {
        return abreviaturaDestino;
    }

    /**
     * Sets the abreviatura destino.
     *
     * @param abreviaturaDestino
     *            the new abreviatura destino
     */
    public void setAbreviaturaDestino(String abreviaturaDestino) {
        this.abreviaturaDestino = abreviaturaDestino;
    }

    /**
     * Gets the abreviatura origen.
     *
     * @return the abreviatura origen
     */
    public String getAbreviaturaOrigen() {
        return abreviaturaOrigen;
    }

    /**
     * Sets the abreviatura origen.
     *
     * @param abreviaturaOrigen
     *            the new abreviatura origen
     */
    public void setAbreviaturaOrigen(String abreviaturaOrigen) {
        this.abreviaturaOrigen = abreviaturaOrigen;
    }

    /**
     * Gets the tiene pago programado.
     *
     * @return the tiene pago programado
     */
    public Boolean getTienePagoProgramado() {
        return tienePagoProgramado;
    }

    /**
     * Sets the tiene pago programado.
     *
     * @param tienePagoProgramado
     *            the new tiene pago programado
     */
    public void setTienePagoProgramado(Boolean tienePagoProgramado) {
        this.tienePagoProgramado = tienePagoProgramado;
    }

    /**
     * Gets the boton habilitado.
     *
     * @return the botonHabilitado
     */
    public Boolean getBotonHabilitado() {
        return botonHabilitado;
    }

    /**
     * Sets the boton habilitado.
     *
     * @param botonHabilitado
     *            the botonHabilitado to set
     */
    public void setBotonHabilitado(Boolean botonHabilitado) {
        this.botonHabilitado = botonHabilitado;
    }

    /**
     * Gets the importe text.
     *
     * @return the importeText
     */
    public String getImporteText() {
        return importeText;
    }

    /**
     * Sets the importe text.
     *
     * @param importeText
     *            the importeText to set
     */
    public void setImporteText(String importeText) {
        this.importeText = importeText;
    }

    /**
     * Gets the alias cuenta origen.
     *
     * @return the aliasCuentaOrigen
     */
    public String getAliasCuentaOrigen() {
        return aliasCuentaOrigen;
    }

    /**
     * Sets the alias cuenta origen.
     *
     * @param aliasCuentaOrigen
     *            the aliasCuentaOrigen to set
     */
    public void setAliasCuentaOrigen(String aliasCuentaOrigen) {
        this.aliasCuentaOrigen = aliasCuentaOrigen;
    }

    /**
     * Gets the nro cuenta origen.
     *
     * @return the nroCuentaOrigen
     */
    public String getNroCuentaOrigen() {
        return nroCuentaOrigen;
    }

    /**
     * Sets the nro cuenta origen.
     *
     * @param nroCuentaOrigen
     *            the nroCuentaOrigen to set
     */
    public void setNroCuentaOrigen(String nroCuentaOrigen) {
        this.nroCuentaOrigen = nroCuentaOrigen;
    }

    /**
     * Gets the checks if is saldo pesos negativo.
     *
     * @return the isSaldoPesosNegativo
     */
    public Boolean getIsSaldoPesosNegativo() {
        return isSaldoPesosNegativo;
    }

    /**
     * Sets the checks if is saldo pesos negativo.
     *
     * @param isSaldoPesosNegativo
     *            the isSaldoPesosNegativo to set
     */
    public void setIsSaldoPesosNegativo(Boolean isSaldoPesosNegativo) {
        this.isSaldoPesosNegativo = isSaldoPesosNegativo;
    }

    /**
     * Gets the nick name.
     *
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Sets the nick name.
     *
     * @param nickName
     *            the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    public Integer getCantDiasUltimoCambioClave() {
		return cantDiasUltimoCambioClave;
	}

	public void setCantDiasUltimoCambioClave(Integer cantDiasUltimoCambioClave) {
		this.cantDiasUltimoCambioClave = cantDiasUltimoCambioClave;
	}

	public Integer getCantDiasUltimoCambioToken() {
		return cantDiasUltimoCambioToken;
	}

	public void setCantDiasUltimoCambioToken(Integer cantDiasUltimoCambioToken) {
		this.cantDiasUltimoCambioToken = cantDiasUltimoCambioToken;
	}

    public Boolean getChallenge() {
        return isChallenge;
    }

    public void setChallenge(Boolean challenge) {
        isChallenge = challenge;
    }

    public BiocatchResponseDataDTO getBiocatchResponseData() {
        return biocatchResponseData;
    }

    public void setBiocatchResponseData(BiocatchResponseDataDTO biocatchResponseDataDTO) {
        this.biocatchResponseData = biocatchResponseDataDTO;
    }

    public float getScoringDestinatario() {
        return scoringDestinatario;
    }

    public void setScoringDestinatario(float scoringDestinatario) {
        this.scoringDestinatario = scoringDestinatario;
    }

    public Integer getCantDiasUltimoCambioMail() {
        return cantDiasUltimoCambioMail;
    }

    public void setCantDiasUltimoCambioMail(Integer cantDiasUltimoCambioMail) {
        this.cantDiasUltimoCambioMail = cantDiasUltimoCambioMail;
    }

    public boolean isPagoProgramado() {
        return isPagoProgramado;
    }

    public void setPagoProgramado(boolean pagoProgramado) {
        isPagoProgramado = pagoProgramado;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }


    public boolean isPif() {
        return pif;
    }

    public void setPif(boolean pif) {
        this.pif = pif;
    }

    /**
     * To string.
     *
     * @return the string
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).append(id).append(descripcionEmpleado).append(nickName)
                .append(tipoCuentaDestino).append(abreviaturaDestino).append(cuentaDestino).append(tipoPago)
                .append(concepto).append(tipoDocumento).append(documento).append(importe).append(tipoPersona)
                .append(tipoId).append(idCliente).append(fechaNac).append(nup).append(idTransaccion).append(abreviatura)
                .append(divisa).append(aliasOrigen).append(tipoCuentaOrigen).append(abreviaturaOrigen)
                .append(cuentaOrigen).append(nroRecurrencia).append(aliasDestino).append(tipoCuil).append(cuil)
                .append(primeraVez).append(fechaBaseRecurrencia).append(frecRecurrencia).append(maxRecurrencia)
                .append(tipoRecurrencia).append(saldoCuentaOrigen).append(importeText).append(cb)
                .append(tienePagoProgramado).append(botonHabilitado).append(aliasCuentaOrigen).append(nroCuentaOrigen)
                .append(isSaldoPesosNegativo).append(isChallenge).toString();
    }

}
