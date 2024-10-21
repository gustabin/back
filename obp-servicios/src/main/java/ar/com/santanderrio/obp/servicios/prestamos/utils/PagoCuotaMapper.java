package ar.com.santanderrio.obp.servicios.prestamos.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoCuotaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.view.PagoCuotaOutView;

@Component
public class PagoCuotaMapper {

	public PagoCuotaOutView from(PagoCuotaResponseDTO pagoCuotaDTO) {
		PagoCuotaOutView out = new PagoCuotaOutView();
		out.setInteresesCompPeriodo(pagoCuotaDTO.getInterestAmount());
		out.setInteresesCompVto(pagoCuotaDTO.getComplementaryAmount());
		out.setOtrosImpuestos(pagoCuotaDTO.getOtherTaxesAmount());
		out.setNroComprobante(obtenerNroComprobante(pagoCuotaDTO));
		out.setFechaHora(new Date());
		out.setImporteCuota(pagoCuotaDTO.getTotalAmountReceipt());
		out.setCapitalCuota(pagoCuotaDTO.getAmortizationAmount());
		out.setIva(pagoCuotaDTO.getIva());
		out.setImporteCuotaUVA(pagoCuotaDTO.getTotalAmountUE());
		out.setCapitalUVA(pagoCuotaDTO.getCapitalAmountUE());
		out.setInteresesUVA(pagoCuotaDTO.getInterestAmountUE());
		out.setTea(pagoCuotaDTO.getTea());
		out.setCftnaConImpuestos(pagoCuotaDTO.getCftna());
		out.setCftnaSinImpuestos(pagoCuotaDTO.getCftnaWithoutTax());
		out.setMoneda(pagoCuotaDTO.getCurrencyCode());
		return out;
	}

	private String obtenerNroComprobante(PagoCuotaResponseDTO pagoCuotaDTO) {
		try {
			return pagoCuotaDTO.getOperationIdentificationNumber().trim().substring(14, 22);
		} catch (Exception e) {
			return pagoCuotaDTO.getReceiptNumber();
		}
	}
}
