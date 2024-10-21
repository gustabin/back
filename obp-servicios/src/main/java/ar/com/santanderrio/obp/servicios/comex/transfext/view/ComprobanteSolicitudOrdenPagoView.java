package ar.com.santanderrio.obp.servicios.comex.transfext.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.util.ResourceUtils;

import ar.com.santanderrio.obp.servicios.comprobantes.web.view.DetalleComprobanteView;

public class ComprobanteSolicitudOrdenPagoView extends DetalleComprobanteView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4182844853087656575L;
	private String numeroReferencia;
	private String concepto;
	private String documentacionAdjunta;
	private String nroCuentaDestino;
	private String monto;
	private String empresaVinculada;

	private static final String CABECERA = "Solicitud de liquidación de";
	private static final String CABECERA2 = "Orden de Pago ";
	private static final String PMC_SOLICITUD_ORDEN_PAGO = "ordenPagoExterior.jasper";
	private static final String PATH_PDF = "classpath:/report/ordenPagoExterior/";
	protected final String nroReferenciaKey = "NUMERO_REFERENCIA";
	protected final String conceptoKey = "CONCEPTO";
	protected final String nroCuentaDestinoKey = "NRO_CUENTA_DESTINO";
	protected final String tipoCuentaDestinoKey = "TIPO_CUENTA_DESTINO";
	protected final String documentacionAdjuntaKey = "DOC_ADJ";
	protected final String fechaHoraKey = "FECHA_HORA";
	protected final String titulo2Key = "TITULO_2";
	protected final String empresaVinculadaKey = "EMPRESA_VINCULADA";

	public String getNumeroReferencia() {
		return numeroReferencia;
	}

	public void setNumeroReferencia(String numeroReferencia) {
		this.numeroReferencia = numeroReferencia;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getDocumentacionAdjunta() {
		return documentacionAdjunta;
	}

	public void setDocumentacionAdjunta(String documentacionAdjunta) {
		this.documentacionAdjunta = documentacionAdjunta;
	}

	public String getNroCuentaDestino() {
		return nroCuentaDestino;
	}

	public void setNroCuentaDestino(String nroCuentaDestino) {
		this.nroCuentaDestino = nroCuentaDestino;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getEmpresaVinculada() {
		return empresaVinculada;
	}

	public void setEmpresaVinculada(String empresaVinculada) {
		this.empresaVinculada = empresaVinculada;
	}

	@Override
	public HashMap<String, Object> obtenerParametrosPDF() throws IOException {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(tituloKey, CABECERA);
		parametros.put(titulo2Key, CABECERA2 + getNumeroReferencia());
		parametros.put(nroReferenciaKey, getNumeroReferencia());
		parametros.put(importeKey, getMonto());
		parametros.put(conceptoKey, getConcepto());
		parametros.put(nroCuentaDestinoKey, getNroCuentaDestino());
		parametros.put(tipoCuentaDestinoKey, getTipoCuentaDestino());
		parametros.put(documentacionAdjuntaKey, getDocumentacionAdjunta());
		parametros.put(numeroComprobanteKey, getNroComprobante());
		parametros.put(empresaVinculadaKey, getEmpresaVinculada());
		parametros.put(fechaHoraKey, getFechaOperacion());
		return parametros;
	}

	@Override
	public String obtenerJasper() throws FileNotFoundException {
		return ResourceUtils.getFile(PATH_PDF + PMC_SOLICITUD_ORDEN_PAGO).getPath();
	}

}
