package ar.com.santanderrio.obp.servicios.getnet.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

@Record
public class ActualizacionConsultaMarcaAdhesionEntityOut {
	
	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";
	
	/** The header trama. */
	@Field
	private String headerTrama;

	/** numerico. */
	@Field
	private String codigoRetornoExtendido;

	/** The cant ocurrencias. */
	@Field (minOccurs = 0, defaultValue = "000")
	private Integer cantOcurrencias;
	
	/**
	 * Lista de impuestos.
	 */
	@Segment(minOccurs = 0, occursRef = "cantOcurrencias", maxOccurs = 50)
	private List<ListaIndicadoresMarcaAdhesion> listaIndicadores = new ArrayList<ListaIndicadoresMarcaAdhesion>();

	public String getHeaderTrama() {
		return headerTrama;
	}

	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	public Integer getCantOcurrencias() {
		return cantOcurrencias;
	}

	public void setCantOcurrencias(Integer cantOcurrencias) {
		this.cantOcurrencias = cantOcurrencias;
	}


	public List<ListaIndicadoresMarcaAdhesion> getListaIndicadores() {
		return listaIndicadores;
	}

	public void setListaIndicadores(List<ListaIndicadoresMarcaAdhesion> listaIndicadores) {
		this.listaIndicadores = listaIndicadores;
	}

	public static String getDelimiter() {
		return DELIMITER;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(this);
		builder.append("headerTrama", headerTrama);
		builder.append("codigoRetornoExtendido", codigoRetornoExtendido);
		builder.append("cantOcurrencias", cantOcurrencias);
		builder.append("listaIndicadores", listaIndicadores);
		return builder.toString();
	}

}
