/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class ConsultaFondoOutEntity.
 */
public class ConsultaFondoOutEntity {

	/** The id. */
	private String id;

	/** The codigo fondo. */
	private String codigoFondo;

	/** The especie. */
	private String especie;

	/** The nombre fondo. */
	private String nombreFondo;

	/** The moneda. */
	private String moneda;

	/** The limite minimo suscripcion. */
	private String limiteMinimoSuscripcion;

	/** The limite maximo suscripcion. */
	private String limiteMaximoSuscripcion;

	/** The limite minimo rescate. */
	private String limiteMinimoRescate;

	/** The limite maximo rescate. */
	private String limiteMaximoRescate;

	/** The plazo efectivo. */
	private String plazoEfectivo;

	/** The contrato suscripcion. */
	private String contratoSuscripcion;

	/** The codigo agrupador. */
	private String codigoAgrupador;

	/** The orden agrupacion. */
	private String ordenAgrupacion;

	/** The descripcion. */
	private String descripcion;

	/** The link cartera. */
	private String linkCartera;

	/** The link regla. */
	private String linkRegla;

	/** The habilitar suscripcion. */
	private String habilitarSuscripcion;

	/** The agrupador suscripcion. */
	private String agrupadorSuscripcion;

	/** The habilitar rescate. */
	private String habilitarRescate;

	/** The habilitar transferencia. */
	private String habilitarTransferencia;

	/** The tipo banca. */
	private String tipoBanca;

	/** The id mensaje gastos. */
	private String idMensajeGastos;

	/** The descripcion larga. */
	private String descripcionLarga;

	/** The horario. */
	private String horario;

	/** The id legales informacion. */
	private String idLegalesInformacion;

	/** The id legales 3. */
	private String idLegales3;

	/** The descripcion adicional. */
	private String descripcionAdicional;

	/** The honorarios admin. */
	private String honorariosAdmin;

	/** The honorarios entrada. */
	private String honorariosEntrada;

	/** The honorarios salida. */
	private String honorariosSalida;

	/** the S_GTE. */
	private String sGTE;

	/** the S_DEP. */
	private String sDEP;
	
	/** The ex citi. */
	private String exCiti;
	
	private boolean repatriacion;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id fondo.
	 *
	 * @param id
	 *            the new id fondo
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the codigo fondo.
	 *
	 * @return the codigo fondo
	 */
	public String getCodigoFondo() {
		if (codigoFondo != null) {
			return codigoFondo.trim();
		}
		return codigoFondo;
	}

	/**
	 * Sets the codigo fondo.
	 *
	 * @param codigoFondo
	 *            the new codigo fondo
	 */
	public void setCodigoFondo(String codigoFondo) {
		this.codigoFondo = codigoFondo;
	}

	/**
	 * Gets the especie.
	 *
	 * @return the especie
	 */
	public String getEspecie() {
		return especie;
	}

	/**
	 * Sets the especie.
	 *
	 * @param especie
	 *            the new especie
	 */
	public void setEspecie(String especie) {
		this.especie = especie;
	}

	/**
	 * Gets the nombre fondo.
	 *
	 * @return the nombre fondo
	 */
	public String getNombreFondo() {
		return nombreFondo;
	}

	/**
	 * Sets the nombre fondo.
	 *
	 * @param nombreFondo
	 *            the new nombre fondo
	 */
	public void setNombreFondo(String nombreFondo) {
		this.nombreFondo = nombreFondo;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the limite minimo suscripcion.
	 *
	 * @return the limite minimo suscripcion
	 */
	public String getLimiteMinimoSuscripcion() {
		return limiteMinimoSuscripcion;
	}

	/**
	 * Sets the limite minimo suscripcion.
	 *
	 * @param limiteMinimoSuscripcion
	 *            the new limite minimo suscripcion
	 */
	public void setLimiteMinimoSuscripcion(String limiteMinimoSuscripcion) {
		this.limiteMinimoSuscripcion = limiteMinimoSuscripcion;
	}

	/**
	 * Gets the limite maximo suscripcion.
	 *
	 * @return the limite maximo suscripcion
	 */
	public String getLimiteMaximoSuscripcion() {
		return limiteMaximoSuscripcion;
	}

	/**
	 * Sets the limite maximo suscripcion.
	 *
	 * @param limiteMaximoSuscripcion
	 *            the new limite maximo suscripcion
	 */
	public void setLimiteMaximoSuscripcion(String limiteMaximoSuscripcion) {
		this.limiteMaximoSuscripcion = limiteMaximoSuscripcion;
	}

	/**
	 * Gets the limite minimo rescate.
	 *
	 * @return the limite minimo rescate
	 */
	public String getLimiteMinimoRescate() {
		return limiteMinimoRescate;
	}

	/**
	 * Sets the limite minimo rescate.
	 *
	 * @param limiteMinimoRescate
	 *            the new limite minimo rescate
	 */
	public void setLimiteMinimoRescate(String limiteMinimoRescate) {
		this.limiteMinimoRescate = limiteMinimoRescate;
	}

	/**
	 * Gets the limite maximo rescate.
	 *
	 * @return the limite maximo rescate
	 */
	public String getLimiteMaximoRescate() {
		return limiteMaximoRescate;
	}

	/**
	 * Sets the limite maximo rescate.
	 *
	 * @param limiteMaximoRescate
	 *            the new limite maximo rescate
	 */
	public void setLimiteMaximoRescate(String limiteMaximoRescate) {
		this.limiteMaximoRescate = limiteMaximoRescate;
	}

	/**
	 * Gets the plazo efectivo.
	 *
	 * @return the plazo efectivo
	 */
	public String getPlazoEfectivo() {
		return plazoEfectivo;
	}

	/**
	 * Sets the plazo efectivo.
	 *
	 * @param plazoEfectivo
	 *            the new plazo efectivo
	 */
	public void setPlazoEfectivo(String plazoEfectivo) {
		this.plazoEfectivo = plazoEfectivo;
	}

	/**
	 * Gets the contrato suscripcion.
	 *
	 * @return the contrato suscripcion
	 */
	public String getContratoSuscripcion() {
		return contratoSuscripcion;
	}

	/**
	 * Sets the contrato suscripcion.
	 *
	 * @param contratoSuscripcion
	 *            the new contrato suscripcion
	 */
	public void setContratoSuscripcion(String contratoSuscripcion) {
		this.contratoSuscripcion = contratoSuscripcion;
	}

	/**
	 * Gets the codigo agrupador.
	 *
	 * @return the codigo agrupador
	 */
	public String getCodigoAgrupador() {
		return codigoAgrupador;
	}

	/**
	 * Sets the codigo agrupador.
	 *
	 * @param codigoAgrupador
	 *            the new codigo agrupador
	 */
	public void setCodigoAgrupador(String codigoAgrupador) {
		this.codigoAgrupador = codigoAgrupador;
	}

	/**
	 * Gets the orden agrupacion.
	 *
	 * @return the orden agrupacion
	 */
	public String getOrdenAgrupacion() {
		return ordenAgrupacion;
	}

	/**
	 * Sets the orden agrupacion.
	 *
	 * @param ordenAgrupacion
	 *            the new orden agrupacion
	 */
	public void setOrdenAgrupacion(String ordenAgrupacion) {
		this.ordenAgrupacion = ordenAgrupacion;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the link cartera.
	 *
	 * @return the link cartera
	 */
	public String getLinkCartera() {
		return linkCartera;
	}

	/**
	 * Sets the link cartera.
	 *
	 * @param linkCartera
	 *            the new link cartera
	 */
	public void setLinkCartera(String linkCartera) {
		this.linkCartera = linkCartera;
	}

	/**
	 * Gets the link regla.
	 *
	 * @return the link regla
	 */
	public String getLinkRegla() {
		return linkRegla;
	}

	/**
	 * Sets the link regla.
	 *
	 * @param linkRegla
	 *            the new link regla
	 */
	public void setLinkRegla(String linkRegla) {
		this.linkRegla = linkRegla;
	}

	/**
	 * Gets the habilitar suscripcion.
	 *
	 * @return the habilitar suscripcion
	 */
	public String getHabilitarSuscripcion() {
		return habilitarSuscripcion;
	}

	/**
	 * Sets the habilitar suscripcion.
	 *
	 * @param habilitarSuscripcion
	 *            the new habilitar suscripcion
	 */
	public void setHabilitarSuscripcion(String habilitarSuscripcion) {
		this.habilitarSuscripcion = habilitarSuscripcion;
	}

	/**
	 * Gets the agrupador suscripcion.
	 *
	 * @return the agrupador suscripcion
	 */
	public String getAgrupadorSuscripcion() {
		return agrupadorSuscripcion;
	}

	/**
	 * Sets the agrupador suscripcion.
	 *
	 * @param agrupadorSuscripcion
	 *            the new agrupador suscripcion
	 */
	public void setAgrupadorSuscripcion(String agrupadorSuscripcion) {
		this.agrupadorSuscripcion = agrupadorSuscripcion;
	}

	/**
	 * Gets the habilitar rescate.
	 *
	 * @return the habilitar rescate
	 */
	public String getHabilitarRescate() {
		return habilitarRescate;
	}

	/**
	 * Sets the habilitar rescate.
	 *
	 * @param habilitarRescate
	 *            the new habilitar rescate
	 */
	public void setHabilitarRescate(String habilitarRescate) {
		this.habilitarRescate = habilitarRescate;
	}

	/**
	 * Gets the habilitar transferencia.
	 *
	 * @return the habilitar transferencia
	 */
	public String getHabilitarTransferencia() {
		return habilitarTransferencia;
	}

	/**
	 * Sets the habilitar transferencia.
	 *
	 * @param habilitarTransferencia
	 *            the new habilitar transferencia
	 */
	public void setHabilitarTransferencia(String habilitarTransferencia) {
		this.habilitarTransferencia = habilitarTransferencia;
	}

	/**
	 * Gets the tipo banca.
	 *
	 * @return the tipo banca
	 */
	public String getTipoBanca() {
		return tipoBanca;
	}

	/**
	 * Sets the tipo banca.
	 *
	 * @param tipoBanca
	 *            the new tipo banca
	 */
	public void setTipoBanca(String tipoBanca) {
		this.tipoBanca = tipoBanca;
	}

	/**
	 * Gets the id mensaje gastos.
	 *
	 * @return the id mensaje gastos
	 */
	public String getIdMensajeGastos() {
		return idMensajeGastos;
	}

	/**
	 * Sets the id mensaje gastos.
	 *
	 * @param idMensajeGastos
	 *            the new id mensaje gastos
	 */
	public void setIdMensajeGastos(String idMensajeGastos) {
		this.idMensajeGastos = idMensajeGastos;
	}

	/**
	 * Gets the descripcion larga.
	 *
	 * @return the descripcion larga
	 */
	public String getDescripcionLarga() {
		return descripcionLarga;
	}

	/**
	 * Sets the descripcion larga.
	 *
	 * @param descripcionLarga
	 *            the new descripcion larga
	 */
	public void setDescripcionLarga(String descripcionLarga) {
		this.descripcionLarga = descripcionLarga;
	}

	/**
	 * Gets the horario.
	 *
	 * @return the horario
	 */
	public String getHorario() {
		return horario;
	}

	/**
	 * Sets the horario.
	 *
	 * @param horario
	 *            the new horario
	 */
	public void setHorario(String horario) {
		this.horario = horario;
	}

	/**
	 * Gets the descripcion adicional.
	 *
	 * @return the descripcionAdicional
	 */
	public String getDescripcionAdicional() {
		return descripcionAdicional;
	}

	/**
	 * Sets the descripcion adicional.
	 *
	 * @param descripcionAdicional
	 *            the descripcionAdicional to set
	 */
	public void setDescripcionAdicional(String descripcionAdicional) {
		this.descripcionAdicional = descripcionAdicional;
	}

	/**
	 * Gets the honorarios salida.
	 *
	 * @return the honorariosSalida
	 */
	public String getHonorariosSalida() {
		return honorariosSalida;
	}

	/**
	 * Sets the honorarios salida.
	 *
	 * @param honorariosSalida
	 *            the honorariosSalida to set
	 */
	public void setHonorariosSalida(String honorariosSalida) {
		this.honorariosSalida = honorariosSalida;
	}

	/**
	 * Gets the id legales informacion.
	 *
	 * @return the idLegalesInformacion
	 */
	public String getIdLegalesInformacion() {
		return idLegalesInformacion;
	}

	/**
	 * Sets the id legales informacion.
	 *
	 * @param idLegalesInformacion
	 *            the idLegalesInformacion to set
	 */
	public void setIdLegalesInformacion(String idLegalesInformacion) {
		this.idLegalesInformacion = idLegalesInformacion;
	}

	/**
	 * Gets the id legales 3.
	 *
	 * @return the idLegales3
	 */
	public String getIdLegales3() {
		return idLegales3;
	}

	/**
	 * Sets the id legales 3.
	 *
	 * @param idLegales3
	 *            the idLegales3 to set
	 */
	public void setIdLegales3(String idLegales3) {
		this.idLegales3 = idLegales3;
	}

	/**
	 * Gets the honorarios entrada.
	 *
	 * @return the honorariosEntrada
	 */
	public String getHonorariosEntrada() {
		return honorariosEntrada;
	}

	/**
	 * Sets the honorarios entrada.
	 *
	 * @param honorariosEntrada
	 *            the honorariosEntrada to set
	 */
	public void setHonorariosEntrada(String honorariosEntrada) {
		this.honorariosEntrada = honorariosEntrada;
	}

	/**
	 * Gets the honorarios admin.
	 *
	 * @return the honorariosAdmin
	 */
	public String getHonorariosAdmin() {
		return honorariosAdmin;
	}

	/**
	 * Sets the honorarios admin.
	 *
	 * @param honorariosAdmin
	 *            the honorariosAdmin to set
	 */
	public void setHonorariosAdmin(String honorariosAdmin) {
		this.honorariosAdmin = honorariosAdmin;
	}

	/**
	 * Gets the s GTE.
	 *
	 * @return the s GTE
	 */
	public String getsGTE() {
		return sGTE;
	}

	/**
	 * Sets the s GTE.
	 *
	 * @param sGTE
	 *            the new s GTE
	 */
	public void setsGTE(String sGTE) {
		this.sGTE = sGTE;
	}

	/**
	 * Gets the s DEP.
	 *
	 * @return the s DEP
	 */
	public String getsDEP() {
		return sDEP;
	}

	/**
	 * Sets the s DEP.
	 *
	 * @param sDEP
	 *            the new s DEP
	 */
	public void setsDEP(String sDEP) {
		this.sDEP = sDEP;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("codigoFondo", codigoFondo).append("especie", especie)
				.append("nombreFondo", nombreFondo).append("moneda", moneda)
				.append("limiteMinimoSuscripcion", limiteMinimoSuscripcion)
				.append("limiteMaximoSuscripcion", limiteMaximoSuscripcion)
				.append("limiteMinimoRescate", limiteMinimoRescate).append("limiteMaximoRescate", limiteMaximoRescate)
				.append("plazoEfectivo", plazoEfectivo).append("contratoSuscripcion", contratoSuscripcion)
				.append("codigoAgrupador", codigoAgrupador).append("ordenAgrupacion", ordenAgrupacion)
				.append("descripcion", descripcion).append("linkCartera", linkCartera).append("linkRegla", linkRegla)
				.append("habilitarSuscripcion", habilitarSuscripcion)
				.append("agrupadorSuscripcion", agrupadorSuscripcion).append("habilitarRescate", habilitarRescate)
				.append("habilitarTransferencia", habilitarTransferencia).append("tipoBanca", tipoBanca)
				.append("idMensajeGastos", idMensajeGastos).append("descripcionLarga", descripcionLarga)
				.append("horario", horario).append("idLegalesInformacion", idLegalesInformacion)
				.append("idLegales3", idLegales3).append("descripcionAdicional", descripcionAdicional)
				.append("honorariosAdmin", honorariosAdmin).append("honorariosEntrada", honorariosEntrada)
				.append("honorariosSalida", honorariosSalida).toString();
	}

    /**
	 * Gets the ex citi.
	 *
	 * @return the ex citi
	 */
    public String getExCiti() {
        return exCiti;
    }

    /**
	 * Sets the ex citi.
	 *
	 * @param exCiti
	 *            the new ex citi
	 */
    public void setExCiti(String exCiti) {
        this.exCiti = exCiti;
    }

	public boolean isRepatriacion() {
		return repatriacion;
	}

	public void setRepatriacion(boolean repatriacion) {
		this.repatriacion = repatriacion;
	}
    
    

}
