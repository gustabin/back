/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;
import org.beanio.annotation.Record;
import org.beanio.annotation.Segment;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * Objeto utilizado para ingresar al DAO.
 * 
 * @author
 *
 */
@Record
public class PrestamoPermitidoOutEntity {

    /** The Constant DELIMITER. */
    public static final String DELIMITER = "õ";

    /** The Constant IND_UVA_N. */
    private static final String IND_UVA_N = "N";

    /** The Constant IND_UVA_S. */
    private static final String IND_UVA_S = "S";

    /** The Constant TASA_F. */
    private static final String TASA_F = "F";

    /** The Constant TASA_V. */
    private static final String TASA_V = "V";

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field
	private String codigoRetornoExtendido;

	/** The cantidad ocurrencias. */
	@Field
	private Long cantidadOcurrencias;

	/** The lista result. */
	@Segment(occursRef = "cantidadOcurrencias")
	private List<PrestamoPermitidoEntity> listaResult = new ArrayList<PrestamoPermitidoEntity>();

	/**
	 * Gets the header trama.
	 *
	 * @return the headerTrama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the headerTrama to set
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigoRetornoExtendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the codigoRetornoExtendido to set
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the cantidad ocurrencias.
	 *
	 * @return the cantidadOcurrencias
	 */
	public Long getCantidadOcurrencias() {
		return cantidadOcurrencias;
	}

	/**
	 * Sets the cantidad ocurrencias.
	 *
	 * @param cantidadOcurrencias
	 *            the cantidadOcurrencias to set
	 */
	public void setCantidadOcurrencias(Long cantidadOcurrencias) {
		this.cantidadOcurrencias = cantidadOcurrencias;
	}

	/**
	 * Gets the lista result.
	 *
	 * @return the listaResult
	 */
	public List<PrestamoPermitidoEntity> getListaResult() {
		return listaResult;
	}

	/**
	 * Sets the lista result.
	 *
	 * @param listaResult
	 *            the listaResult to set
	 */
	public void setListaResult(List<PrestamoPermitidoEntity> listaResult) {
		this.listaResult = listaResult;
	}

	/**
	 * Obtener info prestamo por cuotas.
	 *
	 * @param cuotas
	 *            the cuotas
	 * @param isUva
	 *            the is uva
	 * @param isTasaFija
	 *            the is tasa fija
	 * @param idRangoSeleccionado
	 *            the id rango seleccionado
	 * @return the prestamo permitido entity
	 */
	public PrestamoPermitidoEntity obtenerInfoPrestamoPorCuotas(int cuotas, boolean isUva, boolean isTasaFija, String idRangoSeleccionado) {
	    String indLineaUva = isUva ? IND_UVA_S : IND_UVA_N;
	    String tpoTasa = isTasaFija ? TASA_F : TASA_V;

		for (PrestamoPermitidoEntity infoPrestamos : this.getListaResult()) {
			if (idRangoSeleccionado.equals(getId(infoPrestamos))
			        && Integer.parseInt(infoPrestamos.getMinCantCuotas()) <= cuotas
					&& Integer.parseInt(infoPrestamos.getMaxCantCuotas()) >= cuotas
					&& indLineaUva.equalsIgnoreCase(infoPrestamos.getIndLineaUVA())
					&& tpoTasa.equalsIgnoreCase(infoPrestamos.getTpoTasa())) {
				return infoPrestamos;
			}
		}
		return null;
	}

	
		
		public PrestamoPermitidoEntity obtenerInfoPrestamoPorProducto(String producto, String subproducto, Integer cuotasSelect) {
	        String subproductoPadding = StringUtils.leftPad(subproducto, 4, "0");
	        for (PrestamoPermitidoEntity infoPrestamos : this.getListaResult()) {
	            if (producto.equalsIgnoreCase(infoPrestamos.getCodProductoUg())
	                    && subproductoPadding.equalsIgnoreCase(infoPrestamos.getCodSubpUg())
	                    && cuotasSelect >= Integer.parseInt(infoPrestamos.getMinCantCuotas())
	                    && cuotasSelect <= Integer.parseInt(infoPrestamos.getMaxCantCuotas())) {
	                return infoPrestamos;
	            }
	        }
	        return null;
	    }
		
		
	/**
	 * Gets the id.
	 *
	 * @param infoPrestamos the info prestamos
	 * @return the id
	 */
	public String getId(PrestamoPermitidoEntity infoPrestamos) {
	    StringBuilder sb = new StringBuilder(ISBANStringUtils.eliminarCeros(infoPrestamos.getMinCantCuotas())).
	            append(ISBANStringUtils.eliminarCeros(infoPrestamos.getMaxCantCuotas()));
	    String tasa = infoPrestamos.getValorTasa().substring(0, 5);
	    sb.append(ISBANStringUtils.eliminarCeros(tasa)).append(infoPrestamos.getIndLineaUVA()).append(infoPrestamos.getTpoTasa());
	    return sb.toString();
    }

    /*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				// agregar los append con los atributos que correspondan
				// .append(extra)
				.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		PrestamoPermitidoOutEntity other = (PrestamoPermitidoOutEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return eb
				// agregar los appends que corresponda segun los atributos
				// cargados, Ej: .append(extra, other.getExtra())
				.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				// agregar los appends con los atributos que corresponda, ej:
				// .append("Extra", extra)
				.toString();
	}

	/**
	 * Obtener cuota maxima.
	 *
	 * @return the string
	 */
	public String obtenerCuotaMaxima() {
		return this.listaResult.get(listaResult.size() - 1).getMaxCantCuotas();
	}
}