/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

import java.util.List;

/**
 * ReporteHistorialPagosOpenCreditInEntity.
 *
 * @author Silvina_Luque
 */
public class ReportePagosOpenCreditInEntity {

	/** cabecera. */
	private CabeceraReportePagosOpenCreditEntity cabecera;

	/** items. */
	private List<ItemReportePagoOpenCreditEntity> pagos;

	/**
	 * Gets the cabecera.
	 *
	 * @return the cabecera
	 */
	public CabeceraReportePagosOpenCreditEntity getCabecera() {
		return cabecera;
	}

	/**
	 * Sets the cabecera.
	 *
	 * @param cabecera
	 *            the new cabecera
	 */
	public void setCabecera(CabeceraReportePagosOpenCreditEntity cabecera) {
		this.cabecera = cabecera;
	}

	/**
	 * Gets the pagos.
	 *
	 * @return the pagos
	 */
	public List<ItemReportePagoOpenCreditEntity> getPagos() {
		return pagos;
	}

	/**
	 * Sets the pagos.
	 *
	 * @param pagos
	 *            the new pagos
	 */
	public void setPagos(List<ItemReportePagoOpenCreditEntity> pagos) {
		this.pagos = pagos;
	}

}
