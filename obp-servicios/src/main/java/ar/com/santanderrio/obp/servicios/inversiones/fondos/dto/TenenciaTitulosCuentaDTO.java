/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class TenenciaTitulosCuentaDTO.
 */
public class TenenciaTitulosCuentaDTO {

	/** The numero cuenta. */
	private String numeroCuenta;
	
	/** The numero cuenta operativa. */
	private String numeroCuentaOperativa;
	
	/** The lista porcentaje. */
	private List<TenenciaGrafico> listaPorcentaje;
	
	/** The total ars. */
    private Double totalArs;

    /** The total usd. */
    private Double totalUsd;
    
    /** The graficos en cero. */
    private Boolean graficosEnCero;

	/** The lista pesos. */
	private List<TenenciaTitulosDTO> listaPesos = new ArrayList<TenenciaTitulosDTO>();

	/** The lista dolares. */
	private List<TenenciaTitulosDTO> listaDolares = new ArrayList<TenenciaTitulosDTO>();

	/** The sin tenencias. */
	private boolean sinTenencias = true;

	/** The total resultado pesos. */
	private Double totalResultadoPesos = null;

	/** The total tenencia valuada pesos. */
	private Double totalTenenciaValuadaPesos = null;

	/** The total resultado dolares. */
	private Double totalResultadoDolares = null;

	/** The total tenencia valuada dolares. */
	private Double totalTenenciaValuadaDolares = null;

	/** The cuenta bloqueada. */
	private boolean cuentaBloqueada = false;
	
	/** The warning totales. */
	private boolean warningTotales = false;
	
	/**
	 * Gets the numero cuenta.
	 *
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * Sets the numero cuenta.
	 *
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * Gets the lista pesos.
	 *
	 * @return the listaPesos
	 */
	public List<TenenciaTitulosDTO> getListaPesos() {
		return listaPesos;
	}

	/**
	 * Sets the lista pesos.
	 *
	 * @param listaPesos
	 *            the listaPesos to set
	 */
	public void setListaPesos(List<TenenciaTitulosDTO> listaPesos) {
		this.listaPesos = listaPesos;
	}

	/**
	 * Gets the lista dolares.
	 *
	 * @return the listaDolares
	 */
	public List<TenenciaTitulosDTO> getListaDolares() {
		return listaDolares;
	}

	/**
	 * Sets the lista dolares.
	 *
	 * @param listaDolares
	 *            the listaDolares to set
	 */
	public void setListaDolares(List<TenenciaTitulosDTO> listaDolares) {
		this.listaDolares = listaDolares;
	}

	/**
	 * Gets the total resultado pesos.
	 *
	 * @return the totalResultadoPesos
	 */
	public Double getTotalResultadoPesos() {
		return totalResultadoPesos;
	}

	/**
	 * Sets the total resultado pesos.
	 *
	 * @param totalResultadoPesos
	 *            the totalResultadoPesos to set
	 */
	public void setTotalResultadoPesos(Double totalResultadoPesos) {
		this.totalResultadoPesos = totalResultadoPesos;
	}

	/**
	 * Gets the total tenencia valuada pesos.
	 *
	 * @return the totalTenenciaValuadaPesos
	 */
	public Double getTotalTenenciaValuadaPesos() {
		return totalTenenciaValuadaPesos;
	}

	/**
	 * Sets the total tenencia valuada pesos.
	 *
	 * @param totalTenenciaValuadaPesos
	 *            the totalTenenciaValuadaPesos to set
	 */
	public void setTotalTenenciaValuadaPesos(Double totalTenenciaValuadaPesos) {
		this.totalTenenciaValuadaPesos = totalTenenciaValuadaPesos;
	}

	/**
	 * Gets the total resultado dolares.
	 *
	 * @return the totalResultadoDolares
	 */
	public Double getTotalResultadoDolares() {
		return totalResultadoDolares;
	}

	/**
	 * Sets the total resultado dolares.
	 *
	 * @param totalResultadoDolares
	 *            the totalResultadoDolares to set
	 */
	public void setTotalResultadoDolares(Double totalResultadoDolares) {
		this.totalResultadoDolares = totalResultadoDolares;
	}

	/**
	 * Gets the total tenencia valuada dolares.
	 *
	 * @return the totalTenenciaValuadaDolares
	 */
	public Double getTotalTenenciaValuadaDolares() {
		return totalTenenciaValuadaDolares;
	}

	/**
	 * Sets the total tenencia valuada dolares.
	 *
	 * @param totalTenenciaValuadaDolares
	 *            the totalTenenciaValuadaDolares to set
	 */
	public void setTotalTenenciaValuadaDolares(Double totalTenenciaValuadaDolares) {
		this.totalTenenciaValuadaDolares = totalTenenciaValuadaDolares;
	}

	/**
	 * Checks if is sin tenencias.
	 *
	 * @return the sinTenencias
	 */
	public boolean isSinTenencias() {
		return sinTenencias;
	}

	/**
	 * Sets the sin tenencias.
	 *
	 * @param sinTenencias
	 *            the sinTenencias to set
	 */
	public void setSinTenencias(boolean sinTenencias) {
		this.sinTenencias = sinTenencias;
	}

	/**
	 * Adds the total resultado pesos.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTotalResultadoPesos(Double valor) {
		if(this.totalResultadoPesos == null){
			this.totalResultadoPesos = valor;
		}else {
			this.totalResultadoPesos = this.totalResultadoPesos + valor;
		}
	}

	/**
	 * Adds the total tenencia valuada pesos.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTotalTenenciaValuadaPesos(Double valor) {
		if(this.totalTenenciaValuadaPesos == null){
			this.totalTenenciaValuadaPesos = valor;
		}else {
			this.totalTenenciaValuadaPesos = this.totalTenenciaValuadaPesos + valor;
		}
	}

	/**
	 * Adds the total resultado dolares.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTotalResultadoDolares(Double valor) {
		if(this.totalResultadoDolares == null){
			this.totalResultadoDolares = valor;
		}else{
			this.totalResultadoDolares = this.totalResultadoDolares + valor;
		}
	}

	/**
	 * Adds the total tenencia valuada dolares.
	 *
	 * @param valor
	 *            the valor
	 */
	public void addTotalTenenciaValuadaDolares(double valor) {
		if(this.totalTenenciaValuadaDolares == null){
			this.totalTenenciaValuadaDolares = valor;
		}else{
			this.totalTenenciaValuadaDolares = this.totalTenenciaValuadaDolares + valor;
		}
	}

	/**
	 * Checks if is cuenta bloqueada.
	 *
	 * @return the cuentaBloqueada
	 */
	public boolean isCuentaBloqueada() {
		return cuentaBloqueada;
	}

	/**
	 * Sets the cuenta bloqueada.
	 *
	 * @param cuentaBloqueada
	 *            the cuentaBloqueada to set
	 */
	public void setCuentaBloqueada(boolean cuentaBloqueada) {
		this.cuentaBloqueada = cuentaBloqueada;
	}

    /**
	 * Gets the lista porcentaje.
	 *
	 * @return the lista porcentaje
	 */
    public List<TenenciaGrafico> getListaPorcentaje() {
        return listaPorcentaje;
    }

    /**
	 * Sets the lista porcentaje.
	 *
	 * @param listaPorcentaje
	 *            the new lista porcentaje
	 */
    public void setListaPorcentaje(List<TenenciaGrafico> listaPorcentaje) {
        this.listaPorcentaje = listaPorcentaje;
    }

    /**
	 * Gets the total usd.
	 *
	 * @return the total usd
	 */
    public Double getTotalUsd() {
        return totalUsd;
    }

    /**
	 * Sets the total usd.
	 *
	 * @param totalUsd
	 *            the new total usd
	 */
    public void setTotalUsd(Double totalUsd) {
        this.totalUsd = totalUsd;
    }

    /**
	 * Gets the total ars.
	 *
	 * @return the total ars
	 */
    public Double getTotalArs() {
        return totalArs;
    }

    /**
	 * Sets the total ars.
	 *
	 * @param totalArs
	 *            the new total ars
	 */
    public void setTotalArs(Double totalArs) {
        this.totalArs = totalArs;
    }

    /**
	 * Gets the graficos en cero.
	 *
	 * @return the graficos en cero
	 */
    public Boolean getGraficosEnCero() {
        return graficosEnCero;
    }

    /**
	 * Sets the graficos en cero.
	 *
	 * @param graficosEnCero
	 *            the new graficos en cero
	 */
    public void setGraficosEnCero(Boolean graficosEnCero) {
        this.graficosEnCero = graficosEnCero;
    }

    /**
	 * Checks if is warning totales.
	 *
	 * @return true, if is warning totales
	 */
    public boolean isWarningTotales() {
        return warningTotales;
    }

    /**
	 * Sets the warning totales.
	 *
	 * @param warningTotales
	 *            the new warning totales
	 */
    public void setWarningTotales(boolean warningTotales) {
        this.warningTotales = warningTotales;
    }

	/**
	 * Gets the numero cuenta operativa.
	 *
	 * @return the numero cuenta operativa
	 */
	public String getNumeroCuentaOperativa() {
		return numeroCuentaOperativa;
	}

	/**
	 * Sets the numero cuenta operativa.
	 *
	 * @param numeroCuentaOperativa
	 *            the new numero cuenta operativa
	 */
	public void setNumeroCuentaOperativa(String numeroCuentaOperativa) {
		this.numeroCuentaOperativa = numeroCuentaOperativa;
	}


    
}
