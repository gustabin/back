/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import ar.com.santanderrio.obp.generated.webservices.visa.planv.ConfirmacionSolicitudPlanV;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.InformacionPlanV;

/**
 * Objeto utilizado para transportar los datos referidos a financiacion de
 * tarjetas.
 * 
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @since Dec 6, 2016
 */
public class FinanciacionTarjetaDTO {

	/** The información plan V. */
	InformacionPlanV informaciónPlanV = new InformacionPlanV();

	/** The simulacion plan VDTO. */
	SimulacionPlanVDTO simulacionPlanVDTO = new SimulacionPlanVDTO();

	/** The confirmacion solicitud plan V. */
	ConfirmacionSolicitudPlanV confirmacionSolicitudPlanV;

	/**
	 * Gets the informacion plan V.
	 *
	 * @return the informacion plan V
	 */
	public InformacionPlanV getInformacionPlanV() {
		return informaciónPlanV;
	}

	/**
	 * Sets the informacion plan V.
	 *
	 * @param informaciónPlanV
	 *            the new informacion plan V
	 */
	public void setInformacionPlanV(InformacionPlanV informaciónPlanV) {
		this.informaciónPlanV = informaciónPlanV;
	}

	/**
	 * Gets the simulacion plan VDTO.
	 *
	 * @return the simulacion plan VDTO
	 */
	public SimulacionPlanVDTO getSimulacionPlanVDTO() {
		return simulacionPlanVDTO;
	}

	/**
	 * Sets the simulacion plan VDTO.
	 *
	 * @param simulacionPlanVDTO
	 *            the new simulacion plan VDTO
	 */
	public void setSimulacionPlanVDTO(SimulacionPlanVDTO simulacionPlanVDTO) {
		this.simulacionPlanVDTO = simulacionPlanVDTO;
	}

	/**
	 * Sets the confirmacion solicitud plan V.
	 *
	 * @param confirmacionSolicitudPlanV
	 *            the new confirmacion solicitud plan V
	 */
	public void setConfirmacionSolicitudPlanV(ConfirmacionSolicitudPlanV confirmacionSolicitudPlanV) {
		this.confirmacionSolicitudPlanV = confirmacionSolicitudPlanV;
	}

	/**
	 * Gets the confirmacion solicitud plan V.
	 *
	 * @return the confirmacionSolicitudPlanV
	 */
	public ConfirmacionSolicitudPlanV getConfirmacionSolicitudPlanV() {
		return confirmacionSolicitudPlanV;
	}

}
