/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.web.view;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.compraventa.web.view.SimulacionCompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitOutEntity;
import ar.com.santanderrio.obp.servicios.monedero.dto.DatosSolicitanteDTO;

/**
 * The Class DatosSolicitanteView.
 */
public class DatosSolicitanteView {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SimulacionCompraVentaDolarView.class);

	/** The apellido. */
	private String apellido;

	/** The nombre. */
	private String nombre;

	/** The fecha nacimiento. */
	private String fechaNacimiento;

	/** The doc tipo. */
	private String docTipo;

	/** The documento. */
	private String documento;

	/** The documento. */
	private String doc;

	/** The pais nacimiento. */
	private String paisNacimiento;

	/** The sexo. */
	private String sexo;

	/** The estado civil. */
	private String estadoCivil;

	/** The nacionalidad. */
	private String nacionalidad;

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

	/** The cuit O cuil. */
	private String cuitOCuil;

	/** Si existe en altair. */
	private boolean existe = false;

	/** The cuentas. */
	private List<CuentaMonederoView> cuentas;

	/** The importes. */
	private List<ImporteARecargarView> importes;

	/** The limites. */
	private List<LimiteDeRecargaMensualView> limites;

	/** The nro cuenta. */
	private String nroCuenta;

	/**
	 * Instantiates a new datos solicitante view.
	 */
	public DatosSolicitanteView() {
		// vacio
	}

	/**
	 * Instantiates a new datos solicitante view.
	 *
	 * @param dto
	 *            the dto
	 */
	public DatosSolicitanteView(DatosSolicitanteDTO dto) {
		this.setApellido(dto.getApellido());
		this.setNombre(dto.getNombre());
		this.setFechaNacimiento(dto.getFechaNacimiento());
		this.setDocTipo(dto.getDocTipo());
		this.setDocumento(dto.getDocumento());
		this.setDoc(dto.getDocumento());
		this.setPaisNacimiento(dto.getPaisNacimiento());
		this.setSexo(dto.getSexo());
		this.setEstadoCivil(dto.getEstadoCivil());
		this.setNacionalidad(dto.getNacionalidad());
		this.setCalle(dto.getCalle());
		this.setCalleNro(dto.getCalleNro());
		this.setPiso(dto.getPiso());
		this.setDepto(dto.getDepto());
		this.setLocalidad(dto.getLocalidad());
		this.setCp(dto.getCp());
		this.setProvincia(dto.getProvincia());
		this.setTelefono(dto.getTelefono());
		this.setCuitOCuil(dto.getTelefono());
		this.setCuitOCuil(dto.getCuitOCuil());
		this.setExiste(dto.isExiste());
	}

	/**
	 * 
	 * Author: Alejandro Leal Description: Se implementa un constructor
	 * sobrecargado para respetar la nueva Arquitectura de Entity In / Out, los
	 * campos de salida seran seteados aqui *.
	 *
	 * @param entity
	 *            the entity
	 */
	public DatosSolicitanteView(ConsultaPadronCuitOutEntity entity) {
		// FALTA: setear -->> bcraNroCuit
		this.setCuitOCuil(entity.getAbaNroCuit());
		this.setDocTipo(entity.getAbaTipoDocumento());
		this.setDocumento(entity.getAbaNroDocumento());
		this.setDoc(entity.getAbaNroDocumento());
		this.setNombre(WordUtils.capitalize(StringUtils.lowerCase(entity.getAbaNombre().trim())));
		this.setApellido(WordUtils.capitalize(StringUtils.lowerCase(entity.getAbaApellido().trim())));
		this.setFechaNacimiento(entity.getAbaFechaNacimiento());
		this.setSexo(entity.getAbaSexo());
		// FALTA: setear -->> abaTipoPersona
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
		try {
			this.fechaNacimiento = ISBANStringUtils.formatearFechaConAnio(fechaNacimiento);
		} catch (Exception e) {
			LOGGER.error("Error en la conversion de DTO a View en la fecha.", e);
			/** throw new CompraVentaDolaresException(e); */
		}
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
	 *            the new documento
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
	}

	/**
	 * Gets the doc.
	 *
	 * @return the doc
	 */
	/*
	 * @return the doc
	 */
	public String getDoc() {
		return doc;
	}

	/**
	 * Sets the doc.
	 *
	 * @param doc
	 *            the doc to set
	 */
	public void setDoc(String doc) {
		this.doc = doc;
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
	 * Gets the cuentas.
	 *
	 * @return the cuentas
	 */
	public List<CuentaMonederoView> getCuentas() {
		return cuentas;
	}

	/**
	 * Sets the cuentas.
	 *
	 * @param cuentas
	 *            the new cuentas
	 */
	public void setCuentas(List<CuentaMonederoView> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * Gets the importes.
	 *
	 * @return the importes
	 */
	public List<ImporteARecargarView> getImportes() {
		return importes;
	}

	/**
	 * Sets the importes.
	 *
	 * @param importes
	 *            the new importes
	 */
	public void setImportes(List<ImporteARecargarView> importes) {
		this.importes = importes;
	}

	/**
	 * Gets the limites.
	 *
	 * @return the limites
	 */
	public List<LimiteDeRecargaMensualView> getLimites() {
		return limites;
	}

	/**
	 * Sets the limites.
	 *
	 * @param limites
	 *            the new limites
	 */
	public void setLimites(List<LimiteDeRecargaMensualView> limites) {
		this.limites = limites;
	}

	/**
	 * Gets the logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return LOGGER;
	}

	/**
	 * Checks if is existe.
	 *
	 * @return true, if is existe
	 */
	public boolean isExiste() {
		return existe;
	}

	/**
	 * Sets the existe.
	 *
	 * @param existe
	 *            the new existe
	 */
	public void setExiste(boolean existe) {
		this.existe = existe;
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
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(apellido);
		hcb.append(nombre);
		hcb.append(fechaNacimiento);
		hcb.append(docTipo);
		hcb.append(documento);
		hcb.append(doc);
		hcb.append(paisNacimiento);
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
		hcb.append(existe);
		return hcb.toHashCode();
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
		DatosSolicitanteView other = (DatosSolicitanteView) obj;

		EqualsBuilder eb = new EqualsBuilder();

		eb.append(apellido, other.getApellido());
		eb.append(nombre, other.getNombre());
		eb.append(fechaNacimiento, other.getFechaNacimiento());
		eb.append(docTipo, other.getDocTipo());
		eb.append(documento, other.getDocumento());
		eb.append(doc, other.getDoc());
		eb.append(paisNacimiento, other.getPaisNacimiento());
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
		eb.append(cuitOCuil, other.getCuitOCuil());
		eb.append(existe, other.isExiste());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DatosSolicitanteView [apellido=" + apellido + ", nombre=" + nombre + ", fechaNacimiento="
				+ fechaNacimiento + ", docTipo=" + docTipo + ", documento=" + documento + ", paisNacimiento="
				+ paisNacimiento + ", sexo=" + sexo + ", estadoCivil=" + estadoCivil + ", nacionalidad=" + nacionalidad
				+ ", calle=" + calle + ", calleNro=" + calleNro + ", piso=" + piso + ", depto=" + depto + ", localidad="
				+ localidad + ", cp=" + cp + ", provincia=" + provincia + ", telefono=" + telefono + ", cuitOCuil="
				+ cuitOCuil + ", cuentas=" + cuentas + ", importes=" + importes + ", limites=" + limites + "]";
	}
}
