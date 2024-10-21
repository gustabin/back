/*
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ReportComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class ReportSimulacionPrestamosView.
 */
public class ReportSimulacionPrestamosView extends ReportComprobanteView{

	/** The simulacion. */
	private ResultadoSimulacionView simulacion;

	/** The nro de comprobante. */
	private String nroDeComprobante;

	/** The fecha hora. */
	private String fechaHora;

	/** The legales SEUO. */
	private String legalesSEUO;
	
	/** The importe key. */
	private final String importeKey = "IMPORTE_ACREDITAR";

	/** The datos del prestamo key. */
	private final String datosDelPrestamoKey = "DATOS_DEL_PRESTAMO";

	/** The datos primera cuota key. */
	private final String datosPrimeraCuotaKey = "DATOS_PRIMERA_CUOTA";

	/** The tasas key. */
	private final String tasasKey = "TASAS";

	/** The cftea con impuestos key. */
	private final String cfteaConImpuestosKey = "COSTO_FINANCIERO_CON_IMPUESTOS";

	/** The cftea sin impuestos key. */
	private final String cfteaSinImpuestosKey = "COSTO_FINANCIERO_SIN_IMPUESTOS";

	/** The legales SEUO key. */
	private final String legalesSEUOKey = "LEGALES_SEUO";
	
	/** The link BCRA. */
	private final String linkBCRA = "http://www.bcra.gob.ar/BCRAyVos/Regimen_de_transparencia.asp";
	
	/** The nro comprobante key. */
	private final String nroComprobanteKey = "NRO_COMPROBANTE";

	
	protected final String legal1bis ="(1) En caso de no coincidir el \"Importe Neto a Acreditar\" con “el Importe a Solicitar”, la diferencia corresponderá a \"Gastos de Otorgamiento\".";	
	
	/** The legal 1. */
	protected final String legal1 = "(2) El plazo de la cuota esta expresado en meses";

	/** The legal 2. */
	protected final String legal2 = "(3) (4) Incluye capital e intereses. No incluye impuestos, los que serán calculados sobre el importe de la cuota en pesos.";

	/** The legal 3. */
	protected final String legal3 = "LEGAL3";
	
	protected final String legal2bis = "(3) De existir diferencia entre la sumatoria de los rubros que se detalla y el \"Importe de la cuota\", la misma corresponde a los intereses punitorios.";
	
	protected final String legal3bis ="(4) Sellados";
	
	/** The legal 1 key. */
	protected final String legal0Key = "LEGAL0";
	
	/** The legal 1 key. */
	protected final String legal1Key = "LEGAL1";

	/** The legal 2 key. */
	protected final String legal2Key = "LEGAL2";

	/** The legal 3 key. */
	protected final String legal3Key = "LEGAL3";

	/** The i. */
	private int i = 1;
	
	/** The titulo. */
	private final String titulo = "Comprobante de solicitud de Súper Préstamo Personal";

	/** The titulo uva. */
	private final String tituloUva = "Comprobante de solicitud de Súper Préstamo Personal UVA";
	
	/** The personal. */
	private final String personal = "PERSONAL";
	
	/** The prestamos jasper. */
	protected final String prestamosJasper = "calculador-prestamo.jasper";
	
	/**
	 * Instantiates a new report simulacion prestamos view.
	 *
	 * @param builder
	 *            the builder
	 */
	public ReportSimulacionPrestamosView(ResultadoSimulacionView builder) {
		super();
		setSimulacion(builder);
	}

	/**
	 * Instantiates a new report simulacion prestamos view.
	 */
	public ReportSimulacionPrestamosView() {
		super();
	}

	/**
	 * Gets the nro de comprobante.
	 *
	 * @return the nro de comprobante
	 */
	public String getNroDeComprobante() {
		return nroDeComprobante;
	}

	/**
	 * Sets the nro de comprobante.
	 *
	 * @param nroDeComprobante
	 *            the new nro de comprobante
	 */
	public void setNroDeComprobante(String nroDeComprobante) {
		this.nroDeComprobante = nroDeComprobante;
	}

	/**
	 * Gets the fecha hora.
	 *
	 * @return the fecha hora
	 */
	public String getFechaHora() {
		return fechaHora;
	}

	/**
	 * Sets the fecha hora.
	 *
	 * @param fechaHora
	 *            the new fecha hora
	 */
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	/**
	 * Gets the legales SEUO.
	 *
	 * @return the legales SEUO
	 */
	public String getLegalesSEUO() {
		return legalesSEUO;
	}

	/**
	 * Sets the legales SEUO.
	 *
	 * @param legalesSEUO
	 *            the new legales SEUO
	 */
	public void setLegalesSEUO(String legalesSEUO) {
		this.legalesSEUO = legalesSEUO;
	}

	/**
	 * Gets the simulacion.
	 *
	 * @return the simulacion
	 */
	public ResultadoSimulacionView getSimulacion() {
		return simulacion;
	}

	/**
	 * Sets the simulacion.
	 *
	 * @param simulacion
	 *            the new simulacion
	 */
	public void setSimulacion(ResultadoSimulacionView simulacion) {
		this.simulacion = simulacion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.
	 * DetalleComprobanteTransferenciaView#obtenerJasper()
	 */
	@Override
	public String obtenerJasper() throws FileNotFoundException {
		return ResourceUtils.getFile(path + prestamosJasper).getPath();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.comprobantes.web.view.ReportComprobanteView#obtenerParametrosPDF(boolean)
	 */
	@Override
	public HashMap<String, Object> obtenerParametrosPDF(boolean isUva) {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		if (isUva) {
			tituloComprobante = tituloUva;
		} else {
			tituloComprobante = titulo;
		}
		tipoPrestamo = personal;

		parametros.put(legal0Key, simulacion.getLegal1simulacionConfirmacion());
		parametros.put(legal1Key, simulacion.getLegal2simulacionConfirmacion());
		parametros.put(legal2Key, simulacion.getLegal3simulacionConfirmacion());
		
		parametros.put(tituloComprobanteKey, tituloComprobante);		
		parametros.put(importeKey, simulacion.getImporte());
		List<ItemView> datosDelPrestamoParseados = obtenerHtmlDatos(simulacion.getDatosDelPrestamo(), isUva);
		parametros.put(datosDelPrestamoKey, datosDelPrestamoParseados);
		List<ItemView> datosPrimeraCuotaParseados = obtenerHtmlDatos(simulacion.getDatosPrimeraCuota(), isUva);
		parametros.put(datosPrimeraCuotaKey, datosPrimeraCuotaParseados);
		ItemView cfteaCon = simulacion.getTasas().get(simulacion.getTasas().size() - 2);
		ItemView cfteaSin = simulacion.getTasas().get(simulacion.getTasas().size() - 1);
		parametros.put(tasasKey, simulacion.getTasas().subList(0, simulacion.getTasas().size() - 2));
		parametros.put(cfteaConImpuestosKey, "CFTEA C/Imp: " + cfteaCon.getValor());
		parametros.put(cfteaSinImpuestosKey, "CFTEA S/Imp: " + cfteaSin.getValor());
		parametros.put(nroComprobanteKey, nroDeComprobante);
		if (!StringUtils.isBlank(legalesSEUO) && legalesSEUO.contains("siguiente link")) {
			legalesSEUO.replace("siguiente link", linkBCRA);
		}
		parametros.put(legalesSEUOKey, legalesSEUO);
		
		// Agrego los legales solo si coinciden con los Label
		/*for (ItemView view : simulacion.getDatosDelPrestamo()) {
			if (view.getLabel().equals("Cantidad de cuotas (1)")|| (view.getLabel().equals("Importe neto a acreditar (1)"))) {
								
				parametros.put(legal0Key, legal1bis);
				parametros.put(legal1Key, legal1);
				//
				parametros.put(legal2Key, legal2bis);
				parametros.put(legal3Key, legal3bis);
			}			
		}					
		Boolean isImporteUVA = false;
		Boolean isValorUVA = false;
		for (ItemView view : simulacion.getDatosPrimeraCuota()) {
			if (view.getLabel().equals("Importe primera cuota en UVAs (2)")) {
				isImporteUVA = true;
			}
			if (view.getLabel().equals("Valor próximas cuotas en UVAs (3)")) {
				isValorUVA = true;
			}
		}
		if (isImporteUVA && isValorUVA) {
			parametros.put(legal2Key, legal2);
		}*/
		
		parametros.put(fechaHoraActualKey, ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		return parametros;
	}

	/**
	 * Obtener html datos del prestamo.
	 *
	 * @return the list
	 */
	private List<ItemView> obtenerHtmlDatos(List<ItemView> listItemView, boolean isUVA) {
		List<ItemView> res = new ArrayList<ItemView>();
		for (ItemView view : listItemView) {
			ItemView copia = new ItemView();
			/*if (view.getLabel().equals("Cantidad de cuotas") || view.getLabel().equals("Importe primera cuota en UVAs") 
					|| view.getLabel().equals("Valor próximas cuotas en UVAs") || view.getLabel().equals("Importe neto a acreditar")) {
				view.setLabel(view.getLabel() + " (" + this.i + ")");
				this.i++;
			}
			
			//Importe primera cuota (2)			
			if (view.getLabel().equals("Importe primera cuota")) {
				view.setLabel(view.getLabel() + " (" + this.i + ")");
				this.i++;
			}
			
			//Otros impuestos (3)
			if (view.getLabel().equals("Otros impuestos")) {
				view.setLabel(view.getLabel() + " (" + this.i + ")");
				this.i++;
			}*/
			
			if("Importe neto a acreditar".equals(view.getLabel())) {
				view.setLabel(view.getLabel() + " (1)");
			} else if("Cantidad de cuotas".equals(view.getLabel())) {
				view.setLabel(view.getLabel() + " (2)");
			} else if("Otros impuestos".equals(view.getLabel())) {
				if(isUVA) {
					view.setLabel(view.getLabel() + " (4)");
				}else {
					view.setLabel(view.getLabel() + " (3)");
				}
			} else if(isUVA && "Importe primera cuota en UVAs".equals(view.getLabel())) {
				view.setLabel(view.getLabel() + " (3)");
			}
			
			copia.setLabel(view.getLabel());
			if (view.getValor2() != null) {
				copia.setValor(view.getValor()+"<br/>"+view.getValor2());
			}else {
				copia.setValor(view.getValor());
			}
			res.add(copia);
		}
		return res;
	}

}
