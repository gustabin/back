/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.entities;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;

/**
 * The Class DatosAltaTagMonedero.
 */
public class DatosAltaTagMonederoEntity extends ResumenCliente {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The cuenta pesos seleccionada. */
	private String cuentaPesosSeleccionada;

	/** The sucursal. */
	private String sucursal;

	/** The apellido embozado. */
	private String apellidoEmbozado;

	/** The nombre embozado. */
	private String nombreEmbozado;

	/** The secuencia domicilio. */
	private String secuenciaDomicilio;

	/** The secuencia telefono. */
	private String secuenciaTelefono;

	/** The limite mensual recarga. */
	private String limiteMensualRecarga;

	/** The modulo maximo recargas. */
	private String moduloMaximoRecargas;

	/** The marca emisora tarjeta origen principal. */
	private String marcaEmisoraTarjetaOrigenPrincipal;

	/** The nro tarjeta origen recargas principal. */
	private String nroTarjetaOrigenRecargasPrincipal;

	/** The marca emisora tarjeta origen secundaria. */
	private String marcaEmisoraTarjetaOrigenSecundaria;

	/** The nro tarjeta origen recargas secundaria. */
	private String nroTarjetaOrigenRecargasSecundaria;

	/** The nup. */
	private String nup;

	/** The apellido embozado adicional. */
	private String apellidoEmbozadoAdicional;

	/** The nombre embozado adicional. */
	private String nombreEmbozadoAdicional;

	/** The nup adicional. */
	private String nupAdicional;

	/** The bonificacion. */
	private String bonificacion;

	/** The categoria. */
	private String categoria;

	/** The importe A recargar. */
	private String importeARecargar;

	/** The pais de nacimiento. */
	private String paisDeNacimiento;

	/** The sexo. */
	private String sexo;

	/** The estado civil. */
	private String estadoCivil;

	/** The nacionalidad. */
	private String nacionalidad;

	/** The calle. */
	private String calle;

	/** The nro. */
	private String nro;

	/** The piso. */
	private String piso;

	/** The depto. */
	private String depto;

	/** The cp. */
	private String cp;

	/** The localidad. */
	private String localidad;

	/** The provincia. */
	private String provincia;

	/** The adicional. */
	private boolean adicional;

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Gets the cuenta pesos seleccionada.
	 *
	 * @return the cuentaPesosSeleccionada
	 */
	public String getCuentaPesosSeleccionada() {
		return cuentaPesosSeleccionada;
	}

	/**
	 * Sets the cuenta pesos seleccionada.
	 *
	 * @param cuentaPesosSeleccionada
	 *            the cuentaPesosSeleccionada to set
	 */
	public void setCuentaPesosSeleccionada(String cuentaPesosSeleccionada) {
		this.cuentaPesosSeleccionada = cuentaPesosSeleccionada;
	}

	/**
	 * Gets the sucursal.
	 *
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * Sets the sucursal.
	 *
	 * @param sucursal
	 *            the new sucursal
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * Gets the apellido embozado.
	 *
	 * @return the apellido embozado
	 */
	public String getApellidoEmbozado() {
		return apellidoEmbozado;
	}

	/**
	 * Sets the apellido embozado.
	 *
	 * @param apellidoEmbozado
	 *            the new apellido embozado
	 */
	public void setApellidoEmbozado(String apellidoEmbozado) {
		this.apellidoEmbozado = apellidoEmbozado;
	}

	/**
	 * Gets the nombre embozado.
	 *
	 * @return the nombre embozado
	 */
	public String getNombreEmbozado() {
		return nombreEmbozado;
	}

	/**
	 * Sets the nombre embozado.
	 *
	 * @param nombreEmbozado
	 *            the new nombre embozado
	 */
	public void setNombreEmbozado(String nombreEmbozado) {
		this.nombreEmbozado = nombreEmbozado;
	}

	/**
	 * Gets the secuencia domicilio.
	 *
	 * @return the secuencia domicilio
	 */
	public String getSecuenciaDomicilio() {
		return secuenciaDomicilio;
	}

	/**
	 * Sets the secuencia domicilio.
	 *
	 * @param secuenciaDomicilio
	 *            the new secuencia domicilio
	 */
	public void setSecuenciaDomicilio(String secuenciaDomicilio) {
		this.secuenciaDomicilio = secuenciaDomicilio;
	}

	/**
	 * Gets the secuencia telefono.
	 *
	 * @return the secuencia telefono
	 */
	public String getSecuenciaTelefono() {
		return secuenciaTelefono;
	}

	/**
	 * Sets the secuencia telefono.
	 *
	 * @param secuenciaTelefono
	 *            the new secuencia telefono
	 */
	public void setSecuenciaTelefono(String secuenciaTelefono) {
		this.secuenciaTelefono = secuenciaTelefono;
	}

	/**
	 * Gets the limite mensual recarga.
	 *
	 * @return the limite mensual recarga
	 */
	public String getLimiteMensualRecarga() {
		return limiteMensualRecarga;
	}

	/**
	 * Sets the limite mensual recarga.
	 *
	 * @param limiteMensualRecarga
	 *            the new limite mensual recarga
	 */
	public void setLimiteMensualRecarga(String limiteMensualRecarga) {
		this.limiteMensualRecarga = limiteMensualRecarga;
	}

	/**
	 * Gets the modulo maximo recargas.
	 *
	 * @return the modulo maximo recargas
	 */
	public String getModuloMaximoRecargas() {
		return moduloMaximoRecargas;
	}

	/**
	 * Sets the modulo maximo recargas.
	 *
	 * @param moduloMaximoRecargas
	 *            the new modulo maximo recargas
	 */
	public void setModuloMaximoRecargas(String moduloMaximoRecargas) {
		this.moduloMaximoRecargas = moduloMaximoRecargas;
	}

	/**
	 * Gets the marca emisora tarjeta origen principal.
	 *
	 * @return the marca emisora tarjeta origen principal
	 */
	public String getMarcaEmisoraTarjetaOrigenPrincipal() {
		return marcaEmisoraTarjetaOrigenPrincipal;
	}

	/**
	 * Sets the marca emisora tarjeta origen principal.
	 *
	 * @param marcaEmisoraTarjetaOrigenPrincipal
	 *            the new marca emisora tarjeta origen principal
	 */
	public void setMarcaEmisoraTarjetaOrigenPrincipal(String marcaEmisoraTarjetaOrigenPrincipal) {
		this.marcaEmisoraTarjetaOrigenPrincipal = marcaEmisoraTarjetaOrigenPrincipal;
	}

	/**
	 * Gets the nro tarjeta origen recargas principal.
	 *
	 * @return the nro tarjeta origen recargas principal
	 */
	public String getNroTarjetaOrigenRecargasPrincipal() {
		return nroTarjetaOrigenRecargasPrincipal;
	}

	/**
	 * Sets the nro tarjeta origen recargas principal.
	 *
	 * @param nroTarjetaOrigenRecargasPrincipal
	 *            the new nro tarjeta origen recargas principal
	 */
	public void setNroTarjetaOrigenRecargasPrincipal(String nroTarjetaOrigenRecargasPrincipal) {
		this.nroTarjetaOrigenRecargasPrincipal = nroTarjetaOrigenRecargasPrincipal;
	}

	/**
	 * Gets the marca emisora tarjeta origen secundaria.
	 *
	 * @return the marca emisora tarjeta origen secundaria
	 */
	public String getMarcaEmisoraTarjetaOrigenSecundaria() {
		return marcaEmisoraTarjetaOrigenSecundaria;
	}

	/**
	 * Sets the marca emisora tarjeta origen secundaria.
	 *
	 * @param marcaEmisoraTarjetaOrigenSecundaria
	 *            the new marca emisora tarjeta origen secundaria
	 */
	public void setMarcaEmisoraTarjetaOrigenSecundaria(String marcaEmisoraTarjetaOrigenSecundaria) {
		this.marcaEmisoraTarjetaOrigenSecundaria = marcaEmisoraTarjetaOrigenSecundaria;
	}

	/**
	 * Gets the nro tarjeta origen recargas secundaria.
	 *
	 * @return the nro tarjeta origen recargas secundaria
	 */
	public String getNroTarjetaOrigenRecargasSecundaria() {
		return nroTarjetaOrigenRecargasSecundaria;
	}

	/**
	 * Sets the nro tarjeta origen recargas secundaria.
	 *
	 * @param nroTarjetaOrigenRecargasSecundaria
	 *            the new nro tarjeta origen recargas secundaria
	 */
	public void setNroTarjetaOrigenRecargasSecundaria(String nroTarjetaOrigenRecargasSecundaria) {
		this.nroTarjetaOrigenRecargasSecundaria = nroTarjetaOrigenRecargasSecundaria;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente#getNup()
	 */
	@Override
	public String getNup() {
		return nup;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente#setNup(java
	 * .lang.String)
	 */
	@Override
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the apellido embozado adicional.
	 *
	 * @return the apellido embozado adicional
	 */
	public String getApellidoEmbozadoAdicional() {
		return apellidoEmbozadoAdicional;
	}

	/**
	 * Sets the apellido embozado adicional.
	 *
	 * @param apellidoEmbozadoAdicional
	 *            the new apellido embozado adicional
	 */
	public void setApellidoEmbozadoAdicional(String apellidoEmbozadoAdicional) {
		this.apellidoEmbozadoAdicional = apellidoEmbozadoAdicional;
	}

	/**
	 * Gets the nombre embozado adicional.
	 *
	 * @return the nombre embozado adicional
	 */
	public String getNombreEmbozadoAdicional() {
		return nombreEmbozadoAdicional;
	}

	/**
	 * Sets the nombre embozado adicional.
	 *
	 * @param nombreEmbozadoAdicional
	 *            the new nombre embozado adicional
	 */
	public void setNombreEmbozadoAdicional(String nombreEmbozadoAdicional) {
		this.nombreEmbozadoAdicional = nombreEmbozadoAdicional;
	}

	/**
	 * Gets the nup adicional.
	 *
	 * @return the nup adicional
	 */
	public String getNupAdicional() {
		return nupAdicional;
	}

	/**
	 * Sets the nup adicional.
	 *
	 * @param nupAdicional
	 *            the new nup adicional
	 */
	public void setNupAdicional(String nupAdicional) {
		this.nupAdicional = nupAdicional;
	}

	/**
	 * Gets the bonificacion.
	 *
	 * @return the bonificacion
	 */
	public String getBonificacion() {
		return bonificacion;
	}

	/**
	 * Sets the bonificacion.
	 *
	 * @param bonificacion
	 *            the new bonificacion
	 */
	public void setBonificacion(String bonificacion) {
		this.bonificacion = bonificacion;
	}

	/**
	 * Gets the categoria.
	 *
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Sets the categoria.
	 *
	 * @param categoria
	 *            the new categoria
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Gets the importe A recargar.
	 *
	 * @return the importe A recargar
	 */
	public String getImporteARecargar() {
		return importeARecargar;
	}

	/**
	 * Sets the importe A recargar.
	 *
	 * @param importeARecargar
	 *            the new importe A recargar
	 */
	public void setImporteARecargar(String importeARecargar) {
		this.importeARecargar = importeARecargar;
	}

	/**
	 * Gets the pais de nacimiento.
	 *
	 * @return the pais de nacimiento
	 */
	public String getPaisDeNacimiento() {
		return paisDeNacimiento;
	}

	/**
	 * Sets the pais de nacimiento.
	 *
	 * @param paisDeNacimiento
	 *            the new pais de nacimiento
	 */
	public void setPaisDeNacimiento(String paisDeNacimiento) {
		this.paisDeNacimiento = paisDeNacimiento;
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
	 * Gets the nro.
	 *
	 * @return the nro
	 */
	public String getNro() {
		return nro;
	}

	/**
	 * Sets the nro.
	 *
	 * @param nro
	 *            the new nro
	 */
	public void setNro(String nro) {
		this.nro = nro;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = hashCode1(prime, result);
		result = prime * result
				+ ((nroTarjetaOrigenRecargasPrincipal == null) ? 0 : nroTarjetaOrigenRecargasPrincipal.hashCode());
		result = prime * result
				+ ((marcaEmisoraTarjetaOrigenSecundaria == null) ? 0 : marcaEmisoraTarjetaOrigenSecundaria.hashCode());
		result = prime * result
				+ ((nroTarjetaOrigenRecargasSecundaria == null) ? 0 : nroTarjetaOrigenRecargasSecundaria.hashCode());
		result = prime * result + ((nup == null) ? 0 : nup.hashCode());
		result = prime * result + ((apellidoEmbozadoAdicional == null) ? 0 : apellidoEmbozadoAdicional.hashCode());
		result = prime * result + ((nombreEmbozadoAdicional == null) ? 0 : nombreEmbozadoAdicional.hashCode());
		result = prime * result + ((nupAdicional == null) ? 0 : nupAdicional.hashCode());
		result = prime * result + ((bonificacion == null) ? 0 : bonificacion.hashCode());
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		return result;
	}

	/**
	 * Hash code 1.
	 *
	 * @param prime
	 *            the prime
	 * @param result1
	 *            the result 1
	 * @return the int
	 */
	private int hashCode1(final int prime, int result1) {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(apellidoEmbozado);
		hcb.append(nombreEmbozado);
		hcb.append(secuenciaDomicilio);
		hcb.append(secuenciaTelefono);
		hcb.append(limiteMensualRecarga);
		hcb.append(moduloMaximoRecargas);
		hcb.append(marcaEmisoraTarjetaOrigenPrincipal);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente#equals(java
	 * .lang.Object)
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
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente#toString()
	 */
	@Override
	public String toString() {

		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("apellidoEmbozado", apellidoEmbozado)
				.append("nombreEmbozado", nombreEmbozado).append("secuenciaDomicilio", secuenciaDomicilio)
				.append("secuenciaTelefono", secuenciaTelefono).append("limiteMensualRecarga", limiteMensualRecarga)
				.append("moduloMaximoRecargas", moduloMaximoRecargas)
				.append("marcaEmisoraTarjetaOrigenPrincipal", marcaEmisoraTarjetaOrigenPrincipal)
				.append("nroTarjetaOrigenRecargasPrincipal", nroTarjetaOrigenRecargasPrincipal)
				.append("marcaEmisoraTarjetaOrigenSecundaria", marcaEmisoraTarjetaOrigenSecundaria)
				.append("nroTarjetaOrigenRecargasSecundaria", nroTarjetaOrigenRecargasSecundaria).append("nup", nup)
				.append("apellidoEmbozadoAdicional", apellidoEmbozadoAdicional)
				.append("nombreEmbozadoAdicional", nombreEmbozadoAdicional).append("nupAdicional", nupAdicional)
				.append("bonificacion", bonificacion).append("categoria", categoria).toString();
	}
}
