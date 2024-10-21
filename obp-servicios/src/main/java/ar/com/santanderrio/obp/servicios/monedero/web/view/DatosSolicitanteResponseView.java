/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.web.view;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.servicios.compraventa.web.view.SimulacionCompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.EstadoCivilView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.PaisDeNacimientoView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.SexoView;

/**
 * The Class DatosSolicitanteViewResponse.
 */
public class DatosSolicitanteResponseView {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SimulacionCompraVentaDolarView.class);

	/** The datos solicitante view. */
	private DatosSolicitanteView datosSolicitanteView;

	/** The cuentas monedero view. */
	private List<CuentaMonederoView> cuentasMonederoView;

	/** The importes A recargar view. */
	private List<ImporteARecargarView> importesARecargarView;

	/** The limites de recarga mensual view. */
	private List<LimiteDeRecargaMensualView> limitesDeRecargaMensualView;

	/** The pais nacimiento view. */
	private List<PaisDeNacimientoView> paisNacimientoView;

	/** The sexo view. */
	private List<SexoView> sexoView;

	/** The estado civil view. */
	private List<EstadoCivilView> estadoCivilView;

	/** The nacionalidad view. */
	private List<NacionalidadView> nacionalidadView;

	/** The existe en altair. */
	private boolean existe;

	/** mensajeSinDesafio. */
    private String mensajeSinDesafio;

    /** urlAyuda. */
    private String urlAyuda;
    
	/**
	 * Gets the datos solicitante view.
	 *
	 * @return the datos solicitante view
	 */
	public DatosSolicitanteView getDatosSolicitanteView() {
		return datosSolicitanteView;
	}

	/**
	 * Sets the datos solicitante view.
	 *
	 * @param datosSolicitanteView
	 *            the new datos solicitante view
	 */
	public void setDatosSolicitanteView(DatosSolicitanteView datosSolicitanteView) {
		this.datosSolicitanteView = datosSolicitanteView;
	}

	/**
	 * Gets the cuentas monedero view.
	 *
	 * @return the cuentas monedero view
	 */
	public List<CuentaMonederoView> getCuentasMonederoView() {
		return cuentasMonederoView;
	}

	/**
	 * Sets the cuentas monedero view.
	 *
	 * @param cuentasMonederoView
	 *            the new cuentas monedero view
	 */
	public void setCuentasMonederoView(List<CuentaMonederoView> cuentasMonederoView) {
		this.cuentasMonederoView = cuentasMonederoView;
	}

	/**
	 * Gets the importes A recargar view.
	 *
	 * @return the importes A recargar view
	 */
	public List<ImporteARecargarView> getImportesARecargarView() {
		return importesARecargarView;
	}

	/**
	 * Sets the importes A recargar view.
	 *
	 * @param importesARecargarView
	 *            the new importes A recargar view
	 */
	public void setImportesARecargarView(List<ImporteARecargarView> importesARecargarView) {
		this.importesARecargarView = importesARecargarView;
	}

	/**
	 * Gets the limites de recarga mensual view.
	 *
	 * @return the limites de recarga mensual view
	 */
	public List<LimiteDeRecargaMensualView> getLimitesDeRecargaMensualView() {
		return limitesDeRecargaMensualView;
	}

	/**
	 * Sets the limites de recarga mensual view.
	 *
	 * @param limitesDeRecargaMensualView
	 *            the new limites de recarga mensual view
	 */
	public void setLimitesDeRecargaMensualView(List<LimiteDeRecargaMensualView> limitesDeRecargaMensualView) {
		this.limitesDeRecargaMensualView = limitesDeRecargaMensualView;
	}

	/**
	 * Gets the pais nacimiento view.
	 *
	 * @return the pais nacimiento view
	 */
	public List<PaisDeNacimientoView> getPaisNacimientoView() {
		return paisNacimientoView;
	}

	/**
	 * Sets the pais nacimiento view.
	 *
	 * @param paisNacimientoView
	 *            the new pais nacimiento view
	 */
	public void setPaisNacimientoView(List<PaisDeNacimientoView> paisNacimientoView) {
		this.paisNacimientoView = paisNacimientoView;
	}

	/**
	 * Gets the sexo view.
	 *
	 * @return the sexo view
	 */
	public List<SexoView> getSexoView() {
		return sexoView;
	}

	/**
	 * Sets the sexo view.
	 *
	 * @param sexoView
	 *            the new sexo view
	 */
	public void setSexoView(List<SexoView> sexoView) {
		this.sexoView = sexoView;
	}

	/**
	 * Gets the estado civil view.
	 *
	 * @return the estado civil view
	 */
	public List<EstadoCivilView> getEstadoCivilView() {
		return estadoCivilView;
	}

	/**
	 * Sets the estado civil view.
	 *
	 * @param estadoCivilView
	 *            the new estado civil view
	 */
	public void setEstadoCivilView(List<EstadoCivilView> estadoCivilView) {
		this.estadoCivilView = estadoCivilView;
	}

	/**
	 * Gets the nacionalidad view.
	 *
	 * @return the nacionalidad view
	 */
	public List<NacionalidadView> getNacionalidadView() {
		return nacionalidadView;
	}

	/**
	 * Sets the nacionalidad view.
	 *
	 * @param nacionalidadView
	 *            the new nacionalidad view
	 */
	public void setNacionalidadView(List<NacionalidadView> nacionalidadView) {
		this.nacionalidadView = nacionalidadView;
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
	 * Gets the logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return LOGGER;
	}
    public String getMensajeSinDesafio() {
		return mensajeSinDesafio;
	}

	public void setMensajeSinDesafio(String mensajeSinDesafio) {
		this.mensajeSinDesafio = mensajeSinDesafio;
	}

	public String getUrlAyuda() {
		return urlAyuda;
	}

	public void setUrlAyuda(String urlAyuda) {
		this.urlAyuda = urlAyuda;
	}

}
