package ar.com.santanderrio.obp.servicios.inversiones.accesosdirectos.entity;

import org.codehaus.jackson.annotate.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatoConsultaAgedaRequest;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DatosServiciosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.EntityBase;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dao.MapServiceDAOImpl;



public class AccesoDirectoRequestEntity extends EntityBase {
	
	@Autowired
	private InversionWSHelper inversionWSHelper;
	

	/** The firmar. */
	@Value("${INVERSIONES.FIRMAR:true}")
	private String firmar;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AccesoDirectoRequestEntity.class);
	
	
	@JsonProperty("TipoHash")
	private String tipoHash = "0";

	@JsonProperty("Firma")
	private String firma;

	@JsonProperty("Dato")
	private String dato;
	
	@JsonProperty("DatosFirmados")
	private String datosFirmados;
	
	@JsonProperty("Datos")
	private DatosAccesoDirectoRequestEntity datos;
	
	@JsonProperty("Canal")
	private String canal;
	
	@JsonProperty("SubCanal")
	private String subCanal;
	


	/**
	 * Gets the canal.
	 *
	 * @return the canal
	 */
	public final String getCanal() {
		return canal;
	}

	/**
	 * Sets the canal.
	 *
	 * @param canal
	 *            the canal to set
	 */
	public final void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Gets the sub canal.
	 *
	 * @return the subCanal
	 */
	public final String getSubCanal() {
		return subCanal;
	}

	/**
	 * Sets the sub canal.
	 *
	 * @param subCanal
	 *            the subCanal to set
	 */
	public final void setSubCanal(String subCanal) {
		this.subCanal = subCanal;
	}
	
	/**
	 * Gets the datos firmados.
	 *
	 * @return the datosFirmados
	 */
	public final String getDatosFirmados() {
		return datosFirmados;
	}

	/**
	 * Sets the datos firmados.
	 *
	 * @param datosFirmados
	 *            the datosFirmados to set
	 */
	public final void setDatosFirmados(String datosFirmados) {
		this.datosFirmados = datosFirmados;
	}
	
	
	 /** @return the datos
	 */
	public DatosAccesoDirectoRequestEntity getDatos() {
		return datos;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDatos(DatosAccesoDirectoRequestEntity datos) {
		this.datos = datos;
	}
	
	/**
	 * Gets the firma.
	 *
	 * @return the firma
	 */
	public String getFirma() {
		return firma;
	}

	/**
	 * Sets the firma.
	 *
	 * @param firma
	 *            the new firma
	 */
	public void setFirma(String firma) {
		this.firma = firma;
	}
	
	 /** @return the datos
	 */
	public String getDato() {
		return dato;
	}

	/**
	 * @param datos the datos to set
	 */
	public void setDato(String dato) {
		this.dato = dato;
	}
	
	

	public void setTipoHasg(String tipoHash) {
		this.tipoHash = tipoHash;
	}
	
	public String getTipoHash() {
		return tipoHash;
	}
	
}
