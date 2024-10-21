package ar.com.santanderrio.obp.servicios.comprobantes.web.view;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresException;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ComprobanteCompraVentaView;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CabeceraComprobantesEnum;

@RunWith(MockitoJUnitRunner.class)
public class DetalleCompraVentaDolarViewTest {

	private static final String LABEL_DEBITO_PESOS = "Importe en pesos debitado";

	private static final String LABEL_DEBITO_DOLARES = "Importe en dólares debitado";

	private static final String LABEL_CREDITO_PESOS = "Importe en pesos acreditado";

	private static final String LABEL_CREDITO_DOLARES = "Importe en dólares acreditado";

	@Test
	public void obtenerParametrosPDFTest() throws CompraVentaDolaresException,
			IOException {

		DetalleCompraVentaDolarView view = getComprobanteCompraVentaDTO();

		HashMap<String, Object> map = view.obtenerParametrosPDF();
		
		Assert.assertNotNull(map);
		String result = "$ 3.455,54";
		String mapResult =  map.get("IMPORTE").toString();
		
		Assert.assertEquals(result,mapResult);
	}

	/**
	 * Gets the resumen compra venta DTO.
	 * 
	 * @return the resumen compra venta DTO
	 */
	private DetalleCompraVentaDolarView getComprobanteCompraVentaDTO()
			throws CompraVentaDolaresException {

		ComprobanteCompraVentaView compraVentaView = generarComprobanteVentaView();
		DetalleCompraVentaDolarView detalle = new DetalleCompraVentaDolarView();
		detalle.setNroCuentaOrigen(compraVentaView.getNroCuentaOrigen());
		detalle.setTipoCuentaOrigen(compraVentaView.getTipoCuentaOrigen());
		detalle.setDestinatario(compraVentaView.getNroCuentaDestino());
		detalle.setTipoCuentaDestino(compraVentaView.getTipoCuentaDestino());
		detalle.setCotizacionAplicada("u$s 1 = $"
				+ compraVentaView.getCotizacion());
		setearImportesYLabels(detalle, compraVentaView);
		detalle.setLegales(compraVentaView.getLegalesDos());
		detalle.setHora(null);
		detalle.setFecha(null);
		detalle.setNroOperacion(compraVentaView.getNumeroOperacion());
		detalle.setNroComprobante(compraVentaView.getNumeroComprobante());
		detalle.setFechaOperacion(compraVentaView.getFecha() + " - "
				+ compraVentaView.getHora());
		return detalle;
	}

	private void setearImportesYLabels(DetalleCompraVentaDolarView detalle,
			ComprobanteCompraVentaView compraVentaView)
			throws CompraVentaDolaresException {
		if (null != compraVentaView.getImporteCompraDolar()
				&& null != compraVentaView.getImporteDebitarPesos()) {
			detalle.setTituloComprobante(CabeceraComprobantesEnum.COMPRA_DOLARES
					.getDetalle());
			detalle.setLabelCredito(LABEL_CREDITO_DOLARES);
			detalle.setLabelDebito(LABEL_DEBITO_PESOS);
			detalle.setSaldoAcreditado(compraVentaView.getImporteCompraDolar());
			detalle.setSaldoDebitado(compraVentaView.getImporteDebitarPesos());
		} else {
			detalle.setTituloComprobante(CabeceraComprobantesEnum.VENTA_DOLARES
					.getDetalle());
			detalle.setLabelCredito(LABEL_CREDITO_PESOS);
			detalle.setLabelDebito(LABEL_DEBITO_DOLARES);
			detalle.setSaldoAcreditado(compraVentaView
					.getImporteAcreditarPesos());
			detalle.setSaldoDebitado(compraVentaView.getImporteVentaDolar());
		}
	}

	public static ComprobanteCompraVentaView generarComprobanteVentaView()
			throws CompraVentaDolaresException {
		ComprobanteCompraVentaView comprobanteVentaView = new ComprobanteCompraVentaView();
		comprobanteVentaView.setImporteVentaDolar(BigDecimal.valueOf(2309.00));
		comprobanteVentaView.setNroCuentaOrigen("123-456789/0");
		comprobanteVentaView.setTipoCuentaOrigen("Caja de Ahorro en u$s");
		comprobanteVentaView.setNroCuentaDestino("789-455633/4");
		comprobanteVentaView.setTipoCuentaDestino("Caja de Ahorro en $");
		comprobanteVentaView.setCotizacion(BigDecimal.valueOf(15.56));
		comprobanteVentaView.setImporteAcreditarPesos(BigDecimal
				.valueOf(3455.54));
		comprobanteVentaView.setEsVenta(true);
		comprobanteVentaView.setEsCompra(false);
		comprobanteVentaView.setFecha(new Date());
		comprobanteVentaView.setHora("14:07");
		comprobanteVentaView
				.setLegalesDos("Por la presente declaro bajo juramento que:"
						+ "1) Las informaciones consignadas son exactas y verdaderas, en los términos previstos en el Régimen Penal Cambiario, del cual tengo pleno conocimiento de sus normas y sanciones."
						+ "2) Al día de la fecha no me encuentro inhabilitado a operar en cambios en los términos de la normativa aplicable del BCRA"
						+ "Asimismo, me comprometo por la presente a mantener indemne a Banco Santander Río S.A., por cualquier y contra cualesquiera responsabilidades y daños y perjuicios de cualquier clase o naturaleza, incurridos por el incumplimiento de la Declaración Jurada arriba realizada.");
		comprobanteVentaView
				.setLegalesUno("Conserve este ticket como comprobante S.E.U.O.");
		comprobanteVentaView
				.setLegalesUno("Conserve este ticket como comprobante S.E.U.O.");
		comprobanteVentaView.setNumeroComprobante("12445664333");
		comprobanteVentaView.setNumeroOperacion("67786555666544");
		return comprobanteVentaView;
	}

}
