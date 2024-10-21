/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import javax.validation.constraints.NotNull;
import org.codehaus.jackson.annotate.JsonProperty;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class PeriodoActualEntity.
 */
public class PeriodoActualEntity{

	/** The cod evento. */
	@NotNull
	@JsonProperty("cod_evento")
	private String cod_evento;

	/** The nombre evento. */
	@NotNull
	@JsonProperty("nombre_evento")
	private String nombre_evento;
	
	/** The nup. */
	@NotNull
	@JsonProperty("nup")
	private String nup;
	
	public PeriodoActualEntity(Cliente cliente) {
		this.cod_evento = "23";
		this.nombre_evento = "premiacionesPeriodoActual";
		this.nup = cliente.getNup();
	}

	

	/**
	 *Gets cod_evento
	 *
	 * @return the cod_evento
	 */
	public String getCod_evento() {
		return cod_evento;
	}



	/**
	 *Sets cod_evento
	 *
	 * @param the cod_evento 
	 */
	public void setCod_evento(String cod_evento) {
		this.cod_evento = cod_evento;
	}



	/**
	 *Gets nombre_evento
	 *
	 * @return the nombre_evento
	 */
	public String getNombre_evento() {
		return nombre_evento;
	}



	/**
	 *Sets nombre_evento
	 *
	 * @param the nombre_evento 
	 */
	public void setNombre_evento(String nombre_evento) {
		this.nombre_evento = nombre_evento;
	}



	/**
	 *Gets nup
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 *Sets nup
	 *
	 * @param the nup 
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("codEvento=");
		builder.append(cod_evento);
		builder.append(", nombreEvento=");
		builder.append(nombre_evento);
		builder.append(", nup=");
		builder.append(nup);
		return builder.toString();
	}

}