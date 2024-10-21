/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.merlin.entities;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class DatosDeDomicilioView.
 */
public class DatosMerlinInEntity {

    /** The cliente. */
    private Cliente cliente;

    /** The nup. */
    private String nup;

    /** The tipo de domicilio. */
    private String tipoDeDomicilio;

    /** The secuencia de domicilio. */
    private String secuenciaDeDomicilio;

    /** The nombre calle. */
    private String nombreCalle;

    /** The numero bloque. */
    private String numeroBloque;

    /** The codigo postal. */
    private String codigoPostal;

    /** The provincia. */
    private String provincia;

    /** The localidad. */
    private String localidad;

    /** The barrio. */
    private String barrio;

    /** The piso. */
    private String piso;

    /** The departamento. */
    private String departamento;

    /** The timestamp. */
    private String timestamp;

    /** The telediscado. */
    private String telediscado;

    /** The telefono. */
    private String telefono;

    /** The is padron. */
    private boolean isPadron;

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
     *            the new cliente
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
     *            the nup to set
     */
    public void setNup(String nup) {
        this.nup = nup;
    }

    /**
     * Gets the tipo de domicilio.
     *
     * @return the tipoDeDomicilio
     */
    public String getTipoDeDomicilio() {
        return tipoDeDomicilio;
    }

    /**
     * Sets the tipo de domicilio.
     *
     * @param tipoDeDomicilio
     *            the tipoDeDomicilio to set
     */
    public void setTipoDeDomicilio(String tipoDeDomicilio) {
        this.tipoDeDomicilio = tipoDeDomicilio;
    }

    /**
     * Gets the secuencia de domicilio.
     *
     * @return the secuenciaDeDomicilio
     */
    public String getSecuenciaDeDomicilio() {
        return secuenciaDeDomicilio;
    }

    /**
     * Sets the secuencia de domicilio.
     *
     * @param secuenciaDeDomicilio
     *            the secuenciaDeDomicilio to set
     */
    public void setSecuenciaDeDomicilio(String secuenciaDeDomicilio) {
        this.secuenciaDeDomicilio = secuenciaDeDomicilio;
    }

    /**
     * Gets the nombre calle.
     *
     * @return the nombreCalle
     */
    public String getNombreCalle() {
        return nombreCalle;
    }

    /**
     * Sets the nombre calle.
     *
     * @param nombreCalle
     *            the nombreCalle to set
     */
    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    /**
     * Gets the numero bloque.
     *
     * @return the numeroBloque
     */
    public String getNumeroBloque() {
        return numeroBloque;
    }

    /**
     * Sets the numero bloque.
     *
     * @param numeroBloque
     *            the numeroBloque to set
     */
    public void setNumeroBloque(String numeroBloque) {
        this.numeroBloque = numeroBloque;
    }

    /**
     * Gets the codigo postal.
     *
     * @return the codigoPostal
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Sets the codigo postal.
     *
     * @param codigoPostal
     *            the codigoPostal to set
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Gets the provincia.
     *
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Sets the provincia.
     *
     * @param provincia
     *            the provincia to set
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Gets the localidad.
     *
     * @return the localidad
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Sets the localidad.
     *
     * @param localidad
     *            the localidad to set
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * Gets the barrio.
     *
     * @return the barrio
     */
    public String getBarrio() {
        return barrio;
    }

    /**
     * Sets the barrio.
     *
     * @param barrio
     *            the barrio to set
     */
    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    /**
     * Gets the piso.
     *
     * @return the piso
     */
    public String getPiso() {
        return piso;
    }

    /**
     * Sets the piso.
     *
     * @param piso
     *            the piso to set
     */
    public void setPiso(String piso) {
        this.piso = piso;
    }

    /**
     * Gets the departamento.
     *
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Sets the departamento.
     *
     * @param departamento
     *            the departamento to set
     */
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    /**
     * Gets the timestamp.
     *
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp.
     *
     * @param timestamp
     *            the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the telediscado.
     *
     * @return the telediscado
     */
    public String getTelediscado() {
        return telediscado;
    }

    /**
     * Sets the telediscado.
     *
     * @param telediscado
     *            the new telediscado
     */
    public void setTelediscado(String telediscado) {
        this.telediscado = telediscado;
    }

    /**
     * Gets the telefono.
     *
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Sets the telefono.
     *
     * @param telefono
     *            the new telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
	 * Gets the checks if is padron.
	 *
	 * @return the checks if is padron
	 */
    public boolean getIsPadron() {
        return isPadron;
    }

    /**
	 * Sets the padron.
	 *
	 * @param isPadron
	 *            the new padron
	 */
    public void setPadron(boolean isPadron) {
        this.isPadron = isPadron;
    }

    /**
	 * Gets the secuencia de domicilio.
	 *
	 * @return the secuencia de domicilio
	 */
    public String obtenerSecuenciaDeDomicilio() {
        return new StringBuilder().append(StringUtils.trimToEmpty(this.nombreCalle)).append("~")
                .append(StringUtils.trimToEmpty(this.numeroBloque)).append("~")
                .append(StringUtils.trimToEmpty(this.piso)).append("~")
                .append(StringUtils.trimToEmpty(this.departamento)).append("~")
                .append(StringUtils.trimToEmpty(this.localidad)).append("~")
                .append(StringUtils.trimToEmpty(this.codigoPostal)) // .append("~") (Lo dejo como estaba)
                .append(StringUtils.trimToEmpty(this.telediscado)).append("~")
                .append(StringUtils.trimToEmpty(this.telefono)).toString();
    }

    /**
	 * Obtener secuencia de domicilio busqueda.
	 *
	 * @return the string
	 */
    public String obtenerSecuenciaDeDomicilioBusqueda() {
        return new StringBuilder().append(StringUtils.trimToEmpty(this.nombreCalle)).append("~")
                .append(StringUtils.trimToEmpty(this.numeroBloque)).append("~")
                .append(StringUtils.trimToEmpty(this.piso)).append("~")
                .append(StringUtils.trimToEmpty(this.departamento)).append("~")
                .append(StringUtils.trimToEmpty(this.localidad)).append("~")
                .append(StringUtils.trimToEmpty(this.codigoPostal)).append("~")
                .append(StringUtils.trimToEmpty(StringUtils.removeStart(this.provincia, "0"))).append("~")
                .append("0").append(StringUtils.trimToEmpty(this.telediscado)).append("~")
                .append(StringUtils.trimToEmpty(StringUtils.remove(this.telefono, '-')))
                .append(StringUtils.rightPad("", 10, "~")).toString();
    }

}
