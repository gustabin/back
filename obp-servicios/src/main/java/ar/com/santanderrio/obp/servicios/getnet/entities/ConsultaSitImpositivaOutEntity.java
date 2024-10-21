package ar.com.santanderrio.obp.servicios.getnet.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

@Record
public class ConsultaSitImpositivaOutEntity {

	/** The Constant DELIMITER. */
	public static final String DELIMITER = "õ";
	
	/** The header trama. */
	@Field
	private String headerTrama;

	/** numerico. */
	@Field
	private String codigoRetornoExtendido;

	/** The cant ocurrencias. */
	@Field
	private Integer cantOcurrencias;
	
	/**
	 * Lista de impuestos.
	 */
	@Segment(occursRef = "cantOcurrencias")
	private List<ConsultarInformacionImpositivaEntity> listaInfoImpositiva = new ArrayList<ConsultarInformacionImpositivaEntity>();

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


	public List<ConsultarInformacionImpositivaEntity> getListaInfoImpositiva() {
		return listaInfoImpositiva;
	}

	public void setListaInfoImpositiva(List<ConsultarInformacionImpositivaEntity> listaInfoImpositiva) {
		this.listaInfoImpositiva = listaInfoImpositiva;
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
		builder.append("listaInfoImpositiva", listaInfoImpositiva);
		return builder.toString();
	}
	
	
}
