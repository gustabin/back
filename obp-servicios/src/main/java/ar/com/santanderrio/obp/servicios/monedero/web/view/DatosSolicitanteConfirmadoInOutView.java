/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.web.view;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;

/**
 * The Class DatosSolicitanteConfirmadoInOutView.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosSolicitanteConfirmadoInOutView extends RsaDTOParaDesafio {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The apellido. */
    private String apellido;

    /** The nombre. */
    private String nombre;

    /** The fecha nacimiento. */
    private String fechaNacimiento;

    /** The doc tipo. */
    private String docTipo;

    /** The doc. */
    private String doc;

    /** The nro cuenta pesos seleccionada. */
    private String idCuentaSeleccionada;

    /** The pais nacimiento. */
    private String paisNacimiento;

    /** The pais. */
    private String pais;

    /** The id pais. */
    private String idPais;

    /** The sexo. */
    private String sexo;

    /** The estado civil. */
    private String estadoCivil;

    /** The nacionalidad. */
    private String nacionalidad;

    /** The id nacionalidad. */
    private String idNacionalidad;

    /** The calle. */
    private String calle;

    /** The calle nro. */
    private String calleNro;

    /** The piso. */
    private String piso;

    /** The depto. */
    private String depto;

    /** The localidad. */
    private String localidad;

    /** The cp. */
    private String cp;

    /** The provincia. */
    private String provincia;

    /** The telefono. */
    private String telefono;

    /** The importe seleccionado. */
    private String importeSeleccionado;

    /** The limite seleccionado. */
    private String limiteSeleccionado;

    /** The nro cuenta producto. */
    private String nroCuentaProducto;

    /** The cuenta pesos seleccionada. */
    private String cuentaPesosSeleccionada;

    /** The importe. */
    private String importe;

    /** The limite. */
    private String limite;

    /** The numero comprobante. */
    private String numeroComprobante;

    /** The nup. */
    // ver que desde front venga
    private String nup;

    /** The tipo doc. */
    private String tipoDoc;

    /** The cuit O cuil. */
    private String cuitOCuil;

    /** The adicional. */
    private boolean adicional;

    /** The nro cuenta. */
    private String nroCuenta;

    /** The comprobante. */
    private String comprobante;

    /** The fecha hora. */
    private String fechaHora;

    /** The mensaje. */
    private String mensaje;

    /** The legales SEO. */
    private String legalesSEO;

    /**
     * Instantiates a new datos solicitante confirmado in out view.
     */
    public DatosSolicitanteConfirmadoInOutView() {
        super(OperacionesRSAEnum.ALTA_TAG_MONEDERO);
    }

    /**
     * Gets the apellido.
     *
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Sets the apellido.
     *
     * @param apellido
     *            the new apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Gets the nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the nombre.
     *
     * @param nombre
     *            the new nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
     * Gets the doc tipo.
     *
     * @return the doc tipo
     */
    public String getDocTipo() {
        return docTipo;
    }

    /**
     * Gets the domicilio.
     *
     * @return the domicilio
     */
    public String getDomicilio() {
        return this.calle + " " + this.calleNro + " " + this.piso + " " + this.depto + " - " + this.cp + " " + " - "
                + this.localidad;
    }

    /**
     * Sets the doc tipo.
     *
     * @param docTipo
     *            the new doc tipo
     */
    public void setDocTipo(String docTipo) {
        this.docTipo = docTipo;
    }

    /**
     * Gets the doc.
     *
     * @return the doc
     */
    public String getDoc() {
        return doc;
    }

    /**
     * Sets the doc.
     *
     * @param doc
     *            the new doc
     */
    public void setDoc(String doc) {
        this.doc = doc;
    }

    /**
     * Gets the id cuenta seleccionada.
     *
     * @return the id cuenta seleccionada
     */
    public String getIdCuentaSeleccionada() {
        return idCuentaSeleccionada;
    }

    /**
     * Sets the id cuenta seleccionada.
     *
     * @param idCuentaSeleccionada
     *            the new id cuenta seleccionada
     */
    public void setIdCuentaSeleccionada(String idCuentaSeleccionada) {
        this.idCuentaSeleccionada = idCuentaSeleccionada;
    }

    /**
     * Gets the pais nacimiento.
     *
     * @return the pais nacimiento
     */
    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    /**
     * Sets the pais nacimiento.
     *
     * @param paisNacimiento
     *            the new pais nacimiento
     */
    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    /**
     * Gets the pais.
     *
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * Sets the pais.
     *
     * @param pais
     *            the new pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Gets the id pais.
     *
     * @return the id pais
     */
    public String getIdPais() {
        return idPais;
    }

    /**
     * Sets the id pais.
     *
     * @param idPais
     *            the new id pais
     */
    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    /**
     * Gets the sexo.
     *
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Sets the sexo.
     *
     * @param sexo
     *            the new sexo
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Gets the estado civil.
     *
     * @return the estado civil
     */
    public String getEstadoCivil() {
        return estadoCivil;
    }

    /**
     * Sets the estado civil.
     *
     * @param estadoCivil
     *            the new estado civil
     */
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    /**
     * Gets the nacionalidad.
     *
     * @return the nacionalidad
     */
    public String getNacionalidad() {
        return nacionalidad;
    }

    /**
     * Sets the nacionalidad.
     *
     * @param nacionalidad
     *            the new nacionalidad
     */
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    /**
     * Gets the id nacionalidad.
     *
     * @return the id nacionalidad
     */
    public String getIdNacionalidad() {
        return idNacionalidad;
    }

    /**
     * Sets the id nacionalidad.
     *
     * @param idNacionalidad
     *            the new id nacionalidad
     */
    public void setIdNacionalidad(String idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    /**
     * Gets the calle.
     *
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Sets the calle.
     *
     * @param calle
     *            the new calle
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Gets the calle nro.
     *
     * @return the calle nro
     */
    public String getCalleNro() {
        return calleNro;
    }

    /**
     * Sets the calle nro.
     *
     * @param calleNro
     *            the new calle nro
     */
    public void setCalleNro(String calleNro) {
        this.calleNro = calleNro;
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
     *            the new piso
     */
    public void setPiso(String piso) {
        this.piso = piso;
    }

    /**
     * Gets the depto.
     *
     * @return the depto
     */
    public String getDepto() {
        return depto;
    }

    /**
     * Sets the depto.
     *
     * @param depto
     *            the new depto
     */
    public void setDepto(String depto) {
        this.depto = depto;
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
     *            the new localidad
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * Gets the cp.
     *
     * @return the cp
     */
    public String getCp() {
        return cp;
    }

    /**
     * Sets the cp.
     *
     * @param cp
     *            the new cp
     */
    public void setCp(String cp) {
        this.cp = cp;
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
     *            the new provincia
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
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
     * Gets the importe seleccionado.
     *
     * @return the importe seleccionado
     */
    public String getImporteSeleccionado() {
        return importeSeleccionado;
    }

    /**
     * Sets the importe seleccionado.
     *
     * @param importeSeleccionado
     *            the new importe seleccionado
     */
    public void setImporteSeleccionado(String importeSeleccionado) {
        this.importeSeleccionado = importeSeleccionado;
    }

    /**
     * Gets the limite seleccionado.
     *
     * @return the limite seleccionado
     */
    public String getLimiteSeleccionado() {
        return limiteSeleccionado;
    }

    /**
     * Sets the limite seleccionado.
     *
     * @param limiteSeleccionado
     *            the new limite seleccionado
     */
    public void setLimiteSeleccionado(String limiteSeleccionado) {
        this.limiteSeleccionado = limiteSeleccionado;
    }

    /**
     * Gets the nro cuenta producto.
     *
     * @return the nro cuenta producto
     */
    public String getNroCuentaProducto() {
        return nroCuentaProducto;
    }

    /**
     * Sets the nro cuenta producto.
     *
     * @param nroCuentaProducto
     *            the new nro cuenta producto
     */
    public void setNroCuentaProducto(String nroCuentaProducto) {
        this.nroCuentaProducto = nroCuentaProducto;
    }

    /**
     * Gets the cuenta pesos seleccionada.
     *
     * @return the cuenta pesos seleccionada
     */
    public String getCuentaPesosSeleccionada() {
        return cuentaPesosSeleccionada;
    }

    /**
     * Sets the cuenta pesos seleccionada.
     *
     * @param cuentaPesosSeleccionada
     *            the new cuenta pesos seleccionada
     */
    public void setCuentaPesosSeleccionada(String cuentaPesosSeleccionada) {
        this.cuentaPesosSeleccionada = cuentaPesosSeleccionada;
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
     *            the new importe
     */
    public void setImporte(String importe) {
        this.importe = importe;
    }

    /**
     * Gets the limite.
     *
     * @return the limite
     */
    public String getLimite() {
        return limite;
    }

    /**
     * Sets the limite.
     *
     * @param limite
     *            the new limite
     */
    public void setLimite(String limite) {
        this.limite = limite;
    }

    /**
     * Gets the numero comprobante.
     *
     * @return the numero comprobante
     */
    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    /**
     * Sets the numero comprobante.
     *
     * @param numeroComprobante
     *            the new numero comprobante
     */
    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
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
     * Gets the tipo doc.
     *
     * @return the tipo doc
     */
    public String getTipoDoc() {
        return tipoDoc;
    }

    /**
     * Sets the tipo doc.
     *
     * @param tipoDoc
     *            the new tipo doc
     */
    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    /**
     * Gets the cuit O cuil.
     *
     * @return the cuit O cuil
     */
    public String getCuitOCuil() {
        return cuitOCuil;
    }

    /**
     * Sets the cuit O cuil.
     *
     * @param cuitOCuil
     *            the new cuit O cuil
     */
    public void setCuitOCuil(String cuitOCuil) {
        this.cuitOCuil = cuitOCuil;
    }

    /**
     * Checks if is adicional.
     *
     * @return true, if is adicional
     */
    public boolean isAdicional() {
        return adicional;
    }

    /**
     * Sets the adicional.
     *
     * @param adicional
     *            the new adicional
     */
    public void setAdicional(boolean adicional) {
        this.adicional = adicional;
    }

    /**
     * Gets the nro cuenta.
     *
     * @return the nro cuenta
     */
    public String getNroCuenta() {
        return nroCuenta;
    }

    /**
     * Sets the nro cuenta.
     *
     * @param nroCuenta
     *            the new nro cuenta
     */
    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
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
        result = prime * result + (adicional ? 1231 : 1237);
        result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
        result = prime * result + ((calle == null) ? 0 : calle.hashCode());
        result = prime * result + ((calleNro == null) ? 0 : calleNro.hashCode());
        result = prime * result + ((comprobante == null) ? 0 : comprobante.hashCode());
        result = prime * result + ((cp == null) ? 0 : cp.hashCode());
        result = prime * result + ((cuentaPesosSeleccionada == null) ? 0 : cuentaPesosSeleccionada.hashCode());
        result = prime * result + ((cuitOCuil == null) ? 0 : cuitOCuil.hashCode());
        result = prime * result + ((depto == null) ? 0 : depto.hashCode());
        result = prime * result + ((doc == null) ? 0 : doc.hashCode());
        result = prime * result + ((docTipo == null) ? 0 : docTipo.hashCode());
        result = prime * result + ((estadoCivil == null) ? 0 : estadoCivil.hashCode());
        result = prime * result + ((fechaHora == null) ? 0 : fechaHora.hashCode());
        result = prime * result + ((fechaNacimiento == null) ? 0 : fechaNacimiento.hashCode());
        result = prime * result + ((idCuentaSeleccionada == null) ? 0 : idCuentaSeleccionada.hashCode());
        result = prime * result + ((idNacionalidad == null) ? 0 : idNacionalidad.hashCode());
        result = prime * result + ((idPais == null) ? 0 : idPais.hashCode());
        result = prime * result + ((importe == null) ? 0 : importe.hashCode());
        result = prime * result + ((importeSeleccionado == null) ? 0 : importeSeleccionado.hashCode());
        result = prime * result + ((legalesSEO == null) ? 0 : legalesSEO.hashCode());
        result = prime * result + ((limite == null) ? 0 : limite.hashCode());
        result = prime * result + ((limiteSeleccionado == null) ? 0 : limiteSeleccionado.hashCode());
        result = prime * result + ((localidad == null) ? 0 : localidad.hashCode());
        result = prime * result + ((mensaje == null) ? 0 : mensaje.hashCode());
        result = prime * result + ((nacionalidad == null) ? 0 : nacionalidad.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((nroCuenta == null) ? 0 : nroCuenta.hashCode());
        result = prime * result + ((nroCuentaProducto == null) ? 0 : nroCuentaProducto.hashCode());
        result = prime * result + ((numeroComprobante == null) ? 0 : numeroComprobante.hashCode());
        result = prime * result + ((nup == null) ? 0 : nup.hashCode());
        result = prime * result + ((pais == null) ? 0 : pais.hashCode());
        result = prime * result + ((paisNacimiento == null) ? 0 : paisNacimiento.hashCode());
        result = prime * result + ((piso == null) ? 0 : piso.hashCode());
        result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
        result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
        result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
        result = prime * result + ((tipoDoc == null) ? 0 : tipoDoc.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DatosSolicitanteConfirmadoInOutView other = (DatosSolicitanteConfirmadoInOutView) obj;
        if (adicional != other.adicional)
            return false;
        if (apellido == null) {
            if (other.apellido != null)
                return false;
        } else if (!apellido.equals(other.apellido))
            return false;
        if (calle == null) {
            if (other.calle != null)
                return false;
        } else if (!calle.equals(other.calle))
            return false;
        if (calleNro == null) {
            if (other.calleNro != null)
                return false;
        } else if (!calleNro.equals(other.calleNro))
            return false;
        if (comprobante == null) {
            if (other.comprobante != null)
                return false;
        } else if (!comprobante.equals(other.comprobante))
            return false;
        if (cp == null) {
            if (other.cp != null)
                return false;
        } else if (!cp.equals(other.cp))
            return false;
        if (cuentaPesosSeleccionada == null) {
            if (other.cuentaPesosSeleccionada != null)
                return false;
        } else if (!cuentaPesosSeleccionada.equals(other.cuentaPesosSeleccionada))
            return false;
        if (cuitOCuil == null) {
            if (other.cuitOCuil != null)
                return false;
        } else if (!cuitOCuil.equals(other.cuitOCuil))
            return false;
        if (depto == null) {
            if (other.depto != null)
                return false;
        } else if (!depto.equals(other.depto))
            return false;
        if (doc == null) {
            if (other.doc != null)
                return false;
        } else if (!doc.equals(other.doc))
            return false;
        if (docTipo == null) {
            if (other.docTipo != null)
                return false;
        } else if (!docTipo.equals(other.docTipo))
            return false;
        if (estadoCivil == null) {
            if (other.estadoCivil != null)
                return false;
        } else if (!estadoCivil.equals(other.estadoCivil))
            return false;
        if (fechaHora == null) {
            if (other.fechaHora != null)
                return false;
        } else if (!fechaHora.equals(other.fechaHora))
            return false;
        if (fechaNacimiento == null) {
            if (other.fechaNacimiento != null)
                return false;
        } else if (!fechaNacimiento.equals(other.fechaNacimiento))
            return false;
        if (idCuentaSeleccionada == null) {
            if (other.idCuentaSeleccionada != null)
                return false;
        } else if (!idCuentaSeleccionada.equals(other.idCuentaSeleccionada))
            return false;
        if (idNacionalidad == null) {
            if (other.idNacionalidad != null)
                return false;
        } else if (!idNacionalidad.equals(other.idNacionalidad))
            return false;
        if (idPais == null) {
            if (other.idPais != null)
                return false;
        } else if (!idPais.equals(other.idPais))
            return false;
        if (importe == null) {
            if (other.importe != null)
                return false;
        } else if (!importe.equals(other.importe))
            return false;
        if (importeSeleccionado == null) {
            if (other.importeSeleccionado != null)
                return false;
        } else if (!importeSeleccionado.equals(other.importeSeleccionado))
            return false;
        if (legalesSEO == null) {
            if (other.legalesSEO != null)
                return false;
        } else if (!legalesSEO.equals(other.legalesSEO))
            return false;
        if (limite == null) {
            if (other.limite != null)
                return false;
        } else if (!limite.equals(other.limite))
            return false;
        if (limiteSeleccionado == null) {
            if (other.limiteSeleccionado != null)
                return false;
        } else if (!limiteSeleccionado.equals(other.limiteSeleccionado))
            return false;
        if (localidad == null) {
            if (other.localidad != null)
                return false;
        } else if (!localidad.equals(other.localidad))
            return false;
        if (mensaje == null) {
            if (other.mensaje != null)
                return false;
        } else if (!mensaje.equals(other.mensaje))
            return false;
        if (nacionalidad == null) {
            if (other.nacionalidad != null)
                return false;
        } else if (!nacionalidad.equals(other.nacionalidad))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (nroCuenta == null) {
            if (other.nroCuenta != null)
                return false;
        } else if (!nroCuenta.equals(other.nroCuenta))
            return false;
        if (nroCuentaProducto == null) {
            if (other.nroCuentaProducto != null)
                return false;
        } else if (!nroCuentaProducto.equals(other.nroCuentaProducto))
            return false;
        if (numeroComprobante == null) {
            if (other.numeroComprobante != null)
                return false;
        } else if (!numeroComprobante.equals(other.numeroComprobante))
            return false;
        if (nup == null) {
            if (other.nup != null)
                return false;
        } else if (!nup.equals(other.nup))
            return false;
        if (pais == null) {
            if (other.pais != null)
                return false;
        } else if (!pais.equals(other.pais))
            return false;
        if (paisNacimiento == null) {
            if (other.paisNacimiento != null)
                return false;
        } else if (!paisNacimiento.equals(other.paisNacimiento))
            return false;
        if (piso == null) {
            if (other.piso != null)
                return false;
        } else if (!piso.equals(other.piso))
            return false;
        if (provincia == null) {
            if (other.provincia != null)
                return false;
        } else if (!provincia.equals(other.provincia))
            return false;
        if (sexo == null) {
            if (other.sexo != null)
                return false;
        } else if (!sexo.equals(other.sexo))
            return false;
        if (telefono == null) {
            if (other.telefono != null)
                return false;
        } else if (!telefono.equals(other.telefono))
            return false;
        if (tipoDoc == null) {
            if (other.tipoDoc != null)
                return false;
        } else if (!tipoDoc.equals(other.tipoDoc))
            return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "DatosSolicitanteConfirmadoInOutView [apellido=" + apellido + ", nombre=" + nombre + ", fechaNacimiento="
                + fechaNacimiento + ", docTipo=" + docTipo + ", doc=" + doc + ", idCuentaSeleccionada="
                + idCuentaSeleccionada + ", paisNacimiento=" + paisNacimiento + ", pais=" + pais + ", idPais=" + idPais
                + ", sexo=" + sexo + ", estadoCivil=" + estadoCivil + ", nacionalidad=" + nacionalidad
                + ", idNacionalidad=" + idNacionalidad + ", calle=" + calle + ", calleNro=" + calleNro + ", piso="
                + piso + ", depto=" + depto + ", localidad=" + localidad + ", cp=" + cp + ", provincia=" + provincia
                + ", telefono=" + telefono + ", importeSeleccionado=" + importeSeleccionado + ", limiteSeleccionado="
                + limiteSeleccionado + ", nroCuentaProducto=" + nroCuentaProducto + ", cuentaPesosSeleccionada="
                + cuentaPesosSeleccionada + ", importe=" + importe + ", limite=" + limite + ", numeroComprobante="
                + numeroComprobante + ", nup=" + nup + ", tipoDoc=" + tipoDoc + ", cuitOCuil=" + cuitOCuil
                + ", adicional=" + adicional + ", nroCuenta=" + nroCuenta + "]";
    }

    /**
     * Gets the comprobante.
     *
     * @return the comprobante
     */
    public String getComprobante() {
        return comprobante;
    }

    /**
     * Sets the comprobante.
     *
     * @param comprobante
     *            the new comprobante
     */
    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    /**
     * Gets the fecha hora.
     *
     * @return the fecha hora
     */
    public String getFechaHora() {
        return fechaHora;
    }

    /**
     * Sets the fecha hora.
     *
     * @param fechaHora
     *            the new fecha hora
     */
    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
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
     * Sets the mensaje.
     *
     * @param mensaje
     *            the new mensaje
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Gets the legales SEO.
     *
     * @return the legales SEO
     */
    public String getLegalesSEO() {
        return legalesSEO;
    }

    /**
     * Sets the legales SEO.
     *
     * @param legalesSEO
     *            the new legales SEO
     */
    public void setLegalesSEO(String legalesSEO) {
        this.legalesSEO = legalesSEO;
    }
}
