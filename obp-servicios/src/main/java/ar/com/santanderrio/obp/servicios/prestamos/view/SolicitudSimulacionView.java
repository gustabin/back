/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * The Class SolicitudSimulacionView.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SolicitudSimulacionView {

	/** The cbu. */
	@NotNull
	@Size(min = 1)
	private String cbu;

	/** The importe. */
	@NotNull
	private String importeSeleccionado;

	/** The cuotas. */
	@NotNull
	private String cuotaSeleccionada;

	/** The fecha. */
	@NotNull
	@Pattern(regexp = "^[0-9]{2}/[0-9]{2}/[0-9]{4}$")
	private String fechaSeleccionada;

	/** The motivo seleccionado. */
	@NotNull
	private DestinoPrestamoSeleccionView motivoSeleccionado;

	/** The reintentar. */
	private String reintentar;
	
	/** The isUVA. */
	@NotNull
	private boolean isUva;
	
	/** The isTasaFija. */
	@NotNull
	private boolean isTasaFija;
	
	/** The idRangoSeleccionado. */
    @NotNull
    private String idRangoSeleccionado;

    /** The legal. */
    @NotNull
    private String legal;
    
    /** The cantiCuentas. */
    private String cantiCuentas;
    
    /** The destino. */
    private String destino;

	private String tna;

	private String producto;

	private String subproducto;

	private String fase;

	private AutentificacionDTO desafio;
	
	public AutentificacionDTO getDesafio() {
		return desafio;
	}

	public void setDesafio(AutentificacionDTO desafio) {
		this.desafio = desafio;
	}

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
	 *            the new cbu
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporteSeleccionado() {
		return importeSeleccionado;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importeSeleccionado
	 *            the new importe
	 */
	public void setImporteSeleccionado(String importeSeleccionado) {
		this.importeSeleccionado = importeSeleccionado;
	}

	/**
	 * Gets the cuotas.
	 *
	 * @return the cuotas
	 */
	public String getCuotaSeleccionada() {
		return cuotaSeleccionada;
	}

	/**
	 * Sets the cuotas.
	 *
	 * @param cuotas
	 *            the new cuotas
	 */
	public void setCuotaSeleccionada(String cuotas) {
		this.cuotaSeleccionada = cuotas;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFechaSeleccionada() {
		return fechaSeleccionada;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fechaSeleccionada
	 *            the new fecha seleccionada
	 */
	public void setFechaSeleccionada(String fechaSeleccionada) {
		this.fechaSeleccionada = fechaSeleccionada;
	}

	/**
	 * Gets the motivo seleccionado.
	 *
	 * @return the motivo seleccionado
	 */
	public DestinoPrestamoSeleccionView getMotivoSeleccionado() {
		return motivoSeleccionado;
	}

	/**
	 * Sets the motivo seleccionado.
	 *
	 * @param motivoSeleccionado
	 *            the new motivo seleccionado
	 */
	public void setMotivoSeleccionado(DestinoPrestamoSeleccionView motivoSeleccionado) {
		this.motivoSeleccionado = motivoSeleccionado;
	}

	/**
	 * Gets the reintentar.
	 *
	 * @return the reintentar
	 */
	public String getReintentar() {
		return reintentar;
	}

	/**
	 * Sets the reintentar.
	 *
	 * @param reintentar
	 *            the new reintentar
	 */
	public void setReintentar(String reintentar) {
		this.reintentar = reintentar;
	}

	/**
	 * Importe to big decimal.
	 *
	 * @return the big decimal
	 */
	@JsonIgnore
	public BigDecimal importeToBigDecimal() {
		return new BigDecimal(this.importeSeleccionado);
	}

	/**
	 * Gets the isUva.
	 *
	 * @return the isUva
	 */
	public boolean isUva() {
		return isUva;
	}

	/**
	 * Sets the isUva.
	 *
	 * @param isUva
	 *            the new isUva
	 */
	public void setUVA(boolean isUva) {
		this.isUva = isUva;
	}

	/**
	 * Gets the isTasaFija.
	 *
	 * @return the isTasaFija
	 */
	public boolean isTasaFija() {
		return isTasaFija;
	}

	/**
	 * Sets the isTasaFija.
	 *
	 * @param isTasaFija
	 *            the new isTasaFija
	 */
	public void setTasaFija(boolean isTasaFija) {
		this.isTasaFija = isTasaFija;
	}

    /**
	 * Gets the id rango seleccionado.
	 *
	 * @return the idRangoSeleccionado
	 */
    public String getIdRangoSeleccionado() {
        return idRangoSeleccionado;
    }

    /**
	 * Sets the id rango seleccionado.
	 *
	 * @param idRangoSeleccionado
	 *            the idRangoSeleccionado to set
	 */
    public void setIdRangoSeleccionado(String idRangoSeleccionado) {
        this.idRangoSeleccionado = idRangoSeleccionado;
    }
    
    /**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
    public String getLegal() {
        return legal;
    }

    /**
	 * Sets the legal.
	 *
	 * @param legal
	 *            the legal to set
	 */
    public void setLegal(String legal) {
        this.legal = legal;
    }
    
    /**
	 * Gets the canti cuentas.
	 *
	 * @return the cantiCuentas
	 */
    public String getCantiCuentas() {
        return cantiCuentas;
    }

    /**
	 * Sets the canti cuentas.
	 *
	 * @param cantiCuentas
	 *            the cantiCuentas to set
	 */
    public void setCantiCuentas(String cantiCuentas) {
        this.cantiCuentas = cantiCuentas;
    }
    
    /**
	 * Gets the destino.
	 *
	 * @return the destino
	 */
    public String getDestino() {
        return destino;
    }

    /**
	 * Sets the destino.
	 *
	 * @param destino
	 *            the destino to set
	 */
    public void setDestino(String destino) {
        this.destino = destino;
    }

	public String getTna() {
		return tna;
	}

	public void setTna(String tna) {
		this.tna = tna;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getSubproducto() {
		return subproducto;
	}

	public void setSubproducto(String subproducto) {
		this.subproducto = subproducto;
	}

	public String getFase() {
		return fase;
	}

	public void setFase(String fase) {
		this.fase = fase;
	}
}
