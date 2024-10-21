package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.view;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

public class NumeroCuentaFavoritaView {

	private String numeroCuenta;
	
	private String cbu;
	
	private String alias;
	
	
	public NumeroCuentaFavoritaView() {
		super();
	}
	
	public NumeroCuentaFavoritaView(Cliente cliente) {
		this.numeroCuenta = buscarCuenta(cliente);
	}
		
    public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public String buscarCuenta (Cliente cliente) {
    	List<Cuenta> cuentasPago = cliente.getCuentasParaEfectuarPago(); 
    	for (Cuenta cuenta : cuentasPago) {
    		if (cuenta.getIsFavorita()) {
    			this.cbu = cuenta.getCbu();
    			this.alias = cuenta.getAlias();
    			return ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuenta);
    		}
    	}
    	
    	if (StringUtils.isEmpty(this.numeroCuenta)) {
    		this.cbu = cuentasPago.get(0).getCbu();
    		this.alias = cuentasPago.get(0).getAlias();
    		return ISBANStringUtils.formatearNroCuentaProductoConSucursal
    				(cuentasPago.get(0));
    	}
    	return StringUtils.EMPTY;
    	
    }
    

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
}
