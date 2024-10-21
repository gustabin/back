package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.ChancesDTO;

/**
 * The Class ChanceView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class ChanceView {
	
	/** The header. */
	private ChanceHeaderView header;
	
	/** The cuerpo. */
	private ChanceCuerpoView cuerpo;
	
	/** The fecha corte. */
	private String fechaCorte;
	
	/** The mes seleccionado. */
	private String mesSeleccionado;
	
	/** The  ChanceHistorialView. */
	private ChanceHistorialView historial;
	
	/**
	 *Constructor Chance View
	 *
	 */
	public ChanceView() {
		super();
	}

	/**
	 *Constructor Chance View
	 *
	 * @param ChancesDTO
	 */
	public ChanceView(ChancesDTO chancesDTO, ChanceHistorialView historial) {
		this.header = new ChanceHeaderView(null, chancesDTO.getTotal());
		this.cuerpo = new ChanceCuerpoView(chancesDTO.getListaChances(), chancesDTO.getHeader());
		this.historial = historial;
	}

	/**
	 *Gets historial
	 *
	 * @return the historial
	 */
	public ChanceHistorialView getHistorial() {
		return historial;
	}

	/**
	 *Sets historial
	 *
	 * @param the historial 
	 */
	public void setHistorial(ChanceHistorialView historial) {
		this.historial = historial;
	}

	/**
	 *Gets header
	 *
	 * @return the header
	 */
	public ChanceHeaderView getHeader() {
		return header;
	}

	/**
	 *Sets header
	 *
	 * @param the header 
	 */
	public void setHeader(ChanceHeaderView header) {
		this.header = header;
	}

	/**
	 *Gets cuerpo
	 *
	 * @return the cuerpo
	 */
	public ChanceCuerpoView getCuerpo() {
		return cuerpo;
	}

	/**
	 *Sets cuerpo
	 *
	 * @param the cuerpo 
	 */
	public void setCuerpo(ChanceCuerpoView cuerpo) {
		this.cuerpo = cuerpo;
	}
	
	/**
	 *Gets fecha corte
	 *
	 * @return the fecha corte
	 */
	public String getFechaCorte() {
		return fechaCorte;
	}

	/**
	 *Sets fecha corte
	 *
	 * @param the fechaCorte 
	 */
	public void setFechaCorte(String fechaCorte) {
		this.fechaCorte = fechaCorte;
	}

	/**
	 *Gets mesSeleccionado
	 *
	 * @return the mesSeleccionado
	 */
	public String getMesSeleccionado() {
		return mesSeleccionado;
	}

	/**
	 *Sets mesSeleccionado
	 *
	 * @param the mesSeleccionado 
	 */
	public void setMesSeleccionado(String mesSeleccionado) {
		this.mesSeleccionado = mesSeleccionado;
	}
	
}
