/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que modela una cuenta.
 * 
 * @author marcelo.ruiz
 *
 */
public class FondoResumidoDTO implements Cloneable, Comparable<FondoResumidoDTO> {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(FondoResumidoDTO.class);

	/** The nombre fondo. */
	private String nombreFondo;

	/** The codigo fondo. */
	private String codigoFondo;

	/** The importe minimo. */
	private String importeMinimo;

	/** The importe maximo. */
	private String importeMaximo;

	/** The moneda. */
	private String moneda;

	/** The saldo. */
	private String saldo;

	/** The grupo. */
	private String grupo;

	/** The grupo. */
	private String ordenPorGrupo;

	/** ID legales. */
	private String idMensajeGastos;

	/** Fondo habilitado para suscribir. */
	private String habilitarSuscripcion;

	/** Fondo habilitado para rescatar. */
	private String habilitarRescate;

	/** Tipo de banca. */
	private String tipoBanca;

	/** Plazo efectivo. */
	private String plazoEfectivo;

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

	/** The honorarios entrada. */
	private String honorariosEntrada;

	/** The honorarios salida. */
	private String honorariosSalida;

	/** The codigo agrupador. */
	private String codigoAgrupador;

	/** The orden agrupacion. */
	private String ordenAgrupacion;
	
	/** The ex citi. */
	private String exCiti;
	
	private boolean fondoNuevo;
	
	private String soloEspecie;
	
	private boolean repatriacion;

	/**
	 * Gets the sub orden por grupo.
	 *
	 * @return the sub orden por grupo
	 */
	public String getSubOrdenPorGrupo() {
		return subOrdenPorGrupo;
	}

	/**
	 * Sets the sub orden por grupo.
	 *
	 * @param subOrdenPorGrupo
	 *            the new sub orden por grupo
	 */
	public void setSubOrdenPorGrupo(String subOrdenPorGrupo) {
		this.subOrdenPorGrupo = subOrdenPorGrupo;
	}

	/** The grupo. */
	private String subOrdenPorGrupo;

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
	 * Gets the codigo fondo.
	 *
	 * @return the codigo fondo
	 */
	public String getCodigoFondo() {
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
	 * Gets the importe minimo.
	 *
	 * @return the importe minimo
	 */
	public String getImporteMinimo() {
		return importeMinimo;
	}

	/**
	 * Sets the importe minimo.
	 *
	 * @param importeMinimo
	 *            the new importe minimo
	 */
	public void setImporteMinimo(String importeMinimo) {
		this.importeMinimo = importeMinimo;
	}

	/**
	 * Gets the importe maximo.
	 *
	 * @return the importe maximo
	 */
	public String getImporteMaximo() {
		return importeMaximo;
	}

	/**
	 * Sets the importe maximo.
	 *
	 * @param importeMaximo
	 *            the new importe maximo
	 */
	public void setImporteMaximo(String importeMaximo) {
		this.importeMaximo = importeMaximo;
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
	 * Gets the grupo.
	 *
	 * @return the grupo
	 */
	public String getGrupo() {
		return grupo;
	}

	/**
	 * Sets the grupo.
	 *
	 * @param grupo
	 *            the new grupo
	 */
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	/**
	 * Gets the orden por grupo.
	 *
	 * @return the orden por grupo
	 */
	public String getOrdenPorGrupo() {
		return ordenPorGrupo;
	}

	/**
	 * Sets the orden por grupo.
	 *
	 * @param ordenPorGrupo
	 *            the new orden por grupo
	 */
	public void setOrdenPorGrupo(String ordenPorGrupo) {
		this.ordenPorGrupo = ordenPorGrupo;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#clone()
	 */
	public FondoResumidoDTO clone() {
		try {
			return (FondoResumidoDTO) super.clone();
		} catch (CloneNotSupportedException e) {
			LOGGER.error("Error clonando Objeto", e);
			return null;
		}
	}

	/**
	 * Gets the plazo efectivo.
	 *
	 * @return the plazoEfectivo
	 */
	public String getPlazoEfectivo() {
		return plazoEfectivo;
	}

	/**
	 * Sets the plazo efectivo.
	 *
	 * @param plazoEfectivo
	 *            the plazoEfectivo to set
	 */
	public void setPlazoEfectivo(String plazoEfectivo) {
		this.plazoEfectivo = plazoEfectivo;
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
	 * Gets the codigo agrupador.
	 *
	 * @return the codigoAgrupador
	 */
	public String getCodigoAgrupador() {
		return codigoAgrupador;
	}

	/**
	 * Sets the codigo agrupador.
	 *
	 * @param codigoAgrupador
	 *            the codigoAgrupador to set
	 */
	public void setCodigoAgrupador(String codigoAgrupador) {
		this.codigoAgrupador = codigoAgrupador;
	}

	/**
	 * Gets the orden agrupacion.
	 *
	 * @return the ordenAgrupacion
	 */
	public String getOrdenAgrupacion() {
		return ordenAgrupacion;
	}

	/**
	 * Sets the orden agrupacion.
	 *
	 * @param ordenAgrupacion
	 *            the ordenAgrupacion to set
	 */
	public void setOrdenAgrupacion(String ordenAgrupacion) {
		this.ordenAgrupacion = ordenAgrupacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(FondoResumidoDTO fondoResumidoDTO) {
		if (Integer.valueOf(this.getCodigoAgrupador().trim()) > Integer
				.valueOf(fondoResumidoDTO.getCodigoAgrupador().trim()))
			return 1;
		else if (Integer.valueOf(this.getCodigoAgrupador().trim()) == Integer
				.valueOf(fondoResumidoDTO.getCodigoAgrupador().trim())) {
			if (Integer.valueOf(this.getOrdenAgrupacion().trim()) >= Integer
					.valueOf(fondoResumidoDTO.getOrdenAgrupacion().trim()))
				return 1;
			else
				return -1;
		} else
			return -1;
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

	public boolean isFondoNuevo() {
		return fondoNuevo;
	}

	public void setFondoNuevo(boolean fondoNuevo) {
		this.fondoNuevo = fondoNuevo;
	}

	public String getSoloEspecie() {
		return soloEspecie;
	}

	public void setSoloEspecie(String soloEspecie) {
		this.soloEspecie = soloEspecie;
	}

	public boolean isRepatriacion() {
		return repatriacion;
	}

	public void setRepatriacion(boolean repatriacion) {
		this.repatriacion = repatriacion;
	}
	
	
	
}
