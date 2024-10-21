package ar.com.santanderrio.obp.servicios.delete.account.web.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ComprobanteBajaCuentaPDF {
	private static final String LOGO_CABECERA = "LOGO_CABECERA";
	private static final String LOGO_PIE = "LOGO_PIE";
	private static final String TITULO_CUENTA_CERRADA = "TITULO_CUENTA_CERRADA";
	private static final String TITULO_SOLICITUD_ENVIADA = "TITULO_SOLICITUD_ENVIADA";
	private static final String MANTIENE_CAJA_AHORRO = "MANTIENE_CAJA_AHORRO";
	private static final String FECHA_HORA_OPERACION = "FECHA_HORA_OPERACION";
	private static final String LIST_ITEMS_PROCESADOS = "LIST_ITEMS_PROCESADOS";
	
	private String logoCabeceraPath;
	private String logoPiePath;
	private String tituloCuentaCerrada;
	private String tituloSolicitudEnviada;
	private Boolean mantieneCajaAhorro;
	private String fechaHoraOperacion;
	private List<ItemDetalleRow> listItemsProcesados;
	
	private ComprobanteBajaCuentaPDF(Builder builder) {
		this.logoCabeceraPath = builder.logoCabeceraPath;
		this.logoPiePath = builder.logoPiePath;
		this.tituloCuentaCerrada = builder.tituloCuentaCerrada;
		this.tituloSolicitudEnviada = builder.tituloSolicitudEnviada;
		this.mantieneCajaAhorro = builder.mantieneCajaAhorro;
		this.fechaHoraOperacion = builder.fechaHoraOperacion;
		this.listItemsProcesados = builder.listItemsProcesados;
	}
	
	public Map<String, Object> mapParams() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(LOGO_CABECERA, this.logoCabeceraPath);
		params.put(LOGO_PIE, logoPiePath);
		params.put(TITULO_CUENTA_CERRADA, tituloCuentaCerrada);
		params.put(TITULO_SOLICITUD_ENVIADA, tituloSolicitudEnviada);
		params.put(MANTIENE_CAJA_AHORRO, mantieneCajaAhorro);
		params.put(FECHA_HORA_OPERACION, fechaHoraOperacion);
		params.put(LIST_ITEMS_PROCESADOS, new JRBeanCollectionDataSource(listItemsProcesados));
		return params;
	}
	
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private String logoCabeceraPath;
		private String logoPiePath;
		private String tituloCuentaCerrada;
		private String tituloSolicitudEnviada;
		private Boolean mantieneCajaAhorro;
		private String fechaHoraOperacion;
		private List<ItemDetalleRow> listItemsProcesados;
		
		public Builder logoCabeceraPath(String logoCabeceraPath) {
			this.logoCabeceraPath = logoCabeceraPath;
			return this;
		}
		
		public Builder logoPiePath(String logoPiePath) {
			this.logoPiePath = logoPiePath;
			return this;
		}
		
		public Builder tituloCuentaCerrada(String tituloCuentaCerrada) {
			this.tituloCuentaCerrada = tituloCuentaCerrada;
			return this;
		}
		
		public Builder tituloSolicitudEnviada(String tituloSolicitudEnviada) {
			this.tituloSolicitudEnviada = tituloSolicitudEnviada;
			return this;
		}
		public Builder mantieneCajaAhorro(Boolean mantieneCajaAhorro) {
			this.mantieneCajaAhorro = mantieneCajaAhorro;
			return this;
		}
		public Builder fechaHoraOperacion(String fechaHoraOperacion) {
			this.fechaHoraOperacion = fechaHoraOperacion;
			return this;
		}
		public Builder listItemsProcesados(List<ItemDetalleRow> listItemsProcesados) {
			this.listItemsProcesados = listItemsProcesados;
			return this;
		}
		
		public ComprobanteBajaCuentaPDF build() {
			return new ComprobanteBajaCuentaPDF(this);
		}
	}
}
