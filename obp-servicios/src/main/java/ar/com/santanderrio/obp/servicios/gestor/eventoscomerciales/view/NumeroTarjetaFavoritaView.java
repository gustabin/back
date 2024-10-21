package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.bo.impl.TarjetaBOUtils;

public class NumeroTarjetaFavoritaView {

	
	private String numeroCuenta;
	
	private String numeroTarjeta;
	
	
	public NumeroTarjetaFavoritaView (Cliente cliente) {
		this.numeroCuenta = TarjetaBOUtils.buscarTarjeta(cliente);
		this.numeroTarjeta = this.numeroCuenta;
	}
	
	
	public NumeroTarjetaFavoritaView() {
		super();
	}

	
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
        
}
