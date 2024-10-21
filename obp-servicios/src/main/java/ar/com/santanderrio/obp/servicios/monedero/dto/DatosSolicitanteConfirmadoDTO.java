/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.dto;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * The Class DatosSolicitanteConfirmadoView.
 */
public class DatosSolicitanteConfirmadoDTO {

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
	 * Gets the domicilio.
	 *
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return this.calle + " " + this.calleNro + " " + this.piso + " " + this.depto + " - " + this.cp + " " + " - "
				+ this.localidad;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(apellido);
		hcb.append(nombre);
		hcb.append(fechaNacimiento);
		hcb.append(docTipo);
		hcb.append(doc);
		hcb.append(pais);
		hcb.append(sexo);
		hcb.append(estadoCivil);
		hcb.append(nacionalidad);
		hcb.append(calle);
		hcb.append(calleNro);
		hcb.append(piso);
		hcb.append(depto);
		hcb.append(localidad);
		hcb.append(cp);
		hcb.append(provincia);
		hcb.append(telefono);
		hcb.append(importe);
		hcb.append(limite);
		hcb.append(numeroComprobante);
		hcb.append(adicional);
		hcb.append(nup);
		hcb.append(nroCuenta);
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
		DatosSolicitanteConfirmadoDTO other = (DatosSolicitanteConfirmadoDTO) obj;

		EqualsBuilder eb = new EqualsBuilder();

		eb.append(apellido, other.getApellido());
		eb.append(nombre, other.getNombre());
		eb.append(fechaNacimiento, other.getFechaNacimiento());
		eb.append(docTipo, other.getDocTipo());
		eb.append(doc, other.getDoc());
		eb.append(pais, other.getPais());
		eb.append(sexo, other.getSexo());
		eb.append(estadoCivil, other.getEstadoCivil());
		eb.append(nacionalidad, other.getNacionalidad());
		eb.append(calle, other.getCalle());
		eb.append(calleNro, other.getCalleNro());
		eb.append(piso, other.getPiso());
		eb.append(depto, other.getDepto());
		eb.append(localidad, other.getLocalidad());
		eb.append(cp, other.getCp());
		eb.append(provincia, other.getProvincia());
		eb.append(telefono, other.getTelefono());
		eb.append(importe, other.getImporte());
		eb.append(limite, other.getLimite());
		eb.append(numeroComprobante, other.getNumeroComprobante());
		eb.append(adicional, other.isAdicional());
		eb.append(nup, other.getNup());
		eb.append(nroCuenta, other.getNroCuenta());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DatosSolicitanteConfirmadoView [apellido=" + apellido + ", nombre=" + nombre + ", fechaNacimiento="
				+ fechaNacimiento + ", docTipo=" + docTipo + ", doc=" + doc + ", pais=" + pais + ", sexo=" + sexo
				+ ", estadoCivil=" + estadoCivil + ", nacionalidad=" + nacionalidad + ", calle=" + calle + ", calleNro="
				+ calleNro + ", piso=" + piso + ", depto=" + depto + ", localidad=" + localidad + ", cp=" + cp
				+ ", provincia=" + provincia + ", telefono=" + telefono + ", nroCuentaProducto=" + nroCuentaProducto
				+ ", cuentaPesosSeleccionada=" + cuentaPesosSeleccionada + ", importe=" + importe + ", limite=" + limite
				+ ", numeroComprobante=" + numeroComprobante + ", adicional=" + adicional + ", nup=" + nup + "]";
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
}
