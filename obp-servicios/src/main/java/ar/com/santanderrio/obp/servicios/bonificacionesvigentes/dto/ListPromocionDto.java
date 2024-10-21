package ar.com.santanderrio.obp.servicios.bonificacionesvigentes.dto;

import java.util.List;

public class ListPromocionDto {
	
	private List<PromocionDto> result;

	public List<PromocionDto> getResult() {
		return result;
	}

	public void setResult(List<PromocionDto> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (result != null && !result.isEmpty()) {
			for (PromocionDto dto : result) {
				sb.append(dto.toString());
			}
		}else {
			sb.append("Sin promociones");
		}
		return sb.toString();
	}
	
	

}
