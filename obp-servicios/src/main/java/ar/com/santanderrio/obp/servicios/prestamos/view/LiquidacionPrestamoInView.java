package ar.com.santanderrio.obp.servicios.prestamos.view;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.servicios.prestamos.entity.TipoOfertaEnum;

/**
 * 
 * Representa la solicitud de un prestamo por parte del front
 *
 */
public class LiquidacionPrestamoInView {

	// datos validacion
	private String tokenPrisma;

	private TipoOfertaEnum tipoOferta;

	private String tokenSms;

	private String nup;

	private String id;

	private String nroCuenta;

	private BigDecimal disponible;

	public String getTokenPrisma() {
		return tokenPrisma;
	}

	public void setTokenPrisma(String tokenPrisma) {
		this.tokenPrisma = tokenPrisma;
	}

	public TipoOfertaEnum getTipoOferta() {
		return tipoOferta;
	}

	public void setTipoOferta(TipoOfertaEnum tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

	public String getTokenSms() {
		return tokenSms;
	}

	public void setTokenSms(String tokenSms) {
		this.tokenSms = tokenSms;
	}

	public String getNup() {
		return nup;
	}

	public void setNup(String nup) {
		this.nup = nup;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public BigDecimal getDisponible() {
		return disponible;
	}

	public void setDisponible(BigDecimal disponible) {
		this.disponible = disponible;
	}

}
