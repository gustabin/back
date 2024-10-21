package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto;

public class OfertaPoliticaPrivacidadDTO {

	private OfertaComercialDTO oferta;
	
	private Boolean avisoGecOfertaPoliticasPrivacidad = false;

	public OfertaComercialDTO getOferta() {
		return oferta;
	}

	public void setOferta(OfertaComercialDTO oferta) {
		this.oferta = oferta;
	}

	public Boolean getAvisoGecOfertaPoliticasPrivacidad() {
		return avisoGecOfertaPoliticasPrivacidad;
	}

	public void setAvisoGecOfertaPoliticasPrivacidad(Boolean avisoGecOfertaPoliticasPrivacidad) {
		this.avisoGecOfertaPoliticasPrivacidad = avisoGecOfertaPoliticasPrivacidad;
	}
		
}
