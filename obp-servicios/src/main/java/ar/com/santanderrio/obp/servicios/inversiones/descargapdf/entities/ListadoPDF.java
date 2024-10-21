package ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities;

import java.util.List;

import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;

public class ListadoPDF {

		private List<ReporteView> pdfPedidos;

		
		public List<ReporteView> getPdfPedidos() {
			return pdfPedidos;
		}

		public void setPdfPedidos(List<ReporteView> pdfPedidos) {
			this.pdfPedidos = pdfPedidos;
		}

}	