/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.base.web.view.View;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.InformacionPlanV;
import ar.com.santanderrio.obp.servicios.tarjetas.web.view.TarjetaView;

/**
 * View de financiacion de tarjetas
 * <P>
 * DTO que se usa para transportar los datos de financiacion de tarjetas a la
 * vista.
 * </P>
 *
 * @author emilio.watemberg
 * @since Dec 5, 2016
 */
public class FinanciacionTarjetaView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The tarjetas. */
	private List<TarjetaView> tarjetas;

	/** El tipo de tarjeta seleccionada. */
	private String nroCuentaProductoSeleccionado;

	/** The marca tarjeta seleccionada. */
	private String marcaTarjetaSeleccionada;

	/** The numero tarjeta seleccionada. */
	private String numeroTarjetaSeleccionada;

	/** The index del combo de cuotas seleccionado. */
	private String indexCuotaSeleccionada;

	/** TNA seeccionado. */
	private String tna;

	/** The cuotas. */
	private List<CuotaFinanciadaDTO> cuotas;

	/** The importe financiar. */
	private String importeFinanciar;

	/** The importe minimo financiar. */
	private String importeMinimoFinanciar;

	/** The importe maximo financiar. */
	private String importeMaximoFinanciar;

	/** The cantidad cuotas seleccionada. */
	private String cantidadCuotas;

	/** The Costo Financiero Total. */
	private String cft;

	/** The importe cuota. */
	private String importeCuota;

	/** The mensaje informativo en el footer del stack. */
	private String mensajeInformativo;

	/**
	 * Instantiates a new financiacion tarjeta view.
	 */
	public FinanciacionTarjetaView() {
		super();
	}

	/**
	 * Instantiates a new financiacion tarjeta view.
	 *
	 * @param id
	 *            the id
	 */
	public FinanciacionTarjetaView(String id) {
		super(id);
	}

	/**
	 * Instantiates a new financiacion tarjeta view.
	 *
	 * @param informacionPlanV
	 *            the informacion plan V
	 */
	public FinanciacionTarjetaView(InformacionPlanV informacionPlanV) {
		setCuotasDisponibles(informacionPlanV);
	}

	/**
	 * Gets the tarjetas.
	 *
	 * @return the tarjetas
	 */
	public List<TarjetaView> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Sets the tarjetas.
	 *
	 * @param tarjetas
	 *            the new tarjetas
	 */
	public void setTarjetas(List<TarjetaView> tarjetas) {
		this.tarjetas = new ArrayList<TarjetaView>();
		this.tarjetas = tarjetas;
	}

	/**
	 * Gets the importe financiar.
	 *
	 * @return the importe financiar
	 */
	public String getImporteFinanciar() {
		return importeFinanciar;
	}

	/**
	 * Sets the importe financiar.
	 *
	 * @param importeFinanciar
	 *            the new importe financiar
	 */
	public void setImporteFinanciar(String importeFinanciar) {
		this.importeFinanciar = importeFinanciar;
	}

	/**
	 * Gets the cantidad cuotas.
	 *
	 * @return the cantidad cuotas
	 */
	public String getCantidadCuotas() {
		return cantidadCuotas;
	}

	/**
	 * Sets the cantidad cuotas.
	 *
	 * @param cantidadCuotas
	 *            the new cantidad cuotas
	 */
	public void setCantidadCuotas(String cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}

	/**
	 * Gets the cft.
	 *
	 * @return the cft
	 */
	public String getCft() {
		return cft;
	}

	/**
	 * Sets the cft.
	 *
	 * @param cft
	 *            the new cft
	 */
	public void setCft(String cft) {
		this.cft = cft;
	}

	/**
	 * Gets the importe cuota.
	 *
	 * @return the importe cuota
	 */
	public String getImporteCuota() {
		return importeCuota;
	}

	/**
	 * Sets the importe cuota.
	 *
	 * @param importeCuota
	 *            the new importe cuota
	 */
	public void setImporteCuota(String importeCuota) {
		this.importeCuota = importeCuota;
	}

	/**
	 * Gets the importe minimo financiar.
	 *
	 * @return the importe minimo financiar
	 */
	public String getImporteMinimoFinanciar() {
		return importeMinimoFinanciar;
	}

	/**
	 * Sets the importe minimo financiar.
	 *
	 * @param importeMinimoFinanciar
	 *            the new importe minimo financiar
	 */
	public void setImporteMinimoFinanciar(String importeMinimoFinanciar) {
		this.importeMinimoFinanciar = importeMinimoFinanciar;
	}

	/**
	 * Gets the importe maximo financiar.
	 *
	 * @return the importe maximo financiar
	 */
	public String getImporteMaximoFinanciar() {
		return importeMaximoFinanciar;
	}

	/**
	 * Sets the importe maximo financiar.
	 *
	 * @param importeMaximoFinanciar
	 *            the new importe maximo financiar
	 */
	public void setImporteMaximoFinanciar(String importeMaximoFinanciar) {
		this.importeMaximoFinanciar = importeMaximoFinanciar;
	}

	/**
	 * Gets the cuotas.
	 *
	 * @return the cuotas
	 */
	public List<CuotaFinanciadaDTO> getCuotas() {
		return cuotas;
	}

	/**
	 * Sets the cuotas.
	 *
	 * @param cuotas
	 *            the new cuotas
	 */
	public void setCuotas(List<CuotaFinanciadaDTO> cuotas) {
		this.cuotas = new ArrayList<CuotaFinanciadaDTO>();
		this.cuotas = cuotas;
	}

	/**
	 * Gets the nro cuenta producto seleccionado.
	 *
	 * @return the nro cuenta producto seleccionado
	 */
	public String getNroCuentaProductoSeleccionado() {
		return nroCuentaProductoSeleccionado;
	}

	/**
	 * Sets the nro cuenta producto seleccionado.
	 *
	 * @param nroCuentaProductoSeleccionado
	 *            the new nro cuenta producto seleccionado
	 */
	public void setNroCuentaProductoSeleccionado(String nroCuentaProductoSeleccionado) {
		this.nroCuentaProductoSeleccionado = nroCuentaProductoSeleccionado;
	}

	/**
	 * Gets the marca tarjeta seleccionada.
	 *
	 * @return the marca tarjeta seleccionada
	 */
	public String getMarcaTarjetaSeleccionada() {
		return marcaTarjetaSeleccionada;
	}

	/**
	 * Sets the marca tarjeta seleccionada.
	 *
	 * @param marcaTarjetaSeleccionada
	 *            the new marca tarjeta seleccionada
	 */
	public void setMarcaTarjetaSeleccionada(String marcaTarjetaSeleccionada) {
		this.marcaTarjetaSeleccionada = marcaTarjetaSeleccionada;
	}

	/**
	 * Gets the numero tarjeta seleccionada.
	 *
	 * @return the numero tarjeta seleccionada
	 */
	public String getNumeroTarjetaSeleccionada() {
		return numeroTarjetaSeleccionada;
	}

	/**
	 * Sets the numero tarjeta seleccionada.
	 *
	 * @param numeroTarjetaSeleccionada
	 *            the new numero tarjeta seleccionada
	 */
	public void setNumeroTarjetaSeleccionada(String numeroTarjetaSeleccionada) {
		this.numeroTarjetaSeleccionada = numeroTarjetaSeleccionada;
	}

	/**
	 * Gets the mensaje informativo.
	 *
	 * @return the mensaje informativo
	 */
	public String getMensajeInformativo() {
		return mensajeInformativo;
	}

	/**
	 * Sets the mensaje informativo.
	 *
	 * @param mensajeInformativo
	 *            the new mensaje informativo
	 */
	public void setMensajeInformativo(String mensajeInformativo) {
		this.mensajeInformativo = mensajeInformativo;
	}

	/**
	 * Gets the index cuota seleccionada.
	 *
	 * @return the index cuota seleccionada
	 */
	public String getIndexCuotaSeleccionada() {
		return indexCuotaSeleccionada;
	}

	/**
	 * Sets the index cuota seleccionada.
	 *
	 * @param indexCuotaSeleccionada
	 *            the new index cuota seleccionada
	 */
	public void setIndexCuotaSeleccionada(String indexCuotaSeleccionada) {
		this.indexCuotaSeleccionada = indexCuotaSeleccionada;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 * 
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cantidadCuotas);
		hcb.append(importeFinanciar);
		hcb.append(cft);
		hcb.append(importeCuota);
		hcb.append(importeMaximoFinanciar);
		hcb.append(importeMinimoFinanciar);
		hcb.append(nroCuentaProductoSeleccionado);
		hcb.append(marcaTarjetaSeleccionada);
		hcb.append(numeroTarjetaSeleccionada);
		hcb.append(mensajeInformativo);
		hcb.append(indexCuotaSeleccionada);
		hcb.append(tna);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		FinanciacionTarjetaView other = (FinanciacionTarjetaView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cantidadCuotas, other.getCantidadCuotas());
		eb.append(importeFinanciar, other.getImporteFinanciar());
		eb.append(cft, other.getCft());
		eb.append(importeCuota, other.getImporteCuota());
		eb.append(importeMaximoFinanciar, other.getImporteMaximoFinanciar());
		eb.append(importeMaximoFinanciar, other.getImporteMinimoFinanciar());
		eb.append(nroCuentaProductoSeleccionado, other.getNroCuentaProductoSeleccionado());
		eb.append(marcaTarjetaSeleccionada, other.getMarcaTarjetaSeleccionada());
		eb.append(numeroTarjetaSeleccionada, other.getMarcaTarjetaSeleccionada());
		eb.append(mensajeInformativo, other.getMensajeInformativo());
		eb.append(indexCuotaSeleccionada, other.getMensajeInformativo());
		eb.append(tna, other.getTna());
		return eb.isEquals();
	}

	/**
	 * Gets the cuotas disponibles. Carga las cuotas disponibles en base al
	 * minimo y maximo.
	 *
	 * @param informacionPlanV
	 *            the informacion plan V
	 * @return the cuotas disponibles
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setCuotasDisponibles(InformacionPlanV informacionPlanV) {
		this.cuotas = new ArrayList<CuotaFinanciadaDTO>();
		int minimo = informacionPlanV.getCuotasMinimo();
		int maximo = informacionPlanV.getCuotasMaximo();

		Set<Map.Entry<Integer, Double>> setCuotas = cargarCuotasMap(informacionPlanV).entrySet();
		Iterator it = setCuotas.iterator();
		// solo cargamos las disponibles en base a su maximo /minimo
		// de la información del plan.
		while (it.hasNext()) {
			Map.Entry<Integer, Double> pair = (Map.Entry<Integer, Double>) it.next();
			if (pair.getKey() >= minimo && pair.getKey() <= maximo) {
				CuotaFinanciadaDTO cuotaFinanciada = new CuotaFinanciadaDTO(pair.getKey().toString(),
						StringUtils.replace(pair.getValue().toString(), ".", ","));
				cuotas.add(cuotaFinanciada);
			}
		}
		indexCuotaSeleccionada = String.valueOf(cuotas.size() - 1);
		cantidadCuotas = cuotas.get(cuotas.size() - 1).getCuota();
	}

	/**
	 * Cargar cuotas con su correspondiente TNA.
	 *
	 * @param informacionPlanV
	 *            the informacion plan V
	 * @return the map
	 */
	private Map<Integer, Double> cargarCuotasMap(InformacionPlanV informacionPlanV) {
		// cuota - tna
		Map<Integer, Double> cuotas = new TreeMap<Integer, Double>();
		cuotas.put(1, informacionPlanV.getTNAHasta3Cuotas());
		cuotas.put(2, informacionPlanV.getTNAHasta3Cuotas());
		cuotas.put(3, informacionPlanV.getTNAHasta3Cuotas());
		cuotas.put(4, informacionPlanV.getTNAHasta6Cuotas());
		cuotas.put(5, informacionPlanV.getTNAHasta6Cuotas());
		cuotas.put(6, informacionPlanV.getTNAHasta6Cuotas());
		cuotas.put(7, informacionPlanV.getTNAHasta9Cuotas());
		cuotas.put(8, informacionPlanV.getTNAHasta9Cuotas());
		cuotas.put(9, informacionPlanV.getTNAHasta9Cuotas());
		cuotas.put(10, informacionPlanV.getTNAHasta12Cuotas());
		cuotas.put(11, informacionPlanV.getTNAHasta12Cuotas());
		cuotas.put(12, informacionPlanV.getTNAHasta12Cuotas());
		cuotas.put(13, informacionPlanV.getTNAHasta18Cuotas());
		cuotas.put(14, informacionPlanV.getTNAHasta18Cuotas());
		cuotas.put(15, informacionPlanV.getTNAHasta18Cuotas());
		cuotas.put(16, informacionPlanV.getTNAHasta18Cuotas());
		cuotas.put(17, informacionPlanV.getTNAHasta18Cuotas());
		cuotas.put(18, informacionPlanV.getTNAHasta18Cuotas());
		cuotas.put(19, informacionPlanV.getTNAHasta24Cuotas());
		cuotas.put(20, informacionPlanV.getTNAHasta24Cuotas());
		cuotas.put(21, informacionPlanV.getTNAHasta24Cuotas());
		cuotas.put(22, informacionPlanV.getTNAHasta24Cuotas());
		cuotas.put(23, informacionPlanV.getTNAHasta24Cuotas());
		cuotas.put(24, informacionPlanV.getTNAHasta24Cuotas());
		cuotas.put(25, informacionPlanV.getTNAHasta36Cuotas());
		cuotas.put(26, informacionPlanV.getTNAHasta36Cuotas());
		cuotas.put(27, informacionPlanV.getTNAHasta36Cuotas());
		cuotas.put(28, informacionPlanV.getTNAHasta36Cuotas());
		cuotas.put(29, informacionPlanV.getTNAHasta36Cuotas());
		cuotas.put(30, informacionPlanV.getTNAHasta36Cuotas());
		cuotas.put(31, informacionPlanV.getTNAHasta36Cuotas());
		cuotas.put(32, informacionPlanV.getTNAHasta36Cuotas());
		cuotas.put(33, informacionPlanV.getTNAHasta36Cuotas());
		cuotas.put(34, informacionPlanV.getTNAHasta36Cuotas());
		cuotas.put(35, informacionPlanV.getTNAHasta36Cuotas());
		cuotas.put(36, informacionPlanV.getTNAHasta36Cuotas());
		cuotas.put(37, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(38, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(39, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(40, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(41, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(42, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(43, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(44, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(45, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(46, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(47, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(48, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(49, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(50, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(51, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(52, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(53, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(54, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(55, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(56, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(57, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(58, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(59, informacionPlanV.getTNAHasta60Cuotas());
		cuotas.put(60, informacionPlanV.getTNAHasta60Cuotas());
		return cuotas;
	}

	/**
	 * Gets the tna.
	 *
	 * @return the tna
	 */
	public String getTna() {
		return tna;
	}

	/**
	 * Sets the tna.
	 *
	 * @param tna
	 *            the tna to set
	 */
	public void setTna(String tna) {
		this.tna = tna;
	}

}
