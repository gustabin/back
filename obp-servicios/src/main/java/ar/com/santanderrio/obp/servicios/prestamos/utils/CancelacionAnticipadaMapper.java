package ar.com.santanderrio.obp.servicios.prestamos.utils;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelacionAnticipadaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.view.CancelacionAnticipadaOutView;

@Component
public class CancelacionAnticipadaMapper {


	public CancelacionAnticipadaOutView from(CancelacionAnticipadaResponseDTO cancelacionAnticipadaResponseDTO) {
		CancelacionAnticipadaOutView out = new CancelacionAnticipadaOutView();
		out.setNroPrestamo(cancelacionAnticipadaResponseDTO.getLoanNumber());
		out.setNroCuenta(cancelacionAnticipadaResponseDTO.getAcountNumber());
		out.setMontoTotal(cancelacionAnticipadaResponseDTO.getTotalAmount());
		out.setMontoCapital(cancelacionAnticipadaResponseDTO.getCapitalAmount());
		out.setMontoInteres(cancelacionAnticipadaResponseDTO.getInterestAmount());
		out.setIva(cancelacionAnticipadaResponseDTO.getIva());
		out.setIvaAdicional(cancelacionAnticipadaResponseDTO.getIvaAdditional());
		out.setIvaTotal(cancelacionAnticipadaResponseDTO.getIvaTotalAmount());
		out.setOtroImpuestos(cancelacionAnticipadaResponseDTO.getOtherTaxesAmount());
		out.setImpuestoFinanciero(cancelacionAnticipadaResponseDTO.getFinancialDebtTaxAmount());
		out.setIibb(cancelacionAnticipadaResponseDTO.getIibbTaxAmount());
		out.setMontoComisiones(cancelacionAnticipadaResponseDTO.getCommissionTotalAmount());
		out.setMontoTotalUE(cancelacionAnticipadaResponseDTO.getTotalAmountUE());
		out.setMontoCapitalUE(cancelacionAnticipadaResponseDTO.getCapitalAmountUE());
		out.setMontoInteresUE(cancelacionAnticipadaResponseDTO.getInterestAmountUE());
		out.setCotizacionUE(cancelacionAnticipadaResponseDTO.getExpositionUnitCotizationUE());
		out.setFechaCotizacionUE(cancelacionAnticipadaResponseDTO.getCotizationDateUE());
		out.setNio(cancelacionAnticipadaResponseDTO.getNio());
		return out;
	}

}
