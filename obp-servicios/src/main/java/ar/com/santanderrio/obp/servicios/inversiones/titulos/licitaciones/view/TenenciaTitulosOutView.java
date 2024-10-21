/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciaTitulosCuentaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TenenciasGraficos;

/**
 * The Class TenenciaTitulosOutView.
 */
public class TenenciaTitulosOutView {

	/** The list tenencias. */
	private List<TenenciaTitulosCuentaDTO> listTenencias;

	/** The legales. */
	private String legales;
	
	/** The legales. */
	private String legalTitulosNuevos;
	
	/** The legales detalle 1. */
	private String legalesDetalle1;
	
	/** The legales detalle 2. */
	private String legalesDetalle2;
	
	/** The mensaje warning totales. */
	private String mensajeWarningTotales;

	/** The mensaje cuenta sin tenencias. */
	private String mensajeCuentaSinTenencias;

	/** The mensaje filtro especies. */
	private String mensajeFiltroEspecies;

	/** The mensaje cuenta bloqueada. */
	private String mensajeCuentaBloqueada;

	/** The graficos. */
	private TenenciasGraficos graficos;
		
	/** The DescripcionEstadoTenencia. */
	private String descripcionEstadoTenencia;
	
	private Boolean noHayTenencias = Boolean.FALSE;
	
	private boolean nuevaApertura;
	
	
	/**
	 * Gets the list tenencias.
	 *
	 * @return the list tenencias
	 */
	public List<TenenciaTitulosCuentaDTO> getListTenencias() {
		return listTenencias;
	}

	/**
	 * Sets the list tenencias.
	 *
	 * @param listTenencias
	 *            the new list tenencias
	 */
	public void setListTenencias(List<TenenciaTitulosCuentaDTO> listTenencias) {
		this.listTenencias = listTenencias;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the legales to set
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	public String getLegalTitulosNuevos() {
		return legalTitulosNuevos;
	}

	public void setLegalTitulosNuevos(String legalTitulosNuevos) {
		this.legalTitulosNuevos = legalTitulosNuevos;
	}

	/**
	 * Gets the mensaje cuenta sin tenencias.
	 *
	 * @return the cuentaSinTenencias
	 */
	public String getMensajeCuentaSinTenencias() {
		return mensajeCuentaSinTenencias;
	}

	/**
	 * Sets the mensaje cuenta sin tenencias.
	 *
	 * @param mensajeCuentaSinTenencias
	 *            the new mensaje cuenta sin tenencias
	 */
	public void setMensajeCuentaSinTenencias(String mensajeCuentaSinTenencias) {
		this.mensajeCuentaSinTenencias = mensajeCuentaSinTenencias;
	}

	/**
	 * Gets the mensaje filtro especies.
	 *
	 * @return the mensajeFiltroEspecies
	 */
	public String getMensajeFiltroEspecies() {
		return mensajeFiltroEspecies;
	}

	/**
	 * Sets the mensaje filtro especies.
	 *
	 * @param mensajeFiltroEspecies
	 *            the mensajeFiltroEspecies to set
	 */
	public void setMensajeFiltroEspecies(String mensajeFiltroEspecies) {
		this.mensajeFiltroEspecies = mensajeFiltroEspecies;
	}

	/**
	 * Gets the graficos.
	 *
	 * @return the graficos
	 */
	public TenenciasGraficos getGraficos() {
		return graficos;
	}

	/**
	 * Sets the graficos.
	 *
	 * @param graficos
	 *            the new graficos
	 */
	public void setGraficos(TenenciasGraficos graficos) {
		this.graficos = graficos;
	}

	/**
	 * Gets the mensaje cuenta bloqueada.
	 *
	 * @return the mensajeCuentaBloqueada
	 */
	public String getMensajeCuentaBloqueada() {
		return mensajeCuentaBloqueada;
	}

	/**
	 * Sets the mensaje cuenta bloqueada.
	 *
	 * @param mensajeCuentaBloqueada
	 *            the mensajeCuentaBloqueada to set
	 */
	public void setMensajeCuentaBloqueada(String mensajeCuentaBloqueada) {
		this.mensajeCuentaBloqueada = mensajeCuentaBloqueada;
	}

    /**
	 * Gets the legales detalle 1.
	 *
	 * @return the legales detalle 1
	 */
    public String getLegalesDetalle1() {
        return legalesDetalle1;
    }

    /**
	 * Sets the legales detalle 1.
	 *
	 * @param legalesDetalle1
	 *            the new legales detalle 1
	 */
    public void setLegalesDetalle1(String legalesDetalle1) {
        this.legalesDetalle1 = legalesDetalle1;
    }

    /**
	 * Gets the legales detalle 2.
	 *
	 * @return the legales detalle 2
	 */
    public String getLegalesDetalle2() {
        return legalesDetalle2;
    }

    /**
	 * Sets the legales detalle 2.
	 *
	 * @param legalesDetalle2
	 *            the new legales detalle 2
	 */
    public void setLegalesDetalle2(String legalesDetalle2) {
        this.legalesDetalle2 = legalesDetalle2;
    }

    /**
	 * Gets the mensaje warning totales.
	 *
	 * @return the mensaje warning totales
	 */
    public String getMensajeWarningTotales() {
        return mensajeWarningTotales;
    }

    /**
	 * Sets the mensaje warning totales.
	 *
	 * @param mensajeWarningTotales
	 *            the new mensaje warning totales
	 */
    public void setMensajeWarningTotales(String mensajeWarningTotales) {
        this.mensajeWarningTotales = mensajeWarningTotales;
    }

	public Boolean getNoHayTenencias() {
		return noHayTenencias;
	}

	public void setNoHayTenencias(Boolean noHayTenencias) {
		this.noHayTenencias = noHayTenencias;
	}
  
	public String getDescripcionEstadoTenencia() {
		return descripcionEstadoTenencia;
	}

	public void setDescripcionEstadoTenencia(String descripcionEstadoTenencia) {
		this.descripcionEstadoTenencia = descripcionEstadoTenencia;
	}

	public boolean isNuevaApertura() {
		return nuevaApertura;
	}

	public void setNuevaApertura(boolean nuevaApertura) {
		this.nuevaApertura = nuevaApertura;
	}
	
	

}
