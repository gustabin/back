/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.entities;

/**
 * The Class Tag.
 */
public class TagEntity {

	/** The id tag. */
	private String idTag;

	/** The tag tag. */
	private String tagTag;

	/** The id banco. */
	private String idBanco;

	/** The apellido. */
	private String apellido;

	/** The nombre. */
	private String nombre;

	/** The clte limite mensual recarga. */
	private String clteLimiteMensualRecarga;

	/** The clte modulo recarga. */
	private String clteModuloRecarga;

	/** The clte cod tipo frecuencia. */
	private String clteCodTipoFrecuencia;

	/** The bco limite recarga mensual TAG. */
	private String bcoLimiteRecargaMensualTAG;

	/** The clte cant frecuencia. */
	private String clteCantFrecuencia;

	/** The saldo. */
	private String saldo;

	/** The fecha saldo. */
	private String fechaSaldo;

	/** The limite disponible. */
	private String limiteDisponible;

	/** The medio de recarga. */
	private ObtenerTrxTagMedioDeRecargaEntity medioDeRecarga;

	/** The categoria. */
	private String categoria;

	/** The estado. */
	private String estado;

	/**
	 * Gets the id tag.
	 *
	 * @return the id tag
	 */
	public String getIdTag() {
		return idTag;
	}

	/**
	 * Sets the id tag.
	 *
	 * @param idTag
	 *            the new id tag
	 */
	public void setIdTag(String idTag) {
		this.idTag = idTag;
	}

	/**
	 * Gets the tag.
	 *
	 * @return the tag
	 */
	public String getTag() {
		return tagTag;
	}

	/**
	 * Sets the tag.
	 *
	 * @param tag
	 *            the new tag
	 */
	public void setTag(String tag) {
		this.tagTag = tag;
	}

	/**
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}

	/**
	 * Sets the saldo.
	 *
	 * @param saldo
	 *            the new saldo
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	/**
	 * Gets the medio de recarga.
	 *
	 * @return the medio de recarga
	 */
	public ObtenerTrxTagMedioDeRecargaEntity getMedioDeRecarga() {
		return medioDeRecarga;
	}

	/**
	 * Sets the medio de recarga.
	 *
	 * @param medioDeRecarga
	 *            the new medio de recarga
	 */
	public void setMedioDeRecarga(ObtenerTrxTagMedioDeRecargaEntity medioDeRecarga) {
		this.medioDeRecarga = medioDeRecarga;
	}

	/**
	 * Gets the clte limite mensual recarga.
	 *
	 * @return the clte limite mensual recarga
	 */
	public String getClteLimiteMensualRecarga() {
		return clteLimiteMensualRecarga;
	}

	/**
	 * Sets the clte limite mensual recarga.
	 *
	 * @param clteLimiteMensualRecarga
	 *            the new clte limite mensual recarga
	 */
	public void setClteLimiteMensualRecarga(String clteLimiteMensualRecarga) {
		this.clteLimiteMensualRecarga = clteLimiteMensualRecarga;
	}

	/**
	 * Gets the clte modulo recarga.
	 *
	 * @return the clte modulo recarga
	 */
	public String getClteModuloRecarga() {
		return clteModuloRecarga;
	}

	/**
	 * Sets the clte modulo recarga.
	 *
	 * @param clteModuloRecarga
	 *            the new clte modulo recarga
	 */
	public void setClteModuloRecarga(String clteModuloRecarga) {
		this.clteModuloRecarga = clteModuloRecarga;
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
	 * Gets the id banco.
	 *
	 * @return the id banco
	 */
	public String getIdBanco() {
		return idBanco;
	}

	/**
	 * Sets the id banco.
	 *
	 * @param idBanco
	 *            the new id banco
	 */
	public void setIdBanco(String idBanco) {
		this.idBanco = idBanco;
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
	 * Gets the clte cod tipo frecuencia.
	 *
	 * @return the clte cod tipo frecuencia
	 */
	public String getClteCodTipoFrecuencia() {
		return clteCodTipoFrecuencia;
	}

	/**
	 * Sets the clte cod tipo frecuencia.
	 *
	 * @param clteCodTipoFrecuencia
	 *            the new clte cod tipo frecuencia
	 */
	public void setClteCodTipoFrecuencia(String clteCodTipoFrecuencia) {
		this.clteCodTipoFrecuencia = clteCodTipoFrecuencia;
	}

	/**
	 * Gets the bco limite recarga mensual TAG.
	 *
	 * @return the bco limite recarga mensual TAG
	 */
	public String getBcoLimiteRecargaMensualTAG() {
		return bcoLimiteRecargaMensualTAG;
	}

	/**
	 * Sets the bco limite recarga mensual TAG.
	 *
	 * @param bcoLimiteRecargaMensualTAG
	 *            the new bco limite recarga mensual TAG
	 */
	public void setBcoLimiteRecargaMensualTAG(String bcoLimiteRecargaMensualTAG) {
		this.bcoLimiteRecargaMensualTAG = bcoLimiteRecargaMensualTAG;
	}

	/**
	 * Gets the clte cant frecuencia.
	 *
	 * @return the clte cant frecuencia
	 */
	public String getClteCantFrecuencia() {
		return clteCantFrecuencia;
	}

	/**
	 * Sets the clte cant frecuencia.
	 *
	 * @param clteCantFrecuencia
	 *            the new clte cant frecuencia
	 */
	public void setClteCantFrecuencia(String clteCantFrecuencia) {
		this.clteCantFrecuencia = clteCantFrecuencia;
	}

	/**
	 * Gets the fecha saldo.
	 *
	 * @return the fecha saldo
	 */
	public String getFechaSaldo() {
		return fechaSaldo;
	}

	/**
	 * Sets the fecha saldo.
	 *
	 * @param fechaSaldo
	 *            the new fecha saldo
	 */
	public void setFechaSaldo(String fechaSaldo) {
		this.fechaSaldo = fechaSaldo;
	}

	/**
	 * Gets the limite disponible.
	 *
	 * @return the limite disponible
	 */
	public String getLimiteDisponible() {
		return limiteDisponible;
	}

	/**
	 * Sets the limite disponible.
	 *
	 * @param limiteDisponible
	 *            the new limite disponible
	 */
	public void setLimiteDisponible(String limiteDisponible) {
		this.limiteDisponible = limiteDisponible;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
