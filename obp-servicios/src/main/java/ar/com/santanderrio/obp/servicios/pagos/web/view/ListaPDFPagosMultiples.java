package ar.com.santanderrio.obp.servicios.pagos.web.view;

import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

public class ListaPDFPagosMultiples {

	private List<ReporteView> pdfPagos;
		
	public List<ReporteView> getPdfPagos() {
		return pdfPagos;
	}

	public void setPdfPagos(List<ReporteView> pdfPagos) {
		this.pdfPagos = pdfPagos;
	}
	
	
}
