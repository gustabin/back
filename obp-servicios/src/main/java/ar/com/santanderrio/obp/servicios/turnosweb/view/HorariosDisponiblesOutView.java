/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.comun.view.ComboView;

/**
 * The Class HorariosDisponiblesOutView.
 *
 * @author IT Resources
 */
public class HorariosDisponiblesOutView {
	
	/** The direccion. */
	private String direccion;
	
	/** The descripcion sucursal. */
	private String descripcionSucursal;

	/** The sector. */
	private String sector;

	/** The id suc. */
	private String idSuc;
	
	/** The id turno. */
	private String idTurno;

	/** The dias disponibles. */
	private List<DiasDisponiblesView> diasDisponibles;
	
	/** The datos mya. */
	private DatosMYAView datosMYA;
	
	/** The menssaje feedback error. */
	private String mensajeFeedbackError;

	/** The dia permitido. */
	private String diaPermitido = "";

	/** The nro documento. */
	private String nroDocumento;

	/** The nombre apellido. */
	private String nombreApellido;

	/** The legal comprobante. */
	private String legalComprobante = "";

	/** The legal tyC. */
	private String legalTyC;

	/** The mensaje caja. */
	private String mensajeCaja;

	/** The motivos. */
	private List<ComboView> motivos = new ArrayList<ComboView>();

	/**
	 * Gets the direccion.
	 *
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Sets the direccion.
	 *
	 * @param direccion
	 *            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Gets the sector.
	 *
	 * @return the sector
	 */
	public String getSector() {
		return sector;
	}

	/**
	 * Sets the sector.
	 *
	 * @param sector
	 *            the sector to set
	 */
	public void setSector(String sector) {
		this.sector = sector;
	}

	/**
	 * Gets the id suc.
	 *
	 * @return the idSuc
	 */
	public String getIdSuc() {
		return idSuc;
	}

	/**
	 * Sets the id suc.
	 *
	 * @param idSuc
	 *            the idSuc to set
	 */
	public void setIdSuc(String idSuc) {
		this.idSuc = idSuc;
	}

	/**
	 * Gets the id turno.
	 *
	 * @return the idTurno
	 */
	public String getIdTurno() {
		return idTurno;
	}

	/**
	 * Sets the id turno.
	 *
	 * @param idTurno
	 *            the idTurno to set
	 */
	public void setIdTurno(String idTurno) {
		this.idTurno = idTurno;
	}

	/**
	 * Gets the dias disponibles.
	 *
	 * @return the diasDisponibles
	 */
	public List<DiasDisponiblesView> getDiasDisponibles() {
		return diasDisponibles;
	}

	/**
	 * Sets the dias disponibles.
	 *
	 * @param diasDisponibles
	 *            the diasDisponibles to set
	 */
	public void setDiasDisponibles(List<DiasDisponiblesView> diasDisponibles) {
		this.diasDisponibles = diasDisponibles;
	}

	/**
	 * Gets the descripcion sucursal.
	 *
	 * @return the descripcionSucursal
	 */
	public String getDescripcionSucursal() {
		return descripcionSucursal;
	}

	/**
	 * Sets the descripcion sucursal.
	 *
	 * @param descripcionSucursal
	 *            the descripcionSucursal to set
	 */
	public void setDescripcionSucursal(String descripcionSucursal) {
		this.descripcionSucursal = descripcionSucursal;
	}

	/**
	 * Gets the datos MYA.
	 *
	 * @return the datosMYA
	 */
	public DatosMYAView getDatosMYA() {
		return datosMYA;
	}

	/**
	 * Sets the datos MYA.
	 *
	 * @param datosMYA
	 *            the datosMYA to set
	 */
	public void setDatosMYA(DatosMYAView datosMYA) {
		this.datosMYA = datosMYA;
	}

	/**
	 * Gets the mensaje feedback error.
	 *
	 * @return the mensajeFeedbackError
	 */
	public String getMensajeFeedbackError() {
		return mensajeFeedbackError;
	}

	/**
	 * Sets the mensaje feedback error.
	 *
	 * @param mensajeFeedbackError
	 *            the mensajeFeedbackError to set
	 */
	public void setMensajeFeedbackError(String mensajeFeedbackError) {
		this.mensajeFeedbackError = mensajeFeedbackError;
	}

	/**
	 * Gets the dia permitido.
	 *
	 * @return the dia permitido
	 */
	public String getDiaPermitido() {
		return diaPermitido;
	}

	/**
	 * Sets the dia permitido.
	 *
	 * @param diaPermitido the new dia permitido
	 */
	public void setDiaPermitido(String diaPermitido) {
		this.diaPermitido = diaPermitido;
	}

	/**
	 * Gets the nro documento.
	 *
	 * @return the nro documento
	 */
	public String getNroDocumento() {
		return nroDocumento;
	}

	/**
	 * Sets the nro documento.
	 *
	 * @param nroDocumento the new nro documento
	 */
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	/**
	 * Gets the nombre apellido.
	 *
	 * @return the nombre apellido
	 */
	public String getNombreApellido() {
		return nombreApellido;
	}

	/**
	 * Sets the nombre apellido.
	 *
	 * @param nombreApellido the new nombre apellido
	 */
	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

	/**
	 * Gets the legal comprobante.
	 *
	 * @return the legal comprobante
	 */
	public String getLegalComprobante() {
		return legalComprobante;
	}

	/**
	 * Sets the legal comprobante.
	 *
	 * @param legalComprobante the new legal comprobante
	 */
	public void setLegalComprobante(String legalComprobante) {
		this.legalComprobante = legalComprobante;
	}

	/**
	 * Gets the legal ty C.
	 *
	 * @return the legal ty C
	 */
	public String getLegalTyC() {
		return legalTyC;
	}

	/**
	 * Sets the legal ty C.
	 *
	 * @param legalTyC the new legal ty C
	 */
	public void setLegalTyC(String legalTyC) {
		this.legalTyC = legalTyC;
	}

	/**
	 * Gets the mensaje caja.
	 *
	 * @return the mensaje caja
	 */
	public String getMensajeCaja() {
		return mensajeCaja;
	}

	/**
	 * Sets the mensaje caja.
	 *
	 * @param mensajeCaja the new mensaje caja
	 */
	public void setMensajeCaja(String mensajeCaja) {
		this.mensajeCaja = mensajeCaja;
	}

	/**
	 * Gets the motivos.
	 *
	 * @return the motivos
	 */
	public List<ComboView> getMotivos() {
		return motivos;
	}

	/**
	 * Sets the motivos.
	 *
	 * @param motivos the new motivos
	 */
	public void setMotivos(List<ComboView> motivos) {
		this.motivos = motivos;
	}

}
