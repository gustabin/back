package ar.com.santanderrio.obp.servicios.prestamos.view;

import ar.com.santanderrio.obp.servicios.prestamos.dto.SolicitudPrestamoDTO;

/**
 * 
 * Representa la solicitud de un prestamo por parte del front
 *
 */
public class SolicitudPrestamoInView {

	private SolicitudPrestamoDTO solicitud;

	// datos validacion
	private String tokenPrisma;

	public SolicitudPrestamoDTO getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudPrestamoDTO solicitud) {
		this.solicitud = solicitud;
	}

	public String getTokenPrisma() {
		return tokenPrisma;
	}

	public void setTokenPrisma(String tokenPrisma) {
		this.tokenPrisma = tokenPrisma;
	}
}
